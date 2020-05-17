package com.zx.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import com.zx.dao.OddProjectTableDao;
import com.zx.util.C3P0Utils;
import com.zx.util.RealseResource;

/**
 * 
 * @author biao boss
 *
 */
public class OddProjectTableDaoImpl implements OddProjectTableDao {

    @Override
    public Vector selectInfoByOddProject(String orderId) {
        Connection conn = C3P0Utils.getConnection();
        PreparedStatement pstmt = null;
        ResultSet resultSet = null;

        Vector datas = new Vector();
        try {
            String sql = "select p.pro_id p_id, p.pro_name p_name, p.pro_type p_type, p.units p_units, p.price p_price, \r\n"
                    + "       op.pro_amount op_amount , op.all_price op_allprice, p.pro_size p_size \r\n"
                    + "from  project p ,odd_project op\r\n" + "where p.pro_id = op.pro_id and op.odd_id = ? ";

            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, orderId);
            resultSet = pstmt.executeQuery();
            while (resultSet.next()) {
                Vector data = new Vector();
                data.add(resultSet.getString("p_id"));
                data.add(resultSet.getString("p_name"));
                data.add(resultSet.getString("p_type"));
                data.add(resultSet.getString("p_units"));
                data.add(resultSet.getDouble("p_price"));
                data.add(resultSet.getInt("op_amount"));
                data.add(resultSet.getDouble("op_allprice"));
                data.add(resultSet.getString("p_size"));
                datas.add(data);
            }
            RealseResource.release(null, pstmt, resultSet);
            return datas;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            RealseResource.release(null, pstmt, resultSet);
        }

        return null;
    }

    @Override
    public boolean deletePatientOrderRecord(String orderId) {

        Connection conn = C3P0Utils.getConnection();
        try {
            conn.setAutoCommit(false);
            this.deleteOddProtableById(conn, orderId);
            this.deleteOrdertableByid(conn, orderId);
        } catch (SQLException e) {
            try {
                if (conn != null) {
                    conn.rollback();
                }
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        } finally {
            try {
                conn.commit();
                return true;
            } catch (SQLException e) {
                e.printStackTrace();
            }finally {                
            }
        }

        return false;
    }

    @Override
    public int deleteOddProtableById(Connection conn, String orderId) throws SQLException {
        int row = 0;
        String sql = "delete from odd_project where odd_id = ? ";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, orderId);
        row = pstmt.executeUpdate();
        RealseResource.release(null, pstmt, null);
        return row;
    }

    @Override
    public int deleteOrdertableByid(Connection conn, String orderId) throws SQLException {
        int row = 0;
        String sql ="delete from orders where ord_id = ? ";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, orderId);
        row = pstmt.executeUpdate();
        RealseResource.release(null, pstmt, null);
        return row;
    }
    

}
