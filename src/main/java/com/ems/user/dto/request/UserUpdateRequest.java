package com.ems.user.dto.request;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotBlank;

import com.sun.istack.NotNull;

public class UserUpdateRequest {

	private String userId = "";

	private String employeeId = "";

	@NotNull
	@NotBlank(message = "User Type cant'be empty")
	private String userType;

	@NotNull
	@NotBlank(message = "First Name cant'be empty")
	private String firstName;

	private String middleName = "";

	private String lastName = "";

	private String gender = "";

	// add validation check on if email id is valid?
	private String orgEmail = "";

	@NotNull
	@NotBlank(message = "Personal Email cant'be empty")
	private String personalEmail;

	// add validation check on phone Number if it is valid?
	@NotNull
	@NotBlank(message = "Country Code cant'be empty")
	private String countryCode;

	@NotNull
	@NotBlank(message = "Phone Number cant'be empty")
	private String phoneNumber;

	private String officialDesignation = "";

	private long dateOfJoining = 0;

	private long dateOfBirth = 0;

	private String bloodGroup = "";

	private String maritalStatus = "";

	private List<UserUpdateRequest> emergencyContactList = new ArrayList<>();

	@NotNull
	@NotBlank(message = "Relationship cant'be empty")
	private String relationship;

	private List<AddressUpdateRequest> addressList = new ArrayList<>();

	private List<EducationUpdateRequest> educationList = new ArrayList<>();

	@NotNull
	@NotBlank(message = "Status cant'be empty")
	private String status;

	private boolean isConsentAccepted = false;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getOrgEmail() {
		return orgEmail;
	}

	public void setOrgEmail(String orgEmail) {
		this.orgEmail = orgEmail;
	}

	public String getPersonalEmail() {
		return personalEmail;
	}

	public void setPersonalEmail(String personalEmail) {
		this.personalEmail = personalEmail;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getOfficialDesignation() {
		return officialDesignation;
	}

	public void setOfficialDesignation(String officialDesignation) {
		this.officialDesignation = officialDesignation;
	}

	public long getDateOfJoining() {
		return dateOfJoining;
	}

	public void setDateOfJoining(long dateOfJoining) {
		this.dateOfJoining = dateOfJoining;
	}

	public long getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(long dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getBloodGroup() {
		return bloodGroup;
	}

	public void setBloodGroup(String bloodGroup) {
		this.bloodGroup = bloodGroup;
	}

	public String getMaritalStatus() {
		return maritalStatus;
	}

	public void setMaritalStatus(String maritalStatus) {
		this.maritalStatus = maritalStatus;
	}

	public List<UserUpdateRequest> getEmergencyContactList() {
		return emergencyContactList;
	}

	public void setEmergencyContactList(List<UserUpdateRequest> emergencyContactList) {
		this.emergencyContactList = emergencyContactList;
	}

	public String getRelationship() {
		return relationship;
	}

	public void setRelationship(String relationship) {
		this.relationship = relationship;
	}

	public List<AddressUpdateRequest> getAddressList() {
		return addressList;
	}

	public void setAddressList(List<AddressUpdateRequest> addressList) {
		this.addressList = addressList;
	}

	public List<EducationUpdateRequest> getEducationList() {
		return educationList;
	}

	public void setEducationList(List<EducationUpdateRequest> educationList) {
		this.educationList = educationList;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public boolean isConsentAccepted() {
		return isConsentAccepted;
	}

	public void setConsentAccepted(boolean isConsentAccepted) {
		this.isConsentAccepted = isConsentAccepted;
	}

}
