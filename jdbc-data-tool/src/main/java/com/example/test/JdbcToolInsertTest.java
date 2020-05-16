package com.example.test;

import com.example.db.DbHelperTool;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author mengq
 * @date 2020-05-16 22:12
 */
@Slf4j
public class JdbcToolInsertTest {
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/db_test1?rewriteBatchedStatements=true&useUnicode=true&characterEncoding=utf8&autoReconnect=true&failOverReadOnly=false&serverTimezone=Asia/Shanghai&useSSL=false";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "123456";

    public static void main(String[] args) throws Exception {
        DbHelperTool dbHelperTool = new DbHelperTool(URL, DRIVER, USERNAME, PASSWORD);
        String insertSql1 = "INSERT INTO `student`(`name`, `age`) VALUES (?, ?)";
        int insertDemo1 = dbHelperTool.executeUpdate(insertSql1, "小八", 20);
        log.info("1.单行插入：{}", insertDemo1);

        String insertBatchSql1 = "INSERT INTO `student`(`name`, `age`) VALUES (?, ?),(?,?)";
        int insertBatchDemo1 = dbHelperTool.executeUpdate(insertBatchSql1, "小九", 20, "小十", 23);
        log.info("2.多行插入：{}", insertBatchDemo1);

        String insertBatchSql2 = "INSERT INTO `student`(`name`, `age`) VALUES (?, ?)";
        List<Object[]> paramsList = new ArrayList<>(10);
        Object[] paramItem;
        for (int i = 0; i <= 10; i++) {
            paramItem = new Object[]{"小", i};
            paramsList.add(paramItem);
        }
        int[] insertBatchDemo2 = dbHelperTool.executeBatch(insertBatchSql2, paramsList, 100);
        log.info("3.批量插入：{}", Arrays.toString(insertBatchDemo2));
    }
}