package com.academy.lecture.service;

import java.io.Serializable;
import java.util.Date;

import com.academy.common.CommonVO;

/**
 * LectureMst Value Object
 * TB_LEC_MST 테이블 매핑 VO
 */
public class LectureMstVO extends CommonVO implements Serializable {

    private static final long serialVersionUID = 1L;

    // LectureMst specific fields
    private String leccode;                 // LECCODE - 강의 코드
    private String mstcode;                 // MSTCODE - 마스터 코드
    private String bridgeMstcode;           // BRIDGE_MSTCODE - 브릿지 마스터 코드
    private String subjectTitle;            // SUBJECT_TITLE - 강의 제목
    private String categoryCd;              // CATEGORY_CD - 카테고리 코드
    private String[] categoryCdArr;         // CATEGORY_CD[] - 카테고리 코드 배열
    private String subjectCd;               // SUBJECT_CD - 과목 코드
    private String teacherCd;               // TEACHER_CD - 강사 코드
    private String formCd;                  // FORM_CD - 학습형태 코드
    private Integer subjectPeriod;          // SUBJECT_PERIOD - 수강기간
    private Integer subjectMovie;           // SUBJECT_MOVIE - 동영상 수
    private String lecTypeChoice;           // LEC_TYPE_CHOICE - 강의 타입 선택
    private String isOpen;                  // IS_OPEN - 공개 여부
    private String useYn;                   // USE_YN - 사용 여부
    private Integer seq;                    // SEQ - 시퀀스
    private String jseq;                    // JSEQ - J시퀀스
    private String plan;                    // PLAN - 계획
    private String subjectTeacher;          // SUBJECT_TEACHER - 강사
    private String subjectTeacherPayment;   // SUBJECT_TEACHER_PAYMENT - 강사 급여
    private String subjectDesc;             // SUBJECT_DESC - 강의 설명
    private String subjectMemo;             // SUBJECT_MEMO - 강의 메모
    private String subjectKeyword;          // SUBJECT_KEYWORD - 키워드
    private String subjectOffOpenYear;      // SUBJECT_OFF_OPEN_YEAR - 오프라인 개강년도
    private String subjectOffOpenMonth;     // SUBJECT_OFF_OPEN_MONTH - 오프라인 개강월
    private String subjectOffOpenDay;       // SUBJECT_OFF_OPEN_DAY - 오프라인 개강일
    private String subjectDiscount;         // SUBJECT_DISCOUNT - 할인율
    private String subjectPrice;            // SUBJECT_PRICE - 가격
    private String subjectPoint;            // SUBJECT_POINT - 포인트
    private String subjectPmp;              // SUBJECT_PMP - PMP
    private String subjectMoviePmp;         // SUBJECT_MOVIE_PMP - 동영상+PMP
    private String subjectMovieMp4;         // SUBJECT_MOVIE_MP4 - 동영상 MP4
    private String subjectMovieVodMp4;      // SUBJECT_MOVIE_VOD_MP4 - VOD MP4
    private String subjectOption;           // SUBJECT_OPTION - 옵션
    private String subjectIsuse;            // SUBJECT_ISUSE - 사용여부
    private String subjectSjtCd;            // SUBJECT_SJT_CD - 과목 코드
    private String subjectVodDefaultPath;   // SUBJECT_VOD_DEFAULT_PATH - VOD 기본 경로
    private String subjectWideDefaultPath;  // SUBJECT_WIDE_DEFAULT_PATH - WIDE 기본 경로
    private String subjectMp4DefaultPath;   // SUBJECT_MP4_DEFAULT_PATH - MP4 기본 경로
    private String subjectPmpDefaultPath;   // SUBJECT_PMP_DEFAULT_PATH - PMP 기본 경로
    private String subjectPass;             // SUBJECT_PASS - 패스
    private String subjectJang;             // SUBJECT_JANG - 장
    private String reCourse;                // RE_COURSE - 재수강
    private String lecSchedule;             // LEC_SCHEDULE - 강의 일정
    private String lecGubun;                // LEC_GUBUN - 강의 구분
    private String recGubun;                // REC_GUBUN - 녹화 구분
    private String movIng;                  // MOV_ING - 동영상 진행 중
    private String mstUseYn;                // MST_USE_YN - 마스터 사용 여부
    private String teacherNm;               // TEACHER_NM - 강사명
    private String teacherSubject;          // TEACHER_SUBJECT - 강사 과목
    private String orderno;                 // ORDERNO - 주문 번호
    private String orderId;                 // ORDERID - 주문 ID
    private String prefix;                  // PREFIX - 접두어
    private String updateFlag;              // UPDATE_FLAG - 업데이트 플래그
    private String bridgeLec;               // BRIDGE_LEC - 브릿지 강의
    private String rscId;                   // RSC_ID - 리소스 ID
    private String flag;                    // FLAG - 플래그
    private String[] juRscIdArr;            // JU_RSC_ID[] - 주교재 리소스 ID 배열
    private String[] buRscIdArr;            // BU_RSC_ID[] - 부교재 리소스 ID 배열
    private String[] suRscIdArr;            // SU_RSC_ID[] - 학생교재 리소스 ID 배열
    private String[] learningCdArr;         // LEARNING_CD[] - 학습형태 코드 배열
    private String searchPayYn;             // SEARCHPAYYN - 검색 결제 여부
    private String searchPayType;           // SEARCHPAYTYPE - 검색 결제 타입
    private String searchKind;              // SEARCHKIND - 검색 종류
    private String searchForm;              // SEARCHFORM - 검색 형태
    private String searchYear;              // SEARCHYEAR - 검색 연도
    private String searchOpenPage;          // SEARCHOPENPAGE - 검색 오픈 페이지
    private String addBookArea;             // ADDBOOKAREA - 추가 도서 영역
    private String addArea;                 // ADDAREA - 추가 영역
    // currentPage, pageRow from CommonVO

    // Additional display fields
    private String categoryNm;
    private String subjectNm;
    private String formNm;

    // Book fields
    private String mainBook;
    private String subBook;
    private String studentBook;

    // Additional info
    private String subjectInfo;
    private String contents;

    public String getLeccode() {
        return leccode;
    }

    public void setLeccode(String leccode) {
        this.leccode = leccode;
    }

    public String getMstcode() {
        return mstcode;
    }

    public void setMstcode(String mstcode) {
        this.mstcode = mstcode;
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

    public Integer getSubjectPeriod() {
        return subjectPeriod;
    }

    public void setSubjectPeriod(Integer subjectPeriod) {
        this.subjectPeriod = subjectPeriod;
    }

    public Integer getSubjectMovie() {
        return subjectMovie;
    }

    public void setSubjectMovie(Integer subjectMovie) {
        this.subjectMovie = subjectMovie;
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

    public String getSubjectInfo() {
        return subjectInfo;
    }

    public void setSubjectInfo(String subjectInfo) {
        this.subjectInfo = subjectInfo;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    @Override
    public String toString() {
        return org.apache.commons.lang3.builder.ToStringBuilder.reflectionToString(this);
    }
}