package com.zx.control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import com.zx.dao.PatientDao;
import com.zx.dao.PatientManagementDao;
import com.zx.dao.impl.PatientDaoImpl;
import com.zx.dao.impl.PatientManagementDaoImpl;
import com.zx.view.PatientManagementDialog;
import com.zx.view.PatientModifyDialog;
import com.zx.view.ShowBasicInfomationPane;
import com.zx.vo.PatientVo;
/**
 * luo
 * @author Administrator
 *
 */
public class PatientModifyAction implements ActionListener{
	PatientModifyDialog modifyDialog;
	PatientManagementDialog manageDialog;
	ShowBasicInfomationPane showPane;
	PatientVo patVo;
	PatientManagementDao dao;
	PatientDao daos;
	public PatientModifyAction (PatientModifyDialog modifyDialog,PatientManagementDialog manageDialog,ShowBasicInfomationPane showPane) {
		this.modifyDialog=modifyDialog;
		this.manageDialog=manageDialog;
		this.showPane=showPane;
	}

	public void actionPerformed(ActionEvent e) {
		CheckInputValue check=new CheckInputValue();
		dao=new PatientManagementDaoImpl();
		String btn=e.getActionCommand();
		if(btn.equals("保存")) {
			if(("").equals(modifyDialog.getTxtName().getText())) {
				JOptionPane.showMessageDialog(modifyDialog,"请输入患者姓名！");
			}else if(!check.checkAgeIsLegal(modifyDialog.getTxtAge().getText())) {
				JOptionPane.showMessageDialog(modifyDialog,"输入的年龄不合法，请重新输入三位数以内的整数！");
			}else if(!check.checkHeightIsLegal(modifyDialog.getTxtHeight().getText())) {
				JOptionPane.showMessageDialog(modifyDialog,"输入的身高不合法，请重新输入！如（180.0）");
			}else if(!check.checkValueIsLegal(modifyDialog.getTxtWeight().getText())) {
				JOptionPane.showMessageDialog(modifyDialog,"输入的体重不合法，请重新输入！如（60.5）");
			}else if(!check.checkValueIsLegal(modifyDialog.getTxtBloodPressure().getText())) {
				JOptionPane.showMessageDialog(modifyDialog,"输入的血压不合法，请重新输入！如（120）");
			}else if(!check.checkTemperatureIsLegal(modifyDialog.getTxtTemperature().getText())) {
				JOptionPane.showMessageDialog(modifyDialog,"输入的体温不合法，请重新输入！如（36.7）");
			}else if(modifyDialog.getBoxBrith().getSelectedItem()==null) {
				JOptionPane.showMessageDialog(modifyDialog,"没有输入日期，请输入日期！");
			}else if(!check.checkDateFormateIsLagal(modifyDialog.getBoxBrith().getSelectedItem().toString())){
				JOptionPane.showMessageDialog(modifyDialog,"输入的日期不合法，请输入合法日期，如（2020-05-10）！");
			}else {String id=modifyDialog.getTxtId().getText().trim();
			    String name=modifyDialog.getTxtName().getText().trim();
			    String sex=modifyDialog.getBoxSex().getSelectedItem().toString();
			    int age=Integer.parseInt(modifyDialog.getTxtAge().getText());
			    String phone=modifyDialog.getTxtPhone().getText().trim();
			    String address=modifyDialog.getTxtAddress().getText().trim();
			    String idCard=modifyDialog.getTxtIdCard().getText().trim();
			    String nation=modifyDialog.getTxtNation().getText().trim();
			    String brith=modifyDialog.getBoxBrith().getSelectedItem().toString();
			    String job=modifyDialog.getTxtJob().getText().trim();
			    Double height=Double.valueOf(modifyDialog.getTxtHeight().getText().trim());
			    Double weight=Double.valueOf(modifyDialog.getTxtWeight().getText().trim());
			    Double bloodPressure=Double.valueOf(modifyDialog.getTxtBloodPressure().getText().trim());
			    Double temperature=Double.valueOf(modifyDialog.getTxtTemperature().getText().trim());
			    String remarks=modifyDialog.getTxtRemarks().getText().trim();
			    patVo=new PatientVo(id,name,sex,age,phone,address,idCard,nation,brith,job,height,weight,bloodPressure,temperature,remarks);
			
			    int row=dao.modifyPatientById(patVo);
			    if(row==0) {
				    JOptionPane.showMessageDialog(modifyDialog, "修改患者信息失败！");
			    }else {
				    manageDialog.model=new DefaultTableModel(dao.findPartPatient(),manageDialog.v);
				    manageDialog.getTable().setModel(manageDialog.model);
				    manageDialog.getTable().updateUI();
				    modifyDialog.setVisible(false);
				    JOptionPane.showMessageDialog(modifyDialog, "修改患者信息成功！");
				    daos=new PatientDaoImpl();
				    PatientVo patientVo=daos.getPatientVo(id);
				    if(id.equals(showPane.getIdText().getText())) {
				    	showPane.getPhoneText().setText(patientVo.getPhone());
				    	showPane.getSexText().setText(patientVo.getSex());
				    	showPane.getAgeText().setText(patientVo.getAge()+"");
				    	showPane.getNameText().setText(patientVo.getName());
				    	showPane.getAddrText().setText(patientVo.getAddress());
				    	showPane.getRemarkText().setText(patientVo.getRemarks());

				    }
			    }
			
			}
		}else if(btn.equals("退出")) {
			modifyDialog.dispose();
		}
	}

}
