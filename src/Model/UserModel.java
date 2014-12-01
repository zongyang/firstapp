package Model;

//人员基本信息模型类
public class UserModel {
	private String id;
	private String email;
	private String psw;
	private String nickName;
	private String online;
	private String ip;

	public UserModel() {
		this.email = "";
		this.psw = "";
		this.nickName = "";
		this.online = "";
		this.ip = "";
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPwd() {
		return psw;
	}

	public void setPwd(String pwd) {
		this.psw = pwd;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getOnline() {
		return online;
	}

	public void setOnline(String online) {
		this.online = online;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getPsw() {
		return psw;
	}

	public void setPsw(String psw) {
		this.psw = psw;
	}
}
