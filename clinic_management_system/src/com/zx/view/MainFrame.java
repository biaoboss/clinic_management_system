package com.zx.view;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.zx.util.C3P0Utils;
/**
 * 
 * @author biao boss
 *
 */
public class MainFrame extends JFrame {
    
    
    // 左右面板
    private JPanel leftPane, rightPane;
    //
    private JButton patientInfoBtn, analysisBtn, switchBtn,exitBtn;

    private CardLayout cardLayout;
    // 基本信息面板
    ShowBasicInfomationPane showPane;
    // 业务分析面板
    BusinessAnalysisPane businessAnalysisPane;

    //操作人用户名
    String username  = "";
    public MainFrame() {}
    public MainFrame(String username) {
        this.username = username;
        this.setTitle("诊所管理系统");
        this.setSize(850, 600);
        this.setLayout(new BorderLayout());
        
        ImageIcon mainIcon = new ImageIcon("./img/clinic.png");
        this.setIconImage(mainIcon.getImage());
        // 面板初始化
        this.initialView();
        // 事件绑定
        this.bindEvent();


        this.setLocationRelativeTo(this);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setVisible(true);
    }

    private void initialView() {

        leftPane = new JPanel();
        JPanel pane = new JPanel(new GridLayout(4, 1, 30, 30));
        
        patientInfoBtn = new JButton("患者接待");
        analysisBtn = new JButton("业务分析");
        switchBtn = new JButton("切换账号");
        exitBtn = new JButton("退出系统");
        pane.add(patientInfoBtn);
        pane.add(analysisBtn);
        pane.add(switchBtn);
        pane.add(exitBtn);
        leftPane.add(pane);
        this.add(leftPane, BorderLayout.WEST);

        rightPane = new JPanel();
        cardLayout = new CardLayout();
        rightPane.setLayout(cardLayout);
        showPane = new ShowBasicInfomationPane(username);
        businessAnalysisPane = new BusinessAnalysisPane();
        rightPane.add("1", showPane);
        rightPane.add("2", businessAnalysisPane);
        this.add(rightPane);

    }

    private void bindEvent() {
        this.patientInfoBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.first(rightPane);

            }
        });

        
        this.analysisBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(rightPane, "2");

            }
        });
        
        this.switchBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int result = JOptionPane.showConfirmDialog(MainFrame.this, "你确定要切换账号吗?", "系统提示", JOptionPane.OK_CANCEL_OPTION,
                        JOptionPane.INFORMATION_MESSAGE);
                if (result == JOptionPane.OK_OPTION) {
                    MainFrame.this.dispose();
                    new LoginDialog().setVisible(true);
                }
            }
        });
        
        this.exitBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int result = JOptionPane.showConfirmDialog(MainFrame.this, "确认退出?", "系统提示", JOptionPane.OK_CANCEL_OPTION,
                        JOptionPane.INFORMATION_MESSAGE);
                if (result == JOptionPane.OK_OPTION) {
                    System.exit(0);
                }
            }
        });

    }

  

    public ShowBasicInfomationPane getShowPane() {
        return showPane;
    }

    

    
}
