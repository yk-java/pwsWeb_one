package com.glens.pwCloudOs.fm.expense.service;

import com.glens.eap.platform.framework.service.impl.EAPAbstractService;
import com.glens.pwCloudOs.fm.expense.dao.FmFeeTypeDao;
import com.glens.pwCloudOs.fm.expense.dao.FmMoneyReturnDao;
import com.glens.pwCloudOs.fm.expense.dao.FmMoneyReturnDetailDao;
import com.glens.pwCloudOs.fm.expense.vo.FmFeeType;
import com.glens.pwCloudOs.fm.expense.vo.FmMoneyReturn;

public class FmMoneyReturnService extends EAPAbstractService {

	private FmFeeTypeDao fmFeeTypeDao;

	private FmMoneyReturnDetailDao fmMoneyReturnDetailDao;

	public FmMoneyReturn queryFmMoneyReturn(Long rowid) {
		FmMoneyReturnDao fmMoneyReturnDao = (FmMoneyReturnDao) getDao();
		FmMoneyReturn fmMoneyReturn = fmMoneyReturnDao
				.queryFmMoneyReturnByRowid(rowid);
		FmFeeType ft = fmFeeTypeDao.queryFeeTypeByCode(fmMoneyReturn
				.getFeeType());
		fmMoneyReturn.setFeeName(ft != null ? ft.getFeeName() : "");
		fmMoneyReturn.getDetailList().addAll(
				fmMoneyReturnDetailDao.queryDetail(fmMoneyReturn.getRowid()));

		return fmMoneyReturn;
	}

	public void setFmFeeTypeDao(FmFeeTypeDao fmFeeTypeDao) {
		this.fmFeeTypeDao = fmFeeTypeDao;
	}

	public void setFmMoneyReturnDetailDao(
			FmMoneyReturnDetailDao fmMoneyReturnDetailDao) {
		this.fmMoneyReturnDetailDao = fmMoneyReturnDetailDao;
	}

	public void updateSelective(FmMoneyReturn fr) {
		// TODO Auto-generated method stub
		FmMoneyReturnDao fmMoneyReturnDao = (FmMoneyReturnDao) getDao();
		fmMoneyReturnDao.updateSelective(fr);
	}

}
