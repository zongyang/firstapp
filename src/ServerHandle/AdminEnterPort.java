package ServerHandle;

import java.util.Enumeration;
import java.util.Hashtable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Controller.AdminAction;

public class AdminEnterPort {
	public static String getRequestMehthod(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		AdminAction adminAction = new AdminAction(request.getSession());
		Enumeration<String> enu = request.getParameterNames();
		Hashtable<String, String> paraHash = new Hashtable<String, String>();

		while (enu.hasMoreElements()) {
			String paraName = (String) enu.nextElement();
			String paraValue = request.getParameter(paraName);
			paraHash.put(paraName, paraValue);
		}

		if (!paraHash.containsKey("method")) {
			return "";
		}
		// 管理员登录
		if (paraHash.get("method").equals("admin_login")) {
			return adminAction.admin_login(paraHash.get("name"),
					paraHash.get("password"));
		}

		/** 需最高管理员权限 **/
		if (adminAction.is_admin()) {

			// 添加一般管理员
			if (paraHash.get("method").equals("add_admin")) {
				return adminAction.add_admin(paraHash.get("strModel"));
			}
			// 管理员删除
			if (paraHash.get("method").equals("del_admin")) {
				return adminAction.del_admin(paraHash.get("name"));
			}
		}
		/** 需一般管理员权限 **/
		if (adminAction.is_admin()) {
			// 获得当前管理员名
			if(paraHash.get("method").equals("get_admin_name")){
				return adminAction.get_admin_name();
			}
			// 管理员查看(如果是一般的只能看自己的信息)
			if (paraHash.get("method").equals("query_admin")) {
				return adminAction.query_admin(paraHash.get("page"),paraHash.get("limit"));
			}
			// 密码修改
			if (paraHash.get("method").equals("password_modify")) {
				return adminAction.password_modify(paraHash.get("name"),
						paraHash.get("old"), paraHash.get("refresh"));
			}
			// 消息删除
			if (paraHash.get("method").equals("del_chat")) {
				return adminAction.del_chat(paraHash.get("strDate"));
			}
			// 用户删除
			if (paraHash.get("method").equals("del_user")) {
				return adminAction.del_user(paraHash.get("userNames"));
			}
			// 查看所有用户
			if (paraHash.get("method").equals("query_user")) {
				return adminAction.query_user(paraHash.get("page"),paraHash.get("limit"));
			}
			// 发送系统消息
		}

		return "";
	}
}
