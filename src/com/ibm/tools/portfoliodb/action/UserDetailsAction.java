package com.ibm.tools.portfoliodb.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ibm.app.web.frmwk.WebActionHandler;
import com.ibm.app.web.frmwk.annotations.RequestMapping;
import com.ibm.app.web.frmwk.bean.ModelAndView;
import com.ibm.app.web.frmwk.bean.ViewType;
import com.ibm.tools.dbacess.DAOResponse;
import com.ibm.tools.portfoliodb.data.UserDetails;
import com.ibm.tools.utils.DAOFactory;

public class UserDetailsAction implements WebActionHandler {

	@RequestMapping("loadUserView.wss")
	public ModelAndView loadUserPage(HttpServletRequest request,
			HttpServletResponse response) {
		ModelAndView mvObject = new ModelAndView(ViewType.JSP_VIEW);
		String requestJsp = request.getParameter("requestJsp");
		mvObject.setView("admin/" + requestJsp);
		return mvObject;
	}

	@RequestMapping("addNewUser.wss")
	public ModelAndView addNewUser(HttpServletRequest request,
			HttpServletResponse response) {
		ModelAndView mvObject = new ModelAndView(ViewType.AJAX_VIEW);
		UserDetails userDetails = buildUserDetails(request);
		if(validateForSave(userDetails))
		{
			DAOResponse daoResponse = DAOFactory.getUserDetailsDAO().saveUser(userDetails);
			mvObject.setView(DAOFactory.toJSONResponse(daoResponse));
		}
		else
		{
			mvObject.setView(DAOFactory.toJSONResponse(-4,"All fields are manadatory"));
		}
		return mvObject;
	}

	private boolean validateForSave(UserDetails empDetails) {
		if (getSafeString(empDetails.getUid()).length() > 0
				&& getSafeString(empDetails.getPassword()).length() > 0
				&& getSafeString(empDetails.getEmailId()).length() > 0
				&& getSafeString(empDetails.getRole()).length() > 0
				&& getSafeString(empDetails.getFirstName()).length() > 0
				&& getSafeString(empDetails.getLastName()).length() > 0)
			return true;
		else
			return false;
	}

	private UserDetails buildUserDetails(HttpServletRequest request) {
		UserDetails builtObject = new UserDetails();
		builtObject.setUid(getSafeString(request.getParameter("uid")));
		builtObject
				.setPassword(getSafeString(request.getParameter("password")));
		builtObject.setEmailId(getSafeString(request.getParameter("emailId")));
		builtObject.setRole(getSafeString(request.getParameter("role")));
		builtObject.setFirstName(getSafeString(request
				.getParameter("firstName")));
		builtObject
				.setLastName(getSafeString(request.getParameter("lastName")));
		return builtObject;
	}

	private String getSafeString(String input) {
		return (input != null ? input.trim() : "");
	}

}
