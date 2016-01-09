package com.ibm.tools.portfoliodb.action;

import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.bson.types.ObjectId;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.ibm.app.web.frmwk.WebActionHandler;
import com.ibm.app.web.frmwk.annotations.RequestMapping;
import com.ibm.app.web.frmwk.bean.ModelAndView;
import com.ibm.app.web.frmwk.bean.ViewType;
import com.ibm.tools.dbacess.DAOResponse;
import com.ibm.tools.portfoliodb.data.DataCache;
import com.ibm.tools.portfoliodb.data.EmployeeDetails;
import com.ibm.tools.portfoliodb.data.ProjAssignmentDetails;
import com.ibm.tools.portfoliodb.data.ProjectDataHelper;
import com.ibm.tools.portfoliodb.data.ProjectDetails;
import com.ibm.tools.portfoliodb.data.UOMDetails;
import com.ibm.tools.portfoliodb.reports.ReportHelper;
import com.ibm.tools.portfoliodb.reports.ReportOptions;
import com.ibm.tools.utils.DAOFactory;

public class ProjectDataAction implements WebActionHandler {

	private static final SimpleDateFormat DT_FMT = new SimpleDateFormat(
			"MM/dd/yyyy");

	@RequestMapping("loadProjectDataPage.wss")
	public ModelAndView loadProjectDataPage(HttpServletRequest request,
			HttpServletResponse response) {
		ModelAndView mvObject = new ModelAndView(ViewType.JSP_VIEW);
		String requestType = getSafeString(request.getParameter("requestType"));
		if ("add_proj".equals(requestType)) {
			mvObject.setView("app/addProjectData.jsp");
			mvObject.addModel("mgrList", DataCache.getAllMgrList());
			mvObject.addModel("domainArea", DataCache.getDomainArea());
			mvObject.addModel("projTech", DataCache.getProjectTechList());
			mvObject.addModel("projType", DataCache.getProjectType());
			mvObject.addModel("uomList", getUOMList());
		} else if ("viewall_proj".equals(requestType)) {
			mvObject.setView("app/projectDataView.jsp");
		} else if ("view_proj".equals(requestType)) {
			mvObject.addModel("mgrList", DataCache.getAllMgrList());
			mvObject.addModel("domainArea", DataCache.getDomainArea());
			mvObject.addModel("projTech", DataCache.getProjectTechList());
			mvObject.addModel("projType", DataCache.getProjectType());
			mvObject.addModel("uomList", getUOMList());
			mvObject.addModel("projDetails",
					getProjectDetails(getSafeString(request
							.getParameter("objIdHexString"))));
			mvObject.setView("app/updateProjectData.jsp");
		} else if ("viewall_assignment".equals(requestType)) {
			mvObject.addModel("prjList",getAllPrjectList());
			mvObject.setView("app/projAssignmentView.jsp");
		} else if ("view_projassign".equals(requestType)) {
			mvObject.setView("app/updateProjAssignment.jsp");
			getProjAssignment(request, mvObject);
			mvObject.addModel("roleList", DataCache.getRoleInProjectList());
			mvObject.addModel("releaseReasonList",
					DataCache.getReleaseResonList());
		} else if ("add_projassignment".equals(requestType))
		{
			mvObject.setView("app/addProjectAssignment.jsp");
			mvObject.addModel("roleList", DataCache.getRoleInProjectList());
			mvObject.addModel("empList", getAllEmployeeList());
			mvObject.addModel("projList",getAllPrjectList());
		}
		
		return mvObject;
	}

	@RequestMapping("addNewProjData.wss")
	public ModelAndView addNewProjData(HttpServletRequest request,
			HttpServletResponse response) {
		ModelAndView mvObject = new ModelAndView(ViewType.AJAX_VIEW);
		ProjectDetails projDetails = buildProjectDetails(request);
		if (validateForSave(projDetails)) {
			DAOResponse daoResponse = DAOFactory.getProjetDataDAO()
					.addNewProject(projDetails);
			mvObject.setView(DAOFactory.toJSONResponse(daoResponse));
		} else {
			mvObject.setView(DAOFactory.toJSONResponse(-4,
					"All fields are manadatory"));
		}
		return mvObject;
	}

	@RequestMapping("loadAllProjAssignments.wss")
	public ModelAndView loadAllProjAssignments(HttpServletRequest request,
			HttpServletResponse response) {
		ModelAndView mvObject = new ModelAndView(ViewType.AJAX_VIEW);
		Map<String,String> reportOptions = new HashMap<String, String>(1);
		String projetId = getSafeString(request.getParameter("projectId"));
		if(projetId.length()>0)
		{
			reportOptions.put(ReportOptions.FILTER_PROJECT,projetId);
		}
		List<ProjAssignmentDetails> list = ReportHelper.retriveAssignmentData(
				reportOptions, null);
		if (list != null && list.size() > 0) {
			Gson gson = new GsonBuilder().setDateFormat("MM/dd/yyyy")
					.serializeNulls().create();
			String jsonOutput = "{ \"data\": " + gson.toJson(list) + "}";
			mvObject.setView(jsonOutput);
		} else {
			String jsonOutput = "{ \"data\": []}";
			mvObject.setView(jsonOutput);
		}
		return mvObject;
	}

	@RequestMapping("updateProjetData.wss")
	public ModelAndView updateProjetData(HttpServletRequest request,
			HttpServletResponse response) {
		ModelAndView mvObject = new ModelAndView(ViewType.AJAX_VIEW);
		ProjectDetails projDetails = buildProjectDetails(request);
		projDetails.setObjIdHexString(getSafeString(request
				.getParameter("objIdHexString")));
		if (validateForUpdate(projDetails)) {
			DAOResponse daoResponse = DAOFactory.getProjetDataDAO()
					.updateProject(projDetails);
			mvObject.setView(DAOFactory.toJSONResponse(daoResponse));
		} else {
			mvObject.setView(DAOFactory.toJSONResponse(-4,
					"All fields are manadatory"));
		}
		return mvObject;
	}

	@RequestMapping("loadAllProjectList.wss")
	public ModelAndView loadAllProjectList(HttpServletRequest request,
			HttpServletResponse response) {
		ModelAndView mvObject = new ModelAndView(ViewType.AJAX_VIEW);
		DAOResponse daoResponse = DAOFactory.getProjetDataDAO().getProjectList(
				null);
		if (daoResponse != null
				&& daoResponse.getStatus() == DAOResponse.STATUS_DAO_SUCCESS) {
			@SuppressWarnings("unchecked")
			List<ProjectDetails> projDataList = (List<ProjectDetails>) daoResponse
					.getResponse();
			// Add the UOM details here
			Map<Long, UOMDetails> uomMap = getUOMMApMap();
			for (ProjectDetails projDetails : projDataList) {
				projDetails.setUomDetails(uomMap.get(projDetails.getUomId()));
			}
			Gson gson = new GsonBuilder().setDateFormat("MM/dd/yyyy")
					.serializeNulls().create();
			String jsonOutput = "{ \"data\": " + gson.toJson(projDataList)
					+ "}";
			mvObject.setView(jsonOutput);
		} else {
			String jsonOutput = "{ \"data\": []}";
			mvObject.setView(jsonOutput);
		}
		return mvObject;
	}

	@RequestMapping("updateProjAssignment.wss")
	public ModelAndView updateProjAssignment(HttpServletRequest request,
			HttpServletResponse response) {
		ModelAndView mvObject = new ModelAndView(ViewType.AJAX_VIEW);
		ProjAssignmentDetails projDetails = buildProjAssignmentForUpdate(request);

		if (validateForUpdate(projDetails)) {
			DAOResponse daoResponse = DAOFactory.getProjetDataDAO()
					.updateAssignment(projDetails);
			mvObject.setView(DAOFactory.toJSONResponse(daoResponse));
		} else {
			mvObject.setView(DAOFactory.toJSONResponse(-4,
					"All fields are manadatory"));
		}
		return mvObject;
	}
	
	@RequestMapping("addNewPojectAssignment.wss")
	public ModelAndView addNewPojectAssignment(HttpServletRequest request,
			HttpServletResponse response) {
		ModelAndView mvObject = new ModelAndView(ViewType.AJAX_VIEW);
		ProjAssignmentDetails projDetails = buildAssignment(request);
		if (validateForSave(projDetails)) {
			DAOResponse daoResponse = DAOFactory.getProjetDataDAO()
					.addNewAssignment(projDetails);
			mvObject.setView(DAOFactory.toJSONResponse(daoResponse));
		} else {
			mvObject.setView(DAOFactory.toJSONResponse(-4,
					"All fields are manadatory. Please fill them up."));
		}
		return mvObject;
	}
	
	private ProjAssignmentDetails buildAssignment(HttpServletRequest request)
	{
		ProjAssignmentDetails builtObject = new ProjAssignmentDetails();
		builtObject.setEmpid(getSafeString(request.getParameter("empid")));
		builtObject.setProjectId(getSafeNumeric(request
				.getParameter("projectId")));
		builtObject
				.setStartDate(getSafeDate(request.getParameter("startDate")));
		builtObject.setEndDate(getSafeDate(request.getParameter("endDate")));
		builtObject.setRole(getSafeString(request.getParameter("role")));
		builtObject.setIsCore(getSafeString(request.getParameter("isCore")));
		builtObject.setPmpId(getSafeString(request.getParameter("pmpId")));
		builtObject.setReleasedFg("N");
		return builtObject;
	}
	
	private boolean validateForSave(ProjAssignmentDetails projDetails) {
		if (getSafeString(projDetails.getEmpid()).length() > 0
				&& projDetails.getProjectId() > 0
				&& projDetails.getStartDate() != null
				&& projDetails.getEndDate() != null
				&& getSafeString(projDetails.getRole()).length() > 0
				&& getSafeString(projDetails.getIsCore()).length() > 0
				&& getSafeString(projDetails.getReleasedFg()).length() > 0) {
			return true;
		}

		return false;
	}
	
	private boolean validateForUpdate(ProjAssignmentDetails projDetails) {
		String safeObjId = getSafeString(projDetails.getObjIdHexString());
		if (getSafeString(projDetails.getEmpid()).length() > 0
				&& projDetails.getProjectId() > 0
				&& projDetails.getStartDate() != null
				&& projDetails.getEndDate() != null
				&& getSafeString(projDetails.getRole()).length() > 0
				&& getSafeString(projDetails.getIsCore()).length() > 0
				&& getSafeString(projDetails.getReleasedFg()).length() > 0
				&& safeObjId.length() > 0 && ObjectId.isValid(safeObjId)) {
			projDetails.setId(new ObjectId(safeObjId));
			//Further check for release reason
			return true;
		}

		return false;
	}

	private ProjAssignmentDetails buildProjAssignmentForUpdate(
			HttpServletRequest request) {
		ProjAssignmentDetails builtObject = new ProjAssignmentDetails();
		builtObject.setEmpid(getSafeString(request.getParameter("empid")));
		builtObject.setProjectId(getSafeNumeric(request
				.getParameter("projectId")));
		builtObject
				.setStartDate(getSafeDate(request.getParameter("startDate")));
		builtObject.setEndDate(getSafeDate(request.getParameter("endDate")));
		builtObject.setRole(getSafeString(request.getParameter("role")));
		builtObject.setIsCore(getSafeString(request.getParameter("isCore")));
		builtObject.setPmpId(getSafeString(request.getParameter("pmpId")));
		builtObject.setRollOffType(getSafeString(request
				.getParameter("rollOffType")));
		builtObject.setReleaseReasonText(getSafeString(request
				.getParameter("releaseReasonText")));
		builtObject.setReleasedFg(getSafeString(request
				.getParameter("releasedFg")));
		builtObject.setObjIdHexString(getSafeString(request
				.getParameter("objIdHexString")));
		return builtObject;
	}

	private ProjAssignmentDetails getProjAssignment(HttpServletRequest request,
			ModelAndView mvObject) {
		String objId = request.getParameter("objIdHexString");
		if (objId != null && ObjectId.isValid(objId)) {
			ProjAssignmentDetails projAssignment = new ProjAssignmentDetails();
			projAssignment.setId(new ObjectId(objId));
			DAOResponse response = DAOFactory.getProjetDataDAO()
					.getAssignmentDetails(projAssignment);
			if (response != null
					&& response.getStatus() == DAOResponse.STATUS_DAO_SUCCESS) {
				ProjAssignmentDetails existingDetails = (ProjAssignmentDetails) response
						.getResponse();
				mvObject.addModel("projDetails", existingDetails);
				mvObject.addModel("projName", ProjectDataHelper
						.getSubProjectName(existingDetails.getProjectId()));
				EmployeeDetails inputEmpDetails = new EmployeeDetails();
				inputEmpDetails.setEmpId(existingDetails.getEmpid());
				DAOResponse daoResponse = DAOFactory.getEmployeeDataDAO()
						.getEmployeeDetails(inputEmpDetails);
				if (daoResponse != null
						&& daoResponse.getStatus() == DAOResponse.STATUS_DAO_SUCCESS) {
					EmployeeDetails existingEmpDetails = (EmployeeDetails) daoResponse
							.getResponse();
					mvObject.addModel("empName", existingEmpDetails.getName());
				}
			}
		}
		return null;
	}

	private ProjectDetails getProjectDetails(String objId) {
		ProjectDetails projDetails = new ProjectDetails();
		if (ObjectId.isValid(objId)) {
			projDetails.setId(new ObjectId(objId));
			DAOResponse response = DAOFactory.getProjetDataDAO()
					.getProjectDetails(projDetails);
			if (response != null
					&& response.getStatus() == DAOResponse.STATUS_DAO_SUCCESS) {
				ProjectDetails existingDetails = (ProjectDetails) response
						.getResponse();
				return existingDetails;
			}
		}
		return null;

	}

	private Map<Long, UOMDetails> getUOMMApMap() {
		Map<Long, UOMDetails> uomMap = new HashMap<Long, UOMDetails>(16);
		List<UOMDetails> uomList = getUOMList();
		for (UOMDetails uomDetails : uomList) {
			uomDetails.setDisplayFields();
			uomMap.put(uomDetails.getUomId(), uomDetails);
		}
		return uomMap;
	}

	private boolean validateForSave(ProjectDetails projDetails) {
		if (projDetails.getUomId() > 0
				&& getSafeString(projDetails.getProjectName()).length() > 0
				&& getSafeString(projDetails.getProjMgr()).length() > 0
				&& getSafeString(projDetails.getDpe()).length() > 0
				&& getSafeString(projDetails.getBlueCommunity()).length() > 0
				&& getSafeString(projDetails.getBam()).length() > 0
				&& getSafeString(projDetails.getDirector()).length() > 0
				&& getSafeString(projDetails.getProjType()).length() > 0
				&& getSafeString(projDetails.getProjPrimTech()).length() > 0
				&& getSafeString(projDetails.getDomain()).length() > 0)
			return true;
		return false;
	}

	private boolean validateForUpdate(ProjectDetails projDetails) {
		if (projDetails.getUomId() > 0
				&& getSafeString(projDetails.getProjectName()).length() > 0
				&& getSafeString(projDetails.getProjMgr()).length() > 0
				&& getSafeString(projDetails.getDpe()).length() > 0
				&& getSafeString(projDetails.getBlueCommunity()).length() > 0
				&& getSafeString(projDetails.getBam()).length() > 0
				&& getSafeString(projDetails.getDirector()).length() > 0
				&& getSafeString(projDetails.getProjType()).length() > 0
				&& getSafeString(projDetails.getProjPrimTech()).length() > 0
				&& getSafeString(projDetails.getDomain()).length() > 0
				&& getSafeString(projDetails.getObjIdHexString()).length() > 0)
			return true;
		return false;
	}

	private ProjectDetails buildProjectDetails(HttpServletRequest request) {
		ProjectDetails builtObject = new ProjectDetails();

		builtObject.setUomId(getSafeNumeric(request.getParameter("uomId")));
		builtObject.setProjectName(getSafeString(request
				.getParameter("projectName")));
		builtObject.setProjMgr(getSafeString(request.getParameter("projMgr")));
		builtObject.setDpe(getSafeString(request.getParameter("dpe")));
		builtObject.setBlueCommunity(getSafeString(request
				.getParameter("blueCommunity")));
		builtObject.setBam(getSafeString(request.getParameter("bam")));
		builtObject
				.setDirector(getSafeString(request.getParameter("director")));
		builtObject
				.setProjType(getSafeString(request.getParameter("projType")));
		builtObject.setProjPrimTech(getSafeString(request
				.getParameter("projPrimTech")));
		builtObject.setDomain(getSafeString(request.getParameter("domain")));
		builtObject.setDeleteFg("N");
		return builtObject;

	}
	
	@SuppressWarnings("unchecked")
	private List<EmployeeDetails> getAllEmployeeList() {
		DAOResponse daoResponse = DAOFactory.getEmployeeDataDAO()
				.getEmployeeList(null);
		if (daoResponse != null
				&& daoResponse.getStatus() == DAOResponse.STATUS_DAO_SUCCESS) {
			return (List<EmployeeDetails>) daoResponse.getResponse();
		} else {
			return Collections.emptyList();
		}
	}
	@SuppressWarnings("unchecked")
	private List<ProjectDetails> getAllPrjectList() {
		DAOResponse daoResponse = DAOFactory.getProjetDataDAO()
				.getProjectList(null);
		if (daoResponse != null
				&& daoResponse.getStatus() == DAOResponse.STATUS_DAO_SUCCESS) {
			return (List<ProjectDetails>) daoResponse.getResponse();
		} else {
			return Collections.emptyList();
		}
	}
	@SuppressWarnings("unchecked")
	private List<UOMDetails> getUOMList() {
		DAOResponse response = DAOFactory.getUOMDataDAO().getUOMList(null);
		if (response != null
				&& response.getStatus() == DAOResponse.STATUS_DAO_SUCCESS) {
			return (List<UOMDetails>) response.getResponse();
		}
		return Collections.emptyList();
	}

	private String getSafeString(String input) {
		return (input != null ? input.trim() : "");
	}

	private long getSafeNumeric(String input) {
		long safeLong = 0;
		try {
			safeLong = Long.parseLong(getSafeString(input));
		} catch (Exception ex) {
			safeLong = Long.MIN_VALUE;
		}
		return safeLong;
	}

	private Date getSafeDate(String input) {
		Date date = null;
		try {
			date = DT_FMT.parse(input);
		} catch (Exception ex) {
			date = null;
		}
		return date;
	}
}
