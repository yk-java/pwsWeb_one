package com.glens.pwCloudOs.pe.baseMgr.remouldBatch.web;

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
import com.glens.eap.platform.core.annotation.ValueObjectProcessor;
import com.glens.eap.platform.core.web.ControllerForm;

@ValueObjectProcessor(clazz="com.glens.pwCloudOs.pe.baseMgr.remouldBatch.vo.RemouldBatchVo")
public class RemouldBatchForm extends ControllerForm {

	private Long rowid;
	private String companyCode;
	private String reserveProNo;
	private String reserveProName;
	private String remouldBatchCode;
	private String remouldBatchName;
	private String remouldSdate;
	private String remouldEdate;
	private int remouldCount;
	private String remouldExtent;
	private String remarks;
	
	private String planRebuildTime;
	
	public String getPlanRebuildTime() {
		return planRebuildTime;
	}

	public void setPlanRebuildTime(String planRebuildTime) {
		this.planRebuildTime = planRebuildTime;
	}
	private String proNo;
	
	private String mctCode;
	
	private String attrJson;
	
	private String deviceObjCode;
	
	private String deviceObjName;
	
	private float x;
	
	private float y;
	
	private String xqAddressCode;
	
	private String xqAddressName;
	
	private String addressCode;
	
	private String addressName;
	
	private String qrCode;
	
	private String problemCode;
	
	private String problemName;
	
	private String healthCode;
	
	private String healthName;
	
	private String proName;
	
	private String searchName;
	
	private String mctViewCode;
	
	private String deviceTypeCode;
	
	private String remouldSchemeCode;
	
	private String rpAuditState;
	
	private String auditPersonCode;
	
	private String auditPersonName;
	
	private String auditDate;
	
	private String auditSuggest;
	
	private String sysProcessFlag;
	
	private String materialType;
	
	private String materialTypeCount;
	
	private String materialTypeAmount;
	private String reseverOrgcode;
	private String reseverOrgname;
	
	private String price;//单价
	private String deviceBhIds;//表号
	private String deviceByqName;
	private String deviceLineName;
	private String deviceBxId;
	
	private String reserveB1Img;
	private String reserveB2Img;
	private String reserveA1Img;
	private String reserveA2Img;
	private String reserveOpImg;
	private String reserveSignImg;
	private String reservePcutImg;
	
	
	public String getReserveB1Img() {
		return reserveB1Img;
	}

	public void setReserveB1Img(String reserveB1Img) {
		this.reserveB1Img = reserveB1Img;
	}

	public String getReserveB2Img() {
		return reserveB2Img;
	}

	public void setReserveB2Img(String reserveB2Img) {
		this.reserveB2Img = reserveB2Img;
	}

	public String getReserveA1Img() {
		return reserveA1Img;
	}

	public void setReserveA1Img(String reserveA1Img) {
		this.reserveA1Img = reserveA1Img;
	}

	public String getReserveA2Img() {
		return reserveA2Img;
	}

	public void setReserveA2Img(String reserveA2Img) {
		this.reserveA2Img = reserveA2Img;
	}

	public String getReserveOpImg() {
		return reserveOpImg;
	}

	public void setReserveOpImg(String reserveOpImg) {
		this.reserveOpImg = reserveOpImg;
	}

	public String getReserveSignImg() {
		return reserveSignImg;
	}

	public void setReserveSignImg(String reserveSignImg) {
		this.reserveSignImg = reserveSignImg;
	}

	public String getReservePcutImg() {
		return reservePcutImg;
	}

	public void setReservePcutImg(String reservePcutImg) {
		this.reservePcutImg = reservePcutImg;
	}

	public String getDeviceByqName() {
		return deviceByqName;
	}

	public void setDeviceByqName(String deviceByqName) {
		this.deviceByqName = deviceByqName;
	}

	public String getDeviceLineName() {
		return deviceLineName;
	}

	public void setDeviceLineName(String deviceLineName) {
		this.deviceLineName = deviceLineName;
	}

	public String getDeviceBxId() {
		return deviceBxId;
	}

	public void setDeviceBxId(String deviceBxId) {
		this.deviceBxId = deviceBxId;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getDeviceBhIds() {
		return deviceBhIds;
	}

	public void setDeviceBhIds(String deviceBhIds) {
		this.deviceBhIds = deviceBhIds;
	}

	public String getReseverOrgname() {
		return reseverOrgname;
	}

	public void setReseverOrgname(String reseverOrgname) {
		this.reseverOrgname = reseverOrgname;
	}

	public String getReseverOrgcode() {
		return reseverOrgcode;
	}

	public void setReseverOrgcode(String reseverOrgcode) {
		this.reseverOrgcode = reseverOrgcode;
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

	public String getReserveProNo() {
		return reserveProNo;
	}

	public void setReserveProNo(String reserveProNo) {
		this.reserveProNo = reserveProNo;
	}

	public String getReserveProName() {
		return reserveProName;
	}

	public void setReserveProName(String reserveProName) {
		this.reserveProName = reserveProName;
	}

	public String getRemouldBatchCode() {
		return remouldBatchCode;
	}

	public void setRemouldBatchCode(String remouldBatchCode) {
		this.remouldBatchCode = remouldBatchCode;
	}

	public String getRemouldBatchName() {
		return remouldBatchName;
	}

	public void setRemouldBatchName(String remouldBatchName) {
		this.remouldBatchName = remouldBatchName;
	}

	public String getRemouldSdate() {
		return remouldSdate;
	}

	public void setRemouldSdate(String remouldSdate) {
		this.remouldSdate = remouldSdate;
	}

	public String getRemouldEdate() {
		return remouldEdate;
	}

	public void setRemouldEdate(String remouldEdate) {
		this.remouldEdate = remouldEdate;
	}

	public int getRemouldCount() {
		return remouldCount;
	}

	public void setRemouldCount(int remouldCount) {
		this.remouldCount = remouldCount;
	}

	public String getRemouldExtent() {
		return remouldExtent;
	}

	public void setRemouldExtent(String remouldExtent) {
		this.remouldExtent = remouldExtent;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	private String reserveConfirm;
	private String reserveStatus;

	public String getReserveConfirm() {
		return reserveConfirm;
	}

	public void setReserveConfirm(String reserveConfirm) {
		this.reserveConfirm = reserveConfirm;
	}

	public String getReserveStatus() {
		return reserveStatus;
	}

	public void setReserveStatus(String reserveStatus) {
		this.reserveStatus = reserveStatus;
	}
	
	@Override
	public void setRequest(HttpServletRequest request) {
		// TODO Auto-generated method stub
		super.setRequest(request);
		GZIPInputStream in;
		try {
			//System.out.println(request.getInputStream());
			
			
			in = new GZIPInputStream(request.getInputStream());
			byte [] b= new byte[1024];
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
	        if (reserveB1Img != null && !"".equals(reserveB1Img)) {
	        	reserveB1Img = reserveB1Img.replaceAll(" ", "+");
	        }
	        if (reserveB2Img != null && !"".equals(reserveB2Img)) {
	        	reserveB2Img = reserveB2Img.replaceAll(" ", "+");
	        }
			if (reserveA1Img != null && !"".equals(reserveA1Img)) {
				reserveA1Img = reserveA1Img.replaceAll(" ", "+");
			}
			if (reserveA2Img != null && !"".equals(reserveA2Img)) {
				reserveA2Img = reserveA2Img.replaceAll(" ", "+");
			}
			if (reserveOpImg != null && !"".equals(reserveOpImg)) {
				reserveOpImg = reserveOpImg.replaceAll(" ", "+");
			}
			if (reserveSignImg != null && !"".equals(reserveSignImg)) {
				reserveSignImg = reserveSignImg.replaceAll(" ", "+");
			}
			if (reservePcutImg != null && !"".equals(reservePcutImg)) {
				reservePcutImg = reservePcutImg.replaceAll(" ", "+");
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
	protected Map doPreToMap() {
		Map<String, String> params = new HashMap<String, String>();
		params.put("reserveProNo", reserveProNo);
		params.put("companyCode", companyCode);
		params.put("remouldBatchName", remouldBatchName);
		params.put("remouldBatchCode", remouldBatchCode);
		params.put("reseverOrgcode", reseverOrgcode);
		params.put("reseverOrgname", reseverOrgname);
		// TODO Auto-generated method stub
				params.put("proNo", proNo);
				params.put("mctCode", mctCode);
				params.put("attrJson", attrJson);
				params.put("deviceObjCode", deviceObjCode);
				params.put("deviceObjName", deviceObjName);
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
				params.put("reserveProName", reserveProName);
				params.put("remouldSchemeCode", remouldSchemeCode);
				params.put("rpAuditState", rpAuditState);
				params.put("auditPersonCode", auditPersonCode);
				params.put("auditPersonName", auditPersonName);
				params.put("auditDate", auditDate);
				params.put("auditSuggest", auditSuggest);
				params.put("sysProcessFlag", sysProcessFlag);
				params.put("materialType", materialType);
				params.put("materialTypeCount", materialTypeCount);
				params.put("materialTypeAmount", materialTypeAmount);
				params.put("price", price);
				params.put("deviceBhIds", deviceBhIds);
				params.put("deviceByqName", deviceByqName);
				params.put("deviceLineName", deviceLineName);
				params.put("deviceBxId", deviceBxId);
				
				params.put("reserveB1Img", reserveB1Img);
				params.put("reserveB2Img", reserveB2Img);
				params.put("reserveA1Img", reserveA1Img);
				params.put("reserveA2Img", reserveA2Img);
				params.put("reserveOpImg", reserveOpImg);
				params.put("reserveSignImg", reserveSignImg);
				params.put("reservePcutImg", reservePcutImg);

				params.put("reserveConfirm", reserveConfirm);
				params.put("reserveStatus", reserveStatus);
				params.put("planRebuildTime", planRebuildTime);
				
				
				
		return params;
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

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
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

	public String getRemouldSchemeCode() {
		return remouldSchemeCode;
	}

	public void setRemouldSchemeCode(String remouldSchemeCode) {
		this.remouldSchemeCode = remouldSchemeCode;
	}

	public String getRpAuditState() {
		return rpAuditState;
	}

	public void setRpAuditState(String rpAuditState) {
		this.rpAuditState = rpAuditState;
	}

	public String getAuditPersonCode() {
		return auditPersonCode;
	}

	public void setAuditPersonCode(String auditPersonCode) {
		this.auditPersonCode = auditPersonCode;
	}

	public String getAuditPersonName() {
		return auditPersonName;
	}

	public void setAuditPersonName(String auditPersonName) {
		this.auditPersonName = auditPersonName;
	}

	public String getAuditDate() {
		return auditDate;
	}

	public void setAuditDate(String auditDate) {
		this.auditDate = auditDate;
	}

	public String getAuditSuggest() {
		return auditSuggest;
	}

	public void setAuditSuggest(String auditSuggest) {
		this.auditSuggest = auditSuggest;
	}

	public String getSysProcessFlag() {
		return sysProcessFlag;
	}

	public void setSysProcessFlag(String sysProcessFlag) {
		this.sysProcessFlag = sysProcessFlag;
	}

	public String getMaterialType() {
		return materialType;
	}

	public void setMaterialType(String materialType) {
		this.materialType = materialType;
	}

	public String getMaterialTypeCount() {
		return materialTypeCount;
	}

	public void setMaterialTypeCount(String materialTypeCount) {
		this.materialTypeCount = materialTypeCount;
	}

	public String getMaterialTypeAmount() {
		return materialTypeAmount;
	}

	public void setMaterialTypeAmount(String materialTypeAmount) {
		this.materialTypeAmount = materialTypeAmount;
	}

}
