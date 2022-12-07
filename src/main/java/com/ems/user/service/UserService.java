package com.ems.user.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.web.bind.annotation.RequestParam;

import com.ems.user.dto.request.AddressRequest;
import com.ems.user.dto.request.AddressUpdateRequest;
import com.ems.user.dto.request.EducationRequest;
import com.ems.user.dto.request.EducationUpdateRequest;
import com.ems.user.dto.request.UserRequest;
import com.ems.user.dto.request.UserUpdateRequest;
import com.ems.user.dto.response.AddressResponse;
import com.ems.user.dto.response.EducationResponse;
import com.ems.user.dto.response.UserResponse;
import com.ems.user.entity.Address;
import com.ems.user.entity.Education;
import com.ems.user.entity.User;

public interface UserService {

	public UserResponse createUser(UserRequest userRequest);

	public UserResponse updateUser(UserUpdateRequest userUpdateRequest, String userId);

	public boolean deleteUser(String userId);

	public UserResponse getByUserId(String userId);

	public HashMap<String, Object> getAllUsers(String userType, int pageNumber, int pageSize);

	public HashMap<String, Object> searchUsersByUserTypeAndFirstName(int pageNumber, int pageSize, String userType,
			String searchQuery);

	public List<UserResponse> createEmergencyUser(List<UserRequest> emergencyContactRequestList,
			User emergencyContactParent);

	public List<UserResponse> updateEmergencyUser(List<UserUpdateRequest> emergencyContactUpdateRequestList,
			User emergencyContactParent);

	public void deleteEmergencyContactByUser(User user);

	public List<UserResponse> getEmergencyContactByParentUser(User user);

	public List<AddressResponse> createAddress(List<AddressRequest> addressRequestList, User user);

	public List<AddressResponse> updateAddress(List<AddressUpdateRequest> addressUpdateRequestList, User user);

	public void deleteAddress(Address address);

	public void deleteAddressByUser(User user);

	public List<AddressResponse> getAddressByUser(User user);

	public List<EducationResponse> createEducation(List<EducationRequest> educationRequestList, User user);

	public List<EducationResponse> updateEducation(List<EducationUpdateRequest> educationUpdateRequestList, User user);

	public void deleteEducation(Education education);

	public void deleteEducationByUser(User user);

	public List<EducationResponse> getEducationByUser(User user);

}
