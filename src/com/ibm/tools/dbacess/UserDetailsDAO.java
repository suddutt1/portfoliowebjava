package com.ibm.tools.dbacess;

import com.ibm.tools.portfoliodb.data.UserDetails;

public interface UserDetailsDAO {

	DAOResponse getAllUsers();
	DAOResponse getUser(String userId);
	DAOResponse saveUser(UserDetails userDetails);
	DAOResponse updateUser(UserDetails userDetails);
	DAOResponse deleteUser(String userId);
	
}
