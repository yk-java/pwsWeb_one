/**
 * @Title: ProjDocDao.java
 * @Package com.glens.pwCloudOs.pm.projDoc.dao
 * @Description: TODO
 * Copyright: Copyright (c) 2016 
 * Company:南京量为石信息科技有限公司
 * @author 
 * @date 2016-7-19 上午11:11:17
 * @version V1.0
 */


package com.glens.pwCloudOs.pm.projDoc.dao;

import java.util.List;
import java.util.Map;

import com.glens.eap.platform.core.annotation.MybatisNamespaceProcessor;
import com.glens.eap.platform.core.orm.mybatis.exception.MyBatisAccessException;
import com.glens.eap.platform.framework.dao.impl.EAPAbstractDao;
import com.glens.pwCloudOs.pm.projDoc.vo.ProjDocVo;

/**
 * 
 * 
 * @author 
 * @version V1.0
 */

@MybatisNamespaceProcessor(value="com.glens.pwCloudOs.pm.projDoc.dao.ProjDocVoMapper")
public class ProjDocDao extends EAPAbstractDao {


	public List<Map<String, Object>> listall(Map<String, String> params) {

		try {


			List<Map<String, Object>> resultList = this.getSqlSession().selectList(getSqlStatement("listall"), params);


			return resultList;
		}
		catch (Exception e) {
			// TODO: handle exception


			throw new MyBatisAccessException("exe sql=" + getSqlStatement("listall"), e);
		}
	}
	public List<Map<String, Object>> getAllFileByProNo(Map<String, String> params) {

		try {


			List<Map<String, Object>> resultList = this.getSqlSession().selectList(getSqlStatement("getAllFileByProNo"), params);


			return resultList;
		}
		catch (Exception e) {
			// TODO: handle exception


			throw new MyBatisAccessException("exe sql=" + getSqlStatement("getAllFileByProNo"), e);
		}
	}
	
	
	
	public boolean insertProj(List<ProjDocVo> members) {
		try {
			int result= this.getSqlSession().insert(getSqlStatement("insertProj"), members);
			if(result>0){
				return true;
			}else{
				return false;
			}
		}
		catch (Exception e) {
			throw new MyBatisAccessException("exe sql=" + getSqlStatement("insertProj"), e);
		}
	}
	
	public List<Map<String, String>> selectDocType(Map<String, String> params) {
		
		try {
			
			return this.getSqlSession().selectList(getSqlStatement("selectDocType"), params);
		}
		catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql=" + getSqlStatement("selectDocType"), e);
		}
	}
	
	public List<Map<String, Object>> queryProDoc(Map<String, String> params) {
		
		try {
			
			return this.getSqlSession().selectList(getSqlStatement("queryProDoc"), params);
		}
		catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql=" + getSqlStatement("queryProDoc"), e);
		}
	}
}
