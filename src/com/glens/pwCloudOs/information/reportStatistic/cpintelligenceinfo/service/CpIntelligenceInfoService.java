package com.glens.pwCloudOs.information.reportStatistic.cpintelligenceinfo.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.glens.eap.platform.core.utils.StringUtil;
import com.glens.eap.platform.framework.beans.PageBean;
import com.glens.eap.platform.framework.context.EAPContext;
import com.glens.eap.platform.framework.service.impl.EAPAbstractService;
import com.glens.eap.platform.framework.web.ResponseConstants;
import com.glens.pwCloudOs.information.reportStatistic.cpintelligenceinfo.dao.CpIntelligenceInfoDao;
import com.glens.pwCloudOs.information.reportStatistic.cpintelligenceinfo.vo.CpIntelligenceInfo;
import com.glens.pwCloudOs.information.reportStatistic.cpintelligenceinfo.web.CpIntelligenceInfoForm;
import com.glens.eap.sys.orgEmployee.employee.dao.MdEmployeeDao;
import com.glens.eap.sys.orgEmployee.employee.vo.MdEmployee;
import com.glens.eap.sys.orgEmployee.orgunit.dao.OrgUnitDao;
import com.glens.eap.sys.orgEmployee.orgunit.vo.OrgUnit;

public class CpIntelligenceInfoService extends EAPAbstractService {

	public Map queryList(CpIntelligenceInfoForm form) {
		CpIntelligenceInfoDao cpintelligenceinfodao = (CpIntelligenceInfoDao) this.dao;
		OrgUnitDao orgUnitDao = (OrgUnitDao) EAPContext.getContext().getBean(
				"orgUnitDao");

		MdEmployeeDao mdEmployeeDao = (MdEmployeeDao) EAPContext.getContext()
				.getBean("mdEmployeeDao");
		PageBean page = cpintelligenceinfodao.queryForPage(form.toMap(),
				form.getCurrentPage(), form.getPerpage());
		for (CpIntelligenceInfo ci : (List<CpIntelligenceInfo>) page.getList()) {
			String unitCode = ci.getUnitCode();
			String employeecode = ci.getEmployeecode();
			String intelligenceTypeCode = ci.getIntelligenceTypeCode();
			String intelligenceUrgencyTypeCode = ci
					.getIntelligenceUrgencyTypeCode();
			if (StringUtil.isNotNull(unitCode)) {
				OrgUnit ou = orgUnitDao.queryOrgUnit(unitCode);
				if (ou != null)
					ci.setUnitName(ou.getUnitName());
			}

			if (StringUtil.isNotNull(employeecode)) {
				MdEmployee md = mdEmployeeDao.queryEmployeeByCode(employeecode);
				if (md != null)
					ci.setEmployeeName(md.getEmployeename());
			}

			if (StringUtil.isNotNull(intelligenceTypeCode)) {
				List<Map<String, String>> list = cpintelligenceinfodao
						.queryIntelligenceType(intelligenceTypeCode);
				if (list.size() > 0) {
					Map<String, String> m = list.get(0);
					ci.setIntelligenceTypeName(m.get("name"));
				}
			}

			if (StringUtil.isNotNull(intelligenceUrgencyTypeCode)) {
				List<Map<String, String>> list = cpintelligenceinfodao
						.queryIntelligenceUrgencyType(intelligenceUrgencyTypeCode);
				if (list.size() > 0) {
					Map<String, String> m = list.get(0);
					ci.setIntelligenceUrgencyTypeName(m.get("name"));
				}
			}

		}
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

}
