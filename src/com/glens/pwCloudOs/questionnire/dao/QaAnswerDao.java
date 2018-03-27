package com.glens.pwCloudOs.questionnire.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.glens.eap.platform.core.annotation.MybatisNamespaceProcessor;
import com.glens.eap.platform.core.orm.mybatis.exception.MyBatisAccessException;
import com.glens.eap.platform.framework.dao.impl.EAPAbstractDao;
import com.glens.pwCloudOs.questionnire.vo.QaAnswer;
@MybatisNamespaceProcessor(value="com.glens.pwCloudOs.questionnire.dao.QaAnswerMapper")
public class QaAnswerDao extends EAPAbstractDao {
	/**
	 * 根据编号查询
	 * @param parameter
	 * @return
	 */
	public List<QaAnswer> findByAnswerSheetCode(String answerSheetCode) {
		try {
			return (List) this.getSqlSession().selectList(getSqlStatement("findByAnswerSheetCode"), answerSheetCode);
		} catch (Exception e) {
			e.printStackTrace();
			throw new MyBatisAccessException("exe sql=" + getSqlStatement("findByAnswerSheetCode"), e);
		}
	}
	
	/**
	 * 根据答案表编号和问题编号查询
	 * @param parameter
	 * @return
	 */
	public QaAnswer findBySheetCodeAndQuestionCode(String answerSheetCode, String questionCode) {
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("answerSheetCode", answerSheetCode);
			params.put("questionCode", questionCode);
			return (QaAnswer) this.getSqlSession().selectOne(getSqlStatement("findBySheetCodeAndQuestionCode"), params);
		} catch (Exception e) {
			e.printStackTrace();
			throw new MyBatisAccessException("exe sql=" + getSqlStatement("findBySheetCodeAndQuestionCode"), e);
		}
	}
	
	/**
	 * 根据问卷编号和提交者查询
	 * @param parameter
	 * @return
	 */
	public List<QaAnswer> findByQuestionnireCodeAndSubmitUser(String answerSheetCode, String questionCode) {
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("questionnireCode", answerSheetCode);
			params.put("submitUser", questionCode);
			return (List) this.getSqlSession().selectOne(getSqlStatement("findByQuestionnireCodeAndSubmitUser"), params);
		} catch (Exception e) {
			e.printStackTrace();
			throw new MyBatisAccessException("exe sql=" + getSqlStatement("findByQuestionnireCodeAndSubmitUser"), e);
		}
	}
	/**
	 * 批量添加
	 * @param answers
	 * @return
	 */
	public int batchInsert(List<QaAnswer> answers){
		try {
			return this.getSqlSession().insert(getSqlStatement("batchInsert"), answers);
		} catch (Exception e) {
			e.printStackTrace();
			throw new MyBatisAccessException("exe sql=" + getSqlStatement("batchInsert"), e);
		}
	}
}
