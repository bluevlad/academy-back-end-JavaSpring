package com.academy.lecture.service;

import java.util.ArrayList;

import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.academy.mapper.SeriesMapper;

/**
 * Series Service
 * ExamService 패턴 적용 - ArrayList<JSONObject> 및 VO 사용
 */
@Service
public class SeriesService {

	private SeriesMapper seriesMapper;

	public SeriesService(SeriesMapper seriesMapper) {
		this.seriesMapper = seriesMapper;
	}

	public ArrayList<JSONObject> seriesList(SeriesVO seriesVO) {
		return seriesMapper.seriesList(seriesVO);
	}

	public int seriesListCount(SeriesVO seriesVO) {
		return seriesMapper.seriesListCount(seriesVO);
	}

	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public void seriesInsert(SeriesVO seriesVO) {
		seriesMapper.seriesInsert(seriesVO);
	}

	public ArrayList<JSONObject> seriesView(SeriesVO seriesVO) {
		return seriesMapper.seriesView(seriesVO);
	}

	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public void seriesUpdate(SeriesVO seriesVO) {
		seriesMapper.seriesUpdate(seriesVO);
	}

	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public void seriesDelete(SeriesVO seriesVO) {
		seriesMapper.seriesDelete(seriesVO);
	}

	public int seriesCheck(SeriesVO seriesVO) {
		return seriesMapper.seriesCheck(seriesVO);
	}

	public void catSeriesInsert(SeriesVO seriesVO) {
		seriesMapper.catSeriesInsert(seriesVO);
	}

	public void catSeriesDeleteWthCatCd(SeriesVO seriesVO) {
		seriesMapper.catSeriesDeleteWthCatCd(seriesVO);
	}

	public void catSeriesDeleteWthSrsCd(SeriesVO seriesVO) {
		seriesMapper.catSeriesDeleteWthSrsCd(seriesVO);
	}
}