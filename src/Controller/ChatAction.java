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

	public static int add_chat_record(ChatModel mdoel) {
		LinkedList<ChatModel> list = new LinkedList<ChatModel>();
		list.push(mdoel);
		return add_chat_record(list);
	}

	public static int add_chat_record(LinkedList<ChatModel> mdoels) {
		String insert = "";
		ChatModel temp;
		int len = mdoels.size();

		if (len == 0) {
			return -1;
		}

		for (int i = 0; i < len; i++) {
			temp = mdoels.get(i);
			insert += "INSERT INTO chat (fromName,toName,content,time) VALUES ('"
					+ temp.getFromName()
					+ "', '"
					+ temp.getToName()
					+ "', '"
					+ temp.getContent() + "', '" + temp.getTime() + "') ;";
		}

		return DBHelper.executeNonQuery(insert);

	}

	public static String get_chat_record(String self, String friend)
			throws SQLException {
		if (CommFuns.CheckNull(new String[] { self, friend })) {
			return "{success:false,msg:'非法操作！'}";
		}

		if (self == friend) {
			return "{success:false,msg:'你在逗我玩呢！'}";
		}

		String sql = " select * from chat where fromName='" + self
				+ "' and toName ='" + friend + "' ";
		sql += " union ";
		sql += " select * from chat where toName='" + self
				+ "' and fromName ='" + friend + "' ";

		String json = "[";
		ResultSet rs = DBHelper.executeQuery(sql);
		while (rs.next()) {
			json += "{id:'" + rs.getString("id") + "',formName:'"
					+ rs.getString("fromName") + "',toName:'"
					+ rs.getString("toName") + "',content:'"
					+ rs.getString("content") + "',time:'"
					+ rs.getString("time") + "'},";
		}
		json = CommFuns.TrimEnd(json, ",") + "]";
		return json;
	}

	public static String get_chat_latest_record(String userName)
			throws SQLException {
		if (CommFuns.CheckNull(new String[] { userName })) {
			return "{success:fasle,msg:'非法操作！'}";
		}

		String sql = "SELECT * from chat_view where fromName='" + userName
				+ "' or toName='" + userName+"' ";
		ResultSet rs = DBHelper.executeQuery(sql);
		HashMap<String, ChatModel> hm = new HashMap<String, ChatModel>();
		String json, key;// key存储的是好友的名字
		while (rs.next()) {
			ChatModel chat = new ChatModel();
			if (rs.getString("fromName").equals(userName)) {
				key = rs.getString("toName");
				chat.setImg(rs.getString("toImg"));
			} else {
				key = rs.getString("fromName");
				chat.setImg(rs.getString("fromImg"));
			}
			if (hm.containsKey(key)) {
				continue;
			}
			chat.setId(rs.getString("id"));
			chat.setFromName(rs.getString("fromName"));
			chat.setToName(rs.getString("toName"));
			chat.setContent(rs.getString("content"));
			chat.setTime(rs.getString("time"));
			hm.put(key, chat);
		}

		Iterator<String> iter = hm.keySet().iterator();
		json = "[";
		while (iter.hasNext()) {
			key = iter.next();
			json += "{id:'" + hm.get(key).getId() + "',";
			json += "userName:'" + key + "',";
			json += "fromName:'" + hm.get(key).getFromName() + "',";
			json += "toName:'" + hm.get(key).getToName() + "',";
			json += "content:'" + hm.get(key).getContent() + "',";
			json += "time:'" + hm.get(key).getTime() + "',";
			json += "userName:'" + key + "',";
			json += "img:'" + hm.get(key).getImg() + "'},";
		}
		json = CommFuns.TrimEnd(json, ",") + "]";
		return json;
	}

}
