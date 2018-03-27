/**
 * @Title: DormProVo.java
 * @Package com.glens.pwCloudOs.materielMg.comprehensiveQuery.vo
 * @Description: TODO
 * Copyright: Copyright (c) 2016 
 * Company:南京量为石信息科技有限公司
 * @author 
 * @date 2016-7-4 上午11:36:34
 * @version V1.0
 */


package com.glens.pwCloudOs.materielMg.comprehensiveQuery.vo;

import java.util.ArrayList;
import java.util.List;

import com.glens.eap.platform.core.beans.ValueObject;
import com.glens.eap.platform.core.utils.StringUtil;

/**
 * 
 * 
 * @author 
 * @version V1.0
 */

public class DormProVo implements ValueObject {

	private String proNo;
	
	private String proName;
	
	private int memberCount;
	
	private double amount;
	
	private List<String> memberList;
	
	/**
	  
	 * 创建一个新的实例 DormProVo. 

	 * <p>Title: </p>

	 * <p>Description: </p>

	 **/

	public DormProVo() {

		// TODO Auto-generated constructor stub
		memberList = new ArrayList<String>();
		amount = 0.0f;
	}
	
	public void addMember(String member) {
		
		memberList.add(member);
		
		memberCount++;
	}
	
	public void addRentAmount(Double rentAmount) {
		
		amount += rentAmount;
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

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public int getMemberCount() {
		return memberCount;
	}

	public String getMembers() {
		
		return StringUtil.join(memberList.toArray(), ",");
	}
	
}
