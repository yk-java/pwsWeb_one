package com.glens.pwCloudOs.om.deviceMgr.device.dao;

import java.text.ParseException;
import java.util.Date;

import org.springframework.dao.DataAccessException;
import org.springframework.data.mongodb.core.CollectionCallback;
import org.springframework.data.mongodb.core.MongoTemplate;

import com.glens.eap.platform.core.utils.DateTimeUtil;
import com.glens.pwCloudOs.om.deviceMgr.device.vo.QualityCheckRecordVo;
import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoException;
import com.mongodb.WriteResult;

public class QualityCheckRecordMongoDao {
	private MongoTemplate mongoTemplate;

	public void setMongoTemplate(MongoTemplate mongoTemplate) {
		this.mongoTemplate = mongoTemplate;
	}
	/**
	 * 在设备记录中新增质量核查记录
	 * @param vo
	 * @return
	 */
	public int insert(final QualityCheckRecordVo vo) {
		int rows=0;
		rows = mongoTemplate.execute("PM_MCT_LIST", new CollectionCallback<Integer>() {

			@Override
			public Integer doInCollection(DBCollection collection)
					throws MongoException, DataAccessException {
				DBObject query = new BasicDBObject();
				query.put("companyCode", vo.getCompanyCode());
				query.put("deviceObjCode", vo.getDeviceObjCode());
				DBCursor cursor = collection.find(query);
				if(cursor.hasNext()){
					DBObject dev = cursor.next();
					BasicDBList list = (BasicDBList)dev.get("qualityCheckRecord");//
					if(list==null){
						list=new BasicDBList();
					}
					DBObject item = new BasicDBObject();
					item.put("checkType", vo.getCheckType());
					item.put("checkResult", vo.getCheckResult());
					item.put("faultType", vo.getFaultType());
					item.put("faultName", vo.getFaultName());
					item.put("faultDescr", vo.getFaultDescr());
					item.put("isRectify", vo.getIsRectify());
					item.put("pics", vo.getPics());
					String date = null;
					try {
						date = DateTimeUtil.formatDate(new Date(), DateTimeUtil.FORMAT_1);
					} catch (ParseException e) {
						e.printStackTrace();
					}
					item.put("checkTime", date);
					item.put("checkUser", vo.getCheckUser());
					item.put("checkUserName", vo.getCheckUserName());
					list.add(item);
					dev.put("qualityCheckRecord", list);
					WriteResult wr = collection.save(dev);
					return wr.getN();
				}
				return 0;
			}
			
		});
		return rows;
	}
	
	
}
