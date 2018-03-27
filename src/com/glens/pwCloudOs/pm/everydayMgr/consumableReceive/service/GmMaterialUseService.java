package com.glens.pwCloudOs.pm.everydayMgr.consumableReceive.service;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.transaction.annotation.Transactional;

import com.glens.eap.platform.framework.service.impl.EAPAbstractService;
import com.glens.pwCloudOs.materielMg.assetMg.materialBase.dao.MaterialBaseDao;
import com.glens.pwCloudOs.materielMg.assetMg.materialBase.vo.MaterialBaseVo;
import com.glens.pwCloudOs.pm.everydayMgr.consumableReceive.dao.GmMaterialUseDao;
import com.glens.pwCloudOs.pm.everydayMgr.consumableReceive.vo.GmMaterialUse;

public class GmMaterialUseService extends EAPAbstractService {
	MaterialBaseDao materialBaseDao;
	
	public MaterialBaseDao getMaterialBaseDao() {
		return materialBaseDao;
	}

	public void setMaterialBaseDao(MaterialBaseDao materialBaseDao) {
		this.materialBaseDao = materialBaseDao;
	}

	@Override
	public boolean insert(Object parameters) {
		// TODO Auto-generated method stub
		GmMaterialUse gmMaterialUse = (GmMaterialUse)parameters;
		gmMaterialUse.setFlowStatus(1);
		return this.dao.insert(gmMaterialUse);
	}
	
	@Transactional( rollbackFor={Exception.class})
	public int addFaster(GmMaterialUse gmMaterialUse) {
		// 获取
		GmMaterialUseDao daoBean = (GmMaterialUseDao)this.dao;
		gmMaterialUse.setFlowStatus(4);
		daoBean.insert(gmMaterialUse);
		
		MaterialBaseVo params1 = new MaterialBaseVo();
		params1.setMaterialBatchno(gmMaterialUse.getMaterialBatchno());
		MaterialBaseVo baseVo = (MaterialBaseVo)materialBaseDao.findById(params1);
		
		// 修改当前库存
		MaterialBaseVo params2 = new MaterialBaseVo();
		params2.setMaterialBatchno(gmMaterialUse.getMaterialBatchno());
		params2.setCurcountStorage(baseVo.getCurcountStorage()-gmMaterialUse.getUseCount());
		int row = materialBaseDao.update(params2);
		return row;
	}
	
	@Transactional( rollbackFor={Exception.class})
	public int updateMaterial(GmMaterialUse gmMaterialUse) {
		// 获取
		GmMaterialUseDao daoBean = (GmMaterialUseDao)this.dao;
		gmMaterialUse.setFlowStatus(4);
		daoBean.update(gmMaterialUse);
		
		MaterialBaseVo params1 = new MaterialBaseVo();
		params1.setMaterialBatchno(gmMaterialUse.getMaterialBatchno());
		MaterialBaseVo baseVo = (MaterialBaseVo)materialBaseDao.findById(params1);
		
		// 修改当前库存
		MaterialBaseVo params2 = new MaterialBaseVo();
		params2.setMaterialBatchno(gmMaterialUse.getMaterialBatchno());
		params2.setCurcountStorage(baseVo.getCurcountStorage()-gmMaterialUse.getUseCount());
		int row = materialBaseDao.update(params2);
		return row;
	}
}
