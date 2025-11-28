package com.academy.lecture.service;

import java.util.ArrayList;

import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;

import com.academy.mapper.FormMapper;

/**
 * Form Service
 * ExamService 패턴 적용 - ArrayList<JSONObject> 및 VO 사용
 */
@Service
public class FormService {

	private FormMapper formMapper;

	public FormService(FormMapper formMapper) {
		this.formMapper = formMapper;
	}

	public ArrayList<JSONObject> formList(FormVO formVO) {
		return formMapper.formList(formVO);
	}

	public int formListCount(FormVO formVO) {
		return formMapper.formListCount(formVO);
	}

	public String formGetCode() {
		return formMapper.formGetCode();
	}

	public void formInsert(FormVO formVO) {
		formMapper.formInsert(formVO);
	}

	public ArrayList<JSONObject> formView(FormVO formVO) {
		return formMapper.formView(formVO);
	}

	public void formUpdate(FormVO formVO) {
		formMapper.formUpdate(formVO);
	}

	public void formDelete(FormVO formVO) {
		formMapper.formDelete(formVO);
	}

	public int formCheck(FormVO formVO) {
		return formMapper.formCheck(formVO);
	}

	public ArrayList<JSONObject> getCodeList(FormVO formVO) {
		return formMapper.getCodeList(formVO);
	}
}