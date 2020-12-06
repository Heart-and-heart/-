package heartandheart.controller;

import java.util.Scanner;

import heartandheart.exception.NotIntegerException;
import heartandheart.model.UserInfoDAO;
import heartandheart.model.dto.UserInfo;
import heartandheart.view.RunningEndUserView;

public class InputController {
	static Scanner scan = new Scanner(System.in);
	
	static public int inputInt() throws NotIntegerException{		
		String temp = scan.next();
		for(int i=0;i<temp.length();i++) {
			if(!(temp.charAt(i)>='0' && temp.charAt(i)<='9')) {
				throw new NotIntegerException("정수를 입력하지 않았습니다!");
			}
		}
		return Integer.parseInt(temp);
	}
	static public String inputString() {
		return scan.next();
	}

}




