package com.zx.dao;

import java.util.Vector;

import com.zx.vo.PatientVo;
/**
 * lei
 * @author DELL
 *
 */
public interface  PatientDao {
	//获取患者信息
	public Vector getPatients();
	//根据id或者姓名模糊查询员工信息
	public Vector getPatientsByIdAndName(String pat_id);
	
	public PatientVo getPatientVo(String pat_id);


 
}
