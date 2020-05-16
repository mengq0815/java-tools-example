package com.example.db;

import com.example.util.PageUtils;
import com.google.common.base.CaseFormat;
import lombok.extern.slf4j.Slf4j;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * JDBC连接数据源工具类
 *
 * @author mengq
 * @date 2020-05-16 00:11
 */
@Slf4j
public class DbHelperTool {

    private static final String SPACE = " ";
    private static final String LIMIT = "LIMIT";
    private static final String LIMIT_SUFFIX = SPACE + "LIMIT ?,?";

    private String url;
    private String driver;
    private String username;
    private String password;

    /**
     * 获取对象实例
     */
    private static DbHelperTool dbHelperTool;

    /**
     * 数据库连接
     */
    private static Connection connection;

    /**
     * 执行对象预通道
     */
    private static PreparedStatement preparedStatement;

    /**
     * 返回值
     */
    private static ResultSet resultSet;

    public DbHelperTool(String url, String driver, String username, String password) {
        this.url = url;
        this.driver = driver;
        this.username = username;
        this.password = password;
    }

    /**
     * 获取连接对象
     *
     * @return Connection连接对象
     */
    public Connection getConnection() {
        Connection conn = null;
        try {
            //加载驱动-校验是否存在
            Class.forName(driver);
            //获取连接
            conn = DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException("获取 Connection 出现异常！", e);
        }
        return conn;
    }

    /**
     * 封装jdbc增加，删除，修改
     */
    public int executeUpdate(String sql, Object... params) throws SQLException {
        int result;
        try {
            //获取数据库连接对象
            connection = getConnection();
            //获取预编译语句对象
            preparedStatement = connection.prepareStatement(sql);
            int index = 1;
            //给预编译语句赋值
            if (params != null) {
                for (Object param : params) {
                    preparedStatement.setObject(index++, param);
                }
            }
            log.debug("{}", preparedStatement.toString());
            //执行SQL语句获取执行结果
            result = preparedStatement.executeUpdate();
        } finally {
            //关闭连接
            closeAll();
        }
        return result;
    }

    /**
     * 批量新增
     *
     * @param sql       sql语句
     * @param params    执行参数，排列泛型内Object[]参数必须按照SQL参数顺序
     * @param batchSize 批次提交最大值
     * @throws Exception
     */
    public int[] executeBatch(String sql, List<Object[]> params, int batchSize) throws SQLException {
        int[] result = null;
        try {
            //获取数据库连接对象
            connection = getConnection();
            connection.setAutoCommit(false);
            //获取预编译语句对象
            preparedStatement = connection.prepareStatement(sql);
            //给预编译语句赋值
            for (int i = 0; i < params.size(); i++) {
                int index = 1;
                for (Object param : params.get(i)) {
                    preparedStatement.setObject(index++, param);
                }
                preparedStatement.addBatch();
                if (i % batchSize == 0) {
                    preparedStatement.executeBatch();
                }
            }
            log.debug("{}", preparedStatement.toString());
            //执行SQL语句获取执行结果
            result = preparedStatement.executeBatch();
            connection.commit();
        } finally {
            //关闭连接
            closeAll();
        }
        return result;
    }

    /**
     * 查询返回单条
     *
     * @param sql    sql语句
     * @param params 执行参数
     * @throws Exception
     */
    public Map<String, Object> selectOne(String sql, Object... params) throws SQLException {
        List<Map<String, Object>> result = selectList(sql, params);
        return result != null && !result.isEmpty() ? result.get(0) : null;
    }

    /**
     * 查询返回集合
     *
     * @param sql    sql语句
     * @param params 执行参数
     * @throws Exception
     */
    public List<Map<String, Object>> selectList(String sql, Object... params) throws SQLException {
        List<Map<String, Object>> list = null;
        try {
            list = new ArrayList<>();
            //执行SQL获取结果
            resultSet = commonStep(sql, params);
            ResultSetMetaData metaData = resultSet.getMetaData();
            int colsLen = metaData.getColumnCount();
            Map<String, Object> valueMap = null;
            while (resultSet.next()) {
                valueMap = new HashMap<>(colsLen);
                for (int i = 0; i < colsLen; i++) {
                    String colsName = metaData.getColumnName(i + 1);
                    valueMap.put(colsName, resultSet.getObject(colsName));
                }
                list.add(valueMap);
            }
        } finally {
            //关闭连接
            closeAll();
        }
        return list;
    }

    /**
     * 查询操作
     * 注：参数默认遵循-驼峰转下划线
     *
     * @param sql    SQL语句
     * @param clazz  返回对象class
     * @param params 可变参数
     * @param <T>    具体操作的实体类
     */
    public <T> T selectOne(String sql, Class<T> clazz, Object... params) throws Exception {
        List<T> result = selectList(sql, clazz, true, params);
        return result != null && !result.isEmpty() ? result.get(0) : null;
    }

    /**
     * 查询操作
     *
     * @param sql           SQL语句
     * @param clazz         返回对象class
     * @param underlineFlag 参数名是否驼峰转下划线
     * @param params        可变参数
     * @param <T>           具体操作的实体类
     */
    public <T> T selectOne(String sql, Class<T> clazz, boolean underlineFlag, Object... params) throws Exception {
        List<T> result = selectList(sql, clazz, underlineFlag, params);
        return result != null && !result.isEmpty() ? result.get(0) : null;
    }

    /**
     * 查询操作
     * 注：参数默认遵循-驼峰转下划线
     *
     * @param sql    SQL语句
     * @param clazz  返回对象class
     * @param params 可变参数
     * @param <T>    具体操作的实体类
     */
    public <T> List<T> selectList(String sql, Class<T> clazz, Object... params) throws Exception {
        return selectList(sql, clazz, true, params);
    }

    /**
     * 查询操作
     *
     * @param sql    SQL语句
     * @param params 可变参数
     */
    public Integer selectCount(String sql, Object... params)
            throws Exception {
        int count = 0;
        try {
            //执行SQL获取结果
            resultSet = commonStep(sql, params);
            while (resultSet.next()) {
                count = resultSet.getInt(1);
            }
        } finally {
            //关闭连接
            closeAll();
        }
        return count;
    }

    /**
     * 查询操作
     *
     * @param sql           SQL语句
     * @param clazz         返回对象class
     * @param underlineFlag 参数名是否驼峰转下划线
     * @param params        可变参数
     * @param <T>           具体操作的实体类
     */
    public <T> List<T> selectList(String sql, Class<T> clazz, boolean underlineFlag, Object... params)
            throws Exception {
        List<T> resultList = null;
        try {
            //执行SQL获取结果
            resultSet = commonStep(sql, params);
            //获取指定字节码信息
            BeanInfo beanInfo = Introspector.getBeanInfo(clazz, Object.class);
            //获取所有属性描述器
            PropertyDescriptor[] pds = beanInfo.getPropertyDescriptors();
            resultList = new ArrayList<>();
            Object value = null;
            while (resultSet.next()) {
                T obj = clazz.newInstance();
                for (PropertyDescriptor pd : pds) {
                    String name = pd.getName();
                    if (underlineFlag) {
                        //驼峰转下划线
                        name = CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, pd.getName());
                    }
                    //获取结果集中对应字段名的值
                    value = resultSet.getObject(name);
                    //执行当前方法并传入参数
                    pd.getWriteMethod().invoke(obj, value);
                }
                resultList.add(obj);
            }
        } finally {
            //关闭连接
            closeAll();
        }
        //处理结果集
        return resultList;
    }

    /**
     * 查询返回集合
     *
     * @param sql    sql语句
     * @param params 执行参数
     * @return
     * @throws Exception
     */
    public List<Map<String, Object>> selectPage(String sql, int page, int pageSize, Object... params) throws SQLException {
        //是否存在Limit校验
        if (sql.toUpperCase().contains(LIMIT)) {
            throw new RuntimeException("selectPage method cannot need add limit keyword ");
        }
        //SQL 拼接Limit
        sql = sql + LIMIT_SUFFIX;
        List<Map<String, Object>> list = null;
        try {
            list = new ArrayList<>(pageSize);
            //获取数据库连接对象
            connection = getConnection();
            preparedStatement = connection.prepareStatement(sql);
            int index = 1;
            //给预编译语句赋值
            if (params != null) {
                //有其它参数
                for (Object param : params) {
                    preparedStatement.setObject(index++, param);
                }
                preparedStatement.setObject(index++, PageUtils.getStartRow(page, pageSize));
                preparedStatement.setObject(index, pageSize);
            } else {
                //无其他参数
                preparedStatement.setObject(1, PageUtils.getStartRow(page, pageSize));
                preparedStatement.setObject(2, pageSize);
            }
            resultSet = preparedStatement.executeQuery();
            ResultSetMetaData metaData = resultSet.getMetaData();
            int colsLen = metaData.getColumnCount();
            Map<String, Object> map = null;
            while (resultSet.next()) {
                map = new HashMap<>();
                for (int i = 0; i < colsLen; i++) {
                    String colsName = metaData.getColumnName(i + 1);
                    map.put(colsName, resultSet.getObject(colsName));
                }
                list.add(map);
            }
        } finally {
            //关闭连接
            closeAll();
        }
        return list;
    }


    /**
     * 执行公共步骤
     *
     * @return Connection连接对象
     */
    private ResultSet commonStep(String sql, Object... params) throws SQLException {
        //1.获取数据库连接对象
        connection = getConnection();
        //2.获取预编译语句对象
        preparedStatement = connection.prepareStatement(sql);
        int index = 1;
        //3.给预编译语句赋值
        if (params != null) {
            for (Object param : params) {
                preparedStatement.setObject(index++, param);
            }
        }

        log.debug("{}", preparedStatement.toString());
        //4.执行SQL语句获取结果集
        return preparedStatement.executeQuery();
    }

    /**
     * 释放资源
     */
    private void closeAll() {
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                throw new RuntimeException(" close resultSet exception ！", e);
            }
        }
        //关闭通道
        if (preparedStatement != null) {
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                throw new RuntimeException(" close preparedStatement exception ！", e);

            }
        }
        //关闭连接
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                throw new RuntimeException(" close connection exception ！", e);

            }
        }
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}