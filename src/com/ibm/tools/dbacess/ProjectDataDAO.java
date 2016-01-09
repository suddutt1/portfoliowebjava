package com.ibm.tools.dbacess;

import com.ibm.tools.portfoliodb.data.ProjAssignmentDetails;
import com.ibm.tools.portfoliodb.data.ProjectDetails;

public interface ProjectDataDAO {

	DAOResponse addNewProject(ProjectDetails projDetails);
	DAOResponse updateProject(ProjectDetails projDetails);
	DAOResponse getProjectDetails(ProjectDetails projDetails);
	DAOResponse getProjectList(ProjectDetails projDetails);
	DAOResponse deleteProject(ProjectDetails projDetails);
	DAOResponse addNewAssignment(ProjAssignmentDetails projAssignment);
	DAOResponse updateAssignment(ProjAssignmentDetails projAssignment);
	DAOResponse getAssignmentDetails(ProjAssignmentDetails projAssignment);
	DAOResponse getAssignmentList(ProjAssignmentDetails projAssignment);
	
}
