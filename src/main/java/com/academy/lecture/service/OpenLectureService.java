package com.academy.lecture.service;

import java.util.ArrayList;

import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;

import com.academy.mapper.OpenLectureMapper;

/**
 * OpenLecture Service
 * ExamService 패턴 적용 - ArrayList<JSONObject> 및 VO 사용
 */
@Service
public class OpenLectureService {

	private OpenLectureMapper openLectureMapper;

	public OpenLectureService(OpenLectureMapper openLectureMapper) {
		this.openLectureMapper = openLectureMapper;
	}

	public ArrayList<JSONObject> openlectureList(OpenLectureVO openLectureVO) {
		return openLectureMapper.openlectureList(openLectureVO);
	}

	public int openlectureListCount(OpenLectureVO openLectureVO) {
		return openLectureMapper.openlectureListCount(openLectureVO);
	}

	public ArrayList<JSONObject> bookList(OpenLectureVO openLectureVO) {
		return openLectureMapper.bookList(openLectureVO);
	}

	public int bookListCount(OpenLectureVO openLectureVO) {
		return openLectureMapper.bookListCount(openLectureVO);
	}

	public ArrayList<JSONObject> getBridgeLeccodeSeq(OpenLectureVO openLectureVO) {
		return openLectureMapper.getBridgeLeccodeSeq(openLectureVO);
	}

	public ArrayList<JSONObject> getJongLeccodeSeq(OpenLectureVO openLectureVO) {
		return openLectureMapper.getJongLeccodeSeq(openLectureVO);
	}

	public ArrayList<JSONObject> getopenLeccode(OpenLectureVO openLectureVO) {
		return openLectureMapper.getopenLeccode(openLectureVO);
	}

	public ArrayList<JSONObject> getBridgeLeccode(OpenLectureVO openLectureVO) {
		return openLectureMapper.getBridgeLeccode(openLectureVO);
	}

	public ArrayList<JSONObject> BridgeLeccode(OpenLectureVO openLectureVO) {
		return openLectureMapper.BridgeLeccode(openLectureVO);
	}

	public void openlectureInsert(OpenLectureVO openLectureVO) {
		openLectureMapper.openlectureInsert(openLectureVO);
	}

	public void openlectureBridgeInsert(OpenLectureVO openLectureVO) {
		openLectureMapper.openlectureBridgeInsert(openLectureVO);
	}

	public void openlectureBookInsert(OpenLectureVO openLectureVO) {
		openLectureMapper.openlectureBookInsert(openLectureVO);
	}

	public void openlectureBookInsert2(OpenLectureVO openLectureVO) {
		openLectureMapper.openlectureBookInsert2(openLectureVO);
	}

	public ArrayList<JSONObject> openlectureViewList(OpenLectureVO openLectureVO) {
		return openLectureMapper.openlectureViewList(openLectureVO);
	}

	public ArrayList<JSONObject> openlectureView(OpenLectureVO openLectureVO) {
		return openLectureMapper.openlectureView(openLectureVO);
	}

	public ArrayList<JSONObject> openlectureViewBookList(OpenLectureVO openLectureVO) {
		return openLectureMapper.openlectureViewBookList(openLectureVO);
	}

	public void openlectureBookDelete(OpenLectureVO openLectureVO) {
		openLectureMapper.openlectureBookDelete(openLectureVO);
	}

	public void openlectureUpdate(OpenLectureVO openLectureVO) {
		openLectureMapper.openlectureUpdate(openLectureVO);
	}

	public void Modify_Lecture_On_Off(OpenLectureVO openLectureVO) {
		openLectureMapper.Modify_Lecture_On_Off(openLectureVO);
	}

	public void openlectureIsUseUpdate(OpenLectureVO openLectureVO) {
		openLectureMapper.openlectureIsUseUpdate(openLectureVO);
	}

	public void openlectureDelete(OpenLectureVO openLectureVO) {
		openLectureMapper.openlectureDelete(openLectureVO);
	}

	public void openlectureBridgeDelete(OpenLectureVO openLectureVO) {
		openLectureMapper.openlectureBridgeDelete(openLectureVO);
	}

	public void lecMovUpdate(OpenLectureVO openLectureVO) {
		openLectureMapper.lecMovUpdate(openLectureVO);
	}

	public ArrayList<JSONObject> openlectureSeqList(OpenLectureVO openLectureVO) {
		return openLectureMapper.openlectureSeqList(openLectureVO);
	}

	public void openlectureSeqUpdate(OpenLectureVO openLectureVO) {
		openLectureMapper.openlectureSeqUpdate(openLectureVO);
	}

	public ArrayList<JSONObject> openlectureViewJongList(OpenLectureVO openLectureVO) {
		return openLectureMapper.openlectureViewJongList(openLectureVO);
	}

	public ArrayList<JSONObject> openlectureJongList(OpenLectureVO openLectureVO) {
		return openLectureMapper.openlectureJongList(openLectureVO);
	}

	public int openlectureJongListCount(OpenLectureVO openLectureVO) {
		return openLectureMapper.openlectureJongListCount(openLectureVO);
	}

	public ArrayList<JSONObject> openlectureJongSubjectList(OpenLectureVO openLectureVO) {
		return openLectureMapper.openlectureJongSubjectList(openLectureVO);
	}

	public ArrayList<JSONObject> openlectureJongView(OpenLectureVO openLectureVO) {
		return openLectureMapper.openlectureJongView(openLectureVO);
	}

	public int openlectureJongSubjectListCount(OpenLectureVO openLectureVO) {
		return openLectureMapper.openlectureJongSubjectListCount(openLectureVO);
	}

	public void openlectureLecJongInsert(OpenLectureVO openLectureVO) {
		openLectureMapper.openlectureLecJongInsert(openLectureVO);
	}

	public void openlectureChoiceJongNoInsert(OpenLectureVO openLectureVO) {
		openLectureMapper.openlectureChoiceJongNoInsert(openLectureVO);
	}

	public ArrayList<JSONObject> openlectureViewLeccodeList(OpenLectureVO openLectureVO) {
		return openLectureMapper.openlectureViewLeccodeList(openLectureVO);
	}

	public void openlectureLecJongDelete(OpenLectureVO openLectureVO) {
		openLectureMapper.openlectureLecJongDelete(openLectureVO);
	}

	public void openlectureChoiceJongNoDelete(OpenLectureVO openLectureVO) {
		openLectureMapper.openlectureChoiceJongNoDelete(openLectureVO);
	}

	public ArrayList<JSONObject> openlecturePayList(OpenLectureVO openLectureVO) {
		return openLectureMapper.openlecturePayList(openLectureVO);
	}

	public ArrayList<JSONObject> openlectureJongPayList(OpenLectureVO openLectureVO) {
		return openLectureMapper.openlectureJongPayList(openLectureVO);
	}

	public ArrayList<JSONObject> openlectureDataMemoViewList(OpenLectureVO openLectureVO) {
		return openLectureMapper.openlectureDataMemoViewList(openLectureVO);
	}

	public ArrayList<JSONObject> openlectureDataViewList(OpenLectureVO openLectureVO) {
		return openLectureMapper.openlectureDataViewList(openLectureVO);
	}

	public ArrayList<JSONObject> openlectureDataMovieViewList(OpenLectureVO openLectureVO) {
		return openLectureMapper.openlectureDataMovieViewList(openLectureVO);
	}

	public ArrayList<JSONObject> openlectureDataMovieList(OpenLectureVO openLectureVO) {
		return openLectureMapper.openlectureDataMovieList(openLectureVO);
	}

	public void openlectureMovieInsert(OpenLectureVO openLectureVO) {
		openLectureMapper.openlectureMovieInsert(openLectureVO);
	}

	public void openlectureMovieDelete(OpenLectureVO openLectureVO) {
		openLectureMapper.openlectureMovieDelete(openLectureVO);
	}

	public void openlectureMovieUpdate(OpenLectureVO openLectureVO) {
		openLectureMapper.openlectureMovieUpdate(openLectureVO);
	}

	public void openlectureMovieFileDelete(OpenLectureVO openLectureVO) {
		openLectureMapper.openlectureMovieFileDelete(openLectureVO);
	}

	public void openlectureMovieMemoInsert(OpenLectureVO openLectureVO) {
		openLectureMapper.openlectureMovieMemoInsert(openLectureVO);
	}

	public void openlectureMovieMemoUpdate(OpenLectureVO openLectureVO) {
		openLectureMapper.openlectureMovieMemoUpdate(openLectureVO);
	}

	public void openlectureMovieMemoDelete(OpenLectureVO openLectureVO) {
		openLectureMapper.openlectureMovieMemoDelete(openLectureVO);
	}

	public int openlectureDeleteCheck(OpenLectureVO openLectureVO) {
		return openLectureMapper.openlectureDeleteCheck(openLectureVO);
	}

	public ArrayList<JSONObject> playinfo(OpenLectureVO openLectureVO) {
		return openLectureMapper.playinfo(openLectureVO);
	}

	public ArrayList<JSONObject> getCbMovie4_free_admin(OpenLectureVO openLectureVO) {
		return openLectureMapper.getCbMovie4_free_admin(openLectureVO);
	}

	public int getCbMovie4_free_admin_count(OpenLectureVO openLectureVO) {
		return openLectureMapper.getCbMovie4_free_admin_count(openLectureVO);
	}

	public JSONObject openlectureOnDetailS(OpenLectureVO openLectureVO) {
		return openLectureMapper.openlectureOnDetailS(openLectureVO);
	}

	public void insertPmpDownLog(OpenLectureVO openLectureVO) {
		openLectureMapper.insertPmpDownLog(openLectureVO);
	}

	public ArrayList<JSONObject> openlectureWMV(OpenLectureVO openLectureVO) {
		return openLectureMapper.openlectureWMV(openLectureVO);
	}

	public ArrayList<JSONObject> openlectureDown_Count(OpenLectureVO openLectureVO) {
		return openLectureMapper.openlectureDown_Count(openLectureVO);
	}
}