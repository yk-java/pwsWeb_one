package com.glens.pwCloudOs.materielMg.assetMg.assetClass.vo;

import com.glens.eap.platform.core.beans.ValueObject;

public class AssetClassVo implements ValueObject {

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column gm_asset_class.ASSET_CLASS_CODE
     *
     * @mbggenerated
     */
    private String assetClassCode;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column gm_asset_class.ASSET_CLASS_NAME
     *
     * @mbggenerated
     */
    private String assetClassName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column gm_asset_class.ASSET_CHARACTER_CODE
     *
     * @mbggenerated
     */
    private String assetCharacterCode;
    
    private String assetCharacterName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column gm_asset_class.RESIDUAL_RATE
     *
     * @mbggenerated
     */
    private Float residualRate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column gm_asset_class.USEFUL_LIFE
     *
     * @mbggenerated
     */
    private Integer usefulLife;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column gm_asset_class.EUP_MONTH
     *
     * @mbggenerated
     */
    private Integer eupMonth;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column gm_asset_class.REMARKS
     *
     * @mbggenerated
     */
    private String remarks;
    
    
    private String iscm;

    public String getIscm() {
		return iscm;
	}

	public void setIscm(String iscm) {
		this.iscm = iscm;
	}

	/**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column gm_asset_class.ASSET_CLASS_CODE
     *
     * @return the value of gm_asset_class.ASSET_CLASS_CODE
     *
     * @mbggenerated
     */
    public String getAssetClassCode() {
        return assetClassCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column gm_asset_class.ASSET_CLASS_CODE
     *
     * @param assetClassCode the value for gm_asset_class.ASSET_CLASS_CODE
     *
     * @mbggenerated
     */
    public void setAssetClassCode(String assetClassCode) {
        this.assetClassCode = assetClassCode == null ? null : assetClassCode.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column gm_asset_class.ASSET_CLASS_NAME
     *
     * @return the value of gm_asset_class.ASSET_CLASS_NAME
     *
     * @mbggenerated
     */
    public String getAssetClassName() {
        return assetClassName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column gm_asset_class.ASSET_CLASS_NAME
     *
     * @param assetClassName the value for gm_asset_class.ASSET_CLASS_NAME
     *
     * @mbggenerated
     */
    public void setAssetClassName(String assetClassName) {
        this.assetClassName = assetClassName == null ? null : assetClassName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column gm_asset_class.ASSET_CHARACTER_CODE
     *
     * @return the value of gm_asset_class.ASSET_CHARACTER_CODE
     *
     * @mbggenerated
     */
    public String getAssetCharacterCode() {
        return assetCharacterCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column gm_asset_class.ASSET_CHARACTER_CODE
     *
     * @param assetCharacterCode the value for gm_asset_class.ASSET_CHARACTER_CODE
     *
     * @mbggenerated
     */
    public void setAssetCharacterCode(String assetCharacterCode) {
        this.assetCharacterCode = assetCharacterCode == null ? null : assetCharacterCode.trim();
    }

    public String getAssetCharacterName() {
		return assetCharacterName;
	}

	public void setAssetCharacterName(String assetCharacterName) {
		this.assetCharacterName = assetCharacterName;
	}

	/**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column gm_asset_class.RESIDUAL_RATE
     *
     * @return the value of gm_asset_class.RESIDUAL_RATE
     *
     * @mbggenerated
     */
    public Float getResidualRate() {
        return residualRate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column gm_asset_class.RESIDUAL_RATE
     *
     * @param residualRate the value for gm_asset_class.RESIDUAL_RATE
     *
     * @mbggenerated
     */
    public void setResidualRate(Float residualRate) {
        this.residualRate = residualRate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column gm_asset_class.USEFUL_LIFE
     *
     * @return the value of gm_asset_class.USEFUL_LIFE
     *
     * @mbggenerated
     */
    public Integer getUsefulLife() {
        return usefulLife;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column gm_asset_class.USEFUL_LIFE
     *
     * @param usefulLife the value for gm_asset_class.USEFUL_LIFE
     *
     * @mbggenerated
     */
    public void setUsefulLife(Integer usefulLife) {
        this.usefulLife = usefulLife;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column gm_asset_class.EUP_MONTH
     *
     * @return the value of gm_asset_class.EUP_MONTH
     *
     * @mbggenerated
     */
    public Integer getEupMonth() {
        return eupMonth;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column gm_asset_class.EUP_MONTH
     *
     * @param eupMonth the value for gm_asset_class.EUP_MONTH
     *
     * @mbggenerated
     */
    public void setEupMonth(Integer eupMonth) {
        this.eupMonth = eupMonth;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column gm_asset_class.REMARKS
     *
     * @return the value of gm_asset_class.REMARKS
     *
     * @mbggenerated
     */
    public String getRemarks() {
        return remarks;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column gm_asset_class.REMARKS
     *
     * @param remarks the value for gm_asset_class.REMARKS
     *
     * @mbggenerated
     */
    public void setRemarks(String remarks) {
        this.remarks = remarks == null ? null : remarks.trim();
    }
}