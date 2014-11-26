package Model;

//好友关系模型类
public class FriendModel {
	private String from;
	private String fromName;
	private String to;
	private String toName;

	public FriendModel() {
		this.from = "";
		this.fromName = "";
		this.to = "";
		this.toName = "";
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getFromName() {
		return fromName;
	}

	public void setFromName(String fromName) {
		this.fromName = fromName;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public String getToName() {
		return toName;
	}

	public void setToName(String toName) {
		this.toName = toName;
	}
}
