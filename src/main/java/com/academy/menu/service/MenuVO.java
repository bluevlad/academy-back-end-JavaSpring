package com.academy.menu.service;

import com.academy.common.CommonVO;

public class MenuVO extends CommonVO {

    private int menuNo;
    private int menuUpperId;
    private String isUse;

    public int getMenuNo() {
        return menuNo;
    }

    public void setMenuNo(int menuNo) {
        this.menuNo = menuNo;
    }

    public int getMenuUpperId() {
        return menuUpperId;
    }

    public void setMenuUpperId(int menuUpperId) {
        this.menuUpperId = menuUpperId;
    }

    public String getIsUse() {
        return isUse;
    }

    public void setIsUse(String isUse) {
        this.isUse = isUse;
    }

}
