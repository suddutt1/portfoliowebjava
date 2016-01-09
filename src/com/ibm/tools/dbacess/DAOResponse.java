package com.ibm.tools.dbacess;


/**
 * DAO Response interface object
 * @author SUDDUTT1
 *
 */
public interface DAOResponse {

	int STATUS_DAO_SUCCESS = 0;
	int STATUS_DAO_FAILURE = -1; //Exception in DAO
	int STATUS_DAO_NO_RESULT = -2 ;//No result found
	int STATUS_DAO_INVALID_INPUT = -3;//Invalid input provided 
	int STATUS_DAO_DATA_EXISTIS = -4;
	
	int getStatus();
	String getStatusText();
	Object getResponse();
}
