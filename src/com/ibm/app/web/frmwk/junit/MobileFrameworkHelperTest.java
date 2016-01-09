package com.ibm.app.web.frmwk.junit;

import static org.junit.Assert.*;

import org.junit.Test;

import com.ibm.app.web.frmwk.MobileFrameworkHelper;
import com.ibm.app.web.frmwk.bean.ActionConfigurations;
import com.ibm.tools.portfoliodb.data.UserDetails;
import com.ibm.tools.utils.MongoDBHelper;
import com.mongodb.DBCollection;

public class MobileFrameworkHelperTest {

	public void test() {
		ActionConfigurations configs = MobileFrameworkHelper
				.loadActionConfigs(new String[] { "com.ibm.mytelco.action.LoginAction" });
		assertNotNull(configs);
		System.out.println(configs);
	}

	@Test
	public void testDB()
	{
		DBCollection myCollection = MongoDBHelper.getCollection("test");
		UserDetails userDetails = new UserDetails();
		userDetails.setEmailId("Sudip@gamil.com");
		
		myCollection.save(userDetails.toDBObject());
		
	}
}
