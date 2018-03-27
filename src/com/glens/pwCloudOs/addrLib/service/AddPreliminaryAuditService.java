package com.glens.pwCloudOs.addrLib.service;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONObject;
import com.glens.eap.platform.afinal.FinalHttp;
import com.glens.eap.platform.afinal.http.AjaxParams;
import com.glens.eap.platform.core.utils.FileUtil;
import com.glens.eap.platform.framework.codeCenter.CodeWorker;
import com.glens.eap.platform.framework.context.EAPContext;
import com.glens.eap.platform.framework.service.impl.EAPAbstractService;
import com.glens.pwCloudOs.addrLib.dao.AddPreliminaryAuditDao;
import com.glens.pwCloudOs.addrLib.vo.AddPreliminaryAuditVo;
import com.glens.pwCloudOs.config.PWCloudOsConfig;

public class AddPreliminaryAuditService extends EAPAbstractService {
	
	private FinalHttp httpClient = new FinalHttp();
	/**
	 * 保存地名申请信息
	 * @param vo
	 * @param rootPath
	 * @return
	 * @throws IOException
	 */
	public String saveApplicat(AddPreliminaryAuditVo vo, String rootPath) throws IOException {
		CodeWorker codeWorker = (CodeWorker) EAPContext.getContext().getBean(
				CodeWorker.SIMPLE_CODE_WORKER);
		Map<String, Object> fileInfo1 = postSync(vo.getApplicationFile(), rootPath);
		Map<String, Object> fileInfo2 = postSync(vo.getConfirmationFile(), rootPath);
		if(fileInfo1.get("convertFileCode")!=null){
			vo.setApplication((String)fileInfo1.get("convertFileCode"));
		}else{
			vo.setApplication((String)fileInfo1.get("fileCode"));
		}
		if(fileInfo2.get("convertFileCode")!=null){
			vo.setConfirmation((String)fileInfo2.get("convertFileCode"));
		}else{
			vo.setConfirmation((String)fileInfo2.get("fileCode"));
		}
		vo.setFormCode(codeWorker.createCode("AP"));
		AddPreliminaryAuditDao dao = (AddPreliminaryAuditDao)getDao();
		boolean isOk = dao.insert(vo);
		if(isOk){
			return vo.getFormCode();
		}else{
			return null;
		}
	}
	
	public AddPreliminaryAuditVo getApplicat(String formCode){
		AddPreliminaryAuditDao dao = (AddPreliminaryAuditDao)getDao();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("formCode", formCode);
		AddPreliminaryAuditVo res = (AddPreliminaryAuditVo)dao.findById(map);
		return res;
	}
	
	private Map<String, Object> postSync(MultipartFile file, String rootPath) throws IOException{
		Map<String, Object> res = new HashMap<String, Object>();
		PWCloudOsConfig config = (PWCloudOsConfig) EAPContext.getContext().getBean("pwcloudosConfig");
		String tmpFileHomeUrl = rootPath + File.separator + config.getTmpfileHome();
		String fileName = file.getOriginalFilename();
		File tmpFile = new File(tmpFileHomeUrl + File.separator + fileName);
		boolean writeResult = FileUtil.writeFile(file.getInputStream(), tmpFile);
		if (writeResult) {
			AjaxParams params = new AjaxParams();
			params.put("file", tmpFile);
			Object content = httpClient.postSync(config.getPrefix()+ config.getFileServerUploadUrl(), params);
			if (content != null) {
				if(tmpFile.exists()){
					tmpFile.delete();
				}
				JSONObject jsonObj = JSONObject.parseObject(content.toString());
				if ("200".equals(jsonObj.get("statusCode"))) {
					JSONObject fileJsonObj = jsonObj.getJSONObject("data");
					if (fileJsonObj != null) {
						String fileCode = fileJsonObj.getString("fileCode");
						String convertFileCode = fileJsonObj.getString("convertFileCode");
						String fileSuffix = fileJsonObj.getString("fileSuffix");
						Long fileSize = fileJsonObj.getLong("fileSize");
						res.put("statusCode", "200");
						res.put("fileCode", fileCode);
						res.put("convertFileCode", convertFileCode);
						res.put("fileSuffix", fileSuffix);
						res.put("fileSize", fileSize);
						return res;
					}
				}
			}
		}
		res.put("statusCode", "500");
		return res;
	}

	
	public int updateAccept(Object parameters) {
		// TODO Auto-generated method stub
		
		AddPreliminaryAuditDao dao=(AddPreliminaryAuditDao)getDao();
		
		return dao.updateAccept(parameters);
	}
	
	public int updateAudit(Object parameters) {
		// TODO Auto-generated method stub
		
		AddPreliminaryAuditDao dao=(AddPreliminaryAuditDao)getDao();
		
		return dao.updateAudit(parameters);
	}
	
	public int updateAudit2(Object parameters) {
		// TODO Auto-generated method stub
		
		AddPreliminaryAuditDao dao=(AddPreliminaryAuditDao)getDao();
		
		return dao.updateAudit2(parameters);
	}
	
}
