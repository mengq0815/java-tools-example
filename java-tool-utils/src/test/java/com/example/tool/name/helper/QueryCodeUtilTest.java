package com.example.tool.name.helper;


public class QueryCodeUtilTest {

    public static void main(String[] args) {

        String str1 = "我是测试";
        String str2 = "小明";
        String str3 = "大黄";

        System.out.println(QueryCodeUtil.getQueryCode(str1));
        System.out.println(QueryCodeUtil.getQueryCode(str2));
        System.out.println(QueryCodeUtil.getQueryCode(str3));
    }
}