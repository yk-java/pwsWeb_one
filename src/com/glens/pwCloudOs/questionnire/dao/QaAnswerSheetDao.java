package com.glens.pwCloudOs.questionnire.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.glens.eap.platform.core.annotation.MybatisNamespaceProcessor;
import com.glens.eap.platform.core.orm.mybatis.exception.MyBatisAccessException;
import com.glens.eap.platform.framework.dao.impl.EAPAbstractDao;
import com.glens.pwCloudOs.questionnire.vo.QaAnswerSheet;
@MybatisNamespaceProcessor(value="com.glens.pwCloudOs.questionnire.dao.QaAnswerSheetMapper")
public class QaAnswerSheetDao extends EAPAbstractDao {
	/**
	 * 根据编号查询
	 * @param parameter
	 * @return
	 */
	public List<QaAnswerSheet> findByQuestionnireCode(String questionnireCode) {
		try {
			return (List) this.getSqlSession().selectOne(getSqlStatement("findByQuestionnireCode"), questionnireCode);
		} catch (Exception e) {
			e.printStackTrace();
			throw new MyBatisAccessException("exe sql=" + getSqlStatement("findByQuestionnireCode"), e);
		}
	}
	/**
	 * 根据编号查询
	 * @param parameter
	 * @return
	 */
	public QaAnswerSheet findByQuestionnireCodeAndSubmitUser(String questionnireCode, String employeeCode) {
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("questionnireCode", questionnireCode);
			params.put("submitUser", employeeCode);
			return (QaAnswerSheet) this.getSqlSession().selectOne(getSqlStatement("findByQuestionnireCodeAndSubmitUser"), params);
		} catch (Exception e) {
			e.printStackTrace();
			throw new MyBatisAccessException("exe sql=" + getSqlStatement("findByQuestionnireCodeAndSubmitUser"), e);
		}
	}
}
