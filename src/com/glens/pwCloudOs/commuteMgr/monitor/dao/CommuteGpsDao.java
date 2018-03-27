/**
 * @Title: CommuteGpsDao.java
 * @Package com.glens.pwCloudOs.commuteMgr.monitor.dao
 * @Description: TODO
 * Copyright: Copyright (c) 2016 
 * Company:南京量为石信息科技有限公司
 * @author 
 * @date 2016-7-14 下午2:31:29
 * @version V1.0
 */

package com.glens.pwCloudOs.commuteMgr.monitor.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.glens.eap.platform.core.annotation.MybatisNamespaceProcessor;
import com.glens.eap.platform.core.orm.mybatis.exception.MyBatisAccessException;
import com.glens.eap.platform.framework.beans.PageBean;
import com.glens.eap.platform.framework.dao.impl.EAPAbstractDao;
import com.glens.pwCloudOs.commuteMgr.monitor.vo.CpCommuteGpsVo;
import com.glens.pwCloudOs.websocket.web.AndroidSystemWebSocketHandler;

/**
 * 
 * 
 * @author
 * @version V1.0
 */

@MybatisNamespaceProcessor(value = "com.glens.pwCloudOs.commuteMgr.monitor.dao.monitorMapper")
public class CommuteGpsDao extends EAPAbstractDao {

	private static Log logger = LogFactory.getLog(CommuteGpsDao.class);

	public Integer selectRtCommuteStatusCount(Map<String, Object> params) {

		try {
			return this.getSqlSession().selectOne(
					getSqlStatement("selectRtCommuteStatusCount"), params);
		} catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql="
					+ getSqlStatement("selectRtCommuteStatusCount"), e);
		}
	}

	public PageBean selectRtCommuteStatus(Map<String, Object> params) {

		PageBean page = null;

		try {

			int totalCount = this.selectRtCommuteStatusCount(params);
			if (totalCount < 1) {

				page = new PageBean();
				page.setList(new ArrayList());
			} else {

				Integer currentPage = (Integer) params.get("currentPage");
				Integer perpage = (Integer) params.get("perpage");
				page = new PageBean(totalCount, currentPage, perpage);
				params.put("firstResult",
						page.getPerpage() * (page.getCurrentPage() - 1));
				params.put("maxResult", page.getPerpage());

				List<CpCommuteGpsVo> commuteList = this.getSqlSession()
						.selectList(getSqlStatement("selectRtCommuteStatus"),
								params);
				for (CpCommuteGpsVo c : commuteList) {
					if (AndroidSystemWebSocketHandler.isOnline(c
							.getAccountCode())) {
						c.setOnline(AndroidSystemWebSocketHandler.ONLINE);
					} else {
						c.setOnline(AndroidSystemWebSocketHandler.OFFLINE);
					}
				}
				page.setList(commuteList);
			}

			return page;
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("exe sql=" + getSqlStatement("selectRtCommuteStatus"),
					e);
			throw new MyBatisAccessException("exe sql="
					+ getSqlStatement("selectRtCommuteStatus"), e);
		}
	}

	public Integer selectOutdorWorkersForCount(Map<String, Object> params) {

		try {
			return this.getSqlSession().selectOne(
					getSqlStatement("selectOutdorWorkersForCount"), params);
		} catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql="
					+ getSqlStatement("selectOutdorWorkersForCount"), e);
		}
	}

	public PageBean selectOutdoorWorkers(Map<String, Object> params) {

		PageBean page = null;

		try {

			int totalCount = this.selectOutdorWorkersForCount(params);
			if (totalCount < 1) {

				page = new PageBean();
				page.setList(new ArrayList());
			} else {

				Integer currentPage = (Integer) params.get("currentPage");
				Integer perpage = (Integer) params.get("perpage");
				page = new PageBean(totalCount, currentPage, perpage);
				params.put("firstResult",
						page.getPerpage() * (page.getCurrentPage() - 1));
				params.put("maxResult", page.getPerpage());

				List<Map<String, Object>> employeeList = this.getSqlSession()
						.selectList(getSqlStatement("selectOutdoorWorkers"),
								params);
				page.setList(employeeList);
			}

			return page;
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("exe sql=" + getSqlStatement("selectOutdoorWorkers"),
					e);
			throw new MyBatisAccessException("exe sql="
					+ getSqlStatement("selectOutdoorWorkers"), e);
		}
	}

}
