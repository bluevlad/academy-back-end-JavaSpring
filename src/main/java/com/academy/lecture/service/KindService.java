package com.academy.lecture.service;

import java.util.ArrayList;

import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;

import com.academy.mapper.KindMapper;

/**
 * Kind Service
 * ExamService 패턴 적용 - ArrayList<JSONObject> 및 VO 사용
 */
@Service
public class KindService {

	private KindMapper kindMapper;

	public KindService(KindMapper kindMapper) {
		this.kindMapper = kindMapper;
	}

	public ArrayList<JSONObject> getKindList(KindVO kindVO) {
		return kindMapper.getKindList(kindVO);
	}

	public ArrayList<JSONObject> kindList(KindVO kindVO) {
		return kindMapper.kindList(kindVO);
	}

	public int kindListCount(KindVO kindVO) {
		return kindMapper.kindListCount(kindVO);
	}

	public void kindInsert(KindVO kindVO) {
		kindMapper.kindInsert(kindVO);
	}

	public ArrayList<JSONObject> kindView(KindVO kindVO) {
		return kindMapper.kindView(kindVO);
	}

	public void kindUpdate(KindVO kindVO) {
		kindMapper.kindUpdate(kindVO);
	}

	public void kindDelete(KindVO kindVO) {
		kindMapper.kindDelete(kindVO);
	}

	public int kindCheck(KindVO kindVO) {
		return kindMapper.kindCheck(kindVO);
	}

	public ArrayList<JSONObject> selectKindCode() {
		return kindMapper.selectKindCode();
	}

	public void SeqUpdate(KindVO kindVO) {
		kindMapper.SeqUpdate(kindVO);
	}
}