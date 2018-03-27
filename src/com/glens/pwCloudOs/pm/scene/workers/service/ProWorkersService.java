package com.glens.pwCloudOs.pm.scene.workers.service;

import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONObject;
import com.glens.eap.platform.afinal.FinalHttp;
import com.glens.eap.platform.afinal.http.AjaxParams;
import com.glens.eap.platform.core.beans.ValueObject;
import com.glens.eap.platform.core.utils.DateTimeUtil;
import com.glens.eap.platform.core.utils.FileUtil;
import com.glens.eap.platform.framework.beans.PageBean;
import com.glens.eap.platform.framework.codeCenter.CodeWorker;
import com.glens.eap.platform.framework.context.EAPContext;
import com.glens.eap.platform.framework.service.impl.EAPAbstractService;
import com.glens.pwCloudOs.config.PWCloudOsConfig;
import com.glens.pwCloudOs.pm.scene.workers.dao.ProWorkersDao;
import com.glens.pwCloudOs.pm.scene.workers.vo.ProWorkers;

public class ProWorkersService extends EAPAbstractService {

	private FinalHttp httpClient = new FinalHttp();

	private PWCloudOsConfig pwcloudosConfig;

	public void insert(ValueObject parameters, String root) throws Exception {
		// TODO Auto-generated method stub
		ProWorkers vo = (ProWorkers) parameters;
		PWCloudOsConfig config = (PWCloudOsConfig) EAPContext.getContext()
				.getBean("pwcloudosConfig");
		CodeWorker codeWorker = (CodeWorker) EAPContext.getContext().getBean(
				CodeWorker.SIMPLE_CODE_WORKER);
		vo.setCompanyCode("001");
		vo.setWorkerCode(codeWorker.createCode("P"));

		ProWorkersDao dao = (ProWorkersDao) getDao();

		if (vo != null) {
			if (vo.getFile1() != null) {
				String fileCode = upload(root, vo, vo.getFile1(), config);
				vo.setPhoto(config.getPrefix()
						+ String.format(pwcloudosConfig.getFileUrl(), fileCode));
			}

			if (vo.getFile2() != null) {
				String fileCode = upload(root, vo, vo.getFile2(), config);
				vo.setPic1(config.getPrefix()
						+ String.format(pwcloudosConfig.getFileUrl(), fileCode));
			}

			if (vo.getFile3() != null) {
				String fileCode = upload(root, vo, vo.getFile3(), config);
				vo.setPic2(config.getPrefix()
						+ String.format(pwcloudosConfig.getFileUrl(), fileCode));
			}

			if (vo.getFile4() != null) {
				String fileCode = upload(root, vo, vo.getFile4(), config);
				vo.setPic3(config.getPrefix()
						+ String.format(pwcloudosConfig.getFileUrl(), fileCode));
			}

			vo.setSysCreateTime(DateTimeUtil.formatDate(new Date(),
					DateTimeUtil.FORMAT_1));
			vo.setSysProcessFlag("1");
			dao.insertSelective(vo);

		}
	}

	public void updateWorkers(ValueObject parameters, String root)
			throws Exception {

		ProWorkers vo = (ProWorkers) parameters;

		PWCloudOsConfig config = (PWCloudOsConfig) EAPContext.getContext()
				.getBean("pwcloudosConfig");

		ProWorkersDao dao = (ProWorkersDao) getDao();

		if (vo != null) {

			if (vo.getFile1() != null) {
				String fileCode = upload(root, vo, vo.getFile1(), config);
				vo.setPhoto(config.getPrefix()
						+ String.format(pwcloudosConfig.getFileUrl(), fileCode));
			}

			if (vo.getFile2() != null) {
				String fileCode = upload(root, vo, vo.getFile2(), config);
				vo.setPic1(config.getPrefix()
						+ String.format(pwcloudosConfig.getFileUrl(), fileCode));

			}

			if (vo.getFile3() != null) {
				String fileCode = upload(root, vo, vo.getFile3(), config);
				vo.setPic2(config.getPrefix()
						+ String.format(pwcloudosConfig.getFileUrl(), fileCode));
			}

			if (vo.getFile4() != null) {
				String fileCode = upload(root, vo, vo.getFile4(), config);
				vo.setPic3(config.getPrefix()
						+ String.format(pwcloudosConfig.getFileUrl(), fileCode));
			}

			vo.setSysCreateTime(DateTimeUtil.formatDate(new Date(),
					DateTimeUtil.FORMAT_1));
			vo.setSysProcessFlag("1");
			dao.updateSelective(vo);

		}
	}

	private String upload(String root, ProWorkers vo, MultipartFile file,
			PWCloudOsConfig config) throws Exception {
		String tmpFileHomeUrl;
		String fileName;
		File tmpFile;
		String fileCode = "";
		tmpFileHomeUrl = root + File.separator + config.getTmpfileHome();
		fileName = file.getOriginalFilename();
		tmpFile = new File(tmpFileHomeUrl + File.separator + fileName);
		boolean writeResult = FileUtil
				.writeFile(file.getInputStream(), tmpFile);
		if (writeResult) {
			AjaxParams params = new AjaxParams();
			params.put("file", tmpFile);
			Object content = httpClient.postSync(
					config.getPrefix() + config.getFileServerUploadUrl(),
					params);
			if (content != null) {
				JSONObject jsonObj = JSONObject.parseObject(content.toString());
				if ("200".equals(jsonObj.get("statusCode"))) {
					JSONObject fileJsonObj = jsonObj.getJSONObject("data");
					if (fileJsonObj != null) {
						fileCode = fileJsonObj.getString("fileCode");
					}
				}
			}
		}
		if (tmpFile.exists()) {
			tmpFile.delete();
		}

		return fileCode;
	}

	public void deleteWorker(ValueObject parameters) throws Exception {
		// TODO Auto-generated method stub
		ProWorkers vo = (ProWorkers) parameters;
		ProWorkersDao dao = (ProWorkersDao) getDao();
		if (vo != null) {
			vo.setSysDeleteTime(DateTimeUtil.formatDate(new Date(),
					DateTimeUtil.FORMAT_1));
			vo.setSysProcessFlag("0");
			dao.updateSelective(vo);
		}
	}

	public PageBean chooseWorkersList(Map map, int currentPage, int perpage) {
		ProWorkersDao dao = (ProWorkersDao) getDao();
		PageBean pb = dao.queryForPage(map, "queryForCount1", "queryForPage1");
		return pb;
	}

	public Map get(String workerCode) {

		ProWorkersDao dao = (ProWorkersDao) getDao();
		return dao.get(workerCode);
	}

	public List queryProWorker(Map<String, Object> paramsMap) {
		// TODO Auto-generated method stub
		ProWorkersDao dao = (ProWorkersDao) getDao();
		return dao.queryProWorker(paramsMap);
	}

	public Map queryProWorkerByCardNo(Map<String, Object> paramsMap) {
		// TODO Auto-generated method stub
		ProWorkersDao dao = (ProWorkersDao) getDao();
		return dao.queryProWorkerByCardNo(paramsMap);
	}

	public void setPwcloudosConfig(PWCloudOsConfig pwcloudosConfig) {
		this.pwcloudosConfig = pwcloudosConfig;
	}

	public List getSpotWorkers(Map map) {
		// TODO Auto-generated method stub
		ProWorkersDao dao = (ProWorkersDao) getDao();

		return dao.getSpotWorkers(map);
	}

}
