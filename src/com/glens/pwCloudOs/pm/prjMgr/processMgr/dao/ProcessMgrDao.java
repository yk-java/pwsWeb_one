package com.glens.pwCloudOs.pm.prjMgr.processMgr.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.glens.eap.platform.core.annotation.MybatisNamespaceProcessor;
import com.glens.eap.platform.core.orm.mybatis.exception.MyBatisAccessException;
import com.glens.eap.platform.framework.beans.PageBean;
import com.glens.eap.platform.framework.dao.impl.EAPAbstractDao;
import com.glens.pwCloudOs.pm.prjMgr.processMgr.vo.ProcessVo;

@MybatisNamespaceProcessor(value="com.glens.pwCloudOs.pm.prjMgr.processMgr.dao.ProcessMgrMapper")
public class ProcessMgrDao extends EAPAbstractDao {
	/**
	 * 查询待办（项目管理部）
	 * @param parameters
	 * @return
	 */
	public List<ProcessVo> selectToDoList(Object parameters) {
		try {
			return this.getSqlSession().selectList(getSqlStatement("selectToDoList"), parameters);
		}catch (Exception e) {
			throw new MyBatisAccessException("exe sql=" + getSqlStatement("selectToDoList"));
		}
	}
	
	/**
	 * 查询待办（行政部）
	 * @param parameters
	 * @return
	 */
	public List<ProcessVo> selectXzToDoList(Object parameters) {
		try {
			return this.getSqlSession().selectList(getSqlStatement("selectXzToDoList"), parameters);
		}catch (Exception e) {
			throw new MyBatisAccessException("exe sql=" + getSqlStatement("selectXzToDoList"));
		}
	}
	
	/**
	 * 查询已办
	 * @param parameters
	 * @return
	 */
	public List<ProcessVo> selectDoneList(Object parameters) {
		try {
			return this.getSqlSession().selectList(getSqlStatement("selectDoneList"), parameters);
		}catch (Exception e) {
			throw new MyBatisAccessException("exe sql=" + getSqlStatement("selectDoneList"));
		}
	}
	
	public PageBean queryForPage(Map parameters, int currentPage, int perpage) {
		
		PageBean page = null;
		
		try {
			
			int totalCount = this.queryForCount(parameters);
			if (totalCount < 1) {
				
				page = new PageBean();
				page.setList(new ArrayList());
			}
			else {
				
				page = new PageBean(totalCount, currentPage, perpage);
				page = this.queryForPage(parameters, page);
			}
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new MyBatisAccessException("exe sql=" + getSqlStatement("selectXzToDoList"), e);
		}
		
		return page;
	}
	
	public int queryForCount(Map parameters) {
		// TODO Auto-generated method stub
		try {
			
			return (Integer) this.getSqlSession().selectOne(getSqlStatement("queryForCount"), parameters);
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new MyBatisAccessException("exe sql=" + getSqlStatement("queryForCount"), e);
		}
	}
	private PageBean queryForPage(Map parameters, PageBean page) {
		// TODO Auto-generated method stub
		try {
			
			if (parameters == null) {
				parameters = new HashMap();
			}
			
			parameters.put("firstResult", page.getPerpage() * (page.getCurrentPage() - 1));
			parameters.put("maxResult", page.getPerpage());
			
			List list = this.getSqlSession().selectList(getSqlStatement("selectXzToDoList"), parameters);
			page.setList(list);
		}
		catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql=" + getSqlStatement("selectXzToDoList"), e);
		}
		
		return page;
	}
	
	//////////已办分页
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
			throw new MyBatisAccessException("exe sql=" + getSqlStatement("selectDoneList"), e);
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
			
			List list = this.getSqlSession().selectList(getSqlStatement("selectDoneList"), parameters);
			page.setList(list);
		}
		catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql=" + getSqlStatement("selectDoneList"), e);
		}
		
		return page;
	}
}
