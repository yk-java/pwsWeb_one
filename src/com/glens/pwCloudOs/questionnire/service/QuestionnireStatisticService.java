package com.glens.pwCloudOs.questionnire.service;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.glens.eap.platform.framework.service.impl.EAPAbstractService;
import com.glens.pwCloudOs.questionnire.dao.QuestionnireStatisticDao;
import com.glens.pwCloudOs.questionnire.vo.QaOption;
import com.glens.pwCloudOs.questionnire.vo.QaQuestion;
import com.glens.pwCloudOs.questionnire.vo.QaQuestionnire;

public class QuestionnireStatisticService extends EAPAbstractService {
	private QaQuestionnireService questionnireService;
	
	public QaQuestionnireService getQuestionnireService() {
		return questionnireService;
	}

	public void setQuestionnireService(QaQuestionnireService questionnireService) {
		this.questionnireService = questionnireService;
	}
	/**
	 * 根据问卷号与提交人查询作答情况
	 * @param questionnireCode
	 * @param submitUser
	 * @return
	 */
	public QaQuestionnire getAnswerInfo(String questionnireCode, String submitUser){
		QaQuestionnire questionnire=questionnireService.findByCode(questionnireCode);
		QuestionnireStatisticDao dao = (QuestionnireStatisticDao)getDao();
		Map<String, Object> params= new HashMap<String, Object>();
		params.put("questionnireCode", questionnireCode);
		params.put("submitUser", submitUser);
		List<Map<String, Object>> answers = dao.selectAnswerByQuestionnireCodeAndUser(params);
		List<QaQuestion> questions = questionnire.getQuestions();
		for (Iterator<QaQuestion> iterator = questions.iterator(); iterator.hasNext();) {
			QaQuestion qaQuestion = iterator.next();
			if("question".equals(qaQuestion.getType())){
				if("radio".equals(qaQuestion.getSubType()) || "checkbox".equals(qaQuestion.getSubType())){
					List<QaOption> options = qaQuestion.getOptions();
					for (Iterator<QaOption> iterator2 = options.iterator(); iterator2
							.hasNext();) {
						QaOption qaOption = iterator2.next();
						Map<String, Object> answer = getAnswer(answers, qaQuestion.getQuestionCode(), qaOption.getNum());
						if(answer!=null){
							qaOption.setIsAnswer(1);
							if(qaOption.getIsSupplement()!=null && qaOption.getIsSupplement()==1){
								qaOption.setSupplement((String)answer.get("supplement"));
							}
						}
					}
				}else if("text".equals(qaQuestion.getSubType())){
					Map<String, Object> answer = getAnswer(answers, qaQuestion.getQuestionCode(), null);
					if(answer!=null){
						qaQuestion.setAnswer((String)answer.get("answer"));
					}
				}
			}
		}
		return questionnire;
	}
	
	public List<Map<String, Object>> statisticRadioAnswer(String questionCode){
		QuestionnireStatisticDao dao = (QuestionnireStatisticDao)getDao();
		return dao.statisticRadioAnswerByQuestionCode(questionCode);
	}
	
	public List<Map<String, Object>> selectSupplementByQuestionCodeAndOptCode(String questionCode, String optNum){
		QuestionnireStatisticDao dao = (QuestionnireStatisticDao)getDao();
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("questionCode", questionCode);
		params.put("optNum", optNum);
		return dao.selectSupplementByQuestionCodeAndOptCode(params);
	}
	public List<Map<String, Object>> selectTextAnswerByQuestionCode(String questionCode){
		QuestionnireStatisticDao dao = (QuestionnireStatisticDao)getDao();
		return dao.selectTextAnswerByQuestionCode(questionCode);
	}
	/**
	 * 从答案集中查找问题答案
	 * @param answers
	 * @param questionCode
	 * @param optNum
	 * @return
	 */
	private Map<String, Object> getAnswer(List<Map<String, Object>> answers, String questionCode, String optNum){
		for (Iterator<Map<String, Object>> iterator = answers.iterator(); iterator.hasNext();) {
			Map<String, Object> map = iterator.next();
			if(questionCode.equals((String)map.get("questionCode"))){
				if(optNum==null){
					iterator.remove();// 递减
					return map;
				}else if(optNum.equals((String)map.get("answer"))){
					iterator.remove();// 递减
					return map;
				}
			}
		}
		return null;
	}
	/**
	 * 根据问卷编号查询答卷数据
	 * @param params
	 * @return
	 */
	public List<Map<String, Object>> selectAnswerData(Map<String, Object> params){
		QuestionnireStatisticDao dao = (QuestionnireStatisticDao)getDao();
		List<Map<String, Object>> data = dao.selectAnswerData(params);
		return data;
	}
}
