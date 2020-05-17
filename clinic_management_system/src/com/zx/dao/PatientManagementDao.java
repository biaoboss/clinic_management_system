package com.zx.dao;

import java.util.Vector;

import com.zx.vo.PatientVo;
/**
 * 
 * @author luo
 *
 */
public interface PatientManagementDao {
	/**
	 * 查询患者基本信息
	 * @return
	 */
	public Vector FindPatient();
	/**
	 * 查询患者表中的部分信息
	 * @return
	 */
	public Vector findPartPatient();
	/**
	 * 增加患者信息
	 */
	public int addPatient(PatientVo patientVo);
	/**
	 * 根据患者相关信息模糊查询患者信息
	 * @return
	 */
	public Vector findPatientLike(String value);
	
	/**
	 * 根据id删除患者信息
	 */
	public int deletePatientById(String id);
	/**
	 * 根据id修改患者信息
	 */
    public int modifyPatientById(PatientVo patient);
    /**
     * 根据id查询患者信息，并封装为一个PatientVo对象
     */
    public PatientVo findPatientById(String id);
    /**
     * 查询是否有相同编号的患者
     */
    public boolean isExistSameID(String id);
}
