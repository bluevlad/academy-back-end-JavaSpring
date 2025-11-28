package com.academy.mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

/**
 * Category Mapper Interface
 */
@Mapper
public interface CategoryMapper {

    List<HashMap<String, Object>> getSeriesCateTree();

    HashMap<String, Object> getDetail(HashMap<String, Object> params);

    void updateProcess(HashMap<String, Object> params);

    int deleteProcess(HashMap<String, Object> params);

    int idCheck(HashMap<String, Object> params);

    int insertProcess(HashMap<String, Object> params);

    HashMap<String, Object> getMaxOrdr(Object obj);
}