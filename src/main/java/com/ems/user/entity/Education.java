package com.ems.user.entity;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.GenericGenerator;

@Entity
public class Education {

	@Id
	@GenericGenerator(name = "education_id", strategy = "com.ems.user.id.generator.EducationIdGenerator")
	@GeneratedValue(generator = "education_id")
	@Column(name = "education_id")
	private String educationId;

	@Column(name = "degree_name")
	private String degreeName;

	@Column(name = "specialisation_name")
	private String specialisationName;

	@Column(name = "institution_name")
	private String institutionName;

	@Column(name = "start_date")
	private long startDate;

	@Column(name = "end_Date")
	private long endDate;

	private String percentage;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private User user;

	@Embedded
	private Audit audit;

	public String getEducationId() {
		return educationId;
	}

	public void setEducationId(String educationId) {
		this.educationId = educationId;
	}

	public String getDegreeName() {
		return degreeName;
	}

	public void setDegreeName(String degreeName) {
		this.degreeName = degreeName;
	}

	public String getSpecialisationName() {
		return specialisationName;
	}

	public void setSpecialisationName(String specialisationName) {
		this.specialisationName = specialisationName;
	}

	public String getInstitutionName() {
		return institutionName;
	}

	public void setInstitutionName(String institutionName) {
		this.institutionName = institutionName;
	}

	public long getStartDate() {
		return startDate;
	}

	public void setStartDate(long startDate) {
		this.startDate = startDate;
	}

	public long getEndDate() {
		return endDate;
	}

	public void setEndDate(long endDate) {
		this.endDate = endDate;
	}

	public String getPercentage() {
		return percentage;
	}

	public void setPercentage(String percentage) {
		this.percentage = percentage;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Audit getAudit() {
		return audit;
	}

	public void setAudit(Audit audit) {
		this.audit = audit;
	}

}
