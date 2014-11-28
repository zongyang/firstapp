package Model;

//聊天模型类
public class ChatMoel {
	private String id;
	private String from;
	private String to;
	private String content;
	private String recept;
	private String time;
	private String actions;
	
	public ChatMoel() {
		this.id = "";
		this.from = "";
		this.to = "";
		this.recept = "";
		this.time = "";
		this.content="";
		this.actions = "";
	}

	
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public String getRecept() {
		return recept;
	}

	public void setRecept(String recept) {
		this.recept = recept;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getActions() {
		return actions;
	}

	public void setActions(String actions) {
		this.actions = actions;
	}
}
