package com.glens.pwCloudOs.addrLib.service;

import java.util.List;

import com.glens.eap.platform.framework.service.impl.EAPAbstractService;
import com.glens.pwCloudOs.addrLib.dao.AddAddrClassDao;

public class AddAddrClassService extends EAPAbstractService {
	
	public List queryAll(Object parameters) {
		return this.dao.queryForList(parameters);
	}
	
	
	public List getDatasourceList(){
		AddAddrClassDao dao=(AddAddrClassDao)getDao();
		return dao.getDatasourceList();
	}

}
