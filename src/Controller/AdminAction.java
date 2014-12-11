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

	private Boolean is_super_admin() {
		if (this.get_admin_auth() == AdminModel.SUPER_ADMIN) {
			return true;
		}
		return false;
	}

	private Boolean is_admin() {
		if (is_super_admin()) {
			return true;
		}
		if (this.get_admin_auth() == AdminModel.GENERAL_ADMIN) {
			return true;
		}
		return false;

	}

	private void set_admin_name(String val) {
		this.session.setAttribute("admin_name", val);
	}

	private String get_admin_name() {
		return session.getAttribute("admin_name").toString();
	}

	private void set_admin_auth(String val) {
		this.session.setAttribute("admin_auth", val);
	}

	private String get_admin_auth() {
		return session.getAttribute("admin_auth").toString();
	}

	// 添加一般管理员
	public String add_admin(String strModel) {

		if (strModel == null || strModel.isEmpty()) {
			return "{success:false,msg:'非法操作！'}";
		}

		Gson gson = new Gson();
		AdminModel model = gson.fromJson(strModel, AdminModel.class);

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

		String sql = "select auth from admin where name='" + name + "'";
		ResultSet rs = DBHelper.executeQuery(sql);
		if (rs.next()) {
			if (rs.getString("auth") == AdminModel.SUPER_ADMIN) {
				return "{success:false,msg:'删除失败：超级管理员不能被删除！'}";
			} else {
				return "{success:true,msg:'删除成功！'}";
			}
		}
		return "{success:false,msg:'删除失败：数据库操作失败或者该用户名不存在！'}";
	}

	// 管理员登录
	public String admin_login(String name, String password) throws SQLException {
		String sql = " select * from admin where name ='" + name + "',password='"+password+"' ";
		ResultSet rs=DBHelper.executeQuery(sql);
		if(rs.next()){
			this.set_admin_auth(rs.getString("auth"));
			this.set_admin_name(rs.getString("name"));
			return "{success:true,msg:'登录成功！'}";
		}
		return "{success:false,msg:'登录失败！'}";
	}

	// 管理员查看
	public String query_admin() throws SQLException {
		String sql = "select name,auth from admin";
		if (!this.is_super_admin()) {
			sql = "select name,auth from admin where name='"
					+ this.get_admin_name() + "'";
		}

		String json = "[";

		ResultSet rs = DBHelper.executeQuery(sql);
		while (rs.next()) {
			json += "{name:'" + rs.getString("name") + "',auth:'"
					+ rs.getString("auth") + "'},";
		}

		return CommFuns.TrimEnd(json, ",") + "]";
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
		return "{success:false,msg:'密码修改失败：数据库操作失败！'}";
	}

	// 消息删除
	public String del_chat(String strDate) {
		if (strDate == null || strDate.isEmpty()) {
			return "{success:false,msg:'非法操作！'}";
		}
		if (CommFuns.IsDate(strDate)) {
			return "{success:false,msg:'请检查时间格式！'}";
		}
		String sql = "delete from chat where time <='" + strDate + "'";

		DBHelper.executeNonQuery(sql);
		return "{success:true,msg:'聊天记录删除成功！'}";
	}

	// 用户删除
	public String del_user(String userName) {
		if (userName == null || userName.isEmpty()) {
			return "{success:false,msg:'非法操作！'}";
		}
		String del_user = "delete from userInfo where userName='" + userName
				+ "'";
		String del_chat = "delete from chat where formName='" + userName
				+ "' or toName='" + userName + "'";
		String del_friend = "delete from friend where fromName='" + userName
				+ "' or toName='" + userName + "'";

		DBHelper.executeNonQuery(del_user);
		DBHelper.executeNonQuery(del_chat);
		DBHelper.executeNonQuery(del_friend);

		return "{success:true,msg:'删除成功！'}";
	}

	// 查看所有用户
	public String query_user() throws SQLException {
		String sql = "select userName,sex,mark,img,regTime from userInfo";
		String json = "[";

		ResultSet rs = DBHelper.executeQuery(sql);
		while (rs.next()) {
			json += "{" + "userName:'" + rs.getString("userName") + "',"
					+ "sex:'" + rs.getString("sex") + "'," + "mark:'"
					+ rs.getString("mark") + "'," + "img:'"
					+ rs.getString("img") + "'," + "regTime:'"
					+ rs.getString("regTime") + "'},";
		}

		return CommFuns.TrimEnd(json, ",") + "]";
	}

	// 发送系统消息

	private int add_admin(AdminModel model) {

		if (CommFuns.CheckNull(new String[] { model.getName(),
				model.getPassword(), model.getAuth() })) {
			return -1;
		}

		String sql = " insert into admin values (" + "'" + model.getName()
				+ "'" + "'" + model.getPassword() + "'" + "'" + model.getAuth()
				+ "'" + ")";

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
