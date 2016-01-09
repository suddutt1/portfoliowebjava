package com.ibm.tools.portfoliodb.dataexport;

import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.ibm.tools.portfoliodb.data.EmployeeDetails;
import com.ibm.tools.utils.MongoDBHelper;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

public class EmployeeDataExporter {

	private static final String EMP_DETAILS_COLLECTION = "EmpDetailsCollection";

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testImportFromJSON() throws Exception{
	
		exportToFile("C:\\Users\\SUDDUTT1\\SD_DATA\\NONPROJ\\Data\\EMP_DATA_V1.json");
		
	}

	
	private void exportToFile(String exportPath) throws Exception
	{
		FileWriter jsonWriter = new FileWriter(exportPath);
		
		Gson gson  = new GsonBuilder().serializeNulls().setPrettyPrinting().create();
		jsonWriter.write(gson.toJson(readData()));
		jsonWriter.close();
	}
	private List<EmployeeDetails> readData()
	{
		
		List<EmployeeDetails> refDataList = new ArrayList<EmployeeDetails>();
		DBCollection collection = MongoDBHelper
				.getCollection(EMP_DETAILS_COLLECTION);
		DBCursor cursor = collection.find();
		if (cursor != null) {
			while (cursor.hasNext()) {
				DBObject result = cursor.next();
				refDataList.add(EmployeeDetails.parseToObject(result));
			}
			cursor.close();
		}
		
		return refDataList;
	}
}
