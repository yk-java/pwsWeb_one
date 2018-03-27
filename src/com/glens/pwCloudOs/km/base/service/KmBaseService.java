package com.glens.pwCloudOs.km.base.service;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.glens.eap.platform.afinal.FinalHttp;
import com.glens.eap.platform.afinal.http.AjaxParams;
import com.glens.eap.platform.core.utils.FileUtil;
import com.glens.eap.platform.core.utils.StringUtil;
import com.glens.eap.platform.es.impl.ElasticSearchServiceImpl;
import com.glens.eap.platform.framework.beans.PageBean;
import com.glens.eap.platform.framework.codeCenter.CodeWorker;
import com.glens.eap.platform.framework.context.EAPContext;
import com.glens.eap.platform.framework.service.impl.EAPAbstractService;
import com.glens.pwCloudOs.config.PWCloudOsConfig;
import com.glens.pwCloudOs.km.attach.dao.KmAttachmentDao;
import com.glens.pwCloudOs.km.base.dao.KmBaseDao;
import com.glens.pwCloudOs.km.base.vo.EskmBase;
import com.glens.pwCloudOs.km.base.vo.KmBaseVo;
import com.glens.pwCloudOs.km.base.web.KmBaseForm;
import com.glens.pwCloudOs.km.download.dao.KmStudyDownloadDao;
import com.glens.pwCloudOs.km.read.dao.KmStudyReadDao;

public class KmBaseService extends EAPAbstractService {
	private static final float KB = 1024.0f;

	private ElasticSearchServiceImpl esService;

	/**
	 * 知识库附件
	 */
	private KmAttachmentDao kmAttachmentDao;

	/**
	 * 知识库下载
	 */
	private KmStudyDownloadDao kmStudyDownloadDao;

	/**
	 * 知识库阅读
	 */
	private KmStudyReadDao kmStudyReadDao;

	private FinalHttp httpClient = new FinalHttp();

	public FinalHttp getHttpClient() {
		return httpClient;
	}

	public void setHttpClient(FinalHttp httpClient) {
		this.httpClient = httpClient;
	}

	public List getTop5(Object parameters) {
		// TODO Auto-generated method stub

		KmBaseDao dao = (KmBaseDao) getDao();
		return dao.getTop5(parameters);
	}

	public List getAttachments(Object parameters) {
		// TODO Auto-generated method stub

		KmBaseDao dao = (KmBaseDao) getDao();
		
	
		return dao.getAttachments(parameters);
	}

	public List catalogMap(Object parameters) {
		// TODO Auto-generated method stub

		KmBaseDao dao = (KmBaseDao) getDao();
		return dao.catalogMap(parameters);
	}

	public List getPersonRead(Object parameters) {
		// TODO Auto-generated method stub

		KmBaseDao dao = (KmBaseDao) getDao();
		return dao.getPersonRead(parameters);
	}

	public boolean insertReadrecord(Object parameters){
		KmBaseDao dao = (KmBaseDao) getDao();
		return dao.insertReadrecord(parameters);
	}
	
	public boolean insert(Object parameters, String root) throws IOException,
			ParseException {
		// TODO Auto-generated method stub

		KmBaseVo vo = (KmBaseVo) parameters;
		PWCloudOsConfig config = (PWCloudOsConfig) EAPContext.getContext()
				.getBean("pwcloudosConfig");
		boolean _ok = false;
		CodeWorker codeWorker = (CodeWorker) EAPContext.getContext().getBean(
				CodeWorker.SIMPLE_CODE_WORKER);
		String filecode = codeWorker.createCode("F");

		KmBaseDao dao = (KmBaseDao) getDao();
		if (vo != null && vo.getProjDoc1() != null) {

			String tmpFileHomeUrl = root + File.separator
					+ config.getTmpfileHome();
			String fileName = vo.getProjDoc1().getOriginalFilename();
			File tmpFile = new File(tmpFileHomeUrl + File.separator + fileName);
			boolean writeResult = FileUtil.writeFile(vo.getProjDoc1()
					.getInputStream(), tmpFile);

			System.out.println(config.getFileServerUploadUrl());

			if (writeResult) {
				AjaxParams params = new AjaxParams();
				params.put("file", tmpFile);
				Object content = httpClient.postSync(
						config.getPrefix()+ config.getFileServerUploadUrl(), params);
				if (content != null) {
					JSONObject jsonObj = JSONObject.parseObject(content
							.toString());
					if ("200".equals(jsonObj.get("statusCode"))) {

						JSONObject fileJsonObj = jsonObj.getJSONObject("data");
						if (fileJsonObj != null) {
							String fileCode = fileJsonObj.getString("fileCode");

							String convertFileCode = fileJsonObj
									.getString("convertFileCode");

							String fileSuffix = fileJsonObj
									.getString("fileSuffix");
							Long fileSize = fileJsonObj.getLong("fileSize");
							Map map = new HashMap();
							map.put("fileCode", filecode);
							map.put("fileName", vo.getProjDoc1()
									.getOriginalFilename());

							map.put("fileUrl", fileCode);
							map.put("dlUrl", convertFileCode);
							map.put("fileType", fileSuffix);
							map.put("fileSize", fileSize);
							dao.insertAttachment(map);

						}
					}
				}
				if (tmpFile.exists()) {
					tmpFile.delete();
				}
			}
		}
		if (vo != null && vo.getProjDoc2() != null) {

			String tmpFileHomeUrl = root + File.separator
					+ config.getTmpfileHome();
			String fileName = vo.getProjDoc2().getOriginalFilename();
			File tmpFile = new File(tmpFileHomeUrl + File.separator + fileName);
			boolean writeResult = FileUtil.writeFile(vo.getProjDoc2()
					.getInputStream(), tmpFile);
			if (writeResult) {
				AjaxParams params = new AjaxParams();
				params.put("file", tmpFile);
				Object content = httpClient.postSync(
						config.getPrefix()+ config.getFileServerUploadUrl(), params);
				if (content != null) {
					JSONObject jsonObj = JSONObject.parseObject(content
							.toString());
					if ("200".equals(jsonObj.get("statusCode"))) {

						JSONObject fileJsonObj = jsonObj.getJSONObject("data");
						if (fileJsonObj != null) {
							String fileCode = fileJsonObj.getString("fileCode");

							String convertFileCode = fileJsonObj
									.getString("convertFileCode");

							String fileSuffix = fileJsonObj
									.getString("fileSuffix");
							Long fileSize = fileJsonObj.getLong("fileSize");
							Map map = new HashMap();
							map.put("fileCode", filecode);
							map.put("fileName", vo.getProjDoc2()
									.getOriginalFilename());
							map.put("fileUrl", fileCode);
							map.put("dlUrl", convertFileCode);
							map.put("fileType", fileSuffix);
							map.put("fileSize", fileSize);
							dao.insertAttachment(map);
						}
					}
				}
				if (tmpFile.exists()) {
					tmpFile.delete();
				}
			}
		}

		vo.setFileCode(filecode);

		_ok = dao.insertKmBase(vo);

		if (_ok) {
			Long rowid = vo.getRowid();
			EskmBase eb = new EskmBase();
			eb.setBtext(vo.getBtextContent());
			eb.setCatalogName(vo.getCatalogName());
			eb.setFileTitle(vo.getFileTitle());
			eb.setKeywords(vo.getKeywords());
			eb.setRowid(rowid);
			eb.setCreateUser(vo.getEmployeeName());
			eb.setStatus1("1");
			eb.setRemark(vo.getRemarks());
			SimpleDateFormat ds=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String date=ds.format(new Date());
			
			eb.setCreateTime(date);

			esService.addIndex(eb);
			/*
			 * eb.setKeywords(vo.getKeywords()); esService.updateIndex(eb);
			 * eb.setRowid(rowid); esService.delIndex(eb);
			 * esService.query("北京");
			 */

		}

		return _ok;
	}

	public List queryKmBaseList(Map map) {
		KmBaseDao dao = (KmBaseDao) getDao();
		// TODO Auto-generated method stub
		return dao.queryKmBaseList(map);
	}

	public List<String> querySubCatalogList(String catalogCode) {
		KmBaseDao dao = (KmBaseDao) getDao();

		return dao.querySubCatalogList(catalogCode);
	}

	public KmBaseVo queryKbBase(long rowid) {
		KmBaseDao dao = (KmBaseDao) getDao();
		KmBaseVo vo = dao.queryKbBase(rowid);

		Map<String, Object> paramsMap = new HashMap<String, Object>();
		if (StringUtil.isNotNull(vo.getFileCode())) {
			paramsMap.put("fileCode", vo.getFileCode());
			dao.updateNum(paramsMap);
		}

		vo.getAttachList().addAll(
				kmAttachmentDao.queryAttachList(vo.getFileCode()));

		return vo;
	}

	public PageBean queryKmBasekeyWordList(Map<String, Object> m,int pageSize,int pageNo,String fullTextSearch) {
		String fileTitle = (String) m.get("fileTitle");
		PageBean list = esService.queryForPage(fileTitle, pageSize, pageNo, fullTextSearch, "");
		return list;
	}
	
	public List queryKmBasekeyWordList(Map<String, Object> m) {
		String fileTitle = (String) m.get("fileTitle");
		List list = esService.query(fileTitle);
		return list;
	}

	public int delete(Object parameters, Long rowid) {

		KmBaseDao dao = (KmBaseDao) getDao();
		dao.deleteAttachment(parameters);
		EskmBase eb = new EskmBase();
		eb.setRowid(rowid);
		esService.delIndex(eb);
		return dao.delete(parameters);
	}

	// 下载
	public boolean insertDownStudy(Object parameters) {
		// TODO Auto-generated method stub
		KmBaseDao dao = (KmBaseDao) getDao();
		
		// 更新附件下载数量
		dao.updateDownStudyNum(parameters);
		// 更新文档的下载量
		dao.updateNum(parameters);

		return dao.insertDownStudy(parameters);
	}

	// 点击量增加
	public void updateDownNum(Object params) {
		KmBaseDao dao = (KmBaseDao) getDao();
		dao.updateDownNum(params);
	}
	

	public boolean updatePublishStatus(Object params,KmBaseForm form) {
		KmBaseDao dao = (KmBaseDao) getDao();
		EskmBase eb = new EskmBase();
		eb.setRowid(form.getRowid());
		eb.setStatus1(form.getPublishStatus()+"");
		eb.setFileTitle(form.getFileTitle());
		eb.setCatalogName(form.getCatalogName());
		eb.setCreateTime(form.getSysCreateTime());
		eb.setKeywords(form.getKeywords());
		eb.setBtext(form.getBtext());
		eb.setCreateUser(form.getEmployeeName());
		eb.setRemark(form.getRemarks());
		esService.updateIndex(eb);
		
		boolean isSucess=dao.updatePublishStatus(params);
		return isSucess;
	}

	//修改文档基本信息
	public boolean updateKmBae(Object params,KmBaseForm form) {
		KmBaseDao dao = (KmBaseDao) getDao();
		boolean isSucess=dao.updateKmBae(params);
		
		EskmBase eb = new EskmBase();
		eb.setRowid(form.getRowid());
		eb.setStatus1(form.getPublishStatus()+"");
		eb.setFileTitle(form.getFileTitle());
		eb.setCatalogName(form.getCatalogName());
		eb.setCreateTime(form.getSysCreateTime());
		eb.setKeywords(form.getKeywords());
		eb.setBtext(form.getBtext());
		eb.setCreateUser(form.getEmployeeName());
		eb.setRemark(form.getRemarks());
		eb.setStatus1(form.getPublishStatus()+"");
		esService.updateIndex(eb);
		
		return isSucess;
	}
	
	public void setKmAttachmentDao(KmAttachmentDao kmAttachmentDao) {
		this.kmAttachmentDao = kmAttachmentDao;
	}

	public void setKmStudyDownloadDao(KmStudyDownloadDao kmStudyDownloadDao) {
		this.kmStudyDownloadDao = kmStudyDownloadDao;
	}

	public void setKmStudyReadDao(KmStudyReadDao kmStudyReadDao) {
		this.kmStudyReadDao = kmStudyReadDao;
	}

	public void setEsService(ElasticSearchServiceImpl esService) {
		this.esService = esService;
	}

}
