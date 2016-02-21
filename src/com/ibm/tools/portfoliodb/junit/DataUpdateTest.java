package com.ibm.tools.portfoliodb.junit;

import java.util.List;

import org.junit.Test;

import com.ibm.tools.dbacess.DAOResponse;
import com.ibm.tools.dbacess.ProjectDataDAO;
import com.ibm.tools.portfoliodb.data.ProjectDetails;
import com.ibm.tools.portfoliodb.dataexport.ProjectDataExporter;
import com.ibm.tools.utils.DAOFactory;

public class DataUpdateTest {

	
	@Test
	public void updateFLMSLMData()
	{
		ProjectDataDAO daoInstance = DAOFactory.getProjetDataDAO();
		DAOResponse  resp = daoInstance.getProjectList(null);
		@SuppressWarnings("unchecked")
		List<ProjectDetails> projList = (List<ProjectDetails>)resp.getResponse();
		
		for(ProjectDetails projDetails : projList)
		{
			projDetails.setL1ex("0000000");
			projDetails.setL2ex("0000000");
			projDetails.setFlm("0000000");
			projDetails.setSlm("0000000");
			DAOResponse updResp = daoInstance.updateProject(projDetails);
			if(updResp.getStatus()==DAOResponse.STATUS_DAO_SUCCESS)
			{
				System.out.println("Project "+ projDetails.getProjectName() + " updated successfully ");
			}
		}
	}
	@Test
	public void deleteProject()
	{
		/*ProjectDataDAO daoInstance = DAOFactory.getProjetDataDAO();
		long[] projIdList = new long[]{2,15};
		for(long projId: projIdList)
		{
			ProjectDetails details= new ProjectDetails();
			details.setProjectId(projId);
			details.setDeleteFg("Y");
			DAOResponse  resp = daoInstance.updateProject(details);
		}*/
	}
}
