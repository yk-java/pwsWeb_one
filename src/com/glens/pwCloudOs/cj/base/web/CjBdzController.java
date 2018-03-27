package com.glens.pwCloudOs.cj.base.web;

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
import com.glens.pwCloudOs.cj.base.vo.CjBdz;
import com.glens.pwCloudOs.cj.utils.ExcelUtil;

@FormProcessor(clazz = "com.glens.pwCloudOs.cj.base.web.CjBdzForm")
@RequestMapping("pmsServices/cj/base/cjBdz")
public class CjBdzController extends EAPJsonAbstractController {
	 
	
	@RequestMapping(method = RequestMethod.POST, value = "queryCjBdzForPage")
	public Object queryCjBdzForPage(HttpServletRequest request, HttpServletResponse response) {
		return this.process(request, response, new JsonProcessRequestHandler() {
			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				CjBdzService service = (CjBdzService) getService();
				CjBdzForm form = (CjBdzForm) actionForm;
				PageBean page = service.queryCjBdzForPage(form);
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

	@RequestMapping(value = "queryCjBdzList", method = RequestMethod.POST)
	public Object queryCjBdzList(HttpServletRequest request,
			HttpServletResponse response) {
		return this.process(request, response, new JsonProcessRequestHandler() {
			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				CjBdzService service = (CjBdzService) getService();
				CjBdzForm form = (CjBdzForm) actionForm;
				List list = service.queryCjBdzList(form);
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
	
	@RequestMapping(value = "importExcel", method = RequestMethod.POST)
	public Object importExcel(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {

				CjBdzForm form = (CjBdzForm) actionForm;

				CjBdzService service = (CjBdzService) getService();

				List resultlist = ExcelUtil.importExcel(form
						.getExcelFile()); 

				int num = 0;
				for (int i = 0; i < resultlist.size(); i++) {

					try {
						Map m = (Map) resultlist.get(i);
						if (m.get("变电站") == null||m.get("区域") == null) {
							continue;
						}
						CjBdz bdz = new CjBdz();
					 
						bdz.setAmname(m.get("变电站").toString());						 
					 
						bdz.setCity(m.get("区域").toString());
					 
						if (m.get("电压等级") != null) {
							bdz.setVoltagelevel(m.get("电压等级").toString());
						}
						if (m.get("容量") != null) {
							bdz.setRl(m.get("容量").toString());						 
						}
						 
						if (m.get("投运日期") != null) {
							bdz.setTyrq(m.get("投运日期").toString());
						}
						if (m.get("经度") != null) {
							bdz.setJd(m.get("经度").toString());
						}

						 
						if (m.get("纬度") != null) {
							bdz.setWd(m.get("纬度").toString());
						}
						bdz.setSysProcessFlag("1");
						bdz.setSysCreateTime(new Date());
						Map result = service.saveBdz(bdz);
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
 
				CjBdzService service = (CjBdzService) getService();
				CjBdzForm form = (CjBdzForm) actionForm;
				List result = service.queryCjBdzList(form);
				
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
								"attachment;filename=BDZ.xls");
						ExcelHelper excelHelper = new ExcelHelper();
						List dataList = (List) data;
						excelHelper.writeData(response.getOutputStream(),
								CjBdz.class, dataList); 
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
