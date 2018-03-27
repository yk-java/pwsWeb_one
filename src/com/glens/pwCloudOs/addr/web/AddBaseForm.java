package com.glens.pwCloudOs.addr.web;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import java.util.zip.GZIPInputStream;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.glens.eap.platform.core.web.ControllerForm;

public class AddBaseForm extends ControllerForm {
	
	
	private String COMPANY_CODE;
	private String PROVINCE;//省
	private String CITY;//市
	private String REGION;//区县
	private String TOWN_SHIP;//街道
	private String STREET;//街路巷
	private String ROOM_NO;//门牌号;
	private String PLOT;//小区
	private String BUILDING_NO;//楼牌号
	private String UNIT_NO;//单元号
	private String GROUP;//村组
	private String FARMER_ROOM_NO;//农户门牌号
	private String ZAIMUTH;//方位
	private String PARENT_ADDR_CODE;//上级地址编码
	private String PARENT_ADDR_NAME;//上级地址名称
	private String ADDR_CODE;//地址统一编码
    private String ADDR_NAME;//地址名称
    private String ADDR_CLASS;//地址级别
    private String ADDR_CONTENT;//地址内容
    private String WGS84_X;//地址标准经度
    private String WGS84_Y;//地址标准维度
    private String SHOW_X;//地址显示经度
    private String SHOW_Y;//地址显示维度
    private String IS_REGION;//是否区域
    private String SHOW_XS;//区域范围经度;
    private String SHOW_YS;//区域范围维度
    private int ADDR_STATUS;//地址状态
    private String REMARKS;
    
    private String UNIT_NAME;
    private String UP_FLOOR_NUM;
    private String DOWN_FLOOR_NUM;
    private String HOUSE_NUM;
    private String POLICE_PIC;
    
    private String BOX_NO;//表箱号
    private String AMUL_BOX_NUM;//位数
    private String HOUSE_HOLD;//户数
    private String AMULBOX_CLASS;//表箱类型
    private String ELECTRIC_METER;//电表
    private String ELECTRIC_PIC;
    
    
    private String companyCode;
    private String x;
    private String y;
    private String attrJson;
    private String deviceObjName;

    private String deviceimg1;
    private String deviceimg2;
    private String deviceimg3;
    
	
	

	public String getDeviceimg1() {
		return deviceimg1;
	}

	public void setDeviceimg1(String deviceimg1) {
		this.deviceimg1 = deviceimg1;
	}

	public String getDeviceimg2() {
		return deviceimg2;
	}

	public void setDeviceimg2(String deviceimg2) {
		this.deviceimg2 = deviceimg2;
	}

	public String getDeviceimg3() {
		return deviceimg3;
	}

	public void setDeviceimg3(String deviceimg3) {
		this.deviceimg3 = deviceimg3;
	}

	public String getX() {
		return x;
	}

	public void setX(String x) {
		this.x = x;
	}

	public String getY() {
		return y;
	}

	public void setY(String y) {
		this.y = y;
	}

	public String getAttrJson() {
		return attrJson;
	}

	public void setAttrJson(String attrJson) {
		this.attrJson = attrJson;
	}

	public String getDeviceObjName() {
		return deviceObjName;
	}

	public void setDeviceObjName(String deviceObjName) {
		this.deviceObjName = deviceObjName;
	}

	public String getCompanyCode() {
		return companyCode;
	}

	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}

	public String getBOX_NO() {
		return BOX_NO;
	}

	public void setBOX_NO(String bOX_NO) {
		BOX_NO = bOX_NO;
	}

	public String getAMUL_BOX_NUM() {
		return AMUL_BOX_NUM;
	}

	public void setAMUL_BOX_NUM(String aMUL_BOX_NUM) {
		AMUL_BOX_NUM = aMUL_BOX_NUM;
	}

	public String getHOUSE_HOLD() {
		return HOUSE_HOLD;
	}

	public void setHOUSE_HOLD(String hOUSE_HOLD) {
		HOUSE_HOLD = hOUSE_HOLD;
	}

	public String getAMULBOX_CLASS() {
		return AMULBOX_CLASS;
	}

	public void setAMULBOX_CLASS(String aMULBOX_CLASS) {
		AMULBOX_CLASS = aMULBOX_CLASS;
	}

	public String getELECTRIC_METER() {
		return ELECTRIC_METER;
	}

	public void setELECTRIC_METER(String eLECTRIC_METER) {
		ELECTRIC_METER = eLECTRIC_METER;
	}

	public String getELECTRIC_PIC() {
		return ELECTRIC_PIC;
	}

	public void setELECTRIC_PIC(String eLECTRIC_PIC) {
		ELECTRIC_PIC = eLECTRIC_PIC;
	}

	public String getUNIT_NAME() {
		return UNIT_NAME;
	}

	public void setUNIT_NAME(String uNIT_NAME) {
		UNIT_NAME = uNIT_NAME;
	}

	public String getUP_FLOOR_NUM() {
		return UP_FLOOR_NUM;
	}

	public void setUP_FLOOR_NUM(String uP_FLOOR_NUM) {
		UP_FLOOR_NUM = uP_FLOOR_NUM;
	}

	public String getDOWN_FLOOR_NUM() {
		return DOWN_FLOOR_NUM;
	}

	public void setDOWN_FLOOR_NUM(String dOWN_FLOOR_NUM) {
		DOWN_FLOOR_NUM = dOWN_FLOOR_NUM;
	}

	public String getHOUSE_NUM() {
		return HOUSE_NUM;
	}

	public void setHOUSE_NUM(String hOUSE_NUM) {
		HOUSE_NUM = hOUSE_NUM;
	}

	public String getPOLICE_PIC() {
		return POLICE_PIC;
	}

	public void setPOLICE_PIC(String pOLICE_PIC) {
		POLICE_PIC = pOLICE_PIC;
	}

	public String getCOMPANY_CODE() {
		return COMPANY_CODE;
	}

	public void setCOMPANY_CODE(String cOMPANY_CODE) {
		COMPANY_CODE = cOMPANY_CODE;
	}

	public String getPROVINCE() {
		return PROVINCE;
	}

	public void setPROVINCE(String pROVINCE) {
		PROVINCE = pROVINCE;
	}

	public String getCITY() {
		return CITY;
	}

	public void setCITY(String cITY) {
		CITY = cITY;
	}

	public String getREGION() {
		return REGION;
	}

	public void setREGION(String rEGION) {
		REGION = rEGION;
	}

	public String getTOWN_SHIP() {
		return TOWN_SHIP;
	}

	public void setTOWN_SHIP(String tOWN_SHIP) {
		TOWN_SHIP = tOWN_SHIP;
	}

	public String getSTREET() {
		return STREET;
	}

	public void setSTREET(String sTREET) {
		STREET = sTREET;
	}

	public String getROOM_NO() {
		return ROOM_NO;
	}

	public void setROOM_NO(String rOOM_NO) {
		ROOM_NO = rOOM_NO;
	}

	public String getPLOT() {
		return PLOT;
	}

	public void setPLOT(String pLOT) {
		PLOT = pLOT;
	}

	public String getBUILDING_NO() {
		return BUILDING_NO;
	}

	public void setBUILDING_NO(String bUILDING_NO) {
		BUILDING_NO = bUILDING_NO;
	}

	public String getUNIT_NO() {
		return UNIT_NO;
	}

	public void setUNIT_NO(String uNIT_NO) {
		UNIT_NO = uNIT_NO;
	}

	public String getGROUP() {
		return GROUP;
	}

	public void setGROUP(String gROUP) {
		GROUP = gROUP;
	}

	public String getFARMER_ROOM_NO() {
		return FARMER_ROOM_NO;
	}

	public void setFARMER_ROOM_NO(String fARMER_ROOM_NO) {
		FARMER_ROOM_NO = fARMER_ROOM_NO;
	}

	public String getZAIMUTH() {
		return ZAIMUTH;
	}

	public void setZAIMUTH(String zAIMUTH) {
		ZAIMUTH = zAIMUTH;
	}

	public String getPARENT_ADDR_CODE() {
		return PARENT_ADDR_CODE;
	}

	public void setPARENT_ADDR_CODE(String pARENT_ADDR_CODE) {
		PARENT_ADDR_CODE = pARENT_ADDR_CODE;
	}

	public String getPARENT_ADDR_NAME() {
		return PARENT_ADDR_NAME;
	}

	public void setPARENT_ADDR_NAME(String pARENT_ADDR_NAME) {
		PARENT_ADDR_NAME = pARENT_ADDR_NAME;
	}

	public String getADDR_CODE() {
		return ADDR_CODE;
	}

	public void setADDR_CODE(String aDDR_CODE) {
		ADDR_CODE = aDDR_CODE;
	}

	public String getADDR_NAME() {
		return ADDR_NAME;
	}

	public void setADDR_NAME(String aDDR_NAME) {
		ADDR_NAME = aDDR_NAME;
	}

	public String getADDR_CLASS() {
		return ADDR_CLASS;
	}

	public void setADDR_CLASS(String aDDR_CLASS) {
		ADDR_CLASS = aDDR_CLASS;
	}

	public String getADDR_CONTENT() {
		return ADDR_CONTENT;
	}

	public void setADDR_CONTENT(String aDDR_CONTENT) {
		ADDR_CONTENT = aDDR_CONTENT;
	}

	public String getWGS84_X() {
		return WGS84_X;
	}

	public void setWGS84_X(String wGS84_X) {
		WGS84_X = wGS84_X;
	}

	public String getWGS84_Y() {
		return WGS84_Y;
	}

	public void setWGS84_Y(String wGS84_Y) {
		WGS84_Y = wGS84_Y;
	}

	public String getSHOW_X() {
		return SHOW_X;
	}

	public void setSHOW_X(String sHOW_X) {
		SHOW_X = sHOW_X;
	}

	public String getSHOW_Y() {
		return SHOW_Y;
	}

	public void setSHOW_Y(String sHOW_Y) {
		SHOW_Y = sHOW_Y;
	}

	public String getIS_REGION() {
		return IS_REGION;
	}

	public void setIS_REGION(String iS_REGION) {
		IS_REGION = iS_REGION;
	}

	public String getSHOW_XS() {
		return SHOW_XS;
	}

	public void setSHOW_XS(String sHOW_XS) {
		SHOW_XS = sHOW_XS;
	}

	public String getSHOW_YS() {
		return SHOW_YS;
	}

	public void setSHOW_YS(String sHOW_YS) {
		SHOW_YS = sHOW_YS;
	}

	public int getADDR_STATUS() {
		return ADDR_STATUS;
	}

	public void setADDR_STATUS(int aDDR_STATUS) {
		ADDR_STATUS = aDDR_STATUS;
	}

	public String getREMARKS() {
		return REMARKS;
	}

	public void setREMARKS(String rEMARKS) {
		REMARKS = rEMARKS;
	}

	@Override
	protected Map doPreToMap() {
		
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("COMPANY_CODE", COMPANY_CODE);
		params.put("PROVINCE", PROVINCE);
		params.put("CITY", CITY);
		params.put("REGION", REGION);
		params.put("TOWN_SHIP", TOWN_SHIP);
		params.put("STREET", STREET);
		params.put("ROOM_NO", ROOM_NO);
		params.put("PLOT", PLOT);
		params.put("BUILDING_NO", BUILDING_NO);
		params.put("UNIT_NO", UNIT_NO);
		params.put("GROUP", GROUP);
		params.put("FARMER_ROOM_NO", FARMER_ROOM_NO);
		params.put("ZAIMUTH", ZAIMUTH);
		params.put("PARENT_ADDR_CODE", PARENT_ADDR_CODE);
		params.put("PARENT_ADDR_NAME", PARENT_ADDR_NAME);
		params.put("ADDR_CODE", ADDR_CODE);
		params.put("ADDR_NAME", ADDR_NAME);
		params.put("ADDR_CLASS", ADDR_CLASS);
		params.put("ADDR_CONTENT", ADDR_CONTENT);
		params.put("WGS84_X", WGS84_X);
		params.put("WGS84_Y", WGS84_Y);
		params.put("SHOW_X", SHOW_X);
		params.put("SHOW_Y", SHOW_Y);
		params.put("IS_REGION", IS_REGION);
		params.put("SHOW_XS", SHOW_XS);
		params.put("SHOW_YS", SHOW_YS);
		params.put("ADDR_STATUS", ADDR_STATUS);
		params.put("REMARKS", REMARKS);
		
		params.put("UNIT_NAME", UNIT_NAME);
		params.put("UP_FLOOR_NUM", UP_FLOOR_NUM);
		params.put("DOWN_FLOOR_NUM", DOWN_FLOOR_NUM);
		params.put("HOUSE_NUM", HOUSE_NUM);
		params.put("POLICE_PIC", POLICE_PIC);
		
		params.put("BOX_NO", BOX_NO);
		params.put("AMUL_BOX_NUM", AMUL_BOX_NUM);
		params.put("HOUSE_HOLD", HOUSE_HOLD);
		params.put("AMULBOX_CLASS", AMULBOX_CLASS);
		params.put("ELECTRIC_METER", ELECTRIC_METER);
		params.put("ELECTRIC_PIC", ELECTRIC_PIC);
		
		params.put("companyCode", companyCode);
		params.put("deviceObjName", deviceObjName);
		params.put("x", x);
		params.put("y", y);
		params.put("attrJson", attrJson);
		
		params.put("deviceimg1", deviceimg1);
		
		params.put("deviceimg2", deviceimg2);
		params.put("deviceimg3", deviceimg3);
		
		
	   
		return params;
	}

	@Override
	public void setRequest(HttpServletRequest request) {
		// TODO Auto-generated method stub
		super.setRequest(request);
		GZIPInputStream in;
		try {
			//System.out.println(request.getInputStream());
			
			
			in = new GZIPInputStream(request.getInputStream());
			byte[] b= new byte[1024];
	        int temp =0;
	        ByteArrayOutputStream out = new ByteArrayOutputStream();
	        while((temp = in.read(b,0,b.length))!=-1){
	            out.write(b, 0, temp);
	        }
	        out.flush();
	        in.close();
	        out.close();
	        
	        
	        JSONObject obj = JSON.parseObject(out.toString("UTF-8"));
			
	        BeanUtils.populate(this, obj);
	        
	        if (deviceimg1 != null && !"".equals(deviceimg1)) {
	        	
	        	deviceimg1 = deviceimg1.replaceAll(" ", "+");
	        }
	        
	        if (deviceimg2 != null && !"".equals(deviceimg2)) {
	        	
	        	deviceimg2 = deviceimg2.replaceAll(" ", "+");
	        }

			if (deviceimg3 != null && !"".equals(deviceimg3)) {
				
				deviceimg3 = deviceimg3.replaceAll(" ", "+");
			}
	        
		}catch (IOException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		
	}
	
	@Override
	protected void doPostRequest(HttpServletRequest request) {
		// TODO Auto-generated method stub

	}

	@Override
	public Object getGenerateKey() {
		// TODO Auto-generated method stub
		return null;
	}

}
