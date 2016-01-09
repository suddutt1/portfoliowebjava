package com.ibm.tools.portfoliodb.data;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ibm.tools.dbacess.DAOResponse;
import com.ibm.tools.utils.DAOFactory;

public class ManagerReferenceDataHelper {

	private static Map<String, ManagerDetails> _mgrRefLookupMap;

	private static void init() {
		DAOResponse resp = DAOFactory.getEmployeeDataDAO().getAllManagerList();
		if (resp != null && resp.getStatus() == DAOResponse.STATUS_DAO_SUCCESS) {
			List<ManagerDetails> mgrList = (List<ManagerDetails>) resp
					.getResponse();
			_mgrRefLookupMap = new HashMap<String, ManagerDetails>();
			for (ManagerDetails mgDetails : mgrList) {
				_mgrRefLookupMap.put(
						mgDetails.getMgrType() + "_" + mgDetails.getEmpId(),
						mgDetails);
			}
		}
	}
	public static String getManagerName(String mgrType,String id )
	{
		if(_mgrRefLookupMap==null)
		{
			init();
		}
		if(_mgrRefLookupMap!=null)
		{
			if(_mgrRefLookupMap.containsKey(mgrType+"_"+id))
			{
				return _mgrRefLookupMap.get(mgrType+"_"+id).getEmpName();
			}
		}	
		return null;
	}
	
}
