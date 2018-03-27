package com.glens.pwCloudOs.fm.invoicebase.service;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.glens.eap.platform.core.beans.ValueObject;
import com.glens.eap.platform.core.utils.DateTimeUtil;
import com.glens.eap.platform.framework.service.impl.EAPAbstractService;
import com.glens.pwCloudOs.fm.billApply.dao.FmBillApplicationFormDao;
import com.glens.pwCloudOs.fm.billApply.dao.FmBillApplicationLogDao;
import com.glens.pwCloudOs.fm.billApply.vo.FmBillApplicationForm;
import com.glens.pwCloudOs.fm.billApply.vo.FmBillApplicationLog;
import com.glens.pwCloudOs.fm.invoicebase.dao.InvoiceBaseDao;
import com.glens.pwCloudOs.fm.invoicebase.vo.InvoiceBaseVo;
import com.glens.pwCloudOs.fm.invoicebase.web.InvoiceBaseForm;

public class InvoiceBaseService extends EAPAbstractService {

	private FmBillApplicationFormDao fmBillApplicationFormDao;

	private FmBillApplicationLogDao fmBillApplicationLogDao;

	public List<Map<String, String>> getInvoiceType() {
		InvoiceBaseDao dao = (InvoiceBaseDao) getDao();
		return dao.getInvoiceType();
	}

	public List<Map<String, String>> getinvoiceList(Object parms) {
		InvoiceBaseDao dao = (InvoiceBaseDao) getDao();
		return dao.getinvoiceList(parms);
	}

	public String getInvoiceClassName(String appType) {
		InvoiceBaseDao dao = (InvoiceBaseDao) getDao();
		return dao.getInvoiceClassName(appType);
	}

	public Map getInvoiceTaxRate(String appType) {
		InvoiceBaseDao dao = (InvoiceBaseDao) getDao();
		return dao.getInvoiceTaxRate(appType);
	}

	/**
	 * 生成开票明细
	 * 
	 * @param form
	 * @return
	 */
	public boolean generateKp(InvoiceBaseForm form) {
		boolean result = true;
		InvoiceBaseDao dao = (InvoiceBaseDao) getDao();
		String applyCode = form.getApplyCode();
		FmBillApplicationForm ba = fmBillApplicationFormDao
				.queryBillApplication(applyCode);
		String invoiceNo = form.getInvoiceNo();
		// 不含税金额
		String mount2 = form.get_mount2();
		// 开票金额
		String mount1 = form.get_mount1();
		String taxAmount = form.get_taxAmount();
		String[] invoiceNoAttr = invoiceNo.split("\\|");
		String[] mount2Attr = mount2.split("\\|");
		String[] mount1Attr = mount1.split("\\|");
		String[] taxAmountAttr = taxAmount.split("\\|");
		InvoiceBaseVo vo = new InvoiceBaseVo();
		vo.setProNo(ba.getProCode());
		vo.setProName(ba.getProName());
		vo.setCgUnit(ba.getCompany());
		vo.setContractNo(ba.getContractCode());
		vo.setInvoiceDate(form.getInvoiceDate());
		vo.setInvoiceClassCode(ba.getAppType());
		vo.setInvoiceContent(ba.getAppContent());

		for (int i = 0; i < invoiceNoAttr.length; i++) {
			vo.setInvoiceNo(invoiceNoAttr[i]);
			vo.setMount1(Double.parseDouble(mount1Attr[i]));
			vo.setMount2(Double.parseDouble(mount2Attr[i]));
			vo.setTaxAmount(Double.parseDouble(taxAmountAttr[i]));
			dao.insert(vo);

			// 记录日志
			FmBillApplicationLog log = new FmBillApplicationLog();
			log.setApplyCode(applyCode);
			log.setSysProcessFlag("1");
			try {
				log.setCreateTime(DateTimeUtil.formatDate(new Date(),
						DateTimeUtil.FORMAT_1));
			} catch (ParseException e) {
				e.printStackTrace();
			}
			log.setInvoiceNo(invoiceNoAttr[i]);
			log.setPureMoney(Float.parseFloat(mount1Attr[i]));
			log.setTaxMoney(Float.parseFloat(taxAmountAttr[i]));
			log.setTotalMoney(Float.parseFloat(mount2Attr[i]));
			fmBillApplicationLogDao.insertSelective(log);
		}

		ba.setStatus("1");
		fmBillApplicationFormDao.updateSelective(ba);
		return result;
	}

	public FmBillApplicationFormDao getFmBillApplicationFormDao() {
		return fmBillApplicationFormDao;
	}

	public void setFmBillApplicationFormDao(
			FmBillApplicationFormDao fmBillApplicationFormDao) {
		this.fmBillApplicationFormDao = fmBillApplicationFormDao;
	}

	public void setFmBillApplicationLogDao(
			FmBillApplicationLogDao fmBillApplicationLogDao) {
		this.fmBillApplicationLogDao = fmBillApplicationLogDao;
	}

	public List<Map<String, String>> getinvoiceRepayList(Object parms) {
		InvoiceBaseDao dao = (InvoiceBaseDao) getDao();
		// 发票号列表
		List invoiceList = dao.getinvoiceList(parms);
		// 回款发票表
		List repayList = dao.getinvoiceRepayList();

		List<Map<String, String>> resultList = new ArrayList<Map<String, String>>();

		for (int i = 0; i < invoiceList.size(); i++) {
			boolean flag = false;
			Map m = (Map) invoiceList.get(i);
			String invoiceNos = (String) m.get("invoiceNos");
			for (int k = 0; k < repayList.size(); k++) {
				Map m1 = (Map) repayList.get(k);
				String invoiceNos1 = (String) m1.get("INVOICE_NOS");
				String[] invoiceNosAttr1 = invoiceNos1.split(",");
				for (String no : invoiceNosAttr1) {
					if (invoiceNos.equals(no)) {
						flag = true;
						break;
					}
				}
			}
			if (!flag) {
				resultList.add(m);
			}
		}

		return resultList;
	}
	public List<Map<String, String>> getInvoiceNosByProno(Object parms) {
		InvoiceBaseDao dao = (InvoiceBaseDao) getDao();
		// 发票号列表
		List invoiceList = dao.getInvoiceNosByProno(parms);
		
		return invoiceList;
	}
	
}
