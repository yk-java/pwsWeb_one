/**
 * @Title: PwBaoAppService.java
 * @Package com.glens.pwCloudOs.pwBaoApp.service
 * @Description: TODO
 * Copyright: Copyright (c) 2016 
 * Company:南京量为石信息科技有限公司
 * @author 
 * @date 2016-8-13 上午10:49:23
 * @version V1.0
 */

package com.glens.pwCloudOs.pwBaoApp.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Transactional;

import com.glens.eap.platform.framework.beans.UserToken;
import com.glens.eap.platform.framework.service.impl.EAPAbstractService;
import com.glens.eap.platform.framework.web.ResponseConstants;
import com.glens.eap.platform.framework.web.support.response.BeanResult;
import com.glens.eap.sys.orgEmployee.employee.dao.MdEmployeeDao;
import com.glens.pwCloudOs.common.context.PwCloudOsConstant;
import com.glens.pwCloudOs.config.PWCloudOsConfig;
import com.glens.pwCloudOs.login.dao.LoginDao;
import com.glens.pwCloudOs.materielMg.assetMg.asset.dao.AssetDao;
import com.glens.pwCloudOs.materielMg.assetMg.asset.dao.GmAssetCheckDao;
import com.glens.pwCloudOs.materielMg.assetMg.asset.vo.AssetVo;
import com.glens.pwCloudOs.materielMg.assetMg.asset.vo.GmAssetCheck;
import com.glens.pwCloudOs.materielMg.goods.dao.GoodsDao;
import com.glens.pwCloudOs.materielMg.goods.dao.GoodsDetailDao;
import com.glens.pwCloudOs.materielMg.goods.vo.Goods;
import com.glens.pwCloudOs.materielMg.goods.vo.GoodsDetail;
import com.glens.pwCloudOs.pwBaoApp.dao.PwBaoAppDao;

/**
 * 
 * 
 * @author
 * @version V1.0
 */

public class PwBaoAppService extends EAPAbstractService {

	private static final int DO_CHECKIN = 1;

	private static final int DO_CHECKOUT = 2;

	private static final int DO_CHECKDISTANCE = 3;

	private PWCloudOsConfig pwcloudosconfig;

	private AssetDao assetDao;

	private GmAssetCheckDao gmAssetCheckDao;

	private GoodsDetailDao goodsDetailDao;

	private GoodsDao goodsDao;

	private MdEmployeeDao mdEmployeeDao;

	private LoginDao loginDao;

	public void setGoodsDetailDao(GoodsDetailDao goodsDetailDao) {
		this.goodsDetailDao = goodsDetailDao;
	}

	public void setGoodsDao(GoodsDao goodsDao) {
		this.goodsDao = goodsDao;
	}

	public Map<String, Object> getAccountByAccountName(String accountName) {

		PwBaoAppDao appDao = (PwBaoAppDao) this.getDao();

		Map<String, Object> m = appDao.getUserByAccount(accountName);

		m.put("wsUrl", pwcloudosconfig.getWsUrl());

		return m;
	}

	public String getPswByAccountName(String accountName) {

		PwBaoAppDao appDao = (PwBaoAppDao) this.getDao();

		return appDao.getPswByAccountName(accountName);
	}

	public Map<String, Object> getCommuteConfig(Map<String, Object> params) {

		PwBaoAppDao appDao = (PwBaoAppDao) this.getDao();

		return appDao.getCommuteConfig(params);
	}

	public List<Map<String, String>> getIntelligenceType() {

		PwBaoAppDao appDao = (PwBaoAppDao) this.getDao();

		return appDao.getIntelligenceType();
	}

	// roleApp 轻应用
	public List<Map<String, Object>> getRoleApp(String accountCode) {

		List<Map<String, Object>> groupList = new ArrayList<Map<String, Object>>();
		PwBaoAppDao appDao = (PwBaoAppDao) this.getDao();
		Map<String, String> params = new HashMap<String, String>();
		params.put("accountCode", accountCode);
		List<Map<String, String>> appList = appDao.getRoleApp(params);
		if (appList != null && appList.size() > 0) {

			Map<String, Object> groupItem = null;
			String groupCode = "";
			for (Map<String, String> appItem : appList) {

				if (groupCode.equals(appItem.get("groupCode"))) {

					List<Map<String, String>> _appList = (List<Map<String, String>>) groupItem
							.get("appList");
					_appList.add(appItem);
				} else {

					groupItem = new HashMap<String, Object>();
					groupItem.put("groupCode", appItem.get("groupCode"));
					groupItem.put("groupName", appItem.get("groupName"));
					List<Map<String, String>> _appList = new ArrayList<Map<String, String>>();
					_appList.add(appItem);
					groupItem.put("appList", _appList);
					groupList.add(groupItem);
				}

				groupCode = appItem.get("groupCode");
			}
		}

		return groupList;
	}

	public List<Map<String, String>> getIntelligenceUrgency() {

		PwBaoAppDao appDao = (PwBaoAppDao) this.getDao();

		return appDao.getIntelligenceUrgency();
	}

	public int insertIntelligence(Map<String, Object> params) {

		PwBaoAppDao appDao = (PwBaoAppDao) this.getDao();

		return appDao.insertIntelligence(params);
	}

	public Map<String, Object> checkin(Map<String, Object> params) {

		PwBaoAppDao appDao = (PwBaoAppDao) this.getDao();
		Integer checkinType = (Integer) params.get("checkinType");
		Map<String, Object> checkinResultMap = new HashMap<String, Object>();
		checkinResultMap.put("checkInTime", "");
		Integer icount = 0;
		if (checkinType == DO_CHECKIN) {

			String checkInTime = appDao.getEmployeeCheckInTime(params);
			if (checkInTime == null || "".equals(checkInTime)) {

				icount = appDao.saveCheckin(params);
			} else {

				icount = 1;
				checkinResultMap.put("checkInTime", checkInTime);
			}
		} else if (checkinType == DO_CHECKOUT) {

			icount = appDao.updateCheckout(params);
		} else if (checkinType == DO_CHECKDISTANCE) {

			icount = appDao.updateCheckDistance(params);
		}

		checkinResultMap.put("icount", icount);

		return checkinResultMap;
	}

	public Map<String, Object> getMyCommuteInfoByDate(Map<String, Object> params) {

		PwBaoAppDao appDao = (PwBaoAppDao) this.getDao();

		return appDao.getMyCommuteInfoByDate(params);
	}

	public Map<String, Object> getLastMobileVersion() {

		PwBaoAppDao appDao = (PwBaoAppDao) this.getDao();

		return appDao.getLastMobileVersion();
	}

	public int updateUserPsw(String accountCode, String psw) {

		PwBaoAppDao appDao = (PwBaoAppDao) this.getDao();
		Map<String, String> params = new HashMap<String, String>();
		params.put("accountCode", accountCode);
		params.put("psw", psw);
		return appDao.updateUserPsw(params);
	}

	public Map<String, Object> getEmployeeInfo(String accountCode) {

		PwBaoAppDao appDao = (PwBaoAppDao) this.getDao();

		return appDao.getEmployeeInfo(accountCode);
	}

	public int updateEmployeeField(Map<String, String> params) {

		PwBaoAppDao appDao = (PwBaoAppDao) this.getDao();

		return appDao.updateEmployeeField(params);
	}

	public Map<String, Object> getUserCheckStatus(Map<String, String> params) {

		PwBaoAppDao appDao = (PwBaoAppDao) this.getDao();
		Map<String, Object> userCheckInfo = appDao
				.getUserCurdateCheckInfo(params);
		if (userCheckInfo != null && userCheckInfo.size() > 0) {

			if (userCheckInfo.get("checkOutTime") != null
					&& !"".equals(userCheckInfo.get("checkOutTime"))) {

				userCheckInfo.put("checkStatus", 2);
			} else if (userCheckInfo.get("checkInTime") != null
					&& !"".equals(userCheckInfo.get("checkInTime"))) {

				userCheckInfo.put("checkStatus", 1);
			} else {

				userCheckInfo.put("checkStatus", 0);
			}
		} else {

			userCheckInfo = new HashMap<String, Object>();
			userCheckInfo.put("checkStatus", 0);
		}

		return userCheckInfo;
	}

	/**
	 * 资产盘点
	 * 
	 * @param gmAssetCheck
	 * @return
	 */
	public BeanResult checkAssets(GmAssetCheck gmAssetCheck) {
		BeanResult result = new BeanResult();
		try {
			// 处理流水信息
			Map m = gmAssetCheckDao.queryLatestAssetCheck(gmAssetCheck
					.getAssetCode());
			if (m != null) {
				String checkEmployeeCode = (String) m
						.get("CHECK_EMPLOYEE_CODE");
				String checkDealDate = (String) m.get("CHECK_DEAL_DATE");
				String client = (String) m.get("CLIENT");
				Long rowid = (Long) m.get("ROWID");
				AssetVo asset = (AssetVo) assetDao.findById(gmAssetCheck
						.getAssetCode());
				Map paramsMap = new HashMap();
				paramsMap.put("rowid", rowid);
				GmAssetCheck check = (GmAssetCheck) gmAssetCheckDao
						.queryGmAssetCheck(paramsMap);
				check.setCheckEndDate(gmAssetCheck.getCheckDealDate());
				gmAssetCheckDao.updateSelective(check);

				if (asset != null) {
					gmAssetCheck.setLoanEmployeecode(asset
							.getLoanEmployeecode());
					gmAssetCheck.setLoanEmployeename(asset
							.getLoanEmployeename());
					gmAssetCheck.setLoanProName(asset.getLoanProName());
					gmAssetCheck.setLoanProNo(asset.getLoanProNo());
					gmAssetCheck.setLoanUnitCode(asset.getLoanUnitCode());
					gmAssetCheck
							.setLoanUnitName(gmAssetCheck.getLoanUnitName());
					gmAssetCheck.setStatus(gmAssetCheck.getStatus());
					gmAssetCheck.setClient3(gmAssetCheck.getClient3());
					gmAssetCheck.setSysProcessFlag("1");
					gmAssetCheckDao.insertSelective(gmAssetCheck);
					result.setStatusCode(ResponseConstants.OK);
					result.setResultMsg("资产信息保存成功！");
					result.setData(gmAssetCheck);

				}

			} else {
				AssetVo asset = (AssetVo) assetDao.findById(gmAssetCheck
						.getAssetCode());
				if (asset != null) {
					gmAssetCheck.setLoanEmployeecode(asset
							.getLoanEmployeecode());
					gmAssetCheck.setLoanEmployeename(asset
							.getLoanEmployeename());
					gmAssetCheck.setLoanProName(asset.getLoanProName());
					gmAssetCheck.setLoanProNo(asset.getLoanProNo());
					gmAssetCheck.setLoanUnitCode(asset.getLoanUnitCode());
					gmAssetCheck
							.setLoanUnitName(gmAssetCheck.getLoanUnitName());
					gmAssetCheck.setStatus(gmAssetCheck.getStatus());
					gmAssetCheck.setClient3(gmAssetCheck.getClient3());
					gmAssetCheck.setSysProcessFlag("1");
					gmAssetCheckDao.insertSelective(gmAssetCheck);
					result.setStatusCode(ResponseConstants.OK);
					result.setResultMsg("资产信息保存成功！");
					result.setData(gmAssetCheck);

				}
			}

			// 如果是仓库管理员,就将该资产状态改为：总部仓库闲置
			Map<String, Object> paramsMap = new HashMap<String, Object>();
			paramsMap.put("employeeCode", gmAssetCheck.getCheckEmployeeCode());
			Map resultMap = mdEmployeeDao.queryEmployeeInfo(paramsMap);
			if (resultMap != null) {
				String accountName = (String) resultMap.get("ACCOUNT_NAME");
				UserToken token = loginDao.getAccountByAccountName(accountName);
				if (token != null) {
					if (token.getRoleCode().equals(
							PwCloudOsConstant.ASSET_MANAGER)) {
						AssetVo av = new AssetVo();
						av.setAssetCode(gmAssetCheck.getAssetCode());
						// 总部仓库闲置
						av.setStatus(8);
						assetDao.update(av);
					}
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			result.setStatusCode(ResponseConstants.SERVER_ERROR);
			result.setResultMsg("服务器错误！");
		}

		return result;
	}

	public AssetVo getAsset(String assetCode) {
		AssetVo asset = (AssetVo) assetDao.findById(assetCode);
		return asset;
	}

	public void setPwcloudosconfig(PWCloudOsConfig pwcloudosconfig) {
		this.pwcloudosconfig = pwcloudosconfig;
	}

	public void setAssetDao(AssetDao assetDao) {
		this.assetDao = assetDao;
	}

	public void setGmAssetCheckDao(GmAssetCheckDao gmAssetCheckDao) {
		this.gmAssetCheckDao = gmAssetCheckDao;
	}

	@Transactional(rollbackFor = { Exception.class })
	public BeanResult commitGoods(GoodsDetail goodsDetail) {
		BeanResult result = new BeanResult();
		goodsDetail.setSysProcessFlag("1");
		goodsDetail.setSysCreateTime(new Date());
		goodsDetailDao.insertSelective(goodsDetail);
		// 查询物资表，如果是没有新增，有(入库加库存，出库减库存）
		Map<String, Object> paramsMap = new HashMap<String, Object>();
		paramsMap.put("goodsCode", goodsDetail.getGoodsCode());
		paramsMap.put("specCode", goodsDetail.getSpecCode());
		paramsMap.put("storeCode", goodsDetail.getStoreCode());
		List list = goodsDao.queryGoodsList(paramsMap);
		if (list.size() > 0) {
			Goods goods = (Goods) list.get(0);
			// 有 入库就增加库存 出库就减少库存
			if (goodsDetail.getGtype().equals("1")) {
				// 入库
				goods.setStock(goods.getStock() + goodsDetail.getAmount());
				goods.setSysUpdateTime(new Date());
				goodsDao.updateSelective(goods);
			} else {
				// 出库
				goods.setStock(goods.getStock() - goodsDetail.getAmount());
				goods.setSysUpdateTime(new Date());
				goodsDao.updateSelective(goods);
			}
		} else {
			Goods goods = new Goods();
			goods.setGoodsCode(goodsDetail.getGoodsCode());
			goods.setGoodsName(goodsDetail.getGoodsName());
			goods.setSpecCode(goodsDetail.getSpecCode());
			goods.setSpecName(goodsDetail.getSpecName());
			goods.setStock(goodsDetail.getAmount());
			goods.setStoreCode(goodsDetail.getStoreCode());
			goods.setSysProcessFlag("1");
			goods.setSysCreateTime(new Date());
			goodsDao.insertSelective(goods);
		}

		result.setStatusCode(ResponseConstants.OK);
		result.setResultMsg("物资信息保存成功！");
		result.setData(goodsDetail);
		return result;
	}

	public void setMdEmployeeDao(MdEmployeeDao mdEmployeeDao) {
		this.mdEmployeeDao = mdEmployeeDao;
	}

	public void setLoginDao(LoginDao loginDao) {
		this.loginDao = loginDao;
	}

}
