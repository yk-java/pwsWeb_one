package com.glens.pwCloudOs.questionnire.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;
import com.glens.eap.platform.framework.service.impl.EAPAbstractService;
import com.glens.pwCloudOs.common.dao.CommonDao;
import com.glens.pwCloudOs.notice.service.SmNoticeService;
import com.glens.pwCloudOs.questionnire.dao.QaQuestionnireDao;
import com.glens.pwCloudOs.questionnire.dao.QaQuestionnireSendDao;
import com.glens.pwCloudOs.questionnire.vo.QaQuestionnire;
import com.glens.pwCloudOs.questionnire.vo.QaQuestionnireSend;

public class QaQuestionnireSendService extends EAPAbstractService {
	private SmNoticeService smNoticeService;
	private QaQuestionnireDao questionnireDao;
	private CommonDao commonDao;
	
	
	public QaQuestionnireDao getQuestionnireDao() {
		return questionnireDao;
	}

	public void setQuestionnireDao(QaQuestionnireDao questionnireDao) {
		this.questionnireDao = questionnireDao;
	}

	public CommonDao getCommonDao() {
		return commonDao;
	}

	public void setCommonDao(CommonDao commonDao) {
		this.commonDao = commonDao;
	}

	public SmNoticeService getSmNoticeService() {
		return smNoticeService;
	}

	public void setSmNoticeService(SmNoticeService smNoticeService) {
		this.smNoticeService = smNoticeService;
	}

	/**
	 * 问卷分发
	 * @param items
	 * @return
	 * @throws Exception
	 */
	@Transactional
	public int doSend(JSONObject params) throws Exception{
		String questionnireCode = (String)params.get("questionnireCode");
		String title = (String)params.get("title");
		String message = (String)params.get("message");
		String employeecodes = (String)params.get("employeecodes");
		String[] employeecodeArr = employeecodes.split(",");
		String source=(String)params.get("source");
		
		/* 保存问卷下发记录 */
		List<String> employeecodeList=new ArrayList<String>();
		List<QaQuestionnireSend> items = new ArrayList<QaQuestionnireSend>();
		for (int i = 0; i < employeecodeArr.length; i++) {
			String empCode = employeecodeArr[i];
			QaQuestionnireSend item = new QaQuestionnireSend();
			item.setQuestionnireCode(questionnireCode);
			item.setEmployeeCode(empCode);
			items.add(item);
			employeecodeList.add(empCode);
		}
		// 修改问卷状态为已发布
		Map<String, Object> paramsUpdate = new HashMap<String, Object>();
		paramsUpdate.put("questionnireCode", questionnireCode);
		paramsUpdate.put("status", 1);
		questionnireDao.update(paramsUpdate);
		// 删除之前分发过的
		QaQuestionnireSendDao sendDao = (QaQuestionnireSendDao)getDao();
		sendDao.batchDelete(questionnireCode, employeecodeList);
		// 新分发
		int res = sendDao.batchInsert(items);
		// 发送消息通知
		List<String> accountCodeList = commonDao.employeecodesToAccountCodes(employeecodeList);
		smNoticeService.sendMessage(source, title, message, accountCodeList);
		return res;
	}
	/**
	 * 根据问卷编号和员工编号删除（撤销）已下发到某人的问卷
	 * @param map
	 * @return
	 */
	public int delete(Map<String, Object> map){
		int res = dao.delete(map);
		// 检查是否还有下发的记录，如果没有，则修改问卷状态为未发布
		QaQuestionnireSendDao sendDao = (QaQuestionnireSendDao)dao;
		List<QaQuestionnireSend> lst = sendDao.findByQuestionnireCode((String)map.get("questionnireCode"));
		if(lst == null || lst.size() == 0){
			// 修改问卷状态为未发布
			Map<String, Object> paramsUpdate = new HashMap<String, Object>();
			paramsUpdate.put("questionnireCode", map.get("questionnireCode"));
			paramsUpdate.put("status", 0);
			questionnireDao.update(paramsUpdate);
		}
		return res;
	}
	/**
	 * 根据问卷编号删除（撤销）已下发的问卷
	 * @param questionnireCode
	 * @return
	 */
	public int deleteByQuestionnireCode(String questionnireCode){
		QaQuestionnireSendDao sendDao = (QaQuestionnireSendDao)getDao();
		int res = sendDao.deleteByQuestionnireCode(questionnireCode);
		if(res > 0){
			// 修改问卷状态为未发布
			Map<String, Object> paramsUpdate = new HashMap<String, Object>();
			paramsUpdate.put("questionnireCode", questionnireCode);
			paramsUpdate.put("status", 0);
			questionnireDao.update(paramsUpdate);
		}
		return res;
	}
	/**
	 * 我的问卷
	 * @param employeeCode
	 * @return
	 */
	public List<Map<String, Object>> selectMyQuestionnire(String employeeCode){
		QaQuestionnireSendDao sendDao = (QaQuestionnireSendDao)getDao();
		return sendDao.selectMyQuestionnire(employeeCode);
	}
	
}
