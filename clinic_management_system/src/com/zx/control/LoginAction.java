package com.zx.control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import com.zx.dao.LoginDao;
import com.zx.dao.impl.CheckAction;
import com.zx.dao.impl.LoginDaoImpl;
import com.zx.view.LoginDialog;
import com.zx.view.MainFrame;
import com.zx.view.RegisterDialog;
import com.zx.vo.AccountVo;

/**
 * 
 * @author biao boss 
 *
 */
public class LoginAction implements ActionListener {
    
    private LoginDialog loginDialog;
    public LoginAction(LoginDialog loginDialog) {
        this.loginDialog = loginDialog;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        String name = e.getActionCommand();
        if(name.equals("登录")) {
            String userName = loginDialog.buildUserText().getText();
            String pwd = String.valueOf(loginDialog.buildPwdField().getPassword());
            LoginDao lc = new LoginDaoImpl();
            CheckAction ck = new CheckAction();
            //检查用户或密码是否为空
            if(ck.isAccountInformationEmpty(userName, pwd)) {
                return ;
            };
            
            AccountVo account = lc.findAccount(userName, pwd);
            if(account != null) {
                loginDialog.dispose();
                
                new MainFrame(userName);
            }else {
                JOptionPane.showMessageDialog(null, "用户名或密码错误！");
            }
            
        }else if(name.equals("注册")) {
            new RegisterDialog().setVisible(true);
        }else if(name.equals("退出")) {
            int result = JOptionPane.showConfirmDialog(loginDialog, "确认退出?", "确认", JOptionPane.OK_CANCEL_OPTION,
                    JOptionPane.INFORMATION_MESSAGE);
            if (result == JOptionPane.OK_OPTION) {
                System.exit(0);
            }
        }
    }

}
