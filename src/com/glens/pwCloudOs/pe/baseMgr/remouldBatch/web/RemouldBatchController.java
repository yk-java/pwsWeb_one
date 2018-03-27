package com.glens.pwCloudOs.pe.baseMgr.remouldBatch.web;

import java.io.File;
import java.io.OutputStream;
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
import com.glens.eap.platform.framework.codeCenter.CodeWorker;
import com.glens.eap.platform.framework.context.EAPContext;
import com.glens.eap.platform.framework.web.EAPJsonAbstractController;
import com.glens.eap.platform.framework.web.ResponseConstants;
import com.glens.eap.platform.framework.web.support.JsonProcessRequestHandler;
import com.glens.eap.platform.framework.web.support.response.BeanResult;
import com.glens.eap.platform.framework.web.support.response.KeyResult;
import com.glens.eap.platform.framework.web.support.response.ListResult;
import com.glens.eap.platform.framework.web.support.response.PageBeanResult;
import com.glens.eap.platform.framework.web.support.response.ResponseResult;
import com.glens.pwCloudOs.config.PWCloudOsConfig;
import com.glens.pwCloudOs.om.deviceMgr.device.service.DeviceService;
import com.glens.pwCloudOs.om.deviceMgr.imagesMgr.vo.DeviceBookVo;
import com.glens.pwCloudOs.pe.baseMgr.remouldBatch.service.RemouldBatchService;
import com.glens.pwCloudOs.pe.baseMgr.reservePro.service.ReserveProService;
import com.glens.pwCloudOs.pe.baseMgr.reservePro.vo.ReserveProVo;
import com.glens.pwCloudOs.pe.proReserve.proDevice.service.ProDeviceService;

@FormProcessor(clazz="com.glens.pwCloudOs.pe.baseMgr.remouldBatch.web.RemouldBatchForm")
@RequestMapping("pmsServices/pe/baseMgr/remouldBatch")
public class RemouldBatchController extends EAPJsonAbstractController {
	
	
	private DeviceService deviceService;

	public DeviceService getDeviceService() {
		return deviceService;
	}


	public void setDeviceService(DeviceService deviceService) {
		this.deviceService = deviceService;
	}


	/**
	 * 查询改造批次下的设备清单 
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="queryBatchDevice", method=RequestMethod.POST)
	public Object queryBatchDevice(HttpServletRequest request, HttpServletResponse response) {
		
		return this.process(request, response, new JsonProcessRequestHandler() {
			
			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				RemouldBatchService service = (RemouldBatchService)getService();
				try {
					PageBean pageBean = service.queryBatchDevice(actionForm.toMap());
					return PageBeanResult.success("ok", pageBean);
				} catch (Exception e) {
					e.printStackTrace();
					return ResponseResult.fail(e.getMessage());
				}
			}
		});
	}
	
	
	//导出数据
	
	@RequestMapping(method = RequestMethod.POST, value = "exportBatchDevice")
	public Object dailySheetExport(HttpServletRequest request,
			HttpServletResponse response) {
		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {

				//DeviceService deviceService = (DeviceService) getService();
				RemouldBatchService service = (RemouldBatchService)getService();
				/* 表头数据 */
				
				Map<String, List<Map<String, Object>>> listAttrMap = deviceService
						.getMctDeviceListShowAttr(actionForm.toMap());
				
				List<String> theaderData = new ArrayList<String>();
				theaderData.add("序号");
				theaderData.add("改造单位名称");
				theaderData.add("设备名称");
				theaderData.add("设备编码");
				theaderData.add("表箱编号");
				theaderData.add("表号编号");
				//表箱编号  表号编号
				
				List filedlist=listAttrMap.get("listFields");
				
				for(int i=0;i<filedlist.size();i++){
					Map temp =(Map)filedlist.get(i);
					String cname=temp.get("cname").toString();
					theaderData.add(cname);
				}
				
				
				theaderData.add("计划改造时间");
				theaderData.add("实际改造时间");
				theaderData.add("改造状态");
				theaderData.add("确认验收");
				/* 表体数据 */
				/*List<Map> deviceList = deviceService
						.getMctDeviceList(actionForm.toMap());*/
				
				PageBean pageBean = service.getExportList(actionForm.toMap());
				List<Map> deviceList=pageBean.getList();
				
				
				List<List<Object>> tData = new ArrayList<List<Object>>();
				for (int i = 0; i < deviceList.size(); i++) {
					Map device = deviceList.get(i);
					
					List<Object> trData = new ArrayList<Object>();
					trData.add(i + 1);
					trData.add(device.get("reseverOrgname"));
					trData.add(device.get("deviceObjName"));
					trData.add(device.get("deviceObjCode"));
					
					trData.add(device.get("deviceBxId"));
					trData.add(device.get("deviceBhIds"));
					
					for(int j=0;j<filedlist.size();j++){
						Map temp =(Map)filedlist.get(j);
						String ename=temp.get("ename").toString();
						trData.add(device.get(ename));
					}
					
					trData.add(device.get("planRebuildTime"));
					trData.add(device.get("realRebuildTime"));
					
					String reserveStatus="";
					if("1".equals(device.get("reserveStatus"))){
						reserveStatus="已改造";
					}else if("2".equals(device.get("reserveStatus"))){
						reserveStatus="已改造被驳回";
					}else{
						reserveStatus="未改造";
					}
					trData.add(reserveStatus);
					String reserveConfirm="";
					if("1".equals(device.get("reserveConfirm"))){
						reserveConfirm="已确认";
					}else{
						reserveConfirm="未确认";
					}
					trData.add(reserveConfirm);
				
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
						String fileName = URLEncoder.encode("项目验收清单", "UTF-8");

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
						SXSSFSheet sheet = workbook.createSheet("项目验收清单");
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
	
	
	
	    // 导出图片
		@RequestMapping(value = "getZipFiles", method = RequestMethod.POST)
		public Object getZipFiles(HttpServletRequest request,
				HttpServletResponse response) {

			return this.process(request, response, new JsonProcessRequestHandler() {

				@Override
				public Object doWithRequest(HttpServletRequest request,
						HttpServletResponse response, ControllerForm actionForm) {
					// TODO Auto-generated method stub
					
					RemouldBatchService service = (RemouldBatchService)getService();
					
					//DeviceService deviceService = (DeviceService) getService();
					BeanResult result = new BeanResult();
					PageBean pageBean = service.getExportList(actionForm.toMap());
					List<Map> deviceList=pageBean.getList();
					
					

					// String
					// filePath="http://42.96.144.54:8070/pmsWeb/eap/pmsServices/fileServerClientService";

					String fileCodes = "";
					String fileNames = "";
					for (int i = 0; i < deviceList.size(); i++) {
						System.out.println(deviceList.get(i));

						Map m = (Map) deviceList.get(i);
						
						String deviceObjCode = m.get("deviceObjCode").toString();
						String deviceObjName = m.get("deviceObjName").toString();
						String reserveB1Img="";
						if(m.get("reserveB1Img")!=null && !m.get("reserveB1Img").equals("")){
							reserveB1Img=m.get("reserveB1Img").toString();
							
							fileCodes += reserveB1Img + ",";
							fileNames += deviceObjName +deviceObjCode+"改造前1,";
							
						}
						String reserveB2Img="";
						if(m.get("reserveB2Img")!=null && !m.get("reserveB2Img").equals("")){
							reserveB2Img=m.get("reserveB2Img").toString();
							fileCodes += reserveB2Img + ",";
							fileNames += deviceObjName +deviceObjCode+"改造前2,";
						}
						
						String reserveA1Img="";
						if(m.get("reserveA1Img")!=null && !m.get("reserveA1Img").equals("")){
							reserveA1Img=m.get("reserveA1Img").toString();
							fileCodes += reserveA1Img + ",";
							fileNames += deviceObjName +deviceObjCode+"改造后1,";
						}
						
						String reserveA2Img="";
						if(m.get("reserveA2Img")!=null && !m.get("reserveA2Img").equals("")){
							reserveA2Img=m.get("reserveA2Img").toString();
							fileCodes += reserveA2Img + ",";
							fileNames += deviceObjName +deviceObjCode+"改造后2,";
						}
						
						/*String reserveOpImg="";
						if(m.get("reserveOpImg")!=null && !m.get("reserveOpImg").equals("")){
							reserveOpImg=m.get("reserveOpImg").toString();
							fileCodes += reserveOpImg + ",";
							fileNames += deviceObjName +deviceObjCode+"工作票,";
						}
						
						String reserveSignImg="";
						if(m.get("reserveSignImg")!=null && !m.get("reserveSignImg").equals("")){
							reserveSignImg=m.get("reserveSignImg").toString();
							fileCodes += reserveSignImg + ",";
							fileNames += deviceObjName +deviceObjCode+"户变关系核查,";
						}
						
						String reservePcutImg="";
						if(m.get("reservePcutImg")!=null && !m.get("reservePcutImg").equals("")){
							reservePcutImg=m.get("reservePcutImg").toString();
							fileCodes += reservePcutImg + ",";
							fileNames += deviceObjName +deviceObjCode+"停电通知书,";
						}*/
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
	
	
	
	//手机端list
	@RequestMapping(value="queryBatchDevice1", method=RequestMethod.POST)
	public Object queryBatchDevice1(HttpServletRequest request, HttpServletResponse response) {
		
		return this.process(request, response, new JsonProcessRequestHandler() {
			
			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				RemouldBatchService service = (RemouldBatchService)getService();
				try {
					PageBean pageBean = service.queryBatchDevice1(actionForm.toMap());
					return PageBeanResult.success("ok", pageBean);
				} catch (Exception e) {
					e.printStackTrace();
					return ResponseResult.fail(e.getMessage());
				}
			}
		});
	}
	
	/**
	 * 查询储备项目下的设备清单 
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="queryProDevice", method=RequestMethod.POST)
	public Object queryProDevice(HttpServletRequest request, HttpServletResponse response) {
		
		return this.process(request, response, new JsonProcessRequestHandler() {
			
			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				RemouldBatchService service = (RemouldBatchService)getService();
				try {
					PageBean pageBean = service.queryProDevice(actionForm.toMap());
					return PageBeanResult.success("ok", pageBean);
				} catch (Exception e) {
					e.printStackTrace();
					return ResponseResult.fail(e.getMessage());
				}
			}
		});
	}
	
	/**
	 * 添加储备项目下的设备到改造批次下
	 * 根据查询条件查询储备项目下的设备信息，依次插入到改造批次下，修改储备项目下的设备改造状态为已避免设备重复出现
	 * 所需参数：储备项目下设备查询条件，改造批次相关信息
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="addProDeviceToBatch", method=RequestMethod.POST)
	public Object addProDeviceToBatch(HttpServletRequest request, HttpServletResponse response) {
		
		return this.process(request, response, new JsonProcessRequestHandler() {
			
			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				RemouldBatchService service = (RemouldBatchService)getService();
				try {
					int rows = service.addProDeviceToBatch(actionForm.toMap());
					return ResponseResult.success("updated "+rows+" rows data");
				} catch (Exception e) {
					e.printStackTrace();
					return ResponseResult.fail(e.getMessage());
				}
			}
		});
	}
	
	@RequestMapping(value="reserveConfirm", method=RequestMethod.POST)
	public Object reserveConfirm(HttpServletRequest request, HttpServletResponse response) {
		
		return this.process(request, response, new JsonProcessRequestHandler() {
			
			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				RemouldBatchService service = (RemouldBatchService)getService();
				try {
					int rows = service.reserveConfirm(actionForm.toMap());
					return ResponseResult.success("updated "+rows+" rows data");
				} catch (Exception e) {
					e.printStackTrace();
					return ResponseResult.fail(e.getMessage());
				}
			}
		});
	}
	
	@RequestMapping(value="delProDevice", method=RequestMethod.POST)
	public Object delProDevice(HttpServletRequest request, HttpServletResponse response) {
		
		return this.process(request, response, new JsonProcessRequestHandler() {
			
			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				RemouldBatchService service = (RemouldBatchService)getService();
				try {
					int rows = service.delProDevice(actionForm.toMap());
					return ResponseResult.success("updated "+rows+" rows data");
				} catch (Exception e) {
					e.printStackTrace();
					return ResponseResult.fail(e.getMessage());
				}
			}
		});
	}
	
	@RequestMapping(value="batDelProDevice", method=RequestMethod.POST)
	public Object batDelProDevice(HttpServletRequest request, HttpServletResponse response) {
		
		return this.process(request, response, new JsonProcessRequestHandler() {
			
			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				RemouldBatchService service = (RemouldBatchService)getService();
				try {
					int rows = service.batDelProDevice(actionForm.toMap());
					return ResponseResult.success("updated "+rows+" rows data");
				} catch (Exception e) {
					e.printStackTrace();
					return ResponseResult.fail(e.getMessage());
				}
			}
		});
	}
	
	/**
	 * 分配设备的改造单位信息
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="setDeviceToPro", method=RequestMethod.POST)
	public Object setDeviceToPro(HttpServletRequest request, HttpServletResponse response) {
		
		return this.process(request, response, new JsonProcessRequestHandler() {
			
			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				RemouldBatchService service = (RemouldBatchService)getService();
				try {
					int rows = service.setDeviceToPro(actionForm.toMap());
					return ResponseResult.success("updated "+rows+" rows data");
				} catch (Exception e) {
					e.printStackTrace();
					return ResponseResult.fail(e.getMessage());
				}
			}
		});
	}
	
	@RequestMapping(value="getProDevice", method=RequestMethod.GET)
	public Object getProDevice(HttpServletRequest request, HttpServletResponse response) {
		
		return this.process(request, response, new JsonProcessRequestHandler() {
			
			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub
				RemouldBatchService deviceService = (RemouldBatchService) getService();
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
	
	@RequestMapping(value="getProDevice1", method=RequestMethod.GET)
	public Object getProDevice1(HttpServletRequest request, HttpServletResponse response) {
		
		return this.process(request, response, new JsonProcessRequestHandler() {
			
			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub
				RemouldBatchService deviceService = (RemouldBatchService) getService();
				BeanResult result = new BeanResult();
				Map<String, Object> device = deviceService.getProDevice1(actionForm.toMap());
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
	
	
	@Override
	@RequestMapping(value="add", method=RequestMethod.POST)
	public Object insert(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub
				
				CodeWorker codeWorker = (CodeWorker) EAPContext.getContext().getBean(
						CodeWorker.SIMPLE_CODE_WORKER);
				String code = codeWorker.createCode("M");
				RemouldBatchForm form=(RemouldBatchForm)actionForm;
				form.setRemouldBatchCode(code);
				boolean ok =
					getService().insert(form.toVo());
				KeyResult result = new KeyResult();
				if (ok) {
					
					result.setStatusCode(ResponseConstants.OK);
					result.setResultMsg("新增成功");
					result.setGenerateKey(actionForm.getGenerateKey());
				}
				else {
					
					result.setStatusCode(ResponseConstants.SERVER_ERROR);
					result.setResultMsg("新增失败");
				}
				
				return result;
			}
			
		});
	}
	
	@RequestMapping(value="remouldStatistics", method=RequestMethod.GET)
	public Object getRemouldStatistics(HttpServletRequest request, HttpServletResponse response) {
		
		return this.process(request, response, new JsonProcessRequestHandler() {
			
			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub
				RemouldBatchService ser = (RemouldBatchService) getService();
				ListResult result = new ListResult();
				List<Map<String, Object>> proDeviceStatisticsList = ser.getRemouldStatistics(actionForm.toMap());
				if (proDeviceStatisticsList != null && proDeviceStatisticsList.size() > 0) {
					
					result.setResultMsg("获取改造项目统计信息成功");
					result.setStatusCode(ResponseConstants.OK);
					result.setList(proDeviceStatisticsList);
				}
				else {
					
					result.setResultMsg("获取改造项目统计信息失败");
					result.setStatusCode(ResponseConstants.NO_DATA);
				}
				
				return result;
			}
		});
	}
	
	//手机端报竣工
	@RequestMapping(value="updateDevice", method=RequestMethod.POST)
	public Object updateDevice(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub
				
				
				
				RemouldBatchService service=(RemouldBatchService)getService();
				
				int iCount=service.upDevice(actionForm.toMap());
				
				ResponseResult result = new ResponseResult();
				
				if (iCount > 0) {
					
					result.setStatusCode(ResponseConstants.OK);
					result.setResultMsg("修改成功");
				}
				else {
					
					result.setStatusCode(ResponseConstants.SERVER_ERROR);
					result.setResultMsg("修改失败");
				}
				
				return result;
			}
			
		});
	}
	
	
	//WEB端报竣工
		@RequestMapping(value="webUpdateDevice", method=RequestMethod.POST)
		public Object webUpdateDevice(HttpServletRequest request,
				HttpServletResponse response) {
			// TODO Auto-generated method stub
			return this.process(request, response, new JsonProcessRequestHandler() {

				@Override
				public Object doWithRequest(HttpServletRequest request,
						HttpServletResponse response, ControllerForm actionForm) {
					// TODO Auto-generated method stub
					
					
					
					RemouldBatchService service=(RemouldBatchService)getService();
					
					int iCount=service.upDevice1(actionForm.toMap());
					
					ResponseResult result = new ResponseResult();
					
					if (iCount > 0) {
						
						result.setStatusCode(ResponseConstants.OK);
						result.setResultMsg("修改成功");
					}
					else {
						
						result.setStatusCode(ResponseConstants.SERVER_ERROR);
						result.setResultMsg("修改失败");
					}
					
					return result;
				}
				
			});
		}
	
	@RequestMapping(value="updateRemouldBatchDevice", method=RequestMethod.POST)
	public Object updateRemouldBatchDevice(HttpServletRequest request, HttpServletResponse response) {
		
		return this.process(request, response, new JsonProcessRequestHandler() {
			
			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub
				RemouldBatchService deviceService = (RemouldBatchService) getService();
				ResponseResult result = new ResponseResult();
				int icount = deviceService.updateRemouldBatchDevice(actionForm.toMap());
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
	
	@RequestMapping(value="updateRemouldMaterial", method=RequestMethod.POST)
	public Object updateRemouldMaterial(HttpServletRequest request, HttpServletResponse response) {
		
		return this.process(request, response, new JsonProcessRequestHandler() {
			
			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub
				RemouldBatchService deviceService = (RemouldBatchService) getService();
				ResponseResult result = new ResponseResult();
				int icount = deviceService.updateRemouldMaterial(actionForm.toMap());
				if (icount > 0) {
					
					result.setResultMsg("更新改造材料成功！");
					result.setStatusCode(ResponseConstants.OK);
				}
				else {
					
					result.setResultMsg("更新改造材料失败！");
					result.setStatusCode(ResponseConstants.UPDATE_DATA_ERROR);
				}
				
				return result;
			}
		});
	}
	
}
