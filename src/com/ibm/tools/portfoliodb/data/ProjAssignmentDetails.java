package com.ibm.tools.portfoliodb.data;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.bson.types.ObjectId;

import com.ibm.tools.dbacess.JSONSerializable;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

public class ProjAssignmentDetails implements JSONSerializable {

	private ObjectId id;
	private String empid;
	private long projectId;
	private Date startDate;
	private Date endDate;
	private String role;
	private String isCore;
	private String pmpId;
	private String rollOffType;
	private String releaseReasonText;
	private String releasedFg;
	
	private String objIdHexString;
	private Map<String,String> displayFields;
	
	//Reference objects 
	private EmployeeDetails empDetails;
	private ProjectDetails projDetails;
	private UOMDetails uomDetails;

	/**
	 * @return the id
	 */
	public ObjectId getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(ObjectId id) {
		this.id = id;
	}

	/**
	 * @return the empid
	 */
	public String getEmpid() {
		return empid;
	}

	/**
	 * @param empid the empid to set
	 */
	public void setEmpid(String empid) {
		this.empid = empid;
	}

	/**
	 * @return the projectId
	 */
	public long getProjectId() {
		return projectId;
	}

	/**
	 * @param projectId the projectId to set
	 */
	public void setProjectId(long projectId) {
		this.projectId = projectId;
	}

	/**
	 * @return the startDate
	 */
	public Date getStartDate() {
		return startDate;
	}

	/**
	 * @param startDate the startDate to set
	 */
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	/**
	 * @return the endDate
	 */
	public Date getEndDate() {
		return endDate;
	}

	/**
	 * @param endDate the endDate to set
	 */
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	/**
	 * @return the role
	 */
	public String getRole() {
		return role;
	}

	/**
	 * @param role the role to set
	 */
	public void setRole(String role) {
		this.role = role;
	}

	/**
	 * @return the isCore
	 */
	public String getIsCore() {
		return isCore;
	}

	/**
	 * @param isCore the isCore to set
	 */
	public void setIsCore(String isCore) {
		this.isCore = isCore;
	}

	/**
	 * @return the pmpId
	 */
	public String getPmpId() {
		return pmpId;
	}

	/**
	 * @param pmpId the pmpId to set
	 */
	public void setPmpId(String pmpId) {
		this.pmpId = pmpId;
	}

	/**
	 * @return the rollOffType
	 */
	public String getRollOffType() {
		return rollOffType;
	}

	/**
	 * @param rollOffType the rollOffType to set
	 */
	public void setRollOffType(String rollOffType) {
		this.rollOffType = rollOffType;
	}

	/**
	 * @return the releaseReasonText
	 */
	public String getReleaseReasonText() {
		return releaseReasonText;
	}

	/**
	 * @param releaseReasonText the releaseReasonText to set
	 */
	public void setReleaseReasonText(String releaseReasonText) {
		this.releaseReasonText = releaseReasonText;
	}

	/**
	 * @return the objIdHexString
	 */
	public String getObjIdHexString() {
		return objIdHexString;
	}

	/**
	 * @param objIdHexString the objIdHexString to set
	 */
	public void setObjIdHexString(String objIdHexString) {
		this.objIdHexString = objIdHexString;
	}

	/**
	 * @return the displayFields
	 */
	public Map<String, String> getDisplayFields() {
		return displayFields;
	}

	/**
	 * @param displayFields the displayFields to set
	 */
	public void setDisplayFields(Map<String, String> displayFields) {
		this.displayFields = displayFields;
	}
	@Override
	public BasicDBObject toDBObject() {
		BasicDBObject dbObject = new BasicDBObject();
		if (this.id == null) {
			this.id = new ObjectId();
		}
		dbObject.put("_id",this.id);
		dbObject.put("empid",this.empid);
		dbObject.put("projectId",this.projectId);
		dbObject.put("startDate",this.startDate);
		dbObject.put("endDate",this.endDate);
		dbObject.put("role",this.role);
		dbObject.put("isCore",this.isCore);
		dbObject.put("pmpId",this.pmpId);
		dbObject.put("rollOffType",this.rollOffType);
		dbObject.put("releaseReasonText",this.releaseReasonText);
		dbObject.put("releasedFg",this.releasedFg);

		return dbObject;
	}

	@Override
	public String toJSON() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * @return the releasedFg
	 */
	public String getReleasedFg() {
		return releasedFg;
	}

	/**
	 * @param releasedFg the releasedFg to set
	 */
	public void setReleasedFg(String releasedFg) {
		this.releasedFg = releasedFg;
	}

	public static ProjAssignmentDetails parseToObject(DBObject dbObject)
	{
		if(dbObject!=null)
		{
			ProjAssignmentDetails parsedObject = new ProjAssignmentDetails();
			parsedObject.setId((ObjectId)dbObject.get("_id"));
			parsedObject.setEmpid((String)dbObject.get("empid"));
			parsedObject.setProjectId((Long)dbObject.get("projectId"));
			parsedObject.setStartDate((Date)dbObject.get("startDate"));
			parsedObject.setEndDate((Date)dbObject.get("endDate"));
			parsedObject.setRole((String)dbObject.get("role"));
			parsedObject.setIsCore((String)dbObject.get("isCore"));
			parsedObject.setPmpId((String)dbObject.get("pmpId"));
			parsedObject.setRollOffType((String)dbObject.get("rollOffType"));
			parsedObject.setReleaseReasonText((String)dbObject.get("releaseReasonText"));
			parsedObject.setReleasedFg((String)dbObject.get("releasedFg"));
			parsedObject.setObjIdHexString(parsedObject.getId().toHexString());
			return parsedObject;
		}
		return null;
	}
	
	public void setDisplayFields()
	{
		this.displayFields = new HashMap<String, String>(2);
		this.displayFields.put("role", ReferenceDataHelper.getDisplayName("role", this.role));
		this.displayFields.put("rollOffType", ReferenceDataHelper.getDisplayName("releaseReason", this.rollOffType));
		this.objIdHexString = this.id.toHexString();
		
		
	}

	/**
	 * @return the empDetails
	 */
	public EmployeeDetails getEmpDetails() {
		return empDetails;
	}

	/**
	 * @param empDetails the empDetails to set
	 */
	public void setEmpDetails(EmployeeDetails empDetails) {
		this.empDetails = empDetails;
	}

	/**
	 * @return the projDetails
	 */
	public ProjectDetails getProjDetails() {
		return projDetails;
	}

	/**
	 * @param projDetails the projDetails to set
	 */
	public void setProjDetails(ProjectDetails projDetails) {
		this.projDetails = projDetails;
	}

	/**
	 * @return the uomDetails
	 */
	public UOMDetails getUomDetails() {
		return uomDetails;
	}

	/**
	 * @param uomDetails the uomDetails to set
	 */
	public void setUomDetails(UOMDetails uomDetails) {
		this.uomDetails = uomDetails;
	}
	public void updateWith(ProjAssignmentDetails updatedObject)
	{
		if(updatedObject!=null)
		{
			this.empid=(updatedObject.empid!=null?updatedObject.empid:this.empid);
			this.projectId=(updatedObject.projectId>0?updatedObject.projectId:this.projectId);
			this.startDate=(updatedObject.startDate!=null?updatedObject.startDate:this.startDate);
			this.endDate=(updatedObject.endDate!=null?updatedObject.endDate:this.endDate);
			this.role=(updatedObject.role!=null?updatedObject.role:this.role);
			this.isCore=(updatedObject.isCore!=null?updatedObject.isCore:this.isCore);
			this.pmpId=(updatedObject.pmpId!=null?updatedObject.pmpId:this.pmpId);
			this.rollOffType=(updatedObject.rollOffType!=null?updatedObject.rollOffType:this.rollOffType);
			this.releaseReasonText=(updatedObject.releaseReasonText!=null?updatedObject.releaseReasonText:this.releaseReasonText);
			this.releasedFg=(updatedObject.releasedFg!=null?updatedObject.releasedFg:this.releasedFg);

		}
	}
}
