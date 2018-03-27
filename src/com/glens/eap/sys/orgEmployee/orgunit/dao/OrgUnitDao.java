package com.glens.eap.sys.orgEmployee.orgunit.dao;

import java.util.List;
import java.util.Map;

import com.glens.eap.platform.core.annotation.MybatisNamespaceProcessor;
import com.glens.eap.platform.core.orm.mybatis.exception.MyBatisAccessException;
import com.glens.eap.platform.framework.dao.impl.EAPAbstractDao;
import com.glens.eap.sys.orgEmployee.orgunit.vo.OrgUnit;

@MybatisNamespaceProcessor(value = "OrgUnitMapper")
public class OrgUnitDao extends EAPAbstractDao {

	public int updateArea(Object parameters) {
		// TODO Auto-generated method stub
		try {

			return this.getSqlSession().update(getSqlStatement("updateArea"),
					parameters);
		} catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql="
					+ getSqlStatement("updateArea"), e);
		}
	}

	public List<OrgUnit> queryOrgUnitList(String companyCode) {
		try {
			return this.getSqlSession().selectList(
					getSqlStatement("[queryOrgUnitList]"), companyCode);
		} catch (Exception e) {
			throw new MyBatisAccessException("exe sql="
					+ getSqlStatement("[queryOrgUnitList]"), e);
		}
	}

	public List<OrgUnit> getPartOrgUnitList(String companyCode) {
		try {
			return this.getSqlSession().selectList(
					getSqlStatement("getPartOrgUnitList"), companyCode);
		} catch (Exception e) {
			throw new MyBatisAccessException("exe sql="
					+ getSqlStatement("getPartOrgUnitList"), e);
		}
	}

	public void saveOrgUnit(OrgUnit orgUnit) {
		// TODO Auto-generated method stub
		this.getSqlSession().insert(getSqlStatement("[insertSelective]"),
				orgUnit);
	}

	public void updateOrgUnit(OrgUnit orgUnit) {
		// TODO Auto-generated method stub
		this.getSqlSession().update(
				getSqlStatement("[updateByPrimaryKeySelective]"), orgUnit);
	}

	public OrgUnit queryOrgUnit(String unitCode) {
		try {
			return this.getSqlSession().selectOne(
					getSqlStatement("[queryOrgUnit]"), unitCode);
		} catch (Exception e) {
			throw new MyBatisAccessException("exe sql="
					+ getSqlStatement("[queryOrgUnit]"), e);
		}
	}

	public List<OrgUnit> queryOrgList(Map<String, String> paramsMap) {
		try {
			return this.getSqlSession().selectList(
					getSqlStatement("[queryOrgList]"), paramsMap);
		} catch (Exception e) {
			throw new MyBatisAccessException("exe sql="
					+ getSqlStatement("[queryOrgList]"), e);
		}
	}

	public boolean insertHistory(OrgUnit ou) {
		try {
			this.getSqlSession().insert(getSqlStatement("[insertHistory]"), ou);

			return true;
		} catch (Exception e) {
			e.printStackTrace();
			throw new MyBatisAccessException("exe sql="
					+ getSqlStatement(INSERT_STATEMENT), e);

		}

	}

	public void deleteHistory() {
		// TODO Auto-generated method stub
		this.getSqlSession().delete(getSqlStatement("[deleteHistory]"));
	}

	public Map queryUnitLeaders(String unitCode) {
		// TODO Auto-generated method stub
		Map resultMap = null;
		try {
			resultMap = this.getSqlSession().selectOne("queryUnitLeaders",
					unitCode);

		} catch (Exception e) {
			e.printStackTrace();
			throw new MyBatisAccessException("exe sql="
					+ getSqlStatement("queryUnitLeaders"), e);

		}

		return resultMap;
	}

}
