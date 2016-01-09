package com.ibm.tools.portfoliodb.data;

import java.util.ArrayList;
import java.util.List;

import com.ibm.tools.dbacess.DAOResponse;
import com.ibm.tools.dbacess.UOMDataDAO;
import com.ibm.tools.utils.MongoDBHelper;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

public class UOMDataDAOJSONImpl implements UOMDataDAO {

	private static final String UOM_DETAILS_COLLECTION = "UOMDetailsCollection";
	@Override
	public DAOResponse addNewUOM(UOMDetails uomDetails) {
		// TODO Auto-generated method stub
		BaseDAOResponse response = new BaseDAOResponse();
		if(uomDetails!=null)
		{
			//Expecting UOM id not set
			boolean isValidEntry = true;
			uomDetails.setUomId(JSONDBSequencer.getNextSequenceNumber("uom_id_seq"));
			DBCollection collection = MongoDBHelper.getCollection(UOM_DETAILS_COLLECTION);
			//Cross checking if the id already matches or not
			BasicDBObject filter = new BasicDBObject("uomId",uomDetails.getUomId());
			DBCursor cursor = collection.find(filter);
			if(cursor!=null )
			{
				while(cursor.hasNext())
				{
					isValidEntry = false;
				}
				cursor.close();
			}
			if(isValidEntry)
			{
				collection.save(uomDetails.toDBObject());
				response.setStatus(DAOResponse.STATUS_DAO_SUCCESS);
				response.setStatusText("Save successful");
				response.setResponse(uomDetails);
			}
			else
			{
				response.setStatus(DAOResponse.STATUS_DAO_DATA_EXISTIS);
				response.setStatusText("Entry with same UOM id exists");
				response.setResponse(uomDetails);
			}
			
		}
		else
		{
			response.setStatus(DAOResponse.STATUS_DAO_INVALID_INPUT);
			response.setStatusText("Null object passed as input");
		}
		return response;
	}

	@Override
	public DAOResponse updateUOM(UOMDetails uomDtails) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DAOResponse getUOMDetails(UOMDetails uomDtails) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DAOResponse getUOMList(UOMDetails uomDtails) {
		List<UOMDetails> uomList = new ArrayList<UOMDetails>();
		//So far to check on the input 
		//TODO:Add filter using uomDetails input
		BaseDAOResponse response = new BaseDAOResponse();
		DBCursor cursor = MongoDBHelper.getCollection(UOM_DETAILS_COLLECTION).find();
		if(cursor!=null)
		{
			while(cursor.hasNext())
			{
				DBObject dbObject = cursor.next();
				UOMDetails uomInfo = UOMDetails.parseToObject(dbObject);
				uomInfo.setDisplayFields();
				uomList.add(uomInfo);
			}
			cursor.close();
			response.setStatus(DAOResponse.STATUS_DAO_SUCCESS);
			response.setResponse(uomList);
		}
		else
		{
			response.setStatus(DAOResponse.STATUS_DAO_NO_RESULT);
			response.setStatusText("No result");
		}
		return response;
	}

}
