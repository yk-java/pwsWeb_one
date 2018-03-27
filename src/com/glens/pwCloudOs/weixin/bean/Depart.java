package com.glens.pwCloudOs.weixin.bean;

import java.io.Serializable;

/**
 * 部门
 * 
 * @author Administrator
 * 
 */
public class Depart {

	/**
	 * 部门名称。长度限制为32个字（汉字或英文字母），字符不能包括\:*?"<>｜
	 */
	private String name;

	/**
	 * 父亲部门id。根部门id为1
	 */
	private int parentid;

	/**
	 * 在父部门中的次序值。order值小的排序靠前。
	 */
	private int order;

	/**
	 * 部门id，整型。指定时必须大于1，不指定时则自动生成
	 */
	private int id;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getParentid() {
		return parentid;
	}

	public void setParentid(int parentid) {
		this.parentid = parentid;
	}

	public int getOrder() {
		return order;
	}

	public void setOrder(int order) {
		this.order = order;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Depart [name=" + name + ", parentid=" + parentid + ", order="
				+ order + ", id=" + id + "]";
	}

}
