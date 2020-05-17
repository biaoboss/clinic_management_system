package com.zx.control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.zx.dao.OddProjectTableDao;
import com.zx.dao.impl.ManagementDaoImpl;
import com.zx.dao.impl.OddProjectTableDaoImpl;
import com.zx.view.AddSalWindow;
import com.zx.view.MedicalRecordManagementDialog;
import com.zx.view.PatientManagementDialog;
import com.zx.view.SelectDialog;
import com.zx.view.ShowBasicInfomationPane;

/**
 * 
 * @author biao boss
 *
 */
public class ShowBasicPaneAction implements ActionListener {

    private ShowBasicInfomationPane showPane;
    
    public ShowBasicPaneAction(ShowBasicInfomationPane showPane) {
        this.showPane = showPane;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        String actName = e.getActionCommand();
        
        if("查询".equals(actName)) {
            new SelectDialog(showPane);
            
            
        }else if("患者管理".equals(actName)) {
            new PatientManagementDialog(showPane);
            
        }else if("病例管理".equals(actName)) {
            new MedicalRecordManagementDialog();
            
        }else if("增加收费".equals(actName)) {
            if(showPane.getIdText().getText()==null || "".equals(showPane.getIdText().getText())) {
                JOptionPane.showMessageDialog(showPane, "请选择患者！");
                return ;
            }
            new AddSalWindow(showPane);
        }else if("删除订单".equals(actName)) {
            JTable orderTable = showPane.getOrderTable();
            int selectRow  = orderTable.getSelectedRow();
            System.out.println("选中所在的行："+selectRow);
            if(selectRow == -1) {
                JOptionPane.showMessageDialog(showPane, "请选中需删除的订单项！");
                return ;
            }
            int result = JOptionPane.showConfirmDialog(showPane, "确认删除?", "确认", JOptionPane.OK_CANCEL_OPTION,
                    JOptionPane.INFORMATION_MESSAGE);
            if (result == JOptionPane.OK_OPTION) {
                DefaultTableModel model = showPane.getOrderModel();
                String id = model.getValueAt(selectRow, 0).toString();
                System.out.println("获取需要删除的订单id为："+id);
                OddProjectTableDao oddDao = new OddProjectTableDaoImpl();
                boolean flag = oddDao.deletePatientOrderRecord(id);
                String patientId = showPane.getIdText().getText();
                Vector datas =  new ManagementDaoImpl().selectProjectRecordByOrderID(patientId);
                
                if(flag) {
                    model.setRowCount(0);
                    for (int i = 0; i < datas.size(); i++) {
                        Vector data = (Vector)datas.get(i);
                        model.addRow(data);
                        orderTable.addRowSelectionInterval(0, 0);
                    }
                    DefaultTableModel proModel = showPane.getProModel();
                    proModel.setRowCount(0);
                    
                    
                    System.out.println("编号为："+id+" 的订单删除成功！");
                    JOptionPane.showMessageDialog(showPane, "订单删除成功！");
                }else {
                    System.out.println("编号为："+id+" 的订单删除失败！");
                    JOptionPane.showMessageDialog(showPane, "订单删除失败！");
                }
            }
            
            
            
        }else if("打印订单".equals(actName)) {
            
        }
        
        
        
    }

}
