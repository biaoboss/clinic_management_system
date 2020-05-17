package com.zx.view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.zx.dao.ManagementDao;
import com.zx.dao.PatientDao;
import com.zx.dao.impl.ManagementDaoImpl;
import com.zx.dao.impl.OddProjectTableDaoImpl;
import com.zx.dao.impl.PatientDaoImpl;
import com.zx.vo.PatientVo;



/**
 * 
 * @author lei
 *
 */
public class SelectDialog extends JDialog {
	PatientVo patientVo;
	PatientDao patientDao;
	ManagementDao managementDao;
	Vector columnNames;
	JTable table;

	
	DefaultTableModel model;
	private JButton enquireBtn,sureBtn,quitBtn;
	private JTextField txt;
	private JPanel panel,panelTop,panelUp;
	private JLabel label;
	private ShowBasicInfomationPane showPane;

	
	public SelectDialog(ShowBasicInfomationPane showPane){
		this.setTitle("患者查询");
		this.showPane =showPane;


		Vector vector = new Vector();
		Vector vectors = new Vector();
		enquireBtn = new JButton("查询");
		sureBtn = new JButton("确定");
		quitBtn = new JButton("退出");
		label = new JLabel("根据id或者姓名模糊查询");
		txt = new JTextField(10);
		
		managementDao=new ManagementDaoImpl();
		patientDao = new PatientDaoImpl();
		table = new JTable();
		panelTop = new JPanel();
		panel=new JPanel();
		panelUp=new JPanel();
		
		
		columnNames = new Vector();
		columnNames.add("编号");
		columnNames.add("患者姓名");
		columnNames.add("联系电话");
		columnNames.add("联系地址");
		columnNames.add("备注");


		panel.setLayout(new FlowLayout());
		panel.add(label);
		panel.add(txt);
		panel.add(enquireBtn);
		
		model=new DefaultTableModel(patientDao.getPatients(),columnNames);
		table.setModel(model);
		
		panelTop.add(panel,BorderLayout.NORTH);
		panelTop.add(new JScrollPane(table), BorderLayout.CENTER);
		
		
		panelUp.add(sureBtn);
		panelUp.add(quitBtn);
		
		this.setLayout(new GridLayout(2,1));
		this.add(panelTop);
		this.add(panelUp);
		this.setSize(500, 350);
		this.setLocationRelativeTo(this);
		
		
		
		
		

		/**
		 * 
		 */
		enquireBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String gettxt = txt.getText().trim();
				model = new DefaultTableModel(patientDao.getPatientsByIdAndName(gettxt), columnNames);
				table.setModel(model);
				table.updateUI();
			}

		});
		//对查询Dialog中的确认按钮监听
		sureBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				int row=table.getSelectedRow();
				if(row==-1) {
					JOptionPane.showMessageDialog(SelectDialog.this, "你没有选择一行");
				}
				else{
					String pat_id=table.getValueAt(row, 0).toString();
				//对主界面患者信息更新
				patientVo= patientDao.getPatientVo(pat_id);
				showPane.getIdText().setText((String) table.getValueAt(row, 0));
				showPane.getNameText().setText((String) table.getValueAt(row, 1));
				showPane.getPhoneText().setText((String) table.getValueAt(row, 2));
		
				showPane.getSexText().setText(patientVo.getSex());
				showPane.getAgeText().setText(String.valueOf(patientVo.getAge()));
				
			
				showPane.getAddrText().setText((String) table.getValueAt(row, 3));
				showPane.getRemarkText().setText((String) table.getValueAt(row, 4));

				//对主界面患者订单信息表格更新
				
				DefaultTableModel model =showPane.getOrderModel();
				
				JTable orderTable = showPane.getOrderTable();
				Vector datas = managementDao.selectProjectRecordByOrderID(pat_id);
				model.setRowCount(0);
				for (int i = 0; i < datas.size(); i++) {
					Vector data =(Vector) datas.get(i);
					model.addRow(data);
					orderTable.addRowSelectionInterval(0, 0);
				}
				DefaultTableModel proModel = showPane.getProModel();
				proModel.setRowCount(0);
				if(datas.size()>=1) {
				    String orderId = orderTable.getValueAt(0, 0).toString();
				    datas = new OddProjectTableDaoImpl().selectInfoByOddProject(orderId);
				    for (int i = 0; i < datas.size(); i++) {
	                    Vector data =(Vector) datas.get(i);
	                    proModel.addRow(data);
	                }
				    
				}
				
//				JTable orderTable = showPane.getOrderTable();
			
//				JTable ordertable=showPane.getOrderTable();
//				ordertable.setModel(model);
//				ordertable.updateUI();
				SelectDialog.this.setModal(false);
				SelectDialog.this.dispose();
				}
				
			}


		});
		quitBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SelectDialog.this.setModal(false);
				SelectDialog.this.dispose();
		
			
				}

		});
		this.setModal(true);
		this.setVisible(true);
	}

	
}