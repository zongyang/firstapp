package Controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
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

		return "{success:true,msg:"+gson.toJson(list)+"}";

	}
	public static Boolean is_friend(String user1,String user2){
		Boolean flg =CommFuns.CheckNull(new String []{user1,user2});

		if (flg) {
			return true;
		}

		if (user1.equals(user2)) {
			return true;
		}

		String sql = "select * from friend where (formName='"+user1+"' and toName='"+user2+"') or (toName='"+user1+"' and fromName='"+user2+"')";

		return DBHelper.isExist(sql);
	} 
	
	public static  int add_friend_req(FriendModel model){
		
		if(is_friend(model.getFromName(), model.getToName())){
			return -1;
		}
		
		String sql="insert into friend values('"+model.getFromName()+"','"+model.getToName()+"')";
		return DBHelper.executeNonQuery(sql);
	}
	public static  String add_friend_req(String strModel){

		if (strModel == null||strModel.isEmpty()) {
			return "{success:false,msg:'非法操作！'}";
		}
		
		Gson gson = new Gson();
		FriendModel model = gson.fromJson(strModel, FriendModel.class);
		
		if(add_friend_req(model)<=0){
			return "{success:false,msg:'添加好友失败：数据库操作失败!'}";
		}
		
		return "{success:false,msg:'添加"+model.getToName()+"为好友成功！'}";
	}
	
	// 考虑 from和to
	public static String addFriend(String friend) throws SQLException {
		if (friend == null) {
			return null;
		}

		Gson gson = new Gson();
		FriendModel model = gson.fromJson(friend, FriendModel.class);

		return addFriend(model);
	}

	public static String addFriend(FriendModel friend) throws SQLException {
		/*
		 * String sql = "insert into friend (`from`,`to`) values ('" +
		 * friend.getFrom() + "','" + friend.getTo() + "')";
		 * 
		 * if (isFriend(friend.getFrom(), friend.getTo())) { return
		 * CommFuns.getTip(false, "已经是好友!", ""); }
		 * DBHelper.executeNonQuery(sql); return CommFuns.getTip(true, "添加成功!",
		 * "");
		 */
		return "";
	}

	public static String update(String friend) {
		/*
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
		return CommFuns.getTip(true, "修改成功!", "");*/
		return null;
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

	public static Boolean isFriend(String from, String to) throws SQLException {

		Boolean flg = (from == null || from.isEmpty() || to == null || to
				.isEmpty());

		if (flg) {
			return true;
		}

		if (from.equals(to)) {
			return true;
		}

		String sql = "select * from friend where (`from`='" + from
				+ "' and `to`='" + to + "') or( `from`='" + to + "' and `to`='"
				+ from + "')";

		return DBHelper.isExist(sql);

	}


	public static LinkedList<FriendModel> getFirends(String user)
			throws SQLException {
/*
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

		return friends;*/
		return null;
	}

}
