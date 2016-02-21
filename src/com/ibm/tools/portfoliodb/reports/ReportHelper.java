package com.ibm.tools.portfoliodb.reports;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ibm.tools.dbacess.DAOResponse;
import com.ibm.tools.portfoliodb.data.EmployeeDetails;
import com.ibm.tools.portfoliodb.data.ProjAssignmentDetails;
import com.ibm.tools.portfoliodb.data.ProjectDetails;
import com.ibm.tools.portfoliodb.data.UOMDetails;
import com.ibm.tools.utils.DAOFactory;

public class ReportHelper implements ReportOptions{

	private static final ReportDAO _rptDAO = new ReportDAOJsonImpl();
	
	public static List<ProjAssignmentDetails> retriveAssignmentData(Map<String,String> inputOpts,ReportEntryAccumulator accumlator)
	{
		List<ProjAssignmentDetails> baseList = null;
		if(inputOpts !=null)
		{
			//
			if(inputOpts.containsKey(EXCLUDE_RELEASED_RESOURCES))
			{
				baseList = retrieveAssignments(inputOpts);
			}
			else
			{
				baseList = retrieveAssignments(inputOpts);
			}
		}
		else
		{
			//TODO: Yet to decide what I would be doing here
		}
		if(baseList!=null && baseList.size()>0)
		{
			List<ProjAssignmentDetails> outputList = new ArrayList<>(baseList.size());
			//Process the entries 
			Map<String, EmployeeDetails> empMap  = getEmployeeDetails();
			Map<Long, ProjectDetails> projMap = getProjectDetails();
			Map<Long,UOMDetails> uomMap = getUOMDetails();
			for(ProjAssignmentDetails rptData: baseList)
			{
				//Need to fillup the project and employee data
				rptData.setEmpDetails(empMap.get(rptData.getEmpid()));
				ProjectDetails projDetails =projMap.get(rptData.getProjectId()); 
				
				if(projDetails!=null)
				{
					rptData.setProjDetails(projDetails);
					rptData.setUomDetails(uomMap.get(projDetails.getUomId()));
					rptData.setDisplayFields();
					if(accumlator!=null)
					{
						accumlator.addLineItem(rptData);
					}
					outputList.add(rptData);
				}
			}
			baseList = outputList;
		}
		return baseList;
	}
	
	private static List<ProjAssignmentDetails> retrieveAssignments(Map<String,String> filter)
	{
		return _rptDAO.getAllAssignments(filter);
	}
	
	public static Map<Long,UOMDetails> getUOMDetails()
	{
		DAOResponse daoResponse = DAOFactory.getUOMDataDAO().getUOMList(null);
		if(daoResponse!=null && daoResponse.getStatus() == DAOResponse.STATUS_DAO_SUCCESS)
		{
			List<UOMDetails> prjList = (List<UOMDetails>)daoResponse.getResponse();
			Map<Long,UOMDetails> map = new HashMap<Long, UOMDetails>();
			for(UOMDetails projDetails : prjList)
			{
				map.put(projDetails.getUomId(),projDetails);
			}
			return map;
		}
		return Collections.emptyMap();
	}
	public static  Map<String, EmployeeDetails> getEmployeeDetails()
	{
		DAOResponse daoResponse = DAOFactory.getEmployeeDataDAO().getEmployeeList(null);
		if(daoResponse!=null && daoResponse.getStatus() == DAOResponse.STATUS_DAO_SUCCESS)
		{
			List<EmployeeDetails> empList = (List<EmployeeDetails>)daoResponse.getResponse();
			Map<String,EmployeeDetails> map = new HashMap<String, EmployeeDetails>();
			for(EmployeeDetails empDetails : empList)
			{
				empDetails.setDisplayFields();
				map.put(empDetails.getEmpId(),empDetails);
			}
			return map;
		}
		return Collections.emptyMap();
	}
	public static  Map<Long, ProjectDetails> getProjectDetails()
	{
		DAOResponse daoResponse = DAOFactory.getProjetDataDAO().getProjectList(null);
		if(daoResponse!=null && daoResponse.getStatus() == DAOResponse.STATUS_DAO_SUCCESS)
		{
			List<ProjectDetails> prjList = (List<ProjectDetails>)daoResponse.getResponse();
			Map<Long,ProjectDetails> map = new HashMap<Long, ProjectDetails>();
			for(ProjectDetails projDetails : prjList)
			{
				//Execule the deleted prokect 
				if(projDetails.getDeleteFg().equalsIgnoreCase("N"))
				{
					map.put(projDetails.getProjectId(),projDetails);
				}
			}
			return map;
		}
		return Collections.emptyMap();
	}
}
