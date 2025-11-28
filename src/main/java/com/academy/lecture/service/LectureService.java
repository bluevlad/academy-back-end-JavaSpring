package com.academy.lecture.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Service;

import com.academy.mapper.LectureMapper;

/**
 * Lecture Service
 * LoginService 패턴 적용 - ServiceImpl 없이 @Service 클래스로 직접 구현
 */
@Service
public class LectureService {

	private LectureMapper lectureMapper;

	public LectureService(LectureMapper lectureMapper) {
		this.lectureMapper = lectureMapper;
	}

	public List<HashMap<String, String>> lectureList(HashMap<String, String> params) {
		return lectureMapper.lectureList(params);
	}

	public List<HashMap<String, String>> oldFreeToNewFree(HashMap<String, String> params) {
		return lectureMapper.oldFreeToNewFree(params);
	}

	public int lectureListCount(HashMap<String, String> params) {
		return lectureMapper.lectureListCount(params);
	}

	public List<HashMap<String, String>> bookList(HashMap<String, String> params) {
		return lectureMapper.bookList(params);
	}

	public int bookListCount(HashMap<String, String> params) {
		return lectureMapper.bookListCount(params);
	}

	public List<HashMap<String, String>> getBridgeLeccodeSeq(HashMap<String, String> params) {
		return lectureMapper.getBridgeLeccodeSeq(params);
	}

	public List<HashMap<String, String>> getJongLeccodeSeq(HashMap<String, String> params) {
		return lectureMapper.getJongLeccodeSeq(params);
	}

	public List<HashMap<String, String>> getLeccode(HashMap<String, String> params) {
		return lectureMapper.getLeccode(params);
	}

	public List<HashMap<String, String>> getBridgeLeccode(HashMap<String, String> params) {
		return lectureMapper.getBridgeLeccode(params);
	}

	public List<HashMap<String, String>> BridgeLeccode(HashMap<String, String> params) {
		return lectureMapper.BridgeLeccode(params);
	}

	public void lectureInsert(HashMap<String, String> params) {
		lectureMapper.lectureInsert(params);
	}

	public void lectureBridgeInsert(HashMap<String, String> params) {
		lectureMapper.lectureBridgeInsert(params);
	}

	public void lectureBookInsert(HashMap<String, String> params) {
		lectureMapper.lectureBookInsert(params);
	}

	public void lectureBookInsert2(HashMap<String, String> params) {
		lectureMapper.lectureBookInsert2(params);
	}

	public List<HashMap<String, String>> lectureViewList(HashMap<String, String> params) {
		return lectureMapper.lectureViewList(params);
	}

	public List<HashMap<String, String>> lectureView(HashMap<String, String> params) {
		return lectureMapper.lectureView(params);
	}

	public List<HashMap<String, String>> lectureViewBookList(HashMap<String, String> params) {
		return lectureMapper.lectureViewBookList(params);
	}

	public void lectureBookDelete(HashMap<String, String> params) {
		lectureMapper.lectureBookDelete(params);
	}

	public void lectureUpdate(HashMap<String, String> params) {
		lectureMapper.lectureUpdate(params);
	}

	public void Modify_Lecture_On_Off(HashMap<String, String> params) {
		lectureMapper.Modify_Lecture_On_Off(params);
	}

	public void lectureIsUseUpdate(HashMap<String, String> params) {
		lectureMapper.lectureIsUseUpdate(params);
	}

	public void lectureDelete(HashMap<String, String> params) {
		lectureMapper.lectureDelete(params);
	}

	public void lectureBridgeDelete(HashMap<String, String> params) {
		lectureMapper.lectureBridgeDelete(params);
	}

	public void lecMovUpdate(HashMap<String, String> params) {
		lectureMapper.lecMovUpdate(params);
	}

	public List<HashMap<String, String>> lectureSeqList(HashMap<String, String> params) {
		return lectureMapper.lectureSeqList(params);
	}

	public void lectureSeqUpdate(HashMap<String, String> params) {
		lectureMapper.lectureSeqUpdate(params);
	}

	public List<HashMap<String, String>> lectureViewJongList(HashMap<String, String> params) {
		return lectureMapper.lectureViewJongList(params);
	}

	public List<HashMap<String, String>> lectureJongList(HashMap<String, String> params) {
		return lectureMapper.lectureJongList(params);
	}

	public int lectureJongListCount(HashMap<String, String> params) {
		return lectureMapper.lectureJongListCount(params);
	}

	public List<HashMap<String, String>> lectureYearList(HashMap<String, String> params) {
		return lectureMapper.lectureYearList(params);
	}

	public int lectureYearListCount(HashMap<String, String> params) {
		return lectureMapper.lectureYearListCount(params);
	}

	public List<HashMap<String, String>> lectureJongSubjectList(HashMap<String, String> params) {
		return lectureMapper.lectureJongSubjectList(params);
	}

	public List<HashMap<String, String>> lectureJongView(HashMap<String, String> params) {
		return lectureMapper.lectureJongView(params);
	}

	public int lectureJongSubjectListCount(HashMap<String, String> params) {
		return lectureMapper.lectureJongSubjectListCount(params);
	}

	public void lectureLecJongInsert(HashMap<String, String> params) {
		lectureMapper.lectureLecJongInsert(params);
	}

	public void lectureChoiceJongNoInsert(HashMap<String, String> params) {
		lectureMapper.lectureChoiceJongNoInsert(params);
	}

	public List<HashMap<String, String>> lectureViewLeccodeList(HashMap<String, String> params) {
		return lectureMapper.lectureViewLeccodeList(params);
	}

	public void lectureLecJongDelete(HashMap<String, String> params) {
		lectureMapper.lectureLecJongDelete(params);
	}

	public void lectureChoiceJongNoDelete(HashMap<String, String> params) {
		lectureMapper.lectureChoiceJongNoDelete(params);
	}

	public List<HashMap<String, String>> lecturePayList(HashMap<String, String> params) {
		return lectureMapper.lecturePayList(params);
	}

	public List<HashMap<String, String>> lectureJongPayList(HashMap<String, String> params) {
		return lectureMapper.lectureJongPayList(params);
	}

	public List<HashMap<String, String>> lectureDataMemoViewList(HashMap<String, String> params) {
		return lectureMapper.lectureDataMemoViewList(params);
	}

	public List<HashMap<String, String>> lectureDataViewList(HashMap<String, String> params) {
		return lectureMapper.lectureDataViewList(params);
	}

	public List<HashMap<String, String>> lectureMobileList(HashMap<String, String> params) {
		return lectureMapper.lectureMobileList(params);
	}

	public List<HashMap<String, String>> lectureDataMovieViewList(HashMap<String, String> params) {
		return lectureMapper.lectureDataMovieViewList(params);
	}

	public List<HashMap<String, String>> lectureDataMovieList(HashMap<String, String> params) {
		return lectureMapper.lectureDataMovieList(params);
	}

	public void lectureMovieInsert(HashMap<String, String> params) {
		lectureMapper.lectureMovieInsert(params);
	}

	public void lectureMovieDelete(HashMap<String, String> params) {
		lectureMapper.lectureMovieDelete(params);
	}

	public void lectureMovieUpdate(HashMap<String, String> params) {
		lectureMapper.lectureMovieUpdate(params);
	}

	public void lectureMovieFileDelete(HashMap<String, String> params) {
		lectureMapper.lectureMovieFileDelete(params);
	}

	public void lectureMovieMemoInsert(HashMap<String, String> params) {
		lectureMapper.lectureMovieMemoInsert(params);
	}

	public void lectureMovieMemoUpdate(HashMap<String, String> params) {
		lectureMapper.lectureMovieMemoUpdate(params);
	}

	public void lectureMovieMemoDelete(HashMap<String, String> params) {
		lectureMapper.lectureMovieMemoDelete(params);
	}

	public int lectureDeleteCheck(HashMap<String, String> params) {
		return lectureMapper.lectureDeleteCheck(params);
	}

	public List<HashMap<String, String>> playinfo(HashMap<String, String> params) {
		return lectureMapper.playinfo(params);
	}

	public List<HashMap<String, String>> getCbMovie4_free_admin(HashMap<String, String> params) {
		return lectureMapper.getCbMovie4_free_admin(params);
	}

	public int getCbMovie4_free_admin_count(HashMap<String, String> params) {
		return lectureMapper.getCbMovie4_free_admin_count(params);
	}

	public HashMap<String, String> lectureOnDetailS(HashMap<String, String> params) {
		return lectureMapper.lectureOnDetailS(params);
	}

	public void insertPmpDownLog(HashMap<String, String> params) {
		lectureMapper.insertPmpDownLog(params);
	}

	public List<HashMap<String, String>> lectureWMV(HashMap<String, String> params) {
		return lectureMapper.lectureWMV(params);
	}

	public List<HashMap<String, String>> lectureDown_Count(HashMap<String, String> params) {
		return lectureMapper.lectureDown_Count(params);
	}

	public void oldFreeToNewFreeInsert(HashMap<String, String> params) {
		lectureMapper.oldFreeToNewFreeInsert(params);
	}

	public void oldBogangFreeToNewBogangFree(HashMap<String, String> params) {
		lectureMapper.oldBogangFreeToNewBogangFree(params);
	}

	public List<HashMap<String, String>> lectureFreePassPayList(HashMap<String, String> params) {
		return lectureMapper.lectureFreePassPayList(params);
	}

	public List<HashMap<String, String>> YearIngList(HashMap<String, String> params) {
		return lectureMapper.YearIngList(params);
	}

	public List<HashMap<String, String>> MyYearIngList(HashMap<String, String> params) {
		return lectureMapper.MyYearIngList(params);
	}

	public List<HashMap<String, String>> bookView(HashMap<String, String> params) {
		return lectureMapper.bookView(params);
	}
}