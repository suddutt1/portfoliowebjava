package com.ibm.tools.utils;

import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.ibm.nosql.json.api.BasicDBList;
import com.ibm.nosql.json.api.BasicDBObject;
import com.ibm.nosql.json.util.JSON;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;

public class MongoDBHelper {

	private static Logger LOGGER = Logger.getLogger(MongoDBHelper.class
			.getName());

	// To stop instantiation and access only static way
	private MongoDBHelper() {
		super();
	}

	private static MongoClient mongo;
	private static boolean isInitialized = false;
	private static DB db;

	private static boolean init() {
		try {
			if (mongo == null) {
					MongoClientURI uri = new MongoClientURI(
							LocalVCAPProperties.getLocalProperty("mongodb.url")
									+ "?connectTimeoutMS=300000");
					mongo = new MongoClient(uri);
					db = mongo.getDB(uri.getDatabase());
					System.out.println("COMMON ENVIRONMENT . Connecting to "+ LocalVCAPProperties.getLocalProperty("mongodb.url") );
				isInitialized = true;
			}
		} catch (Exception ex) {
			LOGGER.log(Level.WARNING, "Error in initizlizing MongoBB", ex);
			isInitialized = false;
		}
		return isInitialized;
	}

	public static DBCollection getCollection(String collectionName) {

		if (init()) {
			return db.getCollection(collectionName);
		} else {
			return null;
		}

	}

}
