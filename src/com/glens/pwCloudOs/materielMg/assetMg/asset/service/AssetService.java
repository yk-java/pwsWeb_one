/**
 * @Title: AssetService.java
 * @Package com.glens.pwCloudOs.materielMg.assetMg.asset.service
 * @Description: TODO
 * Copyright: Copyright (c) 2016 
 * Company:南京量为石信息科技有限公司
 * @author 
 * @date 2016-6-22 下午3:46:34
 * @version V1.0
 */

package com.glens.pwCloudOs.materielMg.assetMg.asset.service;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;

import com.glens.eap.platform.framework.beans.PageBean;
import com.glens.eap.platform.framework.codeCenter.CodeWorker;
import com.glens.eap.platform.framework.context.EAPContext;
import com.glens.eap.platform.framework.service.impl.EAPAbstractService;
import com.glens.eap.platform.framework.web.ResponseConstants;
import com.glens.pwCloudOs.materielMg.assetMg.asset.dao.AssetDao;
import com.glens.pwCloudOs.materielMg.assetMg.asset.vo.AssetClassInfoVo;
import com.glens.pwCloudOs.materielMg.assetMg.asset.vo.AssetVo;
import com.glens.pwCloudOs.materielMg.assetMg.asset.vo.ProAssetChecklistVo;
import com.glens.pwCloudOs.materielMg.assetMg.asset.web.AssetForm;

/**
 * 
 * 
 * @author
 * @version V1.0
 */

public class AssetService extends EAPAbstractService {

	private static final String BED_CLASS_CODE = "9999";

	private static final String BED_CLASS_NAME = "宿舍床位";

	private DecimalFormat decimalFormat = new DecimalFormat(".00");

	/**
	 * 
	 * <p>
	 * Title: insert
	 * </p>
	 * 
	 * <p>
	 * Description:
	 * </p>
	 * 
	 * @param parameters
	 * @return
	 * 
	 * @see com.glens.eap.platform.framework.service.impl.EAPAbstractService#insert(java.lang.Object)
	 **/

	@Override
	public boolean insert(Object parameters) {
		// TODO Auto-generated method stub
		AssetVo params = (AssetVo) parameters;
		CodeWorker simpleCodeWorker = (CodeWorker) EAPContext.getContext()
				.getBean("simpleCodeWorker");
		params.setAssetCode(simpleCodeWorker.createCode("A"));
		List<AssetVo> assetList = new ArrayList<AssetVo>();
		for (int i = 0; i < params.getCount(); i++) {
			AssetVo _vo = new AssetVo();

			try {
				BeanUtils.copyProperties(_vo, params);

				_vo.setAssetCode(simpleCodeWorker.createCode("A"));
				assetList.add(_vo);
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block

				e.printStackTrace();
			} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block

				e.printStackTrace();
			}
		}

		return super.insert(assetList);
	}

	/**
	 * 获取设备的生命周期信息
	 * 
	 * @param @param assetCode
	 * @param @return
	 * @return 一个Map类型记录
	 * @throws
	 * @author:
	 * @update:[日期YYYY-MM-DD] [更改人姓名][变更描述]
	 */

	public Map<String, Object> getAssetLiftCycle(String assetCode) {

		Map<String, String> params = new HashMap<String, String>();
		params.put("assetCode", assetCode);
		AssetDao assetDao = (AssetDao) getDao();
		Map<String, Object> proDetail = assetDao.selectAssetDetail(params);
		if (proDetail != null) {

			// 获取保养记录(top20)
			List<Map<String, Object>> maintainList = assetDao
					.selectTop20AssetMaintain(params);
			proDetail.put("maintainList", maintainList);
			// 获取维修记录(top20)
			List<Map<String, Object>> repairList = assetDao
					.selectTop20AssetRepair(params);
			proDetail.put("repairList", repairList);
			// 获取租用记录(top20)
			List<Map<String, Object>> rentList = assetDao
					.selectTop20AssetRent(params);
			proDetail.put("rentList", rentList);
		}

		return proDetail;
	}

	// 资产调动
	public boolean assetMove(Map<String, String> params) {

		AssetDao assetDao = (AssetDao) getDao();
		try {
			assetDao.updateRentStatus(params);// 修改租用状态

			assetDao.addRent(params);// 新增一条租用记录

			assetDao.updateAssetStatus(params);// 修改资产租用状态
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	// 资产归还
	public boolean doReturn(Map<String, String> params) {

		AssetDao assetDao = (AssetDao) getDao();
		try {

			assetDao.updateAssetStatus(params);// 修改资产租用状态为退租

			assetDao.updateRentStatus(params);// 修改租用状态

			return true;
		} catch (Exception e) {
			return false;
		}
	}

	// 资产报废
	public Map<String, Object> assetScrap(String assetCode, float dumpAmount,
			String dateDump) {

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("assetCode", assetCode);
		params.put("dateDump", dateDump);
		params.put("dumpAmount", dumpAmount);
		AssetDao assetDao = (AssetDao) getDao();
		Map<String, Object> proDetail = assetDao.assetScrap(params);

		return proDetail;
	}

	// 修改资产内容
	public Map<String, Object> upDateForms(String assetCode, String forms) {

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("assetCode", assetCode);
		params.put("forms", forms);

		AssetDao assetDao = (AssetDao) getDao();
		Map<String, Object> proDetail = assetDao.upDateForms(params);

		return proDetail;
	}

	/**
	 * 获取项目租用资产清单
	 * 
	 * @param @return
	 * @return 一个Map类型记录
	 * @throws
	 * @author:
	 * @update:[日期YYYY-MM-DD] [更改人姓名][变更描述]
	 */

	public Map<String, Object> selectAssetChecklist(Map<String, Object> params) {

		Map<String, Object> assetCheckListResult = new HashMap<String, Object>();

		List<ProAssetChecklistVo> proAssetRentDetailList = new ArrayList<ProAssetChecklistVo>();
		AssetDao assetDao = (AssetDao) getDao();
		// 获取租赁设备的项目组
		List<Map<String, String>> proList = assetDao.selectRentAssetPro(params);
		// 获取租赁给项目组的设备的分类
		List<Map<String, String>> assetClassList = assetDao
				.selectRentAssetClass();
		if (proList != null && proList.size() > 0 && assetClassList != null
				&& assetClassList.size() > 0) {

			// 合并成结果结构
			proAssetRentDetailList = makeUpResult(proList, assetClassList);

			List<Map<String, Object>> assetChecklist = assetDao
					.selectAssetChecklist(params);
			if (assetChecklist != null && assetChecklist.size() > 0) {

				for (Map<String, Object> assetCheck : assetChecklist) {

					// 查找相应的项目设备明细对象
					ProAssetChecklistVo proAssetDetail = getProAssetRentDetailByProNo(
							proAssetRentDetailList,
							(String) assetCheck.get("loadProNo"));
					if (proAssetDetail != null) {

						// 处理租用信息
						setAssetCheckToPro(proAssetDetail, assetCheck);
					}
				}
			}

			// 获取床位租用信息并且处理
			List<Map<String, Object>> rentBedList = assetDao
					.selectAllProRentBed();
			if (rentBedList != null && rentBedList.size() > 0) {

				for (Map<String, Object> rentBed : rentBedList) {

					// 查找相应的项目设备明细对象
					ProAssetChecklistVo proAssetDetail = getProAssetRentDetailByProNo(
							proAssetRentDetailList,
							(String) rentBed.get("proNo"));
					if (proAssetDetail != null) {

						// 处理租用信息
						setAssetCheckToPro(proAssetDetail, rentBed);
					}
				}
			}
		}

		assetCheckListResult.put("assetChecklist", proAssetRentDetailList);

		// 统计各个项目的总和
		for (int i = 0; i < proAssetRentDetailList.size(); i++) {
			ProAssetChecklistVo tempvo = proAssetRentDetailList.get(i);
			List<AssetClassInfoVo> list = tempvo.getAssetClassInfoList();
			double totalAmount = 0;
			for (int j = 0; j < list.size(); j++) {
				AssetClassInfoVo assetvo = list.get(j);
				double amount = assetvo.getAmount();
				totalAmount += amount;
			}
			tempvo.setTotalAmount(totalAmount);
		}

		// 添加一个床位类型
		Map<String, String> bedClass = new HashMap<String, String>();
		bedClass.put("assetClassCode", BED_CLASS_CODE);
		bedClass.put("assetClassName", BED_CLASS_NAME);
		assetClassList.add(bedClass);

		// 统计各个设备的总和
		List<Map> resultList = new ArrayList<Map>();

		for (int i = 0; i < assetClassList.size(); i++) {
			Map m = assetClassList.get(i);
			String assetClassCode = m.get("assetClassCode").toString();
			String assetClassName = m.get("assetClassName").toString();
			Map totalMap = new HashMap();
			totalMap.put("assetClassCode", assetClassCode);
			totalMap.put("assetClassName", assetClassName);

			double assetClassTotal = 0;
			int assetClassNum = 0;
			double lastTotal = 0;
			for (int k = 0; k < proAssetRentDetailList.size(); k++) {
				ProAssetChecklistVo tempvo = proAssetRentDetailList.get(k);
				List<AssetClassInfoVo> list = tempvo.getAssetClassInfoList();
				lastTotal += tempvo.getTotalAmount();

				for (int j = 0; j < list.size(); j++) {
					AssetClassInfoVo assetvo = list.get(j);
					if (assetvo.getAssetClassCode().equals(assetClassCode)) {
						double amount = assetvo.getAmount();
						int num = assetvo.getCount();
						assetClassTotal += amount;
						assetClassNum += num;
					}
				}
			}
			totalMap.put("assetClassTotal", assetClassTotal);
			totalMap.put("assetClassNum", assetClassNum);
			totalMap.put("lastTotal", lastTotal);
			resultList.add(totalMap);

		}

		assetCheckListResult.put("assetClassList", assetClassList);
		assetCheckListResult.put("totalResultList", resultList);
		return assetCheckListResult;
	}

	/**
	 * 获取项目租用的某分类的资产
	 * 
	 * @param @param params
	 * @param @return
	 * @return 一个Map类型记录
	 * @throws
	 * @author:
	 * @update:[日期YYYY-MM-DD] [更改人姓名][变更描述]
	 */

	public List<Map<String, Object>> selectProRentAsset(
			Map<String, String> params) {

		AssetDao assetDao = (AssetDao) getDao();

		return assetDao.selectProRentAsset(params);
	}

	/**
	 * 获取项目租用床位
	 * 
	 * @param @param params
	 * @param @return
	 * @return 一个Map类型记录
	 * @throws
	 * @author:
	 * @update:[日期YYYY-MM-DD] [更改人姓名][变更描述]
	 */

	public List<Map<String, Object>> selectProRentBed(Map<String, String> params) {

		AssetDao assetDao = (AssetDao) getDao();

		return assetDao.selectProRentBed(params);
	}

	/**
	 * 组成项目设备类型的维度信息表结构
	 * 
	 * @param @param proList
	 * @param @param assetClassList
	 * @param @return
	 * @return 一个Map类型记录
	 * @throws
	 * @author:
	 * @update:[日期YYYY-MM-DD] [更改人姓名][变更描述]
	 */

	private List<ProAssetChecklistVo> makeUpResult(
			List<Map<String, String>> proList,
			List<Map<String, String>> assetClassList) {

		List<ProAssetChecklistVo> proAssetRentDetailList = new ArrayList<ProAssetChecklistVo>();

		for (Map<String, String> pro : proList) {

			ProAssetChecklistVo proAssetChecklistVo = new ProAssetChecklistVo();
			proAssetChecklistVo.setProNo(pro.get("loadProNo"));
			proAssetChecklistVo.setProName(pro.get("loanProName"));
			proAssetChecklistVo
					.setAssetClassInfoList(new ArrayList<AssetClassInfoVo>());

			for (Map<String, String> assetClass : assetClassList) {

				AssetClassInfoVo assetClassInfoVo = new AssetClassInfoVo();
				assetClassInfoVo.setAmount(0.0);
				assetClassInfoVo.setAssetClassCode(assetClass
						.get("assetClassCode"));
				assetClassInfoVo.setAssetClassName(assetClass
						.get("assetClassName"));
				assetClassInfoVo.setAvgAmount(0.0);
				assetClassInfoVo.setCount(0);
				assetClassInfoVo.setAssetClassFlag(1);

				proAssetChecklistVo.getAssetClassInfoList().add(
						assetClassInfoVo);
			}

			// 手工添加床位
			AssetClassInfoVo assetClassInfoVo = new AssetClassInfoVo();
			assetClassInfoVo.setAmount(0.0);
			assetClassInfoVo.setAssetClassCode(BED_CLASS_CODE);
			assetClassInfoVo.setAssetClassName(BED_CLASS_NAME);
			assetClassInfoVo.setAvgAmount(0.0);
			assetClassInfoVo.setCount(0);
			assetClassInfoVo.setAssetClassFlag(2);

			proAssetChecklistVo.getAssetClassInfoList().add(assetClassInfoVo);

			proAssetRentDetailList.add(proAssetChecklistVo);
		}

		return proAssetRentDetailList;
	}

	/**
	 * 根据项目编号获取相应的项目设备明细对象
	 * 
	 * @param @param proAssetRentDetailList
	 * @param @param proNo
	 * @param @return
	 * @return 一个Map类型记录
	 * @throws
	 * @author:
	 * @update:[日期YYYY-MM-DD] [更改人姓名][变更描述]
	 */

	private ProAssetChecklistVo getProAssetRentDetailByProNo(
			List<ProAssetChecklistVo> proAssetRentDetailList, String proNo) {

		ProAssetChecklistVo vo = null;
		if (proAssetRentDetailList != null && proAssetRentDetailList.size() > 0) {

			for (ProAssetChecklistVo proAssetDetail : proAssetRentDetailList) {

				if (proAssetDetail.getProNo().equals(proNo)) {

					vo = proAssetDetail;

					break;
				}
			}
		}

		return vo;
	}

	/**
	 * 把租用信息设置到项目设备明细对象里面
	 * 
	 * @param @param proAssetChecklistVo
	 * @param @param assetCheck
	 * @return 一个Map类型记录
	 * @throws
	 * @author:
	 * @update:[日期YYYY-MM-DD] [更改人姓名][变更描述]
	 */

	private void setAssetCheckToPro(ProAssetChecklistVo proAssetChecklistVo,
			Map<String, Object> assetCheck) {

		List<AssetClassInfoVo> assetClassInfoList = proAssetChecklistVo
				.getAssetClassInfoList();
		if (assetClassInfoList != null && assetClassInfoList.size() > 0) {

			for (AssetClassInfoVo assetClassInfoVo : assetClassInfoList) {

				if (assetClassInfoVo.getAssetClassCode().equals(
						assetCheck.get("assetClassCode"))) {

					assetClassInfoVo.setCount(assetClassInfoVo.getCount() + 1);
					Double amount = assetClassInfoVo.getAmount()
							+ ((BigDecimal) assetCheck.get("rentAmount"))
									.doubleValue();
					assetClassInfoVo.setAmount(amount);
					assetClassInfoVo.setAvgAmount(Double
							.parseDouble(decimalFormat.format((assetClassInfoVo
									.getAmount() * 1.0f)
									/ assetClassInfoVo.getCount())));
				}
			}
		}
	}

	public List<AssetVo> queryForExport(AssetForm form) {
		AssetDao dao = (AssetDao) this.dao;
		List<AssetVo> list = dao.queryForExport(form.toMap());
		return list;
	}

	public PageBean getMaterialBase(Map parameters, int currentPage, int perpage) {
		AssetDao dao = (AssetDao) this.dao;
		return dao.queryForPage1(parameters, currentPage, perpage);
	}

	public PageBean getVehicleList(Map parameters, int currentPage, int perpage) {
		AssetDao dao = (AssetDao) this.dao;
		return dao.queryForPage2(parameters, currentPage, perpage);
	}

	public String queryHouseRent(Map paramsMap) {
		String result = "0";
		AssetDao dao = (AssetDao) this.dao;
		Map m = dao.queryHouseRent(paramsMap);
		if (m != null) {
			// rentAmount
			result = m.get("rentAmount") + "";
		}
		return result;
	}

	public String queryCarRent(Map paramsMap) {
		String result = "0";
		AssetDao dao = (AssetDao) this.dao;
		Map m = dao.queryCarRent(paramsMap);
		if (m != null) {
			// rentAmount
			result = m.get("rentAmount") + "";
		}
		return result;
	}

	public String queryDeviceUse(Map paramsMap) {
		// TODO Auto-generated method stub
		String result = "0";
		AssetDao dao = (AssetDao) this.dao;
		Map m = dao.queryDeviceUse(paramsMap);
		if (m != null) {
			// rentAmount
			result = m.get("rentAmount") + "";
		}
		return result;
	}

	public String queryGasolineUse(Map paramsMap) {
		// TODO Auto-generated method stub
		String result = "0";
		AssetDao dao = (AssetDao) this.dao;
		Map m = dao.queryGasolineUse(paramsMap);
		if (m != null) {
			// rentAmount
			result = m.get("rentAmount") + "";
		}
		return result;
	}
}
