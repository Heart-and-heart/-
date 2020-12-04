package heartandheart.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

import Util.DBUtil;
import heartandheart.model.dto.DiaryInfo;
import heartandheart.model.dto.UserInfo;

public class DiaryInfoDAO {
	static Properties sqlAll = DBUtil.getSqlAll();
	// 관리자
	// 모든 다이어리 정보 가져오기
	public static ArrayList<DiaryInfo> getAllDiarys() throws SQLException{
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<DiaryInfo> datas = null;

		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sqlAll.getProperty("diary.getAll"));
			rset = pstmt.executeQuery();

			while (rset.next()) {
				datas.add(new DiaryInfo(rset.getInt(1), rset.getString(2), rset.getInt(3), rset.getInt(4), rset.getString(5), rset.getInt(6), rset.getString(7), rset.getInt(8)));
			}
		} finally {
			DBUtil.close(con, pstmt, rset);
		}
		return datas;
	}


	// 회원 crud
	// 작성자 기준으로 다이어리 정보 가져오기
	public static ArrayList<DiaryInfo> USRDiaryInfo(UserInfo user) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<DiaryInfo> userdatas = null;

		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sqlAll.getProperty("diary.getUSR"));
			pstmt.setString(1, user.getId());
			pstmt.setInt(2, user.getPw());

			rset = pstmt.executeQuery();
			while (rset.next()) {
				userdatas.add(new DiaryInfo(rset.getInt(1), rset.getString(2), rset.getInt(3), rset.getInt(4), rset.getString(5), rset.getInt(6), rset.getString(7), rset.getInt(8)));
			}
		} finally {
			DBUtil.close(con, pstmt,rset);
		}
		return userdatas;
	}
	// 다이어리 수정
	public static boolean updateDiaryInfo(String newDiaryComment,int diaryNo, String id) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sqlAll.getProperty("diary.update"));
			pstmt.setString(1, newDiaryComment);
			pstmt.setString(2, id);
			pstmt.setInt(3, diaryNo);
			if (pstmt.executeUpdate()==1) {
				return true;
			}
		} finally {
			DBUtil.close(con, pstmt);
		}
		return false;
	}

	// 다이어리 쓰기
	public static boolean writeDiary(String id, int emotionNo, int weatherNo, String reportingDate, int sleepingTime, String diaryComment, int	isPublic) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sqlAll.getProperty("diary.insert"));
			pstmt.setString(1, id);
			pstmt.setInt(2, emotionNo);
			pstmt.setInt(3, weatherNo);
			pstmt.setString(4, reportingDate);
			pstmt.setInt(5, sleepingTime);
			pstmt.setString(6, diaryComment);
			pstmt.setInt(7, isPublic);
			if (pstmt.executeUpdate()==1) {
				return true;
			}
		} finally {
			DBUtil.close(con, pstmt);
		}
		return false;
	}


	// 다이어리 삭제
	public static boolean deleteDiary(String reportingDate, String id) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sqlAll.getProperty("diary.delete"));
			pstmt.setString(1, reportingDate);
			pstmt.setString(2, id);
			if (pstmt.executeUpdate()==1) {
				return true;
			}
		} finally {
			DBUtil.close(con, pstmt);
		}
		return false;
	}


	// 커플의 다이어리 정보 가져오기
	public static boolean howMatchingIdDoes(String id) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sqlAll.getProperty("user.howMatchingIdDoes"));
			pstmt.setString(1, id);
			if (pstmt.executeUpdate()==1) {
				return true;
			}
		} finally {
			DBUtil.close(con, pstmt);
		}
		return false;
	}

	// 감정 기준으로 다른 사람들의 다이어리 정보 가져오기 (공개여부 고려하여)
	public static boolean otherPeopleFeels(String id) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sqlAll.getProperty("user.otherPeopleFeels"));
			pstmt.setString(1, id);
			if (pstmt.executeUpdate()==1) {
				return true;
			}
		} finally {
			DBUtil.close(con, pstmt);
		}
		return false;
	}
	//  
}
