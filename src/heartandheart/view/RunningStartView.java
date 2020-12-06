package heartandheart.view;

import heartandheart.controller.InputController;
import heartandheart.controller.LoginController;
import heartandheart.exception.NotIntegerException;

public class RunningStartView {

	public static void main(String[] args) {
		String id;
		int pw;
		while(true) {
			System.out.println("********Welcome to Dear Diary********");
			System.out.println("ID를 입력하세요:");
			id = InputController.inputString();
			System.out.println("PW를 입력하세요.:");
			try {
				pw = InputController.inputInt();
				System.out.println("id: "+ id+"  pw: "+pw);
				LoginController.check(id,pw);
			} catch (NotIntegerException e) {
				FailView.showError("정수만 입력가능합니다.");
				e.printStackTrace();
			}			
			System.out.println("로그인 재시도를 원하시면 R을 입력하세요");
			if(!InputController.inputString().equals("R")) {
				System.out.println("프로그램을 종료합니다.");
				break;
			}
		}
	}

}
