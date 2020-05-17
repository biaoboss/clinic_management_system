package com.zx.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import com.zx.dao.PatientManagementDao;
import com.zx.util.C3P0Utils;
import com.zx.util.RealseResource;
import com.zx.vo.PatientVo;
/**
 * 
 * @author luo
 *
 */
public class PatientManagementDaoImpl implements PatientManagementDao {

	/**
	 * 查找患者所有信息
	 */
	public Vector FindPatient() {
		Vector pats=new Vector();
		Connection conn=C3P0Utils.getConnection();
		PreparedStatement pstat=null;
		ResultSet rs=null;
		String sql="select * from patient";
		try {
			pstat=conn.prepareStatement(sql);
			rs=pstat.executeQuery();
			while(rs.next()){
				Vector pat=new Vector();
				pat.add(rs.getString("pat_id"));
				pat.add(rs.getString("pat_name"));
				pat.add(rs.getString("pat_sex"));
				pat.add(rs.getInt("pat_age"));
				pat.add(rs.getString("pat_phone"));
				pat.add(rs.getString("pat_address"));
				pat.add(rs.getString("pat_id_card"));
				pat.add(rs.getString("pat_nation"));
				pat.add(rs.getString("pat_brith"));
				pat.add(rs.getString("pat_job"));
				pat.add(rs.getInt("pat_height"));
				pat.add(rs.getInt("pat_weight"));
				pat.add(rs.getInt("pat_blood_pressure"));
				pat.add(rs.getInt("pat_temperature"));
				pat.add(rs.getString("pat_remarks"));
				pats.add(pat);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			RealseResource.release(null, pstat, rs);
		}
		return pats;
	}
	
	/**
	 * 查找患者部分信息
	 */
	public Vector findPartPatient() {
		Vector pats=new Vector();
		Vector pat=null;
		Connection conn=C3P0Utils.getConnection();
		PreparedStatement pstat=null;
		ResultSet rs=null;
		String sql="select pat_id,pat_name,pat_sex,pat_age,pat_phone,pat_address,pat_nation,pat_id_card from patient";
		try {
			pstat=conn.prepareStatement(sql);
			rs=pstat.executeQuery();
			while(rs.next()){
				pat=new Vector();
				pat.add(rs.getString("pat_id"));
				pat.add(rs.getString("pat_name"));
				pat.add(rs.getString("pat_sex"));
				pat.add(rs.getInt("pat_age"));
				pat.add(rs.getString("pat_phone"));
				pat.add(rs.getString("pat_address"));
				pat.add(rs.getString("pat_nation"));
				pat.add(rs.getString("pat_id_card"));
				pats.add(pat);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			RealseResource.release(null, pstat, rs);
		}
		return pats;
	}
	/**
	 * 添加患者信息
	 */
	public int addPatient(PatientVo patientVo) {
		Connection conn=C3P0Utils.getConnection();
		int row=0;
		PreparedStatement pstat=null;
		ResultSet rs=null;
		String sql="insert into patient values(?,?,?,?,?,?,?,?,to_date(?,'yy-mm-dd'),?,?,?,?,?,?)";
		try {
			pstat=conn.prepareStatement(sql);
			pstat.setString(1, patientVo.getId());
			pstat.setString(2, patientVo.getName());
			pstat.setString(3, patientVo.getSex());
			pstat.setInt(4, patientVo.getAge());
			pstat.setString(5, patientVo.getPhone());
			pstat.setString(6, patientVo.getAddress());
			pstat.setString(7, patientVo.getIdCard());
			pstat.setString(8, patientVo.getNation());
			pstat.setString(9, patientVo.getBrith());
			pstat.setString(10, patientVo.getJob());
			pstat.setDouble(11, patientVo.getHeight());
			pstat.setDouble(12, patientVo.getWeight());
			pstat.setDouble(13, patientVo.getBloodPressure());
			pstat.setDouble(14, patientVo.getTemperature());
			pstat.setString(15, patientVo.getRemarks());
			row=pstat.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			RealseResource.release(null, pstat, rs);
		}
		return row;
	}
	/**
	 * 根据患者编号、姓名、联系电话、地址模糊查找患者信息
	 */
	public Vector findPatientLike(String value) {
		Vector pats=new Vector();
		Connection conn=C3P0Utils.getConnection();
		PreparedStatement pstat=null;
		ResultSet rs=null;
		String sql="select pat_id,pat_name,pat_sex,pat_age,pat_phone,pat_address,pat_nation,pat_id_card from patient "
				+ "where pat_id like ? or pat_name like ? or pat_phone like ? or pat_address like ?";
		try {
			
			pstat=conn.prepareStatement(sql);
			pstat.setString(1, "%"+value+"%");
			pstat.setString(2, "%"+value+"%");
			pstat.setString(3, "%"+value+"%");
			pstat.setString(4, "%"+value+"%");
			rs=pstat.executeQuery();
			while(rs.next()){
				Vector pat =new Vector();
				pat.add(rs.getString("pat_id"));
				pat.add(rs.getString("pat_name"));
				pat.add(rs.getString("pat_sex"));
				pat.add(rs.getInt("pat_age"));
				pat.add(rs.getString("pat_phone"));
				pat.add(rs.getString("pat_address"));
				pat.add(rs.getString("pat_nation"));
				pat.add(rs.getString("pat_id_card"));
				pats.add(pat);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			RealseResource.release(null, pstat, rs);
		}
		return pats;
	}
	/**
	 * 根据患者编号删除患者信息
	 */
	public int deletePatientById(String id) {
		Connection conn=C3P0Utils.getConnection();
		PreparedStatement pstat=null;
		int row=0;
		String sql="delete from patient where pat_id=?";
		try {
			pstat=conn.prepareStatement(sql);
			pstat.setString(1, id);
			row=pstat.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			RealseResource.release(null, pstat, null);
		}
		return row;
	}
	/**
	 * 根据id修改患者信息
	 */
	public int modifyPatientById(PatientVo patient) {
		Connection conn=C3P0Utils.getConnection();
		PreparedStatement pstat=null;
		int row=0;
		String sql="update patient set pat_name=?,pat_sex=?,pat_age=?,pat_phone=?,pat_address=?,"
				+ "pat_id_card=?,pat_nation=?,pat_brith=to_date(?,'yyyy-mm-dd'),pat_job=?,pat_height=?,pat_weight=?,"
				+ "pat_blood_pressure=?,pat_temperature=?,pat_remarks=? where pat_id=?";
		try {
			pstat=conn.prepareStatement(sql);
			pstat.setString(1, patient.getName());
			pstat.setString(2, patient.getSex());
			pstat.setInt(3, patient.getAge());
			pstat.setString(4,patient.getPhone());
			pstat.setString(5, patient.getAddress());
			pstat.setString(6, patient.getIdCard());
			pstat.setString(7, patient.getNation());
			pstat.setString(8, patient.getBrith());
			pstat.setString(9, patient.getJob());
			pstat.setDouble(10, patient.getHeight());
			pstat.setDouble(11, patient.getWeight());
			pstat.setDouble(12, patient.getBloodPressure());
			pstat.setDouble(13, patient.getTemperature());
			pstat.setString(14, patient.getRemarks());
			pstat.setString(15, patient.getId());
			row=pstat.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			RealseResource.release(null, pstat, null);
		}
		return row;
	}
	/**
     * 根据id查询患者信息，并封装为一个PatientVo对象
     */
    public PatientVo findPatientById(String id) {
    	PatientVo patVo=null;
    	Connection conn=C3P0Utils.getConnection();
		PreparedStatement pstat=null;
		ResultSet rs=null;
		String sql="select pat_id, pat_name,pat_sex,pat_age,pat_phone,pat_address,pat_id_card,"
				+ "pat_nation,to_char(pat_brith,'YYYY-MM-dd') pat_brith,pat_job,pat_height,pat_height,pat_blood_pressure,"
				+ "pat_temperature,pat_remarks from patient where pat_id=?";
		try {
			pstat=conn.prepareStatement(sql);
			pstat.setString(1, id);
			rs=pstat.executeQuery();
			if(rs.next()) {
				patVo=new PatientVo();
				patVo.setId(rs.getString("pat_id"));
				patVo.setName(rs.getString("pat_name"));
				patVo.setSex(rs.getString("pat_sex"));
				patVo.setAge(rs.getInt("pat_age"));
				patVo.setPhone(rs.getString("pat_phone"));
				patVo.setAddress(rs.getString("pat_address"));
				patVo.setIdCard(rs.getString("pat_id_card"));
				patVo.setNation(rs.getString("pat_nation"));
				patVo.setBrith(rs.getString("pat_brith"));
				patVo.setJob(rs.getString("pat_job"));
				patVo.setHeight(rs.getDouble("pat_height"));
				patVo.setWeight(rs.getDouble("pat_height"));
				patVo.setBloodPressure(rs.getDouble("pat_blood_pressure"));
				patVo.setTemperature(rs.getDouble("pat_temperature"));
				patVo.setRemarks(rs.getString("pat_remarks"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			RealseResource.release(null, pstat, null);
		}
		return patVo;
    }
    /**
     * 查询是否有相同编号的患者
     */
    public boolean isExistSameID(String id) {
    	Connection conn=C3P0Utils.getConnection();
		PreparedStatement pstat=null;
		ResultSet rs=null;
		String sql="select * from patient where pat_id=?";
		try {
			pstat=conn.prepareStatement(sql);
			pstat.setString(1, id);
			rs=pstat.executeQuery();
			if(rs.next()) {
			    RealseResource.release(null, pstat, rs);
			    return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
            RealseResource.release(null, pstat, rs);
        }
    	return false;
    }

}
