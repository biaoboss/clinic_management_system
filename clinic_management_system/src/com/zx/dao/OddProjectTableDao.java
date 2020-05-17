package com.zx.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Vector;

/**
 * 
 * @author biao boss 
 *
 */
public interface OddProjectTableDao {

    /**
     * 根据订单号获取项目清单及消费记录的信息
     * @param orderId
     * @return
     */
    public Vector selectInfoByOddProject(String orderId);
    
    /**
     * 根据订单号删除 订单记录 及消费记录表中的记录
     * @return
     */
    public boolean deletePatientOrderRecord(String orderId);
    
    /**
     * 根据订单号删除消费记录表中的记录
     * @param conn
     * @param orderId
     * @return
     * @throws SQLException
     */
    public int deleteOddProtableById(Connection conn, String orderId)throws SQLException;
    
    /**
     * 根据订单删除订单表中的记录
     * @param conn
     * @param orderId
     * @return
     * @throws SQLException
     */
    public int deleteOrdertableByid(Connection conn, String orderId)throws SQLException;
    
 
    
}
