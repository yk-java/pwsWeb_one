
package com.glens.pwCloudOs.pm.prjMgr.processMgr.service;

import java.util.List;
import java.util.Map;

import com.glens.eap.platform.framework.beans.PageBean;
import com.glens.eap.platform.framework.service.impl.EAPAbstractService;
import com.glens.pwCloudOs.pm.prjMgr.processMgr.dao.ProcessMgrDao;
import com.glens.pwCloudOs.pm.prjMgr.processMgr.vo.ProcessVo;


public class ProcessMgrService extends EAPAbstractService {
	
	public List<ProcessVo> selectToDoList(Object params){
		ProcessMgrDao processMgrDao = (ProcessMgrDao)this.dao;
		return processMgrDao.selectToDoList(params);
	}
	
	public PageBean selectXzToDoList(Map<String, Object> params,int currentPage,int perpage){
		ProcessMgrDao processMgrDao = (ProcessMgrDao)this.dao;
		return processMgrDao.queryForPage(params, currentPage, perpage);
	}
	
	public PageBean selectDoneList(Map<String, Object> params,int currentPage,int perpage){
		ProcessMgrDao processMgrDao = (ProcessMgrDao)this.dao;
		return processMgrDao.queryForPage1(params, currentPage, perpage);
	}
	
}
