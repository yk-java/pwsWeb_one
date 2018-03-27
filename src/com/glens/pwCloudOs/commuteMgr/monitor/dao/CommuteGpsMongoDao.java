package com.glens.pwCloudOs.commuteMgr.monitor.dao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import com.glens.eap.platform.core.utils.DateTimeUtil;
import com.glens.pwCloudOs.commuteMgr.monitor.vo.CpCommuteGpsVo;

public class CommuteGpsMongoDao {

	private MongoTemplate mongoTemplae;

	public CpCommuteGpsVo getRealTimePosition(String accountCode) {

		CpCommuteGpsVo gpsVo = null;
		try {
			String today = DateTimeUtil.getDateTime(new Date(),
					DateTimeUtil.FORMAT_2);
			Date startTime = DateTimeUtil.getDateFromDateString(today
					+ " 00:00:00", DateTimeUtil.FORMAT_1);
			Date endTime = DateTimeUtil.getDateFromDateString(today
					+ " 23:59:59", DateTimeUtil.FORMAT_1);
			Query query = new Query();
			Criteria criteria = Criteria.where("accountCode").is(accountCode);
			criteria.and("rpTime").gte(startTime).lte(endTime);
			query.addCriteria(criteria);
			Sort sort = new Sort(Direction.DESC, "rpTime");
			query.with(sort);
			query.limit(1);

			gpsVo = this.mongoTemplae.findOne(query, CpCommuteGpsVo.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return gpsVo;
	}

	public List<CpCommuteGpsVo> getHisPosition(String accountCode,
			String beginTime, String endTime) {
		Query query = new Query();
		Criteria criteria = Criteria.where("accountCode").is(accountCode);
		Date beginTime_d = null;
		Date endTime_d = null;
		try {
			beginTime_d = DateTimeUtil.getDateFromDateString(beginTime,
					DateTimeUtil.FORMAT_1);
			endTime_d = DateTimeUtil.getDateFromDateString(endTime,
					DateTimeUtil.FORMAT_1);
		} catch (Exception e1) {
		}
		if (beginTime_d != null && endTime_d != null)
			criteria.and("rpTime").gte(beginTime_d).lte(endTime_d);
		query.addCriteria(criteria);
		Sort sort = new Sort(Direction.ASC, "rpTime");
		query.with(sort);
		List<CpCommuteGpsVo> res = new ArrayList<CpCommuteGpsVo>();
		List<CpCommuteGpsVo> result = null;
		try {
			res = this.mongoTemplae.find(query, CpCommuteGpsVo.class);
			// 过滤到异常点
			result = new ArrayList<CpCommuteGpsVo>();
			for (CpCommuteGpsVo c : res) {
				String x = c.getX();
				if (!x.contains("-")) {
					result.add(c);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	public MongoTemplate getMongoTemplae() {
		return mongoTemplae;
	}

	public void setMongoTemplae(MongoTemplate mongoTemplae) {
		this.mongoTemplae = mongoTemplae;
	}

}
