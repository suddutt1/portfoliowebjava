package com.ibm.tools.portfoliodb.reports;

import java.util.List;
import java.util.Map;

import com.ibm.tools.portfoliodb.data.ProjAssignmentDetails;

public interface ReportDAO {

	List<ProjAssignmentDetails> getAllAssignments(Map<String,String> filter);
	
}
