package com.ibm.tools.portfoliodb.data;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ibm.tools.dbacess.DAOResponse;
import com.ibm.tools.utils.DAOFactory;

public class ReferenceDataHelper {

	private static Map<String, ReferenceData> _refDataLookupMap;

	private static void init() {
		DAOResponse resp = DAOFactory.getReferenceDataDAO().getReferenceData(null);
		if (resp != null && resp.getStatus() == DAOResponse.STATUS_DAO_SUCCESS) {
			List<ReferenceData> refDataList = (List<ReferenceData>) resp
					.getResponse();
			_refDataLookupMap = new HashMap<String, ReferenceData>();
			for (ReferenceData refData : refDataList) {
				_refDataLookupMap.put(
						refData.getType() + "_" + refData.getCode(),
						refData);
			}
		}
	}
	public static String getDisplayName(String type,String code )
	{
		if(_refDataLookupMap==null)
		{
			init();
		}
		if(_refDataLookupMap!=null)
		{
			if(_refDataLookupMap.containsKey(type+"_"+code))
			{
				return _refDataLookupMap.get(type+"_"+code).getDisplayValue();
			}
		}	
		return null;
	}
	
}
