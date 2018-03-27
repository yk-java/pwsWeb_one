package com.glens.pwCloudOs.questionnire.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.glens.eap.platform.core.annotation.MybatisNamespaceProcessor;
import com.glens.eap.platform.core.orm.mybatis.exception.MyBatisAccessException;
import com.glens.eap.platform.framework.dao.impl.EAPAbstractDao;
import com.glens.pwCloudOs.questionnire.vo.QaQuestionnireSend;
@MybatisNamespaceProcessor(value="com.glens.pwCloudOs.questionnire.dao.QaQuestionnireSendMapper")
public class QaQuestionnireSendDao extends EAPAbstractDao {

	/**
	 * 批量新增
	 * @param questions
	 * @return
	 */
	public int batchInsert(List<QaQuestionnireSend> qaQuestionnireSends){
		int res=0;
		try {
			res = this.getSqlSession().insert(getSqlStatement("batchInsert"), qaQuestionnireSends);
		} catch (Exception e) {
			e.printStackTrace();
			throw new MyBatisAccessException("exe sql=" + getSqlStatement("batchInsert"), e);
		}
		return res;
	}
	/**
	 * 根据问卷编号和多个员工编号批量删除
	 * @param parameter
	 * @return
	 */
	public int batchDelete(String questionnireCode, List<String> employeecodes) {
		int res=0;
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("questionnireCode", questionnireCode);
			params.put("employeecodes", employeecodes);
			res = this.getSqlSession().delete(getSqlStatement("batchDelete"), params);
		} catch (Exception e) {
			e.printStackTrace();
			throw new MyBatisAccessException("exe sql=" + getSqlStatement("batchDelete"), e);
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
	public List<QaQuestionnireSend> findByQuestionnireCode(String questionnireCode) {
		List<QaQuestionnireSend> res=null;
		try {
			res = this.getSqlSession().selectList(getSqlStatement("findByQuestionnireCode"), questionnireCode);
		} catch (Exception e) {
			e.printStackTrace();
			throw new MyBatisAccessException("exe sql=" + getSqlStatement("findByQuestionnireCode"), e);
		}
		return res;
	}
	
	
	/**
	 * 根据员工编号查询我的问卷
	 * @param parameter
	 * @return
	 */
	public List<Map<String, Object>> selectMyQuestionnire(String employeeCode) {
		List<Map<String, Object>> res=null;
		try {
			res = this.getSqlSession().selectList(getSqlStatement("selectMyQuestionnire"), employeeCode);
		} catch (Exception e) {
			e.printStackTrace();
			throw new MyBatisAccessException("exe sql=" + getSqlStatement("selectMyQuestionnire"), e);
		}
		return res;
	}
}
