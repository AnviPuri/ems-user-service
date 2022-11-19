package com.ems.user.service;

import org.springframework.stereotype.Service;

import com.ems.user.dto.request.UserRequest;
import com.ems.user.dto.response.UserResponse;

@Service
public class CreateUserService {

	public UserResponse createUser(UserRequest userRequest) {

		UserResponse userResponse = new UserResponse();
		return userResponse;
	}

}
