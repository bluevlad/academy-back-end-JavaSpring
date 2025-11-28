package com.academy.mapper;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;
import org.json.simple.JSONObject;

import com.academy.lecture.service.FormVO;

/**
 * Form Mapper Interface
 * ExamMapper 패턴 적용 - ArrayList<JSONObject> 및 VO 사용
 */
@Mapper
public interface FormMapper {

    ArrayList<JSONObject> formList(FormVO formVO);

    int formListCount(FormVO formVO);

    String formGetCode();

    void formInsert(FormVO formVO);

    ArrayList<JSONObject> formView(FormVO formVO);

    void formUpdate(FormVO formVO);

    void formDelete(FormVO formVO);

    int formCheck(FormVO formVO);

    ArrayList<JSONObject> getCodeList(FormVO formVO);
}