package com.glens.pwCloudOs.fm.invoicebase.dao;

import java.util.List;
import java.util.Map;

import com.glens.eap.platform.core.annotation.MybatisNamespaceProcessor;
import com.glens.eap.platform.core.orm.mybatis.exception.MyBatisAccessException;
import com.glens.eap.platform.framework.dao.impl.EAPAbstractDao;

@MybatisNamespaceProcessor(value = "com.glens.pwCloudOs.fm.invoicebase.dao.InvoiceBaseVoMapper")
public class InvoiceBaseDao extends EAPAbstractDao {

	// 得到发票类型
	public List<Map<String, String>> getInvoiceType() {
		// TODO Auto-generated method stub
		try {

			return this.getSqlSession().selectList(
					getSqlStatement("getInvoiceType"), null);
		} catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql="
					+ getSqlStatement("getInvoiceType"));
		}
	}

	// 得到发票类型
	public List<Map<String, String>> getinvoiceList(Object obj) {
		// TODO Auto-generated method stub
		try {

			return this.getSqlSession().selectList(
					getSqlStatement("getinvoiceList"), obj);
		} catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql="
					+ getSqlStatement("getinvoiceList"));
		}
	}

	
	public List<Map<String, String>> getInvoiceNosByProno(Object obj) {
		// TODO Auto-generated method stub
		try {

			return this.getSqlSession().selectList(
					getSqlStatement("getInvoiceNosByProno"), obj);
		} catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql="
					+ getSqlStatement("getInvoiceNosByProno"));
		}
	}

	
	
	public String getInvoiceClassName(String appType) {
		try {

			return this.getSqlSession().selectOne(
					getSqlStatement("getInvoiceClassName"), appType);
		} catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql="
					+ getSqlStatement("getInvoiceClassName"));
		}
	}

	public Map getInvoiceTaxRate(String appType) {
		try {

			return this.getSqlSession().selectOne(
					getSqlStatement("getInvoiceTaxRate"), appType);
		} catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql="
					+ getSqlStatement("getInvoiceTaxRate"));
		}
	}

	public List<Map<String, String>> getinvoiceRepayList() {
		// TODO Auto-generated method stub
		try {

			return this.getSqlSession().selectList(
					getSqlStatement("getinvoiceRepayList"));
		} catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql="
					+ getSqlStatement("getinvoiceRepayList"));
		}
	}

}
