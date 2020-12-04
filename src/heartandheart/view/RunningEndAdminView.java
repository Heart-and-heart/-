package heartandheart.view;

import heartandheart.controller.InputController;
import heartandheart.model.dto.DiaryInfo;

public class RunningEndAdminView {
	static public void printToAdmin(String userid) {
		System.out.println(userid+"관리자님 안녕하세요! 원하는 메뉴를 선택하세요");
		System.out.println("1. 모든 회원 정보 열람하기");
		System.out.println("2. 모든 다이어리 정보 열람하기");
		System.out.println("3. 감정정보 추가/삭제");
		System.out.println("4. 날씨정보 추가/삭제");
	}
	
	static public void printToAdminMenu1(String userid) {	
		System.out.println("모든 회원 정보를 열람합니다.");
	}	
	
	static public void printToAdminMenu2(String userid) {	
		System.out.println("모든 다이어리 정보를 열람합니다.");
	}
	
	static public void printToAdminMenu3(DiaryInfo diary) {	
		System.out.println("감정table 추가/삭제");	
		System.out.println("추가할 감정");
	}
	
	static public void printToAdminMenu4(DiaryInfo diary) {	
		System.out.println("날씨table 추가/삭제");
	}		
	
	static public void printObject(Object o) {
		System.out.println(o);
	}

}
