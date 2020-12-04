package heartandheart.view;

import heartandheart.controller.InputController;

public class RunningStartView {

	public static void main(String[] args) {

		System.out.println("********DB모든 사용자 정보 load********");
		
		System.out.println("********Welcome Heart and Heart Diary********");
		InputController.inputbyClient("start"); 	//로그인 화면으로 이동		
	}

}
