/**
 * @Title: CommonDao.java
 * @Package com.glens.pwCloudOs.common.dao
 * @Description: TODO
 * Copyright: Copyright (c) 2016 
 * Company:南京量为石信息科技有限公司
 * @author 
 * @date 2016-6-8 下午3:23:18
 * @version V1.0
 */


package com.glens.pwCloudOs.common.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.glens.eap.platform.core.annotation.MybatisNamespaceProcessor;
import com.glens.eap.platform.core.orm.mybatis.exception.MyBatisAccessException;
import com.glens.eap.platform.framework.beans.TreeNode;
import com.glens.eap.platform.framework.dao.impl.EAPAbstractDao;

/**
 * 
 * 
 * @author 
 * @version V1.0
 */

@MybatisNamespaceProcessor(value="CommonMapper")
public class CommonDao extends EAPAbstractDao {

	public List<TreeNode> getOrgTree() {
		
		List<TreeNode> list = null;
		
		try {
			
			list = this.getSqlSession().selectList(getSqlStatement("getOrgTree"));
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return list;
	}
	
	public List<Map<String, String>> getActivePro(Map<String, String> params) {
		try {
			return this.getSqlSession().selectList(getSqlStatement("getActivePro"), params);
		}
		catch (Exception e) {
			throw new MyBatisAccessException("exe sql=" + getSqlStatement("getActivePro"), e);
		}
	}
	
	public List<Map<String, String>> getAllPro(Map<String, String> params) {
		try {
			return this.getSqlSession().selectList(getSqlStatement("getAllPro"), params);
		}
		catch (Exception e) {
			throw new MyBatisAccessException("exe sql=" + getSqlStatement("getAllPro"), e);
		}
	}
	public List<Map<String, String>> getGaisuanPros(Map<String, String> params) {
		try {
			return this.getSqlSession().selectList(getSqlStatement("getGaisuanPros"), params);
		}
		catch (Exception e) {
			throw new MyBatisAccessException("exe sql=" + getSqlStatement("getGaisuanPros"), e);
		}
	}
	
	public List<Map<String, String>> getCheckPros(Map<String, String> params) {
		try {
			return this.getSqlSession().selectList(getSqlStatement("getCheckPros"), params);
		}
		catch (Exception e) {
			throw new MyBatisAccessException("exe sql=" + getSqlStatement("getCheckPros"), e);
		}
	}
	
	
	
	
	public List<Map<String, String>> getClosedPro(Map<String, String> params) {
		try {
			return this.getSqlSession().selectList(getSqlStatement("getClosedPro"), params);
		}
		catch (Exception e) {
			throw new MyBatisAccessException("exe sql=" + getSqlStatement("getClosedPro"), e);
		}
	}
	
	public List<Map<String, String>> selectProCategory(Map<String, Object> params) {
		
		try {
			
			return this.getSqlSession().selectList(getSqlStatement("selectProCategory"), params);
		}
		catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql=" + getSqlStatement("selectProCategory"), e);
		}
	}
	
	/**
	 * Edit by MaDx
	 * @return
	 */
	public List<Map<String, String>> selectAssetClass(Map<String, Object> params) {
		try {
			return this.getSqlSession().selectList(getSqlStatement("selectAssetClass"), params);
		}
		catch (Exception e) {
			throw new MyBatisAccessException("exe sql=" + getSqlStatement("selectAssetClass"), e);
		}
	} 
	
	public List<Map<String, String>> selectAssetCharacter() {
		
		try {
			
			return this.getSqlSession().selectList(getSqlStatement("selectAssetCharacter"));
		}
		catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql=" + getSqlStatement("selectAssetCharacter"), e);
		}
	}
	
	public List<Map<String, String>> selectVehicle() {
		
		try {
			
			return this.getSqlSession().selectList(getSqlStatement("selectVehicle"));
		}
		catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql=" + getSqlStatement("selectVehicle"), e);
		}
	}
	
	public List<Map<String, String>> vehicleInProject(String proNo) {
		try {
			return this.getSqlSession().selectList(getSqlStatement("vehicleInProject"), proNo);
		}
		catch (Exception e) {
			throw new MyBatisAccessException("exe sql=" + getSqlStatement("vehicleInProject"), e);
		}
	}
	/**
	 * 项目与员工树
	 * Mdx
	 * @param params 项目编号与姓名筛选
	 * @return
	 */
	public List<Map<String, String>> selectEmployeeByPro(Map<String, Object> params) {
		try {
			return this.getSqlSession().selectList(getSqlStatement("selectEmployeeByPro"), params);
		} catch (Exception e) {
			e.printStackTrace();
			throw new MyBatisAccessException("exe sql=" + getSqlStatement("selectEmployeeByPro"), e);
		}
	}
	/**
	 * 部门与员工树
	 * Mdx
	 * @param params 部门编号与姓名筛选
	 * @return
	 */
	public List<Map<String, String>> selectEmployeeByUnit(Map<String, Object> params) {
		try {
			return this.getSqlSession().selectList(getSqlStatement("selectEmployeeByUnit"), params);
		} catch (Exception e) {
			e.printStackTrace();
			throw new MyBatisAccessException("exe sql=" + getSqlStatement("selectEmployeeByUnit"), e);
		}
	}
	/**
	 * 职位与员工树
	 * Mdx
	 * @param params 部门编号与姓名筛选
	 * @return
	 */
	public List<Map<String, String>> selectEmployeeByJob() {
		try {
			return this.getSqlSession().selectList(getSqlStatement("selectEmployeeByJob"), null);
		} catch (Exception e) {
			e.printStackTrace();
			throw new MyBatisAccessException("exe sql=" + getSqlStatement("selectEmployeeByJob"), e);
		}
	}
	/**
	 * 员工编号转帐号
	 * @param params
	 * @return
	 */
	public List<String> employeecodesToAccountCodes(List<String> params) {
		try {
			return this.getSqlSession().selectList(getSqlStatement("employeecodesToAccountCodes"), params);
		} catch (Exception e) {
			e.printStackTrace();
			throw new MyBatisAccessException("exe sql=" + getSqlStatement("employeecodesToAccountCodes"), e);
		}
	}
	
	//得到所有的员工
	public List getAllEmps() {
		try {
			return this.getSqlSession().selectList(getSqlStatement("getAllEmps"), null);
		} catch (Exception e) {
			e.printStackTrace();
			throw new MyBatisAccessException("exe sql=" + getSqlStatement("getAllEmps"), e);
		}
	}
	
	//得到项目管理人的项目
	public List getManagerPros(Object params) {
		try {
			return this.getSqlSession().selectList(getSqlStatement("getManagerPros"), params);
		} catch (Exception e) {
			e.printStackTrace();
			throw new MyBatisAccessException("exe sql=" + getSqlStatement("getManagerPros"), e);
		}
	}
	//得到非项目负责人的项目列表
	public List getProList(Object params) {
		try {
			return this.getSqlSession().selectList(getSqlStatement("getProList"), params);
		} catch (Exception e) {
			e.printStackTrace();
			throw new MyBatisAccessException("exe sql=" + getSqlStatement("getProList"), e);
		}
	}
	
	public List<String> getAccountByRole(Map<String, Object> params) {
		
		try {
			
			return this.getSqlSession().selectList(this.getSqlStatement("getAccountByRole"), params);
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new MyBatisAccessException("exec sql=" + this.getSqlStatement("getAccountByRole"), e);
		}
	}
	
	
}
