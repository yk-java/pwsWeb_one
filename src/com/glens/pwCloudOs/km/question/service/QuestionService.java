package com.glens.pwCloudOs.km.question.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.aspectj.weaver.patterns.TypePatternQuestions.Question;

import com.glens.eap.platform.es.EsQuestionService;
import com.glens.eap.platform.es.impl.ElasticSearchServiceImpl;
import com.glens.eap.platform.framework.beans.PageBean;
import com.glens.eap.platform.framework.codeCenter.CodeWorker;
import com.glens.eap.platform.framework.context.EAPContext;
import com.glens.eap.platform.framework.service.impl.EAPAbstractService;
import com.glens.pwCloudOs.km.base.vo.EskmBase;
import com.glens.pwCloudOs.km.base.vo.KmBaseVo;
import com.glens.pwCloudOs.km.question.dao.QuestionDao;
import com.glens.pwCloudOs.km.question.vo.EsQuestionVo;
import com.glens.pwCloudOs.km.question.web.QuestionForm;

public class QuestionService extends EAPAbstractService {
	
	private EsQuestionService esQuestionService;
	
	private ElasticSearchServiceImpl esService;
	
	public ElasticSearchServiceImpl getEsService() {
		return esService;
	}

	public void setEsService(ElasticSearchServiceImpl esService) {
		this.esService = esService;
	}

	public EsQuestionService getEsQuestionService() {
		return esQuestionService;
	}

	public void setEsQuestionService(EsQuestionService esQuestionService) {
		this.esQuestionService = esQuestionService;
	}

	public List getTypes(Object parameters) {
		QuestionDao dao=(QuestionDao)getDao();
		return dao.getTypes(parameters);
	}
	public List getCheckers(Object parameters) {
		QuestionDao dao=(QuestionDao)getDao();
		return dao.getCheckers(parameters);
	}
	
	
	public List getQuestionByCode(Object parameters) {
		QuestionDao dao=(QuestionDao)getDao();
		return dao.getQuestionByCode(parameters);
	}
	
	@Override
	public PageBean queryForPage(Map parameters, int currentPage, int perpage) {
		// TODO Auto-generated method stub
		PageBean page=this.dao.queryForPage(parameters, currentPage, perpage);
		
		/*
		List list=page.getList();
		
		SimpleDateFormat ds=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String date=ds.format(new Date());
		
		
            
		
		page.setList(returnList);*/
		return page;
		
		
	}
	
	public boolean insertQuestion(Object parameters,QuestionForm form){
		
		CodeWorker codeWorker = (CodeWorker) EAPContext.getContext().getBean(
				CodeWorker.SIMPLE_CODE_WORKER);
		
		String questionCode = codeWorker.createCode("Q");
		
		SimpleDateFormat ds=new SimpleDateFormat("yyyy-MM-dd");
		String date=ds.format(new Date());
		form.setQuestionCode(questionCode);
		
		EsQuestionVo vo=(EsQuestionVo)parameters;
		
		vo.setQuestionCode(questionCode);
		
		QuestionDao dao=(QuestionDao)getDao();
		boolean isok=dao.insertQuestion(vo);
		
		
		/*EsQuestionVo question=new EsQuestionVo();
		Long rowid=vo.getRowid();
		
		
		question.setRowid(vo.getRowid());
		
		
		
		
		question.setQuestionCode(questionCode);
		question.setAnswer(form.getAnswer());
		question.setTypeName(form.getTypeName());
		question.setCollator(form.getCollator());
		question.setCompanyCode(form.getCompanyCode());
		
		question.setCreateDate(date);
		question.setKeywords(form.getKeywords());
		question.setPlanSoveDate(form.getPlanSoveDate());
		
		
		String str = "";
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        Calendar lastDate = Calendar.getInstance();
        lastDate.roll(Calendar.DATE, 7);//日期回滚7天
        str=sdf.format(lastDate.getTime());
		
		question.setAlertDate(str);
		
		
		question.setPublicStatus("1");
		question.setQuestionContent(form.getQuestionContent());
		question.setQuestionTitle(form.getQuestionTitle());
		question.setRemarks(form.getRemarks());
		
		esQuestionService.addIndex(question);*/
		
		return isok;
		
	}
	
	
	public int delete(Object parameters,Long rowid) {
		
		QuestionDao dao=(QuestionDao)getDao();
		/*EsQuestionVo question=new EsQuestionVo();
		question.setRowid(rowid);
		esQuestionService.delIndex(question);*/
		
		return dao.delete(parameters);
	}
	
	public int updateQuestion(Object parameters,QuestionForm form) {
		
		QuestionDao dao=(QuestionDao)getDao();
		
		
		/*EsQuestionVo question=new EsQuestionVo();
		question.setRowid(form.getRowid());
		question.setAnswer(form.getAnswer());
		question.setCatalogName(form.getCatalogName());
		question.setChecker(form.getChecker());
		question.setCheckerCodes(form.getCheckerCodes());
		question.setCheckOption(form.getCheckOption());
		question.setCollator(form.getCollator());
		question.setCompanyCode(form.getCompanyCode());
		question.setKeywords(form.getKeywords());
		question.setPlanSoveDate(form.getPlanSoveDate());
		question.setAlertDate(form.getAlertTime());
		question.setCreateDate(form.getCreateTime());
		question.setProName(form.getProName());
		question.setProNo(form.getProNo());
		question.setPublicStatus(form.getPublicStatus()+"");
		question.setQuestionCode(form.getQuestionCode());
		question.setQuestionContent(form.getQuestionContent());
		question.setQuestionTitle(form.getQuestionTitle());
		question.setSovleDate(form.getSovleDate());
		question.setSovler(form.getSovler());
		question.setTypeName(form.getTypeName());
		question.setTypeCode(form.getTypeCode());
		
		esQuestionService.updateIndex(question);*/
		
		//Map m=new HashMap();
		if(form.getPublicStatus()==5){ //归档
			String title="<p style='font-size:14px;font-weight:700;margin-top:10px;'>问题答案</p>";
			
			/*m.put("btext", form.getQuestionContent()+title+form.getAnswer());
			m.put("remarks", form.getRemarks());*/
		
			
			KmBaseVo vo=new KmBaseVo();
			vo.setCompanyCode(form.getCompanyCode());
			vo.setCatalogCode(form.getCatalogCode());
			vo.setFileCode(form.getQuestionCode());
			vo.setFileTitle(form.getQuestionTitle());
			vo.setKeywords(form.getKeywords());
			vo.setEmployeeName(form.getEmployeeName());
			vo.setEmployeeCode(form.getEmployeeCode());
			vo.setBtext(form.getQuestionContent()+title+form.getAnswer());
			vo.setRemarks(form.getRemarks());
			
		    dao.insertKmBase(vo);
		    
		    System.out.println(vo.getRowid()+"================");
		    
		    EskmBase eb = new EskmBase();
			eb.setBtext(form.getQuestionContent());
			eb.setCatalogName(form.getCatalogName());
			eb.setFileTitle(form.getQuestionTitle());
			eb.setKeywords(form.getKeywords());
			eb.setRowid(vo.getRowid());
			eb.setCreateUser(form.getEmployeeName());
			
			
			SimpleDateFormat ds=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String date=ds.format(new Date());
			
			eb.setCreateTime(date);

			esService.addIndex(eb);
		    
		    return dao.updateStatus(parameters);
		}else if(form.getPublicStatus()==4){
			return dao.updateStatus(parameters);
		}else{
			
			//esService.updateIndex(kmbase);
			
			return dao.updateQuestion(parameters);
		}
		
		
	}
	public int updateReadNum(Object parameters) {
		
		QuestionDao dao=(QuestionDao)getDao();
		return dao.updateReadNum(parameters);
	}
	
	public int checkOption(Object parameters,QuestionForm form) {
		
		QuestionDao dao=(QuestionDao)getDao();
		
		/*Map m=new HashMap();
		
		m.put("questionCode", form.getQuestionCode());
		List list=dao.getQuestionByCode(m);
		if(list!=null && list.size()>0){
			Map mp=(Map)list.get(0);
			
			EsQuestionVo vo=new EsQuestionVo();
			
			vo.setRowid(form.getRowid());
			if(mp.get("ROW_ID")!=null){
				vo.setRowid(Long.parseLong(mp.get("ROW_ID").toString()));
			}
			if(mp.get("ANSWER")!=null){
				vo.setAnswer(mp.get("ANSWER").toString());
			}
			if(mp.get("CHECKER")!=null){
				vo.setChecker(mp.get("CHECKER").toString());
			}
			if(mp.get("CHECKERCODES")!=null){
				vo.setCheckerCodes(mp.get("CHECKERCODES").toString());
			}
			if(mp.get("CHECKOPTION")!=null){
				vo.setCheckOption(mp.get("CHECKOPTION").toString());
			}
			if(mp.get("COLLATOR")!=null){
				vo.setCollator(mp.get("COLLATOR").toString());
			}
			if(mp.get("COMPANY_CODE")!=null){
				vo.setCompanyCode(mp.get("COMPANY_CODE").toString());
			}
			if(mp.get("KEYWORDS")!=null){
				vo.setKeywords(mp.get("KEYWORDS").toString());
			}
			if(mp.get("PLANSOVLE_DATE")!=null){
				vo.setPlanSoveDate(mp.get("PLANSOVLE_DATE").toString());
			}
			if(mp.get("SYS_CREATE_TIME")!=null){
				vo.setCreateDate(mp.get("SYS_CREATE_TIME").toString());
			}
			if(mp.get("PRO_NAME")!=null){
				vo.setProName(mp.get("PRO_NAME").toString());
			}
			if(mp.get("PRO_NO")!=null){
				vo.setProNo(mp.get("PRO_NO").toString());
			}
			if(mp.get("QUESTION_CONTENT")!=null){
				vo.setQuestionContent(mp.get("QUESTION_CONTENT").toString());
			}
			if(mp.get("QUESTION_TITLE")!=null){
				vo.setQuestionTitle(mp.get("QUESTION_TITLE").toString());
			}
			
			if(mp.get("SOVLE_DATE")!=null){
				vo.setSovleDate(mp.get("SOVLE_DATE").toString());
			}
			if(mp.get("SOVLER")!=null){
				vo.setSovler(mp.get("SOVLER").toString());
			}
			if(mp.get("TYPE_NAME")!=null){
				vo.setTypeName(mp.get("TYPE_NAME").toString());
			}
			if(mp.get("TYPE_CODE")!=null){
				vo.setTypeCode(mp.get("TYPE_CODE").toString());
			}
			if(mp.get("ALERTTIME")!=null){
				vo.setAlertDate(mp.get("ALERTTIME").toString());
			}
			
			
			vo.setPublicStatus(form.getPublicStatus()+"");
			vo.setQuestionCode(form.getQuestionCode());
			
			
			esQuestionService.updateIndex(vo);
		}*/
		
		
		
		return dao.checkOption(parameters);
	}
	
	public PageBean queryQuestionList(Map<String, Object> m,int pageSize,int pageNo,String fullTextSearch,int publishstatus,String status) {
		String questionTitle = (String) m.get("questionTitle");
		String tempStatus="";
		if(publishstatus!=0){
			tempStatus=publishstatus+"";
		}
		PageBean page = esQuestionService.queryForPage(questionTitle, pageSize, pageNo,fullTextSearch,tempStatus,status);
		
		List list=page.getList();
		
		SimpleDateFormat ds=new SimpleDateFormat("yyyy-MM-dd");
		String date=ds.format(new Date());
		
		List returnList=new ArrayList();
		for(int i=0;i<list.size();i++){
			EsQuestionVo vo=(EsQuestionVo)list.get(i);
			String planTime=vo.getPlanSoveDate();
			
			if(planTime==null || planTime.equals("")){
				vo.setStatus(0);
				returnList.add(vo);
			}else{
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		        
		        try {
					Date date2 = format.parse(planTime);
					Date date1 = format.parse(date);
					
					if(differentDaysByMillisecond(date1,date2)>3){//大于10天
						vo.setStatus(0); //正常
					}else if(differentDaysByMillisecond(date1,date2)<=3 && differentDaysByMillisecond(date1,date2)>=0){ //告警
						vo.setStatus(1);
					}else{
						vo.setStatus(2);//逾期
					}
					returnList.add(vo);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
            
		}
		page.setList(returnList);
		return page;
	}
	
	public int differentDaysByMillisecond(Date date1,Date date2)
    {
        int days = (int) ((date2.getTime() - date1.getTime()) / (1000*3600*24));
        return days;
    }
	
}
