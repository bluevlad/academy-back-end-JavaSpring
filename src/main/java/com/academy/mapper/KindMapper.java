package com.academy.mapper;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;
import org.json.simple.JSONObject;

import com.academy.lecture.service.KindVO;

/**
 * Kind Mapper Interface
 * ExamMapper 패턴 적용 - ArrayList<JSONObject> 및 VO 사용
 */
@Mapper
public interface KindMapper {

    ArrayList<JSONObject> getKindList(KindVO kindVO);

    ArrayList<JSONObject> kindList(KindVO kindVO);

    int kindListCount(KindVO kindVO);

    void kindInsert(KindVO kindVO);

    ArrayList<JSONObject> kindView(KindVO kindVO);

    void kindUpdate(KindVO kindVO);

    void kindDelete(KindVO kindVO);

    int kindCheck(KindVO kindVO);

    ArrayList<JSONObject> selectKindCode();

    void SeqUpdate(KindVO kindVO);
}