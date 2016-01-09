package com.ibm.tools.portfoliodb.data;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ibm.tools.dbacess.DAOResponse;
import com.ibm.tools.utils.DAOFactory;

public class ProjectDataHelper {

	private static Map<Long, ProjectDetails> _projDataLookupMap;

	private static void init() {
		DAOResponse resp = DAOFactory.getProjetDataDAO().getProjectList(null);
		if (resp != null && resp.getStatus() == DAOResponse.STATUS_DAO_SUCCESS) {
			List<ProjectDetails> refDataList = (List<ProjectDetails>) resp
					.getResponse();
			_projDataLookupMap = new HashMap<Long, ProjectDetails>();
			for (ProjectDetails refData : refDataList) {
				_projDataLookupMap.put(
						refData.getProjectId(),
						refData);
			}
		}
	}
	public static String getSubProjectName(Long projectId )
	{
		if(_projDataLookupMap==null)
		{
			init();
		}
		if(_projDataLookupMap!=null)
		{
			if(_projDataLookupMap.containsKey(projectId))
			{
				return _projDataLookupMap.get(projectId).getProjectName();
			}
		}	
		return null;
	}
	
}
