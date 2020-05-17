package com.zx.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;


import com.sun.corba.se.spi.orbutil.fsm.Guard.Result;
import com.zx.dao.ManagementDao;
import com.zx.util.C3P0Utils;
import com.zx.util.RealseResource;
import com.zx.vo.MainVo;

/**
 * author lei
 */
public class ManagementDaoImpl implements ManagementDao {
	
	
    @Override
    public Vector selectProjectRecordByOrderID(String pat_id) {
    	Connection conn=null;
    	PreparedStatement pstmt=null;
    	ResultSet result=null;
        Vector vectors=new Vector();
       
        String sql="select ord_id, to_char(ord_date,'YYYY-MM-DD hh24:mi:ss') ord_date, ord_price, disease_type, ord_oper ,advice  from orders where pat_id=? ";
        
        conn=C3P0Utils.getConnection();
        try {
        	conn=C3P0Utils.getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, pat_id);
			result=pstmt.executeQuery();
			while(result.next()) {
				Vector vector=new Vector();
				vector.add(result.getString("ord_id"));
				vector.add(result.getTimestamp("ord_date"));
//				System.out.println(result.getDate("date"));
				
				vector.add(result.getDouble("ord_price"));
				vector.add(result.getString("disease_type"));
				vector.add(result.getString("ord_oper"));
				vector.add(result.getString("advice"));
				vectors.add(vector);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			RealseResource.release(null, pstmt, result);
		}
        return vectors;
    }

    

}
