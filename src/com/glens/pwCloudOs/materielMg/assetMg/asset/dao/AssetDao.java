/**
 * @Title: AssetDao.java
 * @Package com.glens.pwCloudOs.materielMg.assetMg.asset.dao
 * @Description: TODO
 * Copyright: Copyright (c) 2016 
 * Company:南京量为石信息科技有限公司
 * @author 
 * @date 2016-6-22 下午3:45:16
 * @version V1.0
 */

package com.glens.pwCloudOs.materielMg.assetMg.asset.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.glens.eap.platform.core.annotation.MybatisNamespaceProcessor;
import com.glens.eap.platform.core.orm.mybatis.exception.MyBatisAccessException;
import com.glens.eap.platform.framework.beans.PageBean;
import com.glens.eap.platform.framework.dao.impl.EAPAbstractDao;
import com.glens.pwCloudOs.materielMg.assetMg.asset.vo.AssetVo;

/**
 * 
 * 
 * @author
 * @version V1.0
 */

@MybatisNamespaceProcessor(value = "com.glens.pwCloudOs.materielMg.assetMg.asset.dao.AssetVoMapper")
public class AssetDao extends EAPAbstractDao {

	public Map<String, Object> selectAssetDetail(Map<String, String> params) {

		try {

			return this.getSqlSession().selectOne(
					getSqlStatement("selectAssetDetail"), params);
		} catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql="
					+ getSqlStatement("selectAssetDetail"), e);
		}
	}

	// 资产报废
	public Map<String, Object> assetScrap(Map<String, Object> params) {

		try {

			return this.getSqlSession().selectOne(
					getSqlStatement("assetScrap"), params);
		} catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql="
					+ getSqlStatement("assetScrap"), e);
		}
	}

	// 修改资产内容
	public Map<String, Object> upDateForms(Map<String, Object> params) {

		try {

			return this.getSqlSession().selectOne(
					getSqlStatement("upDateForms"), params);
		} catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql="
					+ getSqlStatement("upDateForms"), e);
		}
	}

	public List<Map<String, Object>> selectTop20AssetMaintain(
			Map<String, String> params) {

		try {

			return this.getSqlSession().selectList(
					getSqlStatement("selectTop20AssetMaintain"), params);
		} catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql="
					+ getSqlStatement("selectTop20AssetMaintain"), e);
		}
	}

	public List<Map<String, Object>> selectTop20AssetRepair(
			Map<String, String> params) {

		try {

			return this.getSqlSession().selectList(
					getSqlStatement("selectTop20AssetRepair"), params);
		} catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql="
					+ getSqlStatement("selectTop20AssetRepair"), e);
		}
	}

	public List<Map<String, Object>> selectTop20AssetRent(
			Map<String, String> params) {

		try {

			return this.getSqlSession().selectList(
					getSqlStatement("selectTop20AssetRent"), params);
		} catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql="
					+ getSqlStatement("selectTop20AssetRent"), e);
		}
	}

	public List<Map<String, Object>> selectAssetChecklist(
			Map<String, Object> params) {

		try {

			return this.getSqlSession().selectList(
					getSqlStatement("selectAssetChecklist"), params);
		} catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql="
					+ getSqlStatement("selectAssetChecklist"), e);
		}
	}

	public List<Map<String, String>> selectRentAssetPro(
			Map<String, Object> params) {

		try {

			return this.getSqlSession().selectList(
					getSqlStatement("selectRentAssetPro"), params);
		} catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql="
					+ getSqlStatement("selectRentAssetPro"), e);
		}
	}

	public List<Map<String, String>> selectRentAssetClass() {

		try {

			return this.getSqlSession().selectList(
					getSqlStatement("selectRentAssetClass"));
		} catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql="
					+ getSqlStatement("selectRentAssetClass"), e);
		}
	}

	public List<Map<String, Object>> selectProRentAsset(
			Map<String, String> params) {

		try {

			return this.getSqlSession().selectList(
					getSqlStatement("selectProRentAsset"), params);
		} catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql="
					+ getSqlStatement("selectProRentAsset"), e);
		}
	}

	public List<Map<String, Object>> selectAllProRentBed() {

		try {

			return this.getSqlSession().selectList(
					getSqlStatement("selectAllProRentBed"));
		} catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql="
					+ getSqlStatement("selectAllProRentBed"), e);
		}
	}

	public List<Map<String, Object>> selectProRentBed(Map<String, String> params) {

		try {

			return this.getSqlSession().selectList(
					getSqlStatement("selectProRentBed"), params);
		} catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql="
					+ getSqlStatement("selectProRentBed"), e);
		}
	}

	public List<AssetVo> queryForExport(Object params) {
		try {
			return this.getSqlSession().selectList(
					getSqlStatement("queryForExport"), params);
		} catch (Exception e) {
			throw new MyBatisAccessException("exe sql="
					+ getSqlStatement("queryForExport"), e);
		}
	}

	public int updateRentStatus(Object parameters) {
		// TODO Auto-generated method stub
		try {

			return this.getSqlSession().update(
					getSqlStatement("updateRentStatus"), parameters);
		} catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql="
					+ getSqlStatement("updateRentStatus"), e);
		}
	}

	public int updateAssetStatus(Object parameters) {
		// TODO Auto-generated method stub
		try {

			return this.getSqlSession().update(
					getSqlStatement("updateAssetStatus"), parameters);
		} catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql="
					+ getSqlStatement("updateAssetStatus"), e);
		}
	}

	public boolean addRent(Object parameters) {
		// TODO Auto-generated method stub

		try {

			this.getSqlSession().insert(getSqlStatement("addRent"), parameters);

			return true;
		} catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql="
					+ getSqlStatement("addRent"), e);

		}
	}

	public PageBean queryForPage1(Map parameters, int currentPage, int perpage) {

		PageBean page = null;

		try {

			int totalCount = this.queryForCount1(parameters);
			if (totalCount < 1) {

				page = new PageBean();
				page.setList(new ArrayList());
			} else {

				page = new PageBean(totalCount, currentPage, perpage);
				page = this.queryForPage1(parameters, page);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new MyBatisAccessException("exe sql="
					+ getSqlStatement("getMaterialBase"), e);
		}

		return page;
	}

	public int queryForCount1(Map parameters) {
		// TODO Auto-generated method stub
		try {

			return (Integer) this.getSqlSession().selectOne(
					getSqlStatement("queryForCount1"), parameters);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new MyBatisAccessException("exe sql="
					+ getSqlStatement("queryForCount1"), e);
		}
	}

	private PageBean queryForPage1(Map parameters, PageBean page) {
		// TODO Auto-generated method stub
		try {

			if (parameters == null) {
				parameters = new HashMap();
			}

			parameters.put("firstResult",
					page.getPerpage() * (page.getCurrentPage() - 1));
			parameters.put("maxResult", page.getPerpage());

			List list = this.getSqlSession().selectList(
					getSqlStatement("getMaterialBase"), parameters);
			page.setList(list);
		} catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql="
					+ getSqlStatement("getMaterialBase"), e);
		}

		return page;
	}

	public PageBean queryForPage2(Map parameters, int currentPage, int perpage) {

		PageBean page = null;

		try {

			int totalCount = this.queryForCount2(parameters);
			if (totalCount < 1) {

				page = new PageBean();
				page.setList(new ArrayList());
			} else {

				page = new PageBean(totalCount, currentPage, perpage);
				page = this.queryForPage2(parameters, page);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new MyBatisAccessException("exe sql="
					+ getSqlStatement("getVehicleList"), e);
		}

		return page;
	}

	public int queryForCount2(Map parameters) {
		// TODO Auto-generated method stub
		try {

			return (Integer) this.getSqlSession().selectOne(
					getSqlStatement("queryForCount2"), parameters);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new MyBatisAccessException("exe sql="
					+ getSqlStatement("queryForCount2"), e);
		}
	}

	private PageBean queryForPage2(Map parameters, PageBean page) {
		// TODO Auto-generated method stub
		try {

			if (parameters == null) {
				parameters = new HashMap();
			}

			parameters.put("firstResult",
					page.getPerpage() * (page.getCurrentPage() - 1));
			parameters.put("maxResult", page.getPerpage());

			List list = this.getSqlSession().selectList(
					getSqlStatement("getVehicleList"), parameters);
			page.setList(list);
		} catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql="
					+ getSqlStatement("getVehicleList"), e);
		}

		return page;
	}

	public Map queryHouseRent(Map paramsMap) {
		// TODO Auto-generated method stub
		try {
			return this.getSqlSession().selectOne(
					getSqlStatement("queryHouseRent"), paramsMap);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new MyBatisAccessException("exe sql="
					+ getSqlStatement("queryHouseRent"), e);
		}
	}

	public Map queryCarRent(Map paramsMap) {
		// TODO Auto-generated method stub
		try {

			return this.getSqlSession().selectOne(
					getSqlStatement("queryCarRent"), paramsMap);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new MyBatisAccessException("exe sql="
					+ getSqlStatement("queryCarRent"), e);
		}
	}

	public Map queryDeviceUse(Map paramsMap) {
		// TODO Auto-generated method stub
		try {

			return this.getSqlSession().selectOne(
					getSqlStatement("queryDeviceUse"), paramsMap);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new MyBatisAccessException("exe sql="
					+ getSqlStatement("queryDeviceUse"), e);
		}
	}

	public Map queryGasolineUse(Map paramsMap) {
		// TODO Auto-generated method stub
		try {
			return this.getSqlSession().selectOne(
					getSqlStatement("queryGasolineUse"), paramsMap);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new MyBatisAccessException("exe sql="
					+ getSqlStatement("queryGasolineUse"), e);
		}
	}

}
