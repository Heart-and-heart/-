package heartandheart.controller;

import java.sql.SQLException;
import java.util.ArrayList;

import heartandheart.exception.NotExistException;
import heartandheart.exception.RedundantDataException;
import heartandheart.model.DiaryInfoDAO;
import heartandheart.model.EmotionInfoDAO;
import heartandheart.model.UserInfoDAO;
import heartandheart.model.WeatherInfoDAO;
import heartandheart.model.dto.DiaryInfo;
import heartandheart.model.dto.EmotionInfo;
import heartandheart.model.dto.UserInfo;
import heartandheart.model.dto.WeatherInfo;
import heartandheart.view.FailView;
import heartandheart.view.RunningEndAdminView;

public class HeartandHeartAdminController extends HeartandHeartUserController{
	// 모든 감정 검색
	public static void selectAllEmotions() {
		try {
			for (EmotionInfo emotion : EmotionInfoDAO.getAllEmotionInfo()) {
				RunningEndAdminView.printObject(emotion);
			}
		} catch (SQLException e) {
			FailView.showError("감정 검색 오류");
			e.printStackTrace();
		} catch (NotExistException e) {
			FailView.showError("반환값이 없습니다.");
			e.printStackTrace();
		}
	}

	// 감정 추가
	public static void addEmotion(String emotionStat) {
		try {
			boolean flag=true;
			for(EmotionInfo data : EmotionInfoDAO.getAllEmotionInfo()) {
				if(data.getEmotionStat().equals(emotionStat)) {
					flag=false;
					break;
				}
			}
			if(flag) {
				if(EmotionInfoDAO.addEmotionInfo(emotionStat)==false) {
					System.out.println("데이터 추가 실패");
				}
			} else {
				throw new RedundantDataException("이미 존재하는 감정데이터를 입력하셨습니다.");
			}
		} catch (SQLException e) {
			FailView.showError("감정 추가 오류");
			e.printStackTrace();
		} catch (NotExistException e) {
			FailView.showError("감정데이터 추가 SQL오류");
			e.printStackTrace();
		} catch (RedundantDataException e) {
			FailView.showError("이미 존재하는 감정데이터를 입력하셨습니다");
			e.printStackTrace();
		}
	}

	// 감정 업데이트
	public static void updateEmotion(int emotionNo, String newemotionStat) throws NotExistException{
		try {
			if(EmotionInfoDAO.updateEmotionInfo(emotionNo, newemotionStat)==false) {
				throw new NotExistException("업데이트 시킬 감정번호가 존재하지 않습니다.");
			}
		} catch (SQLException e) {
			FailView.showError("감정 수정 오류");
			e.printStackTrace();
		}
	}

	// 감정 삭제
	public static void deleteEmotion(int emotionNo) {
		try {
			EmotionInfoDAO.deleteEmotionInfo(emotionNo);
		} catch (SQLException e) {
			FailView.showError("감정 삭제 오류");
			e.printStackTrace();
		}
	}

	// 모든 날씨 검색
	public static void selectAllWeathers() {
		try {
			for (WeatherInfo weather : WeatherInfoDAO.getAllWeatherInfo()) {
				RunningEndAdminView.printObject(weather);
			}
		} catch (SQLException e) {
			FailView.showError("날씨 검색 오류");
			e.printStackTrace();
		} catch (NotExistException e) {
			FailView.showError("날씨 정보 없음");
			e.printStackTrace();
		}
	}

	// 날씨 추가
	public static void addWeather(String weatherStat) {
		try {
			boolean flag=true;
			for(WeatherInfo data : WeatherInfoDAO.getAllWeatherInfo()) {
				if(data.getWeatherStats().equals(weatherStat)) {
					flag=false;
					break;
				}
			}
			if(flag) {
				if(WeatherInfoDAO.addWeatherInfo(weatherStat)==false) {
					System.out.println("데이터 추가 실패");
				}
			} else {
				throw new RedundantDataException("이미 존재하는 감정데이터를 입력하셨습니다.");
			}
		}catch (SQLException e) {
			FailView.showError("날씨 검색 오류");
			e.printStackTrace();
		} catch (NotExistException e) {
			FailView.showError("날씨 정보 없음");
			e.printStackTrace();
		} catch (RedundantDataException e) {
			FailView.showError("이미 존재하는 날씨정보를 입력하셨습니다");
			e.printStackTrace();
		} 
	}

	// 날씨 업데이트
	public static void updateweather(int weatherNo, String weatherStat) throws NotExistException{
		try {
			if(WeatherInfoDAO.updateWeatherInfo(weatherNo, weatherStat)==false) {
				throw new NotExistException("업데이트할 날씨 번호가 존재하지 않습니다.");
			}
		} catch (SQLException e) {
			FailView.showError("날씨 수정 오류");
			e.printStackTrace();
		}
	}

	// 날씨 삭제
	public static void deleteWeather(int weatherNo) {
		try {
			WeatherInfoDAO.deleteWeatherInfo(weatherNo);
		} catch (SQLException e) {
			FailView.showError("날씨 삭제 오류");
			e.printStackTrace();
		}
	}

	// 모든 유저 검색
	public static void selectAllUsers() {
		try {
			for (UserInfo user : UserInfoDAO.getAllUserInfo()) {
				RunningEndAdminView.printObject(user);
			}
		} catch (SQLException e) {
			FailView.showError("유저 검색 반환값 없음");
			e.printStackTrace();
		} catch (NotExistException e) {
			FailView.showError("유저 검색 오류");
			e.printStackTrace();
		}
	}

	// 유저 한명 검색
	public static void selectUser(String id, int pw) {
		try {
			UserInfoDAO.getUserInfo(id, pw);
		} catch (SQLException e) {
			FailView.showError("유저 검색 반환값 없음");
			e.printStackTrace();
		} catch (NotExistException e) {
			FailView.showError("유저 검색 오류");
			e.printStackTrace();
		}
	}

	// 유저 추가
	public static void addUser(String id, int pw, String cid) {
		try {
			UserInfoDAO.addUserInfo(id, pw, cid);
		} catch (SQLException e) {
			FailView.showError("유저 추가 오류");
			e.printStackTrace();
		}
	}

	// 유저 정보 수정
	public static void updateUser(String id, int pw) {
		try {
			UserInfoDAO.updateUserInfo(id, pw);
		} catch (SQLException e) {
			FailView.showError("유저 정보 수정 오류");
			e.printStackTrace();
		}
	}

	// 유저 정보 삭제
	public static void deleteUser(String id, int pw) {
		try {
			UserInfoDAO.deleteUserInfo(id, pw);
		} catch (SQLException e) {
			FailView.showError("유저 정보 수정 오류");
			e.printStackTrace();
		}
	}

	// 모든 다이어리 정보 가져오기
	public static void selectAllDiarys() {

		try {
			for (DiaryInfo diary : DiaryInfoDAO.getAllDiarys()) {
				RunningEndAdminView.printObject(diary);
			}
		} catch (SQLException e) {
			FailView.showError("다이어리 검색 반환값 없음");
			e.printStackTrace();
		} catch (NotExistException e) {
			FailView.showError("다이어리 검색 오류");
			e.printStackTrace();
		}
	}

	// 유저 한명의 다이어리 검색
	public static void selectDiarys(UserInfo user) {
		try {
			for (DiaryInfo diary : DiaryInfoDAO.USRDiaryInfo(user.getId())) {
				RunningEndAdminView.printObject(diary);
			}
		} catch (SQLException e) {
			FailView.showError("다이어리 검색 오류");
			e.printStackTrace();
		} catch (NotExistException e) {
			FailView.showError("다이어리 검색 반환값 없음");
			e.printStackTrace();
		}
	}

	// 다이어리 정보 삭제
	public static void deleteUser(UserInfo user, int diaryNo) {
		try {
			DiaryInfoDAO.deleteDiary(diaryNo, user.getId());
		} catch (SQLException e) {
			FailView.showError("다이어리 삭제 오류");
			e.printStackTrace();
		}
	}
}