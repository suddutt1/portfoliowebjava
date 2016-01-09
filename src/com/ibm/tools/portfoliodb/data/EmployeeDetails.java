package com.ibm.tools.portfoliodb.data;

import java.util.HashMap;
import java.util.Map;

import org.bson.types.ObjectId;

import com.ibm.tools.dbacess.JSONSerializable;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

public class EmployeeDetails implements JSONSerializable {
	private ObjectId id;
	private String empId;
	private String name;
	private String intranetId;
	private String empType;
	private String notesId;
	private String eltpFg;
	private String gender;
	private String primarySkill;
	private String location;
	private String building;
	private String pem;
	private String band;
	private String subBand;
	private String attrType;
	
	private String objIdHexString;
	private Map<String,String> displayFields;
	
	public EmployeeDetails() {
		// TODO Auto-generated constructor stub
	}
	
	
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
	 * @return the empId
	 */
	public String getEmpId() {
		return empId;
	}


	/**
	 * @param empId the empId to set
	 */
	public void setEmpId(String empId) {
		this.empId = empId;
	}


	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}


	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}


	/**
	 * @return the intranetId
	 */
	public String getIntranetId() {
		return intranetId;
	}


	/**
	 * @param intranetId the intranetId to set
	 */
	public void setIntranetId(String intranetId) {
		this.intranetId = intranetId;
	}


	/**
	 * @return the empType
	 */
	public String getEmpType() {
		return empType;
	}


	/**
	 * @param empType the empType to set
	 */
	public void setEmpType(String empType) {
		this.empType = empType;
	}


	/**
	 * @return the notesId
	 */
	public String getNotesId() {
		return notesId;
	}


	/**
	 * @param notesId the notesId to set
	 */
	public void setNotesId(String notesId) {
		this.notesId = notesId;
	}


	/**
	 * @return the eltpFg
	 */
	public String getEltpFg() {
		return eltpFg;
	}


	/**
	 * @param eltpFg the eltpFg to set
	 */
	public void setEltpFg(String eltpFg) {
		this.eltpFg = eltpFg;
	}


	/**
	 * @return the gender
	 */
	public String getGender() {
		return gender;
	}


	/**
	 * @param gender the gender to set
	 */
	public void setGender(String gender) {
		this.gender = gender;
	}


	/**
	 * @return the primarySkill
	 */
	public String getPrimarySkill() {
		return primarySkill;
	}


	/**
	 * @param primarySkill the primarySkill to set
	 */
	public void setPrimarySkill(String primarySkill) {
		this.primarySkill = primarySkill;
	}

	
	/**
	 * @return the location
	 */
	public String getLocation() {
		return location;
	}


	/**
	 * @param location the location to set
	 */
	public void setLocation(String location) {
		this.location = location;
	}


	/**
	 * @return the building
	 */
	public String getBuilding() {
		return building;
	}


	/**
	 * @param building the building to set
	 */
	public void setBuilding(String building) {
		this.building = building;
	}


	/**
	 * @return the pem
	 */
	public String getPem() {
		return pem;
	}


	/**
	 * @param pem the pem to set
	 */
	public void setPem(String pem) {
		this.pem = pem;
	}


	/**
	 * @return the band
	 */
	public String getBand() {
		return band;
	}


	/**
	 * @param band the band to set
	 */
	public void setBand(String band) {
		this.band = band;
	}


	/**
	 * @return the subBand
	 */
	public String getSubBand() {
		return subBand;
	}


	/**
	 * @param subBand the subBand to set
	 */
	public void setSubBand(String subBand) {
		this.subBand = subBand;
	}


	/**
	 * @return the attrType
	 */
	public String getAttrType() {
		return attrType;
	}


	/**
	 * @param attrType the attrType to set
	 */
	public void setAttrType(String attrType) {
		this.attrType = attrType;
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

	@Override
	public BasicDBObject toDBObject() {
		BasicDBObject dbObject = new BasicDBObject();
		if (this.id == null) {
			this.id = new ObjectId();
		}
		dbObject.put("_id", this.id);
		dbObject.put("empId",this.empId);
		dbObject.put("name",this.name);
		dbObject.put("intranetId",this.intranetId);
		dbObject.put("empType",this.empType);
		dbObject.put("notesId",this.notesId);
		dbObject.put("eltpFg",this.eltpFg);
		dbObject.put("gender",this.gender);
		dbObject.put("primarySkill",this.primarySkill);
		dbObject.put("location",this.location);
		dbObject.put("building",this.building);
		dbObject.put("pem",this.pem);
		dbObject.put("band",this.band);
		dbObject.put("subBand",this.subBand);
		dbObject.put("attrType",this.attrType);

		return dbObject;
	}
	public static EmployeeDetails parseToObject(DBObject dbObject)
	{
		if(dbObject!=null)
		{
			EmployeeDetails parsedObject = new EmployeeDetails();
			parsedObject.setId((ObjectId)dbObject.get("_id"));
			parsedObject.setEmpId((String)dbObject.get("empId"));
			parsedObject.setName((String)dbObject.get("name"));
			parsedObject.setIntranetId((String)dbObject.get("intranetId"));
			parsedObject.setEmpType((String)dbObject.get("empType"));
			parsedObject.setNotesId((String)dbObject.get("notesId"));
			parsedObject.setEltpFg((String)dbObject.get("eltpFg"));
			parsedObject.setGender((String)dbObject.get("gender"));
			parsedObject.setPrimarySkill((String)dbObject.get("primarySkill"));
			parsedObject.setLocation((String)dbObject.get("location"));
			parsedObject.setBuilding((String)dbObject.get("building"));
			parsedObject.setPem((String)dbObject.get("pem"));
			parsedObject.setBand((String)dbObject.get("band"));
			parsedObject.setSubBand((String)dbObject.get("subBand"));
			parsedObject.setAttrType((String)dbObject.get("attrType"));
			return parsedObject;
		}
		return null;
	}

	public void updateWith(EmployeeDetails updatedDetails)
	{
		this.empId=(updatedDetails.empId!=null?updatedDetails.empId:this.empId);
		this.name=(updatedDetails.name!=null?updatedDetails.name:this.name);
		this.intranetId=(updatedDetails.intranetId!=null?updatedDetails.intranetId:this.intranetId);
		this.empType=(updatedDetails.empType!=null?updatedDetails.empType:this.empType);
		this.notesId=(updatedDetails.notesId!=null?updatedDetails.notesId:this.notesId);
		this.eltpFg=(updatedDetails.eltpFg!=null?updatedDetails.eltpFg:this.eltpFg);
		this.gender=(updatedDetails.gender!=null?updatedDetails.gender:this.gender);
		this.primarySkill=(updatedDetails.primarySkill!=null?updatedDetails.primarySkill:this.primarySkill);
		this.location=(updatedDetails.location!=null?updatedDetails.location:this.location);
		this.building=(updatedDetails.building!=null?updatedDetails.building:this.building);
		this.pem=(updatedDetails.pem!=null?updatedDetails.pem:this.pem);
		this.band=(updatedDetails.band!=null?updatedDetails.band:this.band);
		this.subBand=(updatedDetails.subBand!=null?updatedDetails.subBand:this.subBand);
		this.attrType=(updatedDetails.attrType!=null?updatedDetails.attrType:this.attrType);

	}

	@Override
	public String toJSON() {
		// TODO Auto-generated method stub
		return null;
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
	
	public void setDisplayFields()
	{
		this.displayFields = new HashMap<String, String>(5);
		this.displayFields.put("empType", ReferenceDataHelper.getDisplayName("empType",this.empType));
		this.displayFields.put("primarySkill", ReferenceDataHelper.getDisplayName("skill",this.primarySkill));
		this.displayFields.put("location", ReferenceDataHelper.getDisplayName("location",this.location));
		this.displayFields.put("building",  ReferenceDataHelper.getDisplayName("locationBlding",this.building));
		this.displayFields.put("pem", ManagerReferenceDataHelper.getManagerName("pem",this.pem));
		this.displayFields.put("attrType", ReferenceDataHelper.getDisplayName("attrType",this.attrType));
		this.objIdHexString =id.toHexString();
	}
}
