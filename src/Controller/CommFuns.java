package Controller;

import Model.TipModel;

//公用类：常用方法的封装
public class CommFuns {

	//获得提示消息
	public static String getTip(boolean success, String msg, String actions) {
		TipModel tip = new TipModel(success, msg, actions);
		return tip.ToJSON();
	}

	//字符串过滤
	public static String filter(String message) {

		if (message == null)
			return (null);

		char content[] = new char[message.length()];
		message.getChars(0, message.length(), content, 0);
		StringBuilder result = new StringBuilder(content.length + 50);
		for (int i = 0; i < content.length; i++) {
			switch (content[i]) {
			case '<':
				result.append("&lt;");
				break;
			case '>':
				result.append("&gt;");
				break;
			case '&':
				result.append("&amp;");
				break;
			case '"':
				result.append("&quot;");
				break;
			case '\n':
				result.append("<br />");
				break;
			default:
				result.append(content[i]);
			}
		}
		return (result.toString());

	}
	public static String TrimEnd(String input, String charsToTrim)
	{
		return input.replaceAll("[" + charsToTrim + "]+$", "");		
	}	
	
	public static String TrimStart(String input, String charsToTrim)
	{
		return input.replaceAll("^[" + charsToTrim + "]+", "");	
	}

	public static String Trim(String input, String charsToTrim)
	{
		return input.replaceAll("^[" + charsToTrim + "]+|[" + charsToTrim + "]+$", "");	
	}

}
