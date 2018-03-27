package com.glens.pwCloudOs.cj.base.service;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.UUID;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.glens.eap.platform.framework.service.impl.EAPAbstractService;
import com.glens.eap.platform.framework.web.ResponseConstants;
import com.glens.pwCloudOs.cj.base.dao.CjAttachmentDao;
import com.glens.pwCloudOs.cj.base.vo.CjAttachment;
import com.glens.pwCloudOs.cj.base.web.CjAttachmentForm;

public class CjAttachmentService extends EAPAbstractService {

	private static final float KB = 1024.0f;

	public List queryCjAttachmentList(CjAttachmentForm form) {
		CjAttachmentDao cjDao = (CjAttachmentDao) this.dao;
		return cjDao.queryForList(form.toMap());
	}

	public CjAttachment getCjAttachment(String attachmentAttachId) {
		CjAttachmentDao dao = (CjAttachmentDao) this.dao;
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("attachmentAttachId", attachmentAttachId);
		CjAttachment res = (CjAttachment) dao.findById(map);
		return res;
	}

	public Map saveCjAttachment(CjAttachment cjAttachment) {
		CjAttachmentDao dao = (CjAttachmentDao) this.dao;
		String attachmentId = UUID.randomUUID().toString()
				.replaceAll("\\-", "");
		cjAttachment.setAttachmentId(attachmentId);
		cjAttachment.setSysCreateTime(new Date());

		Map result = new HashMap();
		if (dao.insert(cjAttachment)) {
			result.put("statusCode", ResponseConstants.OK);
			result.put("resultMsg", "返回结果成功");
			result.put("attachmentId", attachmentId);
		} else {
			result.put("statusCode", ResponseConstants.INSERT_DATA_ERROR);
			result.put("resultMsg", "新增附件信息失败");
		}
		return result;
	}

	public Map saveCjAttachment(List<CjAttachment> cjAttachmentList) {
		CjAttachmentDao dao = (CjAttachmentDao) this.dao;

		for (CjAttachment cjAttachment : cjAttachmentList) {
			dao.insert(cjAttachment);
		}

		Map result = new HashMap();
		result.put("statusCode", ResponseConstants.OK);
		result.put("resultMsg", "返回结果成功");
		return result;
	}

	public Map mobileSync(String systemPath, String attachmentJson) {

		List<CjAttachment> cjAttachmentList = new ArrayList<CjAttachment>();

		ResourceBundle resource = ResourceBundle.getBundle("cjAttachment");
		String uploadPath = resource.getString("upload");
		String attachmentId = "", savePathString = "";

		if (attachmentJson != null && !attachmentJson.isEmpty()) {
			JSONArray jsonArray = JSONArray.parseArray(attachmentJson);
			CjAttachment cjAttachment = null;
			for (int i = 0; i < jsonArray.size(); i++) {
				JSONObject jobject = jsonArray.getJSONObject(i);

				attachmentId = UUID.randomUUID().toString()
						.replaceAll("\\-", "");

				savePathString = systemPath + uploadPath
						+ jobject.getString("attachmentAttachRange") + "/";

				int fileSize = saveToImgByStr(jobject.getString("imgStr"),
						savePathString,
						attachmentId + "." + jobject.getString("fileType"));

				if (fileSize != -1) {
					cjAttachment = new CjAttachment();
					cjAttachment.setAttachmentId(attachmentId);
					cjAttachment.setAttachmentAttachId(jobject
							.getString("attachmentAttachId"));
					cjAttachment.setAttachmentName(attachmentId + "."
							+ jobject.getString("fileType"));
					cjAttachment.setFileName(jobject.getString("fileName"));
					cjAttachment.setFileSize(fileSize);
					cjAttachment.setFileType(jobject.getString("fileType"));
					cjAttachment.setStorageLocation(uploadPath
							+ jobject.getString("attachmentAttachRange"));
					cjAttachment.setAttachmentAttachRange(jobject
							.getString("attachmentAttachRange"));
					cjAttachment.setSysCreateTime(new Date());
					cjAttachmentList.add(cjAttachment);
				}
			}
		}

		CjAttachmentDao dao = (CjAttachmentDao) this.dao;

		for (CjAttachment cjAttachment : cjAttachmentList) {
			dao.insert(cjAttachment);
		}

		Map result = new HashMap();
		result.put("statusCode", ResponseConstants.OK);
		result.put("resultMsg", "返回结果成功");
		return result;

	}

	public Map delCjAttachmentById(List<String> attachmentIdList) {
		CjAttachmentDao cjDao = (CjAttachmentDao) this.dao;
		cjDao.delByIdList(attachmentIdList);
		Map result = new HashMap();
		result.put("statusCode", ResponseConstants.OK);
		result.put("resultMsg", "返回结果成功");
		return result;
	}

	public Map delCjAttachmentByObjId(String attachmentAttachId) {
		CjAttachmentDao cjDao = (CjAttachmentDao) this.dao;
		cjDao.delByObjId(attachmentAttachId);
		Map result = new HashMap();
		result.put("statusCode", ResponseConstants.OK);
		result.put("resultMsg", "返回结果成功");
		return result;
	}

	/**
	 * 将接收的字符串转换成图片保存
	 * 
	 * @param imgStr
	 *            二进制流转换的字符串
	 * @param imgPath
	 *            图片的保存路径
	 * @param imgName
	 *            图片的名称
	 * @return !=-1：保存正常 -1：保存失败
	 */
	public static int saveToImgByStr(String imgStr, String imgPath,
			String imgName) {
		int fileSize = 0;
		if (imgStr != null && imgStr.length() > 0) {
			try {
				// 将字符串转换成二进制，用于显示图片
				// 将上面生成的图片格式字符串 imgStr，还原成图片显示
				byte[] imgByte = hex2byte(imgStr);

				InputStream in = new ByteArrayInputStream(imgByte);

				File file = new File(imgPath, imgName);// 可以是任何图片格式.jpg,.png等
				FileOutputStream fos = new FileOutputStream(file);

				fileSize = imgByte.length;

				byte[] b = new byte[imgByte.length + 1024];
				int nRead = 0;
				while ((nRead = in.read(b)) != -1) {
					fos.write(b, 0, nRead);
				}
				fos.flush();
				fos.close();
				in.close();
			} catch (Exception e) {
				fileSize = -1;
				e.printStackTrace();
			} finally {
			}
		}
		return fileSize;
	}

	/**
	 * 字符串转二进制
	 * 
	 * @param str
	 *            要转换的字符串
	 * @return 转换后的二进制数组
	 */
	public static byte[] hex2byte(String str) { // 字符串转二进制
		if (str == null)
			return null;
		str = str.trim();
		int len = str.length();
		if (len == 0 || len % 2 == 1)
			return null;
		byte[] b = new byte[len / 2];
		try {
			for (int i = 0; i < str.length(); i += 2) {
				b[i / 2] = (byte) Integer
						.decode("0X" + str.substring(i, i + 2)).intValue();
			}
			return b;
		} catch (Exception e) {
			return null;
		}
	}
}
