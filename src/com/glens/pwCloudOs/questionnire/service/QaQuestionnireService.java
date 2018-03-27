package com.glens.pwCloudOs.questionnire.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.glens.eap.platform.framework.codeCenter.CodeWorker;
import com.glens.eap.platform.framework.context.EAPContext;
import com.glens.eap.platform.framework.service.impl.EAPAbstractService;
import com.glens.eap.platform.framework.web.support.response.BeanResult;
import com.glens.pwCloudOs.questionnire.dao.QaAnswerDao;
import com.glens.pwCloudOs.questionnire.dao.QaAnswerSheetDao;
import com.glens.pwCloudOs.questionnire.dao.QaOptionDao;
import com.glens.pwCloudOs.questionnire.dao.QaQuestionDao;
import com.glens.pwCloudOs.questionnire.dao.QaQuestionGroupDao;
import com.glens.pwCloudOs.questionnire.dao.QaQuestionnireDao;
import com.glens.pwCloudOs.questionnire.vo.QaAnswer;
import com.glens.pwCloudOs.questionnire.vo.QaAnswerSheet;
import com.glens.pwCloudOs.questionnire.vo.QaOption;
import com.glens.pwCloudOs.questionnire.vo.QaQuestion;
import com.glens.pwCloudOs.questionnire.vo.QaQuestionGroup;
import com.glens.pwCloudOs.questionnire.vo.QaQuestionnire;

public class QaQuestionnireService extends EAPAbstractService {
	private QaQuestionGroupDao qaQuestionGroupDao;
	private QaQuestionDao qaQuestionDao;
	private QaOptionDao qaOptionDao;
	private QaAnswerSheetDao qaAnswerSheetDao;
	private QaAnswerDao qaAnswerDao;
	
	public QaAnswerSheetDao getQaAnswerSheetDao() {
		return qaAnswerSheetDao;
	}

	public void setQaAnswerSheetDao(QaAnswerSheetDao qaAnswerSheetDao) {
		this.qaAnswerSheetDao = qaAnswerSheetDao;
	}

	public QaAnswerDao getQaAnswerDao() {
		return qaAnswerDao;
	}

	public void setQaAnswerDao(QaAnswerDao qaAnswerDao) {
		this.qaAnswerDao = qaAnswerDao;
	}

	public QaQuestionGroupDao getQaQuestionGroupDao() {
		return qaQuestionGroupDao;
	}

	public void setQaQuestionGroupDao(QaQuestionGroupDao qaQuestionGroupDao) {
		this.qaQuestionGroupDao = qaQuestionGroupDao;
	}

	public QaQuestionDao getQaQuestionDao() {
		return qaQuestionDao;
	}

	public void setQaQuestionDao(QaQuestionDao qaQuestionDao) {
		this.qaQuestionDao = qaQuestionDao;
	}

	public QaOptionDao getQaOptionDao() {
		return qaOptionDao;
	}

	public void setQaOptionDao(QaOptionDao qaOptionDao) {
		this.qaOptionDao = qaOptionDao;
	}

	/**
	 * 问卷保存（有则修改，无则新增）
	 * @param questionnire
	 * @return
	 */
	@Transactional
	public BeanResult saveQuestionnire(QaQuestionnire questionnire) throws Exception{
		CodeWorker codeWorker = (CodeWorker) EAPContext.getContext().getBean(
				CodeWorker.SIMPLE_CODE_WORKER);
		// 查询是否在存
		QaQuestionnireDao dao = (QaQuestionnireDao)getDao();
		QaQuestionnire old = (QaQuestionnire)dao.findByCode(questionnire.getQuestionnireCode());
		if(old==null){
			/* 新增 */
			// 新增问卷信息
			String questionnireCode = codeWorker.createCode("QA");
			questionnire.setQuestionnireCode(questionnireCode);
			boolean res = dao.insert(questionnire);
			
			/* 整理数据 */
			List<QaQuestionGroup> groups = new ArrayList<QaQuestionGroup>();
			List<QaQuestion> questions = questionnire.getQuestions();
			List<QaOption> options = new ArrayList<QaOption>();
			QaQuestionGroup group = null;
			if(questions!=null && questions.size()>0){
				for (QaQuestion question : questions) {
					question.setQuestionnireCode(questionnire.getQuestionnireCode());
					String questionCode = codeWorker.createCode("Q");
					question.setQuestionCode(questionCode);
					if("group".equals(question.getType())){
						QaQuestionGroup qGroup = new QaQuestionGroup();
						String groupCode = codeWorker.createCode("G");
						qGroup.setQuestionnireCode(questionnire.getQuestionnireCode());
						qGroup.setGroupCode(groupCode);
						qGroup.setGroupName(question.getTitle());
						groups.add(qGroup);
						question.setGroupCode(groupCode);
						group = qGroup;
					}else if("question".equals(question.getType())){
						if(group!=null){
							question.setGroupCode(group.getGroupCode());
						}
						// options
						if(question.getOptions()!=null && question.getOptions().size()>0){
							for (Iterator<QaOption> iterator = question.getOptions().iterator(); iterator
									.hasNext();) {
								QaOption qaOption = (QaOption) iterator.next();
								qaOption.setQuestionnireCode(question.getQuestionnireCode());
								qaOption.setQuestionCode(question.getQuestionCode());
								String optionCode = codeWorker.createCode("O");
								qaOption.setOptionCode(optionCode);
								options.add(qaOption);
							}
						}
					}
				}
				
				// 新增分组信息
				if(groups.size()>0)
					qaQuestionGroupDao.batchInsert(groups);
				// 新增题目（标题）信息
				if(questions.size()>0)
					qaQuestionDao.batchInsert(questions);
				// 新增选项信息
				if(options.size()>0)
					qaOptionDao.batchInsert(options);
			}
		}else{
			/* 修改（删除再新增） */
			// 修改问卷
			dao.update(questionnire);
			/* 整理数据 */
			List<QaQuestionGroup> groups = new ArrayList<QaQuestionGroup>();
			List<QaQuestion> questions = questionnire.getQuestions();
			List<QaOption> options = new ArrayList<QaOption>();
			QaQuestionGroup group = null;
			if(questions!=null && questions.size()>0){
				for (QaQuestion question : questions) {
					question.setQuestionnireCode(questionnire.getQuestionnireCode());
					String questionCode = codeWorker.createCode("Q");
					question.setQuestionCode(questionCode);
					if("group".equals(question.getType())){
						QaQuestionGroup qGroup = new QaQuestionGroup();
						String groupCode = codeWorker.createCode("G");
						qGroup.setQuestionnireCode(questionnire.getQuestionnireCode());
						qGroup.setGroupCode(groupCode);
						qGroup.setGroupName(question.getTitle());
						groups.add(qGroup);
						question.setGroupCode(groupCode);
						group = qGroup;
					}else if("question".equals(question.getType())){
						if(group!=null){
							question.setGroupCode(group.getGroupCode());
						}
						// options
						if(question.getOptions()!=null && question.getOptions().size()>0){
							for (Iterator<QaOption> iterator = question.getOptions().iterator(); iterator
									.hasNext();) {
								QaOption qaOption = (QaOption) iterator.next();
								qaOption.setQuestionnireCode(question.getQuestionnireCode());
								qaOption.setQuestionCode(question.getQuestionCode());
								String optionCode = codeWorker.createCode("O");
								qaOption.setOptionCode(optionCode);
								options.add(qaOption);
							}
						}
					}
				}
				// 删除分组信息
				qaQuestionGroupDao.deleteByQuestionnireCode(questionnire.getQuestionnireCode());
				// 新增分组信息
				if(groups.size()>0)
					qaQuestionGroupDao.batchInsert(groups);
				// 删除题目
				qaQuestionDao.deleteByQuestionnireCode(questionnire.getQuestionnireCode());
				// 新增题目
				if(questions.size()>0)
					qaQuestionDao.batchInsert(questions);
				// 删除选项
				qaOptionDao.deleteByQuestionnireCode(questionnire.getQuestionnireCode());
				// 新增选项
				if(options.size()>0)
					qaOptionDao.batchInsert(options);
			}
		}
		return BeanResult.success("ok", questionnire.getQuestionnireCode());
	}

	/**
	 * 根据问卷编号查询问卷详细
	 * @param questionnireCode
	 * @return
	 */
	public QaQuestionnire findByCode(String questionnireCode) {
		QaQuestionnireDao dao = (QaQuestionnireDao)getDao();
		QaQuestionnire questionnire = dao.findByCode(questionnireCode);
		List<QaQuestion> questions = qaQuestionDao.findByQuestionnireCode(questionnireCode);
		List<QaOption> optionsAll = qaOptionDao.findByQuestionnireCode(questionnireCode);
		for (Iterator<QaQuestion> iterator = questions.iterator(); iterator.hasNext();) {
			QaQuestion question = iterator.next();
			List<QaOption> options = new ArrayList<QaOption>();
			for (Iterator<QaOption> iterator2 = optionsAll.iterator(); iterator2
					.hasNext();) {
				QaOption option = iterator2.next();
				if(option.getQuestionnireCode().equals(question.getQuestionnireCode()) && 
						option.getQuestionCode().equals(question.getQuestionCode())){
					options.add(option);
				}
			}
			question.setOptions(options);
		}
		questionnire.setQuestions(questions);
		return questionnire;
	}
	/**
	 * 删除问卷
	 */
	@Transactional
	public int delete(String questionnireCode) throws Exception {
		int res=0;
		res += qaOptionDao.deleteByQuestionnireCode(questionnireCode);
		res += qaQuestionDao.deleteByQuestionnireCode(questionnireCode);
		res += qaQuestionGroupDao.deleteByQuestionnireCode(questionnireCode);
		res += getDao().delete(questionnireCode);
		return res;
	}
	
	/**
	 * 提交问卷，保存答案
	 * @param answerSheet
	 * @return
	 * @throws Exception
	 */
	@Transactional
	public BeanResult submitAnswer(QaAnswerSheet answerSheet) throws Exception{
		int res = 0;
		// 判断是否已答过
		QaAnswerSheet oldAnswerSheet = qaAnswerSheetDao.findByQuestionnireCodeAndSubmitUser(answerSheet.getQuestionnireCode(), answerSheet.getSubmitUser());
		if(oldAnswerSheet!=null){
			return BeanResult.fail("已提交过，不能重复提交！");
		}
		CodeWorker codeWorker = (CodeWorker) EAPContext.getContext().getBean(
				CodeWorker.SIMPLE_CODE_WORKER);
		String answerSheetCode = codeWorker.createCode("AS");
		// 答案簿
		answerSheet.setAnswerSheetCode(answerSheetCode);
		
		// 答案
		/* 算分、(评等级 TODO) */
		float score=0;
		QaQuestion question = null;
		String questionCode = "";
		String ansStr = "";
		List<QaAnswer> answers = answerSheet.getAnswers();
		List<QaQuestion> questions = qaQuestionDao.findByQuestionnireCode(answerSheet.getQuestionnireCode());
		for (Iterator<QaAnswer> iterator = answers.iterator(); iterator.hasNext();) {
			QaAnswer answer = (QaAnswer) iterator.next();
			answer.setAnswerSheetCode(answerSheetCode);//指定sheetCode
			if(!questionCode.equals(answer.getQuestionCode())){
				if(!"".equals(ansStr)){
					// 判断答案
					if(ansStr.equals(question.getAnswer())){
						score += question.getQuestionScore();
					}
				}
				// new
				questionCode = answer.getQuestionCode();
				ansStr="";
				for (Iterator qIterator = questions.iterator(); qIterator.hasNext();) {
					QaQuestion qaQuestion = (QaQuestion) qIterator.next();
					if(questionCode.equals(qaQuestion.getQuestionCode())){
						question = qaQuestion;
						qIterator.remove();
						break;
					}
				}
			}
			if(ansStr.length()>0) ansStr+=",";
			ansStr+=answer.getAnswer();
		}
		if(!"".equals(ansStr)){// last
			// 判断答案
			if(ansStr.equals(question.getAnswer())){
				score += question.getQuestionScore();
			}
		}
		answerSheet.setScore(score);// 得分
		// 保存
		qaAnswerSheetDao.insert(answerSheet);
		if(answers.size()>0){
			res = qaAnswerDao.batchInsert(answers);
		}else{
			throw new RuntimeException("提交异常，答案为空！");
		}
		return BeanResult.success("提交成功！", res);
	}
	
}
