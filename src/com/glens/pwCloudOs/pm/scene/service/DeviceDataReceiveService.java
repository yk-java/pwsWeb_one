package com.glens.pwCloudOs.pm.scene.service;

import java.util.List;

import org.apache.log4j.Logger;

import com.glens.eap.platform.framework.service.impl.EAPAbstractService;
import com.glens.pwCloudOs.pm.scene.dao.PmSceneMonitorPointDao;
import com.glens.pwCloudOs.pm.scene.dao.PmSceneMonitorPointHisDao;
import com.glens.pwCloudOs.pm.scene.receiver.DeviceDataMemery;
import com.glens.pwCloudOs.pm.scene.receiver.UpdateHandler;
import com.glens.pwCloudOs.pm.scene.receiver.VerifyHandler;
import com.glens.pwCloudOs.pm.scene.vo.PmSceneMonitorPoint;
import com.glens.pwCloudOs.pm.scene.vo.PmSceneMonitorPointHis;

public class DeviceDataReceiveService extends EAPAbstractService {
	private PmSceneMonitorPointDao pmSceneMonitorPointDao;
	private PmSceneMonitorPointHisDao pmSceneMonitorPointHisDao;

	public PmSceneMonitorPointDao getPmSceneMonitorPointDao() {
		return pmSceneMonitorPointDao;
	}

	public void setPmSceneMonitorPointDao(
			PmSceneMonitorPointDao pmSceneMonitorPointDao) {
		this.pmSceneMonitorPointDao = pmSceneMonitorPointDao;
	}

	public PmSceneMonitorPointHisDao getPmSceneMonitorPointHisDao() {
		return pmSceneMonitorPointHisDao;
	}

	public void setPmSceneMonitorPointHisDao(
			PmSceneMonitorPointHisDao pmSceneMonitorPointHisDao) {
		this.pmSceneMonitorPointHisDao = pmSceneMonitorPointHisDao;
	}

	Logger logger = Logger.getLogger(DeviceDataReceiveService.class);
	/**
	 * 接收数据
	 * 
	 * @param msg
	 */
	public void receive(String msg) {
		if (msg != null && msg.length() > 0) {
			String[] datas = msg.split(" ");
			if (datas.length >= 3) {
				String idStr = datas[0];
				String[] idArr = idStr.split(":");
				String temStr = datas[1];
				String[] temArr = temStr.split(":");
				String humStr = datas[2];
				String[] humArr = humStr.split(":");
				final String id = idArr[1];
				final String tem = temArr[1];
				final String hum = humArr[1];
				// 根据id查询
				List<Object> res = DeviceDataMemery.find("monitor",
						new VerifyHandler() {

							@Override
							public boolean verify(Object obj) {
								PmSceneMonitorPoint point = (PmSceneMonitorPoint) obj;
								if ((id+"_Tem").equals(point.getMonitorPointCode())) {
									return true;
								}
								return false;
							}

						});
				if (res == null || res.size() == 0) {
					logger.info("新增到Memery");
					// 新增
					PmSceneMonitorPoint itemTem = (PmSceneMonitorPoint)pmSceneMonitorPointDao.findByMonitorPointCode(id+"_Tem");
					if(itemTem!=null){
						itemTem.setMonitorValue(tem);
						DeviceDataMemery.insert("monitor", itemTem);
					}
					PmSceneMonitorPoint itemHum = (PmSceneMonitorPoint)pmSceneMonitorPointDao.findByMonitorPointCode(id+"_Hum");
					if(itemHum!=null){
						itemHum.setMonitorValue(hum);
						DeviceDataMemery.insert("monitor", itemHum);
					}
				} else {
					logger.info("更新Memery");
					// 修改
					DeviceDataMemery.update("monitor", new VerifyHandler(){

						@Override
						public boolean verify(Object obj) {
							PmSceneMonitorPoint point = (PmSceneMonitorPoint) obj;
							if ((id+"_Tem").equals(point.getMonitorPointCode())) {
								return true;
							}
							return false;
						}
						
					}, new UpdateHandler(){

						@Override
						public void update(Object obj) {
							PmSceneMonitorPoint point = (PmSceneMonitorPoint) obj;
							point.setMonitorValue(tem);
						}
						
					});
					DeviceDataMemery.update("monitor", new VerifyHandler(){

						@Override
						public boolean verify(Object obj) {
							PmSceneMonitorPoint point = (PmSceneMonitorPoint) obj;
							if ((id+"_Hem").equals(point.getMonitorPointCode())) {
								return true;
							}
							return false;
						}
						
					}, new UpdateHandler(){

						@Override
						public void update(Object obj) {
							PmSceneMonitorPoint point = (PmSceneMonitorPoint) obj;
							point.setMonitorValue(hum);
						}
						
					});
				}
				logger.info("保存历史记录");
				// 记录历史记录
				PmSceneMonitorPointHis his= new PmSceneMonitorPointHis();
				his.setMonitorPointCode(id+"_Tem");
				his.setMonitorValue(tem);
				pmSceneMonitorPointHisDao.insert(his);
				
				PmSceneMonitorPointHis hisHum= new PmSceneMonitorPointHis();
				hisHum.setMonitorPointCode(id+"_Hum");
				hisHum.setMonitorValue(hum);
				pmSceneMonitorPointHisDao.insert(hisHum);
				
				
			} else {
				// error
				logger.info("异常：Data too short!");
			}
		} else {
			// error
			logger.info("异常：Data is empty!");
		}
	}
}
