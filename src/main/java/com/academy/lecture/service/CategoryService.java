package com.academy.lecture.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.academy.mapper.KindMapper;
import com.academy.mapper.SeriesMapper;

/**
 * Category Service
 * Series-Category 관리 서비스 (직접 구현)
 */
@Service
public class CategoryService {

    @Autowired
    private KindMapper kindMapper;

    @Autowired
    private SeriesMapper seriesMapper;

    /**
     * 트리 리스트 조회
     */
    public List<HashMap<String, Object>> getSeriesCateTree() {
        return kindMapper.getSeriesCateTree();
    }

    /**
     * 상세 조회
     */
    public HashMap<String, Object> getDetail(SeriesVO seriesVO) {
        return seriesMapper.seriesView(seriesVO);
    }

    /**
     * 수정 프로세스
     */
    public void updateProcess(SeriesVO seriesVO) {
        seriesMapper.seriesUpdate(seriesVO);
    }

    /**
     * 삭제 프로세스
     */
    public int deleteProcess(SeriesVO seriesVO) {
        seriesMapper.categoryDelete(seriesVO);
        return seriesMapper.seriesDelete(seriesVO);
    }

    /**
     * 등록 프로세스
     */
    public int insertProcess(SeriesVO seriesVO) {
        return seriesMapper.seriesInsert(seriesVO);
    }

    /**
     * ID 중복체크
     */
    public int idCheck(SeriesVO seriesVO) {
        return seriesMapper.seriesCheck(seriesVO);
    }

    /**
     * 최대 순서 조회
     */
    public HashMap<String, Object> getMaxOrdr(CategoryVO categoryVO) {
        return kindMapper.getMaxOrdr(categoryVO);
    }
}