package heartandheart.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import heartandheart.model.dto.WeatherInfo;
import heartandheart.model.util.DBUtil;

public class WeatherInfoDAO {
	// ������
	// ��� ���� ���� ��������
	public static ArrayList<WeatherInfo> getAllWeatherInfo() throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<WeatherInfo> datas = null;
		
		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(DBUtil.getSqlAll().getProperty(""));
			rset = pstmt.executeQuery();
			
			while (rset.next()) {
				datas.add(new WeatherInfo(rset.getInt(1), rset.getString(2)));
			}
		} finally {
			DBUtil.close(con, pstmt);
		}
		return datas;
	}
	
	// �������� ����
	public static WeatherInfo getWeatherInfo(int emotionNo) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(DBUtil.getSqlAll().getProperty(""));
			rset = pstmt.executeQuery();
			
			if (rset.next()) {
				return new WeatherInfo(rset.getInt(1), rset.getString(2));
			}
		} finally {
			DBUtil.close(con, pstmt);
		}
		return null;
	}
	
	// �������� �߰�
	public static boolean addWeatherInfo(int weatherNo, String weatherStats) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(DBUtil.getSqlAll().getProperty(""));
			
			if (pstmt.executeUpdate()==1) {
				return true;
			}
		} finally {
			DBUtil.close(con, pstmt);
		}
		return false;
	}
	
	// �������� ����
	public static boolean deleteWeatherInfo(int emotionNo) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(DBUtil.getSqlAll().getProperty(""));
			
			if (pstmt.executeUpdate()==1) {
				return true;
			}
		} finally {
			DBUtil.close(con, pstmt);
		}
		return false;
	}
	
	// �������� ����
	public static boolean updateWeatherInfo(int weatherNo, String newweatherStats) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(DBUtil.getSqlAll().getProperty(""));
			
			if (pstmt.executeUpdate()==1) {
				return true;
			}
		} finally {
			DBUtil.close(con, pstmt);
		}
		return false;
	}
}
