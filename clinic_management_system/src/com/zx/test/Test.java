package com.zx.test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import com.zx.util.C3P0Utils;
import com.zx.util.RealseResource;
import com.zx.view.MainFrame;
public class Test {

    void connectionTest() throws SQLException{
        Connection conn = C3P0Utils.getConnection();
        String sql = "select prostock from project where pro_id = 'p1002'";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        ResultSet resultSet  = pstmt.executeQuery();
        if(resultSet.next()) {
            System.out.println(resultSet.getObject(1));
        }
        RealseResource.release(conn, pstmt, resultSet);
    }
    public static void main(String[] args) throws SQLException {
        
        getconnection();
        
    }
    public static void getconnection() throws SQLException {
        Connection conn  = C3P0Utils.getConnection();
        System.out.println(conn.getAutoCommit());
        conn.setAutoCommit(false);
        System.out.println(conn.getAutoCommit());
    }
    
}


