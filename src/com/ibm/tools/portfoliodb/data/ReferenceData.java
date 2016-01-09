package com.ibm.tools.portfoliodb.data;

import org.bson.types.ObjectId;

import com.ibm.tools.dbacess.JSONSerializable;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

/**
 * Reference data bean
 * @author SUDDUTT1
 *
 */
public class ReferenceData implements JSONSerializable {

	private ObjectId id;
	private String type;
	private String code;
	private String displayValue;
	
	public ReferenceData()
	{
		super();
	}
	
	
	/**
	 * @param type
	 * @param code
	 * @param displayValue
	 */
	public ReferenceData(String type, String code, String displayValue) {
		super();
		this.type = type;
		this.code = code;
		this.displayValue = displayValue;
	}


	public ObjectId getId() {
		return id;
	}

	public void setId(ObjectId id) {
		this.id = id;
	}


	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
	}


	public String getCode() {
		return code;
	}


	public void setCode(String code) {
		this.code = code;
	}


	public String getDisplayValue() {
		return displayValue;
	}


	public void setDisplayValue(String displayValue) {
		this.displayValue = displayValue;
	}


	@Override
	public BasicDBObject toDBObject() {
		BasicDBObject dbObject = new BasicDBObject();
		if (this.id == null) {
			this.id = new ObjectId();
		}
		dbObject.put("_id", this.id);
		dbObject.put("type", this.type);
		dbObject.put("code", this.code);
		dbObject.put("displayValue", this.displayValue);
		return dbObject;
	}

	public static ReferenceData parseToObject(DBObject dbObject)
	{
		if(dbObject!=null)
		{
			ReferenceData parsedObject = new ReferenceData();
			parsedObject.setId((ObjectId)dbObject.get("_id"));
			parsedObject.setType((String)dbObject.get("type"));
			parsedObject.setCode((String)dbObject.get("code"));
			parsedObject.setDisplayValue((String)dbObject.get("displayValue"));
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
