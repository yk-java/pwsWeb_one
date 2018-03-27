package com.glens.pwCloudOs.gm.vm.filling.service;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.glens.eap.platform.framework.beans.PageBean;
import com.glens.eap.platform.framework.service.impl.EAPAbstractService;
import com.glens.pwCloudOs.gm.vm.filling.dao.GmVmFillingRecordDao;
import com.glens.pwCloudOs.gm.vm.filling.dao.GmVmSinopecBillDao;
import com.glens.pwCloudOs.gm.vm.filling.vo.GmVmFillingRecord;
import com.glens.pwCloudOs.gm.vm.filling.vo.GmVmSinopecBill;

public class GmVmFillingRecordService extends EAPAbstractService {
	private GmVmSinopecBillDao gmVmSinopecBillDao;

	public GmVmSinopecBillDao getGmVmSinopecBillDao() {
		return gmVmSinopecBillDao;
	}

	public void setGmVmSinopecBillDao(GmVmSinopecBillDao gmVmSinopecBillDao) {
		this.gmVmSinopecBillDao = gmVmSinopecBillDao;
	}
	
	/**
	 * 新增（如果存在“卡号、加油日期、上报人”一致则新增失败）
	 */
	@Override
	public boolean insert(Object parameters) {
		GmVmFillingRecordDao fillingDao = (GmVmFillingRecordDao)this.dao;
		GmVmFillingRecord rec = fillingDao.selectByCardNoDateUser((GmVmFillingRecord)parameters);
		if(rec == null){
			return this.dao.insert(parameters);
		}else{
			return false;
		}
	}
	
	
	public GmVmSinopecBill findSinopecBillById(Long rowid){
		return (GmVmSinopecBill)gmVmSinopecBillDao.findById(rowid);
	}
	
	/**
	 * 导入中石化消费账单（去重复）
	 * @param bills
	 * @return
	 */
	public int batchInsertSinopecBill(List<GmVmSinopecBill> bills){
//		return this.gmVmSinopecBillDao.batchInsert(bills);
		int rows=0;
		for (Iterator iterator = bills.iterator(); iterator.hasNext();) {
			GmVmSinopecBill gmVmSinopecBill = (GmVmSinopecBill) iterator.next();
			// get 
			GmVmSinopecBill bill = this.gmVmSinopecBillDao.selectByCardNoTimeAddr(gmVmSinopecBill);
			if(bill == null){
				// insert
				boolean res = this.gmVmSinopecBillDao.insert(gmVmSinopecBill);
				if(res){
					rows++;
				}
			}
		}
		return rows;
	}
	
	public PageBean querySinopecBillForPage(Map parameters, int currentPage, int perpage) {
		return this.gmVmSinopecBillDao.queryForPage(parameters, currentPage, perpage);
	}
	
	public int checkWithEachOther(){
		int rows=0;
		GmVmFillingRecordDao fillingDao = (GmVmFillingRecordDao)dao;
		// 查出未核对通过的上报信息
		List<GmVmFillingRecord> uncheckList = fillingDao.selectUncheckList();
		for (Iterator iterator = uncheckList.iterator(); iterator.hasNext();) {
			GmVmFillingRecord fillingRecord = (GmVmFillingRecord) iterator
					.next();
			// 根据卡号与日期查询中石化的账单记录
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("cardNo", fillingRecord.getCardNo());
			String fillingTime = fillingRecord.getFillingTime();
			params.put("recTime", fillingTime);
			List<GmVmSinopecBill> bills = this.gmVmSinopecBillDao.selectByCardNoDate(params);
			GmVmSinopecBill bill = null;
			if(bills!=null && bills.size()>0){
				if(bills.size()>1){
					// 异常
					// 找到多条记录，修改上报信息与账单记录，并描述原因
					GmVmFillingRecord fillingUpdateVal = new GmVmFillingRecord();
					fillingUpdateVal.setRowid(fillingRecord.getRowid());
					fillingUpdateVal.setCheckResult(2);// 异常
					fillingUpdateVal.setCheckDescr("找到多条记录无法决断");
					fillingDao.updateCheckState(fillingUpdateVal);
					continue;
				}else{
					bill = bills.get(0);
				}
			}
			if(bill!=null){
				// 能查到，则核对金额（上下不超过10元）
				if(bill.getAmount()!=null && fillingRecord.getAmount()!=null
						&& Math.abs(bill.getAmount()-fillingRecord.getAmount())<10){
					// 核对无误，修改上报信息与账单记录
					GmVmFillingRecord fillingUpdateVal = new GmVmFillingRecord();
					fillingUpdateVal.setRowid(fillingRecord.getRowid());
					fillingUpdateVal.setCheckResult(1);// 正常
					fillingUpdateVal.setCheckDescr("核对无误");
					fillingDao.updateCheckState(fillingUpdateVal);
					GmVmSinopecBill billUpdateVal = new GmVmSinopecBill();
					billUpdateVal.setRowid(bill.getRowid());
					billUpdateVal.setCheckResult(1);// 正常
					billUpdateVal.setRelId(fillingUpdateVal.getRowid());
					this.gmVmSinopecBillDao.update(billUpdateVal);
					rows++;
				}else{
					// 核对异常，修改上报信息与账单记录，并描述原因
					GmVmFillingRecord fillingUpdateVal = new GmVmFillingRecord();
					fillingUpdateVal.setRowid(fillingRecord.getRowid());
					fillingUpdateVal.setCheckResult(2);// 异常
					fillingUpdateVal.setCheckDescr("金额相差太大");
					fillingDao.updateCheckState(fillingUpdateVal);
					GmVmSinopecBill billUpdateVal = new GmVmSinopecBill();
					billUpdateVal.setRowid(bill.getRowid());
					billUpdateVal.setCheckResult(2);// 异常
					this.gmVmSinopecBillDao.update(billUpdateVal);
				}
			}else{
				// 未找到，修改上报信息与账单记录，并描述原因
				GmVmFillingRecord fillingUpdateVal = new GmVmFillingRecord();
				fillingUpdateVal.setRowid(fillingRecord.getRowid());
				fillingUpdateVal.setCheckResult(2);// 异常
				fillingUpdateVal.setCheckDescr("未找到对应记录");
				fillingDao.updateCheckState(fillingUpdateVal);
			}
		}
		return rows;
	}
}
