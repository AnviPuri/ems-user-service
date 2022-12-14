package com.ems.user.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.GenericGenerator;

@Entity
public class User {

	@Id
	@GenericGenerator(name = "user_id", strategy = "com.ems.user.id.generator.UserIdGenerator")
	@GeneratedValue(generator = "user_id")
	@Column(name = "user_id")
	private String userId;

	@Column(name = "employee_id")
	private String employeeId;

	@Column(name = "user_type")
	private String userType;

	@Column(name = "first_name")
	private String firstName;

	@Column(name = "middle_name")
	private String middleName;

	@Column(name = "last_name")
	private String lastName;

	private String gender;

	@Column(name = "org_email")
	private String orgEmail;

	@Column(name = "personal_email")
	private String personalEmail;

	@Column(name = "country_code")
	private String countryCode;

	@Column(name = "phone_number")
	private String phoneNumber;

	@Column(name = "official_designation")
	private String officialDesignation;

	@Column(name = "date_of_joining")
	private long dateOfJoining;

	@Column(name = "date_of_birth")
	private long dateOfBirth;

	@Column(name = "blood_group")
	private String bloodGroup;

	@Column(name = "marital_status")
	private String maritalStatus;

	@OneToMany(mappedBy = "emergencyContactParent")
	private List<User> emergencyContacts;

	@ManyToOne(cascade = { CascadeType.ALL })
	@JoinColumn(name = "emergency_contact_parent_id")
	private User emergencyContactParent;

	private String relationship;

	private String status;

	@Column(name = "is_consent_accepted")
	private boolean isConsentAccepted;

	@Embedded
	private Audit audit;

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

	public List<User> getEmergencyContacts() {
		return emergencyContacts;
	}

	public void setEmergencyContacts(List<User> emergencyContacts) {
		this.emergencyContacts = emergencyContacts;
	}

	public User getEmergencyContactParent() {
		return emergencyContactParent;
	}

	public void setEmergencyContactParent(User emergencyContactParent) {
		this.emergencyContactParent = emergencyContactParent;
	}

	public String getRelationship() {
		return relationship;
	}

	public void setRelationship(String relationship) {
		this.relationship = relationship;
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

	public Audit getAudit() {
		return audit;
	}

	public void setAudit(Audit audit) {
		this.audit = audit;
	}
}
