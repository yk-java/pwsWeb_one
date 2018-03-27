/**
 * @Title: ContractPageBeanResult.java
 * @Package com.glens.pwCloudOs.fm.contractBase.vo
 * @Description: TODO
 * Copyright: Copyright (c) 2016 
 * Company:南京量为石信息科技有限公司
 * @author 
 * @date 2017-3-28 上午11:09:42
 * @version V1.0
 */


package com.glens.pwCloudOs.fm.contractBase.vo;

import java.util.List;
import java.util.Map;

import com.glens.eap.platform.framework.beans.PageBean;
import com.glens.eap.platform.framework.web.support.response.PageBeanResult;

/**
 * 
 * 
 * @author 
 * @version V1.0
 */

public class ContractPageBeanResult extends PageBeanResult {

	private List<Map<String, Object>> yearList;

	public List<Map<String, Object>> getYearList() {
		return yearList;
	}

	public void setYearList(List<Map<String, Object>> yearList) {
		this.yearList = yearList;
	}
	
	/**
	
	  * <p>Title: setPageBean</p>
	
	  * <p>Description: </p>
	
	  * @param page
	
	  * @see com.glens.eap.platform.framework.web.support.response.PageBeanResult#setPageBean(com.glens.eap.platform.framework.beans.PageBean)
	
	  **/
	
	
	@Override
	public void setPageBean(PageBean page) {
		// TODO Auto-generated method stub
		
		super.setPageBean(page);
		
		ContractPageBean contractPage = (ContractPageBean) page;
		this.yearList = contractPage.getYearList();
	}
	
}
