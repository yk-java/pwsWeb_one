package com.glens.pwCloudOs.goods.web;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.glens.eap.platform.core.annotation.FormProcessor;
import com.glens.eap.platform.core.utils.DateTimeUtil;
import com.glens.eap.platform.core.utils.StringUtil;
import com.glens.eap.platform.core.view.EAPView;
import com.glens.eap.platform.core.web.ControllerForm;
import com.glens.eap.platform.core.web.EAPController;
import com.glens.eap.platform.framework.beans.PageBean;
import com.glens.eap.platform.framework.beans.UserToken;
import com.glens.eap.platform.framework.codeCenter.CodeWorker;
import com.glens.eap.platform.framework.context.EAPContext;
import com.glens.eap.platform.framework.utils.excel.ExcelHelper;
import com.glens.eap.platform.framework.web.EAPJsonAbstractController;
import com.glens.eap.platform.framework.web.ResponseConstants;
import com.glens.eap.platform.framework.web.support.JsonProcessRequestHandler;
import com.glens.eap.platform.framework.web.support.response.BeanResult;
import com.glens.eap.platform.framework.web.support.response.ListResult;
import com.glens.eap.platform.framework.web.support.response.PageBeanResult;
import com.glens.eap.platform.framework.web.support.response.ResponseResult;
import com.glens.pwCloudOs.common.context.PwCloudOsConstant;
import com.glens.pwCloudOs.goods.service.GoodsService;
import com.glens.pwCloudOs.materielMg.goods.web.GoodsForm;

@FormProcessor(clazz = "com.glens.pwCloudOs.goods.web.GoodsStockForm")
@RequestMapping("pmsServices/goods")
public class GoodsController extends EAPJsonAbstractController {

	/**
	 * 得到物资出入库流程列表
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "getProcessList", method = RequestMethod.GET)
	public Object getProcessList(HttpServletRequest request,
			HttpServletResponse response) {

		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub

				UserToken token = (UserToken) request.getSession()
						.getAttribute(UserToken.KEY_TOKEN);

				GoodsStockForm form = (GoodsStockForm) actionForm;

				form.setUserCode(token.getEmployeeCode());

				GoodsService ser = (GoodsService) getService();
				PageBean page = ser.getProcessList(form.toMap(),
						actionForm.getCurrentPage(), actionForm.getPerpage());
				PageBeanResult result = new PageBeanResult();
				if (page != null) {

					result.setResultMsg("获取成功");
					result.setStatusCode(ResponseConstants.OK);
					result.setPageBean(page);
				} else {
					result.setResultMsg("获取数据失败");
					result.setStatusCode(ResponseConstants.SERVER_ERROR);
				}

				return result;
			}
		});
	}

	/**
	 * 得到废料出入库 列表
	 * 
	 * @param request
	 * @param response
	 * @return
	 */

	@RequestMapping(value = "getWasteProcessPage", method = RequestMethod.GET)
	public Object getWasteProcessPage(HttpServletRequest request,
			HttpServletResponse response) {

		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub

				UserToken token = (UserToken) request.getSession()
						.getAttribute(UserToken.KEY_TOKEN);

				GoodsStockForm form = (GoodsStockForm) actionForm;

				form.setUserCode(token.getEmployeeCode());

				GoodsService ser = (GoodsService) getService();
				PageBean page = ser.getWasteProcessPage(form.toMap(),
						actionForm.getCurrentPage(), actionForm.getPerpage());
				PageBeanResult result = new PageBeanResult();
				if (page != null) {

					result.setResultMsg("获取成功");
					result.setStatusCode(ResponseConstants.OK);
					result.setPageBean(page);
				} else {
					result.setResultMsg("获取数据失败");
					result.setStatusCode(ResponseConstants.SERVER_ERROR);
				}

				return result;
			}
		});
	}

	/**
	 * 得到废料出入库 列表
	 * 
	 * @param request
	 * @param response
	 * @return
	 */

	@RequestMapping(value = "getMasteCheckPage", method = RequestMethod.GET)
	public Object getMasteCheckPage(HttpServletRequest request,
			HttpServletResponse response) {

		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub

				UserToken token = (UserToken) request.getSession()
						.getAttribute(UserToken.KEY_TOKEN);

				GoodsStockForm form = (GoodsStockForm) actionForm;

				form.setUserCode(token.getEmployeeCode());

				GoodsService ser = (GoodsService) getService();
				PageBean page = ser.getMasteCheckPage(form.toMap(),
						actionForm.getCurrentPage(), actionForm.getPerpage());
				PageBeanResult result = new PageBeanResult();
				if (page != null) {

					result.setResultMsg("获取成功");
					result.setStatusCode(ResponseConstants.OK);
					result.setPageBean(page);
				} else {
					result.setResultMsg("获取数据失败");
					result.setStatusCode(ResponseConstants.SERVER_ERROR);
				}

				return result;
			}
		});
	}

	/**
	 * 获取项目类别
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "getProCategory", method = RequestMethod.GET)
	public Object getProCategory(HttpServletRequest request,
			HttpServletResponse response) {

		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub

				UserToken token = (UserToken) request.getSession()
						.getAttribute(UserToken.KEY_TOKEN);

				GoodsStockForm form = (GoodsStockForm) actionForm;

				form.setUserCode(token.getEmployeeCode());

				GoodsService ser = (GoodsService) getService();
				List list = ser.getProCategory(form.toMap());
				ListResult result = new ListResult();
				if (list != null) {

					result.setResultMsg("获取成功");
					result.setStatusCode(ResponseConstants.OK);
					result.setList(list);
				} else {
					result.setResultMsg("获取数据失败");
					result.setStatusCode(ResponseConstants.SERVER_ERROR);
				}

				return result;
			}
		});
	}

	/**
	 * 获取项目
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "getPros", method = RequestMethod.GET)
	public Object getPros(HttpServletRequest request,
			HttpServletResponse response) {

		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub

				UserToken token = (UserToken) request.getSession()
						.getAttribute(UserToken.KEY_TOKEN);

				GoodsStockForm form = (GoodsStockForm) actionForm;

				form.setUserCode(token.getEmployeeCode());

				GoodsService ser = (GoodsService) getService();
				List list = ser.getPros(form.toMap());
				ListResult result = new ListResult();
				if (list != null) {

					result.setResultMsg("获取成功");
					result.setStatusCode(ResponseConstants.OK);
					result.setList(list);
				} else {
					result.setResultMsg("获取数据失败");
					result.setStatusCode(ResponseConstants.SERVER_ERROR);
				}

				return result;
			}
		});
	}

	/**
	 * 获取审核列表
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "getCheckPage", method = RequestMethod.GET)
	public Object getCheckPage(HttpServletRequest request,
			HttpServletResponse response) {

		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub

				UserToken token = (UserToken) request.getSession()
						.getAttribute(UserToken.KEY_TOKEN);

				GoodsStockForm form = (GoodsStockForm) actionForm;

				form.setPmanager(token.getEmployeeCode());

				GoodsService ser = (GoodsService) getService();
				PageBean page = ser.getCheckPage(actionForm.toMap(),
						actionForm.getCurrentPage(), actionForm.getPerpage());
				PageBeanResult result = new PageBeanResult();
				if (page != null) {

					result.setResultMsg("获取成功");
					result.setStatusCode(ResponseConstants.OK);
					result.setPageBean(page);
				} else {
					result.setResultMsg("获取数据失败");
					result.setStatusCode(ResponseConstants.SERVER_ERROR);
				}

				return result;
			}
		});
	}

	/**
	 * 新增物资入库
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "addGoodsProcess", method = RequestMethod.POST)
	public Object addGoodsProcess(HttpServletRequest request,
			HttpServletResponse response) {

		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub
				GoodsService ser = (GoodsService) getService();

				CodeWorker codeWorker = (CodeWorker) EAPContext.getContext()
						.getBean(CodeWorker.SIMPLE_CODE_WORKER);
				String processCode = codeWorker.createCode("P");

				GoodsStockForm form = (GoodsStockForm) actionForm;
				form.setProcessCode(processCode);
				boolean icount = false;
				try {
					icount = ser.addGoodsProcess(form.toMap(),
							getRootPath(request));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				ResponseResult result = new ResponseResult();
				if (icount) {

					result.setResultMsg("新增成功");
					result.setStatusCode(ResponseConstants.OK);
				} else {

					result.setResultMsg("新增失败");
					result.setStatusCode(ResponseConstants.INSERT_DATA_ERROR);
				}

				return result;
			}
		});
	}

	/**
	 * 物资入库流程修改
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "updateGoodsProcess", method = RequestMethod.POST)
	public Object updateGoodsProcess(HttpServletRequest request,
			HttpServletResponse response) {

		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub
				GoodsService ser = (GoodsService) getService();

				boolean icount = false;
				try {
					icount = ser.updateGoodsProcess(actionForm.toMap(),
							getRootPath(request));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				ResponseResult result = new ResponseResult();
				if (icount) {

					result.setResultMsg("修改成功");
					result.setStatusCode(ResponseConstants.OK);
				} else {

					result.setResultMsg("修改失败");
					result.setStatusCode(ResponseConstants.INSERT_DATA_ERROR);
				}

				return result;
			}
		});
	}

	/**
	 * 废料入库流程修改
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "updateWasteProcess", method = RequestMethod.POST)
	public Object updateWasteProcess(HttpServletRequest request,
			HttpServletResponse response) {

		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub
				GoodsService ser = (GoodsService) getService();

				boolean icount = false;
				try {
					icount = ser.updateWasteProcess(actionForm.toMap(),
							getRootPath(request));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				ResponseResult result = new ResponseResult();
				if (icount) {

					result.setResultMsg("修改成功");
					result.setStatusCode(ResponseConstants.OK);
				} else {

					result.setResultMsg("修改失败");
					result.setStatusCode(ResponseConstants.INSERT_DATA_ERROR);
				}

				return result;
			}
		});
	}

	/**
	 * 物资入库审核流程
	 * 
	 * @param request
	 * @param response
	 * @return
	 */

	@RequestMapping(value = "checkProcess", method = RequestMethod.POST)
	public Object checkProcess(HttpServletRequest request,
			HttpServletResponse response) {

		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub
				GoodsService ser = (GoodsService) getService();

				boolean icount = false;

				icount = ser.checkProcess(actionForm.toMap());

				ResponseResult result = new ResponseResult();
				if (icount) {

					result.setResultMsg("审核成功");
					result.setStatusCode(ResponseConstants.OK);
				} else {

					result.setResultMsg("审核失败");
					result.setStatusCode(ResponseConstants.INSERT_DATA_ERROR);
				}

				return result;
			}
		});
	}

	/**
	 * 废料入库审核流程
	 * 
	 * @param request
	 * @param response
	 * @return
	 */

	@RequestMapping(value = "wasteCheckProcess", method = RequestMethod.POST)
	public Object wasteCheckProcess(HttpServletRequest request,
			HttpServletResponse response) {

		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub
				GoodsService ser = (GoodsService) getService();

				boolean icount = false;

				icount = ser.wasteCheckProcess(actionForm.toMap());

				ResponseResult result = new ResponseResult();
				if (icount) {

					result.setResultMsg("审核成功");
					result.setStatusCode(ResponseConstants.OK);
				} else {

					result.setResultMsg("审核失败");
					result.setStatusCode(ResponseConstants.INSERT_DATA_ERROR);
				}

				return result;
			}
		});
	}

	/**
	 * 获取物品类型
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "getGoodsTypes", method = RequestMethod.GET)
	public Object getGoodsTypes(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub

				GoodsService ser = (GoodsService) getService();

				List list = ser.getGoodsTypes(actionForm.toMap());

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

	/**
	 * 根据物品获取单位
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "getGoodsUnit", method = RequestMethod.GET)
	public Object getGoodsUnit(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub

				GoodsService ser = (GoodsService) getService();
				List list = ser.getGoodsUnit(actionForm.toMap());

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

	/**
	 * 获取仓库
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "getStores", method = RequestMethod.GET)
	public Object getStores(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub

				GoodsService ser = (GoodsService) getService();
				List list = ser.getStores(actionForm.toMap());

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

	/**
	 * 根据processCode 获取流程详信息
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "getGoodsProcessDetail", method = RequestMethod.GET)
	public Object getGoodsProcessDetail(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub

				GoodsService ser = (GoodsService) getService();
				Map map = ser.getGoodsProcessDetail(actionForm.toMap());

				BeanResult result = new BeanResult();
				if (map != null) {

					result.setStatusCode(ResponseConstants.OK);
					result.setResultMsg("获取成功");
					result.setData(map);
				} else {

					result.setStatusCode(ResponseConstants.NO_DATA);
					result.setResultMsg("没有获取到数据");
				}
				return result;
			}

		});
	}

	/**
	 * 根据processCode 获取废料详情
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "getWasteProcessDetail", method = RequestMethod.GET)
	public Object getWasteProcessDetail(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub

				GoodsService ser = (GoodsService) getService();
				Map map = ser.getWasteProcessDetail(actionForm.toMap());

				BeanResult result = new BeanResult();
				if (map != null) {

					result.setStatusCode(ResponseConstants.OK);
					result.setResultMsg("获取成功");
					result.setData(map);
				} else {

					result.setStatusCode(ResponseConstants.NO_DATA);
					result.setResultMsg("没有获取到数据");
				}
				return result;
			}

		});
	}

	/**
	 * 废料种类新增
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "addCategory", method = RequestMethod.POST)
	public Object addCategory(HttpServletRequest request,
			HttpServletResponse response) {

		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub
				GoodsService ser = (GoodsService) getService();

				CodeWorker codeWorker = (CodeWorker) EAPContext.getContext()
						.getBean(CodeWorker.SIMPLE_CODE_WORKER);
				String catecode = codeWorker.createCode("C");
				// String wasteCode= codeWorker.createCode("S");
				GoodsStockForm form = (GoodsStockForm) actionForm;

				boolean icount = false;
				form.setCategoryCode(catecode);
				// form.setStockCode(wasteCode);
				icount = ser.addCategory(form.toMap());

				ResponseResult result = new ResponseResult();
				if (icount) {

					result.setResultMsg("新增成功");
					result.setStatusCode(ResponseConstants.OK);
				} else {

					result.setResultMsg("新增失败");
					result.setStatusCode(ResponseConstants.INSERT_DATA_ERROR);
				}

				return result;
			}
		});
	}

	/**
	 * /** 获取废料种类
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "getWastTypes", method = RequestMethod.GET)
	public Object getWastTypes(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub

				GoodsService ser = (GoodsService) getService();
				List list = ser.getWastTypes(actionForm.toMap());

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

	/**
	 * 新增废料入库
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "addWasteProcess", method = RequestMethod.POST)
	public Object addWasteProcess(HttpServletRequest request,
			HttpServletResponse response) {

		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub
				GoodsService ser = (GoodsService) getService();

				CodeWorker codeWorker = (CodeWorker) EAPContext.getContext()
						.getBean(CodeWorker.SIMPLE_CODE_WORKER);
				String processCode = codeWorker.createCode("P");

				GoodsStockForm form = (GoodsStockForm) actionForm;
				form.setProcessCode(processCode);
				boolean icount = false;
				try {
					icount = ser.addWasteProcess(form.toMap(),
							getRootPath(request));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				ResponseResult result = new ResponseResult();
				if (icount) {
					result.setResultMsg("新增成功");
					result.setStatusCode(ResponseConstants.OK);
				} else {

					result.setResultMsg("新增失败");
					result.setStatusCode(ResponseConstants.INSERT_DATA_ERROR);
				}

				return result;
			}
		});
	}

	/**
	 * 物资库存查询
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "getGoodsStocks", method = RequestMethod.GET)
	public Object getGoodsStocks(HttpServletRequest request,
			HttpServletResponse response) {

		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub

				GoodsStockForm form = (GoodsStockForm) actionForm;
				GoodsService ser = (GoodsService) getService();
				PageBean page = ser.getGoodsStocks(form.toMap(),
						actionForm.getCurrentPage(), actionForm.getPerpage());
				PageBeanResult result = new PageBeanResult();
				if (page != null) {

					result.setResultMsg("获取成功");
					result.setStatusCode(ResponseConstants.OK);
					result.setPageBean(page);
				} else {
					result.setResultMsg("获取数据失败");
					result.setStatusCode(ResponseConstants.SERVER_ERROR);
				}

				return result;
			}
		});
	}

	/**
	 * 废料库存查询
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "getWasteStocks", method = RequestMethod.GET)
	public Object getWasteStocks(HttpServletRequest request,
			HttpServletResponse response) {

		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub

				GoodsStockForm form = (GoodsStockForm) actionForm;
				GoodsService ser = (GoodsService) getService();
				PageBean page = ser.getWasteStocks(form.toMap(),
						actionForm.getCurrentPage(), actionForm.getPerpage());
				PageBeanResult result = new PageBeanResult();
				if (page != null) {

					result.setResultMsg("获取成功");
					result.setStatusCode(ResponseConstants.OK);
					result.setPageBean(page);
				} else {
					result.setResultMsg("获取数据失败");
					result.setStatusCode(ResponseConstants.SERVER_ERROR);
				}

				return result;
			}
		});
	}

	/**
	 * 查询获取物资流程
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "searchGoodsProcess", method = RequestMethod.GET)
	public Object searchGoodsProcess(HttpServletRequest request,
			HttpServletResponse response) {

		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub

				GoodsStockForm form = (GoodsStockForm) actionForm;
				GoodsService ser = (GoodsService) getService();
				PageBean page = ser.searchGoodsProcess(form.toMap(),
						actionForm.getCurrentPage(), actionForm.getPerpage());
				PageBeanResult result = new PageBeanResult();
				if (page != null) {

					result.setResultMsg("获取成功");
					result.setStatusCode(ResponseConstants.OK);
					result.setPageBean(page);
				} else {
					result.setResultMsg("获取数据失败");
					result.setStatusCode(ResponseConstants.SERVER_ERROR);
				}

				return result;
			}
		});
	}

	/**
	 * 导出 物资出入库流程
	 */

	@RequestMapping(value = "exportGoodsProcess", method = RequestMethod.GET)
	public Object exportGoodsProcess(HttpServletRequest request,
			HttpServletResponse response) {

		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {

				GoodsStockForm form = (GoodsStockForm) actionForm;
				GoodsService ser = (GoodsService) getService();
				List list = ser.searchGoodsProcessList(form.toMap());
				return list;
			}

			@Override
			public Object doWithFinish(HttpServletRequest request,
					HttpServletResponse response, Object data, String viewType,
					EAPController controller) {
				// TODO Auto-generated method stub

				ModelAndView modelAndView = new ModelAndView();
				EAPView view = new EAPView() {

					@Override
					public void render(Map<String, ?> model,
							HttpServletRequest request,
							HttpServletResponse response) throws Exception {
						response.setCharacterEncoding("UTF-8");
						String fileName = URLEncoder
								.encode("物资出入库流程）", "UTF-8");

						response.setContentType("application/ms-excel;charset=UTF-8");
						response.addHeader("Content-Disposition",
								"attachment;filename=" + fileName
										+ ".xlsx;charset=UTF-8");
						ExcelHelper excelHelper = new ExcelHelper();
						excelHelper.setSheetName("物资出入库流程");
						List<Map<String, Object>> dataList = (List<Map<String, Object>>) data;
						excelHelper
								.writeData("goodsProcessListExport",
										response.getOutputStream(), Map.class,
										dataList);

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

	/**
	 * 废料出入库流程
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "exportWasteProcess", method = RequestMethod.GET)
	public Object exportWasteProcess(HttpServletRequest request,
			HttpServletResponse response) {

		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {

				GoodsStockForm form = (GoodsStockForm) actionForm;
				GoodsService ser = (GoodsService) getService();
				List list = ser.exportWasteProcess(form.toMap());
				return list;
			}

			@Override
			public Object doWithFinish(HttpServletRequest request,
					HttpServletResponse response, Object data, String viewType,
					EAPController controller) {
				// TODO Auto-generated method stub

				ModelAndView modelAndView = new ModelAndView();
				EAPView view = new EAPView() {

					@Override
					public void render(Map<String, ?> model,
							HttpServletRequest request,
							HttpServletResponse response) throws Exception {
						response.setCharacterEncoding("UTF-8");
						String fileName = URLEncoder.encode("废料出入库流程", "UTF-8");

						response.setContentType("application/ms-excel;charset=UTF-8");
						response.addHeader("Content-Disposition",
								"attachment;filename=" + fileName
										+ ".xlsx;charset=UTF-8");
						ExcelHelper excelHelper = new ExcelHelper();
						excelHelper.setSheetName("废料出入库流程");
						List<Map<String, Object>> dataList = (List<Map<String, Object>>) data;
						excelHelper.writeData("exportWasteProcess",
								response.getOutputStream(), Map.class, dataList);

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

	/**
	 * 导出物资库存
	 * 
	 * @param request
	 * @param response
	 * @return
	 */

	@RequestMapping(value = "exportGoodsStocks", method = RequestMethod.GET)
	public Object exportGoodsStocks(HttpServletRequest request,
			HttpServletResponse response) {

		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {

				GoodsStockForm form = (GoodsStockForm) actionForm;
				GoodsService ser = (GoodsService) getService();
				List list = ser.exportGoodsStocks(form.toMap());
				return list;
			}

			@Override
			public Object doWithFinish(HttpServletRequest request,
					HttpServletResponse response, Object data, String viewType,
					EAPController controller) {
				// TODO Auto-generated method stub

				ModelAndView modelAndView = new ModelAndView();
				EAPView view = new EAPView() {

					@Override
					public void render(Map<String, ?> model,
							HttpServletRequest request,
							HttpServletResponse response) throws Exception {
						response.setCharacterEncoding("UTF-8");
						String fileName = URLEncoder.encode("物资库存列表", "UTF-8");

						response.setContentType("application/ms-excel;charset=UTF-8");
						response.addHeader("Content-Disposition",
								"attachment;filename=" + fileName
										+ ".xlsx;charset=UTF-8");
						ExcelHelper excelHelper = new ExcelHelper();
						excelHelper.setSheetName("物资库存列表");
						List<Map<String, Object>> dataList = (List<Map<String, Object>>) data;
						excelHelper.writeData("exportGoodsStocks",
								response.getOutputStream(), Map.class, dataList);

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

	/**
	 * 导出废料库存
	 * 
	 * @param request
	 * @param response
	 * @return
	 */

	@RequestMapping(value = "exportWasteStocks", method = RequestMethod.GET)
	public Object exportWasteStocks(HttpServletRequest request,
			HttpServletResponse response) {

		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {

				GoodsStockForm form = (GoodsStockForm) actionForm;
				GoodsService ser = (GoodsService) getService();
				List list = ser.exportWasteStocks(form.toMap());
				return list;
			}

			@Override
			public Object doWithFinish(HttpServletRequest request,
					HttpServletResponse response, Object data, String viewType,
					EAPController controller) {
				// TODO Auto-generated method stub

				ModelAndView modelAndView = new ModelAndView();
				EAPView view = new EAPView() {

					@Override
					public void render(Map<String, ?> model,
							HttpServletRequest request,
							HttpServletResponse response) throws Exception {
						response.setCharacterEncoding("UTF-8");
						String fileName = URLEncoder.encode("废料库存列表", "UTF-8");

						response.setContentType("application/ms-excel;charset=UTF-8");
						response.addHeader("Content-Disposition",
								"attachment;filename=" + fileName
										+ ".xlsx;charset=UTF-8");
						ExcelHelper excelHelper = new ExcelHelper();
						excelHelper.setSheetName("废料库存列表");
						List<Map<String, Object>> dataList = (List<Map<String, Object>>) data;
						excelHelper.writeData("exportWasteStocks",
								response.getOutputStream(), Map.class, dataList);

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

	/**
	 * 废料出入库流程查询
	 * 
	 * @param request
	 * @param response
	 * @return
	 */

	@RequestMapping(value = "searchWasteProcess", method = RequestMethod.GET)
	public Object searchWasteProcess(HttpServletRequest request,
			HttpServletResponse response) {

		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub

				GoodsStockForm form = (GoodsStockForm) actionForm;
				GoodsService ser = (GoodsService) getService();
				PageBean page = ser.searchWasteProcess(form.toMap(),
						actionForm.getCurrentPage(), actionForm.getPerpage());
				PageBeanResult result = new PageBeanResult();
				if (page != null) {

					result.setResultMsg("获取成功");
					result.setStatusCode(ResponseConstants.OK);
					result.setPageBean(page);
				} else {
					result.setResultMsg("获取数据失败");
					result.setStatusCode(ResponseConstants.SERVER_ERROR);
				}

				return result;
			}
		});
	}

}
