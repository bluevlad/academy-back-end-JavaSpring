package com.academy.mapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * ProductEvent Mapper Interface
 */
@Mapper
public interface ProductEventMapper {

    List<HashMap<String, String>> list(HashMap<String, String> params);

    int listCount(HashMap<String, String> params);

    HashMap<String, String> getOne(HashMap<String, String> params);

    List<HashMap<String, String>> list_prd(HashMap<String, String> params);

    void insert(HashMap<String, String> params);

    void update(HashMap<String, String> params);

    void lec_insert(HashMap<String, String> params);

    void lec_delete(HashMap<String, String> params);

    @SuppressWarnings({ "rawtypes" })
    List getCaCatCdList(Map keyName);

    @SuppressWarnings({ "rawtypes" })
    List getVwMenuMstTree_lec(Map keyName);

    @SuppressWarnings({ "rawtypes" })
    List getCaSubjectCdList(Map keyName);

    @SuppressWarnings({ "rawtypes" })
    List getCbLecMstFreeOrderList(Map keyName);

    @SuppressWarnings({ "rawtypes" })
    int getCbLecMstListFreeOrderCount(Map keyName);
}