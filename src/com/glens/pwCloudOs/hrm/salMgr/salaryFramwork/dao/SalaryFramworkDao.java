/**
 * @Title: SalaryFramworkDao.java
 * @Package com.glens.pwCloudOs.hrm.salMgr.salaryFramwork.dao
 * @Description: TODO
 * Copyright: Copyright (c) 2016 
 * Company:南京量为石信息科技有限公司
 * @author 
 * @date 2017-2-16 下午5:49:02
 * @version V1.0
 */


package com.glens.pwCloudOs.hrm.salMgr.salaryFramwork.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.glens.eap.platform.core.annotation.MybatisNamespaceProcessor;
import com.glens.eap.platform.core.beans.ValueObject;
import com.glens.eap.platform.core.orm.mybatis.exception.MyBatisAccessException;
import com.glens.eap.platform.framework.beans.PageBean;
import com.glens.eap.platform.framework.dao.impl.EAPAbstractDao;

/**
 * 
 * 
 * @author 
 * @version V1.0
 */

@MybatisNamespaceProcessor(value="com.glens.pwCloudOs.hrm.salMgr.salaryFramwork.dao.SalaryFramworkVoMapper")
public class SalaryFramworkDao extends EAPAbstractDao {

	public List<Map<String, Object>> selectFrameworkSetting(Map<String, Object> params) {
		
		try {
			
			return this.getSqlSession().selectList(this.getSqlStatement("selectFrameworkSetting"), params);
		}
		catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql=" + getSqlStatement("selectFrameworkSetting"), e);
		}
	} 
	
	public Map<String, Object> selectSalaryFrameworkDetail(Map<String, Object> params) {
		
		try {
			
			return this.getSqlSession().selectOne(this.getSqlStatement("selectSalaryFrameworkDetail"), params);
		}
		catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql=" + getSqlStatement("selectSalaryFrameworkDetail"), e);
		}
		
	}
	
	public int insertFrameworkSetting(List<Map<String, Object>> list) {
		
		try {
			
			return this.getSqlSession().insert(this.getSqlStatement("insertFrameworkSetting"), list);
		}
		catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql=" + getSqlStatement("insertFrameworkSetting"), e);
		}
	}
	
	public int deleteFrameworkSetting(Map<String, Object> params) {
		
		try {
			
			return this.getSqlSession().delete(this.getSqlStatement("deleteFrameworkSetting"), params);
		}
		catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql=" + getSqlStatement("deleteFrameworkSetting"), e);
		}
	}
	
	public List<Map<String, Object>> selectEmployeeByUnit(Map<String, Object> params) {
		
		try {
			
			return this.getSqlSession().selectList(this.getSqlStatement("selectEmployeeByUnit"), params);
		}
		catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql=" + getSqlStatement("selectEmployeeByUnit"), e);
		}
	}
	
	public List<Map<String, Object>> getJobNums(Map<String, Object> params) {
		
		try {
			
			return this.getSqlSession().selectList(this.getSqlStatement("getJobNums"), params);
		}
		catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql=" + getSqlStatement("getJobNums"), e);
		}
	}
	
	
	public Map<String, Object> selectEnableSalaryFramework(Map<String, Object> params) {
		
		try {
			
			return this.getSqlSession().selectOne(this.getSqlStatement("selectEnableSalaryFramework"), params);
		}
		catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql=" + getSqlStatement("selectEnableSalaryFramework"), e);
		}
	}
	
	public Map<String, Object> getStandSalary(Map<String, Object> params) {
		
		try {
			
			return this.getSqlSession().selectOne(this.getSqlStatement("getStandSalary"), params);
		}
		catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql=" + getSqlStatement("getStandSalary"), e);
		}
	}
	
	
	public int updateEmployeeSalaryGrade(Map<String, Object> params) {
		
		try {
			
			return this.getSqlSession().update(this.getSqlStatement("updateEmployeeSalaryGrade"), params);
		}
		catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql=" + getSqlStatement("updateEmployeeSalaryGrade"), e);
		}
	}
	
	public int updateLabourCost(Map<String, Object> params) {
		
		try {
			
			return this.getSqlSession().update(this.getSqlStatement("updateLabourCost"), params);
		}
		catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql=" + getSqlStatement("updateLabourCost"), e);
		}
	}
	
	
	public int resetEmployeeSalaryGrade(Map<String, Object> params) {
		
		try {
			
			return this.getSqlSession().update(this.getSqlStatement("resetEmployeeSalaryGrade"), params);
		}
		catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql=" + getSqlStatement("resetEmployeeSalaryGrade"), e);
		}
	}
	
	public int selectEmployeeSalaryForCount(Map<String, Object> params) {
		
		try {
			
			return this.getSqlSession().selectOne(this.getSqlStatement("selectEmployeeSalaryForCount"), params);
		}
		catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql=" + getSqlStatement("selectEmployeeSalaryForCount"), e);
		}
	}
	
	public PageBean selectEmployeeSalaryForPage(Map<String, Object> params) {
		
		try {
			int totalCount = this.selectEmployeeSalaryForCount(params);
			PageBean page = null;
			if (totalCount < 1) {
				
				page = new PageBean();
				page.setList(new ArrayList<Map<String, Object>>());
			}
			else {
				
				int currentPage = (Integer) params.get("currentPage");
				int perpage = (Integer) params.get("perpage");
				page = new PageBean(totalCount, currentPage, perpage);
				params.put("firstResult", page.getPerpage() * (page.getCurrentPage() - 1));
				params.put("maxResult", page.getPerpage());
				List<Map<String, Object>> employeeList = this.getSqlSession().
						selectList(this.getSqlStatement("selectEmployeeSalaryForPage"), params);
				page.setList(employeeList);
			}
			
			return page;
		}
		catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql=" + getSqlStatement("selectEmployeeSalaryForPage"), e);
		}
	}
	
	public List<Map<String, Object>> selectSalaryParam(Map<String, Object> params) {
		
		try {
			
			return this.getSqlSession().selectList(this.getSqlStatement("selectSalaryParam"), params);
		}
		catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql=" + getSqlStatement("selectSalaryParam"), e);
		}
	}
	
	//判断是否有启用的体系
	public List<Map<String, Object>> isStart(Map<String, Object> params) {
		
		try {
			
			return this.getSqlSession().selectList(this.getSqlStatement("isStart"), params);
		}
		catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql=" + getSqlStatement("isStart"), e);
		}
	}
	
	//启用 /关闭 体系
	public int updateStatus(Map<String, Object> params) {
		
		try {
			
			return this.getSqlSession().update(this.getSqlStatement("updateStatus"), params);
		}
		catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql=" + getSqlStatement("updateStatus"), e);
		}
	}
	
	public Map<String, Object> selectEmployeeSalaryByCode(Map<String, Object> params) {
		
		try {
			
			return this.getSqlSession().selectOne(this.getSqlStatement("selectEmployeeSalaryByCode"), params);
		}
		catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql=" + getSqlStatement("selectEmployeeSalaryByCode"), e);
		}
	}
	public Map<String, Object> selectUnitSalary(Map<String, Object> params) {
		
		try {
			
			return this.getSqlSession().selectOne(this.getSqlStatement("selectUnitSalary"), params);
		}
		catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql=" + getSqlStatement("selectUnitSalary"), e);
		}
	}
	
	public List<Map<String, Object>> selectEmployeeSalaryForList(Map<String, Object> params) {
		
		try {
			
			return this.getSqlSession().selectList(this.getSqlStatement("selectEmployeeSalaryForList"), params);
		}
		catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql=" + getSqlStatement("selectEmployeeSalaryForList"), e);
		}
	}
	
	public List<Map<String, Object>> getLabourcostByProNo(Map<String, Object> params) {
		
		try {
			
			return this.getSqlSession().selectList(this.getSqlStatement("getLabourcostByProNo"), params);
		}
		catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql=" + getSqlStatement("getLabourcostByProNo"), e);
		}
	}
	
	public List<Map<String, Object>> getCostByProPhase(Map<String, Object> params) {
		
		try {
			
			return this.getSqlSession().selectList(this.getSqlStatement("getCostByProPhase"), params);
		}
		catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql=" + getSqlStatement("getCostByProPhase"), e);
		}
	}
	
	public int updateCostByProPhase(Map<String, Object> params) {
		
		try {
			
			return this.getSqlSession().update(this.getSqlStatement("updateCostByProPhase"), params);
		}
		catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql=" + getSqlStatement("updateCostByProPhase"), e);
		}
	}
	

}
