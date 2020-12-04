package heartandheart.controller;

import java.util.Scanner;

import heartandheart.model.UserInfoDAO;
import heartandheart.model.dto.UserInfo;
import heartandheart.view.RunningEndUserView;

public class InputController {
	static Scanner scan = new Scanner(System.in);
	
	static public void inputbyClient(String input) {
		String id="";
		int pw=0;
		
		if(input.equals("start")){//로그인			
			System.out.println("ID를 입력하세요:");
			id = scan.next();
			System.out.println("PW를 입력하세요.:");
			pw = scan.nextInt();
			if(LoginController.check(id,pw)==0) {	//관리자일때				
			//	RunningEndView.
			}else if(LoginController.check(id,pw)==1){	//사용자일때				
				RunningEndUserView.printStartToUser(id);
			}else {	
				//회원이 아닐때				
			}
		}else if(input.equals("menu1")) {
			
		}
	}
	static public void inputzero(String id) {
		RunningEndUserView.printStartToUser(id);
	}
	static public int inputInt() {
		return scan.nextInt();
	}
	static public String inputString() {
		return scan.next();
	}

}



