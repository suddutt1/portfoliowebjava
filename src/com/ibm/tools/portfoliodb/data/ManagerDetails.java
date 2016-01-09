package com.ibm.tools.portfoliodb.data;

import org.bson.types.ObjectId;

import com.ibm.tools.dbacess.JSONSerializable;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

public class ManagerDetails implements JSONSerializable {
	private ObjectId id;
	private String empId;
	private String empName;
	private String mgrType;
	private String notesId;
	private String emailId;
	
	public ManagerDetails()
	{
		super();
	}
	
	
	/**
	 * @param empId
	 * @param empName
	 * @param mgrType
	 * @param notesId
	 * @param emailId
	 */
	public ManagerDetails(String empId, String empName, String mgrType,
			String notesId, String emailId) {
		super();
		this.empId = empId;
		this.empName = empName;
		this.mgrType = mgrType;
		this.notesId = notesId;
		this.emailId = emailId;
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
	 * @return the empName
	 */
	public String getEmpName() {
		return empName;
	}


	/**
	 * @param empName the empName to set
	 */
	public void setEmpName(String empName) {
		this.empName = empName;
	}


	/**
	 * @return the mgrType
	 */
	public String getMgrType() {
		return mgrType;
	}


	/**
	 * @param mgrType the mgrType to set
	 */
	public void setMgrType(String mgrType) {
		this.mgrType = mgrType;
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
	 * @return the emailId
	 */
	public String getEmailId() {
		return emailId;
	}


	/**
	 * @param emailId the emailId to set
	 */
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}


	@Override
	public BasicDBObject toDBObject() {
		BasicDBObject dbObject = new BasicDBObject();
		if (this.id == null) {
			this.id = new ObjectId();
		}
		dbObject.put("_id", this.id);
		dbObject.put("empId",this.empId);
		dbObject.put("empName",this.empName);
		dbObject.put("mgrType",this.mgrType);
		dbObject.put("notesId",this.notesId);
		dbObject.put("emailId",this.emailId);
		return dbObject;
	}
	public static ManagerDetails parseToObject(DBObject dbObject)
	{
		if(dbObject!=null)
		{
			ManagerDetails parsedObject = new ManagerDetails();
			parsedObject.setId((ObjectId)dbObject.get("_id"));
			parsedObject.setEmpId((String)dbObject.get("empId"));
			parsedObject.setEmpName((String)dbObject.get("empName"));
			parsedObject.setMgrType((String)dbObject.get("mgrType"));
			parsedObject.setNotesId((String)dbObject.get("notesId"));
			parsedObject.setEmailId((String)dbObject.get("emailId"));
			return parsedObject;
		}
		return null;
	}


	@Override
	public String toJSON() {
		// TODO Auto-generated method stub
		return null;
	}
}
