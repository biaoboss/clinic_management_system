package com.zx.util;

import java.sql.Connection;
import java.sql.SQLException;

import com.mchange.v2.c3p0.ComboPooledDataSource;
/**
 * C3P0数据库连接池
 * 
 * @author 
 * 
 */

public class C3P0Utils {
    private static ComboPooledDataSource dataSource = new ComboPooledDataSource();
    
    private static Connection conn =null;
    
    public static ComboPooledDataSource getDataSource() {
        return C3P0Utils.dataSource;
    }
    /**
     * 获取数据库连接对象
     * @return
     */
//    public static Connection getConnection() {
//        try {
//            return dataSource.getConnection();
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//    }
    public static Connection getConnection() {
        try {
            if(conn==null || conn.isClosed()) {
                conn = dataSource.getConnection();
            }
            conn.setAutoCommit(true);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }
}
