package com.ibm.tools.portfoliodb.action;


import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.ibm.app.web.frmwk.WebActionHandler;
import com.ibm.app.web.frmwk.annotations.RequestMapping;
import com.ibm.app.web.frmwk.bean.ModelAndView;
import com.ibm.app.web.frmwk.bean.ViewType;
import com.ibm.tools.dbacess.DAOResponse;
import com.ibm.tools.portfoliodb.data.ProjAssignmentDetails;
import com.ibm.tools.portfoliodb.data.UOMDetails;
import com.ibm.tools.portfoliodb.reports.BandmixAccumulator;
import com.ibm.tools.portfoliodb.reports.EmpSkillAccumulator;
import com.ibm.tools.portfoliodb.reports.EmployeeTypeAccumulator;
import com.ibm.tools.portfoliodb.reports.GenderAccumulator;
import com.ibm.tools.portfoliodb.reports.LocationAccumulator;
import com.ibm.tools.portfoliodb.reports.ReportHelper;
import com.ibm.tools.portfoliodb.reports.ReportOptions;
import com.ibm.tools.portfoliodb.reports.RoleAccumulator;
import com.ibm.tools.portfoliodb.reports.SubbandAccumulator;
import com.ibm.tools.portfoliodb.reports.TenuredReseCriticalAccumulator;
import com.ibm.tools.portfoliodb.reports.TenuredResourceAccumulator;
import com.ibm.tools.utils.DAOFactory;

public class ReportAction implements WebActionHandler {

	@RequestMapping("loadFullReportView.wss")
	public ModelAndView loadFullReportView(HttpServletRequest request,
			HttpServletResponse response) {
		ModelAndView mvObject = new ModelAndView(ViewType.JSP_VIEW);
		mvObject.addModel("uomList",getUOMList());
		mvObject.setView("reports/fullReportView.jsp");
		return mvObject;
	}

	@RequestMapping("generateFullReport.wss")
	public ModelAndView generateFullReport(HttpServletRequest request,
			HttpServletResponse response) {
		ModelAndView mvObject = new ModelAndView(ViewType.AJAX_VIEW);
		// TODO: Apply filters
		// Get all the assignments
		long uomId  = getSafeNumeric(request.getParameter("uomId"));
		Map<String, String> inputOpts = new HashMap<String, String>(1);
		
		
		List<ProjAssignmentDetails> dataList = filterWithUOM(ReportHelper
				.retriveAssignmentData(inputOpts, null),uomId);
		if (dataList != null && dataList.size() > 0) {
			Gson gson = new GsonBuilder().serializeNulls()
					.setDateFormat("MM/dd/yyyy").create();
			String jsonOutput = "{ \"data\": " + gson.toJson(dataList) + "}";
			mvObject.setView(jsonOutput);
		} else {
			String jsonOutput = "{ \"data\": []}";
			mvObject.setView(jsonOutput);
		}

		return mvObject;
	}

	@RequestMapping("loadReportView.wss")
	public ModelAndView loadReportView(HttpServletRequest request,
			HttpServletResponse response) {
		ModelAndView mvObject = new ModelAndView(ViewType.JSP_VIEW);
		String requestJsp = request.getParameter("requestJsp");
		mvObject.setView("reports/" + requestJsp);
		return mvObject;
	}

	@RequestMapping("retriveReportData.wss")
	public ModelAndView retriveReportData(HttpServletRequest request,
			HttpServletResponse response) {
		Map<String, String> reportOptions = new HashMap<String, String>();
		String outputJSON = "";
		ModelAndView mvObject = new ModelAndView(ViewType.AJAX_VIEW);
		String reportType = request.getParameter("reportType");
		if ("GENDER_REPORT".equalsIgnoreCase(reportType)) {
			reportOptions.put(ReportOptions.EXCLUDE_RELEASED_RESOURCES, "Y");
			GenderAccumulator genderAccuulator = new GenderAccumulator();
			ReportHelper.retriveAssignmentData(reportOptions, genderAccuulator);
			Gson gson = new GsonBuilder().serializeNulls().create();
			String reportOutput = gson.toJson(genderAccuulator
					.getConsolidatedLineItems());
			outputJSON = "{ \"data\": " + reportOutput + "}";
		} else if("SUBBAND_REPORT".equalsIgnoreCase(reportType)){
			reportOptions.put(ReportOptions.EXCLUDE_RELEASED_RESOURCES, "Y");
			SubbandAccumulator accumulator = new SubbandAccumulator();
			ReportHelper.retriveAssignmentData(reportOptions, accumulator);
			Gson gson = new GsonBuilder().serializeNulls().create();
			String reportOutput = gson.toJson(accumulator
					.getConsolidatedLineItems());
			outputJSON = "{ \"data\": " + reportOutput + "}";
			
		} else if ("TENUREDRSC_REPORT".equalsIgnoreCase(reportType)){
			reportOptions.put(ReportOptions.EXCLUDE_RELEASED_RESOURCES, "Y");
			TenuredResourceAccumulator accumulator = new TenuredResourceAccumulator();
			ReportHelper.retriveAssignmentData(reportOptions, accumulator);
			Gson gson = new GsonBuilder().serializeNulls().create();
			String reportOutput = gson.toJson(accumulator
					.getConsolidatedLineItems());
			outputJSON = "{ \"data\": " + reportOutput + "}";
			
		}
		else if ("LOCATION_REPORT".equalsIgnoreCase(reportType)){
			reportOptions.put(ReportOptions.EXCLUDE_RELEASED_RESOURCES, "Y");
			LocationAccumulator accumulator = new LocationAccumulator();
			ReportHelper.retriveAssignmentData(reportOptions, accumulator);
			Gson gson = new GsonBuilder().serializeNulls().create();
			String reportOutput = gson.toJson(accumulator
					.getConsolidatedLineItems());
			outputJSON = "{ \"data\": " + reportOutput + "}";
			
		}else if ("PROJROLE_REPORT".equalsIgnoreCase(reportType)){
			reportOptions.put(ReportOptions.EXCLUDE_RELEASED_RESOURCES, "Y");
			RoleAccumulator accumulator = new RoleAccumulator();
			ReportHelper.retriveAssignmentData(reportOptions, accumulator);
			Gson gson = new GsonBuilder().serializeNulls().create();
			String reportOutput = gson.toJson(accumulator
					.getConsolidatedLineItems());
			outputJSON = "{ \"data\": " + reportOutput + "}";
			
		}
		else if ("EMPSKILL_REPORT".equalsIgnoreCase(reportType)){
			reportOptions.put(ReportOptions.EXCLUDE_RELEASED_RESOURCES, "Y");
			EmpSkillAccumulator accumulator = new EmpSkillAccumulator();
			ReportHelper.retriveAssignmentData(reportOptions, accumulator);
			Gson gson = new GsonBuilder().serializeNulls().create();
			String reportOutput = gson.toJson(accumulator
					.getConsolidatedLineItems());
			outputJSON = "{ \"data\": " + reportOutput + "}";
			
		}
		else if ("EMPTYPE_REPORT".equalsIgnoreCase(reportType)){
			reportOptions.put(ReportOptions.EXCLUDE_RELEASED_RESOURCES, "Y");
			EmployeeTypeAccumulator accumulator = new EmployeeTypeAccumulator();
			ReportHelper.retriveAssignmentData(reportOptions, accumulator);
			Gson gson = new GsonBuilder().serializeNulls().create();
			String reportOutput = gson.toJson(accumulator
					.getConsolidatedLineItems());
			outputJSON = "{ \"data\": " + reportOutput + "}";
		}
		else if ("BANDMIX_REPORT".equalsIgnoreCase(reportType)){
			reportOptions.put(ReportOptions.EXCLUDE_RELEASED_RESOURCES, "Y");
			BandmixAccumulator accumulator = new BandmixAccumulator();
			ReportHelper.retriveAssignmentData(reportOptions, accumulator);
			Gson gson = new GsonBuilder().serializeNulls().create();
			String reportOutput = gson.toJson(accumulator
					.getConsolidatedLineItems());
			outputJSON = "{ \"data\": " + reportOutput + "}";
		}
		else if ("TENUREDRSC_CRIT_REPORT".equalsIgnoreCase(reportType)){
			reportOptions.put(ReportOptions.EXCLUDE_RELEASED_RESOURCES, "Y");
			TenuredReseCriticalAccumulator accumulator = new TenuredReseCriticalAccumulator();
			ReportHelper.retriveAssignmentData(reportOptions, accumulator);
			Gson gson = new GsonBuilder().serializeNulls().create();
			String reportOutput = gson.toJson(accumulator
					.getConsolidatedLineItems());
			outputJSON = "{ \"data\": " + reportOutput + "}";
		}
		else {//
			outputJSON = DAOFactory.toJSONResponse(-5, "Invalid report type");
		}

		mvObject.setView(outputJSON);
		return mvObject;
	}
	
	private List<ProjAssignmentDetails> filterWithUOM(List<ProjAssignmentDetails> fullDataList,long uomId)
	{
		if(uomId>0 && fullDataList!=null && fullDataList.size()>0)
		{
			List<ProjAssignmentDetails> filteredList = new ArrayList<>();
			for(ProjAssignmentDetails projAssignment: fullDataList)
			{
				if(projAssignment.getUomDetails()!=null && uomId==projAssignment.getUomDetails().getUomId())
				{
					filteredList.add(projAssignment);
				}
			}
			return filteredList;
		}
		return fullDataList;
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
	private long getSafeNumeric(String input) {
		long safeLong = 0;
		try {
			safeLong = Long.parseLong(getSafeString(input));
		} catch (Exception ex) {
			safeLong = Long.MIN_VALUE;
		}
		return safeLong;
	}
	private String getSafeString(String input) {
		return (input != null ? input.trim() : "");
	}
}
