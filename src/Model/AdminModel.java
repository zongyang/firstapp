package Model;

public class AdminModel {
	public static String  SUPER_ADMIN="最高管理员";
	public static String  GENERAL_ADMIN="通用管理员";
	

	private String name;
	private String password;
	private String auth;
	
	
	public AdminModel() {
		super();
	
		this.name = "";
		this.password = "";
		this.auth = "";
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getAuth() {
		return auth;
	}
	public void setAuth(String auth) {
		this.auth = auth;
	}
	
}
