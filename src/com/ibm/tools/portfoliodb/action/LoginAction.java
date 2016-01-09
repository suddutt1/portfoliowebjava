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

public class LoginAction implements WebActionHandler {

	@RequestMapping("login.wss")
	public ModelAndView login(HttpServletRequest request,HttpServletResponse response)
	{
		ModelAndView mvObject = null;
		boolean isValid = false;
		String user = request.getParameter("userid");
		String password = request.getParameter("pwd");
		DAOResponse daoResponse = DAOFactory.getUserDetailsDAO().getUser(user);
		if(daoResponse!=null && daoResponse.getStatus()== DAOResponse.STATUS_DAO_SUCCESS)
		{
			UserDetails userDetails = (UserDetails)daoResponse.getResponse();
			if(userDetails!=null && userDetails.getPassword().equals(password))
			{
				request.getSession().setAttribute("user_role", userDetails.getRole());
				request.getSession().setAttribute("user_id", userDetails.getUid());
				isValid = true;
			}
		}
		if(isValid)
		{
			mvObject = new ModelAndView(ViewType.FORWARD_ACTION_VIEW);
			mvObject.setView("home.wss");
		}
		else
		{	
			mvObject = new ModelAndView(ViewType.JSP_VIEW);
			mvObject.setView("login.jsp");
			mvObject.addModel("login_error", "Invalid credentials");
		}
		return mvObject;
	}
	
	
}
