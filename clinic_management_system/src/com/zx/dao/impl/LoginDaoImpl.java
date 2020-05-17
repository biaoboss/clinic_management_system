package com.zx.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.zx.dao.LoginDao;
import com.zx.util.C3P0Utils;
import com.zx.util.DBSql;
import com.zx.util.RealseResource;
import com.zx.vo.AccountVo;



/**
 * 
 * @author biao boss 
 *
 */
public class LoginDaoImpl implements LoginDao {

    public AccountVo findAccount(String userName, String password) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        AccountVo account = null;
        try {
            conn = C3P0Utils.getConnection();
            pstmt = conn.prepareStatement(DBSql.FIND_ACCOUNT);
            pstmt.setString(1, userName);
            pstmt.setString(2, password);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                account =new AccountVo();
                account.setUserName(rs.getString("account_username"));
                account.setPassword(rs.getString("account_password"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 释放资源
            RealseResource.release(null, pstmt, rs);
        }

        return account;
    }

    public boolean IsUserNameExsist(String userName) {
        boolean flag = false;
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = C3P0Utils.getConnection();
            pstmt = conn.prepareStatement(DBSql.FIND_ACCOUNT_USERNAME);
            pstmt.setString(1, userName);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                flag = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            RealseResource.release(null, pstmt, rs);
        }
        return flag;
    }

    public int InsertAccount(AccountVo account) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        int count = 0;
        try {
            conn = C3P0Utils.getConnection();
            if (!this.IsUserNameExsist(account.getUserName())) {
                pstmt = conn.prepareStatement(DBSql.INSERT_ACCOUNT);
                pstmt.setString(1, account.getUserName());
                pstmt.setString(2, account.getPassword());
                count = pstmt.executeUpdate();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            RealseResource.release(null, pstmt, null);
        }
        return count;
    }

}
