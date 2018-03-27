package com.glens.pwCloudOs.fm.invoicebase.web;

import java.net.URLEncoder;
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
import com.glens.eap.platform.framework.utils.excel.ExcelHelper;
import com.glens.eap.platform.framework.web.EAPJsonAbstractController;
import com.glens.eap.platform.framework.web.ResponseConstants;
import com.glens.eap.platform.framework.web.support.JsonProcessRequestHandler;
import com.glens.eap.platform.framework.web.support.response.KeyResult;
import com.glens.eap.platform.framework.web.support.response.PageBeanResult;
import com.glens.eap.platform.framework.web.support.response.ResponseResult;
import com.glens.pwCloudOs.fm.invoicebase.service.InvoiceBaseService;
import com.glens.pwCloudOs.fm.invoicebase.vo.InvoiceBaseVo;

@FormProcessor(clazz = "com.glens.pwCloudOs.fm.invoicebase.web.InvoiceBaseForm")
@RequestMapping("pmsServices/fm/invoiceBase/invoiceBase")
public class InvoiceBaseController extends EAPJsonAbstractController {

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
				int iCount = getService().update(actionForm.toVo());

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

	/**
	 * 生成开票明细
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "generateKp", method = RequestMethod.POST)
	public Object generateKp(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				InvoiceBaseForm form = (InvoiceBaseForm) actionForm;
				InvoiceBaseService service = (InvoiceBaseService) getService();
				KeyResult result = new KeyResult();
				try {
					boolean ok = service.generateKp(form);
					result.setStatusCode(ResponseConstants.OK);
					result.setResultMsg("成功");
					result.setGenerateKey(actionForm.getGenerateKey());
				} catch (Exception e) {
					e.printStackTrace();
					result.setStatusCode(ResponseConstants.SERVER_ERROR);
					result.setResultMsg("失败");
				}

				return result;
			}

		});
	}

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
				boolean ok = getService().insert(actionForm.toVo());
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
	@RequestMapping(value = "list", method = RequestMethod.GET)
	public Object list(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub
				InvoiceBaseService ser = (InvoiceBaseService) getService();
				InvoiceBaseForm form = (InvoiceBaseForm) actionForm;
				PageBean page = ser.queryForPage(form.toMap(),
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

	/**
	 * 导出excel
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "export", method = RequestMethod.GET)
	public Object export(HttpServletRequest request,
			HttpServletResponse response) {

		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				InvoiceBaseService ser = (InvoiceBaseService) getService();
				InvoiceBaseForm form = (InvoiceBaseForm) actionForm;
				List data = ser.queryForList(form.toMap());
				return data;
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
						String fileName = URLEncoder.encode("开票明细", "UTF-8");

						response.setContentType("application/ms-excel;charset=UTF-8");
						response.addHeader("Content-Disposition",
								"attachment;filename=" + fileName
										+ ".xlsx;charset=UTF-8");
						ExcelHelper excelHelper = new ExcelHelper();
						excelHelper.setSheetName("开票明细");
						List dataList = (List) data;
						excelHelper.writeData(response.getOutputStream(),
								InvoiceBaseVo.class, dataList);
					}

					@Override
					public String getContentType() {
						return "application/vnd.ms-excel;charset=UTF-8";
					}
				};

				view.setData(data);
				modelAndView.setView(view);

				return modelAndView;
			}

		});

	}

	@RequestMapping(value = "getTypeList", method = RequestMethod.GET)
	public Object getTypeList(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub
				InvoiceBaseService ser = (InvoiceBaseService) getService();
				// InvoiceBaseForm form=(InvoiceBaseForm)actionForm;
				List<Map<String, String>> list = ser.getInvoiceType();
				PageBeanResult result = new PageBeanResult();
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

	
	@RequestMapping(value = "getInvoiceNosByProno", method = RequestMethod.GET)
	public Object getInvoiceNosByProno(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub
				InvoiceBaseService ser = (InvoiceBaseService) getService();
				// InvoiceBaseForm form=(InvoiceBaseForm)actionForm;
				List<Map<String, String>> list = ser.getInvoiceNosByProno(actionForm.toMap());
				PageBeanResult result = new PageBeanResult();
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
	
	@RequestMapping(value = "getinvoiceList", method = RequestMethod.GET)
	public Object getinvoiceList(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub
				InvoiceBaseService ser = (InvoiceBaseService) getService();
				// InvoiceBaseForm form=(InvoiceBaseForm)actionForm;
				List<Map<String, String>> list = ser.getinvoiceList(actionForm
						.toVo());
				PageBeanResult result = new PageBeanResult();
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

	/**
	 * 已认领过的发票不再显示
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "getinvoiceRepayList", method = RequestMethod.GET)
	public Object getinvoiceRepayList(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub
				InvoiceBaseService ser = (InvoiceBaseService) getService();
				// InvoiceBaseForm form=(InvoiceBaseForm)actionForm;
				List<Map<String, String>> list = ser
						.getinvoiceRepayList(actionForm.toVo());
				
				PageBeanResult result = new PageBeanResult();
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
}
