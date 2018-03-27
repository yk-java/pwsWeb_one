package com.glens.pwCloudOs.pm.taskMgr.task.service;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONObject;
import com.glens.eap.platform.framework.beans.PageBean;
import com.glens.eap.platform.framework.beans.UserToken;
import com.glens.eap.platform.framework.codeCenter.CodeWorker;
import com.glens.eap.platform.framework.context.EAPContext;
import com.glens.eap.platform.framework.service.impl.EAPAbstractService;
import com.glens.eap.sys.orgEmployee.orgunit.dao.OrgUnitDao;
import com.glens.eap.sys.orgEmployee.orgunit.vo.OrgUnit;
import com.glens.pwCloudOs.common.dao.CommonDao;
import com.glens.pwCloudOs.om.deviceMgr.device.dao.DeviceDao;
import com.glens.pwCloudOs.pm.taskMgr.task.dao.TaskDao;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

public class TaskService extends EAPAbstractService {
	
	private TaskDao taskDao;

	public TaskDao getTaskDao() {
		return taskDao;
	}

	public void setTaskDao(TaskDao taskDao) {
		this.taskDao = taskDao;
	}
	DeviceDao deviceDao;
	
	public DeviceDao getDeviceDao() {
		return deviceDao;
	}

	public void setDeviceDao(DeviceDao deviceDao) {
		this.deviceDao = deviceDao;
	}
	
	CommonDao commonDao;
	

	public CommonDao getCommonDao() {
		return commonDao;
	}

	public void setCommonDao(CommonDao commonDao) {
		this.commonDao = commonDao;
	}
	
	OrgUnitDao orgUnitDao;

	public OrgUnitDao getOrgUnitDao() {
		return orgUnitDao;
	}

	public void setOrgUnitDao(OrgUnitDao orgUnitDao) {
		this.orgUnitDao = orgUnitDao;
	}

	public int importExcel(MultipartFile multiFile, UserToken token) {
		try {
			/* 查询设备属性 */
			Map<String, Object> params=new HashMap<String,Object>();
			params.put("companyCode", token.getCompanyCode());
			params.put("deviceTypeCode", "P02");// 写死设备类型
			List<Map<String, Object>> attrList = deviceDao.getMctDeviceAttr1(params);
			/* 查询项目信息 */
			Map<String, String> params2 = new HashMap<String, String>();
			params2.put("companyCode", token.getCompanyCode());
			List<Map<String, String>> proList = commonDao.getActivePro(params2);
			/* 查询单位信息 */
			List<OrgUnit> unitList = orgUnitDao.queryOrgUnitList(token.getCompanyCode());
			/* 读取Excel */
			List<LinkedHashMap<String, Object>> res= readExcel(multiFile.getInputStream());
			/* 组织数据 */
			List<DBObject> vals=new ArrayList<DBObject>();
			for (Iterator<LinkedHashMap<String, Object>> iterator = res.iterator(); iterator.hasNext();) {
				LinkedHashMap<String, Object> object = iterator.next();
				DBObject obj=new BasicDBObject();
				for (Iterator iterator2 = object.keySet().iterator(); iterator2.hasNext();) {
					String key = (String) iterator2.next();
					String ename = getEname(attrList, key);
					obj.put(ename, object.get(key));
				}
				obj.put("commpanyCode", token.getCompanyCode());
				String unitName_p = (String)object.get("施工单位");
				String proName_p = unitName_p+"_电表运维";
				OrgUnit unit = getUnit(unitList, unitName_p);
				Map<String, String> pro = getPro(proList, proName_p);
				obj.put("proNo", pro.get("proNo"));
				obj.put("proName", pro.get("proName"));
				obj.put("unitCode", unit.getUnitCode());
				obj.put("unitName", unit.getUnitName());
				
				obj.put("deviceObjType", "P02");// 写死设备类型
//				obj.put("deviceCode", "");
				
				CodeWorker codeWorker = (CodeWorker) EAPContext.getContext().getBean(
						CodeWorker.SIMPLE_CODE_WORKER);
				String taskCode = codeWorker.createCode("T");
				obj.put("taskCode", taskCode);
				
				String taskName = (String)object.get("终端号")+"_"+(String)object.get("故障类型");
				obj.put("taskName", taskName);
				
				obj.put("taskStatus", 1);
				obj.put("overdueStatus", 1);
				obj.put("taskResultFlag", null);
				
				
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				String today = sdf.format(new Date());
				Calendar calendar=Calendar.getInstance();
				calendar.add(Calendar.DATE, 3);
				String threeDay = sdf.format(calendar.getTime());
				obj.put("startTime1", today);
				obj.put("endTime1", threeDay);
				obj.put("sysCreateTime", new Date());
				
				vals.add(obj);
			}
			/* 保存入库 */
			int rows = taskDao.batchSave(vals);
			return rows;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	private Map<String, String> getPro(List<Map<String, String>> proList, String proName_p) {
		for (Iterator iterator = proList.iterator(); iterator.hasNext();) {
			Map<String, String> map = (Map<String, String>) iterator.next();
			if(proName_p.equals(map.get("proName"))){
				return map;
			}
		}
		return null;
	}

	private OrgUnit getUnit(List<OrgUnit> orgNodes, String unitName_p) {
		for (Iterator iterator = orgNodes.iterator(); iterator.hasNext();) {
			OrgUnit map = (OrgUnit) iterator.next();
			if(unitName_p.equals(map.getUnitName())){
				return map;
			}
		}
		return null;
	}

	private String getEname(List<Map<String, Object>> attrs, String cname){
		for (Iterator iterator = attrs.iterator(); iterator.hasNext();) {
			Map<String, Object> map = (Map<String, Object>) iterator.next();
			if(cname.equals(map.get("cname"))){
				return (String)map.get("ename");
			}
		}
		return "";
	}
	
	private List<LinkedHashMap<String, Object>> readExcel(InputStream ins){
		XSSFWorkbook workbook = null;
		try {
			workbook = new XSSFWorkbook(ins);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		
		List<LinkedHashMap<String, Object>> res = new ArrayList<LinkedHashMap<String, Object>>();
		try {
			/* Get first sheet */
			XSSFSheet sheet = workbook.getSheetAt(0);
			/* Get first row, get table headers */
			List<String> ths = new ArrayList<String>();
			XSSFRow rowFirst = sheet.getRow(0);
			for(int i=0; true; i++){
				XSSFCell cell = rowFirst.getCell(i);
				if(cell==null){
					break;
				}
				String val = cell.getStringCellValue();
				ths.add(val);
			}
			
			
			XSSFRow row;
			LinkedHashMap<String, Object> bean;
			for (int i = 1; true; i++) {

				/* Get row by index */
				row = sheet.getRow(i);
				if (row != null) {
					bean = new LinkedHashMap<String, Object>();
					/* Set value to bean */
					for (int j=0; j<ths.size(); j++) {
						String th = ths.get(j);
						/* Get cell value */
						XSSFCell cell = row.getCell(j);
						if(cell!=null){
							String val;
							try {
								val = cell.getStringCellValue();
							} catch (Exception e) {
								Date val_d = cell.getDateCellValue();
								val = new SimpleDateFormat("yyyy-MM-dd").format(val_d);
							}
							bean.put(th, val);
						}else{
							bean.put(th, "");
						}
					}
					res.add(bean);
				} else {
					/* Done, Current row is null, break */
					break;
				}
			}
		} catch (Exception e) {
			throw new RuntimeException("ERROR: Get data error", e);
		}
		return res;
	}
	
	public List<Map<String, Object>> getUserTask(Map<String, Object> params){
		List<Map<String, Object>> res = taskDao.getUserTask(params);
		return res;
	}
	
	public List<Map<String, Object>> getUserTaskDetail(Map<String, Object> params){
		List<Map<String, Object>> res = taskDao.getUserTaskDetail(params);
		return res;
	}
	public Map<String, Integer> findTaskCount(Map<String, Object> params){
		Map<String, Integer> map = taskDao.findTaskCount(params);
		return map;
	}
	public Map<String, Integer> findTaskCountInRange(Map<String, Object> params){
		Map<String, Integer> map = taskDao.findTaskCountInRange(params);
		return map;
	}
	public int saveTaskOpInfo(Map<String, Object> map) {
		int rows = taskDao.saveTaskOpInfo(map);
		return rows;
	}
	public int batSaveTaskOpInfo(Map<String, Object> map) {
		int rows = 0;
		String taskCodes = (String)map.get("taskCodes");
		String [] taskCodes_arr = taskCodes.split(",");
		for (int i = 0; i < taskCodes_arr.length; i++) {
			String taskCode = taskCodes_arr[i];
			map.put("taskCode", taskCode);
			int r=taskDao.saveTaskOpInfo(map);
			rows+=r;
		}
		return rows;
	}
	public PageBean getList(Map<String, Object> params) {

		int currentPage = (Integer) params.get("currentPage");
		int perpage = (Integer) params.get("perpage");

		Map<String, Object> queryParams = new HashMap<String, Object>();
		queryParams.put("companyCode", params.get("companyCode"));

		
		if(params.get("taskStatus")!=null&&Integer.parseInt(params.get("taskStatus").toString())!=0){
			queryParams.put("taskStatus", Integer.parseInt(params.get("taskStatus").toString()));
		}
		
		
		if(params.get("proNo")!=null && !params.get("proNo").equals("") ){
			queryParams.put("proNo", params.get("proNo").toString());
		}
		
		
		

		if(params.get("overdueStatus")!=null&&Integer.parseInt(params.get("overdueStatus").toString())!=0){
			queryParams.put("overdueStatus", Integer.parseInt(params.get("overdueStatus").toString()));
		}

		if(params.get("taskResultFlag")!=null&&Integer.parseInt(params.get("taskResultFlag").toString())!=3){  //3全部
			queryParams.put("taskResultFlag", Integer.parseInt(params.get("taskResultFlag").toString()));
		}
		
		String startTime1="";
		if(params.get("startTime1")!=null && !params.get("startTime1").equals("") ){
			startTime1=params.get("startTime1").toString();
		}
		String endtime1="";
		if(params.get("endTime1")!=null && !params.get("endTime1").equals("") ){
			endtime1=params.get("endTime1").toString();
		}

		Object searchName = params.get("searchName");
		if (params.get("attrJson") != null && !"".equals(params.get("attrJson"))) {

			String attrJson = (String) params.get("attrJson");
			Map<String, Object> attrMap = JSONObject.parseObject(attrJson);
			Iterator<String> it = attrMap.keySet().iterator();
			while (it.hasNext()) {

				String key = it.next();
				if(attrMap.get(key)==null || "".equals(attrMap.get(key))){
					continue;
				}
				queryParams.put(key, attrMap.get(key));
			}
		}

		String paramJson = JSONObject.toJSONString(queryParams);
		PageBean page = taskDao.getList(paramJson, searchName,startTime1,endtime1,currentPage,perpage);

		return page;
	}
	
	
	public Map<String, Object> getTask(String taskCode) {
		
		Map<String, Object> mctDevice = taskDao.getTask(taskCode);
		
		return mctDevice;
	}
	
	public int updateTask(Map<String, Object> params) {
		
		int mctDevice = taskDao.updateTask(params);
		
		return mctDevice;
	}
	
	
	

}
