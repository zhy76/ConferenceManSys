package com.atguigu5.dao;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.*;
import java.util.List;

/**
 * @Description 定义一个用来被继承的对数据库进行基本操作的Dao
 * @Author 谢 娇
 * @Date 2020/11/29 13:26
 * @sno 6109118015
 */

public abstract class BaseDAO<T> {

    private QueryRunner queryRunner = new QueryRunner();
    // 定义一个变量来接收泛型的类型
    private Class<T> type;
    // 获取T的Class对象，获取泛型的类型，泛型是在被子类继承时才确定
    public BaseDAO() {
        // 获取子类的类型
        Class clazz = this.getClass();
        // 获取父类的类型
        // getGenericSuperclass()用来获取当前类的父类的类型
        // ParameterizedType表示的是带泛型的类型
        ParameterizedType parameterizedType = (ParameterizedType) clazz.getGenericSuperclass();
        // 获取具体的泛型类型 getActualTypeArguments获取具体的泛型的类型
        // 这个方法会返回一个Type的数组
        Type[] types = parameterizedType.getActualTypeArguments();
        // 获取具体的泛型的类型·
        this.type = (Class<T>) types[0];
    }

    // @Description 通用的增删改操作(考虑事务) version 2.0  返回值为影响的行数。
    public int update(Connection connection, String sql, Object...args){
        int count = 0;
        try {
            count = queryRunner.update(connection,sql,args);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }

    // @Description 针对于不用的表的通用的查询操作 version 2.0 获取一个对象
    public  T getBean(Connection connection,String sql,Object...args){
        T t = null;
        try {
            t = queryRunner.query(connection, sql, new BeanHandler<T>(type), args);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return t;
    }

    // @Description 针对于不用的表的通用的查询操作 version 2.0 获取多个对象
    public  List<T> getBeanList(Connection connection, String sql, Object...args){
        List<T> list = null;
        try {
            list = queryRunner.query(connection, sql, new BeanListHandler<T>(type), args);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    // @Description 获取一个单一值得方法，专门用来执行像 select count(*)...这样的sql语句
    public Object getValue(Connection connection,String sql,Object...args){
        Object count = null;
        try {
            // 调用queryRunner的query方法获取一个单一的值
            count = queryRunner.query(connection,sql,new ScalarHandler(),args);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }
}
