package com.glens.pwCloudOs.goods.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.glens.eap.platform.core.annotation.MybatisNamespaceProcessor;
import com.glens.eap.platform.core.orm.mybatis.exception.MyBatisAccessException;
import com.glens.eap.platform.framework.beans.PageBean;
import com.glens.eap.platform.framework.dao.impl.EAPAbstractDao;


@MybatisNamespaceProcessor(value = "com.glens.pwCloudOs.goods.dao.goodsMapper")
public class GoodsDao extends EAPAbstractDao {
	
	
	
	
	
	public PageBean getProcessPage(Map parameters, int currentPage, int perpage) {

		PageBean page = null;

		try {

			int totalCount = this.getProcessCount(parameters);
			if (totalCount < 1) {

				page = new PageBean();
				page.setList(new ArrayList());
			} else {

				page = new PageBean(totalCount, currentPage, perpage);
				page = this.getProcessPage(parameters, page);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new MyBatisAccessException("exe sql="
					+ getSqlStatement("getProcessPage"), e);
		}

		return page;
	}

	public int getProcessCount(Map parameters) {
		// TODO Auto-generated method stub
		try {

			return (Integer) this.getSqlSession().selectOne(
					getSqlStatement("getProcessCount"), parameters);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new MyBatisAccessException("exe sql="
					+ getSqlStatement("getProcessCount"), e);
		}
	}

	private PageBean getProcessPage(Map parameters, PageBean page) {
		// TODO Auto-generated method stub
		try {

			if (parameters == null) {
				parameters = new HashMap();
			}

			parameters.put("firstResult",
					page.getPerpage() * (page.getCurrentPage() - 1));
			parameters.put("maxResult", page.getPerpage());

			List list = this.getSqlSession().selectList(
					getSqlStatement("getProcessPage"), parameters);
			page.setList(list);
		} catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql="
					+ getSqlStatement("getProcessPage"), e);
		}

		return page;
	}
	
	
	
	public PageBean getCheckPage(Map parameters, int currentPage, int perpage) {

		PageBean page = null;

		try {

			int totalCount = this.getCheckPageCount(parameters);
			if (totalCount < 1) {

				page = new PageBean();
				page.setList(new ArrayList());
			} else {

				page = new PageBean(totalCount, currentPage, perpage);
				page = this.getCheckPage(parameters, page);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new MyBatisAccessException("exe sql="
					+ getSqlStatement("getCheckPage"), e);
		}

		return page;
	}

	public int getCheckPageCount(Map parameters) {
		// TODO Auto-generated method stub
		try {

			return (Integer) this.getSqlSession().selectOne(
					getSqlStatement("getCheckPageCount"), parameters);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new MyBatisAccessException("exe sql="
					+ getSqlStatement("getCheckPageCount"), e);
		}
	}

	private PageBean getCheckPage(Map parameters, PageBean page) {
		// TODO Auto-generated method stub
		try {

			if (parameters == null) {
				parameters = new HashMap();
			}

			parameters.put("firstResult",
					page.getPerpage() * (page.getCurrentPage() - 1));
			parameters.put("maxResult", page.getPerpage());

			List list = this.getSqlSession().selectList(
					getSqlStatement("getCheckPage"), parameters);
			page.setList(list);
		} catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql="
					+ getSqlStatement("getCheckPage"), e);
		}

		return page;
	}
	
	
	public int addGoodsProcess(Map<String, Object> params) {

		try {

			return this.getSqlSession().insert(
					this.getSqlStatement("addGoodsProcess"), params);
		} catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql="
					+ getSqlStatement("addGoodsProcess"), e);
		}
	}
	public int addGoodsDetail(Map<String, Object> params) {

		try {

			return this.getSqlSession().insert(
					this.getSqlStatement("addGoodsDetail"), params);
		} catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql="
					+ getSqlStatement("addGoodsDetail"), e);
		}
	}
	
	
	public List getGoodsTypes(Object params){
		try {

			return this.getSqlSession().selectList(
					getSqlStatement("getGoodsTypes"), params);
		} catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql="
					+ getSqlStatement("getGoodsTypes"), e);
		}
	}
	
	public List getGoodsUnit(Object params){
		try {

			return this.getSqlSession().selectList(
					getSqlStatement("getGoodsUnit"), params);
		} catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql="
					+ getSqlStatement("getGoodsUnit"), e);
		}
	}
	
	public List getStores(Object params){
		try {

			return this.getSqlSession().selectList(
					getSqlStatement("getStores"), params);
		} catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql="
					+ getSqlStatement("getStores"), e);
		}
	}
	
	public List getProManager(Object params){
		try {

			return this.getSqlSession().selectList(
					getSqlStatement("getProManager"), params);
		} catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql="
					+ getSqlStatement("getProManager"), e);
		}
	}
	
	public List getGoodsProcessDetail(Object params){
		try {

			return this.getSqlSession().selectList(
					getSqlStatement("getGoodsProcessDetail"), params);
		} catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql="
					+ getSqlStatement("getGoodsProcessDetail"), e);
		}
	}
	public List getWasteProcessDetail(Object params){
		try {

			return this.getSqlSession().selectList(
					getSqlStatement("getWasteProcessDetail"), params);
		} catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql="
					+ getSqlStatement("getWasteProcessDetail"), e);
		}
	}
	
	public List getGoodsFlowDetail(Object params){
		try {

			return this.getSqlSession().selectList(
					getSqlStatement("getGoodsFlowDetail"), params);
		} catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql="
					+ getSqlStatement("getGoodsFlowDetail"), e);
		}
	}
	public List getWasteFlowDetail(Object params){
		try {

			return this.getSqlSession().selectList(
					getSqlStatement("getWasteFlowDetail"), params);
		} catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql="
					+ getSqlStatement("getWasteFlowDetail"), e);
		}
	}
	public List searchGoodsProcessList(Object params){
		try {

			return this.getSqlSession().selectList(
					getSqlStatement("searchGoodsProcessList"), params);
		} catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql="
					+ getSqlStatement("searchGoodsProcessList"), e);
		}
	}
	
	public int deleteGoodsDetail(Object parameter) {
		// TODO Auto-generated method stub
		
		try {
			
			return this.getSqlSession().delete(getSqlStatement("deleteGoodsDetail"), parameter);
		}
		catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql=" + getSqlStatement("deleteGoodsDetail"), e);
		}
	}
	
	public int deleteWasteDetail(Object parameter) {
		// TODO Auto-generated method stub
		
		try {
			
			return this.getSqlSession().delete(getSqlStatement("deleteWasteDetail"), parameter);
		}
		catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql=" + getSqlStatement("deleteWasteDetail"), e);
		}
	}
	
	public int updateProcess(Object parameters) {
		// TODO Auto-generated method stub
		try {
			
			return this.getSqlSession().update(getSqlStatement("updateProcess"), parameters);
		}
		catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql=" + getSqlStatement("updateProcess"), e);
		}
	}
	public int updateWasteProcess(Object parameters) {
		// TODO Auto-generated method stub
		try {
			
			return this.getSqlSession().update(getSqlStatement("updateWasteProcess"), parameters);
		}
		catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql=" + getSqlStatement("updateWasteProcess"), e);
		}
	}
	
	public int checkProcess(Object parameters) {
		// TODO Auto-generated method stub
		try {
			
			return this.getSqlSession().update(getSqlStatement("checkProcess"), parameters);
		}
		catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql=" + getSqlStatement("checkProcess"), e);
		}
	}
	public int checkWasteProcess(Object parameters) {
		// TODO Auto-generated method stub
		try {
			
			return this.getSqlSession().update(getSqlStatement("checkWasteProcess"), parameters);
		}
		catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql=" + getSqlStatement("checkWasteProcess"), e);
		}
	}
	
	public List getGoodsManager(Object params){
		try {

			return this.getSqlSession().selectList(
					getSqlStatement("getGoodsManager"), params);
		} catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql="
					+ getSqlStatement("getGoodsManager"), e);
		}
	}
	
	public int updateProcessDetail(Object parameters) {
		// TODO Auto-generated method stub
		try {
			
			return this.getSqlSession().update(getSqlStatement("updateProcessDetail"), parameters);
		}
		catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql=" + getSqlStatement("updateProcessDetail"), e);
		}
	}
	
	public int updateWasteProcessDetail(Object parameters) {
		// TODO Auto-generated method stub
		try {
			
			return this.getSqlSession().update(getSqlStatement("updateWasteProcessDetail"), parameters);
		}
		catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql=" + getSqlStatement("updateWasteProcessDetail"), e);
		}
	}
	public List getCheckProcessDetail(Object params){
		try {

			return this.getSqlSession().selectList(
					getSqlStatement("getCheckProcessDetail"), params);
		} catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql="
					+ getSqlStatement("getCheckProcessDetail"), e);
		}
	}
	
	public List getWasteCheckProcessDetail(Object params){
		try {

			return this.getSqlSession().selectList(
					getSqlStatement("getWasteCheckProcessDetail"), params);
		} catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql="
					+ getSqlStatement("getWasteCheckProcessDetail"), e);
		}
	}
	public List isHaveStore(Object params){
		try {

			return this.getSqlSession().selectList(
					getSqlStatement("isHaveStore"), params);
		} catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql="
					+ getSqlStatement("isHaveStore"), e);
		}
	}
	
	public int updateStock(Object parameters) {
		// TODO Auto-generated method stub
		try {
			
			return this.getSqlSession().update(getSqlStatement("updateStock"), parameters);
		}
		catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql=" + getSqlStatement("updateStock"), e);
		}
	}
	public int updateWasteStock(Object parameters) {
		// TODO Auto-generated method stub
		try {
			
			return this.getSqlSession().update(getSqlStatement("updateWasteStock"), parameters);
		}
		catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql=" + getSqlStatement("updateWasteStock"), e);
		}
	}
	public int updateWasteStock1(Object parameters) {
		// TODO Auto-generated method stub
		try {
			
			return this.getSqlSession().update(getSqlStatement("updateWasteStock1"), parameters);
		}
		catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql=" + getSqlStatement("updateWasteStock1"), e);
		}
	}
	
	public int updateStock1(Object parameters) {
		// TODO Auto-generated method stub
		try {
			
			return this.getSqlSession().update(getSqlStatement("updateStock1"), parameters);
		}
		catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql=" + getSqlStatement("updateStock1"), e);
		}
	}
	public List getProCategory(Object params){
		try {

			return this.getSqlSession().selectList(
					getSqlStatement("getProCategory"), params);
		} catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql="
					+ getSqlStatement("getProCategory"), e);
		}
	}
	
	public List getPros(Object params){
		try {

			return this.getSqlSession().selectList(
					getSqlStatement("getPros"), params);
		} catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql="
					+ getSqlStatement("getPros"), e);
		}
	}
	
	
	
	public int addCategory(Map<String, Object> params) {

		try {

			return this.getSqlSession().insert(
					this.getSqlStatement("addCategory"), params);
		} catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql="
					+ getSqlStatement("addCategory"), e);
		}
	}

	
	
	
	public int addWasteProcess(Map<String, Object> params) {

		try {

			return this.getSqlSession().insert(
					this.getSqlStatement("addWasteProcess"), params);
		} catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql="
					+ getSqlStatement("addWasteProcess"), e);
		}
	}
	
	public int addWasteProcessDetail(Map<String, Object> params) {

		try {

			return this.getSqlSession().insert(
					this.getSqlStatement("addWasteProcessDetail"), params);
		} catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql="
					+ getSqlStatement("addWasteProcessDetail"), e);
		}
	}
	public int addWasteStock(Map<String, Object> params) {

		try {

			return this.getSqlSession().insert(
					this.getSqlStatement("addWasteStock"), params);
		} catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql="
					+ getSqlStatement("addWasteStock"), e);
		}
	}
	
	public List getWastTypes(Object params){
		try {

			return this.getSqlSession().selectList(
					getSqlStatement("getWastTypes"), params);
		} catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql="
					+ getSqlStatement("getWastTypes"), e);
		}
	}
	
	
	
	public PageBean getWasteProcessPage(Map parameters, int currentPage, int perpage) {

		PageBean page = null;

		try {

			int totalCount = this.getWasteProcessCount(parameters);
			if (totalCount < 1) {

				page = new PageBean();
				page.setList(new ArrayList());
			} else {

				page = new PageBean(totalCount, currentPage, perpage);
				page = this.getWasteProcessPage(parameters, page);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new MyBatisAccessException("exe sql="
					+ getSqlStatement("getWasteProcessPage"), e);
		}

		return page;
	}

	public int getWasteProcessCount(Map parameters) {
		// TODO Auto-generated method stub
		try {

			return (Integer) this.getSqlSession().selectOne(
					getSqlStatement("getWasteProcessCount"), parameters);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new MyBatisAccessException("exe sql="
					+ getSqlStatement("getWasteProcessCount"), e);
		}
	}

	private PageBean getWasteProcessPage(Map parameters, PageBean page) {
		// TODO Auto-generated method stub
		try {

			if (parameters == null) {
				parameters = new HashMap();
			}

			parameters.put("firstResult",
					page.getPerpage() * (page.getCurrentPage() - 1));
			parameters.put("maxResult", page.getPerpage());

			List list = this.getSqlSession().selectList(
					getSqlStatement("getWasteProcessPage"), parameters);
			page.setList(list);
		} catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql="
					+ getSqlStatement("getWasteProcessPage"), e);
		}

		return page;
	}
	
	
	
	public List getCaiwuManager(){
		try {

			return this.getSqlSession().selectList(
					getSqlStatement("getCaiwuManager"), null);
		} catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql="
					+ getSqlStatement("getCaiwuManager"), e);
		}
	}
	
	
	public PageBean getMasteCheckPage(Map parameters, int currentPage, int perpage) {

		PageBean page = null;

		try {

			int totalCount = this.getMasteCheckPageCount(parameters);
			if (totalCount < 1) {

				page = new PageBean();
				page.setList(new ArrayList());
			} else {

				page = new PageBean(totalCount, currentPage, perpage);
				page = this.getMasteCheckPage(parameters, page);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new MyBatisAccessException("exe sql="
					+ getSqlStatement("getMasteCheckPage"), e);
		}

		return page;
	}

	public int getMasteCheckPageCount(Map parameters) {
		// TODO Auto-generated method stub
		try {

			return (Integer) this.getSqlSession().selectOne(
					getSqlStatement("getMasteCheckPageCount"), parameters);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new MyBatisAccessException("exe sql="
					+ getSqlStatement("getMasteCheckPageCount"), e);
		}
	}

	private PageBean getMasteCheckPage(Map parameters, PageBean page) {
		// TODO Auto-generated method stub
		try {

			if (parameters == null) {
				parameters = new HashMap();
			}

			parameters.put("firstResult",
					page.getPerpage() * (page.getCurrentPage() - 1));
			parameters.put("maxResult", page.getPerpage());

			List list = this.getSqlSession().selectList(
					getSqlStatement("getMasteCheckPage"), parameters);
			page.setList(list);
		} catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql="
					+ getSqlStatement("getMasteCheckPage"), e);
		}

		return page;
	}
	
	/**
	 * 物资库存
	 * @param parameters
	 * @param currentPage
	 * @param perpage
	 * @return
	 */
	
	public PageBean getGoodsStocks(Map parameters, int currentPage, int perpage) {

		PageBean page = null;

		try {

			int totalCount = this.getGoodsStocksCount(parameters);
			if (totalCount < 1) {

				page = new PageBean();
				page.setList(new ArrayList());
			} else {

				page = new PageBean(totalCount, currentPage, perpage);
				page = this.getGoodsStocks(parameters, page);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new MyBatisAccessException("exe sql="
					+ getSqlStatement("getGoodsStocks"), e);
		}

		return page;
	}

	public int getGoodsStocksCount(Map parameters) {
		// TODO Auto-generated method stub
		try {

			return (Integer) this.getSqlSession().selectOne(
					getSqlStatement("getGoodsStocksCount"), parameters);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new MyBatisAccessException("exe sql="
					+ getSqlStatement("getGoodsStocksCount"), e);
		}
	}
	
	
	
	public List exportGoodsStocks(Map params){
		try {

			return this.getSqlSession().selectList(
					getSqlStatement("exportGoodsStocks"), params);
		} catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql="
					+ getSqlStatement("exportGoodsStocks"), e);
		}
	}

	private PageBean getGoodsStocks(Map parameters, PageBean page) {
		// TODO Auto-generated method stub
		try {

			if (parameters == null) {
				parameters = new HashMap();
			}

			parameters.put("firstResult",
					page.getPerpage() * (page.getCurrentPage() - 1));
			parameters.put("maxResult", page.getPerpage());

			List list = this.getSqlSession().selectList(
					getSqlStatement("getGoodsStocks"), parameters);
			page.setList(list);
		} catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql="
					+ getSqlStatement("getGoodsStocks"), e);
		}

		return page;
	}
	
	
	/**
	 * 废料库存
	 * @param parameters
	 * @param currentPage
	 * @param perpage
	 * @return
	 */
	public PageBean getWasteStocks(Map parameters, int currentPage, int perpage) {

		PageBean page = null;

		try {

			int totalCount = this.getWasteStocksCount(parameters);
			if (totalCount < 1) {

				page = new PageBean();
				page.setList(new ArrayList());
			} else {

				page = new PageBean(totalCount, currentPage, perpage);
				page = this.getWasteStocks(parameters, page);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new MyBatisAccessException("exe sql="
					+ getSqlStatement("getWasteStocks"), e);
		}

		return page;
	}

	public int getWasteStocksCount(Map parameters) {
		// TODO Auto-generated method stub
		try {

			return (Integer) this.getSqlSession().selectOne(
					getSqlStatement("getWasteStocksCount"), parameters);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new MyBatisAccessException("exe sql="
					+ getSqlStatement("getWasteStocksCount"), e);
		}
	}

	private PageBean getWasteStocks(Map parameters, PageBean page) {
		// TODO Auto-generated method stub
		try {

			if (parameters == null) {
				parameters = new HashMap();
			}

			parameters.put("firstResult",
					page.getPerpage() * (page.getCurrentPage() - 1));
			parameters.put("maxResult", page.getPerpage());

			List list = this.getSqlSession().selectList(
					getSqlStatement("getWasteStocks"), parameters);
			page.setList(list);
		} catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql="
					+ getSqlStatement("getWasteStocks"), e);
		}

		return page;
	}
	
	/**
	 * 物资出入库流程 查询
	 */
	
	public PageBean searchGoodsProcess(Map parameters, int currentPage, int perpage) {

		PageBean page = null;

		try {

			int totalCount = this.searchGoodsProcessCount(parameters);
			if (totalCount < 1) {

				page = new PageBean();
				page.setList(new ArrayList());
			} else {

				page = new PageBean(totalCount, currentPage, perpage);
				page = this.searchGoodsProcess(parameters, page);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new MyBatisAccessException("exe sql="
					+ getSqlStatement("searchGoodsProcess"), e);
		}

		return page;
	}

	public int searchGoodsProcessCount(Map parameters) {
		// TODO Auto-generated method stub
		try {

			return (Integer) this.getSqlSession().selectOne(
					getSqlStatement("searchGoodsProcessCount"), parameters);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new MyBatisAccessException("exe sql="
					+ getSqlStatement("searchGoodsProcessCount"), e);
		}
	}

	private PageBean searchGoodsProcess(Map parameters, PageBean page) {
		// TODO Auto-generated method stub
		try {

			if (parameters == null) {
				parameters = new HashMap();
			}

			parameters.put("firstResult",
					page.getPerpage() * (page.getCurrentPage() - 1));
			parameters.put("maxResult", page.getPerpage());

			List list = this.getSqlSession().selectList(
					getSqlStatement("searchGoodsProcess"), parameters);
			page.setList(list);
		} catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql="
					+ getSqlStatement("searchGoodsProcess"), e);
		}

		return page;
	}
	
	/**
	 * 废料出入库流程
	 */
	
	public PageBean searchWasteProcess(Map parameters, int currentPage, int perpage) {

		PageBean page = null;

		try {

			int totalCount = this.searchWasteProcessCount(parameters);
			if (totalCount < 1) {

				page = new PageBean();
				page.setList(new ArrayList());
			} else {

				page = new PageBean(totalCount, currentPage, perpage);
				page = this.searchWasteProcess(parameters, page);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new MyBatisAccessException("exe sql="
					+ getSqlStatement("searchWasteProcess"), e);
		}

		return page;
	}

	public int searchWasteProcessCount(Map parameters) {
		// TODO Auto-generated method stub
		try {

			return (Integer) this.getSqlSession().selectOne(
					getSqlStatement("searchWasteProcessCount"), parameters);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new MyBatisAccessException("exe sql="
					+ getSqlStatement("searchWasteProcessCount"), e);
		}
	}

	private PageBean searchWasteProcess(Map parameters, PageBean page) {
		// TODO Auto-generated method stub
		try {

			if (parameters == null) {
				parameters = new HashMap();
			}

			parameters.put("firstResult",
					page.getPerpage() * (page.getCurrentPage() - 1));
			parameters.put("maxResult", page.getPerpage());

			List list = this.getSqlSession().selectList(
					getSqlStatement("searchWasteProcess"), parameters);
			page.setList(list);
		} catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql="
					+ getSqlStatement("searchWasteProcess"), e);
		}

		return page;
	}
	
	public List exportWasteProcess(Map params){
		try {

			return this.getSqlSession().selectList(
					getSqlStatement("exportWasteProcess"), params);
		} catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql="
					+ getSqlStatement("exportWasteProcess"), e);
		}
	}
	public List exportWasteStocks(Map params){
		try {

			return this.getSqlSession().selectList(
					getSqlStatement("exportWasteStocks"), params);
		} catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql="
					+ getSqlStatement("exportWasteStocks"), e);
		}
	}
	
}
