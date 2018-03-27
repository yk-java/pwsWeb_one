/**

 * @Title: PmBaseDao.java

 * @Package com.glens.pwCloudOs.pm.baseMgr.pmBase.dao

 * @Description: TODO

 * Copyright: Copyright (c) 2016 

 * Company:南京量为石信息科技有限公司

 * @author 

 * @date 2016-6-8 上午10:21:53

 * @version V1.0

 **/

package com.glens.pwCloudOs.pm.baseMgr.pmBase.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.glens.eap.platform.core.annotation.MybatisNamespaceProcessor;
import com.glens.eap.platform.core.beans.ValueObject;
import com.glens.eap.platform.core.orm.mybatis.exception.MyBatisAccessException;
import com.glens.eap.platform.framework.beans.PageBean;
import com.glens.eap.platform.framework.dao.impl.EAPAbstractDao;
import com.glens.pwCloudOs.pm.baseMgr.pmBase.vo.PmBaseVo;

@MybatisNamespaceProcessor(value = "com.glens.pwCloudOs.pm.baseMgr.pmBase.dao.PmBaseVoMapper")
public class PmBaseDao extends EAPAbstractDao {
	public ValueObject findByProCode(Object parameter) {
		try {
			return (ValueObject) this.getSqlSession().selectOne(
					getSqlStatement("findByProCode"), parameter);
		} catch (Exception e) {
			throw new MyBatisAccessException("exe sql="
					+ getSqlStatement("findByProCode"), e);
		}
	}

	public int active(ValueObject vo) {

		try {

			return this.getSqlSession().update(getSqlStatement("active"), vo);
		} catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql="
					+ getSqlStatement("active"), e);
		}
	}

	public int close(ValueObject vo) {

		try {

			return this.getSqlSession().update(getSqlStatement("close"), vo);
		} catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql="
					+ getSqlStatement("close"), e);
		}
	}

	public int insertProMember(Map<String, String> member) {

		try {

			return this.getSqlSession().insert(
					getSqlStatement("insertProMember"), member);
		} catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql="
					+ getSqlStatement("insertProMember"), e);
		}
	}

	public int batchAddProMember(List<Map<String, Object>> members) {
		try {
			return this.getSqlSession().insert(
					getSqlStatement("batchAddProMember"), members);
		} catch (Exception e) {
			throw new MyBatisAccessException("exe sql="
					+ getSqlStatement("batchAddProMember"), e);
		}
	}

	public int deleteProMember(Map<String, String> params) {

		try {

			return this.getSqlSession().delete(
					getSqlStatement("deleteProMember"), params);
		} catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql="
					+ getSqlStatement("deleteProMember"), e);
		}
	}

	public int logoffProMember(Map<String, String> params) {

		try {

			return this.getSqlSession().delete(
					getSqlStatement("logoffProMember"), params);
		} catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql="
					+ getSqlStatement("logoffProMember"), e);
		}
	}

	public List<Map<String, Object>> selectProMember(Map<String, String> params) {

		try {

			return this.getSqlSession().selectList(
					getSqlStatement("selectProMember"), params);
		} catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql="
					+ getSqlStatement("selectProMember"), e);
		}
	}

	public List<Map<String, String>> getFileType(Object params) {

		try {

			return this.getSqlSession().selectList(
					getSqlStatement("getFileType"), params);
		} catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql="
					+ getSqlStatement("getFileType"), e);
		}
	}

	public List getemplist(Object params) {

		try {

			return this.getSqlSession().selectList(
					getSqlStatement("getemplist"), params);
		} catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql="
					+ getSqlStatement("getemplist"), e);
		}
	}

	public List getaccount(Object params) {

		try {

			return this.getSqlSession().selectList(
					getSqlStatement("getaccount"), params);
		} catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql="
					+ getSqlStatement("getaccount"), e);
		}
	}

	public List queryList() {
		try {

			return this.getSqlSession()
					.selectList(getSqlStatement("queryList"));
		} catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql="
					+ getSqlStatement("queryList"), e);
		}
	}

	public List queryEmployeeProList(String jobNo) {
		try {

			return this.getSqlSession().selectList(
					getSqlStatement("queryEmployeeProList"), jobNo);
		} catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql="
					+ getSqlStatement("queryEmployeeProList"), e);
		}
	}

	public List queryAllProList() {
		try {

			return this.getSqlSession().selectList(
					getSqlStatement("queryAllProList"));
		} catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql="
					+ getSqlStatement("queryAllProList"), e);
		}
	}

	public Map getpro(String rowid) {
		try {

			return this.getSqlSession().selectOne(getSqlStatement("getpro"),
					Long.parseLong(rowid));
		} catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql="
					+ getSqlStatement("getpro"), e);
		}
	}

	public List queryProBaseList(Map parasMap) {
		try {

			return this.getSqlSession().selectList(
					getSqlStatement("queryProBaseList"), parasMap);
		} catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql="
					+ getSqlStatement("queryProBaseList"), e);
		}
	}

	public List queryFinishProgress(Map<String, Object> paramsMap) {
		try {

			return this.getSqlSession().selectList(
					getSqlStatement("queryFinishProgress"), paramsMap);
		} catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql="
					+ getSqlStatement("queryFinishProgress"), e);
		}
	}

	public List queryFinancial(Map<String, Object> paramsMap) {
		try {

			return this.getSqlSession().selectList(
					getSqlStatement("queryFinancial"), paramsMap);
		} catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql="
					+ getSqlStatement("queryFinancial"), e);
		}
	}

	public List queryDocument(Map<String, Object> paramsMap) {
		try {

			return this.getSqlSession().selectList(
					getSqlStatement("queryDocument"), paramsMap);
		} catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql="
					+ getSqlStatement("queryDocument"), e);
		}
	}

	public List queryExpensesClaim(Map<String, Object> paramsMap) {
		try {

			return this.getSqlSession().selectList(
					getSqlStatement("queryExpensesClaim"), paramsMap);
		} catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql="
					+ getSqlStatement("queryExpensesClaim"), e);
		}
	}

	public List queryExpensesDorm(Map<String, Object> paramsMap) {
		try {

			return this.getSqlSession().selectList(
					getSqlStatement("queryExpensesDorm"), paramsMap);
		} catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql="
					+ getSqlStatement("queryExpensesDorm"), e);
		}
	}

	public List queryExpensesAsset(Map<String, Object> paramsMap) {
		// TODO Auto-generated method stub
		try {

			return this.getSqlSession().selectList(
					getSqlStatement("queryExpensesAsset"), paramsMap);
		} catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql="
					+ getSqlStatement("queryExpensesAsset"), e);
		}
	}

	public List queryProMember(Map<String, Object> paramsMap) {
		try {

			return this.getSqlSession().selectList(
					getSqlStatement("queryProMember"), paramsMap);
		} catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql="
					+ getSqlStatement("queryProMember"), e);
		}
	}

	public List queryContract(Map<String, Object> paramsMap) {
		try {

			return this.getSqlSession().selectList(
					getSqlStatement("queryContract"), paramsMap);
		} catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql="
					+ getSqlStatement("queryContract"), e);
		}
	}

	public List queryInvoice(Map<String, Object> paramsMap) {
		// TODO Auto-generated method stub
		try {

			return this.getSqlSession().selectList(
					getSqlStatement("queryInvoice"), paramsMap);
		} catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql="
					+ getSqlStatement("queryInvoice"), e);
		}
	}

	public List queryRepayment(Map<String, Object> paramsMap) {
		// TODO Auto-generated method stub
		try {

			return this.getSqlSession().selectList(
					getSqlStatement("queryRepayment"), paramsMap);
		} catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql="
					+ getSqlStatement("queryRepayment"), e);
		}
	}

	public List queryProDocument(Map<String, Object> paramsMap) {
		// TODO Auto-generated method stub
		try {

			return this.getSqlSession().selectList(
					getSqlStatement("queryProDocument"), paramsMap);
		} catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql="
					+ getSqlStatement("queryProDocument"), e);
		}
	}

	public List queryProAsset(Map<String, Object> paramsMap) {
		// TODO Auto-generated method stub
		try {

			return this.getSqlSession().selectList(
					getSqlStatement("queryProAsset"), paramsMap);
		} catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql="
					+ getSqlStatement("queryProAsset"), e);
		}
	}

	public List queryProDorm(Map<String, Object> paramsMap) {
		try {

			return this.getSqlSession().selectList(
					getSqlStatement("queryProDorm"), paramsMap);
		} catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql="
					+ getSqlStatement("queryProDorm"), e);
		}
	}

	public List queryProReturn(Map<String, Object> paramsMap) {
		// TODO Auto-generated method stub
		try {

			return this.getSqlSession().selectList(
					getSqlStatement("queryProReturn"), paramsMap);
		} catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql="
					+ getSqlStatement("queryProReturn"), e);
		}
	}

	public List queryFeeTypeCostList(Map<String, Object> paramsMap) {
		try {
			return this.getSqlSession().selectList(
					getSqlStatement("queryFeeTypeCostList"), paramsMap);
		} catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql="
					+ getSqlStatement("queryFeeTypeCostList"), e);
		}
	}

	public Map getProCost(Map<String, Object> paramsMap) {

		try {
			return this.getSqlSession().selectOne(
					getSqlStatement("getProCost"), paramsMap);
		} catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql="
					+ getSqlStatement("getProCost"), e);
		}
	}

	public Map queryBudgetCost(Map<String, Object> paramsMap) {

		try {
			return this.getSqlSession().selectOne(
					getSqlStatement("queryBudgetCost"), paramsMap);
		} catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql="
					+ getSqlStatement("queryBudgetCost"), e);
		}
	}

	public List<Map<String, Object>> queryAllFeeItem() {
		// TODO Auto-generated method stub
		try {

			return this.getSqlSession().selectList(
					getSqlStatement("queryAllFeeItem"));
		} catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql="
					+ getSqlStatement("queryAllFeeItem"), e);
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

	public List<Map<String, Object>> queryPmBaseForOAList() {
		// TODO Auto-generated method stub
		try {

			return this.getSqlSession().selectList(
					getSqlStatement("queryPmBaseForOAList"));
		} catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql="
					+ getSqlStatement("queryPmBaseForOAList"), e);
		}
	}

	public List<Map<String, Object>> queryProCostForList(
			Map<String, Object> paramsMap) {
		try {

			return this.getSqlSession().selectList(
					getSqlStatement("queryProCostForList"), paramsMap);
		} catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql="
					+ getSqlStatement("queryProCostForList"), e);
		}
	}

	public List<Map<String, Object>> queryProPeopleCostForList(
			Map<String, Object> paramsMap) {
		// TODO Auto-generated method stub
		try {

			return this.getSqlSession().selectList(
					getSqlStatement("queryProPeopleCostForList"), paramsMap);
		} catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql="
					+ getSqlStatement("queryProPeopleCostForList"), e);
		}
	}
	
	

	public int getDialogProsCount(Map parameters) {
		// TODO Auto-generated method stub
		try {
			
			return (Integer) this.getSqlSession().selectOne(getSqlStatement("getDialogProsCount"), parameters);
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new MyBatisAccessException("exe sql=" + getSqlStatement("getDialogProsCount"), e);
		}
	}


	
	
	public PageBean getDialogPros(Map parameters, int currentPage, int perpage) {
		
		PageBean page = null;
		
		try {
			
			int totalCount = this.getDialogProsCount(parameters);
			if (totalCount < 1) {
				
				page = new PageBean();
				page.setList(new ArrayList());
			}
			else {
				
				page = new PageBean(totalCount, currentPage, perpage);
				page = this.getDialogPros(parameters, page);
			}
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new MyBatisAccessException("exe sql=" + getSqlStatement("getDialogPros"), e);
		}
		
		return page;
	}

	private PageBean getDialogPros(Map parameters, PageBean page) {
		// TODO Auto-generated method stub
		try {
			
			if (parameters == null) {
				parameters = new HashMap();
			}
			
			parameters.put("firstResult", page.getPerpage() * (page.getCurrentPage() - 1));
			parameters.put("maxResult", page.getPerpage());
			
			List list = this.getSqlSession().selectList(getSqlStatement("getDialogPros"), parameters);
			page.setList(list);
		}
		catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql=" + getSqlStatement("getDialogPros"), e);
		}
		
		return page;
	}

}
