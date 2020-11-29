package com.atguigu5.test;

import com.atguigu2.bean.Customer;
import com.atguigu4.utils.JDBCUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.*;
import org.junit.Test;

import java.sql.Connection;
import java.sql.Date;
import java.util.List;
import java.util.Map;

/**
 * @Description
 * @Author 谢 娇
 * @Date 2020/11/29 16:51
 * @sno 6109118015
 */

public class QueryRunnerTest {
    //测试插入
    @Test
    public void testInsert(){
        Connection connection = null;
        try {
            connection = JDBCUtils.getConnection();
            QueryRunner runner = new QueryRunner();
            String sql = "insert into customers(name,email,birth)values(?,?,?)";
            int insertCount = runner.update(connection, sql, "库里", "Curry@qq.com", "1989-10-19");
            System.out.println("添加了"+insertCount+"条记录");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResource(connection,null);
        }
    }
    //测试查询
    /*
     * BeanHandler:是 ResultSetHandler 接口的实现类，用于封装一条记录
     **/
    @Test
    public void testQuery1() {
        Connection connection = null;
        try {
            connection = JDBCUtils.getConnection();
            QueryRunner runner = new QueryRunner();
            String sql = "select id,name,email,birth from customers where id = ?";
            BeanHandler<Customer> handler = new BeanHandler<>(Customer.class);
            Customer customer = runner.query(connection, sql, 25, handler);
            System.out.println(customer);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResource(connection,null);
        }
    }


    //测试查询
    /*
     * BeanListHandler:是 ResultSetHandler 接口的实现类，用于封装多条记录构成的集合
     **/
    @Test
    public void testQuery2(){
        Connection connection = null;
        try {
            connection = JDBCUtils.getConnection();
            QueryRunner runner = new QueryRunner();
            String sql = "select id,name,email,birth from customers where id < ?";
            BeanListHandler<Customer> handler = new BeanListHandler<>(Customer.class);
            List<Customer> list =  runner.query(connection, sql, 25, handler);
            list.forEach(System.out::println);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResource(connection,null);
        }

    }

    //测试查询
    /*
     * MapHandler:是 ResultSetHandler 接口的实现类，对应表中的一条记录
     * 将字段和对应字段值作为map中的key和value
     **/
    @Test
    public void testQuery3(){
        Connection connection = null;
        try {
            connection = JDBCUtils.getConnection();
            QueryRunner runner = new QueryRunner();
            String sql = "select id,name,email,birth from customers where id = ?";
            MapHandler handler = new MapHandler();
            Map<String, Object> map = runner.query(connection, sql, 25, handler);
            System.out.println(map);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResource(connection,null);
        }

    }

    //测试查询
    /*
     * MapListHandler:是 ResultSetHandler 接口的实现类，对应表中的多条记录
     * 对于每一条记录：字段和对应字段值作为map中的key和value
     **/
    @Test
    public void testQuery4(){
        Connection connection = null;
        try {
            connection = JDBCUtils.getConnection();
            QueryRunner runner = new QueryRunner();
            String sql = "select id,name,email,birth from customers where id < ?";
            MapListHandler handler = new MapListHandler();
            List<Map<String, Object>> list = runner.query(connection, sql, 25, handler);
            list.forEach(System.out::println);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResource(connection,null);
        }

    }
    //测试查询
    /*
     * ScalarHandler:是 ResultSetHandler 接口的实现类，用于查询特殊值。
     **/
    @Test
    public void testQuery5(){
        Connection connection = null;
        try {
            connection = JDBCUtils.getConnection();
            QueryRunner runner = new QueryRunner();
            String sql = "select count(*) from customers  ";
            ScalarHandler handler = new ScalarHandler();
            Long count = (Long) runner.query(connection, sql, handler);
            System.out.println(count);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResource(connection,null);
        }

    }
    @Test
    public void testQuery6(){
        Connection connection = null;
        try {
            connection = JDBCUtils.getConnection();
            QueryRunner runner = new QueryRunner();
            String sql = "select max(birth) from customers  ";
            ScalarHandler handler = new ScalarHandler();
            Date maxBirth = (Date) runner.query(connection, sql, handler);
            System.out.println(maxBirth);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResource(connection,null);
        }

    }
}
