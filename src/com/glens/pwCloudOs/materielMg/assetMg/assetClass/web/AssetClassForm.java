/**
 * @Title: AsserClassForm.java
 * @Package com.glens.pwCloudOs.materielMg.assetMg.assetClass.web
 * @Description: TODO
 * Copyright: Copyright (c) 2016 
 * Company:南京量为石信息科技有限公司
 * @author 
 * @date 2016-6-22 上午10:41:02
 * @version V1.0
 */


package com.glens.pwCloudOs.materielMg.assetMg.assetClass.web;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.glens.eap.platform.core.annotation.ValueObjectProcessor;
import com.glens.eap.platform.core.beans.ValueObject;
import com.glens.eap.platform.core.web.ControllerForm;
import com.glens.eap.platform.framework.codeCenter.CodeWorker;
import com.glens.eap.platform.framework.context.EAPContext;
import com.glens.pwCloudOs.materielMg.assetMg.assetClass.vo.AssetClassVo;

/**
 * 
 * 
 * @author 
 * @version V1.0
 */

@ValueObjectProcessor(clazz="com.glens.pwCloudOs.materielMg.assetMg.assetClass.vo.AssetClassVo")
public class AssetClassForm extends ControllerForm {

    private String assetClassCode;
    
    private String assetClassName;

    private String assetCharacterCode;

    private Float residualRate;

    private Integer usefulLife;

    private Integer eupMonth;

    private String remarks;
    
    private String iscm;
	
	public String getIscm() {
		return iscm;
	}

	public void setIscm(String iscm) {
		this.iscm = iscm;
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

		Map<String, String> params = new HashMap<String, String>();
		params.put("assetCharacterCode", assetCharacterCode);
		
		return params;
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
		AssetClassVo vo = (AssetClassVo) super.toVo();
		if (vo != null 
				&& (vo.getAssetClassCode() == null 
				|| vo.getAssetClassCode().equals(""))) {
			
			CodeWorker simpleCodeWorker = (CodeWorker) EAPContext.getContext().getBean("simpleCodeWorker");
			vo.setAssetClassCode(simpleCodeWorker.createCode("C"));
		}
		
		return vo;
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

	public String getAssetClassCode() {
		return assetClassCode;
	}

	public void setAssetClassCode(String assetClassCode) {
		this.assetClassCode = assetClassCode;
	}

	public String getAssetClassName() {
		return assetClassName;
	}

	public void setAssetClassName(String assetClassName) {
		this.assetClassName = assetClassName;
	}

	public String getAssetCharacterCode() {
		return assetCharacterCode;
	}

	public void setAssetCharacterCode(String assetCharacterCode) {
		this.assetCharacterCode = assetCharacterCode;
	}

	public Float getResidualRate() {
		return residualRate;
	}

	public void setResidualRate(Float residualRate) {
		this.residualRate = residualRate;
	}

	public Integer getUsefulLife() {
		return usefulLife;
	}

	public void setUsefulLife(Integer usefulLife) {
		this.usefulLife = usefulLife;
	}

	public Integer getEupMonth() {
		return eupMonth;
	}

	public void setEupMonth(Integer eupMonth) {
		this.eupMonth = eupMonth;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

}
