package com.ibm.tools.portfoliodb.data;

import java.util.ArrayList;
import java.util.List;

import com.ibm.tools.dbacess.DAOResponse;
import com.ibm.tools.dbacess.ReferenceDataDAO;
import com.ibm.tools.utils.MongoDBHelper;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

public class RefDataDAOJSONImpl implements ReferenceDataDAO {

	private static final String REF_DATA_COLLECTION = "ReferenceDataCollection";

	@Override
	public DAOResponse getReferenceData(String refDataType) {
		DBCursor cursor = null;
		BaseDAOResponse response = new BaseDAOResponse();
		List<ReferenceData> refDataList = new ArrayList<ReferenceData>();
		DBCollection collection = MongoDBHelper
				.getCollection(REF_DATA_COLLECTION);
		if(refDataType!=null)
		{
			BasicDBObject filter = new BasicDBObject("type", refDataType);
			 cursor = collection.find(filter);
		}
		else
		{
			 cursor = collection.find();
		}
		
		if (cursor != null) {
			while (cursor.hasNext()) {
				DBObject result = cursor.next();
				refDataList.add(ReferenceData.parseToObject(result));
			}
			cursor.close();
			if(refDataList.size()>0)
			{
				response.setStatus(BaseDAOResponse.STATUS_DAO_SUCCESS);
				response.setStatusText("Data exists");
				response.setResponse(refDataList);
				
			}
			else
			{
				response.setStatus(BaseDAOResponse.STATUS_DAO_NO_RESULT);
				response.setStatusText("No data exists");
				
			}
		}
		else
		{
			response.setStatus(BaseDAOResponse.STATUS_DAO_NO_RESULT);
			response.setStatusText("No data exists");
		}
		return response;
	}

	@Override
	public DAOResponse addReferenceData(String refDataType, String code,
			String description) {
		boolean isValidEntry = true;
		BaseDAOResponse response = new BaseDAOResponse();
		DBCollection collection = MongoDBHelper
				.getCollection(REF_DATA_COLLECTION);
		// Check if same code exists or not
		BasicDBObject filter = new BasicDBObject("type", refDataType);
		filter.append("code", code);
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
			ReferenceData dataToSave = new ReferenceData();
			dataToSave.setCode(code);
			dataToSave.setType(refDataType);
			dataToSave.setDisplayValue(description);
			collection.save(dataToSave.toDBObject());
			response.setStatus(BaseDAOResponse.STATUS_DAO_SUCCESS);
		}
		else
		{
			response.setStatus(BaseDAOResponse.STATUS_DAO_DATA_EXISTIS);
			response.setStatusText("Entry exists");
		}
		return response;
	}

}
