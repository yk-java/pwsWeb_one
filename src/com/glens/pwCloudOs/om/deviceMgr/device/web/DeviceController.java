/**
 * @Title: DeviceController.java
 * @Package com.glens.pwCloudOs.om.deviceMgr.device.web
 * @Description: TODO
 * Copyright: Copyright (c) 2016 
 * Company:南京量为石信息科技有限公司
 * @author 
 * @date 2016-8-22 下午4:53:15
 * @version V1.0
 */

package com.glens.pwCloudOs.om.deviceMgr.device.web;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipOutputStream;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.httpclient.HttpClient;
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
import org.springframework.web.servlet.view.RedirectView;

import com.glens.eap.platform.core.annotation.FormProcessor;
import com.glens.eap.platform.core.view.EAPView;
import com.glens.eap.platform.core.web.ControllerForm;
import com.glens.eap.platform.core.web.EAPController;
import com.glens.eap.platform.framework.beans.PageBean;
import com.glens.eap.platform.framework.beans.UserToken;
import com.glens.eap.platform.framework.codeCenter.CodeWorker;
import com.glens.eap.platform.framework.context.EAPContext;
import com.glens.eap.platform.framework.web.EAPJsonAbstractController;
import com.glens.eap.platform.framework.web.ResponseConstants;
import com.glens.eap.platform.framework.web.support.JsonProcessRequestHandler;
import com.glens.eap.platform.framework.web.support.response.BeanResult;
import com.glens.eap.platform.framework.web.support.response.ListResult;
import com.glens.eap.platform.framework.web.support.response.PageBeanResult;
import com.glens.eap.platform.framework.web.support.response.ResponseResult;
import com.glens.pwCloudOs.config.PWCloudOsConfig;
import com.glens.pwCloudOs.om.deviceMgr.device.service.DeviceService;
import com.glens.pwCloudOs.om.deviceMgr.device.vo.GeoPoint;
import com.glens.pwCloudOs.om.deviceMgr.imagesMgr.service.ImagesService;
import com.glens.pwCloudOs.om.deviceMgr.imagesMgr.vo.DeviceBookVo;

/**
 * 
 * 
 * @author
 * @version V1.0
 */

@RequestMapping("/pmsServices/om/deviceMgr/device")
@FormProcessor(clazz = "com.glens.pwCloudOs.om.deviceMgr.device.web.DeviceForm")
public class DeviceController extends EAPJsonAbstractController {

	private ImagesService imagesService;

	public ImagesService getImagesService() {
		return imagesService;
	}

	public void setImagesService(ImagesService imagesService) {
		this.imagesService = imagesService;
	}

	@RequestMapping(value = "mctListAttr", method = RequestMethod.GET)
	public Object getMctListAttr(HttpServletRequest request,
			HttpServletResponse response) {

		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub

				DeviceService deviceService = (DeviceService) getService();
				Map<String, List<Map<String, Object>>> listAttrMap = deviceService
						.getMctDeviceListShowAttr(actionForm.toMap());
				BeanResult result = new BeanResult();
				if (listAttrMap != null && listAttrMap.size() > 0) {

					result.setStatusCode(ResponseConstants.OK);
					result.setResultMsg("获取属性数据成功");
					result.setData(listAttrMap);
				} else {

					result.setStatusCode(ResponseConstants.NO_DATA);
					result.setResultMsg("获取不到属性数据");
				}

				return result;
			}
		});
	}

	// 电表（运维）
	@RequestMapping(value = "mctListAttr1", method = RequestMethod.GET)
	public Object getMctListAttr1(HttpServletRequest request,
			HttpServletResponse response) {

		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub

				DeviceService deviceService = (DeviceService) getService();
				Map<String, List<Map<String, Object>>> listAttrMap = deviceService
						.getMctDeviceListShowAttr1(actionForm.toMap());
				BeanResult result = new BeanResult();
				if (listAttrMap != null && listAttrMap.size() > 0) {

					result.setStatusCode(ResponseConstants.OK);
					result.setResultMsg("获取属性数据成功");
					result.setData(listAttrMap);
				} else {

					result.setStatusCode(ResponseConstants.NO_DATA);
					result.setResultMsg("获取不到属性数据");
				}

				return result;
			}
		});
	}

	@RequestMapping(value = "mctAttr", method = RequestMethod.GET)
	public Object getMctDeviceAttr(HttpServletRequest request,
			HttpServletResponse response) {

		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub
				ListResult result = new ListResult();
				DeviceService deviceService = (DeviceService) getService();
				List<Map<String, Object>> attrList = deviceService
						.getMctDeviceAttr(actionForm.toMap());
				if (attrList != null && attrList.size() > 0) {

					result.setStatusCode(ResponseConstants.OK);
					result.setResultMsg("获取属性数据成功！");
					result.setList(attrList);
				} else {

					result.setStatusCode(ResponseConstants.NO_DATA);
					result.setResultMsg("获取不到属性数据");
				}

				return result;
			}
		});
	}

	// 手机端属性展示
	@RequestMapping(value = "mobileMctAttr", method = RequestMethod.GET)
	public Object getMobileMctAttr(HttpServletRequest request,
			HttpServletResponse response) {

		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub
				ListResult result = new ListResult();
				DeviceService deviceService = (DeviceService) getService();
				List<Map<String, Object>> attrList = deviceService
						.getMobileMctDeviceAttr(actionForm.toMap());
				if (attrList != null && attrList.size() > 0) {

					result.setStatusCode(ResponseConstants.NO_DATA);
					result.setResultMsg("获取属性数据成功！");
					result.setList(attrList);
				} else {

					result.setStatusCode(ResponseConstants.NO_DATA);
					result.setResultMsg("获取不到属性数据");
				}

				return result;
			}
		});
	}

	@RequestMapping(value = "mctAttr1", method = RequestMethod.GET)
	public Object getMctDeviceAttr1(HttpServletRequest request,
			HttpServletResponse response) {

		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub
				ListResult result = new ListResult();
				DeviceService deviceService = (DeviceService) getService();
				List<Map<String, Object>> attrList = deviceService
						.getMctDeviceAttr1(actionForm.toMap());
				if (attrList != null && attrList.size() > 0) {

					result.setStatusCode(ResponseConstants.NO_DATA);
					result.setResultMsg("获取属性数据成功！");
					result.setList(attrList);
				} else {

					result.setStatusCode(ResponseConstants.NO_DATA);
					result.setResultMsg("获取不到属性数据");
				}

				return result;
			}
		});
	}

	// 查询列表
	@RequestMapping(value = "mctDeviceList", method = RequestMethod.POST)
	public Object mctDeviceList(HttpServletRequest request,
			HttpServletResponse response) {

		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub
				DeviceService deviceService = (DeviceService) getService();
				PageBeanResult result = new PageBeanResult();
				PageBean page = deviceService.getMctDevicePage(actionForm
						.toMap());
				if (page != null && page.getList().size() > 0) {

					result.setStatusCode(ResponseConstants.OK);
					result.setResultMsg("获取设备数据成功！");
					result.setPageBean(page);
				} else {

					result.setStatusCode(ResponseConstants.NO_DATA);
					result.setResultMsg("获取设备数据失败！");
				}

				return result;
			}
		});
	}
	
	
	// 数据质量统计
		@RequestMapping(value = "dataQuality", method = RequestMethod.POST)
		public Object dataQuality(HttpServletRequest request,
				HttpServletResponse response) {

			return this.process(request, response, new JsonProcessRequestHandler() {

				@Override
				public Object doWithRequest(HttpServletRequest request,
						HttpServletResponse response, ControllerForm actionForm) {
					// TODO Auto-generated method stub
					DeviceService deviceService = (DeviceService) getService();
					//PageBeanResult result = new PageBeanResult();
					
					ListResult result=new ListResult();
					
					List<Map<String, Object>> list = deviceService.dataQuality(actionForm.toMap());
					if (list != null) {

						result.setStatusCode(ResponseConstants.OK);
						result.setResultMsg("成功！");
						result.setList(list);
					} else {

						result.setStatusCode(ResponseConstants.NO_DATA);
						result.setResultMsg("失败！");
					}

					return result;
				}
			});
		}

	// 手机端列表
	@RequestMapping(value = "phoneMctDeviceList", method = RequestMethod.GET)
	public Object phoneMctDeviceList(HttpServletRequest request,
			HttpServletResponse response) {

		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub
				DeviceService deviceService = (DeviceService) getService();
				PageBeanResult result = new PageBeanResult();
				PageBean page = deviceService.getPhoneMctDevicePage(actionForm
						.toMap());
				if (page != null) {
					result.setStatusCode(ResponseConstants.OK);
					result.setResultMsg("获取设备数据成功！");
					result.setPageBean(page);
				} else {
					result.setStatusCode(ResponseConstants.NO_DATA);
					result.setResultMsg("获取设备数据失败！");
				}

				return result;
			}
		});
	}

	// 导出图片
	@RequestMapping(value = "getZipFiles", method = RequestMethod.POST)
	public Object getZipFiles(HttpServletRequest request,
			HttpServletResponse response) {

		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub
				DeviceService deviceService = (DeviceService) getService();
				BeanResult result = new BeanResult();
				List list = deviceService.exportImgs(actionForm.toMap());

				// String
				// filePath="http://42.96.144.54:8070/pmsWeb/eap/pmsServices/fileServerClientService";

				String fileCodes = "";
				String fileNames = "";
				for (int i = 0; i < list.size(); i++) {
					System.out.println(list.get(i));

					Map m = (Map) list.get(i);
					String companyCode = m.get("companyCode").toString();
					String proNo = m.get("proNo").toString();
					String mctCode = m.get("mctCode").toString();
					String deviceObjCode = m.get("deviceObjCode").toString();
					String deviceObjName = m.get("deviceObjName").toString();

					List<DeviceBookVo> devicelist = imagesService.getList(
							companyCode, proNo, mctCode, deviceObjCode);
					for (int j = 0; j < devicelist.size(); j++) {
						DeviceBookVo vo = devicelist.get(j);
						String picVisitid = vo.getPicVisitid();
						String fileCode = vo.getPicVisitid();
						String fileName = deviceObjName + "_" + (j + 1);
						fileCodes += fileCode + ",";
						fileNames += fileName + ",";
					}
				}

				fileCodes = fileCodes.substring(0, fileCodes.length() - 1);
				fileNames = fileNames.substring(0, fileNames.length() - 1);
				String[] params = new String[2];
				params[0] = fileCodes;
				params[1] = fileNames;

				Object content = deviceService.getZipImgs(fileCodes, fileNames);
				result.setData(content);

				return result;
			}

			@Override
			public Object doWithFinish(HttpServletRequest request,
					HttpServletResponse response, Object data, String viewType,
					EAPController controller) {
				// TODO Auto-generated method stub
				BeanResult result = (BeanResult) data;
				final Object content = result.getData();

				ModelAndView modelAndView = new ModelAndView();
				EAPView view = new EAPView() {
					@Override
					public void render(Map<String, ?> model,
							HttpServletRequest request,
							HttpServletResponse response) throws Exception {
						// TODO Auto-generated method stub

						byte[] zipByte = (byte[]) content;
						PWCloudOsConfig config = (PWCloudOsConfig) EAPContext
								.getContext().getBean("pwcloudosConfig");
						String tmpFileHomeUrl = getRootPath(request)
								+ File.separator + config.getTmpfileHome();
						File baseDataZipFile = new File(tmpFileHomeUrl
								+ File.separator + "tmp.zip");
						if (!baseDataZipFile.exists()) {

							baseDataZipFile.createNewFile();
						}

						// write file
						/*
						 * FileOutputStream fos = new
						 * FileOutputStream(baseDataZipFile);
						 * fos.write(zipByte); fos.flush(); fos.close();
						 */

						response.setContentType("application/x-msdownload");
						response.setHeader("Content-Disposition",
								"attachment;filename=tmp.zip");
						OutputStream out = response.getOutputStream();
						out.write(zipByte);
						out.flush();
						out.close();
					}

					@Override
					public String getContentType() {
						// TODO Auto-generated method stub

						return "";
					}
				};

				modelAndView.setView(view);

				return modelAndView;
			}
		});
	}

	@RequestMapping(method = RequestMethod.POST, value = "mctDeviceExport")
	public Object dailySheetExport(HttpServletRequest request,
			HttpServletResponse response) {
		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {

				DeviceService deviceService = (DeviceService) getService();
				/* 表头数据 */
				List<Map<String, Object>> listFields = deviceService
						.getMctDeviceAttr(actionForm.toMap());
				List<String> theaderData = new ArrayList<String>();
				theaderData.add("序号");
				theaderData.add("设备名称");
				theaderData.add("设备号");
				theaderData.add("地址描述");
				theaderData.add("健康状况");
				for (Iterator<Map<String, Object>> iterator = listFields
						.iterator(); iterator.hasNext();) {
					Map<String, Object> field = iterator.next();
					theaderData.add((String) field.get("cname"));
				}
				theaderData.add("采集人编号");
				theaderData.add("采集人");
				theaderData.add("采集时间");
				theaderData.add("质量审核");
				theaderData.add("质量审核意见");
				theaderData.add("质量审核人编号");
				theaderData.add("质量审核人");
				theaderData.add("质量审核时间");
				theaderData.add("显示经度");
				theaderData.add("显示纬度");
				theaderData.add("GPS经度");
				theaderData.add("GPS纬度");
				/* 表体数据 */
				List<Map> deviceList = deviceService
						.getMctDeviceList(actionForm.toMap());
				List<List<Object>> tData = new ArrayList<List<Object>>();
				for (int i = 0; i < deviceList.size(); i++) {
					Map device = deviceList.get(i);
					List<Object> trData = new ArrayList<Object>();
					trData.add(i + 1);
					trData.add(device.get("deviceObjName"));
					trData.add(device.get("deviceObjCode"));
					trData.add(device.get("addressDesc"));
					trData.add(device.get("healthName"));
					for (Iterator<Map<String, Object>> iterator = listFields
							.iterator(); iterator.hasNext();) {
						Map<String, Object> field = iterator.next();
						trData.add(device.get((String) field.get("ename")));
					}
					trData.add(device.get("CREATER_CODE"));
					trData.add(device.get("CREATER_NAME"));
					trData.add(device.get("SYS_CREATE_TIME"));
					String quality="";
					if("1".equals(device.get("QUALITY_AUDIT"))){
						quality="合格";
					}else if("2".equals(device.get("QUALITY_AUDIT"))){
						quality="不合格";
					}
					trData.add(quality);
					trData.add(device.get("AUDIT_SUGGEST"));
					trData.add(device.get("AUDITOR_CODE"));
					trData.add(device.get("AUDITOR_NAME"));
					trData.add(device.get("AUDIT_TIME"));
					trData.add(device.get("x"));
					trData.add(device.get("y"));
					trData.add(device.get("rx"));
					trData.add(device.get("ry"));
					tData.add(trData);
				}
				/* 组织好的数据 */
				Map<String, Object> result = new HashMap<String, Object>();
				result.put("theaderData", theaderData);
				result.put("tData", tData);
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
						String fileName = URLEncoder.encode("设备清单", "UTF-8");

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
						SXSSFSheet sheet = workbook.createSheet("设备清单");
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
	
	
	@RequestMapping(method = RequestMethod.POST, value = "qualityExport")
	public Object qualityExport(HttpServletRequest request,
			HttpServletResponse response) {
		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {

				DeviceService deviceService = (DeviceService) getService();
				/* 表头数据 */
				List<Map<String, Object>> listFields = deviceService
						.getMctDeviceAttr(actionForm.toMap());
				List<String> theaderData = new ArrayList<String>();
				theaderData.add("序号");
				theaderData.add("设备名称");
				theaderData.add("设备号");
				theaderData.add("地址描述");
				theaderData.add("健康状况");
				for (Iterator<Map<String, Object>> iterator = listFields
						.iterator(); iterator.hasNext();) {
					Map<String, Object> field = iterator.next();
					theaderData.add((String) field.get("cname"));
				}
				theaderData.add("采集人编号");
				theaderData.add("采集人");
				theaderData.add("采集时间");
				theaderData.add("质量审核");
				theaderData.add("质量审核意见");
				theaderData.add("质量审核人编号");
				theaderData.add("质量审核人");
				theaderData.add("质量审核时间");
				theaderData.add("显示经度");
				theaderData.add("显示纬度");
				theaderData.add("GPS经度");
				theaderData.add("GPS纬度");
				/* 表体数据 */
				List<Map> deviceList = deviceService
						.getMctDeviceList(actionForm.toMap());
				List<List<Object>> picList = new ArrayList<List<Object>>();
				List<List<Object>> picList1 = new ArrayList<List<Object>>();
				List<List<Object>> zuobiaoList = new ArrayList<List<Object>>();
				List<List<Object>> zuobiaoList1 = new ArrayList<List<Object>>();
				List<List<Object>> kaoheList = new ArrayList<List<Object>>();
				List<List<Object>> kaoheList1 = new ArrayList<List<Object>>();
				
				
				for (int i = 0; i < deviceList.size(); i++) {
					Map device = deviceList.get(i);		
					
					String pic=device.get("pic").toString();
					String x=device.get("x").toString();
					System.out.println(x);
					//String qualityAudit=device.get("QUALITY_AUDIT").toString();
					if(pic.equals("1")){
						List<Object> trData = new ArrayList<Object>();
						trData.add(i + 1);
						trData.add(device.get("deviceObjName"));
						trData.add(device.get("deviceObjCode"));
						trData.add(device.get("addressDesc"));
						trData.add(device.get("healthName"));
						for (Iterator<Map<String, Object>> iterator = listFields
								.iterator(); iterator.hasNext();) {
							Map<String, Object> field = iterator.next();
							trData.add(device.get((String) field.get("ename")));
						}
						trData.add(device.get("CREATER_CODE"));
						trData.add(device.get("CREATER_NAME"));
						trData.add(device.get("SYS_CREATE_TIME"));
						String quality="";
						if("1".equals(device.get("QUALITY_AUDIT"))){
							quality="合格";
						}else if("2".equals(device.get("QUALITY_AUDIT"))){
							quality="不合格";
						}
						trData.add(quality);
						trData.add(device.get("AUDIT_SUGGEST"));
						trData.add(device.get("AUDITOR_CODE"));
						trData.add(device.get("AUDITOR_NAME"));
						trData.add(device.get("AUDIT_TIME"));
						trData.add(device.get("x"));
						trData.add(device.get("y"));
						trData.add(device.get("rx"));
						trData.add(device.get("ry"));
						//trData.add(device.get("pic"));
						picList.add(trData);
					}else if(pic.equals("0")){
						List<Object> trData = new ArrayList<Object>();
						trData.add(i + 1);
						trData.add(device.get("deviceObjName"));
						trData.add(device.get("deviceObjCode"));
						trData.add(device.get("addressDesc"));
						trData.add(device.get("healthName"));
						for (Iterator<Map<String, Object>> iterator = listFields
								.iterator(); iterator.hasNext();) {
							Map<String, Object> field = iterator.next();
							trData.add(device.get((String) field.get("ename")));
						}
						trData.add(device.get("CREATER_CODE"));
						trData.add(device.get("CREATER_NAME"));
						trData.add(device.get("SYS_CREATE_TIME"));
						String quality="";
						if("1".equals(device.get("QUALITY_AUDIT"))){
							quality="合格";
						}else if("2".equals(device.get("QUALITY_AUDIT"))){
							quality="不合格";
						}
						trData.add(quality);
						trData.add(device.get("AUDIT_SUGGEST"));
						trData.add(device.get("AUDITOR_CODE"));
						trData.add(device.get("AUDITOR_NAME"));
						trData.add(device.get("AUDIT_TIME"));
						trData.add(device.get("x"));
						trData.add(device.get("y"));
						trData.add(device.get("rx"));
						trData.add(device.get("ry"));
						//trData.add(device.get("pic"));
						picList1.add(trData);
					}
					if("1".equals(device.get("QUALITY_AUDIT"))){
						List<Object> trData = new ArrayList<Object>();
						trData.add(i + 1);
						trData.add(device.get("deviceObjName"));
						trData.add(device.get("deviceObjCode"));
						trData.add(device.get("addressDesc"));
						trData.add(device.get("healthName"));
						for (Iterator<Map<String, Object>> iterator = listFields
								.iterator(); iterator.hasNext();) {
							Map<String, Object> field = iterator.next();
							trData.add(device.get((String) field.get("ename")));
						}
						trData.add(device.get("CREATER_CODE"));
						trData.add(device.get("CREATER_NAME"));
						trData.add(device.get("SYS_CREATE_TIME"));
						String quality="";
						if("1".equals(device.get("QUALITY_AUDIT"))){
							quality="合格";
						}else if("2".equals(device.get("QUALITY_AUDIT"))){
							quality="不合格";
						}
						trData.add(quality);
						trData.add(device.get("AUDIT_SUGGEST"));
						trData.add(device.get("AUDITOR_CODE"));
						trData.add(device.get("AUDITOR_NAME"));
						trData.add(device.get("AUDIT_TIME"));
						trData.add(device.get("x"));
						trData.add(device.get("y"));
						trData.add(device.get("rx"));
						trData.add(device.get("ry"));
						//trData.add(device.get("pic"));
						kaoheList.add(trData);
					}else if("2".equals(device.get("QUALITY_AUDIT"))){
						List<Object> trData = new ArrayList<Object>();
						trData.add(i + 1);
						trData.add(device.get("deviceObjName"));
						trData.add(device.get("deviceObjCode"));
						trData.add(device.get("addressDesc"));
						trData.add(device.get("healthName"));
						for (Iterator<Map<String, Object>> iterator = listFields
								.iterator(); iterator.hasNext();) {
							Map<String, Object> field = iterator.next();
							trData.add(device.get((String) field.get("ename")));
						}
						trData.add(device.get("CREATER_CODE"));
						trData.add(device.get("CREATER_NAME"));
						trData.add(device.get("SYS_CREATE_TIME"));
						String quality="";
						if("1".equals(device.get("QUALITY_AUDIT"))){
							quality="合格";
						}else if("2".equals(device.get("QUALITY_AUDIT"))){
							quality="不合格";
						}
						trData.add(quality);
						trData.add(device.get("AUDIT_SUGGEST"));
						trData.add(device.get("AUDITOR_CODE"));
						trData.add(device.get("AUDITOR_NAME"));
						trData.add(device.get("AUDIT_TIME"));
						trData.add(device.get("x"));
						trData.add(device.get("y"));
						trData.add(device.get("rx"));
						trData.add(device.get("ry"));
						//trData.add(device.get("pic"));
						kaoheList1.add(trData);
					}
					
					if(x!=""&& x!="0"){
						List<Object> trData = new ArrayList<Object>();
						trData.add(i + 1);
						trData.add(device.get("deviceObjName"));
						trData.add(device.get("deviceObjCode"));
						trData.add(device.get("addressDesc"));
						trData.add(device.get("healthName"));
						for (Iterator<Map<String, Object>> iterator = listFields
								.iterator(); iterator.hasNext();) {
							Map<String, Object> field = iterator.next();
							trData.add(device.get((String) field.get("ename")));
						}
						trData.add(device.get("CREATER_CODE"));
						trData.add(device.get("CREATER_NAME"));
						trData.add(device.get("SYS_CREATE_TIME"));
						String quality="";
						if("1".equals(device.get("QUALITY_AUDIT"))){
							quality="合格";
						}else if("2".equals(device.get("QUALITY_AUDIT"))){
							quality="不合格";
						}
						trData.add(quality);
						trData.add(device.get("AUDIT_SUGGEST"));
						trData.add(device.get("AUDITOR_CODE"));
						trData.add(device.get("AUDITOR_NAME"));
						trData.add(device.get("AUDIT_TIME"));
						trData.add(device.get("x"));
						trData.add(device.get("y"));
						trData.add(device.get("rx"));
						trData.add(device.get("ry"));
						//trData.add(device.get("pic"));
						zuobiaoList.add(trData);
					}else if(x=="" || x=="0"){
						List<Object> trData = new ArrayList<Object>();
						trData.add(i + 1);
						trData.add(device.get("deviceObjName"));
						trData.add(device.get("deviceObjCode"));
						trData.add(device.get("addressDesc"));
						trData.add(device.get("healthName"));
						for (Iterator<Map<String, Object>> iterator = listFields
								.iterator(); iterator.hasNext();) {
							Map<String, Object> field = iterator.next();
							trData.add(device.get((String) field.get("ename")));
						}
						trData.add(device.get("CREATER_CODE"));
						trData.add(device.get("CREATER_NAME"));
						trData.add(device.get("SYS_CREATE_TIME"));
						String quality="";
						if("1".equals(device.get("QUALITY_AUDIT"))){
							quality="合格";
						}else if("2".equals(device.get("QUALITY_AUDIT"))){
							quality="不合格";
						}
						trData.add(quality);
						trData.add(device.get("AUDIT_SUGGEST"));
						trData.add(device.get("AUDITOR_CODE"));
						trData.add(device.get("AUDITOR_NAME"));
						trData.add(device.get("AUDIT_TIME"));
						trData.add(device.get("x"));
						trData.add(device.get("y"));
						trData.add(device.get("rx"));
						trData.add(device.get("ry"));
						//trData.add(device.get("pic"));
						zuobiaoList1.add(trData);
					}
					
					
				}
				/* 组织好的数据 */
				Map<String, Object> result = new HashMap<String, Object>();
				result.put("theaderData", theaderData);
				result.put("picList", picList);
				result.put("picList1", picList1);
				result.put("zuobiaoList", zuobiaoList);
				result.put("zuobiaoList1", zuobiaoList1);
				result.put("kaoheList", kaoheList);
				result.put("kaoheList1", kaoheList1);
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
						String fileName = URLEncoder.encode("数据质量统计查询", "UTF-8");

						response.setContentType("application/ms-excel;charset=UTF-8");
						response.addHeader("Content-Disposition",
								"attachment;filename=" + fileName
										+ ".xlsx;charset=UTF-8");
						// ExcelHelper excelHelper=new ExcelHelper();
						// excelHelper.setSheetName(date + "_设备清单");
						Map<String, Object> excelData = (Map<String, Object>) data;
						List<String> theaderData = (List<String>) excelData
								.get("theaderData");
						List<List<Object>> picList = (List<List<Object>>) excelData
								.get("picList");
						List<List<Object>> picList1 = (List<List<Object>>) excelData
								.get("picList1");
						List<List<Object>> zuobiaoList = (List<List<Object>>) excelData
								.get("zuobiaoList");
						List<List<Object>> zuobiaoList1 = (List<List<Object>>) excelData
								.get("zuobiaoList1");
						List<List<Object>> kaoheList = (List<List<Object>>) excelData
								.get("kaoheList");
						List<List<Object>> kaoheList1 = (List<List<Object>>) excelData
								.get("kaoheList1");
						// ============ START ==============
						SXSSFWorkbook workbook = new SXSSFWorkbook();
						// Create sheet
						SXSSFSheet picsheet = workbook.createSheet("有照片");
						SXSSFSheet picsheet1 = workbook.createSheet("无照片");
						SXSSFSheet zuobiaosheet = workbook.createSheet("有坐标");
						SXSSFSheet zuobiaosheet1 = workbook.createSheet("无坐标");
						SXSSFSheet kaohesheet = workbook.createSheet("考核合格");
						SXSSFSheet kaohesheet1 = workbook.createSheet("考核不合格");
						
						//6
						
						
						
						picsheet.setDefaultColumnWidth((short) 15);
						picsheet1.setDefaultColumnWidth((short) 15);
						zuobiaosheet.setDefaultColumnWidth((short) 15);
						zuobiaosheet1.setDefaultColumnWidth((short) 15);
						kaohesheet.setDefaultColumnWidth((short) 15);
						kaohesheet1.setDefaultColumnWidth((short) 15);
						
						
						CellStyle thStyle = workbook.createCellStyle();
						thStyle.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);
						thStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
						thStyle.setAlignment(CellStyle.ALIGN_CENTER);
						Font thFont = workbook.createFont();
						thFont.setFontHeightInPoints((short) 12);
						thFont.setBoldweight(Font.BOLDWEIGHT_BOLD);
						thStyle.setFont(thFont);
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
						tdStyle.setFont(tdFont);
						
						SXSSFRow picth = picsheet.createRow(0);
						SXSSFRow picth1 = picsheet1.createRow(0);
						SXSSFRow zuobiaoth = zuobiaosheet.createRow(0);
						SXSSFRow zuobiaoth1 = zuobiaosheet1.createRow(0);
						SXSSFRow kaoheth = kaohesheet.createRow(0);
						SXSSFRow kaoheth1 = kaohesheet1.createRow(0);
						
						
						for (int i = 0; i < theaderData.size(); i++) {
							String thDat = theaderData.get(i);
							SXSSFCell cell = picth.createCell((short) i);
							cell.setCellStyle(thStyle);
							XSSFRichTextString text = new XSSFRichTextString(
									thDat);
							cell.setCellValue(text);
						}
						for (int i = 0; i < theaderData.size(); i++) {
							String thDat = theaderData.get(i);
							SXSSFCell cell = picth1.createCell((short) i);
							cell.setCellStyle(thStyle);
							XSSFRichTextString text = new XSSFRichTextString(
									thDat);
							cell.setCellValue(text);
						}
						for (int i = 0; i < theaderData.size(); i++) {
							String thDat = theaderData.get(i);
							SXSSFCell cell = zuobiaoth.createCell((short) i);
							cell.setCellStyle(thStyle);
							XSSFRichTextString text = new XSSFRichTextString(
									thDat);
							cell.setCellValue(text);
						}
						for (int i = 0; i < theaderData.size(); i++) {
							String thDat = theaderData.get(i);
							SXSSFCell cell = zuobiaoth1.createCell((short) i);
							cell.setCellStyle(thStyle);
							XSSFRichTextString text = new XSSFRichTextString(
									thDat);
							cell.setCellValue(text);
						}
						for (int i = 0; i < theaderData.size(); i++) {
							String thDat = theaderData.get(i);
							SXSSFCell cell = kaoheth.createCell((short) i);
							cell.setCellStyle(thStyle);
							XSSFRichTextString text = new XSSFRichTextString(
									thDat);
							cell.setCellValue(text);
						}
						for (int i = 0; i < theaderData.size(); i++) {
							String thDat = theaderData.get(i);
							SXSSFCell cell = kaoheth1.createCell((short) i);
							cell.setCellStyle(thStyle);
							XSSFRichTextString text = new XSSFRichTextString(
									thDat);
							cell.setCellValue(text);
						}
						
						
						
						
						
						
						

						for (int i = 0; i < picList.size(); i++) {
							List<Object> tr = picList.get(i);
							SXSSFRow td = picsheet.createRow(i + 1);
							for (int j = 0; j < tr.size(); j++) {
								Object val = tr.get(j);
								SXSSFCell cell = td.createCell((short) j);
								XSSFRichTextString text = new XSSFRichTextString(
										val != null ? val.toString() : "");
								cell.setCellValue(text);
							}
						}
						for (int i = 0; i < picList1.size(); i++) {
							List<Object> tr = picList1.get(i);
							SXSSFRow td = picsheet1.createRow(i + 1);
							for (int j = 0; j < tr.size(); j++) {
								Object val = tr.get(j);
								SXSSFCell cell = td.createCell((short) j);
								XSSFRichTextString text = new XSSFRichTextString(
										val != null ? val.toString() : "");
								cell.setCellValue(text);
							}
						}
						for (int i = 0; i < zuobiaoList.size(); i++) {
							List<Object> tr = zuobiaoList.get(i);
							SXSSFRow td = zuobiaosheet.createRow(i + 1);
							for (int j = 0; j < tr.size(); j++) {
								Object val = tr.get(j);
								SXSSFCell cell = td.createCell((short) j);
								XSSFRichTextString text = new XSSFRichTextString(
										val != null ? val.toString() : "");
								cell.setCellValue(text);
							}
						}
						for (int i = 0; i < zuobiaoList1.size(); i++) {
							List<Object> tr = zuobiaoList1.get(i);
							SXSSFRow td = zuobiaosheet1.createRow(i + 1);
							for (int j = 0; j < tr.size(); j++) {
								Object val = tr.get(j);
								SXSSFCell cell = td.createCell((short) j);
								XSSFRichTextString text = new XSSFRichTextString(
										val != null ? val.toString() : "");
								cell.setCellValue(text);
							}
						}
						for (int i = 0; i < kaoheList.size(); i++) {
							List<Object> tr = kaoheList.get(i);
							SXSSFRow td = kaohesheet.createRow(i + 1);
							for (int j = 0; j < tr.size(); j++) {
								Object val = tr.get(j);
								SXSSFCell cell = td.createCell((short) j);
								XSSFRichTextString text = new XSSFRichTextString(
										val != null ? val.toString() : "");
								cell.setCellValue(text);
							}
						}
						for (int i = 0; i < kaoheList1.size(); i++) {
							List<Object> tr = kaoheList1.get(i);
							SXSSFRow td = kaohesheet1.createRow(i + 1);
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
	
	//表箱新增
	@RequestMapping(value="insertMctDevice", method=RequestMethod.POST)
	public Object insertMctDevice(HttpServletRequest request, HttpServletResponse response) {
		
		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub
				DeviceService deviceService = (DeviceService) getService();
				ResponseResult result = new ResponseResult();
				CodeWorker codeWorker = (CodeWorker) EAPContext.getContext()
						.getBean("simpleCodeWorker");
				String code = codeWorker.createCode("MCT");
				UserToken token = getToken(request);
				int icount = deviceService.insertMctDevice1(actionForm.toMap(),
						code, token);

				imagesService.phoneinsert(actionForm.toMap(), code);
				if (icount >= 0) {

					result.setResultMsg("新增总控表设备成功！");
					result.setStatusCode(ResponseConstants.OK);
				} else {

					result.setResultMsg("新增总控表设备失败！");
					result.setStatusCode(ResponseConstants.INSERT_DATA_ERROR);
				}

				return result;
			}
		});
	}
	
		//表箱抢修
		@RequestMapping(value="boxRepair", method=RequestMethod.POST)
		public Object boxRepair(HttpServletRequest request, HttpServletResponse response) {
			
			return this.process(request, response, new JsonProcessRequestHandler() {

				@Override
				public Object doWithRequest(HttpServletRequest request,
						HttpServletResponse response, ControllerForm actionForm) {
					// TODO Auto-generated method stub
					DeviceService deviceService = (DeviceService) getService();
					ResponseResult result = new ResponseResult();
					CodeWorker codeWorker = (CodeWorker) EAPContext.getContext()
							.getBean("simpleCodeWorker");
					String code = codeWorker.createCode("MCT");
					UserToken token = getToken(request);
					int icount = deviceService.insertMctDevice1(actionForm.toMap(),
							code, token);

					imagesService.boxRepairPhoneinsert(actionForm.toMap(), code);
					if (icount >= 0) {

						result.setResultMsg("新增总控表设备成功！");
						result.setStatusCode(ResponseConstants.OK);
					} else {

						result.setResultMsg("新增总控表设备失败！");
						result.setStatusCode(ResponseConstants.INSERT_DATA_ERROR);
					}

					return result;
				}
			});
		}

	// 判断设备名称是否存在
	@RequestMapping(value = "deviceIsHave", method = RequestMethod.GET)
	public Object deviceIsHave(HttpServletRequest request,
			HttpServletResponse response) {

		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub
				DeviceService deviceService = (DeviceService) getService();
				ResponseResult result = new ResponseResult();
				// CodeWorker codeWorker = (CodeWorker)
				// EAPContext.getContext().getBean("simpleCodeWorker");
				// String code=codeWorker.createCode("MCT");

				int icount = deviceService.deviceIsHave(actionForm.toMap());

				if (icount > 0) {

					result.setResultMsg("设备名称存在！");
					result.setStatusCode(ResponseConstants.OK);
				} else {

					result.setResultMsg("设备名称不存在！");
					result.setStatusCode(ResponseConstants.INSERT_DATA_ERROR);
				}

				return result;
			}
		});
	}

	// 表箱查询图片新增
	@RequestMapping(value = "addDeviceImages", method = RequestMethod.POST)
	public Object addDeviceImages(HttpServletRequest request,
			HttpServletResponse response) {

		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub

				ResponseResult result = new ResponseResult();

				UserToken token = getToken(request);

				DeviceForm form = (DeviceForm) actionForm;

				boolean isok = imagesService.phoneinsert(actionForm.toMap(),
						form.getDeviceObjCode());

				if (isok) {

					result.setResultMsg("新增总控表设备成功！");
					result.setStatusCode(ResponseConstants.OK);
				} else {

					result.setResultMsg("新增总控表设备失败！");
					result.setStatusCode(ResponseConstants.INSERT_DATA_ERROR);
				}

				return result;
			}
		});
	}

	@RequestMapping(value = "updateMctDevice", method = RequestMethod.POST)
	public Object updateMctDevice(HttpServletRequest request,
			HttpServletResponse response) {

		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub
				DeviceService deviceService = (DeviceService) getService();
				ResponseResult result = new ResponseResult();
				UserToken token = getToken(request);
				int icount = deviceService.updateMctDevice(actionForm.toMap(),
						token);
				if (icount > 0) {

					result.setResultMsg("修改总控表设备成功！");
					result.setStatusCode(ResponseConstants.OK);
				} else {

					result.setResultMsg("修改总控表设备失败！");
					result.setStatusCode(ResponseConstants.UPDATE_DATA_ERROR);
				}

				return result;
			}
		});
	}

	@RequestMapping(value = "batchUpdateMctDevice", method = RequestMethod.POST)
	public Object batchUpdateMctDevice(HttpServletRequest request,
			HttpServletResponse response) {

		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub

				DeviceService deviceService = (DeviceService) getService();
				UserToken token = getToken(request);
				int icount = deviceService.batchUpdateMctDevice(
						actionForm.toMap(), token);
				BeanResult result = new BeanResult();
				if (icount > 0) {

					result.setStatusCode(ResponseConstants.OK);
					result.setResultMsg("批量更新成功！");
					result.setData(icount);
				} else {

					result.setStatusCode(ResponseConstants.UPDATE_DATA_ERROR);
					result.setResultMsg("批量更新失败！");
				}

				return result;
			}
		});
	}

	@RequestMapping(value = "getMctDevice", method = RequestMethod.GET)
	public Object getMctDevice(HttpServletRequest request,
			HttpServletResponse response) {

		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub

				BeanResult result = new BeanResult();
				DeviceForm deviceForm = (DeviceForm) actionForm;
				DeviceService deviceService = (DeviceService) getService();
				Map<String, Object> mctDevice = deviceService
						.getMctDevice(deviceForm.getDeviceObjCode());
				if (mctDevice != null && mctDevice.size() > 0) {

					result.setResultMsg("获取总控表清单成功！");
					result.setStatusCode(ResponseConstants.OK);
					result.setData(mctDevice);
				} else {

					result.setResultMsg("获取总控表清单失败！");
					result.setStatusCode(ResponseConstants.NO_DATA);
				}

				return result;
			}
		});
	}

	@RequestMapping(value = "deleteMctDevice", method = RequestMethod.POST)
	public Object deleteMctDevice(HttpServletRequest request,
			HttpServletResponse response) {

		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub
				DeviceService deviceService = (DeviceService) getService();
				DeviceForm deviceForm = (DeviceForm) actionForm;
				ResponseResult result = new ResponseResult();
				UserToken token = getToken(request);
				int icount = deviceService.deleteMctDevice(
						deviceForm.getDeviceObjCode(), token);
				if (icount > 0) {

					result.setResultMsg("删除总控表设备成功！");
					result.setStatusCode(ResponseConstants.OK);
				} else {

					result.setResultMsg("删除总控表设备失败！");
					result.setStatusCode(ResponseConstants.UPDATE_DATA_ERROR);
				}

				return result;
			}
		});
	}

	@RequestMapping(value = "updateDeviceLoc", method = RequestMethod.POST)
	public Object updateDeviceLoc(HttpServletRequest request,
			HttpServletResponse response) {

		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub
				DeviceService deviceService = (DeviceService) getService();
				UserToken token = getToken(request);
				ResponseResult result = new ResponseResult();
				int icount = deviceService.updateDeviceLoc(actionForm.toMap(), token);
				if (icount > 0) {

					result.setResultMsg("修改总控表设备坐标成功！");
					result.setStatusCode(ResponseConstants.OK);
				} else {

					result.setResultMsg("修改总控表设备坐标失败！");
					result.setStatusCode(ResponseConstants.UPDATE_DATA_ERROR);
				}

				return result;
			}
		});
	}

	@RequestMapping(value = "mctViewList", method = RequestMethod.GET)
	public Object getMctViewList(HttpServletRequest request,
			HttpServletResponse response) {

		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub
				DeviceService deviceService = (DeviceService) getService();
				DeviceForm deviceForm = (DeviceForm) actionForm;
				ListResult result = new ListResult();
				List<Map<String, Object>> mctViewList = deviceService
						.getMctView(deviceForm.getCompanyCode());
				if (mctViewList != null && mctViewList.size() > 0) {

					result.setResultMsg("搜索到总控表视图");
					result.setStatusCode(ResponseConstants.OK);
					result.setList(mctViewList);

				} else {

					result.setResultMsg("搜索不到总控表视图");
					result.setStatusCode(ResponseConstants.NO_DATA);
				}

				return result;
			}
		});
	}

	@RequestMapping(value = "mctViewProList", method = RequestMethod.GET)
	public Object getMctViewPros(HttpServletRequest request,
			HttpServletResponse response) {

		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub
				DeviceService deviceService = (DeviceService) getService();
				ListResult result = new ListResult();
				List<Map<String, String>> proMctList = deviceService
						.getMctViewPros(actionForm.toMap());
				if (proMctList != null && proMctList.size() > 0) {

					result.setResultMsg("获取视图项目成功!");
					result.setStatusCode(ResponseConstants.OK);
					result.setList(proMctList);
				} else {

					result.setResultMsg("获取视图项目失败!");
					result.setStatusCode(ResponseConstants.NO_DATA);
				}

				return result;
			}
		});
	}

	@RequestMapping(value = "mctDeviceDistribute", method = RequestMethod.POST)
	public Object mctDeviceDistribute(HttpServletRequest request,
			HttpServletResponse response) {

		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub

				DeviceService deviceService = (DeviceService) getService();
				BeanResult result = new BeanResult();
				Map<String, Object> resultMap = deviceService
						.mctDeviceDistribute(actionForm.toMap());
				if (resultMap != null && resultMap.size() > 0) {

					result.setResultMsg("获取设备分布数据成功！");
					result.setStatusCode(ResponseConstants.OK);
					result.setData(resultMap);
				} else {

					result.setResultMsg("获取设备分布数据失败！");
					result.setStatusCode(ResponseConstants.NO_DATA);
				}

				return result;
			}
		});
	}

	@RequestMapping(value = "spatialQueryMctDevice", method = RequestMethod.POST)
	public Object spatialQueryMctDevice(HttpServletRequest request,
			HttpServletResponse response) {

		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {

				DeviceService deviceService = (DeviceService) getService();
				ListResult result = new ListResult();
				List<Map<String, Object>> deviceList = deviceService
						.spatialQueryMctDevice(actionForm.toMap());
				if (deviceList != null && deviceList.size() > 0) {

					result.setStatusCode(ResponseConstants.OK);
					result.setResultMsg("搜索设备成功!");
					result.setList(deviceList);
				} else {

					result.setStatusCode(ResponseConstants.NO_DATA);
					result.setResultMsg("搜索不到设备!");
				}

				return result;
			}
		});
	}

	@RequestMapping(value = "spatialQueryMctDeviceExport", method = RequestMethod.POST)
	public Object spatialQueryMctDeviceExport(HttpServletRequest request,
			HttpServletResponse response) {

		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {

				DeviceService deviceService = (DeviceService) getService();
				/* 表头数据 */
				List<Map<String, Object>> listFields = deviceService
						.getMctDeviceAttr(actionForm.toMap());
				List<String> theaderData = new ArrayList<String>();
				theaderData.add("序号");
				theaderData.add("设备名称");
				theaderData.add("设备号");
				theaderData.add("地址描述");
				theaderData.add("健康状况");
				for (Iterator<Map<String, Object>> iterator = listFields
						.iterator(); iterator.hasNext();) {
					Map<String, Object> field = iterator.next();
					theaderData.add((String) field.get("cname"));
				}
				/* 表体数据 */
				List<Map<String, Object>> deviceList = deviceService
						.spatialQueryMctDeviceExport(actionForm.toMap());
				List<List<Object>> tData = new ArrayList<List<Object>>();
				for (int i = 0; i < deviceList.size(); i++) {
					Map device = deviceList.get(i);
					List<Object> trData = new ArrayList<Object>();
					trData.add(i + 1);
					trData.add(device.get("deviceObjName"));
					trData.add(device.get("deviceObjCode"));
					trData.add(device.get("addressDesc"));
					trData.add(device.get("healthName"));
					for (Iterator<Map<String, Object>> iterator = listFields
							.iterator(); iterator.hasNext();) {
						Map<String, Object> field = iterator.next();
						trData.add(device.get((String) field.get("ename")));
					}
					tData.add(trData);
				}
				/* 组织好的数据 */
				Map<String, Object> result = new HashMap<String, Object>();
				result.put("theaderData", theaderData);
				result.put("tData", tData);
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
						String fileName = URLEncoder.encode("设备清单", "UTF-8");

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
						SXSSFSheet sheet = workbook.createSheet("设备清单");
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

	@RequestMapping(value = "spatialCircleQueryMctDevice", method = RequestMethod.POST)
	public Object spatialCircleQueryMctDevice(HttpServletRequest request,
			HttpServletResponse response) {

		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {

				DeviceService deviceService = (DeviceService) getService();
				ListResult result = new ListResult();
				List<Map<String, Object>> deviceList = deviceService
						.spatialCircleQueryMctDevice(actionForm.toMap());
				if (deviceList != null && deviceList.size() > 0) {

					result.setStatusCode(ResponseConstants.OK);
					result.setResultMsg("搜索设备成功!");
					result.setList(deviceList);
				} else {

					result.setStatusCode(ResponseConstants.NO_DATA);
					result.setResultMsg("搜索不到设备!");
				}

				return result;
			}
		});
	}

	@RequestMapping(value = "spatialCircleQueryMctDeviceExport", method = RequestMethod.POST)
	public Object spatialCircleQueryMctDeviceExport(HttpServletRequest request,
			HttpServletResponse response) {

		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {

				DeviceService deviceService = (DeviceService) getService();
				/* 表头数据 */
				List<Map<String, Object>> listFields = deviceService
						.getMctDeviceAttr(actionForm.toMap());
				List<String> theaderData = new ArrayList<String>();
				theaderData.add("序号");
				theaderData.add("设备名称");
				theaderData.add("设备号");
				theaderData.add("地址描述");
				theaderData.add("健康状况");
				for (Iterator<Map<String, Object>> iterator = listFields
						.iterator(); iterator.hasNext();) {
					Map<String, Object> field = iterator.next();
					theaderData.add((String) field.get("cname"));
				}
				/* 表体数据 */
				List<Map<String, Object>> deviceList = deviceService
						.spatialCircleQueryMctDevice(actionForm.toMap());
				List<List<Object>> tData = new ArrayList<List<Object>>();
				for (int i = 0; i < deviceList.size(); i++) {
					Map device = deviceList.get(i);
					List<Object> trData = new ArrayList<Object>();
					trData.add(i + 1);
					trData.add(device.get("deviceObjName"));
					trData.add(device.get("deviceObjCode"));
					trData.add(device.get("addressDesc"));
					trData.add(device.get("healthName"));
					for (Iterator<Map<String, Object>> iterator = listFields
							.iterator(); iterator.hasNext();) {
						Map<String, Object> field = iterator.next();
						trData.add(device.get((String) field.get("ename")));
					}
					tData.add(trData);
				}
				/* 组织好的数据 */
				Map<String, Object> result = new HashMap<String, Object>();
				result.put("theaderData", theaderData);
				result.put("tData", tData);
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
						String fileName = URLEncoder.encode("设备清单", "UTF-8");

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
						SXSSFSheet sheet = workbook.createSheet("设备清单");
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

	@RequestMapping(value = "deviceObjNames", method = RequestMethod.GET)
	public Object getDeviceObjNames(HttpServletRequest request,
			HttpServletResponse response) {

		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub

				DeviceService deviceService = (DeviceService) getService();
				DeviceForm deviceForm = (DeviceForm) actionForm;
				ListResult result = new ListResult();
				List<Map<String, Object>> deviceObjNameList = deviceService
						.getDeviceObjNames(deviceForm.getDeviceTypeCode(),
								deviceForm.getCompanyCode(), deviceForm.getProNo(), 
								deviceForm.getDependFields());
				if (deviceObjNameList != null && deviceObjNameList.size() > 0) {

					result.setResultMsg("获取设备名称成功!");
					result.setStatusCode(ResponseConstants.OK);
					result.setList(deviceObjNameList);
				} else {

					result.setResultMsg("获取设备名称失败!");
					result.setStatusCode(ResponseConstants.NO_DATA);
				}

				return result;
			}
		});
	}

	@RequestMapping(value = "coordsBD09ToWGS84", method = RequestMethod.GET)
	public Object coordsConvertBD09ToWGS84(HttpServletRequest request,
			HttpServletResponse response) {

		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub
				DeviceService deviceService = (DeviceService) getService();
				DeviceForm deviceForm = (DeviceForm) actionForm;
				BeanResult result = new BeanResult();
				GeoPoint point = deviceService.coordsConvertBD09ToWGS84(
						deviceForm.getX(), deviceForm.getY());
				if (point != null) {

					result.setData(point);
					result.setStatusCode(ResponseConstants.OK);
					result.setResultMsg("BD09ToWGS84：坐标转换成功！");
				} else {

					result.setStatusCode(ResponseConstants.SERVER_ERROR);
					result.setResultMsg("BD09ToWGS84：坐标转换失败！");
				}

				return result;
			}
		});
	}

	@RequestMapping(value = "coordsWGS84ToBD09", method = RequestMethod.GET)
	public Object coordsConvertWGS84ToBD09(HttpServletRequest request,
			HttpServletResponse response) {

		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub
				DeviceService deviceService = (DeviceService) getService();
				DeviceForm deviceForm = (DeviceForm) actionForm;
				BeanResult result = new BeanResult();
				GeoPoint point = deviceService.coordsConvertWGS84ToBD09(
						deviceForm.getX(), deviceForm.getY());
				if (point != null) {

					result.setData(point);
					result.setStatusCode(ResponseConstants.OK);
					result.setResultMsg("WGS84ToBD09：坐标转换成功！");
				} else {

					result.setStatusCode(ResponseConstants.SERVER_ERROR);
					result.setResultMsg("WGS84ToBD09：坐标转换失败！");
				}

				return result;
			}
		});
	}

	@RequestMapping(value = "nullCoordsDevices", method = RequestMethod.POST)
	public Object getNullCoordsDevices(HttpServletRequest request,
			HttpServletResponse response) {

		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub
				DeviceService deviceService = (DeviceService) getService();
				ListResult result = new ListResult();
				List<Map<String, Object>> deviceList = deviceService.getNullCoordsDevices(actionForm
						.toMap());
				if (deviceList != null && deviceList.size() > 0) {

					result.setStatusCode(ResponseConstants.OK);
					result.setResultMsg("获取设备数据成功！");
					result.setList(deviceList);
				} else {

					result.setStatusCode(ResponseConstants.NO_DATA);
					result.setResultMsg("获取设备数据失败！");
				}

				return result;
			}
		});
	}
	
	@RequestMapping(value = "noPicDevices", method = RequestMethod.POST)
	public Object getNoPicDevices(HttpServletRequest request,
			HttpServletResponse response) {

		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub
				DeviceService deviceService = (DeviceService) getService();
				ListResult result = new ListResult();
				List<Map> deviceList = deviceService.getNoPicDevices(actionForm.toMap());
				if (deviceList != null && deviceList.size() > 0) {

					result.setStatusCode(ResponseConstants.OK);
					result.setResultMsg("获取设备数据成功！");
					result.setList(deviceList);
				} else {

					result.setStatusCode(ResponseConstants.NO_DATA);
					result.setResultMsg("获取设备数据失败！");
				}

				return result;
			}
		});
	}
	
	@RequestMapping(value="deviceQualityCheck", method=RequestMethod.POST)
	public Object deviceQualityCheck(HttpServletRequest request, HttpServletResponse response) {
		
		return this.process(request, response, new JsonProcessRequestHandler() {
			
			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				
				DeviceService deviceService = (DeviceService) getService();
				ResponseResult result = new ResponseResult();
				UserToken token = getToken(request);
				Map params = actionForm.toMap();
				params.put("AUDITOR_CODE", token.getEmployeeCode());
				params.put("AUDITOR_NAME", token.getEmployeeName());
				int icount = deviceService.deviceQualityCheck(params, token);
				if (icount >= 0) {
					result.setResultMsg("设备质量审核成功！");
					result.setStatusCode(ResponseConstants.OK);
				} else {
					result.setResultMsg("设备质量审核失败！");
					result.setStatusCode(ResponseConstants.UPDATE_DATA_ERROR);
				}
				return result;
			}
		});
	}
	
	@RequestMapping(value = "memberOpStats", method = RequestMethod.GET)
	public Object getMemberOpStats(HttpServletRequest request,
			HttpServletResponse response) {

		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub
				DeviceService deviceService = (DeviceService) getService();
				PageBeanResult result = new PageBeanResult();
				PageBean page = deviceService.getMemberOpStats(actionForm
						.toMap());
				if (page != null && page.getList().size() > 0) {

					result.setStatusCode(ResponseConstants.OK);
					result.setResultMsg("获取设备操作统计数据成功！");
					result.setPageBean(page);
				} else {

					result.setStatusCode(ResponseConstants.NO_DATA);
					result.setResultMsg("获取设备操作统计数据失败！");
				}

				return result;
			}
		});
	}
	
	//获取设备图片类型
	@RequestMapping(value = "getPicClass", method = RequestMethod.GET)
	public Object getPicClass(HttpServletRequest request,
			HttpServletResponse response) {

		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub

				DeviceForm form=(DeviceForm)actionForm;
				
				
				DeviceService deviceService = (DeviceService) getService();
				List list = deviceService.getPicClass(form.getDeviceTypeCode());
				ListResult result = new ListResult();
				
				if (list != null) {
					result.setStatusCode(ResponseConstants.OK);
					result.setResultMsg("获取属性数据成功");
					result.setList(list);
				} else {

					result.setStatusCode(ResponseConstants.NO_DATA);
					result.setResultMsg("获取不到属性数据");
				}

				return result;
			}
		});
	}

}
