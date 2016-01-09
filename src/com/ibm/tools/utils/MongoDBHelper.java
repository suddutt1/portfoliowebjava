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
				String serviceJSON = System.getenv("VCAP_SERVICES");
				if (serviceJSON != null) {
					BasicDBObject obj = (BasicDBObject) JSON.parse(serviceJSON);
					String thekey = null;
					Set<String> keys = obj.keySet();
					LOGGER.log(Level.INFO,
							"|MONGODB_HELPER|Searching through VCAP keys");
					// Look for the VCAP key that holds the SQLDB information
					for (String eachkey : keys) {
						LOGGER.log(Level.INFO, "|MONGODB_HELPER|Key is: "
								+ eachkey);
						if (eachkey.contains("mongolab")) {
							thekey = eachkey;
						}
					}
					if (thekey == null) {
						LOGGER.log(Level.INFO,
								"|MONGODB_HELPER|Cannot find any MONGOLAB service in the VCAP; Falling back to properties file");
						MongoClientURI uri = new MongoClientURI(
								LocalVCAPProperties.getLocalProperty("mongodb.url")
										+ "?connectTimeoutMS=300000");
						mongo = new MongoClient(uri);
						db = mongo.getDB(uri.getDatabase());
						System.out.println("CLOUD  ENVIRONMENT with properties "+ LocalVCAPProperties.getLocalProperty("mongodb.url") );

					} else {
						BasicDBList list = (BasicDBList) obj.get(thekey);
						obj = (BasicDBObject) list.get("0");
						LOGGER.log(
								Level.INFO,
								"|MONGODB_HELPER|Service found: "
										+ obj.get("name"));
						obj = (BasicDBObject) obj.get("credentials");
						String dbUri = (String) obj.get("uri");
						MongoClientURI uri = new MongoClientURI(dbUri
								+ "?connectTimeoutMS=300000");
						mongo = new MongoClient(uri);
						db = mongo.getDB(uri.getDatabase());
						LOGGER.log(Level.INFO, "|MONGODB_HELPER|DBURL found: "
								+ dbUri);
					}
					System.out.println("COULD ENVIRONMENT ");
				} else {
					MongoClientURI uri = new MongoClientURI(
							LocalVCAPProperties.getLocalProperty("mongodb.url")
									+ "?connectTimeoutMS=300000");
					mongo = new MongoClient(uri);
					db = mongo.getDB(uri.getDatabase());
					System.out.println("LOCAL ENVIRONMENT . Connecting to "+ LocalVCAPProperties.getLocalProperty("mongodb.url") );
				}
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
