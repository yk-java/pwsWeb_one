package com.glens.pwCloudOs.fm.billApply.web;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.glens.eap.platform.core.annotation.FormProcessor;
import com.glens.eap.platform.core.utils.DateTimeUtil;
import com.glens.eap.platform.core.web.ControllerForm;
import com.glens.eap.platform.framework.beans.PageBean;
import com.glens.eap.platform.framework.beans.UserToken;
import com.glens.eap.platform.framework.codeCenter.CodeWorker;
import com.glens.eap.platform.framework.context.EAPContext;
import com.glens.eap.platform.framework.web.EAPJsonAbstractController;
import com.glens.eap.platform.framework.web.ResponseConstants;
import com.glens.eap.platform.framework.web.support.JsonProcessRequestHandler;
import com.glens.eap.platform.framework.web.support.response.BeanResult;
import com.glens.eap.platform.framework.web.support.response.KeyResult;
import com.glens.eap.platform.framework.web.support.response.ListResult;
import com.glens.eap.platform.framework.web.support.response.PageBeanResult;
import com.glens.eap.sys.orgEmployee.account.service.PfAccountService;
import com.glens.eap.sys.orgEmployee.account.vo.PfAccount;
import com.glens.eap.sys.orgEmployee.account.web.PfAccountForm;
import com.glens.pwCloudOs.fm.billApply.service.FmBillApplicationFormSevice;
import com.glens.pwCloudOs.fm.billApply.vo.FmBillApplicationForm;
import com.glens.pwCloudOs.fm.contractBase.service.ContractBaseService;
import com.glens.pwCloudOs.fm.invoicebase.service.InvoiceBaseService;

@FormProcessor(clazz = "com.glens.pwCloudOs.fm.billApply.web.FmBillApplicationForm")
@RequestMapping("pmsServices/fm/billApply")
public class FmBillApplicationFormController extends EAPJsonAbstractController {

	private PfAccountService pfAccountService;

	private InvoiceBaseService invoiceBaseService;

	private static String NO_INVOICE = "未开票";
	private static String INVOICEED = "已开票";

	@RequestMapping(value = "list", method = RequestMethod.GET)
	public Object list(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				FmBillApplicationFormSevice service = (FmBillApplicationFormSevice) getService();
				com.glens.pwCloudOs.fm.billApply.web.FmBillApplicationForm form = (com.glens.pwCloudOs.fm.billApply.web.FmBillApplicationForm) actionForm;
				UserToken token = (UserToken) request.getSession()
						.getAttribute(UserToken.KEY_TOKEN);
				form.setApplyUser(token.getAccountCode());
				PageBean page = service.queryForPage(form.toMap(),
						actionForm.getCurrentPage(), actionForm.getPerpage());
				PageBeanResult result = new PageBeanResult();
				if (page != null) {
					for (FmBillApplicationForm fb : (List<FmBillApplicationForm>) page
							.getList()) {
						fb.setStatus(fb.getStatus().equals("0") ? NO_INVOICE
								: INVOICEED);

					}
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

	@RequestMapping(value = "kplist", method = RequestMethod.GET)
	public Object kplist(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		return this.process(request, response, new JsonProcessRequestHandler() {

			
			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				FmBillApplicationFormSevice service = (FmBillApplicationFormSevice) getService();
				com.glens.pwCloudOs.fm.billApply.web.FmBillApplicationForm form = (com.glens.pwCloudOs.fm.billApply.web.FmBillApplicationForm) actionForm;
				UserToken token = (UserToken) request.getSession()
						.getAttribute(UserToken.KEY_TOKEN);
				// form.setApplyUser(token.getAccountCode());
				PageBean page = service.queryForPage(form.toMap(),
						actionForm.getCurrentPage(), actionForm.getPerpage());
				PageBeanResult result = new PageBeanResult();
				if (page != null) {
					for (FmBillApplicationForm fb : (List<FmBillApplicationForm>) page
							.getList()) {
						fb.setStatus(fb.getStatus().equals("0") ? NO_INVOICE
								: INVOICEED);
					}
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

	@RequestMapping(value = "queryCompanyList", method = RequestMethod.GET)
	public Object queryCompanyList(HttpServletRequest request,
			HttpServletResponse response) {

		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub
				UserToken token = (UserToken) request.getSession()
						.getAttribute(UserToken.KEY_TOKEN);
				// Map map = actionForm.toMap();
				// map.put("companyCode", token.getCompanyCode());
				FmBillApplicationFormSevice service = (FmBillApplicationFormSevice) getService();
				List list = service.queryCompanyList();
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

	@RequestMapping(value = "getBh", method = RequestMethod.GET)
	public Object getBh(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				FmBillApplicationFormSevice service = (FmBillApplicationFormSevice) getService();
				com.glens.pwCloudOs.fm.billApply.web.FmBillApplicationForm form = (com.glens.pwCloudOs.fm.billApply.web.FmBillApplicationForm) actionForm;
				UserToken token = (UserToken) request.getSession()
						.getAttribute(UserToken.KEY_TOKEN);
				CodeWorker codeWorker = (CodeWorker) EAPContext.getContext()
						.getBean(CodeWorker.SIMPLE_CODE_WORKER);
				Map<String, Object> map = new HashMap<String, Object>();
				String code = codeWorker.createCode("AB");
				BeanResult result = new BeanResult();
				result.setStatusCode(ResponseConstants.OK);
				result.setResultMsg("获取成功");
				map.put("code", code);
				map.put("user", token.getEmployeeName());
				map.put("dept", token.getUnitName());
				result.setData(map);

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
				FmBillApplicationFormSevice service = (FmBillApplicationFormSevice) getService();
				com.glens.pwCloudOs.fm.billApply.web.FmBillApplicationForm form = (com.glens.pwCloudOs.fm.billApply.web.FmBillApplicationForm) actionForm;
				form.setSysProcessFlag("1");
				form.setStatus("0");
				int ok;
				com.glens.pwCloudOs.fm.billApply.vo.FmBillApplicationForm ftype = new FmBillApplicationForm();
				UserToken token = (UserToken) request.getSession()
						.getAttribute(UserToken.KEY_TOKEN);
				try {
					PropertyUtils.copyProperties(ftype, form);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				KeyResult result = new KeyResult();
				ftype.setApplyUser(token.getAccountCode());

				try {
					ftype.setCreateTime(DateTimeUtil.formatDate(new Date(),
							DateTimeUtil.FORMAT_1));
					// ftype.setApplyDate(DateTimeUtil.formatDate(new Date(),
					// / DateTimeUtil.FORMAT_1));
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				ok = service.insertSelective(ftype);

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

	@RequestMapping(value = "update", method = RequestMethod.POST)
	public Object update(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				FmBillApplicationFormSevice service = (FmBillApplicationFormSevice) getService();
				com.glens.pwCloudOs.fm.billApply.web.FmBillApplicationForm form = (com.glens.pwCloudOs.fm.billApply.web.FmBillApplicationForm) actionForm;
				form.setSysProcessFlag("1");
				int ok;
				com.glens.pwCloudOs.fm.billApply.vo.FmBillApplicationForm ftype = new FmBillApplicationForm();
				UserToken token = (UserToken) request.getSession()
						.getAttribute(UserToken.KEY_TOKEN);
				try {
					PropertyUtils.copyProperties(ftype, form);
				} catch (Exception e) {
					e.printStackTrace();
				}
				KeyResult result = new KeyResult();
				ftype.setApplyUser(token.getAccountCode());

				try {
					ftype.setUpdateTime(DateTimeUtil.formatDate(new Date(),
							DateTimeUtil.FORMAT_1));
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				ok = service.updateSelective(ftype);

				if (ok == 1) {

					result.setStatusCode(ResponseConstants.OK);
					result.setResultMsg("修改成功");
					result.setGenerateKey(actionForm.getGenerateKey());
				} else {

					result.setStatusCode(ResponseConstants.SERVER_ERROR);
					result.setResultMsg("修改失败");
				}

				return result;

			}

		});
	}

	@RequestMapping(value = "get", method = RequestMethod.GET)
	public Object get(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		return this.process(request, response, new JsonProcessRequestHandler() {
			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub
				FmBillApplicationFormSevice service = (FmBillApplicationFormSevice) getService();
				FmBillApplicationForm vo = (FmBillApplicationForm) service
						.findById(actionForm.toVo());
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				BeanResult result = new BeanResult();
				if (vo != null) {
					vo.setAppTypeName(invoiceBaseService.getInvoiceClassName(vo
							.getAppType()));
					PfAccountForm form = new PfAccountForm();
					form.setAccountCode(vo.getApplyUser());
					Map m = pfAccountService.queryPfAccount(form);
					PfAccount account = (PfAccount) m.get("data");
					vo.setUser(account.getEmployeename());
					vo.setDept(account.getUnitName());
					Map taxMap = invoiceBaseService.getInvoiceTaxRate(vo
							.getAppType());
					if (taxMap != null && taxMap.get("TAX_RATE") != null) {
						vo.setTaxRate((Float) taxMap.get("TAX_RATE"));
					} else {
						vo.setTaxRate(0F);
					}

					Date d = new Date();
					try {
						d = sdf.parse(vo.getApplyDate());
					} catch (ParseException e) {
						e.printStackTrace();
					}
					vo.setApplyDate1(new SimpleDateFormat("yyy年MM月dd日")
							.format(d));
					result.setStatusCode(ResponseConstants.OK);
					result.setResultMsg("获取成功!");
					result.setData(vo);
				} else {

					result.setStatusCode(ResponseConstants.NO_DATA);
					result.setResultMsg("没有获取到数据");
				}
				return result;
			}

		});
	}

	public void setPfAccountService(PfAccountService pfAccountService) {
		this.pfAccountService = pfAccountService;
	}

	public void setInvoiceBaseService(InvoiceBaseService invoiceBaseService) {
		this.invoiceBaseService = invoiceBaseService;
	}

}
