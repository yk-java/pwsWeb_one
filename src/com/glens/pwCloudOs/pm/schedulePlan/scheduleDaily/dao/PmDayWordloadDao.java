/**
 * @Title: PmDayWordloadDao.java
 * @Package com.glens.pwCloudOs.pm.schedulePlan.scheduleDaily.dao
 * @Description: TODO
 * Copyright: Copyright (c) 2016 
 * Company:南京量为石信息科技有限公司
 * @author 
 * @date 2016-6-8 下午5:29:30
 * @version V1.0
 */


package com.glens.pwCloudOs.pm.schedulePlan.scheduleDaily.dao;

import java.util.List;
import java.util.Map;

import com.glens.eap.platform.core.annotation.MybatisNamespaceProcessor;
import com.glens.eap.platform.core.orm.mybatis.exception.MyBatisAccessException;
import com.glens.eap.platform.framework.dao.impl.EAPAbstractDao;
import com.glens.pwCloudOs.pm.schedulePlan.scheduleDaily.vo.PmDayWordloadVo;

/**
 * 
 * 
 * @author 
 * @version V1.0
 */

@MybatisNamespaceProcessor(value="com.glens.pwCloudOs.pm.schedulePlan.scheduleDaily.dao.PmDayWordloadVoMapper")
public class PmDayWordloadDao extends EAPAbstractDao {

	public PmDayWordloadVo selectProByNo(PmDayWordloadVo params) {
		
		try {
			
			return this.getSqlSession().selectOne(getSqlStatement("selectProByNo"), params);
		}
		catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql=" + getSqlStatement("selectProByNo"), e);
		}
	}
	
	public Map<String, Object> selectWordloadByDate(Map params){
		try {
			return this.getSqlSession().selectOne(getSqlStatement("selectWordloadByDate"), params);
		}
		catch (Exception e) {
			throw new MyBatisAccessException("exe sql=" + getSqlStatement("selectWordloadByDate"), e);
		}
	}
	
	public PmDayWordloadVo selectDayWordloadByNo(PmDayWordloadVo params) {
		
		try {
			
			return this.getSqlSession().selectOne(getSqlStatement("selectDayWordloadByNo"), params);
		}
		catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql=" + getSqlStatement("selectDayWordloadByNo"), e);
		}
	}
	
	public int selectFormulaType(String proNo) {
		
		try {
			
			return this.getSqlSession().selectOne(getSqlStatement("selectFormulaType"), proNo);
		}
		catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql=" + getSqlStatement("selectFormulaType"), e);
		}
	}
	
	public PmDayWordloadVo selectPmDayWordload(Map<String, String> params) {
		
		try {
			
			return this.getSqlSession().selectOne(getSqlStatement("selectPmDayWordload"), params);
		}
		catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql=" + getSqlStatement("selectPmDayWordload"), e);
		}
	}
	public List<PmDayWordloadVo> selectPmDayWordload2(Map<String, String> params) {
		
		try {
			
			return this.getSqlSession().selectList(getSqlStatement("selectPmDayWordload2"), params);
		}
		catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql=" + getSqlStatement("selectPmDayWordload2"), e);
		}
	}
	
	public List<PmDayWordloadVo> selectDanWordloadByDate(Map<String, String> params) {
		
		try {
			
			return this.getSqlSession().selectList(getSqlStatement("selectDanWordloadByDate"), params);
		}
		catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql=" + getSqlStatement("selectDanWordloadByDate"), e);
		}
	}
	
	public float getAllAccumulativeWordload(Map<String, Object> params) {
		try {
			Object val = this.getSqlSession().selectOne(getSqlStatement("getAllAccumulativeWordload"), params);
			if(val==null){
				return 0l;
			}else{
				return (Float)val;
			}
		}
		catch (Exception e) {
			throw new MyBatisAccessException("exe sql=" + getSqlStatement("getAllAccumulativeWordload"), e);
		}
	}
	
	public PmDayWordloadVo findByRowid(Long rowid) {
		try {
			return this.getSqlSession().selectOne(getSqlStatement("findByRowid"), rowid);
		}catch (Exception e) {
			throw new MyBatisAccessException("exe sql=" + getSqlStatement("findByRowid"), e);
		}
	}

	public Object getProWorkLoad(Map<String, Object> pm) {
		// TODO Auto-generated method stub
		try {
			return this.getSqlSession().selectOne(getSqlStatement("getProWorkLoad"), pm);
		}catch (Exception e) {
			throw new MyBatisAccessException("exe sql=" + getSqlStatement("getProWorkLoad"), e);
		}
	}

	public String getLastWordloadDate(String proNo) {
		try {
			return this.getSqlSession().selectOne(getSqlStatement("getLastWordloadDate"), proNo);
		}
		catch (Exception e) {
			throw new MyBatisAccessException("exe sql=" + getSqlStatement("getLastWordloadDate"), e);
		}
	}
}
