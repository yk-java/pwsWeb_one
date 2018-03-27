package com.glens.eap.eapAuth.client.key;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import com.alibaba.fastjson.JSON;
import com.glens.eap.eapAuth.core.key.EAPSoKey;
import com.glens.eap.eapAuth.core.key.KeyService;

/** 
 * <p>默认的秘钥信息获取实现类，该类只是一个简单的实现，非常不安全。 在生产环境，建议请使用公钥和私钥的方式对秘钥信息
 * 进行加密，避免秘钥在公网环境下泄露。请自行加强安全性。</p>
 * @author  <a href="mailto: xxx@xxx.com">作者中文名</a>
 * @version $Revision$ 
 */ 
@SuppressWarnings("deprecation")
public class DefaultKeyServiceImpl implements KeyService {

	private static Logger logger = Logger.getLogger(DefaultKeyServiceImpl.class
			.getName());

	private String eapsoServerFetchKeyUrl;
	
	private EAPSoKey eapsoKey;
	
	/**
	 * 本应用的应用id.
	 */
	private String appId;
	
	private static DefaultHttpClient httpClient = new DefaultHttpClient();
	
	public DefaultKeyServiceImpl(String eapsoServerFetchKeyUrl, String appId) {
		// TODO Auto-generated constructor stub
		super();
		this.eapsoServerFetchKeyUrl = eapsoServerFetchKeyUrl;
		this.appId = appId;
	}
	
	@Override
	public EAPSoKey findKeyByAppId(String appId) {
		// TODO Auto-generated method stub
		if (eapsoKey == null) {
			
			eapsoKey = fetchKeyFromEapsoServer();
		}
		
		return eapsoKey;
	}
	
	private EAPSoKey fetchKeyFromEapsoServer() {
		HttpPost httpPost = null;
		try {
			httpPost = new HttpPost(eapsoServerFetchKeyUrl);
			List<NameValuePair> nvps = new ArrayList<NameValuePair>();
			nvps.add(new BasicNameValuePair("appId", this.appId));
			httpPost.setEntity(new UrlEncodedFormEntity(nvps));
			HttpResponse response = httpClient.execute(httpPost);
			if (response.getStatusLine().getStatusCode() == 200) {
				HttpEntity entity = response.getEntity();
				String content = EntityUtils.toString(entity);
				EntityUtils.consume(entity);
				return JSON.parseObject(content, EAPSoKey.class);
			}
		} catch (Exception e) {
			logger.log(Level.SEVERE, "fetch eapso key from server error, the url is ["+eapsoServerFetchKeyUrl+"]", e);
		} finally {
			if (httpPost != null) {
				
				
			}
		}
		return null;
	}

	@Override
	public EAPSoKey findKeyByKeyId(String keyId) {
		// TODO Auto-generated method stub
		if (eapsoKey == null) {
			
			return this.findKeyByAppId(null);
		}
		
		return eapsoKey;
	}

}
