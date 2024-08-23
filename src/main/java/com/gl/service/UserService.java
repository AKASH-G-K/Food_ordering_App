package com.gl.service;

import com.gl.model.User;

public interface UserService {

	public User findUserByJwtToken(String jwt) throws Exception;
	
	public User findUserByEmail(String email) throws Exception;
}
