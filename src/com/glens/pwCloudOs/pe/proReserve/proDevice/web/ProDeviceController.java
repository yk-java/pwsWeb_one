/**
 * @Title: ProDeviceController.java
 * @Package com.glens.pwCloudOs.pe.proReserve.proDevice.web
 * @Description: TODO
 * Copyright: Copyright (c) 2016 
 * Company:南京量为石信息科技有限公司
 * @author 
 * @date 2016-8-26 下午4:26:59
 * @version V1.0
 */


package com.glens.pwCloudOs.pe.proReserve.proDevice.web;

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
import com.glens.eap.platform.framework.beans.PageBean;
import com.glens.eap.platform.framework.web.EAPJsonAbstractController;
import com.glens.eap.platform.framework.web.ResponseConstants;
import com.glens.eap.platform.framework.web.support.JsonProcessRequestHandler;
import com.glens.eap.platform.framework.web.support.response.BeanResult;
import com.glens.eap.platform.framework.web.support.response.ListResult;
import com.glens.eap.platform.framework.web.support.response.PageBeanResult;
import com.glens.eap.platform.framework.web.support.response.ResponseResult;
import com.glens.pwCloudOs.om.deviceMgr.device.service.DeviceService;
import com.glens.pwCloudOs.pe.proReserve.proDevice.service.ProDeviceService;

/**
 * 
 * 
 * @author 
 * @version V1.0
 */

@RequestMapping("pmsServices/pe/proReserve/proDevice")
@FormProcessor(clazz="com.glens.pwCloudOs.pe.proReserve.proDevice.web.ProDeviceForm")
public class ProDeviceController extends EAPJsonAbstractController {
	
	DeviceService deviceService;
	
	public DeviceService getDeviceService() {
		return deviceService;
	}

	public void setDeviceService(DeviceService deviceService) {
		this.deviceService = deviceService;
	}
	
	/* 设备信息导出 */
	@RequestMapping(method = RequestMethod.POST, value = "proDeviceExport")
	public Object proDeviceExport(HttpServletRequest request,
			HttpServletResponse response) {
		
			return this.process(request, response, new JsonProcessRequestHandler() {
				
				@Override
				public Object doWithRequest(HttpServletRequest request,
						HttpServletResponse response, ControllerForm actionForm) {

					/* 表头数据 */
					List<Map<String, Object>> listFields = deviceService.getMctDeviceAttr(actionForm.toMap());
					
					List<String> theaderData = new ArrayList<String>();
					theaderData.add("序号");
					theaderData.add("储备项目名称");
					theaderData.add("项目名称");
					theaderData.add("设备名称");
					theaderData.add("设备号");
					theaderData.add("地址描述");
					theaderData.add("健康状况");
					for (Iterator<Map<String, Object>> iterator = listFields.iterator(); iterator
							.hasNext();) {
						Map<String, Object> field = iterator.next();
						theaderData.add((String)field.get("cname"));
					}
					theaderData.add("审核状态");
					theaderData.add("审核人");
					theaderData.add("审核时间");
					
					ProDeviceService deviceService = (ProDeviceService) getService();
					/* 表体数据 */
					List<Map<String, Object>> deviceList = deviceService.getProDeviceList(actionForm.toMap());
					List<List<Object>> tData = new ArrayList<List<Object>>();
					for (int i = 0; i < deviceList.size(); i++) {
						Map device = deviceList.get(i);
						List<Object> trData = new ArrayList<Object>();
						trData.add(i+1);
						trData.add(device.get("reserveProName"));
						trData.add(device.get("proName"));
						trData.add(device.get("deviceObjName"));
						trData.add(device.get("deviceObjCode"));
						trData.add(device.get("addressDesc"));
						trData.add(device.get("healthName"));
						for (Iterator<Map<String, Object>> iterator = listFields.iterator(); iterator
								.hasNext();) {
							Map<String, Object> field = iterator.next();
							trData.add(device.get((String)field.get("ename")));
						}
						trData.add("1".equals(device.get("rpAuditState"))?"已审核":"未审核");
						trData.add(device.get("auditPersonName"));
						trData.add(device.get("auditDate"));
						tData.add(trData);
					}
					/* 组织好的数据 */
					Map<String, Object> result=new HashMap<String, Object>();
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
						public void render(Map<String, ?> model, HttpServletRequest request,
								HttpServletResponse response) throws Exception {
							response.setCharacterEncoding("UTF-8");
							String fileName = URLEncoder.encode("设备清单", "UTF-8");
							
							response.setContentType("application/ms-excel;charset=UTF-8");
							response.addHeader("Content-Disposition","attachment;filename="+fileName+".xlsx;charset=UTF-8");
							//ExcelHelper excelHelper=new ExcelHelper();
							//excelHelper.setSheetName(date + "_设备清单");
							Map<String, Object> excelData = (Map<String, Object>)data;
							List<String> theaderData = (List<String>)excelData.get("theaderData");
							List<List<Object>> tData = (List<List<Object>>)excelData.get("tData");
							// ============ START ==============
							SXSSFWorkbook workbook = new SXSSFWorkbook();
							// Create sheet
					        SXSSFSheet sheet = workbook.createSheet("设备清单");
					        sheet.setDefaultColumnWidth((short) 15);
					        // Table Header Style
					        CellStyle thStyle = workbook.createCellStyle();
					        thStyle.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);
					        thStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
					        /*thStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
					        thStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
					        thStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
					        thStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);*/
					        thStyle.setAlignment(CellStyle.ALIGN_CENTER);
					        // Table Header Font
					        Font thFont = workbook.createFont();
					        //thFont.setColor(HSSFColor.VIOLET.index);
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
								SXSSFCell cell = th.createCell((short)i);
					            cell.setCellStyle(thStyle);
					            XSSFRichTextString text = new XSSFRichTextString(thDat);
					            cell.setCellValue(text);
							}
							
							for (int i = 0; i < tData.size(); i++) {
								List<Object> tr = tData.get(i);
								SXSSFRow td = sheet.createRow(i+1);
								for (int j = 0; j < tr.size(); j++) {
									Object val = tr.get(j);
									SXSSFCell cell = td.createCell((short)j);
						            XSSFRichTextString text = new XSSFRichTextString(val!=null?val.toString():"");
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
	
	@RequestMapping(value="proDeviceList", method=RequestMethod.POST)
	public Object getProDeviceList(HttpServletRequest request, HttpServletResponse response) {
		
		return this.process(request, response, new JsonProcessRequestHandler() {
			
			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub
				ProDeviceService deviceService = (ProDeviceService) getService();
				PageBeanResult result = new PageBeanResult();
				PageBean page = deviceService.getProDevicePage(actionForm.toMap());
				if (page != null && page.getList().size() > 0) {
					
					result.setStatusCode(ResponseConstants.OK);
					result.setResultMsg("获取储备项目设备数据成功！");
					result.setPageBean(page);
				}
				else {
					
					result.setStatusCode(ResponseConstants.NO_DATA);
					result.setResultMsg("获取储备项目设备数据失败！");
				}
				
				return result;
			}
		});
	}
	
	@RequestMapping(value="proDeviceList1", method=RequestMethod.POST)
	public Object getProDeviceList1(HttpServletRequest request, HttpServletResponse response) {
		
		return this.process(request, response, new JsonProcessRequestHandler() {
			
			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub
				ProDeviceService deviceService = (ProDeviceService) getService();
				PageBeanResult result = new PageBeanResult();
				PageBean page = deviceService.getProDevicePage1(actionForm.toMap());
				if (page != null && page.getList().size() > 0) {
					
					result.setStatusCode(ResponseConstants.OK);
					result.setResultMsg("获取储备项目设备数据成功！");
					result.setPageBean(page);
				}
				else {
					
					result.setStatusCode(ResponseConstants.NO_DATA);
					result.setResultMsg("获取储备项目设备数据失败！");
				}
				
				return result;
			}
		});
	}
	
	
	
	@RequestMapping(value="proDeviceAllPass", method=RequestMethod.POST)
	public Object proDeviceAllPass(HttpServletRequest request, HttpServletResponse response) {
		
		return this.process(request, response, new JsonProcessRequestHandler() {
			
			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub
				ProDeviceService deviceService = (ProDeviceService) getService();
				ResponseResult result = new ResponseResult();
				boolean auditResult = deviceService.proDeviceAllPass(actionForm.toMap());
				if (auditResult) {
					
					result.setResultMsg("审核储备项目设备成功！");
					result.setStatusCode(ResponseConstants.OK);
				}
				else {
					
					result.setResultMsg("审核储备项目设备失败！");
					result.setStatusCode(ResponseConstants.UPDATE_DATA_ERROR);
				}
				
				return result;
			}
		});
	}
	
	
	
	@RequestMapping(value="mctViewProList", method=RequestMethod.GET)
	public Object getMctViewPros(HttpServletRequest request, HttpServletResponse response) {
		
		return this.process(request, response, new JsonProcessRequestHandler() {
			
			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub
				ProDeviceService deviceService = (ProDeviceService) getService();
				ListResult result = new ListResult();
				List<Map<String, String>> proMctList = deviceService.getMctViewPros(actionForm.toMap());
				if (proMctList != null && proMctList.size() > 0) {
					
					result.setResultMsg("获取视图项目成功!");
					result.setStatusCode(ResponseConstants.OK);
					result.setList(proMctList);
				}
				else {
					
					result.setResultMsg("获取视图项目失败!");
					result.setStatusCode(ResponseConstants.NO_DATA);
				}
				
				return result;
			}
		});
	}
	
	@RequestMapping(value="batchInsertProDevice", method=RequestMethod.POST)
	public Object batchInsertProDevice(HttpServletRequest request, HttpServletResponse response) {
		
		return this.process(request, response, new JsonProcessRequestHandler() {
			
			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub
				ProDeviceService deviceService = (ProDeviceService) getService();
				BeanResult result = new BeanResult();
				int icount = deviceService.batchInsertProDevice(actionForm.toMap());
				if (icount > 0) {
					
					result.setResultMsg("批量产生储备项目设备成功！");
					result.setStatusCode(ResponseConstants.OK);
					result.setData(icount);
				}
				else {
					
					result.setResultMsg("批量产生储备项目设备失败！");
					result.setStatusCode(ResponseConstants.INSERT_DATA_ERROR);
				}
				
				return result;
			}
		});
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public Object getProDevice(HttpServletRequest request, HttpServletResponse response) {
		
		return this.process(request, response, new JsonProcessRequestHandler() {
			
			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub
				ProDeviceService deviceService = (ProDeviceService) getService();
				BeanResult result = new BeanResult();
				Map<String, Object> device = deviceService.getProDevice(actionForm.toMap());
				if (device != null && device.size() > 0) {
					
					result.setResultMsg("获取储备项目设备成功！");
					result.setStatusCode(ResponseConstants.OK);
					result.setData(device);
				}
				else {
					
					result.setResultMsg("获取储备项目设备失败！");
					result.setStatusCode(ResponseConstants.NO_DATA);
				}
				
				return result;
			}
		});
	}
	
	@RequestMapping(value="auditProDevice", method=RequestMethod.POST)
	public Object auditProDevice(HttpServletRequest request, HttpServletResponse response) {
		
		return this.process(request, response, new JsonProcessRequestHandler() {
			
			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub
				ProDeviceService deviceService = (ProDeviceService) getService();
				ResponseResult result = new ResponseResult();
				boolean auditResult = deviceService.auditProDevice(actionForm.toMap());
				if (auditResult) {
					
					result.setResultMsg("审核储备项目设备成功！");
					result.setStatusCode(ResponseConstants.OK);
				}
				else {
					
					result.setResultMsg("审核储备项目设备失败！");
					result.setStatusCode(ResponseConstants.UPDATE_DATA_ERROR);
				}
				
				return result;
			}
		});
	}
	
	@RequestMapping(value="deleteProDevice", method=RequestMethod.POST)
	public Object deleteProDevice(HttpServletRequest request, HttpServletResponse response) {
		
		return this.process(request, response, new JsonProcessRequestHandler() {
			
			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub
				ProDeviceService deviceService = (ProDeviceService) getService();
				ResponseResult result = new ResponseResult();
				boolean auditResult = deviceService.deleteProDevice(actionForm.toMap());
				if (auditResult) {
					
					result.setResultMsg("删除储备项目设备成功！");
					result.setStatusCode(ResponseConstants.OK);
				}
				else {
					
					result.setResultMsg("删除储备项目设备失败！");
					result.setStatusCode(ResponseConstants.UPDATE_DATA_ERROR);
				}
				
				return result;
			}
		});
	}
	
	@RequestMapping(value="deleteRPDevice", method=RequestMethod.POST)
	public Object deleteRPDevice(HttpServletRequest request, HttpServletResponse response) {
		
		return this.process(request, response, new JsonProcessRequestHandler() {
			
			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub
				ProDeviceService deviceService = (ProDeviceService) getService();
				ResponseResult result = new ResponseResult();
				int icount = deviceService.deleteRPDevice(actionForm.toMap());
				if (icount > 0) {
					
					result.setResultMsg("删除储备项目设备成功！");
					result.setStatusCode(ResponseConstants.OK);
				}
				else {
					
					result.setResultMsg("删除储备项目设备失败！");
					result.setStatusCode(ResponseConstants.UPDATE_DATA_ERROR);
				}
				
				return result;
			}
		});
	}
	@RequestMapping(value="batchDeleteRPDevice", method=RequestMethod.POST)
	public Object batchDeleteRPDevice(HttpServletRequest request, HttpServletResponse response) {
		
		return this.process(request, response, new JsonProcessRequestHandler() {
			
			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub
				ProDeviceService deviceService = (ProDeviceService) getService();
				ResponseResult result = new ResponseResult();
				int icount = deviceService.batchDeleteRPDevice(actionForm.toMap());
				if (icount > 0) {
					
					result.setResultMsg("删除储备项目设备成功！");
					result.setStatusCode(ResponseConstants.OK);
				}
				else {
					
					result.setResultMsg("删除储备项目设备失败！");
					result.setStatusCode(ResponseConstants.UPDATE_DATA_ERROR);
				}
				
				return result;
			}
		});
	}
	
	@RequestMapping(value="updateReserveProDevice", method=RequestMethod.POST)
	public Object updateReserveProDevice(HttpServletRequest request, HttpServletResponse response) {
		
		return this.process(request, response, new JsonProcessRequestHandler() {
			
			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub
				ProDeviceService deviceService = (ProDeviceService) getService();
				ResponseResult result = new ResponseResult();
				int icount = deviceService.updateReserveProDevice(actionForm.toMap());
				if (icount > 0) {
					
					result.setResultMsg("更新储备项目设备成功！");
					result.setStatusCode(ResponseConstants.OK);
				}
				else {
					
					result.setResultMsg("更新储备项目设备失败！");
					result.setStatusCode(ResponseConstants.UPDATE_DATA_ERROR);
				}
				
				return result;
			}
		});
	}
	
	@RequestMapping(value="rejectProDeviceList", method=RequestMethod.POST)
	public Object getRejectProDevices(HttpServletRequest request, HttpServletResponse response) {
		
		return this.process(request, response, new JsonProcessRequestHandler() {
			
			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub
				ProDeviceService deviceService = (ProDeviceService) getService();
				PageBeanResult result = new PageBeanResult();
				PageBean page = deviceService.getRejectProDevices(actionForm.toMap());
				if (page != null && page.getList().size() > 0) {
					
					result.setStatusCode(ResponseConstants.OK);
					result.setResultMsg("获取储备项目设备数据成功！");
					result.setPageBean(page);
				}
				else {
					
					result.setStatusCode(ResponseConstants.NO_DATA);
					result.setResultMsg("获取储备项目设备数据失败！");
				}
				
				return result;
			}
		});
	}
	
	@RequestMapping(value="unAllocRMctDeviceList", method=RequestMethod.POST)
	public Object getUnAllocRMctDevicePage(HttpServletRequest request, HttpServletResponse response) {
		
		return this.process(request, response, new JsonProcessRequestHandler() {
			
			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub
				ProDeviceService deviceService = (ProDeviceService) getService();
				PageBeanResult result = new PageBeanResult();
				PageBean page = deviceService.getUnAllocRMctDevicePage(actionForm.toMap());
				if (page != null && page.getList().size() > 0) {
					
					result.setStatusCode(ResponseConstants.OK);
					result.setResultMsg("获取项目设备数据成功！");
					result.setPageBean(page);
				}
				else {
					
					result.setStatusCode(ResponseConstants.NO_DATA);
					result.setResultMsg("获取项目设备数据失败！");
				}
				
				return result;
			}
		});
	}
	
	@RequestMapping(value="synRMctDevice", method=RequestMethod.POST)
	public Object synRMctDevice(HttpServletRequest request, HttpServletResponse response) {
		
		return this.process(request, response, new JsonProcessRequestHandler() {
			
			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub
				
				ProDeviceService deviceService = (ProDeviceService) getService();
				BeanResult result = new BeanResult();
				int icount = deviceService.synRMctDevice(actionForm.toMap());
				if (icount > 0) {
					
					result.setResultMsg("成功同步数据");
					result.setStatusCode(ResponseConstants.OK);
					result.setData(icount);
				}
				else {
					
					result.setResultMsg("没有同步数据");
					result.setStatusCode(ResponseConstants.UPDATE_DATA_ERROR);
				}
				
				return result;
			}
		});
	}
	
}
