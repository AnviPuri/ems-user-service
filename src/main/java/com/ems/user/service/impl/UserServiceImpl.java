package com.ems.user.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ems.user.dto.request.AddressRequest;
import com.ems.user.dto.request.EducationRequest;
import com.ems.user.dto.request.UserRequest;
import com.ems.user.dto.response.AddressResponse;
import com.ems.user.dto.response.EducationResponse;
import com.ems.user.dto.response.UserResponse;
import com.ems.user.entity.Address;
import com.ems.user.entity.Education;
import com.ems.user.entity.User;
import com.ems.user.mapper.AddressMapper;
import com.ems.user.mapper.EducationMapper;
import com.ems.user.mapper.UserMapper;
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

		List<AddressRequest> addressRequestList = userRequest.getAddressList();
		List<EducationRequest> educationRequestList = userRequest.getEducationList();
		List<UserRequest> emergencyContactRequestList = userRequest.getEmergencyContactList();

		User user = new User();
		user = UserMapper.userRequestToEntityMapper(userRequest);
		user = userRepository.save(user);

		List<AddressResponse> addressResponseList = this.createAddress(addressRequestList);
		List<EducationResponse> educationResponseList = this.createEducation(educationRequestList);
		List<UserResponse> emergencyContactResponseList = this.createEmergencyUser(emergencyContactRequestList);

		UserResponse userResponse = new UserResponse();
		userResponse = UserMapper.userEntityToResponseMapper(user);
		userResponse.setAddressResponseList(addressResponseList);
		userResponse.setEducationResponseList(educationResponseList);
		userResponse.setEmergencyContactResponseList(emergencyContactResponseList);

		return userResponse;
	}

	public List<UserResponse> createEmergencyUser(List<UserRequest> userRequestList) {

		List<UserResponse> userResponseList = new ArrayList<>();

		if (!userResponseList.isEmpty()) {
			for (UserRequest userRequest : userRequestList) {

				// validate emergency user request

				User user = new User();
				user = UserMapper.userRequestToEntityMapper(userRequest);
				user = userRepository.save(user);

				UserResponse userResponse = new UserResponse();
				userResponse = UserMapper.userEntityToResponseMapper(user);
				userResponseList.add(userResponse);
			}
		}
		return userResponseList;
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

		List<AddressResponse> addressResponseList = new ArrayList<>();

		if (!addressRequestList.isEmpty()) {
			for (AddressRequest addressRequest : addressRequestList) {

				// validate address request

				Address address = new Address();
				address = AddressMapper.addressRequestToEntityMapper(addressRequest);
				address = addressRepository.save(address);

				AddressResponse addressResponse = new AddressResponse();
				addressResponse = AddressMapper.addressEntityToResponseMapper(address);
				addressResponseList.add(addressResponse);
			}
		}
		return addressResponseList;
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

		List<EducationResponse> educationResponseList = new ArrayList<>();

		if (!educationRequestList.isEmpty()) {
			for (EducationRequest educationRequest : educationRequestList) {

				// validate education request

				Education education = new Education();
				education = EducationMapper.educationRequestToEntityMapper(educationRequest);
				education = educationRepository.save(education);

				EducationResponse educationResponse = new EducationResponse();
				educationResponse = EducationMapper.educationEntityToResponseMapper(education);
				educationResponseList.add(educationResponse);
			}
		}
		return educationResponseList;
	}

}
