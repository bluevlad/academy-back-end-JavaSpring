package com.academy.mapper;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;
import org.json.simple.JSONObject;

import com.academy.lecture.service.TeacherVO;

/**
 * Teacher Mapper Interface
 * ExamMapper 패턴 적용 - ArrayList<JSONObject> 및 VO 사용
 */
@Mapper
public interface TeacherMapper {

    ArrayList<JSONObject> getKindList(TeacherVO teacherVO);

    ArrayList<JSONObject> getSubjectList(TeacherVO teacherVO);

    ArrayList<JSONObject> teacherList(TeacherVO teacherVO);

    int teacherListCount(TeacherVO teacherVO);

    ArrayList<JSONObject> teacherAllList(TeacherVO teacherVO);

    int teacherAllListCount(TeacherVO teacherVO);

    int teacherIdCheck(TeacherVO teacherVO);

    void teacherInsert(TeacherVO teacherVO);

    ArrayList<JSONObject> teacherView(TeacherVO teacherVO);

    void teacherUpdate(TeacherVO teacherVO);

    void teacherCategoryInsert(TeacherVO teacherVO);

    void teacherSubjectInsert(TeacherVO teacherVO);

    void teacherSubjectUpdate(TeacherVO teacherVO);

    int teacherSubjectCount(TeacherVO teacherVO);

    void teacherIsUseUpdate(TeacherVO teacherVO);

    void teacherDelete(TeacherVO teacherVO);

    void teacherCategoryDelete(TeacherVO teacherVO);

    void teacherSubjectDelete(TeacherVO teacherVO);

    void teacherSeqUpdate(TeacherVO teacherVO);

    ArrayList<JSONObject> teacherBookLog(TeacherVO teacherVO);

    void teacherMain_Category_Insert(TeacherVO teacherVO);

    void teacherMain_Category_Delete(TeacherVO teacherVO);

    ArrayList<JSONObject> teacherMainList(TeacherVO teacherVO);

    int teacherMainListCount(TeacherVO teacherVO);

    ArrayList<JSONObject> findTeacherList(TeacherVO teacherVO);

    int teacherMain_Category_Subject(TeacherVO teacherVO);

    int teacherIntro_Category_Subject(TeacherVO teacherVO);

    int teacherIntro_F_Category_Subject(TeacherVO teacherVO);

    ArrayList<JSONObject> teacherIntroList(TeacherVO teacherVO);

    int teacherIntroListCount(TeacherVO teacherVO);

    void teacherIntro_Category_Insert(TeacherVO teacherVO);

    void teacherIntro_F_Category_Insert(TeacherVO teacherVO);

    void teacherIntro_Category_Delete(TeacherVO teacherVO);

    void teacherIntro_F_Category_Delete(TeacherVO teacherVO);

    ArrayList<JSONObject> teacherIntro_offList(TeacherVO teacherVO);

    int teacherIntro_offListCount(TeacherVO teacherVO);
}