package com.academy.lecture.service;

import java.util.Date;

/**
 * Category Value Object
 * TB_CATEGORY_INFO 테이블 매핑 VO (카테고리용)
 */
public class CategoryVO {

    private Integer id;
    private String code;
    private String name;
    private String isuse;
    private String useOn;
    private String useOff;
    private String pCode;
    private Integer ordr;
    private Date regDt;
    private String regId;
    private Date updDt;
    private String updId;

    // Additional fields for display
    private String isusenm;
    private String useOnnm;
    private String useOffnm;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIsuse() {
        return isuse;
    }

    public void setIsuse(String isuse) {
        this.isuse = isuse;
    }

    public String getUseOn() {
        return useOn;
    }

    public void setUseOn(String useOn) {
        this.useOn = useOn;
    }

    public String getUseOff() {
        return useOff;
    }

    public void setUseOff(String useOff) {
        this.useOff = useOff;
    }

    public String getpCode() {
        return pCode;
    }

    public void setpCode(String pCode) {
        this.pCode = pCode;
    }

    public Integer getOrdr() {
        return ordr;
    }

    public void setOrdr(Integer ordr) {
        this.ordr = ordr;
    }

    public Date getRegDt() {
        return regDt;
    }

    public void setRegDt(Date regDt) {
        this.regDt = regDt;
    }

    public String getRegId() {
        return regId;
    }

    public void setRegId(String regId) {
        this.regId = regId;
    }

    public Date getUpdDt() {
        return updDt;
    }

    public void setUpdDt(Date updDt) {
        this.updDt = updDt;
    }

    public String getUpdId() {
        return updId;
    }

    public void setUpdId(String updId) {
        this.updId = updId;
    }

    public String getIsusenm() {
        return isusenm;
    }

    public void setIsusenm(String isusenm) {
        this.isusenm = isusenm;
    }

    public String getUseOnnm() {
        return useOnnm;
    }

    public void setUseOnnm(String useOnnm) {
        this.useOnnm = useOnnm;
    }

    public String getUseOffnm() {
        return useOffnm;
    }

    public void setUseOffnm(String useOffnm) {
        this.useOffnm = useOffnm;
    }

    @Override
    public String toString() {
        return org.apache.commons.lang3.builder.ToStringBuilder.reflectionToString(this);
    }
}