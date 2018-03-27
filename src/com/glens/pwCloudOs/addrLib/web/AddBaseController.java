package com.glens.pwCloudOs.addrLib.web;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.xssf.streaming.SXSSFCell;
import org.apache.poi.xssf.streaming.SXSSFRow;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFRichTextString;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.glens.eap.platform.core.annotation.FormProcessor;
import com.glens.eap.platform.core.view.EAPView;
import com.glens.eap.platform.core.web.ControllerForm;
import com.glens.eap.platform.core.web.EAPController;
import com.glens.eap.platform.framework.beans.UserToken;
import com.glens.eap.platform.framework.web.EAPJsonAbstractController;
import com.glens.eap.platform.framework.web.ResponseConstants;
import com.glens.eap.platform.framework.web.support.JsonProcessRequestHandler;
import com.glens.eap.platform.framework.web.support.response.BeanResult;
import com.glens.eap.platform.framework.web.support.response.ListResult;
import com.glens.eap.platform.framework.web.support.response.PageBeanResult;
import com.glens.eap.platform.framework.web.support.response.ResponseResult;
import com.glens.pwCloudOs.addrLib.service.AddBaseService;
/**
 * 地址信息维护接口
 * @author MaDx
 *
 */
@FormProcessor(clazz = "com.glens.pwCloudOs.addrLib.web.AddBaseForm")
@RequestMapping("/pmsServices/addrLib/addrBase")
public class AddBaseController extends EAPJsonAbstractController {
	
	/**
	 * 分页查询
	 */
	@RequestMapping(value = "queryByPage", method = RequestMethod.GET)
	public Object queryByPage(HttpServletRequest request,
			HttpServletResponse response) {

		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				AddBaseService deviceService = (AddBaseService) getService();
				PageBeanResult result = deviceService
						.queryByPage(actionForm.toMap(), actionForm.getCurrentPage(), actionForm.getPerpage());
				return result;
			}
		});
	}
	/**
	 * 导出所有地址信息
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "addrBaseExport", method = RequestMethod.POST)
	public Object addBaseExport(HttpServletRequest request,
			HttpServletResponse response) {

		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				AddBaseService deviceService = (AddBaseService) getService();
				ListResult res = deviceService
						.query(actionForm.toMap());
				
				List<String> theaderData = new ArrayList<String>();
				theaderData.add("省");
				theaderData.add("城市");
				theaderData.add("区县");
				theaderData.add("街道");
				theaderData.add("街路巷");
				theaderData.add("门牌号");
				theaderData.add("小区");
				theaderData.add("楼牌号");
				theaderData.add("单元号");
				theaderData.add("村组");
				theaderData.add("农户门牌号");
				theaderData.add("方位");
				theaderData.add("上级地址编码");
				theaderData.add("上级地址名称");
				theaderData.add("地址统一编码");
				theaderData.add("地址名称");
				theaderData.add("地址级别");
				theaderData.add("地址内容");
				theaderData.add("地址标准经度");
				theaderData.add("地址标准维度");
				theaderData.add("地址显示经度");
				theaderData.add("地址显示维度");
				theaderData.add("是否区域");
				theaderData.add("区域范围经度");
				theaderData.add("区域范围维度");
				theaderData.add("地址状态");
				theaderData.add("创建（采集）人代码");
				theaderData.add("创建（采集）人姓名");
				theaderData.add("质量审核");
				theaderData.add("审核人代码");
				theaderData.add("审核人姓名");
				theaderData.add("审核时间");
				theaderData.add("审核意见");
				theaderData.add("地址名称全拼");
				theaderData.add("地址名称首字母");
				theaderData.add("来源代码");
				theaderData.add("来源名称");
				theaderData.add("地址类型");
				theaderData.add("门牌");
				theaderData.add("创建日期");
				theaderData.add("更新日期");
				theaderData.add("删除日期");
				theaderData.add("处理标志");
				theaderData.add("备注");
				
				List<List<Object>> tData = new ArrayList<List<Object>>();
				if(res.isSuccess()){
					List<Map<String, Object>> list = (List<Map<String, Object>>)res.getList();
					for (int i = 0; i < list.size(); i++) {
						Map device = list.get(i);
						List<Object> trData = new ArrayList<Object>();
						trData.add(device.get("PROVINCE"));
						trData.add(device.get("CITY"));
						trData.add(device.get("REGION"));
						trData.add(device.get("TOWN_SHIP"));
						trData.add(device.get("STREET"));
						trData.add(device.get("ROOM_NO"));
						trData.add(device.get("PLOT"));
						trData.add(device.get("BUILDING_NO"));
						trData.add(device.get("UNIT_NO"));
						trData.add(device.get("GROUP"));
						trData.add(device.get("FARMER_ROOM_NO"));
						trData.add(device.get("ZAIMUTH"));
						trData.add(device.get("PARENT_ADDR_CODE"));
						trData.add(device.get("PARENT_ADDR_NAME"));
						trData.add(device.get("ADDR_CODE"));
						trData.add(device.get("ADDR_NAME"));
						trData.add(device.get("ADDR_CLASS"));
						trData.add(device.get("ADDR_CONTENT"));
						trData.add(device.get("WGS84_X"));
						trData.add(device.get("WGS84_Y"));
						trData.add(device.get("SHOW_X"));
						trData.add(device.get("SHOW_Y"));
						trData.add(device.get("IS_REGION"));
						trData.add(device.get("SHOW_XS"));
						trData.add(device.get("SHOW_YS"));
						trData.add(device.get("ADDR_STATUS"));
						trData.add(device.get("CREATER_CODE"));
						trData.add(device.get("CREATER_NAME"));
						trData.add(device.get("QUALITY_AUDIT"));
						trData.add(device.get("AUDITOR_CODE"));
						trData.add(device.get("AUDITOR_NAME"));
						trData.add(device.get("AUDIT_TIME"));
						trData.add(device.get("AUDIT_SUGGEST"));
						trData.add(device.get("PIN_YIN"));
						trData.add(device.get("FIRST_LETTER"));
						trData.add(device.get("DATASOURCE_CODE"));
						trData.add(device.get("DATASOURCE_NAME"));
						trData.add(device.get("ADDR_TYPE"));
						trData.add(device.get("PLATE"));
						trData.add(device.get("SYS_CREATE_TIME"));
						trData.add(device.get("SYS_UPDATE_TIME"));
						trData.add(device.get("SYS_DELETE_TIME"));
						trData.add(device.get("SYS_PROCESS_FLAG"));
						trData.add(device.get("REMARKS"));
						tData.add(trData);
					}
				}
				Map<String, Object> result = new HashMap<String, Object>();
				result.put("theaderData", theaderData);
				if(res.isSuccess()){
					result.put("tData", tData);
				}else{
					result.put("tData", new ArrayList());
				}
					
				return result;
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
						String fileName = URLEncoder.encode("地址清单", "UTF-8");

						response.setContentType("application/ms-excel;charset=UTF-8");
						response.addHeader("Content-Disposition",
								"attachment;filename=" + fileName
										+ ".xlsx;charset=UTF-8");
						// ExcelHelper excelHelper=new ExcelHelper();
						// excelHelper.setSheetName(date + "_设备清单");
						Map<String, Object> excelData = (Map<String, Object>) data;
						List<String> theaderData = (List<String>) excelData
								.get("theaderData");
						List<List<Object>> tData = (List<List<Object>>) excelData
								.get("tData");
						// ============ START ==============
						SXSSFWorkbook workbook = new SXSSFWorkbook();
						// Create sheet
						SXSSFSheet sheet = workbook.createSheet("地址清单");
						sheet.setDefaultColumnWidth((short) 15);
						// Table Header Style
						CellStyle thStyle = workbook.createCellStyle();
						thStyle.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);
						thStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
						/*
						 * thStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
						 * thStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
						 * thStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
						 * thStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
						 */
						thStyle.setAlignment(CellStyle.ALIGN_CENTER);
						// Table Header Font
						Font thFont = workbook.createFont();
						// thFont.setColor(HSSFColor.VIOLET.index);
						thFont.setFontHeightInPoints((short) 12);
						thFont.setBoldweight(Font.BOLDWEIGHT_BOLD);
						// Set font
						thStyle.setFont(thFont);
						// Table Data Style
						CellStyle tdStyle = workbook.createCellStyle();
						tdStyle.setFillForegroundColor(HSSFColor.LIGHT_YELLOW.index);
						tdStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
						tdStyle.setBorderBottom(CellStyle.BORDER_THIN);
						tdStyle.setBorderLeft(CellStyle.BORDER_THIN);
						tdStyle.setBorderRight(CellStyle.BORDER_THIN);
						tdStyle.setBorderTop(CellStyle.BORDER_THIN);
						tdStyle.setAlignment(CellStyle.ALIGN_CENTER);
						tdStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
						// Table Data Font
						Font tdFont = workbook.createFont();
						tdFont.setBoldweight(Font.BOLDWEIGHT_NORMAL);
						// Set font
						tdStyle.setFont(tdFont);

						// Create table header
						SXSSFRow th = sheet.createRow(0);
						for (int i = 0; i < theaderData.size(); i++) {
							String thDat = theaderData.get(i);
							SXSSFCell cell = th.createCell((short) i);
							cell.setCellStyle(thStyle);
							XSSFRichTextString text = new XSSFRichTextString(
									thDat);
							cell.setCellValue(text);
						}

						for (int i = 0; i < tData.size(); i++) {
							List<Object> tr = tData.get(i);
							SXSSFRow td = sheet.createRow(i + 1);
							for (int j = 0; j < tr.size(); j++) {
								Object val = tr.get(j);
								SXSSFCell cell = td.createCell((short) j);
								XSSFRichTextString text = new XSSFRichTextString(
										val != null ? val.toString() : "");
								cell.setCellValue(text);
							}

						}
						workbook.write(response.getOutputStream());
						// ============ END ==============

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
	
	
	@RequestMapping(value = "query", method = RequestMethod.GET)
	public Object query(HttpServletRequest request,
			HttpServletResponse response) {

		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				AddBaseService deviceService = (AddBaseService) getService();
				ListResult result = deviceService
						.query(actionForm.toMap());
				return result;
			}
		});
	}
	
	/**
	 * 查询详细
	 */
	@RequestMapping(value = "findDetail", method = RequestMethod.GET)
	public Object findDetail(HttpServletRequest request,
			HttpServletResponse response) {

		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				AddBaseService deviceService = (AddBaseService) getService();
				BeanResult result = deviceService
						.find(actionForm.toMap());
				return result;
			}
		});
	}
	
	/**
	 * 新增
	 */
	@RequestMapping(value = "saveAddr", method = RequestMethod.POST)
	public Object saveAddr(HttpServletRequest request,
			HttpServletResponse response) {

		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				AddBaseService deviceService = (AddBaseService) getService();
				UserToken token = getToken(request);
				BeanResult result = deviceService
						.insert(actionForm.toMap(), token);
				return result;
			}
		});
	}
	
	/**
	 * 修改
	 */
	@RequestMapping(value = "updateAddr", method = RequestMethod.POST)
	public Object updateAddr(HttpServletRequest request,
			HttpServletResponse response) {

		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				AddBaseService deviceService = (AddBaseService) getService();
				BeanResult result = deviceService
						.update(actionForm.toMap());
				return result;
			}
		});
	}
	
	/**
	 * 修改层户结构
	 */
	@RequestMapping(value = "updateCenghu", method = RequestMethod.POST)
	public Object updateCenghu(HttpServletRequest request,
			HttpServletResponse response) {

		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				AddBaseService deviceService = (AddBaseService) getService();
				BeanResult result = deviceService
						.updateCenghu(actionForm.toMap());
				return result;
			}
		});
	}
	
	/**
	 * 删除图片
	 */
	@RequestMapping(value = "deleteImg", method = RequestMethod.POST)
	public Object deleteImg(HttpServletRequest request,
			HttpServletResponse response) {

		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				AddBaseService deviceService = (AddBaseService) getService();
				BeanResult result = deviceService
						.deleteImg(actionForm.toMap());
				return result;
			}
		});
	}
	
	/**
	 * 修改坐标信息
	 */
	@RequestMapping(value = "updateAddrPosition", method = RequestMethod.POST)
	public Object updateAddrPosition(HttpServletRequest request,
			HttpServletResponse response) {

		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				AddBaseService deviceService = (AddBaseService) getService();
				BeanResult result = deviceService
						.updatePosition(actionForm.toMap());
				return result;
			}
		});
	}
	
	/**
	 * 删除
	 */
	@RequestMapping(value = "deleteAddr", method = RequestMethod.POST)
	public Object deleteAddr(HttpServletRequest request,
			HttpServletResponse response) {

		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				AddBaseService deviceService = (AddBaseService) getService();
				BeanResult result = deviceService
						.remove(actionForm.toMap());
				return result;
			}
		});
	}
	
	/**
	 * 审核
	 */
	@RequestMapping(value = "audit", method = RequestMethod.POST)
	public Object audit(HttpServletRequest request,
			HttpServletResponse response) {

		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				AddBaseService deviceService = (AddBaseService) getService();
				UserToken token = getToken(request);
				BeanResult result = deviceService
						.audit(actionForm.toMap(), token);
				return result;
			}
		});
	}
	
	@RequestMapping(value = "batchConvertPinyin", method = RequestMethod.POST)
	public Object batchAddrNameConvertPinyin(HttpServletRequest request,
			HttpServletResponse response) {
		
		return this.process(request, response, new JsonProcessRequestHandler() {
			
			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub
				
				AddBaseService deviceService = (AddBaseService) getService();
				deviceService.batchAddrNameConvertPinyin();
				ResponseResult result = new ResponseResult();
				result.setResultMsg("转化完成！");
				result.setStatusCode(ResponseConstants.OK);
				
				return result;
			}
		});
	}
	
	@RequestMapping(value = "addrDistribute", method = RequestMethod.POST)
	public Object getAddrDistribute(HttpServletRequest request,
			HttpServletResponse response) {
		
		return this.process(request, response, new JsonProcessRequestHandler() {
			
			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub
				AddBaseService deviceService = (AddBaseService) getService();
				BeanResult result = new BeanResult();
				Map<String, Object> resultMap = deviceService.getAddrDistribute(actionForm.toMap());
				if (resultMap != null && resultMap.size() > 0) {

					result.setResultMsg("获取地址分布数据成功！");
					result.setStatusCode(ResponseConstants.OK);
					result.setData(resultMap);
				} else {

					result.setResultMsg("获取地址分布数据失败！");
					result.setStatusCode(ResponseConstants.NO_DATA);
				}

				return result;
			}
		});
	}
	
	@RequestMapping(value = "plateAddrList", method = RequestMethod.GET)
	public Object getPlateAddrList(HttpServletRequest request,
			HttpServletResponse response) {
		
		return this.process(request, response, new JsonProcessRequestHandler() {
			
			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub
				AddBaseService deviceService = (AddBaseService) getService();
				ListResult result = new ListResult();
				List<Map<String, Object>> addrList = deviceService.getPlateAddrList(actionForm.toMap());
				if (addrList != null && addrList.size() > 0) {
					
					result.setResultMsg("获取门牌地址数据成功");
					result.setStatusCode(ResponseConstants.OK);
					result.setList(addrList);
				}
				else {
					
					result.setResultMsg("获取门牌地址数据失败");
					result.setStatusCode(ResponseConstants.NO_DATA);
				}
				
				return result;
			}
		});
	}
	
}
