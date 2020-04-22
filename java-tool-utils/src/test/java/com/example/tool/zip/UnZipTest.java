package com.example.tool.zip;

/**
 * 压缩工具测试
 *
 * @author 码农猿
 */
public class UnZipTest {

    public static void main(String[] args) {


        String zipFilePath = "/Users/mengqiang/logs22.zip";
        String targetFileDir = "/Users/mengqiang/logs3";
        long start1 = System.currentTimeMillis();
        ZipUtil.unzip(zipFilePath, targetFileDir);
        long end1 = System.currentTimeMillis();
        System.out.println("success");
        System.out.println("time:" + (end1 - start1));
    }
}