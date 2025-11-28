package com.academy.lecture.service;

import java.util.ArrayList;

import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;

import com.academy.mapper.LectureMstMapper;

/**
 * LectureMst Service
 * ExamService 패턴 적용 - ArrayList<JSONObject> 및 VO 사용
 */
@Service
public class LectureMstService {

	private LectureMstMapper lectureMstMapper;

	public LectureMstService(LectureMstMapper lectureMstMapper) {
		this.lectureMstMapper = lectureMstMapper;
	}

	public ArrayList<JSONObject> lecturemstList(LectureMstVO lectureMstVO) {
		return lectureMstMapper.lecturemstList(lectureMstVO);
	}

	public int lecturemstListCount(LectureMstVO lectureMstVO) {
		return lectureMstMapper.lecturemstListCount(lectureMstVO);
	}

	public ArrayList<JSONObject> getBridgeMstcodeSeq(LectureMstVO lectureMstVO) {
		return lectureMstMapper.getBridgeMstcodeSeq(lectureMstVO);
	}

	public ArrayList<JSONObject> getBridgeMstcode(LectureMstVO lectureMstVO) {
		return lectureMstMapper.getBridgeMstcode(lectureMstVO);
	}

	public ArrayList<JSONObject> getMstcode(LectureMstVO lectureMstVO) {
		return lectureMstMapper.getMstcode(lectureMstVO);
	}

	public void lecturemstInsert(LectureMstVO lectureMstVO) {
		lectureMstMapper.lecturemstInsert(lectureMstVO);
	}

	public void lectureBridgeInsert(LectureMstVO lectureMstVO) {
		lectureMstMapper.lectureBridgeInsert(lectureMstVO);
	}

	public void lectureBookInsert2(LectureMstVO lectureMstVO) {
		lectureMstMapper.lectureBookInsert2(lectureMstVO);
	}

	public ArrayList<JSONObject> lectureDataMemoViewList(LectureMstVO lectureMstVO) {
		return lectureMstMapper.lectureDataMemoViewList(lectureMstVO);
	}

	public ArrayList<JSONObject> lectureDataViewList(LectureMstVO lectureMstVO) {
		return lectureMstMapper.lectureDataViewList(lectureMstVO);
	}

	public ArrayList<JSONObject> lectureWMV(LectureMstVO lectureMstVO) {
		return lectureMstMapper.lectureWMV(lectureMstVO);
	}

	public ArrayList<JSONObject> lectureDown_Count(LectureMstVO lectureMstVO) {
		return lectureMstMapper.lectureDown_Count(lectureMstVO);
	}

	public int lectureDeleteCheck(LectureMstVO lectureMstVO) {
		return lectureMstMapper.lectureDeleteCheck(lectureMstVO);
	}

	public ArrayList<JSONObject> lectureView(LectureMstVO lectureMstVO) {
		return lectureMstMapper.lectureView(lectureMstVO);
	}

	public ArrayList<JSONObject> lectureViewList(LectureMstVO lectureMstVO) {
		return lectureMstMapper.lectureViewList(lectureMstVO);
	}

	public ArrayList<JSONObject> lectureViewBookList(LectureMstVO lectureMstVO) {
		return lectureMstMapper.lectureViewBookList(lectureMstVO);
	}

	public void lectureBookDelete(LectureMstVO lectureMstVO) {
		lectureMstMapper.lectureBookDelete(lectureMstVO);
	}

	public void lecturemstUpdate(LectureMstVO lectureMstVO) {
		lectureMstMapper.lecturemstUpdate(lectureMstVO);
	}

	public void lectureBookInsert(LectureMstVO lectureMstVO) {
		lectureMstMapper.lectureBookInsert(lectureMstVO);
	}

	public void lectureDelete(LectureMstVO lectureMstVO) {
		lectureMstMapper.lectureDelete(lectureMstVO);
	}

	public void lectureBridgeDelete(LectureMstVO lectureMstVO) {
		lectureMstMapper.lectureBridgeDelete(lectureMstVO);
	}
}