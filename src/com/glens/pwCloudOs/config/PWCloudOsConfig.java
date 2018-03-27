/**
 * @Title: PWCloudOsConfig.java
 * @Package com.glens.pwCloudOs.config
 * @Description: TODO
 * Copyright: Copyright (c) 2016 
 * Company:南京量为石信息科技有限公司
 * @author 
 * @date 2016-7-19 下午2:22:20
 * @version V1.0
 */

package com.glens.pwCloudOs.config;

/**
 * 
 * 
 * @author
 * @version V1.0
 */

public class PWCloudOsConfig {

	private static PWCloudOsConfig _instance;

	private String prefix;

	private String fileServerUploadUrl;

	private String tmpfileHome;

	private String deleteFileUrl;

	private String uploadMergeBase64ImgUrl;

	private String batchUploadBase64ImgUrl;

	private String downloadUrl;

	private String geocoderUrl;

	private String zipFiles;

	private String wsUrl;

	private String pushEmployeeToOAUrl;

	private String getExpensesFromOAUrl;

	private String updateProjectUrl;

	private String fileUrl;

	public String getZipFiles() {
		return zipFiles;
	}

	public void setZipFiles(String zipFiles) {
		this.zipFiles = zipFiles;
	}

	public static PWCloudOsConfig get_instance() {
		return _instance;
	}

	public static void set_instance(PWCloudOsConfig _instance) {
		PWCloudOsConfig._instance = _instance;
	}

	public String getDeleteFileUrl() {
		return deleteFileUrl;
	}

	public void setDeleteFileUrl(String deleteFileUrl) {
		this.deleteFileUrl = deleteFileUrl;
	}

	private PWCloudOsConfig() {
	};

	public static synchronized PWCloudOsConfig getInstance() {

		if (_instance == null) {

			_instance = new PWCloudOsConfig();
		}

		return _instance;
	}

	public String getFileServerUploadUrl() {
		return fileServerUploadUrl;
	}

	public void setFileServerUploadUrl(String fileServerUploadUrl) {
		this.fileServerUploadUrl = fileServerUploadUrl;
	}

	public String getTmpfileHome() {
		return tmpfileHome;
	}

	public void setTmpfileHome(String tmpfileHome) {
		this.tmpfileHome = tmpfileHome;
	}

	public String getUploadMergeBase64ImgUrl() {
		return uploadMergeBase64ImgUrl;
	}

	public void setUploadMergeBase64ImgUrl(String uploadMergeBase64ImgUrl) {
		this.uploadMergeBase64ImgUrl = uploadMergeBase64ImgUrl;
	}

	public String getUpdateProjectUrl() {
		return updateProjectUrl;
	}

	public void setUpdateProjectUrl(String updateProjectUrl) {
		this.updateProjectUrl = updateProjectUrl;
	}

	public String getDownloadUrl() {
		return downloadUrl;
	}

	public void setDownloadUrl(String downloadUrl) {
		this.downloadUrl = downloadUrl;
	}

	public String getGeocoderUrl() {
		return geocoderUrl;
	}

	public void setGeocoderUrl(String geocoderUrl) {
		this.geocoderUrl = geocoderUrl;
	}

	public String getBatchUploadBase64ImgUrl() {
		return batchUploadBase64ImgUrl;
	}

	public void setBatchUploadBase64ImgUrl(String batchUploadBase64ImgUrl) {
		this.batchUploadBase64ImgUrl = batchUploadBase64ImgUrl;
	}

	public String getWsUrl() {
		return wsUrl;
	}

	public void setWsUrl(String wsUrl) {
		this.wsUrl = wsUrl;
	}

	public String getPushEmployeeToOAUrl() {
		return pushEmployeeToOAUrl;
	}

	public void setPushEmployeeToOAUrl(String pushEmployeeToOAUrl) {
		this.pushEmployeeToOAUrl = pushEmployeeToOAUrl;
	}

	public String getGetExpensesFromOAUrl() {
		return getExpensesFromOAUrl;
	}

	public void setGetExpensesFromOAUrl(String getExpensesFromOAUrl) {
		this.getExpensesFromOAUrl = getExpensesFromOAUrl;
	}

	public String getFileUrl() {
		return fileUrl;
	}

	public void setFileUrl(String fileUrl) {
		this.fileUrl = fileUrl;
	}

	public String getPrefix() {
		return prefix;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

}
