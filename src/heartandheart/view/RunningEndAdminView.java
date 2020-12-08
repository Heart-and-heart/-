package heartandheart.view;

import heartandheart.controller.HeartandHeartAdminController;
import heartandheart.controller.InputController;
import heartandheart.exception.NotExistException;
import heartandheart.exception.NotIntegerException;
import heartandheart.model.dto.DiaryInfo;

public class RunningEndAdminView {
	static public void printStartToAdmin(String userid) {
		int n;
		while(true) {
			System.out.println(userid+"관리자님 안녕하세요! 원하는 메뉴를 선택하세요");
			System.out.println("1. 모든 회원 정보 열람하기");
			System.out.println("2. 모든 다이어리 정보 열람하기");
			System.out.println("3. 감정정보 추가");
			System.out.println("4. 감정정보 수정");
			System.out.println("5. 날씨정보 추가");
			System.out.println("6. 날씨정보 수정");
			System.out.println("7. 로그아웃");
			
			try {
				n = InputController.inputInt();
				if(n==1) {
					printToAdminMenu1();
				}else if(n==2) {
					printToAdminMenu2();
				}else if(n==3) {
					printToAdminMenu3();
				}else if(n==4) {
					printToAdminMenu4();
				}else if(n==5) {
					printToAdminMenu5();
				}else if(n==6) {
					printToAdminMenu6();
				}else if(n==7) {
					break;
				}
			} catch (NotIntegerException e) {
				System.out.println("정수만 입력가능합니다.");
				e.printStackTrace();
			}
		}
	}

	static public void printToAdminMenu1() {	
		System.out.println("모든 회원 정보를 열람합니다.");	
		HeartandHeartAdminController.selectAllUsers();
		System.out.println("이전화면으로 가려면 아무키나  입력하세요.");
		InputController.inputString();	
	}	

	static public void printToAdminMenu2() {	
		System.out.println("모든 다이어리 정보를 열람합니다.");		
		HeartandHeartAdminController.selectAllDiarys();
		System.out.println("이전화면으로 가려면 아무키나  입력하세요.");
		InputController.inputString();	
	}

	static public void printToAdminMenu3() {	
		System.out.println("감정table 추가");	
		System.out.println("추가할 감정을 입력해주세요.");	
		String emotionstat = InputController.inputString();
		HeartandHeartAdminController.addEmotion(emotionstat);
	}
	
	static public void printToAdminMenu4() {	
		System.out.println("감정table 수정");	
		System.out.println("감정테이블 목록입니다.");
		HeartandHeartAdminController.selectAllEmotions();
		System.out.println("수정할 감정번호를 입력해주세요.");	
		try {
			int emotionno = InputController.inputInt();
			System.out.println("새로운 감정상태를 입력해주세요.");
			String emtionstat = InputController.inputString();
			HeartandHeartAdminController.updateEmotion(emotionno, emtionstat);;
		} catch (NotIntegerException e) {
			FailView.showError("정수만 입력가능합니다.");
			e.printStackTrace();
		} catch (NotExistException e) {
			FailView.showError("감정정보 수정 실패, 존재하는 감정정보만 입력가능함");
			e.printStackTrace();
		}
	}
	
	static public void printToAdminMenu5() {	
		System.out.println("날씨table 추가");		
		System.out.println("추가할 날씨를 입력해주세요.");	
		String weathernstat = InputController.inputString();
		HeartandHeartAdminController.addWeather(weathernstat);
	}		

	static public void printToAdminMenu6() {	
		System.out.println("날씨table 수정");	
		System.out.println("날씨테이블 목록입니다.");
		HeartandHeartAdminController.selectAllWeathers();
		System.out.println("수정할 날씨번호를 입력해주세요.");	
		try {
			
			int weatherno = InputController.inputInt();
			System.out.println("새로운 날씨정보를 입력해주세요.");
			String weatherstat = InputController.inputString();
			HeartandHeartAdminController.updateweather(weatherno, weatherstat);
		} catch (NotIntegerException e) {
			FailView.showError("정수만 입력가능합니다.");
			e.printStackTrace();
		} catch (NotExistException e) {
			FailView.showError("날씨정보 수정 실패, 존재하는 날씨정보만 입력가능함");
			e.printStackTrace();
		}
	}
	
	static public void printObject(Object o) {
		System.out.println(o);
	}

}
