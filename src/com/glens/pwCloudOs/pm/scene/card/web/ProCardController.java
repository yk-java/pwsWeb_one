package com.glens.pwCloudOs.pm.scene.card.web;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.glens.eap.platform.core.annotation.FormProcessor;
import com.glens.eap.platform.core.utils.DateTimeUtil;
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
import com.glens.pwCloudOs.pm.scene.card.service.ProCardService;
import com.glens.pwCloudOs.pm.scene.card.vo.ProCard;

@RequestMapping("pmsServices/pm/card")
@FormProcessor(clazz = "com.glens.pwCloudOs.pm.scene.card.web.ProCardForm")
public class ProCardController extends EAPJsonAbstractController {

	@RequestMapping(value = "chooseCards", method = RequestMethod.GET)
	public Object chooseCards(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				ProCardService pservice = (ProCardService) getService();
				ProCardForm form = (ProCardForm) actionForm;
				List list = pservice.chooseCardsList(form.toMap());
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

		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub
				PageBean page = getService().queryForPage(actionForm.toMap(),
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

	@RequestMapping(value = "add", method = RequestMethod.POST)
	public Object insert(HttpServletRequest request,
			HttpServletResponse response) {
		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub
				ProCardService pservice = (ProCardService) getService();

				KeyResult result = new KeyResult();
				try {
					ProCard card = (ProCard) actionForm.toVo();
					card.setSysProcessFlag("1");
					card.setSysCreateTime(DateTimeUtil.formatDate(new Date(),
							DateTimeUtil.FORMAT_1));
					card.setStatus("1");
					CodeWorker codeWorker = (CodeWorker) EAPContext
							.getContext()
							.getBean(CodeWorker.SIMPLE_CODE_WORKER);
					card.setAssetCode(codeWorker.createCode("A"));
					pservice.insertCard(card);
					result.setStatusCode(ResponseConstants.OK);
					result.setResultMsg("新增成功");
					result.setGenerateKey(actionForm.getGenerateKey());
				} catch (Exception e) {
					result.setStatusCode(ResponseConstants.SERVER_ERROR);
					result.setResultMsg("新增失败");
				}

				return result;
			}

		});
	}

	@RequestMapping(value = "update", method = RequestMethod.POST)
	public Object update(HttpServletRequest request,
			HttpServletResponse response) {
		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {

				ProCardService pservice = (ProCardService) getService();
				KeyResult result = new KeyResult();
				try {
					pservice.updateCard(actionForm.toVo());
					result.setStatusCode(ResponseConstants.OK);
					result.setResultMsg("更新成功");
					result.setGenerateKey(actionForm.getGenerateKey());
				} catch (Exception e) {
					result.setStatusCode(ResponseConstants.SERVER_ERROR);
					result.setResultMsg("更新失败");
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

				ResponseResult result = new ResponseResult();
				ProCardService pservice = (ProCardService) getService();
				try {
					pservice.deleteCard(actionForm.toVo());
					result.setStatusCode(ResponseConstants.OK);
					result.setResultMsg("删除成功");
				} catch (Exception e) {
					e.printStackTrace();
					result.setStatusCode(ResponseConstants.SERVER_ERROR);
					result.setResultMsg("删除出错");
				}
				return result;
			}

		});
	}

	@RequestMapping(value = "get", method = RequestMethod.GET)
	public Object get(HttpServletRequest request, HttpServletResponse response) {

		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				ProCardService pservice = (ProCardService) getService();
				ProCardForm form = (ProCardForm) actionForm;
				Map m = pservice.get(form.getRowid());

				BeanResult result = new BeanResult();
				if (m != null) {
					result.setStatusCode(ResponseConstants.OK);
					result.setResultMsg("获取成功!");
					result.setData(m);
				} else {

					result.setStatusCode(ResponseConstants.NO_DATA);
					result.setResultMsg("没有获取到数据");
				}

				return result;
			}

		});
	}
}
