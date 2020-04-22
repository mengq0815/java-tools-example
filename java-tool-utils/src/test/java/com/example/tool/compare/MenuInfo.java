package com.example.tool.compare;

import lombok.Data;


@Data
public class MenuInfo {

    private String menuId;
    private String menuName;

    public MenuInfo() {
    }

    public MenuInfo(String menuId, String menuName) {
        this.menuId = menuId;
        this.menuName = menuName;
    }
}