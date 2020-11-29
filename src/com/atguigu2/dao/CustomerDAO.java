package com.atguigu2.dao;

import com.atguigu2.bean.Customer;

import java.sql.Connection;
import java.sql.Date;
import java.util.List;

/**
 * @Description 此接口用来规范对于customers表的常用操作
 * @Author 谢 娇
 * @Date 2020/11/29 13:45
 * @sno 6109118015
 */

public interface CustomerDAO {
        //将customer对象添加到数据库中；
        void insert(Connection connection, Customer customer);
        //针对指定id，删除表中的一条记录；
        void deleteById(Connection connection,int id);
        //针对内存中的customer对象，修改数据表中指定的记录；
        void update(Connection connection, Customer customer);
        //针对指定id，查询得到对应的Customer对象；
        Customer getCustomerById(Connection connection,int id);
        //查询表中所有记录构成的Customer对象集合；
        List<Customer> getAll(Connection connection);
        //返回数据表中的数据额条目数；
        Long getCount(Connection connection);
        //返回数据表中的最大生日数；
        Date getMaxBirth(Connection connection);
}
