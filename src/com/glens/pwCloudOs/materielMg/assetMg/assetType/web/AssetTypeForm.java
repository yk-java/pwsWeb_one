/**
 * @Title: AssetForm.java
 * @Package com.glens.pwCloudOs.materielMg.assetMg.assetType.web
 * @Description: TODO
 * Copyright: Copyright (c) 2016 
 * Company:南京量为石信息科技有限公司
 * @author 
 * @date 2016-6-22 上午11:21:31
 * @version V1.0
 */


package com.glens.pwCloudOs.materielMg.assetMg.assetType.web;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.glens.eap.platform.core.annotation.ValueObjectProcessor;
import com.glens.eap.platform.core.beans.ValueObject;
import com.glens.eap.platform.core.web.ControllerForm;
import com.glens.eap.platform.framework.codeCenter.CodeWorker;
import com.glens.eap.platform.framework.context.EAPContext;
import com.glens.pwCloudOs.materielMg.assetMg.assetType.vo.AssetTypeVo;

/**
 * 
 * 
 * @author 
 * @version V1.0
 */

@ValueObjectProcessor(clazz="com.glens.pwCloudOs.materielMg.assetMg.assetType.vo.AssetTypeVo")
public class AssetTypeForm extends ControllerForm {

    private String assetTypeCode;

    private String assetTypeName;

    private String assetClassCode;
    
    private String assetTypeShortName;

    private String brand;

    private String modelNo;

    private String forms;

    private Integer lifetime;

    private String measureUnit;

    private Integer dailyRent;

    private String dailyRentUnit;

    private Integer isExattr;

    private String remarks;
    
    private String assetCharacterCode;
	
	/**
	
	 * <p>Title: doPreToMap</p>
	
	 * <p>Description: </p>
	
	 * @return
	
	 * @see com.glens.eap.platform.core.web.ControllerForm#doPreToMap()
	
	 **/

	@Override
	protected Map doPreToMap() {
		// TODO Auto-generated method stub

		Map<String, String> params = new HashMap<String, String>();
		params.put("assetCharacterCode", assetCharacterCode);
		params.put("assetClassCode", assetClassCode);
		
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
		
		AssetTypeVo vo = (AssetTypeVo) super.toVo();
		if (vo.getAssetTypeCode() == null 
				|| "".equals(vo.getAssetTypeCode())) {
			
			CodeWorker simpleCodeWorker = (CodeWorker) EAPContext.getContext().getBean("simpleCodeWorker");
			vo.setAssetTypeCode(simpleCodeWorker.createCode("T"));
		}
		
		return vo;
	}

	public String getAssetTypeCode() {
		return assetTypeCode;
	}

	public void setAssetTypeCode(String assetTypeCode) {
		this.assetTypeCode = assetTypeCode;
	}

	public String getAssetTypeName() {
		return assetTypeName;
	}

	public void setAssetTypeName(String assetTypeName) {
		this.assetTypeName = assetTypeName;
	}

	public String getAssetClassCode() {
		return assetClassCode;
	}

	public void setAssetClassCode(String assetClassCode) {
		this.assetClassCode = assetClassCode;
	}

	public String getAssetTypeShortName() {
		return assetTypeShortName;
	}

	public void setAssetTypeShortName(String assetTypeShortName) {
		this.assetTypeShortName = assetTypeShortName;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getModelNo() {
		return modelNo;
	}

	public void setModelNo(String modelNo) {
		this.modelNo = modelNo;
	}

	public String getForms() {
		return forms;
	}

	public void setForms(String forms) {
		this.forms = forms;
	}

	public Integer getLifetime() {
		return lifetime;
	}

	public void setLifetime(Integer lifetime) {
		this.lifetime = lifetime;
	}

	public String getMeasureUnit() {
		return measureUnit;
	}

	public void setMeasureUnit(String measureUnit) {
		this.measureUnit = measureUnit;
	}

	public Integer getDailyRent() {
		return dailyRent;
	}

	public void setDailyRent(Integer dailyRent) {
		this.dailyRent = dailyRent;
	}

	public String getDailyRentUnit() {
		return dailyRentUnit;
	}

	public void setDailyRentUnit(String dailyRentUnit) {
		this.dailyRentUnit = dailyRentUnit;
	}

	public Integer getIsExattr() {
		return isExattr;
	}

	public void setIsExattr(Integer isExattr) {
		this.isExattr = isExattr;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getAssetCharacterCode() {
		return assetCharacterCode;
	}

	public void setAssetCharacterCode(String assetCharacterCode) {
		this.assetCharacterCode = assetCharacterCode;
	}

}
