package heartandheart.controller;

import java.sql.SQLException;
import java.util.ArrayList;

import heartandheart.exception.NotExistException;
import heartandheart.model.UserInfoDAO;
import heartandheart.model.dto.UserInfo;
import heartandheart.view.FailView;
import heartandheart.view.RunningEndAdminView;
import heartandheart.view.RunningEndUserView;
import lombok.extern.slf4j.Slf4j;

@Slf4j
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
					log.info("관리자가 로그인함!");
				}else {
				RunningEndUserView.printStartToUser(id);
				log.info(id+"회원님이 로그인함!");
				}
			}
		}
		if(!flag)	RunningEndUserView.printToNotUser();
	}
}
