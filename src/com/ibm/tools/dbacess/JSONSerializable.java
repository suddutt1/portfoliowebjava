package com.ibm.tools.dbacess;

import com.mongodb.BasicDBObject;

/**
 * Interface to represent a java bean that could be stored in JSON based data
 * store.
 * 
 * @author SUDDUTT1
 *
 */
public interface JSONSerializable {

	BasicDBObject toDBObject();
	String toJSON();

}
