package com.zx.view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.jdesktop.swingx.JXDatePicker;

import com.zx.control.PatientAddAction;
import com.zx.util.DataUtil;
import com.zx.util.GBC;
/**
 * 
 * @author luo
 *
 */
public class PatientAddDialog extends JDialog {
	private JButton btnKeep,btnQuit;
	private JTextField txtId,txtName,txtAge,txtPhone,txtAddress,txtIdCard,txtNation,txtJob,
	                   txtHeight,txtWeight,txtBloodPressure,txtTemperature,txtRemarks;
	private JComboBox boxSex,boxBrith;
	private JPanel panel,panelCenter;
	private JDialog dateDialog;
	private PatientManagementDialog patientManagementDialog;
	public PatientAddDialog(PatientManagementDialog patientManagementDialog) {
		this.patientManagementDialog = patientManagementDialog;
		//按钮实例化
		btnKeep=new JButton("保存");
		btnKeep.addActionListener(new PatientAddAction(this,patientManagementDialog));
		btnQuit=new JButton("退出");
		btnQuit.addActionListener(new PatientAddAction(this,patientManagementDialog));
		
		//文本框实例化
		txtId=new JTextField(10);
		txtName=new JTextField(10);
		txtPhone=new JTextField(10);
		txtAddress=new JTextField(10);
		txtIdCard=new JTextField(10);
		txtNation=new JTextField(10);
		txtJob=new JTextField(10);
		txtHeight=new JTextField(10);
		txtWeight=new JTextField(10);
		txtBloodPressure=new JTextField(10);
		txtTemperature=new JTextField(10);
		txtRemarks=new JTextField(35);
		txtAge=new JTextField(10);
		
		//性别下拉列表框实例化
		String [] sex= {"男","女"};
		boxSex=new JComboBox(sex);
		
		boxSex.setEditable(true);
		boxSex.setEnabled(true);
		
		//日期下拉列表框实例化
		boxBrith=new JComboBox();
		boxBrith.setEditable(true);
		boxBrith.setEnabled(true);
		//设置下拉列表框的值为当天时间
		boxBrith.addItem(DataUtil.getCurrentTime().substring(0, 10));
		
		panel=new JPanel();
		panel.setLayout(new GridBagLayout());
		panel.add(new JLabel("患者编号:"),new GBC(0,0).setInsets(10,0,10,0));
		panel.add(txtId,new GBC(1,0).setInsets( 10,30,10,10));
		panel.add(new JLabel("患者姓名:"),new GBC(2,0).setInsets(10, 0, 10, 30));
		panel.add(txtName,new GBC(3,0).setInsets(10,20,10,15));
		panel.add(new JLabel("性       别:"),new GBC(0,1).setInsets(10,0,10,0));
		panel.add(boxSex,new GBC(1,1).setInsets(10,30,10,10));
		panel.add(new JLabel("年        龄:"),new GBC(2,1).setInsets(10,0,10,30));
		panel.add(txtAge,new GBC(3,1).setInsets(10,20,10,15));
		panel.add(new JLabel("联系电话:"),new GBC(0,2).setInsets(10,0,10,0));
		panel.add(txtPhone,new GBC(1,2).setInsets(10,30,10,10));
		panel.add(new JLabel("地      址:"),new GBC(2,2).setInsets(10,0,10,30));
		panel.add(txtAddress,new GBC(3,2).setInsets(10,20,10,15));
		panel.add(new JLabel("身份证号:"),new GBC(0,3).setInsets(10,0,10,0));
		panel.add(txtIdCard,new GBC(1,3).setInsets(10,30,10,10));
		panel.add(new JLabel("民      族:"),new GBC(2,3).setInsets(10,0,10,30));
		panel.add(txtNation,new GBC(3,3).setInsets(10,20,10,15));
		panel.add(new JLabel("出生日期:"),new GBC(0,4).setInsets(10,0,10,0));
		panel.add(boxBrith,new GBC(1,4).setInsets(10,30,10,10));
		panel.add(new JLabel("职       业:"),new GBC(2,4).setInsets(10,0,10,30));
		panel.add(txtJob,new GBC(3,4).setInsets(10,20,10,15));
		panel.add(new JLabel("身       高:"),new GBC(0,5).setInsets(10,0,10,0));
		panel.add(txtHeight,new GBC(1,5).setInsets(10,30,10,10));
		panel.add(new JLabel("体      重:"),new GBC(2,5).setInsets(10,0,10,30));
		panel.add(txtWeight,new GBC(3,5).setInsets(10,20,10,15));
		panel.add(new JLabel("血       压:"),new GBC(0,6).setInsets(10,0,10,0));
		panel.add(txtBloodPressure,new GBC(1,6).setInsets(10,30,10,10));
		panel.add(new JLabel("体      温:"),new GBC(2,6).setInsets(10,0,10,30));
		panel.add(txtTemperature,new GBC(3,6).setInsets(10,20,10,15));
		
		panelCenter=new JPanel();
		
		panelCenter.add(new JLabel("备       注:   "),FlowLayout.LEFT);
		panelCenter.add(txtRemarks);
		
		JPanel panelBtn=new JPanel();
		panelBtn.add(btnKeep);
		panelBtn.add(btnQuit);
		this.setSize(500, 400);
		this.setLocationRelativeTo(patientManagementDialog);
		this.add(panel,BorderLayout.NORTH);
		this.add(panelCenter,BorderLayout.CENTER);
		this.add(panelBtn,BorderLayout.SOUTH);
		this.setTitle("增加患者");
		this.setModal(true);
		this.setVisible(true);
	}
	/**
	 * 时间选择器
	 * @return
	 */
	public JXDatePicker buildTdate() {
		JXDatePicker p = new JXDatePicker();
		p.getEditor().getText();
		JPanel panel = new JPanel();
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		JXDatePicker pick = new JXDatePicker();
		
		pick.setFormats(sdf);
		
		pick.setDate(date);
		
		pick.setBounds(137, 83, 177, 24);
		 
		panel.add(pick);
		
		JButton b = new JButton("获取时间");
		
		b.setBounds(137, 183, 100, 30);
		
		panel.add(b);
		return pick;
	}
	public JTextField getTxtId() {
		return txtId;
	}
	public void setTxtId(JTextField txtId) {
		this.txtId = txtId;
	}
	public JTextField getTxtName() {
		return txtName;
	}
	public void setTxtName(JTextField txtName) {
		this.txtName = txtName;
	}
	public JTextField getTxtAge() {
		return txtAge;
	}
	public void setTxtAge(JTextField txtAge) {
		this.txtAge = txtAge;
	}
	public JTextField getTxtPhone() {
		return txtPhone;
	}
	public void setTxtPhone(JTextField txtPhone) {
		this.txtPhone = txtPhone;
	}
	public JTextField getTxtAddress() {
		return txtAddress;
	}
	public void setTxtAddress(JTextField txtAddress) {
		this.txtAddress = txtAddress;
	}
	public JTextField getTxtIdCard() {
		return txtIdCard;
	}
	public void setTxtIdCard(JTextField txtIdCard) {
		this.txtIdCard = txtIdCard;
	}
	public JTextField getTxtNation() {
		return txtNation;
	}
	public void setTxtNation(JTextField txtNation) {
		this.txtNation = txtNation;
	}
	public JTextField getTxtJob() {
		return txtJob;
	}
	public void setTxtJob(JTextField txtJob) {
		this.txtJob = txtJob;
	}
	public JTextField getTxtHeight() {
		return txtHeight;
	}
	public void setTxtHeight(JTextField txtHeight) {
		this.txtHeight = txtHeight;
	}
	public JTextField getTxtWeight() {
		return txtWeight;
	}
	public void setTxtWeight(JTextField txtWeight) {
		this.txtWeight = txtWeight;
	}
	public JTextField getTxtBloodPressure() {
		return txtBloodPressure;
	}
	public void setTxtBloodPressure(JTextField txtBloodPressure) {
		this.txtBloodPressure = txtBloodPressure;
	}
	public JTextField getTxtTemperature() {
		return txtTemperature;
	}
	public void setTxtTemperature(JTextField txtTemperature) {
		this.txtTemperature = txtTemperature;
	}
	public JTextField getTxtRemarks() {
		return txtRemarks;
	}
	public void setTxtRemarks(JTextField txtRemarks) {
		this.txtRemarks = txtRemarks;
	}
	public JComboBox getBoxSex() {
		return boxSex;
	}
	public void setBoxSex(JComboBox boxSex) {
		this.boxSex = boxSex;
	}
	public JComboBox getBoxBrith() {
		return boxBrith;
	}
	public void setBoxBrith(JComboBox boxBrith) {
		this.boxBrith = boxBrith;
	}
	
}
