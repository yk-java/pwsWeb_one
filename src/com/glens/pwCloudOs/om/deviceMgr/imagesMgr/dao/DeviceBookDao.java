package com.glens.pwCloudOs.om.deviceMgr.imagesMgr.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;
import org.springframework.data.mongodb.core.CollectionCallback;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;







import com.glens.pwCloudOs.om.deviceMgr.imagesMgr.vo.DeviceBookVo;
import com.glens.pwCloudOs.om.deviceMgr.imagesMgr.vo.DeviceVo;
import com.glens.pwCloudOs.om.deviceMgr.imagesMgr.web.DeviceBookForm;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MongoException;
import com.mongodb.WriteResult;

public class DeviceBookDao {
	private MongoTemplate mongoTemplae;

	
	
	public List<DeviceBookVo> getList(String companyCode,String proNo,String mctCode,String deviceObjCode){
		Query query = new Query();
		Criteria criteria = Criteria.where("deviceObjCode").is(deviceObjCode);
		//criteria.and("proNo").is(proNo);
		//criteria.and("mctCode").is(mctCode);
		//criteria.and("deviceObjCode").is(deviceObjCode);
		query.addCriteria(criteria);
		List<DeviceBookVo> res=new ArrayList<DeviceBookVo>();
		try {
			res = this.mongoTemplae.find(query, DeviceBookVo.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}
	
	//量为石
	public List<DeviceVo> getDeviceList(String proNo){
		Query query = new Query();
		Criteria criteria = Criteria.where("proNo").is(proNo);
		criteria.and("status").is("1");
		query.addCriteria(criteria);
	
		List<DeviceVo> res=new ArrayList<DeviceVo>();
		try {
			res = this.mongoTemplae.find(query, DeviceVo.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}
	//所有
		public List<DeviceVo> getAllDeviceList(){
			Query query = new Query();
			Criteria criteria = Criteria.where("status").is("0");
			//criteria.and("status").is("1");
			query.addCriteria(criteria);
		
			List<DeviceVo> res=new ArrayList<DeviceVo>();
			try {
				res = this.mongoTemplae.find(query, DeviceVo.class);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return res;
		}
		//单个设备
		public List<DeviceVo> getObje(String deciceCode){
			Query query = new Query();
			Criteria criteria = Criteria.where("deviceObjCode").is(deciceCode);
			//criteria.and("status").is("1");
			query.addCriteria(criteria);
		
			List<DeviceVo> res=new ArrayList<DeviceVo>();
			try {
				res = this.mongoTemplae.find(query, DeviceVo.class);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return res;
		}
	//其他
	public List<DeviceVo> getDeviceList1(String proNo){
		Query query = new Query();
		Criteria criteria = Criteria.where("proNo").in(proNo);
		query.addCriteria(criteria);
		List<DeviceVo> res=new ArrayList<DeviceVo>();
		try {
			res = this.mongoTemplae.find(query, DeviceVo.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}
	
	
	
	public int updatePic(final String objCode,final String status) {
		int row = mongoTemplae.execute("PM_MCT_LIST", new CollectionCallback<Integer>() {

			@Override
			public Integer doInCollection(DBCollection collection)
					throws MongoException, DataAccessException {
				DBObject query = new BasicDBObject();
		
				query.put("deviceObjCode",objCode);
				//query.put("COMPANY_CODE",params.get("COMPANY_CODE"));
			
				// pageResult
				DBObject set = new BasicDBObject();
				DBObject valParams = new BasicDBObject();
				//valParams.put("deviceObjCode", params.get("deviceObjCode"));
				
				valParams.put("pic", status);
				//valParams.put("POLICE_PIC", params.get("POLICE_PIC"));
				set.put("$set", valParams);
				WriteResult wRes = collection.updateMulti(query, set);
				int row = wRes.getN();
				return row;
			}
			
		});
		return row;
	}
	
	
	
	
	public boolean addImg(DeviceBookVo vo){
		
		
		
		try {
			this.mongoTemplae.insert(vo);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}
		
		
	}
	
	public boolean deleteImg(String picVisitid){
		Query query = new Query();
		Criteria criteria = Criteria.where("picVisitid").is(picVisitid);
		query.addCriteria(criteria);
		try {
			this.mongoTemplae.remove(query, DeviceBookVo.class);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	
	
	
	
	
	public MongoTemplate getMongoTemplae() {
		return mongoTemplae;
	}

	public void setMongoTemplae(MongoTemplate mongoTemplae) {
		this.mongoTemplae = mongoTemplae;
	}
	
}
