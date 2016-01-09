package com.ibm.tools.portfoliodb.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.ibm.app.web.frmwk.WebActionHandler;
import com.ibm.app.web.frmwk.annotations.RequestMapping;
import com.ibm.app.web.frmwk.bean.ModelAndView;
import com.ibm.app.web.frmwk.bean.ViewType;
import com.ibm.tools.dbacess.DAOResponse;
import com.ibm.tools.portfoliodb.data.DataCache;
import com.ibm.tools.portfoliodb.data.UOMDetails;
import com.ibm.tools.utils.DAOFactory;

public class UOMDataAction implements WebActionHandler {

	@RequestMapping("loadUOMDataPage.wss")
	public ModelAndView loadUOMDataPage(HttpServletRequest request,
			HttpServletResponse response) {
		
		ModelAndView mvObject = new ModelAndView(ViewType.JSP_VIEW);
		
		String requestType = getSafeString(request.getParameter("requestType"));
		if("add_uom".equals(requestType))
		{
			mvObject.setView("app/addUOMData.jsp");
			mvObject.addModel("mgrList", DataCache.getAllMgrList());
			mvObject.addModel("geoList", DataCache.getGeoList());
			mvObject.addModel("manageType", DataCache.getManagedType());
		}
		else if("viewall_uom".equals(requestType))
		{
			mvObject.setView("app/uomDataView.jsp");
		}
		return mvObject;
	}
	@RequestMapping("loadAllUOMList.wss")
	public ModelAndView loadAllUOMList(HttpServletRequest request,HttpServletResponse response)
	{
		ModelAndView mvObject = new ModelAndView(ViewType.AJAX_VIEW);
		DAOResponse daoResponse = DAOFactory.getUOMDataDAO().getUOMList(null);
		if(daoResponse.getStatus()==DAOResponse.STATUS_DAO_SUCCESS)
		{
			//We are expecting a list of reference data
			List<UOMDetails> refDataList = (List<UOMDetails> )daoResponse.getResponse();
			Gson gson = new GsonBuilder().setDateFormat("MM/dd/yyyy").serializeNulls().create();
			String jsonOutput = "{ \"data\": "+gson.toJson(refDataList)+"}";
			mvObject.setView(jsonOutput);
		}
		else
		{
			String jsonOutput = "{ \"data\": []}";
			mvObject.setView(jsonOutput);
		}
		return mvObject;
	}
	@RequestMapping("addNewUOMData.wss")
	public ModelAndView addNewUOMData(HttpServletRequest request,HttpServletResponse response)
	{
		ModelAndView mvObject = new ModelAndView(ViewType.AJAX_VIEW);
		UOMDetails uomDetails = buildUOMDetails(request);
		if(validateForSave(uomDetails))
		{
			DAOResponse daoResponse = DAOFactory.getUOMDataDAO().addNewUOM(uomDetails);
			mvObject.setView(DAOFactory.toJSONResponse(daoResponse));
		}
		else
		{
			mvObject.setView(DAOFactory.toJSONResponse(-4, "All fields are manadatory"));
		}
		return mvObject;
	}
	private UOMDetails buildUOMDetails(HttpServletRequest request)
	{
		UOMDetails  builtObject = new UOMDetails();
		builtObject.setPmpProjectId(getSafeString(request.getParameter("pmpProjectId")));
		builtObject.setUomName(getSafeString(request.getParameter("uomName")));
		builtObject.setGeo(getSafeString(request.getParameter("geo")));
		builtObject.setManagdType(getSafeString(request.getParameter("managdType")));
		builtObject.setUomStartDate(getSafeString(request.getParameter("uomStartDate")));
		builtObject.setUomMgr(getSafeString(request.getParameter("uomMgr")));
		builtObject.setProgMgr(getSafeString(request.getParameter("progMgr")));
		builtObject.setAccountMgr(getSafeString(request.getParameter("accountMgr")));
		return builtObject;

	}
	private boolean validateForSave(UOMDetails uomDetails)
	{
		if(	getSafeString(uomDetails.getPmpProjectId()).length()>0 &&
				getSafeString(uomDetails.getUomName()).length()>0 &&
				getSafeString(uomDetails.getGeo()).length()>0 &&
				getSafeString(uomDetails.getManagdType()).length()>0 &&
				getSafeString(uomDetails.getUomStartDate()).length()>0 &&
				getSafeString(uomDetails.getUomMgr()).length()>0 &&
				getSafeString(uomDetails.getProgMgr()).length()>0 &&
				getSafeString(uomDetails.getAccountMgr()).length()>0)
		{
			return true;
		}
		return false;
				

	}
	private String getSafeString(String input)
	{
		return (input!=null? input.trim():"");
	}
}
