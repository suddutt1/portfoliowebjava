package com.ibm.tools.portfoliodb.data;

import java.util.HashMap;
import java.util.Map;

import org.bson.types.ObjectId;

import com.ibm.tools.dbacess.JSONSerializable;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

public class UOMDetails implements JSONSerializable {

	private ObjectId id;
	
	private String pmpProjectId;
	private String uomName;
	private String geo;
	private String managdType;
	private String uomStartDate;
	private String uomMgr;
	private String progMgr;
	private String accountMgr;
	private String objIdHexString;
	private long uomId;
	private String deleteFg;
	
	private Map<String,String> displayFields;
	
	@Override
	public BasicDBObject toDBObject() {
		BasicDBObject dbObject = new BasicDBObject();
		if (this.id == null) {
			this.id = new ObjectId();
		}
		dbObject.put("_id", this.id);
		dbObject.put("pmpProjectId",this.pmpProjectId);
		dbObject.put("uomName",this.uomName);
		dbObject.put("geo",this.geo);
		dbObject.put("managdType",this.managdType);
		dbObject.put("uomStartDate",this.uomStartDate);
		dbObject.put("uomMgr",this.uomMgr);
		dbObject.put("progMgr",this.progMgr);
		dbObject.put("accountMgr",this.accountMgr);
		dbObject.put("uomId",this.uomId);
		dbObject.put("deleteFg", this.deleteFg);
		
		return dbObject;

	}
	public static UOMDetails parseToObject(DBObject dbObject)
	{
		if(dbObject!=null)
		{
			UOMDetails parsedObject = new UOMDetails();
			parsedObject.setId((ObjectId)dbObject.get("_id"));
			parsedObject.setPmpProjectId((String)dbObject.get("pmpProjectId"));
			parsedObject.setUomName((String)dbObject.get("uomName"));
			parsedObject.setGeo((String)dbObject.get("geo"));
			parsedObject.setManagdType((String)dbObject.get("managdType"));
			parsedObject.setUomStartDate((String)dbObject.get("uomStartDate"));
			parsedObject.setUomMgr((String)dbObject.get("uomMgr"));
			parsedObject.setProgMgr((String)dbObject.get("progMgr"));
			parsedObject.setAccountMgr((String)dbObject.get("accountMgr"));
			parsedObject.setUomId((Long)dbObject.get("uomId"));
			parsedObject.setDeleteFg((String)dbObject.get("deleteFg"));
			parsedObject.setObjIdHexString(parsedObject.getId().toHexString());
			return parsedObject;
		}
		return null;
	}
	
	@Override
	public String toJSON() {
		// TODO Auto-generated method stub
		
		return null;
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
	 * @return the pmpProjectId
	 */
	public String getPmpProjectId() {
		return pmpProjectId;
	}

	/**
	 * @param pmpProjectId the pmpProjectId to set
	 */
	public void setPmpProjectId(String pmpProjectId) {
		this.pmpProjectId = pmpProjectId;
	}

	/**
	 * @return the uomName
	 */
	public String getUomName() {
		return uomName;
	}

	/**
	 * @param uomName the uomName to set
	 */
	public void setUomName(String uomName) {
		this.uomName = uomName;
	}

	/**
	 * @return the geo
	 */
	public String getGeo() {
		return geo;
	}

	/**
	 * @param geo the geo to set
	 */
	public void setGeo(String geo) {
		this.geo = geo;
	}

	/**
	 * @return the managdType
	 */
	public String getManagdType() {
		return managdType;
	}

	/**
	 * @param managdType the managdType to set
	 */
	public void setManagdType(String managdType) {
		this.managdType = managdType;
	}

	/**
	 * @return the uomStartDate
	 */
	public String getUomStartDate() {
		return uomStartDate;
	}

	/**
	 * @param uomStartDate the uomStartDate to set
	 */
	public void setUomStartDate(String uomStartDate) {
		this.uomStartDate = uomStartDate;
	}

	/**
	 * @return the uomMgr
	 */
	public String getUomMgr() {
		return uomMgr;
	}

	/**
	 * @param uomMgr the uomMgr to set
	 */
	public void setUomMgr(String uomMgr) {
		this.uomMgr = uomMgr;
	}

	/**
	 * @return the progMgr
	 */
	public String getProgMgr() {
		return progMgr;
	}

	/**
	 * @param progMgr the progMgr to set
	 */
	public void setProgMgr(String progMgr) {
		this.progMgr = progMgr;
	}

	/**
	 * @return the accountMgr
	 */
	public String getAccountMgr() {
		return accountMgr;
	}

	/**
	 * @param accountMgr the accountMgr to set
	 */
	public void setAccountMgr(String accountMgr) {
		this.accountMgr = accountMgr;
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
	 * @return the uomId
	 */
	public long getUomId() {
		return uomId;
	}

	/**
	 * @param uomId the uomId to set
	 */
	public void setUomId(long uomId) {
		this.uomId = uomId;
	}

	/**
	 * @return the displayFields
	 */
	public Map<String, String> getDisplayFields() {
		return displayFields;
	}
	/**
	 * @return the deleteFg
	 */
	public String getDeleteFg() {
		return deleteFg;
	}
	/**
	 * @param deleteFg the deleteFg to set
	 */
	public void setDeleteFg(String deleteFg) {
		this.deleteFg = deleteFg;
	}

	/**
	 * @param displayFields the displayFields to set
	 */
	public void setDisplayFields(Map<String, String> displayFields) {
		this.displayFields = displayFields;
	}
	
	public void setDisplayFields()
	{
		this.displayFields = new HashMap<String, String>(4);
		this.displayFields.put("geo", ReferenceDataHelper.getDisplayName("geography", this.geo));
		this.displayFields.put("managdType", ReferenceDataHelper.getDisplayName("manageType", this.managdType));
		this.displayFields.put("uomMgr", ManagerReferenceDataHelper.getManagerName("uompm", this.uomMgr));
		this.displayFields.put("progMgr", ManagerReferenceDataHelper.getManagerName("tpm", this.progMgr));
		this.displayFields.put("accountMgr", ManagerReferenceDataHelper.getManagerName("am", this.accountMgr));
	}
	
	public void updatedWith(UOMDetails updatedObject)
	{
		if(updatedObject!=null)
		{
			this.pmpProjectId=(updatedObject.pmpProjectId!=null?updatedObject.pmpProjectId:this.pmpProjectId);
			this.uomName=(updatedObject.uomName!=null?updatedObject.uomName:this.uomName);
			this.geo=(updatedObject.geo!=null?updatedObject.geo:this.geo);
			this.managdType=(updatedObject.managdType!=null?updatedObject.managdType:this.managdType);
			this.uomStartDate=(updatedObject.uomStartDate!=null?updatedObject.uomStartDate:this.uomStartDate);
			this.uomMgr=(updatedObject.uomMgr!=null?updatedObject.uomMgr:this.uomMgr);
			this.progMgr=(updatedObject.progMgr!=null?updatedObject.progMgr:this.progMgr);
			this.accountMgr=(updatedObject.accountMgr!=null?updatedObject.accountMgr:this.accountMgr);
			this.deleteFg = (updatedObject.deleteFg!=null?updatedObject.deleteFg:this.deleteFg);
			
		}
	}
	
}
