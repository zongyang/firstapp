package Model;

public class ChatWsModel {
	public static String SEND_TO_FRIEND = "sendToFriend";
	public static String FRIEND_REQUEST = "friendRequest";
	public static String RECEPT_REQUEST = "receptFriendRequest";
    public static String[] MSG_TYPE={"IM","ALERT","CONFIRM"};//即时消息、弹框、确认框
	
    public ChatWsModel() {
		super();
		this.method = "";
		this.msg = "";
		this.from = "";
		this.to="";
		this.msgType="";
		this.time="";
	}
    
	private String method;//通过ws传过来的方法
	private String msg;//消息内容
	private String from;//消息发起人
	private String to;//消息接收人
	private String msgType;//消息类型
	private String time;//消息时间
	
	public String getMsgType() {
		return msgType;
	}

	public void setMsgType(String msgType) {
		this.msgType = msgType;
	}
	public String getMsg() {
		return msg;
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


	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}
	
	public ChatModel toChatModel(){
		ChatModel chat=new ChatModel();
		chat.setContent(this.msg);
		chat.setFromName(this.from);
		chat.setToName(this.to);
		chat.setTime(this.time);
		return chat;
	}
	
	public FriendModel toFriendModel(){
		FriendModel friend=new FriendModel();
		friend.setFromName(this.from);
		friend.setToName(this.to);
		return friend;
	}
	
}
