package com.glens.pwCloudOs.commuteMgr.performance.statistic.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.glens.eap.platform.core.annotation.MybatisNamespaceProcessor;
import com.glens.eap.platform.core.orm.mybatis.exception.MyBatisAccessException;
import com.glens.eap.platform.framework.beans.PageBean;
import com.glens.eap.platform.framework.dao.impl.EAPAbstractDao;
import com.glens.pwCloudOs.commuteMgr.performance.statistic.vo.CommuteStatisticVo;

@MybatisNamespaceProcessor(value = "CommuteStatisticMapper")
public class CommuteStatisticDao extends EAPAbstractDao {

	public PageBean statisticByDate(Map<String, Object> params,
			int currentPage, int perpage) {
		PageBean page = null;
		try {
			int totalCount = 0;
			try {
				totalCount = (Integer) this.getSqlSession().selectOne(
						getSqlStatement("statisticForCount"), params);
			} catch (Exception e) {
				e.printStackTrace();
				throw new MyBatisAccessException("exe sql="
						+ getSqlStatement("statisticForCount"), e);
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
					params.put("firstResult",
							page.getPerpage() * (page.getCurrentPage() - 1));
					params.put("maxResult", page.getPerpage());

					List list = this.getSqlSession().selectList(
							getSqlStatement("statisticForPage"), params);
					page.setList(list);
				} catch (Exception e) {
					throw new MyBatisAccessException("exe sql="
							+ getSqlStatement("statisticForPage"), e);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new MyBatisAccessException("exe sql="
					+ getSqlStatement("statisticForPage"), e);
		}
		return page;
	}

	public List<CommuteStatisticVo> unitSignPerOfPassStat(Map params) {
		try {
			return this.getSqlSession().selectList(
					getSqlStatement("unitSignPerOfPassStat"), params);
		} catch (Exception e) {
			throw new MyBatisAccessException("exe sql="
					+ getSqlStatement("unitSignPerOfPassStat"), e);
		}
	}

	public List<CommuteStatisticVo> signPerOfPassTop10(Map params) {
		try {
			return this.getSqlSession().selectList(
					getSqlStatement("signPerOfPassTop10"), params);
		} catch (Exception e) {
			throw new MyBatisAccessException("exe sql="
					+ getSqlStatement("signPerOfPassTop10"), e);
		}
	}

	public CommuteStatisticVo findCommute(Map params) {
		try {
			return this.getSqlSession().selectOne(
					getSqlStatement("findCommute"), params);
		} catch (Exception e) {
			throw new MyBatisAccessException("exe sql="
					+ getSqlStatement("findCommute"), e);
		}
	}

	public void updateDistance(Map<String, Object> map) {
		// TODO Auto-generated method stub
		try {
			this.getSqlSession().update(getSqlStatement("updateDistance"), map);
		} catch (Exception e) {
			throw new MyBatisAccessException("exe sql="
					+ getSqlStatement("updateDistance"), e);
		}
	}

}
