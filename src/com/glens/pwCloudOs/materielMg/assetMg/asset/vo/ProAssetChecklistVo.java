/**
 * @Title: ProAssetChecklistVo.java
 * @Package com.glens.pwCloudOs.materielMg.assetMg.asset.vo
 * @Description: TODO
 * Copyright: Copyright (c) 2016 
 * Company:南京量为石信息科技有限公司
 * @author 
 * @date 2016-6-27 下午3:47:01
 * @version V1.0
 */


package com.glens.pwCloudOs.materielMg.assetMg.asset.vo;

import java.util.List;

import com.glens.eap.platform.core.beans.ValueObject;

/**
 * 
 * 
 * @author 
 * @version V1.0
 */

public class ProAssetChecklistVo implements ValueObject {

	private String proNo;
	
	private String proName;
	
	private List<AssetClassInfoVo> assetClassInfoList;
	
	private double totalAmount;
	
	

	
	public double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
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

	public List<AssetClassInfoVo> getAssetClassInfoList() {
		return assetClassInfoList;
	}

	public void setAssetClassInfoList(List<AssetClassInfoVo> assetClassInfoList) {
		this.assetClassInfoList = assetClassInfoList;
	}
	
}
