package com.ibm.tools.dbacess;

/**
 * Reference DAO interface
 * @author SUDDUTT1
 *
 */
public interface ReferenceDataDAO {

	DAOResponse getReferenceData(String refDataType);
	DAOResponse addReferenceData(String refDataType,String code,String description);
}
