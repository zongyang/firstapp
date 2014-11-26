package Controller;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.google.gson.Gson;

import DB.DBHelper;
import Model.UserModel;

//登录注册类
public class UserAction {
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

	public static String register(String user) throws SQLException {
		Gson gson = new Gson();
		UserModel model = gson.fromJson(user, UserModel.class);

		String sql_check = " select id from user where email='"
				+ model.getEmail() + "' ";
		String sql_insert = " insert into user (`email`,`psw`,`nickName`,`online`,`ip`) values ('"
				+ model.getEmail()
				+ "','"
				+ model.getPwd()
				+ "','"
				+ model.getNickName()
				+ "','"
				+ model.getOnline()
				+ "','"
				+ model.getIp() + "')";

		ResultSet rs = DBHelper.executeQuery(sql_check);
		if (rs.next()) {
			return CommFuns.getTip(false, "该邮箱已注册！", "");
		}
		DBHelper.executeNonQuery(sql_insert);
		return CommFuns.getTip(true, "注册成功！", "");

	}

}
