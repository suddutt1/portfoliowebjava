package com.ibm.tools.portfoliodb.reports;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.ibm.tools.portfoliodb.data.ProjAssignmentDetails;
import com.ibm.tools.utils.MongoDBHelper;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

public class ReportDAOJsonImpl implements ReportDAO, ReportOptions {

	private static final String PROJECTASSINMENT_COLLECTION = "ProjAssignmentCollection";

	@Override
	public List<ProjAssignmentDetails> getAllAssignments(
			Map<String, String> options) {
		// TODO Auto-generated method stub
		BasicDBObject filter = new BasicDBObject();
		DBCursor cursor = null;
		List<ProjAssignmentDetails> assignmentList = new ArrayList<ProjAssignmentDetails>();
		DBCollection collection = MongoDBHelper
				.getCollection(PROJECTASSINMENT_COLLECTION);
		if (options != null && options.containsKey(EXCLUDE_RELEASED_RESOURCES)) {
			filter.append("releasedFg", "N");
		}
		if(options !=null && options.containsKey(FILTER_PROJECT))
		{
			filter.append("projectId",new Long(options.get(FILTER_PROJECT)));
		}
		if(options !=null && options.containsKey(FILTER_UOM))
		{
			filter.append("projectId",new Long(options.get(FILTER_UOM)));
		}
		cursor = collection.find(filter);
		if (cursor != null) {
			while (cursor.hasNext()) {
				DBObject result = cursor.next();
				ProjAssignmentDetails rptData = ProjAssignmentDetails
						.parseToObject(result);
				assignmentList.add(rptData);
			}
			cursor.close();
		}
		return assignmentList;
	}

}
