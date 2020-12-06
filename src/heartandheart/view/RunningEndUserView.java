package heartandheart.view;

import heartandheart.controller.HeartandHeartUserController;
import heartandheart.controller.InputController;
import heartandheart.exception.NotIntegerException;
import heartandheart.model.dto.DiaryInfo;
import heartandheart.model.dto.UserInfo;

public class RunningEndUserView {
	static public void printStartToUser(String userid) {
		int n;
		while(true) {
			System.out.println(userid+"회원님! 원하는 메뉴를 선택하세요");
			System.out.println("1. 오늘의 일기 작성");
			System.out.println("2. 오늘의 일기 수정");			
			System.out.println("3. 그동안의 일기 열람");
			System.out.println("4. 일기 삭제");					
			System.out.println("5. 커플회원 정보 보기");
			System.out.println("6. 지난주,이번주 나의 감정상태 확인 및 비교하기");
			System.out.println("7. 감정을 기준으로 나의 기분다른회원 일기 열람하기");		
			System.out.println("8. 로그아웃");

			try {
				n = InputController.inputInt();
				if(n==1) {
					printToUserMenu1(userid);
				}else if(n==2) {
					printToUserMenu2(userid);
				}else if(n==3) {
					printToUserMenu3(userid);
				}else if(n==4) {
					printToUserMenu4(userid);
				}else if(n==5) {
					printToUserMenu5(userid);
				}else if(n==6) {
					printToUserMenu6(userid);
				}else if(n==7) {
					printToUserMenu7(userid);
				}else if(n==8) {
					break;
				}
			} catch (NotIntegerException e) {
				FailView.showError("정수만 입력가능합니다.");
				e.printStackTrace();
			}
		}
	}

	static public void printToUserMenu1(String userid) {	//1. 회원의 일기 작성
		int dno=0,eno, wno, st, p;
		String rdate, dc;

		System.out.println(userid+"님의 일기를 작성해주세요!!");
		System.out.println("감정상태를 선택해주세요(1.화가나 2.짜증나 3.걱정돼 4.그럭저럭 5.평온해 6.최고!):");
		try {
			eno = InputController.inputInt();

			System.out.println("날씨를 선택해주세요(1.따뜻해 2.더워 3.비가와 4.추워 5.구름둥둥 6.눈온다):");
			wno = InputController.inputInt();
			System.out.println("날짜를 입력해주세요(YYYY-MM-DD와같은 형식으로):");
			rdate = InputController.inputString();
			System.out.println("잠잔시간을 입력해주세요(숫자만 입력):");
			st = InputController.inputInt();	
			System.out.println("한줄코멘트를 입력해주세요:");
			dc = InputController.inputString();
			System.out.println("공개범위 설정/번호를 입력하세요(0.자기만보기  1.커플이랑 나만 보기  2.전체공개)");
			p = InputController.inputInt();

			HeartandHeartUserController.writeDiary(new DiaryInfo(dno,userid,eno,wno,rdate,st,dc,p));	
		} catch (NotIntegerException e) {
			FailView.showError("정수만 입력가능합니다.");
			e.printStackTrace();
		}	
	}	

	static public void printToUserMenu2(String userid) {	//2.회원의 일기 수정
		String rdate,dc;
		System.out.println(userid+"님의 일기를 수정합니다.");
		System.out.println("수정할 날짜를 입력하세요.");
		rdate = InputController.inputString();
		System.out.println("한줄코멘트를 입력해주세요:");
		dc = InputController.inputString();

		HeartandHeartUserController.updateDiary(dc,rdate,userid);
	}

	static public void printToUserMenu3(String userid) {	//3.일기 열람
		HeartandHeartUserController.selectDiary(userid);
		System.out.println("이전화면으로 가려면 아무키나  입력하세요.");
		InputController.inputString();			
	}
	
	static public void printToUserMenu4(String userid) {	//4. 일기 삭제
		System.out.println("삭제할 일기 번호를 선택하세요.");
		int dno;
		try {
			HeartandHeartUserController.selectDiary(userid);
			dno = InputController.inputInt();
			HeartandHeartUserController.deleteDiary(dno, userid);
		}catch (NotIntegerException e) {
			FailView.showError("정수만 입력가능합니다.");
			e.printStackTrace();
		}
		System.out.println("이전화면으로 가려면 아무키나  입력하세요.");
		InputController.inputString();	
	}
	static public void printToUserMenu5(String userid) {	//5. 커플회원 정보 보기
		System.out.println("커플회원 정보를 확인합니다.");
		HeartandHeartUserController.howMatchingIdDoes(userid);
		System.out.println("이전화면으로 가려면 아무키나  입력하세요.");
		InputController.inputString();		
	}

	static public void printToUserMenu6(String userid) {	//6. 지난주,이번주 나의 감정상태 확인 및 비교하기
		System.out.println(userid+"님 의 이번주 감정상태");
		HeartandHeartUserController.thisweekeEmotion(userid);
		System.out.println(userid+"님 의 지난주 감정상태");
		HeartandHeartUserController.lastweekeEmotion(userid);
		HeartandHeartUserController.emotionRate(userid);		
		System.out.println("이전화면으로 가려면 아무키나  입력하세요.");
		InputController.inputString();	
	}

	static public void printToUserMenu7(String userid) {	//7. 감정을 기준으로 나의 기분을 가진 다른 회원 코멘트 보기
		HeartandHeartUserController.othrePeopleFeels(userid);		
		System.out.println("이전화면으로 가려면 아무키나  입력하세요.");
		InputController.inputString();	
	}

	static public void printToNotUser() {
		System.out.println("ID/PW를 다시 입력해주세요.");
	}
	static public void printObject(Object o) {
		System.out.println(o);
	}
}
