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

import Model.ChatModel;
import Model.FriendModel;

//消息聊类：使用websocket实现消息的聊天消息的推送

@ServerEndpoint(value = "/chat/{email}")
public class ChatWsAction {

	private static final Log log = LogFactory.getLog(ChatWsAction.class);
	// 连接池
	private static final HashMap<String, ChatWsAction> connections = new HashMap<String, ChatWsAction>();
	private String email;
	private Session session;

	public ChatWsAction() {

	}

	@OnOpen
	public void start(Session session, @PathParam("email") String email) {
		if (email.isEmpty()) {
			return;
		}

		this.email = email;
		this.session = session;
		connections.put(this.email, this);
	}

	@OnClose
	public void end() {
		connections.remove(this.email);
	}

	@OnMessage
	public void incoming(String message) throws SQLException {
		if (email.isEmpty()) {
			return;
		}
		
		String json ;
		String filteredMessage = CommFuns.filter(message.toString());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		String now = sdf.format(new Date());
		LinkedList<FriendModel> friends = FriendAction.getFirends(this.email);
		LinkedList<ChatModel> chats = new LinkedList<ChatModel>();
		
		for (int i = 0; i < friends.size(); i++) {

			ChatModel chatModel = new ChatModel();
			 json = "{\"msg\":\"" + filteredMessage + "\",\"time\":\""
					+ now + "\",\"friend\":\"" + this.email + "\"}";
			
			// send to friend
			sendMessageToUser( friends.get(i).getToName(), json);
			// insert to db
			
			chatModel.setFrom(friends.get(i).getFrom());
			chatModel.setTo(friends.get(i).getTo());
			chatModel.setContent(filteredMessage);
			chatModel.setTime(now);
			chats.push(chatModel);
		}
		ChatAction.insertChatRecord(chats);
		
		//发给自己
		json = "{\"msg\":\"" + filteredMessage + "\",\"time\":\""
				+ now + "\",\"friend\":\"" + this.email + "\"}";
		sendMessageToUser(this.email,json);

		// 1.构建getmehtod 方法 2.传回消息{msg:'',time:'',toName:''} 3.传回的消息包括自己
		// 1.前台：根据判断是否是当前聊天框，如不是出发click相应的事件即可，如果是自己left ，不是right

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
