package com.ems.user.dto.builder;

import java.util.function.Consumer;

import com.ems.user.dto.request.UserRequest;

public class UserRequestBuilder {

	public String employeeId;
	public String userType;
	public String firstName;
	public String middleName;
	public String lastName;
	public String gender;
	public String orgEmail;
	public String personalEmail;
	public String countryCode;
	public String phoneNumber;
	public String officialDesignation;
	public long dateOfJoining;
	public long dateOfBirth;
	public String bloodGroup;
	public String maritalStatus;
	public String relationship;
	public String status;
	public boolean isConsentAccepted;

	public UserRequestBuilder with(Consumer<UserRequestBuilder> builderFunction) {
		builderFunction.accept(this);
		return this;
	}

	public UserRequest createUserRequest() {
		return new UserRequest(employeeId, userType, firstName, middleName, lastName, gender, orgEmail, personalEmail,
				countryCode, phoneNumber, officialDesignation, dateOfJoining, dateOfBirth, bloodGroup, maritalStatus,
				null, relationship, null, null, status, isConsentAccepted);
	}
}
