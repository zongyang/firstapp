package Model;

//好友关系模型类
public class FriendModel {

	private String fromName;

	private String toName;

	public FriendModel() {
		
		this.fromName = "";
		
		this.toName = "";
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
}
