package com.example.db;


/**
 * 数据源构建工厂
 *
 * @author mengq
 * @date 2020-05-16 00:11
 */
public class DbHelperFactory {
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static String URL_SUFFIX_PARAM = "?rewriteBatchedStatements=true&useUnicode=true&characterEncoding=utf8&autoReconnect=true&failOverReadOnly=false&serverTimezone=Asia/Shanghai&useSSL=false";

    /**
     * 数据源 1
     */
    private static final String DB1_URL = "jdbc:mysql://localhost:3306/db_test1" + URL_SUFFIX_PARAM;
    private static final String DB1_USERNAME = "root";
    private static final String DB1_PASSWORD = "123456";

    /**
     * 数据源 2
     */
    private static final String DB2_URL = "jdbc:mysql://localhost:3306/db_test1" + URL_SUFFIX_PARAM;
    private static final String DB2_USERNAME = "root";
    private static final String DB2_PASSWORD = "123456";

    public static DbHelperTool DB_ONE = new DbHelperTool(DB1_URL, DRIVER, DB1_USERNAME, DB1_PASSWORD);
    public static DbHelperTool DB_TWO = new DbHelperTool(DB2_URL, DRIVER, DB2_USERNAME, DB2_PASSWORD);

}