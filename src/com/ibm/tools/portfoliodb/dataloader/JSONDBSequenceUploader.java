package com.ibm.tools.portfoliodb.dataloader;


import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.ibm.tools.utils.MongoDBHelper;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;

public class JSONDBSequenceUploader {

	private static final String JSONDB_SEQUENCES_COLLECTION = "pfdata_seq";
	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testUploadSequence() throws Exception{
		importFromJSONData("C:\\Users\\SUDDUTT1\\SD_DATA\\NONPROJ\\Data\\SEQUENCE_DATA_V1.json");
	}
	private void importFromJSONData(String importPath) throws Exception
	{
		BufferedReader br = new BufferedReader(  
			     new FileReader(importPath));  
		
		//Read the file content in a string
		StringBuilder strBuilder = new StringBuilder();
		String line = "";
		while((line = br.readLine())!=null)
		{
			strBuilder.append(line);
		}
		br.close();
		String jsonString  = strBuilder.toString();	
		Gson gson = new GsonBuilder().create();
		Map[] dataList = gson.fromJson(jsonString, Map[].class);
		DBCollection collection = MongoDBHelper.getCollection(JSONDB_SEQUENCES_COLLECTION);
		for(Map map:dataList)
		{
			Double count = (Double)map.get("count");
			String seqId = (String)map.get("seq_id");
			BasicDBObject dbObject = new BasicDBObject();
			dbObject.put("count", new Long(count.longValue()));
			dbObject.put("seq_id", seqId);
			collection.save(dbObject);
			//System.out.println(map);
		}
	}

}
