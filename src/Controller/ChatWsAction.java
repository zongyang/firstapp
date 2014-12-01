package Controller;

/*
 *  Licensed to the Apache Software Foundation (ASF) under one or more
 *  contributor license agreements.  See the NOTICE file distributed with
 *  this work for additional information regarding copyright ownership.
 *  The ASF licenses this file to You under the Apache License, Version 2.0
 *  (the "License"); you may not use this file except in compliance with
 *  the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;

import com.google.gson.Gson;

import Model.ChatModel;
import Model.ChatWsModel;
import Model.FriendModel;

//消息聊类：使用websocket实现消息的聊天消息的推送

@ServerEndpoint(value = "/chat/{email}")
public class ChatWsAction {

	private static final Log log = LogFactory.getLog(ChatWsAction.class);
	// 连接池
	private static final HashMap<String, ChatWsAction> connections = new HashMap<String, ChatWsAction>();
	private String email;
	private String id;
	private Session session;

	public ChatWsAction() {

	}

	@OnOpen
	public void start(Session session, @PathParam("email") String email)
			throws SQLException {
		if (email.isEmpty()) {
			return;
		}

		if (connections.containsKey(this.email)) {
			return;
		}

		this.email = email;
		this.session = session;
		this.id = UserAction.getIdByName(this.email);
		connections.put(this.email, this);
	}

	@OnClose
	public void end() {
		connections.remove(this.email);
	}

	@OnMessage
	public void incoming(String paras) throws SQLException {
		if (this.email.isEmpty()) {
			return;
		}

		String json;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		String now = sdf.format(new Date());
		Gson gson = new Gson();
		ChatWsModel model = gson.fromJson(paras, ChatWsModel.class);

		// 给好友发消息
		if (model.getMethod().equals(ChatWsModel.SEND_TO_FRIEND)) {

			model.setMsgType(ChatWsModel.MSG_TYPE[0]);

			json = "{msg: '" + model.getMsg() + "' ,time:'" + now
					+ "',friend:'" + this.email + "',msgType:'"
					+ model.getMsgType() + "'}";
			// 给好友
			/*
			 * json = "{\"msg\":\"" + model.getMsg() + "\",\"time\":\"" + now +
			 * "\",\"friend\":\"" + this.email + "\"}";
			 */

			sendMessageToUser(model.getFriend(), json);

			// 给自己
			/*
			 * json = "{\"msg\":\"" + model.getMsg() + "\",\"time\":\"" + now +
			 * "\",\"friend\":\"" + this.email + "\"}";
			 */
			sendMessageToUser(this.email, json);

			// 插入到数据库
			ChatModel chat = new ChatModel();
			LinkedList<ChatModel> chats = new LinkedList<ChatModel>();
			chat.setTo(UserAction.getIdByName(model.getFriend()));
			chat.setFrom(this.id);
			chat.setContent(model.getMsg());
			chat.setTime(now);
			chats.push(chat);
			ChatAction.insertChatRecord(chats);

		}

		// 加好友请求
		if (model.getMethod().equals(ChatWsModel.FRIEND_REQUEST)) {
			String friendId = UserAction.getIdByName(model.getFriend());
			// 已经是好友
			if (FriendAction.isFriend(this.id, friendId)) {
				model.setMsgType(ChatWsModel.MSG_TYPE[1]);
				json = "{msg: '" + this.email + "和" + model.getFriend()
						+ "已经是好友！' ,time:'" + now + "',friend:'" + this.email
						+ "',msgType:'" + model.getMsgType() + "'}";
				sendMessageToUser(this.email, json);
			}
			// 发送申请
			else {
				model.setMsgType(ChatWsModel.MSG_TYPE[2]);
				json = "{msg: '" + this.email + " 想加你为好友！' ,time:'" + now
						+ "',friend:'" + this.email + "',friendId:'" + this.id
						+ "',msgType:'" + model.getMsgType() + "'}";
				sendMessageToUser(model.getFriend(), json);
			}
		}

		// 同意加为好友
		if (model.getMethod().equals(ChatWsModel.RECEPT_REQUEST)) {
			
			//此时msg的内容是 fromName，friend是fromId
			
			model.setMsgType(ChatWsModel.MSG_TYPE[1]);

			// 向数据添加关系
			FriendModel fm = new FriendModel();
			fm.setFrom(model.getMsg());
			fm.setTo(this.id);
			FriendAction.addFriend(fm);

			// 返回给自己
			json = "{msg: ' 添加 " + model.getFriend() + " 为好友成功！' ,time:'" + now
					+ "',friend:'" + model.getFriend() + "',msgType:'"
					+ model.getMsgType() + "'}";
			sendMessageToUser(this.email, json);

			// 返回给申请加好友的人
			json = "{msg: ' 添加 " + this.email + " 为好友成功！' ,time:'" + now
					+ "',friend:'" + this.email + "',msgType:'"
					+ model.getMsgType() + "'}";
			sendMessageToUser(model.getFriend(), json);
		}

	}

	@OnError
	public void onError(Throwable t) throws Throwable {
		log.error("Chat Error: " + t.toString(), t);
	}

	// 向特定的用户发送数据
	public static void sendMessageToUser(String user, String message) {
		try {
			System.out.println("send message to user : " + user
					+ " ,message content : " + message);
			ChatWsAction ws = connections.get(user);
			if (ws != null) {
				ws.session.getBasicRemote().sendText(message);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
