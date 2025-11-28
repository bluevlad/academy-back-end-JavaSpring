package com.academy.mapper;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;
import org.json.simple.JSONObject;

import com.academy.lecture.service.LectureMstVO;

/**
 * LectureMst Mapper Interface
 * ExamMapper 패턴 적용 - ArrayList<JSONObject> 및 VO 사용
 */
@Mapper
public interface LectureMstMapper {

    ArrayList<JSONObject> lecturemstList(LectureMstVO lectureMstVO);

    int lecturemstListCount(LectureMstVO lectureMstVO);

    ArrayList<JSONObject> getBridgeMstcodeSeq(LectureMstVO lectureMstVO);

    ArrayList<JSONObject> getBridgeMstcode(LectureMstVO lectureMstVO);

    ArrayList<JSONObject> getMstcode(LectureMstVO lectureMstVO);

    void lecturemstInsert(LectureMstVO lectureMstVO);

    void lectureBridgeInsert(LectureMstVO lectureMstVO);

    void lectureBookInsert2(LectureMstVO lectureMstVO);

    ArrayList<JSONObject> lectureDataMemoViewList(LectureMstVO lectureMstVO);

    ArrayList<JSONObject> lectureDataViewList(LectureMstVO lectureMstVO);

    ArrayList<JSONObject> lectureWMV(LectureMstVO lectureMstVO);

    ArrayList<JSONObject> lectureDown_Count(LectureMstVO lectureMstVO);

    int lectureDeleteCheck(LectureMstVO lectureMstVO);

    ArrayList<JSONObject> lectureView(LectureMstVO lectureMstVO);

    ArrayList<JSONObject> lectureViewList(LectureMstVO lectureMstVO);

    ArrayList<JSONObject> lectureViewBookList(LectureMstVO lectureMstVO);

    void lectureBookDelete(LectureMstVO lectureMstVO);

    void lecturemstUpdate(LectureMstVO lectureMstVO);

    void lectureBookInsert(LectureMstVO lectureMstVO);

    void lectureDelete(LectureMstVO lectureMstVO);

    void lectureBridgeDelete(LectureMstVO lectureMstVO);
}