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

import java.util.List;

import com.glens.eap.platform.core.annotation.MybatisNamespaceProcessor;
import com.glens.eap.platform.core.orm.mybatis.exception.MyBatisAccessException;
import com.glens.eap.platform.framework.dao.impl.EAPAbstractDao;
import com.glens.pwCloudOs.pm.baseMgr.pmBase.vo.PmProKpi;

@MybatisNamespaceProcessor(value = "com.glens.pwCloudOs.pm.baseMgr.pmBase.dao.PmProKpiMapper")
public class PmProKpiDao extends EAPAbstractDao {
	public PmProKpi queryByProNoAndKpiName(Object parameters) {
		try {
			List res = this.getSqlSession().selectList(getSqlStatement("queryByProNoAndKpiName"), parameters);
			if(res!=null && res.size()>0){
				return (PmProKpi)res.get(0);
			}
		}
		catch (Exception e) {
			throw new MyBatisAccessException("exe sql=" + getSqlStatement("queryByProNoAndKpiName"), e);
		}
		return null;
	}
	public int queryPmProKpiInWorkList(Long rowid) {
		// TODO Auto-generated method stub
		try {
			return (Integer) this.getSqlSession().selectOne(
					getSqlStatement("queryPmProKpiInWorkList"), rowid);
		} catch (Exception e) {
			throw new MyBatisAccessException("exe sql="
					+ getSqlStatement("queryPmProKpiInWorkList"), e);
		}
	}
	public PmProKpi selectByKpiCode(String kpiCode){
		try {
			List res = this.getSqlSession().selectList(getSqlStatement("selectByKpiCode"), kpiCode);
			if(res!=null && res.size()>0){
				return (PmProKpi)res.get(0);
			}
		}
		catch (Exception e) {
			throw new MyBatisAccessException("exe sql=" + getSqlStatement("selectByKpiCode"), e);
		}
		return null;
	}
}
