package com.academy.lecture.service;

import java.util.ArrayList;

import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;

import com.academy.mapper.LectureMapper;

/**
 * Lecture Service
 * ExamService 패턴 적용 - ArrayList<JSONObject> 및 VO 사용
 */
@Service
public class LectureService {

	private LectureMapper lectureMapper;

	public LectureService(LectureMapper lectureMapper) {
		this.lectureMapper = lectureMapper;
	}

	public ArrayList<JSONObject> lectureList(LectureVO lectureVO) {
		return lectureMapper.lectureList(lectureVO);
	}

	public ArrayList<JSONObject> oldFreeToNewFree(LectureVO lectureVO) {
		return lectureMapper.oldFreeToNewFree(lectureVO);
	}

	public int lectureListCount(LectureVO lectureVO) {
		return lectureMapper.lectureListCount(lectureVO);
	}

	public ArrayList<JSONObject> bookList(LectureVO lectureVO) {
		return lectureMapper.bookList(lectureVO);
	}

	public int bookListCount(LectureVO lectureVO) {
		return lectureMapper.bookListCount(lectureVO);
	}

	public ArrayList<JSONObject> getBridgeLeccodeSeq(LectureVO lectureVO) {
		return lectureMapper.getBridgeLeccodeSeq(lectureVO);
	}

	public ArrayList<JSONObject> getJongLeccodeSeq(LectureVO lectureVO) {
		return lectureMapper.getJongLeccodeSeq(lectureVO);
	}

	public ArrayList<JSONObject> getLeccode(LectureVO lectureVO) {
		return lectureMapper.getLeccode(lectureVO);
	}

	public ArrayList<JSONObject> getBridgeLeccode(LectureVO lectureVO) {
		return lectureMapper.getBridgeLeccode(lectureVO);
	}

	public ArrayList<JSONObject> BridgeLeccode(LectureVO lectureVO) {
		return lectureMapper.BridgeLeccode(lectureVO);
	}

	public void lectureInsert(LectureVO lectureVO) {
		lectureMapper.lectureInsert(lectureVO);
	}

	public void lectureBridgeInsert(LectureVO lectureVO) {
		lectureMapper.lectureBridgeInsert(lectureVO);
	}

	public void lectureBookInsert(LectureVO lectureVO) {
		lectureMapper.lectureBookInsert(lectureVO);
	}

	public void lectureBookInsert2(LectureVO lectureVO) {
		lectureMapper.lectureBookInsert2(lectureVO);
	}

	public ArrayList<JSONObject> lectureViewList(LectureVO lectureVO) {
		return lectureMapper.lectureViewList(lectureVO);
	}

	public ArrayList<JSONObject> lectureView(LectureVO lectureVO) {
		return lectureMapper.lectureView(lectureVO);
	}

	public ArrayList<JSONObject> lectureViewBookList(LectureVO lectureVO) {
		return lectureMapper.lectureViewBookList(lectureVO);
	}

	public void lectureBookDelete(LectureVO lectureVO) {
		lectureMapper.lectureBookDelete(lectureVO);
	}

	public void lectureUpdate(LectureVO lectureVO) {
		lectureMapper.lectureUpdate(lectureVO);
	}

	public void Modify_Lecture_On_Off(LectureVO lectureVO) {
		lectureMapper.Modify_Lecture_On_Off(lectureVO);
	}

	public void lectureIsUseUpdate(LectureVO lectureVO) {
		lectureMapper.lectureIsUseUpdate(lectureVO);
	}

	public void lectureDelete(LectureVO lectureVO) {
		lectureMapper.lectureDelete(lectureVO);
	}

	public void lectureBridgeDelete(LectureVO lectureVO) {
		lectureMapper.lectureBridgeDelete(lectureVO);
	}

	public void lecMovUpdate(LectureVO lectureVO) {
		lectureMapper.lecMovUpdate(lectureVO);
	}

	public ArrayList<JSONObject> lectureSeqList(LectureVO lectureVO) {
		return lectureMapper.lectureSeqList(lectureVO);
	}

	public void lectureSeqUpdate(LectureVO lectureVO) {
		lectureMapper.lectureSeqUpdate(lectureVO);
	}

	public ArrayList<JSONObject> lectureViewJongList(LectureVO lectureVO) {
		return lectureMapper.lectureViewJongList(lectureVO);
	}

	public ArrayList<JSONObject> lectureJongList(LectureVO lectureVO) {
		return lectureMapper.lectureJongList(lectureVO);
	}

	public int lectureJongListCount(LectureVO lectureVO) {
		return lectureMapper.lectureJongListCount(lectureVO);
	}

	public ArrayList<JSONObject> lectureYearList(LectureVO lectureVO) {
		return lectureMapper.lectureYearList(lectureVO);
	}

	public int lectureYearListCount(LectureVO lectureVO) {
		return lectureMapper.lectureYearListCount(lectureVO);
	}

	public ArrayList<JSONObject> lectureJongSubjectList(LectureVO lectureVO) {
		return lectureMapper.lectureJongSubjectList(lectureVO);
	}

	public ArrayList<JSONObject> lectureJongView(LectureVO lectureVO) {
		return lectureMapper.lectureJongView(lectureVO);
	}

	public int lectureJongSubjectListCount(LectureVO lectureVO) {
		return lectureMapper.lectureJongSubjectListCount(lectureVO);
	}

	public void lectureLecJongInsert(LectureVO lectureVO) {
		lectureMapper.lectureLecJongInsert(lectureVO);
	}

	public void lectureChoiceJongNoInsert(LectureVO lectureVO) {
		lectureMapper.lectureChoiceJongNoInsert(lectureVO);
	}

	public ArrayList<JSONObject> lectureViewLeccodeList(LectureVO lectureVO) {
		return lectureMapper.lectureViewLeccodeList(lectureVO);
	}

	public void lectureLecJongDelete(LectureVO lectureVO) {
		lectureMapper.lectureLecJongDelete(lectureVO);
	}

	public void lectureChoiceJongNoDelete(LectureVO lectureVO) {
		lectureMapper.lectureChoiceJongNoDelete(lectureVO);
	}

	public ArrayList<JSONObject> lecturePayList(LectureVO lectureVO) {
		return lectureMapper.lecturePayList(lectureVO);
	}

	public ArrayList<JSONObject> lectureJongPayList(LectureVO lectureVO) {
		return lectureMapper.lectureJongPayList(lectureVO);
	}

	public ArrayList<JSONObject> lectureDataMemoViewList(LectureVO lectureVO) {
		return lectureMapper.lectureDataMemoViewList(lectureVO);
	}

	public ArrayList<JSONObject> lectureDataViewList(LectureVO lectureVO) {
		return lectureMapper.lectureDataViewList(lectureVO);
	}

	public ArrayList<JSONObject> lectureMobileList(LectureVO lectureVO) {
		return lectureMapper.lectureMobileList(lectureVO);
	}

	public ArrayList<JSONObject> lectureDataMovieViewList(LectureVO lectureVO) {
		return lectureMapper.lectureDataMovieViewList(lectureVO);
	}

	public ArrayList<JSONObject> lectureDataMovieList(LectureVO lectureVO) {
		return lectureMapper.lectureDataMovieList(lectureVO);
	}

	public void lectureMovieInsert(LectureVO lectureVO) {
		lectureMapper.lectureMovieInsert(lectureVO);
	}

	public void lectureMovieDelete(LectureVO lectureVO) {
		lectureMapper.lectureMovieDelete(lectureVO);
	}

	public void lectureMovieUpdate(LectureVO lectureVO) {
		lectureMapper.lectureMovieUpdate(lectureVO);
	}

	public void lectureMovieFileDelete(LectureVO lectureVO) {
		lectureMapper.lectureMovieFileDelete(lectureVO);
	}

	public void lectureMovieMemoInsert(LectureVO lectureVO) {
		lectureMapper.lectureMovieMemoInsert(lectureVO);
	}

	public void lectureMovieMemoUpdate(LectureVO lectureVO) {
		lectureMapper.lectureMovieMemoUpdate(lectureVO);
	}

	public void lectureMovieMemoDelete(LectureVO lectureVO) {
		lectureMapper.lectureMovieMemoDelete(lectureVO);
	}

	public int lectureDeleteCheck(LectureVO lectureVO) {
		return lectureMapper.lectureDeleteCheck(lectureVO);
	}

	public ArrayList<JSONObject> playinfo(LectureVO lectureVO) {
		return lectureMapper.playinfo(lectureVO);
	}

	public ArrayList<JSONObject> getCbMovie4_free_admin(LectureVO lectureVO) {
		return lectureMapper.getCbMovie4_free_admin(lectureVO);
	}

	public int getCbMovie4_free_admin_count(LectureVO lectureVO) {
		return lectureMapper.getCbMovie4_free_admin_count(lectureVO);
	}

	public JSONObject lectureOnDetailS(LectureVO lectureVO) {
		return lectureMapper.lectureOnDetailS(lectureVO);
	}

	public void insertPmpDownLog(LectureVO lectureVO) {
		lectureMapper.insertPmpDownLog(lectureVO);
	}

	public ArrayList<JSONObject> lectureWMV(LectureVO lectureVO) {
		return lectureMapper.lectureWMV(lectureVO);
	}

	public ArrayList<JSONObject> lectureDown_Count(LectureVO lectureVO) {
		return lectureMapper.lectureDown_Count(lectureVO);
	}

	public void oldFreeToNewFreeInsert(LectureVO lectureVO) {
		lectureMapper.oldFreeToNewFreeInsert(lectureVO);
	}

	public void oldBogangFreeToNewBogangFree(LectureVO lectureVO) {
		lectureMapper.oldBogangFreeToNewBogangFree(lectureVO);
	}

	public ArrayList<JSONObject> lectureFreePassPayList(LectureVO lectureVO) {
		return lectureMapper.lectureFreePassPayList(lectureVO);
	}

	public ArrayList<JSONObject> YearIngList(LectureVO lectureVO) {
		return lectureMapper.YearIngList(lectureVO);
	}

	public ArrayList<JSONObject> MyYearIngList(LectureVO lectureVO) {
		return lectureMapper.MyYearIngList(lectureVO);
	}

	public ArrayList<JSONObject> bookView(LectureVO lectureVO) {
		return lectureMapper.bookView(lectureVO);
	}

	public String getRleccode(LectureVO lectureVO) {
		return lectureMapper.getRleccode(lectureVO);
	}

	public void oldTbmovieToNewTbmovieInsert(LectureVO lectureVO) {
		lectureMapper.oldTbmovieToNewTbmovieInsert(lectureVO);
	}

	public void oldBogangFreeToNewFreeBogangInsert(LectureVO lectureVO) {
		lectureMapper.oldBogangFreeToNewFreeBogangInsert(lectureVO);
	}
}