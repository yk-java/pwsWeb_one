package com.glens.pwCloudOs.pm.taskMgr.task.web;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import java.util.zip.GZIPInputStream;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.glens.eap.platform.core.annotation.ValueObjectProcessor;
import com.glens.eap.platform.core.web.ControllerForm;

@ValueObjectProcessor(clazz = "com.glens.pwCloudOs.pm.taskMgr.task.vo.TaskVo")
public class TaskForm extends ControllerForm {
	
	private Long rowid;
	private String companyCode;
	private String proNo;
	private String proName;
	private String unitCode;
	private String unitName;
	private String employeeCode;
	private String employeeName;
	private String accountCode;
	private String deviceObjType;//设备类型
	private String deviceCode;
	private String deviceName;
	private String taskName;
	private String taskCode;
	private int taskScale;//任务级别
	private String mainTaskCode;//主任务编码
	private String mainTaskName;
	private int taskStatus;
	private String taskClassCode;//任务类型
	private int overdueStatus;
	private String startTime1;
	private String endTime1;
	private String startTime2;
	private String endTime2;
	private Integer taskResultFlag;//任务处理状态
	public Integer getTaskResultFlag() {
		return taskResultFlag;
	}

	public void setTaskResultFlag(Integer taskResultFlag) {
		this.taskResultFlag = taskResultFlag;
	}

	private String faultDesc;//故障现象
	private String sceneDesc;//现场状况
	private String img1;
	private String img2;
	private String img3;
	private String sysCreateTime;
	private String sysUpdateTime;
	private String sysDeleteTime;
	private String sysProcessFlag;
	private String remarks;
	private String taskCodes;// 任务编号...
	private String deviceFaultType;//
	private String deviceFaultDetail;//
	
	private int type;
	private int sort;
	
	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getSort() {
		return sort;
	}

	public void setSort(int sort) {
		this.sort = sort;
	}

	public String getDeviceFaultType() {
		return deviceFaultType;
	}

	public void setDeviceFaultType(String deviceFaultType) {
		this.deviceFaultType = deviceFaultType;
	}

	public String getDeviceFaultDetail() {
		return deviceFaultDetail;
	}

	public void setDeviceFaultDetail(String deviceFaultDetail) {
		this.deviceFaultDetail = deviceFaultDetail;
	}

	public String getTaskCodes() {
		return taskCodes;
	}

	public void setTaskCodes(String taskCodes) {
		this.taskCodes = taskCodes;
	}

	private String attrJson;
	public String getAttrJson() {
		return attrJson;
	}

	public void setAttrJson(String attrJson) {
		this.attrJson = attrJson;
	}

	private MultipartFile excelFile;
	
	public MultipartFile getExcelFile() {
		return excelFile;
	}

	public void setExcelFile(MultipartFile excelFile) {
		this.excelFile = excelFile;
	}

	private String searchName;
	public String getSearchName() {
		return searchName;
	}

	public void setSearchName(String searchName) {
		this.searchName = searchName;
	}

	private String terminalId;
	private String faultType;
	
	public String getTerminalId() {
		return terminalId;
	}

	public void setTerminalId(String terminalId) {
		this.terminalId = terminalId;
	}

	public String getFaultType() {
		return faultType;
	}

	public void setFaultType(String faultType) {
		this.faultType = faultType;
	}
	
	public Long getRowid() {
		return rowid;
	}

	public void setRowid(Long rowid) {
		this.rowid = rowid;
	}

	public String getCompanyCode() {
		return companyCode;
	}

	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}

	public String getProNo() {
		return proNo;
	}

	public void setProNo(String proNo) {
		this.proNo = proNo;
	}

	public String getProName() {
		return proName;
	}

	public void setProName(String proName) {
		this.proName = proName;
	}

	public String getUnitCode() {
		return unitCode;
	}

	public void setUnitCode(String unitCode) {
		this.unitCode = unitCode;
	}

	public String getUnitName() {
		return unitName;
	}

	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}

	public String getEmployeeCode() {
		return employeeCode;
	}

	public void setEmployeeCode(String employeeCode) {
		this.employeeCode = employeeCode;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public String getAccountCode() {
		return accountCode;
	}

	public void setAccountCode(String accountCode) {
		this.accountCode = accountCode;
	}

	public String getDeviceObjType() {
		return deviceObjType;
	}

	public void setDeviceObjType(String deviceObjType) {
		this.deviceObjType = deviceObjType;
	}

	public String getDeviceCode() {
		return deviceCode;
	}

	public void setDeviceCode(String deviceCode) {
		this.deviceCode = deviceCode;
	}

	public String getDeviceName() {
		return deviceName;
	}

	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public String getTaskCode() {
		return taskCode;
	}

	public void setTaskCode(String taskCode) {
		this.taskCode = taskCode;
	}

	public int getTaskScale() {
		return taskScale;
	}

	public void setTaskScale(int taskScale) {
		this.taskScale = taskScale;
	}

	public String getMainTaskCode() {
		return mainTaskCode;
	}

	public void setMainTaskCode(String mainTaskCode) {
		this.mainTaskCode = mainTaskCode;
	}

	public String getMainTaskName() {
		return mainTaskName;
	}

	public void setMainTaskName(String mainTaskName) {
		this.mainTaskName = mainTaskName;
	}

	public int getTaskStatus() {
		return taskStatus;
	}

	public void setTaskStatus(int taskStatus) {
		this.taskStatus = taskStatus;
	}

	public String getTaskClassCode() {
		return taskClassCode;
	}

	public void setTaskClassCode(String taskClassCode) {
		this.taskClassCode = taskClassCode;
	}

	public int getOverdueStatus() {
		return overdueStatus;
	}

	public void setOverdueStatus(int overdueStatus) {
		this.overdueStatus = overdueStatus;
	}

	public String getStartTime1() {
		return startTime1;
	}

	public void setStartTime1(String startTime1) {
		this.startTime1 = startTime1;
	}

	public String getEndTime1() {
		return endTime1;
	}

	public void setEndTime1(String endTime1) {
		this.endTime1 = endTime1;
	}

	public String getStartTime2() {
		return startTime2;
	}

	public void setStartTime2(String startTime2) {
		this.startTime2 = startTime2;
	}

	public String getEndTime2() {
		return endTime2;
	}

	public void setEndTime2(String endTime2) {
		this.endTime2 = endTime2;
	}

	

	public String getFaultDesc() {
		return faultDesc;
	}

	public void setFaultDesc(String faultDesc) {
		this.faultDesc = faultDesc;
	}

	public String getSceneDesc() {
		return sceneDesc;
	}

	public void setSceneDesc(String sceneDesc) {
		this.sceneDesc = sceneDesc;
	}

	public String getImg1() {
		return img1;
	}

	public void setImg1(String img1) {
		this.img1 = img1;
	}

	public String getImg2() {
		return img2;
	}

	public void setImg2(String img2) {
		this.img2 = img2;
	}

	public String getImg3() {
		return img3;
	}

	public void setImg3(String img3) {
		this.img3 = img3;
	}

	public String getSysCreateTime() {
		return sysCreateTime;
	}

	public void setSysCreateTime(String sysCreateTime) {
		this.sysCreateTime = sysCreateTime;
	}

	public String getSysUpdateTime() {
		return sysUpdateTime;
	}

	public void setSysUpdateTime(String sysUpdateTime) {
		this.sysUpdateTime = sysUpdateTime;
	}

	public String getSysDeleteTime() {
		return sysDeleteTime;
	}

	public void setSysDeleteTime(String sysDeleteTime) {
		this.sysDeleteTime = sysDeleteTime;
	}

	public String getSysProcessFlag() {
		return sysProcessFlag;
	}

	public void setSysProcessFlag(String sysProcessFlag) {
		this.sysProcessFlag = sysProcessFlag;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	@Override
	protected Map doPreToMap() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("companyCode", companyCode);
		map.put("proNo", proNo);
		map.put("searchName", searchName);
		map.put("attrJson", attrJson);
		//map.put("attrJson", attrJson);
		//map.put("attrJson", attrJson);
		////map.put("mctCode", mctCode);
		//map.put("deviceObjCode", deviceObjCode);
		map.put("taskStatus", taskStatus);
		map.put("overdueStatus", overdueStatus);
		map.put("startTime1", startTime1);
		map.put("endTime1", endTime1);
		map.put("taskResultFlag", taskResultFlag);
		
		map.put("taskCode", taskCode);
		map.put("taskCodes", taskCodes);// 批量编号
		map.put("employeeCode", employeeCode);
		map.put("employeeName", employeeName);
		
		map.put("TERMINAL_ID", terminalId);
		map.put("FAULT_TYPE", faultType);
		
		map.put("faultDesc", faultDesc);
		map.put("sceneDesc", sceneDesc);
		map.put("deviceFaultType", deviceFaultType);
		map.put("deviceFaultDetail", deviceFaultDetail);
		map.put("type", type);
		map.put("sort", sort);
		
		return map;
	}

	
	@Override
	public void setRequest(HttpServletRequest request) {
		// TODO Auto-generated method stub
		super.setRequest(request);
		GZIPInputStream in;
		try {
			//System.out.println(request.getInputStream());
			
			
			in = new GZIPInputStream(request.getInputStream());
			byte[] b= new byte[1024];
	        int temp =0;
	        ByteArrayOutputStream out = new ByteArrayOutputStream();
	        while((temp = in.read(b,0,b.length))!=-1){
	            out.write(b, 0, temp);
	        }
	        out.flush();
	        in.close();
	        out.close();
	        
	        
	        JSONObject obj = JSON.parseObject(out.toString("UTF-8"));
			
	        BeanUtils.populate(this, obj);
	        
	        if (img1 != null && !"".equals(img1)) {
	        	
	        	img1 = img1.replaceAll(" ", "+");
	        }
	        
	        if (img2 != null && !"".equals(img2)) {
	        	
	        	img2 = img2.replaceAll(" ", "+");
	        }

			if (img3 != null && !"".equals(img3)) {
				
				img3 = img3.replaceAll(" ", "+");
			}
	        
		}catch (IOException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		
	}
	
	@Override
	protected void doPostRequest(HttpServletRequest request) {
		// TODO Auto-generated method stub

	}

	@Override
	public Object getGenerateKey() {
		// TODO Auto-generated method stub
		return null;
	}

}
