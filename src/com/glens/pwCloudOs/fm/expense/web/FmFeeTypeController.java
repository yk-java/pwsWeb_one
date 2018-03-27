package com.glens.pwCloudOs.fm.expense.web;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.glens.eap.platform.core.annotation.FormProcessor;
import com.glens.eap.platform.core.web.ControllerForm;
import com.glens.eap.platform.framework.beans.PageBean;
import com.glens.eap.platform.framework.codeCenter.CodeWorker;
import com.glens.eap.platform.framework.context.EAPContext;
import com.glens.eap.platform.framework.web.EAPJsonAbstractController;
import com.glens.eap.platform.framework.web.ResponseConstants;
import com.glens.eap.platform.framework.web.support.JsonProcessRequestHandler;
import com.glens.eap.platform.framework.web.support.response.BeanResult;
import com.glens.eap.platform.framework.web.support.response.KeyResult;
import com.glens.eap.platform.framework.web.support.response.ListResult;
import com.glens.eap.platform.framework.web.support.response.PageBeanResult;
import com.glens.eap.platform.framework.web.support.response.ResponseResult;
import com.glens.pwCloudOs.fm.expense.service.FmFeeTypeService;
import com.glens.pwCloudOs.fm.expense.vo.FmFeeType;

@FormProcessor(clazz = "com.glens.pwCloudOs.fm.expense.web.FmFeeTypeForm")
@RequestMapping("pmsServices/fm/expense/feeType")
public class FmFeeTypeController extends EAPJsonAbstractController {

	@RequestMapping(value = "getTreeList", method = RequestMethod.GET)
	public Object getTreeList(HttpServletRequest request,
			HttpServletResponse response) {
		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub

				FmFeeTypeService fmFeeTypeService = (FmFeeTypeService) getService();

				List list = fmFeeTypeService.getTreeList(actionForm.toMap());

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

	@RequestMapping(value = "list", method = RequestMethod.GET)
	public Object list(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub
				FmFeeTypeService fmFeeTypeService = (FmFeeTypeService) getService();
				PageBean page = fmFeeTypeService.queryForPage(
						actionForm.toMap(), actionForm.getCurrentPage(),
						actionForm.getPerpage());
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

	@RequestMapping(value = "add", method = RequestMethod.POST)
	public Object insert(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				FmFeeTypeService fmFeeTypeService = (FmFeeTypeService) getService();

				FmFeeTypeForm form = (FmFeeTypeForm) actionForm;

				CodeWorker codeWorker = (CodeWorker) EAPContext.getContext()
						.getBean(CodeWorker.SIMPLE_CODE_WORKER);
				String catgalogCode = codeWorker.createCode("F");
				form.setFeeCode(catgalogCode);
				form.setSysProcessFlag("1");
				int ok;
				FmFeeType ftype = new FmFeeType();
				try {
					PropertyUtils.copyProperties(ftype, form);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				KeyResult result = new KeyResult();
				ok = fmFeeTypeService.insertSelective(ftype);

				if (ok == 1) {

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
	@RequestMapping(value = "get", method = RequestMethod.GET)
	public Object get(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub
				FmFeeTypeService fmFeeTypeService = (FmFeeTypeService) getService();
				Map map = fmFeeTypeService.queryFeeType(actionForm.toMap());
				BeanResult result = new BeanResult();
				if (map != null) {

					result.setStatusCode(ResponseConstants.OK);
					result.setResultMsg("获取成功!");
					result.setData(map);
				} else {

					result.setStatusCode(ResponseConstants.NO_DATA);
					result.setResultMsg("没有获取到数据");
				}

				return result;
			}

		});
	}

	@RequestMapping(value = "update", method = RequestMethod.POST)
	public Object update(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub

				FmFeeTypeService fmFeeTypeService = (FmFeeTypeService) getService();
				ResponseResult result = new ResponseResult();

				int iCount;
				FmFeeType ftype = new FmFeeType();
				try {
					PropertyUtils.copyProperties(ftype, actionForm);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				iCount = fmFeeTypeService.updateSelective(ftype);

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

	@RequestMapping(value = "delete", method = RequestMethod.POST)
	public Object delete(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub
				FmFeeTypeService fmFeeTypeService = (FmFeeTypeService) getService();
				FmFeeTypeForm form = (FmFeeTypeForm) actionForm;
				int iCount = fmFeeTypeService.delete(form.getRowid());
				ResponseResult result = new ResponseResult();

				if (iCount > 0) {

					result.setStatusCode(ResponseConstants.OK);
					result.setResultMsg("删除成功");
				} else {

					result.setStatusCode(ResponseConstants.SERVER_ERROR);
					result.setResultMsg("删除出错");
				}

				return result;
			}

		});
	}
}
