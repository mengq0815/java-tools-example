package com.example.tool.tree;


import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 树形结构
 *
 * @author mengqiang
 * @version TreeTest.java, v 3.0 2019-02-28
 */
public class TreeTest {

    public static List<MenuInfo> menuInfoList = new ArrayList<>();

    static {
        MenuInfo menuInfo1 = new MenuInfo("1", "首页", "");
        MenuInfo menuInfo2 = new MenuInfo("2", "人员管理", "");
        MenuInfo menuInfo3 = new MenuInfo("3", "角色管理", "");
        MenuInfo menuInfo4 = new MenuInfo("4", "业务管理", "");
        MenuInfo menuInfo5 = new MenuInfo("5", "人员-1", "2");
        MenuInfo menuInfo6 = new MenuInfo("6", "人员-2", "2");
        MenuInfo menuInfo7 = new MenuInfo("7", "角色-1", "3");
        MenuInfo menuInfo8 = new MenuInfo("8", "角色-2", "3");
        MenuInfo menuInfo9 = new MenuInfo("9", "业务-1", "4");
        MenuInfo menuInfo10 = new MenuInfo("10", "业务-2", "4");
        MenuInfo menuInfo11 = new MenuInfo("11", "业务-1-1", "9");
        MenuInfo menuInfo12 = new MenuInfo("12", "业务-1-2", "9");
        menuInfoList.add(menuInfo1);
        menuInfoList.add(menuInfo2);
        menuInfoList.add(menuInfo3);
        menuInfoList.add(menuInfo4);
        menuInfoList.add(menuInfo5);
        menuInfoList.add(menuInfo6);
        menuInfoList.add(menuInfo7);
        menuInfoList.add(menuInfo8);
        menuInfoList.add(menuInfo9);
        menuInfoList.add(menuInfo10);
        menuInfoList.add(menuInfo11);
        menuInfoList.add(menuInfo12);

        System.out.println("size=" + menuInfoList.size());
    }


    public static void main(String[] args) {

        Map<String, MenuInfo> menuInfoMap = new HashMap<>();

        for (MenuInfo menuInfo : menuInfoList) {
            menuInfoMap.put(menuInfo.getMenuId(), menuInfo);
        }
        List<MenuInfo> menuResult = new ArrayList<>();
        for (MenuInfo menuInfo : menuInfoList) {
            if (menuInfoMap.containsKey(menuInfo.getMenParentId())) {
                menuInfoMap.get(menuInfo.getMenParentId()).getChildren().add(menuInfo);
            } else {
                menuResult.add(menuInfo);
            }
        }

        System.out.println(JSON.toJSONString(menuResult));

    }
}