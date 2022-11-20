package com.ems.user.mapper;

import com.ems.user.dto.request.EducationRequest;
import com.ems.user.dto.response.EducationResponse;
import com.ems.user.entity.Education;

public class EducationMapper {

	public static Education educationRequestToEntityMapper(EducationRequest educationRequest) {

		Education education = new Education();

		education.setDegreeName(educationRequest.getDegreeName());
		education.setSpecialisationName(educationRequest.getSpecialisationName());
		education.setInstitutionName(educationRequest.getInstitutionName());
		education.setStartDate(educationRequest.getStartDate());
		education.setEndDate(educationRequest.getEndDate());
		education.setPercentage(educationRequest.getPercentage());

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
