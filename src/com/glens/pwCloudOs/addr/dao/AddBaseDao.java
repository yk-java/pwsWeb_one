package com.glens.pwCloudOs.addr.dao;

import java.util.ArrayList;
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

public class AddBaseDao {
	private MongoTemplate mongoTemplate;
	
	public MongoTemplate getMongoTemplate() {
		return mongoTemplate;
	}

	public void setMongoTemplate(MongoTemplate mongoTemplate) {
		this.mongoTemplate = mongoTemplate;
	}

	public PageBean getAddrList(final Map<String, Object> params) {
		PageBean pageBean = this.mongoTemplate.execute("ADD_BASE", new CollectionCallback<PageBean>() {

			@Override
			public PageBean doInCollection(DBCollection collection)
					throws MongoException, DataAccessException {
			
				DBObject query = new BasicDBObject(params);

				BasicDBList condSeg = new BasicDBList();
				String searchName = (String)params.get("searchName");
				if (searchName != null && !"".equals(searchName)) {
							Pattern pattern = Pattern.compile("^.*" + searchName + ".*$", Pattern.CASE_INSENSITIVE);
							BasicDBList values = new BasicDBList();
							values.add(new BasicDBObject("ADDR_NAME", pattern));
							//values.add(new BasicDBObject("deviceObjCode", pattern));
							BasicDBObject obj=new BasicDBObject();
							obj.put("$or", values);
							condSeg.add(obj);
							//query.put("$or", values);
				}else{
					query.removeField("searchName");
				}
				if(params.get("COMPANY_CODE")!=null){
					query.put("COMPANY_CODE", params.get("COMPANY_CODE"));
				}
				
				if( params.get("ADDR_STATUS")!=null && !params.get("ADDR_STATUS").equals("0")){
					query.put("ADDR_STATUS", params.get("ADDR_STATUS"));
				}else{
					query.removeField("ADDR_STATUS");
				}
				
				if( params.get("PARENT_ADDR_CODE")!=null){
					query.put("PARENT_ADDR_CODE", params.get("PARENT_ADDR_CODE").toString());
				}else{
					query.removeField("PARENT_ADDR_CODE");
				}
				
				
				
				
				
			//	paramsN.put("ADDR_NAME", params.get("ADDR_NAME"));
				
				
				
				query.removeField("searchName");
				//query.put("$and", condSeg);
				// count
				Long totalCount = collection.count(query);
				// pageResult
				PageBean pageBean = new PageBean(totalCount.intValue(), 1, 100);
			//	int firstResult = pageBean.getPerpage() * (pageBean.getCurrentPage() - 1);
				
				
				DBCursor cur;
				
				if( params.get("ADDR_NAME")!=null){
					DBObject orderBy = new BasicDBObject();
					orderBy.put("ADDR_NAME", 1);
					cur = collection.find(query).sort(orderBy).limit(100) ;
				}else{
					DBObject orderBy = new BasicDBObject();
					orderBy.put("ADDR_NAME", 0);
					cur = collection.find(query).limit(100) ;
					
				}
				
				
				
				
				List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
				while(cur.hasNext()){
					DBObject obj = cur.next();
					list.add(obj.toMap());
				}
				pageBean.setList(list);
				return pageBean;
			}
			
		});
		return pageBean;
	}
	
	public int insertAddrBase(final Map<String, Object> params) {
		int row = this.mongoTemplate.execute("ADD_BASE", new CollectionCallback<Integer>() {

			@Override
			public Integer doInCollection(DBCollection collection)
					throws MongoException, DataAccessException {
				// data list
				List<DBObject> values = new ArrayList<DBObject>();
				
				DBObject obj = new BasicDBObject(params);
				values.add(obj);
				WriteResult wRes = collection.insert(values);
				int rows = wRes.getN();
				return rows;
			}
			
		});
		return row;
	}
	
	public int updateAddrBase(final Map<String, Object> params) {
		int row = this.mongoTemplate.execute("ADD_BASE", new CollectionCallback<Integer>() {

			@Override
			public Integer doInCollection(DBCollection collection)
					throws MongoException, DataAccessException {
				DBObject query = new BasicDBObject();
		
				query.put("ADDR_CODE",params.get("ADDR_CODE"));
				//query.put("COMPANY_CODE",params.get("COMPANY_CODE"));
			
				// pageResult
				DBObject set = new BasicDBObject();
				DBObject valParams = new BasicDBObject();
				//valParams.put("deviceObjCode", params.get("deviceObjCode"));
				
				valParams.put("FLOOR_HOUSE", params.get("FLOOR_HOUSE"));
				valParams.put("POLICE_PIC", params.get("POLICE_PIC"));
				set.put("$set", valParams);
				WriteResult wRes = collection.updateMulti(query, set);
				int row = wRes.getN();
				return row;
			}
			
		});
		return row;
	}
	
	public int updateElectricAddrBase(final Map<String, Object> params) {
		int row = this.mongoTemplate.execute("ADD_BASE", new CollectionCallback<Integer>() {

			@Override
			public Integer doInCollection(DBCollection collection)
					throws MongoException, DataAccessException {
				DBObject query = new BasicDBObject();
		
				query.put("ADDR_CODE",params.get("ADDR_CODE"));
				//query.put("COMPANY_CODE",params.get("COMPANY_CODE"));
			
				// pageResult
				DBObject set = new BasicDBObject();
				DBObject valParams = new BasicDBObject();
				//valParams.put("deviceObjCode", params.get("deviceObjCode"));
				
				valParams.put("ALUMINIUM_BOX", params.get("ALUMINIUM_BOX"));
				valParams.put("ELECTRIC_METER", params.get("ELECTRIC_METER"));
				valParams.put("ELECTRIC_PIC", params.get("ELECTRIC_PIC"));
				set.put("$set", valParams);
				WriteResult wRes = collection.updateMulti(query, set);
				int row = wRes.getN();
				return row;
			}
		});
		return row;
	}

}
