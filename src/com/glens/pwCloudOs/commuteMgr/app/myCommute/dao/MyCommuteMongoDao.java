/**

 * @Title: MyCommuteMongoDao.java

 * @Package com.glens.pwCloudOs.commuteMgr.app.myCommute.dao

 * @Description: TODO

 * Copyright: Copyright (c) 2016 

 * Company:南京量为石信息科技有限公司

 * @author 

 * @date 2016-5-20 下午1:56:39

 * @version V1.0

 **/



package com.glens.pwCloudOs.commuteMgr.app.myCommute.dao;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import com.glens.eap.platform.core.utils.DateTimeUtil;
import com.glens.pwCloudOs.commuteMgr.monitor.vo.CpCommuteGpsVo;

/**

 * @ClassName: MyCommuteMongoDao

 * @Description: TODO

 * @author

 * @date 2016-5-20 下午1:56:39

 **/

public class MyCommuteMongoDao {

	private MongoTemplate mongoTemplae;
	
	public List<CpCommuteGpsVo> getCommuteTrackCount(String accountCode, String start, String end) throws ParseException {
		
		Query query = new Query();
		Date startTime = DateTimeUtil.getDateFromDateString(start, DateTimeUtil.FORMAT_1);
		Date endTime = DateTimeUtil.getDateFromDateString(end, DateTimeUtil.FORMAT_1);
		Criteria criteria = Criteria.where("accountCode").is(accountCode);
		criteria.and("rpTime").gte(startTime).lte(endTime);
		query.addCriteria(criteria);
		
		List<CpCommuteGpsVo> gpsList = this.mongoTemplae.find(query, CpCommuteGpsVo.class);
		
		return gpsList;
	}

	public MongoTemplate getMongoTemplae() {
		return mongoTemplae;
	}

	public void setMongoTemplae(MongoTemplate mongoTemplae) {
		this.mongoTemplae = mongoTemplae;
	} 
	
}
