package ServerHandle;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.Hashtable;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Controller.AdminAction;
import Controller.CommFuns;


/**
 * Servlet implementation class AdminServer
 */
@WebServlet("/AdminServer")
public class AdminServer extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AdminServer() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String json = "";
		PrintWriter out = response.getWriter();
		response.setContentType("text/html;charset=UTF-8");

		Enumeration<String> enu = request.getParameterNames();
		Hashtable<String, String> paraHash = new Hashtable<String, String>();
		AdminAction adminAction = new AdminAction(request.getSession());

		while (enu.hasMoreElements()) {
			String paraName = (String) enu.nextElement();
			String paraValue = request.getParameter(paraName);
			paraHash.put(paraName, paraValue);
		}

		if (!paraHash.containsKey("method")) {
			json = "";
		}

		
		// 管理员登录
		if (paraHash.get("method").endsWith("admin_login")) {
			try {
				json= adminAction.admin_login(paraHash.get("name"),
						 paraHash.get("password"));
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		/** 需最高管理员权限 **/
		// 添加一般管理员
		if (paraHash.get("method").endsWith("add_admin")) {
			json= adminAction.add_admin(paraHash.get("strModel"));
		}
		// 管理员删除
		if (paraHash.get("method").endsWith("del_admin")) {
			try {
				json= adminAction.del_admin(paraHash.get("name"));
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		/** 需一般管理员权限 **/
		
		// 管理员查看
		if (paraHash.get("method").endsWith("query_admin")) {
			try {
				json= adminAction.query_admin();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		// 密码修改
		if (paraHash.get("method").endsWith("password_modify")) {
			json= adminAction.password_modify(paraHash.get("name"),paraHash.get("old"),paraHash.get("refresh"));
		}
		// 消息删除
		if (paraHash.get("method").endsWith("del_chat")) {
			json= adminAction.del_chat(paraHash.get("strDate"));
		}
		// 用户删除
		if (paraHash.get("method").endsWith("del_user")) {
			json= adminAction.del_user(paraHash.get("userName"));
		}
		// 查看所有用户
		if (paraHash.get("method").endsWith("query_user")) {
			try {
				json= adminAction.query_user();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		// 发送系统消息

		if (json.isEmpty()) {
			json = CommFuns.getTip(false, "非法！", "");
		}
		out.print(json);
		out.flush();
		out.close();

	}

}
