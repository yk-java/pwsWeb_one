package com.glens.pwCloudOs.opsApp.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import org.springframework.dao.DataAccessException;
import org.springframework.data.mongodb.core.CollectionCallback;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;







import com.glens.eap.platform.framework.beans.PageBean;
import com.glens.pwCloudOs.opsApp.vo.LogAppVo;
import com.map.coords.algorithm.Converter;
import com.map.coords.algorithm.Point;
import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoException;
import com.mongodb.util.JSON;

public class LogAppDao {

	private MongoTemplate mongoTemplae;

	public MongoTemplate getMongoTemplae() {
		return mongoTemplae;
	}

	public void setMongoTemplae(MongoTemplate mongoTemplae) {
		this.mongoTemplae = mongoTemplae;
	}
	
	
	public List<LogAppVo> getList(String companyCode,String unitCode,String employeeCode,String accountCode,String logFromTime,String logToTime,int appType,int logType){
		Query query = new Query();
		Criteria criteria = Criteria.where("companyCode").is(companyCode);
		if(unitCode!=null && !unitCode.equals("")){
			criteria.and("unitCode").is(unitCode);
		}
		if(employeeCode!=null && !employeeCode.equals("")){
			criteria.and("employeeCode").regex(employeeCode);
		}
		
		if(logFromTime!=null && !logFromTime.equals("") && logToTime!=null && !logToTime.equals("")){
			criteria.and("logTime").gte(logFromTime).lte(logToTime);
		}
		//if(appType!=null){
		if(appType!=0){
			criteria.and("appType").is(appType);
		}
		
		//criteria.and("logType").is(logType);
		//}
		
		query.addCriteria(criteria);
		List<LogAppVo> res=new ArrayList<LogAppVo>();
		try {
			res = this.mongoTemplae.find(query, LogAppVo.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}
	
	//手机日志 分页显示
	public PageBean getPageBean(final String params, final Object searchName, final int currentPage, final int perpage,final String starttime,final String endtime) {
		
		PageBean page = null;
		
		try {
			
			page = this.mongoTemplae.execute("LOG_APP", new CollectionCallback<PageBean>() {
				
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
						values.add(new BasicDBObject("logContent", pattern));
						values.add(new BasicDBObject("employeeCode", pattern));
						query.put("$or", values);
					}
					
					DBObject dbo = new BasicDBObject();
					if(starttime!=null && !starttime.equals("") && endtime!=null && !endtime.equals("")){
						//System.out.println(starttime1+" ," +endtime1);
						dbo.put("$gte", starttime);
						dbo.put("$lte", endtime);
						query.put("logTime",dbo);
					}
					
					query.removeField("employeeCode");
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
						
						
						orderBy.put("logTime", 1);
						int firstResult = pageBean.getPerpage() * (pageBean.getCurrentPage() - 1);
						DBCursor cur = collection.find(query).sort(orderBy).skip(firstResult).limit(pageBean.getPerpage());
						Point point = null;
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
			//logger.error("获取mongodb总控表清单出错", e);
		}
		return page;
	}
	
	public boolean addLog(LogAppVo vo){
		try {
			this.mongoTemplae.insert(vo);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}
	}
	
	
}
