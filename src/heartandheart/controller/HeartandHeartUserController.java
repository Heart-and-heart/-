package heartandheart.controller;

import java.sql.SQLException;
import java.util.ArrayList;

import heartandheart.exception.NotExistException;
import heartandheart.model.DiaryInfoDAO;
import heartandheart.model.dto.DiaryInfo;
import heartandheart.model.dto.UserInfo;
import heartandheart.view.FailView;
import heartandheart.view.RunningEndUserView;

public class HeartandHeartUserController {
	//	작성자 기준으로 다이어리 정보 가져오기
	public static void selectDiary(String user) {
		try {
			ArrayList<DiaryInfo> list = DiaryInfoDAO.USRDiaryInfo(user);
			for (DiaryInfo diary : list) {
				RunningEndUserView.printObject(diary);
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
	public static void writeDiary(DiaryInfo diary) {
		try {
			if (DiaryInfoDAO.writeDiary(diary.getUserId(), diary.getEmotionNo(), diary.getWeatherNo(), diary.getReportingDate(), diary.getSleepingTime(), diary.getDiaryComment(), diary.getIsPublic())) {
				RunningEndUserView.printObject("추가 성공");
			} else {
				RunningEndUserView.printObject("추가 실패");
			}
		} catch (SQLException e) {
			FailView.showError("다이어리 쓰기 오류");
			e.printStackTrace();
		}
	}

	// 다이어리 수정
	public static void updateDiary(String newDiaryComment, String reportingDate, String user) {
		try {
			if (DiaryInfoDAO.updateDiaryInfo(newDiaryComment, reportingDate, user)) {
				RunningEndUserView.printObject("수정 성공");
			} else {
				RunningEndUserView.printObject("수정 실패");
			}
		} catch (SQLException e) {
			FailView.showError("다이어리 수정 오류");
			e.printStackTrace();
		}
	}

	// 다이어리 삭제
	public static void deleteDiary(int diaryNo, String user) {
		try {
			if (DiaryInfoDAO.deleteDiary(diaryNo, user)) {
				RunningEndUserView.printObject("삭제 성공");
			} else {
				RunningEndUserView.printObject("삭제 실패");
			}
		} catch (SQLException e) {
			FailView.showError("다이어리 삭제 오류");
			e.printStackTrace();
		}
	}

	// 커플의 다이어리 정보 가져오기
	public static void howMatchingIdDoes(String user) {
		try {
			for (DiaryInfo diary : DiaryInfoDAO.howMatchingIdDoes(user)) {
				RunningEndUserView.printObject(diary);
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
	public static void othrePeopleFeels(String user) {
		try {
			for (String s : DiaryInfoDAO.otherPeopleFeels(user)){
				RunningEndUserView.printObject(s);
			}
		} catch (SQLException e) {
			FailView.showError("다이어리 정보 검색 오류");
			e.printStackTrace();
		} catch (NotExistException e) {
			FailView.showError("다이어리 정보가 없습니다.");
			e.printStackTrace();
		}
	}

	public static void thisweekeEmotion(String user) {	
		try {
			double thisweekscore = DiaryInfoDAO.thisWeekEmotion(user);
			System.out.println("이번주 감정 점수는 : "+thisweekscore+"입니다.");
		} catch (SQLException e) {
			e.printStackTrace();
			FailView.showError("이번주 감정 정보 검색 오류!!");
		} catch (NotExistException e) {
			e.printStackTrace();
			FailView.showError("이번주 감정 정보가 없습니다");
		}
	}

	public static void lastweekeEmotion(String user) {	
		try {
			double allscore = DiaryInfoDAO.lastWeekEmotion(user);
			System.out.println("지난주 감정 점수는 : "+allscore+"입니다.");
		} catch (SQLException e) {
			e.printStackTrace();
			FailView.showError("지난주 감정 정보 검색 오류!!");
		} catch (NotExistException e) {
			e.printStackTrace();
			FailView.showError("지난주 감정 정보가 없습니다");
		}
	}

	public static void emotionRate(String user) {	//지난주 이번주 비교
		try {
			double allscore = DiaryInfoDAO.lastWeekEmotion(user);
			double thisweekscore = DiaryInfoDAO.thisWeekEmotion(user);

			if (thisweekscore>=allscore) {
				RunningEndUserView.printObject("지난주보다 행복하시네요. 축하드립니다!!");
			} else {
				RunningEndUserView.printObject("지난주보다 덜 행복하시네요... 맛있는 걸 먹어보는건 어떨까요?");
			}

		} catch (SQLException e) {
			FailView.showError("감정 정보 검색 오류!!");
			e.printStackTrace();
		} catch (NotExistException e) {
			FailView.showError("정보가 없습니다.");
			e.printStackTrace();
		}
	}

	public static void thisWeekSleep(UserInfo user) {
		try {
			double score = DiaryInfoDAO.thisWeekSleep(user.getId());
			if (score>=8) {
				RunningEndUserView.printObject(user.getId()+"님, 이번주는 평균보다 덜 주무셨어요. 주말엔 꼭 포근한 이불 속에서 푹 쉬세요!");
			} else {
				RunningEndUserView.printObject("푹 쉬셨네요 ! 주말엔 등산처럼 활기찬 야외활동을 해보는 게 어떨까요 ~?!");
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
			double thisweektime = DiaryInfoDAO.thisWeekSleep(user.getId());
			double lastweektime = DiaryInfoDAO.lastWeekSleep(user.getId());

			if (thisweektime>lastweektime) {
				RunningEndUserView.printObject("지난 주보다 평균 "+ (thisweektime-lastweektime) +"시간 많이 주무셨네요!");
			} else if (thisweektime<lastweektime) {
				RunningEndUserView.printObject("지난 주보다 평균 "+ (lastweektime-thisweektime) +"시간 덜 주무셨네요...");
			} else {
				RunningEndUserView.printObject("지난 주랑 정확히 똑같이 주무셨어요!!");
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