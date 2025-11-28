package com.academy.mapper;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;
import org.json.simple.JSONObject;

import com.academy.lecture.service.ProductEventVO;

/**
 * ProductEvent Mapper Interface
 * ExamMapper 패턴 적용 - ArrayList<JSONObject> 및 VO 사용
 */
@Mapper
public interface ProductEventMapper {

	ArrayList<JSONObject> list(ProductEventVO productEventVO);

	int listCount(ProductEventVO productEventVO);

	JSONObject getOne(ProductEventVO productEventVO);

	ArrayList<JSONObject> list_prd(ProductEventVO productEventVO);

	void insert(ProductEventVO productEventVO);

	void update(ProductEventVO productEventVO);

	void lec_insert(ProductEventVO productEventVO);

	void lec_delete(ProductEventVO productEventVO);

	ArrayList<JSONObject> getCaCatCdList(ProductEventVO productEventVO);

	ArrayList<JSONObject> getVwMenuMstTree_lec(ProductEventVO productEventVO);

	ArrayList<JSONObject> getCaSubjectCdList(ProductEventVO productEventVO);

	ArrayList<JSONObject> getCbLecMstFreeOrderList(ProductEventVO productEventVO);

	int getCbLecMstListFreeOrderCount(ProductEventVO productEventVO);
}