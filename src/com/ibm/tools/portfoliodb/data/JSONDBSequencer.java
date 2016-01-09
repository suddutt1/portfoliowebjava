package com.ibm.tools.portfoliodb.data;

import com.ibm.tools.utils.MongoDBHelper;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;

public class JSONDBSequencer {

	private static final String JSONDB_SEQUENCES_COLLECTION = "pfdata_seq";

	/**
	 * Static access. No object creation
	 */
	private JSONDBSequencer() {
		super();
	}

	public static long getNextSequenceNumber(String sequenceName) {
		if (sequenceName != null) {
			String seqId = sequenceName.trim();
			DBCollection collection = MongoDBHelper
					.getCollection(JSONDB_SEQUENCES_COLLECTION);
			DBObject filter = new BasicDBObject("seq_id", seqId);
			DBObject dbObject = collection.findOne(filter);

			if (dbObject == null) {
				// Create a new sequence
				BasicDBObject seqObject = new BasicDBObject("seq_id", seqId);
				seqObject.append("count", new Long(1));
				collection.save(seqObject);
				return 1;
			} else {
				Long count = (Long) dbObject.get("count");
				Long newCount = new Long(count.longValue() + 1);
				dbObject.put("count", newCount);
				collection.update(filter, dbObject);
				return newCount.longValue();
			}
		} else {
			return Long.MIN_VALUE;
		}
	}

}
