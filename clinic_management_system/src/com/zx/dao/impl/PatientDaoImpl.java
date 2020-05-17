package com.zx.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.JOptionPane;

import com.zx.dao.PatientDao;
import com.zx.util.C3P0Utils;
import com.zx.util.RealseResource;
import com.zx.vo.PatientVo;
/**
 * 
 * @author lei
 *
 */
public class PatientDaoImpl implements PatientDao {

	public Vector getPatients() {
		Vector patients=new Vector();
		Vector patient;
		Connection conn =null;;
		String sql="select pat_id,pat_name,pat_phone,pat_address,pat_remarks from patient";
		PreparedStatement pstmt=null;
		ResultSet result=null;
		try {
			conn= C3P0Utils.getConnection();
			pstmt= conn.prepareStatement(sql);
			result=pstmt.executeQuery();
			while(result.next()) {
				patient=new Vector();
				patient.add(result.getString("pat_id"));
				patient.add(result.getString("pat_name"));
				/*patient.add(rs.getString("pat_sex"));
				patient.add(rs.getInt("pat_age"));*/
				patient.add(result.getString("pat_phone"));
				patient.add(result.getString("pat_address"));
				/*patient.add(rs.getString("pat_id_card"));
				patient.add(rs.getString("pat_nation"));
				patient.add(rs.getDate("pat_brith"));
				patient.add(rs.getString("pat_job"));
				patient.add(rs.getDouble("pet_height"));
				patient.add(rs.getDouble("pat_weight"));
				patient.add(rs.getDouble("pat_blood_pressure"));
				patient.add(rs.getDouble("pat_temperature"));*/
				patient.add(result.getString("pat_remarks"));
				patients.add(patient);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			RealseResource.release(null, pstmt, result);
		}
		

		return patients;
	}

	@Override
	public Vector getPatientsByIdAndName(String gettxt) {
		Vector patients=new Vector();
		Vector patient;
		Connection conn =null;
		String sql="select pat_id,pat_name,pat_phone,pat_address,pat_remarks" + 
				" from patient where pat_id like ? or pat_name like ?";
		PreparedStatement pstmt=null;
		ResultSet result=null;
		try {
			conn= C3P0Utils.getConnection();
			pstmt= conn.prepareStatement(sql);
			pstmt.setString(1, "%"+gettxt+"%");
			pstmt.setString(2, "%"+gettxt+"%");
			result=pstmt.executeQuery();
			while(result.next()) {
				patient=new Vector();
				patient.add(result.getString("pat_id"));
				patient.add(result.getString("pat_name"));
				patient.add(result.getString("pat_phone"));
				patient.add(result.getString("pat_address"));
				patient.add(result.getString("pat_remarks"));
				patients.add(patient);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			RealseResource.release(null, pstmt, result);
		}
		
		return patients;
	}
	public PatientVo getPatientVo(String pat_id) {
		PatientVo patientVo=null;
		String sql="select * from patient where pat_id=?";
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet result=null;
		try {
			conn=C3P0Utils.getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, pat_id);
			result=pstmt.executeQuery();
			if(result.next()) {
				patientVo =new PatientVo();
				patientVo.setId(result.getString("pat_id"));
				patientVo.setName(result.getString("pat_name"));
				patientVo.setPhone(result.getString("pat_phone"));
				patientVo.setSex(result.getString("pat_sex"));
				patientVo.setAge(result.getInt("pat_age"));
				patientVo.setAddress(result.getString("pat_address"));
				patientVo.setRemarks(result.getString("pat_remarks"));
		
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			RealseResource.release(null, pstmt, result);
		}
		
		
		return patientVo;
	}

    

}
