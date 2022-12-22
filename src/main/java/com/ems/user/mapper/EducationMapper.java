package com.ems.user.mapper;

import com.ems.user.dto.request.EducationRequest;
import com.ems.user.dto.request.EducationUpdateRequest;
import com.ems.user.dto.response.EducationResponse;
import com.ems.user.entity.Education;
import com.ems.user.entity.User;
import com.ems.user.utility.AuditUtility;

import ems.utility.util.EmsUtility;

public class EducationMapper {

	public static Education educationRequestToEntityMapper(EducationRequest educationRequest, User user) {

		Education education = new Education();
		education.setDegreeName(educationRequest.getDegreeName());
		education.setSpecialisationName(educationRequest.getSpecialisationName());
		education.setInstitutionName(educationRequest.getInstitutionName());
		education.setStartDate(educationRequest.getStartDate());
		education.setEndDate(educationRequest.getEndDate());
		education.setPercentage(educationRequest.getPercentage());
		education.setAudit(AuditUtility.createApiAuditBuild());
		education.setUser(user);
		return education;
	}

	public static Education educationUpdateRequestToEntityMapper(EducationUpdateRequest educationUpdateRequest,
			User user) {

		Education education = new Education();
		if (!EmsUtility.isNullOrEmpty(educationUpdateRequest.getEducationId())) {
			education.setEducationId(educationUpdateRequest.getEducationId());
		} else {
			education.setEducationId("");
		}
		education.setDegreeName(educationUpdateRequest.getDegreeName());
		education.setSpecialisationName(educationUpdateRequest.getSpecialisationName());
		education.setInstitutionName(educationUpdateRequest.getInstitutionName());
		education.setStartDate(educationUpdateRequest.getStartDate());
		education.setEndDate(educationUpdateRequest.getEndDate());
		education.setPercentage(educationUpdateRequest.getPercentage());
		education.setUser(user);
		return education;
	}

	public static EducationResponse educationEntityToResponseMapper(Education education) {

		EducationResponse educationResponse = new EducationResponse();
		educationResponse.setEducationId(education.getEducationId());
		educationResponse.setDegreeName(education.getDegreeName());
		educationResponse.setSpecialisationName(education.getSpecialisationName());
		educationResponse.setInstitutionName(education.getInstitutionName());
		educationResponse.setStartDate(education.getStartDate());
		educationResponse.setEndDate(education.getEndDate());
		educationResponse.setPercentage(education.getPercentage());
		return educationResponse;
	}

}
