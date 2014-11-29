package Controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import DB.DBHelper;
import Model.UserModel;

//登录注册类
public class UserAction {
	public static HttpSession session;

	public static String login(String email, String psw, String ip)
			throws SQLException {

		String condition = " where email='" + email + "' ";
		String sql = "select id,nickName,psw from user " + condition;
		String update_ip = "update  user set online='1',ip='" + ip + "'"
				+ condition;
		ResultSet rs = DBHelper.executeQuery(sql);

		if (rs.next()) {
			if (rs.getString("psw").equals(psw)) {
				DBHelper.executeNonQuery(update_ip);
		
				return CommFuns
						.getTip(true,
								rs.getString("id") + ","
										+ rs.getString("nickName"), "");
			}
		}
		return CommFuns.getTip(false, "用户名或者密码错误！", "");
	}

	public static String register(String user, String ip) throws SQLException {
		Gson gson = new Gson();
		UserModel model = gson.fromJson(user, UserModel.class);
		model.setIp(ip);
		model.setOnline("1");

		String sql_check = " select id from user where email='"
				+ model.getEmail() + "' ";
		String sql_insert = " insert into user (email,psw,nickName,online,ip) values ('"
				+ model.getEmail()
				+ "','"
				+ model.getPwd()
				+ "','"
				+ model.getNickName()
				+ "','"
				+ model.getOnline()
				+ "','"
				+ model.getIp() + "')";
		// 查询记录是否可以存在，插入后返回id，本来可以只需要一次数据库操作的，但由于总是报错，所以用了最笨的
		ResultSet rs = DBHelper.executeQuery(sql_check);
		if (rs.next()) {
			return CommFuns.getTip(false, "该邮箱已注册！", "");
		}
		// 插入后返回的id
		DBHelper.executeNonQuery(sql_insert);
		rs = DBHelper.executeQuery(sql_check);
		rs.next();
		return CommFuns.getTip(true, rs.getString("id"), "");

	}

	public static LinkedList<String> getFirends(String user) throws SQLException {
		
		String sql = "select fromName,toName from friend_view where fromName='"
				+ user + "' or toName='" + user + "' ";
		LinkedList<String> friends = new LinkedList<String>();

		if (user == null) {
			return friends;
		}

		String toName, fromName;
		ResultSet rs = DBHelper.executeQuery(sql);
		// 存储每个好友的最近记录
		while (rs.next()) {
			toName = rs.getString("toName");
			fromName = rs.getString("fromName");
			// 主动发起的(key是friend)
			if (fromName.equals(user) && !friends.contains(toName)) {
				friends.push(toName);
			}
			// 好友发起的(key是friend)
			if (toName.equals(user) && !friends.contains(fromName)) {
				friends.push(fromName);
			}
		}

		return friends;
	}

}
