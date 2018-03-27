package com.glens.pwCloudOs.km.base.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.glens.eap.platform.core.annotation.FormProcessor;
import com.glens.eap.platform.core.utils.StringUtil;
import com.glens.eap.platform.core.web.ControllerForm;
import com.glens.eap.platform.framework.context.EAPContext;
import com.glens.eap.platform.framework.web.EAPJsonAbstractController;
import com.glens.eap.platform.framework.web.ResponseConstants;
import com.glens.eap.platform.framework.web.support.JsonProcessRequestHandler;
import com.glens.eap.platform.framework.web.support.response.BeanResult;
import com.glens.eap.platform.framework.web.support.response.ListResult;
import com.glens.pwCloudOs.config.PWCloudOsConfig;
import com.glens.pwCloudOs.km.attach.vo.KmAttachment;
import com.glens.pwCloudOs.km.base.service.KmBaseService;
import com.glens.pwCloudOs.km.base.vo.KmBaseVo;
import com.glens.pwCloudOs.km.catalog.web.CatalogAppController;

@FormProcessor(clazz = "com.glens.pwCloudOs.km.base.web.KmBaseForm")
@RequestMapping("pmsServices/kmbaseService")
public class KmBaseAppController extends EAPJsonAbstractController {
	private static Log logger = LogFactory.getLog(CatalogAppController.class);

	@RequestMapping(value = "queryKmBaseList", method = RequestMethod.GET)
	public Object queryKmBaseList(HttpServletRequest request,
			HttpServletResponse response) {
		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				KmBaseService ser = (KmBaseService) getService();

				Map<String, Object> m = new HashMap<String, Object>();
				String catalogCode = request.getParameter("catalogCode");

				List<String> codeList = new ArrayList<String>();
				if (StringUtil.isNotNull(catalogCode)) {
					// 查询当前目录下面所有目录
					codeList.add(catalogCode);
					querySublist(catalogCode, codeList);
					m.put("codeList", codeList);
				}

				List list = ser.queryKmBaseList(m);

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
	 * 查询文件详情
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "queryKmBase", method = RequestMethod.GET)
	public Object queryKmBase(HttpServletRequest request,
			HttpServletResponse response) {
		return this.process(request, response, new JsonProcessRequestHandler() {

			PWCloudOsConfig config = (PWCloudOsConfig) EAPContext.getContext()
					.getBean("pwcloudosConfig");

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				KmBaseService ser = (KmBaseService) getService();

				String rowid = request.getParameter("rowid");
				KmBaseVo kb = (KmBaseVo) ser.queryKbBase(Long.parseLong(rowid));
				BeanResult result = new BeanResult();

				if (kb != null) {
					String path = request.getContextPath();
					String basePath = request.getScheme() + "://"
							+ request.getServerName() + ":"
							+ request.getServerPort() + path;
					String appUrl = basePath
							+ "/mobile/pages/konwledge/klDetailForAd.html";
					kb.setAppUrl(appUrl + "?rowid=" + rowid);
					
					for (int i = 0; i < kb.getAttachList().size(); i++) {
						KmAttachment attach = kb.getAttachList().get(i);
						attach.setFileUrl(config.getPrefix()+config.getDownloadUrl() + "?"
								+ "fileCode=" + attach.getFileUrl()
								+ "&attachment=0");
					}

					result.setStatusCode(ResponseConstants.OK);
					result.setResultMsg("获取成功");
					result.setData(kb);
				} else {
					result.setStatusCode(ResponseConstants.NO_DATA);
					result.setResultMsg("没有获取到数据");
				}

				return result;
			}
		});
	}

	/**
	 * 查询文档（标题）
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "queryKmBasekeyWordList", method = RequestMethod.POST)
	public Object queryKmBasekeyWordList(HttpServletRequest request,
			HttpServletResponse response) {
		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				KmBaseService ser = (KmBaseService) getService();

				Map<String, Object> m = new HashMap<String, Object>();
				String fileTitle = request.getParameter("fileTitle");
				if (StringUtil.isNotNull(fileTitle)) {
					m.put("fileTitle", fileTitle);
				}

				List list = ser.queryKmBasekeyWordList(m);

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

	protected void querySublist(String catalogCode, List<String> codeList) {
		KmBaseService ser = (KmBaseService) getService();
		List<String> list = ser.querySubCatalogList(catalogCode);
		codeList.addAll(list);
	}
}
