package com.zx.view;
import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import com.zx.control.LoginAction;
import com.zx.util.GBC;


/**
 * 登录界面
 * 
 * @author biao boss
 *
 */
public class LoginDialog extends JFrame {
    
    private JLabel userLabel, pwdLabel;
    private JTextField userText;
    private JPasswordField pwdText;
    private JButton loginButton, registerButton, exitButton;

    public LoginDialog() {
        this.setTitle("登录...");
        this.setSize(480, 280);
        this.setLocationRelativeTo(this);
        ImageIcon mainIcon = new ImageIcon("./img/clinic.png");
        this.setIconImage(mainIcon.getImage());
        this.setResizable(false);
        this.setLayout(new BorderLayout());
        this.add(buildLeftPanel(), BorderLayout.WEST);
        this.add(buildRightPanel());
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

    }

    /**
     * 左控制面板
     * 
     * @return
     */
    private JPanel buildLeftPanel() {
        JPanel panel = new JPanel();
        JLabel label = new JLabel();
        ImageIcon image = new ImageIcon("./img/homepage.jpg");
        label.setIcon(image);
        panel.add(label);
        return panel;
    }

    /**
     * 右控制面板
     * 
     * @return
     */
    private JPanel buildRightPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.add(buildThemePanel(), BorderLayout.NORTH);
        panel.add(buildInformationPanel(), BorderLayout.CENTER);
        return panel;
    }

    /**
     * 系统图片
     * 
     * @return
     */
    private JPanel buildThemePanel() {
        JPanel panel = new JPanel();
        JLabel label = new JLabel();
        ImageIcon image = new ImageIcon("./img/CMS.jpg");
        label.setIcon(image);
        panel.add(label);
        return panel;
    }
    
    /**
     * 账户信息面板
     * @return
     */
    private JPanel buildInformationPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.add(buildAccountPanel(),BorderLayout.NORTH);
        panel.add(buildButtonPanel(),BorderLayout.CENTER);
        return panel;
    }
    /**
     * 用户名·密码文本框面板
     * @return
     */
    private JPanel buildAccountPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        panel.add(buildUserLabel(), new GBC(0, 0));
        panel.add(buildUserText(),new GBC(1,0));
        panel.add(buildPwdLabel(),new GBC(0, 1).setInsets(10));
        panel.add(buildPwdField(),new GBC(1, 1).setInsets(10));
        return panel;
    }
    /**
     * 登录·注册·退出按钮面板
     * @return
     */
    private JPanel buildButtonPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());
        panel.add(buildLoginButton());
        panel.add(buildRegisterButton());
        panel.add(buildExitButton());
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

    public JButton buildLoginButton() {
        if (loginButton == null) {
            loginButton = new JButton("登录");
            loginButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            loginButton.addActionListener(new LoginAction(this));
        }
        return loginButton;
    }

    public JButton buildRegisterButton() {
        if (registerButton == null) {
            registerButton = new JButton("注册");
            registerButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            registerButton.addActionListener(new LoginAction(this));
        }
        return registerButton;
    }

    public JButton buildExitButton() {
        if (exitButton == null) {
            exitButton = new JButton("退出");
            exitButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            exitButton.addActionListener(new LoginAction(this));
        }
        return exitButton;
    }
    
//    private void setDecoration() {
//        try {
//            UIManager.setLookAndFeel("com.nilo.plaf.nimrod.NimRODLookAndFeel");
//        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException
//                | UnsupportedLookAndFeelException e) {
//            e.printStackTrace();
//        }
//    }
    
    public static void main(String[] args) {
        new LoginDialog().setVisible(true);
    }
}
