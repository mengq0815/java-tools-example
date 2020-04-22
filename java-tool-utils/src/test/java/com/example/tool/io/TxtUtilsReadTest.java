package com.example.tool.io;

import java.util.List;

/**
 * IO工具类测试
 *
 * @author 码农猿
 */
public class TxtUtilsReadTest {

    //3326+1109
    public static void main(String[] args) {

        String path = "/Users/mengqiang/project/Github/Java-tools-example/tools-util/src/test/java/com/example/tool/io/test1";

        long start1 = System.currentTimeMillis();

        List<String> contentList = TxtUtils.readTxt(path);
        long end1 = System.currentTimeMillis();
        System.out.println("读取数据耗时：" + (end1 - start1) + "--" + contentList.size());


        long start2 = System.currentTimeMillis();
        System.out.println("****** 开始输出数据 ******");
        for (String str : contentList) {
//            System.out.println(str);
        }
        long end2 = System.currentTimeMillis();
        System.out.println("输出数据耗时：" + (end2 - start2) + "--" + contentList.size());
    }
}