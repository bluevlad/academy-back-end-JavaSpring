package com.academy.mapper;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;
import org.json.simple.JSONObject;

import com.academy.lecture.service.SeriesVO;

/**
 * Series Mapper Interface
 * ExamMapper 패턴 적용 - ArrayList<JSONObject> 및 VO 사용
 */
@Mapper
public interface SeriesMapper {

    ArrayList<JSONObject> seriesList(SeriesVO seriesVO);

    int seriesListCount(SeriesVO seriesVO);

    void seriesInsert(SeriesVO seriesVO);

    ArrayList<JSONObject> seriesView(SeriesVO seriesVO);

    void seriesUpdate(SeriesVO seriesVO);

    void seriesDelete(SeriesVO seriesVO);

    int seriesCheck(SeriesVO seriesVO);

    void catSeriesInsert(SeriesVO seriesVO);

    void catSeriesDeleteWthCatCd(SeriesVO seriesVO);

    void catSeriesDeleteWthSrsCd(SeriesVO seriesVO);
}