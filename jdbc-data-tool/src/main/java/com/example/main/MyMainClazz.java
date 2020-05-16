package com.example.main;

import com.example.db.DbHelperFactory;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.Map;

/**
 * 主函数，可执行 java -jar xxxx.jar
 *
 * @author mengq
 * @date 2020-05-16 14:14
 */
@Slf4j
public class MyMainClazz {

    public static void main(String[] args) throws Exception {
        long start = System.currentTimeMillis();
        log.info("run start args:{}", Arrays.toString(args));

        String sql1 = "SELECT *  from student where id =?";
        Map<String, Object> selectOne1 = DbHelperFactory.DB_TWO.selectOne(sql1, 1);
        log.info("查询ID为1的学生信息：{}", selectOne1);

        log.info("run end time:{} , args:{}", (System.currentTimeMillis() - start), Arrays.toString(args));
    }

}