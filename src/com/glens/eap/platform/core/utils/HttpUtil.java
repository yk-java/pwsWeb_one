package com.glens.eap.platform.core.utils;

import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.util.EntityUtils;

public class HttpUtil {

	public static class SSLClient extends DefaultHttpClient {
		public SSLClient() throws Exception {
			super();
			SSLContext ctx = SSLContext.getInstance("TLS");
			X509TrustManager tm = new X509TrustManager() {
				@Override
				public void checkClientTrusted(X509Certificate[] chain,
						String authType) throws CertificateException {
				}

				@Override
				public void checkServerTrusted(X509Certificate[] chain,
						String authType) throws CertificateException {
				}

				@Override
				public X509Certificate[] getAcceptedIssuers() {
					return null;
				}
			};
			ctx.init(null, new TrustManager[] { tm }, null);
			SSLSocketFactory ssf = new SSLSocketFactory(ctx,
					SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
			ClientConnectionManager ccm = this.getConnectionManager();
			SchemeRegistry sr = ccm.getSchemeRegistry();
			sr.register(new Scheme("https", 443, ssf));
		}
	}

	public static String doPost(String url, Map<String, String> map,
			String charset) {
		HttpClient httpClient = null;
		HttpPost httpPost = null;
		String result = null;
		try {
			httpClient = new SSLClient();
			httpPost = new HttpPost(url);
			// 设置参数
			List<NameValuePair> list = new ArrayList<NameValuePair>();
			Iterator iterator = map.entrySet().iterator();
			while (iterator.hasNext()) {
				Entry<String, String> elem = (Entry<String, String>) iterator
						.next();
				list.add(new BasicNameValuePair(elem.getKey(), elem.getValue()));
			}
			if (list.size() > 0) {
				UrlEncodedFormEntity entity = new UrlEncodedFormEntity(list,
						charset);
				httpPost.setEntity(entity);
			}
			HttpResponse response = httpClient.execute(httpPost);
			if (response != null) {
				HttpEntity resEntity = response.getEntity();
				if (resEntity != null) {
					result = EntityUtils.toString(resEntity, charset);
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return result;
	}

	public static String doPost(String url, String params, String charset) {
		HttpClient httpClient = null;
		HttpPost httpPost = null;
		String result = null;
		try {
			httpClient = new SSLClient();
			httpClient.getParams().setIntParameter(
					CoreConnectionPNames.SO_TIMEOUT, 5000);
			httpClient.getParams().setIntParameter(
					CoreConnectionPNames.CONNECTION_TIMEOUT, 5000);
			httpPost = new HttpPost(url);
			// 设置参数
			HttpEntity entity = new StringEntity(params, charset);
			httpPost.setEntity(entity);
			HttpResponse response = httpClient.execute(httpPost);
			if (response != null) {
				HttpEntity resEntity = response.getEntity();
				if (resEntity != null) {
					result = EntityUtils.toString(resEntity, charset);
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return result;
	}

	/**
	 * 发送get请求
	 * 
	 * @param url
	 *            链接地址
	 * @param charset
	 *            字符编码，若为null则默认utf-8
	 * @return
	 */
	public static String doGet(String url, String charset) {
		if (null == charset) {
			charset = "utf-8";
		}
		HttpClient httpClient = null;
		HttpGet httpGet = null;
		String result = null;

		try {
			httpClient = new SSLClient();
			httpClient.getParams().setIntParameter(
					CoreConnectionPNames.SO_TIMEOUT, 5000);
			httpClient.getParams().setIntParameter(
					CoreConnectionPNames.CONNECTION_TIMEOUT, 5000);
			httpGet = new HttpGet(url);

			HttpResponse response = httpClient.execute(httpGet);
			if (response != null) {
				HttpEntity resEntity = response.getEntity();
				if (resEntity != null) {
					result = EntityUtils.toString(resEntity, charset);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	public static String executeGet(String url, String charset) {
		if (null == charset) {
			charset = "utf-8";
		}
		HttpClient httpClient = null;
		HttpGet httpGet = null;
		String result = null;

		try {
			httpClient = new DefaultHttpClient();
			httpGet = new HttpGet(url);

			HttpResponse response = httpClient.execute(httpGet);
			if (response != null) {
				HttpEntity resEntity = response.getEntity();
				if (resEntity != null) {
					result = EntityUtils.toString(resEntity, charset);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	public static String executePost(String url, String params, String charset) {
		HttpClient httpClient = null;
		HttpPost httpPost = null;
		String result = null;
		try {
			httpClient = new DefaultHttpClient();
			httpPost = new HttpPost(url);
			// 设置参数
			HttpEntity entity = new StringEntity(params, charset);
			httpPost.setEntity(entity);
			HttpResponse response = httpClient.execute(httpPost);
			if (response != null) {
				HttpEntity resEntity = response.getEntity();
				if (resEntity != null) {
					result = EntityUtils.toString(resEntity, charset);
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return result;
	}

	public static String executePost(String url, Map<String, String> map,
			String charset) throws Exception {
		HttpClient httpClient = null;
		HttpPost httpPost = null;
		String result = null;
		httpClient = new DefaultHttpClient();
		httpPost = new HttpPost(url);
		// 设置参数
		List<NameValuePair> list = new ArrayList<NameValuePair>();
		Iterator iterator = map.entrySet().iterator();
		while (iterator.hasNext()) {
			Entry<String, String> elem = (Entry<String, String>) iterator
					.next();
			list.add(new BasicNameValuePair(elem.getKey(), elem.getValue()));
		}
		if (list.size() > 0) {
			UrlEncodedFormEntity entity = new UrlEncodedFormEntity(list,
					charset);
			httpPost.setEntity(entity);
		}
		HttpResponse response = httpClient.execute(httpPost);
		if (response != null) {
			HttpEntity resEntity = response.getEntity();
			if (resEntity != null) {
				result = EntityUtils.toString(resEntity, charset);
			}
		}
		return result;
	}

	public static String executeHttpsPost(String url, Map<String, String> map,
			String charset) throws Exception {
		HttpClient httpClient = null;
		HttpPost httpPost = null;
		String result = null;
		httpClient = new SSLClient();
		httpPost = new HttpPost(url);
		// 设置参数
		List<NameValuePair> list = new ArrayList<NameValuePair>();
		Iterator iterator = map.entrySet().iterator();
		while (iterator.hasNext()) {
			Entry<String, String> elem = (Entry<String, String>) iterator
					.next();
			list.add(new BasicNameValuePair(elem.getKey(), elem.getValue()));
		}
		if (list.size() > 0) {
			UrlEncodedFormEntity entity = new UrlEncodedFormEntity(list,
					charset);
			httpPost.setEntity(entity);
		}
		HttpResponse response = httpClient.execute(httpPost);
		if (response != null) {
			HttpEntity resEntity = response.getEntity();
			if (resEntity != null) {
				result = EntityUtils.toString(resEntity, charset);
			}
		}
		return result;
	}

	public static String doYSPost(String url, String params, String charset) {
		HttpClient httpClient = null;
		HttpPost httpPost = null;
		String result = null;
		try {
			httpClient = new SSLClient();
			httpPost = new HttpPost(url);
			// 设置参数
			HttpEntity entity = new StringEntity(params, charset);
			httpPost.setHeader("Content-Type",
					"application/x-www-form-urlencoded");
			httpPost.setEntity(entity);
			HttpResponse response = httpClient.execute(httpPost);
			if (response != null) {
				HttpEntity resEntity = response.getEntity();
				if (resEntity != null) {
					result = EntityUtils.toString(resEntity, charset);
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return result;
	}
}
