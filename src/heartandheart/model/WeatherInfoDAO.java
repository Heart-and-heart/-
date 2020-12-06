package heartandheart.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

import Util.DBUtil;
import heartandheart.exception.NotExistException;
import heartandheart.model.dto.WeatherInfo;

//관리자가 날씨정보 추가, 조회, 삭제 하기 위한 클래스
public class WeatherInfoDAO {
	static Properties sqlAll = DBUtil.getSqlAll();
	
	// 모든 날씨 정보 가져오기
	public static ArrayList<WeatherInfo> getAllWeatherInfo() throws SQLException, NotExistException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<WeatherInfo> datas = null;
		
		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sqlAll.getProperty("weather.getAll"));
			datas = new ArrayList<WeatherInfo>();			
			rset = pstmt.executeQuery();
			
			while (rset.next()) {
				datas.add(new WeatherInfo(rset.getInt(1), rset.getString(2)));
			}
			if (datas.size()==0) {
				throw new NotExistException("날씨정보 조회 결과 반환값 없음");
			}
		} finally {
			DBUtil.close(con, pstmt,rset);
		}
		return datas;
	}
	
	public static WeatherInfo getWeatherInfo() throws SQLException, NotExistException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sqlAll.getProperty("weather.getAll"));
			rset = pstmt.executeQuery();
			
			if (rset.next()) {
				return new WeatherInfo(rset.getInt(1), rset.getString(2));
			} else {
				throw new NotExistException("날씨정보 조회 결과 반환값 없음");
			}
		} finally {
			DBUtil.close(con, pstmt,rset);
		}
	}
	
	// 날씨정보 추가
	public static boolean addWeatherInfo(String weatherStat) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sqlAll.getProperty("weather.insert"));
			pstmt.setString(1, weatherStat);
			
			if (pstmt.executeUpdate()==1) {
				return true;
			}
		} finally {
			DBUtil.close(con, pstmt);
		}
		return false;
	}
	
	// 날씨정보 삭제
	public static boolean deleteWeatherInfo(int weatherNo) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sqlAll.getProperty("weather.delete"));
			pstmt.setInt(1,weatherNo);
			
			if (pstmt.executeUpdate()==1) {
				return true;
			}
		} finally {
			DBUtil.close(con, pstmt);
		}
		return false;
	}
	
	// 날씨정보 수정
	public static boolean updateWeatherInfo(int weatherNo, String weatherStat) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sqlAll.getProperty("weather.update"));
			pstmt.setString(1, weatherStat);
			pstmt.setInt(2, weatherNo);
			
			if (pstmt.executeUpdate()==1) {
				return true;
			}
		} finally {
			DBUtil.close(con, pstmt);
		}
		return false;
	}
}