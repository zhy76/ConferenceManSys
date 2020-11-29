package com.atguigu5.test;

import com.atguigu5.bean.Customer;
import com.atguigu5.dao.CustomerDAOImpl;
import com.atguigu5.utils.JDBCUtils;
import org.junit.Test;

import java.sql.Connection;
import java.sql.Date;
import java.util.List;

/**
 * @Description
 * @Author 谢 娇
 * @Date 2020/11/29 14:16
 * @sno 6109118015
 */

public class CustomerDAOImplTest {
    private CustomerDAOImpl dao = new CustomerDAOImpl();
    @Test
    public void testInsert()  {
        Connection connection = null;
        try {
            connection = JDBCUtils.getConnection();
            Customer customer = new Customer(1, "凌宸", "lingchen@qq.com", new Date(121213213213232L));
            dao.insert(connection,customer);
            System.out.println("添加成功");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResource(connection,null);
        }
    }
    @Test
    public void testDeleteById(){
        Connection connection = null;
        try {
            connection = JDBCUtils.getConnection();
            dao.deleteById(connection,12);
            System.out.println("删除成功");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResource(connection,null);
        }
    }
    @Test
    public void testUpdate(){
        Connection connection = null;
        try {
            connection = JDBCUtils.getConnection();
            Customer customer = new Customer(18,"哈哈","haha@126.com",new Date(126272321319L));
            dao.update(connection,customer);
            System.out.println("修改成功");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResource(connection,null);
        }
    }
    @Test
    public void testGetCustomerById(){
        Connection connection = null;
        try {
            connection = JDBCUtils.getConnection();
            Customer customer = dao.getCustomerById(connection, 25);
            System.out.println(customer);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResource(connection,null);
        }
    }

    @Test
    public void testGetAll(){
        Connection connection = null;
        try {
            connection = JDBCUtils.getConnection();
            List<Customer> all = dao.getAll(connection);
            all.forEach(System.out::println);
            System.out.println();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResource(connection,null);
        }
    }
    @Test
    public void testGetCount(){
        Connection connection = null;
        try {
            connection = JDBCUtils.getConnection();
            Long count = dao.getCount(connection);
            System.out.println(count);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResource(connection,null);
        }
    }

    @Test
    public void testGetMaxBirth(){
        Connection connection = null;
        try {
            connection = JDBCUtils.getConnection();
            Date maxBirth = dao.getMaxBirth(connection);
            System.out.println(maxBirth);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResource(connection,null);
        }
    }
}
