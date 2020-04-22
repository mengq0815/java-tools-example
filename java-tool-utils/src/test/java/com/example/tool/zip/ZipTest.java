package com.example.tool.zip;

/**
 * 压缩工具测试
 *
 * @author 码农猿
 */
public class ZipTest {

    public static void main(String[] args) {

        String filePath = "/Users/mengqiang/logs";
        String zipPath = "/Users/mengqiang/logs2";

        String filePath1 = "/Users/mengqiang/project/Github/Java-tools-example/tools-util/src/test/java/com/example/tool/io/test1.txt";
        String zipPath2 = "/Users/mengqiang/project/Github/Java-tools-example/tools-util/src/test/java/com/example/tool/zip/tst.zip";


        long start1 = System.currentTimeMillis();
        ZipUtil.zip(filePath, zipPath);
        long end1 = System.currentTimeMillis();
        System.out.println("success");
        System.out.println("time:" + (end1 - start1));
    }
}