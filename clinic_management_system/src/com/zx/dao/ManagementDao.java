package com.zx.dao;

import java.util.Vector;

import com.zx.vo.MainVo;

/**
 * 
 * @author lei
 *
 */
public interface ManagementDao {
    
    /**
     * 通过单号id查询出项目清单记录，以Vector集合存储
     * @param orderID
     * @return
     */
    Vector<MainVo> selectProjectRecordByOrderID(String orderID);
  
    
}
