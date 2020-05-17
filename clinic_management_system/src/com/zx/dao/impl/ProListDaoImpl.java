package com.zx.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.JOptionPane;

import com.zx.dao.ProListDao;
import com.zx.util.C3P0Utils;
import com.zx.util.ConnectionBean;
import com.zx.util.RealseResource;
import com.zx.vo.ProListVo;

/**
 * 
 * @author 彭文大帅比
 *
 */
public class ProListDaoImpl implements ProListDao {

    /*
     * C3P0Utils util = null;
     * 
     * public ProListDaoImpl() { util = new C3P0Utils(); }
     */

    // 单例连接对象
    private Connection con = ConnectionBean.getConnection();

//	获取项目清单
    public Vector getProList() {
        Vector list = new Vector();
        Vector pro;
        String sql = "select * from project";

//		Connection con = C3P0Utils.getConnection();
        PreparedStatement pstmt = null;
        ResultSet set = null;

        try {
            pstmt = con.prepareStatement(sql);
            set = pstmt.executeQuery();
            while (set.next()) {
                pro = new Vector();
                pro.add(set.getString("pro_id"));
                pro.add(set.getString("pro_name"));
                pro.add(set.getString("units"));
                pro.add(set.getString("pro_size"));
                pro.add(set.getDouble("price"));
                pro.add(set.getString("pro_type"));
                pro.add(set.getObject("prostock"));
                list.add(pro);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

        return list;
    }

//	通过id查询项目清单
    public void addByProId(String id, int acount) {
        String sql = "select * from project where pro_id = ?";

//		Connection con = C3P0Utils.getConnection();
        PreparedStatement pstmt = null;
        ResultSet set = null;

        try {
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, id);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

//	通过项目id或者项目名称模糊查询
    public Vector findProLikeProIdOrName(String pro_name) {
        Vector list = new Vector();
        Vector pro;
        String sql = " select * from project p where p.pro_id like ? or p.pro_name like ?";

//		Connection con = C3P0Utils.getConnection();
        PreparedStatement pstmt = null;
        ResultSet set = null;

        try {
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, "%" + pro_name + "%");
            pstmt.setString(2, "%" + pro_name + "%");
            set = pstmt.executeQuery();
            while (set.next()) {
                pro = new Vector();
                pro.add(set.getString("pro_id"));
                pro.add(set.getString("pro_name"));
                pro.add(set.getString("units"));
                pro.add(set.getString("pro_size"));
                pro.add(set.getDouble("price"));
                pro.add(set.getString("pro_type"));
                pro.add(set.getObject("prostock"));

                list.add(pro);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return list;
    }

    @Override
    public int updateStockById(String id, int stock) {
        int row = 0;
        String sql = "update project set prostock = ? where pro_id = ? ";
        try {
            PreparedStatement pstmt = con.prepareStatement(sql);

            pstmt.setInt(1, stock);
            pstmt.setString(2, id);
            row = pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return row;
    }

    @Override
    public Object getStockById(String id) {
        String sql = "select prostock from project where pro_id = ?";
        Object stock = null;
        try {
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setString(1, id);
            ResultSet reuResultSet = pstmt.executeQuery();
            if (reuResultSet.next()) {
                stock = reuResultSet.getObject("prostock");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return stock;
    }

    @Override
    public int insertOrderTable(ProListVo proListVo) {
        String sql = "insert into orders(ord_id,ord_price,disease_type,ord_oper,advice,ord_date,pat_id) "
                + "values(?,?,?,?,?,to_date(?,'YYYY-MM-dd HH24:MI:SS'),?)";
        int row = 0;
        PreparedStatement pstmt =null;
        try {
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, proListVo.getOrderId());
            pstmt.setDouble(2, proListVo.getAllPrice());
            pstmt.setString(3, proListVo.getDiseaseType());
            pstmt.setString(4, proListVo.getUsername());
            pstmt.setString(5, proListVo.getAdvice());
            pstmt.setString(6, proListVo.getOrderDate());
            pstmt.setString(7, proListVo.getPatientId());
            row = pstmt.executeUpdate();
            
            
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            RealseResource.release(null, pstmt, null);
        }
        
        
        return row;
    }

    @Override
    public int insertOddproject(ProListVo proListVo) {
        String sql = "insert into odd_project(pro_id,odd_id,pro_amount,all_price) values(?,?,?,?)";
        int row = 0;
        PreparedStatement pstmt = null;
        try {
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, proListVo.getPro_id());
            pstmt.setString(2, proListVo.getOrderId());
            pstmt.setInt(3, proListVo.getProjectAmount());
            pstmt.setDouble(4, proListVo.getProjectPrice());
           
            row = pstmt.executeUpdate();
            
            
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            RealseResource.release(null, pstmt, null);
        }
        
        
        return row;
    }

}
