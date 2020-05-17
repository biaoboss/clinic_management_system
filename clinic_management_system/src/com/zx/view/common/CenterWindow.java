package com.zx.view.common;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.Window;

public class CenterWindow {
  public static void centerWindow(Window w) {
    //�õ���Ļ�Ĵ�С
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    //�õ������С
    Dimension windowSize = w.getSize();
    //���ô���ľ���·�������ꡢ��ȡ��߶ȣ�
    w.setBounds((int)(screenSize.getWidth() - windowSize.getWidth())/2,
        (int)(screenSize.getHeight() - windowSize.getHeight())/2,
        (int)windowSize.getWidth(),
        (int)windowSize.getHeight());
  }
}
