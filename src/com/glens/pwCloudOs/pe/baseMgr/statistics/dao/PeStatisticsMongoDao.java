package com.glens.pwCloudOs.pe.baseMgr.statistics.dao;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import org.springframework.dao.DataAccessException;
import org.springframework.data.mongodb.core.CollectionCallback;
import org.springframework.data.mongodb.core.MongoTemplate;

import com.alibaba.fastjson.JSONObject;
import com.glens.eap.platform.framework.beans.PageBean;
import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoException;
import com.mongodb.WriteResult;
import com.mongodb.util.JSON;

public class PeStatisticsMongoDao {
	private MongoTemplate mongoTemplate;
	
	public MongoTemplate getMongoTemplate() {
		return mongoTemplate;
	}

	public void setMongoTemplate(MongoTemplate mongoTemplate) {
		this.mongoTemplate = mongoTemplate;
	}
	
	public List<Map<String, Object>> groupList(final Map<String, Object> keyParams, final Map<String, Object> condParams){
		List<Map<String, Object>> res = this.mongoTemplate.execute("PM_MCT_LIST", new CollectionCallback<List<Map<String, Object>>>() {
			@Override
			public List<Map<String, Object>> doInCollection(DBCollection collection)
					throws MongoException, DataAccessException {
				DBObject key=new BasicDBObject(keyParams);
				
				DBObject cond=new BasicDBObject(condParams);
				
				DBObject initial=new BasicDBObject();
				initial.put("total", 0);
				String reduce="function(curr, result){result.total+=1;}";
				
				BasicDBList groupResult = (BasicDBList) collection.group(key, cond, initial, reduce);
				List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
				if (groupResult != null && groupResult.size() > 0) {
					for (int i = 0;i < groupResult.size();i++) {
						DBObject item = (DBObject) groupResult.get(i);
						list.add(item.toMap());
					}
				}
				return list;
			}
		});
		return res;
	}
	
	
	public List<Map<String, Object>> query(final Map<String, Object> params){
		List<Map<String, Object>> res = this.mongoTemplate.execute("PM_MCT_LIST", new CollectionCallback<List<Map<String, Object>>>() {
			@Override
			public List<Map<String, Object>> doInCollection(DBCollection collection)
					throws MongoException, DataAccessException {
				DBObject query=new BasicDBObject(params);
				DBCursor cur = (DBCursor) collection.find(query);
				List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
				while(cur.hasNext()){
					DBObject obj = cur.next();
					list.add(obj.toMap());
				}
				return list;
			}
		});
		return res;
	}
	
	public Long queryCount(final Map<String, Object> params){
		long res = this.mongoTemplate.execute("PM_MCT_LIST", new CollectionCallback<Long>() {
			@Override
			public Long doInCollection(DBCollection collection)
					throws MongoException, DataAccessException {
				DBObject query=new BasicDBObject(params);
				long rows = collection.count(query);
				return rows;
			}
		});
		return res;
	}
	
}
