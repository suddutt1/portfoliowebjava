package com.ibm.tools.dbacess;

import java.util.Map;

import com.ibm.tools.portfoliodb.data.EmployeeDetails;
import com.ibm.tools.portfoliodb.data.ManagerDetails;

/**
 * Employee data DAO interface. Implementation should contains employee and
 * manager details information.
 * 
 * @author SUDDUTT1
 *
 */
public interface EmployeeDataDAO {

	DAOResponse saveEmployeeData(EmployeeDetails empDetails);
	DAOResponse updateEmployeeData(EmployeeDetails empDetails);
	DAOResponse getEmployeeDetails(EmployeeDetails empDetails);
	DAOResponse getEmployeeList(Map<String,Object> searchOptions);
	DAOResponse saveManagerData(ManagerDetails mgrDetails);
	DAOResponse updateManagerData(ManagerDetails mgrDetails);
	DAOResponse getManagerList(String managerType);
	DAOResponse getAllManagerList();
	
}
