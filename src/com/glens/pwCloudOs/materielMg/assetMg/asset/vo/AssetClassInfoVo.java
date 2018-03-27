/**
 * @Title: AssetClassInfoVo.java
 * @Package com.glens.pwCloudOs.materielMg.assetMg.asset.vo
 * @Description: TODO
 * Copyright: Copyright (c) 2016 
 * Company:南京量为石信息科技有限公司
 * @author 
 * @date 2016-6-27 下午3:36:13
 * @version V1.0
 */


package com.glens.pwCloudOs.materielMg.assetMg.asset.vo;

import com.glens.eap.platform.core.beans.ValueObject;

/**
 * 
 * 
 * @author 
 * @version V1.0
 */

public class AssetClassInfoVo implements ValueObject {

	private int count;
	
	private Double amount;
	
	private String assetClassCode;
	
	private String assetClassName;
	
	private Double avgAmount;
	
	/**
	 * 资产分类标识1--普通资产分类；2--宿舍
	 */
	private int assetClassFlag;

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
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

	public Double getAvgAmount() {
		return avgAmount;
	}

	public void setAvgAmount(Double avgAmount) {
		this.avgAmount = avgAmount;
	}

	public int getAssetClassFlag() {
		return assetClassFlag;
	}

	public void setAssetClassFlag(int assetClassFlag) {
		this.assetClassFlag = assetClassFlag;
	}
	
}
