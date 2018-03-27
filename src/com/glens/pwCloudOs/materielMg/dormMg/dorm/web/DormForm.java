/**
 * @Title: DormForm.java
 * @Package com.glens.pwCloudOs.materielMg.dormMg.dorm.web
 * @Description: TODO
 * Copyright: Copyright (c) 2016 
 * Company:南京量为石信息科技有限公司
 * @author 
 * @date 2016-6-24 下午5:31:34
 * @version V1.0
 */


package com.glens.pwCloudOs.materielMg.dormMg.dorm.web;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

import com.glens.eap.platform.core.annotation.ValueObjectProcessor;
import com.glens.eap.platform.core.beans.ValueObject;
import com.glens.eap.platform.core.web.ControllerForm;
import com.glens.eap.platform.framework.codeCenter.CodeWorker;
import com.glens.eap.platform.framework.context.EAPContext;
import com.glens.pwCloudOs.materielMg.dormMg.dorm.vo.DormVo;

/**
 * 
 * 
 * @author 
 * @version V1.0
 */

@ValueObjectProcessor(clazz="com.glens.pwCloudOs.materielMg.dormMg.dorm.vo.DormVo")
public class DormForm extends ControllerForm {

    private String dormCode;

    private String dormName;

    private String province;
    
    private String city;
    
    private String district;

    private String x;

    private String y;

    private Float area;

    private String housetype;

    private String frentDate;
    
    private String employeeCode;
    
    private String employeeName;
    
    private String proNo;
    
    private String proName;
    
    private String rentDate;
    
    private String returnDate;
    
    
    
    public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
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

	public String getRentDate() {
		return rentDate;
	}

	public void setRentDate(String rentDate) {
		this.rentDate = rentDate;
	}

	public String getReturnDate() {
		return returnDate;
	}

	public void setReturnDate(String returnDate) {
		this.returnDate = returnDate;
	}

	public String getEmployeeCode() {
		return employeeCode;
	}

	public void setEmployeeCode(String employeeCode) {
		this.employeeCode = employeeCode;
	}

	public String getFrentDate() {
		return frentDate;
	}

	public void setFrentDate(String frentDate) {
		this.frentDate = frentDate;
	}

	private String srentDate;

    private String erentDate;

    private Integer monthrent;

    private Integer deposit;

    private Integer maxBeds;

    private float dayrent;

    private Integer dormStatus;

    private String remarks;
    
    private String areaName;
    
    private Integer expire;  //0---正常 1--快到期
    
    private String  refundaMount;//退租金额
    
    private String dormType; //宿舍状态
    
    private String agency;//中介
    private String landlord;//房东
    private String operator;//经办人
    private String contractLease;//本次合同租期
    private String paymentMode;//支付方式
    private int  agencyAmount;//中介费
    private String refundDate;//退租时间
    private String refundDesc;//退组说明
    private MultipartFile contractDoc;
    private MultipartFile invoicejDoc;
    private String picTitle;
    private String contractDate;
    private String invoiceDate;
    private String contractScanimg;
    private String invoiceScanimg;
	
	public String getContractScanimg() {
		return contractScanimg;
	}

	public void setContractScanimg(String contractScanimg) {
		this.contractScanimg = contractScanimg;
	}

	public String getInvoiceScanimg() {
		return invoiceScanimg;
	}

	public void setInvoiceScanimg(String invoiceScanimg) {
		this.invoiceScanimg = invoiceScanimg;
	}

	public String getContractDate() {
		return contractDate;
	}

	public void setContractDate(String contractDate) {
		this.contractDate = contractDate;
	}

	public String getInvoiceDate() {
		return invoiceDate;
	}

	public void setInvoiceDate(String invoiceDate) {
		this.invoiceDate = invoiceDate;
	}

	public String getPicTitle() {
		return picTitle;
	}

	public void setPicTitle(String picTitle) {
		this.picTitle = picTitle;
	}

	public MultipartFile getContractDoc() {
		return contractDoc;
	}

	public void setContractDoc(MultipartFile contractDoc) {
		this.contractDoc = contractDoc;
	}

	public MultipartFile getInvoicejDoc() {
		return invoicejDoc;
	}

	public void setInvoicejDoc(MultipartFile invoicejDoc) {
		this.invoicejDoc = invoicejDoc;
	}

	public String getPaymentMode() {
		return paymentMode;
	}

	public void setPaymentMode(String paymentMode) {
		this.paymentMode = paymentMode;
	}

	public int getAgencyAmount() {
		return agencyAmount;
	}

	public void setAgencyAmount(int agencyAmount) {
		this.agencyAmount = agencyAmount;
	}

	public String getRefundDate() {
		return refundDate;
	}

	public void setRefundDate(String refundDate) {
		this.refundDate = refundDate;
	}

	public String getRefundDesc() {
		return refundDesc;
	}

	public void setRefundDesc(String refundDesc) {
		this.refundDesc = refundDesc;
	}

	public String getAgency() {
		return agency;
	}

	public void setAgency(String agency) {
		this.agency = agency;
	}

	public String getLandlord() {
		return landlord;
	}

	public void setLandlord(String landlord) {
		this.landlord = landlord;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public String getContractLease() {
		return contractLease;
	}

	public void setContractLease(String contractLease) {
		this.contractLease = contractLease;
	}

	public String getDormType() {
		return dormType;
	}

	public void setDormType(String dormType) {
		this.dormType = dormType;
	}

	public String getRefundaMount() {
		return refundaMount;
	}

	public void setRefundaMount(String refundaMount) {
		this.refundaMount = refundaMount;
	}

	/**
	
	 * <p>Title: doPreToMap</p>
	
	 * <p>Description: </p>
	
	 * @return
	
	 * @see com.glens.eap.platform.core.web.ControllerForm#doPreToMap()
	
	 **/

	@Override
	protected Map doPreToMap() {
		// TODO Auto-generated method stub
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("areaName", areaName);
		params.put("dormName", dormName);
		params.put("dormStatus", dormStatus);
		params.put("dormCode", dormCode);
		params.put("expire", expire);
		params.put("dormType",dormType);
		params.put("contractLease",contractLease);
		params.put("operator",operator);
		params.put("landlord",landlord);
		params.put("agency",agency);
		
		params.put("paymentMode",paymentMode);
		//params.put("agencyAmount",agencyAmount);
		params.put("refundDate",refundDate);
		params.put("refundDesc",refundDesc);
		params.put("frentDate",frentDate);
		
		params.put("employeeCode",employeeCode);
		params.put("employeeName",employeeName);
		params.put("proNo",proNo);
		params.put("proName",proName);
		params.put("rentDate",rentDate);
		params.put("returnDate",returnDate);
		
		
		    
		return params;
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
	
	/**
	
	  * <p>Title: toVo</p>
	
	  * <p>Description: </p>
	
	  * @return
	
	  * @see com.glens.eap.platform.core.web.ControllerForm#toVo()
	
	  **/
	
	
	@Override
	public ValueObject toVo() {
		// TODO Auto-generated method stub
		
		DormVo dormVo = (DormVo) super.toVo();
		if (dormVo.getDormCode() == null || "".equals(dormVo.getDormCode())) {
			
			CodeWorker simpleCodeWorker = (CodeWorker) EAPContext.getContext().getBean("simpleCodeWorker");
			dormVo.setDormCode(simpleCodeWorker.createCode("D"));
		}
		
		return dormVo;
	}

	public String getDormCode() {
		return dormCode;
	}

	public void setDormCode(String dormCode) {
		this.dormCode = dormCode;
	}

	public String getDormName() {
		return dormName;
	}

	public void setDormName(String dormName) {
		this.dormName = dormName;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getX() {
		return x;
	}

	public void setX(String x) {
		this.x = x;
	}

	public String getY() {
		return y;
	}

	public void setY(String y) {
		this.y = y;
	}

	public Float getArea() {
		return area;
	}

	public void setArea(Float area) {
		this.area = area;
	}

	public String getHousetype() {
		return housetype;
	}

	public void setHousetype(String housetype) {
		this.housetype = housetype;
	}

	public String getSrentDate() {
		return srentDate;
	}

	public void setSrentDate(String srentDate) {
		this.srentDate = srentDate;
	}

	public String getErentDate() {
		return erentDate;
	}

	public void setErentDate(String erentDate) {
		this.erentDate = erentDate;
	}

	public Integer getMonthrent() {
		return monthrent;
	}

	public void setMonthrent(Integer monthrent) {
		this.monthrent = monthrent;
	}

	public Integer getDeposit() {
		return deposit;
	}

	public void setDeposit(Integer deposit) {
		this.deposit = deposit;
	}

	public Integer getMaxBeds() {
		return maxBeds;
	}

	public void setMaxBeds(Integer maxBeds) {
		this.maxBeds = maxBeds;
	}

	public float getDayrent() {
		return dayrent;
	}

	public void setDayrent(float dayrent) {
		this.dayrent = dayrent;
	}

	public Integer getDormStatus() {
		return dormStatus;
	}

	public void setDormStatus(Integer dormStatus) {
		this.dormStatus = dormStatus;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public Integer getExpire() {
		return expire;
	}

	public void setExpire(Integer expire) {
		this.expire = expire;
	}

}
