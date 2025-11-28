package com.academy.mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

/**
 * MacAddressManager Mapper Interface
 */
@Mapper
public interface MacAddressManagerMapper {

    List<HashMap<String, String>> macaddressmanagerList(HashMap<String, String> params);

    List<HashMap<String, String>> devicelist(HashMap<String, String> params);

    List<HashMap<String, String>> macaddressView(HashMap<String, String> params);

    int macaddressmanagerListCount(HashMap<String, String> params);

    void macaddressmanagerUpdate(HashMap<String, String> params);

    void macaddressmanagerUpdate1(HashMap<String, String> params);
}