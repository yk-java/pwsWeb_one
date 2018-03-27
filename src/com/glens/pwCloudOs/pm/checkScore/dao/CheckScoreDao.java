package com.glens.pwCloudOs.pm.checkScore.dao;

import java.util.List;

import com.glens.eap.platform.core.annotation.MybatisNamespaceProcessor;
import com.glens.eap.platform.core.orm.mybatis.exception.MyBatisAccessException;
import com.glens.eap.platform.framework.dao.impl.EAPAbstractDao;


@MybatisNamespaceProcessor(value="com.glens.pwCloudOs.pm.checkScore.dao.CheckScoreVoMapper")
public class CheckScoreDao extends EAPAbstractDao {
	
	
	

	
	public boolean insertCheckScore(Object parameters) {
		// TODO Auto-generated method stub
		
		try {
			
			this.getSqlSession().insert(getSqlStatement("insertCheckScore"), parameters);
			
			return true;
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new MyBatisAccessException("exe sql=" + getSqlStatement("insertCheckScore"), e);
			
		}
	}
	
	
	public List getKpi(Object parameters) {
		// TODO Auto-generated method stub
		try {
			
			return this.getSqlSession().selectList(getSqlStatement("getKpi"), parameters);
		}
		catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql=" + getSqlStatement("getKpi"), e);
		}
	}
	
	
	
}
