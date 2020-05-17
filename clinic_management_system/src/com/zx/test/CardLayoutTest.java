package com.zx.test;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class CardLayoutTest  extends JFrame{

    JButton b1,b2,b3,b4,pre ,aft;
    JPanel p1,p2,p;
    CardLayout c = new CardLayout();
    public CardLayoutTest() {
        this.setTitle("诊所管理系统");
        this.setSize(500, 400);
        this.setLayout(new BorderLayout());
        p = new JPanel();
        p.setLayout(c);
        
        b1 = new JButton("1");
        b2 = new JButton("2");
        b3 = new JButton("3");
        b4 = new JButton("4");
        p.add(b1);
        p.add(b2);
        p.add(b3);
        p.add(b4);
        this.add(p,BorderLayout.NORTH);
        p2 = new JPanel();
        pre = new JButton("钱");
        pre.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                c.next(p);
                
            }
        });
        aft = new JButton("厚");
        p2.add(pre);
        p2.add(aft);
        this.add(p2);
        
        
        this.setLocationRelativeTo(this);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setVisible(true);
        
        
        
    }
    public static void main(String[] args) {
        new CardLayoutTest();
    }
    
}
