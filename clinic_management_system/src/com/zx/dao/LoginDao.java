package com.zx.dao;

import com.zx.vo.AccountVo;

/**
 * 
 * @author biao boss 
 *
 */
public interface LoginDao {
    /**
     * 查找账户
     * @param userName
     * @param password
     * @return
     */
    public AccountVo findAccount(String userName, String password);
    /**
     * 查找用户名是否存在
     * @param userName
     * @return
     */
    public boolean IsUserNameExsist(String userName);
    
    /**
     * 添加账户
     * @param account   添加的数据所在的类
     * @return  添加数据影响的行数
     */
    public int InsertAccount(AccountVo account);
}
