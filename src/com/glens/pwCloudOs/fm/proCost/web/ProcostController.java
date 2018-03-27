package com.glens.pwCloudOs.fm.proCost.web;

import java.math.BigDecimal;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.elasticsearch.common.rounding.DateTimeUnit;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.glens.eap.platform.core.utils.DateTimeUtil;
import com.glens.eap.platform.core.utils.StringUtil;
import com.glens.eap.platform.core.view.EAPView;
import com.glens.eap.platform.core.web.ControllerForm;
import com.glens.eap.platform.core.web.EAPController;
import com.glens.eap.platform.framework.beans.PageBean;
import com.glens.eap.platform.framework.beans.UserToken;
import com.glens.eap.platform.framework.utils.excel.DataStructReader;
import com.glens.eap.platform.framework.utils.excel.ExcelHelper;
import com.glens.eap.platform.framework.web.EAPJsonAbstractController;
import com.glens.eap.platform.framework.web.ResponseConstants;
import com.glens.eap.platform.framework.web.support.JsonProcessRequestHandler;
import com.glens.eap.platform.framework.web.support.response.BeanResult;
import com.glens.eap.platform.framework.web.support.response.PageBeanResult;
import com.glens.eap.sys.orgEmployee.employee.service.MdEmployeeService;
import com.glens.pwCloudOs.fm.expense.service.FmFeeTypeService;
import com.glens.pwCloudOs.hrm.salMgr.salaryFramwork.service.SalaryFramworkService;
import com.glens.pwCloudOs.materielMg.assetMg.asset.service.AssetService;
import com.glens.pwCloudOs.om.deviceMgr.device.service.DeviceService;
import com.glens.pwCloudOs.pm.baseMgr.pmBase.service.PmBaseService;
import com.glens.pwCloudOs.pm.pb.budgetEstimate.service.BudgetEstimateService;
import com.glens.pwCloudOs.pm.schedulePlan.scheduleDaily.service.PmDayWordloadService;
import com.glens.pwCloudOs.pm.schedulePlan.worklist.service.PmWorkHoursService;

@SuppressWarnings("all")
@RequestMapping("pmsServices/fm/proCost")
public class ProcostController extends EAPJsonAbstractController {

	private PmBaseService pmBaseService;

	private FmFeeTypeService fmFeeTypeService;

	private BudgetEstimateService budgetEstimateService;

	private SalaryFramworkService salaryFramworkService;

	private DeviceService deviceService;

	private MdEmployeeService mdEmployeeService;

	private AssetService assetService;

	private PmWorkHoursService pmWorkHoursService;

	private PmDayWordloadService pmDayWordloadService;

	/**
	 * 实际成本明细（多项目）
	 */
	@RequestMapping(value = "list", method = RequestMethod.GET)
	public Object list(HttpServletRequest request, HttpServletResponse response) {
		return this.process(request, response, new JsonProcessRequestHandler() {
			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				String currentPage = request.getParameter("currentPage");
				String perpage = request.getParameter("perpage");


				String fromDate = request.getParameter("fromDate");
				String toDate = request.getParameter("toDate");
				String lastDate="";
				try {
					lastDate=DateTimeUtil.lastDayOfDate(toDate);
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				// 查询开始日期和结束日期
				//String starttime = request.getParameter("starttime");
				//String endtime = request.getParameter("queryDate");

				// 日期
				//starttime = "2000-01-01";

				// 项目编号
				String proNos = request.getParameter("proNos");
				List<String> proNosList = new ArrayList<String>();
				if (StringUtil.isNotNull(proNos)) {
					for (String proNo : proNos.split(",")) {
						proNosList.add(proNo);
					}
				}

				// 年度
				String year = request.getParameter("year");

				// 地址
				String address = request.getParameter("address");

				// 部门
				String unitCode = request.getParameter("unitCode");

				// 项目类型
				String categoryCode = request.getParameter("categoryCode");

				Map<String, Object> paramsMap = new HashMap<String, Object>();
				paramsMap.put("currentPage", currentPage);
				paramsMap.put("perpage", perpage);
				paramsMap.put("queryDate", lastDate);
				paramsMap.put("proNosList", proNosList);
				paramsMap.put("year", year);
				paramsMap.put("address", address);
				paramsMap.put("unitCode", unitCode);
				paramsMap.put("categoryCode", categoryCode);

				PageBean page = pmBaseService.queryProCostForPage(paramsMap);
				PageBeanResult result = new PageBeanResult();
				Map<String, Object> pm = new HashMap<String, Object>();
				if (page != null) {

					// 动态标题（费用报销科目)
					List<Map<String, Object>> ftList = fmFeeTypeService
							.queryAllFeeType();

					List headerList = new ArrayList();

					for (Map m : ftList) {
						headerList.add(m.get("FEE_NAME"));
					}
					page.getHeaderList().addAll(headerList);

					List<Map<String, Object>> list = page.getList();

					for (Map m1 : list) {

						String budgetRate = "0";
						pm.put("proNo", m1.get("PRO_NO"));
						pm.put("companyCode", "001");
						pm.put("queryDate", lastDate);
							
					


						//starttime = "2000-01-01";
						//Map<String, Object> paramsMap = new HashMap<String, Object>();
						paramsMap.put("proNo", m1.get("PRO_NO"));
						paramsMap.put("starttime", fromDate+"-01");
						try {
							paramsMap.put("endtime", DateTimeUtil.lastDayOfDate(toDate));
						} catch (ParseException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

						//Map m = pmBaseService.getProCost(paramsMap);


						List budgetList=budgetEstimateService.getBUdgetByProNo(paramsMap);
						String budgetEstimateCode="";
						String versionCode="";
						double contractValue=0;
						if(budgetList!=null && budgetList.size()>0){
							Map mm=(Map)budgetList.get(0);
							budgetEstimateCode=mm.get("BUDGETESTIMATE_CODE").toString();
							versionCode=mm.get("VERSION_CODE").toString();
							m1.put("CONTRACT_VALUE", mm.get("CONTRACT_VALUE"));
							
							if(mm.get("CONTRACT_VALUE")!=null){
								contractValue=Double.parseDouble(mm.get("CONTRACT_VALUE").toString());
							}
						}

						pm.put("budgetEstimateCode", budgetEstimateCode);
						pm.put("versionCode", versionCode);
						Map resultMap =null;
						if(!versionCode.equals("")){
							resultMap = budgetEstimateService
									.proBudgetTotal(pm);

						}

						Map m = (Map) pmDayWordloadService.getProWorkLoad(pm);
						// 累计完工进度(%)
						m1.put("COMPLETION",
								m == null ? 0 : m.get("COMPLETION"));
						
						String completion=""; //完工进度
						if(m!=null && m.get("COMPLETION")!=null){
							completion=m.get("COMPLETION").toString();
						}

						
						
						
						// 时间进度
						Float timeProcess = pmBaseService.getProTimeProgress(
								m1.get("PRO_NO") + "", lastDate);
						m1.put("timeProcess", timeProcess == null ? 0
								: get2Float(timeProcess * 100) + "%");

						if (resultMap != null) {
							budgetRate = (Double) resultMap
									.get("expectedProfitrate") + "";
						}

						// 项目各科目成本费用
						paramsMap.put("proNo", m1.get("PRO_NO"));
						List costList = pmBaseService
								.queryFeeTypeCostList(paramsMap);

						// 概算利润率
						m1.put("BUDGET_RATE", budgetRate);



						Map queryMap=new HashMap();
						queryMap.put("proNo", m1.get("PRO_NO"));
						queryMap.put("starttime", fromDate);
						queryMap.put("endtime", toDate);
						System.out.println(1);
						List<Map<String, Object>>  getProCost=budgetEstimateService.getProCost(queryMap);

						// 各种费用费用
						m1.put("COST_LIST", getProCost);

						//String totalCost = addItemsCost(humanCost, getProCost);

						List getProTotalCost=budgetEstimateService.getProCost(queryMap);

						double totalCost=0;
						String humanCost="";

						for(int i=0;i<getProTotalCost.size();i++){
							Map tp=(Map)getProTotalCost.get(i);
							if(tp.get("ITEM_CODE").toString().equals("C001")){
								humanCost=tp.get("COST_VALUE").toString();
							}

							double costValue=Double.parseDouble(tp.get("COST_VALUE").toString());
							totalCost+=costValue;
						}

						// 累计支出成本
						m1.put("TOTAL_COST", totalCost);

//						当前项目进度利润率=（预估总价*完工进度-实际成本）/(预估总价*完工进度)
						
						
						// 当前进度利润率
						m1.put("CURRENT_RATE",(contractValue*10-totalCost)/(contractValue*10));

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

	/**
	 * 实际成本明细（多项目）导出
	 */
	@RequestMapping(value = "listExport", method = RequestMethod.GET)
	public Object listExport(HttpServletRequest request,
			HttpServletResponse response) {

		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {

				String currentPage = request.getParameter("currentPage");
				String perpage = request.getParameter("perpage");

				String fromDate = request.getParameter("fromDate");
				String toDate = request.getParameter("toDate");
				String endtime="";
				try {
					endtime=DateTimeUtil.lastDayOfDate(toDate);
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				String starttime=fromDate+"-01";



				// 项目编号
				String proNos = request.getParameter("proNos");
				List<String> proNosList = new ArrayList<String>();
				if (StringUtil.isNotNull(proNos)) {
					for (String proNo : proNos.split(",")) {
						proNosList.add(proNo);
					}
				}

				// 年度
				String year = request.getParameter("year");

				// 地址
				String address = request.getParameter("address");

				// 部门
				String unitCode = request.getParameter("unitCode");

				// 项目类型
				String categoryCode = request.getParameter("categoryCode");

				Map<String, Object> paramsMap = new HashMap<String, Object>();
				paramsMap.put("currentPage", currentPage);
				paramsMap.put("perpage", perpage);
				paramsMap.put("queryDate", endtime);
				paramsMap.put("proNosList", proNosList);
				paramsMap.put("year", year);
				paramsMap.put("address", address);
				paramsMap.put("unitCode", unitCode);
				paramsMap.put("categoryCode", categoryCode);

				List<Map<String, Object>> list = pmBaseService
						.queryProCostForList(paramsMap);

				PageBeanResult result = new PageBeanResult();
				Map<String, Object> pm = new HashMap<String, Object>();

				// 动态标题（费用报销科目)
				List<Map<String, Object>> ftList = fmFeeTypeService
						.queryAllFeeType();

				List headerList = new ArrayList();

				for (Map m : ftList) {
					headerList.add(m.get("FEE_NAME"));
				}

				Map<String, Object> map = new HashMap<String, Object>();

				map.put("headerList", headerList);

				for (Map m1 : list) {
					String budgetRate = "0";
					pm.put("proNo", m1.get("PRO_NO"));
					pm.put("companyCode", "001");
					pm.put("queryDate", endtime);



					paramsMap.put("proNo", m1.get("PRO_NO"));
					paramsMap.put("starttime", fromDate+"-01");
					try {
						paramsMap.put("endtime", DateTimeUtil.lastDayOfDate(toDate));
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

					//Map m = pmBaseService.getProCost(paramsMap);


					List budgetList=budgetEstimateService.getBUdgetByProNo(paramsMap);
					String budgetEstimateCode="";
					String versionCode="";
					double contractValue=0;
					if(budgetList!=null && budgetList.size()>0){
						Map mm=(Map)budgetList.get(0);
						budgetEstimateCode=mm.get("BUDGETESTIMATE_CODE").toString();
						versionCode=mm.get("VERSION_CODE").toString();
						if(mm.get("CONTRACT_VALUE")!=null){
							contractValue=Double.parseDouble(mm.get("CONTRACT_VALUE").toString());
						}
					}

					pm.put("budgetEstimateCode", budgetEstimateCode);
					pm.put("versionCode", versionCode);
					Map resultMap =null;
					if(!versionCode.equals("")){
						resultMap = budgetEstimateService
								.proBudgetTotal(pm);

					}



					Map m = (Map) pmDayWordloadService.getProWorkLoad(pm);
					// 累计完工进度(%)
					m1.put("COMPLETION", m == null ? 0 : m.get("COMPLETION"));

					String completion=""; //完工进度  
					if(m!=null && m.get("COMPLETION")!=null){
						completion=m.get("COMPLETION").toString();
					}
					
					// 时间进度
					Float timeProcess = pmBaseService.getProTimeProgress(
							m1.get("PRO_NO") + "", endtime);
					m1.put("timeProcess", timeProcess == null ? 0
							: get2Float(timeProcess * 100) + "%");

					if (resultMap != null) {
						budgetRate = (Double) resultMap
								.get("expectedProfitrate") + "";
					}

					// 项目各科目成本费用
					paramsMap.put("proNo", m1.get("PRO_NO"));
					List costList = pmBaseService
							.queryFeeTypeCostList(paramsMap);

					// 概算利润率
					m1.put("BUDGET_RATE", budgetRate);
					Map queryMap=new HashMap();
					queryMap.put("proNo", m1.get("PRO_NO"));
					queryMap.put("starttime", fromDate);
					queryMap.put("endtime", toDate);
					System.out.println(1);
					List<Map<String, Object>>  getProCost=budgetEstimateService.getProCost(queryMap);

					// 各种费用费用
					m1.put("COST_LIST", getProCost);

					//String totalCost = addItemsCost(humanCost, getProCost);

					List getProTotalCost=budgetEstimateService.getProCost(queryMap);

					double totalCost=0;
					String humanCost="";

					for(int i=0;i<getProTotalCost.size();i++){
						Map tp=(Map)getProTotalCost.get(i);
						if(tp.get("ITEM_CODE").toString().equals("C001")){
							humanCost=tp.get("COST_VALUE").toString();
						}

						double costValue=Double.parseDouble(tp.get("COST_VALUE").toString());
						totalCost+=costValue;
					}

					// 累计支出成本
					m1.put("TOTAL_COST", totalCost);

					// 当前进度利润率
					
					m1.put("CURRENT_RATE",(contractValue*10-totalCost)/(contractValue*10));
				}

				map.put("list", list);

				return map;

			}

			@Override
			public Object doWithFinish(HttpServletRequest request,
					HttpServletResponse response, Object data, String viewType,
					EAPController controller) {
				// TODO Auto-generated method stub

				ModelAndView modelAndView = new ModelAndView();
				EAPView view = new EAPView() {

					@Override
					public void render(Map<String, ?> model,
							HttpServletRequest request,
							HttpServletResponse response) throws Exception {
						response.setCharacterEncoding("UTF-8");
						String fileName = URLEncoder.encode("实际成本明细（多项目）",
								"UTF-8");

						response.setContentType("application/ms-excel;charset=UTF-8");
						response.addHeader("Content-Disposition",
								"attachment;filename=" + fileName
								+ ".xlsx;charset=UTF-8");
						ExcelHelper excelHelper = new ExcelHelper();
						excelHelper.setSheetName("实际成本明细（多项目）");
						Map<String, Object> resultMap = (Map<String, Object>) data;

						List headerList = new ArrayList();

						List<Map<String, Object>> dataList = (List<Map<String, Object>>) resultMap
								.get("list");
						if(dataList.size()>0){
							headerList=(List)dataList.get(0).get("COST_LIST");
						}

						Map<String, Map<String, Object>> dataStruct = DataStructReader
								.getDataStruct();
						Map<String, Object> beanStruct = dataStruct
								.get("actualCostListExport");
						List<Map<String, Object>> properties = (List<Map<String, Object>>) beanStruct
								.get("properties");

						for (int i = properties.size() - 1; i >= 0; i--) {

							Map<String, Object> propertyItem = properties
									.get(i);
							if ((propertyItem.get("cell_flag") != null
									&& "1".equals(propertyItem.get("cell_flag"))) ||propertyItem.get("name").equals("HUMAN_COST")) {

								properties.remove(i);
							}
						}

						int startIndex = 18;

						if (headerList != null && headerList.size() > 0) {
							for (int i = 0; i < headerList.size(); i++) {
								Map m=(Map)headerList.get(i);
								String title = m.get("ITEM_NAME").toString();
								Map<String, Object> m1 = new HashMap<String, Object>();
								m1.put("name", "ZZ" + i);
								m1.put("type", "java.lang.String");
								m1.put("cell-type", "CELL_TYPE_STRING");
								m1.put("comment", title);
								m1.put("cell_flag", "1");
								properties.add(startIndex + 1, m1);
								startIndex++;
							}
						}

						for (int k = 0; k < dataList.size(); k++) {
							Map m2 = dataList.get(k);
							List list2 = (List) m2.get("COST_LIST");
							for (int j = 0; j < list2.size(); j++) {
								Map temp=(Map)list2.get(j);
								m2.put("ZZ" + j, temp.get("COST_VALUE"));
							}
						}

						for (int i = 0; i < properties.size(); i++) {

							Map<String, Object> propertyItem = properties
									.get(i);
							propertyItem.put("col-index", i);
						}

						excelHelper.writeData("actualCostListExport",
								response.getOutputStream(), Map.class, dataList);

					}

					@Override
					public String getContentType() {
						return "application/vnd.ms-excel;charset=UTF-8";
					}
				};

				view.setData(data);
				modelAndView.setView(view);

				return modelAndView;
			}
		});
	}

	/**
	 * 实际成本明细（单项目）
	 */
	@RequestMapping(value = "get", method = RequestMethod.GET)
	public Object get(HttpServletRequest request, HttpServletResponse response) {
		return this.process(request, response, new JsonProcessRequestHandler() {
			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				String proNo = request.getParameter("proNo");
				// 查询开始日期和结束日期
				String fromDate = request.getParameter("fromDate");
				String toDate = request.getParameter("toDate");
				String lastDate="";
				try {
					lastDate=DateTimeUtil.lastDayOfDate(toDate);
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				//starttime = "2000-01-01";
				Map<String, Object> paramsMap = new HashMap<String, Object>();
				paramsMap.put("proNo", proNo);
				paramsMap.put("starttime", fromDate+"-01");
				try {
					paramsMap.put("endtime", DateTimeUtil.lastDayOfDate(toDate));
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				Map m = pmBaseService.getProCost(paramsMap);
				

				List list=budgetEstimateService.getBUdgetByProNo(paramsMap);
				String budgetEstimateCode="";
				String versionCode="";
				double contractValue=0;
				if(list!=null && list.size()>0){
					Map mm=(Map)list.get(0);
					budgetEstimateCode=mm.get("BUDGETESTIMATE_CODE").toString();
					versionCode=mm.get("VERSION_CODE").toString();
					if(mm.get("CONTRACT_VALUE")!=null){
						contractValue=Double.parseDouble(mm.get("CONTRACT_VALUE").toString());
					}
				}


				BeanResult result = new BeanResult();
				if (m != null) {
					// 加入动态标题（费用报销科目)
					List<Map<String, Object>> ftList = fmFeeTypeService
							.queryAllFeeType();
					// 项目各科目成本费用
					List costList = pmBaseService
							.queryFeeTypeCostList(paramsMap);

					// 概算利润率
					String budgetRate = "0";
					Map pm = new HashMap();
					pm.put("proNo", proNo);
					pm.put("companyCode", "001");
					pm.put("queryDate", toDate);



					pm.put("budgetEstimateCode", budgetEstimateCode);
					pm.put("versionCode", versionCode);
					Map resultMap=null;
					if(!budgetEstimateCode.equals("")){
						resultMap= budgetEstimateService.proBudgetTotal(pm);
					}


					Map m3 = (Map) pmDayWordloadService.getProWorkLoad(pm);
					// 累计完工进度(%)
					m.put("COMPLETION", m3 == null ? 0 : m3.get("COMPLETION"));

					String completion=""; //完工进度  
					if(m!=null && m.get("COMPLETION")!=null){
						completion=m.get("COMPLETION").toString();
					}
					
					// 时间进度
					Float timeProcess = pmBaseService.getProTimeProgress(proNo,
							lastDate);
					m.put("timeProcess", timeProcess == null ? 0
							: get2Float(timeProcess * 100) + "%");

					if (resultMap != null) {
						budgetRate = (Double) resultMap
								.get("expectedProfitrate") + "";
					}
					// 概算利润率
					m.put("BUDGET_RATE", budgetRate);

					Map m1 = new HashMap();
					m1.put("PRO_NO", proNo);

					Map<String, Object> proLabourCostMap = calcHumanCost(m1,
							fromDate, toDate);


					Map queryMap=new HashMap();
					queryMap.put("proNo", proNo);
					queryMap.put("starttime", fromDate);
					queryMap.put("endtime", toDate);

					List<Map<String, Object>>  getProCost=budgetEstimateService.getProCost(queryMap);



					for(int i=0;i<getProCost.size();i++){
						Map item=getProCost.get(i);
						if(item.get("ITEM_CODE").toString().equals("C001")){
							getProCost.get(i).put("itemIndex", "A1");
						}

						if(item.get("ITEM_CODE").toString().equals("C002")){
							getProCost.get(i).put("itemIndex", "A2");
						}

						if(item.get("ITEM_CODE").toString().equals("C003")){
							getProCost.get(i).put("itemIndex", "A3");
						}

						if(item.get("ITEM_CODE").toString().equals("C004")){
							getProCost.get(i).put("itemIndex", "A4.1");
						}
						if(item.get("ITEM_CODE").toString().equals("C005")){
							getProCost.get(i).put("itemIndex", "A4.2");
						}

						if(item.get("ITEM_CODE").toString().equals("C006")){
							getProCost.get(i).put("itemIndex", "A4.3");
						}

						if(item.get("ITEM_CODE").toString().equals("C007")){
							getProCost.get(i).put("itemIndex", "A4.4.1");
						}

						if(item.get("ITEM_CODE").toString().equals("C008")){
							getProCost.get(i).put("itemIndex", "A4.4.2");
						}

						if(item.get("ITEM_CODE").toString().equals("C009")){
							getProCost.get(i).put("itemIndex", "A4.4.3");
						}

						if(item.get("ITEM_CODE").toString().equals("C010")){
							getProCost.get(i).put("itemIndex", "A4.4.4");
						}

						if(item.get("ITEM_CODE").toString().equals("C011")){
							getProCost.get(i).put("itemIndex", "A4.4.5");
						}
						if(item.get("ITEM_CODE").toString().equals("C012")){
							getProCost.get(i).put("itemIndex", "A4.4.6");
						}
						if(item.get("ITEM_CODE").toString().equals("C013")){
							getProCost.get(i).put("itemIndex", "A4.4.7");
						}
						if(item.get("ITEM_CODE").toString().equals("C014")){
							getProCost.get(i).put("itemIndex", "A4.4.8");
						}
						if(item.get("ITEM_CODE").toString().equals("C015")){
							getProCost.get(i).put("itemIndex", "A4.4.9");
						}
						if(item.get("ITEM_CODE").toString().equals("C016")){
							getProCost.get(i).put("itemIndex", "A4.4.10");
						}
						if(item.get("ITEM_CODE").toString().equals("C017")){
							getProCost.get(i).put("itemIndex", "A4.4.11");
						}
						if(item.get("ITEM_CODE").toString().equals("C018")){
							getProCost.get(i).put("itemIndex", "A4.4.12");
						}
						if(item.get("ITEM_CODE").toString().equals("C019")){
							getProCost.get(i).put("itemIndex", "A4.4.13");
						}
						if(item.get("ITEM_CODE").toString().equals("C020")){
							getProCost.get(i).put("itemIndex", "A4.4.14");
						}
						if(item.get("ITEM_CODE").toString().equals("C021")){
							getProCost.get(i).put("itemIndex", "A4.4.15");
						}

					}

					if(getProCost.size()>2){
						Map tempa=new HashMap();
						tempa.put("ITEM_NAME", "其他成本");
						tempa.put("itemIndex", "A4");
						getProCost.add(3,tempa);
					}
					if(getProCost.size()>6){
						Map tempb=new HashMap();
						tempb.put("ITEM_NAME", "其他-----");
						tempb.put("itemIndex", "A4.4");
						getProCost.add(7,tempb);
					}




					// 各种费用费用
					m.put("COST_LIST", getProCost);

					List getProTotalCost=budgetEstimateService.getProCost(queryMap);

					double totalCost=0;
					String humanCost="";

					for(int i=0;i<getProTotalCost.size();i++){
						Map tp=(Map)getProTotalCost.get(i);
						if(tp.get("ITEM_CODE").toString().equals("C001")){
							humanCost=tp.get("COST_VALUE").toString();
						}

						double costValue=Double.parseDouble(tp.get("COST_VALUE").toString());
						totalCost+=costValue;
					}



					// 累计支出成本
					m.put("TOTAL_COST", totalCost);

					// 当前进度利润率
					m.put("CURRENT_RATE",(contractValue*10-totalCost)/(contractValue*10));
					
					result.setStatusCode(ResponseConstants.OK);
					result.setResultMsg("获取成功");
					result.setData(m);
				} else {
					result.setStatusCode(ResponseConstants.NO_DATA);
					result.setResultMsg("没有获取到数据");
				}
				return result;
			}

		});
	}

	/**
	 * 实际成本汇总表
	 */
	@RequestMapping(value = "actualCostList", method = RequestMethod.GET)
	public Object actualCostList(HttpServletRequest request,
			HttpServletResponse response) {
		return this.process(request, response, new JsonProcessRequestHandler() {
			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				String currentPage = request.getParameter("currentPage");
				String perpage = request.getParameter("perpage");

				// 查询开始日期和结束日期
				String starttime = request.getParameter("starttime");
				String endtime = request.getParameter("queryDate");
				starttime = "2000-01-01";

				// 项目编号
				String proNos = request.getParameter("proNos");
				List<String> proNosList = new ArrayList<String>();
				if (StringUtil.isNotNull(proNos)) {
					for (String proNo : proNos.split(",")) {
						proNosList.add(proNo);
					}
				}

				// 项目类型
				String categoryCode = request.getParameter("categoryCode");

				Map<String, Object> paramsMap = new HashMap<String, Object>();
				paramsMap.put("currentPage", currentPage);
				paramsMap.put("perpage", perpage);
				paramsMap.put("starttime", starttime);
				paramsMap.put("endtime", endtime);
				paramsMap.put("proNosList", proNosList);

				paramsMap.put("categoryCode", categoryCode);

				PageBean page = pmBaseService.queryProCostForPage(paramsMap);
				PageBeanResult result = new PageBeanResult();
				Map<String, Object> pm = new HashMap<String, Object>();
				String companyCode = "001";
				UserToken token = getToken(request);
				if (token != null) {

					companyCode = token.getCompanyCode();
				}

				if (page != null) {

					List<Map<String, Object>> list = page.getList();

					List<Map<String, Object>> ftList = fmFeeTypeService
							.queryAllFeeType();

					for (Map m1 : list) {

						String budgetRate = "0";
						pm.put("proNo", m1.get("PRO_NO"));
						pm.put("companyCode", companyCode);
						pm.put("queryDate", endtime);


						
						


						List budgetList=budgetEstimateService.getBUdgetByProNo(pm);
						String budgetEstimateCode="";
						String versionCode="";
						double contractValue=0;
						if(budgetList!=null && budgetList.size()>0){
							Map mm=(Map)budgetList.get(0);
							budgetEstimateCode=mm.get("BUDGETESTIMATE_CODE").toString();
							versionCode=mm.get("VERSION_CODE").toString();
							if(mm.get("CONTRACT_VALUE")!=null){
								contractValue=Double.parseDouble(mm.get("CONTRACT_VALUE").toString());
							}
						}

						pm.put("budgetEstimateCode", budgetEstimateCode);
						pm.put("versionCode", versionCode);
						Map resultMap =null;
						if(!versionCode.equals("")){
							resultMap = budgetEstimateService
									.proBudgetTotal(pm);

						}


						
						

						Map m = (Map) pmDayWordloadService.getProWorkLoad(pm);
						// 累计完工进度(%)
						m1.put("COMPLETION",
								m == null ? 0 : m.get("COMPLETION"));

						String completion=""; //完工进度  
						if(m!=null && m.get("COMPLETION")!=null){
							completion=m.get("COMPLETION").toString();
						}
						
						// 时间进度
						Float timeProcess = pmBaseService.getProTimeProgress(
								m1.get("PRO_NO") + "", endtime);
						m1.put("timeProcess", timeProcess == null ? 0
								: get2Float(timeProcess * 100) + "%");

						if (resultMap != null) {
							budgetRate = (Double) resultMap
									.get("expectedProfitrate") + "";
						}

						paramsMap.put("proNo", m1.get("PRO_NO"));
						List costList = pmBaseService
								.queryFeeTypeCostList(paramsMap);

						// 概算利润率
						m1.put("BUDGET_RATE", budgetRate);


						List getProTotalCost=budgetEstimateService.getProTotalCost(paramsMap);

						double totalCost=0;
						String humanCost="";

						for(int i=0;i<getProTotalCost.size();i++){
							Map tp=(Map)getProTotalCost.get(i);
							if(tp.get("ITEM_CODE").toString().equals("C001")){
								humanCost=tp.get("COST_VALUE").toString();
							}

							double costValue=Double.parseDouble(tp.get("COST_VALUE").toString());
							totalCost+=costValue;
						}

						// 累计支出成本
						m1.put("TOTAL_COST", totalCost);

						// 当前进度利润率
						m1.put("CURRENT_RATE",(contractValue*10-totalCost)/(contractValue*10));
						

						// 累计预算成本
						String bugetCost = calcBugetCost(m1, starttime, endtime);
						m1.put("BUGET_COST", bugetCost);

						// 差异
						m1.put("DIFFERENCE",
								Float.parseFloat(bugetCost)
								- Float.parseFloat(totalCost+"")
								- Float.parseFloat(bugetCost));
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

	/**
	 * 实际成本汇总表 导出
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "actualCostExport", method = RequestMethod.GET)
	public Object actualCostExport(HttpServletRequest request,
			HttpServletResponse response) {

		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// 查询开始日期和结束日期
				String starttime = request.getParameter("starttime");
				String endtime = request.getParameter("queryDate");
				starttime = "2000-01-01";

				// 项目编号
				String proNos = request.getParameter("proNos");
				List<String> proNosList = new ArrayList<String>();
				if (StringUtil.isNotNull(proNos)) {
					for (String proNo : proNos.split(",")) {
						proNosList.add(proNo);
					}
				}

				// 项目类型
				String categoryCode = request.getParameter("categoryCode");

				Map<String, Object> paramsMap = new HashMap<String, Object>();
				paramsMap.put("starttime", starttime);
				paramsMap.put("endtime", endtime);
				paramsMap.put("proNosList", proNosList);

				paramsMap.put("categoryCode", categoryCode);

				List<Map<String, Object>> list = pmBaseService
						.queryProCostForList(paramsMap);
				PageBeanResult result = new PageBeanResult();
				Map<String, Object> pm = new HashMap<String, Object>();
				String companyCode = "001";
				UserToken token = getToken(request);
				if (token != null) {

					companyCode = token.getCompanyCode();
				}

				List<Map<String, Object>> ftList = fmFeeTypeService
						.queryAllFeeType();

				for (Map m1 : list) {

					String budgetRate = "0";
					pm.put("proNo", m1.get("PRO_NO"));
					pm.put("companyCode", companyCode);
					pm.put("queryDate", endtime);


					List budgetList=budgetEstimateService.getBUdgetByProNo(pm);
					String budgetEstimateCode="";
					String versionCode="";
					double contractValue=0;
					if(budgetList!=null && budgetList.size()>0){
						Map mm=(Map)budgetList.get(0);
						budgetEstimateCode=mm.get("BUDGETESTIMATE_CODE").toString();
						versionCode=mm.get("VERSION_CODE").toString();
						if(mm.get("CONTRACT_VALUE")!=null){
							contractValue=Double.parseDouble(mm.get("CONTRACT_VALUE").toString());
						}
					}

					pm.put("budgetEstimateCode", budgetEstimateCode);
					pm.put("versionCode", versionCode);
					Map resultMap =null;
					if(!versionCode.equals("")){
						resultMap = budgetEstimateService
								.proBudgetTotal(pm);

					}


					//Map resultMap = budgetEstimateService.proBudgetTotal(pm);

					Map m = (Map) pmDayWordloadService.getProWorkLoad(pm);
					// 累计完工进度(%)
					m1.put("COMPLETION", m == null ? 0 : m.get("COMPLETION"));

					String completion=""; //完工进度  
					if(m!=null && m.get("COMPLETION")!=null){
						completion=m.get("COMPLETION").toString();
					}
					// 时间进度
					Float timeProcess = pmBaseService.getProTimeProgress(
							m1.get("PRO_NO") + "", endtime);
					m1.put("timeProcess", timeProcess == null ? 0
							: get2Float(timeProcess * 100)+ "%");

					if (resultMap != null) {
						budgetRate = (Double) resultMap
								.get("expectedProfitrate") + "";
					}

					paramsMap.put("proNo", m1.get("PRO_NO"));
					List costList = pmBaseService
							.queryFeeTypeCostList(paramsMap);

					// 概算利润率
					m1.put("BUDGET_RATE", budgetRate);

					List getProTotalCost=budgetEstimateService.getProTotalCost(paramsMap);

					double totalCost=0;
					String humanCost="";

					for(int i=0;i<getProTotalCost.size();i++){
						Map tp=(Map)getProTotalCost.get(i);
						if(tp.get("ITEM_CODE").toString().equals("C001")){
							humanCost=tp.get("COST_VALUE").toString();
						}

						double costValue=Double.parseDouble(tp.get("COST_VALUE").toString());
						totalCost+=costValue;
					}

					// 累计支出成本
					m1.put("TOTAL_COST", totalCost);

					// 当前进度利润率
					
					m1.put("CURRENT_RATE",(contractValue*10-totalCost)/(contractValue*10));

					// 累计预算成本
					String bugetCost = calcBugetCost(m1, starttime, endtime);
					m1.put("BUGET_COST", bugetCost);

					// 差异
					m1.put("DIFFERENCE",
							Float.parseFloat(bugetCost)
							- Float.parseFloat(totalCost+"")
							- Float.parseFloat(bugetCost));
				}

				return list;
			}

			@Override
			public Object doWithFinish(HttpServletRequest request,
					HttpServletResponse response, Object data, String viewType,
					EAPController controller) {
				// TODO Auto-generated method stub

				ModelAndView modelAndView = new ModelAndView();
				EAPView view = new EAPView() {

					@Override
					public void render(Map<String, ?> model,
							HttpServletRequest request,
							HttpServletResponse response) throws Exception {
						response.setCharacterEncoding("UTF-8");
						String fileName = URLEncoder.encode("实际成本汇总表", "UTF-8");

						response.setContentType("application/ms-excel;charset=UTF-8");
						response.addHeader("Content-Disposition",
								"attachment;filename=" + fileName
								+ ".xlsx;charset=UTF-8");
						ExcelHelper excelHelper = new ExcelHelper();
						excelHelper.setSheetName("实际成本汇总表");
						List<Map<String, Object>> dataList = (List<Map<String, Object>>) data;
						excelHelper.writeData("actualCostExport",
								response.getOutputStream(), Map.class, dataList);

					}

					@Override
					public String getContentType() {
						return "application/vnd.ms-excel;charset=UTF-8";
					}
				};

				view.setData(data);
				modelAndView.setView(view);

				return modelAndView;
			}
		});
	}

	/**
	 * 预算实际成本差异（多项目汇总）
	 */
	@RequestMapping(value = "bugetList", method = RequestMethod.GET)
	public Object bugetList(HttpServletRequest request,
			HttpServletResponse response) {
		return this.process(request, response, new JsonProcessRequestHandler() {
			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				String currentPage = request.getParameter("currentPage");
				String perpage = request.getParameter("perpage");

				// 查询开始日期和结束日期
				String starttime = request.getParameter("starttime");
				String endtime = request.getParameter("endtime");

				// 项目编号
				String proNos = request.getParameter("proNos");
				List<String> proNosList = new ArrayList<String>();
				if (StringUtil.isNotNull(proNos)) {
					for (String proNo : proNos.split(",")) {
						proNosList.add(proNo);
					}
				}

				// 年度
				String year = request.getParameter("year");

				// 地址
				String address = request.getParameter("address");

				// 部门
				String unitCode = request.getParameter("unitCode");

				// 项目类型
				String categoryCode = request.getParameter("categoryCode");

				Map<String, Object> paramsMap = new HashMap<String, Object>();
				paramsMap.put("currentPage", currentPage);
				paramsMap.put("perpage", perpage);
				paramsMap.put("starttime", starttime+"-01");
				
				String lastDate="";
				try {
					lastDate=DateTimeUtil.lastDayOfDate(endtime);
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				paramsMap.put("endtime", lastDate);
				paramsMap.put("proNosList", proNosList);

				paramsMap.put("year", year);
				paramsMap.put("address", address);
				paramsMap.put("unitCode", unitCode);
				paramsMap.put("categoryCode", categoryCode);

				PageBean page = pmBaseService.queryProCostForPage1(paramsMap);
				PageBeanResult result = new PageBeanResult();
				Map<String, Object> pm = new HashMap<String, Object>();
				String companyCode = "001";
				UserToken token = getToken(request);
				if (token != null) {

					companyCode = token.getCompanyCode();
				}

				if (page != null) {

					List<Map<String, Object>> list = page.getList();

					List<Map<String, Object>> ftList = fmFeeTypeService
							.queryAllFeeType();

					for (Map m1 : list) {

						String budgetRate = "0";
						pm.put("proNo", m1.get("PRO_NO"));
						pm.put("companyCode", companyCode);
						pm.put("queryDate", endtime);
						
						List budgetList=budgetEstimateService.getBUdgetByProNo(pm);
						String budgetEstimateCode="";
						String versionCode="";
						double contractValue=0;
						if(budgetList!=null && budgetList.size()>0){
							Map mm=(Map)budgetList.get(0);
							budgetEstimateCode=mm.get("BUDGETESTIMATE_CODE").toString();
							versionCode=mm.get("VERSION_CODE").toString();
							m1.put("CONTRACT_VALUE", mm.get("CONTRACT_VALUE"));
							if(mm.get("CONTRACT_VALUE")!=null){
								contractValue=Double.parseDouble(mm.get("CONTRACT_VALUE").toString());
							}
						}

						pm.put("budgetEstimateCode", budgetEstimateCode);
						pm.put("versionCode", versionCode);
						
						
						List resultMap =null;
						if(!versionCode.equals("")){
							resultMap = budgetEstimateService
									.getPhaseListByPro(pm); ///////////////////////////////////////////////
						}

						Map m = (Map) pmDayWordloadService.getProWorkLoad(pm);
						// 累计完工进度(%)
						m1.put("COMPLETION",
								m == null ? 0 : m.get("COMPLETION"));

						String completion=""; //完工进度  
						if(m!=null && m.get("COMPLETION")!=null){
							completion=m.get("COMPLETION").toString();
						}
						// 时间进度
						Float timeProcess = pmBaseService.getProTimeProgress(
								m1.get("PRO_NO") + "", lastDate);
						m1.put("timeProcess", timeProcess == null ? 0
								: get2Float(timeProcess * 100)+ "%");

						/*if (resultMap != null) {
							budgetRate = (Double) resultMap
									.get("expectedProfitrate") + "";
						}*/

						paramsMap.put("proNo", m1.get("PRO_NO"));
						List costList = pmBaseService
								.queryFeeTypeCostList(paramsMap);

						// 概算利润率
						//m1.put("BUDGET_RATE", budgetRate);
						
						Map queryMap=new HashMap();
						queryMap.put("proNo", m1.get("PRO_NO"));
						queryMap.put("starttime", starttime);
						queryMap.put("endtime", endtime);

						
						
						List getProTotalCost=budgetEstimateService.getProCost(queryMap);

						double totalCost=0;
						String humanCost="";

						for(int i=0;i<getProTotalCost.size();i++){
							Map tp=(Map)getProTotalCost.get(i);
							if(tp.get("ITEM_CODE").toString().equals("C001")){
								humanCost=tp.get("COST_VALUE").toString();
							}

							double costValue=Double.parseDouble(tp.get("COST_VALUE").toString());
							totalCost+=costValue;
						}

						
						

						// 累计支出成本
						m1.put("TOTAL_COST", totalCost);

						// 当前进度利润率
						

						m1.put("CURRENT_RATE",(contractValue*10-totalCost)/(contractValue*10));
						
						
						// 累计预算成本
						double bugetCost=0;
						try {
							bugetCost=calTotalProphaseCost(resultMap,starttime,endtime);
							
							//bugetCost = calcBugetCost1(pm, starttime, endtime);
						} catch (ParseException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
						
						BigDecimal   b1   =   new   BigDecimal(bugetCost);  
					
						double   f1   =   b1.setScale(2,   BigDecimal.ROUND_HALF_UP).doubleValue(); 
						
						m1.put("BUGET_COST", f1);

						
						BigDecimal   b2   =   new   BigDecimal(Float.parseFloat(bugetCost+"")- Float.parseFloat(totalCost+""));  
						
						double   f2   =   b2.setScale(2,   BigDecimal.ROUND_HALF_UP).doubleValue(); 	
						// 预算与实际差异
						m1.put("DIFFERENCE",f2);
						
						
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

	/**
	 * 预算实际成本差异（多项目汇总） 导出
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "bugetListExport", method = RequestMethod.GET)
	public Object bugetListExport(HttpServletRequest request,
			HttpServletResponse response) {

		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {

				String currentPage = request.getParameter("currentPage");
				String perpage = request.getParameter("perpage");

				// 查询开始日期和结束日期
				String starttime = request.getParameter("starttime");
				String endtime = request.getParameter("endtime");
				String lastDate="";
				try {
					lastDate=DateTimeUtil.lastDayOfDate(endtime);
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				// 项目编号
				String proNos = request.getParameter("proNos");
				List<String> proNosList = new ArrayList<String>();
				if (StringUtil.isNotNull(proNos)) {
					for (String proNo : proNos.split(",")) {
						proNosList.add(proNo);
					}
				}

				// 年度
				String year = request.getParameter("year");

				// 地址
				String address = request.getParameter("address");

				// 部门
				String unitCode = request.getParameter("unitCode");

				// 项目类型
				String categoryCode = request.getParameter("categoryCode");

				Map<String, Object> paramsMap = new HashMap<String, Object>();
				paramsMap.put("currentPage", currentPage);
				paramsMap.put("perpage", perpage);
				paramsMap.put("starttime", starttime);
				paramsMap.put("endtime", lastDate);
				paramsMap.put("proNosList", proNosList);

				paramsMap.put("year", year);
				paramsMap.put("address", address);
				paramsMap.put("unitCode", unitCode);
				paramsMap.put("categoryCode", categoryCode);

				List<Map<String, Object>> list = pmBaseService
						.queryProCostForList(paramsMap);
				Map<String, Object> pm = new HashMap<String, Object>();
				String companyCode = "001";
				UserToken token = getToken(request);
				if (token != null) {

					companyCode = token.getCompanyCode();
				}

				List<Map<String, Object>> ftList = fmFeeTypeService
						.queryAllFeeType();

				for (Map m1 : list) {
					String budgetRate = "0";
					pm.put("proNo", m1.get("PRO_NO"));
					pm.put("companyCode", companyCode);
					pm.put("queryDate", lastDate);
					
					List budgetList=budgetEstimateService.getBUdgetByProNo(pm);
					String budgetEstimateCode="";
					String versionCode="";
					double contractValue=0;
					if(budgetList!=null && budgetList.size()>0){
						Map mm=(Map)budgetList.get(0);
						budgetEstimateCode=mm.get("BUDGETESTIMATE_CODE").toString();
						versionCode=mm.get("VERSION_CODE").toString();
						if(mm.get("CONTRACT_VALUE")!=null){
							contractValue=Double.parseDouble(mm.get("CONTRACT_VALUE").toString());
						}
					}

					pm.put("budgetEstimateCode", budgetEstimateCode);
					pm.put("versionCode", versionCode);
					
					
					Map resultMap =null;
					if(!versionCode.equals("")){
						resultMap = budgetEstimateService
								.proBudgetTotal(pm);
					}

					Map m = (Map) pmDayWordloadService.getProWorkLoad(pm);
					// 累计完工进度(%)
					m1.put("COMPLETION",
							m == null ? 0 : m.get("COMPLETION"));

					String completion=""; //完工进度  
					if(m!=null && m.get("COMPLETION")!=null){
						completion=m.get("COMPLETION").toString();
					}

					// 时间进度
					Float timeProcess = pmBaseService.getProTimeProgress(
							m1.get("PRO_NO") + "", lastDate);
					m1.put("timeProcess", timeProcess == null ? 0
							: get2Float(timeProcess * 100)+ "%");

					if (resultMap != null) {
						budgetRate = (Double) resultMap
								.get("expectedProfitrate") + "";
					}

					paramsMap.put("proNo", m1.get("PRO_NO"));
					List costList = pmBaseService
							.queryFeeTypeCostList(paramsMap);

					// 概算利润率
					m1.put("BUDGET_RATE", budgetRate);
					
					
					
					List getProTotalCost=budgetEstimateService.getProCost(paramsMap);

					double totalCost=0;
					String humanCost="";

					for(int i=0;i<getProTotalCost.size();i++){
						Map tp=(Map)getProTotalCost.get(i);
						if(tp.get("ITEM_CODE").toString().equals("C001")){
							humanCost=tp.get("COST_VALUE").toString();
						}

						double costValue=Double.parseDouble(tp.get("COST_VALUE").toString());
						totalCost+=costValue;
					}

					
					

					// 累计支出成本
					m1.put("TOTAL_COST", totalCost);

					// 当前进度利润率
					

					m1.put("CURRENT_RATE",(contractValue*10-totalCost)/(contractValue*10));
					
					
					// 累计预算成本
					double bugetCost=0;
					try {
						bugetCost = calcBugetCost1(pm, starttime, endtime);
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					
					BigDecimal   b1   =   new   BigDecimal(bugetCost);  
				
					double   f1   =   b1.setScale(2,   BigDecimal.ROUND_HALF_UP).doubleValue(); 
					
					m1.put("BUGET_COST", f1);

					
					BigDecimal   b2   =   new   BigDecimal(Float.parseFloat(bugetCost+"")- Float.parseFloat(totalCost+""));  
					
					double   f2   =   b2.setScale(2,   BigDecimal.ROUND_HALF_UP).doubleValue(); 	
					// 预算与实际差异
					m1.put("DIFFERENCE",f2);
				}

				return list;
			}

			@Override
			public Object doWithFinish(HttpServletRequest request,
					HttpServletResponse response, Object data, String viewType,
					EAPController controller) {
				// TODO Auto-generated method stub

				ModelAndView modelAndView = new ModelAndView();
				EAPView view = new EAPView() {

					@Override
					public void render(Map<String, ?> model,
							HttpServletRequest request,
							HttpServletResponse response) throws Exception {
						response.setCharacterEncoding("UTF-8");
						String fileName = URLEncoder.encode("预算实际成本差异（多项目汇总）",
								"UTF-8");

						response.setContentType("application/ms-excel;charset=UTF-8");
						response.addHeader("Content-Disposition",
								"attachment;filename=" + fileName
								+ ".xlsx;charset=UTF-8");
						ExcelHelper excelHelper = new ExcelHelper();
						excelHelper.setSheetName("预算实际成本差异（多项目汇总）");
						List<Map<String, Object>> dataList = (List<Map<String, Object>>) data;
						excelHelper.writeData("bugetListExport",
								response.getOutputStream(), Map.class, dataList);

					}

					@Override
					public String getContentType() {
						return "application/vnd.ms-excel;charset=UTF-8";
					}
				};

				view.setData(data);
				modelAndView.setView(view);

				return modelAndView;
			}
		});
	}

	/**
	 * 预算实际成本差异（单项目明细）
	 */
	@RequestMapping(value = "bugetGet", method = RequestMethod.GET)
	public Object bugetGet(HttpServletRequest request,
			HttpServletResponse response) {
		return this.process(request, response, new JsonProcessRequestHandler() {
			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				String proNo = request.getParameter("proNo");
				// 查询开始日期和结束日期
				String starttime = request.getParameter("starttime");
				String endtime = request.getParameter("endtime");

				Map<String, Object> paramsMap = new HashMap<String, Object>();
				paramsMap.put("proNo", proNo);
				paramsMap.put("starttime", starttime+"-01");
				String lastDate="";
				try {
					lastDate=DateTimeUtil.lastDayOfDate(endtime);
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				paramsMap.put("endtime", lastDate);

				Map m = pmBaseService.getProCost(paramsMap);
				BeanResult result = new BeanResult();
				Map<String, Object> pm = new HashMap<String, Object>();
				if (m != null) {

//					List<Map<String, Object>> ftList = fmFeeTypeService
//							.queryAllFeeType();
//
//					// 项目各科目实际成本费用
//					List costList = pmBaseService
//							.queryFeeTypeCostList(paramsMap);
//
//					// 费用报销科目，来自预算科目表pm_pb_item
//					List<Map<String, Object>> itemsList = pmBaseService
//							.queryAllFeeItem();
//
//					// 项目各科目预算费用
//					List budgetCostList = budgetEstimateService
//							.queryFeeItemsCostList(paramsMap);

					String budgetRate = "0";
					pm.put("proNo", m.get("PRO_NO"));
					pm.put("companyCode", "001");
					pm.put("queryDate", endtime);

					List budgetList=budgetEstimateService.getBUdgetByProNo(pm);
					String budgetEstimateCode="";
					String versionCode="";
					double contractValue=0;
					if(budgetList!=null && budgetList.size()>0){
						Map mm=(Map)budgetList.get(0);
						budgetEstimateCode=mm.get("BUDGETESTIMATE_CODE").toString();
						versionCode=mm.get("VERSION_CODE").toString();
						if(mm.get("CONTRACT_VALUE")!=null){
							contractValue=Double.parseDouble(mm.get("CONTRACT_VALUE").toString());
							m.put("contractValue", contractValue);
						}
					}

					pm.put("budgetEstimateCode", budgetEstimateCode);
					pm.put("versionCode", versionCode);
					Map resultMap =null;
					if(!versionCode.equals("")){
						resultMap = budgetEstimateService
								.proBudgetTotal(pm);
					}

					List resultMap1 =null;
					if(!versionCode.equals("")){
						resultMap1 = budgetEstimateService
								.getPhaseListByPro(pm); ///////////////////////////////////////////////
					}


					//Map resultMap = budgetEstimateService.proBudgetTotal(pm);

					Map m3 = (Map) pmDayWordloadService.getProWorkLoad(pm);
					// 累计完工进度(%)
					m.put("COMPLETION", m3 == null ? 0 : m3.get("COMPLETION"));

					String completion=""; //完工进度  
					if(m!=null && m.get("COMPLETION")!=null){
						completion=m.get("COMPLETION").toString();
					}
					
					// 时间进度
					Float timeProcess = pmBaseService.getProTimeProgress(
							m.get("PRO_NO") + "", lastDate);
					m.put("timeProcess", timeProcess == null ? 0
							: get2Float(timeProcess * 100)+ "%");

					if (resultMap != null) {
						budgetRate = (Double) resultMap
								.get("expectedProfitrate") + "";
					}

					// 概算利润率
					m.put("BUDGET_RATE", budgetRate);

					Map queryMap=new HashMap();
					queryMap.put("proNo", m.get("PRO_NO"));
					queryMap.put("starttime", starttime);
					queryMap.put("endtime", endtime);

					

					List getProTotalCost=budgetEstimateService.getProCost(queryMap);
				

					double totalCost=0;
					String humanCost="";

					for(int i=0;i<getProTotalCost.size();i++){
						Map tp=(Map)getProTotalCost.get(i);
						if(tp.get("ITEM_CODE").toString().equals("C001")){
							humanCost=tp.get("COST_VALUE").toString();
						}
						double costValue=Double.parseDouble(tp.get("COST_VALUE").toString());
						totalCost+=costValue;
					}



					// 累计支出成本
					m.put("TOTAL_COST", totalCost);

					// 当前进度利润率
					
					m.put("CURRENT_RATE",(contractValue*10-totalCost)/(contractValue*10));
					
					// 累计预算成本
					double bugetCost=0;
					try {
					
						bugetCost=calTotalProphaseCost(resultMap1,starttime,endtime);
						
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					
					BigDecimal   b1   =   new   BigDecimal(bugetCost);  
				
					double   f1   =   b1.setScale(2,   BigDecimal.ROUND_HALF_UP).doubleValue(); 
					
					m.put("BUGET_COST", f1);
					
					
					
					List list=new ArrayList();
					try {
						list=calcBugetCost2(getProTotalCost,pm, starttime, endtime);
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					

					BigDecimal   b2   =   new   BigDecimal(Float.parseFloat(bugetCost+"")- Float.parseFloat(totalCost+""));  
					
					double   f2   =   b2.setScale(2,   BigDecimal.ROUND_HALF_UP).doubleValue(); 	
					
					
					// 预算与实际差异
					m.put("DIFFERENCE",f2);

					// 各种费用费用
					m.put("COST_LIST",list);
					result.setStatusCode(ResponseConstants.OK);
					result.setResultMsg("获取成功");
					result.setData(m);
				} else {
					result.setStatusCode(ResponseConstants.NO_DATA);
					result.setResultMsg("没有获取到数据");
				}
				return result;
			}

		});
	}

	/**
	 * 人力资源成本汇总
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "peopleCostList", method = RequestMethod.GET)
	public Object peopleCostList(HttpServletRequest request,
			HttpServletResponse response) {
		return this.process(request, response, new JsonProcessRequestHandler() {
			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				String currentPage = request.getParameter("currentPage");
				String perpage = request.getParameter("perpage");

				// 查询开始日期和结束日期
				String starttime = request.getParameter("starttime");
				String endtime = request.getParameter("endtime");

				// 项目编号
				String proNos = request.getParameter("proNos");
				List<String> proNosList = new ArrayList<String>();
				if (StringUtil.isNotNull(proNos)) {
					for (String proNo : proNos.split(",")) {
						proNosList.add(proNo);
					}
				}

				// 地址
				String address = request.getParameter("address");

				// 部门
				String unitCode = request.getParameter("unitCode");

				// 项目类型
				String categoryCode = request.getParameter("categoryCode");

				Map<String, Object> paramsMap = new HashMap<String, Object>();
				paramsMap.put("currentPage", currentPage);
				paramsMap.put("perpage", perpage);
				paramsMap.put("starttime", starttime);
				paramsMap.put("endtime", endtime);
				paramsMap.put("proNosList", proNosList);

				paramsMap.put("address", address);
				paramsMap.put("unitCode", unitCode);
				paramsMap.put("categoryCode", categoryCode);

				PageBean page = pmBaseService.queryProCostForPage(paramsMap);
				PageBeanResult result = new PageBeanResult();
				Map<String, Object> pm = new HashMap<String, Object>();
				String companyCode = "001";
				UserToken token = getToken(request);
				if (token != null) {
					companyCode = token.getCompanyCode();
				}

				if (page != null) {
					List<Map<String, Object>> list = page.getList();
					for (Map m1 : list) {
						// 人力资源成本
						m1.put("companyCode", companyCode);
						Map<String, Object> proLabourCostMap = calcHumanCost(
								m1, starttime, endtime);
						m1.put("TOTAL_PEOPLE_COST", proLabourCostMap
								.get("proLabourCost") == null ? 0
										: proLabourCostMap.get("proLabourCost"));
						m1.put("AVAILABLE_PEOPLE_COST", proLabourCostMap
								.get("availabeHourCost") == null ? 0
										: proLabourCostMap.get("availabeHourCost"));
						m1.put("MONEY_PEOPLE_COST", proLabourCostMap
								.get("moneyHourCost") == null ? 0
										: proLabourCostMap.get("moneyHourCost"));

						m1.put("BEYOND_PEOPLE_COST", proLabourCostMap
								.get("beyondHourCost") == null ? 0
										: proLabourCostMap.get("beyondHourCost"));
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

	/**
	 * 人力资源汇总 导出
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "peopleCostListExport", method = RequestMethod.GET)
	public Object peopleCostListExport(HttpServletRequest request,
			HttpServletResponse response) {

		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				String currentPage = request.getParameter("currentPage");
				String perpage = request.getParameter("perpage");

				// 查询开始日期和结束日期
				String starttime = request.getParameter("starttime");
				String endtime = request.getParameter("endtime");

				// 项目编号
				String proNos = request.getParameter("proNos");
				List<String> proNosList = new ArrayList<String>();
				if (StringUtil.isNotNull(proNos)) {
					for (String proNo : proNos.split(",")) {
						proNosList.add(proNo);
					}
				}

				// 地址
				String address = request.getParameter("address");

				// 部门
				String unitCode = request.getParameter("unitCode");

				// 项目类型
				String categoryCode = request.getParameter("categoryCode");

				Map<String, Object> paramsMap = new HashMap<String, Object>();
				paramsMap.put("currentPage", currentPage);
				paramsMap.put("perpage", perpage);
				paramsMap.put("starttime", starttime);
				paramsMap.put("endtime", endtime);
				paramsMap.put("proNosList", proNosList);

				paramsMap.put("address", address);
				paramsMap.put("unitCode", unitCode);
				paramsMap.put("categoryCode", categoryCode);

				List<Map<String, Object>> list = pmBaseService
						.queryProCostForList(paramsMap);
				Map<String, Object> pm = new HashMap<String, Object>();
				String companyCode = "001";
				UserToken token = getToken(request);
				if (token != null) {
					companyCode = token.getCompanyCode();
				}
				for (Map m1 : list) {
					// 人力资源成本
					m1.put("companyCode", companyCode);
					Map<String, Object> proLabourCostMap = calcHumanCost(m1,
							starttime, endtime);
					m1.put("TOTAL_PEOPLE_COST", proLabourCostMap
							.get("proLabourCost") == null ? 0
									: proLabourCostMap.get("proLabourCost"));
					m1.put("AVAILABLE_PEOPLE_COST", proLabourCostMap
							.get("availabeHourCost") == null ? 0
									: proLabourCostMap.get("availabeHourCost"));
					m1.put("MONEY_PEOPLE_COST", proLabourCostMap
							.get("moneyHourCost") == null ? 0
									: proLabourCostMap.get("moneyHourCost"));

					m1.put("BEYOND_PEOPLE_COST", proLabourCostMap
							.get("beyondHourCost") == null ? 0
									: proLabourCostMap.get("beyondHourCost"));
				}

				return list;
			}

			@Override
			public Object doWithFinish(HttpServletRequest request,
					HttpServletResponse response, Object data, String viewType,
					EAPController controller) {

				ModelAndView modelAndView = new ModelAndView();
				EAPView view = new EAPView() {

					@Override
					public void render(Map<String, ?> model,
							HttpServletRequest request,
							HttpServletResponse response) throws Exception {
						response.setCharacterEncoding("UTF-8");
						String fileName = URLEncoder.encode("人力资源汇总", "UTF-8");

						response.setContentType("application/ms-excel;charset=UTF-8");
						response.addHeader("Content-Disposition",
								"attachment;filename=" + fileName
								+ ".xlsx;charset=UTF-8");
						ExcelHelper excelHelper = new ExcelHelper();
						excelHelper.setSheetName("人力资源汇总");
						List<Map<String, Object>> dataList = (List<Map<String, Object>>) data;
						excelHelper.writeData("peopleCostListExport",
								response.getOutputStream(), Map.class, dataList);

					}

					@Override
					public String getContentType() {
						return "application/vnd.ms-excel;charset=UTF-8";
					}
				};

				view.setData(data);
				modelAndView.setView(view);

				return modelAndView;
			}
		});
	}

	/**
	 * 人力资源成本明细
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "peopleCostDetailList", method = RequestMethod.GET)
	public Object peopleCostDetailList(HttpServletRequest request,
			HttpServletResponse response) {
		return this.process(request, response, new JsonProcessRequestHandler() {
			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				String currentPage = request.getParameter("currentPage");
				String perpage = request.getParameter("perpage");

				// 查询开始日期和结束日期
				String starttime = request.getParameter("starttime");
				String endtime = request.getParameter("endtime");

				// 项目编号
				String proNos = request.getParameter("proNos");
				List<String> proNosList = new ArrayList<String>();
				if (StringUtil.isNotNull(proNos)) {
					for (String proNo : proNos.split(",")) {
						proNosList.add(proNo);
					}
				}

				// 地址
				String address = request.getParameter("address");

				// 部门
				String unitCode = request.getParameter("unitCode");

				// 项目类型
				String categoryCode = request.getParameter("categoryCode");

				Map<String, Object> paramsMap = new HashMap<String, Object>();
				paramsMap.put("currentPage", currentPage);
				paramsMap.put("perpage", perpage);
				paramsMap.put("starttime", starttime);
				paramsMap.put("endtime", endtime);
				paramsMap.put("proNosList", proNosList);

				paramsMap.put("address", address);
				paramsMap.put("unitCode", unitCode);
				paramsMap.put("categoryCode", categoryCode);

				PageBean page = pmBaseService
						.queryProPeopleCostForPage(paramsMap);

				PageBeanResult result = new PageBeanResult();
				Map<String, Object> pm = new HashMap<String, Object>();
				String companyCode = "001";
				UserToken token = getToken(request);
				if (token != null) {
					companyCode = token.getCompanyCode();
				}

				if (page != null) {
					List<Map<String, Object>> list = page.getList();
					for (Map m1 : list) {
						// 人力资源成本
						m1.put("companyCode", companyCode);
						calcHumanCost2(m1, starttime, endtime);

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

	/**
	 * 人力资源明细导出
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "peopleCostDetailListExport", method = RequestMethod.GET)
	public Object peopleCostDetailListExport(HttpServletRequest request,
			HttpServletResponse response) {

		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {

				// 查询开始日期和结束日期
				String starttime = request.getParameter("starttime");
				String endtime = request.getParameter("endtime");

				// 项目编号
				String proNos = request.getParameter("proNos");
				List<String> proNosList = new ArrayList<String>();
				if (StringUtil.isNotNull(proNos)) {
					for (String proNo : proNos.split(",")) {
						proNosList.add(proNo);
					}
				}

				// 地址
				String address = request.getParameter("address");

				// 部门
				String unitCode = request.getParameter("unitCode");

				// 项目类型
				String categoryCode = request.getParameter("categoryCode");

				Map<String, Object> paramsMap = new HashMap<String, Object>();
				paramsMap.put("starttime", starttime);
				paramsMap.put("endtime", endtime);
				paramsMap.put("proNosList", proNosList);

				paramsMap.put("address", address);
				paramsMap.put("unitCode", unitCode);
				paramsMap.put("categoryCode", categoryCode);

				List<Map<String, Object>> list = pmBaseService
						.queryProPeopleCostForList(paramsMap);

				PageBeanResult result = new PageBeanResult();
				Map<String, Object> pm = new HashMap<String, Object>();
				String companyCode = "001";
				UserToken token = getToken(request);
				if (token != null) {
					companyCode = token.getCompanyCode();
				}

				for (Map m1 : list) {
					// 人力资源成本
					m1.put("companyCode", companyCode);
					calcHumanCost2(m1, starttime, endtime);

				}

				return list;
			}

			@Override
			public Object doWithFinish(HttpServletRequest request,
					HttpServletResponse response, Object data, String viewType,
					EAPController controller) {

				ModelAndView modelAndView = new ModelAndView();
				EAPView view = new EAPView() {

					@Override
					public void render(Map<String, ?> model,
							HttpServletRequest request,
							HttpServletResponse response) throws Exception {
						response.setCharacterEncoding("UTF-8");
						String fileName = URLEncoder.encode("人力资源明细", "UTF-8");

						response.setContentType("application/ms-excel;charset=UTF-8");
						response.addHeader("Content-Disposition",
								"attachment;filename=" + fileName
								+ ".xlsx;charset=UTF-8");
						ExcelHelper excelHelper = new ExcelHelper();
						excelHelper.setSheetName("人力资源明细");
						List<Map<String, Object>> dataList = (List<Map<String, Object>>) data;
						excelHelper
						.writeData("peopleCostDetailListExport",
								response.getOutputStream(), Map.class,
								dataList);

					}

					@Override
					public String getContentType() {
						return "application/vnd.ms-excel;charset=UTF-8";
					}
				};

				view.setData(data);
				modelAndView.setView(view);

				return modelAndView;
			}
		});
	}

	/**
	 * 
	 * @param m
	 * @param costList
	 *            各科目实际成本费用
	 * @param itemsList
	 *            科目
	 * @param budgetCostList
	 *            各科目预算成本费用
	 * @return
	 */
	protected List calcBudgetItemCost(Map m1, List costList,
			List<Map<String, Object>> ftList, List budgetCostList,
			String humanCost) {
		List list = new ArrayList();
		// 项目编号
		String proNo = (String) m1.get("PRO_NO");
		Map mm = new HashMap<String, Object>();
		String totalBudgetCost = "0";
		String totalRealCost = "0";
		String totalDeference = "0";
		for (int i = 0; i < ftList.size(); i++) {
			Map m = (Map) ftList.get(i);
			String feeCode = (String) m.get("FEE_CODE");
			String feeName = (String) m.get("FEE_NAME");
			mm = new HashMap<String, Object>();
			mm.put("feeName", feeName);
			String bugetCost = queryBudgetCost(feeName, budgetCostList);
			mm.put("BUGET_COST", bugetCost);

			String realCost = queryRealCost(feeName, costList);
			if ("人力资源成本".equals(feeName)) {
				realCost = humanCost;
			}
			mm.put("REAL_COST", realCost);

			String deference = (Float.parseFloat(bugetCost) - Float
					.parseFloat(realCost)) + "";
			mm.put("DEFERENCE", deference);
			list.add(mm);

			totalBudgetCost = (Float.parseFloat(totalBudgetCost) + Float
					.parseFloat(bugetCost)) + "";
			totalRealCost = (Float.parseFloat(totalRealCost) + Float
					.parseFloat(realCost)) + "";
			totalDeference = (Float.parseFloat(totalDeference) + Float
					.parseFloat(deference)) + "";
		}

		mm = new HashMap<String, Object>();
		mm.put("feeName", "合计");
		mm.put("BUGET_COST", totalBudgetCost);
		mm.put("REAL_COST", totalRealCost);
		mm.put("DEFERENCE", totalDeference);
		list.add(0, mm);
		return list;
	}

	private String queryRealCost(String feeName, List costList) {
		// TODO Auto-generated method stub
		String result = "0";
		if (costList != null) {
			for (Object obj : costList) {
				Map m = (Map) obj;
				String FEE_NAME = (String) m.get("FEE_NAME");
				if (feeName.equals(FEE_NAME)) {
					result = (BigDecimal) m.get("TOTAL_MONEY") + "";
					break;
				}
			}

		}

		return result;
	}

	private String queryBudgetCost(String feeName, List budgetCostList) {
		String result = "0";
		if (budgetCostList != null) {
			for (Object obj : budgetCostList) {
				Map m = (Map) obj;
				String itemName = (String) m.get("ITEM_NAME");
				if (feeName.equals(itemName)) {
					result = (BigDecimal) m.get("COST") + "";
					break;
				}
			}

		}

		return result;
	}

	/**
	 * 累计预算成本
	 * 
	 * @param m1
	 * @param queryDate
	 * @return
	 */
	protected String calcBugetCost(Map m1, String starttime, String endtime) {
		// PRO_NO
		String proNo = (String) m1.get("PRO_NO");
		Map<String, Object> paramsMap = new HashMap<String, Object>();
		paramsMap.put("proNo", proNo);
		paramsMap.put("starttime", starttime);
		paramsMap.put("endtime", endtime);
		Map m = budgetEstimateService.queryBudgetCost(paramsMap);

		String result = "";
		if (m != null) {
			result = (BigDecimal) m.get("COST") + "";
		} else {
			result = "0";
		}
		return result;
	}
	
	/**
	 * 
	 * @param m1
	 * @param starttime
	 * @param endtime
	 * @return
	 * @throws ParseException 
	 */
	protected double calcBugetCost1(Map m1, String starttime, String endtime) throws ParseException {
		// PRO_NO
		
		List phaseCostList=budgetEstimateService.getProPhasesCost(m1);
		
		
		return calTotalProphaseCost(phaseCostList,starttime,endtime);
	}
	
	protected List calcBugetCost2(List actualCost,Map m1, String starttime, String endtime) throws ParseException {

		
		for(int i=0;i<actualCost.size();i++){
			Map m=(Map) actualCost.get(i);
			String itemName=m.get("ITEM_NAME").toString();
			String itemValue=m.get("COST_VALUE").toString();
			String itemIndex="";
			if(itemName.equals("人力资源成本")){
				m1.put("itemCode", "I001,I070,I071");
				itemIndex="A1";
			}
			if(itemName.equals("设备资源成本")){
				m1.put("itemCode", "I002");
				itemIndex="A2";
			}
			if(itemName.equals("外包费用")){
				m1.put("itemCode", "I003");
				itemIndex="A3";
			}
			
			if(itemName.equals("房屋租赁费")){
				m1.put("itemCode", "I010,I012");
				itemIndex="A4.1";
			}
			if(itemName.equals("招待费")){
				m1.put("itemCode", "I005");
				itemIndex="A4.2";
			}
			
			if(itemName.equals("租车/汽车费")){
				m1.put("itemCode", "I006,I007");
				itemIndex="A4.3";
			}
			
			if(itemName.equals("差旅费")){
				m1.put("itemCode", "I008");
				
				itemIndex="A4.4.1";
			}
			
			if(itemName.equals("交通费")){
				m1.put("itemCode", "I009");
				itemIndex="A4.4.2";
			}
			
			if(itemName.equals("咨询服务费")){
				m1.put("itemCode", "I004");
				itemIndex="A4.4.3";
			}
			if(itemName.equals("生活宿舍费")){
				m1.put("itemCode", "I017");
				itemIndex="A4.4.4";
			}
			if(itemName.equals("伙食费")){
				m1.put("itemCode", "I013,I014");
				itemIndex="A4.4.5";
			}
			
			if(itemName.equals("劳务费")){
				m1.put("itemCode", "I015,I016");
				itemIndex="A4.4.6";
			}
			
			if(itemName.equals("办公费")){
				m1.put("itemCode", "I019");
				itemIndex="A4.4.7";
			}
			if(itemName.equals("网络通讯费")){
				m1.put("itemCode", "I020");
				itemIndex="A4.4.8";
			}
			if(itemName.equals("仪器办公设备")){
				m1.put("itemCode", "I018");
				itemIndex="A4.4.9";
			}
			if(itemName.equals("设备修理费")){
				m1.put("itemCode", "I024");
				itemIndex="A4.4.10";
			}
			if(itemName.equals("工具材料费")){
				m1.put("itemCode", "I025");
				itemIndex="A4.4.11";
			}
			if(itemName.equals("运费")){
				m1.put("itemCode", "I027");
				itemIndex="A4.4.12";
			}
			if(itemName.equals("劳动保护费")){
				m1.put("itemCode", "I028");
				itemIndex="A4.4.13";
			}
			if(itemName.equals("投标费")){
				m1.put("itemCode", "I029");
				itemIndex="A4.4.14";
			}
			if(itemName.equals("其它费")){
				m1.put("itemCode", "I030");
				itemIndex="A4.4.15";
			}
			
			
			
			List phaseCostList=budgetEstimateService.getProPhasesCost(m1);
			
			double cost=calTotalProphaseCost(phaseCostList,starttime,endtime);
			
			BigDecimal   b   =   new   BigDecimal(cost);  
			double   f1   =   b.setScale(2,   BigDecimal.ROUND_HALF_UP).doubleValue(); 
			
			m.put("bugetCost", f1);
			
			double diff=cost-Float.parseFloat(itemValue);
			BigDecimal   b1   =   new   BigDecimal(diff);  
			double   f2   =   b1.setScale(2,   BigDecimal.ROUND_HALF_UP).doubleValue();
			m.put("differ", f2);
			m.put("itemIndex", itemIndex);
		}
		
		Map tpa=new HashMap();
		
		tpa.put("ITEM_NAME", "其他成本--");
		tpa.put("itemIndex", "A4");
		actualCost.add(3,tpa);
		Map tpb=new HashMap();
		tpb.put("ITEM_NAME", "其他--");
		tpb.put("itemIndex", "A4.4");
		actualCost.add(7,tpb);
		
		return actualCost;
	}
	
	
	public double calTotalProphaseCost(List budgetestimateList,String startMonth,String endMonth) throws ParseException{
		double totalCost = 0.0;
		if(budgetestimateList==null){
			return 0;
		}
		if(budgetestimateList.size()<=0){
			return 0;
		}
		Map m1=(Map)budgetestimateList.get(0);
		Map m2=(Map)budgetestimateList.get(budgetestimateList.size() - 1);
		Date firstDate = DateTimeUtil.getDateFormat(m1.get("PHASE_START_DATE").toString(), DateTimeUtil.FORMAT_2);
		Date lastDate = DateTimeUtil.getDateFormat(m2.get("PHASE_END_DATE").toString(), DateTimeUtil.FORMAT_2);
		String emonthLastDay = DateTimeUtil.lastDayOfDate(endMonth); //取得查询月份最后一天
		
		Date smonth = DateTimeUtil.getDateFormat(startMonth + "-01", DateTimeUtil.FORMAT_2);
		Date emonth = DateTimeUtil.getDateFormat(emonthLastDay, DateTimeUtil.FORMAT_2);
		long dayOfFirst = DateTimeUtil.getTwoTimeDiff(firstDate, smonth);
		long dayOfEnd = DateTimeUtil.getTwoTimeDiff(lastDate, emonth);
		
		List budgetList = new ArrayList();
		if (dayOfFirst <= 0 && dayOfEnd >= 0) {
			
			budgetList = budgetestimateList;
			totalCost = getBudgetestimateCost(budgetList);
			//
			
	
		}else if(dayOfFirst <= 0 && dayOfEnd < 0){
			
			for(int i=0;i<budgetestimateList.size();i++){
				Map m=(Map)budgetestimateList.get(i);
				Date sdate = DateTimeUtil.getDateFormat(m.get("PHASE_START_DATE").toString(), DateTimeUtil.FORMAT_2);
				Date edate = DateTimeUtil.getDateFormat(m.get("PHASE_END_DATE").toString(), DateTimeUtil.FORMAT_2);
				long dayOfStart = DateTimeUtil.getTwoTimeDiff(sdate, emonth);
				dayOfEnd = DateTimeUtil.getTwoTimeDiff(edate, emonth);
				if (dayOfStart < 0) {
					
					continue;
				}
				else if (dayOfStart >= 0 && dayOfEnd <=0) {
					
					int monthDiffCount1 = DateTimeUtil.getMonthDiffer(sdate, edate);
					int monthDiffCount2 = DateTimeUtil.getMonthDiffer(sdate, emonth);
					
					if (monthDiffCount1 == 0) {
						
						totalCost += Double.parseDouble(m.get("cost").toString());
					}
					else {
						
						totalCost += ((monthDiffCount2 + 1) * Double.parseDouble(m.get("cost").toString())) / (monthDiffCount1 + 1);
					}
				}
				else {
					totalCost += Double.parseDouble(m.get("cost").toString());
				}
			}
			
		}else if (dayOfFirst > 0 && dayOfEnd >= 0) {
			for(int i=0;i<budgetestimateList.size();i++){
				Map m=(Map)budgetestimateList.get(i);
				Date sdate = DateTimeUtil.getDateFormat(m.get("PHASE_START_DATE").toString(), DateTimeUtil.FORMAT_2);
				Date edate = DateTimeUtil.getDateFormat(m.get("PHASE_END_DATE").toString(), DateTimeUtil.FORMAT_2);
				long dayOfStart = DateTimeUtil.getTwoTimeDiff(sdate, smonth);
				dayOfEnd = DateTimeUtil.getTwoTimeDiff(edate, smonth);
				if (dayOfEnd > 0) {
					
					continue;
				}
				else if (dayOfStart > 0 && dayOfEnd <=0) {
					
					int monthDiffCount1 = DateTimeUtil.getMonthDiffer(sdate, edate);
					int monthDiffCount2 = DateTimeUtil.getMonthDiffer(smonth, edate);
					
					if (monthDiffCount1 == 0) {
						
						totalCost += Double.parseDouble(m.get("cost").toString());
					}
					else {
						
						totalCost += ((monthDiffCount2 + 1) * Double.parseDouble(m.get("cost").toString())) / (monthDiffCount1 + 1);
					}
					
				}
				else {
					totalCost += Double.parseDouble(m.get("cost").toString());
				}
			}
		}else if (dayOfFirst > 0 && dayOfEnd < 0) {
			
			for(int i=0;i<budgetestimateList.size();i++){
				
				Map m=(Map)budgetestimateList.get(i);
				Date sdate = DateTimeUtil.getDateFormat(m.get("PHASE_START_DATE").toString(), DateTimeUtil.FORMAT_2);
				Date edate = DateTimeUtil.getDateFormat(m.get("PHASE_END_DATE").toString(), DateTimeUtil.FORMAT_2);
				long dayOfStart1 = DateTimeUtil.getTwoTimeDiff(sdate, smonth);
				long dayOfEnd1 = DateTimeUtil.getTwoTimeDiff(edate, smonth);
				long dayOfStart2 = DateTimeUtil.getTwoTimeDiff(sdate, emonth);
				long dayOfEnd2 = DateTimeUtil.getTwoTimeDiff(edate, emonth);
				
				if ((dayOfStart1 < 0 && dayOfStart2 < 0) 
						|| (dayOfEnd1 > 0)) {
					
					continue;
				}
				else if (dayOfStart1 >= 0 && dayOfEnd2 <= 0) {
					
					int monthDiffCount1 = DateTimeUtil.getMonthDiffer(sdate, edate);
					int monthDiffCount2 = DateTimeUtil.getMonthDiffer(smonth, emonth);
					
					if (monthDiffCount1 == 0) {
						
						totalCost += Double.parseDouble(m.get("cost").toString());
					}
					else {
						
						totalCost += ((monthDiffCount2 + 1) * Double.parseDouble(m.get("cost").toString())) / (monthDiffCount1 + 1);
					}
				}
				else if (dayOfStart1 >= 0 && dayOfEnd2 >= 0) {
					
					int monthDiffCount1 = DateTimeUtil.getMonthDiffer(sdate, edate);
					int monthDiffCount2 = DateTimeUtil.getMonthDiffer(smonth, edate);
					
					if (monthDiffCount1 == 0) {
						
						totalCost += Double.parseDouble(m.get("cost").toString());
					}
					else {
						
						totalCost += ((monthDiffCount2 + 1) * Double.parseDouble(m.get("cost").toString())) / (monthDiffCount1 + 1);
					}
				}
				else if (dayOfStart1 <= 0 && dayOfEnd2 <= 0) {
					
					int monthDiffCount1 = DateTimeUtil.getMonthDiffer(sdate, edate);
					int monthDiffCount2 = DateTimeUtil.getMonthDiffer(sdate, emonth);
					
					if (monthDiffCount1 == 0) {
						
						totalCost += Double.parseDouble(m.get("cost").toString());
					}
					else {
						
						totalCost += ((monthDiffCount2 + 1) * Double.parseDouble(m.get("cost").toString())) / (monthDiffCount1 + 1);
					}
				}
				else if (dayOfStart1 <= 0 && dayOfEnd2 >= 0) {
					
					totalCost += Double.parseDouble(m.get("cost").toString());
				}
			}
		}
		
		
		
		return totalCost;
	}
	
	public  double getBudgetestimateCost(List budgetestimateList) {
		
		double totalCost = 0.0;
		for (int i=0;i<budgetestimateList.size();i++) {
			Map m=(Map)budgetestimateList.get(i);
			totalCost += Double.parseDouble(m.get("cost").toString());
		}
		
		return totalCost;
	}

	// 计算各费用科目累计金额（多项目）
	private List calcItemsCost(Map m1, List costList, List ftList) {
		List list = new ArrayList();
		// 项目编号
		for (int i = 0; i < ftList.size(); i++) {
			Map m = (Map) ftList.get(i);
			String feeCode = (String) m.get("FEE_CODE");
			boolean flag = false;
			for (int k = 0; k < costList.size(); k++) {
				Map m2 = (Map) costList.get(k);
				String proNo1 = (String) m2.get("PRO_NO");
				String feeCode2 = (String) m2.get("FEE_TYPE");
				String totalMoney = (BigDecimal) m2.get("TOTAL_MONEY") + "";
				if (feeCode.equals(feeCode2)) {
					list.add(totalMoney);
					flag = true;
				}
			}

			if (!flag) {
				list.add("0");
			}
		}

		return list;
	}

	// 计算机各费用科目金额（单项目）
	private List calcItemCost(Map m1, List costList, List ftList) {
		List list = new ArrayList();
		// 项目编号
		Map mm = new HashMap<String, Object>();
		for (int i = 0; i < ftList.size(); i++) {
			Map m = (Map) ftList.get(i);
			String feeCode = (String) m.get("FEE_CODE");
			String feeName = (String) m.get("FEE_NAME");
			boolean flag = false;
			mm = new HashMap<String, Object>();
			mm.put("feeName", feeName);
			for (int k = 0; k < costList.size(); k++) {
				Map m2 = (Map) costList.get(k);
				String proNo1 = (String) m2.get("PRO_NO");
				String feeCode2 = (String) m2.get("FEE_TYPE");
				String totalMoney = (BigDecimal) m2.get("TOTAL_MONEY") + "";
				if (feeCode.equals(feeCode2)) {
					mm.put("totalMoney", totalMoney);
					flag = true;
				}
			}

			if (!flag) {
				mm.put("totalMoney", "0");
			}

			list.add(mm);
		}

		return list;
	}

	// Map<String, Object> employeeSalary =
	// salaryFramworkService.selectEmployeeSalary(actionForm.toMap());
	// hrmResPrice
	protected Map<String, Object> calcHumanCost(Map m1, String starttime,
			String endtime) {
		Map<String, Object> proLabourCostMap = new HashMap<String, Object>();
		proLabourCostMap.put("proLabourCost", 0.0);
		proLabourCostMap.put("travelSubsidies", 0.0);
		// 通过项目编号，查询工时
		String proNo = (String) m1.get("PRO_NO");
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("proNo", proNo);
		params.put("starttime", starttime);
		params.put("endtime", endtime);
		List<Map<String, Object>> list = pmWorkHoursService
				.proCostStatistics(params);
		// 累计人力资源成本
		double proLabourCost = 0;
		double travelSubsidies = 0;
		// 有效工时成本
		double availabeHourCost = 0;
		// 带薪成本
		double moneyHourCost = 0;

		// 超出工时成本
		double beyondHourCost = 0;

		if (list != null && list.size() > 0) {

			for (int i = 0; i < list.size(); i++) {

				Map m = (Map) list.get(i);

				// 工时
				double workHours = Double.parseDouble(m.get("workHours") + "");

				// 带薪工时
				double paidHours = Double.parseDouble(m.get("paidHours") + "");

				// 调节工时
				double adjustHours = m.get("regulativeHours") == null ? 0
						: Double.parseDouble(m.get("regulativeHours") + "");

				// 人员code
				String employeeCode = (String) m.get("worker");

				Map mm = mdEmployeeService.queryEmployeeInfo(employeeCode);

				Map<String, Object> paramMap = new HashMap<String, Object>();
				paramMap.put("companyCode", mm.get("COMPANY_CODE"));
				paramMap.put("employeeCode", employeeCode);
				paramMap.put("unitCode", mm.get("UNIT_CODE"));
				Map<String, Object> employeeSalary = salaryFramworkService
						.selectEmployeeSalary(paramMap);
				if (employeeSalary != null) {

					// 人员工时费用
					double hrmResPricehrmResPrice = (Double) employeeSalary
							.get("hrmResPrice");

					double totalHours = workHours + paidHours + adjustHours;

					proLabourCost = proLabourCost
							+ (totalHours * hrmResPricehrmResPrice);

					availabeHourCost = availabeHourCost
							+ (workHours * hrmResPricehrmResPrice);

					moneyHourCost = moneyHourCost
							+ (paidHours * hrmResPricehrmResPrice);

					// 超出工时成本
					beyondHourCost = beyondHourCost
							+ (totalHours > 174 ? (totalHours - 174)
									* hrmResPricehrmResPrice : 0);

					if (m.get("hasNotLocalAmount") != null) {
						travelSubsidies += Double.parseDouble(m.get(
								"hasNotLocalAmount").toString());
					}
				}

			}

			proLabourCostMap.put("proLabourCost", get2Double(proLabourCost));
			proLabourCostMap
			.put("travelSubsidies", get2Double(travelSubsidies));
			proLabourCostMap.put("availabeHourCost",
					get2Double(availabeHourCost));
			proLabourCostMap.put("moneyHourCost", get2Double(moneyHourCost));
			proLabourCostMap.put("beyondHourCost", get2Double(beyondHourCost));
		}

		return proLabourCostMap;
	}

	protected void calcHumanCost2(Map m1, String starttime, String endtime) {

		// 通过项目编号，查询工时
		String proNo = (String) m1.get("PRO_NO");

		// 员工code
		String code = (String) m1.get("EMPLOYEECODE");

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("proNo", proNo);
		params.put("starttime", starttime);
		params.put("endtime", endtime);
		List<Map<String, Object>> list = pmWorkHoursService
				.proCostStatistics(params);
		// 累计人力资源成本
		double proLabourCost = 0;
		// 有效工时成本
		double availabeHourCost = 0;
		// 带薪成本
		double moneyHourCost = 0;

		// 调节成本
		double adjustHourCost = 0;

		// 超出工时成本
		double beyondHourCost = 0;

		double hrmResPricehrmResPrice = 0;

		double workHours = 0;

		double paidHours = 0;
		// 调节工时
		double adjustHours = 0;

		double totalHours = 0;

		for (int i = 0; i < list.size(); i++) {

			Map m = (Map) list.get(i);

			// 工时
			workHours = Double.parseDouble(m.get("workHours") + "");

			// 带薪工时
			paidHours = Double.parseDouble(m.get("paidHours") + "");

			adjustHours = m.get("regulativeHours") == null ? 0 : Double
					.parseDouble(m.get("regulativeHours") + "");

			// 人员code
			String employeeCode = (String) m.get("worker");

			if (code.equals(employeeCode)) {
				Map mm = mdEmployeeService.queryEmployeeInfo(employeeCode);
				Map<String, Object> paramMap = new HashMap<String, Object>();
				paramMap.put("companyCode", mm.get("COMPANY_CODE"));
				paramMap.put("employeeCode", employeeCode);
				paramMap.put("unitCode", mm.get("UNIT_CODE"));
				Map<String, Object> employeeSalary = salaryFramworkService
						.selectEmployeeSalary(paramMap);

				if (employeeSalary != null) {

					// 人员工时费用
					hrmResPricehrmResPrice = (Double) employeeSalary
							.get("hrmResPrice");

					totalHours = workHours + paidHours + adjustHours;

					proLabourCost = totalHours * hrmResPricehrmResPrice;

					availabeHourCost = workHours * hrmResPricehrmResPrice;

					adjustHourCost = adjustHours * hrmResPricehrmResPrice;

					moneyHourCost = paidHours * hrmResPricehrmResPrice;

					// 超出工时成本
					beyondHourCost = totalHours > 174 ? (totalHours - 174)
							* hrmResPricehrmResPrice : 0;

				}
			}

		}

		m1.put("hrmResPricehrmResPrice", get2Double(hrmResPricehrmResPrice));
		m1.put("workHours", get2Double(workHours));
		m1.put("adjustHours", get2Double(adjustHours));
		m1.put("paidHours", get2Double(paidHours));
		m1.put("totalHours", get2Double(totalHours));
		m1.put("availabeHourCost", get2Double(availabeHourCost));
		m1.put("adjustHourCost", get2Double(adjustHourCost));
		m1.put("moneyHourCost", get2Double(moneyHourCost));
		m1.put("proLabourCost", get2Double(proLabourCost));
		m1.put("beyondHourCost", get2Double(proLabourCost));
	}

	// 累计成本（人力成本+各费用成本）单项目
	private String addItemCost(String humanCost, List feeList) {
		float result = 0;
		float hc = Float.parseFloat(humanCost);
		result += hc;
		for (Object fee : feeList) {
			Map mm = (Map) fee;
			result += Float.parseFloat((String) mm.get("totalMoney"));
		}
		return result + "";
	}

	/**
	 * 多项目
	 * 
	 * @param humanCost
	 * @param feeList
	 * @return
	 */
	private String addItemsCost(String humanCost, List feeList) {
		float result = 0;
		float hc = Float.parseFloat(humanCost);
		result += hc;
		for (Object fee : feeList) {
			String money = (String) fee;
			if (!money.toLowerCase().equals("null"))
				result += Float.parseFloat(money);
			else
				result = 0;
		}

		return result + "";
	}

	// 房租
	private String queryHouseRent(Map m, String starttime, String endtime) {
		Map paramsMap = new HashMap<String, Object>();
		paramsMap.put("proNo", m.get("PRO_NO"));
		paramsMap.put("starttime", starttime);
		paramsMap.put("endtime", endtime);
		String result = assetService.queryHouseRent(paramsMap);
		return result;
	}

	// 租车费=报销费用+汽车租赁费用
	private String queryCarRent(Map m, String starttime, String endtime,
			Object returnMoney) {
		Map paramsMap = new HashMap<String, Object>();
		paramsMap.put("proNo", m.get("PRO_NO"));
		paramsMap.put("starttime", starttime);
		paramsMap.put("endtime", endtime);
		String result = assetService.queryCarRent(paramsMap);
		if (result.toLowerCase().equals("null"))
			result = "0";
		result = (Float.parseFloat(result) + Float.parseFloat(returnMoney + ""))
				+ "";
		return result;
	}

	// 设备使用费
	private String queryDeviceUse(Map m, String starttime, String endtime) {
		// TODO Auto-generated method stub
		Map paramsMap = new HashMap<String, Object>();
		paramsMap.put("proNo", m.get("PRO_NO"));
		paramsMap.put("starttime", starttime);
		paramsMap.put("endtime", endtime);
		String result = assetService.queryDeviceUse(paramsMap);
		return result;
	}

	// 加油费
	private String queryGasolineUse(Map m, String starttime, String endtime) {
		// TODO Auto-generated method stub
		Map paramsMap = new HashMap<String, Object>();
		paramsMap.put("proNo", m.get("PRO_NO"));
		paramsMap.put("starttime", starttime);
		paramsMap.put("endtime", endtime);
		String result = assetService.queryGasolineUse(paramsMap);
		return result;
	}

	// 实际成本增加特别处理（房租，车租，加油等）
	protected void fillCostlist(List costList, String mc, String val) {
		boolean flag = true;
		for (int i = 0; i < costList.size(); i++) {
			Map m = (Map) costList.get(i);
			String feeName = m.get("FEE_NAME") + "";
			String totalMoney = m.get("TOTAL_MONEY") + "";
			if (mc.equals(feeName)) {
				flag = false;
				if (mc.equals("房租")) {
					m.put("TOTAL_MONEY",
							Float.parseFloat(totalMoney)
							+ Float.parseFloat(val));
				} else {
					m.put("TOTAL_MONEY", val);
				}
			}
		}

		if (flag) {
			Map<String, Object> paramsMap = new HashMap<String, Object>();
			paramsMap.put("FEE_NAME", mc);
			paramsMap.put("TOTAL_MONEY", new BigDecimal(val));
			costList.add(paramsMap);
		}

	}

	protected int getIndex(List<Map<String, Object>> ftList, String mc) {
		// TODO Auto-generated method stub
		int index = 0;
		for (int i = 0; i < ftList.size(); i++) {
			Map m = ftList.get(i);
			String feeName = (String) m.get("FEE_NAME");
			if (mc.equals(feeName)) {
				index = i;
				break;
			}
		}

		return index;
	}

	// 填充
	protected void fillFeelist(List feeList, int index, String val) {
		// TODO Auto-generated method stub
		Map m = (Map) feeList.get(index);
		m.put("totalMoney", val);
	}

	// 进度利润率
	// L4/[L4*(1-0.15-0.06)-P4*1.4/2.3-P4]
	private String calcCurrentRate(Map m1, String totalCost, String humanCost) {
		double result = 0;
		Float tPrice = (Float) m1.get("T_PRICE");
		result = tPrice
				/ (tPrice * 0.79 - Float.parseFloat(humanCost) * 1.4 / 2.3 - (Float
						.parseFloat(totalCost) - Float.parseFloat(humanCost)));
		String val = result + "";
		if (val.equals("NaN"))
			return "-";
		else {
			// 获取格式化对象
			NumberFormat nt = NumberFormat.getPercentInstance();
			// 设置百分数精确度2即保留两位小数
			nt.setMinimumFractionDigits(2);
			return nt.format(Float.parseFloat(val));
		}

	}

	// 保留2位小数
	public static double get2Double(double a) {
		DecimalFormat df = new DecimalFormat("0.00");
		return new Double(df.format(a).toString());
	}

	// 保留2位小数
	public static float get2Float(float a) {
		DecimalFormat df = new DecimalFormat("0.00");
		return new Float(df.format(a).toString());
	}

	public void setPmBaseService(PmBaseService pmBaseService) {
		this.pmBaseService = pmBaseService;
	}

	public void setFmFeeTypeService(FmFeeTypeService fmFeeTypeService) {
		this.fmFeeTypeService = fmFeeTypeService;
	}

	public void setBudgetEstimateService(
			BudgetEstimateService budgetEstimateService) {
		this.budgetEstimateService = budgetEstimateService;
	}

	public void setSalaryFramworkService(
			SalaryFramworkService salaryFramworkService) {
		this.salaryFramworkService = salaryFramworkService;
	}

	public void setDeviceService(DeviceService deviceService) {
		this.deviceService = deviceService;
	}

	public void setMdEmployeeService(MdEmployeeService mdEmployeeService) {
		this.mdEmployeeService = mdEmployeeService;
	}

	public void setAssetService(AssetService assetService) {
		this.assetService = assetService;
	}

	public PmWorkHoursService getPmWorkHoursService() {
		return pmWorkHoursService;
	}

	public void setPmWorkHoursService(PmWorkHoursService pmWorkHoursService) {
		this.pmWorkHoursService = pmWorkHoursService;
	}

	public void setPmDayWordloadService(
			PmDayWordloadService pmDayWordloadService) {
		this.pmDayWordloadService = pmDayWordloadService;
	}
	
	
	
	
	
	
	

}
