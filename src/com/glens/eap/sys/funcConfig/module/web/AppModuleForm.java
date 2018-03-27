package com.glens.eap.sys.funcConfig.module.web;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.glens.eap.platform.core.annotation.ValueObjectProcessor;
import com.glens.eap.platform.core.web.ControllerForm;

@ValueObjectProcessor(clazz = "com.glens.eap.sys.funcConfig.module.vo.AppModuleVo")
public class AppModuleForm extends ControllerForm {
	/**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ad_app_modules.rowid
     *
     * @mbggenerated
     */
    private Long rowid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ad_app_modules.MODULE_CODE
     *
     * @mbggenerated
     */
    private String moduleCode;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ad_app_modules.MODULE_NAME
     *
     * @mbggenerated
     */
    private String moduleName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ad_app_modules.MODULE_DESC
     *
     * @mbggenerated
     */
    private String moduleDesc;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ad_app_modules.APP_CODE
     *
     * @mbggenerated
     */
    private String appCode;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ad_app_modules.PARENT_MODULE_CODE
     *
     * @mbggenerated
     */
    private String parentModuleCode;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ad_app_modules.URL
     *
     * @mbggenerated
     */
    private String url;
    private String mobileUrl;
    

    public String getMobileUrl() {
		return mobileUrl;
	}

	public void setMobileUrl(String mobileUrl) {
		this.mobileUrl = mobileUrl;
	}

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ad_app_modules.MODULE_LEVEL
     *
     * @mbggenerated
     */
    private Integer moduleLevel;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ad_app_modules.ICON
     *
     * @mbggenerated
     */
    private String icon;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ad_app_modules.MODULE_ORDER
     *
     * @mbggenerated
     */
    private Integer moduleOrder;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ad_app_modules.VERSION
     *
     * @mbggenerated
     */
    private String version;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ad_app_modules.PROVIDER
     *
     * @mbggenerated
     */
    private String provider;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ad_app_modules.REMARKS
     *
     * @mbggenerated
     */
    private String remarks;
    
    private String roleCode;
    
    private String modules;
    
    private String companyCode;
    
	public String getCompanyCode() {
		return companyCode;
	}

	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}

	public String getModules() {
		return modules;
	}

	public void setModules(String modules) {
		this.modules = modules;
	}

	public String getRoleCode() {
		return roleCode;
	}

	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}

	public Long getRowid() {
		return rowid;
	}

	public void setRowid(Long rowid) {
		this.rowid = rowid;
	}

	public String getModuleCode() {
		return moduleCode;
	}

	public void setModuleCode(String moduleCode) {
		this.moduleCode = moduleCode;
	}

	public String getModuleName() {
		return moduleName;
	}

	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}

	public String getModuleDesc() {
		return moduleDesc;
	}

	public void setModuleDesc(String moduleDesc) {
		this.moduleDesc = moduleDesc;
	}

	public String getAppCode() {
		return appCode;
	}

	public void setAppCode(String appCode) {
		this.appCode = appCode;
	}

	public String getParentModuleCode() {
		return parentModuleCode;
	}

	public void setParentModuleCode(String parentModuleCode) {
		this.parentModuleCode = parentModuleCode;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Integer getModuleLevel() {
		return moduleLevel;
	}

	public void setModuleLevel(Integer moduleLevel) {
		this.moduleLevel = moduleLevel;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public Integer getModuleOrder() {
		return moduleOrder;
	}

	public void setModuleOrder(Integer moduleOrder) {
		this.moduleOrder = moduleOrder;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getProvider() {
		return provider;
	}

	public void setProvider(String provider) {
		this.provider = provider;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	@Override
	protected Map doPreToMap() {
		Map map=new HashMap();
		return map;
	}

	@Override
	protected void doPostRequest(HttpServletRequest request) {
		
	}

	@Override
	public Object getGenerateKey() {
		return null;
	}

	
}