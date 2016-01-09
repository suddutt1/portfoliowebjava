package com.ibm.tools.portfoliodb.dataloader;

import java.io.BufferedReader;
import java.io.FileReader;

import org.junit.Before;
import org.junit.Test;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.ibm.tools.dbacess.DAOResponse;
import com.ibm.tools.dbacess.ReferenceDataDAO;
import com.ibm.tools.portfoliodb.data.ReferenceData;
import com.ibm.tools.utils.DAOFactory;

public class ReferenceDataLoader {

	
	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testImortFromJSON() throws Exception{
	
		importFromFile("C:\\Users\\SUDDUTT1\\SD_DATA\\NONPROJ\\Data\\REF_DATA_V1.json");
	}

	private void importFromFile(String importPath) throws Exception
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
		ReferenceData[] refDataList = gson.fromJson(jsonString, ReferenceData[].class);
		//System.out.println(refDataList);
		ReferenceDataDAO daoInstance = DAOFactory.getReferenceDataDAO();
		int count = 0;
		for(ReferenceData refData: refDataList)
		{
			DAOResponse resp = daoInstance.addReferenceData(refData.getType(), refData.getCode(), refData.getDisplayValue());
			if(resp!=null && resp.getStatus() == DAOResponse.STATUS_DAO_SUCCESS)
			{
				count++;
			}
		}
		System.out.println("Loaded "+ count+" entries out of "+ refDataList.length+" entries");

			
	}
}
