package com.atguigu1.transaction;

import com.atguigu1.util.JDBCUtils;
import org.junit.Test;

import java.sql.Connection;

/**
 * @Description
 * @Author 谢 娇
 * @Date 2020/11/28 17:28
 * @sno 6109118015
 */

public class ConnectionTest {

    @Test
    public void testGetConnection() throws Exception {
        Connection connection = JDBCUtils.getConnection();
        System.out.println(connection);
    }
}
