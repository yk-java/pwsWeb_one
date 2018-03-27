/**
 * @Title: CommonService.java
 * @Package com.glens.pwCloudOs.common.service
 * @Description: TODO
 * Copyright: Copyright (c) 2016 
 * Company:南京量为石信息科技有限公司
 * @author 
 * @date 2016-6-8 下午3:26:19
 * @version V1.0
 */


package com.glens.pwCloudOs.common.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.glens.eap.platform.framework.beans.TreeNode;
import com.glens.eap.platform.framework.service.impl.EAPAbstractService;
import com.glens.pwCloudOs.common.dao.CommonDao;

/**
 * 
 * 
 * @author 
 * @version V1.0
 */

public class CommonService extends EAPAbstractService {

	public TreeNode getOrgTree() {
		
		CommonDao commonDao = (CommonDao) getDao();
		TreeNode orgTree = null;
		List<TreeNode> orgNodes = commonDao.getOrgTree();
		if (orgNodes != null) {

			Map<String, Object> tmpMap = new HashMap<String, Object>();
			for (TreeNode _n : orgNodes) {

				if (_n.getPid().equals("-1")) {

					orgTree = _n;
				} else {

					TreeNode pNode = (TreeNode) tmpMap.get(_n.getPid());
					if (pNode != null) {

						if (pNode.getChildren() == null) {

							pNode.setChildren(new ArrayList<TreeNode>());
						}

						pNode.getChildren().add(_n);
					}

				}

				tmpMap.put(_n.getId(), _n);
			}
		}

		return orgTree;
	}
	
	public List<Map<String, String>> getActivePro(Map<String, String> params) {
		
		CommonDao commonDao = (CommonDao) getDao();
		
		return commonDao.getActivePro(params);
	}
	
	public List<Map<String, String>> getAllPro(Map<String, String> params) {
	
		CommonDao commonDao = (CommonDao) getDao();
		
		return commonDao.getAllPro(params);
	}
	public List<Map<String, String>> getGaisuanPros(Map<String, String> params) {
		
		CommonDao commonDao = (CommonDao) getDao();
		
		return commonDao.getGaisuanPros(params);
	}
	public List<Map<String, String>> getCheckPros(Map<String, String> params) {
		
		CommonDao commonDao = (CommonDao) getDao();
		
		return commonDao.getCheckPros(params);
	}
	
	
	public List<Map<String, String>> getClosedPro(Map<String, String> params) {
		
		CommonDao commonDao = (CommonDao) getDao();
		return commonDao.getClosedPro(params);
	}
	
	public List<Map<String, String>> getProCategory(Map<String, Object> params) {
		
		CommonDao commonDao = (CommonDao) getDao();
		
		return commonDao.selectProCategory(params);
	}
	
	public List<Map<String, String>> selectAssetClass(String assetCharacterCode,String iscm) {
		
		CommonDao commonDao = (CommonDao) getDao();
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("assetCharacterCode", assetCharacterCode);
		params.put("iscm", iscm);
		return commonDao.selectAssetClass(params);
	}
	
	public List<Map<String, String>> selectAssetCharacter() {
		
		CommonDao commonDao = (CommonDao) getDao();
		return commonDao.selectAssetCharacter();
	}
	
	public List<Map<String, String>> selectVehicle() {
		
		CommonDao commonDao = (CommonDao) getDao();
		return commonDao.selectVehicle();
	}
	
	public List<Map<String, String>> vehicleInProject(String proNo) {
		CommonDao commonDao = (CommonDao) getDao();
		return commonDao.vehicleInProject(proNo);
	}
	
	public List<Map<String, String>> selectEmployeeByPro(String proNo) {
		CommonDao commonDao = (CommonDao) getDao();
		return commonDao.vehicleInProject(proNo);
	}
	/**
	 * 项目员工树
	 * @param params 项目编号与姓名筛选
	 * @return
	 */
	public List<Map<String, String>> selectEmployeeByPro(Map<String, Object> params) {
		CommonDao commonDao = (CommonDao) getDao();
		return commonDao.selectEmployeeByPro(params);
	}
	/**
	 * 部门员工树
	 * @param params 部门编号与姓名筛选
	 * @return
	 */
	public List<Map<String, String>> selectEmployeeByUnit(Map<String, Object> params) {
		CommonDao commonDao = (CommonDao) getDao();
		return commonDao.selectEmployeeByUnit(params);
	}
	
	/**
	 * 职位员工树
	 * @param params 部门编号与姓名筛选
	 * @return
	 */
	public List<Map<String, String>> selectEmployeeByJob() {
		CommonDao commonDao = (CommonDao) getDao();
		return commonDao.selectEmployeeByJob();
	}
	
	
	public List getAllEmps() {
		CommonDao commonDao = (CommonDao) getDao();
		return commonDao.getAllEmps();
	}
	
	
	public List getManagerPros(Object params) {
		CommonDao commonDao = (CommonDao) getDao();
		return commonDao.getManagerPros(params);
	}
	
	public List getProList(Object params) {
		CommonDao commonDao = (CommonDao) getDao();
		return commonDao.getProList(params);
	}
	
	public List<String> getAccountByRole(String roleCode, String proNo) {
		
		CommonDao commonDao = (CommonDao) getDao();
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("roleCode", roleCode);
		params.put("proNo", proNo);
		
		return commonDao.getAccountByRole(params);
	}
	
}
