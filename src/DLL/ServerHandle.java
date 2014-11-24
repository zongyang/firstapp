package DLL;

import java.sql.SQLException;
import java.util.Hashtable;

//选择处理方法
public class ServerHandle {
	public  String  getTargetMehthod(Hashtable<String, String> paraHt) throws SQLException {

		if (!paraHt.containsKey("method")) {
			return "";
		}

		if (paraHt.get("method").endsWith("login")) {
			return UserAdmin.login(paraHt.get("email"), paraHt.get("psw"));
		}

		return "";
	}
}
