package com.glens.pwCloudOs.fm.billApply.service;

import java.util.List;

import com.glens.eap.platform.framework.service.impl.EAPAbstractService;
import com.glens.pwCloudOs.fm.billApply.dao.FmBillApplicationFormDao;
import com.glens.pwCloudOs.fm.billApply.vo.FmBillApplicationForm;

public class FmBillApplicationFormSevice extends EAPAbstractService {

	public int insertSelective(FmBillApplicationForm ftype) {
		// TODO Auto-generated method stub
		FmBillApplicationFormDao dao = (FmBillApplicationFormDao) getDao();
		return dao.insertSelective(ftype);
	}

	public int updateSelective(FmBillApplicationForm ftype) {
		FmBillApplicationFormDao dao = (FmBillApplicationFormDao) getDao();
		return dao.updateSelective(ftype);
	}

	public List queryCompanyList() {
		// TODO Auto-generated method stub
		FmBillApplicationFormDao dao = (FmBillApplicationFormDao) getDao();
		return dao.queryCompanyList();
	}

}
