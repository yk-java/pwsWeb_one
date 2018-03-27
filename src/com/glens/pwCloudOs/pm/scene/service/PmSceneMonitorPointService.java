package com.glens.pwCloudOs.pm.scene.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.glens.eap.platform.framework.service.impl.EAPAbstractService;
import com.glens.pwCloudOs.pm.scene.receiver.DeviceDataMemery;
import com.glens.pwCloudOs.pm.scene.receiver.VerifyHandler;
import com.glens.pwCloudOs.pm.scene.vo.PmSceneMonitorPoint;

public class PmSceneMonitorPointService extends EAPAbstractService {
	public List<PmSceneMonitorPoint> getMonitorRealData(final String spotCode) {
		List<PmSceneMonitorPoint> res = new ArrayList<PmSceneMonitorPoint>();
		List<Object> data = DeviceDataMemery.find("monitor",
				new VerifyHandler() {

					@Override
					public boolean verify(Object obj) {
						PmSceneMonitorPoint point = (PmSceneMonitorPoint) obj;
						if (point.getSpotCode().equals(spotCode)) {
							return true;
						}
						return false;
					}

				});
		if (data != null) {
			for (Iterator iterator = data.iterator(); iterator.hasNext();) {
				PmSceneMonitorPoint object = (PmSceneMonitorPoint) iterator
						.next();
				res.add(object);
			}
		}
		return res;
	}
}
