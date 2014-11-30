package Model;

import Controller.CommFuns;



public class ChatWsModel {
	public static String SEND_TO_FRIEND="sendToFriend";
	
	private String method;
	private String msg;
	private String friend;



	public ChatWsModel() {
		super();
		this.method="";
		this.msg = "";
		this.friend="";
	}

	public String getMsg() {
		return CommFuns.filter(msg);
	}

	public void setMsg(String msg) {
		this.msg =msg;
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
