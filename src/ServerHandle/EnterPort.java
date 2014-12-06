package ServerHandle;

import java.util.Enumeration;
import java.util.Hashtable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Controller.ChatAction;

import Controller.FriendAction;
import Controller.UserAction;

//入口函数处理类:通过url的参数找到对用的处理方法
public class EnterPort {

	public static String getRequestMehthod(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		Enumeration<String> enu = request.getParameterNames();
		Hashtable<String, String> paraHash = new Hashtable<String, String>();
		String ip = request.getRemoteAddr();
		UserAction.session = request.getSession();

		while (enu.hasMoreElements()) {
			String paraName = (String) enu.nextElement();
			String paraValue = request.getParameter(paraName);
			paraHash.put(paraName, paraValue);
		}

		if (!paraHash.containsKey("method")) {
			return "";
		}

		/**
		 * 注册和登录
		 **/
		if (paraHash.get("method").endsWith("reg_req")) {
			return UserAction.reg_req(paraHash.get("userName"),
					paraHash.get("email"), paraHash.get("password"));
		}
		if (paraHash.get("method").endsWith("forget_req")) {
			return UserAction.forget_req(paraHash.get("userName"),
					paraHash.get("email"), paraHash.get("password"));
		}
		if (paraHash.get("method").endsWith("area_req")) {
			return UserAction.area_req(paraHash.get("areaId"));
		}
		if (paraHash.get("method").endsWith("log_req")) {
			return UserAction.log_req(paraHash.get("userName"),
					paraHash.get("password"));
		}
		if (paraHash.get("method").endsWith("info_modify_req")) {
			return UserAction.info_modify_req(paraHash.get("model"));
		}
		if (paraHash.get("method").endsWith("get_userInfo_req")) {
			return UserAction.get_userInfo_req(paraHash.get("userName"));
		}
		if (paraHash.get("method").endsWith("get_random_img_req")) {
			return UserAction.get_random_img_req();
		}
		if (paraHash.get("method").endsWith("icon_uodate_req")) {
			return UserAction.icon_uodate_req(paraHash.get("userName"),
					paraHash.get("img"));
		}
		if (paraHash.get("method").endsWith("psw_update_req")) {
			return UserAction.psw_update_req(paraHash.get("userName"),
					paraHash.get("old"), paraHash.get("refresh"),
					paraHash.get("again"));
		}

		/*****************/
		if (paraHash.get("method").endsWith("login")) {
			return UserAction.login(paraHash.get("email"), paraHash.get("psw"),
					ip);
		}

		if (paraHash.get("method").endsWith("register")) {
			return UserAction.register(paraHash.get("user"), ip);
		}

		if (paraHash.get("method").endsWith("getAllUser")) {
			return (paraHash.containsKey("query")) ? UserAction
					.getAllUser(paraHash.get("query")) : UserAction
					.getAllUser();

		}
		/**
		 * 好友和消息
		 **/
		if (paraHash.get("method").endsWith("addFriend")) {
			return FriendAction.addFriend(paraHash.get("friend"));
		}
		if (paraHash.get("method").endsWith("getFriendByUser")) {
			return FriendAction.getFriendByUser(paraHash.get("id"));
		}
		if (paraHash.get("method").endsWith("getChatByFriend")) {
			return ChatAction.getChatByFriend(paraHash.get("user"),
					paraHash.get("friend"));
		}
		if (paraHash.get("method").endsWith("getChatByUser")) {
			return ChatAction.getChatByUser(paraHash.get("id"));
		}
		return "";

	}
}
