package Controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;

import com.google.gson.Gson;

import DB.DBHelper;
import Model.ChatMoel;

//消息聊类：负责聊天页面非websocket的请求
public class ChatAction {
	public static String getChatByFriend(String user, String friend)
			throws SQLException {
		String sql = "select * from chat where (`from`='" + user
				+ "' and `to`='" + friend + "') or (`from`='" + friend
				+ "' and `to`='" + user + "') ";
		Gson gson = new Gson();
		LinkedList<ChatMoel> models = new LinkedList<ChatMoel>();
		ResultSet rs = DBHelper.executeQuery(sql);
		while (rs.next()) {
			ChatMoel model = new ChatMoel();
			model.setFrom(rs.getString("from"));
			model.setTo(rs.getString("to"));
			model.setContent(rs.getString("content"));
			model.setRecept(rs.getString("recept"));
			model.setTime(rs.getString("time"));
			model.setActions(rs.getString("actions"));
			models.push(model);
		}

		return CommFuns.getTip(true, gson.toJson(models), "");
	}

	public static String getChatByUser(String user) throws SQLException {
		String sql = "select * from chat where `from`='" + user + "' or `to`='"
				+ user + "' order by time desc";

		Gson gson = new Gson();

		HashMap<String, ChatMoel> hm = new HashMap<String, ChatMoel>();
		LinkedList<ChatMoel> models = new LinkedList<ChatMoel>();
		ResultSet rs = DBHelper.executeQuery(sql);
		
		//存储每个好友的最近记录
		while (rs.next()) {
			ChatMoel model = new ChatMoel();
			//主动发起的
			if (rs.getString("from").equals(user)&&!hm.containsKey(rs.getString("to"))) {
				model.setFrom(rs.getString("from"));
				model.setTo(rs.getString("to"));
				model.setContent(rs.getString("content"));
				model.setRecept(rs.getString("recept"));
				model.setTime(rs.getString("time"));
				model.setActions(rs.getString("actions"));
				hm.put(rs.getString("to"), model);
				
			}
			//好友发起的
			if(rs.getString("to").equals(user)&&!hm.containsKey(rs.getString("from"))){
				model.setTo(rs.getString("to"));
				model.setContent(rs.getString("content"));
				model.setRecept(rs.getString("recept"));
				model.setTime(rs.getString("time"));
				model.setActions(rs.getString("actions"));
				hm.put(rs.getString("from"), model);
			}
			
			//字典存储到队列
			String key;
			Iterator<String> iter = hm.keySet().iterator();
			while (iter.hasNext()) {
			    key = iter.next();
			    models.push(hm.get(key));
			}
		}

		return CommFuns.getTip(true, gson.toJson(models), "");
	}
}
