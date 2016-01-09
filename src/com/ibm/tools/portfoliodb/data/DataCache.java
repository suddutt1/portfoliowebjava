package com.ibm.tools.portfoliodb.data;

import java.util.Collections;
import java.util.List;

import com.ibm.tools.dbacess.DAOResponse;
import com.ibm.tools.utils.DAOFactory;

public class DataCache {

	private static List<ManagerDetails> _mgrList = null;
	
	private static List<ReferenceData> _locationList = null;
	private static List<ReferenceData> _subBandList = null;
	private static List<ReferenceData> _bandList = null;
	private static List<ReferenceData> _locationBldgList = null;
	private static List<ReferenceData> _skillList = null;
	private static List<ReferenceData> _empTypeList = null;
	private static List<ReferenceData> _projRoleList = null;
	private static List<ReferenceData> _managedType = null;
	private static List<ReferenceData> _domainArea = null;
	private static List<ReferenceData> _projectType = null;
	private static List<ReferenceData> _geoList = null;
	private static List<ReferenceData> _projectTechList = null;
	private static List<ReferenceData> _releaseReasonList = null;
	private static List<ReferenceData> _attritionReasonList = null;

	
	public static List<ManagerDetails> getAllMgrList() {
		if (_mgrList == null) {
			DAOResponse resp = DAOFactory.getEmployeeDataDAO().getAllManagerList();
			if (resp != null
					&& resp.getStatus() == DAOResponse.STATUS_DAO_SUCCESS) {
				_mgrList = (List<ManagerDetails>) resp.getResponse();
			} else {
				_mgrList = Collections.emptyList();
			}
		}
		return _mgrList;
	}

	private static List<ReferenceData> loadRefData(String refDataType) {
		List<ReferenceData> refData = null;
		DAOResponse resp = DAOFactory.getReferenceDataDAO().getReferenceData(
				refDataType);
		if (resp != null && resp.getStatus() == DAOResponse.STATUS_DAO_SUCCESS) {
			refData = (List<ReferenceData>) resp.getResponse();
		} else {
			refData = Collections.emptyList();
		}
		return refData;
	}

	public static List<ReferenceData> getEmployeeType() {

		if (_empTypeList == null) {

			_empTypeList = loadRefData("empType");
		}
		return _empTypeList;
	}

	public static List<ReferenceData> getSkillList() {
		if (_skillList == null) {
			_skillList = loadRefData("skill");
		}
		return _skillList;
	}

	public static List<ReferenceData> getLocationBldgList() {
		if (_locationBldgList == null) {
			_locationBldgList = loadRefData("locationBlding");
		}
		return _locationBldgList;
	}

	public static List<ReferenceData> getBandList() {
		if (_bandList == null) {
			_bandList = loadRefData("band");
		}
		return _bandList;
	}

	public static List<ReferenceData> getLocationList() {
		if (_locationList == null) {
			_locationList = loadRefData("location");
		}
		return _locationList;
	}

	public static List<ReferenceData> getSubBandList() {
		if (_subBandList == null) {
			_subBandList = loadRefData("subBand");
		}
		return _subBandList;
	}

	/**
	 * @return the _domainArea
	 */
	public static List<ReferenceData> getDomainArea() {
		if(_domainArea==null)
		{
			_domainArea = loadRefData("domainArea");
		}
		return _domainArea;
	}

	/**
	 * @return the _managedType
	 */
	public static List<ReferenceData> getManagedType() {
		if(_managedType==null)
		{
			_managedType = loadRefData("manageType");
		}
		return _managedType;
	}

	/**
	 * @return the _projectType
	 */
	public static List<ReferenceData> getProjectType() {
		if(_projectType==null)
		{
			_projectType = loadRefData("projectType");
		}
		return _projectType;
	}

	/**
	 * @return the _geoList
	 */
	public static List<ReferenceData> getGeoList() {
		if(_geoList==null)
		{
			_geoList = loadRefData("geography");
		}
		return _geoList;
	}

	/**
	 * @return the _projectTechList
	 */
	public static List<ReferenceData> getProjectTechList() {
		if(_projectTechList==null)
		{
			_projectTechList = loadRefData("projectPrimTech");
		}
		return _projectTechList;
	}
	public static List<ReferenceData> getRoleInProjectList()
	{
		if(_projRoleList==null)
		{
			_projRoleList = loadRefData("role");
		}
		return _projRoleList;
	}
	public static List<ReferenceData> getAttritionReasonList()
	{
		if(_attritionReasonList==null)
		{
			_attritionReasonList = loadRefData("attrReason");
		}
		return _attritionReasonList;
	}
	public static List<ReferenceData> getReleaseResonList()
	{
		if(_releaseReasonList==null)
		{
			_releaseReasonList = loadRefData("releaseReason");
		}
		return _releaseReasonList;
	}
	public static void reloadMgrData()
	{
		DAOResponse resp = DAOFactory.getEmployeeDataDAO().getAllManagerList();
		if (resp != null
				&& resp.getStatus() == DAOResponse.STATUS_DAO_SUCCESS) {
			_mgrList = (List<ManagerDetails>) resp.getResponse();
		} else {
			_mgrList = Collections.emptyList();
		}
	}

}
