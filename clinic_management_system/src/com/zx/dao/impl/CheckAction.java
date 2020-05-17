package com.zx.dao.impl;

import javax.swing.JOptionPane;

import com.zx.dao.AccountInformtionCheck;




/**
 * 信息校验
 * @author  biao boss
 *
 */
public class CheckAction implements AccountInformtionCheck {
    
    @Override
    public boolean isAccountInformationEmpty(String userName, String password) {
        //用户名和密码都为空
        if(userName.equals("") && password.equals("")) {
            JOptionPane.showMessageDialog(null, "用户名和密码不能为空！");
            return true;
        }
        //用户名为空
        if(userName.equals("")) {
            JOptionPane.showMessageDialog(null, "用户名不能为空！");
            return true;
        }
        //密码为空
        if(password.equals("")) {
            JOptionPane.showMessageDialog(null, "密码不能为空！");
            return true;
        }
        //用户名和密码都不为空
        return false;
    }
    
    
}
