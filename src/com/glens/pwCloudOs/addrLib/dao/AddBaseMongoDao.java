package com.glens.pwCloudOs.addrLib.dao;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.data.mongodb.core.CollectionCallback;
import org.springframework.data.mongodb.core.MongoTemplate;

import com.glens.eap.platform.core.utils.DateTimeUtil;
import com.glens.eap.platform.framework.beans.PageBean;
import com.glens.eap.platform.framework.beans.UserToken;
import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoException;
import com.mongodb.WriteResult;
import com.mongodb.util.JSON;
/**
 * 地址信息维护
 * @author MaDx
 * 
 */
public class AddBaseMongoDao {
	
	private static Log logger = LogFactory.getLog(AddBaseMongoDao.class);
	private MongoTemplate mongoTemplate;
	
	public void setMongoTemplate(MongoTemplate mongoTemplate) {
		this.mongoTemplate = mongoTemplate;
	}

	/**
	 * 分页查询
	 * @param params
	 * @param currentPage
	 * @param perpage
	 * @return
	 */
	public PageBean queryByPage(final Map<String, Object> params, final int currentPage, final int perpage) {

		PageBean page = null;
		try {
			page = this.mongoTemplate.execute("ADD_BASE", new CollectionCallback<PageBean>() {
				@Override
				public PageBean doInCollection(DBCollection collection)
						throws MongoException, DataAccessException {
					DBObject query = new BasicDBObject();
					// ADDR_CLASS
					if(params != null && params.get("ADDR_CLASS") != null && !"".equals(params.get("ADDR_CLASS"))){
						query.put("ADDR_CLASS", params.get("ADDR_CLASS"));
					}
					// QUALITY_AUDIT
					if(params != null && params.get("QUALITY_AUDIT") != null && 0!=(Integer)params.get("QUALITY_AUDIT")){
						query.put("QUALITY_AUDIT", params.get("QUALITY_AUDIT"));
					}
					// ADDR_STATUS
					if(params != null && params.get("ADDR_STATUS") != null && 0!=(Integer)params.get("ADDR_STATUS")){
						query.put("ADDR_STATUS", params.get("ADDR_STATUS"));
					}
					
					if(params != null && params.get("datasourceCode") != null && !"".equals(params.get("datasourceCode"))){
						query.put("DATASOURCE_CODE", params.get("datasourceCode"));
					}
					
					
					
					// searchName
					if (params != null && params.get("searchName") != null && !"".equals(params.get("searchName"))) {
						String searchName = (String)params.get("searchName");
						String[] packet = searchName.toString().split(";");
						if (packet.length > 1) {
							String[] fields = packet[0].split(",");
							String searchValue = packet[1];
							if (searchValue != null && !"".equals(searchValue)) {
								if(searchValue.indexOf(" ")>-1){
									searchValue=searchValue.replace(" ", ".*");
								}
								Pattern pattern = Pattern.compile("^.*" + searchValue + ".*$", Pattern.CASE_INSENSITIVE);
								BasicDBList values = new BasicDBList();
								for (String field : fields) {
									values.add(new BasicDBObject(field, pattern));
								}

								values.add(new BasicDBObject("ADDR_NAME", pattern));
								values.add(new BasicDBObject("ADDR_CODE", pattern));
								values.add(new BasicDBObject("PIN_YIN", pattern));
								values.add(new BasicDBObject("FIRST_LETTER", pattern));
								query.put("$or", values);
							}
						}
					}
					
					Long totalCount = collection.count(query);
					PageBean pageBean = null;
					if (totalCount < 1) {
						pageBean = new PageBean();
						pageBean.setList(new ArrayList());
					} else {
						pageBean = new PageBean(totalCount.intValue(), currentPage, perpage);
						List<Map> list = new ArrayList<Map>();
						DBObject orderBy = new BasicDBObject();
						orderBy.put("ADDR_CODE", 1);
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
			e.printStackTrace();
			logger.error("获取mongodb地址信息出错", e);
		}
		return page;
	}
	
	/**
	 * 搜索查询
	 * @param params
	 * @param currentPage
	 * @param perpage
	 * @return
	 */
	public List<Map<String, Object>> query(final Map<String, Object> params) {

		List<Map<String, Object>> res = null;
		try {
			res = this.mongoTemplate.execute("ADD_BASE", new CollectionCallback<List<Map<String, Object>>>() {
				@Override
				public List<Map<String, Object>> doInCollection(DBCollection collection)
						throws MongoException, DataAccessException {
					DBObject query = new BasicDBObject();
					// ADDR_CLASS
					if(params != null && params.get("ADDR_CLASS") != null && !"".equals(params.get("ADDR_CLASS"))){
						query.put("ADDR_CLASS", params.get("ADDR_CLASS"));
					}
					// QUALITY_AUDIT
					if(params != null && params.get("QUALITY_AUDIT") != null && 0!=(Integer)params.get("QUALITY_AUDIT")){
						query.put("QUALITY_AUDIT", params.get("QUALITY_AUDIT"));
					}
					// ADDR_STATUS
					if(params != null && params.get("ADDR_STATUS") != null && 0!=(Integer)params.get("ADDR_STATUS")){
						query.put("ADDR_STATUS", params.get("ADDR_STATUS"));
					}
					
					if(params != null && params.get("datasourceCode") != null && !"".equals(params.get("datasourceCode"))){
						query.put("DATASOURCE_CODE", params.get("datasourceCode"));
					}
					
					
					
					// searchName
					if (params != null && params.get("searchName") != null && !"".equals(params.get("searchName"))) {
						String searchName = (String)params.get("searchName");
						String[] packet = searchName.toString().split(";");
						if (packet.length > 1) {
							String[] fields = packet[0].split(",");
							String searchValue = packet[1];
							if (searchValue != null && !"".equals(searchValue)) {
								if(searchValue.indexOf(" ")>-1){
									searchValue=searchValue.replace(" ", ".*");
								}
								Pattern pattern = Pattern.compile("^.*" + searchValue + ".*$", Pattern.CASE_INSENSITIVE);
								BasicDBList values = new BasicDBList();
								for (String field : fields) {
									values.add(new BasicDBObject(field, pattern));
								}

								values.add(new BasicDBObject("ADDR_NAME", pattern));
								values.add(new BasicDBObject("ADDR_CODE", pattern));
								values.add(new BasicDBObject("CREATER_NAME", pattern));
								values.add(new BasicDBObject("AUDITOR_NAME", pattern));
								values.add(new BasicDBObject("PIN_YIN", pattern));
								values.add(new BasicDBObject("FIRST_LETTER", pattern));
								query.put("$or", values);
							}
						}
					}
					
					List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
					DBObject orderBy = new BasicDBObject();
					orderBy.put("ADDR_CODE", 1);
					DBCursor cur = collection.find(query);//.sort(orderBy);
					while (cur.hasNext()) {
						DBObject obj = cur.next();
						list.add(obj.toMap());
					}
					return list;
				}
			});
		}
		catch (Exception e) {
			e.printStackTrace();
			logger.error("获取mongodb地址信息出错", e);
		}
		return res;
	}
	
	/**
	 * 新增
	 * @param params
	 * @param token
	 * @return
	 */
	public int insert(final Map<String, Object> params) {

		int iCount = 0;

		try {

			iCount = this.mongoTemplate.execute("ADD_BASE", new CollectionCallback<Integer>() {

				@Override
				public Integer doInCollection(DBCollection collection)
						throws MongoException, DataAccessException {
					DBObject insertObj = new BasicDBObject(params);
					insertObj.put("ADDR_STATUS", 2);// 默认：2-在用
					String date = null;
					try {
						date = DateTimeUtil.formatDate(new Date(), DateTimeUtil.FORMAT_1);
					} catch (ParseException e) {
						e.printStackTrace();
					}
					insertObj.put("SYS_CREATE_TIME", date);
					insertObj.put("SYS_PROCESS_FLAG", 1);
					
					WriteResult result = collection.insert(insertObj);
					int count = result.getN();
					return count;
				}
			});
		}
		catch (Exception e) {
			e.printStackTrace();
			logger.error("插入mongodb地址信息出错", e);
		}
		return iCount;
	}
	
	/**
	 * 修改
	 * @param params
	 * @return
	 */
	public int update(final Map<String, Object> params) {

		int iCount = 0;

		try {

			iCount = this.mongoTemplate.execute("ADD_BASE", new CollectionCallback<Integer>() {

				@Override
				public Integer doInCollection(DBCollection collection)
						throws MongoException, DataAccessException {
					DBObject query = new BasicDBObject();
					query.put("ADDR_CODE", params.get("ADDR_CODE"));
					
					DBObject val = new BasicDBObject(params);
					val.removeField("ADDR_CODE");
					String date = null;
					try {
						date = DateTimeUtil.formatDate(new Date(), DateTimeUtil.FORMAT_1);
					} catch (ParseException e) {
						e.printStackTrace();
					}
					val.put("SYS_UPDATE_TIME", date);
					
					WriteResult result = collection.update(query, new BasicDBObject("$set", val));
					int count = result.getN();
					return count;
				}
			});
		}
		catch (Exception e) {
			e.printStackTrace();
			logger.error("修改mongodb地址信息出错", e);
		}
		return iCount;
	}
	
	/**
	 * 修改位置信息
	 * @param params
	 * @return
	 */
	public int updatePosition(final Map<String, Object> params) {

		int iCount = 0;

		try {

			iCount = this.mongoTemplate.execute("ADD_BASE", new CollectionCallback<Integer>() {

				@Override
				public Integer doInCollection(DBCollection collection)
						throws MongoException, DataAccessException {
					DBObject query = new BasicDBObject();
					query.put("ADDR_CODE", params.get("ADDR_CODE"));
					DBObject val = new BasicDBObject();
					val.put("SHOW_X", params.get("SHOW_X"));
					val.put("SHOW_Y", params.get("SHOW_Y"));
					val.put("WGS84_X", params.get("WGS84_X"));
					val.put("WGS84_Y", params.get("WGS84_Y"));
					DBObject set = new BasicDBObject("$set", val);
					WriteResult result = collection.update(query, set);
					int count = result.getN();
					return count;
				}
			});
		}
		catch (Exception e) {
			e.printStackTrace();
			logger.error("修改mongodb地址信息出错", e);
		}
		return iCount;
	}
	
	/**
	 * 查询
	 * @param params
	 * @return
	 */
	public Map<String, Object> find(final Map<String, Object> params) {
		Map<String, Object> res = null;
		try {

			res = this.mongoTemplate.execute("ADD_BASE", new CollectionCallback<Map<String, Object>>() {

				@Override
				public Map<String, Object> doInCollection(DBCollection collection)
						throws MongoException, DataAccessException {
					DBObject query = new BasicDBObject();
					query.put("ADDR_CODE", params.get("ADDR_CODE"));
					DBCursor cur = collection.find(query);
					if(cur.hasNext()) {
						DBObject obj = cur.next();
						return obj.toMap();
					}
					return null;
				}
			});
		}
		catch (Exception e) {
			e.printStackTrace();
			logger.error("查询mongodb地址信息出错", e);
		}
		return res;
	}
	/**
	 * 审核
	 * @param params
	 * @return
	 */
	public int audit(final Map<String, Object> params) {

		int iCount = 0;

		try {

			iCount = this.mongoTemplate.execute("ADD_BASE", new CollectionCallback<Integer>() {

				@Override
				public Integer doInCollection(DBCollection collection)
						throws MongoException, DataAccessException {
					DBObject query = new BasicDBObject();
					query.put("ADDR_CODE", params.get("ADDR_CODE"));
					
					DBObject set = new BasicDBObject();
					set.put("QUALITY_AUDIT", params.get("QUALITY_AUDIT"));
					set.put("AUDITOR_CODE", params.get("AUDITOR_CODE"));
					set.put("AUDITOR_NAME", params.get("AUDITOR_NAME"));
					set.put("AUDIT_SUGGEST", params.get("AUDIT_SUGGEST"));
					
					String date = null;
					try {
						date = DateTimeUtil.formatDate(new Date(), DateTimeUtil.FORMAT_1);
					} catch (ParseException e) {
						e.printStackTrace();
					}
					set.put("AUDIT_TIME", date);
					
					WriteResult result = collection.update(query, new BasicDBObject("$set", set));
					int count = result.getN();
					return count;
				}
			});
		}
		catch (Exception e) {
			e.printStackTrace();
			logger.error("审核mongodb地址信息出错", e);
		}
		return iCount;
	}
	
	/**
	 * 删除
	 * @param params
	 * @param token
	 * @return
	 */
	public int remove(final Map<String, Object> params) {

		int iCount = 0;

		try {

			iCount = this.mongoTemplate.execute("ADD_BASE", new CollectionCallback<Integer>() {

				@Override
				public Integer doInCollection(DBCollection collection)
						throws MongoException, DataAccessException {
					DBObject query = new BasicDBObject();
					query.put("ADDR_CODE", params.get("ADDR_CODE"));
					WriteResult result = collection.remove(query);
					int count = result.getN();
					return count;
				}
			});
		}
		catch (Exception e) {
			e.printStackTrace();
			logger.error("删除mongodb地址信息出错", e);
		}
		return iCount;
	}
	
	public List<Map<String, Object>> getAddrsByParentAddrCode(String addrCode) {
		
		DBCollection collection = this.mongoTemplate.getCollection("ADD_BASE");
		List<Map<String, Object>> addrList = new ArrayList<Map<String,Object>>();
		DBObject query = new BasicDBObject();
		query.put("PARENT_ADDR_CODE", addrCode);
		DBCursor cur = collection.find(query);
		while (cur.hasNext()) {
			
			DBObject obj = cur.next();
			addrList.add(obj.toMap());
		}
		
		return addrList;
	}
	
	public List<Map<String, Object>> getAddrList(Map<String, Object> params) {
		
		List<Map<String, Object>> addrList = new ArrayList<Map<String,Object>>();
		DBCollection collection = this.mongoTemplate.getCollection("ADD_BASE");
		DBObject query = new BasicDBObject();
		// ADDR_CLASS
		if(params != null && params.get("ADDR_CLASS") != null && !"".equals(params.get("ADDR_CLASS"))){
			query.put("ADDR_CLASS", params.get("ADDR_CLASS"));
		}
		// QUALITY_AUDIT
		if(params != null && params.get("QUALITY_AUDIT") != null && 0!=(Integer)params.get("QUALITY_AUDIT")){
			query.put("QUALITY_AUDIT", params.get("QUALITY_AUDIT"));
		}
		// ADDR_STATUS
		if(params != null && params.get("ADDR_STATUS") != null && 0!=(Integer)params.get("ADDR_STATUS")){
			query.put("ADDR_STATUS", params.get("ADDR_STATUS"));
		}
		
		if(params != null && params.get("datasourceCode") != null && !"".equals(params.get("datasourceCode"))){
			query.put("DATASOURCE_CODE", params.get("datasourceCode"));
		}
		// searchName
		if (params != null && params.get("searchName") != null && !"".equals(params.get("searchName"))) {
			String searchName = (String)params.get("searchName");
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

					values.add(new BasicDBObject("ADDR_NAME", pattern));
					values.add(new BasicDBObject("ADDR_CODE", pattern));
					values.add(new BasicDBObject("PIN_YIN", pattern));
					values.add(new BasicDBObject("FIRST_LETTER", pattern));
					query.put("$or", values);
				}
			}
		}
		
		DBCursor cur = collection.find(query);
		while (cur.hasNext()) {
			
			DBObject obj = cur.next();
			addrList.add(obj.toMap());
		}
		
		return addrList;
	}
	
	public Map<String, Object> getAddrDistributeList(Map<String, Object> params) {
		
		List<DBObject> addrList = new ArrayList<DBObject>();
		String groupField = params.get("groupField").toString();
		DBCollection collection = this.mongoTemplate.getCollection("ADD_BASE");
		DBObject query = new BasicDBObject();
		query.put("IS_REGION", 0);
		// ADDR_CLASS
		if(params != null && params.get("ADDR_CLASS") != null && !"".equals(params.get("ADDR_CLASS"))){
			query.put("ADDR_CLASS", params.get("ADDR_CLASS"));
		}
		// QUALITY_AUDIT
		if(params != null && params.get("QUALITY_AUDIT") != null && 0!=(Integer)params.get("QUALITY_AUDIT")){
			query.put("QUALITY_AUDIT", params.get("QUALITY_AUDIT"));
		}
		// ADDR_STATUS
		if(params != null && params.get("ADDR_STATUS") != null && 0!=(Integer)params.get("ADDR_STATUS")){
			query.put("ADDR_STATUS", params.get("ADDR_STATUS"));
		}
		
		if(params != null && params.get("datasourceCode") != null && !"".equals(params.get("datasourceCode"))){
			query.put("DATASOURCE_CODE", params.get("datasourceCode"));
		}
		// searchName
		if (params != null && params.get("searchName") != null && !"".equals(params.get("searchName"))) {
			String searchName = (String)params.get("searchName");
			Pattern pattern = Pattern.compile("^.*" + searchName + ".*$", Pattern.CASE_INSENSITIVE);
			BasicDBList values = new BasicDBList();

			values.add(new BasicDBObject("ADDR_NAME", pattern));
			values.add(new BasicDBObject("ADDR_CODE", pattern));
			values.add(new BasicDBObject("PIN_YIN", pattern));
			values.add(new BasicDBObject("FIRST_LETTER", pattern));
			query.put("$or", values);
		}
		
		DBObject keys = new BasicDBObject();
		keys.put("ADDR_CODE", 1);
		keys.put("ADDR_NAME", 1);
		keys.put("WGS84_X", 1);
		keys.put("WGS84_Y", 1);
		keys.put("SHOW_X", 1);
		keys.put("SHOW_Y", 1);
		keys.put("DATASOURCE_CODE", 1);
		keys.put("DATASOURCE_NAME", 1);
		
		List<Map<String, Object>> addrGroupList = new ArrayList<Map<String,Object>>();
		
		DBCursor cur = collection.find(query, keys);
		while (cur.hasNext()) {
			
			DBObject obj = cur.next();
			addrList.add(obj);
			
			//程序手动分组
			Map<String, Object> addrGroupItem = getAddrGroupItem(groupField, 
					(String) obj.get(groupField), addrGroupList);
			if (addrGroupItem != null) {
				
				int groupCount = (Integer) addrGroupItem.get("groupCount");
				groupCount++;
				addrGroupItem.put("groupCount", groupCount);
			}
			else {
				
				addrGroupItem = new HashMap<String, Object>();
				addrGroupItem.put("groupCount", 1);
				addrGroupItem.put(groupField, obj.get(groupField));
				addrGroupList.add(addrGroupItem);
			}
		}
		
		Map<String, Object> resultList = new HashMap<String, Object>();
		resultList.put("addrList", addrList);
		resultList.put("groupList", addrGroupList);
		
		return resultList;
	}
	
	public int saveAddr(Map<String, Object> addr) {
		
		DBCollection collection = this.mongoTemplate.getCollection("ADD_BASE");
		DBObject addrObj = new BasicDBObject(addr);
		WriteResult result = collection.save(addrObj);
		int count = result.getN();
		return count;
	}
	
	public List<Map<String, Object>> getAddrGroupCount(Map<String, Object> params) {

		List<Map<String, Object>> addrList = new ArrayList<Map<String,Object>>();
		String groupField = params.get("groupField").toString();
		DBCollection collection = this.mongoTemplate.getCollection("ADD_BASE");
		DBObject query = new BasicDBObject();
		query.put("IS_REGION", 0);
		// ADDR_CLASS
		if(params != null && params.get("ADDR_CLASS") != null && !"".equals(params.get("ADDR_CLASS"))){
			query.put("ADDR_CLASS", params.get("ADDR_CLASS"));
		}
		// QUALITY_AUDIT
		if(params != null && params.get("QUALITY_AUDIT") != null && 0!=(Integer)params.get("QUALITY_AUDIT")){
			query.put("QUALITY_AUDIT", params.get("QUALITY_AUDIT"));
		}
		// ADDR_STATUS
		if(params != null && params.get("ADDR_STATUS") != null && 0!=(Integer)params.get("ADDR_STATUS")){
			query.put("ADDR_STATUS", params.get("ADDR_STATUS"));
		}
		
		if(params != null && params.get("datasourceCode") != null && !"".equals(params.get("datasourceCode"))){
			query.put("DATASOURCE_CODE", params.get("datasourceCode"));
		}
		// searchName
		if (params != null && params.get("searchName") != null && !"".equals(params.get("searchName"))) {
			String searchName = (String)params.get("searchName");
			Pattern pattern = Pattern.compile("^.*" + searchName + ".*$", Pattern.CASE_INSENSITIVE);
			BasicDBList values = new BasicDBList();

			values.add(new BasicDBObject("ADDR_NAME", pattern));
			values.add(new BasicDBObject("ADDR_CODE", pattern));
			values.add(new BasicDBObject("PIN_YIN", pattern));
			values.add(new BasicDBObject("FIRST_LETTER", pattern));
			query.put("$or", values);
		}
		
		BasicDBObject key = new BasicDBObject(groupField, true);
		BasicDBObject initial = new BasicDBObject();  
		initial.append("groupCount", 0);  
		String reduce = "function(doc, prev) {prev.groupCount++;}";
		BasicDBList groupResult = (BasicDBList) collection.group(key, query, initial, reduce);
		if (groupResult != null && groupResult.size() > 0) {

			for (int i = 0;i < groupResult.size();i++) {

				DBObject item = (DBObject) groupResult.get(i);
				addrList.add(item.toMap());
			}
		}
		
		return addrList;
	}
	
	public List<Map<String, Object>> getPlateAddrList(Map<String, Object> params) {
		
		List<Map<String, Object>> addrList = new ArrayList<Map<String,Object>>();
		DBCollection collection = this.mongoTemplate.getCollection("ADD_BASE");
		DBObject query = new BasicDBObject();
		query.put("ADDR_TYPE", 2);
		if (params.get("plateTypes") != null && !"".equals(params.get("plateTypes"))) {
			
			String plateTypes = (String) params.get("plateTypes");
			String[] plateTypeArray = plateTypes.split(",");
			BasicDBList plateTypeInQuery = new BasicDBList();
			for (String plateType : plateTypeArray) {
				
				plateTypeInQuery.add(Integer.parseInt(plateType));
			}
			
			query.put("PLATE.PLATE_TYPE", new BasicDBObject("$in", plateTypeInQuery));
		}
		
		DBCursor cur = collection.find(query);
		while (cur.hasNext()) {
			
			DBObject obj = cur.next();
			addrList.add(obj.toMap());
		}
		
		return addrList;
	}
	
	private Map<String, Object> getAddrGroupItem(String groupField, String groupValue, 
			List<Map<String, Object>> addrGroupList) {
		
		Map<String, Object> addrGroupItem = null;
		if (addrGroupList != null && addrGroupList.size() > 0) {
			
			for (Map<String, Object> _addrGroupItem : addrGroupList) {
				
				if (groupValue.equals(_addrGroupItem.get(groupField))) {
					
					addrGroupItem = _addrGroupItem;
					
					break;
				}
			}
		}
		
		return addrGroupItem;
	}
	
}
