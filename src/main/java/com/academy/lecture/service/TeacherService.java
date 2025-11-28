package com.academy.lecture.service;

import java.util.ArrayList;

import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;

import com.academy.mapper.TeacherMapper;

/**
 * Teacher Service
 * ExamService 패턴 적용 - ArrayList<JSONObject> 및 VO 사용
 */
@Service
public class TeacherService {

	private TeacherMapper teacherMapper;

	public TeacherService(TeacherMapper teacherMapper) {
		this.teacherMapper = teacherMapper;
	}

	public ArrayList<JSONObject> getKindList(TeacherVO teacherVO) {
		return teacherMapper.getKindList(teacherVO);
	}

	public ArrayList<JSONObject> getSubjectList(TeacherVO teacherVO) {
		return teacherMapper.getSubjectList(teacherVO);
	}

	public ArrayList<JSONObject> teacherList(TeacherVO teacherVO) {
		return teacherMapper.teacherList(teacherVO);
	}

	public int teacherListCount(TeacherVO teacherVO) {
		return teacherMapper.teacherListCount(teacherVO);
	}

	public ArrayList<JSONObject> teacherAllList(TeacherVO teacherVO) {
		return teacherMapper.teacherAllList(teacherVO);
	}

	public int teacherAllListCount(TeacherVO teacherVO) {
		return teacherMapper.teacherAllListCount(teacherVO);
	}

	public int teacherIdCheck(TeacherVO teacherVO) {
		return teacherMapper.teacherIdCheck(teacherVO);
	}

	public void teacherInsert(TeacherVO teacherVO) {
		teacherMapper.teacherInsert(teacherVO);
	}

	public ArrayList<JSONObject> teacherView(TeacherVO teacherVO) {
		return teacherMapper.teacherView(teacherVO);
	}

	public void teacherUpdate(TeacherVO teacherVO) {
		teacherMapper.teacherUpdate(teacherVO);
	}

	public void teacherCategoryInsert(TeacherVO teacherVO) {
		teacherMapper.teacherCategoryInsert(teacherVO);
	}

	public void teacherSubjectInsert(TeacherVO teacherVO) {
		teacherMapper.teacherSubjectInsert(teacherVO);
	}

	public void teacherSubjectUpdate(TeacherVO teacherVO) {
		teacherMapper.teacherSubjectUpdate(teacherVO);
	}

	public int teacherSubjectCount(TeacherVO teacherVO) {
		return teacherMapper.teacherSubjectCount(teacherVO);
	}

	public void teacherIsUseUpdate(TeacherVO teacherVO) {
		teacherMapper.teacherIsUseUpdate(teacherVO);
	}

	public void teacherDelete(TeacherVO teacherVO) {
		teacherMapper.teacherDelete(teacherVO);
	}

	public void teacherCategoryDelete(TeacherVO teacherVO) {
		teacherMapper.teacherCategoryDelete(teacherVO);
	}

	public void teacherSubjectDelete(TeacherVO teacherVO) {
		teacherMapper.teacherSubjectDelete(teacherVO);
	}

	public void teacherSeqUpdate(TeacherVO teacherVO) {
		teacherMapper.teacherSeqUpdate(teacherVO);
	}

	public ArrayList<JSONObject> teacherBookLog(TeacherVO teacherVO) {
		return teacherMapper.teacherBookLog(teacherVO);
	}

	public void teacherMain_Category_Insert(TeacherVO teacherVO) {
		teacherMapper.teacherMain_Category_Insert(teacherVO);
	}

	public void teacherMain_Category_Delete(TeacherVO teacherVO) {
		teacherMapper.teacherMain_Category_Delete(teacherVO);
	}

	public ArrayList<JSONObject> teacherMainList(TeacherVO teacherVO) {
		return teacherMapper.teacherMainList(teacherVO);
	}

	public int teacherMainListCount(TeacherVO teacherVO) {
		return teacherMapper.teacherMainListCount(teacherVO);
	}

	public ArrayList<JSONObject> findTeacherList(TeacherVO teacherVO) {
		return teacherMapper.findTeacherList(teacherVO);
	}

	public int teacherMain_Category_Subject(TeacherVO teacherVO) {
		return teacherMapper.teacherMain_Category_Subject(teacherVO);
	}

	public int teacherIntro_Category_Subject(TeacherVO teacherVO) {
		return teacherMapper.teacherIntro_Category_Subject(teacherVO);
	}

	public int teacherIntro_F_Category_Subject(TeacherVO teacherVO) {
		return teacherMapper.teacherIntro_F_Category_Subject(teacherVO);
	}

	public ArrayList<JSONObject> teacherIntroList(TeacherVO teacherVO) {
		return teacherMapper.teacherIntroList(teacherVO);
	}

	public int teacherIntroListCount(TeacherVO teacherVO) {
		return teacherMapper.teacherIntroListCount(teacherVO);
	}

	public void teacherIntro_Category_Insert(TeacherVO teacherVO) {
		teacherMapper.teacherIntro_Category_Insert(teacherVO);
	}

	public void teacherIntro_F_Category_Insert(TeacherVO teacherVO) {
		teacherMapper.teacherIntro_F_Category_Insert(teacherVO);
	}

	public void teacherIntro_Category_Delete(TeacherVO teacherVO) {
		teacherMapper.teacherIntro_Category_Delete(teacherVO);
	}

	public void teacherIntro_F_Category_Delete(TeacherVO teacherVO) {
		teacherMapper.teacherIntro_F_Category_Delete(teacherVO);
	}

	public ArrayList<JSONObject> teacherIntro_offList(TeacherVO teacherVO) {
		return teacherMapper.teacherIntro_offList(teacherVO);
	}

	public int teacherIntro_offListCount(TeacherVO teacherVO) {
		return teacherMapper.teacherIntro_offListCount(teacherVO);
	}
}