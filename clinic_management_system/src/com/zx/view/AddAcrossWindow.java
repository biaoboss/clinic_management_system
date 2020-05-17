package com.zx.view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.zx.view.common.CenterWindow;
import com.zx.vo.ProListVo;

/**
 * 
 * @author 彭文大帅比
 *
 */

public class AddAcrossWindow extends JDialog {

    AddSalWindow addSal;
    ProListVo listVo;
    private JPanel p1, p2, p3, p4;
    private JTextField txt_pro_id, txt_pro_name, txt_price, txt_pro_type, pro_amount;
    JButton btn1, btn2;

    public AddAcrossWindow(AddSalWindow addSal, ProListVo listVo) {

        p1 = new JPanel();
        p2 = new JPanel();
        p3 = new JPanel();
        p4 = new JPanel();

        txt_pro_id = new JTextField(20);
        txt_pro_name = new JTextField(30);
        txt_price = new JTextField(10);
        txt_pro_type = new JTextField(30);

        pro_amount = new JTextField(5);
        btn1 = new JButton("确定");
        btn2 = new JButton("退出");

        p1.setLayout(new BorderLayout());
        p1.add(p2, BorderLayout.NORTH);
        p1.add(p3, BorderLayout.SOUTH);
        p2.setLayout(new GridLayout(1, 6));
        p2.add(new JLabel("项目编号:"));
        p2.add(txt_pro_id);
        p2.add(new JLabel("医疗服务:"));
        p2.add(txt_pro_name);
        p2.add(new JLabel("项目分类:"));
        p2.add(txt_pro_type);

        p3.add(new JLabel("服务单价:"));
        p3.add(txt_price);
        p3.add(new JLabel("服务次数:"));
        p3.add(pro_amount);

        p1.setBorder(BorderFactory.createTitledBorder("项目信息"));
        p4.add(btn1);
        p4.add(btn2);

        txt_pro_id.setText(listVo.getPro_id());
        txt_pro_id.setEditable(false);
        txt_pro_name.setText(listVo.getPro_name());
        txt_pro_name.setEditable(false);
        txt_price.setText(String.valueOf(listVo.getPrice()));
        txt_pro_type.setText(listVo.getPro_type());
        txt_pro_type.setEditable(false);
        pro_amount.setText(String.valueOf(1));

//		确认按钮动作监听器
        btn1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //TODO
                
                //获取table对象
                JTable listtable = addSal.getListtable();
                int row = listtable.getSelectedRow();
                System.out.println("选中行为："+row);
                //获取model对象
                DefaultTableModel listModel = addSal.getListmodel();
                Object obj = listModel.getValueAt(row, 6);
                String proId = listModel.getValueAt(row, 0).toString();
                System.out.println("编号为："+proId);
                if(obj != null) {
                    //库存数
                    int stock  = Integer.parseInt(obj.toString());
                    //购买数量
                    int buyamount = Integer.parseInt(pro_amount.getText());
                    if(buyamount>stock) {
                        JOptionPane.showMessageDialog(AddAcrossWindow.this, "采购数量超过库存数！");
                        return ;
                    }else { //进行添加
                        stock -= buyamount;
                        //修改库存
                        addSal.getListdao().updateStockById(proId, stock);
                        Vector datas = addSal.getListdao().getProList();
                        listModel.setRowCount(0);
                        for (int i = 0; i < datas.size(); i++) {
                            listModel.addRow((Vector)(datas.get(i)));
                        }
                        
                       
                    }
                    
                }
                
                
                //-------------------
                
                String id = txt_pro_id.getText().trim();
                String name = txt_pro_name.getText().trim();
                String amount = pro_amount.getText().trim();
                String price = txt_price.getText().trim();
                String type = txt_pro_type.getText().trim();

                Object[] data = { id, name, amount, price, type };
                DefaultTableModel model = addSal.promodel;
                model.addRow(data);
                AddAcrossWindow.this.dispose();
            }
        });
//		退出按钮动作监听器
        btn2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                AddAcrossWindow.this.dispose();

            }
        });

        this.setTitle("添加服务项目到收费单");
        this.setSize(500, 300);
        this.setLayout(new BorderLayout());
        this.add(p1, BorderLayout.NORTH);
        this.add(p4, BorderLayout.SOUTH);
        CenterWindow.centerWindow(this);
        this.setModal(true);
        this.setVisible(true);

    }
}
