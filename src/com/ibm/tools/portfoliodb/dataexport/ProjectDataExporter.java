package com.ibm.tools.portfoliodb.dataexport;

import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.ibm.tools.portfoliodb.data.ProjAssignmentDetails;
import com.ibm.tools.portfoliodb.data.ProjectDetails;
import com.ibm.tools.portfoliodb.data.UOMDetails;
import com.ibm.tools.utils.MongoDBHelper;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

public class ProjectDataExporter {

	private static final String UOM_DETAILS_COLLECTION = "UOMDetailsCollection";
	private static final String PROJECT_COLLECTION = "ProjectCollection";
	private static final String PROJECTASSINMENT_COLLECTION = "ProjAssignmentCollection";
	private static final String JSONDB_SEQUENCES_COLLECTION = "pfdata_seq";
	
	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testExportToJSON() throws Exception{
	
		exportToFileUOMData("C:\\Users\\SUDDUTT1\\SD_DATA\\NONPROJ\\Data\\UOM_DATA_V1.json");
		exportToFileProjectData("C:\\Users\\SUDDUTT1\\SD_DATA\\NONPROJ\\Data\\PROJECT_DATA_V1.json");
		exportToFileProjectAssignmentData("C:\\Users\\SUDDUTT1\\SD_DATA\\NONPROJ\\Data\\PROJECT_ASSIGN_DATA_V1.json");
		exportToFileSequnceData("C:\\Users\\SUDDUTT1\\SD_DATA\\NONPROJ\\Data\\SEQUENCE_DATA_V1.json");
		
	}

	
	private void exportToFileUOMData(String exportPath) throws Exception
	{
		FileWriter jsonWriter = new FileWriter(exportPath);
		
		Gson gson  = new GsonBuilder().serializeNulls().setPrettyPrinting().create();
		jsonWriter.write(gson.toJson(readUOMData()));
		jsonWriter.close();
	}
	private void exportToFileProjectData(String exportPath) throws Exception
	{
		FileWriter jsonWriter = new FileWriter(exportPath);
		
		Gson gson  = new GsonBuilder().serializeNulls().setPrettyPrinting().create();
		jsonWriter.write(gson.toJson(readProjectData()));
		jsonWriter.close();
	}
	private void exportToFileProjectAssignmentData(String exportPath) throws Exception
	{
		FileWriter jsonWriter = new FileWriter(exportPath);
		
		Gson gson  = new GsonBuilder().serializeNulls().setPrettyPrinting().create();
		jsonWriter.write(gson.toJson(readProjectAssignmentData()));
		jsonWriter.close();
	}
	private void exportToFileSequnceData(String exportPath) throws Exception
	{
		FileWriter jsonWriter = new FileWriter(exportPath);
		
		Gson gson  = new GsonBuilder().serializeNulls().setPrettyPrinting().create();
		jsonWriter.write(gson.toJson(readSequencer()));
		jsonWriter.close();
	}
	
	private List<UOMDetails> readUOMData()
	{
		
		List<UOMDetails> refDataList = new ArrayList<UOMDetails>();
		DBCollection collection = MongoDBHelper
				.getCollection(UOM_DETAILS_COLLECTION);
		DBCursor cursor = collection.find();
		if (cursor != null) {
			while (cursor.hasNext()) {
				DBObject result = cursor.next();
				refDataList.add(UOMDetails.parseToObject(result));
			}
			cursor.close();
		}
		
		return refDataList;
	}
	private List<ProjectDetails> readProjectData()
	{
		
		List<ProjectDetails> refDataList = new ArrayList<ProjectDetails>();
		DBCollection collection = MongoDBHelper
				.getCollection(PROJECT_COLLECTION);
		DBCursor cursor = collection.find();
		if (cursor != null) {
			while (cursor.hasNext()) {
				DBObject result = cursor.next();
				refDataList.add(ProjectDetails.parseToObject(result));
			}
			cursor.close();
		}
		
		return refDataList;
	}
	private List<ProjAssignmentDetails> readProjectAssignmentData()
	{
		
		List<ProjAssignmentDetails> refDataList = new ArrayList<ProjAssignmentDetails>();
		DBCollection collection = MongoDBHelper
				.getCollection(PROJECTASSINMENT_COLLECTION);
		DBCursor cursor = collection.find();
		if (cursor != null) {
			while (cursor.hasNext()) {
				DBObject result = cursor.next();
				refDataList.add(ProjAssignmentDetails.parseToObject(result));
			}
			cursor.close();
		}
		
		return refDataList;
	}
	
	private List<Map<String,Object>> readSequencer()
	{
		List<Map<String,Object>> refDataList = new ArrayList<Map<String,Object>>();
		DBCollection collection = MongoDBHelper
				.getCollection(JSONDB_SEQUENCES_COLLECTION);
		DBCursor cursor = collection.find();
		if (cursor != null) {
			while (cursor.hasNext()) {
				DBObject result = cursor.next();
				Map<String,Object> map = result.toMap();
				refDataList.add(map);
			}
			cursor.close();
		}
		
		return refDataList;
	}
}
