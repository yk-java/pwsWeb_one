/**
 * @Title: PmDayQcService.java
 * @Package com.glens.pwCloudOs.pm.schedulePlan.qualityDaily.service
 * @Description: TODO
 * Copyright: Copyright (c) 2016 
 * Company:南京量为石信息科技有限公司
 * @author 
 * @date 2016-6-12 上午10:35:21
 * @version V1.0
 */


package com.glens.pwCloudOs.pm.schedulePlan.qualityDaily.service;

import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import com.glens.eap.platform.core.utils.DateTimeUtil;
import com.glens.eap.platform.framework.beans.UserToken;
import com.glens.eap.platform.framework.service.impl.EAPAbstractService;
import com.glens.pwCloudOs.pm.schedulePlan.qualityDaily.dao.PmDayQcDao;
import com.glens.pwCloudOs.pm.schedulePlan.qualityDaily.vo.PmDayQcVo;

/**
 * 
 * 
 * @author 
 * @version V1.0
 */

public class PmDayQcService extends EAPAbstractService {

	/**
	
	  * <p>Title: insert</p>
	
	  * <p>Description: </p>
	
	  * @param parameters
	  * @return
	
	  * @see com.glens.eap.platform.framework.service.impl.EAPAbstractService#insert(java.lang.Object)
	
	  **/
	
	
	@Override
	public boolean insert(Object parameters) {
		// TODO Auto-generated method stub
		
		PmDayQcVo vo = (PmDayQcVo) parameters;
		//vo.setQualityCheckORate((vo.getQualityCheckOWordload() * 1.0f) / vo.getQualityCheckTWordload());
		
		return super.insert(vo);
	}
	
	
	public int deleteDetalList(Object parameters) {
		// TODO Auto-generated method stub
		PmDayQcDao dao=(PmDayQcDao)getDao();
		return dao.deleteDetalList(parameters);
	}
	
	public List<PmDayQcVo> getProDayQc(Map<String, String> params) {
		
		List<PmDayQcVo> resultList = new ArrayList<PmDayQcVo>();
		PmDayQcDao dayQcDao = (PmDayQcDao) getDao();
		String fromDate = params.get("fromDate");
		String toDate = params.get("toDate");
		try {
			
			List<Map<String, String>> dates = DateTimeUtil.getDates(
					DateTimeUtil.getDateFormat(fromDate, DateTimeUtil.FORMAT_2),
					DateTimeUtil.getDateFormat(toDate, DateTimeUtil.FORMAT_2));
			if (dates != null && dates.size() > 0) {
				
				for (Map<String, String> dateItem : dates) {
					
					String date = dateItem.get("simpleDate");
					params.put("date", date);
					PmDayQcVo vo = dayQcDao.selectProDayQc(params);
					if (vo != null) {
						
						vo.setReportDate(date);
					}
					resultList.add(vo);
				}
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			
			e.printStackTrace();
		}
		
		return resultList;
	}
	
	//得到detail  list
	public List getDetailList(Map<String, String> params){
		PmDayQcDao dayQcDao = (PmDayQcDao) getDao();
		return dayQcDao.getDetailList(params);
		
	}
	
	
	
	
	//
	public List<Map<String, String>> problemType(Object params){
		
		
		
		PmDayQcDao dayQcDao = (PmDayQcDao) getDao();
		return dayQcDao.problemType(params);
	}
	
	
	public List<Map<String, String>> checkType(){
		PmDayQcDao dayQcDao = (PmDayQcDao) getDao();
		return dayQcDao.checkType();
	}
	
	public List<Map<String, String>> checkClass(){
		PmDayQcDao dayQcDao = (PmDayQcDao) getDao();
		return dayQcDao.checkClass();
	}
	
	
	public List<Map<String, String>> qualityAnalysis(Object parameters){
		PmDayQcDao dayQcDao = (PmDayQcDao) getDao();
		return dayQcDao.qualityAnalysis(parameters);
	}
	
	
	//导入excel
	public List importExcel(MultipartFile multiFile) {
		
		List<LinkedHashMap<String, Object>> res=null;
		
		try {
			res= readExcel(multiFile.getInputStream());
			for (Iterator<LinkedHashMap<String, Object>> iterator = res.iterator(); iterator.hasNext();) {
				LinkedHashMap<String, Object> object = iterator.next();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return res;
	}
	
	private List<LinkedHashMap<String, Object>> readExcel(InputStream ins){
		XSSFWorkbook workbook = null;
		try {
			workbook = new XSSFWorkbook(ins);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		
		List<LinkedHashMap<String, Object>> res = new ArrayList<LinkedHashMap<String, Object>>();
		try {
			/* Get first sheet */
			XSSFSheet sheet = workbook.getSheetAt(0);
			/* Get first row, get table headers */
			List<String> ths = new ArrayList<String>();
			XSSFRow rowFirst = sheet.getRow(0);
			for(int i=0; true; i++){
				XSSFCell cell = rowFirst.getCell(i);
				if(cell==null){
					break;
				}
				String val = cell.getStringCellValue();
				ths.add(val);
			}
			
			
			XSSFRow row;
			LinkedHashMap<String, Object> bean;
			for (int i = 1; true; i++) {

				/* Get row by index */
				row = sheet.getRow(i);
				if (row != null) {
					bean = new LinkedHashMap<String, Object>();
					/* Set value to bean */
					for (int j=0; j<ths.size(); j++) {
						String th = ths.get(j);
						/* Get cell value */
						XSSFCell cell = row.getCell(j);
						if(cell!=null){
							String val;
							try {
								val = cell.getStringCellValue();
							} catch (Exception e) {
								Date val_d = cell.getDateCellValue();
								val = new SimpleDateFormat("yyyy-MM-dd").format(val_d);
							}
							bean.put(th, val);
						}else{
							bean.put(th, "");
						}
					}
					res.add(bean);
				} else {
					/* Done, Current row is null, break */
					break;
				}
			}
		} catch (Exception e) {
			throw new RuntimeException("ERROR: Get data error", e);
		}
		return res;
	}
}
