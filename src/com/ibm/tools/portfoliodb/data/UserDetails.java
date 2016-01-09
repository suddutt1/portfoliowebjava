package com.ibm.tools.portfoliodb.data;

import org.bson.types.ObjectId;

import com.ibm.tools.dbacess.JSONSerializable;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

public class UserDetails implements JSONSerializable {

	private ObjectId id;
	private String uid;
	private String password;
	private String emailId;
	private String role;
	private String firstName;
	private String lastName;
	
	public UserDetails()
	{
		super();
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
	 * @return the uid
	 */
	public String getUid() {
		return uid;
	}
	/**
	 * @param uid the uid to set
	 */
	public void setUid(String uid) {
		this.uid = uid;
	}
	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
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
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}
	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}
	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	@Override
	public BasicDBObject toDBObject() {
		BasicDBObject dbObject = new BasicDBObject();
		if (this.id == null) {
			this.id = new ObjectId();
		}
		dbObject.put("_id",this.id);
		dbObject.put("uid",this.uid);
		dbObject.put("password",this.password);
		dbObject.put("emailId",this.emailId);
		dbObject.put("role",this.role);
		dbObject.put("firstName",this.firstName);
		dbObject.put("lastName",this.lastName);
		return dbObject;
	}

	@Override
	public String toJSON() {
		// TODO Auto-generated method stub
		return null;
	}
	public static UserDetails parseToObject(DBObject dbObject)
	{
		if(dbObject!=null)
		{
			UserDetails parsedObject = new UserDetails();
			parsedObject.setId((ObjectId)dbObject.get("_id"));
			parsedObject.setUid((String)dbObject.get("uid"));
			parsedObject.setPassword((String)dbObject.get("password"));
			parsedObject.setEmailId((String)dbObject.get("emailId"));
			parsedObject.setRole((String)dbObject.get("role"));
			parsedObject.setFirstName((String)dbObject.get("firstName"));
			parsedObject.setLastName((String)dbObject.get("lastName"));

			return parsedObject;
		}
		return null;
	}
}
