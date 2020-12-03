package heartandheart.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import heartandheart.model.dto.EmotionInfo;
import heartandheart.model.util.DBUtil;

public class EmtionInfoDAO {
	// 관리자
	// 모든 감정 정보 가져오기
	public static ArrayList<EmotionInfo> getAllEmotionInfo() throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<EmotionInfo> datas = null;
		
		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(DBUtil.getSqlAll().getProperty(""));
			rset = pstmt.executeQuery();
			
			while (rset.next()) {
				datas.add(new EmotionInfo(rset.getInt(1), rset.getString(2)));
			}
		} finally {
			DBUtil.close(con, pstmt);
		}
		return datas;
	}
	
	// 감정정보 선택
	public static EmotionInfo getEmotionInfo(int emotionNo) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(DBUtil.getSqlAll().getProperty(""));
			rset = pstmt.executeQuery();
			
			if (rset.next()) {
				return new EmotionInfo(rset.getInt(1), rset.getString(2));
			}
		} finally {
			DBUtil.close(con, pstmt);
		}
		return null;
	}
	
	// 감정정보 추가
	public static boolean addEmotionInfo(int emotionNo, String emotionStat) throws SQLException {
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
	
	// 감정정보 삭제
	public static boolean deleteEmotionInfo(int emotionNo) throws SQLException {
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
	
	// 감정정보 수정
	public static boolean updateEmotionInfo(int emotionNo, String newemotionStat) throws SQLException {
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
