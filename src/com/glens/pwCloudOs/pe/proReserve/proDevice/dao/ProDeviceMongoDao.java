/**
 * @Title: ProDeviceMongoDao.java
 * @Package com.glens.pwCloudOs.pe.proReserve.proDevice.dao
 * @Description: TODO
 * Copyright: Copyright (c) 2016 
 * Company:南京量为石信息科技有限公司
 * @author 
 * @date 2016-8-26 上午11:46:47
 * @version V1.0
 */


package com.glens.pwCloudOs.pe.proReserve.proDevice.dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.data.mongodb.core.CollectionCallback;
import org.springframework.data.mongodb.core.MongoTemplate;

import com.alibaba.fastjson.JSONObject;
import com.glens.eap.platform.core.utils.DateTimeUtil;
import com.glens.eap.platform.framework.beans.PageBean;
import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoException;
import com.mongodb.WriteResult;
import com.mongodb.util.JSON;

/**
 * 
 * 
 * @author 
 * @version V1.0
 */

public class ProDeviceMongoDao {

	private static Log logger = LogFactory.getLog(ProDeviceMongoDao.class);
	
	private MongoTemplate mongoTemplate;
	
	public PageBean getProDevicePage(final String params, final Object searchName, final int currentPage, final int perpage) {
		
		return this.mongoTemplate.execute("PE_BS_RP_DEVICES", new CollectionCallback<PageBean>() {
			
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
				
				DBObject query = (DBObject) JSON.parse(params);
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
	}
	
	//手机端列表
	public PageBean getProDevicePage1(final String params, final Object searchName, final int currentPage, final int perpage) {
		
		return this.mongoTemplate.execute("PE_BS_RP_DEVICES", new CollectionCallback<PageBean>() {
			
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
				
				DBObject query = (DBObject) JSON.parse(params);
				if (searchName != null && !"".equals(searchName)) {
					
							Pattern pattern = Pattern.compile("^.*" + searchName + ".*$", Pattern.CASE_INSENSITIVE);
							BasicDBList values = new BasicDBList();
							values.add(new BasicDBObject("deviceObjName", pattern));
							values.add(new BasicDBObject("deviceObjCode", pattern));
							query.put("$or", values);
						
					
				}
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
					DBCursor cur = collection.find(query).sort(orderBy);
					while (cur.hasNext()) {
						
						DBObject obj = cur.next();
						list.add(obj.toMap());
					}
					
					pageBean.setList(list);
				}
				
				return pageBean;
			}
		});
	}
	
	public int batchInsertProDevice(final List<DBObject> proDeviceList) {
		
		return this.mongoTemplate.execute("PE_BS_RP_DEVICES", new CollectionCallback<Integer>() {
			
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
				WriteResult result = collection.insert(proDeviceList);
				
				return result.getN();
			}
		});
	}
	
	public Map<String, Object> getProDevice(final String params) {
		
		return this.mongoTemplate.execute("PE_BS_RP_DEVICES", new CollectionCallback<Map<String, Object>>() {
			
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
			public Map<String, Object> doInCollection(DBCollection collection)
					throws MongoException, DataAccessException {
				// TODO Auto-generated method stub
				DBObject query = (DBObject) JSON.parse(params);
				DBObject proDeviceObj = collection.findOne(query);
				if (proDeviceObj != null) {
					
					return proDeviceObj.toMap();
				}
				
				return null;
			}
		});
	}
	
	public int auditProDevice(final Map<String, Object> params) {
		
		return this.mongoTemplate.execute("PE_BS_RP_DEVICES", new CollectionCallback<Integer>() {
			
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
				DBObject query = new BasicDBObject();
				query.put("companyCode", params.get("companyCode"));
				query.put("reserveProNo", params.get("reserveProNo"));
				query.put("deviceObjCode", params.get("deviceObjCode"));
				DBObject proDeviceObj = collection.findOne(query);
				if (proDeviceObj != null) {
					
					proDeviceObj.put("AUDIT_STATE", "已审核");
					proDeviceObj.put("rpAuditState", "1");
					proDeviceObj.put("auditPersonCode", params.get("auditPersonCode"));
					proDeviceObj.put("auditPersonName", params.get("auditPersonName"));
					proDeviceObj.put("auditDate", params.get("auditDate"));
					proDeviceObj.put("auditSuggest", params.get("auditSuggest"));
					proDeviceObj.put("sysProcessFlag", "1");
					
					WriteResult result = collection.update(query, proDeviceObj);
					
					return result.getN();
				}
				
				return 0;
			}
		});
	}
	
	public int updateDeviceAuditState(final Map<String, Object> params) {
		
		return this.mongoTemplate.execute("PM_MCT_LIST", new CollectionCallback<Integer>() {
			
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
				DBObject query = new BasicDBObject();
				query.put("companyCode", params.get("companyCode"));
				query.put("deviceObjCode", params.get("deviceObjCode"));
				DBObject proDeviceObj = collection.findOne(query);
				if (proDeviceObj != null) {
					
					proDeviceObj.put("AUDIT_STATE", "已审核");
					
					WriteResult result = collection.update(query, proDeviceObj);
					
					return result.getN();
				}
				
				return 0;
			}
		});
	}
	
public List<Map<String,Object>> auditProDeviceAllBefore(final Map<String, Object> params) {
		
		return this.mongoTemplate.execute("PE_BS_RP_DEVICES", new CollectionCallback<List<Map<String,Object>>>() {
			
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
			public List<Map<String,Object>> doInCollection(DBCollection collection)
					throws MongoException, DataAccessException {
				// TODO Auto-generated method stub
				DBObject query = new BasicDBObject();
				query.put("companyCode", params.get("companyCode"));
				query.put("reserveProNo", params.get("reserveProNo"));
				
				/**/
				if (params.get("mctCode") != null && !"".equals(params.get("mctCode"))) {
					
					query.put("mctCode", params.get("mctCode"));
				}
				if (params.get("deviceTypeCode") != null && !"".equals(params.get("deviceTypeCode"))) {
					
					query.put("deviceTypeCode", params.get("deviceTypeCode"));
				}
				if (params.get("rpAuditState") != null && !"".equals(params.get("rpAuditState"))) {
					
					query.put("rpAuditState", params.get("rpAuditState"));
				}
				if (params.get("sysProcessFlag") != null && !"".equals(params.get("sysProcessFlag"))) {
					
					query.put("sysProcessFlag", params.get("sysProcessFlag"));
				}
				if (params.get("proNo") != null && !"".equals(params.get("proNo"))) {
					
					query.put("proNo", params.get("proNo"));
				}
				Object searchName = params.get("searchName");
				if (params.get("attrJson") != null && !"".equals(params.get("attrJson"))) {
					
					String attrJson = (String) params.get("attrJson");
					Map<String, Object> attrMap = JSONObject.parseObject(attrJson);
					Iterator<String> it = attrMap.keySet().iterator();
					while (it.hasNext()) {
						
						String key = it.next();
						if(attrMap.get(key)==null || "".equals(attrMap.get(key))){
							continue;
						}
						query.put(key, attrMap.get(key));
					}
				}
				BasicDBList andConds = new BasicDBList();
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
							DBObject orCond=new BasicDBObject();
							orCond.put("$or", values);
							andConds.add(orCond);
						}
					}

				}
				BasicDBList orConds = new BasicDBList();
				orConds.add(new BasicDBObject("rpAuditState", "0"));
				orConds.add(new BasicDBObject("rpAuditState", null));
				DBObject orCond = new BasicDBObject();
				orCond.put("$or", orConds);
				
				andConds.add(orCond);
				
				query.put("$and", andConds);
				/**/
				DBCursor curs = collection.find(query);
				
				List<Map<String,Object>> res = new ArrayList<Map<String, Object>>();
				while(curs.hasNext()){
					DBObject obj = curs.next();
					res.add(obj.toMap());
				}
				return res;
			}
		});
	}
	
	public int auditProDeviceAll(final Map<String, Object> params) {
		
		return this.mongoTemplate.execute("PE_BS_RP_DEVICES", new CollectionCallback<Integer>() {
			
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
				DBObject query = new BasicDBObject();
				query.put("companyCode", params.get("companyCode"));
				query.put("reserveProNo", params.get("reserveProNo"));
				
				/**/
				if (params.get("mctCode") != null && !"".equals(params.get("mctCode"))) {
					
					query.put("mctCode", params.get("mctCode"));
				}
				if (params.get("deviceTypeCode") != null && !"".equals(params.get("deviceTypeCode"))) {
					
					query.put("deviceTypeCode", params.get("deviceTypeCode"));
				}
				if (params.get("rpAuditState") != null && !"".equals(params.get("rpAuditState"))) {
					
					query.put("rpAuditState", params.get("rpAuditState"));
				}
				if (params.get("sysProcessFlag") != null && !"".equals(params.get("sysProcessFlag"))) {
					
					query.put("sysProcessFlag", params.get("sysProcessFlag"));
				}
				if (params.get("proNo") != null && !"".equals(params.get("proNo"))) {
					
					query.put("proNo", params.get("proNo"));
				}
				Object searchName = params.get("searchName");
				if (params.get("attrJson") != null && !"".equals(params.get("attrJson"))) {
					
					String attrJson = (String) params.get("attrJson");
					Map<String, Object> attrMap = JSONObject.parseObject(attrJson);
					Iterator<String> it = attrMap.keySet().iterator();
					while (it.hasNext()) {
						
						String key = it.next();
						if(attrMap.get(key)==null || "".equals(attrMap.get(key))){
							continue;
						}
						query.put(key, attrMap.get(key));
					}
				}
				
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
				/**/
				DBObject proDeviceObj = new BasicDBObject();
				proDeviceObj.put("rpAuditState", "1");
				proDeviceObj.put("auditPersonCode", params.get("auditPersonCode"));
				proDeviceObj.put("auditPersonName", params.get("auditPersonName"));
				proDeviceObj.put("auditDate", params.get("auditDate"));
				proDeviceObj.put("auditSuggest", params.get("auditSuggest"));
				proDeviceObj.put("sysProcessFlag", "1");
				
				DBObject set=new BasicDBObject("$set", proDeviceObj);
				WriteResult result = collection.updateMulti(query, set);
				return result.getN();
			}
		});
	}
	
	public int batchAuditProDeviceMaterial(final List<DBObject> proDeviceMateriaList) {
		
		return this.mongoTemplate.execute("PE_BS_RP_DEVICE_MATERIAL_DETAIL", new CollectionCallback<Integer>() {
			
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
				WriteResult result = collection.insert(proDeviceMateriaList);
				
				return result.getN();
			}
		});
	}
	
	public int deleteProDevice(final Map<String, Object> params) {
		
		return this.mongoTemplate.execute("PE_BS_RP_DEVICES", new CollectionCallback<Integer>() {
			
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
				DBObject query = new BasicDBObject();
				query.put("companyCode", params.get("companyCode"));
				query.put("reserveProNo", params.get("reserveProNo"));
				query.put("deviceObjCode", params.get("deviceObjCode"));
				DBObject proDeviceObj = collection.findOne(query);
				if (proDeviceObj != null) {
					
					proDeviceObj.put("sysProcessFlag", "0");
					proDeviceObj.put("auditSuggest", params.get("auditSuggest"));
					proDeviceObj.put("auditPersonCode", params.get("auditPersonCode"));
					proDeviceObj.put("auditPersonName", params.get("auditPersonName"));
					proDeviceObj.put("auditDate", DateTimeUtil.getSystemDateString());
					WriteResult result = collection.update(query, proDeviceObj);
					
					return result.getN();
				}
				
				return 0;
			}
		});
	}
	
	public List<Map<String, Object>> getProDeviceList(final String params, final Object searchName) {
		
		return this.mongoTemplate.execute("PE_BS_RP_DEVICES", new CollectionCallback<List<Map<String, Object>>>() {
			
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
				List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
				DBObject query = (DBObject) JSON.parse(params);
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
				
				DBCursor cur = collection.find(query);
				while (cur.hasNext()) {
					
					DBObject obj = cur.next();
					list.add(obj.toMap());
				}
				
				return list;
			}
		});
	}
	
	public List<Map<String, Object>> censusProDevice(final String companyCode, final String reserveProNo, 
			final String rpAuditState, final List<Map<String, String>> deviceMaterialAttrList ) {
		
		return this.mongoTemplate.execute("PE_BS_RP_DEVICES", new CollectionCallback<List<Map<String, Object>>>() {
			
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
				if (rpAuditState != null && !"".equals(rpAuditState)) {
					
					query.put("rpAuditState", rpAuditState);
				}
				BasicDBObject key = new BasicDBObject();
				key.put("REFORM_PROGRAM", true);
				BasicDBObject initial = new BasicDBObject(); 
				initial.put("houseHold", 0);
				initial.put("estateCount", 0);
				initial.put("unitCount", 0);
				String reduce = "function(doc, prev) {if (!isNaN(parseFloat(doc.HOUSE_HOLD))) prev.houseHold += parseFloat(doc.HOUSE_HOLD);" +
						"if (doc.ESTAE_NAME) prev.estateCount++;if (doc.AMUL_BOX_ADDR) prev.unitCount++;";
				for (Map<String, String> deviceMaterialAttr: deviceMaterialAttrList) {
					
					initial.append(deviceMaterialAttr.get("ename"), 0);
					reduce += "if (!isNaN(parseFloat(doc." + deviceMaterialAttr.get("ename") + "))) prev." + deviceMaterialAttr.get("ename") + "+=parseFloat(doc." 
					+ deviceMaterialAttr.get("ename")  + ");";
				}
				reduce += "}";
				
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
	
	public List<Map<String, Object>> proDeviceAuditMaterialCountStatistics(final String companyCode, final String reserveProNo) {
		
		return this.mongoTemplate.execute("PE_BS_RP_DEVICE_MATERIAL_DETAIL", new CollectionCallback<List<Map<String, Object>>>() {
			
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
				BasicDBObject key = new BasicDBObject();
				key.put("proNo", true);
				key.put("mctCode", true);
				key.put("materialTypeCode", true);
				BasicDBObject initial = new BasicDBObject();  
				initial.append("needCount", 0); 
				String reduce = "function(doc, prev) {prev.needCount += parseInt(doc.needCount);}";
				
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
	
	public int deleteReserveProDevice(final Map<String, Object> params) {
		
		return this.mongoTemplate.execute("PE_BS_RP_DEVICES", new CollectionCallback<Integer>() {
			
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
				DBObject query = new BasicDBObject();
				query.put("companyCode", params.get("companyCode"));
				query.put("reserveProNo", params.get("reserveProNo"));
				WriteResult result = collection.remove(query);
				
				return result.getN();
			}
		});
	}
	
	public int deleteReserveProDeviceMaterial(final Map<String, Object> params) {
		
		return this.mongoTemplate.execute("PE_BS_RP_DEVICE_MATERIAL_DETAIL", new CollectionCallback<Integer>() {
			
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
				DBObject query = new BasicDBObject();
				query.put("companyCode", params.get("companyCode"));
				query.put("reserveProNo", params.get("reserveProNo"));
				WriteResult result = collection.remove(query);
				
				return result.getN();
			}
		});
	}
	
	public int deleteRPDevice(final Map<String, Object> params) {
		
		return this.mongoTemplate.execute("PE_BS_RP_DEVICES", new CollectionCallback<Integer>() {
			
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
				DBObject query = new BasicDBObject();
				query.put("companyCode", params.get("companyCode"));
				query.put("reserveProNo", params.get("reserveProNo"));
				query.put("deviceObjCode", params.get("deviceObjCode"));
				WriteResult result = collection.remove(query);
				
				return result.getN();
			}
		});
	}
	
	public int deleteRPDeviceMaterial(final Map<String, Object> params) {
		
		return this.mongoTemplate.execute("PE_BS_RP_DEVICE_MATERIAL_DETAIL", new CollectionCallback<Integer>() {
			
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
				DBObject query = new BasicDBObject();
				query.put("companyCode", params.get("companyCode"));
				query.put("reserveProNo", params.get("reserveProNo"));
				query.put("deviceObjCode", params.get("deviceObjCode"));
				WriteResult result = collection.remove(query);
				
				return result.getN();
			}
		});
	}
	
	
	public int batchDeleteRPDevice(final Map<String, Object> params) {
		
		return this.mongoTemplate.execute("PE_BS_RP_DEVICES", new CollectionCallback<Integer>() {
			
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
				DBObject query = new BasicDBObject();
				query.put("companyCode", params.get("companyCode"));
				query.put("reserveProNo", params.get("reserveProNo"));
				String codes = (String)params.get("deviceObjCodes");
				String[] codesArr = codes.split(",");
				BasicDBList lst = new BasicDBList();
				for (int i = 0; i < codesArr.length; i++) {
					String code = codesArr[i];
					lst.add(code);
				}
				query.put("deviceObjCode", new BasicDBObject("$in", lst));
				WriteResult result = collection.remove(query);
				
				return result.getN();
			}
		});
	}
	
	public int batchDeleteRPDeviceMaterial(final Map<String, Object> params) {
		
		return this.mongoTemplate.execute("PE_BS_RP_DEVICE_MATERIAL_DETAIL", new CollectionCallback<Integer>() {
			
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
				DBObject query = new BasicDBObject();
				query.put("companyCode", params.get("companyCode"));
				query.put("reserveProNo", params.get("reserveProNo"));
				String codes = (String)params.get("deviceObjCodes");
				String[] codesArr = codes.split(",");
				BasicDBList lst = new BasicDBList();
				for (int i = 0; i < codesArr.length; i++) {
					String code = codesArr[i];
					lst.add(code);
				}
				query.put("deviceObjCode", new BasicDBObject("$in", lst));
				WriteResult result = collection.remove(query);
				
				return result.getN();
			}
		});
	}
	
	public int updateRMctDevice(final String mctDeviceJson) {
		
		int iCount = 0;
		
		try {
			
			iCount = this.mongoTemplate.execute("PM_R_MCT_LIST", new CollectionCallback<Integer>() {
				
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
			logger.error("修改mongodb总控表清单出错", e);
		}
		
		return iCount;
	}
	
	public int batchDeleteRMctDevices(final String mctViewCode, final BasicDBList deviceObjCodes) {
		
		int iCount = 0;
		
		try {
			
			iCount = this.mongoTemplate.execute("PM_R_MCT_LIST", new CollectionCallback<Integer>() {
				
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
					DBObject query = new BasicDBObject();
					query.put("mctViewCode", mctViewCode);
					query.put("deviceObjCode", new BasicDBObject("$in", deviceObjCodes));  
					
					WriteResult result = collection.remove(query);
					
					return result.getN();
				}
			});
		}
		catch (Exception e) {
			// TODO: handle exception
			logger.error("删除mongodb总控表清单出错", e);
		}
		
		return iCount;
	}
	
	public int updateReserveProDevice(final String mctDeviceJson) {
		
		int iCount = 0;
		
		try {
			
			iCount = this.mongoTemplate.execute("PE_BS_RP_DEVICES", new CollectionCallback<Integer>() {
				
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
			logger.error("修改mongodb总控表清单出错", e);
		}
		
		return iCount;
	}
	
	public int updateDevice(final String mctDeviceJson) {
		
		int iCount = 0;
		
		try {
			
			iCount = this.mongoTemplate.execute("PM_MCT_LIST", new CollectionCallback<Integer>() {
				
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
			logger.error("修改mongodb总控表清单出错", e);
		}
		
		return iCount;
	}
	
	public PageBean getUnAllocRMctDevicePage(final String params, final Object searchName, final int currentPage, final int perpage) {
		
		PageBean page = null;
		
		try {
			
			page = this.mongoTemplate.execute("PM_R_MCT_LIST", new CollectionCallback<PageBean>() {
				
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
					DBObject query = (DBObject) JSON.parse(params);
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
					query.put("ARCHIVE_STATUS", 0);
					query.put("PRO_STATUS", 0);
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
		}
		catch (Exception e) {
			// TODO: handle exception
			logger.error("获取mongodb总控表清单出错", e);
		}
		
		return page;
	}
	
	public List<Map> getUnAllocRMctDeviceList(final String params, final Object searchName) {
		
		return this.mongoTemplate.execute("PM_R_MCT_LIST", new CollectionCallback<List<Map>>() {
			
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
			public List<Map> doInCollection(DBCollection collection)
					throws MongoException, DataAccessException {
				// TODO Auto-generated method stub
				DBObject query = (DBObject) JSON.parse(params);
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
				query.put("ARCHIVE_STATUS", 0);
				query.put("PRO_STATUS", 0);
				
				List<Map> list = new ArrayList<Map>();
				DBCursor cur = collection.find(query);
				while (cur.hasNext()) {
					
					DBObject obj = cur.next();
					list.add(obj.toMap());
				}
				
				return list;
			}
		});
	}
	
	public int allocRMctDevices(final String params, final Object searchName) {
		
		return this.mongoTemplate.execute("PM_R_MCT_LIST", new CollectionCallback<Integer>() {
			
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
				DBObject query = (DBObject) JSON.parse(params);
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
				query.put("ARCHIVE_STATUS", 0);
				query.put("PRO_STATUS", 0);
				
				DBObject updateCondition = new BasicDBObject(); 
				DBObject setObject = new BasicDBObject();
				setObject.put("PRO_STATUS", 1);
				updateCondition.put("$set", setObject);
				WriteResult result = collection.update(query, updateCondition, false, true);
				
				return result.getN();
			}
		});
	}
	
	public int batchReleaseRMctDevices(final String mctViewCode, final BasicDBList deviceObjCodes) {
		
		return this.mongoTemplate.execute("PM_R_MCT_LIST", new CollectionCallback<Integer>() {
			
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
				DBObject query = new BasicDBObject();
				query.put("mctViewCode", mctViewCode);
				query.put("deviceObjCode", new BasicDBObject("$in", deviceObjCodes));  
				
				DBObject updateCondition = new BasicDBObject(); 
				DBObject setObject = new BasicDBObject();
				setObject.put("PRO_STATUS", 0);
				updateCondition.put("$set", setObject);
				WriteResult result = collection.update(query, updateCondition, false, true);
				
				return result.getN();
			}
		});
	}
	
	public List<DBObject> getMctViewDevices(final String companyCode, final String mctViewCode) {
		
		return this.mongoTemplate.execute("PM_R_MCT_LIST", new CollectionCallback<List<DBObject>>() {
			
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
			public List<DBObject> doInCollection(
					DBCollection collection) throws MongoException,
					DataAccessException {
				// TODO Auto-generated method stub
				DBObject query = new BasicDBObject();
				query.put("companyCode", companyCode);
				query.put("mctViewCode", mctViewCode);
				DBObject keys = new BasicDBObject();
				keys.put("deviceObjName", 1);
				keys.put("deviceObjCode", 1);
				
				List<DBObject> list = new ArrayList<DBObject>();
				DBCursor cur = collection.find(query, keys);
				while (cur.hasNext()) {
					
					DBObject obj = cur.next();
					list.add(obj);
				}
				
				return list;
			}
		});
	}
	
	public List<DBObject> getNoInRmctDevices(final DBObject query) {
		
		return this.mongoTemplate.execute("PM_MCT_LIST", new CollectionCallback<List<DBObject>>() {
			
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
			public List<DBObject> doInCollection(DBCollection collection)
					throws MongoException, DataAccessException {
				// TODO Auto-generated method stub
				List<DBObject> list = new ArrayList<DBObject>();
				DBCursor cur = collection.find(query);
				while (cur.hasNext()) {
					
					DBObject obj = cur.next();
					list.add(obj);
				}
				
				return list;
			}
			
		});
	}
	
	public int batchInRMct(final DBObject deviceItem) {
		
		return this.mongoTemplate.execute("PM_R_MCT_LIST", new CollectionCallback<Integer>() {
			
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
				
				WriteResult result = collection.insert(deviceItem);
				
				return result.getN();
			}
		});
	}

	public MongoTemplate getMongoTemplate() {
		return mongoTemplate;
	}

	public void setMongoTemplate(MongoTemplate mongoTemplate) {
		this.mongoTemplate = mongoTemplate;
	}
	
}
