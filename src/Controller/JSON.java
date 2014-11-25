package Controller;

import java.lang.reflect.Type;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;


// JSON类：实现JSON的序列化和反序列化
public class JSON {
	
	private static Gson gson = new Gson(); 

	public static <T> String ObjToJSON(T t) {
		return gson.toJson(t);
	}

	public static <T> String ListToJSON(ArrayList<T> t) {
	    Type type=new	TypeToken<T>(){}.getType();
		return gson.toJson(t,type);
	}
	
	public static <T> T JSONToObj(String str){
		Type type=new	TypeToken<T>(){}.getType();
		 return gson.fromJson(str, type);
	}
	public static <T> ArrayList<T> JSONToList (String str){
		 Type type=new	TypeToken<ArrayList<T>>() {}.getType();
		 return gson.fromJson(str,type);
	}
}
