package com.ems.user.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ems.user.dto.request.UserRequest;
import com.ems.user.dto.response.UserResponse;
import com.ems.user.service.UserService;

@RestController
@RequestMapping("/api/user")
public class UserController {

	// User controller --> user service --> user,address, education repos
	// (create,update,delete through user)

	@Autowired
	private UserService userServiceImpl;
	
	@GetMapping(value = "/test", produces = "application/json")
	public String testApi() {

		return "TEST SUCCEEDED";
	}

	@PostMapping(produces = "application/json")
	public UserResponse createUser(@Valid @RequestBody UserRequest userRequest) {

		return userServiceImpl.createUser(userRequest);
	}

}
