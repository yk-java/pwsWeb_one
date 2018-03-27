package com.glens.pwCloudOs.weixin.dao;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import com.glens.eap.platform.core.annotation.MybatisNamespaceProcessor;
import com.glens.eap.platform.framework.dao.impl.EAPAbstractDao;
import com.glens.pwCloudOs.weixin.bean.WeiXinToken;
import com.glens.pwCloudOs.weixin.util.WeixinUtils;

@MybatisNamespaceProcessor(value = "com.glens.pwCloudOs.weixin.dao.WeixinSyncLogMapper")
public class WeixinDao extends EAPAbstractDao {

	private MongoTemplate mongoTemplate;

	private static Log logger = LogFactory.getLog(WeixinDao.class);

	public void setMongoTemplate(MongoTemplate mongoTemplate) {
		this.mongoTemplate = mongoTemplate;
	}

	public MongoTemplate getMongoTemplate() {
		return mongoTemplate;
	}

	public WeiXinToken getWeixinToken() {
		WeiXinToken token = mongoTemplate.findOne(
				new Query(Criteria.where("wid").is(1)), WeiXinToken.class);
		if (token == null) {
			token = WeixinUtils.getAccessToken();
			token.setWid(1);
			token.setCreateTime(System.currentTimeMillis());
			mongoTemplate.insert(token);
		} else {
			long currentTime = System.currentTimeMillis();
			long interval = currentTime - token.getCreateTime();
			if (interval / 1000 > token.getExpiresIn()) {
				token = WeixinUtils.getAccessToken();
				Update update = new Update();
				update.set("createTime", System.currentTimeMillis());
				update.set("accessToken", token.getAccessToken());
				update.set("expiresIn", token.getExpiresIn());
				mongoTemplate.upsert(new Query(Criteria.where("wid").is(1)),
						update, WeiXinToken.class);
			}
		}

		return token;
	}

	public void getSyncInfo(String taskCode) {

	}
}
