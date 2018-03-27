package com.glens.pwCloudOs.commuteMgr.performance.digitalSignage.dao;

import java.text.ParseException;
import java.util.Date;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import com.glens.eap.platform.core.utils.DateTimeUtil;


public class CpCommuteGpsDao {

	private MongoTemplate mongoTemplae;
	
	public long getCommuteTrackCount(String accountCode, String date) throws ParseException {
		
		Query query = new Query();
		Date startTime = DateTimeUtil.getDateFromDateString(date + " 00:00:00", DateTimeUtil.FORMAT_1);
		Date endTime = DateTimeUtil.getDateFromDateString(date + " 23:59:59", DateTimeUtil.FORMAT_1);
		Criteria criteria = Criteria.where("accountCode").is(accountCode);
		criteria.and("rtTime").gte(startTime);
		criteria.and("rtTime").lte(endTime);
		
		long trackCount = this.mongoTemplae.count(query, "CP_COMMUTE_GPS");
		
		return trackCount;
	}

	public MongoTemplate getMongoTemplae() {
		return mongoTemplae;
	}

	public void setMongoTemplae(MongoTemplate mongoTemplae) {
		this.mongoTemplae = mongoTemplae;
	} 
	
}
