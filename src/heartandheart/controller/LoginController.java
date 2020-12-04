package heartandheart.controller;

import java.sql.SQLException;
import java.util.ArrayList;

import heartandheart.model.UserInfoDAO;
import heartandheart.model.dto.UserInfo;

public class LoginController {
	static ArrayList<UserInfo> datas;
	
	static int check(String id, int pw) {	
		try {
			datas =  UserInfoDAO.getAllUserInfo();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("sql exception!!");
		}
		
		for(int i=0;i<datas.size();i++) {
			if((datas.get(i).getId().equals(id))&&(datas.get(i).getPw()==pw)){	//회원
				if(id=="admin" && pw==1234) {
					return 0;
				}
				return 1;
			}
		}
		return 2;
	}
}
