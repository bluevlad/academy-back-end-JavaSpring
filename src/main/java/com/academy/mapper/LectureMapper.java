package com.academy.mapper;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;
import org.json.simple.JSONObject;

import com.academy.lecture.service.LectureVO;

/**
 * Lecture Mapper Interface
 * ExamMapper 패턴 적용 - ArrayList<JSONObject> 및 VO 사용
 */
@Mapper
public interface LectureMapper {

    ArrayList<JSONObject> lectureList(LectureVO lectureVO);

    ArrayList<JSONObject> oldFreeToNewFree(LectureVO lectureVO);

    int lectureListCount(LectureVO lectureVO);

    ArrayList<JSONObject> bookList(LectureVO lectureVO);

    int bookListCount(LectureVO lectureVO);

    ArrayList<JSONObject> getBridgeLeccodeSeq(LectureVO lectureVO);

    ArrayList<JSONObject> getJongLeccodeSeq(LectureVO lectureVO);

    ArrayList<JSONObject> getLeccode(LectureVO lectureVO);

    ArrayList<JSONObject> getBridgeLeccode(LectureVO lectureVO);

    ArrayList<JSONObject> BridgeLeccode(LectureVO lectureVO);

    void lectureInsert(LectureVO lectureVO);

    void lectureBridgeInsert(LectureVO lectureVO);

    void lectureBookInsert(LectureVO lectureVO);

    void lectureBookInsert2(LectureVO lectureVO);

    ArrayList<JSONObject> lectureViewList(LectureVO lectureVO);

    ArrayList<JSONObject> lectureView(LectureVO lectureVO);

    ArrayList<JSONObject> lectureViewBookList(LectureVO lectureVO);

    void lectureBookDelete(LectureVO lectureVO);

    void lectureUpdate(LectureVO lectureVO);

    void Modify_Lecture_On_Off(LectureVO lectureVO);

    void lectureIsUseUpdate(LectureVO lectureVO);

    void lectureDelete(LectureVO lectureVO);

    void lectureBridgeDelete(LectureVO lectureVO);

    void lecMovUpdate(LectureVO lectureVO);

    ArrayList<JSONObject> lectureSeqList(LectureVO lectureVO);

    void lectureSeqUpdate(LectureVO lectureVO);

    ArrayList<JSONObject> lectureViewJongList(LectureVO lectureVO);

    ArrayList<JSONObject> lectureJongList(LectureVO lectureVO);

    int lectureJongListCount(LectureVO lectureVO);

    ArrayList<JSONObject> lectureYearList(LectureVO lectureVO);

    int lectureYearListCount(LectureVO lectureVO);

    ArrayList<JSONObject> lectureJongSubjectList(LectureVO lectureVO);

    ArrayList<JSONObject> lectureJongView(LectureVO lectureVO);

    int lectureJongSubjectListCount(LectureVO lectureVO);

    void lectureLecJongInsert(LectureVO lectureVO);

    void lectureChoiceJongNoInsert(LectureVO lectureVO);

    ArrayList<JSONObject> lectureViewLeccodeList(LectureVO lectureVO);

    void lectureLecJongDelete(LectureVO lectureVO);

    void lectureChoiceJongNoDelete(LectureVO lectureVO);

    ArrayList<JSONObject> lecturePayList(LectureVO lectureVO);

    ArrayList<JSONObject> lectureJongPayList(LectureVO lectureVO);

    ArrayList<JSONObject> lectureDataMemoViewList(LectureVO lectureVO);

    ArrayList<JSONObject> lectureDataViewList(LectureVO lectureVO);

    ArrayList<JSONObject> lectureMobileList(LectureVO lectureVO);

    ArrayList<JSONObject> lectureDataMovieViewList(LectureVO lectureVO);

    ArrayList<JSONObject> lectureDataMovieList(LectureVO lectureVO);

    void lectureMovieInsert(LectureVO lectureVO);

    void lectureMovieDelete(LectureVO lectureVO);

    void lectureMovieUpdate(LectureVO lectureVO);

    void lectureMovieFileDelete(LectureVO lectureVO);

    void lectureMovieMemoInsert(LectureVO lectureVO);

    void lectureMovieMemoUpdate(LectureVO lectureVO);

    void lectureMovieMemoDelete(LectureVO lectureVO);

    int lectureDeleteCheck(LectureVO lectureVO);

    ArrayList<JSONObject> playinfo(LectureVO lectureVO);

    ArrayList<JSONObject> getCbMovie4_free_admin(LectureVO lectureVO);

    int getCbMovie4_free_admin_count(LectureVO lectureVO);

    JSONObject lectureOnDetailS(LectureVO lectureVO);

    void insertPmpDownLog(LectureVO lectureVO);

    ArrayList<JSONObject> lectureWMV(LectureVO lectureVO);

    ArrayList<JSONObject> lectureDown_Count(LectureVO lectureVO);

    void oldFreeToNewFreeInsert(LectureVO lectureVO);

    void oldBogangFreeToNewBogangFree(LectureVO lectureVO);

    ArrayList<JSONObject> lectureFreePassPayList(LectureVO lectureVO);

    ArrayList<JSONObject> YearIngList(LectureVO lectureVO);

    ArrayList<JSONObject> MyYearIngList(LectureVO lectureVO);

    ArrayList<JSONObject> bookView(LectureVO lectureVO);

    String getRleccode(LectureVO lectureVO);

    void oldTbmovieToNewTbmovieInsert(LectureVO lectureVO);

    void oldBogangFreeToNewFreeBogangInsert(LectureVO lectureVO);
}