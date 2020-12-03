package heartandheart.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import heartandheart.model.dto.EmotionInfo;
import heartandheart.model.util.DBUtil;

public class EmtionInfoDAO {
	// ������
	// ��� ���� ���� ��������
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
	
	// �������� ����
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
	
	// �������� �߰�
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
	
	// �������� ����
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
	
	// �������� ����
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
