package com.atguigu1.util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

/**
 * @Description
 * @Author 谢 娇
 * @Date 2020/11/28 17:12
 * @sno 6109118015
 */

public class JDBCUtils {

    public static Connection getConnection() throws Exception {
            //1.获取配置文件
        InputStream inputStream = ClassLoader.getSystemClassLoader().getResourceAsStream("jdbc.properties");
        Properties properties = new Properties();
        properties.load(inputStream);

        //2.读取连接的的四个参数
        String user = properties.getProperty("user");
        String password = properties.getProperty("password");
        String url = properties.getProperty("url");
        String driverClass = properties.getProperty("driverClass");
        //3.加载驱动
        Class.forName(driverClass);
        //4.获取连接
        Connection connection = DriverManager.getConnection(url,user,password);
        return connection;
    }

    public static void closeResource(Connection connection, Statement statement){
        try {
            if(connection != null)
                connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            if(statement != null)
                statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public  static  void closeResource(Connection connection, Statement statement, ResultSet resultSet){
        closeResource(connection,statement);
        try {
            if(resultSet!=null)
                resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
