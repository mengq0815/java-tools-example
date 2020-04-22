package com.example.tool.io;

import java.util.ArrayList;
import java.util.List;

/**
 * IO工具类测试
 *
 * @author 码农猿
 */
public class TxtUtilsWriteTest {

    public static void main(String[] args) {

        String path = "/Users/mengqiang/project/Github/Java-tools-example/tools-util/src/test/java/com/example/tool/io/test1";

        String content = "->测试";
        long start1 = System.currentTimeMillis();
        List<String> contentList = new ArrayList<>();
        for (int i = 1; i <= 100; i++) {
            contentList.add(String.valueOf(i).concat(content));
        }

        long end1 = System.currentTimeMillis();
        System.out.println("构建数据耗时：" + (end1 - start1) + "--" + contentList.size());
        long start2 = System.currentTimeMillis();
        System.out.println("****** 开始输出数据 ******");
        TxtUtils.writeManyLineTxt(path, contentList);
        long end2 = System.currentTimeMillis();
        System.out.println("输出数据耗时：" + (end2 - start2) + "--" + contentList.size());
    }
}