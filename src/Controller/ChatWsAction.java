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
import java.util.Iterator;

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

@ServerEndpoint(value = "/chat/{userName}")
public class ChatWsAction {

	private static final Log log = LogFactory.getLog(ChatWsAction.class);
	private static final HashMap<String, ChatWsAction> connections = new HashMap<String, ChatWsAction>();// 连接池（所有在线的连接）
	private String userName;// 当前连接的用户
	private Session session;// 当前连接的session

	@OnOpen
	public void start(Session session, @PathParam("userName") String userName)
			throws SQLException {

		if (userName == null || userName.isEmpty()) {
			return;
		}

		if (connections.containsKey(this.userName)) {
			return;
		}

		this.userName = userName;
		this.session = session;
		connections.put(this.userName, this);
	}

	@OnClose
	public void end() {
		connections.remove(this.userName);
	}

	@OnMessage
	public void incoming(String paras) throws SQLException {
		if (this.userName.isEmpty()) {
			return;
		}

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		Gson gson = new Gson();
		ChatWsModel model = gson.fromJson(paras, ChatWsModel.class);
		model.setTime(sdf.format(new Date()));

		// 给好友发消息
		if (model.getMethod().equals(ChatWsModel.SEND_TO_FRIEND)) {
			send_msg_to_friend(model);
		}

		// 加好友请求
		if (model.getMethod().equals(ChatWsModel.FRIEND_REQUEST)) {
			send_add_req_to_friend(model);
		}

		// 同意加为好友
		if (model.getMethod().equals(ChatWsModel.RECEPT_REQUEST)) {
			accepet_add_req(model);
		}

		// 系统消息
		if (model.getMethod().equals(ChatWsModel.SYSTREM_INFO)) {
			send_system_info(model);
		}

	}

	@OnError
	public void onError(Throwable t) throws Throwable {
		log.error("Chat Error: " + t.toString(), t);
	}

	// 向特定的用户发送数据
	private static void sendMessageToUser(String user, String message) {
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

	private String json_format(String content, String time, String fromName,
			String toName, String msgType) {
		return "{content: '" + content + "' ,time:'" + time + "',fromName:'"
				+ fromName + "',toName:'" + toName + "',msgType:'" + msgType
				+ "'}";
	}

	private String json_format(ChatWsModel model) {
		return json_format(model.getContent(), model.getTime(),
				model.getFromName(), model.getToName(), model.getMsgType());
	}

	private void userSwap(ChatWsModel model){
		String temp;
		temp=model.getFromName();
		model.setFromName(model.getToName());
		model.setToName(temp);
	}
	// 发送系统消息
	private void send_system_info(ChatWsModel model) {
		String key, json;
		Iterator<String> iter = connections.keySet().iterator();
		model.setMsgType(ChatWsModel.MSG_TYPE[0]);
		model.setFromName(this.userName + "[系统消息]");

		while (iter.hasNext()) {
			key = iter.next();
			json = json_format(model);
			sendMessageToUser(key, json);
		}
	}

	// 给好友发消息
	private void send_msg_to_friend(ChatWsModel model) {

		// 设置消息类型
		model.setMsgType(ChatWsModel.MSG_TYPE[0]);
		String json;

		// 插入到数据库
		ChatModel chat = model.toChatModel();
		int result = ChatAction.add_chat_record(chat);

		// 插入失败
		if (result <= 0) {
			model.setContent("发送失败：数据库操作失败！");
			json = json_format(model);
			sendMessageToUser(model.getFromName(), json);
			return;
		}

		json = json_format(model);
		// 发送给好友
		sendMessageToUser(model.getToName(), json);
		// 发送给自己
		sendMessageToUser(model.getFromName(), json);
	}

	// 加好友请求
	private void send_add_req_to_friend(ChatWsModel model) {

		String json;
		// 非法情况的
		if (model.getFromName().equals(model.getToName())) {

			model.setMsgType(ChatWsModel.MSG_TYPE[1]);
			model.setContent("不能添加自己为好友!");

			json = json_format(model);
			sendMessageToUser(model.getFromName(), json);
			return;
		}
		// 已经是好友
		if (FriendAction.is_friend(model.getFromName(), model.getToName())) {

			model.setMsgType(ChatWsModel.MSG_TYPE[1]);
			model.setContent(model.getFromName() + "和" + model.getToName()
					+ "已经是好友！");

			json = json_format(model);
			sendMessageToUser(model.getFromName(), json);
			return;
		}

		else {// 发送申请

			model.setMsgType(ChatWsModel.MSG_TYPE[2]);
			model.setContent(model.getFromName() + " 想加你为好友！");

			json = json_format(model);
			sendMessageToUser(model.getToName(), json);
			return;
		}

	}

	// 同意加为好友
	private void accepet_add_req(ChatWsModel model) {
		String json ;

		model.setMsgType(ChatWsModel.MSG_TYPE[1]);
		FriendModel friend = model.toFriendModel();

		// 添加好友失败
		if (FriendAction.add_friend_req(friend) <= 0) {
			
			model.setContent("添加 " + model.getToName() + " 为好友失败！");
			json = json_format(model);
			sendMessageToUser(model.getFromName(), json);
			
			userSwap(model);
			model.setContent("添加 " + model.getToName() + " 为好友失败！");
			json = json_format(model);
			sendMessageToUser(model.getToName(), json);
			return;
		}

		// 添加好友成功
		//发消息给自己
		model.setContent("添加 " + model.getToName() + " 为好友成功！");
		json = json_format(model);
		sendMessageToUser(model.getFromName(), json);
		
		//发消息给新加的好友
		userSwap(model);
		model.setContent("添加 " + model.getToName() + " 为好友成功！");
		json = json_format(model);
		sendMessageToUser(model.getFromName(), json);
	}

}
