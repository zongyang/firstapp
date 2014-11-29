package Controller;

import java.sql.ResultSet;
import java.sql.SQLException;

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
	
	public static String getFriendByUser(String id) throws SQLException{
		String sql="select * from friend_view where `from`='"+id+"' or `to`='"+id+"'";
		ResultSet rs = DBHelper.executeQuery(sql);
		String json="[";
		while(rs.next()){
			if(rs.getString("from").endsWith(id)){
				json+="{\"id\":\""+rs.getString("to")+"\",\"nickName\":\""+rs.getString("toName")+"\"},";
			}
			if(rs.getString("to").endsWith(id)){
				json+="{\"id\":\""+rs.getString("from")+"\",\"nickName\":\""+rs.getString("fromName")+"\"},";
			}
		}
		json=CommFuns.TrimEnd(json, ",");
		json+="]";
		//return "{success:true,msg:"+json+"}";
		return CommFuns.getTip(true, json, "");
	}
}
