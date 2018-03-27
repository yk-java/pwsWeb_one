/**
 * @Title: PmsLowVolSaMongoDao.java
 * @Package com.glens.pwCloudOs.pm.pmStatsAn.pmsLowVolSa.dao
 * @Description: TODO
 * Copyright: Copyright (c) 2016 
 * Company:南京量为石信息科技有限公司
 * @author 
 * @date 2016-11-8 下午3:28:54
 * @version V1.0
 */


package com.glens.pwCloudOs.pm.pmStatsAn.pmsLowVolSa.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.data.mongodb.core.MongoTemplate;

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

public class PmsLowVolSaMongoDao {

	private static final String PMS_LOWVOL_DEVICE_TYPE = "P04";
	
	private static final String PMS_DMRD_DEVICE_TYPE = "P03";
	
	private static Log logger = LogFactory.getLog(PmsLowVolSaMongoDao.class);
	
	private MongoTemplate mongoTemplate;
	
	public List<Map<String, Object>> pmMemberWorkloadStatsAn(String companyCode, String fromDate, String toDate, String[] proNos) {
		
		List<Map<String, Object>> resultList = new ArrayList<Map<String,Object>>();
		DBCollection collection = this.mongoTemplate.getCollection("PM_MCT_LIST");
		DBObject query = new BasicDBObject();
		query.put("companyCode", companyCode);
		query.put("deviceTypeCode", PMS_LOWVOL_DEVICE_TYPE);
		DBObject workDateParam = new BasicDBObject();
		workDateParam.put("$lte", toDate);
		workDateParam.put("$gte", fromDate);
		query.put("WORK_DATE", workDateParam);
		if (proNos != null && proNos.length > 0) {
			
			BasicDBList proNoIn = new BasicDBList();
			for (String proNo : proNos) {
				
				proNoIn.add(proNo);
			}
			
			query.put("proNo", new BasicDBObject("$in", proNoIn));
		}
		
		//分组统计初始化
		BasicDBObject keys = new BasicDBObject();
		keys.put("proNo", true);
		keys.put("proName", true);
		keys.put("WORKER", true);
		BasicDBObject initial = new BasicDBObject(); 
		initial.put("dmrdCount", 0);
		initial.put("dmrdMrCount", 0);
		initial.put("postvUserInMrCount", 0);
		initial.put("stationHouseMrCount", 0);
		initial.put("dmrdNewCount", 0);
		initial.put("postvUserInNewCount", 0);
		initial.put("stationHouseNewCount", 0);
		String reduce = "function(doc, prev) {prev.dmrdCount++;if (doc.WORK_TYPE == '迁移修改') {prev.dmrdMrCount++;" +
				"prev.postvUserInMrCount = prev.postvUserInMrCount + Number(doc.POSTV_NUM);" +
				"prev.stationHouseMrCount = prev.stationHouseMrCount + Number(doc.STATION_HOUSE_NUM);} " +
				"if (doc.WORK_TYPE == '新增') {prev.dmrdNewCount++;" +
				"prev.postvUserInNewCount = prev.postvUserInNewCount + Number(doc.POSTV_NUM);" +
				"prev.stationHouseNewCount = prev.stationHouseNewCount + Number(doc.STATION_HOUSE_NUM);}}";
		
		BasicDBList groupResult = (BasicDBList) collection.group(keys, query, initial, reduce);
		if (groupResult != null && groupResult.size() > 0) {
			
			for (int i = 0;i < groupResult.size();i++) {
				
				DBObject item = (DBObject) groupResult.get(i);
				resultList.add(item.toMap());
			}
		}
		
		return resultList;
	}
	
	public List<Map<String, Object>> getMemberAccumulateWorkload(String companyCode, String date, String[] proNos) {
		
		List<Map<String, Object>> resultList = new ArrayList<Map<String,Object>>();
		DBCollection collection = this.mongoTemplate.getCollection("PM_MCT_LIST");
		DBObject query = new BasicDBObject();
		query.put("companyCode", companyCode);
		query.put("deviceTypeCode", PMS_LOWVOL_DEVICE_TYPE);
		query.put("WORK_DATE", new BasicDBObject("$lte", date));
		if (proNos != null && proNos.length > 0) {
			
			BasicDBList proNoIn = new BasicDBList();
			for (String proNo : proNos) {
				
				proNoIn.add(proNo);
			}
			
			query.put("proNo", new BasicDBObject("$in", proNoIn));
		}
		
		//分组统计初始化
		BasicDBObject keys = new BasicDBObject();
		keys.put("proNo", true);
		keys.put("proName", true);
		keys.put("WORKER", true);
		BasicDBObject initial = new BasicDBObject(); 
		initial.put("total", 0);
		String reduce = "function(doc, prev) {prev.total++;}";
		BasicDBList groupResult = (BasicDBList) collection.group(keys, query, initial, reduce);
		if (groupResult != null && groupResult.size() > 0) {
			
			for (int i = 0;i < groupResult.size();i++) {
				
				DBObject item = (DBObject) groupResult.get(i);
				resultList.add(item.toMap());
			}
		}
		
		return resultList;
		
	}
	
	public List<String> getPmsLowVolPros(String companyCode) {
		
		DBObject query = new BasicDBObject();
		query.put("companyCode", companyCode);
		query.put("deviceTypeCode", PMS_LOWVOL_DEVICE_TYPE);
		DBCollection collection = this.mongoTemplate.getCollection("PM_MCT_LIST");
		List<String> proNoList = collection.distinct("proNo", query);
		
		return proNoList;
	}
	
	public List<Map<String, Object>> pmProWorkloadStatsAn(String companyCode, String fromDate, String toDate, String[] proNos) {
		
		List<Map<String, Object>> resultList = new ArrayList<Map<String,Object>>();
		DBCollection collection = this.mongoTemplate.getCollection("PM_MCT_LIST");
		DBObject query = new BasicDBObject();
		query.put("companyCode", companyCode);
		query.put("deviceTypeCode", PMS_LOWVOL_DEVICE_TYPE);
		DBObject workDateParam = new BasicDBObject();
		workDateParam.put("$lte", toDate);
		workDateParam.put("$gte", fromDate);
		query.put("WORK_DATE", workDateParam);
		if (proNos != null && proNos.length > 0) {
			
			BasicDBList proNoIn = new BasicDBList();
			for (String proNo : proNos) {
				
				proNoIn.add(proNo);
			}
			
			query.put("proNo", new BasicDBObject("$in", proNoIn));
		}
		
		//分组统计初始化
		BasicDBObject keys = new BasicDBObject();
		keys.put("proNo", true);
		keys.put("proName", true);
		BasicDBObject initial = new BasicDBObject(); 
		initial.put("dmrdCount", 0);
		initial.put("dmrdMrCount", 0);
		initial.put("dmrdNewCount", 0);
		String reduce = "function(doc, prev) {prev.dmrdCount++;" +
				"if (doc.WORK_TYPE == '迁移修改') {prev.dmrdMrCount++;}" +
				"if (doc.WORK_TYPE == '新增') {prev.dmrdNewCount++;}}";
		
		BasicDBList groupResult = (BasicDBList) collection.group(keys, query, initial, reduce);
		if (groupResult != null && groupResult.size() > 0) {
			
			for (int i = 0;i < groupResult.size();i++) {
				
				DBObject item = (DBObject) groupResult.get(i);
				resultList.add(item.toMap());
			}
		}
		
		return resultList;
	}
	
	public List<Map<String, Object>> getProDmrdCount(String companyCode, String[] proNos) {
		
		List<Map<String, Object>> resultList = new ArrayList<Map<String,Object>>();
		DBCollection collection = this.mongoTemplate.getCollection("PM_MCT_LIST");
		DBObject query = new BasicDBObject();
		query.put("companyCode", companyCode);
		query.put("deviceTypeCode", PMS_DMRD_DEVICE_TYPE);
		if (proNos != null && proNos.length > 0) {
			
			BasicDBList proNoIn = new BasicDBList();
			for (String proNo : proNos) {
				
				proNoIn.add(proNo);
			}
			
			query.put("proNo", new BasicDBObject("$in", proNoIn));
		}
		
		//分组统计初始化
		BasicDBObject keys = new BasicDBObject();
		keys.put("proNo", true);
		keys.put("proName", true);
		BasicDBObject initial = new BasicDBObject(); 
		initial.put("dmrdCount", 0);
		String reduce = "function(doc, prev) {prev.dmrdCount++;}";
		
		BasicDBList groupResult = (BasicDBList) collection.group(keys, query, initial, reduce);
		if (groupResult != null && groupResult.size() > 0) {
			
			for (int i = 0;i < groupResult.size();i++) {
				
				DBObject item = (DBObject) groupResult.get(i);
				resultList.add(item.toMap());
			}
		}
		
		return resultList;
	}
	
	public List<Map<String, Object>> getTaskDmrdCount(String companyCode, String[] proNos, String toDate) {
		
		List<Map<String, Object>> resultList = new ArrayList<Map<String,Object>>();
		DBCollection collection = this.mongoTemplate.getCollection("PM_MCT_LIST");
		DBObject query = new BasicDBObject();
		query.put("companyCode", companyCode);
		query.put("deviceTypeCode", PMS_LOWVOL_DEVICE_TYPE);
		DBObject workDateParam = new BasicDBObject();
		workDateParam.put("$lte", toDate);
		query.put("WORK_DATE", workDateParam);
		if (proNos != null && proNos.length > 0) {
			
			BasicDBList proNoIn = new BasicDBList();
			for (String proNo : proNos) {
				
				proNoIn.add(proNo);
			}
			
			query.put("proNo", new BasicDBObject("$in", proNoIn));
		}
		
		//分组统计初始化
		BasicDBObject keys = new BasicDBObject();
		keys.put("proNo", true);
		keys.put("proName", true);
		keys.put("deviceObjName", true);
		BasicDBObject initial = new BasicDBObject(); 
		initial.put("dmrdCount", 0);
		String reduce = "function(doc, prev) {prev.dmrdCount++;}";
		
		BasicDBList groupResult = (BasicDBList) collection.group(keys, query, initial, reduce);
		if (groupResult != null && groupResult.size() > 0) {
			
			for (int i = 0;i < groupResult.size();i++) {
				
				DBObject item = (DBObject) groupResult.get(i);
				resultList.add(item.toMap());
			}
		}
		
		return resultList;
	}
	
	public List<Map<String, Object>> pmProStuffCount(String companyCode, String fromDate, String toDate, String[] proNos) {
		
		List<Map<String, Object>> resultList = new ArrayList<Map<String,Object>>();
		DBCollection collection = this.mongoTemplate.getCollection("PM_MCT_LIST");
		DBObject query = new BasicDBObject();
		query.put("companyCode", companyCode);
		query.put("deviceTypeCode", PMS_LOWVOL_DEVICE_TYPE);
		DBObject workDateParam = new BasicDBObject();
		workDateParam.put("$lte", toDate);
		workDateParam.put("$gte", fromDate);
		query.put("WORK_DATE", workDateParam);
		if (proNos != null && proNos.length > 0) {
			
			BasicDBList proNoIn = new BasicDBList();
			for (String proNo : proNos) {
				
				proNoIn.add(proNo);
			}
			
			query.put("proNo", new BasicDBObject("$in", proNoIn));
		}
		
		//分组统计初始化
		BasicDBObject keys = new BasicDBObject();
		keys.put("proNo", true);
		keys.put("proName", true);
		keys.put("WORKER", true);
		BasicDBObject initial = new BasicDBObject(); 
		initial.put("dmrdCount", 0);
		String reduce = "function(doc, prev) {prev.dmrdCount++;}";
		
		BasicDBList groupResult = (BasicDBList) collection.group(keys, query, initial, reduce);
		if (groupResult != null && groupResult.size() > 0) {
			
			for (int i = 0;i < groupResult.size();i++) {
				
				DBObject item = (DBObject) groupResult.get(i);
				resultList.add(item.toMap());
			}
		}
		
		return resultList;
	}

	public MongoTemplate getMongoTemplate() {
		return mongoTemplate;
	}

	public void setMongoTemplate(MongoTemplate mongoTemplate) {
		this.mongoTemplate = mongoTemplate;
	}
	
}
