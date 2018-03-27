package com.glens.pwCloudOs.gm.vm.filling.dao;

import java.util.List;
import java.util.Map;

import com.glens.eap.platform.core.annotation.MybatisNamespaceProcessor;
import com.glens.eap.platform.core.orm.mybatis.exception.MyBatisAccessException;
import com.glens.eap.platform.framework.dao.impl.EAPAbstractDao;
import com.glens.pwCloudOs.gm.vm.filling.vo.GmVmSinopecBill;
@MybatisNamespaceProcessor(value = "com.glens.pwCloudOs.gm.vm.filling.dao.GmVmSinopecBillMapper")
public class GmVmSinopecBillDao extends EAPAbstractDao {
	
	public List<GmVmSinopecBill> selectByCardNo(Map<String, Object> params) {
		try {
			return this.getSqlSession().selectList(getSqlStatement("selectByCardNo"), params);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new MyBatisAccessException("exe sql=" + getSqlStatement("selectByCardNo"), e);
		}
	}
	
	public int batchInsert(List<GmVmSinopecBill> bills) {
		try {
			return this.getSqlSession().insert(getSqlStatement("batchInsert"), bills);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new MyBatisAccessException("exe sql=" + getSqlStatement("batchInsert"), e);
		}
	}
	
	public GmVmSinopecBill selectByCardNoTimeAddr(GmVmSinopecBill params) {
		try {
			List<GmVmSinopecBill> res = this.getSqlSession().selectList(getSqlStatement("selectByCardNoTimeAddr"), params);
			if(res!=null && res.size()>0){
				return res.get(0);
			}else{
				return null;
			}
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new MyBatisAccessException("exe sql=" + getSqlStatement("selectByCardNoTimeAddr"), e);
		}
	}
	
	public List<GmVmSinopecBill> selectByCardNoDate(Map<String, Object> params) {
		try {
			return this.getSqlSession().selectList(getSqlStatement("selectByCardNoDate"), params);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new MyBatisAccessException("exe sql=" + getSqlStatement("selectByCardNoDate"), e);
		}
	}
	
}
