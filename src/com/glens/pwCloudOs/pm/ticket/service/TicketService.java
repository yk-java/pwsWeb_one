package com.glens.pwCloudOs.pm.ticket.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.glens.eap.platform.framework.service.impl.EAPAbstractService;

import com.glens.pwCloudOs.pm.ticket.dao.TicketDao;

public class TicketService extends EAPAbstractService {

	
	public int getNum(String companyCode){
		Map<String, Object> map=new HashMap();
		map.put("companyCode", companyCode);
		
		TicketDao dao=(TicketDao)getDao();
		List list=dao.getNum(map);
		int num=0;
		if(list!=null && list.size()>0){
			Map m=(Map)list.get(0);
			if(m.get("workticketCount")!=null){
				num=Integer.parseInt(m.get("workticketCount").toString());
			}
		}
		return num;
	}
	
	public boolean updateNum(String companyCode,int totalNum){
		TicketDao dao=(TicketDao)getDao();
		
		Map m=new HashMap();
		m.put("totalNum", totalNum);
		m.put("companyCode", companyCode);
		
		boolean isdelete=dao.updateNum(m);
		


		return isdelete;
	}
	
}
