package com.glens.pwCloudOs.pm.taskMgr.task.dao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import org.springframework.dao.DataAccessException;
import org.springframework.data.mongodb.core.CollectionCallback;
import org.springframework.data.mongodb.core.MongoTemplate;

import com.glens.eap.platform.framework.beans.PageBean;
import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoException;
import com.mongodb.WriteResult;
import com.mongodb.util.JSON;

public class TaskDao {
	private MongoTemplate mongoTemplae;

	public MongoTemplate getMongoTemplae() {
		return mongoTemplae;
	}

	public void setMongoTemplae(MongoTemplate mongoTemplae) {
		this.mongoTemplae = mongoTemplae;
	}
	public int batchSave(final List<DBObject> vals){
		Integer rows=this.mongoTemplae.execute("PM_TASK", new CollectionCallback<Integer>(){

			@Override
			public Integer doInCollection(DBCollection collection)
					throws MongoException, DataAccessException {
				WriteResult writeRes = collection.insert(vals);
				int rows= writeRes.getN();
				return rows;
			}

		});
		return rows;
	}
	/* 运维人员手机端待办任务查看 */
	public List<Map<String, Object>> getUserTask(final Map<String, Object> params){
		List<Map<String, Object>> res = this.mongoTemplae.execute("PM_TASK", new CollectionCallback<List<Map<String, Object>>>(){

			@Override
			public List<Map<String, Object>> doInCollection(
					DBCollection collection) throws MongoException,
					DataAccessException {
				/*
				* 运维人员手机端待办任务查看

				db.getCollection('PM_TASK').group({
				    key:{"TERMINAL_ID":1, "TERMINAL_LOC":1, "FAULT_TYPE":1},
				    cond:{
				        "taskStatus" : 2,
				        "employeeCode":""
				    },
				    reduce:function(curr, result){
				        result.total+=1;
				    },
				    initial:{total:0}
				});

				*/
				List<Map<String, Object>> res = new ArrayList<Map<String, Object>>();
				DBObject key = new BasicDBObject();
				key.put("TERMINAL_ID", 1);
				key.put("TERMINAL_LOC", 1);
				key.put("FAULT_TYPE", 1);
				
				DBObject cond = new BasicDBObject();
				cond.put("taskStatus", 2);
				cond.put("employeeCode", params.get("employeeCode"));// 员工编号
				
				
				String searchValue = (String)params.get("searchName");
				if (searchValue != null && !"".equals(searchValue)) {
					
					Pattern pattern = Pattern.compile("^.*" + searchValue + ".*$", Pattern.CASE_INSENSITIVE);
					BasicDBList values = new BasicDBList();
					values.add(new BasicDBObject("TERMINAL_LOC", pattern));
					values.add(new BasicDBObject("HOUSEHOLD_ADDR", pattern));
					cond.put("$or", values);
				}
				
				DBObject initial = new BasicDBObject();
				initial.put("total", 0);
				
				String reduce="function(curr, result){result.total+=1;}";
				BasicDBList groupResult = (BasicDBList) collection.group(key, cond, initial, reduce);
				if (groupResult != null && groupResult.size() > 0) {
					for (int i = 0;i < groupResult.size();i++) {
						DBObject item = (DBObject) groupResult.get(i);
						res.add(item.toMap());
					}
				}
				return res;
			}
			
		});
		return res;
	}
	/* 运维人员手机端待办任务明细列表 */
	public List<Map<String, Object>> getUserTaskDetail(final Map<String, Object> params){
		List<Map<String, Object>> res = this.mongoTemplae.execute("PM_TASK", new CollectionCallback<List<Map<String, Object>>>(){

			@Override
			public List<Map<String, Object>> doInCollection(
					DBCollection collection) throws MongoException,
					DataAccessException {
				/*
				* 运维人员手机端待办任务明细列表
				
				db.getCollection('PM_TASK').find({
				    "TERMINAL_ID" : "05-3519-3573",
				    "FAULT_TYPE" : "抄表故障",
				    "taskStatus" : 2,
				    "employeeCode":""
				});
				
				 */
				List<Map<String, Object>> res = new ArrayList<Map<String, Object>>();
				DBObject query = new BasicDBObject();
				query.put("TERMINAL_ID", params.get("TERMINAL_ID"));
				query.put("FAULT_TYPE", params.get("FAULT_TYPE"));
				query.put("taskStatus", 2);
				
				
				query.put("employeeCode", params.get("employeeCode"));
				
				String searchValue = (String)params.get("searchName");
				if (searchValue != null && !"".equals(searchValue)) {
					
					Pattern pattern = Pattern.compile("^.*" + searchValue + ".*$", Pattern.CASE_INSENSITIVE);
					BasicDBList values = new BasicDBList();
					values.add(new BasicDBObject("TERMINAL_LOC", pattern));
					values.add(new BasicDBObject("HOUSEHOLD_ADDR", pattern));
					query.put("$or", values);
				}
				DBCursor cursor = collection.find(query);
				while(cursor.hasNext()){
					DBObject obj = cursor.next();
					try {
						String endTime1 = (String)obj.get("endTime1");
						SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
						Date endTime1_d = sdf.parse(endTime1);
						/*
						 * now-t>3     t<now-3 // 超期 
						 * now-t>=1    t<=now-1 // 告警
						 * now-t<=0    t>=now // 正常
						 */
						Calendar calendar = Calendar.getInstance();
						calendar.add(Calendar.DATE, -3);
						String date1 = sdf.format(calendar.getTime());
						Date date1_d = sdf.parse(date1);
						if(endTime1_d.getTime()<date1_d.getTime()){
							// 超期
							obj.put("timeoutStatus", 3);
						}else{
							calendar = Calendar.getInstance();
							calendar.add(Calendar.DATE, -1);
							String date2 = sdf.format(calendar.getTime());
							Date date2_d = sdf.parse(date2);
							if(endTime1_d.getTime()<=date2_d.getTime()){
								// 告警
								obj.put("timeoutStatus", 2);
							}else{
								String date3 = sdf.format(new Date());
								Date date3_d = sdf.parse(date3);
								if(endTime1_d.getTime()>=date3_d.getTime()){
									// 正常
									obj.put("timeoutStatus", 1);
								}
							}
						}
					} catch (ParseException e) {
						e.printStackTrace();
					}
					res.add(obj.toMap());
				}
				return res;
			}
			
		});
		return res;
	}
	/*
	 * 统计超时数
	 * */
	public Map<String, Integer> findTaskCount(Map<String, Object> params){
		Map<String, Integer> map = new HashMap<String, Integer>();
		int outtimeCnt = this.findOuttimeCount(params);
		map.put("outtimeCnt", outtimeCnt);
		int warningCnt = this.findWarningCount(params);
		map.put("warningCnt", warningCnt);
		int normalCnt = this.findNormalCount(params);
		map.put("normalCnt", normalCnt);
		return map;
	}
	
	public Map<String, Integer> findTaskCountInRange(Map<String, Object> params){
		Map<String, Integer> map = new HashMap<String, Integer>();
		int outtimeCnt = this.findOuttimeCountInRange(params);
		map.put("outtimeCnt", outtimeCnt);
		int warningCnt = this.findWarningCountInRange(params);
		map.put("warningCnt", warningCnt);
		int normalCnt = this.findNormalCountInRange(params);
		map.put("normalCnt", normalCnt);
		return map;
	}
	/*

	now-t>3     t<now-3 // 超时 
	now-t>=1    t<=now-1 // 告警
	now-t<=0    t>=now // 正常

	db.getCollection('PM_TASK').count({"taskStatus" : 2,
	        "employeeCode":"",
	        "endTime1":{$lt:'2016-09-25'}});
	db.getCollection('PM_TASK').count({"taskStatus" : 2,
	        "employeeCode":"",
	        "endTime1":{$lte:'2016-09-27'}});
	db.getCollection('PM_TASK').count({"taskStatus" : 2,
	        "employeeCode":"",
	        "endTime1":{$gte:'2016-09-28'}});
	*/
	public int findOuttimeCount(final Map<String, Object> params){
		int cnt = this.mongoTemplae.execute("PM_TASK", new CollectionCallback<Integer>(){

			@Override
			public Integer doInCollection(DBCollection collection)
					throws MongoException, DataAccessException {
				
				DBObject query = new BasicDBObject();
				query.put("taskStatus", 2);
				query.put("employeeCode", params.get("employeeCode"));
				DBObject time = new BasicDBObject();
				Calendar calendar = Calendar.getInstance();
				calendar.add(Calendar.DATE, -3);
				String outTimeLine="";
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				outTimeLine = sdf.format(calendar.getTime());
				time.put("$lt", outTimeLine);
				query.put("endTime1", time);
				Long cnt = collection.count(query);
				return cnt.intValue();
			}
			
		});
		return cnt;
	}
	public int findWarningCount(final Map<String, Object> params){
		int cnt = this.mongoTemplae.execute("PM_TASK", new CollectionCallback<Integer>(){

			@Override
			public Integer doInCollection(DBCollection collection)
					throws MongoException, DataAccessException {
				
				DBObject query = new BasicDBObject();
				query.put("taskStatus", 2);
				query.put("employeeCode", params.get("employeeCode"));
				DBObject time = new BasicDBObject();
				Calendar calendar = Calendar.getInstance();
				calendar.add(Calendar.DATE, -1);
				String outTimeLine="";
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				outTimeLine = sdf.format(calendar.getTime());
				calendar = Calendar.getInstance();
				calendar.add(Calendar.DATE, -3);
				String outTimeLine2 = sdf.format(calendar.getTime());
				BasicDBList condSec = new BasicDBList();
				condSec.add(new BasicDBObject("endTime1", new BasicDBObject("$lte", outTimeLine)));
				condSec.add(new BasicDBObject("endTime1", new BasicDBObject("$gte", outTimeLine2)));
				query.put("$and", condSec);
				Long cnt = collection.count(query);
				return cnt.intValue();
			}
			
		});
		return cnt;
	}
	public int findNormalCount(final Map<String, Object> params){
		int cnt = this.mongoTemplae.execute("PM_TASK", new CollectionCallback<Integer>(){

			@Override
			public Integer doInCollection(DBCollection collection)
					throws MongoException, DataAccessException {
				
				DBObject query = new BasicDBObject();
				query.put("taskStatus", 2);
				query.put("employeeCode", params.get("employeeCode"));
				DBObject time = new BasicDBObject();
				String outTimeLine="";
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				outTimeLine = sdf.format(new Date());
				time.put("$gte", outTimeLine);
				query.put("endTime1", time);
				Long cnt = collection.count(query);
				return cnt.intValue();
			}
			
		});
		return cnt;
	}
	
	public int findOuttimeCountInRange(final Map<String, Object> params){
		int cnt = this.mongoTemplae.execute("PM_TASK", new CollectionCallback<Integer>(){

			@Override
			public Integer doInCollection(DBCollection collection)
					throws MongoException, DataAccessException {
				
				DBObject query = new BasicDBObject();
				query.put("taskStatus", 2);
				query.put("employeeCode", params.get("employeeCode"));
			    query.put("TERMINAL_ID", params.get("TERMINAL_ID"));
				query.put("FAULT_TYPE", params.get("FAULT_TYPE"));
				
				DBObject time = new BasicDBObject();
				Calendar calendar = Calendar.getInstance();
				calendar.add(Calendar.DATE, -3);
				String outTimeLine="";
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				outTimeLine = sdf.format(calendar.getTime());
				time.put("$lt", outTimeLine);
				query.put("endTime1", time);
				Long cnt = collection.count(query);
				return cnt.intValue();
			}
			
		});
		return cnt;
	}
	public int findWarningCountInRange(final Map<String, Object> params){
		int cnt = this.mongoTemplae.execute("PM_TASK", new CollectionCallback<Integer>(){

			@Override
			public Integer doInCollection(DBCollection collection)
					throws MongoException, DataAccessException {
				
				DBObject query = new BasicDBObject();
				query.put("taskStatus", 2);
				query.put("employeeCode", params.get("employeeCode"));
				query.put("TERMINAL_ID", params.get("TERMINAL_ID"));
				query.put("FAULT_TYPE", params.get("FAULT_TYPE"));
				
				DBObject time = new BasicDBObject();
				Calendar calendar = Calendar.getInstance();
				calendar.add(Calendar.DATE, -1);
				String outTimeLine="";
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				outTimeLine = sdf.format(calendar.getTime());
				time.put("$lte", outTimeLine);
				query.put("endTime1", time);
				Long cnt = collection.count(query);
				return cnt.intValue();
			}
			
		});
		return cnt;
	}
	public int findNormalCountInRange(final Map<String, Object> params){
		int cnt = this.mongoTemplae.execute("PM_TASK", new CollectionCallback<Integer>(){

			@Override
			public Integer doInCollection(DBCollection collection)
					throws MongoException, DataAccessException {
				
				DBObject query = new BasicDBObject();
				query.put("taskStatus", 2);
				query.put("employeeCode", params.get("employeeCode"));
				query.put("TERMINAL_ID", params.get("TERMINAL_ID"));
				query.put("FAULT_TYPE", params.get("FAULT_TYPE"));
				
				DBObject time = new BasicDBObject();
				String outTimeLine="";
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				outTimeLine = sdf.format(new Date());
				time.put("$gte", outTimeLine);
				query.put("endTime1", time);
				Long cnt = collection.count(query);
				return cnt.intValue();
			}
			
		});
		return cnt;
	}
	public int saveTaskOpInfo(final Map<String, Object> params) {
		int rows = this.mongoTemplae.execute("PM_TASK", new CollectionCallback<Integer>(){

			@Override
			public Integer doInCollection(DBCollection collection)
					throws MongoException, DataAccessException {
				DBObject query = new BasicDBObject();
				query.put("taskCode", params.get("taskCode"));
				
				DBObject setData = new BasicDBObject();
				DBObject data = new BasicDBObject();
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				data.put("endTime2", sdf.format(new Date()));
				data.put("taskResultFlag", params.get("taskResultFlag"));
				data.put("taskStatus", 3);
				data.put("faultDesc", params.get("faultDesc"));
				data.put("sceneDesc", params.get("sceneDesc"));
				data.put("deviceFaultType", params.get("deviceFaultType"));
				data.put("deviceFaultDetail", params.get("deviceFaultDetail"));
				data.put("pic1", params.get("pic1"));
				data.put("pic2", params.get("pic2"));
				data.put("pic3", params.get("pic3"));
				
				//data.put("", params.get(""));
				setData.put("$set", data);
				WriteResult wr = collection.update(query, setData);
				return wr.getN();
			}
			
		});
		return rows;
	}
	public PageBean getList(final String params, final Object searchName,final String starttime1,final String endtime1,final int currentPage, final int perpage) {

		PageBean page=null;

		try {

			page = this.mongoTemplae.execute("PM_TASK", new CollectionCallback<PageBean>() {

				/**

				 * <p>Title: doInCollection</p>

				 * <p>Description: </p>

				 * @param collection
				 * @return
				 * @throws MongoException
				 * @throws DataAccessException

				 * @see org.springframework.data.mongodb.core.CollectionCallback#doInCollection(com.mongodb.DBCollection)

				 **/
				public PageBean doInCollection(DBCollection collection)
						throws MongoException, DataAccessException {
					// TODO Auto-generated method stub
					DBObject query = (DBObject)JSON.parse(params);
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

								values.add(new BasicDBObject("taskName", pattern));
								values.add(new BasicDBObject("employeeName", pattern));

								query.put("$or", values);
							}
						}

					}

					
					
					DBObject dbo = new BasicDBObject();
					if(starttime1!=null && !starttime1.equals("") && endtime1!=null && !endtime1.equals("")){
						System.out.println(starttime1+" ," +endtime1);
						dbo.put("$gte", starttime1);
						dbo.put("$lte", endtime1);
						query.put("startTime1",dbo);
					}




					//orderBy.put("deviceObjName", 1);
					//int firstResult = pageBean.getPerpage() * (pageBean.getCurrentPage() - 1);
					
					
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
							try {
								DBObject obj = cur.next();
								
								//String overdueStatus=(String)obj.get("overdueStatus");
								//System.out.println(obj.get("overdueStatus"));
								if(obj.get("taskResultFlag")!=null&&!obj.get("taskResultFlag").toString().equals("1")){
									System.out.println(obj.get("taskResultFlag").toString());
								}
								
								if(obj.get("taskResultFlag")==null){
									obj.put("taskResultFlag",2);
								}
								
								
								/*
								 * now-t>3     t<now-3 // 超期 
								 * now-t>=1    t<=now-1 // 告警
								 * now-t<=0    t>=now // 正常
								 */
								String endTime1 = (String)obj.get("endTime1");//计划结束日期
								String endTime2 = (String)obj.get("endTime2");//实际结束日期
								
								System.out.println(endTime2);
								
								SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
								Date endTime1_d = sdf.parse(endTime1);
								
								
								
								
								
								
								
								if(endTime2!=null && !endTime2.equals("")){
									Date endTime22 = sdf.parse(endTime2);
									Calendar calendar = Calendar.getInstance();
									calendar.setTime(endTime22);
									
									
									//System.out.println(Calendar.DATE);
									calendar.add(Calendar.DATE, -3);
									String date1 = sdf.format(calendar.getTime());
									Date date1_d = sdf.parse(date1);
									
									if(endTime1_d.getTime()<date1_d.getTime()){
										// 超期
										obj.put("timeoutStatus", 3);
									}else{
										
										obj.put("timeoutStatus", 1);
										
										
									}
									
									
								}else{
									Calendar calendar = Calendar.getInstance();
									//System.out.println(Calendar.DATE);
									calendar.add(Calendar.DATE, -3);
									String date1 = sdf.format(calendar.getTime());
									Date date1_d = sdf.parse(date1);
									if(endTime1_d.getTime()<date1_d.getTime()){
										// 超期
										obj.put("timeoutStatus", 3);
									}else{
										calendar = Calendar.getInstance();
										calendar.add(Calendar.DATE, -1);
										String date2 = sdf.format(calendar.getTime());
										Date date2_d = sdf.parse(date2);
										if(endTime1_d.getTime()<=date2_d.getTime()){
											// 告警
											obj.put("timeoutStatus", 2);
										}else{
											String date3 = sdf.format(new Date());
											Date date3_d = sdf.parse(date3);
											if(endTime1_d.getTime()>=date3_d.getTime()){
												// 正常
												obj.put("timeoutStatus", 1);
											}
										}
									}
								}
								
								
								

								
								list.add(obj.toMap());
							} catch (Exception e) {
								// TODO: handle exception
							}
							
						}//while 结束
						
						pageBean.setList(list);
						
					}

					return pageBean;
				}
			});
		}
		catch (Exception e) {
			// TODO: handle exception
			//	logger.error("获取mongodb总控表清单出错", e);
		}

		return page;
	}


	public Map<String, Object> getTask(final String taskCode) {

		Map<String, Object> mctDeviceMap = null;

		try {

			mctDeviceMap = this.mongoTemplae.execute("PM_TASK", new CollectionCallback<Map<String, Object>>() {

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
					BasicDBObject query = new BasicDBObject();
					query.put("taskCode", taskCode);
					DBCursor cur = collection.find(query);
					Map<String, Object> deviceMap = null;
					if (cur.hasNext()) {

						DBObject obj = cur.next();
						
						if(obj.get("taskResultFlag")!=null&&!obj.get("taskResultFlag").toString().equals("1")){
							System.out.println(obj.get("taskResultFlag").toString());
						}
						
						if(obj.get("taskResultFlag")==null){
							obj.put("taskResultFlag",2);
						}
						
						
						String endTime1 = (String)obj.get("endTime1");
						SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
						Date endTime1_d;
						try {
							endTime1_d = sdf.parse(endTime1);
							Calendar calendar = Calendar.getInstance();
							calendar.add(Calendar.DATE, -3);
							String date1 = sdf.format(calendar.getTime());
							Date date1_d = sdf.parse(date1);
							if(endTime1_d.getTime()<date1_d.getTime()){
								// 超期
								obj.put("timeoutStatus", 3);
							}else{
								calendar = Calendar.getInstance();
								calendar.add(Calendar.DATE, -1);
								String date2 = sdf.format(calendar.getTime());
								Date date2_d = sdf.parse(date2);
								if(endTime1_d.getTime()<=date2_d.getTime()){
									// 告警
									obj.put("timeoutStatus", 2);
								}else{
									String date3 = sdf.format(new Date());
									Date date3_d = sdf.parse(date3);
									if(endTime1_d.getTime()>=date3_d.getTime()){
										// 正常
										obj.put("timeoutStatus", 1);
									}
								}
							}
						} catch (ParseException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						deviceMap = obj.toMap();
					}

					return deviceMap;
				}
			});
		}
		catch (Exception e) {
			// TODO: handle exception
			//logger.error("获取mongodb总控表清单出错", e);
		}

		return mctDeviceMap;
	}
	
	//任务派单
		public int updateTask(final Map<String, Object> params) {

			return this.mongoTemplae.execute("PM_TASK", new CollectionCallback<Integer>() {

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
					query.put("taskCode", params.get("taskCode"));
					
					DBObject proDeviceObj = collection.findOne(query);
					if (proDeviceObj != null) {

						proDeviceObj.put("employeeCode", params.get("employeeCode"));
						proDeviceObj.put("employeeName", params.get("employeeName"));
						proDeviceObj.put("taskStatus", 2);

						WriteResult result = collection.update(query, proDeviceObj);

						return result.getN();
					}

					return 0;
				}
			});
		}

}
