package com.glens.pwCloudOs.materielMg.assetMg.asset.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.glens.eap.platform.core.annotation.MybatisNamespaceProcessor;
import com.glens.eap.platform.core.orm.mybatis.exception.MyBatisAccessException;
import com.glens.eap.platform.framework.beans.PageBean;
import com.glens.eap.platform.framework.dao.impl.EAPAbstractDao;
import com.glens.pwCloudOs.materielMg.assetMg.asset.vo.GmAssetCheck;

@MybatisNamespaceProcessor(value = "com.glens.pwCloudOs.materielMg.assetMg.asset.check.dao.GmAssetCheckMapper")
public class GmAssetCheckDao extends EAPAbstractDao {

	public boolean insertSelective(Object parameters) {
		try {

			this.getSqlSession().insert(getSqlStatement("insertSelective"),
					parameters);

			return true;
		} catch (Exception e) {
			e.printStackTrace();
			throw new MyBatisAccessException("exe sql="
					+ getSqlStatement("insertSelective"), e);

		}
	}

	public List<Map<String, Object>> queryForExport(Map params) {
		try {
			return this.getSqlSession().selectList(
					getSqlStatement("queryForExport"), params);
		} catch (Exception e) {
			throw new MyBatisAccessException("exe sql="
					+ getSqlStatement("queryForExport"), e);
		}
	}

	public Map get(Map map) {
		try {
			return this.getSqlSession().selectOne(getSqlStatement("findById"),
					map);
		} catch (Exception e) {
			throw new MyBatisAccessException("exe sql="
					+ getSqlStatement("findById"), e);
		}
	}

	public List<Map<String, Object>> queryImages(Map map) {
		try {
			return this.getSqlSession().selectList(
					getSqlStatement("queryImages"), map);
		} catch (Exception e) {
			throw new MyBatisAccessException("exe sql="
					+ getSqlStatement("queryImages"), e);
		}
	}

	public int queryImagesForCount(Map parameters) {
		// TODO Auto-generated method stub
		try {

			return (Integer) this.getSqlSession().selectOne(
					getSqlStatement("queryImagesForCount"), parameters);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new MyBatisAccessException("exe sql="
					+ getSqlStatement("queryImagesForCount"), e);
		}
	}

	private PageBean queryImagesForPage(Map parameters, PageBean page) {
		// TODO Auto-generated method stub
		try {

			if (parameters == null) {
				parameters = new HashMap();
			}

			parameters.put("firstResult",
					page.getPerpage() * (page.getCurrentPage() - 1));
			parameters.put("maxResult", page.getPerpage());

			List list = this.getSqlSession().selectList(
					getSqlStatement("queryImagesForPage"), parameters);
			page.setList(list);
		} catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql="
					+ getSqlStatement("queryImagesForPage"), e);
		}

		return page;
	}

	public PageBean queryPageImages(Map map, int currentPage, int perpage) {
		PageBean page = null;

		try {

			int totalCount = this.queryImagesForCount(map);
			if (totalCount < 1) {

				page = new PageBean();
				page.setList(new ArrayList());
			} else {

				page = new PageBean(totalCount, currentPage, perpage);
				page = this.queryImagesForPage(map, page);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new MyBatisAccessException("exe sql="
					+ getSqlStatement("queryImagesForPage"), e);
		}

		return page;
	}

	public List queryTodayStatus(Map<String, Object> params) {
		try {
			return this.getSqlSession().selectList(
					getSqlStatement("queryTodayStatus"), params);
		} catch (Exception e) {
			throw new MyBatisAccessException("exe sql="
					+ getSqlStatement("queryTodayStatus"), e);
		}
	}

	public List assetUserList(Map params) {
		// TODO Auto-generated method stub
		try {
			return this.getSqlSession().selectList(
					getSqlStatement("assetUserList"), params);
		} catch (Exception e) {
			throw new MyBatisAccessException("exe sql="
					+ getSqlStatement("assetUserList"), e);
		}
	}

	public Map queryLatestAssetCheck(String assetCode) {
		// TODO Auto-generated method stub
		try {
			return this.getSqlSession().selectOne(
					getSqlStatement("queryLatestAssetCheck"), assetCode);
		} catch (Exception e) {
			throw new MyBatisAccessException("exe sql="
					+ getSqlStatement("queryLatestAssetCheck"), e);
		}
	}

	public GmAssetCheck queryGmAssetCheck(Map paramsMap) {
		// TODO Auto-generated method stub
		try {
			return this.getSqlSession().selectOne(
					getSqlStatement("queryGmAssetCheck"), paramsMap);
		} catch (Exception e) {
			throw new MyBatisAccessException("exe sql="
					+ getSqlStatement("queryGmAssetCheck"), e);
		}
	}

	public void updateSelective(GmAssetCheck check) {
		// TODO Auto-generated method stub
		try {
			this.getSqlSession().update(
					getSqlStatement("updateByPrimaryKeySelective"), check);
		} catch (Exception e) {
			throw new MyBatisAccessException("exe sql="
					+ getSqlStatement("updateByPrimaryKeySelective"), e);
		}
	}
}
