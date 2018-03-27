/**
 * @Title: ContractPageBean.java
 * @Package com.glens.pwCloudOs.fm.contractBase.vo
 * @Description: TODO
 * Copyright: Copyright (c) 2016 
 * Company:南京量为石信息科技有限公司
 * @author 
 * @date 2017-3-28 上午9:54:48
 * @version V1.0
 */


package com.glens.pwCloudOs.fm.contractBase.vo;

import java.util.List;
import java.util.Map;

import com.glens.eap.platform.framework.beans.PageBean;

/**
 * 
 * 
 * @author 
 * @version V1.0
 */

public class ContractPageBean extends PageBean {

	private List<Map<String, Object>> yearList;
	
	/**
	  
	 * 创建一个新的实例 ContractPageBean. 

	 * <p>Title: </p>

	 * <p>Description: </p>

	 **/

	public ContractPageBean() {

		// TODO Auto-generated constructor stub
		super();
	}
	
	public ContractPageBean(int totalRecord, int currentPage, int perpage) {
		
		super(totalRecord, currentPage, perpage);
	}

	public List<Map<String, Object>> getYearList() {
		return yearList;
	}

	public void setYearList(List<Map<String, Object>> yearList) {
		this.yearList = yearList;
	}
	
}
