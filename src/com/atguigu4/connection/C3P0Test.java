package com.atguigu4.connection;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.junit.Test;

import java.beans.PropertyVetoException;
import java.sql.Connection;

/**
 * @Description
 * @Author 谢 娇
 * @Date 2020/11/29 15:43
 * @sno 6109118015
 */

public class C3P0Test {
    //方式一：
    @Test
    public void testGetConnection() throws  Exception {
        ComboPooledDataSource cpds = new ComboPooledDataSource();
        cpds.setDriverClass( "com.mysql.cj.jdbc.Driver" ); //loads the jdbc driver
        cpds.setJdbcUrl( "jdbc:mysql://localhost:3306/test?&useSSL=false&serverTimezone=Asia/Shanghai&rewriteBatchedStatements=true" );
        cpds.setUser("root");
        cpds.setPassword("lingchen0522");
        //通过设置相关的参数，对数据库连接池进行管理
        //设置初始时数据路连接池中的连接数
        cpds.setInitialPoolSize(10);
        //设置初始时数据路连接池中的连接数
        Connection connection = cpds.getConnection();
        System.out.println(connection);


    }




}
