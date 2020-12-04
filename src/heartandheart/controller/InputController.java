package heartandheart.controller;

import java.util.Scanner;

import heartandheart.model.UserInfoDAO;
import heartandheart.view.RunningEndView;

public class InputController {
	static Scanner scan = new Scanner(System.in);
	static int num=0;
	static public void inputbyClient(String input) {
		if(input.equals("start")){//로그인
			String id,pw;
			System.out.println("ID를 입력하세요:");
			id = scan.next();
			System.out.println("PW를 입력하세요.:");
			pw = scan.next();
			if(LoginController.check(id,pw)) {
				RunningEndView.printStartToUser(UserInfoDAO.get);
			}else {
				
			}
		}else if(input.equals("userpage1")) {
			num = scan.nextInt();
			
		}else if(input.equals("userpage2")) {
			
		}else if(input.equals("userpage3")) {
			
		}else if(input.equals("userpage4")) {
			
		}else if(input.equals("adminpage1")) {
			
		}else if(input.equals("adminpage2")) {
			
		}else if(input.equals("adminpage3")) {
			
		}
	}

}



