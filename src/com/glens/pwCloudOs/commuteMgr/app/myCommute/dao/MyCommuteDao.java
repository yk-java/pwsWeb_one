/**

 * @Title: MyCommuteDao.java

 * @Package com.glens.pwCloudOs.commuteMgr.app.myCommute.dao

 * @Description: TODO

 * Copyright: Copyright (c) 2016 

 * Company:南京量为石信息科技有限公司

 * @author 

 * @date 2016-5-20 下午12:01:54

 * @version V1.0

 **/



package com.glens.pwCloudOs.commuteMgr.app.myCommute.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.glens.eap.platform.core.annotation.MybatisNamespaceProcessor;
import com.glens.eap.platform.framework.dao.impl.EAPAbstractDao;

/**

 * @ClassName: MyCommuteDao

 * @Description: TODO

 * @author

 * @date 2016-5-20 下午12:01:54

 **/

@MybatisNamespaceProcessor(value="myCommute")
public class MyCommuteDao extends EAPAbstractDao {
	
	public Map<String, Object> getLastCommuteConfig(String companyCode) {
		
		Map<String, Object> commuteConfig = null;
		
		try {
			
			commuteConfig = this.getSqlSession().selectOne(this.getSqlStatement("getLastCommuteConfig"), companyCode);
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return commuteConfig;
	}
	
	public Map<String, String> getMyCommuteCheckByDate(String accountCode, String date) {
		
		Map<String, String> commuteCheck = null;
		
		try {
			
			Map<String, String> params = new HashMap<String, String>();
			params.put("accountCode", accountCode);
			params.put("date", date);
			
			commuteCheck = this.getSqlSession().selectOne(this.getSqlStatement("getMyCommuteCheckByDate"), params);
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return commuteCheck;
	}
	
	public List<String> getMyRestDate(String companyCode, String unitCode, 
			String accountCode, int year, int month) {
		
		List<String> dayList = null;
		
		try {
			
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("companyCode", companyCode);
			params.put("unitCode", unitCode);
			params.put("accountCode", accountCode);
			params.put("year", year);
			params.put("month", month);
			
			dayList = this.getSqlSession().selectList(this.getSqlStatement("getMyRestDate"), params);
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return dayList;
	}
	
}
