package com.ibm.tools.portfoliodb.junit;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.ibm.tools.portfoliodb.data.ProjAssignmentDetails;
import com.ibm.tools.portfoliodb.reports.ReportHelper;

public class ReportHelperTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testRetriveAssignmentDataNormal() {
		
		Map<String, String> inputOpts = new HashMap<String, String>(1);
		List<ProjAssignmentDetails> projAssignmentList = ReportHelper.retriveAssignmentData(inputOpts, null);
		assertNotNull(projAssignmentList);
		assertEquals(true, (projAssignmentList.size()>0));
		Gson gson = new GsonBuilder().serializeNulls()
				.setDateFormat("MM/dd/yyyy").setPrettyPrinting().create();
		String jsonOutput = gson.toJson(projAssignmentList) ;
		System.out.println(jsonOutput);
	}

}
