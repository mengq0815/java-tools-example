package com.example.tool.demo;

import com.example.tool.demo.model.UserPageModel;

import java.util.HashMap;
import java.util.Map;

/**
 * @author mengq
 * @date 2020-11-05 21:20
 * @desc
 */
public class MyTest {

    public static void main(String[] args) {
        ExportInterface baseExport = new UserExportComment();

        UserPageModel pageModel = new UserPageModel();
        pageModel.setPage(1);
        pageModel.setPageSize(10);
        Map<String, String> map = new HashMap<>();
        MyTestLoop.test(new UserExportComment(), map, pageModel);
    }
}