package ServerHandle;

import java.util.Enumeration;
import java.util.Hashtable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Controller.ChatAction;

import Controller.FriendAction;
import Controller.UserAction;

//入口函数处理类:通过url的参数找到对用的处理方法
public class ActionEnterPort {

	public static String getRequestMehthod(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

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

		/**
		 * 注册和登录
		 **/
		if (paraHash.get("method").equals("reg_req")) {
			return UserAction.reg_req(paraHash.get("userName"),
					paraHash.get("email"), paraHash.get("password"));
		}
		if (paraHash.get("method").equals("forget_req")) {
			return UserAction.forget_req(paraHash.get("userName"),
					paraHash.get("email"), paraHash.get("password"));
		}
		if (paraHash.get("method").equals("area_req")) {
			return UserAction.area_req(paraHash.get("areaId"));
		}
		if (paraHash.get("method").equals("log_req")) {
			return UserAction.log_req(paraHash.get("userName"),
					paraHash.get("password"));
		}
		if (paraHash.get("method").equals("info_modify_req")) {
			return UserAction.info_modify_req(paraHash.get("model"));
		}
		if (paraHash.get("method").equals("get_userInfo_req")) {
			return UserAction.get_userInfo_req(paraHash.get("userName"));
		}
		if (paraHash.get("method").equals("get_random_img_req")) {
			return UserAction.get_random_img_req();
		}
		if (paraHash.get("method").equals("icon_uodate_req")) {
			return UserAction.icon_uodate_req(paraHash.get("userName"),
					paraHash.get("img"));
		}
		if (paraHash.get("method").equals("psw_update_req")) {
			return UserAction.psw_update_req(paraHash.get("userName"),
					paraHash.get("old"), paraHash.get("refresh"),
					paraHash.get("again"));
		}
		if (paraHash.get("method").equals("get_friend_lsit_req")) {
			return FriendAction.get_friend_lsit_req(paraHash.get("userName"));
		}
		if (paraHash.get("method").equals("get_chat_record")) {
			return ChatAction.get_chat_record(paraHash.get("self"),
					paraHash.get("friend"));
		}
		if (paraHash.get("method").equals("get_chat_latest_record")) {
			return ChatAction.get_chat_latest_record(paraHash.get("userName"));
		}
		if (paraHash.get("method").equals("get_all_userName_req")) {
			if (paraHash.containsKey("query")) {
				return UserAction.get_all_userName_req(paraHash.get("query"));
			} else {
				return UserAction.get_all_userName_req();
			}
		}

		return "";

	}
}
