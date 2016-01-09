package com.ibm.tools.portfoliodb.action;

import java.util.ArrayList;
import java.util.Collections;
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
import com.ibm.tools.portfoliodb.data.ManagerDetails;
import com.ibm.tools.portfoliodb.data.ProjAssignmentDetails;
import com.ibm.tools.portfoliodb.data.ProjectDetails;
import com.ibm.tools.portfoliodb.reports.ReportHelper;
import com.ibm.tools.portfoliodb.reports.ReportOptions;
import com.ibm.tools.utils.DAOFactory;

@SuppressWarnings("unchecked")
public class EmployeeDataAction implements WebActionHandler {

	@RequestMapping("loadManagerView.wss")
	public ModelAndView loadViewMgrData(HttpServletRequest request,
			HttpServletResponse response) {
		ModelAndView mvObject = new ModelAndView(ViewType.JSP_VIEW);
		mvObject.setView("app/mgrDataView.jsp");
		return mvObject;
	}

	@RequestMapping("loadAddNewManager.wss")
	public ModelAndView loadAddNewManager(HttpServletRequest request,
			HttpServletResponse response) {
		ModelAndView mvObject = new ModelAndView(ViewType.JSP_VIEW);
		mvObject.setView("app/addNewManager.jsp");
		return mvObject;
	}

	@RequestMapping("addNewManager.wss")
	public ModelAndView addNewManager(HttpServletRequest request,
			HttpServletResponse response) {
		ModelAndView mvObject = new ModelAndView(ViewType.AJAX_VIEW);

		ManagerDetails mgrDetails = new ManagerDetails();
		mgrDetails.setMgrType(getSafeString(request.getParameter("mgrType")));
		mgrDetails.setEmpId(getSafeString(request.getParameter("empId")));
		mgrDetails.setEmpName(getSafeString(request.getParameter("empName")));
		mgrDetails.setNotesId(getSafeString(request.getParameter("notesId")));
		mgrDetails.setEmailId(getSafeString(request.getParameter("emailId")));
		if (validateMgrForSave(mgrDetails)) {
			DAOResponse daoResponse = DAOFactory.getEmployeeDataDAO()
					.saveManagerData(mgrDetails);
			mvObject.setView(DAOFactory.toJSONResponse(daoResponse));
			DataCache.reloadMgrData();
		} else {
			mvObject.setView(DAOFactory.toJSONResponse(-4,
					"Please enter all mandatory fields"));

		}
		return mvObject;
	}

	private boolean validateMgrForSave(ManagerDetails mgrDetails) {
		return getSafeString(mgrDetails.getMgrType()).length() > 0
				&& getSafeString(mgrDetails.getEmailId()).length() > 0
				&& getSafeString(mgrDetails.getEmpId()).length() > 0
				&& getSafeString(mgrDetails.getEmpName()).length() > 0
				&& getSafeString(mgrDetails.getNotesId()).length() > 0;

	}

	@RequestMapping("loadEmployeeView.wss")
	public ModelAndView loadEmployeeViewData(HttpServletRequest request,
			HttpServletResponse response) {
		ModelAndView mvObject = new ModelAndView(ViewType.JSP_VIEW);
		mvObject.addModel("mgrDataList", DataCache.getAllMgrList());
		mvObject.addModel("bandList", DataCache.getBandList());
		mvObject.addModel("subBandList", DataCache.getSubBandList());
		mvObject.addModel("locationList", DataCache.getLocationList());
		mvObject.addModel("empTypeList", DataCache.getEmployeeType());
		mvObject.addModel("skillList", DataCache.getSkillList());
		mvObject.addModel("prjList",getAllPrjectList());
		mvObject.setView("app/empDataView.jsp");
		return mvObject;
	}

	@RequestMapping("loadEmployeeList.wss")
	public ModelAndView loadEmployeeList(HttpServletRequest request,
			HttpServletResponse response) {
		ModelAndView mvObject = new ModelAndView(ViewType.AJAX_VIEW);
		String projectId = getSafeString(request.getParameter("projectId"));
		
		DAOResponse daoResponse = DAOFactory.getEmployeeDataDAO()
				.getEmployeeList(null);
		if (daoResponse != null
				&& daoResponse.getStatus() == DAOResponse.STATUS_DAO_SUCCESS) {
			// We are expecting a list of reference data
			List<EmployeeDetails> refDataList =filterListofEmployee( (List<EmployeeDetails>) daoResponse
					.getResponse(),projectId);
			Gson gson = new GsonBuilder().serializeNulls().create();
			String jsonOutput = "{ \"data\": " + gson.toJson(refDataList) + "}";
			mvObject.setView(jsonOutput);
		} else {
			String jsonOutput = "{ \"data\": []}";
			mvObject.setView(jsonOutput);
		}
		return mvObject;
	}

	@RequestMapping("loadManagerList.wss")
	public ModelAndView loadManagerList(HttpServletRequest request,
			HttpServletResponse response) {
		ModelAndView mvObject = new ModelAndView(ViewType.AJAX_VIEW);
		String mgrType = request.getParameter("mgrType");
		DAOResponse daoResponse = DAOFactory.getEmployeeDataDAO()
				.getManagerList(mgrType);
		if (daoResponse != null
				&& daoResponse.getStatus() == DAOResponse.STATUS_DAO_SUCCESS) {
			// We are expecting a list of reference data
			List<ManagerDetails> refDataList = (List<ManagerDetails>) daoResponse
					.getResponse();
			Gson gson = new Gson();
			String jsonOutput = "{ \"data\": " + gson.toJson(refDataList) + "}";
			mvObject.setView(jsonOutput);
		} else {
			String jsonOutput = "{ \"data\": []}";
			mvObject.setView(jsonOutput);
		}
		return mvObject;
	}

	@RequestMapping("loadUpdateEmpData.wss")
	public ModelAndView loadUpdateEmpData(HttpServletRequest request,
			HttpServletResponse response) {
		ModelAndView mvObject = new ModelAndView(ViewType.JSP_VIEW);
		mvObject.setView("app/updateEmpData.jsp");
		// Load the employee details
		String empId = request.getParameter("empId");
		EmployeeDetails inputEmpDetails = new EmployeeDetails();
		inputEmpDetails.setEmpId(empId);
		DAOResponse daoResponse = DAOFactory.getEmployeeDataDAO()
				.getEmployeeDetails(inputEmpDetails);
		if (daoResponse != null
				&& daoResponse.getStatus() == DAOResponse.STATUS_DAO_SUCCESS) {
			EmployeeDetails existingEmpDetails = (EmployeeDetails) daoResponse
					.getResponse();
			mvObject.addModel("empDetails", existingEmpDetails);
			mvObject.addModel("empObjId", existingEmpDetails.getId()
					.toHexString());
			// Loading the reference data
			mvObject.addModel("mgrList", DataCache.getAllMgrList());
			mvObject.addModel("bandList", DataCache.getBandList());
			mvObject.addModel("subBandList", DataCache.getSubBandList());
			mvObject.addModel("locationList", DataCache.getLocationList());
			mvObject.addModel("buildingList", DataCache.getLocationBldgList());
			mvObject.addModel("empTypeList", DataCache.getEmployeeType());
			mvObject.addModel("skillList", DataCache.getSkillList());
		}
		return mvObject;
	}

	@RequestMapping("loadAddNewEmpData.wss")
	public ModelAndView loadAddNewEmpData(HttpServletRequest request,
			HttpServletResponse response) {
		ModelAndView mvObject = new ModelAndView(ViewType.JSP_VIEW);
		mvObject.setView("app/addEmpData.jsp");
		// Loading the reference data
		mvObject.addModel("mgrList", DataCache.getAllMgrList());
		mvObject.addModel("bandList", DataCache.getBandList());
		mvObject.addModel("subBandList", DataCache.getSubBandList());
		mvObject.addModel("locationList", DataCache.getLocationList());
		mvObject.addModel("buildingList", DataCache.getLocationBldgList());
		mvObject.addModel("empTypeList", DataCache.getEmployeeType());
		mvObject.addModel("skillList", DataCache.getSkillList());
		return mvObject;
	}

	@RequestMapping("updateEmployeeDetails.wss")
	public ModelAndView updateEmployeeDetails(HttpServletRequest request,
			HttpServletResponse response) {
		ModelAndView mvObject = new ModelAndView(ViewType.AJAX_VIEW);

		EmployeeDetails updatedDetails = buildEmployeeDetails(request);
		if (updatedDetails != null) {
			DAOResponse daoResponse = DAOFactory.getEmployeeDataDAO()
					.updateEmployeeData(updatedDetails);
			mvObject.setView(DAOFactory.toJSONResponse(daoResponse));
		} else {
			mvObject.setView(DAOFactory.toJSONResponse(null));
		}

		return mvObject;
	}

	@RequestMapping("addNewEmployeeDetails.wss")
	public ModelAndView addNewEmployeeDetails(HttpServletRequest request,
			HttpServletResponse response) {
		ModelAndView mvObject = new ModelAndView(ViewType.AJAX_VIEW);

		EmployeeDetails empDetails = buildEmployeeDetailsForSave(request);
		if (isValidEntryForSave(empDetails) ) {
			DAOResponse daoResponse = DAOFactory.getEmployeeDataDAO()
					.saveEmployeeData(empDetails);
			mvObject.setView(DAOFactory.toJSONResponse(daoResponse));
		} else {
			mvObject.setView(DAOFactory.toJSONResponse(-4,"All fields are manadatory"));
		}

		return mvObject;
	}

	private List<EmployeeDetails> filterListofEmployee(List<EmployeeDetails> fullList,String projectId)
	{
		if(projectId!=null && projectId.length()>0)
		{
			//Get the assignments of a project 
			Map<String,String> options = new HashMap<String, String>();
			options.put(ReportOptions.FILTER_PROJECT,projectId);
			List<ProjAssignmentDetails> assignments = ReportHelper.retriveAssignmentData(options, null);
			if(assignments!=null && assignments.size()>0)
			{
				Map<String,String> assignedEmpMap = new HashMap<String,String>();
				for(ProjAssignmentDetails assignment: assignments)
				{
					assignedEmpMap.put(assignment.getEmpid(), "Y");
				}
				List<EmployeeDetails> filteredList = new ArrayList<EmployeeDetails>();
				for(EmployeeDetails empDetails: fullList)
				{
					if(assignedEmpMap.containsKey(empDetails.getEmpId()))
					{
						filteredList.add(empDetails);
					}
				}
				return filteredList;
			}
		}
		return fullList;
	}
	private boolean isValidEntryForSave(EmployeeDetails empDetails) {
		if (getSafeString(empDetails.getEmpId()).length() > 0
				&& getSafeString(empDetails.getName()).length() > 0
				&& getSafeString(empDetails.getIntranetId()).length() > 0
				&& getSafeString(empDetails.getEmpType()).length() > 0
				&& getSafeString(empDetails.getNotesId()).length() > 0
				&& getSafeString(empDetails.getEltpFg()).length() > 0
				&& getSafeString(empDetails.getGender()).length() > 0
				&& getSafeString(empDetails.getPrimarySkill()).length() > 0
				&& getSafeString(empDetails.getLocation()).length() > 0
				&& getSafeString(empDetails.getBuilding()).length() > 0
				&& getSafeString(empDetails.getPem()).length() > 0
				&& getSafeString(empDetails.getBand()).length() > 0
				&& getSafeString(empDetails.getSubBand()).length() > 0)
			return true;
		return false;
	}

	private EmployeeDetails buildEmployeeDetailsForSave(
			HttpServletRequest request) {
		EmployeeDetails empDetails = null;
		empDetails = new EmployeeDetails();
		empDetails.setEmpId(getSafeString(request.getParameter("empId")));
		empDetails.setName(getSafeString(request.getParameter("name")));
		empDetails.setIntranetId(getSafeString(request
				.getParameter("intranetId")));
		empDetails.setEmpType(getSafeString(request.getParameter("empType")));
		empDetails.setNotesId(getSafeString(request.getParameter("notesId")));
		empDetails.setEltpFg(getSafeString(request.getParameter("eltpFg")));
		empDetails.setGender(getSafeString(request.getParameter("gender")));
		empDetails.setPrimarySkill(getSafeString(request
				.getParameter("primarySkill")));
		empDetails.setLocation(getSafeString(request.getParameter("location")));
		empDetails.setBuilding(getSafeString(request.getParameter("building")));
		empDetails.setPem(getSafeString(request.getParameter("pem")));
		empDetails.setBand(getSafeString(request.getParameter("band")));
		empDetails.setSubBand(getSafeString(request.getParameter("subBand")));
		empDetails.setAttrType("");
		return empDetails;
	}

	private EmployeeDetails buildEmployeeDetails(HttpServletRequest request) {
		EmployeeDetails empDetails = null;
		String objIdString = getSafeString(request.getParameter("empObjId"));
		if (ObjectId.isValid(objIdString)) {
			empDetails = new EmployeeDetails();
			empDetails.setId(new ObjectId(objIdString));
			empDetails.setEmpId(getSafeString(request.getParameter("empId")));
			empDetails.setName(getSafeString(request.getParameter("name")));
			empDetails.setIntranetId(getSafeString(request
					.getParameter("intranetId")));
			empDetails
					.setEmpType(getSafeString(request.getParameter("empType")));
			empDetails
					.setNotesId(getSafeString(request.getParameter("notesId")));
			empDetails.setEltpFg(getSafeString(request.getParameter("eltpFg")));
			empDetails.setGender(getSafeString(request.getParameter("gender")));
			empDetails.setPrimarySkill(getSafeString(request
					.getParameter("primarySkill")));
			empDetails.setLocation(getSafeString(request
					.getParameter("location")));
			empDetails.setBuilding(getSafeString(request
					.getParameter("building")));
			empDetails.setPem(getSafeString(request.getParameter("pem")));
			empDetails.setBand(getSafeString(request.getParameter("band")));
			empDetails
					.setSubBand(getSafeString(request.getParameter("subBand")));
			empDetails.setAttrType(getSafeString(request
					.getParameter("attrType")));
		}
		return empDetails;
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
	
	private String getSafeString(String input) {
		return (input != null ? input.trim() : "");
	}
}
