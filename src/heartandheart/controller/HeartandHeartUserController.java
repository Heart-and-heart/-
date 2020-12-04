package heartandheart.controller;

import java.sql.SQLException;

import heartandheart.exception.NotExistException;
import heartandheart.model.DiaryInfoDAO;
import heartandheart.model.dto.DiaryInfo;
import heartandheart.model.dto.UserInfo;
import heartandheart.view.FailView;
import heartandheart.view.RunningEndView;

public class HeartandHeartUserController {

//	작성자 기준으로 다이어리 정보 가져오기
	public static void selectDiary(UserInfo user) {
		try {
			for (DiaryInfo diary : DiaryInfoDAO.USRDiaryInfo(user.getId(),user.getPw())) {
				RunningEndView.printObject(diary);
			}
		} catch (SQLException e) {
			FailView.showError("다이어리 정보 검색 오류");
			e.printStackTrace();
		} catch (NotExistException e) {
			FailView.showError("다이어리 정보가 없습니다.");
			e.printStackTrace();
		}
	}
	
	// 다이어리 쓰기
	public static void writeDiary(UserInfo user, DiaryInfo diary) {
		try {
			if (DiaryInfoDAO.writeDiary(user.getId(), diary.getEmotionNo(), diary.getWeatherNo(), diary.getReportingDate(), diary.getSleepingTime(), diary.getDiaryComment(), diary.getIsPublic())) {
				RunningEndView.printObject("추가 성공");
			} else {
				RunningEndView.printObject("추가 실패");
			}
		} catch (SQLException e) {
			FailView.showError("다이어리 쓰기 오류");
			e.printStackTrace();
		}
	}
	
	// 다이어리 수정
	public static void updateDiary(String newDiaryComment, String reportingDate, UserInfo user) {
		try {
			if (DiaryInfoDAO.updateDiaryInfo(newDiaryComment, reportingDate, user.getId())) {
				RunningEndView.printObject("수정 성공");
			} else {
				RunningEndView.printObject("수정 실패");
			}
		} catch (SQLException e) {
			FailView.showError("다이어리 수정 오류");
			e.printStackTrace();
		}
	}
	
	// 다이어리 삭제
	public static void deleteDiary(int diaryNo, UserInfo user) {
		try {
			if (DiaryInfoDAO.deleteDiary(diaryNo, user.getId())) {
				RunningEndView.printObject("삭제 성공");
			} else {
				RunningEndView.printObject("삭제 실패");
			}
		} catch (SQLException e) {
			FailView.showError("다이어리 삭제 오류");
			e.printStackTrace();
		}
	}
	
	// 커플의 다이어리 정보 가져오기
	public static void howMatchingIdDoes(UserInfo user) {
		try {
			for (DiaryInfo diary : DiaryInfoDAO.howMatchingIdDoes(user.getId())) {
				RunningEndView.printObject(diary);
			}
		} catch (SQLException e) {
			FailView.showError("다이어리 정보 검색 오류");
			e.printStackTrace();
		} catch (NotExistException e) {
			FailView.showError("다이어리 정보가 없습니다.");
			e.printStackTrace();
		}
	}
	
	// 같은 감정인 사람들 코멘트
	public static void othrePeopleFeels(UserInfo user) {
		try {
			for (String s : DiaryInfoDAO.otherPeopleFeels(user.getId())){
				RunningEndView.printObject(s);
			}
		} catch (SQLException e) {
			FailView.showError("다이어리 정보 검색 오류");
			e.printStackTrace();
		} catch (NotExistException e) {
			FailView.showError("다이어리 정보가 없습니다.");
			e.printStackTrace();
		}
	}
	
	public static void emotionRate(UserInfo user) {
		try {
			int allscore = DiaryInfoDAO.lastWeekEmotion(user.getId());
			int thisweekscore = DiaryInfoDAO.thisWeekEmotion(user.getId());
			
			if (thisweekscore>=allscore) {
				RunningEndView.printObject("지난주보다 행복하시네요. 축하드립니다.");
			} else {
				RunningEndView.printObject("지난주보다 덜 행복하시네요... 맛있는 걸 먹어보는건 어떨까요?");
			}

		} catch (SQLException e) {
			FailView.showError("감정 정보 검색 오류");
			e.printStackTrace();
		} catch (NotExistException e) {
			FailView.showError("정보가 없습니다.");
			e.printStackTrace();
		}
	}
	
	public static void thisWeekSleep(UserInfo user) {
		try {
			int score = DiaryInfoDAO.thisWeekSleep(user.getId());
			if (score>=8) {
				RunningEndView.printObject(user.getId()+"님, 이번주는 평균보다 덜 주무셨어요. 주말엔 꼭 포근한 이불 속에서 푹 쉬세요!");
			} else {
				RunningEndView.printObject("푹 쉬셨네요 ! 주말엔 등산처럼 활기찬 야외활동을 해보는 게 어떨까요 ~?!");
			}
		} catch (SQLException e) {
			FailView.showError("감정 정보 검색 오류");
			e.printStackTrace();
		} catch (NotExistException e) {
			FailView.showError("정보가 없습니다.");
			e.printStackTrace();
		}
	}
	
	public static void compareSleepTime(UserInfo user) {
		try {
			int thisweektime = DiaryInfoDAO.thisWeekSleep(user.getId());
			int lastweektime = DiaryInfoDAO.lastWeekSleep(user.getId());
			
			if (thisweektime>lastweektime) {
				RunningEndView.printObject("지난 주보다 평균 "+ (thisweektime-lastweektime) +"시간 많이 주무셨네요!");
			} else if (thisweektime<lastweektime) {
				RunningEndView.printObject("지난 주보다 평균 "+ (lastweektime-thisweektime) +"시간 덜 주무셨네요...");
			} else {
				RunningEndView.printObject("지난 주랑 정확히 똑같이 주무셨어요!!");
			}
		} catch (SQLException e) {
			FailView.showError("수면 정보 검색 오류");
			e.printStackTrace();
		} catch (NotExistException e) {
			FailView.showError("수면 정보가 없습니다.");
			e.printStackTrace();
		}
		
	}
}