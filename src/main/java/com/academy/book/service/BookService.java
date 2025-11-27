package com.academy.book.service;

import java.util.HashMap;
import java.util.List;

/**
 * Book Service Interface
 */
public interface BookService {

    /**
     * 교재 목록 조회
     */
    List<HashMap<String, String>> bookList(HashMap<String, String> params);

    /**
     * 교재 목록 카운트
     */
    int bookListCount(HashMap<String, String> params);

    /**
     * 학습형태 목록 조회
     */
    List<HashMap<String, String>> getLearningFormList(HashMap<String, String> params);

    /**
     * 과목(강사) 목록 조회
     */
    List<HashMap<String, String>> getCaSubjectTeacherList(HashMap<String, String> params);

    /**
     * 교재 SEQ 조회
     */
    int getCaBookSeq(HashMap<String, String> params);

    /**
     * 교재 RSC_ID 생성
     */
    String getCaBookRscId(HashMap<String, String> params);

    /**
     * 교재 등록
     */
    void bookInsert(HashMap<String, String> params);

    /**
     * 교재 상세 목록 조회 (관련 도서)
     */
    List<HashMap<String, String>> bookViewList(HashMap<String, String> params);

    /**
     * 교재 상세 조회
     */
    List<HashMap<String, String>> bookView(HashMap<String, String> params);

    /**
     * 교재 사용 여부 확인
     */
    int bookUseCheck(HashMap<String, String> params);

    /**
     * 교재 수정
     */
    void bookUpdate(HashMap<String, String> params);

    /**
     * 교재 삭제
     */
    void bookDelete(HashMap<String, String> params);

    /**
     * 교재 판매 목록 조회
     */
    List<HashMap<String, String>> bookSellList(HashMap<String, String> params);

    /**
     * 교재 판매 목록 카운트
     */
    int bookSellListCount(HashMap<String, String> params);

    /**
     * 교재 판매 목록 엑셀 다운로드용
     */
    List<HashMap<String, String>> bookSellListExcel(HashMap<String, String> params);
}