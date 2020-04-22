package com.example.tool.tree;


import com.alibaba.fastjson.JSON;

import java.util.List;

/**
 * 树形结构
 *
 * @author mengqiang
 * @version TreeTest.java, v 3.0 2019-02-28
 */
public class TreeTest2 {

    public static void main(String[] args) {

        List<MenuInfo> menuInfoList = TreeTest.menuInfoList;

        ListToTreeUtil<MenuInfo> menuList = new ListToTreeUtil<MenuInfo>();

        List<MenuInfo> treeListObject = menuList.getTreeListObject(menuInfoList);

        System.out.println("--");
        System.out.println(JSON.toJSONString(treeListObject));

    }
}