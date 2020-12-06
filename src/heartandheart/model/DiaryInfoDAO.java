package heartandheart.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

import Util.DBUtil;
import heartandheart.exception.NotExistException;
import heartandheart.model.dto.DiaryInfo;

public class DiaryInfoDAO {
	static Properties sqlAll = DBUtil.getSqlAll();

	// 관리자
	// 모든 다이어리 정보 가져오기
	public static ArrayList<DiaryInfo> getAllDiarys() throws SQLException, NotExistException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<DiaryInfo> datas = null;

		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sqlAll.getProperty("diary.getAll"));
			rset = pstmt.executeQuery();
			datas = new ArrayList<DiaryInfo>();

			while (rset.next()) {
				datas.add(new DiaryInfo(rset.getInt(1), rset.getString(2), rset.getInt(3), rset.getInt(4),
						rset.getString(5), rset.getInt(6), rset.getString(7), rset.getInt(8)));
			}
			if (datas.size()==0) {
				throw new NotExistException("반환되는 다이어리 정보가 없습니다.");
			}
		} finally {
			DBUtil.close(con, pstmt, rset);
		}
		return datas;
	}

	// 회원 crud
	// 작성자 기준으로 다이어리 정보 가져오기
	public static ArrayList<DiaryInfo> USRDiaryInfo(String id) throws SQLException, NotExistException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<DiaryInfo> userdatas = null;
		try {
			con = DBUtil.getConnection();
			userdatas=new ArrayList<DiaryInfo>();
			
			pstmt = con.prepareStatement(sqlAll.getProperty("diary.getUSR"));
			pstmt.setString(1, id);		
			rset = pstmt.executeQuery();

			while (rset.next()) {
				userdatas.add(new DiaryInfo(rset.getInt(1), rset.getString(2), rset.getInt(3), rset.getInt(4),
						rset.getString(5), rset.getInt(6), rset.getString(7), rset.getInt(8)));
			}
			if (userdatas.size()==0) {
				throw new NotExistException("반환되는 다이어리 정보가 없습니다.");
			}
		}finally {
			DBUtil.close(con, pstmt, rset);
		}
		return userdatas;
	}
	
	// 다이어리 수정
	public static boolean updateDiaryInfo(String newDiaryComment, int diaryno) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sqlAll.getProperty("diary.update"));
			pstmt.setString(1, newDiaryComment);
			pstmt.setInt(2, diaryno);

			if (pstmt.executeUpdate() == 1) {
				return true;
			}
		} finally {
			DBUtil.close(con, pstmt);
		}
		return false;
	}

	// 다이어리 쓰기
	public static boolean writeDiary(String id, int emotionNo, int weatherNo, int sleepingTime,
			String diaryComment, int isPublic) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			System.out.println(id+"/"+emotionNo+"/"+weatherNo+"/"+sleepingTime+"/"+diaryComment+"/"+isPublic);
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sqlAll.getProperty("diary.insert"));
			pstmt.setString(1, id);
			pstmt.setInt(2, emotionNo);
			pstmt.setInt(3, weatherNo);
			pstmt.setInt(4, sleepingTime);
			pstmt.setString(5, diaryComment);
			pstmt.setInt(6, isPublic);

			if (pstmt.executeUpdate()==1) {
				return true;
			}
		} finally {
			DBUtil.close(con, pstmt);
		}		
		return false;
	}

	// 다이어리 삭제
	public static boolean deleteDiary(int diaryNo, String id) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sqlAll.getProperty("diary.deleteDiary"));
			pstmt.setInt(1, diaryNo);
			pstmt.setString(2, id);

			if (pstmt.executeUpdate() == 1) {
				return true;
			}
		} finally {
			DBUtil.close(con, pstmt);
		}
		return false;
	}

	// 커플의 다이어리 정보 가져오기
	public static ArrayList<DiaryInfo> howMatchingIdDoes(String id) throws SQLException, NotExistException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<DiaryInfo> datas = null;
		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sqlAll.getProperty("diary.howMatchingIdDoes"));
			pstmt.setString(1, id);
			datas = new ArrayList<DiaryInfo>();			
			rset = pstmt.executeQuery();

			while (rset.next()) {
				datas.add(new DiaryInfo(rset.getInt(1), rset.getString(2), rset.getInt(3), rset.getInt(4),
						rset.getString(5), rset.getInt(6), rset.getString(7), rset.getInt(8)));
			}
			
			if (datas.size()==0) {
				throw new NotExistException("반환되는 다이어리 정보가 없습니다.");
			}
		} finally {
			DBUtil.close(con, pstmt, rset);
		}
		return datas;
	}

	// 감정 기준으로 다른 사람들의 다이어리 정보 가져오기 (공개여부 고려하여)
	public static ArrayList<String> otherPeopleFeels(String id) throws SQLException, NotExistException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<String> datas = null;

		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sqlAll.getProperty("diary.otherPeopleFeels"));
			pstmt.setString(1, id);
			datas = new ArrayList<String>();			
			rset = pstmt.executeQuery();

			while (rset.next()) {
				datas.add(rset.getString(2));
			}
			if (datas.size()==0) {
				throw new NotExistException("반환되는 다이어리 정보가 없습니다.");
			}
		} finally {
			DBUtil.close(con, pstmt, rset);
		}
		return datas;
	}

	// 지난주 감정정보 보기
	public static double lastWeekEmotion(String id) throws SQLException, NotExistException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sqlAll.getProperty("func.EmoLastWeek"));
			pstmt.setString(1, id);
			rset = pstmt.executeQuery();

			if (rset.next()) {
				return rset.getDouble(1);
			} else {
				throw new NotExistException("정보가 없습니다.");
			}
		} finally {
			DBUtil.close(con, pstmt, rset);
		}
	}

	// 이번주 감정정보 보기
	public static double thisWeekEmotion(String id) throws SQLException, NotExistException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sqlAll.getProperty("func.EmoThisWeek"));
			pstmt.setString(1, id);
			rset = pstmt.executeQuery();

			if (rset.next()) {
				return rset.getDouble(1);
			} else {
				throw new NotExistException("정보가 없습니다.");
			}
		} finally {
			DBUtil.close(con, pstmt, rset);
		}
	}

	// 지난주 수면시간 보기
	public static double lastWeekSleep(String id) throws SQLException, NotExistException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sqlAll.getProperty("func.SleepThisWeek"));
			pstmt.setString(1, id);
			rset = pstmt.executeQuery();

			if (rset.next()) {
				return rset.getDouble(1);
			} else {
				throw new NotExistException("정보가 없습니다.");
			}
		} finally {
			DBUtil.close(con, pstmt, rset);
		}
	}

	// 이번주 수면시간 보기
	public static double thisWeekSleep(String id) throws SQLException, NotExistException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sqlAll.getProperty("func.SleepAVGMine"));
			pstmt.setString(1, id);
			rset = pstmt.executeQuery();

			if (rset.next()) {
				return rset.getDouble(1);
			} else {
				throw new NotExistException("정보가 없습니다.");
			}
		} finally {
			DBUtil.close(con, pstmt, rset);
		}
	}
}