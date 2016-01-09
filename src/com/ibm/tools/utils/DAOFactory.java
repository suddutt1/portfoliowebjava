package com.ibm.tools.utils;

import com.ibm.tools.dbacess.DAOResponse;
import com.ibm.tools.dbacess.EmployeeDataDAO;
import com.ibm.tools.dbacess.ProjectDataDAO;
import com.ibm.tools.dbacess.ReferenceDataDAO;
import com.ibm.tools.dbacess.UOMDataDAO;
import com.ibm.tools.dbacess.UserDetailsDAO;
import com.ibm.tools.portfoliodb.data.EmpDataDAOJSONImpl;
import com.ibm.tools.portfoliodb.data.ProjectDataDAOJSONImpl;
import com.ibm.tools.portfoliodb.data.RefDataDAOJSONImpl;
import com.ibm.tools.portfoliodb.data.UOMDataDAOJSONImpl;
import com.ibm.tools.portfoliodb.data.UOMDetails;
import com.ibm.tools.portfoliodb.data.UserDetailsDAOJsonImpl;

/**
 * Factory class to abstract/separate DAO layer from action class/Service class
 * implementation
 * 
 * @author SUDDUTT1
 *
 */
public class DAOFactory {

	private static final ReferenceDataDAO _refDataDAO = new RefDataDAOJSONImpl();
	private static final EmployeeDataDAO _empDataDAO = new EmpDataDAOJSONImpl();
	//private static final ProjectDetailsDAO _projDetailsDAO = new ProjectDetailsDAOJSONImpl();
	private static final UserDetailsDAO _userDetailsDAO = new UserDetailsDAOJsonImpl();
	private static final UOMDataDAO _uomDataDAO = new UOMDataDAOJSONImpl();
	private static final ProjectDataDAO _projDataDAO = new ProjectDataDAOJSONImpl();

	public static ReferenceDataDAO getReferenceDataDAO() {
		return _refDataDAO;
	}
	public static EmployeeDataDAO getEmployeeDataDAO(){
		return _empDataDAO;
	}
	/*public static ProjectDetailsDAO getProjectDetailsDAO()
	{
		return _projDetailsDAO;
	}*/
	public static UserDetailsDAO getUserDetailsDAO()
	{
		return _userDetailsDAO;
	}

	public static UOMDataDAO getUOMDataDAO()
	{
		return _uomDataDAO;
	}
	public static ProjectDataDAO getProjetDataDAO()
	{
		return _projDataDAO;
	}
	public static String toJSONResponse(DAOResponse response) {
		StringBuilder jsonResponse = new StringBuilder();
		if (response != null) {
			jsonResponse.append("{ \"status\":").append(response.getStatus())
					.append(",\"statusText\": \"")
					.append(response.getStatusText()).append("\" }");
		} else {
			jsonResponse
					.append("{ \"status\":-99,\"statusText\": \"Invalid or null DAO response.\" } ");
		}
		return jsonResponse.toString();
	}
	public static String toJSONResponse(int status,String statusText) {
		StringBuilder jsonResponse = new StringBuilder();
		jsonResponse.append("{ \"status\":").append(status)
		.append(",\"statusText\": \"")
		.append(statusText).append("\" }");
		return jsonResponse.toString();
	}
}
