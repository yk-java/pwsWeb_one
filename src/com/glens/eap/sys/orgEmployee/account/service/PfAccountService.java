package com.glens.eap.sys.orgEmployee.account.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.glens.eap.eapAuth.core.authentication.handlers.PasswordEncoder;
import com.glens.eap.platform.framework.beans.PageBean;
import com.glens.eap.platform.framework.codeCenter.CodeWorker;
import com.glens.eap.platform.framework.context.EAPContext;
import com.glens.eap.platform.framework.service.impl.EAPAbstractService;
import com.glens.eap.platform.framework.web.ResponseConstants;
import com.glens.eap.sys.orgEmployee.account.dao.PfAccountDao;
import com.glens.eap.sys.orgEmployee.account.vo.PfAccount;
import com.glens.eap.sys.orgEmployee.account.web.PfAccountForm;

public class PfAccountService extends EAPAbstractService {

	public Map savePfAccount(PfAccount pfaccount) {
		PfAccountDao dao = (PfAccountDao) this.dao;

		CodeWorker codeWorker = (CodeWorker) EAPContext.getContext().getBean(
				CodeWorker.SIMPLE_CODE_WORKER);
		String accountCode = codeWorker.createCode("A");
		pfaccount.setAccountCode(accountCode);
		
		PasswordEncoder pswEncoder = (PasswordEncoder) EAPContext.getContext().getBean("passwordEncoder");
		String encodePsw = pswEncoder.encode("123456");
		pfaccount.setAccountPassword(encodePsw);
		
		dao.insert(pfaccount);
		Map result = new HashMap();
		result.put("statusCode", ResponseConstants.OK);
		result.put("resultMsg", "返回结果成功");
		result.put("accountCode", accountCode);
		return result;
	}

	public Map updatePfAccount(PfAccount pfaccount) {
		PfAccountDao dao = (PfAccountDao) this.dao;
		dao.update(pfaccount);
		Map result = new HashMap();
		result.put("statusCode", ResponseConstants.OK);
		result.put("resultMsg", "返回结果成功");
		return result;
	}
	
	public Map reset(PfAccount pfaccount) {
		PfAccountDao dao = (PfAccountDao) this.dao;
		PasswordEncoder pswEncoder = (PasswordEncoder) EAPContext.getContext().getBean("passwordEncoder");
		String encodePsw = pswEncoder.encode("123456");
		pfaccount.setAccountPassword(encodePsw);
		dao.update(pfaccount);
		Map result = new HashMap();
		result.put("statusCode", ResponseConstants.OK);
		result.put("resultMsg", "返回结果成功");
		return result;
	}

	public Map queryPfAccount(PfAccountForm form) {
		PfAccountDao dao = (PfAccountDao) this.dao;
		PfAccount account = dao.queryPfAccount(form.getAccountCode());
		Map result = new HashMap();
		result.put("statusCode", ResponseConstants.OK);
		result.put("resultMsg", "返回结果成功");
		result.put("data", account);
		return result;
	}

	public Map querypfAccountList(PfAccountForm form) {
		Map result = new HashMap();
		PfAccountDao dao = (PfAccountDao) this.dao;
		PageBean page = dao.queryForPage(form.toMap(), form.getCurrentPage(),
				form.getPerpage());
		result.put("statusCode", ResponseConstants.OK);
		result.put("resultMsg", "返回结果成功");
		result.put("currentPage", page.getCurrentPage());
		result.put("perPage", page.getPerpage());
		result.put("totalPage", page.getTotalPage());
		result.put("totalRecord", page.getTotalRecord());
		result.put("list", page.getList());
		return result;
	}
	
	public List<String> selectAccountsByEmployeecodes(List<String> employeecodes){
		PfAccountDao dao = (PfAccountDao) this.dao;
		return dao.selectAccountsByEmployeecodes(employeecodes);
	}

}