package com.ems.user.dto.request;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotBlank;

import com.sun.istack.NotNull;

public class UserRequest {

	// TO DO - ADD VALIDATION FOR FIELDS WITH SET VALUES
	// TO DO - ADD VALIDATION CHECK FOR REQUIRED FIELDS

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

	private List<UserRequest> emergencyContactList = new ArrayList<>();

	@NotNull
	@NotBlank(message = "Relationship cant'be empty")
	private String relationship;

	private List<AddressRequest> addressList = new ArrayList<>();

	private List<EducationRequest> educationList = new ArrayList<>();

	@NotNull
	@NotBlank(message = "Status cant'be empty")
	private String status;

	private boolean isConsentAccepted = false;

	public UserRequest() {

	}

	public UserRequest(String employeeId, @NotBlank(message = "User Type cant'be empty") String userType,
			@NotBlank(message = "First Name cant'be empty") String firstName, String middleName, String lastName,
			String gender, String orgEmail, @NotBlank(message = "Personal Email cant'be empty") String personalEmail,
			@NotBlank(message = "Country Code cant'be empty") String countryCode,
			@NotBlank(message = "Phone Number cant'be empty") String phoneNumber, String officialDesignation,
			long dateOfJoining, long dateOfBirth, String bloodGroup, String maritalStatus,
			List<UserRequest> emergencyContactList,
			@NotBlank(message = "Relationship cant'be empty") String relationship, List<AddressRequest> addressList,
			List<EducationRequest> educationList, @NotBlank(message = "Status cant'be empty") String status,
			boolean isConsentAccepted) {
		super();
		this.employeeId = employeeId;
		this.userType = userType;
		this.firstName = firstName;
		this.middleName = middleName;
		this.lastName = lastName;
		this.gender = gender;
		this.orgEmail = orgEmail;
		this.personalEmail = personalEmail;
		this.countryCode = countryCode;
		this.phoneNumber = phoneNumber;
		this.officialDesignation = officialDesignation;
		this.dateOfJoining = dateOfJoining;
		this.dateOfBirth = dateOfBirth;
		this.bloodGroup = bloodGroup;
		this.maritalStatus = maritalStatus;
		this.emergencyContactList = emergencyContactList;
		this.relationship = relationship;
		this.addressList = addressList;
		this.educationList = educationList;
		this.status = status;
		this.isConsentAccepted = isConsentAccepted;
	}

	@Override
	public String toString() {
		return "UserRequest [employeeId=" + employeeId + ", userType=" + userType + ", firstName=" + firstName
				+ ", middleName=" + middleName + ", lastName=" + lastName + ", gender=" + gender + ", orgEmail="
				+ orgEmail + ", personalEmail=" + personalEmail + ", countryCode=" + countryCode + ", phoneNumber="
				+ phoneNumber + ", officialDesignation=" + officialDesignation + ", dateOfJoining=" + dateOfJoining
				+ ", dateOfBirth=" + dateOfBirth + ", bloodGroup=" + bloodGroup + ", maritalStatus=" + maritalStatus
				+ ", emergencyContactList=" + emergencyContactList + ", relationship=" + relationship + ", addressList="
				+ addressList + ", educationList=" + educationList + ", status=" + status + ", isConsentAccepted="
				+ isConsentAccepted + "]";
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

	public List<UserRequest> getEmergencyContactList() {
		return emergencyContactList;
	}

	public void setEmergencyContactList(List<UserRequest> emergencyContactList) {
		this.emergencyContactList = emergencyContactList;
	}

	public String getRelationship() {
		return relationship;
	}

	public void setRelationship(String relationship) {
		this.relationship = relationship;
	}

	public List<AddressRequest> getAddressList() {
		return addressList;
	}

	public void setAddressList(List<AddressRequest> addressList) {
		this.addressList = addressList;
	}

	public List<EducationRequest> getEducationList() {
		return educationList;
	}

	public void setEducationList(List<EducationRequest> educationList) {
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
