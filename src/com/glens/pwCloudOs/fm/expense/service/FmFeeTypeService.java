package com.glens.pwCloudOs.fm.expense.service;

import java.util.List;
import java.util.Map;

import com.glens.eap.platform.framework.service.impl.EAPAbstractService;
import com.glens.pwCloudOs.fm.expense.dao.FmFeeTypeDao;
import com.glens.pwCloudOs.fm.expense.vo.FmFeeType;

public class FmFeeTypeService extends EAPAbstractService {

	public List getTreeList(Map map) {
		FmFeeTypeDao fmFeeTypeDao = (FmFeeTypeDao) getDao();
		return fmFeeTypeDao.getTreeList(map);
	}

	public int insertSelective(FmFeeType ftype) {
		// TODO Auto-generated method stub
		FmFeeTypeDao fmFeeTypeDao = (FmFeeTypeDao) getDao();
		return fmFeeTypeDao.insertSelective(ftype);
	}

	public Map queryFeeType(Map map) {
		// TODO Auto-generated method stub
		FmFeeTypeDao fmFeeTypeDao = (FmFeeTypeDao) getDao();
		return fmFeeTypeDao.queryFeeType(map);
	}

	public int updateSelective(FmFeeType ftype) {
		// TODO Auto-generated method stub
		FmFeeTypeDao fmFeeTypeDao = (FmFeeTypeDao) getDao();
		return fmFeeTypeDao.updateSelective(ftype);
	}

	public List<Map<String, Object>> queryAllFeeType() {
		FmFeeTypeDao fmFeeTypeDao = (FmFeeTypeDao) getDao();
		return fmFeeTypeDao.queryAllFeeType();
	}

}
