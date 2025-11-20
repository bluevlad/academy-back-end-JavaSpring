package com.academy.menu.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;

import com.academy.mapper.MenuMapper;

@Service
public class MenuService {

    private MenuMapper menuMapper;

    public MenuService(MenuMapper menuMapper) {
        this.menuMapper = menuMapper;
    }

    /**
     * 메뉴를 계층구조로 반환한다. (root부터 하위 노드까지 중첩된 구조)
     * 반환 타입은 ArrayList<JSONObject>로 프론젝트 관례에 맞춘다.
     */
    public ArrayList<JSONObject> getMenuHierarchy(MenuVO menuVO) {
        ArrayList<JSONObject> flat = menuMapper.selectMenuList(menuVO);

        // build map of id -> node
        Map<Integer, JSONObject> map = new HashMap<>();
        List<JSONObject> roots = new ArrayList<>();

        for (JSONObject obj : flat) {
            // normalize fields and ensure children array
            JSONObject node = new JSONObject();
            Integer id = ((Number) obj.get("menu_no")).intValue();
            node.put("menuNo", id);
            node.put("menuId", obj.get("menu_id"));
            node.put("menuTitle", obj.get("menu_title"));
            node.put("menuUrl", obj.get("menu_url"));
            node.put("menuIcon", obj.get("menu_icon"));
            node.put("menuUpperId", obj.get("menu_upper_id"));
            node.put("menuDepth", obj.get("menu_depth"));
            node.put("isUse", obj.get("is_use"));
            node.put("children", new ArrayList<JSONObject>());

            map.put(id, node);
        }

        for (JSONObject node : map.values()) {
            Object parentObj = node.get("menuUpperId");
            int parentId = 0;
            if (parentObj != null) {
                try {
                    parentId = ((Number) parentObj).intValue();
                } catch (Exception e) {
                    parentId = Integer.parseInt(String.valueOf(parentObj));
                }
            }
            if (parentId == 0) {
                roots.add(node);
            } else {
                JSONObject parent = map.get(parentId);
                if (parent != null) {
                    @SuppressWarnings("unchecked")
                    ArrayList<JSONObject> children = (ArrayList<JSONObject>) parent.get("children");
                    children.add(node);
                } else {
                    // Orphan node; treat as root
                    roots.add(node);
                }
            }
        }

        return new ArrayList<>(roots);
    }

}
