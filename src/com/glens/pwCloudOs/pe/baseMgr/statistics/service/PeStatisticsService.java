package com.glens.pwCloudOs.pe.baseMgr.statistics.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import com.alibaba.fastjson.JSONObject;
import com.glens.eap.platform.framework.service.impl.EAPAbstractService;
import com.glens.pwCloudOs.pe.baseMgr.statistics.dao.PeStatisticsMongoDao;
import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;

public class PeStatisticsService extends EAPAbstractService {
	private PeStatisticsMongoDao peStatisticsMongoDao;

	public PeStatisticsMongoDao getPeStatisticsMongoDao() {
		return peStatisticsMongoDao;
	}

	public void setPeStatisticsMongoDao(PeStatisticsMongoDao peStatisticsMongoDao) {
		this.peStatisticsMongoDao = peStatisticsMongoDao;
	}

	public List<Map<String, Object>> statistic(Map<String, Object> params) {
		Map<String, Object> cond = new HashMap<String, Object>();
		String companyCode =(String)params.get("companyCode");
//		String reserveProNo = (String)params.get("reserveProNo");
//		String remouldBatchCode = (String)params.get("remouldBatchCode");
		String mctCode = (String)params.get("mctCode");
		
		if (params.get("mctCode") != null && !"".equals(params.get("mctCode"))) {
			cond.put("mctCode", params.get("mctCode"));
		}
		if (params.get("deviceTypeCode") != null && !"".equals(params.get("deviceTypeCode"))) {
			cond.put("deviceTypeCode", params.get("deviceTypeCode"));
		}
		if (params.get("attrJson") != null && !"".equals(params.get("attrJson"))) {
			String attrJson = (String) params.get("attrJson");
			Map<String, Object> attrMap = JSONObject.parseObject(attrJson);
			Iterator<String> it = attrMap.keySet().iterator();
			while (it.hasNext()) {
				String key = it.next();
				if(attrMap.get(key)==null || "".equals(attrMap.get(key))){
					continue;
				}
				cond.put(key, attrMap.get(key));
			}
		}
		if (params.get("searchName") != null && !"".equals(params.get("searchName"))) {
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
					values.add(new BasicDBObject("deviceObjName", pattern));
					values.add(new BasicDBObject("deviceObjCode", pattern));
					cond.put("$or", values);
				}
			}
		}
		
		String xField =(String)params.get("xField");
		String yField =(String)params.get("yField");
		String xFieldVal=(String)params.get("xFieldVal");
		String yFieldVal=(String)params.get("yFieldVal");
		
		List<Map<String, Object>> xList=new ArrayList<Map<String, Object>>();
		List<Map<String, Object>> yList=new ArrayList<Map<String, Object>>();
		if(xFieldVal!=null && xFieldVal.length()>0){
			String[] xFieldVals = xFieldVal.split(",");
			for (int i = 0; i < xFieldVals.length; i++) {
				String val = xFieldVals[i];
				Map<String, Object> item = new HashMap<String, Object>();
				item.put(xField, val);
				xList.add(item);
			}
		}
		if(yFieldVal!=null && yFieldVal.length()>0){
			String[] yFieldVals = yFieldVal.split(",");
			for (int i = 0; i < yFieldVals.length; i++) {
				String val = yFieldVals[i];
				Map<String, Object> item = new HashMap<String, Object>();
				item.put(yField, val);
				yList.add(item);
			}
		}
		cond.put("companyCode", companyCode);
//		cond.put("reserveProNo", reserveProNo);
//		cond.put("remouldBatchCode", remouldBatchCode);
		Map<String, Object> params1 = new HashMap<String, Object>();
		params1.put("proName", 1);
		params1.put("proNo", 1);
		List<Map<String, Object>> proList = peStatisticsMongoDao.groupList(params1, cond);
		if(xList.size()==0){
			// X
			Map<String, Object> params2 = new HashMap<String, Object>();
			params2.put(xField, 1);//"OLD_AMULBOX_CLASS" 
			xList = peStatisticsMongoDao.groupList(params2, cond);
		}
		if(yList.size()==0){
			// Y
			Map<String, Object> params3 = new HashMap<String, Object>();
			params3.put(yField, 1);// 方案"REFORM_PROGRAM"
			yList = peStatisticsMongoDao.groupList(params3, cond);
		}
		Map<String, Object> params4 = new HashMap<String, Object>();
		params4.put("proNo", 1);
		params4.put(xField, 1);
		params4.put(yField, 1);
		List<Map<String, Object>> statisticsList = peStatisticsMongoDao.groupList(params4, cond);
		
		for (Iterator iterator = proList.iterator(); iterator.hasNext();) {
			Map<String, Object> map = (Map<String, Object>) iterator.next();
			String proNo = (String)map.get("proNo");
			List<Map<String, Object>> xData=new ArrayList<Map<String, Object>>();
			for (Iterator iterator2 = xList.iterator(); iterator2.hasNext();) {
				Map<String, Object> map2 = (Map<String, Object>) iterator2
						.next();
				String x=(String)map2.get(xField);
				List<Map<String, Object>> yData=new ArrayList<Map<String, Object>>();
				for (Iterator iterator3 = yList.iterator(); iterator3.hasNext();) {
					Map<String, Object> map3 = (Map<String, Object>) iterator3
							.next();
					String y=(String)map3.get(yField);
					Map<String, Object> condParams = new HashMap<String, Object>();
					condParams.put("proNo", proNo);
					condParams.put(xField, x);
					condParams.put(yField, y);
//					long cnt = peStatisticsMongoDao.queryCount(params4);
					Map<String, Object> yItem=new HashMap<String, Object>();
					yItem.put(yField, y);
					yItem.put("count", findCount(statisticsList, condParams));
					yData.add(yItem);
				}
				Map<String, Object> xItem = new HashMap<String, Object>();
				xItem.put(xField, x);
				xItem.put("data", yData);
				xData.add(xItem);
			}
			map.put("data", xData);
		}
		return proList;
	}
	
	private long findCount(List<Map<String, Object>> list, Map<String, Object> condParams){
		for (Iterator iterator = list.iterator(); iterator.hasNext();) {
			Map<String, Object> map = (Map<String, Object>) iterator.next();
			boolean fail=false;
			for (Iterator iterator2 = condParams.keySet().iterator(); iterator2.hasNext();) {
				String field = (String) iterator2
						.next();
				if(!map.get(field).toString().equals(condParams.get(field).toString())){
					fail=true;
				}
			}
			if(!fail){
				long cnt=0l;
				try {
					cnt = ((Double)map.get("total")).longValue();
				} catch (Exception e) {
					e.printStackTrace();
				}
				return cnt;
			}
		}
		return 0l;
	}
	
}
