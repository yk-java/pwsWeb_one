package com.glens.pwCloudOs.om.deviceMgr.device.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.glens.eap.platform.framework.service.impl.EAPAbstractService;
import com.glens.pwCloudOs.om.deviceMgr.device.dao.QualityCheckRecordDao;
import com.glens.pwCloudOs.om.deviceMgr.device.dao.QualityCheckRecordMongoDao;
import com.glens.pwCloudOs.om.deviceMgr.device.vo.QualityCheckRecordVo;

public class QualityCheckRecordService extends EAPAbstractService {
	private QualityCheckRecordMongoDao qualityCheckRecordMongoDao;
	
	public void setQualityCheckRecordMongoDao(
			QualityCheckRecordMongoDao qualityCheckRecordMongoDao) {
		this.qualityCheckRecordMongoDao = qualityCheckRecordMongoDao;
	}
	
	@Override
	@Transactional
	public boolean insert(Object parameters) {
		QualityCheckRecordVo vo = (QualityCheckRecordVo)parameters;
		int res = qualityCheckRecordMongoDao.insert(vo);
		return this.dao.insert(parameters);
	}
	/**
	 * 根据设备编号查询质量检查记录
	 * @param deviceCode
	 * @return
	 */
	public List<QualityCheckRecordVo> findByDeviceCode(String deviceObjCode, Integer checkType){
		QualityCheckRecordDao qualityCheckDao = (QualityCheckRecordDao)this.dao;
		List<QualityCheckRecordVo> res = qualityCheckDao.findByDeviceCode(deviceObjCode, checkType);
		return res;
	}
}
