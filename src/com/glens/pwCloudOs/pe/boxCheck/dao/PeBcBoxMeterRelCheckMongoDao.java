package com.glens.pwCloudOs.pe.boxCheck.dao;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.data.mongodb.core.CollectionCallback;
import org.springframework.data.mongodb.core.MongoTemplate;

import com.glens.eap.platform.core.utils.DateTimeUtil;
import com.glens.pwCloudOs.pe.boxCheck.vo.PeBcBoxMeterRelCheckVo;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoException;
import com.mongodb.WriteResult;
/**
 * 箱表关系核查（设备类型固定["P10":箱表关系核查原始数据]）
 * @author Administrator
 *
 */
public class PeBcBoxMeterRelCheckMongoDao {
	private MongoTemplate mongoTemplate;
	
	public void setMongoTemplate(MongoTemplate mongoTemplate) {
		this.mongoTemplate = mongoTemplate;
	}

	/**
	 * 根据表箱号查询表数据
	 * @param boxCode
	 * @return
	 */
	public List<PeBcBoxMeterRelCheckVo> selectByBoxCode(final String boxCode) {
		List<PeBcBoxMeterRelCheckVo> result = null;
		try {
			result = this.mongoTemplate.execute("PM_MCT_LIST", new CollectionCallback<List<PeBcBoxMeterRelCheckVo>>() {

				@Override
				public List<PeBcBoxMeterRelCheckVo> doInCollection(DBCollection collection)
						throws MongoException, DataAccessException {
					DBObject query = new BasicDBObject();
					if (boxCode != null && !"".equals(boxCode)) {
						query.put("deviceTypeCode", "P10");
						query.put("boxCode", boxCode);
					}
					DBCursor res = (DBCursor) collection.find(query);
					List<PeBcBoxMeterRelCheckVo> list = new ArrayList<PeBcBoxMeterRelCheckVo>();
					while (res.hasNext()) {
						DBObject item = res.next();
						PeBcBoxMeterRelCheckVo vo = new PeBcBoxMeterRelCheckVo();
						vo.setCompanyCode((String)item.get("companyCode"));
						vo.setProNo((String)item.get("proNo"));
						vo.setProName((String)item.get("proName"));
						vo.setMctCode((String)item.get("mctCode"));
						vo.setDeviceObjCode((String)item.get("deviceObjCode"));
						vo.setDeviceObjName((String)item.get("deviceObjName"));
						
						vo.setBoxCode((String)item.get("boxCode"));
						vo.setMeterCode((String)item.get("meterCode"));
						vo.setTransSvsAreaCode((String)item.get("transSvsAreaCode"));
						Double checkResult = null;
						if(Double.class.equals(item.get("checkResult").getClass())){
							checkResult = (Double)item.get("checkResult");
						}else if(Integer.class.equals(item.get("checkResult").getClass())){
							checkResult = new Double((Integer)item.get("checkResult"));
						}
						vo.setCheckResult(checkResult.intValue());
						vo.setCheckTime((String)item.get("checkTime"));
						vo.setCheckUser((String)item.get("checkUser"));
						vo.setCheckUserName((String)item.get("checkUserName"));
						list.add(vo);
					}
					return list;
				}
			});
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
		return result;
	}
	
	/**
	 * 新增
	 * @param vo
	 * @return
	 */
	public int insert(final PeBcBoxMeterRelCheckVo vo){
		int result = 0;
		try{
			result = this.mongoTemplate.execute("PM_MCT_LIST", new CollectionCallback<Integer>(){
				@Override
				public Integer doInCollection(DBCollection collection)
						throws MongoException, DataAccessException {
					DBObject params = new BasicDBObject();
					params.put("deviceTypeCode", "P10");
					params.put("companyCode", vo.getCompanyCode());
					params.put("proNo", vo.getProNo());
					params.put("proName", vo.getProName());
					params.put("mctCode", vo.getMctCode());
					params.put("deviceObjCode", vo.getDeviceObjCode());
					params.put("deviceObjName", vo.getDeviceObjName());
					
					params.put("boxCode", vo.getBoxCode());
					params.put("meterCode", vo.getMeterCode());
					params.put("transSvsAreaCode", vo.getTransSvsAreaCode());
					params.put("checkResult",  vo.getCheckResult());
					params.put("checkResultName", vo.getCheckResultName());
					if(0 != vo.getCheckResult()){
						String date = "";
						try {
							date = DateTimeUtil.formatDate(new Date(), DateTimeUtil.FORMAT_1);
						} catch (ParseException e) {
							e.printStackTrace();
						}
						params.put("checkTime", date);
					}
					params.put("checkUser", vo.getCheckUser());
					params.put("checkUserName", vo.getCheckUserName());
					WriteResult wr = collection.insert(params);
					return wr.getN();
				}
			});
		}catch(Exception e){
			throw new RuntimeException(e);
		}
		return result;
	}
	/**
	 * 修改
	 * @param vo
	 * @return
	 */
	public int update(final PeBcBoxMeterRelCheckVo vo){
		int result = 0;
		try{
			result = this.mongoTemplate.execute("PM_MCT_LIST", new CollectionCallback<Integer>(){

				@Override
				public Integer doInCollection(DBCollection collection)
						throws MongoException, DataAccessException {
					DBObject query = new BasicDBObject();
					query.put("deviceTypeCode", "P10");
					query.put("boxCode", vo.getBoxCode());
					query.put("meterCode", vo.getMeterCode());
					
					DBObject set = new BasicDBObject();
					DBObject val = new BasicDBObject();
					val.put("checkResult", vo.getCheckResult());
					val.put("checkResultName", vo.getCheckResultName());
					String date = null;
					try {
						date = DateTimeUtil.formatDate(new Date(), DateTimeUtil.FORMAT_1);
					} catch (ParseException e) {
						e.printStackTrace();
					}
					val.put("checkTime", date);
					val.put("checkUser", vo.getCheckUser());
					val.put("checkUserName", vo.getCheckUserName());
					set.put("$set", val);
					WriteResult wr = collection.update(query, set);
					return wr.getN();
				}
				
			});
		}catch(Exception e){
			throw new RuntimeException(e);
		}
		return result;
	}
}
