package com.glens.pwCloudOs.km.base.dao;

import java.util.List;
import java.util.Map;

import com.glens.eap.platform.core.annotation.MybatisNamespaceProcessor;
import com.glens.eap.platform.core.orm.mybatis.exception.MyBatisAccessException;
import com.glens.eap.platform.framework.dao.impl.EAPAbstractDao;
import com.glens.pwCloudOs.km.base.vo.KmBaseVo;
import com.glens.pwCloudOs.pm.projDoc.vo.ProjDocVo;

@MybatisNamespaceProcessor(value = "com.glens.pwCloudOs.km.base.dao.KmBaseVoMapper")
public class KmBaseDao extends EAPAbstractDao {

	public int delete(Object parameter) {
		// TODO Auto-generated method stub

		try {

			return this.getSqlSession().delete(getSqlStatement("delete"),
					parameter);
		} catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql="
					+ getSqlStatement("delete"), e);
		}
	}

	public int deleteAttachment(Object parameter) {
		// TODO Auto-generated method stub

		try {

			return this.getSqlSession().delete(
					getSqlStatement("deleteAttachment"), parameter);
		} catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql="
					+ getSqlStatement("deleteAttachment"), e);
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

	public boolean insertAttachment(Object params) {
		try {
			int result = this.getSqlSession().insert(
					getSqlStatement("insertAttachment"), params);
			if (result > 0) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			throw new MyBatisAccessException("exe sql="
					+ getSqlStatement("insertAttachment"), e);
		}
	}

	public boolean insertDownStudy(Object params) {
		try {
			int result = this.getSqlSession().insert(
					getSqlStatement("insertDownStudy"), params);
			if (result > 0) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			throw new MyBatisAccessException("exe sql="
					+ getSqlStatement("insertDownStudy"), e);
		}
	}
	
	public boolean insertReadrecord(Object params) {
		try {
			int result = this.getSqlSession().insert(
					getSqlStatement("insertReadrecord"), params);
			if (result > 0) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			throw new MyBatisAccessException("exe sql="
					+ getSqlStatement("insertReadrecord"), e);
		}
	}
	

	public void updateDownStudyNum(Object params) {
		try {

			this.getSqlSession().update(getSqlStatement("updateDownStudyNum"),
					params);
		} catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql="
					+ getSqlStatement("updateDownStudyNum"), e);
		}
	}

	public void updateNum(Object params) {
		try {

			this.getSqlSession().update(getSqlStatement("updateNum"), params);
		} catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql="
					+ getSqlStatement("updateNum"), e);
		}
	}

	public void updateDownNum(Object params) {
		try {

			this.getSqlSession().update(getSqlStatement("updateDownNum"),
					params);
		} catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql="
					+ getSqlStatement("updateDownNum"), e);
		}
	}
	
	public boolean updatePublishStatus(Object params) {
		try {

			int result=this.getSqlSession().update(getSqlStatement("updatePublishStatus"),params);
			return result>0;
		} catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql="
					+ getSqlStatement("updatePublishStatus"), e);
		}
	}
	
	public boolean updateKmBae(Object params) {
		try {

			int result=this.getSqlSession().update(getSqlStatement("updateKmBae"),params);
			return result>0;
		} catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql="
					+ getSqlStatement("updateKmBae"), e);
		}
	}
	
	
	

	public List getTop5(Object parameters) {
		// TODO Auto-generated method stub
		try {

			return this.getSqlSession().selectList(getSqlStatement("getTop5"),
					parameters);
		} catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql="
					+ getSqlStatement("getTop5"), e);
		}
	}

	public List getAttachments(Object parameters) {
		// TODO Auto-generated method stub
		try {

			return this.getSqlSession().selectList(
					getSqlStatement("getAttachments"), parameters);
		} catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql="
					+ getSqlStatement("getAttachments"), e);
		}
	}

	public List catalogMap(Object parameters) {
		// TODO Auto-generated method stub
		try {

			return this.getSqlSession().selectList(
					getSqlStatement("catalogMap"), parameters);
		} catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql="
					+ getSqlStatement("catalogMap"), e);
		}
	}

	public List getPersonRead(Object parameters) {
		// TODO Auto-generated method stub
		try {

			return this.getSqlSession().selectList(
					getSqlStatement("getPersonRead"), parameters);
		} catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql="
					+ getSqlStatement("getPersonRead"), e);
		}
	}

	public List queryKmBaseList(Map map) {
		// TODO Auto-generated method stub
		try {

			return this.getSqlSession().selectList(
					getSqlStatement("queryKmBaseList"), map);
		} catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql="
					+ getSqlStatement("queryKmBaseList"), e);
		}
	}

	public List<String> querySubCatalogList(String catalogCode) {
		try {

			return this.getSqlSession().selectList(
					getSqlStatement("querySubCatalogList"), catalogCode);
		} catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql="
					+ getSqlStatement("querySubCatalogList"), e);
		}
	}

	public KmBaseVo queryKbBase(long rowid) {
		try {
			return this.getSqlSession().selectOne(
					getSqlStatement("queryKbBase"), rowid);
		} catch (Exception e) {
			throw new MyBatisAccessException("exe sql="
					+ getSqlStatement("queryKbBase"), e);
		}
	}

	public List queryKmBasekeyWordList(Map<String, Object> m) {
		try {

			return this.getSqlSession().selectList(
					getSqlStatement("queryKmBasekeyWordList"), m);
		} catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql="
					+ getSqlStatement("queryKmBasekeyWordList"), e);
		}
	}

	public int queryKmBaseCount(Map<String, Object> paramsMap) {
		try {
			return this.getSqlSession().selectOne(
					getSqlStatement("queryKmBaseCount"), paramsMap);
		} catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql="
					+ getSqlStatement("queryKmBaseCount"), e);
		}
	}

}
