package com.ibm.tools.dbacess;

import com.ibm.tools.portfoliodb.data.UOMDetails;

public interface UOMDataDAO {

	DAOResponse addNewUOM(UOMDetails uomDtails);
	DAOResponse updateUOM(UOMDetails uomDtails);
	DAOResponse getUOMDetails(UOMDetails uomDtails);
	DAOResponse getUOMList(UOMDetails uomDtails);
}
