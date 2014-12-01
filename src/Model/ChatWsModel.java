package Model;

import Controller.CommFuns;

public class ChatWsModel {
	public static String SEND_TO_FRIEND = "sendToFriend";
	public static String FRIEND_REQUEST = "friendRequest";
    public static String[] MSG_TYPE={"IM","ALERT"};//即时消息、弹框
	
    public ChatWsModel() {
		super();
		this.method = "";
		this.msg = "";
		this.friend = "";
		this.msgType="";
	}
    
	private String method;
	private String msg;
	private String friend;
	private String msgType;
	
	public String getMsgType() {
		return msgType;
	}

	public void setMsgType(String msgType) {
		this.msgType = msgType;
	}


	
	

	public String getMsg() {
		return CommFuns.filter(msg);
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public String getFriend() {
		return friend;
	}

	public void setFriend(String friend) {
		this.friend = friend;
	}
}
