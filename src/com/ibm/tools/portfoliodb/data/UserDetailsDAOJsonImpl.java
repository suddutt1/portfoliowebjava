package com.ibm.tools.portfoliodb.data;

import com.ibm.tools.dbacess.DAOResponse;
import com.ibm.tools.dbacess.UserDetailsDAO;
import com.ibm.tools.utils.MongoDBHelper;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

public class UserDetailsDAOJsonImpl implements UserDetailsDAO {

	private static final String APP_USER_COLLECTION = "AppUserDetailsCollection";
	@Override
	public DAOResponse getAllUsers() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DAOResponse getUser(String userId) {
		BaseDAOResponse response = new BaseDAOResponse();
		
		DBCollection collection = MongoDBHelper
				.getCollection(APP_USER_COLLECTION);
		// Check if user is there or not
		BasicDBObject filter = new BasicDBObject("uid", userId);
		
		DBObject dbObject = collection.findOne(filter);
		if(dbObject!=null)
		{
			response.setStatus(BaseDAOResponse.STATUS_DAO_SUCCESS);
			response.setResponse(UserDetails.parseToObject(dbObject));
		}
		else
		{
			response.setStatus(BaseDAOResponse.STATUS_DAO_NO_RESULT);
		}
		return response;
	}

	@Override
	public DAOResponse saveUser(UserDetails userDetails) {
		boolean isValidEntry = true;
		BaseDAOResponse response = new BaseDAOResponse();
		if(userDetails!=null)
		{
		DBCollection collection = MongoDBHelper
				.getCollection(APP_USER_COLLECTION);
		// Check if same employee code exists or not
		BasicDBObject filter = new BasicDBObject("uid", userDetails.getUid());
		
		DBCursor cursor = collection.find(filter);
		if (cursor != null) {
			while (cursor.hasNext()) {
				cursor.next();
				isValidEntry = false;
				break;
			}
			cursor.close();
		}
		if (isValidEntry) {
			collection.save(userDetails.toDBObject());
			response.setStatus(BaseDAOResponse.STATUS_DAO_SUCCESS);
		}
		else
		{
			response.setStatus(BaseDAOResponse.STATUS_DAO_DATA_EXISTIS);
			response.setStatusText("Entry exists");
		}
		}
		else
		{
			response.setStatus(BaseDAOResponse.STATUS_DAO_INVALID_INPUT);
			response.setStatusText("Null/Invalid parameter passed");
		}
		return response;
	}

	@Override
	public DAOResponse updateUser(UserDetails userDetails) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DAOResponse deleteUser(String userId) {
		// TODO Auto-generated method stub
		return null;
	}

}
