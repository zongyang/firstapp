package Model;

public class ChatWsModel {
	public static String SEND_TO_FRIEND = "sendToFriend";
	public static String FRIEND_REQUEST = "friendRequest";
	public static String RECEPT_REQUEST = "receptFriendRequest";
    public static String[] MSG_TYPE={"IM","ALERT","CONFIRM"};//即时消息、弹框、确认框
	
    public ChatWsModel() {
		super();
		this.method = "";
		this.contend = "";
		this.fromName = "";
		this.toName="";
		this.msgType="";
		this.time="";
	}
    
	private String method;//通过ws传过来的方法
	private String contend;//消息内容
	private String fromName;//消息发起人
	private String toName;//消息接收人
	private String msgType;//消息类型
	private String time;//消息时间
	
	public String getMsgType() {
		return msgType;
	}

	public void setMsgType(String msgType) {
		this.msgType = msgType;
	}
	

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}


	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	

	public String getContend() {
		return contend;
	}

	public void setContend(String contend) {
		this.contend = contend;
	}

	public String getFromName() {
		return fromName;
	}

	public void setFromName(String fromName) {
		this.fromName = fromName;
	}

	public String getToName() {
		return toName;
	}

	public void setToName(String toName) {
		this.toName = toName;
	}


	
	public ChatModel toChatModel(){
		ChatModel chat=new ChatModel();
		chat.setContent(this.contend);
		chat.setFromName(this.fromName);
		chat.setToName(this.toName);
		chat.setTime(this.time);
		return chat;
	}
	
	public FriendModel toFriendModel(){
		FriendModel friend=new FriendModel();
		friend.setFromName(this.fromName);
		friend.setToName(this.toName);
		return friend;
	}
	
}
