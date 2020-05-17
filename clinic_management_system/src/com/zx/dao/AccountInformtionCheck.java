package com.zx.dao;

/**
 * 
 * @author biao boss 
 *
 */
public interface AccountInformtionCheck {
    /**
     * 判断填写的信息是否为空
     * @param userName
     * @param password
     * @return
     */
    public boolean isAccountInformationEmpty(String userName, String password) ;
}
