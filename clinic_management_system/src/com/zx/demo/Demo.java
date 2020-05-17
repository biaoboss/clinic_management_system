package com.zx.demo;

import java.sql.Connection;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import com.zx.util.C3P0Utils;
import com.zx.util.RealseResource;
import com.zx.view.LoginDialog;
import com.zx.view.MainFrame;

public class Demo {

    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {
       
//        String lookAndFeel = "com.sun.java.swing.plaf.motif.MotifLookAndFeel";
//        UIManager.setLookAndFeel(lookAndFeel);
        
//        String lookAndFeel = UIManager.getSystemLookAndFeelClassName();
//        UIManager.setLookAndFeel(lookAndFeel);
//        UIManager.setLookAndFeel("com.jtattoo.plaf.hifi.HiFiLookAndFeel");
//        UIManager.setLookAndFeel("com.jtattoo.plaf.mcwin.McWinLookAndFeel");
        UIManager.setLookAndFeel("com.jtattoo.plaf.smart.SmartLookAndFeel");
        

        Connection conn = C3P0Utils.getConnection();
        RealseResource.release(conn, null, null);
        new LoginDialog().setVisible(true);
        
    }
    
}
