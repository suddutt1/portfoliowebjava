package com.ibm.tools.portfoliodb.dataloader;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.ibm.tools.dbacess.DAOResponse;
import com.ibm.tools.portfoliodb.data.UserDetails;
import com.ibm.tools.utils.DAOFactory;

public class RegisterUser {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void test() {
		UserDetails userDetails  = new UserDetails();
		userDetails.setEmailId("sududtt1@in.ibm.com");
		userDetails.setFirstName("Sudip");
		userDetails.setLastName("Dutta");
		userDetails.setUid("suddutt1");
		userDetails.setPassword("cnp4test");
		userDetails.setRole("admin");
		DAOResponse daoResp = DAOFactory.getUserDetailsDAO().saveUser(userDetails);
		assertNotNull(daoResp);
		assertEquals(daoResp.getStatus(),DAOResponse.STATUS_DAO_SUCCESS);
	}
	@Test
	public void addUser()
	{
		String[][] userDetailsList = {{ "776133744","LOKESH","GEHALOT","Lokesh Gehalot/India/IBM","lgehalot@in.ibm.com" },
				{ "081446744","BALKRISHNA","KETKAR","Balkrishna D Ketkar/India/IBM","baketkar@in.ibm.com" },
				{ "03259O744","Somnath","Khilari","Somnath Khilari/India/IBM","skhilari@in.ibm.com" },
				{ "07580D744","Tirumala Saiprasad"," Bogavarapu","Tirumala Bogavarapu/India/IBM","sai.bogavarapu@in.ibm.com" },
				{ "073618744","ARCHANA I","MAITRA","Archana I Maitra/India/IBM","armaitra@in.ibm.com" },
				{ "914919744","BALAJI","KOYYALAMUDI","Balaji Koyyalamudi/India/IBM","bkoyyala@in.ibm.com" },
				{ "032127744","GNANADEV ","BOPPANA","Gnanadev Boppana/India/IBM","gboppana@in.ibm.com" },
				{ "888681744","JENISH","GEORGE","Jenish George/India/IBM","jegeorge@in.ibm.com" },
				{ "503119744","MILIND N","SAWANT","Milind N Sawant/India/IBM","milind.sawant@in.ibm.com" },
				{ "912067744","MOHAN V","BORGAONKAR","Mohan V Borgaonkar/India/IBM","bvmohan@in.ibm.com" },
				{ "953130744","NAGESH D S","BABU","Nagesh Babu D Sundarraj/India/IBM","nagesh.babu@in.ibm.com" },
				{ "01299C744","Nandakumar","Sampathkumar","Nandakumar Sampathkumar/India/IBM","nandakumars@in.ibm.com" },
				{ "627068744","RAHUL","YADAV","Rahul Yadav/India/IBM","rahyadav@in.ibm.com" },
				{ "687977744","RAJ JOG","SINGH","Raj J Singh1/India/IBM","rjsingh1@in.ibm.com" },
				{ "00297A744","Rajesh","Rajalingam","Rajesh R Rajalingam1/India/IBM","rajeshrajalingam@in.ibm.com" },
				{ "042977744","SAPTAGIRISH","OLETI","Saptagirish Oleti/India/IBM","sapoleti@in.ibm.com" },
				{ "01528E744","TENKASALA SRINIVASA","RAO","Srinivasa Tenkasala/India/IBM","Srinivasa_Tenkasala@in.ibm.com" }};
		
		for(String[] userDetails:userDetailsList)
		{
			String emailId = "";
			String fName = "";
			String lName = "";
			String uid = "";
			String password = "";
			String role="manager";
			
		}

	}
	
}
