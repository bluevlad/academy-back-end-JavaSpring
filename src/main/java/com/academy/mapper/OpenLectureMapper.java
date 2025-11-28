package com.academy.mapper;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;
import org.json.simple.JSONObject;

import com.academy.lecture.service.OpenLectureVO;

/**
 * OpenLecture Mapper Interface
 * ExamMapper 패턴 적용 - ArrayList<JSONObject> 및 VO 사용
 */
@Mapper
public interface OpenLectureMapper {

	ArrayList<JSONObject> openlectureList(OpenLectureVO openLectureVO);

	int openlectureListCount(OpenLectureVO openLectureVO);

	ArrayList<JSONObject> bookList(OpenLectureVO openLectureVO);

	int bookListCount(OpenLectureVO openLectureVO);

	ArrayList<JSONObject> getBridgeLeccodeSeq(OpenLectureVO openLectureVO);

	ArrayList<JSONObject> getJongLeccodeSeq(OpenLectureVO openLectureVO);

	ArrayList<JSONObject> getopenLeccode(OpenLectureVO openLectureVO);

	ArrayList<JSONObject> getBridgeLeccode(OpenLectureVO openLectureVO);

	ArrayList<JSONObject> BridgeLeccode(OpenLectureVO openLectureVO);

	void openlectureInsert(OpenLectureVO openLectureVO);

	void openlectureBridgeInsert(OpenLectureVO openLectureVO);

	void openlectureBookInsert(OpenLectureVO openLectureVO);

	void openlectureBookInsert2(OpenLectureVO openLectureVO);

	ArrayList<JSONObject> openlectureViewList(OpenLectureVO openLectureVO);

	ArrayList<JSONObject> openlectureView(OpenLectureVO openLectureVO);

	ArrayList<JSONObject> openlectureViewBookList(OpenLectureVO openLectureVO);

	void openlectureBookDelete(OpenLectureVO openLectureVO);

	void openlectureUpdate(OpenLectureVO openLectureVO);

	void Modify_Lecture_On_Off(OpenLectureVO openLectureVO);

	void openlectureIsUseUpdate(OpenLectureVO openLectureVO);

	void openlectureDelete(OpenLectureVO openLectureVO);

	void openlectureBridgeDelete(OpenLectureVO openLectureVO);

	void lecMovUpdate(OpenLectureVO openLectureVO);

	ArrayList<JSONObject> openlectureSeqList(OpenLectureVO openLectureVO);

	void openlectureSeqUpdate(OpenLectureVO openLectureVO);

	ArrayList<JSONObject> openlectureViewJongList(OpenLectureVO openLectureVO);

	ArrayList<JSONObject> openlectureJongList(OpenLectureVO openLectureVO);

	int openlectureJongListCount(OpenLectureVO openLectureVO);

	ArrayList<JSONObject> openlectureJongSubjectList(OpenLectureVO openLectureVO);

	ArrayList<JSONObject> openlectureJongView(OpenLectureVO openLectureVO);

	int openlectureJongSubjectListCount(OpenLectureVO openLectureVO);

	void openlectureLecJongInsert(OpenLectureVO openLectureVO);

	void openlectureChoiceJongNoInsert(OpenLectureVO openLectureVO);

	ArrayList<JSONObject> openlectureViewLeccodeList(OpenLectureVO openLectureVO);

	void openlectureLecJongDelete(OpenLectureVO openLectureVO);

	void openlectureChoiceJongNoDelete(OpenLectureVO openLectureVO);

	ArrayList<JSONObject> openlecturePayList(OpenLectureVO openLectureVO);

	ArrayList<JSONObject> openlectureJongPayList(OpenLectureVO openLectureVO);

	ArrayList<JSONObject> openlectureDataMemoViewList(OpenLectureVO openLectureVO);

	ArrayList<JSONObject> openlectureDataViewList(OpenLectureVO openLectureVO);

	ArrayList<JSONObject> openlectureDataMovieViewList(OpenLectureVO openLectureVO);

	ArrayList<JSONObject> openlectureDataMovieList(OpenLectureVO openLectureVO);

	void openlectureMovieInsert(OpenLectureVO openLectureVO);

	void openlectureMovieDelete(OpenLectureVO openLectureVO);

	void openlectureMovieUpdate(OpenLectureVO openLectureVO);

	void openlectureMovieFileDelete(OpenLectureVO openLectureVO);

	void openlectureMovieMemoInsert(OpenLectureVO openLectureVO);

	void openlectureMovieMemoUpdate(OpenLectureVO openLectureVO);

	void openlectureMovieMemoDelete(OpenLectureVO openLectureVO);

	int openlectureDeleteCheck(OpenLectureVO openLectureVO);

	ArrayList<JSONObject> playinfo(OpenLectureVO openLectureVO);

	ArrayList<JSONObject> getCbMovie4_free_admin(OpenLectureVO openLectureVO);

	int getCbMovie4_free_admin_count(OpenLectureVO openLectureVO);

	JSONObject openlectureOnDetailS(OpenLectureVO openLectureVO);

	void insertPmpDownLog(OpenLectureVO openLectureVO);

	ArrayList<JSONObject> openlectureWMV(OpenLectureVO openLectureVO);

	ArrayList<JSONObject> openlectureDown_Count(OpenLectureVO openLectureVO);
}