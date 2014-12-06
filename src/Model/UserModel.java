package Model;

//人员基本信息模型类
public class UserModel {
	private String userName;
	private String password;
	private String area;
	private String sex;
	private String img;
	private String mark;
	private String regTime;

	public UserModel() {
		this.userName = "";
		this.password = "";
		this.area = "";
		this.sex = "";
		this.img = "";
		this.mark = "";
		this.setRegTime("");
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public String getMark() {
		return mark;
	}

	public void setMark(String mark) {
		this.mark = mark;
	}



	public String getRegTime() {
		return regTime;
	}

	public void setRegTime(String regTime) {
		this.regTime = regTime;
	}

}
