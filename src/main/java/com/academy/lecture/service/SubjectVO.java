package com.academy.lecture.service;

import java.util.Date;

/**
 * Subject Value Object
 * TB_SUBJECT_INFO 테이블 매핑 VO
 */
public class SubjectVO {

    private String subjectCd;
    private String subjectNm;
    private String subjectShortNm;
    private String isuse;
    private Date regDt;
    private String regId;
    private Date updDt;
    private String updId;

    // Additional fields for display
    private String isusenm;

    public String getSubjectCd() {
        return subjectCd;
    }

    public void setSubjectCd(String subjectCd) {
        this.subjectCd = subjectCd;
    }

    public String getSubjectNm() {
        return subjectNm;
    }

    public void setSubjectNm(String subjectNm) {
        this.subjectNm = subjectNm;
    }

    public String getSubjectShortNm() {
        return subjectShortNm;
    }

    public void setSubjectShortNm(String subjectShortNm) {
        this.subjectShortNm = subjectShortNm;
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