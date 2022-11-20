package com.ems.user.mapper;

import com.ems.user.dto.request.UserRequest;
import com.ems.user.dto.response.UserResponse;
import com.ems.user.entity.User;

public class UserMapper {

	public static User userRequestToEntityMapper(UserRequest userRequest) {

		User user = new User();

		user.setEmployeeId(userRequest.getEmployeeId());
		user.setUserType(userRequest.getUserType());
		user.setFirstName(userRequest.getFirstName());
		user.setMiddleName(userRequest.getMiddleName());
		user.setLastName(userRequest.getLastName());
		user.setGender(userRequest.getGender());
		user.setOrgEmail(userRequest.getOrgEmail());
		user.setPersonalEmail(userRequest.getPersonalEmail());
		user.setCountryCode(userRequest.getCountryCode());
		user.setPhoneNumber(userRequest.getPhoneNumber());
		user.setOfficialDesignation(userRequest.getOfficialDesignation());
		user.setRelationship(userRequest.getRelationship());
		user.setConsentAccepted(userRequest.isConsentAccepted());
		user.setDateOfJoining(userRequest.getDateOfJoining());
		user.setDateOfBirth(userRequest.getDateOfBirth());
		user.setBloodGroup(userRequest.getBloodGroup());
		user.setMaritalStatus(userRequest.getMaritalStatus());
		user.setStatus(userRequest.getStatus());

		return user;
	}

	public static UserResponse userEntityToResponseMapper(User user) {

		UserResponse userResponse = new UserResponse();

		userResponse.setUserId(user.getUserId());
		userResponse.setEmployeeId(user.getEmployeeId());
		userResponse.setUserType(user.getUserType());
		userResponse.setFirstName(user.getFirstName());
		userResponse.setMiddleName(user.getMiddleName());
		userResponse.setLastName(user.getLastName());
		userResponse.setGender(user.getGender());
		userResponse.setOrgEmail(user.getOrgEmail());
		userResponse.setPersonalEmail(user.getPersonalEmail());
		userResponse.setCountryCode(user.getCountryCode());
		userResponse.setPhoneNumber(user.getPhoneNumber());
		userResponse.setOfficialDesignation(user.getOfficialDesignation());
		userResponse.setDateOfJoining(user.getDateOfJoining());
		userResponse.setDateOfBirth(user.getDateOfBirth());
		userResponse.setBloodGroup(user.getBloodGroup());
		userResponse.setMaritalStatus(user.getMaritalStatus());
		userResponse.setRelationship(user.getRelationship());
		userResponse.setStatus(user.getStatus());
		userResponse.setConsentAccepted(user.isConsentAccepted());

		return userResponse;
	}

}
