package com.zx.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;

import com.zx.control.ShowBasicPaneAction;
import com.zx.control.ShowTableAction;

/**
 * 
 * @author biao boss
 *
 */
public class ShowBasicInfomationPane extends JPanel {

    // 从上至下面板
    private JPanel paneA, paneB, paneC, paneD;
    // 查询按钮、患者管理按钮、病例管理按钮
    private JButton queryBtn, patientBtn, caseBtn;
    // 编号、姓名、联系电话、性别、年龄、联系地址、备注
    private JLabel idLbl, nameLbl, phoneLbl, sexLbl, ageLbl, addrLbl, remarkLb;
    private JTextField idText, nameText, phoneText, sexText, ageText, addrText, remarkText;
    //
    private JButton addFeeBtn, delOrderBtn, printOrderBtn;

    //
    private JTable orderTable, proTable;
    private DefaultTableModel orderModel, proModel;
    private JLabel orderAmountLbl , proAmountLbl;
    private JTextField orderAmountTxt ,proAmountTxt;

    private String username;
    
    public ShowBasicInfomationPane() {}
    public ShowBasicInfomationPane(String username) {

        this.username = username;
        
        this.setLayout(new GridLayout(3, 1));
        JPanel paneBag = new JPanel(new BorderLayout());
        paneBag.add(buildPaneA(), BorderLayout.NORTH);
        paneBag.add(buildPaneB());
        this.add(paneBag);
        paneBag.setBorder(BorderFactory.createTitledBorder("患者基本信息"));

        this.add(buildPaneC());
        this.add(buildPaneD());
        //绑定事件
        this.bindAction();

    }

    /**
     * 第一行
     * 
     * @return
     */
    private JPanel buildPaneA() {
        paneA = new JPanel();
        paneA.setLayout(new FlowLayout());
//        JPanel paneA1 = new JPanel();
//        paneA1.setLayout(new GridLayout(1, 3, 60, 0));
        queryBtn = new JButton("查询");
        patientBtn = new JButton("患者管理");
        caseBtn = new JButton("病例管理");
        paneA.add(queryBtn);
        paneA.add(patientBtn);
        paneA.add(caseBtn);
//        paneA.add(paneA1, FlowLayout.LEFT);
        return paneA;
    }

    /**
     * 
     * @return
     */
    private JPanel buildPaneB() {
        paneB = new JPanel();
        paneB.setLayout(new GridLayout(2, 1));
        idLbl = new JLabel("编        号：");
        nameLbl = new JLabel("患者姓名：");
        phoneLbl = new JLabel("联系电话：");
        sexLbl = new JLabel("性        别：");
        ageLbl = new JLabel("年        龄：");
        addrLbl = new JLabel("联系地址：");
        remarkLb = new JLabel("备        注：");

        idText = new JTextField(12);
        idText.setEditable(false);
        idText.setBorder(BorderFactory.createEmptyBorder());
        nameText = new JTextField(12);
        nameText.setEditable(false);
        nameText.setBorder(BorderFactory.createEmptyBorder());
        phoneText = new JTextField(12);
        phoneText.setEditable(false);
        phoneText.setBorder(BorderFactory.createEmptyBorder());
        sexText = new JTextField(12);
        sexText.setEditable(false);
        sexText.setBorder(BorderFactory.createEmptyBorder());
        ageText = new JTextField(12);
        ageText.setEditable(false);
        ageText.setBorder(BorderFactory.createEmptyBorder());
        addrText = new JTextField(12);
        addrText.setEditable(false);
        addrText.setBorder(BorderFactory.createEmptyBorder());
        remarkText = new JTextField(40);
        remarkText.setEditable(false);
        remarkText.setBorder(BorderFactory.createEmptyBorder());

        JPanel paneTop = new JPanel(new GridLayout(2, 3));
        JPanel p1, p2, p3, p4, p5, p6;
        p1 = new JPanel();
        p2 = new JPanel();
        p3 = new JPanel();
        p4 = new JPanel();
        p5 = new JPanel();
        p6 = new JPanel();
        p1.add(idLbl);
        p1.add(idText);
        p2.add(nameLbl);
        p2.add(nameText);
        p3.add(phoneLbl);
        p3.add(phoneText);
        p4.add(sexLbl);
        p4.add(sexText);
        p5.add(ageLbl);
        p5.add(ageText);
        p6.add(addrLbl);
        p6.add(addrText);
        paneTop.add(p1);
        paneTop.add(p2);
        paneTop.add(p3);
        paneTop.add(p4);
        paneTop.add(p5);
        paneTop.add(p6);
        JPanel paneBottom = new JPanel();
        paneBottom.add(remarkLb, FlowLayout.LEFT);
        paneBottom.add(remarkText);

        paneB.add(paneTop);
        paneB.add(paneBottom);

        return paneB;
    }

    private JPanel buildPaneC() {
        paneC = new JPanel(new BorderLayout());
        addFeeBtn = new JButton("增加收费");
        delOrderBtn = new JButton("删除订单");
        printOrderBtn = new JButton("打印订单");
        JPanel paneTop = new JPanel();
        paneTop.add(addFeeBtn);
        paneTop.add(delOrderBtn);
        paneTop.add(printOrderBtn);
        paneC.add(paneTop, BorderLayout.NORTH);
        
        Object[] head = { "单号", "日期", "总金额", "病种类型", "操作人", "医嘱信息" };
        orderModel = new DefaultTableModel(null, head);
        
        orderTable = new JTable(orderModel) {
            public boolean isCellEditable(int row, int column){
                       return false;
            }
        };
        
        paneC.add(new JScrollPane(orderTable));
        orderTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JPanel paneBottom = new JPanel(new BorderLayout());
        orderAmountLbl = new JLabel("数量：");
        orderAmountTxt = new JTextField(20);
        orderAmountTxt.setBackground(Color.LIGHT_GRAY);
        orderAmountTxt.setBorder(BorderFactory.createEmptyBorder());
        paneBottom.add(orderAmountLbl, BorderLayout.WEST);
        paneBottom.add(orderAmountTxt);

        paneC.add(paneBottom, BorderLayout.SOUTH);
        paneC.setBorder(BorderFactory.createTitledBorder("患者订单信息"));
        return paneC;
    }

    private JPanel buildPaneD() {
        paneD  = new JPanel(new BorderLayout());
        Object[] head = { "项目编号", "项目名称", "项目类型", "单位", "单价", "数量","总金额","规格" };
        proModel = new DefaultTableModel(null, head);
        proTable = new JTable(proModel) {
            public boolean isCellEditable(int row, int column){
                       return false;
            }
        };
        proTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        paneD.add(new JScrollPane(proTable),BorderLayout.CENTER);
        
        JPanel paneBottom = new JPanel(new BorderLayout());
        proAmountLbl = new JLabel("品种合计：");
        proAmountTxt = new JTextField(20);
        orderAmountTxt.setBackground(Color.LIGHT_GRAY);
        proAmountTxt.setEditable(false);
        proAmountTxt.setBorder(BorderFactory.createEmptyBorder());
        paneBottom.add(proAmountLbl, BorderLayout.WEST);
        paneBottom.add(proAmountTxt);
        
        paneD.add(paneBottom,BorderLayout.SOUTH);
        
        paneD.setBorder(BorderFactory.createTitledBorder("单据详细信息"));
        return paneD;
        
    }
    
    private void bindAction() {
        //患者信息查询按钮
        this.queryBtn.addActionListener(new ShowBasicPaneAction(this));
        //
        this.patientBtn.addActionListener(new ShowBasicPaneAction(this));
        //
     
        this.caseBtn.addActionListener(new ShowBasicPaneAction(this));
        //
        this.addFeeBtn.addActionListener(new ShowBasicPaneAction(this));
        //
        this.delOrderBtn.addActionListener(new ShowBasicPaneAction(this));
        //
        this.printOrderBtn.addActionListener(new ShowBasicPaneAction(this));
        
        this.orderTable.addMouseListener(new ShowTableAction(this) );
        
        
//        orderModel.addTableModelListener(new TableModelListener() {
//            
//            @Override
//            public void tableChanged(TableModelEvent e) {
//                
//                
//                if(orderModel.getRowCount()>= 1) {
//                    orderTable.setRowSelectionInterval(0,0);
//                    new ShowTableAction(ShowBasicInfomationPane.this).mouseClicked(null);
//                }
//                int rows = orderTable.getRowCount();
//                orderAmountTxt.setText("   "+rows);
//            }
//        });
        
        
        
    }
    
    
    
    
    
    
    
    
    
    

    public JTextField getIdText() {
        return idText;
    }

    public JTextField getNameText() {
        return nameText;
    }

    public JTextField getPhoneText() {
        return phoneText;
    }

    public JTextField getSexText() {
        return sexText;
    }

    public JTextField getAgeText() {
        return ageText;
    }

    public JTextField getAddrText() {
        return addrText;
    }

    public JTextField getRemarkText() {
        return remarkText;
    }

    public JTable getOrderTable() {
        return orderTable;
    }

    public JTable getProTable() {
        return proTable;
    }

    public DefaultTableModel getOrderModel() {
        return orderModel;
    }

    public DefaultTableModel getProModel() {
        return proModel;
    }

    public JTextField getOrderAmountTxt() {
        return orderAmountTxt;
    }

    public JTextField getProAmountTxt() {
        return proAmountTxt;
    }
    public String getUsername() {
        return username;
    }
    
    
    

}
