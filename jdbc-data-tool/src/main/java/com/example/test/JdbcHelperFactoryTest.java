package com.example.test;

import com.example.bean.test.Student;
import com.example.db.DbHelperFactory;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Map;

/**
 * @author mengq
 * @date 2020-05-16 22:12
 */
@Slf4j
public class JdbcHelperFactoryTest {
    public static void main(String[] args) throws Exception {

        String sql1 = "SELECT *  from student where id =?";
        Map<String, Object> selectOne1 = DbHelperFactory.DB_TWO.selectOne(sql1, 1);
        log.info("1.单条查询-返回Map：{}", selectOne1);

        Student selectOne2 = DbHelperFactory.DB_TWO.selectOne(sql1, Student.class, 1);
        log.info("2.单条查询-返回实际实例：{}", selectOne2);

        String sql2 = "SELECT *  from student";
        List<Map<String, Object>> selectList1 = DbHelperFactory.DB_ONE.selectList(sql2);
        log.info("3.多条查询-返回Map：{}", selectList1);

        List<Student> selectList2 = DbHelperFactory.DB_ONE.selectList(sql2, Student.class);
        log.info("4.多条查询-返回实际实例：{}", selectList2);

        String sql3 = "SELECT *  from student where age > ? ";
        Object[] pageParam = {4};
        List<Map<String, Object>> selectPage1 = DbHelperFactory.DB_ONE.selectPage(sql3, 1, 2, pageParam);
        log.info("5.分页查询-返回Map：{}", selectPage1);

        String sql4 = "SELECT count(*)  from student where id in(?,?,?,?)";
        Object[] param = {1, 2, 3, 4};
        Integer selectCount = DbHelperFactory.DB_TWO.selectCount(sql4, param);
        log.info("6.统计行数：{}", selectCount);
    }
}