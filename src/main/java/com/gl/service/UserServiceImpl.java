package com.gl.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gl.config.JwtProvider;
import com.gl.model.User;
import com.gl.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private JwtProvider jwtProvider;
	
	@Override
	public User findUserByJwtToken(String jwt) throws Exception {
		// TODO Auto-generated method stub
		String email = jwtProvider.getEmailFromJwtToken(jwt);
		User user = findUserByEmail(email);
		return user;
	}

	@Override
	public User findUserByEmail(String email) throws Exception {
		// TODO Auto-generated method stub
		User user = userRepository.findByEmail(email);
		if(user == null) {
			throw new Exception("user not found");
		}
		return user;
	}

}
