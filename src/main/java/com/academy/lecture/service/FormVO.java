package com.academy.lecture.service;

import java.util.Date;

/**
 * Form Value Object
 * TB_LEARNING_FORM_INFO 테이블 매핑 VO
 */
public class FormVO {

    private String lecDiv;
    private String code;
    private String name;
    private String isuse;
    private Date regDt;
    private String regId;
    private Date updDt;
    private String updId;

    // Additional fields for display
    private String lecDivnm;
    private String isusenm;

    public String getLecDiv() {
        return lecDiv;
    }

    public void setLecDiv(String lecDiv) {
        this.lecDiv = lecDiv;
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

    public String getLecDivnm() {
        return lecDivnm;
    }

    public void setLecDivnm(String lecDivnm) {
        this.lecDivnm = lecDivnm;
    }

    public String getIsusenm() {
        return isusenm;
    }

    public void setIsusenm(String isusenm) {
        this.isusenm = isusenm;
    }

    @Override
    public String toString() {
        return org.apache.commons.lang3.builder.ToStringBuilder.reflectionToString(this);
    }
}