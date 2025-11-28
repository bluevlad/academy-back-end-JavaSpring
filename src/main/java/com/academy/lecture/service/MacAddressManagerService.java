package com.academy.lecture.service;

import java.util.ArrayList;

import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;

import com.academy.mapper.MacAddressManagerMapper;

/**
 * MacAddressManager Service
 * ExamService 패턴 적용 - ArrayList<JSONObject> 및 VO 사용
 */
@Service
public class MacAddressManagerService {

	private MacAddressManagerMapper macAddressManagerMapper;

	public MacAddressManagerService(MacAddressManagerMapper macAddressManagerMapper) {
		this.macAddressManagerMapper = macAddressManagerMapper;
	}

	public ArrayList<JSONObject> macaddressmanagerList(MacAddressManagerVO macAddressManagerVO) {
		return macAddressManagerMapper.macaddressmanagerList(macAddressManagerVO);
	}

	public ArrayList<JSONObject> devicelist(MacAddressManagerVO macAddressManagerVO) {
		return macAddressManagerMapper.devicelist(macAddressManagerVO);
	}

	public ArrayList<JSONObject> macaddressView(MacAddressManagerVO macAddressManagerVO) {
		return macAddressManagerMapper.macaddressView(macAddressManagerVO);
	}

	public int macaddressmanagerListCount(MacAddressManagerVO macAddressManagerVO) {
		return macAddressManagerMapper.macaddressmanagerListCount(macAddressManagerVO);
	}

	public void macaddressmanagerUpdate(MacAddressManagerVO macAddressManagerVO) {
		macAddressManagerMapper.macaddressmanagerUpdate(macAddressManagerVO);
	}

	public void macaddressmanagerUpdate1(MacAddressManagerVO macAddressManagerVO) {
		macAddressManagerMapper.macaddressmanagerUpdate1(macAddressManagerVO);
	}
}