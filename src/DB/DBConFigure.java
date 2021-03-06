package DB;

import java.io.File;
import java.io.FileWriter;
import java.net.URISyntaxException;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import com.google.gson.Gson;

public class DBConFigure {

	private String host;
	private String port;
	private String user;
	private String password;
	private String dbName;
	private Document document;
	private String xml_path;

	public DBConFigure() throws URISyntaxException {
		/*this.xml_path = new File(request.getSession().getServletContext()
				.getRealPath(request.getServletPath())).getParent()
				+ "\\init.xml";*/
		this.xml_path=DBConFigure.class.getResource("").toURI().getPath()+"\\init.xml";
		//System.out.println(xml_path);
	}

	private Document xmlLoad() {

		try {
			SAXReader saxReader = new SAXReader();
			document = saxReader.read(new File(xml_path));
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return document;
	}

	private void xmlSave() {
		try {
			XMLWriter writer = new XMLWriter(new FileWriter(new File(xml_path)));
			writer.write(document);
			writer.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	private String xmlRead(String name) {
		if (document == null) {
			document = xmlLoad();
		}
		Element root = document.getRootElement();
		Element el = root.element(name);
		return el.getText();
	}

	private void xmlWrite(String name, String val) {
		if (document == null) {
			document = xmlLoad();
		}
		Element root = document.getRootElement();
		Element el = root.element(name);
		el.setText(val);
	}

	private void save() {
		xmlWrite("host", this.host);
		xmlWrite("port", this.port);
		xmlWrite("user", this.user);
		xmlWrite("host", this.host);
		xmlWrite("password", this.password);
		xmlSave();
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getDbName() {
		return dbName;
	}

	public void setDbName(String dbName) {
		this.dbName = dbName;
	}

	public void setFromXml() {
		setHost(xmlRead("host"));
		setPort(xmlRead("port"));
		setUser(xmlRead("user"));
		setPassword(xmlRead("password"));
		setDbName(xmlRead("dbName"));
	}

	public String setFromStr(String str) {
		if (str.isEmpty() || str.equals("")) {
			return "{success:false,msg:'很明显的非法操作！'}";
		}
		try {
			Gson gson = new Gson();
			DBConFigure temp = gson.fromJson(str, DBConFigure.class);
			setHost(temp.getHost());
			setPort(temp.getPort());
			setUser(temp.getUser());
			setPassword(temp.getPassword());
			setDbName(temp.getDbName());
			save();
			return "{success:true,msg:'数据库连接修改成功！'}";
		} catch (Exception e) {
			return "{success:false,msg:'" + e.getMessage() + "'}";

		}
	}
	public String toJson() {
		/*
		 * 转换有问题，所以自己写了 Gson gson = new Gson(); return gson.toJson(this);
		 */
		return "{host:'" + this.host + "',port:'" + this.port + "',user:'"
				+ this.user + "',password:'" + this.password + "',dbName:'"
				+ this.dbName + "'}";

	}
	public String getUrl(){
		//String url = "jdbc:MySQL://127.0.0.1:3306/chat"; // 数据库
		return "jdbc:MySQL://"+this.host+":"+this.port+"/"+this.dbName+"";
	}
}
