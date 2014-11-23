package com.po;

import com.mongodb.MongoClient;
import com.mongodb.MongoException;
import com.mongodb.WriteConcern;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.DBCursor;
import com.mongodb.ServerAddress;

import java.util.Arrays;
import java.util.List;

import org.bson.types.ObjectId;

public class Mongodb {
	private static String _dbName = "ChartDB";
	private static int _port = 27017;
	private static String _host = "127.0.0.1";
	private static String _user = "";
	private static char[] _psw = {};
	private static DB db = null;

	public static void main(String args[]) {
		try {
			// 连接到 mongodb 服务
			MongoClient mongoClient = new MongoClient(_host, _port);
			// 连接到数据库
			db = mongoClient.getDB(_dbName);
			System.out.println("Connect to database successfully");
			// boolean auth = db.authenticate(_user, _psw);
			// System.out.println("Authentication: "+auth);
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
		}
	}

	// 获取集合（表）
	public static DBCollection getCollection(String collection) {
		return db.getCollection(collection);
	}

	// 插入
	public static void insert(String collection, String key, String val) {
		BasicDBObject doc = new BasicDBObject(key, val);
		getCollection(collection).insert(doc);
	}

	// 刪除
	public static void delete(String collection, String Key, String val) {

	}

	// 更新
	public static void update(String collection, String Key, String val) {

	}

	// 查找对象
	public DBObject findById(String collection, String _id) {
		DBObject obj = new BasicDBObject();
		obj.put("_id", ObjectId.massageToObjectId(_id));
		return getCollection(collection).findOne(obj);
	}

	// 查找集合所有对象
	public List<DBObject> findAll(String collection) {
		return getCollection(collection).find().toArray();
	}
}
