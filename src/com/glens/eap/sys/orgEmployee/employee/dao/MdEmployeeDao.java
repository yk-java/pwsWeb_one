package com.glens.eap.sys.orgEmployee.employee.dao;

import java.util.List;
import java.util.Map;

import com.glens.eap.platform.core.annotation.MybatisNamespaceProcessor;
import com.glens.eap.platform.core.orm.mybatis.exception.MyBatisAccessException;
import com.glens.eap.platform.framework.beans.PageBean;
import com.glens.eap.platform.framework.dao.impl.EAPAbstractDao;
import com.glens.eap.sys.orgEmployee.employee.vo.MdEmployee;

@MybatisNamespaceProcessor(value = "MdEmployeeMapper")
public class MdEmployeeDao extends EAPAbstractDao {

	public List getUnits() {
		try {
			return this.getSqlSession().selectList(getSqlStatement("getUnits"),
					null);
		} catch (Exception e) {
			throw new MyBatisAccessException("exe sql="
					+ getSqlStatement("getUnits"), e);
		}
	}

	public List getJobs() {
		try {
			return this.getSqlSession().selectList(getSqlStatement("getJobs"),
					null);
		} catch (Exception e) {
			throw new MyBatisAccessException("exe sql="
					+ getSqlStatement("getJobs"), e);
		}
	}

	public List getProperty() {
		try {
			return this.getSqlSession().selectList(
					getSqlStatement("getProperty"), null);
		} catch (Exception e) {
			throw new MyBatisAccessException("exe sql="
					+ getSqlStatement("getProperty"), e);
		}
	}

	public List getContracts() {
		try {
			return this.getSqlSession().selectList(
					getSqlStatement("getContracts"), null);
		} catch (Exception e) {
			throw new MyBatisAccessException("exe sql="
					+ getSqlStatement("getContracts"), e);
		}
	}

	public List<MdEmployee> queryEmployee(String unitCode) {
		try {
			return this.getSqlSession().selectList(
					getSqlStatement("[queryEmployee]"), unitCode);
		} catch (Exception e) {
			throw new MyBatisAccessException("exe sql="
					+ getSqlStatement("[queryEmployee]"), e);
		}
	}

	// 人员从项目中移除
	public int memberLeave(Object parameters) {
		// TODO Auto-generated method stub
		try {

			return this.getSqlSession().update(getSqlStatement("memberLeave"),
					parameters);
		} catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql="
					+ getSqlStatement("memberLeave"), e);
		}
	}

	// 删除人员的账号
	public int deleteAccount(Object parameter) {
		// TODO Auto-generated method stub

		try {

			return this.getSqlSession().delete(
					getSqlStatement("deleteAccount"), parameter);
		} catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql="
					+ getSqlStatement("deleteAccount"), e);
		}
	}

	// 判断是否有租用的宿舍

	public List<Map<String, Object>> queryDorm(Object parameters) {
		// TODO Auto-generated method stub
		try {

			return this.getSqlSession().selectList(
					getSqlStatement("queryDorm"), parameters);
		} catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql="
					+ getSqlStatement("queryDorm"), e);
		}
	}

	// 判断是否有租用的资产

	public List<Map<String, Object>> queryAsset(Object parameters) {
		// TODO Auto-generated method stub
		try {

			return this.getSqlSession().selectList(
					getSqlStatement("queryAsset"), parameters);
		} catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql="
					+ getSqlStatement("queryAsset"), e);
		}
	}

	public MdEmployee queryEmployeeByCode(String employeeCode) {
		// TODO Auto-generated method stub
		try {
			return this.getSqlSession().selectOne(
					getSqlStatement("[queryEmployeeByCode]"), employeeCode);
		} catch (Exception e) {
			throw new MyBatisAccessException("exe sql="
					+ getSqlStatement("[queryEmployeeByCode]"), e);
		}
	}

	public List<Map<String, Object>> selectJob() {
		try {
			return this.getSqlSession().selectList(
					getSqlStatement("selectJob"), null);
		} catch (Exception e) {
			throw new MyBatisAccessException("exe sql="
					+ getSqlStatement("selectJob"), e);
		}
	}

	public List<Map<String, Object>> selectJobProperty() {
		try {
			return this.getSqlSession().selectList(
					getSqlStatement("selectJobProperty"), null);
		} catch (Exception e) {
			throw new MyBatisAccessException("exe sql="
					+ getSqlStatement("selectJobProperty"), e);
		}
	}

	public List<Map<String, Object>> selectContractProperty() {
		try {
			return this.getSqlSession().selectList(
					getSqlStatement("selectContractProperty"), null);
		} catch (Exception e) {
			throw new MyBatisAccessException("exe sql="
					+ getSqlStatement("selectContractProperty"), e);
		}
	}

	public List<MdEmployee> queryForExport(Object params) {
		try {
			return this.getSqlSession().selectList(
					getSqlStatement("queryForExport"), params);
		} catch (Exception e) {
			throw new MyBatisAccessException("exe sql="
					+ getSqlStatement("queryForExport"), e);
		}
	}

	public PageBean queryNotInProForPage(Map<String, Object> params) {
		return this.queryForPage(params, "queryNotInProForCount",
				"queryNotInProForPage");
	}

	public List<MdEmployee> queryAllEmployee() {
		try {
			return this.getSqlSession().selectList(
					getSqlStatement("[queryAllEmployee]"));
		} catch (Exception e) {
			throw new MyBatisAccessException("exe sql="
					+ getSqlStatement("[queryAllEmployee]"), e);
		}
	}

	public boolean insertHistory(MdEmployee em) {
		try {
			this.getSqlSession().insert(getSqlStatement("[insertHistory]"), em);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			throw new MyBatisAccessException("exe sql="
					+ getSqlStatement(INSERT_STATEMENT), e);
		}
	}

	public void deleteHistory() {
		// TODO Auto-generated method stub
		this.getSqlSession().delete(getSqlStatement("[deleteHistory]"));
	}

	public Map<String, Object> queryEmployeeByMobile(String mobilePhone) {

		try {
			return this.getSqlSession().selectOne(
					getSqlStatement("[queryEmployeeByMobile]"), mobilePhone);
		} catch (Exception e) {
			throw new MyBatisAccessException("exe sql="
					+ getSqlStatement("[queryEmployeeByMobile]"), e);
		}
	}

	public Map getJobNO(String accountCode) {
		try {
			return this.getSqlSession().selectOne(
					getSqlStatement("[getJobNO]"), accountCode);
		} catch (Exception e) {
			throw new MyBatisAccessException("exe sql="
					+ getSqlStatement("[getJobNO]"), e);
		}
	}

	public List<Map<String, Object>> queryProList(String jobNo) {
		// TODO Auto-generated method stub
		try {
			return this.getSqlSession().selectList(
					getSqlStatement("[queryProList]"), jobNo);
		} catch (Exception e) {
			throw new MyBatisAccessException("exe sql="
					+ getSqlStatement("[queryProList]"), e);
		}
	}

	public Map<String, Object> queryEmployeeByJObNo(String jobNo) {
		try {
			return this.getSqlSession().selectOne(
					getSqlStatement("queryEmployeeByJObNo"), jobNo);
		} catch (Exception e) {
			throw new MyBatisAccessException("exe sql="
					+ getSqlStatement("queryEmployeeByJObNo"), e);
		}
	}

	public Map<String, Object> queryEmployeeInfo(Map<String, Object> paramsMap) {
		try {
			return this.getSqlSession().selectOne(
					getSqlStatement("queryEmployeeInfo"), paramsMap);
		} catch (Exception e) {
			throw new MyBatisAccessException("exe sql="
					+ getSqlStatement("queryEmployeeInfo"), e);
		}
	}

	public List<MdEmployee> queryLeaveEmployee(Map<String, Object> paramsMap) {
		try {
			return this.getSqlSession().selectList(
					getSqlStatement("[queryLeaveEmployee]"), paramsMap);
		} catch (Exception e) {
			throw new MyBatisAccessException("exe sql="
					+ getSqlStatement("[queryLeaveEmployee]"), e);
		}
	}

	public Map queryEmployeeInfo(String employeeCode) {
		try {
			return this.getSqlSession().selectOne(
					getSqlStatement("[queryEmployeeInfo]"), employeeCode);
		} catch (Exception e) {
			throw new MyBatisAccessException("exe sql="
					+ getSqlStatement("[queryEmployeeInfo]"), e);
		}
	}

	public List<Map> queryEmployeeForOAList() {
		// TODO Auto-generated method stub
		try {
			return this.getSqlSession().selectList(
					getSqlStatement("queryEmployeeForOAList"));
		} catch (Exception e) {
			throw new MyBatisAccessException("exe sql="
					+ getSqlStatement("queryEmployeeForOAList"), e);
		}
	}

	public List<Map<String, String>> queryRelatedEmployeeList(
			String employeeCode) {
		// TODO Auto-generated method stub
		try {
			return this.getSqlSession().selectList(
					getSqlStatement("queryRelatedEmployeeList"), employeeCode);
		} catch (Exception e) {
			throw new MyBatisAccessException("exe sql="
					+ getSqlStatement("queryRelatedEmployeeList"), e);
		}
	}
}
