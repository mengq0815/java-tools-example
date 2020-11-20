package com.example.tool.demo;

import com.example.tool.demo.model.BasePageModel;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Map;

/**
 * @author mengq
 * @date 2020-11-05 21:23
 * @desc
 */
public class MyTestLoop {

    public static void test(ExportInterface baseExport, Map<String, String> map, BasePageModel param) {
        List<?> objects = null;
        int page = param.getPage();
        while (true) {
            objects = baseExport.doExport(param);
            System.out.println(String.format("%d--%s", page, objects));
            page++;
            param.setPage(page);
            if (CollectionUtils.isEmpty(objects)) {
                System.out.println("执行完成");
                return;
            }
        }
    }
}