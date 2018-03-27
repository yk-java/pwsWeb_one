package com.glens.pwCloudOs.pm.pb.rollingBudget.dao;

import java.util.List;
import java.util.Map;

import com.glens.eap.platform.core.annotation.MybatisNamespaceProcessor;
import com.glens.eap.platform.core.orm.mybatis.exception.MyBatisAccessException;
import com.glens.eap.platform.framework.dao.impl.EAPAbstractDao;


@MybatisNamespaceProcessor(value="com.glens.pwCloudOs.pm.pb.rollingBudget.dao.rollingGudgetMapper")
public class RollingGudgetDao extends EAPAbstractDao {


	public List selectProRollingBudgetList(Object parameters) {
		// TODO Auto-generated method stub
		try {

			return this.getSqlSession().selectList(getSqlStatement("selectProRollingBudgetList"), parameters);
		}
		catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql=" + getSqlStatement("selectProRollingBudgetList"), e);
		}
	}

	public List<Map<String, Object>> selectProItemCostDetail(Map<String, Object> params) {

		try {

			return this.getSqlSession().selectList(this.getSqlStatement("selectProItemCostDetail"), params);
		}
		catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql=" + getSqlStatement("selectProItemCostDetail"), e);
		}
	}


	//得到项目区间
	public List<Map<String, String>> selectProSection(Map<String, Object> params) {

		try {

			return this.getSqlSession().selectList(this.getSqlStatement("selectProSection"), params);
		}
		catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql=" + getSqlStatement("selectProSection"), e);
		}
	}
	
	public List<Map<String, String>> getNewVersion(Map<String, Object> params) {
		try {

			return this.getSqlSession().selectList(this.getSqlStatement("getNewVersion"), params);
		}
		catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql=" + getSqlStatement("getNewVersion"), e);
		}
	}

	
	//删除预算项
	public int deleteProCost(Map<String, Object> params) {

		try {

			return this.getSqlSession().delete(this.getSqlStatement("deleteProCost"), params);
		}
		catch (Exception e) {
			// TODO: handle exception

			throw new MyBatisAccessException("exe sql=" + getSqlStatement("deleteProCost"), e);
		}
	}


	//新增 项目预算区间各项成本
	public int insertProSectionCost(List<Map<String, Object>> list) {

		try {

			return this.getSqlSession().insert(this.getSqlStatement("insertProSectionCost"), list);
		}
		catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql=" + getSqlStatement("insertProSectionCost"), e);
		}
	}




	public Map<String, Object> selectProRollingBudget(Map<String, Object> params) {

		try {


			return this.getSqlSession().selectOne(this.getSqlStatement("selectProRollingBudget"), params);
		}
		catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql=" + getSqlStatement("selectProRollingBudget"), e);
		}
	}

	public List<Map<String, Object>> getEmpCostDetail(Map<String, Object> params) {

		try {

			return this.getSqlSession().selectList(this.getSqlStatement("getEmpCostDetail"), params);
		}
		catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql=" + getSqlStatement("getEmpCostDetail"), e);
		}
	}




	public int insertTravelSubsidy(Map<String, Object> params) {

		try {
			return this.getSqlSession().insert(this.getSqlStatement("insertTravelSubsidy"), params);
		}
		catch (Exception e) {
			// TODO: handle exception

			throw new MyBatisAccessException("exe sql=" + getSqlStatement("insertTravelSubsidy"), e);
		}
	}
	
	public int insertSectionLabourCost(List<Map<String, Object>> list) {

		try {
			return this.getSqlSession().insert(this.getSqlStatement("insertSectionLabourCost"), list);
		}
		catch (Exception e) {
			// TODO: handle exception

			throw new MyBatisAccessException("exe sql=" + getSqlStatement("insertSectionLabourCost"), e);
		}
	}
	
	

	public int deleteTravelSubsidy(Map<String, Object> params) {

		try {

			return this.getSqlSession().delete(this.getSqlStatement("deleteTravelSubsidy"), params);
		}
		catch (Exception e) {
			// TODO: handle exception

			throw new MyBatisAccessException("exe sql=" + getSqlStatement("deleteTravelSubsidy"), e);
		}
	}
	
	public int deleteSectionLabourCost(Map<String, Object> params) {

		try {

			return this.getSqlSession().delete(this.getSqlStatement("deleteSectionLabourCost"), params);
		}
		catch (Exception e) {
			// TODO: handle exception

			throw new MyBatisAccessException("exe sql=" + getSqlStatement("deleteSectionLabourCost"), e);
		}
	}

	
	
	
	public List<Map<String, Object>> getSectionLabourCost(Map<String, Object> params) {

		try {

			return this.getSqlSession().selectList(this.getSqlStatement("getSectionLabourCost"), params);
		}
		catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql=" + getSqlStatement("getSectionLabourCost"), e);
		}
	}
	
	public int insertProRollingBudget(Map<String, Object> params) {
		
		try {
			
			return this.getSqlSession().insert(this.getSqlStatement("insertProRollingBudget"), params);
		}
		catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql=" + getSqlStatement("insertProRollingBudget"), e);
		}
	}
	
	public int insertProBudgetSections(List<Map<String, Object>> list) {
		
		try {
			
			return this.getSqlSession().insert(this.getSqlStatement("insertProBudgetSections"), list);
		}
		catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql=" + getSqlStatement("insertProBudgetSections"), e);
		}
	}
	
	public List<Map<String, String>> selectProByNo(Map<String, Object> params) {
		
		try {
			
			return this.getSqlSession().selectList(this.getSqlStatement("selectProByNo"), params);
		}
		catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql=" + getSqlStatement("insertProBudgetSections"), e);
		}
	}
	
	
	public List<Map<String, Object>> selectProSectionEmployeeLabourCost(Map<String, Object> params) {
		
		try {
			
			return this.getSqlSession().selectList(this.getSqlStatement("selectProSectionEmployeeLabourCost"), params);
					
		}
		catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql=" + getSqlStatement("selectProSectionEmployeeLabourCost"), e);
		}
	}
	
	public int selectProSectionLabourCostCountInItem(Map<String, Object> params) {
		
		try {
			
			return this.getSqlSession().selectOne(this.getSqlStatement("selectProSectionLabourCostCountInItem"), params);
		}
		catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql=" + getSqlStatement("selectProSectionLabourCostCountInItem"), e);
		}
	}
	
	public int selectSectionLabourCostCountInItem(Map<String, Object> params) {
		
		try {
			
			return this.getSqlSession().selectOne(this.getSqlStatement("selectSectionLabourCostCountInItem"), params);
		}
		catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql=" + getSqlStatement("selectSectionLabourCostCountInItem"), e);
		}
	}

	
	
	public int updatePlusProSectionItemLabourCost(Map<String, Object> params) {
		
		try {
			
			return this.getSqlSession().update(this.getSqlStatement("updatePlusProSectionItemLabourCost"), params);
		}
		catch (Exception e) {
			// TODO: handle exception
			
			throw new MyBatisAccessException("exe sql=" + getSqlStatement("updatePlusProSectionItemLabourCost"), e);
		}
	}
	
	public int updateVersion(Map<String, Object> params) {
		
		try {
			
			return this.getSqlSession().update(this.getSqlStatement("updateVersion"), params);
		}
		catch (Exception e) {
			// TODO: handle exception
			
			throw new MyBatisAccessException("exe sql=" + getSqlStatement("updateVersion"), e);
		}
	}
	
	

	public int updateProSectionItemLabourCost(Map<String, Object> params) {
		
		try {
			
			return this.getSqlSession().update(this.getSqlStatement("updateProSectionItemLabourCost"), params);
		}
		catch (Exception e) {
			// TODO: handle exception
			
			throw new MyBatisAccessException("exe sql=" + getSqlStatement("updateProSectionItemLabourCost"), e);
		}
	}
	public int insertProSectionItemLabourCost(Map<String, Object> params) {
	
		try {
			
			return this.getSqlSession().insert(this.getSqlStatement("insertProSectionItemLabourCost"), params);
		}
		catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql=" + getSqlStatement("insertProSectionItemLabourCost"), e);
		}
	}
	public int insertSectionItemLabourCost(Map<String, Object> params) {
		
		try {
			
			return this.getSqlSession().insert(this.getSqlStatement("insertSectionItemLabourCost"), params);
		}
		catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql=" + getSqlStatement("insertSectionItemLabourCost"), e);
		}
	}
	public List<Map<String, Object>> selectSectionLabourCost(Map<String, Object> params) {
		
		try {
			
			return this.getSqlSession().selectList(this.getSqlStatement("selectSectionLabourCost"), params);
					
		}
		catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql=" + getSqlStatement("selectSectionLabourCost"), e);
		}
	}
	
	public int insertBudgetVersion(Map<String, Object> params) {
		
		try {
			
			return this.getSqlSession().insert(this.getSqlStatement("insertBudgetVersion"), params);
		}
		catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql=" + getSqlStatement("insertBudgetVersion"), e);
		}
	}
	
	public List<Map<String, Object>> selectWritableSection(Map<String, Object> params) {
		
		try {
			
			return this.getSqlSession().selectList(this.getSqlStatement("selectWritableSection"), params);
		}
		catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql=" + getSqlStatement("selectWritableSection"), e);
		}
	}
	
	public int updateSectionReadonly(Map<String, Object> params) {
		
		try {
			
			return this.getSqlSession().update(this.getSqlStatement("updateSectionReadonly"), params);
		}
		catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql=" + getSqlStatement("updateSectionReadonly"), e);
		}
	}
	
	public List<Map<String, Object>> selectVersionSection(Map<String, Object> params) {
		
		try {
			
			return this.getSqlSession().selectList(this.getSqlStatement("selectVersionSection"), params);
		}
		catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql=" + getSqlStatement("selectVersionSection"), e);
		}
	}
	
	public Map<String, Object> selectVersionByCode(Map<String, Object> params) {
		
		try {
			
			return this.getSqlSession().selectOne(this.getSqlStatement("selectVersionByCode"), params);
		}
		catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql=" + getSqlStatement("selectVersionByCode"), e);
		}
	}
	
	public List<Map<String, Object>> selectVersionLabourCost(Map<String, Object> params) {
		
		try {
			
			return this.getSqlSession().selectList(this.getSqlStatement("selectVersionLabourCost"), params);
		}
		catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql=" + getSqlStatement("selectVersionLabourCost"), e);
		}
	}
	
	public List<Map<String, Object>> selectVersionItemCost(Map<String, Object> params) {
		
		try {
			
			return this.getSqlSession().selectList(this.getSqlStatement("selectVersionItemCost"), params);
		}
		catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql=" + getSqlStatement("selectVersionItemCost"), e);
		}
	}
	
	public int batchInsertSectionLabourCost(List<Map<String, Object>> list) {
		
		try {
			
			return this.getSqlSession().insert(this.getSqlStatement("batchInsertSectionLabourCost"), list);
		}
		catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql=" + getSqlStatement("batchInsertSectionLabourCost"), e);
		}
	}
	
	public List<Map<String, Object>> selectSectionLabourSumCost(Map<String, Object> params) {
		
		try {
			
			return this.getSqlSession().selectList(this.getSqlStatement("selectSectionLabourSumCost"), params);
		}
		catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql=" + getSqlStatement("selectSectionLabourSumCost"), e);
		}
	}
	
	public List<Map<String, Object>> selectProBudgetVersion(Map<String, Object> params) {
		
		try {
			
			return this.getSqlSession().selectList(this.getSqlStatement("selectProBudgetVersion"), params);
		}
		catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql=" + getSqlStatement("selectProBudgetVersion"), e);
		}
	}
	
	public Map<String, Object> selectProBudgetVersionByCode(Map<String, Object> params) {
		
		try {
			
			return this.getSqlSession().selectOne(this.getSqlStatement("selectProBudgetVersionByCode"), params);
		}
		catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql=" + getSqlStatement("selectProBudgetVersionByCode"), e);
		}
	}
	
}
