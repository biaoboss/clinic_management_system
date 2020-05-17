package com.zx.control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.Vector;

import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.zx.dao.ProListDao;
import com.zx.dao.impl.ManagementDaoImpl;
import com.zx.dao.impl.ProListDaoImpl;
import com.zx.util.ConnectionBean;
import com.zx.util.DataUtil;
import com.zx.view.AddAcrossWindow;
import com.zx.view.AddSalWindow;
import com.zx.view.AlterWindow;
import com.zx.view.MainFrame;
import com.zx.view.ShowBasicInfomationPane;
import com.zx.vo.ProListVo;

/**
 * 
 * @author 彭文大帅比
 *
 */
public class AddSalAction implements ActionListener{

    private AddSalWindow window;

    private ShowBasicInfomationPane showPane;
    public AddSalAction() {}
    public AddSalAction(AddSalWindow window, ShowBasicInfomationPane showPane) {
        this.window = window;
        this.showPane = showPane;
    }

    public void actionPerformed(ActionEvent e) {
        String name = e.getActionCommand();
        if (name.equals("修改")) {
            JTable table = window.getProtable();
            DefaultTableModel model = (DefaultTableModel) table.getModel();
            int row = table.getSelectedRow();
            if (row != -1) {
//			int column = table.getColumnCount();
                String id = (String) table.getValueAt(row, 0);
                String pname = (String) table.getValueAt(row, 1);
                int acount = Integer.parseInt(table.getValueAt(row, 2).toString());
                double price = Double.parseDouble(table.getValueAt(row, 3).toString());
                String type = (String) table.getValueAt(row, 4);

                ProListVo listVo = new ProListVo(id, pname, acount, price, type);
                new AlterWindow(window, listVo);
                
            } else {
                JOptionPane.showMessageDialog(window, "您没有选择一行！");
            }
        } else if (name.equals("删除")) {
            JTable table = window.getProtable();
            DefaultTableModel model = (DefaultTableModel) table.getModel();
            int row = table.getSelectedRow();

            if (row != -1) {

                int cd = JOptionPane.showConfirmDialog(window, "确定删除当前项目吗？", "系统提示", JOptionPane.YES_NO_OPTION);
                

                if (cd == JOptionPane.YES_OPTION) {
                    String proId = model.getValueAt(row, 0).toString();
                    int amount = Integer.parseInt(model.getValueAt(row, 2).toString());
                    Object obj = window.getListdao().getStockById(proId);
                    if(obj != null) {
                        int stock = Integer.parseInt(obj.toString());
                        window.getListdao().updateStockById(proId, stock+amount);
                    }
                    DefaultTableModel listModel = window.getListmodel();
                    Vector datas = window.getListdao().getProList();
                    listModel.setRowCount(0);
                    for (int i = 0; i < datas.size(); i++) {
                        listModel.addRow((Vector)datas.get(i));
                    }
                    
                    
                    model.removeRow(row);
                } else if (cd == JOptionPane.NO_OPTION) {
                    row = -1;
                }
            }
        } else if (name.equals("确认")) {
            JTable proTable = window.getProtable();
            if(proTable.getRowCount()==0) {
                JOptionPane.showMessageDialog(window, "请添加项目！");
                return ;
            }
            int result = JOptionPane.showConfirmDialog(window, "确认添加收费?", "系统提示", JOptionPane.OK_CANCEL_OPTION,
                    JOptionPane.INFORMATION_MESSAGE);
            if (result == JOptionPane.OK_OPTION) {
                this.dataConserve();
            }
            
            
            
        } else if (name.equals("退出")) {
            JTable table = window.getProtable();
            int row = table.getRowCount();
            if (row == 0) {

                window.dispose();
            } else {
                int cd = JOptionPane.showConfirmDialog(window, "所选服务没有保存，是否保存？", "系统提示",
                        JOptionPane.OK_CANCEL_OPTION);
                if (cd == JOptionPane.OK_OPTION) {
                    this.dataConserve();
                } else if (cd == JOptionPane.CANCEL_OPTION) {
                    try {
                        ConnectionBean.getConnection().rollback();
                    } catch (SQLException e1) {
                        e1.printStackTrace();
                    }
                    window.dispose();
                }
            }
        } else if (name.equals("查询")) {
            ProListDao dao = new ProListDaoImpl();
            Vector value = new Vector();
            JTable table = window.getListtable();
            DefaultTableModel model = (DefaultTableModel) table.getModel();
            int row = table.getRowCount();
            String pname = window.getFondtxt().getText().trim();

            try {
                value = dao.findProLikeProIdOrName(pname);

                if (model != null) {
                    for (int i = row - 1; i >= 0; i--) {
                        // 删除jtable中的某一行记录
                        model.removeRow(i);
                    }
                    if (value.size() != 0) {

                        model = new DefaultTableModel(value, window.getListname()) {
                            public boolean isCellEditable(int rowIdeax, int columnIdeax) {
                                return false;
                            }
                        };
                        table.setModel(model);

                    }
                }

            } catch (Exception e2) {
                System.out.println(e2.getMessage());
            }

        }

    }
    
    
    //保存患者订单
    private void dataConserve() {
        //右model
        DefaultTableModel proModel = window.getPromodel();
        //右table
        JTable  proTable = window.getProtable();
        //订单编号
        String orderId = DataUtil.getOrderId();
        //订单日期
        String orderDate = DataUtil.getCurrentTime();
        //订单总金额
        double allPrice= 0;
        for (int i = 0; i < proTable.getRowCount(); i++) {
            //单价
            double price = Double.parseDouble(proModel.getValueAt(i, 3).toString());
            int amount = Integer.parseInt(proModel.getValueAt(i, 2).toString());
            allPrice += price* amount;
        }
        System.out.println("订单总金额为："+allPrice);
        //操作人员
        String username = showPane.getUsername();
        //医嘱
        String advice = window.getAdviceArea().getText().toString();
        //患者编号
        String patientId = showPane.getIdText().getText().toString();
        //病种类型
        String diseaseType = window.getClassbox().getSelectedItem().toString();
        
        //System.out.println(orderId+" "+orderDate+" "+allPrice+" " +username+" "+advice+" "+ patientId+" "+diseaseType);
        
        ProListVo proListVo = new ProListVo();
        proListVo.setOrderId(orderId);
        proListVo.setOrderDate(orderDate);
        proListVo.setAllPrice(allPrice);
        proListVo.setUsername(username);
        proListVo.setAdvice(advice);
        proListVo.setPatientId(patientId);
        proListVo.setDiseaseType(diseaseType);
        ProListDao proListDao = new ProListDaoImpl();
        //向订单表添加数据
        proListDao.insertOrderTable(proListVo);
        //未完
        int rows = proModel.getRowCount();
        for (int i = 0; i < rows; i++) {
            String pro_id = proModel.getValueAt(i, 0).toString();
            int proAmount = Integer.parseInt(proModel.getValueAt(i, 2).toString());
            double proPrice = Double.parseDouble(proModel.getValueAt(i, 3).toString()) * proAmount;
            proListVo.setPro_id(pro_id);
            proListVo.setOrderId(orderId);
            proListVo.setProjectAmount(proAmount);
            proListVo.setProjectPrice(proPrice);
            proListDao.insertOddproject(proListVo);
        }
        
        
        //提交事务
        try {
            ConnectionBean.getConnection().commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            ConnectionBean.releaseConnection();
        }
        
        DefaultTableModel orderModel = showPane.getOrderModel();
        orderModel.setRowCount(0);
        JTable orderTable = showPane.getOrderTable();
        Vector datas = new ManagementDaoImpl().selectProjectRecordByOrderID(patientId);
        for (int i = 0; i < datas.size(); i++) {
            orderModel.addRow((Vector)datas.get(i));
           orderTable.addRowSelectionInterval(0, 0);
        }
        window.dispose();
        
        
  //------------------------------------------      
        
        
        
        
        
        
//        
    }
    
    
    
    
    public AddSalWindow getWindow() {
        return window;
    }
    public ShowBasicInfomationPane getShowPane() {
        return showPane;
    }
   
    
    
    
    

}
