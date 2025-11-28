package com.academy.lecture.service;

import java.util.ArrayList;

import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;

import com.academy.mapper.CategoryMapper;

/**
 * Category Service
 * ExamService 패턴 적용 - ArrayList<JSONObject> 및 VO 사용
 */
@Service
public class CategoryService {

	private CategoryMapper categoryMapper;

	public CategoryService(CategoryMapper categoryMapper) {
		this.categoryMapper = categoryMapper;
	}

	public ArrayList<JSONObject> getSeriesCateTree() {
		return categoryMapper.getSeriesCateTree();
	}

	public JSONObject getDetail(CategoryVO categoryVO) {
		return categoryMapper.getDetail(categoryVO);
	}

	public void updateProcess(CategoryVO categoryVO) {
		categoryMapper.updateProcess(categoryVO);
	}

	public int deleteProcess(CategoryVO categoryVO) {
		return categoryMapper.deleteProcess(categoryVO);
	}

	public int idCheck(CategoryVO categoryVO) {
		return categoryMapper.idCheck(categoryVO);
	}

	public int insertProcess(CategoryVO categoryVO) {
		return categoryMapper.insertProcess(categoryVO);
	}

	public JSONObject getMaxOrdr(CategoryVO categoryVO) {
		return categoryMapper.getMaxOrdr(categoryVO);
	}
}