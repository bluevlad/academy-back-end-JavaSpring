package com.academy.mapper;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;
import org.json.simple.JSONObject;

import com.academy.lecture.service.MacAddressManagerVO;

/**
 * MacAddressManager Mapper Interface
 * ExamMapper 패턴 적용 - ArrayList<JSONObject> 및 VO 사용
 */
@Mapper
public interface MacAddressManagerMapper {

	ArrayList<JSONObject> macaddressmanagerList(MacAddressManagerVO macAddressManagerVO);

	ArrayList<JSONObject> devicelist(MacAddressManagerVO macAddressManagerVO);

	ArrayList<JSONObject> macaddressView(MacAddressManagerVO macAddressManagerVO);

	int macaddressmanagerListCount(MacAddressManagerVO macAddressManagerVO);

	void macaddressmanagerUpdate(MacAddressManagerVO macAddressManagerVO);

	void macaddressmanagerUpdate1(MacAddressManagerVO macAddressManagerVO);
}