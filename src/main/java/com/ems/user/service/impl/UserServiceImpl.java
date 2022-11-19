package com.ems.user.service.impl;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ems.user.dto.request.AddressRequest;
import com.ems.user.dto.request.EducationRequest;
import com.ems.user.dto.request.UserRequest;
import com.ems.user.dto.response.AddressResponse;
import com.ems.user.dto.response.EducationResponse;
import com.ems.user.dto.response.UserResponse;
import com.ems.user.entity.User;
import com.ems.user.repo.AddressRepo;
import com.ems.user.repo.EducationRepo;
import com.ems.user.repo.UserRepo;
import com.ems.user.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepo userRepository;

	@Autowired
	private EducationRepo educationRepository;

	@Autowired
	private AddressRepo addressRepository;

	public UserResponse createUser(UserRequest userRequest) {

		// validate user request

		UserResponse userResponse = new UserResponse();
		List<AddressRequest> addressRequestList = userRequest.getAddressList();
		List<EducationRequest> educationRequestList = userRequest.getEducationList();
		List<UserRequest> emergencyContactList = userRequest.getEmergencyContactList();

		User user = new User();
		// make a data transfer class for user
		BeanUtils.copyProperties(userRequest, user);
		this.createAddress(addressRequestList);
		this.createEducation(educationRequestList);
		// make a method to create emergency user

		user = userRepository.save(user);

		return userResponse;
	}

	@Override
	public UserResponse updateUser(UserRequest userRequest) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserResponse deleteUser(UserRequest userRequest) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserResponse getByUserId(UserRequest userRequest) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<AddressResponse> createAddress(List<AddressRequest> addressRequestList) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<AddressResponse> updateAddress(List<AddressRequest> addressRequestList) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean deleteAddress(AddressRequest addressRequest) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<EducationResponse> createEducation(List<EducationRequest> educationRequestList) {
		// TODO Auto-generated method stub
		return null;
	}

}
