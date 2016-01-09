package com.ibm.tools.portfoliodb.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ibm.app.web.frmwk.WebActionHandler;
import com.ibm.app.web.frmwk.annotations.RequestMapping;
import com.ibm.app.web.frmwk.bean.ModelAndView;
import com.ibm.app.web.frmwk.bean.ViewType;

public class HomeAction implements WebActionHandler {

	@RequestMapping("home.wss")
	public ModelAndView loadHome(HttpServletRequest request,
			HttpServletResponse response) {
		ModelAndView mvObject = new ModelAndView(ViewType.JSP_VIEW);
		mvObject.setView("app/index.jsp");
		return mvObject;
	}
	@RequestMapping("logout.wss")
	public ModelAndView logout(HttpServletRequest request,
			HttpServletResponse response) {
		request.getSession().invalidate();
		ModelAndView mvObject = new ModelAndView(ViewType.JSP_VIEW);
		mvObject.setView("login.jsp");
		return mvObject;
	}
}
