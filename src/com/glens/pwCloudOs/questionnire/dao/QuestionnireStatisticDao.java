package com.glens.pwCloudOs.questionnire.dao;

import java.util.List;
import java.util.Map;

import com.glens.eap.platform.core.annotation.MybatisNamespaceProcessor;
import com.glens.eap.platform.core.orm.mybatis.exception.MyBatisAccessException;
import com.glens.eap.platform.framework.dao.impl.EAPAbstractDao;
@MybatisNamespaceProcessor(value="com.glens.pwCloudOs.questionnire.dao.QuestionnireStatisticMapper")
public class QuestionnireStatisticDao extends EAPAbstractDao {
	/**
	 * 根据问卷编号与提交人查询问卷作答信息
	 * @param parameters
	 * @return
	 */
	public List<Map<String, Object>> selectAnswerByQuestionnireCodeAndUser(Object parameters) {
		try {
			return this.getSqlSession().selectList(getSqlStatement("selectAnswerByQuestionnireCodeAndUser"), parameters);
		} catch (Exception e) {
			e.printStackTrace();
			throw new MyBatisAccessException("exe sql=" + getSqlStatement("selectAnswerByQuestionnireCodeAndUser"), e);
		}
	}
	/**
	 * 根据问题编号统计选项选择数量
	 * @param parameters
	 * @return
	 */
	public List<Map<String, Object>> statisticRadioAnswerByQuestionCode(String questionCode) {
		try {
			return this.getSqlSession().selectList(getSqlStatement("statisticRadioAnswerByQuestionCode"), questionCode);
		} catch (Exception e) {
			e.printStackTrace();
			throw new MyBatisAccessException("exe sql=" + getSqlStatement("statisticRadioAnswerByQuestionCode"), e);
		}
	}
	/**
	 * 根据问卷编号与补充项的项目编号查询作答记录
	 * @param parameters
	 * @return
	 */
	public List<Map<String, Object>> selectSupplementByQuestionCodeAndOptCode(Object parameters) {
		try {
			return this.getSqlSession().selectList(getSqlStatement("selectSupplementByQuestionCodeAndOptCode"), parameters);
		} catch (Exception e) {
			e.printStackTrace();
			throw new MyBatisAccessException("exe sql=" + getSqlStatement("selectSupplementByQuestionCodeAndOptCode"), e);
		}
	}
	/**
	 * 根据问卷编号查询填空题作答记录
	 * @param parameters
	 * @return
	 */
	public List<Map<String, Object>> selectTextAnswerByQuestionCode(String questionCode) {
		try {
			return this.getSqlSession().selectList(getSqlStatement("selectTextAnswerByQuestionCode"), questionCode);
		} catch (Exception e) {
			e.printStackTrace();
			throw new MyBatisAccessException("exe sql=" + getSqlStatement("selectTextAnswerByQuestionCode"), e);
		}
	}
	/**
	 * 根据问卷编号与条件查询答卷数据
	 * @param parameters
	 * @return
	 */
	public List<Map<String, Object>> selectAnswerData(Map<String, Object> parameters) {
		try {
			return this.getSqlSession().selectList(getSqlStatement("selectAnswerData"), parameters);
		} catch (Exception e) {
			e.printStackTrace();
			throw new MyBatisAccessException("exe sql=" + getSqlStatement("selectAnswerData"), e);
		}
	}
	
}
