package com.glens.pwCloudOs.pm.scene.receiver;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * 数据存储
 * 
 * @author Administrator
 *
 */
public class DeviceDataMemery {
	private static Map<String, List<Object>> data = new HashMap<String, List<Object>>();

	/**
	 * 创建集合
	 * 
	 * @param key
	 */
	public static void createCollection(String key) {
		synchronized (data) {
			Object collection = data.get(key);
			if (collection == null)
				data.put(key, new ArrayList<Object>());
		}
	}

	/**
	 * 删除集合
	 * 
	 * @param key
	 */
	public static void removeCollection(String key) {
		synchronized (data) {
			data.remove(key);
		}
	}

	/**
	 * 查询
	 * 
	 * @param key
	 * @param verifyHandler
	 * @return
	 */
	public static List<Object> find(String key, VerifyHandler verifyHandler) {
		List<Object> res = new ArrayList<Object>();
		synchronized (data) {
			List<Object> collection = (List<Object>) data.get(key);
			if (collection == null) {
				return null;
			}
			for (Iterator iterator = collection.iterator(); iterator.hasNext();) {
				Object item = iterator.next();
				if (verifyHandler.verify(item)) {
					res.add(item);
				}
			}
		}
		return res;
	}

	/**
	 * 新增
	 * 
	 * @param key
	 * @param item
	 */
	public static void insert(String key, Object item) {
		synchronized (data) {
			List<Object> collection = (List<Object>) data.get(key);
			if (collection == null) {
				collection = new ArrayList<Object>();
				data.put(key, collection);
			}
			collection.add(item);
		}
	}

	/**
	 * 删除
	 * 
	 * @param key
	 * @param verifyHandler
	 */
	public static void remove(String key, VerifyHandler verifyHandler) {
		synchronized (data) {
			List<Object> collection = (List<Object>) data.get(key);
			if (collection == null)
				return;
			for (Iterator iterator = collection.iterator(); iterator.hasNext();) {
				Object item = iterator.next();
				if (verifyHandler.verify(item)) {
					iterator.remove();
				}
			}
		}
	}

	/**
	 * 修改
	 * 
	 * @param key
	 * @param verifyHandler
	 * @param updateHandler
	 */
	public static void update(String key, VerifyHandler verifyHandler,
			UpdateHandler updateHandler) {
		synchronized (data) {
			List<Object> collection = (List<Object>) data.get(key);
			for (Iterator iterator = collection.iterator(); iterator.hasNext();) {
				Object item = iterator.next();
				if (verifyHandler.verify(item)) {
					updateHandler.update(item);
				}
			}
		}
	}
}
