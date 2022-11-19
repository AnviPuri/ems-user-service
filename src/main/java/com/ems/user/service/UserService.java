package com.ems.user.service;

import java.util.List;

import com.ems.user.dto.request.AddressRequest;
import com.ems.user.dto.request.EducationRequest;
import com.ems.user.dto.request.UserRequest;
import com.ems.user.dto.response.AddressResponse;
import com.ems.user.dto.response.EducationResponse;
import com.ems.user.dto.response.UserResponse;

public interface UserService {

	public UserResponse createUser(UserRequest userRequest);

	public UserResponse updateUser(UserRequest userRequest);

	public UserResponse deleteUser(UserRequest userRequest);

	public UserResponse getByUserId(UserRequest userRequest);

	public List<AddressResponse> createAddress(List<AddressRequest> addressRequestList);
	
	public List<AddressResponse> updateAddress(List<AddressRequest> addressRequestList);
	
	public boolean deleteAddress(AddressRequest addressRequest);
	
	public List<EducationResponse> createEducation(List<EducationRequest> educationRequestList);

}
