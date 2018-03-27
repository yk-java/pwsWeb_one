package com.glens.pwCloudOs.dailylog.web;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.alibaba.fastjson.JSONObject;
import com.glens.eap.platform.core.annotation.FormProcessor;
import com.glens.eap.platform.core.utils.DateTimeUtil;
import com.glens.eap.platform.core.web.ControllerForm;
import com.glens.eap.platform.framework.beans.UserToken;
import com.glens.eap.platform.framework.context.EAPContext;
import com.glens.eap.platform.framework.utils.HttpPostUtil;
import com.glens.eap.platform.framework.web.EAPJsonAbstractController;
import com.glens.eap.platform.framework.web.ResponseConstants;
import com.glens.eap.platform.framework.web.support.JsonProcessRequestHandler;
import com.glens.eap.platform.framework.web.support.response.BeanResult;
import com.glens.eap.platform.framework.web.support.response.KeyResult;
import com.glens.eap.platform.framework.web.support.response.PageBeanResult;
import com.glens.eap.sys.orgEmployee.orgunit.service.OrgUnitService;
import com.glens.pwCloudOs.common.context.PwCloudOsConstant;
import com.glens.pwCloudOs.config.PWCloudOsConfig;
import com.glens.pwCloudOs.dailylog.service.DailylogService;
import com.glens.pwCloudOs.dailylog.vo.DailylogVo;
import com.glens.pwCloudOs.weixin.bean.AccessToken;
import com.glens.pwCloudOs.weixin.bean.Articles;
import com.glens.pwCloudOs.weixin.bean.MdlUpload;
import com.glens.pwCloudOs.weixin.bean.Mpnews;
import com.glens.pwCloudOs.weixin.bean.Result;
import com.glens.pwCloudOs.weixin.util.WeixinUtils;

@FormProcessor(clazz = "com.glens.pwCloudOs.dailylog.web.DailylogForm")
@RequestMapping("pmsServices/dailylog")
public class DailylogController extends EAPJsonAbstractController {

	// 日志查看
	@RequestMapping(value = "getLoglist", method = RequestMethod.GET)
	public Object getLoglist(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub

				DailylogService ser = (DailylogService) getService();
				List list = ser.getLogList(actionForm.toMap());

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

	// web端 日志统计
	@RequestMapping(value = "isWriteList", method = RequestMethod.GET)
	public Object isWriteList(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub
				UserToken token = (UserToken) request.getSession()
						.getAttribute(UserToken.KEY_TOKEN);
				DailylogService ser = (DailylogService) getService();

				Map map = actionForm.toMap();
				if (PwCloudOsConstant.DEPT_MANAGER_ROLE_CODE.equals(token
						.getRoleCode())) {
					map.put("deptCode", token.getUnitCode());
				}

				Map list = ser.getAllList(map);

				BeanResult result = new BeanResult();
				if (list != null) {

					result.setStatusCode(ResponseConstants.OK);
					result.setResultMsg("获取成功");
					result.setData(list);
				} else {

					result.setStatusCode(ResponseConstants.NO_DATA);
					result.setResultMsg("没有获取到数据");
				}

				return result;
			}

		});
	}

	// 日志统计
	@RequestMapping(value = "getWriteList", method = RequestMethod.GET)
	public Object getWriteList(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub

				DailylogService ser = (DailylogService) getService();
				List list = ser.getWriteList(actionForm.toMap());

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

	// 查看当天的日志记录
	@RequestMapping(value = "getCurrentList", method = RequestMethod.GET)
	public Object getCurrentList(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub

				DailylogService ser = (DailylogService) getService();
				List list = ser.getCurrentList(actionForm.toMap());

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

	// web端 日志查看和新增
	@RequestMapping(value = "viewLogList", method = RequestMethod.GET)
	public Object viewLogList(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub

				DailylogService ser = (DailylogService) getService();
				List list = ser.getwebLogList(actionForm.toMap());

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

	@RequestMapping(value = "insertLog", method = RequestMethod.POST)
	public Object insertLog(HttpServletRequest request,
			HttpServletResponse response) {
		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				PWCloudOsConfig config = (PWCloudOsConfig) EAPContext
						.getContext().getBean("pwcloudosConfig");
				DailylogService ser = (DailylogService) getService();
				DailylogForm form = (DailylogForm) actionForm;
				List<DailylogVo> list = JSONObject.parseArray(
						form.getSaveJSON(), DailylogVo.class);
				KeyResult result = new KeyResult();
				UserToken token = getToken(request);
				OrgUnitService ouService = (OrgUnitService) EAPContext
						.getContext().getBean("orgUnitService");
				try {
					String unitCode = "";
					String logDate = "";
					String content = new String();
					for (DailylogVo vo : list) {
						unitCode = vo.getUnitCode();
						logDate = vo.getLogDate();
						vo.setSysCreateTime(DateTimeUtil.formatDate(new Date(),
								DateTimeUtil.FORMAT_2));
						vo.setSysProcessFlag("1");
						ser.insert(vo);
						content += "<div style='margin:10px 0;padding:0 0 10px 0;border-bottom:1px solid #ccc'><div style='line-height:30px;'>项目名称："
								+ vo.getWiPro()
								+ "</div><div style='line-height:30px;'>描述："
								+ vo.getWiRecord()
								+ "</div><div style='line-height:30px;'>执行成果："
								+ vo.getWiResult()
								+ "</div><div style='line-height:30px;'>执行情况："
								+ vo.getWiPlan() + "</div></div>";
					}

					String title = "<div style='width:94%;margin: 0 auto;padding: 0;margin: 0;font-family: '微软雅黑';'><p style='font-size:18px;padding:15px 0 ;font-weight: bold;'>"
							+ token.getEmployeeName()
							+ "<span style='float: right;'>工作日期："
							+ logDate
							+ "</span></p>";

					content = title + content + "</div>";

					// 推送微信 ,查询部门主管或副经理 201611140938
					Map m = ouService.queryUnitLeaders(unitCode);
					if (m != null) {
						if (m.get("USER_ID") != null) {
							String userId = (String) m.get("USER_ID");
							AccessToken at = WeixinUtils
									.getWXAccessToken(PwCloudOsConstant.COMPANY_HELP_AGENTID);

							Mpnews mpnews = new Mpnews();
							List<Articles> articles = new ArrayList<Articles>();
							Articles article = new Articles();
							article.setTitle("工作日志");
							article.setDigest(token.getEmployeeName()
									+ " "
									+ DateTimeUtil.formatDate(new Date(),
											DateTimeUtil.FORMAT_2));
							String realPath = request.getRealPath("/")
									+ "img/dailyLog.jpg";
							File tmpFile = new File(realPath);

							Result<MdlUpload> result1 = HttpPostUtil.Upload(
									tmpFile, String.format(
											PwCloudOsConstant.UPLOAD_URL,
											at.getAccess_token(), "image"));
							System.out
									.println("*****上传图片至微信服务器结果********Errcode="
											+ result1.getErrcode()
											+ "\tErrmsg=" + result1.getErrmsg());
							System.out.println(result1.getObj().toString());
							String mediaId = result1.getObj().getMedia_id();

							// 上传封面图片
							article.setThumb_media_id(mediaId);
							article.setContent(content);
							articles.add(article);
							mpnews.setArticles(articles);
							WeixinUtils.pushMpNews(at.getAccess_token(),
									mediaId, userId, mpnews,
									PwCloudOsConstant.COMPANY_HELP_AGENTID);
						}
					}

					result.setStatusCode(ResponseConstants.OK);
					result.setResultMsg("新增成功");

				} catch (Exception e) {
					result.setStatusCode(ResponseConstants.SERVER_ERROR);
					result.setResultMsg("新增失败");
				}
				return result;
			}

		});
	}
}
