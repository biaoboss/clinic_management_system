package com.zx.control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Vector;

import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.zx.dao.OddProjectTableDao;
import com.zx.dao.impl.OddProjectTableDaoImpl;
import com.zx.view.ShowBasicInfomationPane;

/**
 * 
 * @author biao boss 
 *
 */
public class ShowTableAction implements MouseListener{

    private ShowBasicInfomationPane showPane;
    
    public ShowTableAction(ShowBasicInfomationPane showPane) {
        this.showPane= showPane;
       
    }
    

    @Override
    public void mouseClicked(MouseEvent e) {
        JTable orderTable = showPane.getOrderTable();
        int row = orderTable.getSelectedRow();
        if(row == -1) {
            return ;
        }
        
        DefaultTableModel orderModel = showPane.getOrderModel();
        
        String id = orderTable.getValueAt(row, 0).toString();
        
        OddProjectTableDao oddProjectTableDao = new OddProjectTableDaoImpl();
        Vector datas = oddProjectTableDao.selectInfoByOddProject(id);
        DefaultTableModel proModel = showPane.getProModel();
        proModel.setRowCount(0);
        for (int i = 0; i < datas.size(); i++) {
            Vector data = (Vector)datas.get(i);
            proModel.addRow(data);
        }
        
        JTextField text = showPane.getProAmountTxt();
        text.setText("   "+datas.size());
    }

    @Override
    public void mousePressed(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void mouseExited(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }
    

}
