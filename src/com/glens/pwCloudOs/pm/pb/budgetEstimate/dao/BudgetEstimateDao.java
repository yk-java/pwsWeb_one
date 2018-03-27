/**
 * @Title: BudgetEstimateDao.java
 * @Package com.glens.pwCloudOs.pm.pb.budgetEstimate.dao
 * @Description: TODO
 * Copyright: Copyright (c) 2016 
 * Company:南京量为石信息科技有限公司
 * @author 
 * @date 2017-2-8 下午2:02:54
 * @version V1.0
 */

package com.glens.pwCloudOs.pm.pb.budgetEstimate.dao;

import java.util.List;
import java.util.Map;

import com.glens.eap.platform.core.annotation.MybatisNamespaceProcessor;
import com.glens.eap.platform.core.orm.mybatis.exception.MyBatisAccessException;
import com.glens.eap.platform.framework.dao.impl.EAPAbstractDao;

/**
 * 
 * 
 * @author
 * @version V1.0
 */

@MybatisNamespaceProcessor(value = "com.glens.pwCloudOs.pm.pb.bdgetEstimate.dao.budgetEstimateMapper")
public class BudgetEstimateDao extends EAPAbstractDao {

	public List<Map<String, String>> selectProPhaseList(
			Map<String, Object> params) {

		try {

			return this.getSqlSession().selectList(
					this.getSqlStatement("selectProPhaseList"), params);
		} catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql="
					+ getSqlStatement("selectProPhaseList"), e);
		}
	}
	
	public List<Map<String, String>> getPhaseListByPro(
			Map<String, Object> params) {

		try {

			return this.getSqlSession().selectList(
					this.getSqlStatement("getPhaseListByPro"), params);
		} catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql="
					+ getSqlStatement("getPhaseListByPro"), e);
		}
	}
	

	public int insertProPhase(Map<String, Object> params) {

		try {

			return this.getSqlSession().insert(
					this.getSqlStatement("insertProPhase"), params);
		} catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql="
					+ getSqlStatement("insertProPhase"), e);
		}
	}
	
	public int addPmpbLog(Map<String, Object> params) {

		try {

			return this.getSqlSession().insert(
					this.getSqlStatement("addPmpbLog"), params);
		} catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql="
					+ getSqlStatement("addPmpbLog"), e);
		}
	}
	

	public int updateProPhase(Map<String, Object> params) {

		try {

			return this.getSqlSession().update(
					this.getSqlStatement("updateProPhase"), params);
		} catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql="
					+ getSqlStatement("updateProPhase"), e);
		}
	}

	public int deleteProPhase(Map<String, Object> params) {

		try {

			return this.getSqlSession().delete(
					this.getSqlStatement("deleteProPhase"), params);
		} catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql="
					+ getSqlStatement("deleteProPhase"), e);
		}
	}
	public int deleteFmProCost(Map<String, Object> params) {

		try {

			return this.getSqlSession().delete(
					this.getSqlStatement("deleteFmProCost"), params);
		} catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql="
					+ getSqlStatement("deleteFmProCost"), e);
		}
	}
	
	public int deleteProLabourCost(Map<String, Object> params) {

		try {

			return this.getSqlSession().delete(
					this.getSqlStatement("deleteProLabourCost"), params);
		} catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql="
					+ getSqlStatement("deleteProLabourCost"), e);
		}
	}

	public int deleteProItemCost(Map<String, Object> params) {

		try {

			return this.getSqlSession().delete(
					this.getSqlStatement("deleteProItemCost"), params);
		} catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql="
					+ getSqlStatement("deleteProItemCost"), e);
		}
	}

	public int deleteProRelevenfund(Map<String, Object> params) {

		try {

			return this.getSqlSession().delete(
					this.getSqlStatement("deleteProRelevenfund"), params);
		} catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql="
					+ getSqlStatement("deleteProRelevenfund"), e);
		}
	}

	public List<Map<String, Object>> selectProBudgetEstimateList(
			Map<String, Object> params) {

		try {

			return this
					.getSqlSession()
					.selectList(
							this.getSqlStatement("selectProBudgetEstimateList"),
							params);
		} catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql="
					+ getSqlStatement("selectProBudgetEstimateList"), e);
		}
	}
	
	public List<Map<String, Object>> getprodetail(
			Map<String, Object> params) {

		try {

			return this
					.getSqlSession()
					.selectList(
							this.getSqlStatement("getprodetail"),
							params);
		} catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql="
					+ getSqlStatement("getprodetail"), e);
		}
	}
	
	public List<Map<String, Object>> getDistrictManager(
			Map<String, Object> params) {

		try {

			return this.getSqlSession().selectList(
					this.getSqlStatement("getDistrictManager"), params);
		} catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql="
					+ getSqlStatement("getDistrictManager"), e);
		}
	}

	public Map<String, Object> selectProBudgetEstimate(
			Map<String, Object> params) {

		try {

			return this.getSqlSession().selectOne(
					this.getSqlStatement("selectProBudgetEstimate"), params);
		} catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql="
					+ getSqlStatement("selectProBudgetEstimate"), e);
		}
	}

	public List<Map<String, Object>> selectProLabourCostList(
			Map<String, Object> params) {

		try {

			return this.getSqlSession().selectList(
					this.getSqlStatement("selectProLabourCostList"), params);
		} catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql="
					+ getSqlStatement("selectProLabourCostList"), e);
		}
	}

	public List<Map<String, Object>> selectProItemCostList(
			Map<String, Object> params) {

		try {

			return this.getSqlSession().selectList(
					this.getSqlStatement("selectProItemCostList"), params);
		} catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql="
					+ getSqlStatement("selectProItemCostList"), e);
		}
	}

	public List<Map<String, Object>> selectProReleventFundList(
			Map<String, Object> params) {

		try {

			return this.getSqlSession().selectList(
					this.getSqlStatement("selectProReleventFundList"), params);
		} catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql="
					+ getSqlStatement("selectProReleventFundList"), e);
		}
	}

	public List<Map<String, Object>> getBounsSum(Map<String, Object> params) {

		try {

			return this.getSqlSession().selectList(
					this.getSqlStatement("getBounsSum"), params);
		} catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql="
					+ getSqlStatement("getBounsSum"), e);
		}
	}

	public List<Map<String, Object>> selectProLabourCost(
			Map<String, Object> params) {

		try {

			return this.getSqlSession().selectList(
					this.getSqlStatement("selectProLabourCost"), params);
		} catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql="
					+ getSqlStatement("selectProLabourCost"), e);
		}
	}

	public List<Map<String, Object>> selectProItemCostByCode(
			Map<String, Object> params) {

		try {

			return this.getSqlSession().selectList(
					this.getSqlStatement("selectProItemCostByCode"), params);
		} catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql="
					+ getSqlStatement("selectProItemCostByCode"), e);
		}
	}

	public List<Map<String, Object>> selectProPhaseSubTot(
			Map<String, Object> params) {

		try {

			return this.getSqlSession().selectList(
					this.getSqlStatement("selectProPhaseSubTot"), params);
		} catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql="
					+ getSqlStatement("selectProPhaseSubTot"), e);
		}
	}

	public Map<String, Object> selectProTotCost(Map<String, Object> params) {

		try {

			return this.getSqlSession().selectOne(
					this.getSqlStatement("selectProTotCost"), params);
		} catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql="
					+ getSqlStatement("selectProTotCost"), e);
		}
	}

	public List<Map<String, String>> selectItemList(Map<String, Object> params) {

		try {

			return this.getSqlSession().selectList(
					this.getSqlStatement("selectItemList"), params);
		} catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql="
					+ getSqlStatement("selectItemList"), e);
		}
	}

	public List<Map<String, Object>> selectProItemCostDetail(
			Map<String, Object> params) {

		try {

			return this.getSqlSession().selectList(
					this.getSqlStatement("selectProItemCostDetail"), params);
		} catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql="
					+ getSqlStatement("selectProItemCostDetail"), e);
		}
	}

	public int insertProBudgetEstimate(Map<String, Object> params) {

		try {

			return this.getSqlSession().insert(
					this.getSqlStatement("insertProBudgetEstimate"), params);
		} catch (Exception e) {
			// TODO: handle exception

			throw new MyBatisAccessException("exe sql="
					+ getSqlStatement("insertProBudgetEstimate"), e);
		}
	}
	public int insertFmProCost(Map<String, Object> params) {

		try {

			return this.getSqlSession().insert(
					this.getSqlStatement("insertFmProCost"), params);
		} catch (Exception e) {
			// TODO: handle exception

			throw new MyBatisAccessException("exe sql="
					+ getSqlStatement("insertFmProCost"), e);
		}
	}
	
	public int updateProBudgetEstimate(Map<String, Object> params) {

		try {

			return this.getSqlSession().update(
					this.getSqlStatement("updateProBudgetEstimate"), params);
		} catch (Exception e) {
			// TODO: handle exception

			throw new MyBatisAccessException("exe sql="
					+ getSqlStatement("updateProBudgetEstimate"), e);
		}
	}
	public int updateProBudgetEstimateVersion(Map<String, Object> params) {

		try {

			return this.getSqlSession().update(
					this.getSqlStatement("updateProBudgetEstimateVersion"), params);
		} catch (Exception e) {
			// TODO: handle exception

			throw new MyBatisAccessException("exe sql="
					+ getSqlStatement("updateProBudgetEstimateVersion"), e);
		}
	}
	
	public int deleteProBudgetEstimate(Map<String, Object> params) {

		try {

			return this.getSqlSession().delete(
					this.getSqlStatement("deleteProBudgetEstimate"), params);
		} catch (Exception e) {
			// TODO: handle exception

			throw new MyBatisAccessException("exe sql="
					+ getSqlStatement("deleteProBudgetEstimate"), e);
		}
	}

	public int deleteLabourCost(Map<String, Object> params) {

		try {

			return this.getSqlSession().delete(
					this.getSqlStatement("deleteLabourCost"), params);
		} catch (Exception e) {
			// TODO: handle exception

			throw new MyBatisAccessException("exe sql="
					+ getSqlStatement("deleteLabourCost"), e);
		}
	}

	public int deleteProReleventFund(Map<String, Object> params) {

		try {

			return this.getSqlSession().delete(
					this.getSqlStatement("deleteProReleventFund"), params);
		} catch (Exception e) {
			// TODO: handle exception

			throw new MyBatisAccessException("exe sql="
					+ getSqlStatement("deleteProReleventFund"), e);
		}
	}

	public int deleteProCost(Map<String, Object> params) {

		try {

			return this.getSqlSession().delete(
					this.getSqlStatement("deleteProCost"), params);
		} catch (Exception e) {
			// TODO: handle exception

			throw new MyBatisAccessException("exe sql="
					+ getSqlStatement("deleteProCost"), e);
		}
	}

	public int publishProBudgetEstimate(Map<String, Object> params) {

		try {

			return this.getSqlSession().update(
					this.getSqlStatement("publishProBudgetEstimate"), params);
		} catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql="
					+ getSqlStatement("publishProBudgetEstimate"), e);
		}
	}

	public int insertProPhaseReleventFund(List<Map<String, Object>> list) {

		try {

			return this.getSqlSession().insert(
					this.getSqlStatement("insertProPhaseReleventFund"), list);
		} catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql="
					+ getSqlStatement("insertProPhaseReleventFund"), e);
		}
	}

	public int insertProPhaseLabourCost(Map<String, Object> params) {

		try {

			return this.getSqlSession().insert(
					this.getSqlStatement("insertProPhaseLabourCost"), params);
		} catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql="
					+ getSqlStatement("insertProPhaseLabourCost"), e);
		}
	}
	
	
	public int copyProPhaseLabourCost(List<Map<String, Object>> list) {

		try {

			return this.getSqlSession().insert(
					this.getSqlStatement("copyProPhaseLabourCost"), list);
		} catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql="
					+ getSqlStatement("copyProPhaseLabourCost"), e);
		}
	}
	public int deleteProPhaseLabourCost(Map<String, Object> params) {

		try {

			return this.getSqlSession().delete(
					this.getSqlStatement("deleteProPhaseLabourCost"), params);
		} catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql="
					+ getSqlStatement("deleteProPhaseLabourCost"), e);
		}
	}

	public int updateProPhaseLabourCost(Map<String, Object> params) {

		try {

			return this.getSqlSession().update(
					this.getSqlStatement("updateProPhaseLabourCost"), params);
		} catch (Exception e) {
			// TODO: handle exception

			throw new MyBatisAccessException("exe sql="
					+ getSqlStatement("updateProPhaseLabourCost"), e);
		}
	}

	public int insertProPhaseCost(List<Map<String, Object>> list) {

		try {

			return this.getSqlSession().insert(
					this.getSqlStatement("insertProPhaseCost"), list);
		} catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql="
					+ getSqlStatement("insertProPhaseCost"), e);
		}
	}

	public int insertProCostDesc(List<Map<String, Object>> list) {

		try {

			return this.getSqlSession().insert(
					this.getSqlStatement("insertProCostDesc"), list);
		} catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql="
					+ getSqlStatement("insertProCostDesc"), e);
		}
	}

	public int deleteProCostDesc(Map<String, Object> params) {

		try {

			return this.getSqlSession().delete(
					this.getSqlStatement("deleteProCostDesc"), params);
		} catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql="
					+ getSqlStatement("deleteProCostDesc"), e);
		}
	}

	public List<Map<String, Object>> selectProPhaseEmployeeLabourCost(
			Map<String, Object> params) {

		try {

			return this.getSqlSession().selectList(
					this.getSqlStatement("selectProPhaseEmployeeLabourCost"),
					params);

		} catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql="
					+ getSqlStatement("selectProPhaseEmployeeLabourCost"), e);
		}
	}
	
	public List<Map<String, Object>> getProNo(
			Map<String, Object> params) {

		try {

			return this.getSqlSession().selectList(
					this.getSqlStatement("getProNo"),
					params);

		} catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql="
					+ getSqlStatement("getProNo"), e);
		}
	}
	
	public List<Map<String, Object>> getDuptManager(
			Map<String, Object> params) {

		try {

			return this.getSqlSession().selectList(
					this.getSqlStatement("getDuptManager"),
					params);

		} catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql="
					+ getSqlStatement("getDuptManager"), e);
		}
	}
	public List<Map<String, Object>> getDeptManager(
			Map<String, Object> params) {

		try {

			return this.getSqlSession().selectList(
					this.getSqlStatement("getDeptManager"),
					params);

		} catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql="
					+ getSqlStatement("getDeptManager"), e);
		}
	}
	public List<Map<String, Object>> getjianguan(
			Map<String, Object> params) {

		try {

			return this.getSqlSession().selectList(
					this.getSqlStatement("getjianguan"),
					params);

		} catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql="
					+ getSqlStatement("getjianguan"), e);
		}
	}
	
	public List<Map<String, Object>> getFinance(
			Map<String, Object> params) {

		try {

			return this.getSqlSession().selectList(
					this.getSqlStatement("getFinance"),
					params);

		} catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql="
					+ getSqlStatement("getFinance"), e);
		}
	}
	public List<Map<String, Object>> getgeneral(
			Map<String, Object> params) {

		try {

			return this.getSqlSession().selectList(
					this.getSqlStatement("getgeneral"),
					params);

		} catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql="
					+ getSqlStatement("getgeneral"), e);
		}
	}
	
	public int insertProPhaseItemLabourCost(Map<String, Object> params) {

		try {

			return this.getSqlSession().insert(
					this.getSqlStatement("insertProPhaseItemLabourCost"),
					params);
		} catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql="
					+ getSqlStatement("insertProPhaseItemLabourCost"), e);
		}
	}

	public int updatePlusProPhaseItemLabourCost(Map<String, Object> params) {

		try {

			return this.getSqlSession().update(
					this.getSqlStatement("updatePlusProPhaseItemLabourCost"),
					params);
		} catch (Exception e) {
			// TODO: handle exception

			throw new MyBatisAccessException("exe sql="
					+ getSqlStatement("updatePlusProPhaseItemLabourCost"), e);
		}
	}
	public int updateVersionStatus(Map<String, Object> params) {

		try {

			return this.getSqlSession().update(
					this.getSqlStatement("updateVersionStatus"),
					params);
		} catch (Exception e) {
			// TODO: handle exception

			throw new MyBatisAccessException("exe sql="
					+ getSqlStatement("updateVersionStatus"), e);
		}
	}
	
	public List<Map<String, Object>> getVersions(
			Map<String, Object> params) {

		try {

			return this
					.getSqlSession()
					.selectList(
							this.getSqlStatement("getVersions"),
							params);
		} catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql="
					+ getSqlStatement("getVersions"), e);
		}
	}
	
	

	public int updateReduceProPhaseItemLabourCost(Map<String, Object> params) {

		try {

			return this.getSqlSession().update(
					this.getSqlStatement("updateReduceProPhaseItemLabourCost"),
					params);
		} catch (Exception e) {
			// TODO: handle exception

			throw new MyBatisAccessException("exe sql="
					+ getSqlStatement("updateReduceProPhaseItemLabourCost"), e);
		}
	}

	public List<Map<String, Object>> selectProPhaseLabourCostCountInItem(
			Map<String, Object> params) {

		try {

			return this
					.getSqlSession()
					.selectList(
							this.getSqlStatement("selectProPhaseLabourCostCountInItem"),
							params);
		} catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql="
					+ getSqlStatement("selectProPhaseLabourCostCountInItem"), e);
		}
	}

	public int selectProBudgetEstimateCount(Map<String, Object> params) {

		try {

			return this.getSqlSession().selectOne(
					this.getSqlStatement("selectProBudgetEstimateCount"),
					params);
		} catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql="
					+ getSqlStatement("selectProBudgetEstimateCount"), e);
		}
	}

	public List<Map<String, Object>> getContractProperty(
			Map<String, Object> params) {

		try {

			return this.getSqlSession().selectList(
					this.getSqlStatement("getContractProperty"), params);
		} catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql="
					+ getSqlStatement("getContractProperty"), e);
		}
	}

	public List<Map<String, Object>> getJobs(Map<String, Object> params) {

		try {

			return this.getSqlSession().selectList(
					this.getSqlStatement("getJobs"), params);
		} catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql="
					+ getSqlStatement("getJobs"), e);
		}
	}

	public List<Map<String, Object>> getContractCost(Map<String, Object> params) {

		try {

			return this.getSqlSession().selectList(
					this.getSqlStatement("getContractCost"), params);
		} catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql="
					+ getSqlStatement("getContractCost"), e);
		}
	}

	public List<Map<String, Object>> selectProEstimateLabour(
			Map<String, Object> params) {
		try {

			return this.getSqlSession().selectList(
					this.getSqlStatement("selectProEstimateLabour"), params);
		} catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql="
					+ getSqlStatement("selectProEstimateLabour"), e);
		}
	}

	public List<Map<String, Object>> queryForAllList(Map<String, Object> params) {

		try {

			return this.getSqlSession().selectList(
					this.getSqlStatement("queryForAllList"), params);
		} catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql="
					+ getSqlStatement("queryForAllList"), e);
		}
	}

	public List queryFeeItemsCostList(Map<String, Object> paramsMap) {
		// TODO Auto-generated method stub
		try {

			return this.getSqlSession().selectList(
					getSqlStatement("queryFeeItemsCostList"), paramsMap);
		} catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql="
					+ getSqlStatement("queryFeeItemsCostList"), e);
		}
	}

	public Map queryBudgetCost(Map<String, Object> paramsMap) {
		// TODO Auto-generated method stub
		try {

			return this.getSqlSession().selectOne(
					getSqlStatement("queryBudgetCost"), paramsMap);
		} catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql="
					+ getSqlStatement("queryBudgetCost"), e);
		}
	}
	
	public List<Map<String, Object>> selectProMemberLabour(
			Map<String, Object> params) {
		try {

			return this.getSqlSession().selectList(
					this.getSqlStatement("selectProMemberLabour"), params);
		} catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql="
					+ getSqlStatement("selectProMemberLabour"), e);
		}
	}
	//----------------------------------改版后代码 ------------------------
	
	public int createProBudgetEstimate(Map<String, Object> params) {

		try {

			return this.getSqlSession().insert(
					this.getSqlStatement("createProBudgetEstimate"), params);
		} catch (Exception e) {
			// TODO: handle exception

			throw new MyBatisAccessException("exe sql="
					+ getSqlStatement("createProBudgetEstimate"), e);
		}
	}
	public int createProBudgetEstimateVersion(Map<String, Object> params) {

		try {

			return this.getSqlSession().insert(
					this.getSqlStatement("createProBudgetEstimateVersion"), params);
		} catch (Exception e) {
			// TODO: handle exception

			throw new MyBatisAccessException("exe sql="
					+ getSqlStatement("createProBudgetEstimateVersion"), e);
		}
	}
	
	
	public List<Map<String, Object>> getPmPbItemCostDesc(
			Map<String, Object> params) {
		try {

			return this.getSqlSession().selectList(
					this.getSqlStatement("getPmPbItemCostDesc"), params);
		} catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql="
					+ getSqlStatement("getPmPbItemCostDesc"), e);
		}
	}
	
	public List<Map<String, Object>> getBUdgetByProNo(
			Map<String, Object> params) {
		try {

			return this.getSqlSession().selectList(
					this.getSqlStatement("getBUdgetByProNo"), params);
		} catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql="
					+ getSqlStatement("getBUdgetByProNo"), e);
		}
	}
	public List<Map<String, Object>> getProCost(
			Map<String, Object> params) {
		try {

			return this.getSqlSession().selectList(
					this.getSqlStatement("getProCost"), params);
		} catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql="
					+ getSqlStatement("getProCost"), e);
		}
	}
	public List<Map<String, Object>> getProTotalCost(
			Map<String, Object> params) {
		try {

			return this.getSqlSession().selectList(
					this.getSqlStatement("getProTotalCost"), params);
		} catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql="
					+ getSqlStatement("getProTotalCost"), e);
		}
	}
	
	public List<Map<String, Object>> getProPhase(
			Map<String, Object> params) {
		try {

			return this.getSqlSession().selectList(
					this.getSqlStatement("getProPhase"), params);
		} catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql="
					+ getSqlStatement("getProPhase"), e);
		}
	}
	public List<Map<String, Object>> getLabours(Map<String, Object> params) {
		try {

			return this.getSqlSession().selectList(
					this.getSqlStatement("getLabours"), params);
		} catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql="
					+ getSqlStatement("getLabours"), e);
		}
	}
	public List<Map<String, Object>> getPmPbReleventfund(Map<String, Object> params) {
		try {

			return this.getSqlSession().selectList(
					this.getSqlStatement("getPmPbReleventfund"), params);
		} catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql="
					+ getSqlStatement("getPmPbReleventfund"), e);
		}
	}
	
	public List<Map<String, Object>> getPmPbItemCost(Map<String, Object> params) {
		try {

			return this.getSqlSession().selectList(
					this.getSqlStatement("getPmPbItemCost"), params);
		} catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql="
					+ getSqlStatement("getPmPbItemCost"), e);
		}
	}
	
	public List<Map<String, Object>> getProPhasesCost1(Map<String, Object> params) {
		try {

			return this.getSqlSession().selectList(
					this.getSqlStatement("getProPhasesCost"), params);
		} catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql="
					+ getSqlStatement("getProPhasesCost"), e);
		}
	}
	
	public List<Map<String, Object>> getProPlanBooks(Map<String, Object> params) {
		try {

			return this.getSqlSession().selectList(
					this.getSqlStatement("getProPlanBooks"), params);
		} catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql="
					+ getSqlStatement("getProPlanBooks"), e);
		}
	}
	
	public List<Map<String, Object>> getProContract(Map<String, Object> params) {
		try {

			return this.getSqlSession().selectList(
					this.getSqlStatement("getProContract"), params);
		} catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql="
					+ getSqlStatement("getProContract"), e);
		}
	}
	
	public List<Map<String, Object>> getPmPblogs(Map<String, Object> params) {
		try {

			return this.getSqlSession().selectList(
					this.getSqlStatement("getPmPblogs"), params);
		} catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql="
					+ getSqlStatement("getPmPblogs"), e);
		}
	}
	
	public List<Map<String, Object>> getPmVersion(Map<String, Object> params) {
		try {

			return this.getSqlSession().selectList(
					this.getSqlStatement("getPmVersion"), params);
		} catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql="
					+ getSqlStatement("getPmVersion"), e);
		}
	}
	
	

}
