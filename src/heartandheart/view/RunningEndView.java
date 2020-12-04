package heartandheart.view;

import java.util.ArrayList;

import heartandheart.controller.InputController;
import heartandheart.model.dto.DiaryInfo;
import heartandheart.model.dto.UserInfo;

public class RunningEndView {
	static public void printStartToUser(UserInfo user) {
		System.out.println(user.getId()+"회원님 안녕하세요! 원하는 메뉴를 선택하세요");
		System.out.println("1. 오늘의 일기 작성");
		System.out.println("2. 커플회원 정보 보기");
		System.out.println("3. 이번주 나의 감정상태 확인하기");
		System.out.println("4. 지난주 나의 기분점수와 이번주 기분점수 비교하기");
		System.out.println("5. 감정을 기준으로 다른회원 일기 열람하기");
		System.out.println("6. 로그아웃");
		InputController.inputbyClient("userpage1");
	}
	
	static public void printToAdmin(UserInfo user) {
		System.out.println(user.getId()+"관리자님 안녕하세요! 원하는 메뉴를 선택하세요");
		System.out.println("1. 모든 회원 정보 열람하기");
		System.out.println("2. 모든 다이어리 정보 열람하기");
		System.out.println("3. 감정정보 추가/삭제");
		System.out.println("4. 날씨정보 추가/삭제");
	}
	static public void printToUserMenu3(DiaryInfo diary) {
		System.out.println(diary.getUserId()+"님 의 이번주 감정상태");
		//지난주 기분점수 출력
		System.out.println("\n\n");
		System.out.println("이전화면으로 가려면 0번을 입력하세요.");
	}
	static public void printToUserMenu4(ArrayList<DiaryInfo> diary) {	//userid꺼 diary정보만 저장된 arraylist끌고 오기??
		System.out.println(diary.getUserId()+"님 의 지난주 감정정보");
		//지난주 기분점수 출력
		System.out.println(diary.getUserId()+"님 의 이번주 감정정보");
		//이번주 기분점수 출력
		//이번주 감정과 지난주 감정 점수차를 통해 추천문구 출력
		System.out.println("이전화면으로 가려면 0번을 입력하세요.");
	}
	static public void printToUserMenu5(DiaryInfo diary) {
		System.out.println();
		System.out.println("이전화면으로 가려면 0번을 입력하세요.");
	}
	static public void printToNotUser() {
		System.out.println("ID/PW를 다시 입력해주세요.");
		InputController.inputbyClient(1);
	}
}
