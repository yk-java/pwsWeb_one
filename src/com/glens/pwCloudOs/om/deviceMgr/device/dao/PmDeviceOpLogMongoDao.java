/**
 * @Title: PmDeviceOpLogMongoDao.java
 * @Package com.glens.pwCloudOs.om.deviceMgr.device.dao
 * @Description: TODO
 * Copyright: Copyright (c) 2016 
 * Company:南京量为石信息科技有限公司
 * @author 
 * @date 2016-11-4 上午11:04:56
 * @version V1.0
 */


package com.glens.pwCloudOs.om.deviceMgr.device.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.data.mongodb.core.MongoTemplate;

import com.glens.pwCloudOs.om.deviceMgr.device.vo.PmDeviceOpLogVo;
import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;

/**
 * 
 * 
 * @author 
 * @version V1.0
 */

public class PmDeviceOpLogMongoDao {

	private static Log logger = LogFactory.getLog(PmDeviceOpLogMongoDao.class);
	
	private MongoTemplate mongoTemplate;
	
	public boolean insertLog(PmDeviceOpLogVo vo) {
		
		boolean ok = false;
		
		try {
			
			this.mongoTemplate.insert(vo);
			
			ok = true;
		}
		catch (Exception e) {
			// TODO: handle exception
			logger.error("插入设备操作日志信息出错", e);
		}
		
		return ok;
	}
	
	public List<Map<String, Object>> memberOpStats(String companyCode, String startTime, 
			String endTime, List<Map<String, String>> memberList) {
		
		List<Map<String, Object>> opStatsList = new ArrayList<Map<String,Object>>();
		DBCollection collection = this.mongoTemplate.getCollection("PM_DEVICE_OP_LOG");
		DBObject query = new BasicDBObject();
		query.put("companyCode", companyCode);
		if (memberList != null && memberList.size() > 0) {
			
			BasicDBList memberIn = new BasicDBList();
			for (Map<String, String> memberItem : memberList) {
				
				memberIn.add(memberItem.get("employeeCode"));
			}
			
			query.put("employeeCode", new BasicDBObject("$in", memberIn));
			if (startTime != null && !"".equals(startTime)) {
				
				query.put("opTime", new BasicDBObject("$gt", startTime + " 00:00:00"));
			}
			if (endTime != null && !"".equals(endTime)) {
				
				query.put("opTime", new BasicDBObject("$lt", endTime + " 23:59:59"));
			}
			
			if (startTime != null && !"".equals(startTime) 
					&& endTime != null && !"".equals(endTime)) {
				
				DBObject opTimeQueryParam = new BasicDBObject();
				opTimeQueryParam.put("$gt", startTime + " 00:00:00");
				opTimeQueryParam.put("$lt", endTime + " 23:59:59");
				query.put("opTime", opTimeQueryParam);
			}
			//分组统计初始化
			BasicDBObject keys = new BasicDBObject();
			keys.put("unitCode", true);
			keys.put("employeeCode", true);
			keys.put("employeeName", true);
			BasicDBObject initial = new BasicDBObject(); 
			initial.put("addCount", 0);
			initial.put("updateCount", 0);
			initial.put("auditCount", 0);
			initial.put("delCount", 0);
			String reduce = "function(doc, prev) {if (doc.opType==1) prev.addCount += doc.affectRecordNum;" +
					"if (doc.opType==2||doc.opType==5) prev.updateCount += doc.affectRecordNum;" +
					"if (doc.opType==3) prev.delCount += doc.affectRecordNum;" +
					"if (doc.opType==6) prev.auditCount += doc.affectRecordNum;}";
			BasicDBList groupResult = (BasicDBList) collection.group(keys, query, initial, reduce);
			if (groupResult != null && groupResult.size() > 0) {
				
				for (int i = 0;i < groupResult.size();i++) {
					
					DBObject item = (DBObject) groupResult.get(i);
					opStatsList.add(item.toMap());
				}
			}
		}
		
		return opStatsList;
	}

	public MongoTemplate getMongoTemplate() {
		return mongoTemplate;
	}

	public void setMongoTemplate(MongoTemplate mongoTemplate) {
		this.mongoTemplate = mongoTemplate;
	}
	
}
