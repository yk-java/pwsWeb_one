/**
 * @Title: DeviceMongoHandleDao.java
 * @Package com.glens.pwCloudOs.om.deviceMgr.deviceHandle.dao
 * @Description: TODO
 * Copyright: Copyright (c) 2016 
 * Company:南京量为石信息科技有限公司
 * @author 
 * @date 2016-12-5 上午11:40:46
 * @version V1.0
 */

package com.glens.pwCloudOs.om.deviceMgr.deviceHandle.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import com.glens.pwCloudOs.om.deviceMgr.imagesMgr.vo.DeviceVo;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.WriteResult;
import com.mongodb.util.JSON;

/**
 * 
 * 
 * @author
 * @version V1.0
 */

public class DeviceMongoHandleDao {

	private MongoTemplate mongoTemplate;

	private static Log logger = LogFactory.getLog(DeviceMongoHandleDao.class);

	public List<Map<String, Object>> getMctDeviceList(DBObject query) {

		DBCollection collection = this.mongoTemplate
				.getCollection("PM_MCT_LIST");
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		DBCursor cur = collection.find(query);
		while (cur.hasNext()) {

			DBObject obj = cur.next();
			list.add(obj.toMap());
		}

		return list;
	}

	public int saveDevice(Map<String, Object> deviceItem) {

		DBCollection collection = this.mongoTemplate
				.getCollection("PM_MCT_LIST");
		DBObject deviceObj = new BasicDBObject(deviceItem);
		WriteResult result = collection.save(deviceObj);
		int count = result.getN();
		return count;
	}

	public int updateDeviceLoc(final String mctDeviceJson) {

		int iCount = 0;

		try {

			DBCollection collection = this.mongoTemplate
					.getCollection("PM_MCT_LIST");
			DBObject updateObj = (DBObject) JSON.parse(mctDeviceJson);
			BasicDBObject query = new BasicDBObject();
			query.put("deviceObjCode", updateObj.get("deviceObjCode"));
			DBObject obj = collection.findOne(query);
			if (obj != null) {

				obj.put("x", updateObj.get("x"));
				obj.put("y", updateObj.get("y"));
				obj.put("rx", updateObj.get("rx"));
				obj.put("ry", updateObj.get("ry"));
				WriteResult result = collection.update(query, obj);

				iCount = result.getN();
			}
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("修改mongodb总控表设备坐标出错！", e);
		}

		return iCount;
	}

	public MongoTemplate getMongoTemplate() {
		return mongoTemplate;
	}

	public void setMongoTemplate(MongoTemplate mongoTemplate) {
		this.mongoTemplate = mongoTemplate;
	}

	public List<DeviceVo> getMctDeviceList(Criteria criteria) {
		Query query = new Query(criteria);
		List<DeviceVo> list = this.mongoTemplate.find(query, DeviceVo.class);
		return list;
	}

	public int updateDeviceLoc(String mctDeviceJson, String fieldEname) {
		int iCount = 0;

		try {

			DBCollection collection = this.mongoTemplate
					.getCollection("PM_MCT_LIST");
			DBObject updateObj = (DBObject) JSON.parse(mctDeviceJson);
			BasicDBObject query = new BasicDBObject();
			query.put(fieldEname, updateObj.get(fieldEname));
			DBObject obj = collection.findOne(query);
			if (obj != null) {

				obj.put("x", updateObj.get("x"));
				obj.put("y", updateObj.get("y"));
				obj.put("rx", updateObj.get("rx"));
				obj.put("ry", updateObj.get("ry"));
				WriteResult result = collection.update(query, obj);
				iCount = result.getN();
			}
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("修改mongodb总控表设备坐标出错！", e);
		}

		return iCount;
	}
}
