/**
 * @Title: MctDeviceOpLogAspectj.java
 * @Package com.glens.pwCloudOs.om.deviceMgr.device.aop
 * @Description: TODO
 * Copyright: Copyright (c) 2016 
 * Company:南京量为石信息科技有限公司
 * @author 
 * @date 2016-11-2 下午2:32:08
 * @version V1.0
 */


package com.glens.pwCloudOs.om.deviceMgr.device.aop;

import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.regex.Pattern;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.dao.DataAccessException;
import org.springframework.data.mongodb.core.CollectionCallback;

import com.alibaba.fastjson.JSONObject;
import com.glens.eap.platform.core.utils.DateTimeUtil;
import com.glens.eap.platform.framework.beans.UserToken;
import com.glens.pwCloudOs.om.deviceMgr.device.dao.PmDeviceOpLogMongoDao;
import com.glens.pwCloudOs.om.deviceMgr.device.vo.PmDeviceOpLogVo;
import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MongoException;
import com.mongodb.util.JSON;

/**
 * 
 * 
 * @author 
 * @version V1.0
 */

@Aspect
public class MctDeviceOpLogAspect {

	private static final int OP_INSERT = 1;
	
	private static final int OP_UPDATE = 2;
	
	private static final int OP_DELETE = 3;
	
	private static final int OP_SELECT = 4;
	
	private static final int OP_BATCH_UPDATE = 5;
	
	private static final int OP_AUDIT_CHECK = 6;
	
	private PmDeviceOpLogMongoDao opLogDao;
	
	@Around(value="execution(* com.glens.pwCloudOs.om.deviceMgr.device.dao.MctDeviceMongoDao.insertMctDevice(..))")
	public Object insertDeviceLog(ProceedingJoinPoint jp) throws Throwable {
		
		Object[] args = jp.getArgs();
		//插入的值
		String mctDeviceJson = (String) args[0];
		UserToken token = (UserToken) args[1];
		Object pvt = jp.proceed(args);
		
		DBObject updateObj = (DBObject) JSON.parse(mctDeviceJson);
		String deviceObjCode = (String) updateObj.get("deviceObjCode");
		String deviceObjName = (String) updateObj.get("deviceObjName");
		saveOpLog(token,deviceObjCode, deviceObjName, "新增设备:" + mctDeviceJson, 1, OP_INSERT);
		
		return pvt;
	}
	
	@Around(value="execution(* com.glens.pwCloudOs.om.deviceMgr.device.dao.MctDeviceMongoDao.updateMctDevice(..))")
	public Object updateDeviceLog(ProceedingJoinPoint jp) throws Throwable {
		
		Object[] args = jp.getArgs();
		//修改的值
		final String mctDeviceJson = (String) args[0];
		UserToken token = (UserToken) args[1];
		Object pvt = jp.proceed(args);
		int icount = (Integer) pvt;
		if (icount > 0) {
			
			final DBObject updateObj = (DBObject) JSON.parse(mctDeviceJson);
			
			DBObject queryResult = this.opLogDao.getMongoTemplate().execute("PM_MCT_LIST", new CollectionCallback<DBObject>() {
				
				/**
				
				  * <p>Title: doInCollection</p>
				
				  * <p>Description: </p>
				
				  * @param collection
				  * @return
				  * @throws MongoException
				  * @throws DataAccessException
				
				  * @see org.springframework.data.mongodb.core.CollectionCallback#doInCollection(com.mongodb.DBCollection)
				
				  **/
				
				
				@Override
				public DBObject doInCollection(DBCollection collection)
						throws MongoException, DataAccessException {
					// TODO Auto-generated method stub
					BasicDBObject query = new BasicDBObject();
					query.put("deviceObjCode", updateObj.get("deviceObjCode"));
					DBObject obj = collection.findOne(query);
					
					return obj;
				}
				
			});
			
			String opDesc = "修改设备信息成功！修改前:" + JSONObject.toJSONString(queryResult) 
					+ ";修改的信息:" + mctDeviceJson;
			String deviceObjCode = (String) updateObj.get("deviceObjCode");
			String deviceObjName = (String) updateObj.get("deviceObjName");
			saveOpLog(token,deviceObjCode, deviceObjName, opDesc, icount, OP_UPDATE);
		}
		
		return pvt;
		
	}
	
	@Around(value="execution(* com.glens.pwCloudOs.om.deviceMgr.device.dao.MctDeviceMongoDao.batchUpdateMctDevice(..))")
	public int batchUpdateMctDevice(ProceedingJoinPoint jp) throws Throwable {
		
		Object[] args = jp.getArgs();
		//修改的值
		final Map<String, Object> params = (Map<String, Object>) args[0];
		UserToken token = (UserToken) args[1];
		Object pvt = jp.proceed(args);
		int icount = (Integer) pvt;
		if (icount > 0) {
			
			Map<String, Object> queryParams = new HashMap<String, Object>();
			queryParams.put("companyCode", params.get("companyCode"));
			if (params.get("mctCode") != null && !"".equals(params.get("mctCode"))) {
				queryParams.put("mctCode", params.get("mctCode"));
			}
			if (params.get("deviceTypeCode") != null && !"".equals(params.get("deviceTypeCode"))) {
				queryParams.put("deviceTypeCode", params.get("deviceTypeCode"));
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
			if (searchName != null && !"".equals(searchName)) {
				String[] packet = searchName.toString().split(";");
				if (packet.length > 1) {
					
					String[] fields = packet[0].split(",");
					String searchValue = packet[1];
					if (searchValue != null && !"".equals(searchValue)) {
						
						Pattern pattern = Pattern.compile("^.*" + searchValue + ".*$", Pattern.CASE_INSENSITIVE);
						BasicDBList values = new BasicDBList();
						for (String field : fields) {
							
							values.add(new BasicDBObject(field, pattern));
						}
						
						values.add(new BasicDBObject("deviceObjName", pattern));
						values.add(new BasicDBObject("deviceObjCode", pattern));
						queryParams.put("$or", values);
					}
				}
			}
			
			String ename = (String)params.get("fieldEname");
			Object val = params.get("fieldValue");
			
			String opDesc = "修改的查询条件为:" + JSONObject.toJSONString(queryParams) 
					+ ";字段:" + ename + "修改为:" + val;
			saveOpLog(token, "", "", opDesc, icount, OP_BATCH_UPDATE);
		}
		
		return icount;
	}
	
	@Around(value="execution(* com.glens.pwCloudOs.om.deviceMgr.device.dao.MctDeviceMongoDao.deleteMctDevice(..))")
	public int deleteMctDeviceLog(ProceedingJoinPoint jp) throws Throwable {
		
		Object[] args = jp.getArgs();
		String deviceObjCode = (String) args[0];
		DBCollection collection = this.opLogDao.getMongoTemplate().getCollection("PM_MCT_LIST");
		BasicDBObject query = new BasicDBObject();
		query.put("deviceObjCode", deviceObjCode);
		DBObject obj = collection.findOne(query);
		UserToken token = (UserToken) args[1];
		Object pvt = jp.proceed(args);
		int icount = (Integer) pvt;
		if (icount > 0) {
			
			String opDesc = "删除了设备【代码为" + deviceObjCode + "】";
			saveOpLog(token, deviceObjCode, obj.get("deviceObjName").toString(), opDesc, icount, OP_DELETE);
		}
		
		return icount;
	}
	
	@Around(value="execution(* com.glens.pwCloudOs.om.deviceMgr.device.dao.MctDeviceMongoDao.updateDeviceLoc(..))")
	public Object updateDevicLoceLog(ProceedingJoinPoint jp) throws Throwable {
		
		Object[] args = jp.getArgs();
		//修改的值
		String mctDeviceJson = (String) args[0];
		UserToken token = (UserToken) args[1];
		DBObject updateObj = (DBObject) JSON.parse(mctDeviceJson);
		DBCollection collection = this.opLogDao.getMongoTemplate().getCollection("PM_MCT_LIST");
		BasicDBObject query = new BasicDBObject();
		query.put("deviceObjCode", updateObj.get("deviceObjCode"));
		DBObject obj = collection.findOne(query);
		Object pvt = jp.proceed(args);
		int icount = (Integer) pvt;
		if (icount > 0) {
			
			String opDesc = "原坐标:x=" + obj.get("x") + ",y=" + obj.get("y") + ",rx=" + obj.get("rx") 
					+ ",ry=" + obj.get("ry") + ";调整后坐标:"+ updateObj.get("x") + ",y=" + updateObj.get("y") + ",rx=" 
					+ updateObj.get("rx") + ",ry=" + updateObj.get("ry");
			String deviceObjCode = (String) obj.get("deviceObjCode");
			String deviceObjName = (String) obj.get("deviceObjName");
			saveOpLog(token, deviceObjCode, deviceObjName, opDesc, icount, OP_UPDATE);
		}
		
		return pvt;
		
	}
	
	@Around(value="execution(* com.glens.pwCloudOs.om.deviceMgr.device.dao.MctDeviceMongoDao.deviceQualityCheck(..))")
	public Object deviceQualityCheck(ProceedingJoinPoint jp) throws Throwable {
		
		Object[] args = jp.getArgs();
		//修改的值
		Map params = (Map) args[0];
		UserToken token = (UserToken) args[1];
		Object pvt = jp.proceed(args);
		int icount = (Integer) pvt;
		if (icount > 0) {
			
			DBCollection collection = this.opLogDao.getMongoTemplate().getCollection("PM_MCT_LIST");
			BasicDBObject query = new BasicDBObject();
			query.put("deviceObjCode", params.get("deviceObjCode"));
			DBObject obj = collection.findOne(query);
			
			String opDesc = query.get("deviceObjName") + "审核结果为:" + params.get("QUALITY_AUDIT") 
					+ ",审核意见为:" + params.get("AUDIT_SUGGEST");
			String deviceObjCode = (String) obj.get("deviceObjCode");
			String deviceObjName = (String) obj.get("deviceObjName");
			saveOpLog(token, deviceObjCode, deviceObjName, opDesc, icount, OP_AUDIT_CHECK);
		}
		
		return pvt;
		
	}
	
	private void saveOpLog(UserToken token, String deviceObjCode, String deviceObjName, String opDesc, int affectRecordNum, int opType) {
		
		try {
			PmDeviceOpLogVo vo = new PmDeviceOpLogVo();
			vo.setAccountCode(token.getAccountCode());
			vo.setAccountName(token.getAccountName());
			vo.setAffectRecordNum(affectRecordNum);
			vo.setEmployeeCode(token.getEmployeeCode());
			vo.setCompanyCode(token.getCompanyCode());
			vo.setEmployeeName(token.getEmployeeName());
			vo.setUnitCode(token.getUnitCode());
			vo.setUnitName(token.getUnitName());
			vo.setOpType(opType);
			vo.setOpTime(DateTimeUtil.formatDate(new Date(), DateTimeUtil.FORMAT_1));
			vo.setOpDesc(opDesc);
			vo.setRemarks("");
			vo.setDeviceObjCode(deviceObjCode);
			vo.setDeviceObjName(deviceObjName);
			
			this.opLogDao.insertLog(vo);
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public PmDeviceOpLogMongoDao getOpLogDao() {
		return opLogDao;
	}

	public void setOpLogDao(PmDeviceOpLogMongoDao opLogDao) {
		this.opLogDao = opLogDao;
	}
	
}
