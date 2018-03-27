package com.glens.pwCloudOs.weixin.bean;

public class User {

	/**
	 * 成员UserID。对应管理端的帐号，企业内必须唯一。不区分大小写，长度为1~64个字节
	 */
	private String userid;

	/**
	 * 成员名称。长度为1~64个字节
	 */
	private String name;

	/**
	 * 成员所属部门id列表,不超过20个
	 */
	private String department;

	/**
	 * 职位信息。长度为0~64个字节
	 */
	private String position;

	/**
	 * 手机号码。企业内必须唯一，mobile/weixinid/email三者不能同时为空
	 * 
	 */
	private String mobile;

	/**
	 * 性别。1表示男性，2表示女性
	 */
	private int gender;

	/**
	 * 邮箱。长度为0~64个字节。企业内必须唯一
	 * 
	 */
	private String email;

	/**
	 * 微信号。企业内必须唯一。（注意：是微信号，不是微信的名字）
	 */
	private String weixinid;

	/**
	 * 成员头像的mediaid，通过多媒体接口上传图片获得的mediaid
	 */
	private String avatar_mediaid;

	/**
	 * 扩展属性。扩展属性需要在WEB管理端创建后才生效，否则忽略未知属性的赋值
	 */
	private Extattr extattr;

	/**
	 * 启用/禁用成员。1表示启用成员，0表示禁用成员
	 */
	private int enable;

	/**
	 * 出生日期
	 */
	private String birthday;

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public int getGender() {
		return gender;
	}

	public void setGender(int gender) {
		this.gender = gender;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getWeixinid() {
		return weixinid;
	}

	public void setWeixinid(String weixinid) {
		this.weixinid = weixinid;
	}

	public String getAvatar_mediaid() {
		return avatar_mediaid;
	}

	public void setAvatar_mediaid(String avatar_mediaid) {
		this.avatar_mediaid = avatar_mediaid;
	}

	public Extattr getExtattr() {
		return extattr;
	}

	public void setExtattr(Extattr extattr) {
		this.extattr = extattr;
	}

	public int getEnable() {
		return enable;
	}

	public void setEnable(int enable) {
		this.enable = enable;
	}

}
