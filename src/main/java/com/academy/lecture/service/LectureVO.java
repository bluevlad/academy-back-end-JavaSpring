package com.academy.lecture.service;

import java.util.Date;

/**
 * Lecture Value Object
 * TB_LEC_BRIDGE 테이블 매핑 VO
 */
public class LectureVO {

    private String leccode;
    private String bridgeLeccode;
    private String subjectTitle;
    private String categoryCd;
    private String subjectCd;
    private String teacherCd;
    private String formCd;
    private Integer period;
    private Integer price;
    private String lecTypeChoice;
    private String isOpen;
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

    // Book fields
    private String mainBook;
    private String subBook;
    private String studentBook;

    public String getLeccode() {
        return leccode;
    }

    public void setLeccode(String leccode) {
        this.leccode = leccode;
    }

    public String getBridgeLeccode() {
        return bridgeLeccode;
    }

    public void setBridgeLeccode(String bridgeLeccode) {
        this.bridgeLeccode = bridgeLeccode;
    }

    public String getSubjectTitle() {
        return subjectTitle;
    }

    public void setSubjectTitle(String subjectTitle) {
        this.subjectTitle = subjectTitle;
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

    public Integer getPeriod() {
        return period;
    }

    public void setPeriod(Integer period) {
        this.period = period;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getLecTypeChoice() {
        return lecTypeChoice;
    }

    public void setLecTypeChoice(String lecTypeChoice) {
        this.lecTypeChoice = lecTypeChoice;
    }

    public String getIsOpen() {
        return isOpen;
    }

    public void setIsOpen(String isOpen) {
        this.isOpen = isOpen;
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

    public String getMainBook() {
        return mainBook;
    }

    public void setMainBook(String mainBook) {
        this.mainBook = mainBook;
    }

    public String getSubBook() {
        return subBook;
    }

    public void setSubBook(String subBook) {
        this.subBook = subBook;
    }

    public String getStudentBook() {
        return studentBook;
    }

    public void setStudentBook(String studentBook) {
        this.studentBook = studentBook;
    }

    @Override
    public String toString() {
        return org.apache.commons.lang3.builder.ToStringBuilder.reflectionToString(this);
    }
}