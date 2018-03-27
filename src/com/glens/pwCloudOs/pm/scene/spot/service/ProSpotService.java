package com.glens.pwCloudOs.pm.scene.spot.service;

import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONObject;
import com.glens.eap.platform.afinal.FinalHttp;
import com.glens.eap.platform.afinal.http.AjaxParams;
import com.glens.eap.platform.core.beans.ValueObject;
import com.glens.eap.platform.core.utils.DateTimeUtil;
import com.glens.eap.platform.core.utils.FileUtil;
import com.glens.eap.platform.core.utils.StringUtil;
import com.glens.eap.platform.framework.beans.PageBean;
import com.glens.eap.platform.framework.codeCenter.CodeWorker;
import com.glens.eap.platform.framework.context.EAPContext;
import com.glens.eap.platform.framework.service.impl.EAPAbstractService;
import com.glens.pwCloudOs.config.PWCloudOsConfig;
import com.glens.pwCloudOs.pm.scene.spot.dao.ProSpotDao;
import com.glens.pwCloudOs.pm.scene.spot.vo.ProSpot;
import com.glens.pwCloudOs.pm.scene.workers.dao.ProWorkersDao;
import com.glens.pwCloudOs.pm.scene.workers.vo.ProWorkers;

public class ProSpotService extends EAPAbstractService {

	private ProWorkersDao proWorkersDao;

	private PWCloudOsConfig pwcloudosConfig;

	private FinalHttp httpClient = new FinalHttp();

	public void insert(ValueObject parameters, String root) throws Exception {
		ProSpot vo = (ProSpot) parameters;
		PWCloudOsConfig config = (PWCloudOsConfig) EAPContext.getContext()
				.getBean("pwcloudosConfig");
		CodeWorker codeWorker = (CodeWorker) EAPContext.getContext().getBean(
				CodeWorker.SIMPLE_CODE_WORKER);
		vo.setCompanyCode("001");
		vo.setSpotCode(codeWorker.createCode("W"));

		ProSpotDao dao = (ProSpotDao) getDao();
		if (vo != null) {

			if (vo.getFile1() != null) {
				String fileCode = upload(root, vo, vo.getFile1(), config);
				vo.setPic1(config.getPrefix()+ String.format(pwcloudosConfig.getFileUrl(), fileCode));

			}

			if (vo.getFile2() != null) {
				String fileCode = upload(root, vo, vo.getFile2(), config);
				vo.setPic2(config.getPrefix()+ String.format(pwcloudosConfig.getFileUrl(), fileCode));
			}

			if (vo.getFile3() != null) {
				String fileCode = upload(root, vo, vo.getFile3(), config);
				vo.setPic3(config.getPrefix()+ String.format(pwcloudosConfig.getFileUrl(), fileCode));
			}

			vo.setSysCreateTime(DateTimeUtil.formatDate(new Date(),
					DateTimeUtil.FORMAT_1));
			vo.setSysProcessFlag("1");
			dao.insertSelective(vo);

			String workerCodes = vo.getWorkerCodes();
			String[] workerCodeArrays = workerCodes.split(",");
			for (String wc : workerCodeArrays) {
				ProWorkers worker = new ProWorkers();
				worker.setWorkerCode(wc);
				worker.setSpotCode(vo.getSpotCode());
				proWorkersDao.updateSelective(worker);
			}
		}

	}

	public void updateSpot(ValueObject parameters, String root)
			throws Exception {
		ProSpot vo = (ProSpot) parameters;
		PWCloudOsConfig config = (PWCloudOsConfig) EAPContext.getContext()
				.getBean("pwcloudosConfig");
		// vo.setCompanyCode("001");
		// vo.setSpotCode(codeWorker.createCode("W"));

		ProSpotDao dao = (ProSpotDao) getDao();
		if (vo != null) {

			if (vo.getFile1() != null) {
				String fileCode = upload(root, vo, vo.getFile1(), config);
				vo.setPic1(config.getPrefix()+ String.format(pwcloudosConfig.getFileUrl(), fileCode));
			}

			if (vo.getFile2() != null) {
				String fileCode = upload(root, vo, vo.getFile2(), config);
				vo.setPic2(config.getPrefix()+ String.format(pwcloudosConfig.getFileUrl(), fileCode));
			}

			if (vo.getFile3() != null) {
				String fileCode = upload(root, vo, vo.getFile3(), config);
				vo.setPic3(config.getPrefix()+ String.format(pwcloudosConfig.getFileUrl(), fileCode));
			}

			vo.setSysUpdateTime(DateTimeUtil.formatDate(new Date(),
					DateTimeUtil.FORMAT_1));
			dao.updateSelective(vo);

			// 删除人员，重新增加
			if (StringUtil.isNotNull(vo.getWorkerCodes())) {
				proWorkersDao.deleteWorker(vo.getSpotCode());
				String workerCodes = vo.getWorkerCodes();
				String[] workerCodeArrays = workerCodes.split(",");
				for (String wc : workerCodeArrays) {
					ProWorkers worker = new ProWorkers();
					worker.setWorkerCode(wc);
					worker.setSpotCode(vo.getSpotCode());
					proWorkersDao.updateSelective(worker);
				}
			}

		}
	}

	private String upload(String root, ProSpot vo, MultipartFile file,
			PWCloudOsConfig config) throws Exception {
		String fileCode = "";
		String tmpFileHomeUrl;
		String fileName;
		File tmpFile;
		tmpFileHomeUrl = root + File.separator + config.getTmpfileHome();
		fileName = file.getOriginalFilename();
		tmpFile = new File(tmpFileHomeUrl + File.separator + fileName);
		boolean writeResult = FileUtil
				.writeFile(file.getInputStream(), tmpFile);
		if (writeResult) {
			AjaxParams params = new AjaxParams();
			params.put("file", tmpFile);
			Object content = httpClient.postSync(
					config.getPrefix()+ config.getFileServerUploadUrl(), params);
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

	public void deleteSpot(ValueObject parameters) throws Exception {
		ProSpot vo = (ProSpot) parameters;
		ProSpotDao dao = (ProSpotDao) getDao();
		if (vo != null) {
			vo.setSysDeleteTime(DateTimeUtil.formatDate(new Date(),
					DateTimeUtil.FORMAT_1));
			vo.setSysProcessFlag("0");
			dao.updateSelective(vo);
		}
	}

	public void setProWorkersDao(ProWorkersDao proWorkersDao) {
		this.proWorkersDao = proWorkersDao;
	}

	public Map get(String spotCode) {
		ProSpotDao dao = (ProSpotDao) getDao();
		Map<String, Object> map = dao.get(spotCode);
		Map paramsMap = new HashMap<String, Object>();
		paramsMap.put("spotCode", spotCode);
		List<Map<String, Object>> list = proWorkersDao
				.queryProWorkers(paramsMap);
		map.put("workerList", list);
		return map;
	}
	
	public PageBean queryForPage1(Map map) {
		ProSpotDao dao = (ProSpotDao) getDao();
		return dao.queryForPage(map, "queryForCount1", "queryForPage1");
	}

	public List queryProSpotInfo(Map<String, Object> paramsMap) {
		// TODO Auto-generated method stub
		ProSpotDao dao = (ProSpotDao) getDao();
		return dao.queryProSpotInfo(paramsMap);
	}

	public String queryProspotCode(String reportEmployeeCode) {
		// TODO Auto-generated method stub
		ProSpotDao dao = (ProSpotDao) getDao();
		Map<String, Object> paramsMap = new HashMap<String, Object>();
		paramsMap.put("reportEmployeeCode", reportEmployeeCode);
		Map m = dao.queryProspotCode(paramsMap);
		if (m == null) {
			return "";
		} else {
			String spotCode = m.get("SPOT_CODE") + "";
			return spotCode;
		}
	}

	public void setPwcloudosConfig(PWCloudOsConfig pwcloudosConfig) {
		this.pwcloudosConfig = pwcloudosConfig;
	}

}
