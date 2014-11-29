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

//消息聊类：使用websocket实现消息的聊天消息的推送

@ServerEndpoint(value = "/chat/{email}")
public class ChatWsAction {

    private static final Log log = LogFactory.getLog(ChatWsAction.class);
    //连接池
    private static final HashMap<String,ChatWsAction> connections =new HashMap<String,ChatWsAction>();
    private String email;
    private Session session;
    
    public ChatWsAction() {

    }


    @OnOpen
    public void start(Session session,@PathParam("email") String email) {
    	if(email.isEmpty()){
    		return ;
    	}
    	
    	this.email=email;
    	this.session = session;
        connections.put(this.email,this);
    }


    @OnClose
    public void end() {
    	connections.remove(this.email);
    }


    @OnMessage
    public void incoming(String message) throws SQLException {
    	if(email.isEmpty()){
    		return ;
    	}
    	
        String filteredMessage = CommFuns.filter(message.toString());
        LinkedList<String> friends= UserAction.getFirends(this.email);
        
        for(int i=0;i<friends.size();i++){
        	sendMessageToUser(friends.get(i),filteredMessage);
        }
        
    }

    @OnError
    public void onError(Throwable t) throws Throwable {
        log.error("Chat Error: " + t.toString(), t);
    }
    
    //向特定的用户发送数据  
    public static void sendMessageToUser(String user,String message){  
        try {   
            System.out.println("send message to user : " + user + " ,message content : " + message);  
            ChatWsAction ws = connections.get(user);  
            if(ws != null){  
            	ws.session.getBasicRemote().sendText(message); 
            }  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
    } 

}
