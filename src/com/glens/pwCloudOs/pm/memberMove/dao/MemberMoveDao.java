package com.glens.pwCloudOs.pm.memberMove.dao;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.glens.eap.platform.core.annotation.MybatisNamespaceProcessor;
import com.glens.eap.platform.core.orm.mybatis.exception.MyBatisAccessException;
import com.glens.eap.platform.framework.web.EAPAbstractController;
import com.glens.pwCloudOs.materielMg.comprehensiveQuery.dao.ComprehensiveQueryDao;
import com.glens.pwCloudOs.pm.memberMove.vo.PmMemberMoveVo;
import com.glens.eap.platform.framework.dao.impl.EAPAbstractDao;

@MybatisNamespaceProcessor(value="com.glens.pwCloudOs.pm.memberMove.dao.MemberMoveVoMapper")
public class MemberMoveDao extends EAPAbstractDao {

	public boolean insert(Object parameters) {
		// TODO Auto-generated method stub

		try {



			PmMemberMoveVo v=(PmMemberMoveVo)parameters;
			String validDate=v.getValidDate();//生效日期
			String moveday=v.getMoveDate();//调动日期
			SimpleDateFormat ds=new SimpleDateFormat("yyyy-MM-dd");
			String today=ds.format(new Date());

			Date validday=ds.parse(validDate);
			Date moved=ds.parse(moveday);
			String vd=ds.format(validday);

			String moveType=v.getMoveType();

			if(moveType.equals("1")){//项目内调动
				/*if(vd.equals(today)||moved.after(validday)){//生效日期是当天的话  或者生效日期在调用日期之后
					v.setIsFinish(1);
					this.getSqlSession().insert(getSqlStatement(INSERT_STATEMENT), v);
					//this.getSqlSession().insert(getSqlStatement(INSERT_STATEMENT), v);
					this.getSqlSession().update(getSqlStatement(UPDATE_STATEMENT), parameters);
					this.getSqlSession().selectList(getSqlStatement("insertPmMember"), parameters);//新增
					if(!v.getJobCode1().equals(v.getJobCode2())){//如果两个职务不一样 则修改用户职务
						this.getSqlSession().update(getSqlStatement("updateProEmployeeCode"), parameters);//更新职务
					}

				}else{*/
				v.setIsFinish(0);
				this.getSqlSession().insert(getSqlStatement(INSERT_STATEMENT),v);
				//}
			}else if(moveType.equals("2")){//项目--部门
				/*if(vd.equals(today)||moved.after(validday)){//生效日期是当天的话  或者生效日期在调用日期之后
					v.setIsFinish(1);
					this.getSqlSession().insert(getSqlStatement(INSERT_STATEMENT), v);
					this.getSqlSession().update(getSqlStatement(UPDATE_STATEMENT), parameters);
					this.getSqlSession().update(getSqlStatement("updateEmployeeCode1"), parameters); //修改员工基本信息
				}else{*/
				v.setIsFinish(0);
				this.getSqlSession().insert(getSqlStatement(INSERT_STATEMENT),v);
				//}
			}else if(moveType.equals("3")){//部门--项目
				/*if(vd.equals(today)||moved.after(validday)){//生效日期是当天的话  或者生效日期在调用日期之后
					v.setIsFinish(1);
					this.getSqlSession().insert(getSqlStatement(INSERT_STATEMENT), v);
					this.getSqlSession().selectList(getSqlStatement("insertPmMember"), parameters);//新增
					this.getSqlSession().update(getSqlStatement("updateEmployeeCode"), parameters); //修改员工基本信息
				}else{*/
				v.setIsFinish(0);
				this.getSqlSession().insert(getSqlStatement(INSERT_STATEMENT),v);
				//}
			}else if(moveType.equals("4")){//部门--部门
				/*if(vd.equals(today)||moved.after(validday)){//生效日期是当天的话  或者生效日期在调用日期之后
					v.setIsFinish(1);
					this.getSqlSession().insert(getSqlStatement(INSERT_STATEMENT), v);
					//this.getSqlSession().selectList(getSqlStatement("insertPmMember"), parameters);//新增
					this.getSqlSession().update(getSqlStatement("updateEmployeeCode1"), parameters); //修改员工基本信息
				}else{*/
				v.setIsFinish(0);
				this.getSqlSession().insert(getSqlStatement(INSERT_STATEMENT),v);
				//}
			}

			return true;
		}
		catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql=" + getSqlStatement(INSERT_STATEMENT), e);

		}
	}

	//新增流程

	public boolean insertProc(Object parameters) {
		// TODO Auto-generated method stub

		try {

			this.getSqlSession().insert(getSqlStatement("insertProc"), parameters);

			return true;
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new MyBatisAccessException("exe sql=" + getSqlStatement("insertProc"), e);

		}
	}



	//定时任务更新

	public void updateMoves(Object parameters) {
		PmMemberMoveVo v=(PmMemberMoveVo)parameters;


		try {

			String moveType=v.getMoveType();

			if(moveType.equals("1")){//项目内调动
				this.getSqlSession().update(getSqlStatement("updateFinish"), parameters);//新增
				this.getSqlSession().update(getSqlStatement(UPDATE_STATEMENT), parameters);
				this.getSqlSession().selectList(getSqlStatement("insertPmMember"), parameters);//新增

				if(!v.getJobCode1().equals(v.getJobCode2())){//如果两个职务不一样 则修改用户职务
					this.getSqlSession().update(getSqlStatement("updateProEmployeeCode"), parameters);//更新职务
				}

			}else if(moveType.equals("2")){//项目调部门
				this.getSqlSession().update(getSqlStatement("updateFinish"), parameters);//更新状态
				this.getSqlSession().update(getSqlStatement(UPDATE_STATEMENT), parameters);//离开项目
				this.getSqlSession().update(getSqlStatement("updateEmployeeCode1"), parameters); //更新员工所在部门


			}else if(moveType.equals("3")){//部门调项目
				this.getSqlSession().update(getSqlStatement("updateFinish"), parameters);//更新状态
				this.getSqlSession().selectList(getSqlStatement("insertPmMember"), parameters);//添加到项目组
				this.getSqlSession().update(getSqlStatement("updateEmployeeCode"), parameters); //更新员工所在部门

			}else if(moveType.equals("4")){//部门调部门
				this.getSqlSession().update(getSqlStatement("updateFinish"), parameters);//更新状态
				//this.getSqlSession().update(getSqlStatement(UPDATE_STATEMENT), parameters);//离开项目
				this.getSqlSession().update(getSqlStatement("updateEmployeeCode1"), parameters); //更新员工所在部门
			}


		}
		catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql=" + getSqlStatement("getJobs"), e);
		}
	}







	public List<Map<String, Object>> getJobs() {

		try {


			List<Map<String, Object>> resultList = this.getSqlSession().selectList(getSqlStatement("getJobs"), null);


			return resultList;
		}
		catch (Exception e) {
			// TODO: handle exception


			throw new MyBatisAccessException("exe sql=" + getSqlStatement("getJobs"), e);
		}
	}

	public List<Map<String, Object>> memberMoveIn(Map<String, String> params) {

		try {


			List<Map<String, Object>> resultList = this.getSqlSession().selectList(getSqlStatement("memberMoveIn"), params);


			return resultList;
		}
		catch (Exception e) {
			// TODO: handle exception


			throw new MyBatisAccessException("exe sql=" + getSqlStatement("getJobs"), e);
		}
	}

	public List<Map<String, Object>> memberMoveOut(Map<String, String> params) {

		try {


			List<Map<String, Object>> resultList = this.getSqlSession().selectList(getSqlStatement("memberMoveOut"), params);


			return resultList;
		}
		catch (Exception e) {
			// TODO: handle exception


			throw new MyBatisAccessException("exe sql=" + getSqlStatement("getJobs"), e);
		}
	}

	public List<Map<String, Object>> memberMoveChar(Map<String, String> params) {

		try {


			List<Map<String, Object>> resultList = this.getSqlSession().selectList(getSqlStatement("memberMoveChar"), params);


			return resultList;
		}
		catch (Exception e) {
			// TODO: handle exception


			throw new MyBatisAccessException("exe sql=" + getSqlStatement("getJobs"), e);
		}
	}
	public List<Map<String, Object>> memberMoves(Map<String, String> params) {

		try {


			List<Map<String, Object>> resultList = this.getSqlSession().selectList(getSqlStatement("memberMoves"), params);


			return resultList;
		}
		catch (Exception e) {
			// TODO: handle exception


			throw new MyBatisAccessException("exe sql=" + getSqlStatement("getJobs"), e);
		}
	}

	public List<Map<String, Object>> getMovelist() {

		try {


			List<Map<String, Object>> resultList = this.getSqlSession().selectList(getSqlStatement("getMovelist"), null);


			return resultList;
		}
		catch (Exception e) {
			// TODO: handle exception


			throw new MyBatisAccessException("exe sql=" + getSqlStatement("getMovelist"), e);
		}
	}

	public List<Map<String, Object>> getMovelist1(Object params) {

		try {


			List<Map<String, Object>> resultList = this.getSqlSession().selectList(getSqlStatement("getMovelist1"), params);


			return resultList;
		}
		catch (Exception e) {
			// TODO: handle exception


			throw new MyBatisAccessException("exe sql=" + getSqlStatement("getMovelist1"), e);
		}
	}


	public List getmanagerinpro(Object params){
		try {
			List<Map<String, Object>> resultList = this.getSqlSession().selectList(getSqlStatement("getmanagerinpro"), params);
			return resultList;
		}
		catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql=" + getSqlStatement("getmanagerinpro"), e);
		}
	}

	public List getmanageroutpro(Object params){
		try {
			List<Map<String, Object>> resultList = this.getSqlSession().selectList(getSqlStatement("getmanageroutpro"), params);
			return resultList;
		}
		catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql=" + getSqlStatement("getmanageroutpro"), e);
		}
	}
	
	public int updateProc(Object parameters) {
		// TODO Auto-generated method stub
		try {
			
			return this.getSqlSession().update(getSqlStatement("updateProc"), parameters);
		}
		catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql=" + getSqlStatement("updateProc"), e);
		}
	}

	public int updateProcStatus(Object parameters) {
		// TODO Auto-generated method stub
		try {
			
			return this.getSqlSession().update(getSqlStatement("updateProcStatus"), parameters);
		}
		catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql=" + getSqlStatement("updateProcStatus"), e);
		}
	}
	
	public List getListByCode(Object params){
		try {
			List<Map<String, Object>> resultList = this.getSqlSession().selectList(getSqlStatement("getListByCode"), params);
			return resultList;
		}
		catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql=" + getSqlStatement("getListByCode"), e);
		}
	}
	public List getDetailProc(Object params){
		try {
			List<Map<String, Object>> resultList = this.getSqlSession().selectList(getSqlStatement("getDetailProc"), params);
			return resultList;
		}
		catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql=" + getSqlStatement("getDetailProc"), e);
		}
	}
	
	public List employeeAges(Object params){
		try {
			List<Map<String, Object>> resultList = this.getSqlSession().selectList(getSqlStatement("employeeAges"), params);
			return resultList;
		}
		catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql=" + getSqlStatement("employeeAges"), e);
		}
	}
	public List degreeStatics(Object params){
		try {
			List<Map<String, Object>> resultList = this.getSqlSession().selectList(getSqlStatement("degreeStatics"), params);
			return resultList;
		}
		catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql=" + getSqlStatement("degreeStatics"), e);
		}
	}
	
	public List jobsStatics(Object params){
		try {
			List<Map<String, Object>> resultList = this.getSqlSession().selectList(getSqlStatement("jobsStatics"), params);
			return resultList;
		}
		catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql=" + getSqlStatement("jobsStatics"), e);
		}
	}
	
	public List majorDegreeStatics(Object params){
		try {
			List<Map<String, Object>> resultList = this.getSqlSession().selectList(getSqlStatement("majorDegreeStatics"), params);
			return resultList;
		}
		catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql=" + getSqlStatement("majorDegreeStatics"), e);
		}
	}
	public List getHumanResources(Object params){
		try {
			List<Map<String, Object>> resultList = this.getSqlSession().selectList(getSqlStatement("getHumanResources"), params);
			return resultList;
		}
		catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql=" + getSqlStatement("getHumanResources"), e);
		}
	}
	
	
	public List isManager(Object params){
		try {
			List<Map<String, Object>> resultList = this.getSqlSession().selectList(getSqlStatement("isManager"), params);
			return resultList;
		}
		catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql=" + getSqlStatement("isManager"), e);
		}
	}
	
	public List getMoveMessage(Object params){
		try {
			List<Map<String, Object>> resultList = this.getSqlSession().selectList(getSqlStatement("getMoveMessage"), params);
			return resultList;
		}
		catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql=" + getSqlStatement("getMoveMessage"), e);
		}
	}
	
	public List getEmployeeMessage(Object params){
		try {
			List<Map<String, Object>> resultList = this.getSqlSession().selectList(getSqlStatement("getEmployeeMessage"), params);
			return resultList;
		}
		catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql=" + getSqlStatement("getEmployeeMessage"), e);
		}
	}
	
	
	public List getAcccount(Object params){
		try {
			List<Map<String, Object>> resultList = this.getSqlSession().selectList(getSqlStatement("getAcccount"), params);
			return resultList;
		}
		catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql=" + getSqlStatement("getAcccount"), e);
		}
	}
	public List getSendList(){
		try {
			List<Map<String, Object>> resultList = this.getSqlSession().selectList(getSqlStatement("getSendList"), null);
			return resultList;
		}
		catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql=" + getSqlStatement("getSendList"), e);
		}
	}
	
}
