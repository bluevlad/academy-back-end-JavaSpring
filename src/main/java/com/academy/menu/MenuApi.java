package com.academy.menu;

import java.util.HashMap;

import org.json.simple.JSONObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.academy.common.CORSFilter;
import com.academy.menu.service.MenuService;
import com.academy.menu.service.MenuVO;

@RestController
@RequestMapping("/api/menu")
public class MenuApi extends CORSFilter {

    private MenuService menuService;

    public MenuApi(MenuService menuService) {
        this.menuService = menuService;
    }

    @GetMapping(value = "/getMenuTree")
    public JSONObject getMenuTree(@ModelAttribute("MenuVO") MenuVO menuVO) throws Exception {
        HashMap<String, Object> jsonObject = new HashMap<String, Object>();

        jsonObject.put("menuTree", menuService.getMenuHierarchy(menuVO));

        JSONObject jObject = new JSONObject(jsonObject);
        return jObject;
    }

}
