package com.zx.control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import org.jdesktop.swingx.JXDatePicker;

import com.zx.dao.PatientManagementDao;
import com.zx.dao.impl.PatientManagementDaoImpl;
import com.zx.view.PatientAddDialog;
import com.zx.view.PatientManagementDialog;
import com.zx.view.PatientModifyDialog;
import com.zx.vo.PatientVo;
/**
 * 
 * @author luo
 *
 */
public class PatientAddAction implements ActionListener{
	PatientAddDialog addDialog;
	CheckInputValue check;
	PatientManagementDialog manageDialog;
	PatientVo patVo;
	PatientManagementDao dao;
	public PatientAddAction (PatientAddDialog addDialog,PatientManagementDialog manageDialog) {
		this.addDialog=addDialog;
		this.manageDialog=manageDialog;
	}

	public void actionPerformed(ActionEvent e) {
		dao=new PatientManagementDaoImpl();
		String btn=e.getActionCommand();
		if(btn.equals("保存")) {
			check=new CheckInputValue();
			if(dao.isExistSameID(addDialog.getTxtId().getText())) {
				JOptionPane.showMessageDialog(addDialog,"已存在该编号的患者信息！");
			}else if(("").equals(addDialog.getTxtId().getText())) {
				JOptionPane.showMessageDialog(addDialog,"请输入患者编号！");
			}else if(("").equals(addDialog.getTxtName().getText())) {
				JOptionPane.showMessageDialog(addDialog,"请输入患者姓名！");
			}else if(!check.checkAgeIsLegal(addDialog.getTxtAge().getText())) {
				JOptionPane.showMessageDialog(addDialog,"输入的年龄不合法，请重新输入三位数以内的整数！");
			}else if(!check.checkHeightIsLegal(addDialog.getTxtHeight().getText())) {
				JOptionPane.showMessageDialog(addDialog,"输入的身高不合法，请重新输入！如（180.0）");
			}else if(!check.checkValueIsLegal(addDialog.getTxtWeight().getText())) {
				JOptionPane.showMessageDialog(addDialog,"输入的体重不合法，请重新输入！如（60.5）");
			}else if(!check.checkValueIsLegal(addDialog.getTxtBloodPressure().getText())) {
				JOptionPane.showMessageDialog(addDialog,"输入的血压不合法，请重新输入！如（120）");
			}else if(!check.checkTemperatureIsLegal(addDialog.getTxtTemperature().getText())) {
				JOptionPane.showMessageDialog(addDialog,"输入的体温不合法，请重新输入！如（36.7）");
			}else if(addDialog.getBoxBrith().getSelectedItem()==null) {
				JOptionPane.showMessageDialog(addDialog,"没有输入日期，请输入日期！");
			}else if(!check.checkDateFormateIsLagal(addDialog.getBoxBrith().getSelectedItem().toString())){
				JOptionPane.showMessageDialog(addDialog,"输入的日期不合法，请输入合法日期，如（2020-05-10）！");
			}else {
				String id=addDialog.getTxtId().getText().trim();
				String name=addDialog.getTxtName().getText().trim();
				String sex=addDialog.getBoxSex().getSelectedItem().toString();
				int age=Integer.parseInt(addDialog.getTxtAge().getText());
				String phone=addDialog.getTxtPhone().getText().trim();
				String address=addDialog.getTxtAddress().getText().trim();
				String idCard=addDialog.getTxtIdCard().getText().trim();
				String nation=addDialog.getTxtNation().getText().trim();
				String brith=addDialog.getBoxBrith().getSelectedItem().toString();
				String job=addDialog.getTxtJob().getText().trim();
				Double height=Double.valueOf(addDialog.getTxtHeight().getText().trim());
				Double weight=Double.valueOf(addDialog.getTxtWeight().getText().trim());
				Double bloodPressure=Double.valueOf(addDialog.getTxtBloodPressure().getText().trim());
				Double temperature=Double.valueOf(addDialog.getTxtTemperature().getText().trim());
				String remarks=addDialog.getTxtRemarks().getText().trim();
				patVo=new PatientVo(id,name,sex,age,phone,address,idCard,nation,brith,job,height,weight,bloodPressure,temperature,remarks);
				int row = dao.addPatient(patVo);
				if(row==0) {
					JOptionPane.showMessageDialog(addDialog,"添加患者信息失败！");
				}else {
					System.out.println("成功");
					manageDialog.model=new DefaultTableModel(dao.findPartPatient(),manageDialog.v);
					manageDialog.getTable().setModel(manageDialog.model);
					manageDialog.getTable().updateUI();
					JOptionPane.showMessageDialog(addDialog,"添加患者信息成功！");
				    addDialog.dispose();
				}
			}
		}else if(btn.equals("退出")) {
			addDialog.dispose();
		}
	}
	/**
	 * 判断界面输入的数据是否合法
	 * @return
	 */
	public boolean checkInput() {
		boolean flag=false;
		return flag;
	}

}
