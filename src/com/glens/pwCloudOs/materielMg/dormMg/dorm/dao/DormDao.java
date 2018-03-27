/**
 * @Title: DormDao.java
 * @Package com.glens.pwCloudOs.materielMg.dormMg.dorm.dao
 * @Description: TODO
 * Copyright: Copyright (c) 2016 
 * Company:南京量为石信息科技有限公司
 * @author 
 * @date 2016-6-24 下午5:27:25
 * @version V1.0
 */


package com.glens.pwCloudOs.materielMg.dormMg.dorm.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.glens.eap.platform.core.annotation.MybatisNamespaceProcessor;
import com.glens.eap.platform.core.orm.mybatis.exception.MyBatisAccessException;
import com.glens.eap.platform.framework.beans.PageBean;
import com.glens.eap.platform.framework.dao.impl.EAPAbstractDao;

/**
 * 
 * 
 * @author 
 * @version V1.0
 */

@MybatisNamespaceProcessor(value="com.glens.pwCloudOs.materielMg.dormMg.dorm.dao.DormVoMapper")
public class DormDao extends EAPAbstractDao {

	public List<Map<String, String>> selectBedByDormCode(Map<String, String> params) {

		try {

			return this.getSqlSession().selectList(getSqlStatement("selectBedByDormCode"), params);
		}
		catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql=" + getSqlStatement("selectBedByDormCode"), e);
		}
	}

	public List<Map<String, Object>> selectRentDorm(Map<String, Object> params) {

		try {

			return this.getSqlSession().selectList(getSqlStatement("selectRentDorm"), params);
		}
		catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql=" + getSqlStatement("selectRentDorm"), e);
		}
	}

	public List<Map<String, Object>> getContract(Map<String, Object> params) {

		try {

			return this.getSqlSession().selectList(getSqlStatement("getContract"), params);
		}
		catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql=" + getSqlStatement("getContract"), e);
		}
	}

	public List<Map<String, Object>> getBilling(Map<String, Object> params) {

		try {

			return this.getSqlSession().selectList(getSqlStatement("getBilling"), params);
		}
		catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql=" + getSqlStatement("getBilling"), e);
		}
	}


	public List<Map<String, Object>> checkDorm(Map<String, Object> params) {

		try {

			return this.getSqlSession().selectList(getSqlStatement("getDetail"), params);
		}
		catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql=" + getSqlStatement("getDetail"), e);
		}
	}

	public boolean insertContract(Object parameters) {
		// TODO Auto-generated method stub

		try {

			this.getSqlSession().insert(getSqlStatement("insertContract"), parameters);

			return true;
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new MyBatisAccessException("exe sql=" + getSqlStatement("insertContract"), e);

		}
	}

	public boolean insertInvoice(Object parameters) {
		// TODO Auto-generated method stub

		try {

			this.getSqlSession().insert(getSqlStatement("insertInvoice"), parameters);

			return true;
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new MyBatisAccessException("exe sql=" + getSqlStatement("insertInvoice"), e);

		}
	}

	public boolean insertDormPerson(Object parameters) {
		// TODO Auto-generated method stub

		try {

			this.getSqlSession().insert(getSqlStatement("insertDormPerson"), parameters);

			return true;
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new MyBatisAccessException("exe sql=" + getSqlStatement("insertDormPerson"), e);

		}
	}





	public boolean deleteContract(Object parameter) {
		// TODO Auto-generated method stub

		try {

			this.getSqlSession().delete(getSqlStatement("deleteContract"), parameter);
			return true;
		}
		catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql=" + getSqlStatement("deleteContract"), e);
		}
	}

	public boolean deleteInvoice(Object parameter) {
		// TODO Auto-generated method stub

		try {

			this.getSqlSession().delete(getSqlStatement("deleteInvoice"), parameter);
			return true;
		}
		catch (Exception e) {
			// TODO: handle exception

			throw new MyBatisAccessException("exe sql=" + getSqlStatement("deleteInvoice"), e);

		}

	}



	public boolean deletePerson(Object parameter) {
		// TODO Auto-generated method stub

		try {

			this.getSqlSession().delete(getSqlStatement("deletePerson"), parameter);
			return true;
		}
		catch (Exception e) {
			// TODO: handle exception

			throw new MyBatisAccessException("exe sql=" + getSqlStatement("deletePerson"), e);

		}

	}

	public boolean updateNum(Object parameter) {
		// TODO Auto-generated method stub

		try {

			this.getSqlSession().update(getSqlStatement("updateNum"), parameter);
			return true;
		}
		catch (Exception e) {
			// TODO: handle exception

			throw new MyBatisAccessException("exe sql=" + getSqlStatement("updateNum"), e);

		}

	}

	public boolean updatePro(Object parameter) {
		// TODO Auto-generated method stub

		try {

			this.getSqlSession().update(getSqlStatement("updatePro"), parameter);
			return true;
		}
		catch (Exception e) {
			// TODO: handle exception

			throw new MyBatisAccessException("exe sql=" + getSqlStatement("updatePro"), e);

		}

	}

	public boolean updateStatus(Object parameter) {
		// TODO Auto-generated method stub

		try {

			this.getSqlSession().update(getSqlStatement("updateStatus"), parameter);
			return true;
		}
		catch (Exception e) {
			// TODO: handle exception

			throw new MyBatisAccessException("exe sql=" + getSqlStatement("updateStatus"), e);

		}

	}




	public boolean updateDormMess(Object parameter) {
		// TODO Auto-generated method stub

		try {

			this.getSqlSession().update(getSqlStatement("updateDormMess"), parameter);
			return true;
		}
		catch (Exception e) {
			// TODO: handle exception

			throw new MyBatisAccessException("exe sql=" + getSqlStatement("updateDormMess"), e);

		}

	}

	//判断该人员是否有在租的宿舍
	public List getPersonDorm(Object parameter){
		try {

			return this.getSqlSession().selectList(getSqlStatement("getPersonDorm"), parameter);
		}
		catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql=" + getSqlStatement("getPersonDorm"), e);
		}
	}
	
	
	public List getDormRentNums(Object parameter){
		try {

			return this.getSqlSession().selectList(getSqlStatement("getDormRentNums"), parameter);
		}
		catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql=" + getSqlStatement("getDormRentNums"), e);
		}
	}

	






	public PageBean queryHasbedForPage(Map params){
		PageBean pageBean=this.queryForPage(params, "queryHasbedForCount", "queryHasbedForPage");
		return pageBean;
	} 

	//宿舍退租
	public Map<String, Object> dormScrap(Map<String, Object> params) {

		try {

			return this.getSqlSession().selectOne(getSqlStatement("dormScrap"), params);
		}
		catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql=" + getSqlStatement("dormScrap"), e);
		}
	}





	public PageBean queryForPage1(Map parameters, int currentPage, int perpage) {

		PageBean page = null;

		try {

			int totalCount = this.queryForCount1(parameters);
			if (totalCount < 1) {

				page = new PageBean();
				page.setList(new ArrayList());
			}
			else {

				page = new PageBean(totalCount, currentPage, perpage);
				page = this.queryForPage1(parameters, page);
			}
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new MyBatisAccessException("exe sql=" + getSqlStatement("getDormList"), e);
		}

		return page;
	}

	public int queryForCount1(Map parameters) {
		// TODO Auto-generated method stub
		try {

			return (Integer) this.getSqlSession().selectOne(getSqlStatement("queryForCount1"), parameters);
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new MyBatisAccessException("exe sql=" + getSqlStatement("queryForCount1"), e);
		}
	}
	private PageBean queryForPage1(Map parameters, PageBean page) {
		// TODO Auto-generated method stub
		try {

			if (parameters == null) {
				parameters = new HashMap();
			}

			parameters.put("firstResult", page.getPerpage() * (page.getCurrentPage() - 1));
			parameters.put("maxResult", page.getPerpage());

			List list = this.getSqlSession().selectList(getSqlStatement("getDormList"), parameters);
			page.setList(list);
		}
		catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql=" + getSqlStatement("getDormList"), e);
		}

		return page;
	}



}
