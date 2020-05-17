package com.zx.util;

import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionBean {

    private static Connection conn;

    public static Connection getConnection() {
        if (conn == null) {
            conn = C3P0Utils.getConnection();
        }
        //设置手动提交
        try {
            if(conn.getAutoCommit()) {
                conn.setAutoCommit(false);
            }
        } catch (SQLException e) {
            try {
                conn.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        }

       
        return conn;
    }
    
    public static void releaseConnection() {
        try {
            if(conn!= null && !conn.isClosed()) {
                conn.close();
                conn =null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public static void main(String[] args) {
        Connection c1 = ConnectionBean.getConnection();
        
        Connection c2 = ConnectionBean.getConnection();
        System.out.println(c1 == c2);
        
    }
}
