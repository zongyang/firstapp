package Controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;

import com.google.gson.Gson;

import DB.DBHelper;
import Model.ChatModel;

//消息聊类：负责聊天页面非websocket的请求
public class ChatAction {
	
	
	
	
	public static String getChatByFriend(String user, String friend)
			throws SQLException {
		String sql = "select * from chat where (`from`='" + user
				+ "' and `to`='" + friend + "') or (`from`='" + friend
				+ "' and `to`='" + user + "') order by time desc ";
		Gson gson = new Gson();
		LinkedList<ChatModel> models = new LinkedList<ChatModel>();
		ResultSet rs = DBHelper.executeQuery(sql);
		while (rs.next()) {
			ChatModel model = new ChatModel();
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
		String sql = "select * from chat_view where `from`='" + user
				+ "' or `to`='" + user + "' order by time desc";

		Gson gson = new Gson();

		HashMap<String, ChatModel> hm = new HashMap<String, ChatModel>();
		LinkedList<ChatModel> models = new LinkedList<ChatModel>();
		ResultSet rs = DBHelper.executeQuery(sql);

		// 存储每个好友的最近记录
		while (rs.next()) {
			ChatModel model = new ChatModel();
			// 主动发起的(key是friend)
			if (rs.getString("from").equals(user)
					&& !hm.containsKey(rs.getString("to"))) {
				model.setFrom(rs.getString("from"));
				model.setTo(rs.getString("to"));
				model.setContent(rs.getString("content"));
				model.setRecept(rs.getString("recept"));
				model.setTime(rs.getString("time"));
				model.setActions(rs.getString("actions"));
				model.setFriend(rs.getString("to"));
				model.setFriendName(rs.getString("toName"));
				hm.put(rs.getString("to"), model);

			}
			// 好友发起的(key是friend)
			if (rs.getString("to").equals(user)
					&& !hm.containsKey(rs.getString("from"))) {
				model.setTo(rs.getString("to"));
				model.setContent(rs.getString("content"));
				model.setRecept(rs.getString("recept"));
				model.setTime(rs.getString("time"));
				model.setActions(rs.getString("actions"));
				model.setFriend(rs.getString("from"));
				model.setFriendName(rs.getString("fromName"));
				hm.put(rs.getString("from"), model);

			}
		}
		// 字典存储到队列
		String key;
		Iterator<String> iter = hm.keySet().iterator();
		while (iter.hasNext()) {
			key = iter.next();
			models.push(hm.get(key));
		}

		return CommFuns.getTip(true, gson.toJson(models), "");
	}

	public static void insertChatRecord(LinkedList<ChatModel> mdoels) {
		String insert = "";
		ChatModel temp;
		int len = mdoels.size();

		if (len == 0) {
			return;
		}

		for (int i = 0; i < len; i++) {
			temp = mdoels.get(i);
			insert += "INSERT INTO chat (`from`, `to`, `content`, `recept`, `time`, `actions`) VALUES ('"
					+ temp.getFrom()
					+ "', '"
					+ temp.getTo()
					+ "', '"
					+ temp.getContent()
					+ "', '"
					+ temp.getRecept()
					+ "', '"
					+ temp.getTime() + "', '" + temp.getActions() + "'); ";

		}
		DBHelper.executeNonQuery(insert);

	}

}
