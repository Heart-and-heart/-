package heartandheart.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Properties;

import Util.DBUtil;
import heartandheart.model.dto.DiaryInfo;
import heartandheart.model.dto.EmotionInfo;

public class DiaryInfoDAO {
	// 작성자 기준으로 다이어리 정보 가져오기
static Properties sqlAll = DBUtil.getSqlAll();

	// 다이어리 수정 (코멘트만 수정 - userid와 diarynum에 해당하는 행의 comment를 변경)
	public static boolean updateDiaryInfo(???) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<DiaryInfo> datas = null;
		
		try{
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sqlAll.getProperty(""));
			pstmt.setString(1, diaryInfo.getDiaryComment());
			pstmt.setString(2, diaryInfo.getReportingDate());
			pstmt.setString(3, diaryInfo.getUserId());

			int result = pstmt.executeUpdate();

			if(result == 1){
				return true;
			}
		}finally{
			DBUtil.close(con, pstmt);
		}
		return false;
	}
	// 다이어리 쓰기(새로 쓰는 것)
	public static boolean updateDiaryInfo(DiaryInfo diaryInfo) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try{
			
		}finally{
			DBUtil.close(con, pstmt);
		}
		return false;
	}
	// 다이어리 삭제
	public static boolean deleteDiaryInfo(DiaryInfo diaryInfo) {
		//delete from diary where ~~~
	}
	// 커플의 다이어리 정보 가져오기 (공개여부 고려)
	public static DiaryInfo selectCoupleinfo(DiaryInfo diaryInfo) {
		//select * from diary where diaryInfo.get
	}

	// 감정 기준으로 다른 사람들의 다이어리 정보 가져오기 (공개여부 고려하여)

}
