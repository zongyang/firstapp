package Controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

import DB.DBHelper;
import Model.FriendModel;

import com.google.gson.Gson;

public class FriendAction {
	// 考虑 from和to
	public static String insert(String friend) throws SQLException {
		Gson gson = new Gson();
		FriendModel model = gson.fromJson(friend, FriendModel.class);
		String exist_sql = "select * from friend where (`from`='"
				+ model.getFrom() + "' and `to`='" + model.getTo()
				+ "') or( `from`='" + model.getTo() + "' and `to`='"
				+ model.getFrom() + "')";

		String insert_sql = "insert into friend (`from`,from_name,`to`,to_name) values ('"
				+ model.getFrom()
				+ "','"
				+ model.getFromName()
				+ "','"
				+ model.getTo() + "','" + model.getToName() + "')";

		ResultSet rs = DBHelper.executeQuery(exist_sql);
		if (rs.next()) {
			return CommFuns.getTip(false, "已经是好友!", "");
		}
		DBHelper.executeNonQuery(insert_sql);
		return CommFuns.getTip(true, "添加成功!", "");
	}

	public static String update(String friend) {
		Gson gson = new Gson();
		FriendModel model = gson.fromJson(friend, FriendModel.class);
		String sql = "update frind set ";

		if (model.getFromName() != "") {
			sql += " fromName='" + model.getFromName() + "' ";
		}

		if (model.getToName() != "") {
			sql += " toName='" + model.getToName() + "' ";
		}

		sql += " where (`from`='" + model.getFrom() + "' and `to`='"
				+ model.getTo() + "') or (`from`='" + model.getTo()
				+ "' and `to`='" + model.getFrom() + "')";

		DBHelper.executeNonQuery(sql);
		return CommFuns.getTip(true, "修改成功!", "");
	}

	public static String getFriendByUser(String id) throws SQLException {
		String sql = "select * from friend_view where `from`='" + id
				+ "' or `to`='" + id + "'";
		ResultSet rs = DBHelper.executeQuery(sql);
		String json = "[";
		while (rs.next()) {
			if (rs.getString("from").endsWith(id)) {
				json += "{\"id\":\"" + rs.getString("to")
						+ "\",\"nickName\":\"" + rs.getString("toName")
						+ "\"},";
			}
			if (rs.getString("to").endsWith(id)) {
				json += "{\"id\":\"" + rs.getString("from")
						+ "\",\"nickName\":\"" + rs.getString("fromName")
						+ "\"},";
			}
		}
		json = CommFuns.TrimEnd(json, ",");
		json += "]";
		// return "{success:true,msg:"+json+"}";
		return CommFuns.getTip(true, json, "");
	}

	public static LinkedList<FriendModel> getFirends(String user)
			throws SQLException {

		LinkedList<FriendModel> friends = new LinkedList<FriendModel>();
		String sql = "select `fromName`,`from`,`toName`,`to` from friend_view where `fromName`='"
				+ user + "' or `toName`='" + user + "' ";

		if (user == null) {
			return friends;
		}

		String toName, fromName;
		ResultSet rs = DBHelper.executeQuery(sql);
		// 存储每个好友的最近记录
		while (rs.next()) {
			toName = rs.getString("toName");
			fromName = rs.getString("fromName");

			FriendModel friend = new FriendModel();

			// 主动发起的好友请求
			if (fromName.equals(user) && !friends.contains(toName)) {
				friend.setTo(rs.getString("to"));
				friend.setToName(rs.getString("toName"));
				friend.setFrom(rs.getString("from"));
				friend.setFromName(rs.getString("fromName"));
				friends.push(friend);
			}
			// 好友发起的好友请求
			if (toName.equals(user) && !friends.contains(fromName)) {
				friend.setFrom(rs.getString("to"));
				friend.setFromName(rs.getString("toName"));
				friend.setTo(rs.getString("from"));
				friend.setToName(rs.getString("fromName"));
				friends.push(friend);
			}
		}

		return friends;
	}

}
