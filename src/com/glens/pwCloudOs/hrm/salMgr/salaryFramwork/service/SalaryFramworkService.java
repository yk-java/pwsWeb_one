/**
 * @Title: SalaryFramworkService.java
 * @Package com.glens.pwCloudOs.hrm.salMgr.salaryFramwork.service
 * @Description: TODO
 * Copyright: Copyright (c) 2016 
 * Company:南京量为石信息科技有限公司
 * @author 
 * @date 2017-2-16 下午5:49:45
 * @version V1.0
 */


package com.glens.pwCloudOs.hrm.salMgr.salaryFramwork.service;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.omg.CORBA.OBJ_ADAPTER;

import com.glens.eap.platform.core.utils.DateTimeUtil;
import com.glens.eap.platform.framework.beans.PageBean;
import com.glens.eap.platform.framework.codeCenter.CodeWorker;
import com.glens.eap.platform.framework.context.EAPContext;
import com.glens.eap.platform.framework.service.impl.EAPAbstractService;
import com.glens.pwCloudOs.hrm.salMgr.salaryFramwork.dao.SalaryFramworkDao;
import com.glens.pwCloudOs.hrm.salMgr.salaryFramwork.vo.SalaryFramworkVo;

/**
 * 
 * 
 * @author 
 * @version V1.0
 */

public class SalaryFramworkService extends EAPAbstractService {

	private static final int MONTH_WORK_DATE = 22;
	
	private static final String REGULAR_EMPLOYEE = "1";
	
	private static final int TRAINEE_TYPE = 2;
	
	private static final double TRAINEE_LABOUR_RATIO = 1.2 / 1.4;
	
	public Map<String, Object> getSalaryFramworkDetail(Map<String, Object> params) {
		
		SalaryFramworkDao salaryFramworkDao = (SalaryFramworkDao) getDao();
		
		Map<String, Object> salaryFramwork = salaryFramworkDao.selectSalaryFrameworkDetail(params);
		if (salaryFramwork != null) {
			
			Map<String, Map<String, Object>> salarySetting = new HashMap<String, Map<String,Object>>();
			salaryFramwork.put("salarySetting", salarySetting);
			String salaryGradeNamePrefix = salaryFramwork.get("salaryGradeNamePrefix").toString();
			List<Map<String, Object>> salaryFramworkSettingList = salaryFramworkDao.selectFrameworkSetting(params);
			if (salaryFramworkSettingList != null && salaryFramworkSettingList.size() > 0) {
				
				for (Map<String, Object> salaryFramworkSettingItem : salaryFramworkSettingList) {
					
					if (salarySetting.get(salaryGradeNamePrefix + salaryFramworkSettingItem.get("salaryGrade")) != null) {
						
						Map<String, Object> gradeSalarySetting = salarySetting.get(salaryGradeNamePrefix 
								+ salaryFramworkSettingItem.get("salaryGrade"));
						gradeSalarySetting.put("D" + salaryFramworkSettingItem.get("salaryLevel"), 
								salaryFramworkSettingItem.get("standardSalary"));
					}
					else {
						
						Map<String, Object> gradeSalarySetting = new HashMap<String, Object>();
						gradeSalarySetting.put("D" + salaryFramworkSettingItem.get("salaryLevel"), 
								salaryFramworkSettingItem.get("standardSalary"));
						
						salarySetting.put(salaryGradeNamePrefix + salaryFramworkSettingItem.get("salaryGrade"), gradeSalarySetting);
					}
					
				}
			}
		}
		
		return salaryFramwork;
	}
	
	public int insertFrameworkSetting(Map<String, Object> params) {
		
		SalaryFramworkDao salaryFramworkDao = (SalaryFramworkDao) getDao();
		String companyCode = params.get("companyCode").toString();
		String unitCode = params.get("unitCode").toString();
		String frameworkCode = params.get("frameworkCode").toString();
		String salarys = (String) params.get("salarys");
		int affectedCount = 0;
		if (salarys != null && !"".equals(salarys)) {
			//先删除原来的数据
			salaryFramworkDao.deleteFrameworkSetting(params);
			String[] salaryArray = salarys.split(";");
			List<Map<String, Object>> salaryList = new ArrayList<Map<String,Object>>();
			for (String salary : salaryArray) {
				
				Map<String, Object> salaryParam = new HashMap<String, Object>();
				String[] salaryInfos = salary.split(",");
				salaryParam.put("companyCode", companyCode);
				salaryParam.put("unitCode", unitCode);
				salaryParam.put("frameworkCode", frameworkCode);
				salaryParam.put("salaryGrade", salaryInfos[0].substring(1));
				salaryParam.put("salaryLevel", salaryInfos[1].substring(1));
				salaryParam.put("standardSalary", salaryInfos[2]);
				
				salaryList.add(salaryParam);
			}
			
			affectedCount = salaryFramworkDao.insertFrameworkSetting(salaryList);
		}
		
		return affectedCount;
	}
	
	public Map<String, Object> getUnitSalaryFramework(Map<String, Object> params) {
		
		SalaryFramworkDao salaryFramworkDao = (SalaryFramworkDao) getDao();
		Map<String, Object> salaryFramwork = salaryFramworkDao.selectEnableSalaryFramework(params);
		
		return salaryFramwork;
	}
	
	public Map<String, Object> getStandSalary(Map<String, Object> params) {
		
		SalaryFramworkDao salaryFramworkDao = (SalaryFramworkDao) getDao();
		Map<String, Object> salaryFramwork = salaryFramworkDao.getStandSalary(params);
		
		return salaryFramwork;
	}
	

	
	public List<Map<String, Object>> getUnitEmployeeList(Map<String, Object> params) {
		
		SalaryFramworkDao salaryFramworkDao = (SalaryFramworkDao) getDao();
		
		return salaryFramworkDao.selectEmployeeByUnit(params);
	}
	
	public List<Map<String, Object>> getJobNums(Map<String, Object> params) {
		
		SalaryFramworkDao salaryFramworkDao = (SalaryFramworkDao) getDao();
		
		return salaryFramworkDao.getJobNums(params);
	}
	
	
	public Map<String, Map<String, List<Map<String, Object>>>> getUnitGradeSetting(Map<String, Object> params) {
		
		SalaryFramworkDao salaryFramworkDao = (SalaryFramworkDao) getDao();
		Map<String, Map<String, List<Map<String, Object>>>> salarySetting = new HashMap<String, Map<String, List<Map<String, Object>>>>();
		String salaryGradeNamePrefix = (String) params.get("salaryGradeNamePrefix");
		List<Map<String, Object>> salaryFramworkSettingList = salaryFramworkDao.selectFrameworkSetting(params);
		List<Map<String, Object>> employeeList = salaryFramworkDao.selectEmployeeByUnit(params);
		if (salaryFramworkSettingList != null && salaryFramworkSettingList.size() > 0) {
			
			for (Map<String, Object> salaryFramworkSettingItem : salaryFramworkSettingList) {
				
				if (salarySetting.get(salaryGradeNamePrefix + salaryFramworkSettingItem.get("salaryGrade")) == null) {
					
					Map<String, List<Map<String, Object>>> gradeSalarySetting = new HashMap<String, List<Map<String, Object>>>();
					
					salarySetting.put(salaryGradeNamePrefix + salaryFramworkSettingItem.get("salaryGrade"), gradeSalarySetting);
				}
				
				Map<String, List<Map<String, Object>>> gradeSalarySetting = salarySetting.get(salaryGradeNamePrefix 
						+ salaryFramworkSettingItem.get("salaryGrade"));
				List<Map<String, Object>> employees = configSalaryGrade(salaryFramworkSettingItem, employeeList);
				gradeSalarySetting.put("D" + salaryFramworkSettingItem.get("salaryLevel"), 
						employees);
			}
		}
		
		return salarySetting;
	}
	
	private List<Map<String, Object>> configSalaryGrade(Map<String, Object> salaryFramworkSettingItem, 
			List<Map<String, Object>> employeeList) {
		
		List<Map<String, Object>> employees = new ArrayList<Map<String,Object>>();
		int l = Integer.parseInt(salaryFramworkSettingItem.get("salaryGrade").toString());
		int d = Integer.parseInt(salaryFramworkSettingItem.get("salaryLevel").toString());
		if (employeeList != null && employeeList.size() > 0) {
			
			for (Map<String, Object> employeeItem : employeeList) {
				
				if (employeeItem.get("salaryGrade") != null) {
					
					int _l = Integer.parseInt(employeeItem.get("salaryGrade").toString());
					int _d = Integer.parseInt(employeeItem.get("salaryLevel").toString());
					
					if (l == _l && d == _d) {
						
						employees.add(employeeItem);
					}
				}
				
			} 
		}
		
		
		return employees;
	}
	
	public int updateEmployeeSalaryGrade(Map<String, Object> params) {
		
		SalaryFramworkDao salaryFramworkDao = (SalaryFramworkDao) getDao();
		
		return salaryFramworkDao.updateEmployeeSalaryGrade(params);
	}
	
	public int resetEmployeeSalaryGrade(Map<String, Object> params) {
		
		SalaryFramworkDao salaryFramworkDao = (SalaryFramworkDao) getDao();
		
		return salaryFramworkDao.resetEmployeeSalaryGrade(params);
	}
	
	public PageBean selectEmployeeSalaryForPage(Map<String, Object> params) {
		
		SalaryFramworkDao salaryFramworkDao = (SalaryFramworkDao) getDao();
		
		PageBean page = salaryFramworkDao.selectEmployeeSalaryForPage(params);
		try {
			if (page != null && page.getList() != null && page.getList().size() > 0) {
				
				List<Map<String, Object>> salaryParamList = salaryFramworkDao.selectSalaryParam(params);
				if (salaryParamList != null && salaryParamList.size() > 0) {
					
					List<Map<String, Object>> employeeList = page.getList();
					Map<String, Object> salaryParam = salaryParamList.get(0);
					
					for (Map<String, Object> employeeItem : employeeList) {
						
						calcEmployeeSalary(employeeItem, salaryParam);
					}
				}
			}
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return page;
	}
	
	//得到人力资源单价 等
	public Map<String, Object> selectEmployeeSalary(Map<String, Object> params) {
		
		SalaryFramworkDao salaryFramworkDao = (SalaryFramworkDao) getDao();
		
		Map<String, Object> employeeSalaryItem = salaryFramworkDao.selectEmployeeSalaryByCode(params);
		try {
			if (employeeSalaryItem != null && employeeSalaryItem.size() > 0) {
				
				List<Map<String, Object>> salaryParamList = salaryFramworkDao.selectSalaryParam(params);
				if (salaryParamList != null && salaryParamList.size() > 0) {
					
					Map<String, Object> salaryParam = salaryParamList.get(0);
					calcEmployeeSalary(employeeSalaryItem, salaryParam);
				}
			}
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return employeeSalaryItem;
	}
	
	//得到部门资源单价
	public Map<String, Object> selectUnitSalary(Map<String, Object> params) {
		
		SalaryFramworkDao salaryFramworkDao = (SalaryFramworkDao) getDao();
		
		Map<String, Object> employeeSalaryItem = salaryFramworkDao.selectUnitSalary(params);
		
		
		try {
			if (employeeSalaryItem != null && employeeSalaryItem.size() > 0) {
				
				List<Map<String, Object>> salaryParamList = salaryFramworkDao.selectSalaryParam(params);
				if (salaryParamList != null && salaryParamList.size() > 0) {
					
					Map<String, Object> salaryParam = salaryParamList.get(0);
					calcUnitSalary(employeeSalaryItem, salaryParam, params);
				}
			}else{
				
				employeeSalaryItem = new HashMap<String, Object>();
				employeeSalaryItem.put("hrmResPrice", 0.0);
			}
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return employeeSalaryItem;
	}
	
	private void calcEmployeeSalary(Map<String, Object> employeeItem, 
			Map<String, Object> salaryParam) throws ParseException {
		
		String jobCode=""; //判断是否是项目经理 8
		if(employeeItem.get("jobCode")!=null && !employeeItem.get("jobCode").toString().equals("")){
			jobCode=employeeItem.get("jobCode").toString();
		}
		
		
		BigDecimal realSalary = new BigDecimal(employeeItem.get("realSalary").toString());//实际工资
		
		
		int salaryGrade = Integer.parseInt(employeeItem.get("salaryGrade").toString());
		int salaryLevel = Integer.parseInt(employeeItem.get("salaryLevel").toString());
		
		int isYearlySalary=Integer.parseInt(employeeItem.get("isYearlySalary").toString());
		BigDecimal mealAllowance = (BigDecimal) salaryParam.get("mealAllowance");
		BigDecimal fullAttendanceBonus = (BigDecimal) salaryParam.get("fullAttendanceBonus");
		BigDecimal standardSenioritySalary = (BigDecimal) salaryParam.get("senioritySalary");
		BigDecimal monParableDay = (BigDecimal) salaryParam.get("monParableDay");
		BigDecimal dayWorkHour = (BigDecimal) salaryParam.get("dayWorkHour");
		BigDecimal standardSalary = (BigDecimal) employeeItem.get("standardSalary");
		BigDecimal monthWorkDay = new BigDecimal(MONTH_WORK_DATE);
		BigDecimal salaryFactor = (BigDecimal) salaryParam.get("salaryFactor");
		String officeDate = employeeItem.get("officeDate").toString();
		String workDate = employeeItem.get("workDate").toString();
		int recruitType = Integer.parseInt(employeeItem.get("recruitType").toString());
		employeeItem.put("mealAllowance", salaryParam.get("mealAllowance"));
		employeeItem.put("fullAttendanceBonus", salaryParam.get("fullAttendanceBonus"));
		employeeItem.put("salary", 0.0);
		employeeItem.put("monSalary", 0.0);
		employeeItem.put("hrmResPrice", 0.0);
		employeeItem.put("monHrmResPrice", 0.0);
		BigDecimal senioritySalary = new BigDecimal(0.0);
		//开始计算
		
		BigDecimal salary = mealAllowance.multiply(monthWorkDay);//餐补 *22
		//计算全勤、工龄工资
		if (employeeItem.get("contractPropertyCode") != null && (
				employeeItem.get("contractPropertyCode").equals("1") 
				|| employeeItem.get("contractPropertyCode").equals("3"))  //判断正式员工 试用期
				&& officeDate != null && !"".equals(officeDate)) {
			
			salary = salary.add(fullAttendanceBonus);//加上全勤
			
			if (employeeItem.get("contractPropertyCode").equals("1")) {
				
				if (recruitType == TRAINEE_TYPE) { //原来是实习生
					
					officeDate = workDate;
				}
				
				int year = DateTimeUtil.getDateAndCurDateYear(officeDate, DateTimeUtil.FORMAT_2);
				if (year > 0) {
					
					senioritySalary = standardSenioritySalary.multiply(new BigDecimal(year));//工龄工资  *年限
					salary = salary.add(senioritySalary);
					employeeItem.put("monSalary", fullAttendanceBonus.multiply(new BigDecimal(year)));
				}
			}
			
		} else {  //临时工  暑假工  不加上全勤奖
			
			employeeItem.put("fullAttendanceBonus", 0);//全勤奖为0 
		}
		
		if (jobCode.equals("8") && realSalary.doubleValue()>0) { //项目经理 并且设置了薪酬等级
			//if(项目经理  || 实习生)  合同性质 2 4
			//salary*80%/12
			
			if(isYearlySalary==1){
				
				BigDecimal proManagerSalary = realSalary.multiply(new BigDecimal(0.8)).divide(new BigDecimal(12),2,BigDecimal.ROUND_HALF_DOWN);
				salary = salary.add(proManagerSalary);// 加上实际工资
				
				
				
				BigDecimal monHrmResPrice=new BigDecimal(0);
				
				monHrmResPrice = realSalary.multiply(new BigDecimal(0.8)).divide(new BigDecimal(12), 2, BigDecimal.ROUND_HALF_DOWN).divide(monParableDay, 2, BigDecimal.ROUND_HALF_DOWN)
							.divide(dayWorkHour, 2, BigDecimal.ROUND_HALF_DOWN);
			
				
				BigDecimal hrmResPrice = salary.multiply(salaryFactor).divide(monParableDay, 2, 
						BigDecimal.ROUND_HALF_DOWN).divide(dayWorkHour, 2, BigDecimal.ROUND_HALF_DOWN);//单价
				//salary.multiply(salaryFactor).;
				employeeItem.put("salary", salary.doubleValue());
				employeeItem.put("hrmResPrice", hrmResPrice.doubleValue());
				employeeItem.put("monHrmResPrice", monHrmResPrice.doubleValue());
				/*employeeItem.put("salaryGrade", employeeItem.get("salaryGradeNamePrefix") 
						+ "" + employeeItem.get("salaryGrade"));
				employeeItem.put("salaryLevel", salaryLevel + "档");*/
				
				employeeItem.put("salaryGrade", employeeItem.get("salaryGrade"));
				employeeItem.put("salaryLevel", salaryLevel);
			}
			else {
				//项目经理，但是不采用年薪制
				salary = salary.add(standardSalary);
				
				BigDecimal monHrmResPrice=new BigDecimal(0);
				if (realSalary != null && realSalary.doubleValue() > 0) {
					
					monHrmResPrice = realSalary.divide(monParableDay, 2, BigDecimal.ROUND_HALF_DOWN)
							.divide(dayWorkHour, 2, BigDecimal.ROUND_HALF_DOWN);
				}
				else {
					
					monHrmResPrice = standardSalary.divide(monParableDay, 2, BigDecimal.ROUND_HALF_DOWN)
							.divide(dayWorkHour, 2, BigDecimal.ROUND_HALF_DOWN);
				}
				
				BigDecimal hrmResPrice = salary.multiply(salaryFactor).divide(monParableDay, 2, 
						BigDecimal.ROUND_HALF_DOWN).divide(dayWorkHour, 2, BigDecimal.ROUND_HALF_DOWN);//单价
				employeeItem.put("salary", salary.doubleValue());
				employeeItem.put("hrmResPrice", hrmResPrice.doubleValue());
				employeeItem.put("monHrmResPrice", monHrmResPrice.doubleValue());
				
				employeeItem.put("salaryGrade", employeeItem.get("salaryGrade"));
				employeeItem.put("salaryLevel", salaryLevel);
			}
			
		}else if(!jobCode.equals("8") && salaryGrade > 0) {
			
			if(isYearlySalary==1){
				salary = salary.add(standardSalary.divide(new BigDecimal(12),2,BigDecimal.ROUND_HALF_DOWN));// 如果是年薪制  加上等级工资除以12
			}else{
				salary = salary.add(standardSalary);// 加上等级工资
			}
			
			BigDecimal monHrmResPrice=new BigDecimal(0);
			if(employeeItem.get("contractPropertyCode").equals("2") || employeeItem.get("contractPropertyCode").equals("4")){
				
					if(isYearlySalary==1){
						monHrmResPrice = realSalary.divide(new BigDecimal(12), 2, BigDecimal.ROUND_HALF_DOWN).divide(monParableDay, 2, BigDecimal.ROUND_HALF_DOWN)
								.divide(dayWorkHour, 2, BigDecimal.ROUND_HALF_DOWN);
					}else{
						monHrmResPrice = realSalary.divide(monParableDay, 2, BigDecimal.ROUND_HALF_DOWN)
								.divide(dayWorkHour, 2, BigDecimal.ROUND_HALF_DOWN);
					}
			}else{
				if(isYearlySalary==1){
					monHrmResPrice = standardSalary.divide(new BigDecimal(12), 2, BigDecimal.ROUND_HALF_DOWN).divide(monParableDay, 2, BigDecimal.ROUND_HALF_DOWN)
							.divide(dayWorkHour, 2, BigDecimal.ROUND_HALF_DOWN);
				}else{
					monHrmResPrice = standardSalary.divide(monParableDay, 2, BigDecimal.ROUND_HALF_DOWN)
							.divide(dayWorkHour, 2, BigDecimal.ROUND_HALF_DOWN);
				}
			}
			
			
			/*BigDecimal monHrmResPrice = standardSalary.divide(monParableDay, 2, BigDecimal.ROUND_HALF_DOWN)
					.divide(dayWorkHour, 2, BigDecimal.ROUND_HALF_DOWN);*/
			BigDecimal hrmResPrice = salary.multiply(salaryFactor).divide(monParableDay, 2, 
					BigDecimal.ROUND_HALF_DOWN).divide(dayWorkHour, 2, BigDecimal.ROUND_HALF_DOWN);//单价
			//salary.multiply(salaryFactor).;
			employeeItem.put("salary", salary.doubleValue());
			employeeItem.put("hrmResPrice", hrmResPrice.doubleValue());
			employeeItem.put("monHrmResPrice", monHrmResPrice.doubleValue());
			/*employeeItem.put("salaryGrade", employeeItem.get("salaryGradeNamePrefix") 
					+ "" + employeeItem.get("salaryGrade"));
			employeeItem.put("salaryLevel", salaryLevel + "档");*/
			
			employeeItem.put("salaryGrade", employeeItem.get("salaryGrade"));
			employeeItem.put("salaryLevel", salaryLevel);
		}else{
			employeeItem.put("salary", 0.0);
			employeeItem.put("hrmResPrice", 0.0);
			employeeItem.put("monHrmResPrice", 0.0);
			employeeItem.put("salaryGrade", "");
			employeeItem.put("salaryLevel", "");
		}
	}
	
	
	
	//根据等级和层级来计算薪酬
	private void calcUnitSalary(Map<String, Object> employeeItem, 
			Map<String, Object> salaryParam, Map<String, Object> params) throws ParseException {
		
		//int salaryGrade = Integer.parseInt(employeeItem.get("salaryGrade").toString());
		int isYearlySalary=Integer.parseInt(employeeItem.get("isYearlySalary").toString());
		String contractPropertyCode = (String) params.get("contractPropertyCode");
		String jobCode = (String) params.get("jobCode");
		
		BigDecimal mealAllowance = (BigDecimal) salaryParam.get("mealAllowance");//餐补
		BigDecimal fullAttendanceBonus = (BigDecimal) salaryParam.get("fullAttendanceBonus");//全勤
		
		BigDecimal monParableDay = (BigDecimal) salaryParam.get("monParableDay");
		BigDecimal dayWorkHour = (BigDecimal) salaryParam.get("dayWorkHour");
		BigDecimal standardSalary = (BigDecimal) employeeItem.get("standardSalary");
		BigDecimal monthWorkDay = new BigDecimal(MONTH_WORK_DATE);
		BigDecimal salaryFactor = (BigDecimal) salaryParam.get("salaryFactor");

		employeeItem.put("hrmResPrice", 0.0);
		if (isYearlySalary == 1) {
			
			standardSalary = standardSalary.divide(new BigDecimal(12), 2, BigDecimal.ROUND_HALF_DOWN);
		}

		BigDecimal salary = mealAllowance.multiply(monthWorkDay);
		if (contractPropertyCode.equals("2") || contractPropertyCode.equals("4")) {
			
			salary = salary.add(standardSalary);
		}
		else {
			
			salary = salary.add(fullAttendanceBonus).add(standardSalary);
		}
			
		BigDecimal hrmResPrice = salary.multiply(salaryFactor).divide(monParableDay, 2, 
					BigDecimal.ROUND_HALF_DOWN).divide(dayWorkHour, 2, BigDecimal.ROUND_HALF_DOWN);

		employeeItem.put("hrmResPrice", hrmResPrice.doubleValue());
			
		
		
	}
		
		
	
	//判断是否有启用的体系
	public List<Map<String, Object>> isStart(Map<String, Object> params) {
		
		SalaryFramworkDao salaryFramworkDao = (SalaryFramworkDao) getDao();
		List<Map<String, Object>> salaryFramwork = salaryFramworkDao.isStart(params);
		
		return salaryFramwork;
	}
	
	//启用 /关闭 体系
	public int updateStatus(Map<String, Object> params) {
		
		SalaryFramworkDao salaryFramworkDao = (SalaryFramworkDao) getDao();
		
		return salaryFramworkDao.updateStatus(params);
	}
	
	public boolean copySalaryFramework(Map<String, Object> params) {
		
		SalaryFramworkDao salaryFramworkDao = (SalaryFramworkDao) getDao();
		String frameworkCode = params.get("frameworkCode").toString();
		String companyCode = params.get("companyCode").toString();
		String newUnitCode = params.get("newUnitCode").toString();
		String validStartTime = params.get("validStartTime").toString();
		String validEndTime = params.get("validEndTime").toString();
		CodeWorker codeWorker = (CodeWorker) EAPContext.getContext().getBean(
				CodeWorker.SIMPLE_CODE_WORKER);
		String newFrameworkCode = codeWorker.createCode("S");
		boolean affectedResult = false;
		SalaryFramworkVo voParam = new SalaryFramworkVo();
		voParam.setCompanyCode(companyCode);
		voParam.setFrameworkCode(frameworkCode);
		SalaryFramworkVo copyVo = (SalaryFramworkVo) salaryFramworkDao.findById(voParam);
		if (copyVo != null) {
			
			List<Map<String, Object>> salaryFramworkSettingList = salaryFramworkDao.selectFrameworkSetting(params);
			SalaryFramworkVo newVo = new SalaryFramworkVo();
			newVo.setCompanyCode(companyCode);
			newVo.setFrameworkCode(newFrameworkCode);
			newVo.setRemarks(copyVo.getRemarks());
			newVo.setSalaryGrade(copyVo.getSalaryGrade());
			newVo.setSalaryGradeNamePrefix(copyVo.getSalaryGradeNamePrefix());
			newVo.setSalaryGradeStart(copyVo.getSalaryGradeStart());
			newVo.setSalaryLevel(copyVo.getSalaryLevel());
			newVo.setStatus(1);
			newVo.setTitle(copyVo.getTitle());
			newVo.setUnitCode(newUnitCode);
			newVo.setValidStartTime(validStartTime);
			newVo.setValidEndTime(validEndTime);
			
			affectedResult  = salaryFramworkDao.insert(newVo);
			if (affectedResult && salaryFramworkSettingList != null && salaryFramworkSettingList.size() > 0) {
				
				for (Map<String, Object> salarytFrameworkSetting : salaryFramworkSettingList) {
					
					salarytFrameworkSetting.put("unitCode", newUnitCode);
					salarytFrameworkSetting.put("frameworkCode", newFrameworkCode);
					salarytFrameworkSetting.put("companyCode", companyCode);
				}
				
				salaryFramworkDao.insertFrameworkSetting(salaryFramworkSettingList);
			}
			
		}
		
		return affectedResult;
		
	}
	
	public List<Map<String, Object>> selectEmployeeSalaryForList(Map<String, Object> params) {
		
		SalaryFramworkDao salaryFramworkDao = (SalaryFramworkDao) getDao();
		
		List<Map<String, Object>> salaryList = salaryFramworkDao.selectEmployeeSalaryForList(params);
		try {
			if (salaryList != null && salaryList.size() > 0) {
				
				List<Map<String, Object>> salaryParamList = salaryFramworkDao.selectSalaryParam(params);
				if (salaryParamList != null && salaryParamList.size() > 0) {
					
					List<Map<String, Object>> employeeList = salaryList;
					Map<String, Object> salaryParam = salaryParamList.get(0);
					
					for (Map<String, Object> employeeItem : employeeList) {
						
						calcEmployeeSalary(employeeItem, salaryParam);
					}
				}
			}
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return salaryList;
	}
	
	
	public boolean getLabourcostByProNo(Map<String, Object> params) {
		SalaryFramworkDao salaryFramworkDao = (SalaryFramworkDao) getDao();
		boolean returnValue=false;
		List<Map<String, Object>> salaryList = salaryFramworkDao.getLabourcostByProNo(params);
		for (int i = 0; i < salaryList.size(); i++) {
			Map m=salaryList.get(i);
			//employeeCode unitCode companyCode
			String employeeCode="";
			if(m.get("EMPLOYEECODE")!=null){
				employeeCode=m.get("EMPLOYEECODE").toString();
			}
			String contractPropertyCode="";
			if(m.get("CONTRACT_PROPERTY_CODE")!=null){
				contractPropertyCode=m.get("CONTRACT_PROPERTY_CODE").toString();
			}
			String companyCode=m.get("COMPANY_CODE").toString();
			String unitCode=m.get("UNIT_CODE").toString();
			String rowid=m.get("ROW_ID").toString();
			double worktime=0;
			if(m.get("WORK_TIME")!=null){
				worktime=Double.parseDouble(m.get("WORK_TIME").toString());
			}
			
			Map<String, Object> pa=new HashMap<String, Object>();
			pa.put("companyCode", companyCode);
			pa.put("unitCode", unitCode);
			pa.put("contractPropertyCode", contractPropertyCode);
			pa.put("worktime", worktime);
			pa.put("rowid", rowid);
			
			if(!employeeCode.equals("")){
				 pa.put("employeeCode", employeeCode);
				 Map<String, Object> returnMap=selectEmployeeSalary(pa);
				
				 double hrmResPrice=0;
				 if(returnMap!=null && returnMap.get("hrmResPrice")!=null){
					 hrmResPrice=Double.parseDouble(returnMap.get("hrmResPrice").toString());
					 pa.put("hrmResPrice", hrmResPrice);
					 if (contractPropertyCode.equals("2") || contractPropertyCode.equals("4")) {
						 
						 pa.put("labourCost", hrmResPrice * worktime * TRAINEE_LABOUR_RATIO);
					 }
					 else {
						 
						 pa.put("labourCost", hrmResPrice*worktime);
					 }
					 
					 returnValue= salaryFramworkDao.updateLabourCost(pa)>=0;
				 }
			}else{
				
				 pa.put("salaryGrade", m.get("SALARY_GRADE").toString());
				 pa.put("salaryLevel", m.get("SALARY_LEVEL").toString());
				 Map<String, Object> returnMap=selectUnitSalary(pa);  
				 double hrmResPrice=0;
				 if(returnMap!=null && returnMap.get("hrmResPrice")!=null){
					 hrmResPrice=Double.parseDouble(returnMap.get("hrmResPrice").toString());
					 pa.put("hrmResPrice", hrmResPrice);
					 pa.put("labourCost", hrmResPrice*worktime);
					 if (contractPropertyCode.equals("2") || contractPropertyCode.equals("4")) {
						 
						 pa.put("labourCost", hrmResPrice * worktime * TRAINEE_LABOUR_RATIO);
					 }
					 else {
						 
						 pa.put("labourCost", hrmResPrice*worktime);
					 }
					 returnValue= salaryFramworkDao.updateLabourCost(pa)>=0;
				 }
				 
				
			}
		}
		
		if(returnValue){
			List<Map<String, Object>> costList=salaryFramworkDao.getCostByProPhase(params);
			
			for(int i=0;i<costList.size();i++){
				Map m=(Map)costList.get(i);
				String proNo=m.get("PRO_NO").toString();
				String cost=m.get("cost").toString();
				String phaseCode=m.get("PHASE_CODE").toString();
				
				Map setMap=new HashMap();
				setMap.put("proNo", proNo);
				setMap.put("cost", cost);
				setMap.put("phaseCode", phaseCode);
				
				returnValue=salaryFramworkDao.updateCostByProPhase(setMap)>=0;
			}
		}
		
		return returnValue;
	}
	
	
	
}
