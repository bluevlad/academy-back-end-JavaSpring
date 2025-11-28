package com.academy.mapper;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;
import org.json.simple.JSONObject;

import com.academy.lecture.service.SubjectVO;

/**
 * Subject Mapper Interface
 * ExamMapper 패턴 적용 - ArrayList<JSONObject> 및 VO 사용
 */
@Mapper
public interface SubjectMapper {

    ArrayList<JSONObject> subjectList(SubjectVO subjectVO);

    int subjectListCount(SubjectVO subjectVO);

    String subjectGetCode(SubjectVO subjectVO);

    void subjectInsert(SubjectVO subjectVO);

    ArrayList<JSONObject> subjectView(SubjectVO subjectVO);

    void subjectUpdate(SubjectVO subjectVO);

    void subjectDelete(SubjectVO subjectVO);

    int subjectCheck(SubjectVO subjectVO);

    void subjectCategoryInsert(SubjectVO subjectVO);

    void subjectCategoryDelete(SubjectVO subjectVO);

    void subjectCategoryDeleteByCat(SubjectVO subjectVO);

    void subjectCategoryOrderInsert(SubjectVO subjectVO);

    int chkSubjectCategoryOrderCnt(SubjectVO subjectVO);

    int chkSubjectCategoryCnt(SubjectVO subjectVO);

    void subjectCategoryOrderDeleteByOnoff(SubjectVO subjectVO);

    String getSubjectCategoryOrderIdx(SubjectVO subjectVO);

    void subjectCategoryOrderDelete(SubjectVO subjectVO);

    ArrayList<JSONObject> subjectCategoryView(SubjectVO subjectVO);

    ArrayList<JSONObject> findSubjectCategoryList(SubjectVO subjectVO);

    void main_category_subject_order_Insert(SubjectVO subjectVO);
}