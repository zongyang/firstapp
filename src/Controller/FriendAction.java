package Controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

import DB.DBHelper;
import Model.FriendModel;
import Model.UserModel;

import com.google.gson.Gson;

public class FriendAction {

	public static String get_friend_lsit_req(String userName)
			throws SQLException {
		if (userName == null || userName.isEmpty()) {
			return "{success:false,msg:'非法操作！'}";
		}

		LinkedList<UserModel> list = new LinkedList<UserModel>();
		Gson gson = new Gson();
		String sql = " SELECT t2.userName,t2.img,t2.mark,t2.regTime,t2.sex,t3.name as area FROM ( ";
		sql += " SELECT toName AS userName FROM friend WHERE fromName = '"
				+ userName + "' ";
		sql += " UNION ";
		sql += " SELECT fromName AS userName FROM friend WHERE toName = '"
				+ userName + "' ";
		sql += " ) AS t1 ";
		sql += " INNER JOIN userinfo t2 ON t1.userName = t2.userName ";
		sql += " left JOIN area t3 on t2.area=t3.id ";
		ResultSet rs = DBHelper.executeQuery(sql);

		while (rs.next()) {
			UserModel user = new UserModel();
			user.setUserName(rs.getString("userName"));
			user.setImg(rs.getString("img"));
			user.setMark(rs.getString("mark"));
			user.setRegTime(rs.getString("regTime"));
			user.setSex(rs.getString("sex"));
			user.setArea(rs.getString("area"));
			list.add(user);
		}

		return "{success:true,msg:" + gson.toJson(list) + "}";

	}

	public static Boolean is_friend(String user1, String user2) {
		Boolean flg = CommFuns.CheckNull(new String[] { user1, user2 });

		if (flg) {
			return true;
		}

		if (user1.equals(user2)) {
			return true;
		}

		String sql = "select * from friend where (fromName='" + user1
				+ "' and toName='" + user2 + "') or (toName='" + user1
				+ "' and fromName='" + user2 + "')";

		return DBHelper.isExist(sql);
	}

	public static int add_friend_req(FriendModel model) {

		if (is_friend(model.getFromName(), model.getToName())) {
			return -1;
		}

		String sql = "insert into friend values('" + model.getFromName()
				+ "','" + model.getToName() + "')";
		return DBHelper.executeNonQuery(sql);
	}

	public static String add_friend_req(String strModel) {

		if (strModel == null || strModel.isEmpty()) {
			return "{success:false,msg:'非法操作！'}";
		}

		Gson gson = new Gson();
		FriendModel model = gson.fromJson(strModel, FriendModel.class);

		if (add_friend_req(model) <= 0) {
			return "{success:false,msg:'添加好友失败：数据库操作失败!'}";
		}

		return "{success:false,msg:'添加" + model.getToName() + "为好友成功！'}";
	}
}
