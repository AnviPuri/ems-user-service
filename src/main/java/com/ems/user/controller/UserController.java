package com.ems.user.controller;

import java.util.HashMap;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ems.user.dto.request.UserRequest;
import com.ems.user.dto.request.UserUpdateRequest;
import com.ems.user.dto.response.UserResponse;
import com.ems.user.service.UserService;

@RestController
@RequestMapping("/api/user")
public class UserController {

	@Autowired
	private UserService userServiceImpl;

	@PostMapping(produces = "application/json")
	public UserResponse createUser(@Valid @RequestBody UserRequest userRequest) {

		return userServiceImpl.createUser(userRequest);
	}

	@PutMapping(value = "/{userId}", produces = "application/json")
	public UserResponse updateUser(@Valid @RequestBody UserUpdateRequest userUpdateRequest,
			@PathVariable String userId) {

		return userServiceImpl.updateUser(userUpdateRequest, userId);
	}

	@DeleteMapping(value = "/{userId}", produces = "application/json")
	public boolean deleteUser(@PathVariable String userId) {

		return userServiceImpl.deleteUser(userId);
	}

	@GetMapping(value = "/{userId}", produces = "application/json")
	public UserResponse getByUserId(@PathVariable String userId) {

		return userServiceImpl.getByUserId(userId);
	}

	@GetMapping(value = "/all", produces = "application/json")
	public HashMap<String, Object> getAllUsersByUserType(
			@RequestParam(value = "pageNumber", defaultValue = "1") int pageNumber,
			@RequestParam(value = "pageSize", defaultValue = "10") int pageSize,
			@RequestParam(value = "userType", defaultValue = "EMPLOYEE") String userType) {

		return userServiceImpl.getAllUsers(userType, pageNumber, pageSize);
	}

	@GetMapping(value = "/all/search", produces = "application/json")
	public HashMap<String, Object> searchUsersByUserTypeAndFirstName(
			@RequestParam(value = "pageNumber", defaultValue = "1") int pageNumber,
			@RequestParam(value = "pageSize", defaultValue = "10") int pageSize,
			@RequestParam(value = "userType", defaultValue = "EMPLOYEE") String userType,
			@RequestParam(value = "searchQuery") String searchQuery) {

		return userServiceImpl.searchUsersByUserTypeAndFirstName(pageNumber, pageSize, userType, searchQuery);
	}

}
