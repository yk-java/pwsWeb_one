package com.glens.pwCloudOs.pm.ticket.dao;

import java.util.List;
import java.util.Map;

import com.glens.eap.platform.core.annotation.MybatisNamespaceProcessor;
import com.glens.eap.platform.core.orm.mybatis.exception.MyBatisAccessException;
import com.glens.eap.platform.framework.dao.impl.EAPAbstractDao;


@MybatisNamespaceProcessor(value="com.glens.pwCloudOs.pm.ticket.dao.TicketVoMapper")
public class TicketDao extends EAPAbstractDao {
	
	public List<Map<String, Object>> getNum(Map<String, Object> params) {
		
		try {
			
			return this.getSqlSession().selectList(getSqlStatement("getNum"), params);
		}
		catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql=" + getSqlStatement("getNum"), e);
		}
	}
	
	
	
	public boolean updateNum(Object parameter) {
		// TODO Auto-generated method stub
		
		try {
			
			 this.getSqlSession().update(getSqlStatement("updateNum"), parameter);
			 return true;
		}
		catch (Exception e) {
			// TODO: handle exception
			
			throw new MyBatisAccessException("exe sql=" + getSqlStatement("updateNum"), e);
			
		}
		
	}

}
