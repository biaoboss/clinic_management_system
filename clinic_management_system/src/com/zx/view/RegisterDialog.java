package com.zx.view;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.GridBagLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.zx.control.RegisterAction;
import com.zx.util.GBC;


/**
 * 
 * @author biao boss
 * 
 */
public class RegisterDialog extends JDialog{
    
    private JLabel userLabel, pwdLabel;
    private JTextField userText;
    private JPasswordField pwdText;
    private JButton registerButton, cancelButton;
    
    public RegisterDialog() {
        this.setTitle("账户注册");
        this.setSize(250, 200);
        this.setResizable(false);
        this.setLocationRelativeTo(this);
        this.setModal(true);
        this.setLayout(new BorderLayout());
        this.add(buildAccountPanel(),BorderLayout.CENTER);
        this.add(buildButtonPanel(),BorderLayout.SOUTH);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }
    
    /**
     * 用户名·密码文本框面板
     * @return
     */
    private JPanel buildAccountPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        panel.add(buildUserLabel(), new GBC(0, 0).setInsets(5,15));
        panel.add(buildUserText(),new GBC(1,0).setInsets(5,15));
        panel.add(buildPwdLabel(),new GBC(0, 1));
        panel.add(buildPwdField(),new GBC(1, 1));
        return panel;
    }
    /**
     * 注册·取消 按钮面板
     * @return
     */
    private JPanel buildButtonPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        panel.add(buildRegisterButton(),new GBC(1, 1).setInsets(10,20));
        panel.add(buildCancelButton(),new GBC(2, 1).setInsets(10,20));
        return panel;
    }
    public JLabel buildUserLabel() {
        if (userLabel == null) {
            userLabel = new JLabel("用户名");
        }
        return userLabel;
    }

    public JLabel buildPwdLabel() {
        if (pwdLabel == null) {
            pwdLabel = new JLabel("密码");
        }
        return pwdLabel;
    }

    public JTextField buildUserText() {
        if (userText == null) {
            userText = new JTextField(10);
        }
        return userText;
    }

    public JPasswordField buildPwdField() {
        if (pwdText == null) {
            pwdText = new JPasswordField(10);
        }
        return pwdText;
    }
    public JButton buildRegisterButton() {
        if (registerButton == null) {
            registerButton = new JButton("注册");
            registerButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            registerButton.addActionListener(new RegisterAction(this));
        }
        return registerButton;
    }

    public JButton buildCancelButton() {
        if (cancelButton == null) {
            cancelButton = new JButton("取消");
            cancelButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            cancelButton.addActionListener(new RegisterAction(this));
        }
        return cancelButton;
    }
    
    public static void main(String[] args) {
        new RegisterDialog().setVisible(true);
    }
}
