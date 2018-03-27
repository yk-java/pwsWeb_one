package com.glens.pwCloudOs.pm.everydayMgr.assetsMgr.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.transaction.annotation.Transactional;

import com.glens.eap.platform.framework.service.impl.EAPAbstractService;
import com.glens.eap.sys.orgEmployee.employee.dao.MdEmployeeDao;
import com.glens.eap.sys.orgEmployee.employee.vo.MdEmployee;
import com.glens.pwCloudOs.materielMg.assetMg.asset.dao.AssetDao;
import com.glens.pwCloudOs.pm.everydayMgr.assetsMgr.dao.GmAssetRentDao;
import com.glens.pwCloudOs.pm.everydayMgr.assetsMgr.vo.GmAssetRentVo;

public class GmAssetRentService extends EAPAbstractService  {
	private AssetDao assetDao;
	
	public AssetDao getAssetDao() {
		return assetDao;
	}
	public void setAssetDao(AssetDao assetDao) {
		this.assetDao = assetDao;
	}
	
	MdEmployeeDao mdEmployeeDao;
	
	public MdEmployeeDao getMdEmployeeDao() {
		return mdEmployeeDao;
	}

	public void setMdEmployeeDao(MdEmployeeDao mdEmployeeDao) {
		this.mdEmployeeDao = mdEmployeeDao;
	}
	
	@Override
	public boolean insert(Object parameters) {
		GmAssetRentVo assetRent = (GmAssetRentVo)parameters;
		assetRent.setRentStatus(0);
		assetRent.setFlowStatus(1);
		assetRent.setSysCreateTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
		MdEmployee employee = mdEmployeeDao.queryEmployeeByCode(assetRent.getLoanEmployeecode());
		assetRent.setLoanUnitCode(employee.getUnitCode());
		assetRent.setLoanUnitName(employee.getUnitName());
		return this.dao.insert(parameters);
	}
	
	public int updateRentStatus(Object parameters) {
		GmAssetRentDao daoBean = (GmAssetRentDao)this.dao;
		return daoBean.updateRentStatus(parameters);
	}
	public int updateAssetCode(Object parameters) {
		GmAssetRentDao daoBean = (GmAssetRentDao)this.dao;
		return daoBean.updateAssetCode(parameters);
	}
	
	@Transactional( rollbackFor={Exception.class})
	public int settingAsset(Object parameters) {
		Map<String,Object> params = (HashMap<String, Object>)parameters;
		String assetCodes = (String)params.get("assetCode");
		String[] assetCodes_arr = assetCodes.split(",");
		GmAssetRentDao daoBean = (GmAssetRentDao)this.dao;
		// 获取申请
		GmAssetRentVo assetRent = (GmAssetRentVo)daoBean.findById(params);
		// 删除申请
		daoBean.delete(params);
		// 添加带有资产编号的申请
		for (int i = 0; i < assetCodes_arr.length; i++) {
			assetRent.setFlowStatus(4);
			assetRent.setAssetCode(assetCodes_arr[i]);
			daoBean.insert(assetRent);
			// 修改资产状态
			Map<String,Object> u_params = new HashMap<String, Object>();
			u_params.put("assetCode", assetCodes_arr[i]);
			u_params.put("loanEmployeecode", assetRent.getLoanEmployeecode());
			u_params.put("loanEmployeename", assetRent.getLoanEmployeename());
			u_params.put("loanUnitCode", assetRent.getLoanUnitCode());
			u_params.put("loanUnitName", assetRent.getLoanUnitName());
			u_params.put("loanProNo", assetRent.getLoanProNo());
			u_params.put("loanProName", assetRent.getLoanProName());
			if(assetRent.getLoanUnitCode()==null || assetRent.getLoanUnitCode().length()==0){
				u_params.put("status", 2);
			}else{
				u_params.put("status", 3);
			}
			assetDao.update(u_params);
		}
		
		//int a=Integer.parseInt("aa");
		return assetCodes_arr.length;
	}
	
	@Transactional( rollbackFor={Exception.class})
	public int returnAsset(Object parameters) {
		Map<String,Object> params = (HashMap<String, Object>)parameters;
		if("2".equals((String)params.get("rentStatus"))){// 确定退租则执行，否则消息刚不修改资产信息
			String assetCode = (String)params.get("assetCode");
			String location = (String)params.get("location");
			// 修改资产状态
			Map<String,Object> u_params = new HashMap<String, Object>();
			u_params.put("assetCode", assetCode);
			u_params.put("loanEmployeecode", "");
			u_params.put("loanEmployeename", "");
			u_params.put("loanUnitCode", "");
			u_params.put("loanUnitName", "");
			u_params.put("loanProNo", "");
			u_params.put("loanProName", "");
			u_params.put("status", 1);
			u_params.put("location", location);
			
			assetDao.update(u_params);
		}
		GmAssetRentDao daoBean = (GmAssetRentDao)this.dao;
		return daoBean.updateRentStatus(parameters);
	}
	
	@Transactional( rollbackFor={Exception.class})
	public int addFaster(GmAssetRentVo assetRent, Object parameters) {
		assetRent.setRentStatus(1);
		assetRent.setFlowStatus(4);
		assetRent.setSysCreateTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
		if(assetRent.getLoanUnitCode()==null || assetRent.getLoanUnitCode().length()==0){
			MdEmployee employee = mdEmployeeDao.queryEmployeeByCode(assetRent.getLoanEmployeecode());
			assetRent.setLoanUnitCode(employee.getUnitCode());
			assetRent.setLoanUnitName(employee.getUnitName());
		}
		Map<String,Object> params = (HashMap<String, Object>)parameters;
		String assetCodes = (String)params.get("assetCodes");
		String[] assetCodes_arr = assetCodes.split(",");
		GmAssetRentDao daoBean = (GmAssetRentDao)this.dao;
		// 添加带有资产编号的申请
		for (int i = 0; i < assetCodes_arr.length; i++) {
			assetRent.setAssetCode(assetCodes_arr[i]);
			daoBean.insert(assetRent);
			// 修改资产状态
			Map<String,Object> u_params = new HashMap<String, Object>();
			u_params.put("assetCode", assetCodes_arr[i]);
			u_params.put("loanEmployeecode", assetRent.getLoanEmployeecode());
			u_params.put("loanEmployeename", assetRent.getLoanEmployeename());
			u_params.put("loanUnitCode", assetRent.getLoanUnitCode());
			u_params.put("loanUnitName", assetRent.getLoanUnitName());
			u_params.put("loanProNo", assetRent.getLoanProNo());
			u_params.put("loanProName", assetRent.getLoanProName());
			if(assetRent.getLoanProNo()==null || assetRent.getLoanProNo().length()==0){
				u_params.put("status", 2);
			}else{
 				u_params.put("status", 3);
			}
			assetDao.update(u_params);
		}
		return assetCodes_arr.length;
	}
	
	public int rentChange(Map<String, Object> params) {
		GmAssetRentDao daoBean = (GmAssetRentDao)this.dao;
		// 获取申请
		GmAssetRentVo assetRent = (GmAssetRentVo)daoBean.findById(params);
		assetRent.setLoanEmployeecode((String)params.get("changeLoanEmployeecode"));
		assetRent.setLoanEmployeename((String)params.get("changeLoanEmployeename"));
		assetRent.setRentDate(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
		int rows = daoBean.update(assetRent);
		return rows;
	}
}
