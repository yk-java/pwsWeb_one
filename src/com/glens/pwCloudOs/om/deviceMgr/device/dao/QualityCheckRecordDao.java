package com.glens.pwCloudOs.om.deviceMgr.device.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.glens.eap.platform.core.annotation.MybatisNamespaceProcessor;
import com.glens.eap.platform.core.orm.mybatis.exception.MyBatisAccessException;
import com.glens.eap.platform.framework.dao.impl.EAPAbstractDao;
import com.glens.pwCloudOs.om.deviceMgr.device.vo.QualityCheckRecordVo;

@MybatisNamespaceProcessor(value="com.glens.pwCloudOs.om.deviceMgr.device.dao.qualityCheckRecordMapper")
public class QualityCheckRecordDao extends EAPAbstractDao {
	
	public List<QualityCheckRecordVo> findByDeviceCode(String deviceObjCode, Integer checkType) {
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("deviceObjCode", deviceObjCode);
			params.put("checkType", checkType);
			return (List) this.getSqlSession().selectList(getSqlStatement("findByDeviceCode"), params);
		}catch (Exception e) {
			throw new MyBatisAccessException("exe sql=" + getSqlStatement("findByDeviceCode"), e);
		}
	}
	
	
}
