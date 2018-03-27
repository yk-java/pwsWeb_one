package com.glens.pwCloudOs.om.deviceMgr.imagesMgr.web;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.net.*;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import com.glens.eap.platform.core.annotation.FormProcessor;
import com.glens.eap.platform.core.web.ControllerForm;
import com.glens.eap.platform.framework.beans.PageBean;
import com.glens.eap.platform.framework.codeCenter.CodeWorker;
import com.glens.eap.platform.framework.context.EAPContext;
import com.glens.eap.platform.framework.web.EAPJsonAbstractController;
import com.glens.eap.platform.framework.web.ResponseConstants;
import com.glens.eap.platform.framework.web.support.JsonProcessRequestHandler;
import com.glens.eap.platform.framework.web.support.response.KeyResult;
import com.glens.eap.platform.framework.web.support.response.PageBeanResult;
import com.glens.eap.platform.framework.web.support.response.ResponseResult;
import com.glens.pwCloudOs.commuteMgr.monitor.service.CommuteGpsService;
import com.glens.pwCloudOs.commuteMgr.monitor.web.CommuteGpsForm;
import com.glens.pwCloudOs.om.deviceMgr.imagesMgr.service.ImagesService;
import com.glens.pwCloudOs.om.deviceMgr.imagesMgr.vo.DeviceBookVo;
import com.glens.pwCloudOs.om.deviceMgr.imagesMgr.vo.DeviceVo;
import com.mongodb.util.Hash;

@FormProcessor(clazz="com.glens.pwCloudOs.om.deviceMgr.imagesMgr.web.DeviceBookForm")
@RequestMapping("pmsServices/om/deviceMgr/images")
public class ImagesController extends EAPJsonAbstractController {

	//图片列表
	@RequestMapping(value="getList", method=RequestMethod.GET)
	public Object getList(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub
				DeviceBookForm form=(DeviceBookForm)actionForm;

				ImagesService service=(ImagesService)getService();
				List<DeviceBookVo> list=service.getList(form.getCompanyCode(), form.getProNo(), form.getMctCode(), form.getDeviceObjCode());
				if(list.size()>0){
					service.updatePic(form.getDeviceObjCode(),"1");
				}else{
					service.updatePic(form.getDeviceObjCode(),"0");
				}
				
				
				
				PageBeanResult result = new PageBeanResult();
				if (list != null) {
					result.setStatusCode(ResponseConstants.OK);
					result.setResultMsg("获取成功");
					result.setList(list);
				}
				return result;
			}
		});
	}



	@Override
	@RequestMapping(value="delete", method=RequestMethod.POST)
	public Object delete(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub
				DeviceBookForm form=(DeviceBookForm)actionForm;

				ImagesService service=(ImagesService)getService();
				boolean isdelete=service.deleteImg(form.getPicVisitid());
				ResponseResult result = new ResponseResult();

				if (isdelete) {

					result.setStatusCode(ResponseConstants.OK);
					result.setResultMsg("删除成功");
				}
				else {

					result.setStatusCode(ResponseConstants.SERVER_ERROR);
					result.setResultMsg("删除出错");
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

				ImagesService ser=(ImagesService)getService();
				boolean ok;
				KeyResult result = new KeyResult();
				try {
					ok = ser.insert(actionForm.toVo(), getRootPath(request));
					if (ok) {
						result.setStatusCode(ResponseConstants.OK);
						result.setResultMsg("新增成功");
						result.setGenerateKey(actionForm.getGenerateKey());
					}
					else {

						result.setStatusCode(ResponseConstants.SERVER_ERROR);
						result.setResultMsg("新增失败");
					}

				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return result;
			}

		});
	}
	
	
	
    //通用接口 上传图片  返回成功之后的filecode
	@RequestMapping(value="uploadImg", method=RequestMethod.POST)
	public Object uploadImg(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub

				ImagesService ser=(ImagesService)getService();
				boolean ok;
				KeyResult result = new KeyResult();
				try {
					String filecode = ser.uploadImg(actionForm.toVo(), getRootPath(request));
					if (filecode!=null && !filecode.equals("")) {
						result.setStatusCode(ResponseConstants.OK);
						result.setResultMsg(filecode);
						result.setGenerateKey(actionForm.getGenerateKey());
					}
					else {

						result.setStatusCode(ResponseConstants.SERVER_ERROR);
						result.setResultMsg("");
					}

				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return result;
			}

		});
	}

//	@RequestMapping(value="importLwsImg", method=RequestMethod.GET)
//	public Object importLwsImg(HttpServletRequest request, HttpServletResponse response) {
//		// TODO Auto-generated method stub
//		return this.process(request, response, new JsonProcessRequestHandler() {
//
//			@Override
//			public Object doWithRequest(HttpServletRequest request,
//					HttpServletResponse response, ControllerForm actionForm) {
//				// TODO Auto-generated method stub
//
//				PageBeanResult result = new PageBeanResult();
//				ImagesService ser=(ImagesService)getService();
//				List<DeviceVo> list=ser.getDeviceList("NJSQ_BXKC_LWS_2016_0001");  
//
//				for(int i=0;i<list.size();i++){
//					DeviceVo vo=list.get(i);
//
//					String addr=vo.getAMUL_BOX_ADDR();//表箱地址
//					String estaeName=vo.getESTAE_NAME();//小区名称
//					String companyCode=vo.getCompanyCode();
//					String mctCode=vo.getMctCode();
//					String proNo=vo.getProNo();
//					String deviceObjCode=vo.getDeviceObjCode();
//					
//					/*if(estaeName.equals("中山北路")){
//						System.out.println(111);
//					}else{
//						continue;
//					}*/
//
//					List<Map<String, Object>> deviceImgList=ser.getImageslist(addr, estaeName);//设备对应的图片
//
//					if(deviceImgList.size()>0){
//
//						for(int j=0;j<deviceImgList.size();j++){
//							Map m=deviceImgList.get(j);
//							String storageLocation="";
//							if(m.get("storageLocation")!=null){
//								storageLocation=m.get("storageLocation").toString();
//							}else{
//
//								continue;
//							}
//
//							String filepath="http://42.96.137.173:8080/NJJLWeb/"+storageLocation;
//							//actionForm.toVo(), getRootPath(request);
//
//							String suffname=filepath.substring(filepath.lastIndexOf(".")+1,filepath.length());
//
//							String picTitle=estaeName+"_"+addr+"0"+(j+1);
//
//							File file=new File(filepath);
//
//							/*if(file.getName().equals("4a9409978ea741939a1fa3d0a6df1a1a.jpg")){
//									inx=1;
//									System.out.println(1);
//								}else{
//									continue;
//								}*/
//
//							System.out.println(file.getName()+"i:"+i);
//
//							DeviceBookVo book=new DeviceBookVo();
//							book.setCompanyCode(companyCode);
//							book.setDeviceObjCode(deviceObjCode);
//							book.setMctCode(mctCode);
//							book.setProNo(proNo);
//							book.setPicTitle(picTitle);
//
//							String fileCode=storageLocation.substring(storageLocation.lastIndexOf("/")+1,storageLocation.length()-4);
//							//System.out.println(fileCode);
//
//
//							try {
//								//Long size=getFileSizes(file);
//								URL url =new URL(filepath);
//								URLConnection uc = url.openConnection();
//
//
//								String fileSize=uc.getContentLength()+"";
//								ser.importImg(book,suffname,picTitle,file,fileSize,fileCode);
//							} catch (IOException e) {
//								// TODO Auto-generated catch block
//								e.printStackTrace();
//							} catch (Exception e) {
//								// TODO Auto-generated catch block
//								e.printStackTrace();
//							}
//
//
//
//						}
//
//
//					}else{
//						try {
//							File f=new File("D:\\rizhi.txt");
//							FileWriter fw = new FileWriter(f,true);
//
//							fw.write(estaeName+"_"+addr+"没有对应的图片\n"); 
//							fw.close(); 
//						} catch (IOException e) {
//							// TODO Auto-generated catch block
//							e.printStackTrace();
//						}
//					}	
//				}
//				System.out.println("-----------------插入完毕--------------");
//				result.setStatusCode(ResponseConstants.OK);
//				result.setResultMsg("获取成功");
//				return result;
//
//
//			}
//
//		});
//	}

	public void copyFile(File oldFile, String newPath) { 
		try { 
			int bytesum = 0; 
			int byteread = 0; 
			// File oldfile = new File(oldPath); 
			if (oldFile.exists()) { //文件存在时 
				InputStream inStream = new FileInputStream(oldFile); //读入原文件 
				FileOutputStream fs = new FileOutputStream(newPath); 
				byte[] buffer = new byte[1444]; 
				int length; 
				while ( (byteread = inStream.read(buffer)) != -1) { 
					bytesum += byteread; //字节数 文件大小 
					//System.out.println(bytesum); 
					fs.write(buffer, 0, byteread); 
				} 
				inStream.close(); 
			}else{
				System.out.println("不存在");
			}
		} 
		catch (Exception e) { 
			System.out.println("复制单个文件操作出错"); 
			e.printStackTrace();
		} 
	}



	@RequestMapping(value="importOthers", method=RequestMethod.GET)
	public Object importOthers(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub
				DeviceBookForm form=(DeviceBookForm)actionForm;

				int flag=form.getFlag();

				//NJJL_FY_BXKC_2016_0001  富阳
				//NJSQ_SL_BXKC_20160001  松林
				//NJSQ_HQ_BXKC_20160001 华强
				//NJSQ_QXYD_BXKC_20160001 栖霞一队
				//NJSQ_ZY_BXKC_20160001 业主表箱
				//NJSQ_HG_BXKC_20160001 汉高

				String pno="";
				String rizhi="";
				if(flag==1){  //富阳
					pno="NJJL_FY_BXKC_2016_0001";
					rizhi="fuyang";
				}else if(flag==2){//松林
					pno="NJSQ_SL_BXKC_20160001";
					rizhi="songlin";
				}else if(flag==3){//华强
					pno="NJSQ_HQ_BXKC_20160001";
					rizhi="huaqiang";
				}else if(flag==4){//栖霞
					pno="NJSQ_QXYD_BXKC_20160001";
					rizhi="qixia";
				}else if(flag==5){//业主表箱
					pno="NJSQ_ZY_BXKC_20160001";
					rizhi="yezhu";
				}else if(flag==6){//汉高
					pno="NJSQ_HG_BXKC_20160001";
					rizhi="hangao";
				}else if(flag==7){
					pno="NJSQ_BXKC_LWS_2016_0001";
					rizhi="lws";
				}else if(flag==8){
					pno="NJSQ_BXKC_2016_0001";
					rizhi="bxkc";
				}

				PageBeanResult result = new PageBeanResult();
				ImagesService ser=(ImagesService)getService();
				
				
				
				List<DeviceVo> list=ser.getDeviceList(pno);
				
				
				
				//////////////////////////////////////////  测试用
				//mctCode,companyCode,proNo,deviceObjCode
				
				/*ImagesService service=(ImagesService)getService();
				for(int i=0;i<list.size();i++){
					DeviceVo d= list.get(i);
					
					String estaeName=d.getESTAE_NAME();
					String addr=d.getAMUL_BOX_ADDR();
					System.out.println(estaeName+"--"+addr);
					List<DeviceBookVo> temp=service.getList(d.getCompanyCode(), d.getProNo(), d.getMctCode(), d.getDeviceObjCode());
					if(temp.size()==0){
						try {
							File rz=new File("D:\\"+rizhi+".txt");
							FileWriter fw = new FileWriter(rz,true);

							fw.write(rizhi+"_"+estaeName+"_"+addr+"没有对应的图片\n"); 
							fw.close(); 
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					
				}*/
				//////////////////////////////////////////测试用
                 
				
				////////////////导入
				//导入开始
				String rootPath="D:\\deviceImg\\"+rizhi;
				String destPath="D:\\deviceImg1\\"+rizhi+"\\";
				String imgpath="D:\\deviceImg1\\images\\";
				//System.out.println(rootPath);
				File file = new File(rootPath);
				File[] fs = file.listFiles();
				int num=0;
				for(int i=0;i<list.size();i++){
					DeviceVo vo=list.get(i);
					String addr=vo.getAMUL_BOX_ADDR();//表箱地址
					String deviceName=vo.getDeviceObjName();
					//String estaeName=vo.getESTAE_NAME();//小区名称
					String companyCode=vo.getCompanyCode();
					String mctCode=vo.getMctCode();
					String proNo=vo.getProNo();
					String deviceObjCode=vo.getDeviceObjCode();


					boolean isfind=false; //设备是否有对应的图片
					int indx=0;
					for (File f : fs) {

						isfind=false;
						//if(f.getName().indexOf(addr)>-1&&f.getName().indexOf(estaeName)>-1){
						String fname=f.getName();
						
						
						if(fname.indexOf("_")>-1){//包含下划线
							fname=fname.substring(0,fname.lastIndexOf("_"));
						}else{//不包含
							fname=fname.substring(0,fname.lastIndexOf("."));
						}
						
						
						
						if(fname.equals(deviceName)){

							System.out.println("文件----------------：" + f.getName());

							String picTitel=f.getName().substring(f.getName().lastIndexOf("/")+1,f.getName().length()-4);

							DeviceBookVo book=new DeviceBookVo();
							book.setCompanyCode(companyCode);
							book.setDeviceObjCode(deviceObjCode);
							book.setMctCode(mctCode);
							book.setProNo(proNo);
							book.setPicTitle(picTitel);

							CodeWorker codeWorker = (CodeWorker) EAPContext.getContext().getBean(
									CodeWorker.SIMPLE_CODE_WORKER);
							String fileCode = codeWorker.createCode("F");

							//actionForm.toVo(), getRootPath(request);

							String suffname=f.getName().substring(f.getName().lastIndexOf(".")+1,f.getName().length());

							//String picTitle=estaeName+"_"+addr+"0"+(indx+1);
							String picTitle=addr+"0"+(indx+1);

							String fileSize=f.length()+"";

							try {


								ser.importImg(book,suffname,picTitle,file,fileSize,fileCode);
								copyFile(f,destPath+f.getName());// 复制一份源文件
								copyFile(f,imgpath+fileCode+"."+suffname); //文件移动并重命名
								   if(f.exists()){
								    	f.delete();//删除文件
								    }
								isfind=true;

							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							} catch (ParseException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
					}	
				}
			
				//////导入结束	
				ImagesService service=(ImagesService)getService();
				for(int j=0;j<list.size();j++){
					DeviceVo d= list.get(j);
					String estaeName1=d.getESTAE_NAME();
					String addr1=d.getAMUL_BOX_ADDR();
					String deviceCode=d.getDeviceObjCode();
					String mctCode=d.getMctCode();
					String proName=d.getProName();
					String REFORM_PROGRAM=d.getREFORM_PROGRAM();
					
					System.out.println(estaeName1+"--"+addr1);
					List<DeviceBookVo> temp=service.getList(d.getCompanyCode(), d.getProNo(), d.getMctCode(), d.getDeviceObjCode());
					if(temp.size()==0){
						try {
							File rz=new File("D:\\"+rizhi+".txt");
							FileWriter fw = new FileWriter(rz,true);

							fw.write(pno+","+estaeName1+","+addr1+","+deviceCode+","+mctCode+","+proName+","+REFORM_PROGRAM+"\n"); 
							fw.close(); 
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					
				}
				
				System.out.println("========完成=========");


				return result;
			}
		});
	}
	
	
	
	///获取没有图片的设备
	@RequestMapping(value="getNoImgs", method=RequestMethod.GET)
	public Object getNoImgs(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub
				DeviceBookForm form=(DeviceBookForm)actionForm;

				int flag=form.getFlag();

				//NJJL_FY_BXKC_2016_0001  富阳
				//NJSQ_SL_BXKC_20160001  松林
				//NJSQ_HQ_BXKC_20160001 华强
				//NJSQ_QXYD_BXKC_20160001 栖霞一队
				//NJSQ_ZY_BXKC_20160001 业主表箱
				//NJSQ_HG_BXKC_20160001 汉高

				String pno="";
				String rizhi="";
				if(flag==1){  //富阳
					pno="NJJL_FY_BXKC_2016_0001";
					rizhi="fuyang";
				}else if(flag==2){//松林
					pno="NJSQ_SL_BXKC_20160001";
					rizhi="songlin";
				}else if(flag==3){//华强
					pno="NJSQ_HQ_BXKC_20160001";
					rizhi="huaqiang";
				}else if(flag==4){//栖霞
					pno="NJSQ_QXYD_BXKC_20160001";
					rizhi="qixia";
				}else if(flag==5){//业主表箱
					pno="NJSQ_ZY_BXKC_20160001";
					rizhi="yezhu";
				}else if(flag==6){//汉高
					pno="NJSQ_HG_BXKC_20160001";
					rizhi="hangao";
				}else if(flag==7){
					pno="NJSQ_BXKC_LWS_2016_0001";
					rizhi="lws";
				}

				PageBeanResult result = new PageBeanResult();
				ImagesService ser=(ImagesService)getService();
				List<DeviceVo> list=ser.getDeviceList(pno);
				
			//////导入结束	
				ImagesService service=(ImagesService)getService();
				for(int j=0;j<list.size();j++){
					DeviceVo d= list.get(j);
					String estaeName1=d.getESTAE_NAME();
					String addr1=d.getAMUL_BOX_ADDR();
					String deviceCode=d.getDeviceObjCode();
					String mctCode=d.getMctCode();
					String proName=d.getProName();
					String REFORM_PROGRAM=d.getREFORM_PROGRAM();
					
					System.out.println(estaeName1+"--"+addr1);
					List<DeviceBookVo> temp=service.getList(d.getCompanyCode(), d.getProNo(), d.getMctCode(), d.getDeviceObjCode());
					if(temp.size()==0){
						try {
							File rz=new File("D:\\"+rizhi+".txt");
							FileWriter fw = new FileWriter(rz,true);

							fw.write(pno+","+estaeName1+","+addr1+","+deviceCode+","+mctCode+","+proName+","+REFORM_PROGRAM+"\n"); 
							fw.close(); 
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					
				}
				
				System.out.println("========完成=========");
				return result;
			}
		});
	}
	
	///添加pic字段
		@RequestMapping(value="updatePics", method=RequestMethod.GET)
		public Object updatePics(HttpServletRequest request, HttpServletResponse response) {
			// TODO Auto-generated method stub
			return this.process(request, response, new JsonProcessRequestHandler() {

				@Override
				public Object doWithRequest(HttpServletRequest request,
						HttpServletResponse response, ControllerForm actionForm) {
					// TODO Auto-generated method stub
					DeviceBookForm form=(DeviceBookForm)actionForm;

					//int flag=form.getFlag();


					PageBeanResult result = new PageBeanResult();
					ImagesService ser=(ImagesService)getService();
					List<DeviceVo> list=ser.getAllDeviceList();
					
				//////导入结束	
					ImagesService service=(ImagesService)getService();
					for(int j=0;j<list.size();j++){
						DeviceVo d= list.get(j);
						String estaeName1=d.getESTAE_NAME();
						String addr1=d.getAMUL_BOX_ADDR();
						String deviceCode=d.getDeviceObjCode();
						String mctCode=d.getMctCode();
						String proName=d.getProName();
						String REFORM_PROGRAM=d.getREFORM_PROGRAM();
						String deviceObjectCode=d.getDeviceObjCode();
						
						System.out.println(estaeName1+"--"+addr1);
						List<DeviceBookVo> temp=service.getList(d.getCompanyCode(), d.getProNo(), d.getMctCode(), d.getDeviceObjCode());
						if(temp.size()==0){
							service.updatePic(deviceObjectCode, "0");
						}else{
							service.updatePic(deviceObjectCode, "1");
						}
						
					}
					result.setStatusCode(ResponseConstants.OK);
					
					System.out.println("========完成=========");
					return result;
				}
			});
		}
		
		//更新一个
		@RequestMapping(value="updateOnePic", method=RequestMethod.GET)
		public Object updateOnePic(HttpServletRequest request, HttpServletResponse response) {
			// TODO Auto-generated method stub
			return this.process(request, response, new JsonProcessRequestHandler() {

				@Override
				public Object doWithRequest(HttpServletRequest request,
						HttpServletResponse response, ControllerForm actionForm) {
					// TODO Auto-generated method stub
					DeviceBookForm form=(DeviceBookForm)actionForm;

					//int flag=form.getFlag();


					PageBeanResult result = new PageBeanResult();
					ImagesService ser=(ImagesService)getService();
					List<DeviceVo> list=ser.getObje(form.getDeviceObjCode());
					
				//////导入结束	
					ImagesService service=(ImagesService)getService();
					for(int j=0;j<list.size();j++){
						DeviceVo d= list.get(j);
						String estaeName1=d.getESTAE_NAME();
						String addr1=d.getAMUL_BOX_ADDR();
						String deviceObjectCode=d.getDeviceObjCode();
						
						System.out.println(estaeName1+"--"+addr1);
						List<DeviceBookVo> temp=service.getList(d.getCompanyCode(), d.getProNo(), d.getMctCode(), d.getDeviceObjCode());
						if(temp.size()==0){
							service.updatePic(deviceObjectCode, "0");
						}else{
							service.updatePic(deviceObjectCode, "1");
						}
						
					}
					result.setStatusCode(ResponseConstants.OK);
					
					System.out.println("========完成=========");
					return result;
				}
			});
		}



//	@RequestMapping(value="getImagelist", method=RequestMethod.GET)
//	public Object getImagelist(HttpServletRequest request, HttpServletResponse response) {
//		// TODO Auto-generated method stub
//		return this.process(request, response, new JsonProcessRequestHandler() {
//
//			@Override
//			public Object doWithRequest(HttpServletRequest request,
//					HttpServletResponse response, ControllerForm actionForm) {
//				// TODO Auto-generated method stub
//
//				PageBeanResult result = new PageBeanResult();
//
//				ImagesForm form=(ImagesForm)actionForm;
//				String proNo=form.getProNo();
//				if(proNo!=null&&proNo.equals("NJSQ_BXKC_LWS_2016_0001")){//南京量为石
//					String addr=form.getAddr();
//					String estaeName=form.getEstaeName();
//					ImagesService ser=(ImagesService)getService();
//					List<Map<String, Object>> list=ser.getImageslist(addr, estaeName);  
//
//
//					/*List<Map<String, Object>> list=er.getImageslist(); 
//
//					小区名称 表项地址
//					--  mongodb   shebei  */
//
//
//
//
//
//					//{BX_NAME=01A, crateTime=2016-08-10 09:32:06.0, BX_CODE=0500625563 , storageLocation=file/upload/06aa3c89f46e4619bc98bad025bac461.jpg, ID=3020e65886024945839a6a643d7a149f, userName=量为石, ATTACHMENT_OBJECT_ID=3020e65886024945839a6a643d7a149f, FILE_NAME=大锏银巷小区01A一层
//					//storageLocation
//					List<Map<String, Object>> resultList=new ArrayList<Map<String,Object>>();
//					for(int i=0;i<list.size();i++){
//						Map m=list.get(i);
//						String storageLocation=m.get("storageLocation").toString();
//						Map mp=new HashMap();
//						mp.put("url","http://42.96.137.173:8080/NJJLWeb/"+storageLocation);
//						resultList.add(mp);
//					}
//					if (resultList != null) {
//						result.setStatusCode(ResponseConstants.OK);
//						result.setResultMsg("获取成功");
//						result.setList(resultList);
//					}
//					else {	
//						result.setStatusCode(ResponseConstants.NO_DATA);
//						result.setResultMsg("没有获取到数据");
//					}
//
//					return result;
//				}else{
//
//					String rootPath=request.getSession().getServletContext().getRealPath("/")+"img\\deviceImgs";
//					//System.out.println(rootPath);
//					File file = new File(rootPath);
//					File[] fs = file.listFiles();
//					String addr=form.getAddr();
//					String estaeName=form.getEstaeName();
//
//
//					List<Map<String, Object>> list=new ArrayList<Map<String,Object>>();
//
//					for (File f : fs) {
//						System.out.println("文件：" + f.getName());
//						if(f.getName().indexOf(addr)>-1&&f.getName().indexOf(estaeName)>-1){
//							Map mp=new HashMap();
//							mp.put("url", "../../img/deviceImgs/"+f.getName());
//							list.add(mp);
//						}
//					}
//					if (list != null) {
//						result.setStatusCode(ResponseConstants.OK);
//						result.setResultMsg("获取成功");
//						result.setList(list);
//					}
//					else {	
//						result.setStatusCode(ResponseConstants.NO_DATA);
//						result.setResultMsg("没有获取到数据");
//					}
//
//					return result;
//				}
//
//
//			}
//
//		});
//	}



}
