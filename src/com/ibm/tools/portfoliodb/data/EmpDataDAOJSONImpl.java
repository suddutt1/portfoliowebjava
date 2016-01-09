package com.ibm.tools.portfoliodb.data;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.ibm.tools.dbacess.DAOResponse;
import com.ibm.tools.dbacess.EmployeeDataDAO;
import com.ibm.tools.utils.MongoDBHelper;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

public class EmpDataDAOJSONImpl implements EmployeeDataDAO{

	private static final String MGR_DETAILS_COLLECTION = "MgrDetailsCollection";
	private static final String EMP_DETAILS_COLLECTION = "EmpDetailsCollection";

	@Override
	public DAOResponse saveEmployeeData(EmployeeDetails empDetails) {
		boolean isValidEntry = true;
		BaseDAOResponse response = new BaseDAOResponse();
		if(empDetails!=null)
		{
		DBCollection collection = MongoDBHelper
				.getCollection(EMP_DETAILS_COLLECTION);
		// Check if same employee code exists or not
		BasicDBObject filter = new BasicDBObject("empId", empDetails.getEmpId());
		
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
			collection.save(empDetails.toDBObject());
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
	public DAOResponse updateEmployeeData(EmployeeDetails empDetails) {
		//We need the object id to be set by the called
		BasicDBObject filter = null;
		BaseDAOResponse response = new BaseDAOResponse();
		filter = new BasicDBObject("_id",empDetails.getId());
		DBCollection collection = MongoDBHelper
				.getCollection(EMP_DETAILS_COLLECTION);
		DBObject dbObject = collection.findOne(filter);
		if(dbObject!=null)
		{
			EmployeeDetails existingData = EmployeeDetails.parseToObject(dbObject);
			existingData.updateWith(empDetails);
			collection.update(filter, existingData.toDBObject());
			response.setStatus(BaseDAOResponse.STATUS_DAO_SUCCESS);
			response.setResponse(existingData);
		}
		else
		{
			response.setStatus(BaseDAOResponse.STATUS_DAO_INVALID_INPUT);
			response.setStatusText("Invalid employee details");
		}
		return response;
	}

	@Override
	public DAOResponse getEmployeeDetails(EmployeeDetails empDetails) {
		BasicDBObject filter = null;
		BaseDAOResponse response = new BaseDAOResponse();
		filter = new BasicDBObject("empId",empDetails.getEmpId());
		DBCollection collection = MongoDBHelper
				.getCollection(EMP_DETAILS_COLLECTION);
		DBObject dbObject = collection.findOne(filter);
		if(dbObject!=null)
		{
			response.setStatus(BaseDAOResponse.STATUS_DAO_SUCCESS);
			response.setResponse(EmployeeDetails.parseToObject(dbObject));
			response.setStatusText("Employee does not exist");
		}
		else
		{
			response.setStatus(BaseDAOResponse.STATUS_DAO_NO_RESULT);
			response.setStatusText("Employee does not exist");
		}
		return response;
	}

	@Override
	public DAOResponse getEmployeeList(Map<String, Object> searchOptions) {
		BasicDBObject filter = null;
		DBCursor cursor = null;
		BaseDAOResponse response = new BaseDAOResponse();
		List<EmployeeDetails> refDataList = new ArrayList<EmployeeDetails>();
		DBCollection collection = MongoDBHelper
				.getCollection(EMP_DETAILS_COLLECTION);
		if(searchOptions!=null)
		{
			//TODO: Fillup various options
			filter = new BasicDBObject();
		}
		if(filter==null)
		{
			cursor = collection.find();
		}
		else
		{
			cursor = collection.find(filter);
		}
		if (cursor != null) {
			while (cursor.hasNext()) {
				DBObject result = cursor.next();
				EmployeeDetails details  = EmployeeDetails.parseToObject(result);
				details.setDisplayFields();
				refDataList.add(details);
				
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
	public DAOResponse saveManagerData(ManagerDetails mgrDetails) {
		boolean isValidEntry = true;
		
		BaseDAOResponse response = new BaseDAOResponse();
		if(mgrDetails!=null)
		{
		DBCollection collection = MongoDBHelper
				.getCollection(MGR_DETAILS_COLLECTION);
		// Check if same employee code exists or not
		BasicDBObject filter = new BasicDBObject("empId", mgrDetails.getEmpId());
		filter.append("mgrType", mgrDetails.getMgrType());
		
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
			collection.save(mgrDetails.toDBObject());
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
	public DAOResponse getManagerList(String managerType) {
		BaseDAOResponse response = new BaseDAOResponse();
		List<ManagerDetails> refDataList = new ArrayList<ManagerDetails>();
		DBCollection collection = MongoDBHelper
				.getCollection(MGR_DETAILS_COLLECTION);
		BasicDBObject filter = new BasicDBObject("mgrType", managerType);
		
		DBCursor cursor = collection.find(filter);
		if (cursor != null) {
			while (cursor.hasNext()) {
				DBObject result = cursor.next();
				refDataList.add(ManagerDetails.parseToObject(result));
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
	public DAOResponse getAllManagerList() {
		BaseDAOResponse response = new BaseDAOResponse();
		List<ManagerDetails> refDataList = new ArrayList<ManagerDetails>();
		DBCollection collection = MongoDBHelper
				.getCollection(MGR_DETAILS_COLLECTION);
		
		DBCursor cursor = collection.find();
		if (cursor != null) {
			while (cursor.hasNext()) {
				DBObject result = cursor.next();
				refDataList.add(ManagerDetails.parseToObject(result));
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
	public DAOResponse updateManagerData(ManagerDetails mgrDetails) {
		// TODO Auto-generated method stub
		return null;
	}

}
