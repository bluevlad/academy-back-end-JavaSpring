package com.academy.lecture.service;

import java.util.Date;

/**
 * OpenLecture Value Object
 * TB_OPEN_LEC_MST 테이블 매핑 VO
 */
public class OpenLectureVO {

    private String openleccode;
    private String openTitle;
    private String categoryCd;
    private String subjectCd;
    private String teacherCd;
    private String formCd;
    private String openContents;
    private String openFile;
    private String iconGubn;
    private String useYn;
    private Date regDt;
    private String regId;
    private Date updDt;
    private String updId;

    // Additional display fields
    private String categoryNm;
    private String subjectNm;
    private String teacherNm;
    private String formNm;
    private String iconGubnNm;

    public String getOpenleccode() {
        return openleccode;
    }

    public void setOpenleccode(String openleccode) {
        this.openleccode = openleccode;
    }

    public String getOpenTitle() {
        return openTitle;
    }

    public void setOpenTitle(String openTitle) {
        this.openTitle = openTitle;
    }

    public String getCategoryCd() {
        return categoryCd;
    }

    public void setCategoryCd(String categoryCd) {
        this.categoryCd = categoryCd;
    }

    public String getSubjectCd() {
        return subjectCd;
    }

    public void setSubjectCd(String subjectCd) {
        this.subjectCd = subjectCd;
    }

    public String getTeacherCd() {
        return teacherCd;
    }

    public void setTeacherCd(String teacherCd) {
        this.teacherCd = teacherCd;
    }

    public String getFormCd() {
        return formCd;
    }

    public void setFormCd(String formCd) {
        this.formCd = formCd;
    }

    public String getOpenContents() {
        return openContents;
    }

    public void setOpenContents(String openContents) {
        this.openContents = openContents;
    }

    public String getOpenFile() {
        return openFile;
    }

    public void setOpenFile(String openFile) {
        this.openFile = openFile;
    }

    public String getIconGubn() {
        return iconGubn;
    }

    public void setIconGubn(String iconGubn) {
        this.iconGubn = iconGubn;
    }

    public String getUseYn() {
        return useYn;
    }

    public void setUseYn(String useYn) {
        this.useYn = useYn;
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

    public String getCategoryNm() {
        return categoryNm;
    }

    public void setCategoryNm(String categoryNm) {
        this.categoryNm = categoryNm;
    }

    public String getSubjectNm() {
        return subjectNm;
    }

    public void setSubjectNm(String subjectNm) {
        this.subjectNm = subjectNm;
    }

    public String getTeacherNm() {
        return teacherNm;
    }

    public void setTeacherNm(String teacherNm) {
        this.teacherNm = teacherNm;
    }

    public String getFormNm() {
        return formNm;
    }

    public void setFormNm(String formNm) {
        this.formNm = formNm;
    }

    public String getIconGubnNm() {
        return iconGubnNm;
    }

    public void setIconGubnNm(String iconGubnNm) {
        this.iconGubnNm = iconGubnNm;
    }

    @Override
    public String toString() {
        return org.apache.commons.lang3.builder.ToStringBuilder.reflectionToString(this);
    }
}