package com.glens.pwCloudOs.gm.vm.filling.dao;

import java.util.List;
import java.util.Map;

import com.glens.eap.platform.core.annotation.MybatisNamespaceProcessor;
import com.glens.eap.platform.core.orm.mybatis.exception.MyBatisAccessException;
import com.glens.eap.platform.framework.dao.impl.EAPAbstractDao;
import com.glens.pwCloudOs.gm.vm.filling.vo.GmVmFillingRecord;
import com.glens.pwCloudOs.gm.vm.filling.vo.GmVmSinopecBill;
@MybatisNamespaceProcessor(value = "com.glens.pwCloudOs.gm.vm.filling.dao.GmVmFillingRecordMapper")
public class GmVmFillingRecordDao extends EAPAbstractDao {
	public List<GmVmFillingRecord> selectUncheckList() {
		try {
			return this.getSqlSession().selectList(getSqlStatement("selectUncheckList"), null);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new MyBatisAccessException("exe sql=" + getSqlStatement("selectUncheckList"), e);
		}
	}
	public int updateCheckState(GmVmFillingRecord params) {
		try {
			return this.getSqlSession().update(getSqlStatement("updateCheckState"), params);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new MyBatisAccessException("exe sql=" + getSqlStatement("updateCheckState"), e);
		}
	}
	
	
	public GmVmFillingRecord selectByCardNoDateUser(GmVmFillingRecord params) {
		try {
			return this.getSqlSession().selectOne(getSqlStatement("selectByCardNoDateUser"), params);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new MyBatisAccessException("exe sql=" + getSqlStatement("selectByCardNoDateUser"), e);
		}
	}
	
	
}
