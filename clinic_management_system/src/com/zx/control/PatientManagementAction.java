package com.zx.control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import com.zx.dao.PatientManagementDao;
import com.zx.dao.impl.PatientManagementDaoImpl;
import com.zx.view.PatientAddDialog;
import com.zx.view.PatientManagementDialog;
import com.zx.view.PatientModifyDialog;
import com.zx.view.ShowBasicInfomationPane;
import com.zx.vo.PatientVo;

/**
 * 
 * @author luo
 *
 */
public class PatientManagementAction implements ActionListener{
	PatientManagementDao dao;
	PatientVo patVo;
    PatientManagementDialog manage;
    ShowBasicInfomationPane showPane;
	public PatientManagementAction(PatientManagementDialog manage,ShowBasicInfomationPane showPane) {
		this.manage=manage;
		this.showPane=showPane;
	}

	public void actionPerformed(ActionEvent e) {
		String name=e.getActionCommand();
		if(name.equals("增加")) {
			new PatientAddDialog(manage);
		}else if(name.equals("修改")) {
			int rows=manage.getTable().getSelectedRowCount();
			int row=manage.getTable().getSelectedRow();
			if(rows==0) {
				JOptionPane.showMessageDialog(manage, "请选择要修改的数据！");
			}else { 
				if(rows>1) {
					JOptionPane.showMessageDialog(manage, "请选择一行数据进行修改！");
				}else {
				    
				    dao=new PatientManagementDaoImpl();
				    String id=manage.getTable().getValueAt(row, 0).toString();
				    PatientVo patVo=dao.findPatientById(id);
				    new PatientModifyDialog(patVo,manage,showPane);
				}
			}
		}else if(name.equals("删除")) {
			int[] rows=manage.getTable().getSelectedRows();
			for (int i : rows) {
				System.out.println(i);
			}
			
			if(rows.length == 0) {
				JOptionPane.showMessageDialog(manage, "请选择要删除的数据！");
			}else {
				int n=0;
				Vector ids= new Vector();
				int result = JOptionPane.showConfirmDialog(null, "你确定要删除？"); 
		        if(result == JOptionPane.OK_OPTION){
		        	for (int row : rows) {
			            String id=manage.getTable().getValueAt(row, 0).toString();
			            dao=new PatientManagementDaoImpl();
			            ids.add(id);
			            n=dao.deletePatientById(id);
		            }if(n==0) {
		            	JOptionPane.showMessageDialog(manage, "删除患者信息失败！");
		            }else {
		            	int row=manage.getTable().getSelectedRow();
		            	manage.model=new DefaultTableModel(dao.findPartPatient(),manage.v);
			        	manage.getTable().setModel(manage.model);
			        	manage.getTable().updateUI();
			        	for(int i=0;i<ids.size();i++) {
			        		String id=ids.get(i).toString();
			        		if(id.equals(showPane.getIdText().getText())) {
				        		showPane.getIdText().setText("");
				        		showPane.getNameText().setText("");
				        		showPane.getPhoneText().setText("");
				        		showPane.getSexText().setText("");
				        		showPane.getAgeText().setText("");
				        		showPane.getRemarkText().setText("");
				        		showPane.getOrderAmountTxt().setText("");
				        		showPane.getProAmountTxt().setText("");
				        		DefaultTableModel orderModel=showPane.getOrderModel();
				        		orderModel.setRowCount(0);
				        		DefaultTableModel proModel=showPane.getProModel();
				        		proModel.setRowCount(0);
				        	}
			        	}
	
			        	JOptionPane.showMessageDialog(manage, "删除患者信息成功！");
		            }	
		        }
			}
		}else if(name.equals("查询")) {
			String value=manage.getTxt().getText().trim();
			dao=new PatientManagementDaoImpl();
			manage.model=new DefaultTableModel(dao.findPatientLike(value),manage.v);
			manage.getTable().setModel(manage.model);
			manage.getTable().updateUI();
		}else if(name.equals("退出")) {
			int result = JOptionPane.showConfirmDialog(null, "你确定要退出？"); 
			if(result==JOptionPane.OK_OPTION) {
				manage.dispose();
			}
		}
	}

}
