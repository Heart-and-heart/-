package heartandheart.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

import Util.DBUtil;
import heartandheart.exception.NotExistException;
import heartandheart.model.dto.UserInfo;

//관리자가 회원정보 조회, 삭제 하기 위한 클래스
public class UserInfoDAO {
static Properties sqlAll = DBUtil.getSqlAll();
	
	// 모든 회원 정보 가져오기
	public static ArrayList<UserInfo> getAllUserInfo() throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<UserInfo> datas = null;
		
		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sqlAll.getProperty("user.getAll"));
			rset = pstmt.executeQuery();
			
			while (rset.next()) {
				datas.add(new UserInfo(rset.getString(1), rset.getInt(2),rset.getString(3)));
			}
		} finally {
			DBUtil.close(con, pstmt,rset);
		}
		return datas;
	}
	
	// 특정회원정보 반환
		public static UserInfo getUserInfo(String id,int pw) throws SQLException ,NotExistException {
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rset = null;
			UserInfo user = null;
			try {
				con = DBUtil.getConnection();
				pstmt = con.prepareStatement(sqlAll.getProperty("user.getUSR"));
				pstmt.setString(1, id);
				pstmt.setInt(2, pw);
				rset = pstmt.executeQuery();
				
				if(!rset.next()) throw new NotExistException("회원조회결과 반환값 없음.");
				
				user = new UserInfo();
				user.setId(rset.getString(1));
				user.setPw(rset.getInt(2));
								
				return user;
			}finally {
				DBUtil.close(con, pstmt,rset);
			}
		}
	
	// 회원정보 추가
	public static boolean addUserInfo(String id, int pw,String cid) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sqlAll.getProperty("user.insert"));
			pstmt.setString(1, id);
			pstmt.setInt(2, pw);
			pstmt.setString(3, cid);
			
			if (pstmt.executeUpdate()==1) {
				return true;
			}
		} finally {
			DBUtil.close(con, pstmt);
		}
		return false;
	}
	
	// 회원정보 삭제
	public static boolean deleteEmotionInfo(String id, int pw) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sqlAll.getProperty("user.delete"));
			pstmt.setString(1,id);
			pstmt.setInt(2,pw);
			
			if (pstmt.executeUpdate()==1) {
				return true;
			}
		} finally {
			DBUtil.close(con, pstmt);
		}
		return false;
	}
	
	// 회원정보 수정
	public static boolean updateEmotionInfo(String id, int pw) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sqlAll.getProperty("user.update"));
			pstmt.setInt(1, pw);
			pstmt.setString(2, id);
			
			if (pstmt.executeUpdate()==1) {
				return true;
			}
		} finally {
			DBUtil.close(con, pstmt);
		}
		return false;
	}
}
