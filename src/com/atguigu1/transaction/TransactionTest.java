package com.atguigu1.transaction;

import com.atguigu1.util.JDBCUtils;
import org.junit.Test;

import java.lang.reflect.Field;
import java.sql.*;

/**
 * @Description
 * @Author 谢 娇
 * @Date 2020/11/28 17:40
 * @sno 6109118015
 */

public class TransactionTest {
    //*************************未考虑数据库事务的转账操作****************************
    /*
     * @Author 谢娇
     * @Description 针对数据表user_table来说，AA用户给BB用户转账100
     * update user_table set balance = balance - 100 where user = 'AA';
     * update user_table set balance = balance + 100 where user = 'BB';
     * @return
     **/
    @Test
    public void testUpdate(){
        String sql1 = "update user_table set balance = balance - 100 where user = ?";
        update(sql1,"AA");
        // 模拟网路异常
        System.out.println(10/0);
        String sql2 = "update user_table set balance = balance + 100 where user = ?";
        update(sql2,"BB");

        System.out.println("转账成功！");
    }

    //通用的增删改操作(未考虑事务）
    public int update(String sql,Object...args){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = JDBCUtils.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            for (int i = 0; i < args.length; i++) {
                preparedStatement.setObject(i+1,args[i]);
            }
            return preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResource(connection,preparedStatement);
        }
        return 0;
    }


    //*************************考虑数据库事务后的转账操作****************************
    @Test
    public void testUpdateWithTransaction()  {
        Connection connection = null;
        try {
            connection = JDBCUtils.getConnection();
            //取消数据的自动提交功能
            connection.setAutoCommit(false);
            String sql1 = "update user_table set balance = balance - 100 where user = ?";
            update(connection,sql1,"AA");
            // 模拟网路异常
            System.out.println(10/0);
            String sql2 = "update user_table set balance = balance + 100 where user = ?";
            update(connection,sql2,"BB");

            System.out.println("转账成功！");

            connection.commit();
        } catch (Exception e) {
            e.printStackTrace();
            try {
                connection.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            JDBCUtils.closeResource(connection,null);
        }

    }
    /* @Author 谢娇
     * @Description
     * @return
     **/
    //通用的增删改操作(考虑事务)
    public int update(Connection connection,String sql,Object...args){

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



    //*************************考虑数据库事务后的转账操作****************************
     @Test
     public void testTransactionSelect() throws Exception{
         Connection connection = JDBCUtils.getConnection();
         //获取当前连接的隔离级别
         System.out.println(connection.getTransactionIsolation());
         //设置数据库的隔离级别
         connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);

         connection.setAutoCommit(false);
         String sql = "select user,password,balance from user_table where user = ?";
         User user = getInstance(connection, User.class, sql, "CC");
         System.out.println(user);
     }
     @Test
     public void testTransactionUpdate() throws Exception{
         Connection connection = JDBCUtils.getConnection();
         connection.setAutoCommit(false);
         String sql = "update user_table set balance = ? where user = ?";
         update(connection,sql,5000,"CC");
    }
    /*
     * @Author 谢娇
     * @Description 针对于不用的表的通用的查询操作
     * @version 2.0
     **/
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
}
