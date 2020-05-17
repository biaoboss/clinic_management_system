package com.zx.test;

import java.awt.BorderLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Test2 extends JFrame{
    JButton b1,b2,b3,b4,pre ,aft;
    JTextField text;
    JPanel p1,p2,p;
    public Test2() {
        this.setTitle("诊所管理系统");
        this.setSize(500, 400);
        this.setLayout(new BorderLayout());
        p = new JPanel();
        text = new JTextField(12);
        text.setText("testt");
        text.setEditable(false);
        text.setBorder(BorderFactory.createEmptyBorder());
        p.add(text);
        this.add(p);
        
        this.setVisible(true);
        
    }
    public static void main(String[] args) {
        new Test2();
    }
}
