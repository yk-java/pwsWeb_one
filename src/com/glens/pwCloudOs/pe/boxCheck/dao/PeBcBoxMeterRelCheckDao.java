package com.glens.pwCloudOs.pe.boxCheck.dao;

import java.util.List;

import com.glens.eap.platform.core.annotation.MybatisNamespaceProcessor;
import com.glens.eap.platform.core.orm.mybatis.exception.MyBatisAccessException;
import com.glens.eap.platform.framework.dao.impl.EAPAbstractDao;
import com.glens.pwCloudOs.pe.boxCheck.vo.PeBcBoxMeterRelCheckVo;

@MybatisNamespaceProcessor(value = "com.glens.pwCloudOs.pe.boxCheck.dao.PeBcBoxMeterRelCheckMapper")
public class PeBcBoxMeterRelCheckDao extends EAPAbstractDao {
	
	public List<PeBcBoxMeterRelCheckVo> selectByBoxCode(String parameters) {
		try {
			return this.getSqlSession().selectList(getSqlStatement("selectByBoxCode"), parameters);
		}
		catch (Exception e) {
			throw new MyBatisAccessException("exe sql=" + getSqlStatement("selectByBoxCode"), e);
		}
	}
	
	
	public boolean batchInsert(List<PeBcBoxMeterRelCheckVo> parameters) {
		try {
			this.getSqlSession().insert(getSqlStatement("batchInsert"), parameters);
			return true;
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new MyBatisAccessException("exe sql=" + getSqlStatement("batchInsert"), e);
		}
	}
}
