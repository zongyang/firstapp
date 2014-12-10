package Model;

//聊天模型类
public class ChatModel {
	private String id;
	private String fromName;
	private String toName;
	private String content;
	private String time;
	private String img;

	public ChatModel() {
		super();
		this.id = "";
		this.fromName = "";
		this.toName = "";
		this.content = "";
		this.time = "";
		this.img = "";
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

}
