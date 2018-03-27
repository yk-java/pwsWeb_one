package com.glens.pwCloudOs.information.reportStatistic.cpintelligenceinfo.dao;

import java.util.List;
import java.util.Map;

import com.glens.eap.platform.core.annotation.MybatisNamespaceProcessor;
import com.glens.eap.platform.core.orm.mybatis.exception.MyBatisAccessException;
import com.glens.eap.platform.framework.dao.impl.EAPAbstractDao;

@MybatisNamespaceProcessor(value = "CpIntelligenceInfoMapper")
public class CpIntelligenceInfoDao extends EAPAbstractDao {

	public List<Map<String, String>> queryIntelligenceType(
			String intelligenceTypeCode) {
		try {
			return this.getSqlSession().selectList(
					getSqlStatement("[queryIntelligenceType]"),
					intelligenceTypeCode);
		} catch (Exception e) {
			throw new MyBatisAccessException("exe sql="
					+ getSqlStatement("[queryIntelligenceType]"), e);
		}
	}

	public List<Map<String, String>> queryIntelligenceUrgencyType(
			String intelligenceUrgencyTypeCode) {
		try {
			return this.getSqlSession().selectList(
					getSqlStatement("[queryIntelligenceUrgencyType]"),
					intelligenceUrgencyTypeCode);
		} catch (Exception e) {
			throw new MyBatisAccessException("exe sql="
					+ getSqlStatement("[queryIntelligenceUrgencyType]"), e);
		}
	}

}
