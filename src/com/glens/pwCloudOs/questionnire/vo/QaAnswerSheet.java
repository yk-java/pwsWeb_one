package com.glens.pwCloudOs.questionnire.vo;

import java.util.Date;
import java.util.List;

import com.glens.eap.platform.core.beans.ValueObject;

public class QaAnswerSheet implements ValueObject {
	private Long rowid;
	private String answerSheetCode;
	private String questionnireCode;
	private String submitUser;
	private String employeeName;// for View
	private Date submitTime;
	private Float score;
	private String levelCode;
	private String levelDescr;// for View
	private List<QaAnswer> answers;
	public Long getRowid() {
		return rowid;
	}
	public void setRowid(Long rowid) {
		this.rowid = rowid;
	}
	public String getAnswerSheetCode() {
		return answerSheetCode;
	}
	public void setAnswerSheetCode(String answerSheetCode) {
		this.answerSheetCode = answerSheetCode;
	}
	public String getQuestionnireCode() {
		return questionnireCode;
	}
	public void setQuestionnireCode(String questionnireCode) {
		this.questionnireCode = questionnireCode;
	}
	public String getSubmitUser() {
		return submitUser;
	}
	public void setSubmitUser(String submitUser) {
		this.submitUser = submitUser;
	}
	public String getEmployeeName() {
		return employeeName;
	}
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}
	public Date getSubmitTime() {
		return submitTime;
	}
	public void setSubmitTime(Date submitTime) {
		this.submitTime = submitTime;
	}
	public Float getScore() {
		return score;
	}
	public void setScore(Float score) {
		this.score = score;
	}
	public String getLevelCode() {
		return levelCode;
	}
	public void setLevelCode(String levelCode) {
		this.levelCode = levelCode;
	}
	public String getLevelDescr() {
		return levelDescr;
	}
	public void setLevelDescr(String levelDescr) {
		this.levelDescr = levelDescr;
	}
	public List<QaAnswer> getAnswers() {
		return answers;
	}
	public void setAnswers(List<QaAnswer> answers) {
		this.answers = answers;
	}
	
}
