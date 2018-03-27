package com.glens.pwCloudOs.commuteMgr.performance.digitalSignage.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.glens.eap.platform.core.annotation.MybatisNamespaceProcessor;
import com.glens.eap.platform.core.orm.mybatis.exception.MyBatisAccessException;
import com.glens.eap.platform.framework.beans.PageBean;
import com.glens.eap.platform.framework.dao.impl.EAPAbstractDao;
import com.glens.pwCloudOs.commuteMgr.performance.digitalSignage.vo.CpCommuteCheck;

@MybatisNamespaceProcessor(value = "CpCommuteCheckMapper")
public class CpCommuteCheckDao extends EAPAbstractDao {

	public int insertWithJudging(Object parameters) {
		try {
			int res = this.getSqlSession().insert(getSqlStatement("insertWithJudging"), parameters);
			return res;
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new MyBatisAccessException("exe sql=" + getSqlStatement("insertWithJudging"), e);
			
		}
	}
	public int updateJudging(Object parameters) {
		try {
			int res = this.getSqlSession().update(getSqlStatement("updateJudging"), parameters);
			return res;
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new MyBatisAccessException("exe sql=" + getSqlStatement("updateJudging"), e);
			
		}
	}
	
	public PageBean statisticByDate(Map<String, Object> params , int currentPage, int perpage) {
		PageBean page = null;
		try {
			int totalCount =0;
			try {
				totalCount = (Integer) this.getSqlSession().selectOne(getSqlStatement("statisticForCount"), params);
			} catch (Exception e) {
				e.printStackTrace();
				throw new MyBatisAccessException("exe sql=" + getSqlStatement("statisticForCount"), e);
			}
			if (totalCount < 1) {
				page = new PageBean();
				page.setList(new ArrayList());
			} else {
				page = new PageBean(totalCount, currentPage, perpage);
				try {
					if (params == null) {
						params = new HashMap();
					}
					params.put("firstResult", page.getPerpage() * (page.getCurrentPage() - 1));
					params.put("maxResult", page.getPerpage());
					
					List list = this.getSqlSession().selectList(getSqlStatement("statisticForPage"), params);
					page.setList(list);
				}catch (Exception e) {
					throw new MyBatisAccessException("exe sql=" + getSqlStatement("statisticForPage"), e);
				}
			}
		}catch (Exception e) {
			e.printStackTrace();
			throw new MyBatisAccessException("exe sql=" + getSqlStatement("statisticForPage"), e);
		}
		return page;
	}
	
	public List<CpCommuteCheck> unitSignPerOfPassStat(Map params) {
		try {
			return this.getSqlSession().selectList(
					getSqlStatement("unitSignPerOfPassStat"),
					params);
		} catch (Exception e) {
			throw new MyBatisAccessException("exe sql="
					+ getSqlStatement("unitSignPerOfPassStat"), e);
		}
	}
	
	public List<CpCommuteCheck> signPerOfPassTop10(Map params) {
		try {
			return this.getSqlSession().selectList(
					getSqlStatement("signPerOfPassTop10"),
					params);
		} catch (Exception e) {
			throw new MyBatisAccessException("exe sql="
					+ getSqlStatement("signPerOfPassTop10"), e);
		}
	}

}
