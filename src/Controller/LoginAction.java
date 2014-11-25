package Controller;

import java.sql.ResultSet;
import java.sql.SQLException;

import DB.DBHelper;

//登录注册类
public class LoginAction {
	public static String login(String email, String psw) throws SQLException {
		String sql = "select * from user";
		ResultSet rs = DBHelper.executeQuery(sql);

		if (rs.next()) {
			if (rs.getString("psw").equals(psw)) {
				return CommFuns.getTip(true, "登录成功！", "");
			}
		}
		return CommFuns.getTip(false, "用户名或者密码错误！", "");
	}

}
