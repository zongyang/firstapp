package Controller;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.http.HttpSession;

import DB.DBHelper;
import Model.AdminModel;

import com.google.gson.Gson;

public class AdminAction {
	private HttpSession session;

	public AdminAction(HttpSession session) {
		this.session = session;
	}

	public Boolean is_super_admin() {
		if (this.get_admin_auth().equals(AdminModel.SUPER_ADMIN)) {
			return true;
		}
		return false;
	}

	public Boolean is_admin() {
		if (is_super_admin()) {
			return true;
		}
		if (this.get_admin_auth().equals(AdminModel.GENERAL_ADMIN)) {
			return true;
		}
		return false;

	}

	private void set_admin_name(String val) {
		this.session.setAttribute("admin_name", val);
	}

	private void set_admin_auth(String val) {
		this.session.setAttribute("admin_auth", val);
	}

	private String get_admin_auth() {
		Object admin_auth=session.getAttribute("admin_auth");
		if(admin_auth==null){
			return "";
		}
		return session.getAttribute("admin_auth").toString();
	}

	// 获得当前管理员名
	public String get_admin_name() {
		Object admin_name=session.getAttribute("admin_name");
		if(admin_name==null){
			return "";
		}
		return session.getAttribute("admin_name").toString();
	}

	// 添加一般管理员
	public String add_admin(String strModel) {

		if (strModel == null || strModel.isEmpty()) {
			return "{success:false,msg:'非法操作！'}";
		}

		Gson gson = new Gson();
		AdminModel model = gson.fromJson(strModel, AdminModel.class);
		model.setAuth(AdminModel.GENERAL_ADMIN);

		if (add_admin(model) <= 0) {
			return "{success:false,msg:'添加失败：数据库操作失败或者该用户名已存在!'}";
		}
		return "{success:true,msg:'添加成功!'}";
	}

	// 管理员删除
	public String del_admin(String name) throws SQLException {
		if (name == null || name.isEmpty()) {
			return "{success:false,msg:'非法操作！'}";
		}

		String sql_query = "select auth from admin where name='" + name + "'";
		String sql_del = "delete  from admin where name='" + name + "'";

		ResultSet rs_query = DBHelper.executeQuery(sql_query);
		if (rs_query.next()) {
			if (rs_query.getString("auth").equals(AdminModel.SUPER_ADMIN)) {
				return "{success:false,msg:'删除失败：超级管理员不能被删除！'}";
			}
		}
		if (DBHelper.executeNonQuery(sql_del) <= 0) {
			return "{success:false,msg:'删除失败：数据库操作失败或者该用户名不存在！'}";
		}
		return "{success:true,msg:'删除成功！'}";

	}

	// 管理员登录
	public String admin_login(String name, String password) throws SQLException {
		String sql = " select * from admin where name ='" + name
				+ "' and password='" + password + "' ";
		ResultSet rs = DBHelper.executeQuery(sql);
		if (rs.next()) {
			this.set_admin_auth(rs.getString("auth"));
			this.set_admin_name(rs.getString("name"));
			return "{success:true,msg:'登录成功！'}";
		}
		return "{success:false,msg:'登录失败：用户名或者密码错误！'}";
	}

	// 管理员查看
	public String query_admin(String page, String limit) throws SQLException {

		int start, end;
		try {
			int i_page = Integer.parseInt(page);
			int i_limit = Integer.parseInt(limit);
			start = (i_page - 1) * i_limit;
			end = (i_page) * i_limit - 1;
		} catch (Exception e) {
			start = 0;
			end = 0;
		}

		String sql_query = "select name,auth from admin ";
		String sql_total = "select count(*)as total from admin ";
		if (!this.is_super_admin()) {
			sql_query = "select name,auth from admin where name='"
					+ this.get_admin_name() + "'";
			sql_total += "select name,auth from admin where name='"
					+ this.get_admin_name() + "'";
		}
		sql_query += " limit " + start + "," + end + "";
		sql_total += " limit " + start + "," + end + "";

		String json = "{total:";
		ResultSet rs_query = DBHelper.executeQuery(sql_query);
		ResultSet rs_total = DBHelper.executeQuery(sql_total);

		if (rs_total.next()) {
			json += rs_total.getString("total");
		}
		json += ",rows:[";
		while (rs_query.next()) {
			json += "{name:'" + rs_query.getString("name") + "',auth:'"
					+ rs_query.getString("auth") + "'},";
		}
		return CommFuns.TrimEnd(json, ",") + "]}";
	}

	// 密码修改
	public String password_modify(String name, String old, String refresh) {
		if (CommFuns.CheckNull(new String[] { old, refresh })) {
			return "{success:false,msg:'非法操作！'}";
		}
		String sql = "update admin set password='" + refresh + "' where name='"
				+ name + "' and password='" + old + "'";

		if (DBHelper.executeNonQuery(sql) > 0) {
			return "{success:false,msg:'密码修改成功！'}";
		}
		return "{success:false,msg:'密码修改失败：密码错误或者数据库操作失败！'}";
	}

	// 消息删除
	public String del_chat(String strDate) {
		if (strDate == null || strDate.isEmpty()) {
			return "{success:false,msg:'非法操作！'}";
		}

		strDate = CommFuns.GetMaxTimeOfTheDay(strDate);
		if (strDate == null) {
			return "{success:false,msg:'时间格式有误！'}";
		}

		String sql = "delete from chat where time <='" + strDate + "'";

		DBHelper.executeNonQuery(sql);
		return "{success:true,msg:'聊天记录删除成功！'}";
	}

	// 用户删除
	public String del_user(String userNames) {
		if (userNames == null || userNames.isEmpty()) {
			return "{success:false,msg:'非法操作！'}";
		}
		userNames = CommFuns.StringSplit(userNames);

		String del_user = "delete from userInfo where userName in(" + userNames
				+ ")";
		String del_chat = "delete from chat where formNamein(" + userNames
				+ ") or toName in(" + userNames + ")";
		String del_friend = "delete from friend where fromName in(" + userNames
				+ ") or toName in (" + userNames + ")";

		DBHelper.executeNonQuery(del_user);
		DBHelper.executeNonQuery(del_chat);
		DBHelper.executeNonQuery(del_friend);

		return "{success:true,msg:'删除成功！'}";
	}

	// 查看所有用户
	public String query_user(String page, String limit) throws SQLException {
		int start, end;
		try {
			int i_page = Integer.parseInt(page);
			int i_limit = Integer.parseInt(limit);
			start = (i_page - 1) * i_limit;
			end = (i_page) * i_limit - 1;
		} catch (Exception e) {
			start = 0;
			end = 0;
		}

		String sql = "select userName,sex,mark,img,regTime from userInfo limit "
				+ start + " ," + end + " ";
		String total = "SELECT count(*)as total  from userinfo ";
		String json = "{total:";

		ResultSet rs_total = DBHelper.executeQuery(total);
		ResultSet rs_query = DBHelper.executeQuery(sql);

		if (rs_total.next()) {
			json += rs_total.getString("total");
		}
		json += ",rows:[";
		while (rs_query.next()) {
			json += "{" + "userName:'" + rs_query.getString("userName") + "',"
					+ "sex:'" + rs_query.getString("sex") + "'," + "mark:'"
					+ rs_query.getString("mark") + "'," + "img:'"
					+ rs_query.getString("img") + "'," + "regTime:'"
					+ rs_query.getString("regTime") + "'},";
		}

		return CommFuns.TrimEnd(json, ",") + "]}";
	}

	// 注销
	public String admin_out(){
		this.session.invalidate();
		return "{success:true,msg:'注销成功！'}";
	}

	private int add_admin(AdminModel model) {

		if (CommFuns.CheckNull(new String[] { model.getName(),
				model.getPassword(), model.getAuth() })) {
			return -1;
		}

		String sql = " insert into admin (name,password,auth) values (" + "'"
				+ model.getName() + "'," + "'" + model.getPassword() + "',"
				+ "'" + model.getAuth() + "'" + ")";

		if (is_exist_admin(model.getName(), null)) {
			return -1;
		}

		return DBHelper.executeNonQuery(sql);
	}

	private Boolean is_exist_admin(String name, String password) {
		String sql = " select * from admin where name ='" + name + "' ";
		if (password != null) {
			sql += " and passwoed='" + password + "' ";
		}

		return DBHelper.isExist(sql);
	}
}
