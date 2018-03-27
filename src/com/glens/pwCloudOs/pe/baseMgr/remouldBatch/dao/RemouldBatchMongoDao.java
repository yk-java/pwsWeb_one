package com.glens.pwCloudOs.pe.baseMgr.remouldBatch.dao;

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

public class RemouldBatchMongoDao {
	private MongoTemplate mongoTemplate;
	
	public MongoTemplate getMongoTemplate() {
		return mongoTemplate;
	}

	public void setMongoTemplate(MongoTemplate mongoTemplate) {
		this.mongoTemplate = mongoTemplate;
	}

	public PageBean queryBatchDevice(final Map<String, Object> params) {
		PageBean pageBean = this.mongoTemplate.execute("PE_BS_RP_REMOULD_DEVICES", new CollectionCallback<PageBean>() {

			@Override
			public PageBean doInCollection(DBCollection collection)
					throws MongoException, DataAccessException {
				int currentPage = Integer.parseInt(params.get("currentPage").toString());
				int perpage = Integer.parseInt(params.get("perpage").toString());
				
				
				if (params.get("attrJson") != null && !"".equals(params.get("attrJson"))) {
					
					String attrJson = (String) params.get("attrJson");
					Map<String, Object> attrMap = JSONObject.parseObject(attrJson);
					Iterator<String> it = attrMap.keySet().iterator();
					while (it.hasNext()) {
						
						String key = it.next();
						if(attrMap.get(key)==null || "".equals(attrMap.get(key))){
							continue;
						}
						params.put(key, attrMap.get(key));
					}
				}
				
				DBObject query = new BasicDBObject(params);
				query.removeField("searchName");
				query.removeField("currentPage");
				query.removeField("perpage");
				query.removeField("attrJson");
				query.removeField("currentPage");
				query.removeField("perpage");
				if(query.get("proNo")==null || query.get("proNo").toString().equals("")){
					query.removeField("proNo");
				}
				if(query.get("reserveConfirm")==null || query.get("reserveConfirm").toString().equals("")){
					query.removeField("reserveConfirm");
				}
				if(query.get("reserveStatus")==null || query.get("reserveStatus").toString().equals("")){
					query.removeField("reserveStatus");
				}
				String searchName = (String)params.get("searchName");
				if (searchName != null && !"".equals(searchName)) {
					
					String[] packet = searchName.toString().split(";");
					if (packet.length > 1) {
						
						String[] fields = packet[0].split(",");
						String searchValue = packet[1];
						if (searchValue != null && !"".equals(searchValue)) {
							
							Pattern pattern = Pattern.compile("^.*" + searchValue + ".*$", Pattern.CASE_INSENSITIVE);
							BasicDBList values = new BasicDBList();
							for (String field : fields) {
								
								values.add(new BasicDBObject(field, pattern));
							}
							
							values.add(new BasicDBObject("deviceObjName", pattern));
							values.add(new BasicDBObject("deviceObjCode", pattern));
							values.add(new BasicDBObject("reseverOrgname", pattern));
							query.put("$or", values);
						}
					}

				}
				
				// count
				Long totalCount = collection.count(query);
				// pageResult
				PageBean pageBean = new PageBean(totalCount.intValue(), currentPage, perpage);
				int firstResult = pageBean.getPerpage() * (pageBean.getCurrentPage() - 1);
				DBObject orderBy = new BasicDBObject();
				orderBy.put("deviceObjName", 1);
				DBCursor cur = collection.find(query).sort(orderBy).skip(firstResult).limit(pageBean.getPerpage());
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
	
	
	public PageBean getExportList(final Map<String, Object> params) {
		PageBean pageBean = this.mongoTemplate.execute("PE_BS_RP_REMOULD_DEVICES", new CollectionCallback<PageBean>() {

			@Override
			public PageBean doInCollection(DBCollection collection)
					throws MongoException, DataAccessException {
				int currentPage = Integer.parseInt(params.get("currentPage").toString());
				int perpage = Integer.parseInt(params.get("perpage").toString());
				
				
				if (params.get("attrJson") != null && !"".equals(params.get("attrJson"))) {
					
					String attrJson = (String) params.get("attrJson");
					Map<String, Object> attrMap = JSONObject.parseObject(attrJson);
					Iterator<String> it = attrMap.keySet().iterator();
					while (it.hasNext()) {
						
						String key = it.next();
						if(attrMap.get(key)==null || "".equals(attrMap.get(key))){
							continue;
						}
						params.put(key, attrMap.get(key));
					}
				}
				
				DBObject query = new BasicDBObject(params);
				query.removeField("searchName");
				query.removeField("currentPage");
				query.removeField("perpage");
				query.removeField("attrJson");
				query.removeField("currentPage");
				query.removeField("perpage");
				if(query.get("proNo")==null || query.get("proNo").toString().equals("")){
					query.removeField("proNo");
				}
				if(query.get("reserveConfirm")==null || query.get("reserveConfirm").toString().equals("")){
					query.removeField("reserveConfirm");
				}
				if(query.get("reserveStatus")==null || query.get("reserveStatus").toString().equals("")){
					query.removeField("reserveStatus");
				}
				String searchName = (String)params.get("searchName");
				if (searchName != null && !"".equals(searchName)) {
					
					String[] packet = searchName.toString().split(";");
					if (packet.length > 1) {
						
						String[] fields = packet[0].split(",");
						String searchValue = packet[1];
						if (searchValue != null && !"".equals(searchValue)) {
							
							Pattern pattern = Pattern.compile("^.*" + searchValue + ".*$", Pattern.CASE_INSENSITIVE);
							BasicDBList values = new BasicDBList();
							for (String field : fields) {
								
								values.add(new BasicDBObject(field, pattern));
							}
							
							values.add(new BasicDBObject("deviceObjName", pattern));
							values.add(new BasicDBObject("deviceObjCode", pattern));
							values.add(new BasicDBObject("reseverOrgname", pattern));
							query.put("$or", values);
						}
					}

				}
				
				// count
				Long totalCount = collection.count(query);
				// pageResult
				PageBean pageBean = new PageBean(totalCount.intValue(), currentPage, perpage);
				int firstResult = pageBean.getPerpage() * (pageBean.getCurrentPage() - 1);
				DBObject orderBy = new BasicDBObject();
				orderBy.put("deviceObjName", 1);
				DBCursor cur = collection.find(query).sort(orderBy);
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
	
	
	
	//手机端List
	public PageBean queryBatchDevice1(final Map<String, Object> params) {
		PageBean pageBean = this.mongoTemplate.execute("PE_BS_RP_REMOULD_DEVICES", new CollectionCallback<PageBean>() {

			@Override
			public PageBean doInCollection(DBCollection collection)
					throws MongoException, DataAccessException {
				//int currentPage = Integer.parseInt(params.get("currentPage").toString());
				//int perpage = Integer.parseInt(params.get("perpage").toString());
				
				
				
				
				DBObject query = new BasicDBObject(params);
				//query.removeField("searchName");
				
				BasicDBList condSeg = new BasicDBList();
				
				
				
				
				String searchName = (String)params.get("searchName");
				if (searchName != null && !"".equals(searchName)) {
							Pattern pattern = Pattern.compile("^.*" + searchName + ".*$", Pattern.CASE_INSENSITIVE);
							BasicDBList values = new BasicDBList();
							values.add(new BasicDBObject("deviceObjName", pattern));
							values.add(new BasicDBObject("deviceObjCode", pattern));
							values.add(new BasicDBObject("reseverOrgname", pattern));
							BasicDBObject obj=new BasicDBObject();
							obj.put("$or", values);
							condSeg.add(obj);
							//query.put("$or", values);
				}else{
					query.removeField("searchName");
				}
				if(params.get("companyCode")!=null){
					query.put("companyCode", params.get("companyCode"));
				}
				
				if(params.get("reseverOrgcode")!=null){
					query.put("reseverOrgcode", params.get("reseverOrgcode"));
				}
				
				//query.put("reserveStatus", params.get("reserveStatus"));
				if(params.get("reserveStatus")!=null && !params.get("reserveStatus").equals("")){
					query.put("reserveStatus", params.get("reserveStatus"));
				}else{
					BasicDBObject obj1=new BasicDBObject();
					BasicDBList bd=new BasicDBList();
					bd.add(new BasicDBObject("reserveStatus",null));
					bd.add(new BasicDBObject("reserveStatus","0"));
					bd.add(new BasicDBObject("reserveStatus","2"));
					obj1.put("$or", bd);
					condSeg.add(obj1);
					query.put("$and", condSeg);
					query.removeField("reserveStatus");
				}
				
				query.removeField("searchName");
				
				// count
				Long totalCount = collection.count(query);
				// pageResult
				PageBean pageBean = new PageBean(totalCount.intValue(), 1, 100);
				int firstResult = pageBean.getPerpage() * (pageBean.getCurrentPage() - 1);
				DBObject orderBy = new BasicDBObject();
				orderBy.put("deviceObjName", 1);
				DBCursor cur = collection.find(query).sort(orderBy).limit(100) ;
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

	public List queryProDeviceList(final Map<String, Object> params) {
		List<Map<String, Object>> lst = this.mongoTemplate.execute("PE_BS_RP_DEVICES", new CollectionCallback<List<Map<String, Object>>>() {

			@Override
			public List<Map<String, Object>> doInCollection(DBCollection collection)
					throws MongoException, DataAccessException {
				// query
				
				if (params.get("attrJson") != null && !"".equals(params.get("attrJson"))) {
					
					String attrJson = (String) params.get("attrJson");
					Map<String, Object> attrMap = JSONObject.parseObject(attrJson);
					Iterator<String> it = attrMap.keySet().iterator();
					while (it.hasNext()) {
						
						String key = it.next();
						if(attrMap.get(key)==null || "".equals(attrMap.get(key))){
							continue;
						}
						params.put(key, attrMap.get(key));
					}
				}
				
				DBObject query = new BasicDBObject(params);
				query.removeField("searchName");
				query.removeField("currentPage");
				query.removeField("perpage");
				query.removeField("attrJson");
				query.removeField("remouldBatchCode");// 删除多余信息
				if(query.get("proNo")==null || query.get("proNo").toString().equals("")){
					query.removeField("proNo");
				}
				if(query.get("rpAuditState")==null || query.get("rpAuditState").toString().equals("")){
					query.removeField("rpAuditState");
				}
				BasicDBList condSeg = new BasicDBList();
				
				String searchName = (String)params.get("searchName");
				if (searchName != null && !"".equals(searchName)) {
					
					String[] packet = searchName.toString().split(";");
					if (packet.length > 1) {
						
						String[] fields = packet[0].split(",");
						String searchValue = packet[1];
						if (searchValue != null && !"".equals(searchValue)) {
							
							Pattern pattern = Pattern.compile("^.*" + searchValue + ".*$", Pattern.CASE_INSENSITIVE);
							BasicDBList values1 = new BasicDBList();
							for (String field : fields) {
								
								values1.add(new BasicDBObject(field, pattern));
							}
							
							values1.add(new BasicDBObject("deviceObjName", pattern));
							values1.add(new BasicDBObject("deviceObjCode", pattern));
							BasicDBObject cond1 = new BasicDBObject();
							cond1.put("$or", values1);
							condSeg.add(cond1);
						}
					}

				}
				BasicDBList values2 = new BasicDBList();
				values2.add(new BasicDBObject("isBatch", "0"));
				values2.add(new BasicDBObject("isBatch", null));
				BasicDBObject cond2 = new BasicDBObject();
				cond2.put("$or", values2);
				condSeg.add(cond2);
				query.put("$and", condSeg);
				// pageResult
				DBCursor cur = collection.find(query);
				List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
				while(cur.hasNext()){
					DBObject obj = cur.next();
					list.add(obj.toMap());
				}
				return list;
			}
			
		});
		return lst;
	}
	
	public int signProDevice(final Map<String, Object> params) {
		int row = this.mongoTemplate.execute("PE_BS_RP_DEVICES", new CollectionCallback<Integer>() {

			@Override
			public Integer doInCollection(DBCollection collection)
					throws MongoException, DataAccessException {
				// query
				if (params.get("attrJson") != null && !"".equals(params.get("attrJson"))) {
					
					String attrJson = (String) params.get("attrJson");
					Map<String, Object> attrMap = JSONObject.parseObject(attrJson);
					Iterator<String> it = attrMap.keySet().iterator();
					while (it.hasNext()) {
						
						String key = it.next();
						if(attrMap.get(key)==null || "".equals(attrMap.get(key))){
							continue;
						}
						params.put(key, attrMap.get(key));
					}
				}
				
				DBObject query = new BasicDBObject(params);
				query.removeField("searchName");
				query.removeField("currentPage");
				query.removeField("perpage");
				query.removeField("attrJson");
				query.removeField("remouldBatchCode");// 删除多余信息
				if(query.get("proNo")==null || query.get("proNo").toString().equals("")){
					query.removeField("proNo");
				}
				if(query.get("rpAuditState")==null || query.get("rpAuditState").toString().equals("")){
					query.removeField("rpAuditState");
				}
				BasicDBList condSeg = new BasicDBList();
				
				String searchName = (String)params.get("searchName");
				if (searchName != null && !"".equals(searchName)) {
					
					String[] packet = searchName.toString().split(";");
					if (packet.length > 1) {
						
						String[] fields = packet[0].split(",");
						String searchValue = packet[1];
						if (searchValue != null && !"".equals(searchValue)) {
							
							Pattern pattern = Pattern.compile("^.*" + searchValue + ".*$", Pattern.CASE_INSENSITIVE);
							BasicDBList values1 = new BasicDBList();
							for (String field : fields) {
								
								values1.add(new BasicDBObject(field, pattern));
							}
							
							values1.add(new BasicDBObject("deviceObjName", pattern));
							values1.add(new BasicDBObject("deviceObjCode", pattern));
							BasicDBObject cond1 = new BasicDBObject();
							cond1.put("$or", values1);
							condSeg.add(cond1);
						}
					}

				}
				BasicDBList values2 = new BasicDBList();
				values2.add(new BasicDBObject("isBatch", "0"));
				values2.add(new BasicDBObject("isBatch", null));
				BasicDBObject cond2 = new BasicDBObject();
				cond2.put("$or", values2);
				condSeg.add(cond2);
				query.put("$and", condSeg);
				// pageResult
				DBObject set = new BasicDBObject();
				DBObject valParams = new BasicDBObject();
				valParams.put("isBatch", "1");
				set.put("$set", valParams);
				WriteResult wRes = collection.updateMulti(query, set);
				int row = wRes.getN();
				return row;
			}
			
		});
		return row;
	}
	/**
	 * 添加设备到改造批次
	 * @param listParams
	 * @param remouldBatchCode
	 * @return
	 */
	public int addDeviceToBatch(final List<Map<String, Object>> listParams, final String remouldBatchCode) {
		int row = this.mongoTemplate.execute("PE_BS_RP_REMOULD_DEVICES", new CollectionCallback<Integer>() {

			@Override
			public Integer doInCollection(DBCollection collection)
					throws MongoException, DataAccessException {
				// data list
				List<DBObject> values = new ArrayList<DBObject>();
				for (Iterator iterator = listParams.iterator(); iterator
						.hasNext();) {
					Map<String,Object> map = (Map<String,Object>) iterator
							.next();
					DBObject obj = new BasicDBObject(map);
					obj.removeField("_id");
					obj.removeField("auditPersonCode");
					obj.removeField("auditPersonName");
					obj.removeField("auditSuggest");
					obj.removeField("auditDate");
					obj.put("remouldBatchCode", remouldBatchCode);
					obj.put("reserveStatus", "0");
					obj.put("reserveConfirm", "0");
					values.add(obj);
				}
				// batch insert
				WriteResult wRes = collection.insert(values);
				int rows = wRes.getN();
				return rows;
			}
			
		});
		return row;
	}
	
	public List queryRemouldProDeviceList(final Map<String, Object> params) {
		List<Map<String, Object>> lst = this.mongoTemplate.execute("PE_BS_RP_REMOULD_DEVICES", new CollectionCallback<List<Map<String, Object>>>() {

			@Override
			public List<Map<String, Object>> doInCollection(DBCollection collection)
					throws MongoException, DataAccessException {
				// query
				
				if (params.get("attrJson") != null && !"".equals(params.get("attrJson"))) {
					
					String attrJson = (String) params.get("attrJson");
					Map<String, Object> attrMap = JSONObject.parseObject(attrJson);
					Iterator<String> it = attrMap.keySet().iterator();
					while (it.hasNext()) {
						
						String key = it.next();
						if(attrMap.get(key)==null || "".equals(attrMap.get(key))){
							continue;
						}
						params.put(key, attrMap.get(key));
					}
				}
				
				DBObject query = new BasicDBObject(params);
				query.removeField("searchName");
				query.removeField("currentPage");
				query.removeField("perpage");
				query.removeField("attrJson");
				if(query.get("proNo")==null || query.get("proNo").toString().equals("")){
					query.removeField("proNo");
				}
				if(query.get("rpAuditState")==null || query.get("rpAuditState").toString().equals("")){
					query.removeField("rpAuditState");
				}
				
				String searchName = (String)params.get("searchName");
				if (searchName != null && !"".equals(searchName)) {
					
					String[] packet = searchName.toString().split(";");
					if (packet.length > 1) {
						
						String[] fields = packet[0].split(",");
						String searchValue = packet[1];
						if (searchValue != null && !"".equals(searchValue)) {
							
							Pattern pattern = Pattern.compile("^.*" + searchValue + ".*$", Pattern.CASE_INSENSITIVE);
							BasicDBList values1 = new BasicDBList();
							for (String field : fields) {
								
								values1.add(new BasicDBObject(field, pattern));
							}
							
							values1.add(new BasicDBObject("deviceObjName", pattern));
							values1.add(new BasicDBObject("deviceObjCode", pattern));
							values1.add(new BasicDBObject("reseverOrgname", pattern));
							query.put("$or", values1);
						}
					}

				}
				// pageResult
				DBCursor cur = collection.find(query);
				List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
				while(cur.hasNext()){
					DBObject obj = cur.next();
					list.add(obj.toMap());
				}
				return list;
			}
			
		});
		return lst;
	}
	/**
	 * 从改造批次删除设备
	 * @param listParams
	 * @param remouldBatchCode
	 * @return
	 */
	public int batDelDeviceToBatch(final Map<String, Object> params) {
		int row = this.mongoTemplate.execute("PE_BS_RP_REMOULD_DEVICES", new CollectionCallback<Integer>() {

			@Override
			public Integer doInCollection(DBCollection collection)
					throws MongoException, DataAccessException {
				// query
				
				if (params.get("attrJson") != null && !"".equals(params.get("attrJson"))) {
					
					String attrJson = (String) params.get("attrJson");
					Map<String, Object> attrMap = JSONObject.parseObject(attrJson);
					Iterator<String> it = attrMap.keySet().iterator();
					while (it.hasNext()) {
						
						String key = it.next();
						if(attrMap.get(key)==null || "".equals(attrMap.get(key))){
							continue;
						}
						params.put(key, attrMap.get(key));
					}
				}
				
				DBObject query = new BasicDBObject(params);
				query.removeField("searchName");
				query.removeField("currentPage");
				query.removeField("perpage");
				query.removeField("attrJson");
				if(query.get("proNo")==null || query.get("proNo").toString().equals("")){
					query.removeField("proNo");
				}
				if(query.get("rpAuditState")==null || query.get("rpAuditState").toString().equals("")){
					query.removeField("rpAuditState");
				}
				String searchName = (String)params.get("searchName");
				if (searchName != null && !"".equals(searchName)) {
					
					String[] packet = searchName.toString().split(";");
					if (packet.length > 1) {
						
						String[] fields = packet[0].split(",");
						String searchValue = packet[1];
						if (searchValue != null && !"".equals(searchValue)) {
							
							Pattern pattern = Pattern.compile("^.*" + searchValue + ".*$", Pattern.CASE_INSENSITIVE);
							BasicDBList values = new BasicDBList();
							for (String field : fields) {
								
								values.add(new BasicDBObject(field, pattern));
							}
							
							values.add(new BasicDBObject("deviceObjName", pattern));
							values.add(new BasicDBObject("deviceObjCode", pattern));
							values.add(new BasicDBObject("reseverOrgname", pattern));
							query.put("$or", values);
						}
					}

				}
				// batch insert
				WriteResult wRes = collection.remove(query);
				int rows = wRes.getN();
				return rows;
			}
			
		});
		return row;
	}
	
	/**
	 * 从改造批次删除设备
	 * @param listParams
	 * @param remouldBatchCode
	 * @return
	 */
	public int delDeviceToBatch(final Map<String, Object> params) {
		int row = this.mongoTemplate.execute("PE_BS_RP_REMOULD_DEVICES", new CollectionCallback<Integer>() {

			@Override
			public Integer doInCollection(DBCollection collection)
					throws MongoException, DataAccessException {
				// query
				
				DBObject query = new BasicDBObject(params);
				
				// batch insert
				WriteResult wRes = collection.remove(query);
				int rows = wRes.getN();
				return rows;
			}
			
		});
		return row;
	}
	/**
	 * 分配改造单位
	 * @param params
	 * @param reseverOrgcode
	 * @return
	 */
	public int updateDevice(final Map<String, Object> params, final String reseverOrgcode, final String reseverOrgname,final String planRebuildTime) {
		int row = this.mongoTemplate.execute("PE_BS_RP_REMOULD_DEVICES", new CollectionCallback<Integer>() {

			@Override
			public Integer doInCollection(DBCollection collection)
					throws MongoException, DataAccessException {
// query
				
				if (params.get("attrJson") != null && !"".equals(params.get("attrJson"))) {
					
					String attrJson = (String) params.get("attrJson");
					Map<String, Object> attrMap = JSONObject.parseObject(attrJson);
					Iterator<String> it = attrMap.keySet().iterator();
					while (it.hasNext()) {
						
						String key = it.next();
						if(attrMap.get(key)==null || "".equals(attrMap.get(key))){
							continue;
						}
						params.put(key, attrMap.get(key));
					}
				}
				
				DBObject query = new BasicDBObject(params);
				query.removeField("searchName");
				query.removeField("currentPage");
				query.removeField("perpage");
				query.removeField("attrJson");
				if(query.get("proNo")==null || query.get("proNo").toString().equals("")){
					query.removeField("proNo");
				}
				if(query.get("rpAuditState")==null || query.get("rpAuditState").toString().equals("")){
					query.removeField("rpAuditState");
				}
				String searchName = (String)params.get("searchName");
				if (searchName != null && !"".equals(searchName)) {
					
					String[] packet = searchName.toString().split(";");
					if (packet.length > 1) {
						
						String[] fields = packet[0].split(",");
						String searchValue = packet[1];
						if (searchValue != null && !"".equals(searchValue)) {
							
							Pattern pattern = Pattern.compile("^.*" + searchValue + ".*$", Pattern.CASE_INSENSITIVE);
							BasicDBList values = new BasicDBList();
							for (String field : fields) {
								
								values.add(new BasicDBObject(field, pattern));
							}
							
							values.add(new BasicDBObject("deviceObjName", pattern));
							values.add(new BasicDBObject("deviceObjCode", pattern));
							values.add(new BasicDBObject("reseverOrgname", pattern));
							query.put("$or", values);
						}
					}

				}
				// pageResult
				DBObject set = new BasicDBObject();
				DBObject valParams = new BasicDBObject();
				
				SimpleDateFormat ds=new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
				String date=ds.format(new Date());
				valParams.put("reseverOrgcode", reseverOrgcode);
				valParams.put("reseverOrgname", reseverOrgname);
				valParams.put("planRebuildTime", planRebuildTime);
				valParams.put("allocTime", date);
				set.put("$set", valParams);
				WriteResult wRes = collection.updateMulti(query, set);
				int row = wRes.getN();
				return row;
			}
			
		});
		return row;
	}
	
	
	//final String img1,final String img2,final String img3,final String img4,final String img5,final String img6,final String img7
	//手机端改造
	public int updateDeviceByPhone(final Map<String, Object> params) {
		int row = this.mongoTemplate.execute("PE_BS_RP_REMOULD_DEVICES", new CollectionCallback<Integer>() {

			@Override
			public Integer doInCollection(DBCollection collection)
					throws MongoException, DataAccessException {
				DBObject query = new BasicDBObject();
				/*query.removeField("companyCode");
				query.removeField("proNo");
				query.removeField("reserveProNo");
				query.removeField("price");
				query.removeField("attrJson");*/
				query.put("deviceObjCode",params.get("deviceObjCode"));
				/*query.put("companyCode", params.get("companyCode"));
				query.put("reserveProNo", params.get("reserveProNo"));
				query.put("proNo", params.get("proNo"));
				query.put("deviceObjCode", params.get("deviceObjCode"));
				query.put("companyCode", params.get("companyCode"));*/
				// pageResult
				DBObject set = new BasicDBObject();
				DBObject valParams = new BasicDBObject();
				//valParams.put("deviceObjCode", params.get("deviceObjCode"));
				
				valParams.put("deviceBhIds", params.get("deviceBhIds"));
				valParams.put("deviceByqName", params.get("deviceByqName"));
				valParams.put("deviceLineName", params.get("deviceLineName"));
				valParams.put("deviceBxId", params.get("deviceBxId"));
				valParams.put("reserveStatus", "1");
				
				valParams.put("reserveB1Img", params.get("reserveB1Img"));
				valParams.put("reserveB2Img", params.get("reserveB2Img"));
				valParams.put("reserveA1Img", params.get("reserveA1Img"));
				valParams.put("reserveA2Img", params.get("reserveA2Img"));
				valParams.put("reserveOpImg", params.get("reserveOpImg"));
				valParams.put("reserveSignImg", params.get("reserveSignImg"));
				valParams.put("reservePcutImg", params.get("reservePcutImg"));
				
				SimpleDateFormat ds=new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
				String date=ds.format(new Date());
				
				valParams.put("realRebuildTime",date);
				
				if (params.get("attrJson") != null && !"".equals(params.get("attrJson"))) {
					
					String attrJson = (String) params.get("attrJson");
					Map<String, Object> attrMap = JSONObject.parseObject(attrJson);
					Iterator<String> it = attrMap.keySet().iterator();
					int i=0;
					while (it.hasNext()) {
						//float p=Float.parseFloat(prices[i].toString());
						i++;
						String key = it.next();
						if(attrMap.get(key)==null || "".equals(attrMap.get(key))){
							continue;
						}
						
						//int count=Integer.parseInt(attrMap.get(key).toString());
						valParams.put(key, attrMap.get(key));
					}
				}
				
				set.put("$set", valParams);
				WriteResult wRes = collection.updateMulti(query, set);
				int row = wRes.getN();
				return row;
			}
			
		});
		return row;
	}
	//手机端材料新增
	public int phoneAddDeviceToBatch(final Map<String, Object> params) {
		int row = this.mongoTemplate.execute("PE_BS_RP_REMOULD_DEVICE_MATERIAL_DETAIL", new CollectionCallback<Integer>() {

			@Override
			public Integer doInCollection(DBCollection collection)
					throws MongoException, DataAccessException {
				// data list
				List<DBObject> values = new ArrayList<DBObject>();
				
				String price=params.get("price").toString();
				String prices[]=price.split(",");
				
				
				 
				
				if (params.get("attrJson") != null && !"".equals(params.get("attrJson"))) {
					
					String attrJson = (String) params.get("attrJson");
					Map<String, Object> attrMap = JSONObject.parseObject(attrJson);
					Iterator<String> it = attrMap.keySet().iterator();
					int i=0;
					while (it.hasNext()) {
						float p=Float.parseFloat(prices[i].toString());
						i++;
						String key = it.next();
						if(attrMap.get(key)==null || "".equals(attrMap.get(key))){
							continue;
						}
						
						int count=Integer.parseInt(attrMap.get(key).toString());
						params.put(key, attrMap.get(key));
						DBObject obj = new BasicDBObject();
						obj.put("companyCode", params.get("companyCode"));
						obj.put("reserveProNo", params.get("reserveProNo"));
						obj.put("deviceObjCode", params.get("deviceObjCode"));
						obj.put("materialTypeCode", key);
						obj.put("needCount", attrMap.get(key));
						obj.put("mctCode", params.get("mctCode"));
						obj.put("proNo", params.get("proNo"));
						obj.put("needAmount", p*count);
						values.add(obj);
					}
				}
				WriteResult wRes = collection.insert(values);
				int rows = wRes.getN();
				return rows;
			}
			
		});
		return row;
	}
	
	

	public PageBean queryProDevice(final Map<String, Object> params) {
		PageBean pageBean = this.mongoTemplate.execute("PE_BS_RP_DEVICES", new CollectionCallback<PageBean>() {
			
			/**
			  * <p>Title: doInCollection</p>
			  * <p>Description: </p>
			
			  * @param collection
			  * @return
			  * @throws MongoException
			  * @throws DataAccessException
			
			  * @see org.springframework.data.mongodb.core.CollectionCallback#doInCollection(com.mongodb.DBCollection)
			
			  **/
			
			@Override
			public PageBean doInCollection(DBCollection collection)
					throws MongoException, DataAccessException {
				// TODO Auto-generated method stub
				int currentPage = Integer.parseInt(params.get("currentPage").toString());
				int perpage = Integer.parseInt(params.get("perpage").toString());
				
				if (params.get("attrJson") != null && !"".equals(params.get("attrJson"))) {
					
					String attrJson = (String) params.get("attrJson");
					Map<String, Object> attrMap = JSONObject.parseObject(attrJson);
					Iterator<String> it = attrMap.keySet().iterator();
					while (it.hasNext()) {
						
						String key = it.next();
						if(attrMap.get(key)==null || "".equals(attrMap.get(key))){
							continue;
						}
						params.put(key, attrMap.get(key));
					}
				}
				
				DBObject query = new BasicDBObject(params);
				query.removeField("searchName");
				query.removeField("currentPage");
				query.removeField("perpage");
				query.removeField("attrJson");
				if(query.get("proNo")==null || query.get("proNo").toString().equals("")){
					query.removeField("proNo");
				}
				if(query.get("rpAuditState")==null || query.get("rpAuditState").toString().equals("")){
					query.removeField("rpAuditState");
				}
				
				
				BasicDBList condSeg = new BasicDBList();
				
				String searchName = (String)params.get("searchName");
				if (searchName != null && !"".equals(searchName)) {
					
					String[] packet = searchName.toString().split(";");
					if (packet.length > 1) {
						
						String[] fields = packet[0].split(",");
						String searchValue = packet[1];
						if (searchValue != null && !"".equals(searchValue)) {
							
							Pattern pattern = Pattern.compile("^.*" + searchValue + ".*$", Pattern.CASE_INSENSITIVE);
							BasicDBList values1 = new BasicDBList();
							for (String field : fields) {
								
								values1.add(new BasicDBObject(field, pattern));
							}
							
							values1.add(new BasicDBObject("deviceObjName", pattern));
							values1.add(new BasicDBObject("deviceObjCode", pattern));
							BasicDBObject cond1 = new BasicDBObject();
							cond1.put("$or", values1);
							condSeg.add(cond1);
						}
					}

				}
				BasicDBList values2 = new BasicDBList();
				values2.add(new BasicDBObject("isBatch", "0"));
				values2.add(new BasicDBObject("isBatch", null));
				BasicDBObject cond2 = new BasicDBObject();
				cond2.put("$or", values2);
				condSeg.add(cond2);
				query.put("$and", condSeg);
				Long totalCount = collection.count(query);
				PageBean pageBean = null;
				if (totalCount < 1) {
					
					pageBean = new PageBean();
					pageBean.setList(new ArrayList());
				}
				else {
					
					pageBean = new PageBean(totalCount.intValue(), currentPage, perpage);
					List<Map> list = new ArrayList<Map>();
					DBObject orderBy = new BasicDBObject();
					orderBy.put("deviceObjName", 1);
					int firstResult = pageBean.getPerpage() * (pageBean.getCurrentPage() - 1);
					DBCursor cur = collection.find(query).sort(orderBy).skip(firstResult).limit(pageBean.getPerpage());
					while (cur.hasNext()) {
						
						DBObject obj = cur.next();
						list.add(obj.toMap());
					}
					
					pageBean.setList(list);
				}
				
				return pageBean;
			}
		});
		return pageBean;
	}
/*
 * 修改分配标记
 * */
	public int signProDeviceDel(final Map<String, Object> params) {
		int row = this.mongoTemplate.execute("PE_BS_RP_DEVICES", new CollectionCallback<Integer>() {

			@Override
			public Integer doInCollection(DBCollection collection)
					throws MongoException, DataAccessException {
				// query
				if (params.get("attrJson") != null && !"".equals(params.get("attrJson"))) {
					
					String attrJson = (String) params.get("attrJson");
					Map<String, Object> attrMap = JSONObject.parseObject(attrJson);
					Iterator<String> it = attrMap.keySet().iterator();
					while (it.hasNext()) {
						
						String key = it.next();
						if(attrMap.get(key)==null || "".equals(attrMap.get(key))){
							continue;
						}
						params.put(key, attrMap.get(key));
					}
				}
				
				DBObject query = new BasicDBObject(params);
				query.removeField("searchName");
				query.removeField("currentPage");
				query.removeField("perpage");
				query.removeField("attrJson");
				query.removeField("remouldBatchCode");// 删除多余信息
				if(query.get("proNo")==null || query.get("proNo").toString().equals("")){
					query.removeField("proNo");
				}
				if(query.get("rpAuditState")==null || query.get("rpAuditState").toString().equals("")){
					query.removeField("rpAuditState");
				}
				
				String searchName = (String)params.get("searchName");
				if (searchName != null && !"".equals(searchName)) {
					
					String[] packet = searchName.toString().split(";");
					if (packet.length > 1) {
						
						String[] fields = packet[0].split(",");
						String searchValue = packet[1];
						if (searchValue != null && !"".equals(searchValue)) {
							
							Pattern pattern = Pattern.compile("^.*" + searchValue + ".*$", Pattern.CASE_INSENSITIVE);
							BasicDBList values = new BasicDBList();
							for (String field : fields) {
								
								values.add(new BasicDBObject(field, pattern));
							}
							
							values.add(new BasicDBObject("deviceObjName", pattern));
							values.add(new BasicDBObject("deviceObjCode", pattern));
							query.put("$or", values);
						}
					}

				}
				
				query.put("isBatch", "1");
				// pageResult
				DBObject set = new BasicDBObject();
				DBObject valParams = new BasicDBObject();
				valParams.put("isBatch", "0");
				set.put("$set", valParams);
				WriteResult wRes = collection.updateMulti(query, set);
				int row = wRes.getN();
				return row;
			}
			
		});
		return row;
	}
	
	public List<Map<String, Object>> censusRemouldBatchDevice(final String companyCode, final String reserveProNo, 
			final String remouldBatchCode, final List<Map<String, Object>> materialAttrs) {
		
		return this.mongoTemplate.execute("PE_BS_RP_REMOULD_DEVICES", new CollectionCallback<List<Map<String, Object>>>() {
			
			/**
			
			  * <p>Title: doInCollection</p>
			
			  * <p>Description: </p>
			
			  * @param collection
			  * @return
			  * @throws MongoException
			  * @throws DataAccessException
			
			  * @see org.springframework.data.mongodb.core.CollectionCallback#doInCollection(com.mongodb.DBCollection)
			
			  **/
			
			@Override
			public List<Map<String, Object>> doInCollection(
					DBCollection collection) throws MongoException,
					DataAccessException {
				// TODO Auto-generated method stub
				DBObject query = new BasicDBObject();
				query.put("companyCode", companyCode);
				query.put("reserveProNo", reserveProNo);
				query.put("remouldBatchCode", remouldBatchCode);
				query.put("reseverOrgcode", new BasicDBObject("$ne", null));
				BasicDBObject key = new BasicDBObject();
				key.put("reseverOrgcode", true);
				key.put("reseverOrgname", true);
				BasicDBObject initial = new BasicDBObject(); 
				initial.put("meterBoxCount", 0);
				initial.put("houseHold", 0);
				initial.put("estateCount", 0);
				initial.put("unitCount", 0);
				
				String materials="";
				for (Iterator iterator = materialAttrs.iterator(); iterator
						.hasNext();) {
					Map<String, Object> map = (Map<String, Object>) iterator
							.next();
					initial.put((String)map.get("ename"), 0);
					materials+="if (!isNaN(parseFloat(curr."+map.get("ename")+"))) result."+map.get("ename")+" += parseFloat(curr."+map.get("ename")+");";
				}
				
				String reduce = "function(curr, result) {"
						+ "result.meterBoxCount++;"
						+ "if (!isNaN(parseFloat(curr.HOUSE_HOLD))) result.houseHold += parseFloat(curr.HOUSE_HOLD);"
						+ materials
						+ "if (curr.ESTAE_NAME) result.estateCount++;"
						+ "if (curr.AMUL_BOX_ADDR) result.unitCount++;"
						+ "}";
				
				BasicDBList groupResult = (BasicDBList) collection.group(key, query, initial, reduce);
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
	}
	
	public List<Map<String, Object>> getReserveOrgDevices(final String companyCode, final String reserveProNo, 
			final String remouldBatchCode, final String reseverOrgcode) {
		
		return this.mongoTemplate.execute("PE_BS_RP_REMOULD_DEVICES", new CollectionCallback<List<Map<String, Object>>>() {
			
			/**
			
			  * <p>Title: doInCollection</p>
			
			  * <p>Description: </p>
			
			  * @param collection
			  * @return
			  * @throws MongoException
			  * @throws DataAccessException
			
			  * @see org.springframework.data.mongodb.core.CollectionCallback#doInCollection(com.mongodb.DBCollection)
			
			  **/
			
			@Override
			public List<Map<String, Object>> doInCollection(
					DBCollection collection) throws MongoException,
					DataAccessException {
				// TODO Auto-generated method stub
				DBObject query = new BasicDBObject();
				query.put("companyCode", companyCode);
				query.put("reserveProNo", reserveProNo);
				query.put("remouldBatchCode", remouldBatchCode);
				query.put("reseverOrgcode", reseverOrgcode);
				List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
				
				DBCursor cur = collection.find(query);
				while (cur.hasNext()) {
					
					DBObject obj = cur.next();
					list.add(obj.toMap());
				}
				
				return list;
			}
		});
	}
	
	public List<Map<String, Object>> getReserveDeviceMaterials(final String companyCode, final String reserveProNo, 
			final String deviceObjCode) {
		
		return this.mongoTemplate.execute("PE_BS_RP_DEVICE_MATERIAL_DETAIL", new CollectionCallback<List<Map<String, Object>>>() {
			
			@Override
			public List<Map<String, Object>> doInCollection(
					DBCollection collection) throws MongoException,
					DataAccessException {
				// TODO Auto-generated method stub
				DBObject query = new BasicDBObject();
				query.put("companyCode", companyCode);
				query.put("reserveProNo", reserveProNo);
				query.put("deviceObjCode", deviceObjCode);
				List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
				
				DBCursor cur = collection.find(query);
				while (cur.hasNext()) {
					
					DBObject obj = cur.next();
					list.add(obj.toMap());
				}
				
				return list;
			}
		});
	}
	
	public List<Map<String, Object>> getRemouldDeviceMaterials(final String companyCode, final String reserveProNo, 
			final String deviceObjCode) {
		
		return this.mongoTemplate.execute("PE_BS_RP_REMOULD_DEVICE_MATERIAL_DETAIL", new CollectionCallback<List<Map<String, Object>>>() {
			
			@Override
			public List<Map<String, Object>> doInCollection(
					DBCollection collection) throws MongoException,
					DataAccessException {
				// TODO Auto-generated method stub
				DBObject query = new BasicDBObject();
				query.put("companyCode", companyCode);
				query.put("reserveProNo", reserveProNo);
				query.put("deviceObjCode", deviceObjCode);
				List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
				
				DBCursor cur = collection.find(query);
				while (cur.hasNext()) {
					
					DBObject obj = cur.next();
					list.add(obj.toMap());
				}
				
				return list;
			}
		});
	}

	public Map<String, Object> getDevice(final Map<String, Object> params) {
		Map<String, Object> res = this.mongoTemplate.execute("PE_BS_RP_REMOULD_DEVICES", new CollectionCallback<Map<String, Object>>() {

			@Override
			public Map<String, Object> doInCollection(DBCollection collection)
					throws MongoException, DataAccessException {
				// query
				
				DBObject query = new BasicDBObject(params);
				
				DBCursor cur = collection.find(query);
				if (cur.hasNext()) {
					DBObject obj = cur.next();
					return obj.toMap();
				}
				return null;
			}
			
		});
		return res;
	}

	public int reserveConfirm(final Map<String, Object> params) {
		int rows = this.mongoTemplate.execute("PE_BS_RP_REMOULD_DEVICES", new CollectionCallback<Integer>() {

			@Override
			public Integer doInCollection(DBCollection collection)
					throws MongoException, DataAccessException {
				// query
				
				DBObject query = new BasicDBObject();
				query.put("companyCode", params.get("companyCode"));
				query.put("deviceObjCode", params.get("deviceObjCode"));
				query.put("reserveProNo", params.get("reserveProNo"));
				query.put("remouldBatchCode", params.get("remouldBatchCode"));
				
				
				DBObject set = new BasicDBObject();
				DBObject val = new BasicDBObject();
				
				if(params.get("reserveConfirm")!=null && params.get("reserveConfirm").toString().equals("2")){
					//val.put("reserveConfirm", "2");
					val.put("reserveStatus", "2");
				}else{
					val.put("reserveConfirm", "1");
					val.put("PROJECT_TYPE", params.get("PROJECT_TYPE"));
				}
				
				
				val.put("auditPersonCode", params.get("auditPersonCode"));
				val.put("auditPersonName", params.get("auditPersonName"));
				val.put("auditSuggest", params.get("auditSuggest"));
				val.put("auditDate", new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
				set.put("$set", val);
				WriteResult wRes = collection.update(query, set);
				int row = wRes.getN();
				return row;
			}
			
		});
		return rows;
	}

	public int updateRemouldBatchDevice(final String mctDeviceJson) {
		
		int iCount = 0;
		
		try {
			
			iCount = this.mongoTemplate.execute("PE_BS_RP_REMOULD_DEVICES", new CollectionCallback<Integer>() {
				
				/**
				
				  * <p>Title: doInCollection</p>
				
				  * <p>Description: </p>
				
				  * @param collection
				  * @return
				  * @throws MongoException
				  * @throws DataAccessException
				
				  * @see org.springframework.data.mongodb.core.CollectionCallback#doInCollection(com.mongodb.DBCollection)
				
				  **/
				
				
				@Override
				public Integer doInCollection(DBCollection collection)
						throws MongoException, DataAccessException {
					// TODO Auto-generated method stub
					DBObject updateObj = (DBObject) JSON.parse(mctDeviceJson);
					BasicDBObject query = new BasicDBObject();
					query.put("deviceObjCode", updateObj.get("deviceObjCode"));
					query.put("mctViewCode", updateObj.get("mctViewCode"));
					query.put("reserveProNo", updateObj.get("reserveProNo"));
					query.put("remouldBatchCode", updateObj.get("remouldBatchCode"));
					DBObject obj = collection.findOne(query);
					if (obj != null) {
						
						Iterator<String> it = updateObj.keySet().iterator();
						while (it.hasNext()) {
							
							String key = it.next();
							obj.put(key, updateObj.get(key));
						}
					}
					
					WriteResult result = collection.update(query, obj);
					
					return result.getN();
				}
			});
		}
		catch (Exception e) {
			// TODO: handle exception
		}
		
		return iCount;
	}

	public int updateRemouldMaterial(final Map map) {
		int iCount = 0;
		
		try {
			
			iCount = this.mongoTemplate.execute("PE_BS_RP_REMOULD_DEVICE_MATERIAL_DETAIL", new CollectionCallback<Integer>() {
				
				/**
				
				  * <p>Title: doInCollection</p>
				
				  * <p>Description: </p>
				
				  * @param collection
				  * @return
				  * @throws MongoException
				  * @throws DataAccessException
				
				  * @see org.springframework.data.mongodb.core.CollectionCallback#doInCollection(com.mongodb.DBCollection)
				
				  **/
				
				
				@Override
				public Integer doInCollection(DBCollection collection)
						throws MongoException, DataAccessException {
					// TODO Auto-generated method stub
					
					
					String materialType = map.get("materialType").toString();
					String materialTypeCount = map.get("materialTypeCount").toString();
					if (materialType != null && !"".equals(materialType) 
							&& materialTypeCount != null && !"".equals(materialTypeCount)) {
						// 删除
						DBObject selectObj = new BasicDBObject();
						selectObj.put("companyCode", map.get("companyCode"));
						selectObj.put("reserveProNo", map.get("reserveProNo"));
						selectObj.put("mctCode", map.get("mctCode"));
						selectObj.put("deviceObjCode", map.get("deviceObjCode"));
						collection.remove(selectObj);
						// 批量新增
						String[] materialTypes = materialType.split(",");
						String[] materialTypeCounts = materialTypeCount.split(",");
						List<DBObject> materialList = new ArrayList<DBObject>();
						for (int i = 0;i < materialTypes.length;i++) {
							
							DBObject materialObj = new BasicDBObject();
							materialObj.put("companyCode", map.get("companyCode"));
							materialObj.put("reserveProNo", map.get("reserveProNo"));
							materialObj.put("deviceObjCode", map.get("deviceObjCode"));
							materialObj.put("materialTypeCode", materialTypes[i]);
							materialObj.put("needCount", materialTypeCounts[i]);
							materialObj.put("needAmount", 0);
							materialObj.put("proNo", map.get("proNo"));
							materialObj.put("mctCode", map.get("mctCode"));
							
							materialList.add(materialObj);
						}
						
						WriteResult result =  collection.insert(materialList);
						return 1;
					}
					return 0;
				}
			});
		}
		catch (Exception e) {
			// TODO: handle exception
		}
		
		return iCount;
	}

}
