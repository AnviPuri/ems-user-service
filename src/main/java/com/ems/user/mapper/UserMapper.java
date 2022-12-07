package com.ems.user.mapper;

import com.ems.user.dto.request.UserRequest;
import com.ems.user.dto.request.UserUpdateRequest;
import com.ems.user.dto.response.UserResponse;
import com.ems.user.entity.User;
import com.ems.user.utility.AuditUtility;
import com.ems.user.utility.EmsUtility;

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
		user.setAudit(AuditUtility.createApiAuditBuild());
		return user;
	}

	public static User userUpdateRequestToEntityMapper(UserUpdateRequest userUpdateRequest, User user) {

		user.setUserId(userUpdateRequest.getUserId());
		user.setEmployeeId(userUpdateRequest.getEmployeeId());
		user.setUserType(userUpdateRequest.getUserType());
		user.setFirstName(userUpdateRequest.getFirstName());
		user.setMiddleName(userUpdateRequest.getMiddleName());
		user.setLastName(userUpdateRequest.getLastName());
		user.setGender(userUpdateRequest.getGender());
		user.setOrgEmail(userUpdateRequest.getOrgEmail());
		user.setPersonalEmail(userUpdateRequest.getPersonalEmail());
		user.setCountryCode(userUpdateRequest.getCountryCode());
		user.setPhoneNumber(userUpdateRequest.getPhoneNumber());
		user.setOfficialDesignation(userUpdateRequest.getOfficialDesignation());
		user.setRelationship(userUpdateRequest.getRelationship());
		user.setConsentAccepted(userUpdateRequest.isConsentAccepted());
		user.setDateOfJoining(userUpdateRequest.getDateOfJoining());
		user.setDateOfBirth(userUpdateRequest.getDateOfBirth());
		user.setBloodGroup(userUpdateRequest.getBloodGroup());
		user.setMaritalStatus(userUpdateRequest.getMaritalStatus());
		user.setStatus(userUpdateRequest.getStatus());
		user.setAudit(AuditUtility.updateApiAuditBuild(user.getAudit()));
		return user;
	}

	public static User emergencyContactUpdateRequestToEntityMapper(UserUpdateRequest emergencyContactUpdateRequest,
			User emergencyContactParent) {

		User emergencyContactUpdate = new User();
		if (!EmsUtility.isNullOrEmpty(emergencyContactUpdate.getUserId())) {
			emergencyContactUpdate.setUserId(emergencyContactUpdateRequest.getUserId());
		} else {
			emergencyContactUpdate.setUserId("");
		}
		emergencyContactUpdate.setEmployeeId(emergencyContactUpdateRequest.getEmployeeId());
		emergencyContactUpdate.setUserType(emergencyContactUpdateRequest.getUserType());
		emergencyContactUpdate.setFirstName(emergencyContactUpdateRequest.getFirstName());
		emergencyContactUpdate.setMiddleName(emergencyContactUpdateRequest.getMiddleName());
		emergencyContactUpdate.setLastName(emergencyContactUpdateRequest.getLastName());
		emergencyContactUpdate.setGender(emergencyContactUpdateRequest.getGender());
		emergencyContactUpdate.setOrgEmail(emergencyContactUpdateRequest.getOrgEmail());
		emergencyContactUpdate.setPersonalEmail(emergencyContactUpdateRequest.getPersonalEmail());
		emergencyContactUpdate.setCountryCode(emergencyContactUpdateRequest.getCountryCode());
		emergencyContactUpdate.setPhoneNumber(emergencyContactUpdateRequest.getPhoneNumber());
		emergencyContactUpdate.setOfficialDesignation(emergencyContactUpdateRequest.getOfficialDesignation());
		emergencyContactUpdate.setRelationship(emergencyContactUpdateRequest.getRelationship());
		emergencyContactUpdate.setConsentAccepted(emergencyContactUpdateRequest.isConsentAccepted());
		emergencyContactUpdate.setDateOfJoining(emergencyContactUpdateRequest.getDateOfJoining());
		emergencyContactUpdate.setDateOfBirth(emergencyContactUpdateRequest.getDateOfBirth());
		emergencyContactUpdate.setBloodGroup(emergencyContactUpdateRequest.getBloodGroup());
		emergencyContactUpdate.setMaritalStatus(emergencyContactUpdateRequest.getMaritalStatus());
		emergencyContactUpdate.setStatus(emergencyContactUpdateRequest.getStatus());
		emergencyContactUpdate.setEmergencyContactParent(emergencyContactParent);
		return emergencyContactUpdate;
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
