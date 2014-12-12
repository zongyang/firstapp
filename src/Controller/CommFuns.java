package Controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import Model.TipModel;

//公用类：常用方法的封装
public class CommFuns {

	// 获得提示消息
	public static String getTip(boolean success, String msg, String actions) {
		TipModel tip = new TipModel(success, msg, actions);
		return tip.ToJSON();
	}

	// 字符串过滤
	public static String Filter(String message) {

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

	public static Boolean CheckNull(String[] args) {

		if (args == null) {
			return true;
		}
		for (int i = 0; i < args.length; i++) {
			if (args[i] == null || args.equals("")) {
				return true;
			}
		}
		return false;
	}

	public static String TrimEnd(String input, String charsToTrim) {
		return input.replaceAll("[" + charsToTrim + "]+$", "");
	}

	public static String TrimStart(String input, String charsToTrim) {
		return input.replaceAll("^[" + charsToTrim + "]+", "");
	}

	public static String Trim(String input, String charsToTrim) {
		return input.replaceAll("^[" + charsToTrim + "]+|[" + charsToTrim
				+ "]+$", "");
	}

	public static String StringSplit(String strArr) {
		String[] arr = strArr.split(",");

		strArr = "";
		for (int i = 0; i < arr.length; i++) {
			strArr += "'" + arr[i] + "',";
		}
		return TrimEnd(strArr, ",");

	}

	public static String GetMaxTimeOfTheDay(String str) {
		SimpleDateFormat formate = new SimpleDateFormat("yyyy-MM-dd ");
		Date date;
		Calendar calendar = Calendar.getInstance();
		try {
			date = formate.parse(str);
			calendar.setTime(date);
			calendar.add(Calendar.DATE, 1);
			//calendar.set(Calendar.HOUR_OF_DAY, 0);
			//calendar.set(Calendar.SECOND, 0);
			//calendar.set(Calendar.MINUTE, 0);
			//calendar.set(Calendar.MILLISECOND, 0);
			date = calendar.getTime();
			formate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			return formate.format(date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "";
		}
	}

	public static Boolean IsDate(String str) {
		SimpleDateFormat formate = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		try {
			formate.parse(str);
			return true;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}

}
