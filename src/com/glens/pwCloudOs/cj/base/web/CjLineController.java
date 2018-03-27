package com.glens.pwCloudOs.cj.base.web;

import java.math.BigDecimal;
import java.util.Date;
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
import com.glens.eap.platform.framework.utils.excel.ExcelHelper;
import com.glens.eap.platform.framework.web.EAPJsonAbstractController;
import com.glens.eap.platform.framework.web.ResponseConstants;
import com.glens.eap.platform.framework.web.support.JsonProcessRequestHandler;
import com.glens.eap.platform.framework.web.support.response.KeyResult;
import com.glens.eap.platform.framework.web.support.response.ListResult;
import com.glens.pwCloudOs.cj.base.service.CjBdzService;
import com.glens.pwCloudOs.cj.base.service.CjLineService;
import com.glens.pwCloudOs.cj.base.vo.CjBdz;
import com.glens.pwCloudOs.cj.base.vo.CjLine;
import com.glens.pwCloudOs.cj.utils.ExcelUtil;

@FormProcessor(clazz = "com.glens.pwCloudOs.cj.base.web.CjLineForm")
@RequestMapping("pmsServices/cj/base/cjLine")
public class CjLineController extends EAPJsonAbstractController {

	@RequestMapping(method = RequestMethod.POST, value = "queryCjLineForPage")
	public Object queryCjLineForPage(HttpServletRequest request,
			HttpServletResponse response) {
		return this.process(request, response, new JsonProcessRequestHandler() {
			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				CjLineService service = (CjLineService) getService();
				CjLineForm form = (CjLineForm) actionForm;
				PageBean page = service.queryCjLineForPage(form);
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

	@RequestMapping(value = "queryCjLineList", method = RequestMethod.POST)
	public Object queryCjLineList(HttpServletRequest request,
			HttpServletResponse response) {
		return this.process(request, response, new JsonProcessRequestHandler() {
			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				CjLineService service = (CjLineService) getService();
				CjLineForm form = (CjLineForm) actionForm;
				List list = service.queryCjLineList(form);
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

	@RequestMapping(value = "queryCjLineById", method = RequestMethod.POST)
	public Object queryCjByqById(HttpServletRequest request,
			HttpServletResponse response) {
		return this.process(request, response, new JsonProcessRequestHandler() {
			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				CjLineService service = (CjLineService) getService();
				CjLineForm form = (CjLineForm) actionForm;
				CjLine cjLine = (CjLine) service.findById(form.getCollectId());
				Map result = new HashMap();
				result.put("statusCode", ResponseConstants.OK);
				result.put("resultMsg", "返回结果成功");
				result.put("cjLine", cjLine != null ? cjLine : "{}");
				return result;
			}
		});
	}

	@Override
	@RequestMapping(method = RequestMethod.POST, value = "add")
	public Object insert(HttpServletRequest request,
			HttpServletResponse response) {
		return this.process(request, response, new JsonProcessRequestHandler() {
			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				CjLineService service = (CjLineService) getService();
				CjLineForm form = (CjLineForm) actionForm;
				CjLine cjLine = (CjLine) form.toVo();
				Map result = service.saveCjLine(cjLine);
				return result;
			}
		});
	}

	@Override
	@RequestMapping(value = "update", method = RequestMethod.POST)
	public Object update(HttpServletRequest request,
			HttpServletResponse response) {
		return this.process(request, response, new JsonProcessRequestHandler() {
			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				CjLineService service = (CjLineService) getService();
				CjLineForm form = (CjLineForm) actionForm;
				CjLine cjLine = (CjLine) form.toVo();
				Map result = service.updateCjLine(cjLine);
				return result;
			}
		});
	}

	@RequestMapping(value = "delect", method = RequestMethod.POST)
	public Object delect(HttpServletRequest request,
			HttpServletResponse response) {
		return this.process(request, response, new JsonProcessRequestHandler() {
			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				CjLineService service = (CjLineService) getService();
				CjLineForm form = (CjLineForm) actionForm;
				CjLine cjLine = (CjLine) form.toVo();
				Map result = service.delCjLine(cjLine);
				return result;
			}
		});
	}

	@RequestMapping(value = "importExcel", method = RequestMethod.POST)
	public Object importExcel(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {

				CjLineForm form = (CjLineForm) actionForm;

				CjLineService service = (CjLineService) getService();

				List resultlist = ExcelUtil.importExcel(form
						.getExcelFile()); 

				int num = 0;
				for (int i = 0; i < resultlist.size(); i++) {

					try {
						Map m = (Map) resultlist.get(i);
						if (m.get("所属变电站") == null||m.get("中压线路") == null||m.get("区域") == null) {
							continue;
						}
						CjLine line = new CjLine();

						line.setBdzAmname(m.get("所属变电站").toString()); 
						line.setAmname(m.get("中压线路").toString());
						line.setCity(m.get("区域").toString());						

						if (m.get("电压等级") != null) {
							line.setVoltagelevel(Math.round(Double.parseDouble( m.get("电压等级").toString())) );
						}
						if (m.get("线路长度") != null) {
							line.setXlcdTz(new BigDecimal(m.get("线路长度").toString()));						 
						}
						 
						if (m.get("投运日期") != null) {
							line.setTyrq(m.get("投运日期").toString());
						}
					 
						line.setSysProcessFlag("1");
						line.setSysCreateTime(new Date());
						Map result = service.saveLine(line);
						if (result.get("statusCode") == "200") {
							num = num + 1;
						}
					} catch (Exception e) {
						e.printStackTrace();

					}

				}

				KeyResult result = new KeyResult();
		 
					result.setStatusCode(ResponseConstants.OK);
					result.setResultMsg("共" + resultlist.size() + "条,成功导入"
							+ num + "条！");
					result.setGenerateKey(actionForm.getGenerateKey());
			 
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
 
				CjLineService service = (CjLineService) getService();
				CjLineForm form = (CjLineForm) actionForm;
				List result = service.queryCjLineList(form);
				
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
								"attachment;filename=Line.xls");
						ExcelHelper excelHelper = new ExcelHelper();
						List dataList = (List) data;
						excelHelper.writeData(response.getOutputStream(),
								CjLine.class, dataList); 
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
