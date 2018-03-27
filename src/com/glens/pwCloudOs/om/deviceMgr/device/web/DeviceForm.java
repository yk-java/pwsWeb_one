/**
 * @Title: DeviceForm.java
 * @Package com.glens.pwCloudOs.om.deviceMgr.device.web
 * @Description: TODO
 * Copyright: Copyright (c) 2016 
 * Company:南京量为石信息科技有限公司
 * @author 
 * @date 2016-8-22 下午4:51:20
 * @version V1.0
 */


package com.glens.pwCloudOs.om.deviceMgr.device.web;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import java.util.zip.GZIPInputStream;

import javax.servlet.http.HttpServletRequest;




import org.apache.commons.beanutils.BeanUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.glens.eap.platform.core.web.ControllerForm;

/**
 * 
 * 
 * @author 
 * @version V1.0
 */

public class DeviceForm extends ControllerForm {

	private String companyCode;
	
	private String proNo;
	
	private String mctCode;
	
	private String attrJson;
	
	private String userAttrJson;//用户 表号
	
	public String getUserAttrJson() {
		return userAttrJson;
	}

	public void setUserAttrJson(String userAttrJson) {
		this.userAttrJson = userAttrJson;
	}

	private String deviceObjCode;
	private String deviceObjCodes;
	
	
	public String getDeviceObjCodes() {
		return deviceObjCodes;
	}

	public void setDeviceObjCodes(String deviceObjCodes) {
		this.deviceObjCodes = deviceObjCodes;
	}

	private String deviceObjName;
	
	private String codes;
	
	public String getCodes() {
		return codes;
	}

	public void setCodes(String codes) {
		this.codes = codes;
	}

	private double x;
	
	private double y;
	
	private String xqAddressCode;
	
	private String xqAddressName;
	
	private String addressCode;
	
	private String addressName;
	
	private String qrCode;
	
	private String problemCode;
	
	private String problemName;
	
	private String healthCode;
	
	private String healthName;
	
	private String remarks;
	
	private String proName;
	
	private String searchName;
	
	private String mctViewCode;
	
	private String deviceTypeCode;
	
	private String groupField;
	
	private String fieldEname;
	private String fieldValue;
	private int fieldDtype;
	
	private String deviceimg1;
	private String deviceimg2;
	private String deviceimg3;
	private String deviceimg4;
	/*private String deviceimg4;
	private String deviceimg5;
	private String deviceimg6;
	
	public String getDeviceimg4() {
		return deviceimg4;
	}

	public void setDeviceimg4(String deviceimg4) {
		this.deviceimg4 = deviceimg4;
	}

	public String getDeviceimg5() {
		return deviceimg5;
	}

	public void setDeviceimg5(String deviceimg5) {
		this.deviceimg5 = deviceimg5;
	}

	public String getDeviceimg6() {
		return deviceimg6;
	}

	public void setDeviceimg6(String deviceimg6) {
		this.deviceimg6 = deviceimg6;
	}*/

	public String getDeviceimg4() {
		return deviceimg4;
	}

	public void setDeviceimg4(String deviceimg4) {
		this.deviceimg4 = deviceimg4;
	}

	private String xs;
	
	private String ys;
	
	private float r;
	private String QUALITY_AUDIT;
	private String startTime;
	private String endTime;
	private String qcStartTime;
	private String qcEndTime;
	
	private String AUDITOR_CODE;
	private String AUDITOR_NAME;
	private String AUDIT_TIME;
	private String AUDIT_SUGGEST;
	
	private String hasPic;
	private String hasPosition;
	
	private String dependFields;
	
	
	public String getQcStartTime() {
		return qcStartTime;
	}

	public void setQcStartTime(String qcStartTime) {
		this.qcStartTime = qcStartTime;
	}

	public String getQcEndTime() {
		return qcEndTime;
	}

	public void setQcEndTime(String qcEndTime) {
		this.qcEndTime = qcEndTime;
	}

	public String getHasPic() {
		return hasPic;
	}

	public void setHasPic(String hasPic) {
		this.hasPic = hasPic;
	}

	public String getHasPosition() {
		return hasPosition;
	}

	public void setHasPosition(String hasPosition) {
		this.hasPosition = hasPosition;
	}

	private double rx;
	
	private double ry;
	
	
	public String getAUDITOR_CODE() {
		return AUDITOR_CODE;
	}

	public void setAUDITOR_CODE(String aUDITOR_CODE) {
		AUDITOR_CODE = aUDITOR_CODE;
	}

	public String getAUDITOR_NAME() {
		return AUDITOR_NAME;
	}

	public void setAUDITOR_NAME(String aUDITOR_NAME) {
		AUDITOR_NAME = aUDITOR_NAME;
	}

	public String getAUDIT_TIME() {
		return AUDIT_TIME;
	}

	public void setAUDIT_TIME(String aUDIT_TIME) {
		AUDIT_TIME = aUDIT_TIME;
	}

	public String getAUDIT_SUGGEST() {
		return AUDIT_SUGGEST;
	}

	public void setAUDIT_SUGGEST(String aUDIT_SUGGEST) {
		AUDIT_SUGGEST = aUDIT_SUGGEST;
	}

	public String getQUALITY_AUDIT() {
		return QUALITY_AUDIT;
	}

	public void setQUALITY_AUDIT(String qUALITY_AUDIT) {
		QUALITY_AUDIT = qUALITY_AUDIT;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getDeviceimg1() {
		return deviceimg1;
	}

	public void setDeviceimg1(String deviceimg1) {
		this.deviceimg1 = deviceimg1;
	}

	public String getDeviceimg2() {
		return deviceimg2;
	}

	public void setDeviceimg2(String deviceimg2) {
		this.deviceimg2 = deviceimg2;
	}

	public String getDeviceimg3() {
		return deviceimg3;
	}

	public void setDeviceimg3(String deviceimg3) {
		this.deviceimg3 = deviceimg3;
	}
	
	public int getFieldDtype() {
		return fieldDtype;
	}

	public void setFieldDtype(int fieldDtype) {
		this.fieldDtype = fieldDtype;
	}

	public String getFieldEname() {
		return fieldEname;
	}

	public void setFieldEname(String fieldEname) {
		this.fieldEname = fieldEname;
	}

	public String getFieldValue() {
		return fieldValue;
	}

	public void setFieldValue(String fieldValue) {
		this.fieldValue = fieldValue;
	}

	/**
	
	 * <p>Title: doPreToMap</p>
	
	 * <p>Description: </p>
	
	 * @return
	
	 * @see com.glens.eap.platform.core.web.ControllerForm#doPreToMap()
	
	 **/
	private String FAULT_TYPE;
	private String FAULT_TYPE_NAME;
	private String FAULT_DETAIL;
	private String IS_RECTIFY;
	
	
	public String getFAULT_TYPE() {
		return FAULT_TYPE;
	}

	public void setFAULT_TYPE(String fAULT_TYPE) {
		FAULT_TYPE = fAULT_TYPE;
	}

	public String getFAULT_TYPE_NAME() {
		return FAULT_TYPE_NAME;
	}

	public void setFAULT_TYPE_NAME(String fAULT_TYPE_NAME) {
		FAULT_TYPE_NAME = fAULT_TYPE_NAME;
	}

	public String getFAULT_DETAIL() {
		return FAULT_DETAIL;
	}

	public void setFAULT_DETAIL(String fAULT_DETAIL) {
		FAULT_DETAIL = fAULT_DETAIL;
	}

	public String getIS_RECTIFY() {
		return IS_RECTIFY;
	}

	public void setIS_RECTIFY(String iS_RECTIFY) {
		IS_RECTIFY = iS_RECTIFY;
	}

	@Override
	protected Map doPreToMap() {
		// TODO Auto-generated method stub

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("companyCode", companyCode);
		params.put("proNo", proNo);
		params.put("mctCode", mctCode);
		params.put("attrJson", attrJson);
		params.put("userAttrJson", userAttrJson);
		params.put("deviceObjCode", deviceObjCode);
		params.put("deviceObjName", deviceObjName);
		params.put("x", x);
		params.put("y", y);
		params.put("xqAddressCode", xqAddressCode);
		params.put("xqAddressName", xqAddressName);
		params.put("addressCode", addressCode);
		params.put("addressName", addressName);
		params.put("qrCode", qrCode);
		params.put("problemCode", problemCode);
		params.put("problemName", problemName);
		params.put("healthCode", healthCode);
		params.put("healthName", healthName);
		params.put("remarks", remarks);
		params.put("proName", proName);
		params.put("searchName", searchName);
		params.put("mctViewCode", mctViewCode);
		params.put("deviceTypeCode", deviceTypeCode);
		params.put("groupField", groupField);
		params.put("fieldEname", fieldEname);
		params.put("fieldValue", fieldValue);
		params.put("fieldDtype", fieldDtype);
		params.put("xs", xs);
		params.put("ys", ys);
		params.put("r", r);
		params.put("deviceimg1", deviceimg1);
		params.put("deviceimg2", deviceimg2);
		params.put("deviceimg3", deviceimg3);
		params.put("deviceimg4", deviceimg4);
		params.put("QUALITY_AUDIT", QUALITY_AUDIT);
		params.put("AUDITOR_CODE", AUDITOR_CODE);
		params.put("AUDITOR_NAME", AUDITOR_NAME);
		params.put("AUDIT_TIME", AUDIT_TIME);
		params.put("AUDIT_SUGGEST", AUDIT_SUGGEST);
		params.put("startTime", startTime);
		params.put("endTime", endTime);
		params.put("qcStartTime", qcStartTime);
		params.put("qcEndTime", qcEndTime);
		params.put("rx", rx);
		params.put("ry", ry);
		params.put("deviceObjCodes", deviceObjCodes);
		params.put("hasPic", hasPic);
		params.put("hasPosition", hasPosition);
		
		params.put("FAULT_TYPE", FAULT_TYPE);
		params.put("FAULT_TYPE_NAME", FAULT_TYPE_NAME);
		params.put("FAULT_DETAIL", FAULT_DETAIL);
		params.put("IS_RECTIFY", IS_RECTIFY);
		params.put("codes", codes);
		return params;
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
	        
	        if (deviceimg1 != null && !"".equals(deviceimg1)) {
	        	
	        	deviceimg1 = deviceimg1.replaceAll(" ", "+");
	        }
	        
	        if (deviceimg2 != null && !"".equals(deviceimg2)) {
	        	
	        	deviceimg2 = deviceimg2.replaceAll(" ", "+");
	        }

			if (deviceimg3 != null && !"".equals(deviceimg3)) {
				
				deviceimg3 = deviceimg3.replaceAll(" ", "+");
			}
			if (deviceimg4 != null && !"".equals(deviceimg4)) {
				
				deviceimg4 = deviceimg4.replaceAll(" ", "+");
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

	/**
	
	 * <p>Title: doPostRequest</p>
	
	 * <p>Description: </p>
	
	 * @param request
	
	 * @see com.glens.eap.platform.core.web.ControllerForm#doPostRequest(javax.servlet.http.HttpServletRequest)
	
	 **/

	@Override
	protected void doPostRequest(HttpServletRequest request) {
		// TODO Auto-generated method stub

	}

	/**
	
	 * <p>Title: getGenerateKey</p>
	
	 * <p>Description: </p>
	
	 * @return
	
	 * @see com.glens.eap.platform.core.web.ControllerForm#getGenerateKey()
	
	 **/

	@Override
	public Object getGenerateKey() {
		// TODO Auto-generated method stub

		return null;
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

	public String getMctCode() {
		return mctCode;
	}

	public void setMctCode(String mctCode) {
		this.mctCode = mctCode;
	}

	public String getAttrJson() {
		return attrJson;
	}

	public void setAttrJson(String attrJson) {
		this.attrJson = attrJson;
	}

	public String getDeviceObjCode() {
		return deviceObjCode;
	}

	public void setDeviceObjCode(String deviceObjCode) {
		this.deviceObjCode = deviceObjCode;
	}

	public String getDeviceObjName() {
		return deviceObjName;
	}

	public void setDeviceObjName(String deviceObjName) {
		this.deviceObjName = deviceObjName;
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	public String getXqAddressCode() {
		return xqAddressCode;
	}

	public void setXqAddressCode(String xqAddressCode) {
		this.xqAddressCode = xqAddressCode;
	}

	public String getXqAddressName() {
		return xqAddressName;
	}

	public void setXqAddressName(String xqAddressName) {
		this.xqAddressName = xqAddressName;
	}

	public String getAddressCode() {
		return addressCode;
	}

	public void setAddressCode(String addressCode) {
		this.addressCode = addressCode;
	}

	public String getAddressName() {
		return addressName;
	}

	public void setAddressName(String addressName) {
		this.addressName = addressName;
	}

	public String getQrCode() {
		return qrCode;
	}

	public void setQrCode(String qrCode) {
		this.qrCode = qrCode;
	}

	public String getProblemCode() {
		return problemCode;
	}

	public void setProblemCode(String problemCode) {
		this.problemCode = problemCode;
	}

	public String getProblemName() {
		return problemName;
	}

	public void setProblemName(String problemName) {
		this.problemName = problemName;
	}

	public String getHealthCode() {
		return healthCode;
	}

	public void setHealthCode(String healthCode) {
		this.healthCode = healthCode;
	}

	public String getHealthName() {
		return healthName;
	}

	public void setHealthName(String healthName) {
		this.healthName = healthName;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getProName() {
		return proName;
	}

	public void setProName(String proName) {
		this.proName = proName;
	}

	public String getSearchName() {
		return searchName;
	}

	public void setSearchName(String searchName) {
		this.searchName = searchName;
	}

	public String getMctViewCode() {
		return mctViewCode;
	}

	public void setMctViewCode(String mctViewCode) {
		this.mctViewCode = mctViewCode;
	}

	public String getDeviceTypeCode() {
		return deviceTypeCode;
	}

	public void setDeviceTypeCode(String deviceTypeCode) {
		this.deviceTypeCode = deviceTypeCode;
	}

	public String getGroupField() {
		return groupField;
	}

	public void setGroupField(String groupField) {
		this.groupField = groupField;
	}

	public String getXs() {
		return xs;
	}

	public void setXs(String xs) {
		this.xs = xs;
	}

	public String getYs() {
		return ys;
	}

	public void setYs(String ys) {
		this.ys = ys;
	}

	public float getR() {
		return r;
	}

	public void setR(float r) {
		this.r = r;
	}

	public double getRx() {
		return rx;
	}

	public void setRx(double rx) {
		this.rx = rx;
	}

	public double getRy() {
		return ry;
	}

	public void setRy(double ry) {
		this.ry = ry;
	}

	public String getDependFields() {
		return dependFields;
	}

	public void setDependFields(String dependFields) {
		this.dependFields = dependFields;
	}

}
