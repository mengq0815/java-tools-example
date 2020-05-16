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
public class JdbcToolUpdateTest {

    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/db_test1?rewriteBatchedStatements=true&useUnicode=true&characterEncoding=utf8&autoReconnect=true&failOverReadOnly=false&serverTimezone=Asia/Shanghai&useSSL=false";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "123456";

    public static void main(String[] args) throws Exception {
        DbHelperTool dbHelperTool = new DbHelperTool(URL, DRIVER, USERNAME, PASSWORD);

        String updateSql1 = "update student set name = ? , age = ? where id = ?";
        int update1 = dbHelperTool.executeUpdate(updateSql1, "下雪了", 2, 1);
        log.info("1.单条修改：{}", update1);


        String batchUpdateSql = "update student set name = ? , age = ? where id = ?";
        List<Object[]> paramsList = new ArrayList<>(10);
        Object[] paramItem;
        for (int i = 1; i <= 10; i++) {
            //按照参数顺序
            paramItem = new Object[]{"大", i, i};
            paramsList.add(paramItem);
        }

        int[] insertBatchDemo2 = dbHelperTool.executeBatch(batchUpdateSql, paramsList, 100);
        log.info("2.批量修改：{}", Arrays.toString(insertBatchDemo2));
    }
}