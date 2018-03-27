/**
 * @Title: AssetController.java
 * @Package com.glens.pwCloudOs.materielMg.assetMg.asset.web
 * @Description: TODO
 * Copyright: Copyright (c) 2016 
 * Company:南京量为石信息科技有限公司
 * @author 
 * @date 2016-6-22 下午4:49:57
 * @version V1.0
 */

package com.glens.pwCloudOs.materielMg.assetMg.asset.web;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.glens.eap.platform.core.annotation.FormProcessor;
import com.glens.eap.platform.core.utils.StringUtil;
import com.glens.eap.platform.core.view.EAPView;
import com.glens.eap.platform.core.web.ControllerForm;
import com.glens.eap.platform.core.web.EAPController;
import com.glens.eap.platform.framework.beans.PageBean;
import com.glens.eap.platform.framework.beans.UserToken;
import com.glens.eap.platform.framework.utils.excel.ExcelHelper;
import com.glens.eap.platform.framework.web.EAPJsonAbstractController;
import com.glens.eap.platform.framework.web.ResponseConstants;
import com.glens.eap.platform.framework.web.support.JsonProcessRequestHandler;
import com.glens.eap.platform.framework.web.support.response.BeanResult;
import com.glens.eap.platform.framework.web.support.response.KeyResult;
import com.glens.eap.platform.framework.web.support.response.ListResult;
import com.glens.eap.platform.framework.web.support.response.PageBeanResult;
import com.glens.eap.platform.framework.web.support.response.ResponseResult;
import com.glens.pwCloudOs.common.context.PwCloudOsConstant;
import com.glens.pwCloudOs.materielMg.assetMg.asset.service.AssetService;
import com.glens.pwCloudOs.materielMg.assetMg.asset.vo.AssetVo;

/**
 * 
 * 
 * @author
 * @version V1.0
 */

@FormProcessor(clazz = "com.glens.pwCloudOs.materielMg.assetMg.asset.web.AssetForm")
@RequestMapping("pmsServices/materielMg/assetMg/asset")
public class AssetController extends EAPJsonAbstractController {

	@Override
	@RequestMapping(value = "add", method = RequestMethod.POST)
	public Object insert(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub
				AssetForm form = (AssetForm) actionForm;
				if (StringUtil.isNotNull(form.getMarkOwner())) {
					form.setIsMark("1");
				} else {
					form.setIsMark("0");
				}
				boolean ok = getService().insert(form.toVo());
				KeyResult result = new KeyResult();
				if (ok) {
					result.setStatusCode(ResponseConstants.OK);
					result.setResultMsg("新增成功");
					result.setGenerateKey(actionForm.getGenerateKey());
				} else {

					result.setStatusCode(ResponseConstants.SERVER_ERROR);
					result.setResultMsg("新增失败");
				}

				return result;
			}

		});
	}

	@Override
	@RequestMapping(value = "update", method = RequestMethod.POST)
	public Object update(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub

				AssetForm form = (AssetForm) actionForm;
				if (StringUtil.isNotNull(form.getMarkOwner())) {
					form.setIsMark("1");
				} else {
					form.setIsMark("0");
				}

				int iCount = getService().update(form.toVo());

				ResponseResult result = new ResponseResult();

				if (iCount > 0) {

					result.setStatusCode(ResponseConstants.OK);
					result.setResultMsg("修改成功");
				} else {

					result.setStatusCode(ResponseConstants.SERVER_ERROR);
					result.setResultMsg("修改失败");
				}

				return result;
			}

		});
	}

	@RequestMapping(value = "assetLiftCycle", method = RequestMethod.GET)
	public Object getAssetLiftCycle(HttpServletRequest request,
			HttpServletResponse response) {

		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub

				AssetService assetService = (AssetService) getService();
				AssetForm assetForm = (AssetForm) actionForm;
				Map<String, Object> asset = assetService
						.getAssetLiftCycle(assetForm.getAssetCode());
				BeanResult result = new BeanResult();
				if (asset != null && asset.size() > 0) {

					result.setStatusCode(ResponseConstants.OK);
					result.setResultMsg("获取资产生命周期成功!");
					result.setData(asset);
				} else {

					result.setStatusCode(ResponseConstants.NO_DATA);
					result.setResultMsg("获取资产生命周期失败!");
				}

				return result;
			}
		});
	}

	@Override
	@RequestMapping(value = "list", method = RequestMethod.GET)
	public Object list(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub
				UserToken token = getToken(request);
				Map<String, Object> params = actionForm.toMap();
				if (token != null) {

					if (PwCloudOsConstant.DISTRICT_MANAGER_ROLE_CODE
							.equals(token.getRoleCode())) {

						params.put("districtManager", token.getEmployeeCode());
					}
				}

				PageBean page = getService().queryForPage(params,
						actionForm.getCurrentPage(), actionForm.getPerpage());
				PageBeanResult result = new PageBeanResult();
				if (page != null) {

					result.setStatusCode(ResponseConstants.OK);
					result.setResultMsg("获取成功");
					result.setPageBean(page);
				} else {

					result.setStatusCode(ResponseConstants.NO_DATA);
					result.setResultMsg("没有获取到数据");
				}

				return result;
			}

		});
	}

	// 得到物资基础表
	@RequestMapping(value = "getMaterialBase", method = RequestMethod.GET)
	public Object getMaterialBase(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub

				AssetService ser = (AssetService) getService();

				PageBean page = ser.getMaterialBase(actionForm.toMap(),
						actionForm.getCurrentPage(), actionForm.getPerpage());
				PageBeanResult result = new PageBeanResult();
				if (page != null) {

					result.setStatusCode(ResponseConstants.OK);
					result.setResultMsg("获取成功");
					result.setPageBean(page);
				} else {

					result.setStatusCode(ResponseConstants.NO_DATA);
					result.setResultMsg("没有获取到数据");
				}

				return result;
			}

		});
	}

	// 得到车辆基础表
	@RequestMapping(value = "getVehicleList", method = RequestMethod.GET)
	public Object getVehicleList(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub

				AssetService ser = (AssetService) getService();

				PageBean page = ser.getVehicleList(actionForm.toMap(),
						actionForm.getCurrentPage(), actionForm.getPerpage());
				PageBeanResult result = new PageBeanResult();
				if (page != null) {

					result.setStatusCode(ResponseConstants.OK);
					result.setResultMsg("获取成功");
					result.setPageBean(page);
				} else {

					result.setStatusCode(ResponseConstants.NO_DATA);
					result.setResultMsg("没有获取到数据");
				}

				return result;
			}

		});
	}

	// 资产归还

	@RequestMapping(value = "doReturn", method = RequestMethod.POST)
	public Object doReturn(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub

				AssetService ser = (AssetService) getService();
				AssetForm form = (AssetForm) actionForm;

				form.setStatus("1");// 闲置
				form.setLoanEmployeecode("");
				form.setLoanEmployeename("");
				form.setLoanUnitCode("");
				form.setLoanUnitName("");
				form.setLoanProName("");
				form.setLoanProNo("");

				// String assetCode=form.getAssetCode();

				boolean iCount = ser.doReturn(form.toMap());

				ResponseResult result = new ResponseResult();

				if (iCount) {

					result.setStatusCode(ResponseConstants.OK);
					result.setResultMsg("归还成功");
				} else {

					result.setStatusCode(ResponseConstants.SERVER_ERROR);
					result.setResultMsg("归还出错");
				}

				return result;
			}

		});
	}

	@RequestMapping(value = "assetMove", method = RequestMethod.POST)
	public Object assetMove(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub

				AssetService ser = (AssetService) getService();
				AssetForm form = (AssetForm) actionForm;

				String type = form.getRentType();
				AssetForm f = new AssetForm();
				if (type.equals("0")) { // 项目租用

					f.setAssetCode(form.getAssetCode());
					f.setLoanEmployeecode(form.getEmployeeCode());
					f.setLoanEmployeename(form.getEmployeeName());
					f.setLoanProNo(form.getProNo());
					f.setLoanProName(form.getProName());
					f.setLoanUnitCode(form.getUnitCode1());
					f.setLoanUnitName(form.getUnitName1());
					f.setRentDate(form.getRentDate());
					f.setEstimateReturnDate(form.getEstimateReturnDate());
					f.setRemarks(form.getRemarks());
					f.setStatus("3");
				} else if (type.equals("1")) {// 部门租用

					f.setAssetCode(form.getAssetCode());
					f.setLoanEmployeecode(form.getLoanEmployeecode());
					f.setLoanEmployeename(form.getLoanEmployeename());
					// f.setLoanProNo(form.getProNo());
					// f.setLoanProName(form.getProName());
					f.setLoanUnitCode(form.getLoanUnitCode());
					f.setLoanUnitName(form.getLoanUnitName());
					f.setRentDate(form.getRentDate());
					f.setEstimateReturnDate(form.getEstimateReturnDate());
					f.setRemarks(form.getRemarks());
					f.setStatus("2");

				}

				boolean isok = ser.assetMove(f.toMap());

				ResponseResult result = new ResponseResult();

				if (isok) {

					result.setStatusCode(ResponseConstants.OK);
					result.setResultMsg("调动成功");
				} else {

					result.setStatusCode(ResponseConstants.SERVER_ERROR);
					result.setResultMsg("调动失败");
				}

				return result;
			}

		});
	}

	@RequestMapping(value = "assetChecklist", method = RequestMethod.GET)
	public Object selectAssetChecklist(HttpServletRequest request,
			HttpServletResponse response) {

		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub
				UserToken token = (UserToken) request.getSession()
						.getAttribute(UserToken.KEY_TOKEN);
				AssetService assetService = (AssetService) getService();
				Map map = actionForm.toMap();
				if (PwCloudOsConstant.DEPT_MANAGER_ROLE_CODE.equals(token
						.getRoleCode())) {
					map.put("deptCode", token.getUnitCode());
				}
				if (PwCloudOsConstant.DISTRICT_MANAGER_ROLE_CODE
						.equals(token.getRoleCode())) {
					map.put("districtManager", token.getEmployeeCode());
				}

				BeanResult result = new BeanResult();
				Map<String, Object> assetCheckListResult = assetService
						.selectAssetChecklist(map);
				if (assetCheckListResult != null
						&& assetCheckListResult.size() > 0) {

					result.setStatusCode(ResponseConstants.OK);
					result.setResultMsg("获取项目设备清单成功!");
					result.setData(assetCheckListResult);
				} else {

					result.setStatusCode(ResponseConstants.NO_DATA);
					result.setResultMsg("获取项目设备清单失败!");
				}

				return result;
			}
		});
	}

	@RequestMapping(value = "proAssetRentlist", method = RequestMethod.GET)
	public Object selectProRentAsset(HttpServletRequest request,
			HttpServletResponse response) {

		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub

				AssetService assetService = (AssetService) getService();
				ListResult result = new ListResult();
				List<Map<String, Object>> proAssetRentList = assetService
						.selectProRentAsset(actionForm.toMap());
				if (proAssetRentList != null && proAssetRentList.size() > 0) {

					result.setStatusCode(ResponseConstants.OK);
					result.setResultMsg("获取项目设备租用信息成功!");
					result.setList(proAssetRentList);
				} else {

					result.setStatusCode(ResponseConstants.NO_DATA);
					result.setResultMsg("获取项目设备租用信息失败!");
				}

				return result;
			}
		});
	}

	@RequestMapping(value = "proBedRentlist", method = RequestMethod.GET)
	public Object selectProRentBed(HttpServletRequest request,
			HttpServletResponse response) {

		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub

				AssetService assetService = (AssetService) getService();
				ListResult result = new ListResult();
				List<Map<String, Object>> proBedRentList = assetService
						.selectProRentBed(actionForm.toMap());
				if (proBedRentList != null && proBedRentList.size() > 0) {

					result.setStatusCode(ResponseConstants.OK);
					result.setResultMsg("获取项目床位租用信息成功!");
					result.setList(proBedRentList);
				} else {

					result.setStatusCode(ResponseConstants.NO_DATA);
					result.setResultMsg("获取项目床位租用信息失败!");
				}

				return result;
			}
		});
	}

	@RequestMapping(value = "assetScrap", method = RequestMethod.POST)
	public Object assetScrap(HttpServletRequest request,
			HttpServletResponse response) {

		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub

				AssetService assetService = (AssetService) getService();
				AssetForm assetForm = (AssetForm) actionForm;
				Map<String, Object> asset = assetService.assetScrap(
						assetForm.getAssetCode(), assetForm.getDumpAmount(),
						assetForm.getDateDump());
				BeanResult result = new BeanResult();
				if (asset != null && asset.size() > 0) {

					result.setStatusCode(ResponseConstants.OK);
					result.setResultMsg("资产报废成功!");
					result.setData(asset);
				} else {

					result.setStatusCode(ResponseConstants.NO_DATA);
					result.setResultMsg("资产报废失败!");
				}

				return result;
			}
		});
	}

	// 修改内容
	@RequestMapping(value = "upDateForms", method = RequestMethod.POST)
	public Object upDateForms(HttpServletRequest request,
			HttpServletResponse response) {

		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub

				AssetService assetService = (AssetService) getService();
				AssetForm assetForm = (AssetForm) actionForm;
				Map<String, Object> asset = assetService.upDateForms(
						assetForm.getAssetCode(), assetForm.getForms());
				BeanResult result = new BeanResult();
				if (asset != null && asset.size() > 0) {

					result.setStatusCode(ResponseConstants.OK);
					result.setResultMsg("资产报废成功!");
					result.setData(asset);
				} else {

					result.setStatusCode(ResponseConstants.NO_DATA);
					result.setResultMsg("资产报废失败!");
				}

				return result;
			}
		});
	}

	@RequestMapping(method = RequestMethod.GET, value = "export")
	public Object export(HttpServletRequest request,
			HttpServletResponse response) {
		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {

				AssetService assetService = (AssetService) getService();
				AssetForm form = (AssetForm) actionForm;

				List<AssetVo> result = assetService.queryForExport(form);

				return result;
			}

			@Override
			public Object doWithFinish(HttpServletRequest request,
					HttpServletResponse response, Object data, String viewType,
					EAPController controller) {

				ModelAndView modelAndView = new ModelAndView();
				EAPView view = new EAPView() {

					@Override
					public void render(Map<String, ?> model,
							HttpServletRequest request,
							HttpServletResponse response) throws Exception {
						response.setCharacterEncoding("UTF-8");
						response.addHeader("Content-Disposition",
								"attachment;filename=Asset.xls");
						ExcelHelper excelHelper = new ExcelHelper();
						List dataList = (List) data;
						excelHelper.writeData(response.getOutputStream(),
								AssetVo.class, dataList);
						// response.getOutputStream().write("abc".getBytes());
					}

					@Override
					public String getContentType() {
						return "application/vnd.ms-excel";
					}
				};

				view.setData(data);
				modelAndView.setView(view);

				return modelAndView;
			}

		});
	}

}
