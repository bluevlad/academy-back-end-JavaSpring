package com.academy.mapper;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;
import org.json.simple.JSONObject;

import com.academy.menu.service.MenuVO;

@Mapper
public interface MenuMapper {

    public ArrayList<JSONObject> selectMenuList(MenuVO menuVO);

}
