package com.glens.pwCloudOs.pm.everydayMgr.bedMgr.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.transaction.annotation.Transactional;

import com.glens.eap.platform.framework.service.impl.EAPAbstractService;
import com.glens.pwCloudOs.materielMg.dormMg.dorm.dao.DormDao;
import com.glens.pwCloudOs.materielMg.dormMg.dorm.vo.DormVo;
import com.glens.pwCloudOs.pm.everydayMgr.bedMgr.dao.GmBmBedrentDao;
import com.glens.pwCloudOs.pm.everydayMgr.bedMgr.vo.GmBmBedrentVo;

public class GmBmBedrentService extends EAPAbstractService {
	private DormDao dormDao;
	
	public DormDao getDormDao() {
		return dormDao;
	}
	public void setDormDao(DormDao dormDao) {
		this.dormDao = dormDao;
	}
	public int updateRentStatus(Object parameters) {
		GmBmBedrentDao daoBean = (GmBmBedrentDao)this.dao;
		return daoBean.updateRentStatus(parameters);
	}
	@Transactional( rollbackFor={Exception.class})
	public int updateDormCode(Object parameters) {
		Map<String,Object> paramsTemp = (HashMap<String, Object>)parameters;
		String dormCode = (String)paramsTemp.get("dormCode");
		String[] dormCodes = dormCode.split(",");
		String employeecode = (String)paramsTemp.get("employeecode");
		String[] employeecodes =employeecode.split(","); 
		String employeename = (String)paramsTemp.get("employeename");
		String[] employeenames =employeename.split(","); 
		// 获取
		GmBmBedrentDao daoBean = (GmBmBedrentDao)this.dao;
		GmBmBedrentVo bedrentVo = (GmBmBedrentVo)daoBean.findById(parameters);
		// 删除借用记录
		daoBean.delete(parameters);
		// 添加带床位信息的借用记录
		for (int i = 0; i < dormCodes.length; i++) {
			bedrentVo.setRentStatus(1);
			bedrentVo.setFlowStatus(4);
			bedrentVo.setDormCode(dormCodes[i]);
			bedrentVo.setEmployeecode(employeecodes[i]);
			bedrentVo.setEmployeename(employeenames[i]);
			daoBean.insert(bedrentVo);
			// 修改床位状态
			Map<String, Object> params_q = new HashMap<String, Object>();
			params_q.put("dormCode", dormCodes[i]);
			DormVo dormVo = (DormVo)dormDao.findById(params_q);
			
			Map<String, Object> params_u = new HashMap<String, Object>();
			String curProNos = dormVo.getCurProNos();
			String curProNames = dormVo.getCurProNames();
			if(curProNos!=null && !curProNos.contains(bedrentVo.getProNo())){
				if(curProNos==null || curProNos.length()==0){
					curProNos=bedrentVo.getProNo();
					curProNames=bedrentVo.getProName();
				}else{
					curProNos+=","+bedrentVo.getProNo();
					curProNames+=","+bedrentVo.getProName();
				}
			}
			params_u.put("dormCode", dormVo.getDormCode());
			params_u.put("curBeds", dormVo.getCurBeds()+1);
			params_u.put("curProNos", curProNos);
			params_u.put("curProNames", curProNames);
			if(dormVo.getDormStatus()==1){
				params_u.put("dormStatus", 2);// 闲置改为在租
			}
			dormDao.update(params_u);
		}
		
		return dormCodes.length;
	}
	
	@Transactional( rollbackFor={Exception.class})
	public int returnDorm(Object parameters) {
		GmBmBedrentDao daoBean = (GmBmBedrentDao)this.dao;
		daoBean.updateRentStatus(parameters);
		
		GmBmBedrentVo proNos = (GmBmBedrentVo)daoBean.selectProNosByDormCode(parameters);
		DormVo dormVo = (DormVo)dormDao.findById(parameters);
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("dormCode", dormVo.getDormCode());
		params.put("curBeds", dormVo.getCurBeds()-1);
		if(proNos==null){
			params.put("curProNos", "");
			params.put("curProNames", "");
		}else{
			params.put("curProNos", proNos.getProNo());
			params.put("curProNames", proNos.getProName());
		}
		if(dormVo.getDormStatus()==2 && dormVo.getCurBeds()-1==0){
			params.put("dormStatus", 1);// 在租改为闲置
		}
		return dormDao.update(params);
	}
	
	@Transactional( rollbackFor={Exception.class})
	public int addFaster(GmBmBedrentVo bedrentVo, Object parameters) {
		Map<String,Object> paramsTemp = (HashMap<String, Object>)parameters;
		String dormCode = (String)paramsTemp.get("dormCodes");
		String[] dormCodes = dormCode.split(",");
		String employeecode = (String)paramsTemp.get("employeecodes");
		String[] employeecodes =employeecode.split(","); 
		String employeename = (String)paramsTemp.get("employeenames");
		String[] employeenames =employeename.split(","); 
		// 获取
		GmBmBedrentDao daoBean = (GmBmBedrentDao)this.dao;
		// 添加带床位信息的借用记录
		for (int i = 0; i < dormCodes.length; i++) {
			bedrentVo.setRentStatus(1);
			bedrentVo.setFlowStatus(4);
			bedrentVo.setDormCode(dormCodes[i]);
			bedrentVo.setEmployeecode(employeecodes[i]);
			bedrentVo.setEmployeename(employeenames[i]);
			daoBean.insert(bedrentVo);
			// 修改床位状态
			Map<String, Object> params_q = new HashMap<String, Object>();
			params_q.put("dormCode", dormCodes[i]);
			DormVo dormVo = (DormVo)dormDao.findById(params_q);
			
			Map<String, Object> params_u = new HashMap<String, Object>();
			String curProNos = dormVo.getCurProNos();
			String curProNames = dormVo.getCurProNames();
			if(curProNos!=null && !curProNos.contains(bedrentVo.getProNo())){
				if(curProNos==null || curProNos.length()==0){
					curProNos=bedrentVo.getProNo();
					curProNames=bedrentVo.getProName();
				}else{
					curProNos+=","+bedrentVo.getProNo();
					curProNames+=","+bedrentVo.getProName();
				}
			}
			params_u.put("dormCode", dormVo.getDormCode());
			params_u.put("curBeds", dormVo.getCurBeds()+1);
			params_u.put("curProNos", curProNos);
			params_u.put("curProNames", curProNames);
			if(dormVo.getDormStatus()==1){
				params_u.put("dormStatus", 2);// 闲置改为在租
			}
			dormDao.update(params_u);
		}
		
		return dormCodes.length;
	}
	
}
