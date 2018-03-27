package com.glens.pwCloudOs.materielMg.vehicleMg.insurance.service;

import java.util.Map;

import com.glens.eap.platform.framework.beans.PageBean;
import com.glens.eap.platform.framework.service.impl.EAPAbstractService;
import com.glens.pwCloudOs.materielMg.vehicleMg.insurance.dao.InsuranceDao;

public class InsuranceService extends EAPAbstractService {

	
	public PageBean queryForPage1(Map parameters, int currentPage, int perpage) {
		// TODO Auto-generated method stub
		InsuranceDao dao=(InsuranceDao)getDao();
		return dao.queryForPage1(parameters, currentPage, perpage);
	}
	
}
