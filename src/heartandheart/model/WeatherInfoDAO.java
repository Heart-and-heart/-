package heartandheart.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import heartandheart.model.dto.WeatherInfo;
import heartandheart.model.util.DBUtil;

public class WeatherInfoDAO {
	// 관리자
	// 모든 날씨 정보 가져오기
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
	
	// 날씨정보 선택
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
	
	// 날씨정보 추가
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
	
	// 날씨정보 삭제
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
	
	// 날씨정보 수정
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
