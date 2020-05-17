package com.zx.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 
 * @author biao boss
 *
 */
public class RealseResource {
    /**
     * 释放资源
     * 
     * @param conn
     * @param pstmt
     * @param result
     */
    public static void release(Connection conn, PreparedStatement pstmt, ResultSet result) {
        try {
            if (result != null) {
                result.close();
                result = null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (pstmt != null) {
                    pstmt.close();
                    pstmt = null;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (conn != null) {
                        conn.close();
                        conn = null;
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

        }

    }

}
