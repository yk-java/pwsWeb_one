/**
 * @Title: PwBaoAppDao.java
 * @Package com.glens.pwCloudOs.pwBaoApp.dao
 * @Description: TODO
 * Copyright: Copyright (c) 2016 
 * Company:南京量为石信息科技有限公司
 * @author 
 * @date 2016-8-13 上午10:24:34
 * @version V1.0
 */

package com.glens.pwCloudOs.pwBaoApp.dao;

import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.glens.eap.platform.core.annotation.MybatisNamespaceProcessor;
import com.glens.eap.platform.core.orm.mybatis.exception.MyBatisAccessException;
import com.glens.eap.platform.framework.dao.impl.EAPAbstractDao;

/**
 * 
 * 
 * @author
 * @version V1.0
 */

@MybatisNamespaceProcessor(value = "com.glens.pwCloudOs.pwBaoAppMapper")
public class PwBaoAppDao extends EAPAbstractDao {

	private Log logger = LogFactory.getLog(PwBaoAppDao.class);

	public Map<String, Object> getUserByAccount(String accountName) {

		try {

			Map<String, Object> employeeInfo = (Map<String, Object>) this
					.getSqlSession().selectOne(
							this.getSqlStatement("getUserByAccount"),
							accountName);
			logger.info("获取账号:" + accountName + "对应用户信息成功！");

			return employeeInfo;
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("获取账号:" + accountName + "对应用户信息失败!", e);

			throw new MyBatisAccessException("exe sql="
					+ getSqlStatement("getUserByAccount"), e);
		}
	}

	public String getPswByAccountName(String accountName) {

		try {

			return this.getSqlSession().selectOne(
					getSqlStatement("getPswByAccountName"), accountName);
		} catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql="
					+ getSqlStatement("getPswByAccountName"), e);
		}
	}

	public Map<String, Object> getCommuteConfig(Map<String, Object> params) {

		try {

			return this.getSqlSession().selectOne(
					getSqlStatement("getCommuteConfig"), params);
		} catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql="
					+ getSqlStatement("getCommuteConfig"), e);
		}
	}

	public List<Map<String, String>> getIntelligenceType() {

		try {

			return this.getSqlSession().selectList(
					getSqlStatement("getIntelligenceType"));
		} catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql="
					+ getSqlStatement("getIntelligenceType"), e);
		}
	}

	// 轻应用
	public List<Map<String, String>> getRoleApp(Object params) {

		try {

			return this.getSqlSession().selectList(
					getSqlStatement("getRoleApp"), params);
		} catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql="
					+ getSqlStatement("getRoleApp"), e);
		}
	}

	public List<Map<String, String>> getIntelligenceUrgency() {

		try {

			return this.getSqlSession().selectList(
					getSqlStatement("getIntelligenceUrgency"));
		} catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql="
					+ getSqlStatement("getIntelligenceUrgency"), e);
		}
	}

	public int insertIntelligence(Map<String, Object> params) {

		try {

			return this.getSqlSession().insert(
					getSqlStatement("insertIntelligence"), params);
		} catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql="
					+ getSqlStatement("insertIntelligence"), e);
		}
	}

	public int saveCheckin(Map<String, Object> params) {

		try {

			return this.getSqlSession().insert(getSqlStatement("saveCheckin"),
					params);
		} catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql="
					+ getSqlStatement("saveCheckin"), e);
		}
	}

	public int updateCheckout(Map<String, Object> params) {

		try {

			return this.getSqlSession().update(
					getSqlStatement("updateCheckout"), params);
		} catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql="
					+ getSqlStatement("updateCheckout"), e);
		}
	}

	public Map<String, Object> getMyCommuteInfoByDate(Map<String, Object> params) {

		try {

			return this.getSqlSession().selectOne(
					getSqlStatement("getMyCommuteInfoByDate"), params);
		} catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql="
					+ getSqlStatement("getMyCommuteInfoByDate"), e);
		}
	}

	public Map<String, Object> getLastMobileVersion() {

		try {

			return this.getSqlSession().selectOne(
					getSqlStatement("getLastMobileVersion"));
		} catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql="
					+ getSqlStatement("getLastMobileVersion"), e);
		}
	}

	public int updateUserPsw(Map<String, String> params) {

		try {

			return this.getSqlSession().update(
					getSqlStatement("updateUserPsw"), params);
		} catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql="
					+ getSqlStatement("[updateUserPsw]"), e);
		}
	}

	public Map<String, Object> getEmployeeInfo(String accountCode) {

		try {

			Map<String, Object> employeeInfo = (Map<String, Object>) this
					.getSqlSession().selectOne(
							this.getSqlStatement("getEmployeeInfo"),
							accountCode);
			logger.info("获取账号代码:" + accountCode + "对应人员信息成功！");

			return employeeInfo;
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("获取账号代码:" + accountCode + "对应人员信息失败!", e);

			throw new MyBatisAccessException("exe sql="
					+ getSqlStatement("getEmployeeInfo"), e);
		}
	}

	public int updateEmployeeField(Map<String, String> params) {

		try {

			int updateCount = this.getSqlSession().update(
					this.getSqlStatement("updateEmployeeField"), params);
			logger.info("更新人员代码为:" + params.get("employeeCode") + ",更新字段"
					+ params.get("field") + "=" + params.get("fieldValue")
					+ "成功！");

			return updateCount;
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("更新人员代码为:" + params.get("employeeCode") + ",更新字段"
					+ params.get("field") + "=" + params.get("fieldValue")
					+ "出错！", e);

			throw new MyBatisAccessException("exe sql="
					+ getSqlStatement("updateEmployeeField"), e);
		}
	}

	public String getEmployeeCheckInTime(Map<String, Object> params) {

		try {

			return this.getSqlSession().selectOne(
					this.getSqlStatement("getEmployeeCheckInTime"), params);
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("获取:" + params.get("employeeCode") + "签到时间出错！", e);

			throw new MyBatisAccessException("exe sql="
					+ getSqlStatement("getEmployeeCheckInTime"), e);
		}
	}

	public Integer updateCheckDistance(Map<String, Object> params) {
		try {

			return this.getSqlSession().update(
					getSqlStatement("updateCheckDistance"), params);
		} catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql="
					+ getSqlStatement("updateCheckDistance"), e);
		}
	}
	
	public Map<String, Object> getUserCurdateCheckInfo(Map<String, String> params) {
		
		Map<String, Object> userCheckInfo = null;
		
		try {
			
			List<Map<String, Object>> checkInfoList = this.getSqlSession().
					selectList(getSqlStatement("getUserCurdateCheckInfo"), params);
			if (checkInfoList != null && checkInfoList.size() > 0) {
				
				userCheckInfo = checkInfoList.get(0);
			}
		} catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql="
					+ getSqlStatement("getUserCurdateCheckInfo"), e);
		}
		
		return userCheckInfo;
	}

}
