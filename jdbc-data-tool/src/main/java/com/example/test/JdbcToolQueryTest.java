package com.example.test;

import com.example.bean.test.Student;
import com.example.db.DbHelperTool;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Map;

/**
 * @author mengq
 * @date 2020-05-16 22:12
 */
@Slf4j
public class JdbcToolQueryTest {
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/db_test1?rewriteBatchedStatements=true&useUnicode=true&characterEncoding=utf8&autoReconnect=true&failOverReadOnly=false&serverTimezone=Asia/Shanghai&useSSL=false";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "123456";


    public static void main(String[] args) throws Exception {
        DbHelperTool dbHelperTool = new DbHelperTool(URL, DRIVER, USERNAME, PASSWORD);

        String sql1 = "SELECT *  from student where id =?";
        Map<String, Object> selectOne1 = dbHelperTool.selectOne(sql1, 1);
        log.info("1.单条查询-返回Map：{}", selectOne1);


        Student selectOne2 = dbHelperTool.selectOne(sql1, Student.class, 1);
        log.info("2.单条查询-返回实际实例：{}", selectOne2);

        String sql2 = "SELECT *  from student";
        List<Map<String, Object>> selectList1 = dbHelperTool.selectList(sql2);
        log.info("3.多条查询-返回Map：{}", selectList1);

        List<Student> selectList2 = dbHelperTool.selectList(sql2, Student.class);
        log.info("4.多条查询-返回实际实例：{}", selectList2);

        String sql3 = "SELECT *  from student where age > ? ";
        List<Map<String, Object>> selectPage1 = dbHelperTool.selectPage(sql3, 1, 2, 33);
        log.info("5.分页查询-返回Map：{}", selectPage1);

        String sql4 = "SELECT count(*)  from student where id in(?,?,?,?)";
        Object[] param = {1, 2, 3, 4};
        Integer selectCount = dbHelperTool.selectCount(sql4, param);
        log.info("6.统计行数：{}", selectCount);

    }
}