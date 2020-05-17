package com.zx.view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.zx.dao.PatientManagementDao;
import com.zx.dao.impl.PatientManagementDaoImpl;

public class MedicalRecordManagementDialog extends JDialog {
	private JTextField txt;
	private JButton btnAdd,btnModify,btnDelete,btnQuery,btnOut;
	private JTable table;
	private JPanel panelTop,panelUnder,panel;
	private JScrollPane spanel;
	PatientManagementDao dao;
	public DefaultTableModel model;
	public Vector v;
	ShowBasicInfomationPane showPane;
	
	public MedicalRecordManagementDialog() {
		this.showPane=showPane;
		txt=new JTextField(15);
		
		btnAdd=new JButton("增加");
		btnModify=new JButton("修改");
		btnDelete=new JButton("删除");
		btnQuery=new JButton("查询");
		btnOut=new JButton("退出");
		
		dao=new PatientManagementDaoImpl();
		v=new Vector();
		v.add("病历编号");
		v.add("日期");
		v.add("患者名称");
		v.add("操作员");
		v.add("病历备注");
		model=new DefaultTableModel(null,v);
		table=new JTable();
		table.setModel(model);
		
		spanel=new JScrollPane(table);
		
		panelTop=new JPanel();
		panelTop.add(btnAdd);
		panelTop.add(btnModify);
		panelTop.add(btnDelete);
		panelTop.add(btnOut);
		
		panelUnder=new JPanel();
		panelUnder.add(new JLabel("请输入患者相关信息进行查询："));
		panelUnder.add(txt);
		panelUnder.add(btnQuery);
		
		panel=new JPanel();
		panel.setLayout(new GridLayout(2,1));
		panel.add(panelTop);
		panel.add(panelUnder);
		
		this.setLayout(new BorderLayout());
		this.setSize( 700, 500);
		this.setLocationRelativeTo(showPane);
		this.setModal(true);
		this.setTitle("病历管理");
		this.add(panel,BorderLayout.NORTH);
		this.add(spanel,BorderLayout.CENTER);
		this.setVisible(true);
	}
	public JTable getTable() {
		return table;
	}
	public void setTable(JTable table) {
		this.table=table;
	}
	public JTextField getTxt() {
		return txt;
	}

}
