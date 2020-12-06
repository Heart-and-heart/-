package heartandheart.controller;

import java.sql.SQLException;
import java.util.ArrayList;

import heartandheart.exception.NotExistException;
import heartandheart.model.UserInfoDAO;
import heartandheart.model.dto.UserInfo;
import heartandheart.view.FailView;
import heartandheart.view.RunningEndAdminView;
import heartandheart.view.RunningEndUserView;

public class LoginController {
	static ArrayList<UserInfo> datas;

	public static void check(String id, int pw) {
		boolean flag=false;
		try {
			datas =  UserInfoDAO.getAllUserInfo();
		}catch (SQLException e) {
			e.printStackTrace();
			FailView.showError("sqlexception error!!");
		}catch (NotExistException e) {
			e.printStackTrace();
			FailView.showError("NoException error!!");
		}

		for(int i=0;i<datas.size();i++) {
			if((datas.get(i).getId().equals(id))&&(datas.get(i).getPw()==pw)){	//회원
				flag=true;
				if(id.equals("admin") && pw==1234) {	//관리자
					RunningEndAdminView.printStartToAdmin(id);
				}
				RunningEndUserView.printStartToUser(id);;
			}
		}
		if(!flag)	RunningEndUserView.printToNotUser();
	}
}
