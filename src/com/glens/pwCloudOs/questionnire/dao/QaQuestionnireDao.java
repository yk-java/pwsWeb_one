package com.glens.pwCloudOs.questionnire.dao;

import com.glens.eap.platform.core.annotation.MybatisNamespaceProcessor;
import com.glens.eap.platform.core.orm.mybatis.exception.MyBatisAccessException;
import com.glens.eap.platform.framework.dao.impl.EAPAbstractDao;
import com.glens.pwCloudOs.questionnire.vo.QaQuestionnire;
@MybatisNamespaceProcessor(value="com.glens.pwCloudOs.questionnire.dao.QaQuestionnireMapper")
public class QaQuestionnireDao extends EAPAbstractDao {
	/**
	 * 根据编号查询
	 * @param parameter
	 * @return
	 */
	public QaQuestionnire findByCode(Object parameter) {
		try {
			return (QaQuestionnire) this.getSqlSession().selectOne(getSqlStatement("findByCode"), parameter);
		} catch (Exception e) {
			e.printStackTrace();
			throw new MyBatisAccessException("exe sql=" + getSqlStatement("findByCode"), e);
		}
	}
}
