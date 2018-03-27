package com.glens.pwCloudOs.transmission.tower.dao;

import java.util.List;
import java.util.Map;

import com.glens.eap.platform.core.annotation.MybatisNamespaceProcessor;
import com.glens.eap.platform.core.orm.mybatis.exception.MyBatisAccessException;
import com.glens.eap.platform.framework.dao.impl.EAPAbstractDao;

@MybatisNamespaceProcessor(value="com.glens.pwCloudOs.transmission.tower.dao.towerDaoMapper")
public class TowerDao extends EAPAbstractDao {
	
	
	public List<Map<String, String>> getVoltageLevel(Map<String, Object> params) {

		try {

			return this.getSqlSession().selectList(this.getSqlStatement("getVoltageLevel"), params);
		}
		catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql=" + getSqlStatement("getVoltageLevel"), e);
		}
	}
	
	public List<Map<String, String>> getLines(Map<String, Object> params) {

		try {

			return this.getSqlSession().selectList(this.getSqlStatement("getLines"), params);
		}
		catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql=" + getSqlStatement("getLines"), e);
		}
	}
	
	public List<Map<String, String>> getTowers(Map<String, Object> params) {

		try {

			return this.getSqlSession().selectList(this.getSqlStatement("getTowers"), params);
		}
		catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql=" + getSqlStatement("getTowers"), e);
		}
	}
	public List<Map<String, String>> getTowerDetail(Map<String, Object> params) {

		try {

			return this.getSqlSession().selectList(this.getSqlStatement("getTowerDetail"), params);
		}
		catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql=" + getSqlStatement("getTowerDetail"), e);
		}
	}
	public List<Map<String, String>> getPics(Map<String, Object> params) {

		try {

			return this.getSqlSession().selectList(this.getSqlStatement("getPics"), params);
		}
		catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql=" + getSqlStatement("getPics"), e);
		}
	}
	public List<Map<String, String>> getDianYa(Map<String, Object> params) {

		try {

			return this.getSqlSession().selectList(this.getSqlStatement("getDianYa"), params);
		}
		catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql=" + getSqlStatement("getDianYa"), e);
		}
	}
	
	public List<Map<String, String>> getTowerslist(Map<String, Object> params) {

		try {

			return this.getSqlSession().selectList(this.getSqlStatement("getTowerslist"), params);
		}
		catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql=" + getSqlStatement("getTowerslist"), e);
		}
	}
	
	
	
	public boolean addTower(Object parameters) {
		// TODO Auto-generated method stub
		
		try {
			
			return this.getSqlSession().insert(getSqlStatement("addTower"), parameters) > 0;
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new MyBatisAccessException("exe sql=" + getSqlStatement("addTower"), e);
			
		}
	}
	
	public boolean addDeviceRange(Object parameters) {
		// TODO Auto-generated method stub
		
		try {
			
			return this.getSqlSession().insert(getSqlStatement("addDeviceRange"), parameters) > 0;
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new MyBatisAccessException("exe sql=" + getSqlStatement("addDeviceRange"), e);
			
		}
	}
	
	public boolean addPic(Object parameters) {
		// TODO Auto-generated method stub
		
		try {
			
			return this.getSqlSession().insert(getSqlStatement("addPic"), parameters) > 0;
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new MyBatisAccessException("exe sql=" + getSqlStatement("addPic"), e);
			
		}
	}
	
	public List<Map<String, String>> getDefectList(Map<String, Object> params) {

		try {

			return this.getSqlSession().selectList(this.getSqlStatement("getDefectList"), params);
		}
		catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql=" + getSqlStatement("getDefectList"), e);
		}
	}
	
	
	
	public int checkDefect(Object parameters) {
		// TODO Auto-generated method stub
		try {
			
			return this.getSqlSession().update(getSqlStatement("checkDefect"), parameters);
		}
		catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql=" + getSqlStatement("checkDefect"), e);
		}
	}
	public List<Map<String, String>> getPassList(Map<String, Object> params) {

		try {

			return this.getSqlSession().selectList(this.getSqlStatement("getPassList"), params);
		}
		catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql=" + getSqlStatement("getPassList"), e);
		}
	}
	public int workOutDefect(Object parameters) {
		// TODO Auto-generated method stub
		try {
			
			return this.getSqlSession().update(getSqlStatement("workOutDefect"), parameters);
		}
		catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql=" + getSqlStatement("workOutDefect"), e);
		}
	}
	
	public List<Map<String, String>> getAreaList(Map<String, Object> params) {

		try {

			return this.getSqlSession().selectList(this.getSqlStatement("getAreaList"), params);
		}
		catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql=" + getSqlStatement("getAreaList"), e);
		}
	}
	public List<Map<String, String>> getTeam1(Map<String, Object> params) {

		try {

			return this.getSqlSession().selectList(this.getSqlStatement("getTeam1"), params);
		}
		catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql=" + getSqlStatement("getTeam1"), e);
		}
	}
	public List<Map<String, String>> getTeam2(Map<String, Object> params) {

		try {

			return this.getSqlSession().selectList(this.getSqlStatement("getTeam2"), params);
		}
		catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql=" + getSqlStatement("getTeam2"), e);
		}
	}
	
}
