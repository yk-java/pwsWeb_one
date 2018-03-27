package com.glens.pwCloudOs.pe.boxCheck.service;

import java.util.Iterator;
import java.util.List;

import com.glens.eap.platform.framework.service.impl.EAPAbstractService;
import com.glens.pwCloudOs.pe.boxCheck.dao.PeBcBoxMeterRelCheckMongoDao;
import com.glens.pwCloudOs.pe.boxCheck.vo.PeBcBoxMeterRelCheckVo;

public class PeBcBoxMeterRelCheckService extends EAPAbstractService {
	private PeBcBoxMeterRelCheckMongoDao peBcBoxMeterRelCheckMongoDao;
	
	public PeBcBoxMeterRelCheckMongoDao getPeBcBoxMeterRelCheckMongoDao() {
		return peBcBoxMeterRelCheckMongoDao;
	}


	public void setPeBcBoxMeterRelCheckMongoDao(
			PeBcBoxMeterRelCheckMongoDao peBcBoxMeterRelCheckMongoDao) {
		this.peBcBoxMeterRelCheckMongoDao = peBcBoxMeterRelCheckMongoDao;
	}

	/**
	 * 根据表箱编号查询所有箱表关系
	 * @param parameters
	 * @return
	 */
	public List<PeBcBoxMeterRelCheckVo> selectByBoxCode(String parameters) {
		return peBcBoxMeterRelCheckMongoDao.selectByBoxCode(parameters);
	}
	
	/**
	 * 批量新增
	 * @param parameters
	 * @return
	 */
	public boolean batchInsert(List<PeBcBoxMeterRelCheckVo> parameters) {
		for (Iterator iterator = parameters.iterator(); iterator.hasNext();) {
			PeBcBoxMeterRelCheckVo peBcBoxMeterRelCheckVo = (PeBcBoxMeterRelCheckVo) iterator
					.next();
			peBcBoxMeterRelCheckMongoDao.insert(peBcBoxMeterRelCheckVo);
		}
		return true;
	}
	
	/**
	 * 批量核查
	 * @param checkUser
	 * @param parameters
	 * @return
	 */
	public boolean batchCheck(List<PeBcBoxMeterRelCheckVo> parameters){
		for (Iterator iterator = parameters.iterator(); iterator.hasNext();) {
			PeBcBoxMeterRelCheckVo vo = (PeBcBoxMeterRelCheckVo) iterator
					.next();
			if(vo.getCheckResult()==1 || vo.getCheckResult()==2){
				peBcBoxMeterRelCheckMongoDao.update(vo);
			}else if(vo.getCheckResult()==3 && vo.getIsNew()==1){
				peBcBoxMeterRelCheckMongoDao.insert(vo);
			}
		}
		return true;
	}
	
//	public List<PeBcBoxMeterRelCheckVo> selectByBoxCode(String parameters) {
//		return ((PeBcBoxMeterRelCheckDao)this.getDao()).selectByBoxCode(parameters);
//	}
//	
//	
//	public boolean batchInsert(List<PeBcBoxMeterRelCheckVo> parameters) {
//		return ((PeBcBoxMeterRelCheckDao)this.getDao()).batchInsert(parameters);
//	}
//	
//	public boolean batchCheck(String checkUser, List<PeBcBoxMeterRelCheckVo> parameters){
//		for (Iterator iterator = parameters.iterator(); iterator.hasNext();) {
//			PeBcBoxMeterRelCheckVo vo = (PeBcBoxMeterRelCheckVo) iterator
//					.next();
//			if(vo.getCheckResult()==1 || vo.getCheckResult()==2){
//				this.getDao().update(vo);
//			}else if(vo.getCheckResult()==3){
//				this.getDao().insert(vo);
//			}
//		}
//		return true;
//	}
}
