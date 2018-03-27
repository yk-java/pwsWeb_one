/**
 * @Title: RechargeDao.java
 * @Package com.glens.pwCloudOs.materielMg.vehicleMg.recharge.dao
 * @Description: TODO
 * Copyright: Copyright (c) 2016 
 * Company:南京量为石信息科技有限公司
 * @author 
 * @date 2016-6-24 上午10:56:58
 * @version V1.0
 */


package com.glens.pwCloudOs.materielMg.vehicleMg.recharge.dao;

import com.glens.eap.platform.core.annotation.MybatisNamespaceProcessor;
import com.glens.eap.platform.core.orm.mybatis.exception.MyBatisAccessException;
import com.glens.eap.platform.framework.dao.impl.EAPAbstractDao;
import com.glens.pwCloudOs.materielMg.vehicleMg.recharge.vo.RechargeVo;

/**
 * 
 * 
 * @author 
 * @version V1.0
 */

@MybatisNamespaceProcessor(value="com.glens.pwCloudOs.materielMg.vehicleMg.recharge.dao.RechargeVoMapper")
public class RechargeDao extends EAPAbstractDao {

	/**
	
	  * <p>Title: insert</p>
	
	  * <p>Description: </p>
	
	  * @param parameters
	  * @return
	
	  * @see com.glens.eap.platform.framework.dao.impl.EAPAbstractDao#insert(java.lang.Object)
	
	  **/
	
	
	@Override
	public boolean insert(Object parameters) {
		// TODO Auto-generated method stub
		int iCount = 0;
		try {
			
			iCount = this.getSqlSession().insert(getSqlStatement(INSERT_STATEMENT), parameters);
			if (iCount > 0) {
				
				iCount = this.getSqlSession().update(getSqlStatement("updateFuelCardAmount"), parameters);
				
			}
		}
		catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql=" + getSqlStatement(INSERT_STATEMENT), e);
		}
		
		return iCount > 0;
	}
	
}
