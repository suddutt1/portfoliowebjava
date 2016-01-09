package com.ibm.tools.portfoliodb.data;

import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;

import com.ibm.tools.dbacess.DAOResponse;
import com.ibm.tools.dbacess.ProjectDataDAO;
import com.ibm.tools.utils.MongoDBHelper;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

public class ProjectDataDAOJSONImpl implements ProjectDataDAO {

	private static final String PROJECT_COLLECTION = "ProjectCollection";
	private static final String PROJECTASSINMENT_COLLECTION = "ProjAssignmentCollection";

	@Override
	public DAOResponse addNewProject(ProjectDetails projDetails) {
		BaseDAOResponse response = new BaseDAOResponse();
		if (projDetails != null) {
			// Expecting UOM id not set
			boolean isValidEntry = true;
			projDetails.setProjectId(JSONDBSequencer
					.getNextSequenceNumber("proj_id_seq"));
			DBCollection collection = MongoDBHelper
					.getCollection(PROJECT_COLLECTION);
			// Cross checking if the id already matches or not
			BasicDBObject filter = new BasicDBObject("projectId",
					projDetails.getProjectId());
			DBCursor cursor = collection.find(filter);
			if (cursor != null) {
				while (cursor.hasNext()) {
					isValidEntry = false;
				}
				cursor.close();
			}
			if (isValidEntry) {
				collection.save(projDetails.toDBObject());
				response.setStatus(DAOResponse.STATUS_DAO_SUCCESS);
				response.setStatusText("Save successful");
				response.setResponse(projDetails);
			} else {
				response.setStatus(DAOResponse.STATUS_DAO_DATA_EXISTIS);
				response.setStatusText("Entry with same Project id exists");
				response.setResponse(projDetails);
			}

		} else {
			response.setStatus(DAOResponse.STATUS_DAO_INVALID_INPUT);
			response.setStatusText("Null object passed as input");
		}
		return response;
	}

	@Override
	public DAOResponse updateProject(ProjectDetails projDetails) {
		// TODO Auto-generated method stub
		// Updating the project based on hex string
		BaseDAOResponse response = new BaseDAOResponse();
		DBCollection collection = MongoDBHelper
				.getCollection(PROJECT_COLLECTION);
		if (projDetails != null && projDetails.getObjIdHexString() != null
				&& ObjectId.isValid(projDetails.getObjIdHexString())) {
			ObjectId id = new ObjectId(projDetails.getObjIdHexString());
			BasicDBObject filter =new BasicDBObject("_id",id);
			DBObject existingObj = collection.findOne(filter);
			if(existingObj!=null)
			{
				ProjectDetails existingProjDetails = ProjectDetails.parseToObject(existingObj);
				existingProjDetails.updateWith(projDetails);
				collection.update(filter, existingProjDetails.toDBObject());
				response.setStatus(DAOResponse.STATUS_DAO_SUCCESS);
				response.setResponse(existingProjDetails);
			}
			else
			{
				response.setStatus(DAOResponse.STATUS_DAO_INVALID_INPUT);
				response.setStatusText("Invalid object id specified");
			}
			
		} else {
			response.setStatus(DAOResponse.STATUS_DAO_INVALID_INPUT);
			response.setStatusText("Invalid object id specified");
		}
		return response;
	}

	@Override
	public DAOResponse getProjectDetails(ProjectDetails projDetails) {
		BaseDAOResponse response = new BaseDAOResponse();
		DBCollection collection = MongoDBHelper.getCollection(PROJECT_COLLECTION);
		DBObject existingData = null;
		if(projDetails!=null && projDetails.getId()!=null)
		{
			existingData = collection.findOne(new BasicDBObject("_id",projDetails.getId()));
		}
		if(existingData!=null)
		{
			response.setStatus(DAOResponse.STATUS_DAO_SUCCESS);
			response.setResponse(ProjectDetails.parseToObject(existingData));
		}
		else
		{
			response.setStatus(DAOResponse.STATUS_DAO_NO_RESULT);
			response.setStatusText("No data");
		}
		return response;
	}

	@Override
	public DAOResponse getProjectList(ProjectDetails projDetails) {
		DBCursor cursor = null;
		List<ProjectDetails> projList = new ArrayList<ProjectDetails>();
		BaseDAOResponse response = new BaseDAOResponse();
		if (projDetails != null) {
			// TODO: Add filter criteria here
		} else {
			// Get all the projects including the deleted
			cursor = MongoDBHelper.getCollection(PROJECT_COLLECTION).find();
		}
		if (cursor != null) {
			while (cursor.hasNext()) {
				DBObject dbObject = cursor.next();
				ProjectDetails projInfo = ProjectDetails
						.parseToObject(dbObject);
				projInfo.setDisplayFields();
				projList.add(projInfo);
			}
			cursor.close();
			response.setStatus(DAOResponse.STATUS_DAO_SUCCESS);
			response.setResponse(projList);
		} else {
			response.setStatus(DAOResponse.STATUS_DAO_NO_RESULT);
			response.setStatusText("No result");
		}
		return response;
	}

	@Override
	public DAOResponse deleteProject(ProjectDetails projDetails) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DAOResponse addNewAssignment(ProjAssignmentDetails projAssignment) {
		BaseDAOResponse response = new BaseDAOResponse();
		if(projAssignment!=null)
		{
			DBCollection collection = MongoDBHelper.getCollection(PROJECTASSINMENT_COLLECTION);
			collection.save(projAssignment.toDBObject());
			response.setStatus(DAOResponse.STATUS_DAO_SUCCESS);
		}
		else
		{
			response.setStatus(DAOResponse.STATUS_DAO_INVALID_INPUT);
			response.setStatusText("Invalid null input to save");
		}
		return response;
	}

	@Override
	public DAOResponse updateAssignment(ProjAssignmentDetails projAssignment) {
		BaseDAOResponse response = new BaseDAOResponse();
		DBCollection collection = MongoDBHelper
				.getCollection(PROJECTASSINMENT_COLLECTION);
		if (projAssignment != null && projAssignment.getObjIdHexString() != null
				&& ObjectId.isValid(projAssignment.getObjIdHexString())) {
			ObjectId id = new ObjectId(projAssignment.getObjIdHexString());
			BasicDBObject filter =new BasicDBObject("_id",id);
			DBObject existingObj = collection.findOne(filter);
			if(existingObj!=null)
			{
				ProjAssignmentDetails existingProjDetails = ProjAssignmentDetails.parseToObject(existingObj);
				existingProjDetails.updateWith(projAssignment);
				collection.update(filter, existingProjDetails.toDBObject());
				response.setStatus(DAOResponse.STATUS_DAO_SUCCESS);
				response.setResponse(existingProjDetails);
			}
			else
			{
				response.setStatus(DAOResponse.STATUS_DAO_INVALID_INPUT);
				response.setStatusText("Invalid object id specified");
			}
			
		} else {
			response.setStatus(DAOResponse.STATUS_DAO_INVALID_INPUT);
			response.setStatusText("Invalid object id specified");
		}
		return response;
	}

	@Override
	public DAOResponse getAssignmentDetails(ProjAssignmentDetails projAssignment) {
		BaseDAOResponse response = new BaseDAOResponse();
		DBCollection collection = MongoDBHelper.getCollection(PROJECTASSINMENT_COLLECTION);
		DBObject existingData = null;
		if(projAssignment!=null && projAssignment.getId()!=null)
		{
			existingData = collection.findOne(new BasicDBObject("_id",projAssignment.getId()));
		}
		if(existingData!=null)
		{
			response.setStatus(DAOResponse.STATUS_DAO_SUCCESS);
			response.setResponse(ProjAssignmentDetails.parseToObject(existingData));
		}
		else
		{
			response.setStatus(DAOResponse.STATUS_DAO_NO_RESULT);
			response.setStatusText("No data");
		}
		return response;
	}

	@Override
	public DAOResponse getAssignmentList(ProjAssignmentDetails projAssignment) {
		BaseDAOResponse response = new BaseDAOResponse();
		DBCollection collection = MongoDBHelper.getCollection(PROJECTASSINMENT_COLLECTION);
		//Not applying any filteration
		DBCursor dbCursor = collection.find();
		if(dbCursor!=null)
		{
			List<ProjAssignmentDetails> returnList = new ArrayList<ProjAssignmentDetails>();
			while(dbCursor.hasNext())
			{
				ProjAssignmentDetails assignmentData = ProjAssignmentDetails.parseToObject(dbCursor.next());
				assignmentData.setDisplayFields();
			}
			dbCursor.close();
			response.setStatus(DAOResponse.STATUS_DAO_SUCCESS);
			response.setResponse(returnList);
		}
		else
		{
			response.setStatus(DAOResponse.STATUS_DAO_NO_RESULT);
			response.setStatusText("No data found");
		}
		return response;
	}

}
