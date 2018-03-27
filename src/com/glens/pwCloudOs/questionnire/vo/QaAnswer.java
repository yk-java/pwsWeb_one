package com.glens.pwCloudOs.questionnire.vo;

import com.glens.eap.platform.core.beans.ValueObject;

public class QaAnswer implements ValueObject {
	private Long rowid;
	private String answerSheetCode;
	private String questionnireCode;
	private String questionCode;
	private String answer;
	private String supplement;
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
	public String getQuestionCode() {
		return questionCode;
	}
	public void setQuestionCode(String questionCode) {
		this.questionCode = questionCode;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	public String getSupplement() {
		return supplement;
	}
	public void setSupplement(String supplement) {
		this.supplement = supplement;
	}
	
}
