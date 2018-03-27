/**
 * @Title: ComprehensiveQueryDao.java
 * @Package com.glens.pwCloudOs.materielMg.comprehensiveQuery.dao
 * @Description: TODO
 * Copyright: Copyright (c) 2016 
 * Company:南京量为石信息科技有限公司
 * @author 
 * @date 2016-6-29 下午4:38:22
 * @version V1.0
 */


package com.glens.pwCloudOs.materielMg.comprehensiveQuery.dao;

import java.util.ArrayList;
import java.util.HashMap;
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

@MybatisNamespaceProcessor(value="com.glens.pwCloudOs.materielMg.comprehensiveQuery.dao.comprehensiveQueryMapper")
public class ComprehensiveQueryDao extends EAPAbstractDao {

	private static Log logger = LogFactory.getLog(ComprehensiveQueryDao.class);
	
	public List<Map<String, Object>> vehicleOilConsumptionStatistics(Map<String, String> params) {
		
		try {
			
			logger.info("车辆油耗统计查询开始...");
			List<Map<String, Object>> resultList = this.getSqlSession().selectList(getSqlStatement("vehicleOilConsumptionStatistics"), params);
			logger.info("车辆油耗统计查询结束");
			
			return resultList;
		}
		catch (Exception e) {
			// TODO: handle exception
			logger.error("车辆油耗统计查询出错", e);
			
			throw new MyBatisAccessException("exe sql=" + getSqlStatement("vehicleOilConsumptionStatistics"), e);
		}
	}
	
	public List<Map<String, Object>> selectVehicleFuelCharge(Map<String, String> params) {
		
		try {
			
			logger.info("车辆加油记录查询开始...");
			List<Map<String, Object>> resultList = this.getSqlSession().selectList(getSqlStatement("selectVehicleFuelCharge"), params);
			logger.info("车辆加油记录查询结束");
			
			return resultList;
		}
		catch (Exception e) {
			// TODO: handle exception
			logger.error("车辆加油记录查询出错", e);
			
			throw new MyBatisAccessException("exe sql=" + getSqlStatement("selectVehicleFuelCharge"), e);
		}
	}
	
	public List<Map<String, Object>> selectAssetRepair(Map<String, String> params) {
		
		try {
			
			logger.info("资产维修记录查询开始...");
			List<Map<String, Object>> resultList = this.getSqlSession().selectList(getSqlStatement("selectAssetRepair"), params);
			logger.info("资产维修记录查询结束");
			
			return resultList;
		}
		catch (Exception e) {
			// TODO: handle exception
			logger.error("资产维修记录查询出错", e);
			
			throw new MyBatisAccessException("exe sql=" + getSqlStatement("selectAssetRepair"), e);
		}
	}
	
	public List<Map<String, Object>> selectAssetStorage(Map<String, String> params) {
		
		try {
			
			logger.info("资产入库记录查询开始...");
			List<Map<String, Object>> resultList = this.getSqlSession().selectList(getSqlStatement("selectAssetStorage"), params);
			logger.info("资产入库记录查询结束");
			
			return resultList;
		}
		catch (Exception e) {
			// TODO: handle exception
			logger.error("资产入库记录查询出错", e);
			
			throw new MyBatisAccessException("exe sql=" + getSqlStatement("selectAssetStorage"), e);
		}
	}
	
	public List<Map<String, Object>> materialStorage(Map<String, String> params) {
		
		try {
			
			//logger.info("资产入库记录查询开始...");
			List<Map<String, Object>> resultList = this.getSqlSession().selectList(getSqlStatement("materialStorage"), params);
			//logger.info("资产入库记录查询结束");
			
			return resultList;
		}
		catch (Exception e) {
			// TODO: handle exception
			//logger.error("资产入库记录查询出错", e);
			
			throw new MyBatisAccessException("exe sql=" + getSqlStatement("materialStorage"), e);
		}
	}
	
	
	
	public List<Map<String, Object>> selectAssetRent(Map<String, String> params) {
		
		try {
			
			logger.info("资产出库记录查询开始...");
			List<Map<String, Object>> resultList = this.getSqlSession().selectList(getSqlStatement("selectAssetRent"), params);
			logger.info("资产出库记录查询结束");
			
			return resultList;
		}
		catch (Exception e) {
			// TODO: handle exception
			logger.error("资产出库记录查询出错", e);
			
			throw new MyBatisAccessException("exe sql=" + getSqlStatement("selectAssetRent"), e);
		}
	}
	
	public List<Map<String, Object>> selectAssetRentReturn(Map<String, String> params) {
		
		try {
			
			logger.info("资产归还记录查询开始...");
			List<Map<String, Object>> resultList = this.getSqlSession().selectList(getSqlStatement("selectAssetRentReturn"), params);
			logger.info("资产归还记录查询结束");
			
			return resultList;
		}
		catch (Exception e) {
			// TODO: handle exception
			logger.error("资产归还记录查询出错", e);
			
			throw new MyBatisAccessException("exe sql=" + getSqlStatement("selectAssetRentReturn"), e);
		}
	}
	
	public List<Map<String, Object>> selectAssetScrap(Map<String, String> params) {
		
		try {
			
			logger.info("资产报废记录查询开始...");
			List<Map<String, Object>> resultList = this.getSqlSession().selectList(getSqlStatement("selectAssetScrap"), params);
			logger.info("资产报废记录查询结束");
			
			return resultList;
		}
		catch (Exception e) {
			// TODO: handle exception
			logger.error("资产报废记录查询出错", e);
			
			throw new MyBatisAccessException("exe sql=" + getSqlStatement("selectAssetScrap"), e);
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
			throw new MyBatisAccessException("exe sql=" + getSqlStatement("selectAssetRentalCost"), e);
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
			
			List list = this.getSqlSession().selectList(getSqlStatement("selectAssetRentalCost"), parameters);
			page.setList(list);
		}
		catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql=" + getSqlStatement("selectAssetRentalCost"), e);
		}
		
		return page;
	}
	
	
	
	public List<Map<String, Object>> exportAssetRentalCost(Map<String, String> params) {
		
		try {
			
			
			List<Map<String, Object>> resultList = this.getSqlSession().selectList(getSqlStatement("exportAssetRentalCost"), params);
			
			
			return resultList;
		}
		catch (Exception e) {
			// TODO: handle exception
		
			
			throw new MyBatisAccessException("exe sql=" + getSqlStatement("exportAssetRentalCost"), e);
		}
	}
	
	public List<Map<String, Object>> selectRentDorm(Map<String, String> params) {
		
		try {
			
			logger.info("宿舍记录查询开始...");
			List<Map<String, Object>> resultList = this.getSqlSession().selectList(getSqlStatement("selectRentDorm"), params);
			logger.info("宿舍记录查询结束");
			
			return resultList;
		}
		catch (Exception e) {
			// TODO: handle exception
			logger.error("宿舍记录查询出错", e);
			
			throw new MyBatisAccessException("exe sql=" + getSqlStatement("selectRentDorm"), e);
		}
	}
	
	public List<Map<String, Object>> selectRentDormBed(Map<String, String> params) {
		
		try {
			
			logger.info("宿舍床位查询开始...");
			List<Map<String, Object>> resultList = this.getSqlSession().selectList(getSqlStatement("selectRentDormBed"), params);
			logger.info("宿舍床位查询结束");
			
			return resultList;
		}
		catch (Exception e) {
			// TODO: handle exception
			logger.error("宿舍床位查询出错", e);
			
			throw new MyBatisAccessException("exe sql=" + getSqlStatement("selectRentDormBed"), e);
		}
	}
	
	public List<Map<String, Object>> queryAssetDistributing(Map<String, String> params) {
		
		try {
			
			return this.getSqlSession().selectList(getSqlStatement("queryAssetDistributing"), params);
		}
		catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql=" + getSqlStatement("queryAssetDistributing"), e);
		}
	}
	
	public List<Map<String, Object>> selectDistributeAsset(Map<String, Object> params) {
		
		try {
			
			List<Map<String, Object>> assetList = this.getSqlSession().selectList(getSqlStatement("selectDistributeAsset"), params);
			
			return assetList;
		}
		catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql=" + getSqlStatement("selectDistributeAsset"), e);
		}
	}
	
	public int selectProAssetCostForCount(Map<String, Object> params) {
		
		try {
			
			return this.getSqlSession().selectOne(this.getSqlStatement("selectProAssetCostForCount"), params);
		}
		catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql=" + getSqlStatement("selectProAssetCostForCount"), e);
		}
	}
	
	public PageBean selectProAssetCostForPage(Map<String, Object> params) {
		
		try {
			
			int totalCount = this.selectProAssetCostForCount(params);
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
				List<Map<String, Object>> proList = this.getSqlSession().
						selectList(this.getSqlStatement("selectProAssetCostForPage"), params);
				page.setList(proList);
			}
			
			return page;
		}
		catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql=" + getSqlStatement("selectProAssetCostForPage"), e);
		}
	}
	
	public List<Map<String, Object>> selectProAssetCostForList(Map<String, Object> params) {
		
		try {
			
			return this.getSqlSession().selectList(this.getSqlStatement("selectProAssetCostForList"), params);
		}
		catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql=" + getSqlStatement("selectProAssetCostForList"), e);
		}
	}
	
	public List<Map<String, Object>> selectProAssetList(Map<String, Object> params) {
		
		try {
			
			return this.getSqlSession().selectList(this.getSqlStatement("selectProAssetList"), params);
		}
		catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql=" + getSqlStatement("selectProAssetList"), e);
		}
	}
	
	public int selectProDormCostForCount(Map<String, Object> params) {
		
		try {
			
			return this.getSqlSession().selectOne(this.getSqlStatement("selectProDormCostForCount"), params);
		}
		catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql=" + getSqlStatement("selectProDormCostForCount"), e);
		}
	}
	
	public PageBean selectProDormCostForPage(Map<String, Object> params) {
		
		try {
			
			int totalCount = this.selectProDormCostForCount(params);
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
				List<Map<String, Object>> proList = this.getSqlSession().
						selectList(this.getSqlStatement("selectProDormCostForPage"), params);
				page.setList(proList);
			}
			
			return page;
		}
		catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql=" + getSqlStatement("selectProDormCostForPage"), e);
		}
	}
	
	public List<Map<String, Object>> selectProDormList(Map<String, Object> params) {
		
		try {
			
			return this.getSqlSession().selectList(this.getSqlStatement("selectProDormList"), params);
		}
		catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql=" + getSqlStatement("selectProDormList"), e);
		}
	}
	
	public List<Map<String, Object>> selectProDormCostForList(Map<String, Object> params) {
		
		try {
			
			return this.getSqlSession().selectList(this.getSqlStatement("selectProDormCostForList"), params);
		}
		catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql=" + getSqlStatement("selectProDormCostForList"), e);
		}
	}
	
}
