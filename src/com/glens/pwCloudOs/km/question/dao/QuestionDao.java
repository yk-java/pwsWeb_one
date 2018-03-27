package com.glens.pwCloudOs.km.question.dao;

import java.util.List;

import com.glens.eap.platform.core.annotation.MybatisNamespaceProcessor;
import com.glens.eap.platform.core.orm.mybatis.exception.MyBatisAccessException;
import com.glens.eap.platform.framework.dao.impl.EAPAbstractDao;

@MybatisNamespaceProcessor(value = "com.glens.pwCloudOs.km.question.dao.QuestionVoMapper")
public class QuestionDao extends EAPAbstractDao {
	
	public List getTypes(Object parameters) {
		// TODO Auto-generated method stub
		try {

			return this.getSqlSession().selectList(getSqlStatement("getTypes"),
					parameters);
		} catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql="
					+ getSqlStatement("getTypes"), e);
		}
	}
	public List getCheckers(Object parameters) {
		// TODO Auto-generated method stub
		try {

			return this.getSqlSession().selectList(getSqlStatement("getCheckers"),
					parameters);
		} catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql="
					+ getSqlStatement("getCheckers"), e);
		}
	}
	
	
	public List getQuestionByCode(Object parameters) {
		// TODO Auto-generated method stub
		try {

			return this.getSqlSession().selectList(getSqlStatement("getQuestionByCode"),
					parameters);
		} catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql="
					+ getSqlStatement("getQuestionByCode"), e);
		}
	}
	

	
	public boolean insertQuestion(Object params) {
		try {
			int result = this.getSqlSession().insert(
					getSqlStatement("insertQuestion"), params);
			if (result > 0) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			throw new MyBatisAccessException("exe sql="
					+ getSqlStatement("insertQuestion"), e);
		}
	}
	
	public int delete(Object parameter) {
		// TODO Auto-generated method stub

		try {

			return this.getSqlSession().delete(
					getSqlStatement("delete"), parameter);
		} catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql="
					+ getSqlStatement("delete"), e);
		}
	}
	
	public int updateQuestion(Object params) {
		try {

			return this.getSqlSession().update(getSqlStatement("updateQuestion"),
					params);
		} catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql="
					+ getSqlStatement("updateQuestion"), e);
		}
	}
	
	public int updateStatus(Object params) {
		try {

			return this.getSqlSession().update(getSqlStatement("updateStatus"),
					params);
		} catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql="
					+ getSqlStatement("updateStatus"), e);
		}
	}
	
	
	public int updateReadNum(Object params) {
		try {

			return this.getSqlSession().update(getSqlStatement("updateReadNum"),
					params);
		} catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql="
					+ getSqlStatement("updateReadNum"), e);
		}
	}
	
	public int checkOption(Object params) {
		try {

			return this.getSqlSession().update(getSqlStatement("checkOption"),
					params);
		} catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql="
					+ getSqlStatement("checkOption"), e);
		}
	}
	
	
	public boolean insertKmBase(Object params) {
		try {
			int result = this.getSqlSession().insert(
					getSqlStatement("insertKmBase"), params);
			if (result > 0) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			throw new MyBatisAccessException("exe sql="
					+ getSqlStatement("insertKmBase"), e);
		}
	}
	
}
