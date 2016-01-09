package com.ibm.tools.portfoliodb.dataloader;

import java.io.BufferedReader;
import java.io.FileReader;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.ibm.tools.dbacess.DAOResponse;
import com.ibm.tools.dbacess.EmployeeDataDAO;
import com.ibm.tools.dbacess.ProjectDataDAO;
import com.ibm.tools.dbacess.UOMDataDAO;
import com.ibm.tools.portfoliodb.data.EmployeeDetails;
import com.ibm.tools.portfoliodb.data.ProjAssignmentDetails;
import com.ibm.tools.portfoliodb.data.ProjectDetails;
import com.ibm.tools.portfoliodb.data.UOMDetails;
import com.ibm.tools.utils.DAOFactory;
import com.ibm.tools.utils.MongoDBHelper;
import com.mongodb.DBCollection;

public class ProjectDataLoader {

	private static final SimpleDateFormat _DTMORMAT = new SimpleDateFormat("MM/dd/yyyy");
	
	private static final String UOM_DETAILS_COLLECTION = "UOMDetailsCollection";
	private static final String PROJECT_COLLECTION = "ProjectCollection";
	//private static final String PROJECTASSINMENT_COLLECTION = "ProjAssignmentCollection";
	//private static final String JSONDB_SEQUENCES_COLLECTION = "pfdata_seq";
	

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testImortFromJSON() throws Exception{
	
		importFromFileUOMDetails("C:\\Users\\SUDDUTT1\\SD_DATA\\NONPROJ\\Data\\UOM_DATA_V1.json");
		importFromFileProjetDetails("C:\\Users\\SUDDUTT1\\SD_DATA\\NONPROJ\\Data\\PROJECT_DATA_V1.json");
		importFromFileProjetAssignment("C:\\Users\\SUDDUTT1\\SD_DATA\\NONPROJ\\Data\\PROJECT_ASSIGN_DATA_V1.json");
		//importFromFile("C:\\Users\\SUDDUTT1\\SD_DATA\\NONPROJ\\Data\\EMP_DATA_V1.json");
	}

	@Test
	public void testImortFromTABDEL() throws Exception{
	
		//importFromTABDelFile("C:\\Users\\SUDDUTT1\\SD_DATA\\NONPROJ\\PortfolioDBDocs\\PORTFOLIODATA_UPLAOD_V2.txt");
	}
	private void importFromFileUOMDetails(String importPath) throws Exception
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
		UOMDetails[] dataList = gson.fromJson(jsonString, UOMDetails[].class);
		//UOMDataDAO daoInstance = DAOFactory.getUOMDataDAO();
		DBCollection collection = MongoDBHelper.getCollection(UOM_DETAILS_COLLECTION);
		int count = 0;
		for(UOMDetails dataRow: dataList)
		{
			//DAOResponse resp = daoInstance.addNewUOM(dataRow);
			collection.save(dataRow.toDBObject());
			count++;
			
		}
		System.out.println("UOM details loaded "+ count+" entries out of "+ dataList.length+" entries");
	}
	private void importFromFileProjetDetails(String importPath) throws Exception
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
		ProjectDetails[] dataList = gson.fromJson(jsonString, ProjectDetails[].class);
		//ProjectDataDAO daoInstance = DAOFactory.getProjetDataDAO();
		DBCollection collection = MongoDBHelper.getCollection(PROJECT_COLLECTION);
		int count = 0;
		for(ProjectDetails dataRow: dataList)
		{
			collection.save(dataRow.toDBObject());
			//DAOResponse resp = daoInstance.addNewProject(dataRow);
			//if(resp!=null && resp.getStatus() == DAOResponse.STATUS_DAO_SUCCESS)
			//{
				count++;
			//}
		}
		System.out.println("Project details loaded "+ count+" entries out of "+ dataList.length+" entries");
	}
	private void importFromFileProjetAssignment(String importPath) throws Exception
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
		ProjAssignmentDetails[] dataList = gson.fromJson(jsonString, ProjAssignmentDetails[].class);
		ProjectDataDAO daoInstance = DAOFactory.getProjetDataDAO();
		int count = 0;
		for(ProjAssignmentDetails dataRow: dataList)
		{
			DAOResponse resp = daoInstance.addNewAssignment(dataRow);
			if(resp!=null && resp.getStatus() == DAOResponse.STATUS_DAO_SUCCESS)
			{
				count++;
			}
		}
		System.out.println("Project assignment details loaded "+ count+" entries out of "+ dataList.length+" entries");
	}
	public void importFromTABDelFile(String csvFileName) throws Exception
	{
		//PORTFOLIODATA_UPLAOD_V2.txt
		int empDataCount = 0;
		int projAssignmentDataCount = 0;
		DAOResponse empDAOResponse = null;
		DAOResponse projDAOResponse = null;
		EmployeeDataDAO empDAO = DAOFactory.getEmployeeDataDAO();
		ProjectDataDAO projDataDAO  = DAOFactory.getProjetDataDAO();
		List<String> lines = CSVFileReader.readTABDeliminatedFile(csvFileName);
		List<Map<String,String>> csvData = CSVFileReader.readTABDiliitedLine(lines);
		if(csvData!=null)
		{
			for(Map<String,String> csvRow: csvData)
			{
				EmployeeDetails empDetails = new EmployeeDetails();
				empDetails.setEmpId(csvRow.get("Employee ID").trim());
				empDetails.setName(csvRow.get("EMP NAME").trim());
				empDetails.setNotesId(csvRow.get("Employee Notes Id").trim());
				empDetails.setIntranetId(csvRow.get("Employee Internet Id").trim());
				empDetails.setEmpType(csvRow.get("Employee Type").trim());
				empDetails.setEltpFg(csvRow.get("ELTP Flag").trim());
				empDetails.setGender(csvRow.get("Gender").trim());
				empDetails.setPrimarySkill(csvRow.get("Primary Skill Code").trim());
				empDetails.setLocation(csvRow.get("Location").trim());
				empDetails.setBuilding(csvRow.get("Location - Building").trim());
				empDetails.setPem(csvRow.get("People manager employee id").trim());
				empDetails.setBand(csvRow.get("Band").trim());
				empDetails.setSubBand(csvRow.get("Sub Band").trim());
				empDetails.setAttrType("");
				//System.out.println(gson.toJson(empDetails));
				ProjAssignmentDetails objInstance= new ProjAssignmentDetails();
				objInstance.setEmpid(csvRow.get("Employee ID").trim());
				long projId = Long.parseLong(csvRow.get("PROJECT_ID").trim());
				objInstance.setProjectId(projId);
				objInstance.setStartDate(getDate(csvRow.get("Project Start Date for Individual(In MM/DD/YY)").trim()));
				objInstance.setEndDate(getDate(csvRow.get("Project End Date (Actual / Projected) for Individual(In MM/DD/YY)").trim()));
				objInstance.setRole(csvRow.get("Role Code"));
				objInstance.setIsCore("N");
				objInstance.setPmpId(csvRow.get("PMP ID"));
				objInstance.setRollOffType("");
				objInstance.setReleasedFg(csvRow.get("Release Flag").trim());
				empDAOResponse = empDAO.saveEmployeeData(empDetails);
				projDAOResponse = projDataDAO.addNewAssignment(objInstance);
				if(empDAOResponse.getStatus()== DAOResponse.STATUS_DAO_SUCCESS)
				{
					empDataCount++;
				}
				if(projDAOResponse.getStatus()==DAOResponse.STATUS_DAO_SUCCESS)
				{
					projAssignmentDataCount++;
				}

			}
			System.out.println("Employee data count "+ empDataCount);
			System.out.println("Project assignment data count "+ projAssignmentDataCount);
		}
	}
	private Date getDate(String dtString) throws Exception 
	{
		return _DTMORMAT.parse(dtString);
	}
}
