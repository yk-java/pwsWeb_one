package com.glens.pwCloudOs.cj.base.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.glens.eap.platform.core.annotation.FormProcessor;
import com.glens.eap.platform.core.view.EAPView;
import com.glens.eap.platform.core.web.ControllerForm;
import com.glens.eap.platform.core.web.EAPController;
import com.glens.eap.platform.framework.beans.PageBean;
import com.glens.eap.platform.framework.context.EAPContext;
import com.glens.eap.platform.framework.utils.excel.ExcelHelper;
import com.glens.eap.platform.framework.web.EAPJsonAbstractController;
import com.glens.eap.platform.framework.web.ResponseConstants;
import com.glens.eap.platform.framework.web.support.JsonProcessRequestHandler;
import com.glens.eap.platform.framework.web.support.response.ListResult;
import com.glens.pwCloudOs.cj.base.service.CjBdsService;
import com.glens.pwCloudOs.cj.base.service.CjBdzLineDeviceService;
import com.glens.pwCloudOs.cj.base.service.CjByqService;
import com.glens.pwCloudOs.cj.base.service.CjFzxService;
import com.glens.pwCloudOs.cj.base.service.CjHwgService;
import com.glens.pwCloudOs.cj.base.service.CjKgService;
import com.glens.pwCloudOs.cj.base.service.CjPdsService;
import com.glens.pwCloudOs.cj.base.service.CjPoleService;
import com.glens.pwCloudOs.cj.base.service.CjXsbService;
import com.glens.pwCloudOs.cj.base.service.CjZbService;
import com.glens.pwCloudOs.cj.base.vo.CjBds;
import com.glens.pwCloudOs.cj.base.vo.CjBdzLineDevice;
import com.glens.pwCloudOs.cj.base.vo.CjByq;
import com.glens.pwCloudOs.cj.base.vo.CjFzx;
import com.glens.pwCloudOs.cj.base.vo.CjHwg;
import com.glens.pwCloudOs.cj.base.vo.CjKg;
import com.glens.pwCloudOs.cj.base.vo.CjPds;
import com.glens.pwCloudOs.cj.base.vo.CjPole;
import com.glens.pwCloudOs.cj.base.vo.CjXsb;
import com.glens.pwCloudOs.cj.base.vo.CjZb;

@FormProcessor(clazz = "com.glens.pwCloudOs.cj.base.web.CjBdzLineDeviceForm")
@RequestMapping("pmsServices/cj/base/cjBdzLineDevice")
public class CjBdzLineDeviceController extends EAPJsonAbstractController {

	@RequestMapping(method = RequestMethod.POST, value = "queryCjBdzLineDeviceForPage")
	public Object queryCjBdzLineDeviceForPage(HttpServletRequest request,
			HttpServletResponse response) {
		return this.process(request, response, new JsonProcessRequestHandler() {
			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				CjBdzLineDeviceService service = (CjBdzLineDeviceService) getService();
				CjBdzLineDeviceForm form = (CjBdzLineDeviceForm) actionForm;
				PageBean page = service.queryCjBdzLineDeviceForPage(form);
				Map result = new HashMap();
				result.put("statusCode", ResponseConstants.OK);
				result.put("resultMsg", "返回结果成功");
				result.put("currentPage", page.getCurrentPage());
				result.put("perPage", page.getPerpage());
				result.put("totalPage", page.getTotalPage());
				result.put("totalRecord", page.getTotalRecord());
				result.put("list", page.getList());
				return result;
			}
		});
	}

	@RequestMapping(value = "queryCjBdzLineDeviceList", method = RequestMethod.POST)
	public Object queryCjBdzLineDeviceList(HttpServletRequest request,
			HttpServletResponse response) {
		return this.process(request, response, new JsonProcessRequestHandler() {
			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				CjBdzLineDeviceService service = (CjBdzLineDeviceService) getService();
				CjBdzLineDeviceForm form = (CjBdzLineDeviceForm) actionForm;
				List list = service.queryCjBdzLineDeviceList(form);
				ListResult result = new ListResult();
				if (list != null) {
					result.setStatusCode(ResponseConstants.OK);
					result.setResultMsg("获取成功");
					result.setList(list);
				} else {
					result.setStatusCode(ResponseConstants.NO_DATA);
					result.setResultMsg("没有获取到数据");
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
				CjBdzLineDeviceForm form = (CjBdzLineDeviceForm) actionForm;
				String type = form.getType();
				List result = null;
				if ("".equals(type)) {
					CjBdzLineDeviceService service = (CjBdzLineDeviceService) getService();
					result = service.queryCjBdzLineDeviceList(form);
				}
				// 杆塔
				if ("2".equals(type)) {
					CjPoleService service = (CjPoleService) EAPContext
							.getContext().getBean("cjPoleService");
					CjPoleForm poleForm = new CjPoleForm();
					poleForm.setXlAmname(form.getXlAmname());
					poleForm.setCity(form.getCity());
					result = service.queryCjPoleList(poleForm);
				}
				// 配变变压器
				if ("9".equals(type)) {
					CjByqService service = (CjByqService) EAPContext
							.getContext().getBean("cjByqService");
					CjByqForm byqForm = new CjByqForm();
					byqForm.setXlAmname(form.getXlAmname());
					byqForm.setCity(form.getCity());
					result = service.queryCjByqList(byqForm);
				}
				// 分支箱
				if ("18".equals(type)) {
					CjFzxService service = (CjFzxService) EAPContext
							.getContext().getBean("cjFzxService");
					CjFzxForm fzxForm = new CjFzxForm();
					fzxForm.setXlAmname(form.getXlAmname());
					fzxForm.setCity(form.getCity());
					result = service.queryCjFzxList(fzxForm);
				}
				// 环网柜
				if ("10".equals(type)) {
					CjHwgService service = (CjHwgService) EAPContext
							.getContext().getBean("cjHwgService");
					CjHwgForm hwgForm = new CjHwgForm();
					hwgForm.setXlAmname(form.getXlAmname());
					hwgForm.setCity(form.getCity());
					result = service.queryCjHwgList(hwgForm);
				}
				// 开关
				if ("14".equals(type)) {
					CjKgService service = (CjKgService) EAPContext.getContext()
							.getBean("cjKgService");
					CjKgForm kgForm = new CjKgForm();
					kgForm.setXlAmname(form.getXlAmname());
					kgForm.setCity(form.getCity());
					result = service.queryCjKgList(kgForm);
				}
				// 变电所
				if ("7".equals(type)) {
					CjBdsService service = (CjBdsService) EAPContext
							.getContext().getBean("cjBdsService");
					CjBdsForm bdsForm = new CjBdsForm();
					bdsForm.setXlAmname(form.getXlAmname());
					bdsForm.setCity(form.getCity());
					result = service.queryCjBdsList(bdsForm);
				}
				// 配电所
				if ("11".equals(type)) {
					CjPdsService service = (CjPdsService) EAPContext
							.getContext().getBean("cjPdsService");
					CjPdsForm pdsForm = new CjPdsForm();
					pdsForm.setXlAmname(form.getXlAmname());
					pdsForm.setCity(form.getCity());
					result = service.queryCjPdsList(pdsForm);
				}
				// 箱式变
				if ("8".equals(type)) {
					CjXsbService service = (CjXsbService) EAPContext
							.getContext().getBean("cjXsbService");
					CjXsbForm xsbForm = new CjXsbForm();
					xsbForm.setXlAmname(form.getXlAmname());
					xsbForm.setCity(form.getCity());
					result = service.queryCjXsbList(xsbForm);
				}
				// 专变
				if ("21".equals(type)) {
					CjZbService service = (CjZbService) EAPContext.getContext()
							.getBean("cjZbService");
					CjZbForm zbForm = new CjZbForm();
					zbForm.setXlAmname(form.getXlAmname());
					zbForm.setCity(form.getCity());
					result = service.queryCjZbList(zbForm);
				}

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
						String type = request.getParameter("type");
						String exportType = request.getParameter("exportType");

						if (exportType == null || exportType.isEmpty()) {
							exportType = "BdzLineDevice";
						}

						response.setCharacterEncoding("UTF-8");
						response.addHeader("Content-Disposition",
								"attachment;filename=" + exportType + ".xls");
						ExcelHelper excelHelper = new ExcelHelper();
						List dataList = (List) data;

						if ("".equals(type)) {
							excelHelper.writeData(response.getOutputStream(),
									CjBdzLineDevice.class, dataList);
						}
						// 杆塔
						if ("2".equals(type)) {
							excelHelper.writeData(response.getOutputStream(),
									CjPole.class, dataList);
						}
						// 配变变压器
						if ("9".equals(type)) {
							excelHelper.writeData(response.getOutputStream(),
									CjByq.class, dataList);
						}
						// 分支箱
						if ("18".equals(type)) {
							excelHelper.writeData(response.getOutputStream(),
									CjFzx.class, dataList);
						}
						// 环网柜
						if ("10".equals(type)) {
							excelHelper.writeData(response.getOutputStream(),
									CjHwg.class, dataList);
						}
						// 开关
						if ("14".equals(type)) {
							excelHelper.writeData(response.getOutputStream(),
									CjKg.class, dataList);
						}
						// 变电所
						if ("7".equals(type)) {
							excelHelper.writeData(response.getOutputStream(),
									CjBds.class, dataList);
						}
						// 配电所
						if ("11".equals(type)) {
							excelHelper.writeData(response.getOutputStream(),
									CjPds.class, dataList);
						}
						// 箱式变
						if ("8".equals(type)) {
							excelHelper.writeData(response.getOutputStream(),
									CjXsb.class, dataList);
						}
						// 专变
						if ("21".equals(type)) {
							excelHelper.writeData(response.getOutputStream(),
									CjZb.class, dataList);
						}
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
