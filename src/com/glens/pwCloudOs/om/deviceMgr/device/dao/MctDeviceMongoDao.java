/**
 * @Title: MctDeviceMongoDao.java
 * @Package com.glens.pwCloudOs.om.deviceMgr.device.dao
 * @Description: TODO
 * Copyright: Copyright (c) 2016 
 * Company:南京量为石信息科技有限公司
 * @author 
 * @date 2016-8-22 下午5:27:33
 * @version V1.0
 */

package com.glens.pwCloudOs.om.deviceMgr.device.dao;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.dao.DataAccessException;
import org.springframework.data.mongodb.core.CollectionCallback;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;

import com.glens.eap.platform.core.utils.DateTimeUtil;
import com.glens.eap.platform.core.utils.StringUtil;
import com.glens.eap.platform.framework.beans.PageBean;
import com.glens.eap.platform.framework.beans.UserToken;
import com.map.coords.algorithm.Point;
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

public class MctDeviceMongoDao {

	private static final String G_CONVERT_FILTER = "量为石";

	private static Log logger = LogFactory.getLog(MctDeviceMongoDao.class);

	private MongoTemplate mongoTemplate;

	public PageBean getMctDevicePage(final String params,
			final Object searchName, final int currentPage, final int perpage) {

		PageBean page = null;

		try {

			page = this.mongoTemplate.execute("PM_MCT_LIST",
					new CollectionCallback<PageBean>() {

						/**
						 * 
						 * <p>
						 * Title: doInCollection
						 * </p>
						 * 
						 * <p>
						 * Description:
						 * </p>
						 * 
						 * @param collection
						 * @return
						 * @throws MongoException
						 * @throws DataAccessException
						 * 
						 * @see org.springframework.data.mongodb.core.CollectionCallback#doInCollection(com.mongodb.DBCollection)
						 **/

						@Override
						public PageBean doInCollection(DBCollection collection)
								throws MongoException, DataAccessException {
							// TODO Auto-generated method stub
							DBObject query = (DBObject) JSON.parse(params);
							String startTime = (String) query.get("startTime");
							String endTime = (String) query.get("endTime");
							String qcStartTime = (String) query
									.get("qcStartTime");
							String qcEndTime = (String) query.get("qcEndTime");
							query.removeField("startTime");
							query.removeField("endTime");
							query.removeField("qcStartTime");
							query.removeField("qcEndTime");

							if (query.get("QUALITY_AUDIT") != null
									&& query.get("QUALITY_AUDIT").toString()
											.length() == 0) {
								query.removeField("QUALITY_AUDIT");
							}
							BasicDBList $andVals = new BasicDBList();
							if (query.get("hasPic") != null
									&& query.get("hasPic").toString().length() > 0) {
								String hasPic = (String) query.get("hasPic");
								query.removeField("hasPic");
								query.put("pic", hasPic);
							}
							if ("0".equals((String) query.get("hasPosition"))) {
								query.removeField("hasPosition");
								DBObject obj = (DBObject) JSON
										.parse("{$and:[{$or:[{'rx':{$exists:false}},{'rx':{$eq:0}}]},{$or:[{'ry':{$exists:false}},{'ry':{$eq:0}}]}]}");
								$andVals.add(obj);
							} else if ("1".equals((String) query
									.get("hasPosition"))) {
								query.removeField("hasPosition");
								DBObject obj = (DBObject) JSON
										.parse("{$and:[{$or:[{'rx':{$exists:true}},{'rx':{$ne:0}}]},{$or:[{'ry':{$exists:true}},{'ry':{$ne:0}}]}]}");
								$andVals.add(obj);
							}
							if (searchName != null && !"".equals(searchName)) {

								String[] packet = searchName.toString().split(
										";");
								if (packet.length > 1) {

									String[] fields = packet[0].split(",");
									String searchValue = packet[1];
									if (searchValue != null
											&& !"".equals(searchValue)) {

										Pattern pattern = Pattern.compile("^.*"
												+ searchValue + ".*$",
												Pattern.CASE_INSENSITIVE);
										BasicDBList values = new BasicDBList();
										for (String field : fields) {

											values.add(new BasicDBObject(field,
													pattern));
										}

										values.add(new BasicDBObject(
												"deviceObjName", pattern));
										values.add(new BasicDBObject(
												"deviceObjCode", pattern));
										values.add(new BasicDBObject(
												"CREATER_NAME", pattern));
										values.add(new BasicDBObject(
												"AUDITOR_NAME", pattern));
										$andVals.add(new BasicDBObject("$or",
												values));
									}
								}
							}
							if (startTime != null
									&& startTime.length() > 0
									&& (endTime == null || endTime.length() == 0)) {
								query.put("SYS_CREATE_TIME", new BasicDBObject(
										"$gt", startTime + " 00:00:00"));
							}
							if ((startTime == null || startTime.length() == 0)
									&& endTime != null && endTime.length() > 0) {
								query.put("SYS_CREATE_TIME", new BasicDBObject(
										"$lt", endTime + " 23:59:59"));
							}
							if (startTime != null && startTime.length() > 0
									&& endTime != null && endTime.length() > 0) {
								DBObject createTime = new BasicDBObject("$gt",
										startTime + " 00:00:00");
								createTime.put("$lt", endTime + " 23:59:59");
								query.put("SYS_CREATE_TIME", createTime);
							}
							if (qcStartTime != null
									&& qcStartTime.length() > 0
									&& (qcEndTime == null || qcEndTime.length() == 0)) {
								query.put("qualityCheckRecord.checkTime",
										new BasicDBObject("$gt", qcStartTime
												+ " 00:00:00"));
							}
							if ((qcStartTime == null || qcStartTime.length() == 0)
									&& qcEndTime != null
									&& qcEndTime.length() > 0) {
								query.put("qualityCheckRecord.checkTime",
										new BasicDBObject("$lt", qcEndTime
												+ " 23:59:59"));
							}
							if (qcStartTime != null && qcStartTime.length() > 0
									&& qcEndTime != null
									&& qcEndTime.length() > 0) {
								DBObject createTime = new BasicDBObject("$gt",
										qcStartTime + " 00:00:00");
								createTime.put("$lt", qcEndTime + " 23:59:59");
								query.put("qualityCheckRecord.checkTime",
										createTime);
							}
							if ($andVals.size() > 0) {
								query.put("$and", $andVals);
							}
							Long totalCount = collection.count(query);
							PageBean pageBean = null;
							if (totalCount < 1) {
								pageBean = new PageBean();
								pageBean.setList(new ArrayList());
							} else {
								pageBean = new PageBean(totalCount.intValue(),
										currentPage, perpage);
								List<Map> list = new ArrayList<Map>();
								DBObject orderBy = new BasicDBObject();
								orderBy.put("SYS_CREATE_TIME", -1);
								int firstResult = pageBean.getPerpage()
										* (pageBean.getCurrentPage() - 1);
								DBCursor cur = collection.find(query)
										.sort(orderBy).skip(firstResult)
										.limit(pageBean.getPerpage());
								Point point = null;
								while (cur.hasNext()) {

									DBObject obj = cur.next();
									// if (obj.containsField("x") &&
									// obj.containsField("y")
									// && !"".equals(obj.get("x")) &&
									// !"".equals(obj.get("y"))) {
									//
									// double x =
									// Double.parseDouble(obj.get("x").toString());
									// double y =
									// Double.parseDouble(obj.get("y").toString());
									// if (x > 0 && y > 0) {
									//
									// point = Converter.WGS84ToBD09(x, y);
									// if (point != null) {
									//
									// obj.put("x", point.getLongitude());
									// obj.put("y", point.getLatitude());
									// }
									// }
									//
									// }
									list.add(obj.toMap());
								}

								pageBean.setList(list);
							}

							return pageBean;
						}
					});
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("获取mongodb总控表清单出错", e);
		}
		return page;
	}

	// 数据质量分析

	public List<Map<String, Object>> dataQuality(final String params,
			final Object searchName, final int currentPage, final int perpage) {

		List<Map<String, Object>> page = null;

		try {

			page = this.mongoTemplate.execute("PM_MCT_LIST",
					new CollectionCallback<List<Map<String, Object>>>() {

						/**
						 * 
						 * <p>
						 * Title: doInCollection
						 * </p>
						 * 
						 * <p>
						 * Description:
						 * </p>
						 * 
						 * @param collection
						 * @return
						 * @throws MongoException
						 * @throws DataAccessException
						 * 
						 * @see org.springframework.data.mongodb.core.CollectionCallback#doInCollection(com.mongodb.DBCollection)
						 **/

						@Override
						public List<Map<String, Object>> doInCollection(
								DBCollection collection) throws MongoException,
								DataAccessException {
							// TODO Auto-generated method stub
							DBObject query = (DBObject) JSON.parse(params);
							String startTime = (String) query.get("startTime");
							String endTime = (String) query.get("endTime");
							query.removeField("startTime");
							query.removeField("endTime");
							if (query.get("QUALITY_AUDIT") != null
									&& query.get("QUALITY_AUDIT").toString()
											.length() == 0) {
								query.removeField("QUALITY_AUDIT");
							}
							if (searchName != null && !"".equals(searchName)) {

								String[] packet = searchName.toString().split(
										";");
								if (packet.length > 1) {

									String[] fields = packet[0].split(",");
									String searchValue = packet[1];
									if (searchValue != null
											&& !"".equals(searchValue)) {

										Pattern pattern = Pattern.compile("^.*"
												+ searchValue + ".*$",
												Pattern.CASE_INSENSITIVE);
										BasicDBList values = new BasicDBList();
										for (String field : fields) {

											values.add(new BasicDBObject(field,
													pattern));
										}

										values.add(new BasicDBObject(
												"deviceObjName", pattern));
										values.add(new BasicDBObject(
												"deviceObjCode", pattern));
										values.add(new BasicDBObject(
												"CREATER_NAME", pattern));
										values.add(new BasicDBObject(
												"AUDITOR_NAME", pattern));
										query.put("$or", values);
									}
								}
							}
							if (startTime != null
									&& startTime.length() > 0
									&& (endTime == null || endTime.length() == 0)) {
								query.put("SYS_CREATE_TIME", new BasicDBObject(
										"$gt", startTime + " 00:00:00"));
							}
							if ((startTime == null || startTime.length() == 0)
									&& endTime != null && endTime.length() > 0) {
								query.put("SYS_CREATE_TIME", new BasicDBObject(
										"$lt", endTime + " 23:59:59"));
							}
							if (startTime != null && startTime.length() > 0
									&& endTime != null && endTime.length() > 0) {
								BasicDBList values = new BasicDBList();
								values.add(new BasicDBObject("SYS_CREATE_TIME",
										new BasicDBObject("$gt", startTime
												+ " 00:00:00")));
								values.add(new BasicDBObject("SYS_CREATE_TIME",
										new BasicDBObject("$lt", endTime
												+ " 23:59:59")));
								query.put("$and", values);
							}

							BasicDBObject key = new BasicDBObject();
							// key.put("proNo", true);
							key.put("mctCode", true);
							// key.put("proName", true);
							BasicDBObject initial = new BasicDBObject();
							initial.put("pic", 0);
							initial.put("pic1", 0);
							initial.put("coordinate", 0);
							initial.put("coordinate1", 0);
							initial.put("examine", 0);
							initial.put("examine1", 0);
							String reduce = "function(doc, prev) {if (doc.pic == '1') prev.pic++;"
									+ "if (doc.pic == '0') prev.pic1++;"
									+ "if (doc.x == '' || doc.x=='0') prev.coordinate++; if (doc.x!='' && doc.x!='0') prev.coordinate1++;"
									+ "if (doc.QUALITY_AUDIT=='合格') prev.examine++;if(doc.QUALITY_AUDIT=='不合格') prev.examine1++;";

							reduce += "}";

							BasicDBList groupResult = (BasicDBList) collection
									.group(key, query, initial, reduce);

							List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
							if (groupResult != null && groupResult.size() > 0) {

								for (int i = 0; i < groupResult.size(); i++) {
									DBObject item = (DBObject) groupResult
											.get(i);
									list.add(item.toMap());
								}
							} else {
								Map m = new HashMap();
								m.put("pic", 0);
								m.put("pic1", 0);
								m.put("coordinate", 0);
								m.put("coordinate1", 0);
								m.put("examine", 0);
								m.put("examine1", 0);
								list.add(m);
							}
							return list;
						}
					});
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("获取mongodb总控表清单出错", e);
		}
		return page;
	}

	// 手机端列表

	public PageBean getPhoneMctDevicePage(final String params,
			final Object searchName, final int currentPage, final int perpage) {

		PageBean page = null;

		try {

			page = this.mongoTemplate.execute("PM_MCT_LIST",
					new CollectionCallback<PageBean>() {

						/**
						 * 
						 * <p>
						 * Title: doInCollection
						 * </p>
						 * 
						 * <p>
						 * Description:
						 * </p>
						 * 
						 * @param collection
						 * @return
						 * @throws MongoException
						 * @throws DataAccessException
						 * 
						 * @see org.springframework.data.mongodb.core.CollectionCallback#doInCollection(com.mongodb.DBCollection)
						 **/

						@Override
						public PageBean doInCollection(DBCollection collection)
								throws MongoException, DataAccessException {
							// TODO Auto-generated method stub
							DBObject query = (DBObject) JSON.parse(params);
							if (searchName != null && !"".equals(searchName)) {

								String[] packet = searchName.toString().split(
										";");
								if (packet.length > 1) {

									String[] fields = packet[0].split(",");
									String searchValue = packet[1];
									if (searchValue != null
											&& !"".equals(searchValue)) {

										Pattern pattern = Pattern.compile("^.*"
												+ searchValue + ".*$",
												Pattern.CASE_INSENSITIVE);
										BasicDBList values = new BasicDBList();
										for (String field : fields) {

											values.add(new BasicDBObject(field,
													pattern));
										}

										values.add(new BasicDBObject(
												"deviceObjName", pattern));
										values.add(new BasicDBObject(
												"deviceObjCode", pattern));
										values.add(new BasicDBObject(
												"CREATER_NAME", pattern));
										values.add(new BasicDBObject(
												"AUDITOR_NAME", pattern));
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

								pageBean = new PageBean(totalCount.intValue(),
										currentPage, perpage);
								List<Map> list = new ArrayList<Map>();
								DBObject orderBy = new BasicDBObject();
								orderBy.put("deviceObjName", 1);
								int firstResult = pageBean.getPerpage()
										* (pageBean.getCurrentPage() - 1);
								// DBCursor cur =
								// collection.find(query).sort(orderBy).skip(firstResult).limit(pageBean.getPerpage());
								DBCursor cur = collection.find(query)
										.sort(orderBy).limit(100);
								Point point = null;
								while (cur.hasNext()) {

									DBObject obj = cur.next();
									// if (obj.containsField("x") &&
									// obj.containsField("y")
									// && !"".equals(obj.get("x")) &&
									// !"".equals(obj.get("y"))) {
									//
									// double x =
									// Double.parseDouble(obj.get("x").toString());
									// double y =
									// Double.parseDouble(obj.get("y").toString());
									// if (x > 0 && y > 0) {
									//
									// point = Converter.WGS84ToBD09(x, y);
									// if (point != null) {
									//
									// obj.put("x", point.getLongitude());
									// obj.put("y", point.getLatitude());
									// }
									// }
									//
									// }
									list.add(obj.toMap());
								}

								pageBean.setList(list);
							}

							return pageBean;
						}
					});
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("获取mongodb总控表清单出错", e);
		}
		return page;
	}

	public List exportImgs(final String params, final Object searchName,
			final int currentPage, final int perpage) {

		PageBean page = null;
		List list = null;
		try {

			list = this.mongoTemplate.execute("PM_MCT_LIST",
					new CollectionCallback<List>() {

						/**
						 * 
						 * <p>
						 * Title: doInCollection
						 * </p>
						 * 
						 * <p>
						 * Description:
						 * </p>
						 * 
						 * @param collection
						 * @return
						 * @throws MongoException
						 * @throws DataAccessException
						 * 
						 * @see org.springframework.data.mongodb.core.CollectionCallback#doInCollection(com.mongodb.DBCollection)
						 **/

						@Override
						public List doInCollection(DBCollection collection)
								throws MongoException, DataAccessException {
							// TODO Auto-generated method stub
							DBObject query = (DBObject) JSON.parse(params);
							String startTime = (String) query.get("startTime");
							String endTime = (String) query.get("endTime");
							String qcStartTime = (String) query
									.get("qcStartTime");
							String qcEndTime = (String) query.get("qcEndTime");
							query.removeField("startTime");
							query.removeField("endTime");
							query.removeField("qcStartTime");
							query.removeField("qcEndTime");

							if (query.get("QUALITY_AUDIT") != null
									&& query.get("QUALITY_AUDIT").toString()
											.length() == 0) {
								query.removeField("QUALITY_AUDIT");
							}
							BasicDBList $andVals = new BasicDBList();
							if (query.get("hasPic") != null
									&& query.get("hasPic").toString().length() > 0) {
								String hasPic = (String) query.get("hasPic");
								query.removeField("hasPic");
								query.put("pic", hasPic);
							}
							if ("0".equals((String) query.get("hasPosition"))) {
								query.removeField("hasPosition");
								DBObject obj = (DBObject) JSON
										.parse("{$and:[{$or:[{'rx':{$exists:false}},{'rx':{$eq:''}}]},{$or:[{'ry':{$exists:false}},{'ry':{$eq:''}}]}]}");
								$andVals.add(obj);
							} else if ("1".equals((String) query
									.get("hasPosition"))) {
								query.removeField("hasPosition");
								DBObject obj = (DBObject) JSON
										.parse("{$and:[{$or:[{'rx':{$exists:true}},{'rx':{$ne:''}}]},{$or:[{'ry':{$exists:true}},{'ry':{$ne:''}}]}]}");
								$andVals.add(obj);
							}
							if (searchName != null && !"".equals(searchName)) {

								String[] packet = searchName.toString().split(
										";");
								if (packet.length > 1) {

									String[] fields = packet[0].split(",");
									String searchValue = packet[1];
									if (searchValue != null
											&& !"".equals(searchValue)) {

										Pattern pattern = Pattern.compile("^.*"
												+ searchValue + ".*$",
												Pattern.CASE_INSENSITIVE);
										BasicDBList values = new BasicDBList();
										for (String field : fields) {

											values.add(new BasicDBObject(field,
													pattern));
										}

										values.add(new BasicDBObject(
												"deviceObjName", pattern));
										values.add(new BasicDBObject(
												"deviceObjCode", pattern));
										values.add(new BasicDBObject(
												"CREATER_NAME", pattern));
										values.add(new BasicDBObject(
												"AUDITOR_NAME", pattern));
										$andVals.add(new BasicDBObject("$or",
												values));
									}
								}
							}
							if (startTime != null
									&& startTime.length() > 0
									&& (endTime == null || endTime.length() == 0)) {
								query.put("SYS_CREATE_TIME", new BasicDBObject(
										"$gt", startTime + " 00:00:00"));
							}
							if ((startTime == null || startTime.length() == 0)
									&& endTime != null && endTime.length() > 0) {
								query.put("SYS_CREATE_TIME", new BasicDBObject(
										"$lt", endTime + " 23:59:59"));
							}
							if (startTime != null && startTime.length() > 0
									&& endTime != null && endTime.length() > 0) {
								BasicDBList values = new BasicDBList();
								values.add(new BasicDBObject("SYS_CREATE_TIME",
										new BasicDBObject("$gt", startTime
												+ " 00:00:00")));
								values.add(new BasicDBObject("SYS_CREATE_TIME",
										new BasicDBObject("$lt", endTime
												+ " 23:59:59")));
								$andVals.add(new BasicDBObject("$and", values));
							}
							if (qcStartTime != null
									&& qcStartTime.length() > 0
									&& (qcEndTime == null || qcEndTime.length() == 0)) {
								query.put("qualityCheckRecord.checkTime",
										new BasicDBObject("$gt", qcStartTime
												+ " 00:00:00"));
							}
							if ((qcStartTime == null || qcStartTime.length() == 0)
									&& qcEndTime != null
									&& qcEndTime.length() > 0) {
								query.put("qualityCheckRecord.checkTime",
										new BasicDBObject("$lt", qcEndTime
												+ " 23:59:59"));
							}
							if (qcStartTime != null && qcStartTime.length() > 0
									&& qcEndTime != null
									&& qcEndTime.length() > 0) {
								DBObject createTime = new BasicDBObject("$gt",
										qcStartTime + " 00:00:00");
								createTime.put("$lt", qcEndTime + " 23:59:59");
								query.put("qualityCheckRecord.checkTime",
										createTime);
							}
							if ($andVals.size() > 0) {
								query.put("$and", $andVals);
							}
							List<Map> list = new ArrayList<Map>();
							DBObject orderBy = new BasicDBObject();
							orderBy.put("deviceObjName", 1);
							// int firstResult = pageBean.getPerpage() *
							// (pageBean.getCurrentPage() - 1);
							DBCursor cur = collection.find(query).sort(orderBy);
							Point point = null;
							while (cur.hasNext()) {

								DBObject obj = cur.next();
								// if (obj.containsField("x") &&
								// obj.containsField("y")
								// && !"".equals(obj.get("x")) &&
								// !"".equals(obj.get("y"))) {
								//
								// double x =
								// Double.parseDouble(obj.get("x").toString());
								// double y =
								// Double.parseDouble(obj.get("y").toString());
								// if (x > 0 && y > 0) {
								//
								// point = Converter.WGS84ToBD09(x, y);
								// if (point != null) {
								//
								// obj.put("x", point.getLongitude());
								// obj.put("y", point.getLatitude());
								// }
								// }
								//
								// }
								list.add(obj.toMap());
							}

							return list;
						}
					});
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("获取mongodb总控表清单出错", e);
		}
		return list;
	}

	public int insertMctDevice(final String mctDeviceJson, final UserToken token) {

		int iCount = 0;

		try {

			iCount = this.mongoTemplate.execute("PM_MCT_LIST",
					new CollectionCallback<Integer>() {

						/**
						 * 
						 * <p>
						 * Title: doInCollection
						 * </p>
						 * 
						 * <p>
						 * Description:
						 * </p>
						 * 
						 * @param collection
						 * @return
						 * @throws MongoException
						 * @throws DataAccessException
						 * 
						 * @see org.springframework.data.mongodb.core.CollectionCallback#doInCollection(com.mongodb.DBCollection)
						 **/

						@Override
						public Integer doInCollection(DBCollection collection)
								throws MongoException, DataAccessException {
							// TODO Auto-generated method stub
							collection.getDB().requestStart();
							DBObject insertObj = (DBObject) JSON
									.parse(mctDeviceJson);
							insertObj.put("CREATER_CODE",
									token.getEmployeeCode());
							insertObj.put("CREATER_NAME",
									token.getEmployeeName());
							String date = null;
							try {
								date = DateTimeUtil.formatDate(new Date(),
										DateTimeUtil.FORMAT_1);
							} catch (ParseException e) {
								e.printStackTrace();
							}
							insertObj.put("SYS_CREATE_TIME", date);
							insertObj.put("pic", "0");
							List<DBObject> objList = new ArrayList<DBObject>();
							objList.add(insertObj);
							WriteResult result = collection.insert(objList);
							int count = result.getN();
							collection.getDB().requestDone();

							return count;
						}
					});
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("插入mongodb总控表清单出错", e);
		}

		return iCount;
	}

	// 判断设备名称是否

	public int deviceIsHave(final Map<String, Object> params) {

		int mctDeviceMap = 0;

		try {

			mctDeviceMap = this.mongoTemplate.execute("PM_MCT_LIST",
					new CollectionCallback<Integer>() {

						/**
						 * 
						 * <p>
						 * Title: doInCollection
						 * </p>
						 * 
						 * <p>
						 * Description:
						 * </p>
						 * 
						 * @param collection
						 * @return
						 * @throws MongoException
						 * @throws DataAccessException
						 * 
						 * @see org.springframework.data.mongodb.core.CollectionCallback#doInCollection(com.mongodb.DBCollection)
						 **/

						@Override
						public Integer doInCollection(DBCollection collection)
								throws MongoException, DataAccessException {
							// TODO Auto-generated method stub
							BasicDBObject query = new BasicDBObject();

							query.put("companyCode", params.get("companyCode"));
							query.put("deviceObjName",
									params.get("deviceObjName"));
							DBCursor cur = collection.find(query);
							int result = 0;
							if (cur.hasNext()) {

								result = 1;
							}

							return result;
						}
					});
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("获取mongodb总控表清单出错", e);
		}

		return mctDeviceMap;
	}

	public int updateMctDevice(final String mctDeviceJson, final UserToken token) {

		int iCount = 0;

		try {

			iCount = this.mongoTemplate.execute("PM_MCT_LIST",
					new CollectionCallback<Integer>() {

						/**
						 * 
						 * <p>
						 * Title: doInCollection
						 * </p>
						 * 
						 * <p>
						 * Description:
						 * </p>
						 * 
						 * @param collection
						 * @return
						 * @throws MongoException
						 * @throws DataAccessException
						 * 
						 * @see org.springframework.data.mongodb.core.CollectionCallback#doInCollection(com.mongodb.DBCollection)
						 **/

						@Override
						public Integer doInCollection(DBCollection collection)
								throws MongoException, DataAccessException {
							// TODO Auto-generated method stub
							DBObject updateObj = (DBObject) JSON
									.parse(mctDeviceJson);
							BasicDBObject query = new BasicDBObject();
							query.put("deviceObjCode",
									updateObj.get("deviceObjCode"));
							DBObject obj = collection.findOne(query);
							if (obj != null) {

								Iterator<String> it = updateObj.keySet()
										.iterator();
								while (it.hasNext()) {

									String key = it.next();
									obj.put(key, updateObj.get(key));
								}
							}
							obj.put("UPDATETOR_CODE", token.getEmployeeCode());
							obj.put("UPDATETOR", token.getEmployeeName());
							String date = null;
							try {
								date = DateTimeUtil.formatDate(new Date(),
										DateTimeUtil.FORMAT_1);
							} catch (ParseException e) {
								e.printStackTrace();
							}
							obj.put("SYS_UPDATE_TIME", date);

							if (updateObj.get("QUALITY_AUDIT") != null
									&& updateObj.get("QUALITY_AUDIT")
											.toString().length() > 0) {
								obj.put("QUALITY_AUDIT",
										updateObj.get("QUALITY_AUDIT"));
								obj.put("AUDITOR_CODE", token.getEmployeeCode());
								obj.put("AUDITOR_NAME", token.getEmployeeName());
								try {
									date = DateTimeUtil.formatDate(new Date(),
											DateTimeUtil.FORMAT_1);
								} catch (ParseException e) {
									e.printStackTrace();
								}
								obj.put("AUDIT_TIME", date);
							}

							WriteResult result = collection.update(query, obj);

							return result.getN();
						}
					});
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("修改mongodb总控表清单出错", e);
		}

		return iCount;
	}

	public int batchUpdateMctDevice(final Map<String, Object> params,
			UserToken token) {
		int iCount = 0;
		try {

			iCount = this.mongoTemplate.execute("PM_MCT_LIST",
					new CollectionCallback<Integer>() {

						@Override
						public Integer doInCollection(DBCollection collection)
								throws MongoException, DataAccessException {

							DBObject query = new BasicDBObject();
							query.put("companyCode", params.get("companyCode"));
							if (params.get("mctCode") != null
									&& !"".equals(params.get("mctCode"))) {
								query.put("mctCode", params.get("mctCode"));
							}
							if (params.get("deviceTypeCode") != null
									&& !"".equals(params.get("deviceTypeCode"))) {
								query.put("deviceTypeCode",
										params.get("deviceTypeCode"));
							}
							String codes = (String) params
									.get("deviceObjCodes");
							String[] codesArr = codes.split(",");
							BasicDBList vals = new BasicDBList();
							for (int i = 0; i < codesArr.length; i++) {
								String code = codesArr[i];
								vals.add(code);
							}
							DBObject $in = new BasicDBObject("$in", vals);
							query.put("deviceObjCode", $in);
							// Object searchName = params.get("searchName");
							// if (params.get("attrJson") != null &&
							// !"".equals(params.get("attrJson"))) {
							// String attrJson = (String)
							// params.get("attrJson");
							// Map<String, Object> attrMap =
							// JSONObject.parseObject(attrJson);
							// Iterator<String> it =
							// attrMap.keySet().iterator();
							// while (it.hasNext()) {
							// String key = it.next();
							// if(attrMap.get(key)==null ||
							// "".equals(attrMap.get(key))){
							// continue;
							// }
							// query.put(key, attrMap.get(key));
							// }
							// }
							// if (searchName != null && !"".equals(searchName))
							// {
							// String[] packet =
							// searchName.toString().split(";");
							// if (packet.length > 1) {
							//
							// String[] fields = packet[0].split(",");
							// String searchValue = packet[1];
							// if (searchValue != null &&
							// !"".equals(searchValue)) {
							//
							// Pattern pattern = Pattern.compile("^.*" +
							// searchValue + ".*$", Pattern.CASE_INSENSITIVE);
							// BasicDBList values = new BasicDBList();
							// for (String field : fields) {
							//
							// values.add(new BasicDBObject(field, pattern));
							// }
							//
							// values.add(new BasicDBObject("deviceObjName",
							// pattern));
							// values.add(new BasicDBObject("deviceObjCode",
							// pattern));
							// values.add(new BasicDBObject("CREATER_NAME",
							// pattern));
							// values.add(new BasicDBObject("AUDITOR_NAME",
							// pattern));
							// query.put("$or", values);
							// }
							// }
							// }

							String ename = (String) params.get("fieldEname");
							Object val = params.get("fieldValue");
							int dtype = 1;
							try {
								dtype = Integer.parseInt(params.get(
										"fieldDtype").toString());
							} catch (Exception e) {
							}
							if (dtype == 2) {
								val = Integer.parseInt(val.toString());
							}
							DBObject updateObj = new BasicDBObject();
							updateObj.put(ename, val);
							DBObject set = new BasicDBObject();
							set.put("$set", updateObj);
							WriteResult result = collection.updateMulti(query,
									set);
							return result.getN();
						}
					});
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			logger.error("批量修改mongodb总控表清单出错", e);
		}
		return iCount;
	}

	public Map<String, Object> getMctDevice(final String deviceObjCode) {

		Map<String, Object> mctDeviceMap = null;

		try {

			mctDeviceMap = this.mongoTemplate.execute("PM_MCT_LIST",
					new CollectionCallback<Map<String, Object>>() {

						/**
						 * 
						 * <p>
						 * Title: doInCollection
						 * </p>
						 * 
						 * <p>
						 * Description:
						 * </p>
						 * 
						 * @param collection
						 * @return
						 * @throws MongoException
						 * @throws DataAccessException
						 * 
						 * @see org.springframework.data.mongodb.core.CollectionCallback#doInCollection(com.mongodb.DBCollection)
						 **/

						@Override
						public Map<String, Object> doInCollection(
								DBCollection collection) throws MongoException,
								DataAccessException {
							// TODO Auto-generated method stub
							BasicDBObject query = new BasicDBObject();
							query.put("deviceObjCode", deviceObjCode);
							DBCursor cur = collection.find(query);
							Map<String, Object> deviceMap = null;
							if (cur.hasNext()) {

								DBObject obj = cur.next();
								deviceMap = obj.toMap();
							}

							return deviceMap;
						}
					});
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("获取mongodb总控表清单出错", e);
		}

		return mctDeviceMap;
	}

	public int deleteMctDevice(final String deviceObjCode, UserToken token) {

		int iCount = 0;

		try {

			iCount = this.mongoTemplate.execute("PM_MCT_LIST",
					new CollectionCallback<Integer>() {

						/**
						 * 
						 * <p>
						 * Title: doInCollection
						 * </p>
						 * 
						 * <p>
						 * Description:
						 * </p>
						 * 
						 * @param collection
						 * @return
						 * @throws MongoException
						 * @throws DataAccessException
						 * 
						 * @see org.springframework.data.mongodb.core.CollectionCallback#doInCollection(com.mongodb.DBCollection)
						 **/

						@Override
						public Integer doInCollection(DBCollection collection)
								throws MongoException, DataAccessException {
							// TODO Auto-generated method stub
							BasicDBObject query = new BasicDBObject();
							query.put("deviceObjCode", deviceObjCode);
							DBCursor cur = collection.find(query);
							int icount = 0;
							if (cur.hasNext()) {

								WriteResult result = collection.remove(cur
										.next());
								icount = result.getN();
							}

							return icount;
						}
					});
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("修改mongodb总控表清单出错", e);
		}

		return iCount;
	}

	public int updateDeviceLoc(final String mctDeviceJson, UserToken token) {

		int iCount = 0;

		try {

			iCount = this.mongoTemplate.execute("PM_MCT_LIST",
					new CollectionCallback<Integer>() {

						/**
						 * 
						 * <p>
						 * Title: doInCollection
						 * </p>
						 * 
						 * <p>
						 * Description:
						 * </p>
						 * 
						 * @param collection
						 * @return
						 * @throws MongoException
						 * @throws DataAccessException
						 * 
						 * @see org.springframework.data.mongodb.core.CollectionCallback#doInCollection(com.mongodb.DBCollection)
						 **/

						@Override
						public Integer doInCollection(DBCollection collection)
								throws MongoException, DataAccessException {
							// TODO Auto-generated method stub

							DBObject updateObj = (DBObject) JSON
									.parse(mctDeviceJson);
							BasicDBObject query = new BasicDBObject();
							query.put("deviceObjCode",
									updateObj.get("deviceObjCode"));
							DBObject obj = collection.findOne(query);
							if (obj != null) {

								obj.put("x", updateObj.get("x"));
								obj.put("y", updateObj.get("y"));
								obj.put("rx", updateObj.get("rx"));
								obj.put("ry", updateObj.get("ry"));
								WriteResult result = collection.update(query,
										obj);

								return result.getN();
							}

							return 0;
						}
					});
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("修改mongodb总控表设备坐标出错！", e);
		}

		return iCount;
	}

	public List<Map> getMctDeviceList(final String params,
			final Object searchName) {

		return this.mongoTemplate.execute("PM_MCT_LIST",
				new CollectionCallback<List<Map>>() {

					/**
					 * 
					 * <p>
					 * Title: doInCollection
					 * </p>
					 * 
					 * <p>
					 * Description:
					 * </p>
					 * 
					 * @param collection
					 * @return
					 * @throws MongoException
					 * @throws DataAccessException
					 * 
					 * @see org.springframework.data.mongodb.core.CollectionCallback#doInCollection(com.mongodb.DBCollection)
					 **/

					@Override
					public List<Map> doInCollection(DBCollection collection)
							throws MongoException, DataAccessException {
						// TODO Auto-generated method stub
						DBObject query = (DBObject) JSON.parse(params);
						String startTime = (String) query.get("startTime");
						String endTime = (String) query.get("endTime");
						String qcStartTime = (String) query.get("qcStartTime");
						String qcEndTime = (String) query.get("qcEndTime");
						query.removeField("startTime");
						query.removeField("endTime");
						query.removeField("qcStartTime");
						query.removeField("qcEndTime");

						if (query.get("QUALITY_AUDIT") != null
								&& query.get("QUALITY_AUDIT").toString()
										.length() == 0) {
							query.removeField("QUALITY_AUDIT");
						}
						BasicDBList $andVals = new BasicDBList();
						if (query.get("hasPic") != null
								&& query.get("hasPic").toString().length() > 0) {
							String hasPic = (String) query.get("hasPic");
							query.removeField("hasPic");
							query.put("pic", hasPic);
						}
						if ("0".equals((String) query.get("hasPosition"))) {
							query.removeField("hasPosition");
							DBObject obj = (DBObject) JSON
									.parse("{$and:[{$or:[{'rx':{$exists:false}},{'rx':{$eq:''}}]},{$or:[{'ry':{$exists:false}},{'ry':{$eq:''}}]}]}");
							$andVals.add(obj);
						} else if ("1".equals((String) query.get("hasPosition"))) {
							query.removeField("hasPosition");
							DBObject obj = (DBObject) JSON
									.parse("{$and:[{$or:[{'rx':{$exists:true}},{'rx':{$ne:''}}]},{$or:[{'ry':{$exists:true}},{'ry':{$ne:''}}]}]}");
							$andVals.add(obj);
						}
						if (searchName != null && !"".equals(searchName)) {

							String[] packet = searchName.toString().split(";");
							if (packet.length > 1) {

								String[] fields = packet[0].split(",");
								String searchValue = packet[1];
								if (searchValue != null
										&& !"".equals(searchValue)) {

									Pattern pattern = Pattern.compile("^.*"
											+ searchValue + ".*$",
											Pattern.CASE_INSENSITIVE);
									BasicDBList values = new BasicDBList();
									for (String field : fields) {

										values.add(new BasicDBObject(field,
												pattern));
									}

									values.add(new BasicDBObject(
											"deviceObjName", pattern));
									values.add(new BasicDBObject(
											"deviceObjCode", pattern));
									values.add(new BasicDBObject(
											"CREATER_NAME", pattern));
									values.add(new BasicDBObject(
											"AUDITOR_NAME", pattern));
									$andVals.add(new BasicDBObject("$or",
											values));
								}
							}
						}
						if (startTime != null && startTime.length() > 0
								&& (endTime == null || endTime.length() == 0)) {
							query.put("SYS_CREATE_TIME", new BasicDBObject(
									"$gt", startTime + " 00:00:00"));
						}
						if ((startTime == null || startTime.length() == 0)
								&& endTime != null && endTime.length() > 0) {
							query.put("SYS_CREATE_TIME", new BasicDBObject(
									"$lt", endTime + " 23:59:59"));
						}
						if (startTime != null && startTime.length() > 0
								&& endTime != null && endTime.length() > 0) {
							BasicDBList values = new BasicDBList();
							values.add(new BasicDBObject("SYS_CREATE_TIME",
									new BasicDBObject("$gt", startTime
											+ " 00:00:00")));
							values.add(new BasicDBObject("SYS_CREATE_TIME",
									new BasicDBObject("$lt", endTime
											+ " 23:59:59")));
							$andVals.add(new BasicDBObject("$and", values));
						}
						if (qcStartTime != null
								&& qcStartTime.length() > 0
								&& (qcEndTime == null || qcEndTime.length() == 0)) {
							query.put("qualityCheckRecord.checkTime",
									new BasicDBObject("$gt", qcStartTime
											+ " 00:00:00"));
						}
						if ((qcStartTime == null || qcStartTime.length() == 0)
								&& qcEndTime != null && qcEndTime.length() > 0) {
							query.put("qualityCheckRecord.checkTime",
									new BasicDBObject("$lt", qcEndTime
											+ " 23:59:59"));
						}
						if (qcStartTime != null && qcStartTime.length() > 0
								&& qcEndTime != null && qcEndTime.length() > 0) {
							DBObject createTime = new BasicDBObject("$gt",
									qcStartTime + " 00:00:00");
							createTime.put("$lt", qcEndTime + " 23:59:59");
							query.put("qualityCheckRecord.checkTime",
									createTime);
						}
						if ($andVals.size() > 0) {
							query.put("$and", $andVals);
						}
						List<Map> list = new ArrayList<Map>();
						DBCursor cur = collection.find(query);
						Point point = null;
						while (cur.hasNext()) {

							DBObject obj = cur.next();
							// if (obj.containsField("x") &&
							// obj.containsField("y")
							// && !"".equals(obj.get("x")) &&
							// !"".equals(obj.get("y"))) {
							//
							// double x =
							// Double.parseDouble(obj.get("x").toString());
							// double y =
							// Double.parseDouble(obj.get("y").toString());
							// if (x > 0 && y > 0) {
							//
							// point = Converter.WGS84ToBD09(x, y);
							// if (point != null) {
							//
							// obj.put("x", point.getLongitude());
							// obj.put("y", point.getLatitude());
							// }
							// }
							//
							// }
							list.add(obj.toMap());
						}

						return list;
					}
				});
	}

	public List<Map<String, Object>> spatialQueryMctDevice(final String params,
			final Object searchName, final List<String> featureFieldList) {

		return this.mongoTemplate.execute("PM_MCT_LIST",
				new CollectionCallback<List<Map<String, Object>>>() {

					/**
					 * 
					 * <p>
					 * Title: doInCollection
					 * </p>
					 * 
					 * <p>
					 * Description:
					 * </p>
					 * 
					 * @param collection
					 * @return
					 * @throws MongoException
					 * @throws DataAccessException
					 * 
					 * @see org.springframework.data.mongodb.core.CollectionCallback#doInCollection(com.mongodb.DBCollection)
					 **/

					@Override
					public List<Map<String, Object>> doInCollection(
							DBCollection collection) throws MongoException,
							DataAccessException {
						// TODO Auto-generated method stub
						DBObject query = (DBObject) JSON.parse(params);
						if (searchName != null && !"".equals(searchName)) {

							String[] packet = searchName.toString().split(";");
							if (packet.length > 1) {

								String[] fields = packet[0].split(",");
								String searchValue = packet[1];
								if (searchValue != null
										&& !"".equals(searchValue)) {

									Pattern pattern = Pattern.compile("^.*"
											+ searchValue + ".*$",
											Pattern.CASE_INSENSITIVE);
									BasicDBList values = new BasicDBList();
									for (String field : fields) {

										values.add(new BasicDBObject(field,
												pattern));
									}

									values.add(new BasicDBObject(
											"deviceObjName", pattern));
									values.add(new BasicDBObject(
											"deviceObjCode", pattern));
									values.add(new BasicDBObject(
											"CREATER_NAME", pattern));
									values.add(new BasicDBObject(
											"AUDITOR_NAME", pattern));
									query.put("$or", values);
								}
							}

						}

						DBObject keys = new BasicDBObject();
						keys.put("proName", 1);
						keys.put("deviceObjName", 1);
						keys.put("deviceObjCode", 1);
						keys.put("healthName", 1);
						keys.put("x", 1);
						keys.put("y", 1);
						if (featureFieldList != null
								&& featureFieldList.size() > 0) {

							for (String field : featureFieldList) {

								keys.put(field, 1);
							}
						}
						List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
						DBCursor cur = collection.find(query, keys);
						Point point = null;
						while (cur.hasNext()) {

							DBObject obj = cur.next();
							// if (obj.containsField("x") &&
							// obj.containsField("y")
							// && !"".equals(obj.get("x")) &&
							// !"".equals(obj.get("y"))) {
							//
							// double x =
							// Double.parseDouble(obj.get("x").toString());
							// double y =
							// Double.parseDouble(obj.get("y").toString());
							// if (x > 0 && y > 0) {
							//
							// point = Converter.WGS84ToBD09(x, y);
							// if (point != null) {
							//
							// obj.put("x", point.getLongitude());
							// obj.put("y", point.getLatitude());
							// }
							// }
							//
							// }
							list.add(obj.toMap());
						}

						return list;
					}
				});
	}

	public List<Map> getMctDeviceDistribute(final String params,
			final Object searchName, final String groupField,
			final List<String> featureFieldList) {

		return this.mongoTemplate.execute("PM_MCT_LIST",
				new CollectionCallback<List<Map>>() {

					/**
					 * 
					 * <p>
					 * Title: doInCollection
					 * </p>
					 * 
					 * <p>
					 * Description:
					 * </p>
					 * 
					 * @param collection
					 * @return
					 * @throws MongoException
					 * @throws DataAccessException
					 * 
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
								if (searchValue != null
										&& !"".equals(searchValue)) {

									Pattern pattern = Pattern.compile("^.*"
											+ searchValue + ".*$",
											Pattern.CASE_INSENSITIVE);
									BasicDBList values = new BasicDBList();
									for (String field : fields) {

										values.add(new BasicDBObject(field,
												pattern));
									}

									values.add(new BasicDBObject(
											"deviceObjName", pattern));
									values.add(new BasicDBObject(
											"deviceObjCode", pattern));
									values.add(new BasicDBObject(
											"CREATER_NAME", pattern));
									values.add(new BasicDBObject(
											"AUDITOR_NAME", pattern));
									query.put("$or", values);
								}
							}

						}

						DBObject keys = new BasicDBObject();
						keys.put("proName", 1);
						keys.put("deviceObjName", 1);
						keys.put("deviceObjCode", 1);
						keys.put("healthName", 1);
						keys.put("x", 1);
						keys.put("y", 1);
						keys.put(groupField, 1);
						if (featureFieldList != null
								&& featureFieldList.size() > 0) {

							for (String field : featureFieldList) {

								keys.put(field, 1);
							}
						}

						List<Map> list = new ArrayList<Map>();
						DBCursor cur = collection.find(query, keys);
						Point point = null;
						while (cur.hasNext()) {

							DBObject obj = cur.next();
							// if (obj.containsField("x") &&
							// obj.containsField("y")
							// && !"".equals(obj.get("x")) &&
							// !"".equals(obj.get("y"))) {
							//
							// double x =
							// Double.parseDouble(obj.get("x").toString());
							// double y =
							// Double.parseDouble(obj.get("y").toString());
							// if (x > 0 && y > 0) {
							//
							// point = Converter.WGS84ToBD09(x, y);
							// if (point != null) {
							//
							// obj.put("x", point.getLongitude());
							// obj.put("y", point.getLatitude());
							// }
							//
							// }
							// }
							list.add(obj.toMap());
						}

						return list;
					}
				});
	}

	public List<Map<String, Object>> mctDeviceCount(final String params,
			final Object searchName, final String groupField) {

		return this.mongoTemplate.execute("PM_MCT_LIST",
				new CollectionCallback<List<Map<String, Object>>>() {

					/**
					 * 
					 * <p>
					 * Title: doInCollection
					 * </p>
					 * 
					 * <p>
					 * Description:
					 * </p>
					 * 
					 * @param collection
					 * @return
					 * @throws MongoException
					 * @throws DataAccessException
					 * 
					 * @see org.springframework.data.mongodb.core.CollectionCallback#doInCollection(com.mongodb.DBCollection)
					 **/

					@Override
					public List<Map<String, Object>> doInCollection(
							DBCollection collection) throws MongoException,
							DataAccessException {
						// TODO Auto-generated method stub
						DBObject query = (DBObject) JSON.parse(params);
						if (searchName != null && !"".equals(searchName)) {

							String[] packet = searchName.toString().split(";");
							if (packet.length > 1) {

								String[] fields = packet[0].split(",");
								String searchValue = packet[1];
								if (searchValue != null
										&& !"".equals(searchValue)) {

									Pattern pattern = Pattern.compile("^.*"
											+ searchValue + ".*$",
											Pattern.CASE_INSENSITIVE);
									BasicDBList values = new BasicDBList();
									for (String field : fields) {

										values.add(new BasicDBObject(field,
												pattern));
									}

									values.add(new BasicDBObject(
											"deviceObjName", pattern));
									values.add(new BasicDBObject(
											"deviceObjCode", pattern));
									values.add(new BasicDBObject(
											"CREATER_NAME", pattern));
									values.add(new BasicDBObject(
											"AUDITOR_NAME", pattern));
									query.put("$or", values);
								}
							}

						}

						BasicDBObject key = new BasicDBObject(groupField, true);
						BasicDBObject initial = new BasicDBObject();
						initial.append("groupCount", 0);
						String reduce = "function(doc, prev) {prev.groupCount++;}";
						BasicDBList groupResult = (BasicDBList) collection
								.group(key, query, initial, reduce);
						List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
						if (groupResult != null && groupResult.size() > 0) {

							for (int i = 0; i < groupResult.size(); i++) {

								DBObject item = (DBObject) groupResult.get(i);
								list.add(item.toMap());
							}
						}

						return list;
					}
				});
	}

	public List<Map<String, Object>> getDeviceObjNames(String deviceTypeCode,
			String companyCode, String proNo, String dependFields) {

		DBObject query = new BasicDBObject();
		query.put("deviceTypeCode", deviceTypeCode);
		query.put("companyCode", companyCode);
		query.put("proNo", proNo);
		DBObject keys = new BasicDBObject();
		keys.put("deviceObjName", 1);
		Map<String, String> filedMap = new HashMap<String, String>();
		if (dependFields != null && !"".equals(dependFields)) {

			String[] fields = dependFields.split(",");
			for (String field : fields) {

				String[] keyFields = field.split(";");
				keys.put(keyFields[0], 1);
				if (keyFields.length > 1) {

					filedMap.put(keyFields[0], keyFields[1]);
				}

			}
		}
		DBCursor cur = this.mongoTemplate.getCollection("PM_MCT_LIST").find(
				query, keys);
		List<Map<String, Object>> deviceObjNameList = new ArrayList<Map<String, Object>>();
		while (cur.hasNext()) {

			DBObject obj = cur.next();
			deviceObjNameList.add(convertDbObjectToMap(obj, filedMap));
		}

		return deviceObjNameList;
	}

	private Map<String, Object> convertDbObjectToMap(DBObject obj,
			Map<String, String> filedMap) {

		Map<String, Object> mapItem = obj.toMap();
		if (filedMap != null && filedMap.size() > 0) {

			Iterator<String> it = filedMap.keySet().iterator();
			while (it.hasNext()) {

				String key = it.next();
				String showField = filedMap.get(key);
				mapItem.put(showField, mapItem.get(key));
			}
		}

		return mapItem;
	}

	public List<Map<String, Object>> getNullCoordsDevices(final String params,
			final Object searchName) {

		DBObject query = (DBObject) JSON.parse(params);
		BasicDBList andList = new BasicDBList();
		if (searchName != null && !"".equals(searchName)) {

			String[] packet = searchName.toString().split(";");
			if (packet.length > 1) {

				String[] fields = packet[0].split(",");
				String searchValue = packet[1];
				if (searchValue != null && !"".equals(searchValue)) {

					Pattern pattern = Pattern.compile("^.*" + searchValue
							+ ".*$", Pattern.CASE_INSENSITIVE);
					BasicDBList values = new BasicDBList();
					for (String field : fields) {

						values.add(new BasicDBObject(field, pattern));
					}

					values.add(new BasicDBObject("deviceObjName", pattern));
					values.add(new BasicDBObject("deviceObjCode", pattern));
					values.add(new BasicDBObject("CREATER_NAME", pattern));
					values.add(new BasicDBObject("AUDITOR_NAME", pattern));
					// query.put("$or", values);
					andList.add(new BasicDBObject("$or", values));
				}
			}

		}

		// 坐标为空的条件
		BasicDBList xyQuryList = new BasicDBList();
		xyQuryList.add(new BasicDBObject("x", 0));
		xyQuryList.add(new BasicDBObject("x", null));
		xyQuryList.add(new BasicDBObject("y", 0));
		xyQuryList.add(new BasicDBObject("y", null));
		andList.add(new BasicDBObject("$or", xyQuryList));
		query.put("$and", andList);

		DBCursor cur = this.mongoTemplate.getCollection("PM_MCT_LIST").find(
				query);
		List<Map<String, Object>> deviceList = new ArrayList<Map<String, Object>>();
		while (cur.hasNext()) {

			deviceList.add(cur.next().toMap());
		}

		return deviceList;
	}

	public long getDevicePicCount(String companyCode, String deviceObjCode) {

		DBObject query = new BasicDBObject();
		query.put("companyCode", companyCode);
		query.put("deviceObjCode", deviceObjCode);
		long icount = this.mongoTemplate.getCollection("PM_DEVICE_PICBOOK")
				.count(query);

		return icount;
	}

	public List queryPmMdList(final String proNo, final String queryDate) {
		// TODO Auto-generated method stub
		List list = null;
		try {
			list = this.mongoTemplate.execute("PM_MCT_LIST",
					new CollectionCallback<List>() {

						@Override
						public List doInCollection(DBCollection collection)
								throws MongoException, DataAccessException {
							List<Map> list = new ArrayList<Map>();
							DBObject query = new BasicDBObject();
							query.put("proNo", proNo);
							query.put("deviceTypeCode", "P04");
							if (StringUtil.isNotNull(queryDate)) {
								query.put("WORK_DATE", new BasicDBObject("$lt",
										queryDate + " 23:59:59"));
							}
							DBCursor cur = collection.find(query);

							while (cur.hasNext()) {
								DBObject obj = cur.next();
								list.add(obj.toMap());
							}

							return list;
						}

					});
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("获取mongodb项目总控表设备清单出错", e);
		}

		return list;

	}

	public int deviceQualityCheck(final Map map, UserToken token) {
		return this.mongoTemplate.execute("PM_MCT_LIST",
				new CollectionCallback<Integer>() {

					/**
					 * 
					 * <p>
					 * Title: doInCollection
					 * </p>
					 * 
					 * <p>
					 * Description:
					 * </p>
					 * 
					 * @param collection
					 * @return
					 * @throws MongoException
					 * @throws DataAccessException
					 * 
					 * @see org.springframework.data.mongodb.core.CollectionCallback#doInCollection(com.mongodb.DBCollection)
					 **/

					@Override
					public Integer doInCollection(DBCollection collection)
							throws MongoException, DataAccessException {
						//
						DBObject query = new BasicDBObject();
						query.put("deviceObjCode", map.get("deviceObjCode"));

						DBObject val = new BasicDBObject();
						val.put("QUALITY_AUDIT", map.get("QUALITY_AUDIT"));
						val.put("AUDITOR_CODE", map.get("AUDITOR_CODE"));
						val.put("AUDITOR_NAME", map.get("AUDITOR_NAME"));

						val.put("FAULT_TYPE", map.get("FAULT_TYPE"));
						val.put("FAULT_TYPE_NAME", map.get("FAULT_TYPE_NAME"));
						val.put("FAULT_DETAIL", map.get("FAULT_DETAIL"));
						val.put("IS_RECTIFY", map.get("IS_RECTIFY"));

						String date = "";
						try {
							date = DateTimeUtil.formatDate(new Date(),
									DateTimeUtil.FORMAT_1);
						} catch (ParseException e) {
							e.printStackTrace();
						}
						val.put("AUDIT_TIME", date);
						val.put("AUDIT_SUGGEST", map.get("AUDIT_SUGGEST"));
						WriteResult wr = collection.update(query,
								new BasicDBObject("$set", val));
						return wr.getN();
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
