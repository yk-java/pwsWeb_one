/**
 * @Title: OpsAppDao.java
 * @Package com.glens.pwCloudOs.opsApp.dao
 * @Description: TODO
 * Copyright: Copyright (c) 2016 
 * Company:南京量为石信息科技有限公司
 * @author 
 * @date 2016-8-10 下午1:44:29
 * @version V1.0
 */


package com.glens.pwCloudOs.opsApp.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

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

@MybatisNamespaceProcessor(value="com.glens.pwCloudOs.opsAppMapper")
public class OpsAppDao extends EAPAbstractDao {

	private static Log logger = LogFactory.getLog(OpsAppDao.class);
	
	public Map<String, Object> getEmployeeInfo(String accountCode) {
		
		try {
			
			Map<String, Object> employeeInfo = (Map<String, Object>) this.getSqlSession().selectOne(this.getSqlStatement("getEmployeeInfo"), accountCode);
			logger.info("获取账号代码:" + accountCode + "对应人员信息成功！");
			
			return employeeInfo;
		}
		catch (Exception e) {
			// TODO: handle exception
			logger.error("获取账号代码:" + accountCode + "对应人员信息失败!", e);
			
			throw new MyBatisAccessException("exe sql=" + getSqlStatement("getEmployeeInfo"), e);
		}
	}
	
	public int updateEmployeeField(Map<String, String> params) {
		
		try {
			
			int updateCount = this.getSqlSession().update(this.getSqlStatement("updateEmployeeField"), params);
			logger.info("更新人员代码为:" + params.get("employeeCode") + ",更新字段" + params.get("field") + "=" + params.get("fieldValue") + "成功！");
			
			return updateCount;
		}
		catch (Exception e) {
			// TODO: handle exception
			logger.error("更新人员代码为:" + params.get("employeeCode") + ",更新字段" + params.get("field") + "=" + params.get("fieldValue") + "出错！", e);
			
			throw new MyBatisAccessException("exe sql=" + getSqlStatement("updateEmployeeField"), e);
		}
	}
	
	public List<Map<String, String>> getProcessDocTypes() {
		
		try {
			
			return this.getSqlSession().selectList(getSqlStatement("getProcessDocTypes"));
		}
		catch (Exception e) {
			// TODO: handle exception
			logger.error("查询过程文档类型出错!", e);
			
			throw new MyBatisAccessException("exe sql=" + getSqlStatement("getProcessDocTypes"), e);
		}
	}
	//查询通讯录
	public List<Map<String, String>> getMailList(Object params) {
		
		try {
			
			return this.getSqlSession().selectList(getSqlStatement("getMailList"),params);
		}
		catch (Exception e) {
			// TODO: handle exception
			logger.error("查询通讯录出错!", e);
			
			throw new MyBatisAccessException("exe sql=" + getSqlStatement("getMailList"), e);
		}
	}
	public int getProcessDocCount(Map<String, Object> params) {
		// TODO Auto-generated method stub
		try {
			
			return (Integer) this.getSqlSession().selectOne(getSqlStatement("getProcessDocCount"), params);
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new MyBatisAccessException("exe sql=" + getSqlStatement("getProcessDocCount"), e);
		}
	}
	
	public PageBean getProcessDoc(Map<String, Object> params, int currentPage, int perpage) {
		
		try {
			PageBean page = null;
			
			int totalCount = this.getProcessDocCount(params);
			if (totalCount < 1) {
				
				page = new PageBean();
				page.setList(new ArrayList());
			}
			else {
				
				page = new PageBean(totalCount, currentPage, perpage);
			}
			
			params.put("firstResult", page.getPerpage() * (page.getCurrentPage() - 1));
			params.put("maxResult", page.getPerpage());
			
			List<Map<String, Object>> docList = this.getSqlSession().selectList(getSqlStatement("getProcessDoc"), params);
			page.setList(docList);
			
			return page;
		}
		catch (Exception e) {
			// TODO: handle exception
			logger.error("查询过程文档出错!", e);
			
			throw new MyBatisAccessException("exe sql=" + getSqlStatement("getProcessDoc"), e);
		}
	}
	
	public int batchInsertProcessDoc(List<Map<String, Object>> docList) {
		
		try {
			
			return this.getSqlSession().insert(getSqlStatement("batchInsertProcessDoc"), docList);
		}
		catch (Exception e) {
			// TODO: handle exception
			logger.error("批量插入过程文档出错!", e);
			
			throw new MyBatisAccessException("exe sql=" + getSqlStatement("batchInsertProcessDoc"), e);
		}
	}
	
	public int updateUserPsw(Map<String, String> params) {
		
		try {
			
			return this.getSqlSession().update(getSqlStatement("updateUserPsw"), params);
		}
		catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql="
					+ getSqlStatement("[updateUserPsw]"), e);
		}
	}
	
	public Map<String, Object> getLastMobileVersion() {
		
		try {
			
			return this.getSqlSession().selectOne(getSqlStatement("getLastMobileVersion"));
		}
		catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql=" + getSqlStatement("getLastMobileVersion"), e);
		}
	}
	
}
