package com.zx.control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import com.zx.dao.AccountInformtionCheck;
import com.zx.dao.LoginDao;
import com.zx.dao.impl.CheckAction;
import com.zx.dao.impl.LoginDaoImpl;
import com.zx.view.RegisterDialog;
import com.zx.vo.AccountVo;


/**
 * 
 * @author biao boss
 *
 */
public class RegisterAction implements ActionListener {

    private RegisterDialog registerDialog;
    public RegisterAction(RegisterDialog registerDialog) {
         this.registerDialog = registerDialog;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        String name = e.getActionCommand();
        if(name.equals("注册")) {
            String userName = registerDialog.buildUserText().getText();
            String pwd = String.valueOf(registerDialog.buildPwdField().getPassword());
            AccountInformtionCheck ck = new CheckAction();
            //检查用户或密码是否为空
            if(ck.isAccountInformationEmpty(userName, pwd)) {
                return ;
            };
            
            LoginDao lc = new LoginDaoImpl();
            
            if(!lc.IsUserNameExsist(userName)) {
                AccountVo account = new AccountVo();
                account.setUserName(userName);
                account.setPassword(pwd);
                int rows =lc.InsertAccount(account);
                if(rows > 0) {
                    JOptionPane.showMessageDialog(null, "注册成功！");
                    registerDialog.dispose();
                }else{
                    JOptionPane.showMessageDialog(null, "注册失败！");
                };
            }else {
                JOptionPane.showMessageDialog(null, "用户名已存在！");
                
            }
            
        }else if(name.equals("取消")) {
            registerDialog.dispose();
        }
    }

}
