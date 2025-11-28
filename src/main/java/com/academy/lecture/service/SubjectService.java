package com.academy.lecture.service;

import java.util.ArrayList;

import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;

import com.academy.mapper.SubjectMapper;

/**
 * Subject Service
 * ExamService 패턴 적용 - ArrayList<JSONObject> 및 VO 사용
 */
@Service
public class SubjectService {

	private SubjectMapper subjectMapper;

	public SubjectService(SubjectMapper subjectMapper) {
		this.subjectMapper = subjectMapper;
	}

	public ArrayList<JSONObject> subjectList(SubjectVO subjectVO) {
		return subjectMapper.subjectList(subjectVO);
	}

	public int subjectListCount(SubjectVO subjectVO) {
		return subjectMapper.subjectListCount(subjectVO);
	}

	public String subjectGetCode(SubjectVO subjectVO) {
		return subjectMapper.subjectGetCode(subjectVO);
	}

	public void subjectInsert(SubjectVO subjectVO) {
		subjectMapper.subjectInsert(subjectVO);
	}

	public ArrayList<JSONObject> subjectView(SubjectVO subjectVO) {
		return subjectMapper.subjectView(subjectVO);
	}

	public void subjectUpdate(SubjectVO subjectVO) {
		subjectMapper.subjectUpdate(subjectVO);
	}

	public void subjectDelete(SubjectVO subjectVO) {
		subjectMapper.subjectDelete(subjectVO);
	}

	public int subjectCheck(SubjectVO subjectVO) {
		return subjectMapper.subjectCheck(subjectVO);
	}

	public void subjectCategoryInsert(SubjectVO subjectVO) {
		subjectMapper.subjectCategoryInsert(subjectVO);
	}

	public void subjectCategoryDelete(SubjectVO subjectVO) {
		subjectMapper.subjectCategoryDelete(subjectVO);
	}

	public void subjectCategoryDeleteByCat(SubjectVO subjectVO) {
		subjectMapper.subjectCategoryDeleteByCat(subjectVO);
	}

	public void subjectCategoryOrderInsert(SubjectVO subjectVO) {
		subjectMapper.subjectCategoryOrderInsert(subjectVO);
	}

	public int chkSubjectCategoryOrderCnt(SubjectVO subjectVO) {
		return subjectMapper.chkSubjectCategoryOrderCnt(subjectVO);
	}

	public int chkSubjectCategoryCnt(SubjectVO subjectVO) {
		return subjectMapper.chkSubjectCategoryCnt(subjectVO);
	}

	public void subjectCategoryOrderDeleteByOnoff(SubjectVO subjectVO) {
		subjectMapper.subjectCategoryOrderDeleteByOnoff(subjectVO);
	}

	public String getSubjectCategoryOrderIdx(SubjectVO subjectVO) {
		return subjectMapper.getSubjectCategoryOrderIdx(subjectVO);
	}

	public void subjectCategoryOrderDelete(SubjectVO subjectVO) {
		subjectMapper.subjectCategoryOrderDelete(subjectVO);
	}

	public ArrayList<JSONObject> subjectCategoryView(SubjectVO subjectVO) {
		return subjectMapper.subjectCategoryView(subjectVO);
	}

	public ArrayList<JSONObject> findSubjectCategoryList(SubjectVO subjectVO) {
		return subjectMapper.findSubjectCategoryList(subjectVO);
	}

	public void main_category_subject_order_Insert(SubjectVO subjectVO) {
		subjectMapper.main_category_subject_order_Insert(subjectVO);
	}
}