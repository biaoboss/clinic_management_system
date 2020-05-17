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

import com.zx.control.PatientManagementAction;
import com.zx.dao.PatientManagementDao;
import com.zx.dao.impl.PatientManagementDaoImpl;
/**
 * 
 * @author luo
 *
 */
public class PatientManagementDialog extends JDialog{
	private JTextField txt;
	private JButton btnAdd,btnModify,btnDelete,btnSeek,btnQuery,btnOut;
	private JTable table;
	private JPanel panelTop,panelUnder,panel;
	private JScrollPane spanel;
	PatientManagementDao dao;
	public DefaultTableModel model;
	public Vector v;
	ShowBasicInfomationPane showPane;
	
	public PatientManagementDialog(ShowBasicInfomationPane showPane) {
		this.showPane=showPane;
		txt=new JTextField(15);
		
		btnAdd=new JButton("增加");
		btnAdd.addActionListener(new PatientManagementAction(this,showPane));
		btnModify=new JButton("修改");
		btnModify.addActionListener(new PatientManagementAction(this,showPane));
		btnDelete=new JButton("删除");
		btnDelete.addActionListener(new PatientManagementAction(this,showPane));
		btnQuery=new JButton("查询");
		btnQuery.addActionListener(new PatientManagementAction(this,showPane));
		btnOut=new JButton("退出");
		btnOut.addActionListener(new PatientManagementAction(this,showPane));
		
		dao=new PatientManagementDaoImpl();
		v=new Vector();
		v.add("患者编号");
		v.add("患者姓名");
		v.add("性别");
		v.add("年龄");
		v.add("联系电话");
		v.add("地址");
		v.add("民族");
		v.add("身份证号");
		model=new DefaultTableModel(dao.findPartPatient(),v);
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
		this.setTitle("患者管理");
		this.add(panel,BorderLayout.NORTH);
		this.add(spanel,BorderLayout.CENTER);
		this.setModal(true);
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
