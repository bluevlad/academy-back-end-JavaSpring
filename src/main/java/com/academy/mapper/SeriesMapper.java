package com.academy.mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

/**
 * Series Mapper Interface
 */
@Mapper
public interface SeriesMapper {

    List<HashMap<String, String>> seriesList(Object obj);

    int seriesListCount(Object obj);

    void seriesInsert(Object obj);

    List<HashMap<String, String>> seriesView(Object obj);

    void seriesUpdate(Object obj);

    void seriesDelete(Object obj);

    int seriesCheck(Object obj);

    void catSeriesInsert(Object obj);

    void catSeriesDeleteWthCatCd(Object obj);

    void catSeriesDeleteWthSrsCd(Object obj);
}