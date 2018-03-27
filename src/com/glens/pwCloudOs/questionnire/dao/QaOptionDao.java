package com.glens.pwCloudOs.questionnire.dao;

import java.util.List;

import com.glens.eap.platform.core.annotation.MybatisNamespaceProcessor;
import com.glens.eap.platform.core.orm.mybatis.exception.MyBatisAccessException;
import com.glens.eap.platform.framework.dao.impl.EAPAbstractDao;
import com.glens.pwCloudOs.questionnire.vo.QaOption;
@MybatisNamespaceProcessor(value="com.glens.pwCloudOs.questionnire.dao.QaOptionMapper")
public class QaOptionDao extends EAPAbstractDao {

	/**
	 * 批量新增
	 * @param questions
	 * @return
	 */
	public int batchInsert(List<QaOption> options){
		int res=0;
		try {
			res = this.getSqlSession().insert(getSqlStatement("batchInsert"), options);
		} catch (Exception e) {
			e.printStackTrace();
			throw new MyBatisAccessException("exe sql=" + getSqlStatement("batchInsert"), e);
		}
		return res;
	}
	/**
	 * 根据问卷编号删除
	 * @param parameter
	 * @return
	 */
	public int deleteByQuestionnireCode(String questionnireCode) {
		int res=0;
		try {
			res = this.getSqlSession().delete(getSqlStatement("deleteByQuestionnireCode"), questionnireCode);
		} catch (Exception e) {
			e.printStackTrace();
			throw new MyBatisAccessException("exe sql=" + getSqlStatement("deleteByQuestionnireCode"), e);
		}
		return res;
	}
	
	/**
	 * 根据问卷编号查询
	 * @param parameter
	 * @return
	 */
	public List<QaOption> findByQuestionnireCode(String questionnireCode) {
		List<QaOption> res=null;
		try {
			res = this.getSqlSession().selectList(getSqlStatement("findByQuestionnireCode"), questionnireCode);
		} catch (Exception e) {
			e.printStackTrace();
			throw new MyBatisAccessException("exe sql=" + getSqlStatement("findByQuestionnireCode"), e);
		}
		return res;
	}
}
