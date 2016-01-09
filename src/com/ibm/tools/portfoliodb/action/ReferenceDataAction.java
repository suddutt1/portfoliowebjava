package com.ibm.tools.portfoliodb.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.ibm.app.web.frmwk.WebActionHandler;
import com.ibm.app.web.frmwk.annotations.RequestMapping;
import com.ibm.app.web.frmwk.bean.ModelAndView;
import com.ibm.app.web.frmwk.bean.ViewType;
import com.ibm.tools.dbacess.DAOResponse;
import com.ibm.tools.dbacess.ReferenceDataDAO;
import com.ibm.tools.portfoliodb.data.ReferenceData;
import com.ibm.tools.utils.DAOFactory;

public class ReferenceDataAction implements WebActionHandler{
	
	private static final ReferenceDataDAO  _referenceDAO = DAOFactory.getReferenceDataDAO();
	
	@RequestMapping("loadRefDataForView.wss")
	public ModelAndView loadViewRefData(HttpServletRequest request, HttpServletResponse response)
	{
		ModelAndView mvObject= new ModelAndView(ViewType.JSP_VIEW);
		mvObject.setView("app/referenceDataView.jsp");
		return mvObject;
	}
	@RequestMapping("loadReferenceData.wss")
	public ModelAndView loadReferenceDataForView(HttpServletRequest request, HttpServletResponse response)
	{
		ModelAndView mvObject = new ModelAndView(ViewType.AJAX_VIEW);
		String refDataType = request.getParameter("refDataType");
		DAOResponse daoResponse =_referenceDAO.getReferenceData(refDataType);
		if(daoResponse.getStatus()==DAOResponse.STATUS_DAO_SUCCESS)
		{
			//We are expecting a list of reference data
			List<ReferenceData> refDataList = (List<ReferenceData> )daoResponse.getResponse();
			Gson gson = new Gson();
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
	@RequestMapping("loadAddNewRefData.wss")
	public ModelAndView loadAddNewRefData(HttpServletRequest request, HttpServletResponse response)
	{
		
		ModelAndView mvObject= new ModelAndView(ViewType.JSP_VIEW);
		mvObject.setView("app/addNewRefData.jsp");
		return mvObject;
	}
	
	@RequestMapping("saveReferenceData.wss")
	public ModelAndView saveReferenceData(HttpServletRequest request, HttpServletResponse response)
	{
		ModelAndView mvObject = new ModelAndView(ViewType.AJAX_VIEW);
		String refDataType = request.getParameter("refDataType");
		String code = request.getParameter("code");
		String displayName = request.getParameter("displayVal");
		DAOResponse daoResponse = _referenceDAO.addReferenceData(refDataType, code, displayName);
		mvObject.setView(DAOFactory.toJSONResponse(daoResponse));
		return mvObject;
	}
	
}
