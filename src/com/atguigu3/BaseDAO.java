package com.atguigu3;

import com.atguigu1.util.JDBCUtils;

import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description
 * @Author 谢 娇
 * @Date 2020/11/29 13:26
 * @sno 6109118015
 */

public abstract class BaseDAO {

    //通用的增删改操作(考虑事务) version 2.0
    public int update(Connection connection, String sql, Object...args){

        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(sql);
            for (int i = 0; i < args.length; i++) {
                preparedStatement.setObject(i+1,args[i]);
            }
            return preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResource(null,preparedStatement);
        }
        return 0;
    }

    // @Description 针对于不用的表的通用的查询操作 version 2.0
    public <T> T getInstance(Connection connection,Class <T> clazz,String sql,Object...args){
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            //1.获取连接
            connection = JDBCUtils.getConnection();
            //2.预编译SQL语句
            preparedStatement = connection.prepareStatement(sql);
            //3.填充占位符
            for (int i = 0; i < args.length; i++) {
                preparedStatement.setObject(i+1,args[i]);
            }
            //4.获取结果集 resultSet
            resultSet = preparedStatement.executeQuery();//executeQuery()
            //获取结果集的元数据 ResultSetMetaData
            ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
            // 通过ResultSetMetaData获取结果集中的列数
            int columnCount = resultSetMetaData.getColumnCount();
            if(resultSet.next()){
                //创建某个对象示例t
                T t = clazz.newInstance();
                // 处理结果集一行数据中的每一个列
                for (int i = 0; i < columnCount; i++) {
                    //获取第 i+1 列的值
                    Object columnValue = resultSet.getObject(i + 1);
                    //获取列的别名getColumnLabel();列没有别名时，获取的是列名
                    String columnLabel = resultSetMetaData.getColumnLabel(i + 1);
                    // 给t对象指定的columnName属性，赋值为columValue：通过反射
                    Field field = clazz.getDeclaredField(columnLabel);
                    field.setAccessible(true);
                    field.set(t,columnValue);
                }
                return t;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResource(null,preparedStatement,resultSet);
        }
        return null;
    }
    // @Description 针对于不用的表的通用的查询操作 version 2.0 返回多条记录
    public <T> List<T> getForList(Connection connection,Class <T> clazz, String sql, Object...args){
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = connection.prepareStatement(sql);
            for (int i = 0; i < args.length; i++) {
                preparedStatement.setObject(i+1,args[i]);
            }

            resultSet = preparedStatement.executeQuery();

            ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
            int columnCount = resultSetMetaData.getColumnCount();
            //创建集合对象
            ArrayList<T> list = new ArrayList<>();
            while(resultSet.next()){
                T t = clazz.newInstance();
                // 处理结果集一行数据中的每一个列,给t对象指定的属性赋值
                for (int i = 0; i < columnCount; i++) {
                    Object columnValue = resultSet.getObject(i + 1);
                    //获取列的别名getColumnLabel();
                    String columnLabel = resultSetMetaData.getColumnLabel(i + 1);
                    // 给t对象指定的columnName属性，赋值为columValue：通过反射
                    Field field = clazz.getDeclaredField(columnLabel);
                    field.setAccessible(true);
                    field.set(t,columnValue);
                }
                list.add(t);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResource(null,preparedStatement,resultSet);
        }
        return null;
    }
    //用于查询特殊值的通用方法
    public <E> E getValue(Connection connection,String sql,Object...args){
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(sql);
            for (int i = 0; i < args.length; i++) {
                preparedStatement.setObject(i+1,args[i]);
            }
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                return (E) resultSet.getObject(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResource(null,preparedStatement);
        }
        return null;
    }
}
