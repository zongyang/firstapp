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

	public static String getIdByName(String name) throws SQLException {

		String id = "";

		if (name.isEmpty()) {
			return id;
		}

		String sql = "select id from user where email='" + name + "'";
		ResultSet rs = DBHelper.executeQuery(sql);
		if (rs.next()) {
			id = rs.getString("id");
		}

		return id;

	}

	public static String getAllUser() throws SQLException {
		return getAllUser("");
	}
	public static String getAllUser(String query) throws SQLException{
		
		String sql="select * from user ";
		
		if(!query.isEmpty()){
			query=" where email like '%"+query+"%' ";
			sql+=query;
		}
		
		
		ResultSet rs=DBHelper.executeQuery(sql); 
		LinkedList<UserModel> models=new LinkedList<UserModel>();
		Gson gson=new Gson();
		
		while(rs.next()){
			UserModel model=new UserModel();
			model.setId(rs.getString("id"));
			model.setEmail(rs.getString("email"));
			model.setPsw(rs.getString("nickName"));
			model.setNickName(rs.getString("online"));
			models.push(model);
		}
		
		return gson.toJson(models);
	}
}
