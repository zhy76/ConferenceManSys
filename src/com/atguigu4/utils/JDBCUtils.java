package com.atguigu4.utils;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import org.apache.commons.dbutils.DbUtils;

import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

/**
 * @Description
 * @Author 谢 娇
 * @Date 2020/11/29 16:34
 * @sno 6109118015
 */

public class JDBCUtils {

    /***********使用Druid数据库连接池技术**************/

    private static DataSource source;
    static{
        try {
            Properties properties = new Properties();
            InputStream inputStream = ClassLoader.getSystemClassLoader().getResourceAsStream("druid.properties");
            properties.load(inputStream);
            source = DruidDataSourceFactory.createDataSource(properties);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static Connection getConnection() throws Exception{

        Connection conn = source.getConnection();
        return conn;
    }


    public static void closeResource(Connection connection, Statement statement){
        DbUtils.closeQuietly(connection);
        DbUtils.closeQuietly(statement);
    }

    public  static  void closeResource(Connection connection, Statement statement, ResultSet resultSet) {
        DbUtils.closeQuietly(connection, statement, resultSet);
    }
}
