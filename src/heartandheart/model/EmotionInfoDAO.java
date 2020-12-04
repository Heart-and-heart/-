package heartandheart.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

import Util.DBUtil;
import heartandheart.model.dto.EmotionInfo;

//관리자가감정정보 추가, 조회, 삭제, 수정 하기 위한 클래스
public class EmotionInfoDAO {
	static Properties sqlAll = DBUtil.getSqlAll();
		// 모든 감정 정보 가져오기
		public static ArrayList<EmotionInfo> getAllEmotionInfo() throws SQLException {
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rset = null;
			ArrayList<EmotionInfo> datas = null;
			
			try {
				con = DBUtil.getConnection();
				pstmt = con.prepareStatement(sqlAll.getProperty("emotion.getAll"));
				rset = pstmt.executeQuery();
				
				while (rset.next()) {
					datas.add(new EmotionInfo(rset.getInt(1), rset.getString(2)));
				}
			} finally {
				DBUtil.close(con, pstmt,rset);
			}
			return datas;
		}
		
		// 감정정보 추가
		public static boolean addEmotionInfo(int emotionNo, String emotionStat) throws SQLException {
			Connection con = null;
			PreparedStatement pstmt = null;
			
			try {
				con = DBUtil.getConnection();
				pstmt = con.prepareStatement(sqlAll.getProperty("emotion.insert"));
				pstmt.setInt(1, emotionNo);
				pstmt.setString(2, emotionStat);
				
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
				pstmt = con.prepareStatement(sqlAll.getProperty("emotion.delete"));
				pstmt.setInt(1,emotionNo);
				
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
				pstmt = con.prepareStatement(sqlAll.getProperty("emotion.update"));
				pstmt.setString(1, newemotionStat);
				pstmt.setInt(2, emotionNo);
				
				if (pstmt.executeUpdate()==1) {
					return true;
				}
			} finally {
				DBUtil.close(con, pstmt);
			}
			return false;
		}
}
