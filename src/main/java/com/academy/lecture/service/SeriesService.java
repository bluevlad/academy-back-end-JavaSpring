package com.academy.lecture.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.academy.mapper.SeriesMapper;
import com.academy.mapper.CategorySeriesMapper;

/**
 * Series Service
 * 시리즈 관리 서비스 (직접 구현)
 */
@Service(value="seriesService")
public class SeriesService {

    @Autowired
    private SeriesMapper seriesMapper;

    @Autowired
    private CategorySeriesMapper categorySeriesMapper;

    /**
     * Series 목록 조회
     */
    public List<HashMap<String, String>> seriesList(SeriesVO seriesVO){
        return seriesMapper.seriesList(seriesVO);
    }

    /**
     * Series 목록 개수 조회
     */
    public int seriesListCount(SeriesVO seriesVO){
        return seriesMapper.seriesListCount(seriesVO);
    }

    /**
     * Series 등록
     */
    @SuppressWarnings("unchecked")
    @Transactional(readOnly=false,rollbackFor=Exception.class)
    public void seriesInsert(Object obj){
        seriesMapper.seriesInsert(obj);

        String[] CAT_CDs = (String[])((HashMap<String, Object>)obj).get("CAT_CDs");
        if(null != CAT_CDs && CAT_CDs.length > 0) {
            for(int i=0; i<CAT_CDs.length; i++) {
                ((HashMap<String, Object>)obj).put("CAT_CD", CAT_CDs[i]);
                ((HashMap<String, Object>)obj).put("ORDR", (i+1));
                categorySeriesMapper.catSeriesInsert(obj);
            }
        }
    }

    /**
     * Series 상세 조회
     */
    public List<HashMap<String, String>> seriesView(SeriesVO seriesVO){
        return seriesMapper.seriesView(seriesVO);
    }

    /**
     * Series 수정
     */
    @SuppressWarnings("unchecked")
    @Transactional(readOnly=false,rollbackFor=Exception.class)
    public void seriesUpdate(Object obj){
        seriesMapper.seriesUpdate(obj);

        categorySeriesMapper.catSeriesDeleteWthSrsCd(obj);

        String[] CAT_CDs = (String[])((HashMap<String, Object>)obj).get("CAT_CDs");
        if(null != CAT_CDs && CAT_CDs.length > 0) {
            for(int i=0; i<CAT_CDs.length; i++) {
                ((HashMap<String, Object>)obj).put("CAT_CD", CAT_CDs[i]);
                ((HashMap<String, Object>)obj).put("ORDR", (i+1));
                categorySeriesMapper.catSeriesInsert(obj);
            }
        }
    }

    /**
     * Series 삭제
     */
    @SuppressWarnings("unchecked")
    @Transactional(readOnly=false,rollbackFor=Exception.class)
    public void seriesDelete(Object obj){
        String[] DEL_ARR = (String[])((HashMap<String, Object>)obj).get("SRS_CDs");
        if(DEL_ARR != null) {
            for(int i=0; i<DEL_ARR.length; i++) {
                ((HashMap<String, Object>)obj).put("SRS_CD", DEL_ARR[i]);
                seriesMapper.seriesDelete(obj);
                categorySeriesMapper.catSeriesDeleteWthSrsCd(obj);
            }
        }
    }

    /**
     * Series 코드 중복 체크
     */
    public int seriesCheck(SeriesVO seriesVO){
        return seriesMapper.seriesCheck(seriesVO);
    }
}