package com.glens.pwCloudOs.pm.scene.receiver;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

import com.glens.pwCloudOs.pm.scene.service.DeviceDataReceiveService;
import com.glens.pwCloudOs.pm.scene.vo.PmSceneMonitorPoint;

public class DeviceDataReceiver implements InitializingBean, DisposableBean {
	private DeviceDataReceiveService deviceDataReceiveService;
	
	public DeviceDataReceiveService getDeviceDataReceiveService() {
		return deviceDataReceiveService;
	}

	public void setDeviceDataReceiveService(
			DeviceDataReceiveService deviceDataReceiveService) {
		this.deviceDataReceiveService = deviceDataReceiveService;
	}

	Logger logger = Logger.getLogger(DeviceDataReceiver.class);

	@Override
	public void destroy() throws Exception {
		logger.info("停止设备数据接收器");
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		logger.info("启动设备数据接收器...");
		
		Thread thread = new Thread(new Runnable() {
			
			@Override
			public void run() {
				try {
					DatagramSocket datagramSocket = new DatagramSocket(60000);
					int errorCnt = 0;
					while (true) {
						try {
							DatagramPacket packet = new DatagramPacket(new byte[512], 512);
							datagramSocket.receive(packet);
							final String msg = new String(packet.getData(), 0,
									packet.getLength());
							logger.info("收到消息："+packet.getAddress() + "/" + packet.getPort() + ":"
									+ msg);
							deviceDataReceiveService.receive(msg);
							errorCnt = 0;
						} catch (Exception e) {
							errorCnt++;
							e.printStackTrace();
							logger.error(e);
							Thread.sleep(1000 * (10 + errorCnt / 5));
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
					logger.info("设备数据接收器异常停止！！！");
				}
			}
		});
		thread.start();
	}

}
