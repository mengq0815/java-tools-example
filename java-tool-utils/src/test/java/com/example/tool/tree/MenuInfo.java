package com.example.tool.tree;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author mengqiang
 * @version .java, v 0.1   -   -
 */
@Data
public class MenuInfo implements TreeParentNode<MenuInfo> {

    private String menuId;
    private String menuName;
    private String menParentId;

    private List<MenuInfo> children = new ArrayList<>();

    public MenuInfo() {
    }

    public MenuInfo(String menuId, String menuName, String menParentId) {
        this.menuId = menuId;
        this.menuName = menuName;
        this.menParentId = menParentId;
    }


    @Override
    public String getTreeNodeId() {
        return this.menuId;
    }

    @Override
    public String getParentTreeNodeId() {
        return this.menParentId;
    }

    @Override
    public List<MenuInfo> getChildTreeNodes() {
        return children;
    }


}