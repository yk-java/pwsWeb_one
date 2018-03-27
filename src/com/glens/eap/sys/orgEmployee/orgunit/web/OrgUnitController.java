package com.glens.eap.sys.orgEmployee.orgunit.web;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.glens.eap.platform.core.annotation.FormProcessor;
import com.glens.eap.platform.core.utils.StringUtil;
import com.glens.eap.platform.core.web.ControllerForm;
import com.glens.eap.platform.framework.context.EAPContext;
import com.glens.eap.platform.framework.web.EAPJsonAbstractController;
import com.glens.eap.platform.framework.web.ResponseConstants;
import com.glens.eap.platform.framework.web.support.JsonProcessRequestHandler;
import com.glens.eap.platform.framework.web.support.response.ListResult;
import com.glens.eap.sys.orgEmployee.orgunit.service.OrgUnitService;
import com.glens.eap.sys.orgEmployee.orgunit.vo.OrgUnit;

@FormProcessor(clazz = "com.glens.eap.sys.orgEmployee.orgunit.web.OrgUnitForm")
@RequestMapping("/pmsServices/sys/orgEmployee/org")
public class OrgUnitController extends EAPJsonAbstractController {

	@RequestMapping(method = RequestMethod.GET, value = "orgTree")
	public Object orgTree(HttpServletRequest request,
			HttpServletResponse response) {
		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {

				OrgUnitForm form = (OrgUnitForm) actionForm;
				String companyCode = form.getCompanyCode();
				String ticket = request.getParameter("ticket");

				OrgUnitService orgUnitService = (OrgUnitService) EAPContext
						.getContext().getBean("orgUnitService");
				Map result = new HashMap();
				try {

					List<OrgUnit> list = orgUnitService
							.queryOrgUnitList(companyCode);

					result.put("statusCode", ResponseConstants.OK);
					result.put("resultMsg", "返回结果成功");
					result.put("tree", list);
				} catch (Exception e) {
					e.printStackTrace();
					result.put("statusCode", ResponseConstants.SERVER_ERROR);
					result.put("resultMsg", "查询失败");
				}

				return result;
			}

		});
	}
	
	//职能部门的tree
	@RequestMapping(method = RequestMethod.GET, value = "partOrgTree")
	public Object partOrgTree(HttpServletRequest request,
			HttpServletResponse response) {
		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {

				OrgUnitForm form = (OrgUnitForm) actionForm;
				String companyCode = form.getCompanyCode();
				String ticket = request.getParameter("ticket");

				OrgUnitService orgUnitService = (OrgUnitService) EAPContext
						.getContext().getBean("orgUnitService");
				Map result = new HashMap();
				try {

					List<OrgUnit> list = orgUnitService
							.getPartOrgUnitList(companyCode);

					result.put("statusCode", ResponseConstants.OK);
					result.put("resultMsg", "返回结果成功");
					result.put("tree", list);
				} catch (Exception e) {
					e.printStackTrace();
					result.put("statusCode", ResponseConstants.SERVER_ERROR);
					result.put("resultMsg", "查询失败");
				}

				return result;
			}

		});
	}
	
	
	@Override
	@RequestMapping(method = RequestMethod.POST, value = "add")
	public Object insert(HttpServletRequest request, HttpServletResponse response) {
		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {

				OrgUnitService orgUnitService = (OrgUnitService) EAPContext
						.getContext().getBean("orgUnitService");

				OrgUnitForm form = (OrgUnitForm) actionForm;
				String ticket = request.getParameter("ticket");
				OrgUnit orgUnit = (OrgUnit) form.toVo();

				Map<String, String> result = orgUnitService
						.saveOrgUnit(orgUnit);

				return result;
			}

		});
	}
	@Override
	@RequestMapping(method = RequestMethod.POST, value = "update")
	public Object update(HttpServletRequest request,
			HttpServletResponse response) {
		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {

				OrgUnitService orgUnitService = (OrgUnitService) EAPContext
						.getContext().getBean("orgUnitService");

				OrgUnitForm form = (OrgUnitForm) actionForm;

				String ticket = request.getParameter("ticket");

				OrgUnit orgUnit = (OrgUnit) form.toVo();

				Map<String, String> result = orgUnitService
						.UpdateOrgUnit(orgUnit);

				return result;
			}

		});
	}
	
	//修改区域范围
	@RequestMapping(method = RequestMethod.POST, value = "updateArea")
	public Object updateArea(HttpServletRequest request,
			HttpServletResponse response) {
		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {

				OrgUnitService orgUnitService = (OrgUnitService) EAPContext
						.getContext().getBean("orgUnitService");

				OrgUnitForm form = (OrgUnitForm) actionForm;

				String ticket = request.getParameter("ticket");

				OrgUnit orgUnit = (OrgUnit) form.toVo();
				Map m =  new HashMap();
				m.put("ys", form.getYs());
				m.put("xs", form.getXs());
				DecimalFormat    df   = new DecimalFormat("######0.00");   

				m.put("perimeter", df.format(Double.parseDouble(form.getPerimeter())/1000));
				m.put("area", df.format(Double.parseDouble(form.getArea())/1000000));
				m.put("unitCode", form.getUnitCode());

				int result = orgUnitService.updateArea(m);
				return result;
			}

		});
	}
	
	   //删除区域范围
		@RequestMapping(method = RequestMethod.POST, value = "deleteArea")
		public Object deleteArea(HttpServletRequest request,
				HttpServletResponse response) {
			return this.process(request, response, new JsonProcessRequestHandler() {

				@Override
				public Object doWithRequest(HttpServletRequest request,
						HttpServletResponse response, ControllerForm actionForm) {

					OrgUnitService orgUnitService = (OrgUnitService) EAPContext
							.getContext().getBean("orgUnitService");

					OrgUnitForm form = (OrgUnitForm) actionForm;

					String ticket = request.getParameter("ticket");

					OrgUnit orgUnit = (OrgUnit) form.toVo();
					Map m =  new HashMap();
					m.put("ys", "");
					m.put("xs", "");
					m.put("perimeter", "0");
					m.put("area", "0");
					m.put("unitCode", form.getUnitCode());

					int result = orgUnitService.updateArea(m);
					return result;
				}

			});
		}
	
	@Override
	@RequestMapping(method = RequestMethod.POST, value = "delete")
	public Object delete(HttpServletRequest request,
			HttpServletResponse response) {
		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {

				OrgUnitService orgUnitService = (OrgUnitService) EAPContext
						.getContext().getBean("orgUnitService");

				OrgUnitForm form = (OrgUnitForm) actionForm;
				String ticket = request.getParameter("ticket");
				String unitCode = form.getUnitCode();

				orgUnitService.delete(unitCode);

				Map<String, String> result = new HashMap<String, String>();
				result.put("statusCode", ResponseConstants.OK);
				result.put("resultMsg", "返回结果成功");

				return result;
			}

		});
	}
	@Override
	@RequestMapping(method = RequestMethod.GET, value = "get")
	public Object get(HttpServletRequest request, HttpServletResponse response) {
		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				OrgUnitService orgUnitService = (OrgUnitService) EAPContext
						.getContext().getBean("orgUnitService");

				OrgUnitForm form = (OrgUnitForm) actionForm;
				String ticket = request.getParameter("ticket");
				String unitCode = form.getUnitCode();
				Map<String, Object> map = orgUnitService.queryOrgUnit(unitCode);
				return map;
			}

		});
	}
}
