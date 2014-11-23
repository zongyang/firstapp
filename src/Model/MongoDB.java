package Model;
import java.util.ArrayList;
public class MongoDB {
//�û���
public class User{
	public User(){
		this.chartMsgs=new ArrayList<ChartMsg>();
		this.leaveMsgs=new ArrayList<LeaveMsg>();
		this.firendGroups=new ArrayList<FriendGroup>();
		this.chartGroups=new ArrayList<ChartGroup>();
	}
	private String id;
	private String name;
	private String sex;
	private String psd;//�û�����
	private String img;//�û�ͼ��
	private String reg_time;//�û�ע��ʱ��
	private String sign;//�û�ǩ��
	private String ip;//�û����µ�¼ip
	private ArrayList<ChartMsg> chartMsgs;//�û������¼
	private ArrayList<LeaveMsg> leaveMsgs;//�û�������Ϣ
	private ArrayList<FriendGroup> firendGroups;//�û����ѷ���
	private ArrayList<ChartGroup> chartGroups;//�û�Ⱥ
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getPsd() {
		return psd;
	}
	public void setPsd(String psd) {
		this.psd = psd;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public String getReg_time() {
		return reg_time;
	}
	public void setReg_time(String reg_time) {
		this.reg_time = reg_time;
	}
	public String getSign() {
		return sign;
	}
	public void setSign(String sign) {
		this.sign = sign;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public ArrayList<ChartMsg> getChartMsgs() {
		return chartMsgs;
	}
	public void setChartMsgs(ArrayList<ChartMsg> chartMsgs) {
		this.chartMsgs = chartMsgs;
	}
	public ArrayList<LeaveMsg> getLeaveMsgs() {
		return leaveMsgs;
	}
	public void setLeaveMsgs(ArrayList<LeaveMsg> leaveMsgs) {
		this.leaveMsgs = leaveMsgs;
	}
	public ArrayList<FriendGroup> getFirendGroups() {
		return firendGroups;
	}
	public void setFirendGroups(ArrayList<FriendGroup> firendGroups) {
		this.firendGroups = firendGroups;
	}
	public ArrayList<ChartGroup> getChartGroups() {
		return chartGroups;
	}
	public void setChartGroups(ArrayList<ChartGroup> chartGroups) {
		this.chartGroups = chartGroups;
	}
	
}
//˽����
public class ChartMsg{
	private String time;
	private String content;
	private String from;//������ѵ�id
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getFrom() {
		return from;
	}
	public void setFrom(String from) {
		this.from = from;
	}
	
}
//������Ϣ��
public class LeaveMsg{
	public LeaveMsg(){
		this.ops=new ArrayList<String>();
	}
	private int type;//������Ϣ����
	private String content;//��Ϣ����
	private String from;//������ѵ�id
	private String time;//����ʱ��
	private ArrayList<String> ops;//��¼�ɹ��������������
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getFrom() {
		return from;
	}
	public void setFrom(String from) {
		this.from = from;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public ArrayList<String> getOps() {
		return ops;
	}
	public void setOps(ArrayList<String> ops) {
		this.ops = ops;
	}
	
}
//���˺��ѷ�����
public class FriendGroup{
	
	public FriendGroup() {
		this.friends=new ArrayList<Friend>();
	}
	private String name;
	ArrayList<Friend> friends;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public ArrayList<Friend> getFriends() {
		return friends;
	}
	public void setFriends(ArrayList<Friend> friends) {
		this.friends = friends;
	} 
	
}
//������
public class Friend{
	private String id;//����id
	private String name;
	private String remarkName;//���ѱ���
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getRemarkName() {
		return remarkName;
	}
	public void setRemarkName(String remarkName) {
		this.remarkName = remarkName;
	}
	
}

//Ⱥ��
public class ChartGroup{
	
	public ChartGroup(  ) {
		this.members = new ArrayList<User>();
		this.msgs = new ArrayList<ChartMsg>() ;
	}
	private String id;//Ⱥid
	private String admin;//����Ա
	private String remarkName;//Ⱥ����
	private ArrayList<User> members;
	private ArrayList<ChartMsg> msgs;//��Ϣ
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getAdmin() {
		return admin;
	}
	public void setAdmin(String admin) {
		this.admin = admin;
	}
	public String getRemarkName() {
		return remarkName;
	}
	public void setRemarkName(String remarkName) {
		this.remarkName = remarkName;
	}
	public ArrayList<User> getMembers() {
		return members;
	}
	public void setMembers(ArrayList<User> members) {
		this.members = members;
	}
	public ArrayList<ChartMsg> getMsgs() {
		return msgs;
	}
	public void setMsgs(ArrayList<ChartMsg> msgs) {
		this.msgs = msgs;
	}
	
	
}
}
