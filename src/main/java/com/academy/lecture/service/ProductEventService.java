package com.academy.lecture.service;

import java.util.ArrayList;

import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;

import com.academy.mapper.ProductEventMapper;

/**
 * ProductEvent Service
 * ExamService 패턴 적용 - ArrayList<JSONObject> 및 VO 사용
 */
@Service
public class ProductEventService {

	private ProductEventMapper productEventMapper;

	public ProductEventService(ProductEventMapper productEventMapper) {
		this.productEventMapper = productEventMapper;
	}

	public ArrayList<JSONObject> list(ProductEventVO productEventVO) {
		return productEventMapper.list(productEventVO);
	}

	public int listCount(ProductEventVO productEventVO) {
		return productEventMapper.listCount(productEventVO);
	}

	public JSONObject getOne(ProductEventVO productEventVO) {
		return productEventMapper.getOne(productEventVO);
	}

	public ArrayList<JSONObject> list_prd(ProductEventVO productEventVO) {
		return productEventMapper.list_prd(productEventVO);
	}

	public void insert(ProductEventVO productEventVO) {
		productEventMapper.insert(productEventVO);
	}

	public void update(ProductEventVO productEventVO) {
		productEventMapper.update(productEventVO);
	}

	public void lec_insert(ProductEventVO productEventVO) {
		productEventMapper.lec_insert(productEventVO);
	}

	public void lec_delete(ProductEventVO productEventVO) {
		productEventMapper.lec_delete(productEventVO);
	}

	public ArrayList<JSONObject> getCaCatCdList(ProductEventVO productEventVO) {
		return productEventMapper.getCaCatCdList(productEventVO);
	}

	public ArrayList<JSONObject> getVwMenuMstTree_lec(ProductEventVO productEventVO) {
		return productEventMapper.getVwMenuMstTree_lec(productEventVO);
	}

	public ArrayList<JSONObject> getCaSubjectCdList(ProductEventVO productEventVO) {
		return productEventMapper.getCaSubjectCdList(productEventVO);
	}

	public ArrayList<JSONObject> getCbLecMstFreeOrderList(ProductEventVO productEventVO) {
		return productEventMapper.getCbLecMstFreeOrderList(productEventVO);
	}

	public int getCbLecMstListFreeOrderCount(ProductEventVO productEventVO) {
		return productEventMapper.getCbLecMstListFreeOrderCount(productEventVO);
	}
}