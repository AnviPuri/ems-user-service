package com.ems.user.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

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
import com.ems.user.mapper.AddressMapper;
import com.ems.user.mapper.EducationMapper;
import com.ems.user.mapper.UserMapper;
import com.ems.user.repo.AddressRepo;
import com.ems.user.repo.EducationRepo;
import com.ems.user.repo.UserRepo;
import com.ems.user.service.UserService;
import com.ems.user.utility.AuditUtility;

import ems.utility.logger.EmsLogger;
import ems.utility.util.EmsUtility;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepo userRepository;

	@Autowired
	private EducationRepo educationRepository;

	@Autowired
	private AddressRepo addressRepository;

	private Logger logger = EmsLogger.getLogger(UserServiceImpl.class.getName());

	public UserResponse createUser(UserRequest userRequest) {

		// validate user request
		// 1. Address can't have more than 2 address - one current and one permanent

		EmsLogger.log("CREATE USER WITH REQUEST : " + EmsUtility.toJsonString(userRequest), logger);

		List<AddressRequest> addressRequestList = userRequest.getAddressList();
		List<EducationRequest> educationRequestList = userRequest.getEducationList();
		List<UserRequest> emergencyContactRequestList = userRequest.getEmergencyContactList();

		User user = new User();
		user = UserMapper.userRequestToEntityMapper(userRequest);
		user = userRepository.save(user);

		List<AddressResponse> addressResponseList = new ArrayList<>();
		List<EducationResponse> educationResponseList = new ArrayList<>();
		List<UserResponse> emergencyContactResponseList = new ArrayList<>();

		addressResponseList = this.createAddress(addressRequestList, user);
		educationResponseList = this.createEducation(educationRequestList, user);
		emergencyContactResponseList = this.createEmergencyUser(emergencyContactRequestList, user);

		UserResponse userResponse = new UserResponse();
		userResponse = UserMapper.userEntityToResponseMapper(user);
		userResponse.setAddressResponseList(addressResponseList);
		userResponse.setEducationResponseList(educationResponseList);
		userResponse.setEmergencyContactResponseList(emergencyContactResponseList);

		return userResponse;
	}

	@Override
	public UserResponse updateUser(UserUpdateRequest userUpdateRequest, String userId) {

		// validate user request
		// 1. Address can't have more than 2 address - one current and one permanent

		EmsLogger.log("UPDATE USER WITH REQUEST : " + EmsUtility.toJsonString(userUpdateRequest), logger);

		User user = new User();
		Optional<User> optionalUser = userRepository.findById(userId);
		if (optionalUser.isPresent()) {
			user = optionalUser.get();
			user = UserMapper.userUpdateRequestToEntityMapper(userUpdateRequest, user);
			user = userRepository.save(user);

			List<AddressResponse> addressResponseList = new ArrayList<>();
			List<EducationResponse> educationResponseList = new ArrayList<>();
			List<UserResponse> emergencyContactResponseList = new ArrayList<>();
			addressResponseList = this.updateAddress(userUpdateRequest.getAddressList(), user);
			educationResponseList = this.updateEducation(userUpdateRequest.getEducationList(), user);
			emergencyContactResponseList = this.updateEmergencyUser(userUpdateRequest.getEmergencyContactList(), user);

			UserResponse userResponse = new UserResponse();
			userResponse = UserMapper.userEntityToResponseMapper(user);
			userResponse.setAddressResponseList(addressResponseList);
			userResponse.setEducationResponseList(educationResponseList);
			userResponse.setEmergencyContactResponseList(emergencyContactResponseList);

			return userResponse;
		}

		return null;
	}

	@Override
	public boolean deleteUser(String userId) {

		EmsLogger.log("DELETE USER : " + userId, logger);

		Optional<User> optionalUser = userRepository.findById(userId);
		User deletedUser = new User();
		boolean isDeleted = false;
		if (optionalUser.isPresent()) {
			deletedUser = optionalUser.get();
			deletedUser.setAudit(AuditUtility.deleteApiAuditBuild(deletedUser.getAudit()));
			deletedUser = userRepository.save(deletedUser);
			if (deletedUser.getAudit().isActive())
				isDeleted = false;
			else {
				isDeleted = true;
				this.deleteAddressByUser(deletedUser);
				this.deleteEducationByUser(deletedUser);
				this.deleteEmergencyContactByUser(deletedUser);
			}
		}
		return isDeleted;
	}

	@Override
	public UserResponse getByUserId(String userId) {

		EmsLogger.log("GET USER : " + userId, logger);

		Optional<User> optionalUser = userRepository.findById(userId);
		User user = new User();
		UserResponse userResponse = new UserResponse();
		if (optionalUser.isPresent() && optionalUser.get().getAudit().isActive()) {
			user = optionalUser.get();
			userResponse = UserMapper.userEntityToResponseMapper(user);

			List<AddressResponse> addressResponseList = new ArrayList<>();
			List<EducationResponse> educationResponseList = new ArrayList<>();
			List<UserResponse> emergencyContactResponseList = new ArrayList<>();

			addressResponseList = this.getAddressByUser(user);
			educationResponseList = this.getEducationByUser(user);
			emergencyContactResponseList = this.getEmergencyContactByParentUser(user);
			userResponse.setAddressResponseList(addressResponseList);
			userResponse.setEducationResponseList(educationResponseList);
			userResponse.setEmergencyContactResponseList(emergencyContactResponseList);
		}
		return userResponse;
	}

	@Override
	public HashMap<String, Object> getAllUsers(String userType, int pageNumber, int pageSize) {

		EmsLogger.log("GET ALL USERS WITH USER_TYPE " + userType, logger);

		HashMap<String, Object> resultBody = new HashMap<>();
		List<User> pagedUserList = new ArrayList<>();
		List<UserResponse> userResponseList = new ArrayList<>();
		Sort firstNameSort = Sort.by("firstName");
		Sort lastNameSort = Sort.by("lastName");
		Sort groupBySort = firstNameSort.and(lastNameSort);

		if (pageNumber == 0) {
			List<User> sortedResult = userRepository.findByUserTypeAndAuditIsActive(userType, true, groupBySort);
			userResponseList = sortedResult.stream().map(user -> UserMapper.userEntityToResponseMapper(user))
					.collect(Collectors.toList());
		} else {
			Pageable paging = PageRequest.of(pageNumber, pageSize, groupBySort);
			Page<User> pagedResult = userRepository.findByUserTypeAndAuditIsActive(userType, true, paging);
			if (pagedResult.hasContent()) {
				pagedUserList = pagedResult.getContent();
			}
			userResponseList = pagedUserList.stream().map(pagedUser -> UserMapper.userEntityToResponseMapper(pagedUser))
					.collect(Collectors.toList());

			resultBody.put("totalPages", pagedResult.getTotalPages());
			resultBody.put("totalUsers", pagedResult.getTotalElements());
		}

		resultBody.put("userResponseList", userResponseList);
		return resultBody;
	}

	@Override
	public List<UserResponse> getAllUsers(List<String> userIdList) {

		EmsLogger.log("GET ALL USERS WITH REQUEST : " + EmsUtility.toJsonString(userIdList), logger);

		List<User> userList = new ArrayList<>();
		List<UserResponse> userResponseList = new ArrayList<>();

		Optional<List<User>> optionalUserList = userRepository.findByUserIdInAndAuditIsActive(userIdList, true);
		if (optionalUserList.isPresent() && !optionalUserList.get().isEmpty()) {
			userList = optionalUserList.get();
			userResponseList = userList.stream().map(user -> UserMapper.userEntityToResponseMapper(user))
					.collect(Collectors.toList());
		}

		return userResponseList;
	}

	@Override
	public HashMap<String, Object> searchUsersByUserTypeAndFirstName(int pageNumber, int pageSize, String userType,
			String searchQuery) {

		// refine search query to include search by last name as well

		EmsLogger.log("SEARCH ALL USERS WITH USER_TYPE " + userType + " AND SEARCH_QUERY " + searchQuery, logger);

		List<User> pagedUserList = new ArrayList<>();
		List<UserResponse> pagedUserResponseList = new ArrayList<>();
		Sort firstNameSort = Sort.by("firstName");
		Sort lastNameSort = Sort.by("lastName");
		Sort groupBySort = firstNameSort.and(lastNameSort);
		Pageable paging = PageRequest.of(pageNumber, pageSize, groupBySort);
		Page<User> pagedResult = userRepository.findByUserTypeAndAuditIsActiveAndFirstNameContainingIgnoreCase(userType,
				true, searchQuery, paging);
		if (pagedResult.hasContent()) {
			pagedUserList = pagedResult.getContent();
		}
		pagedUserResponseList = pagedUserList.stream()
				.map(pagedUser -> UserMapper.userEntityToResponseMapper(pagedUser)).collect(Collectors.toList());
		HashMap<String, Object> resultBody = new HashMap<>();
		resultBody.put("pagedUserResponseList", pagedUserResponseList);
		resultBody.put("totalPages", pagedResult.getTotalPages());
		resultBody.put("totalUsers", pagedResult.getTotalElements());
		return resultBody;
	}

	@Override
	public List<UserResponse> createEmergencyUser(List<UserRequest> emergencyContactRequestList,
			User emergencyContactParent) {

		EmsLogger.log("CREATE EMERGENCY CONTACTS : " + EmsUtility.toJsonString(emergencyContactRequestList)
				+ " FOR USER : " + emergencyContactParent.getUserId(), logger);

		List<UserResponse> emergencyContactList = new ArrayList<>();
		if (!emergencyContactRequestList.isEmpty()) {
			for (UserRequest emergencyContactRequest : emergencyContactRequestList) {
				User emergencyContact = new User();
				UserResponse emergencyContactResponse = new UserResponse();
				emergencyContact = UserMapper.userRequestToEntityMapper(emergencyContactRequest);
				emergencyContact.setEmergencyContactParent(emergencyContactParent);
				emergencyContact = userRepository.save(emergencyContact);
				emergencyContactResponse = UserMapper.userEntityToResponseMapper(emergencyContact);
				emergencyContactList.add(emergencyContactResponse);
			}
		}
		return emergencyContactList;
	}

	@Override
	public List<UserResponse> updateEmergencyUser(List<UserUpdateRequest> emergencyContactUpdateRequestList,
			User emergencyContactParent) {

		EmsLogger.log("UPDATE EMERGENCY CONTACTS : " + EmsUtility.toJsonString(emergencyContactUpdateRequestList)
				+ " FOR USER : " + emergencyContactParent.getUserId(), logger);

		List<User> savedEmergencyContactList = new ArrayList<>();
		HashMap<String, User> emergencyContactMap = new HashMap<>();

		Optional<List<User>> optionalEmergencyContactList = userRepository
				.findByEmergencyContactParentAndAuditIsActive(emergencyContactParent, true);
		if (optionalEmergencyContactList.isPresent()) {
			savedEmergencyContactList = optionalEmergencyContactList.get();
		}
		if (!savedEmergencyContactList.isEmpty()) {
			for (User savedEmergencyContact : savedEmergencyContactList) {
				emergencyContactMap.put(savedEmergencyContact.getUserId(), savedEmergencyContact);
			}
		}
		List<String> updatedEmergencyContactIdList = new ArrayList<>();
		List<UserResponse> updatedEmergencyContactResponseList = new ArrayList<>();
		if (!emergencyContactUpdateRequestList.isEmpty()) {
			for (UserUpdateRequest emergencyContactUpdateRequest : emergencyContactUpdateRequestList) {
				User updatedEmergencyContact = new User();
				UserResponse updatedEmergencyContactResponse = new UserResponse();
				updatedEmergencyContact = UserMapper.emergencyContactUpdateRequestToEntityMapper(
						emergencyContactUpdateRequest, emergencyContactParent);
				if (!EmsUtility.isNullOrEmpty(updatedEmergencyContact.getUserId())) {
					updatedEmergencyContact.setAudit(AuditUtility.updateApiAuditBuild(
							emergencyContactMap.get(updatedEmergencyContact.getUserId()).getAudit()));
					updatedEmergencyContact = userRepository.save(updatedEmergencyContact);
					updatedEmergencyContactResponse = UserMapper.userEntityToResponseMapper(updatedEmergencyContact);
					updatedEmergencyContactResponseList.add(updatedEmergencyContactResponse);
					updatedEmergencyContactIdList.add(updatedEmergencyContact.getUserId());
				} else {
					updatedEmergencyContact.setAudit(AuditUtility.createApiAuditBuild());
					updatedEmergencyContact = userRepository.save(updatedEmergencyContact);
					updatedEmergencyContactResponse = UserMapper.userEntityToResponseMapper(updatedEmergencyContact);
					updatedEmergencyContactResponseList.add(updatedEmergencyContactResponse);
					updatedEmergencyContactIdList.add(updatedEmergencyContact.getUserId());
				}
			}
		}
		if (!savedEmergencyContactList.isEmpty()) {
			for (User emergencyContact : savedEmergencyContactList) {
				if (!updatedEmergencyContactIdList.contains(emergencyContact.getUserId())) {
					emergencyContact.setAudit(AuditUtility.deleteApiAuditBuild(emergencyContact.getAudit()));
					userRepository.save(emergencyContact);
				}
			}
		}
		return updatedEmergencyContactResponseList;
	}

	@Override
	public void deleteEmergencyContactByUser(User user) {

		EmsLogger.log("DELETE EMERGENCY CONTACTS FOR USER : " + EmsUtility.toJsonString(user), logger);

		List<User> savedEmergencyContactList = new ArrayList<>();

		Optional<List<User>> optionalEmergencyContactList = userRepository
				.findByEmergencyContactParentAndAuditIsActive(user, true);
		if (optionalEmergencyContactList.isPresent()) {
			savedEmergencyContactList = optionalEmergencyContactList.get();
		}
		if (!savedEmergencyContactList.isEmpty()) {
			for (User emergencyContact : savedEmergencyContactList) {
				emergencyContact.setAudit(AuditUtility.deleteApiAuditBuild(emergencyContact.getAudit()));
				userRepository.save(emergencyContact);
			}
		}
	}

	@Override
	public List<UserResponse> getEmergencyContactByParentUser(User user) {

		EmsLogger.log("GET EMERGENCY CONTACTS FOR USER : " + user.getUserId(), logger);

		List<User> savedEmergencyContactList = new ArrayList<>();
		List<UserResponse> emergencyContactResponseList = new ArrayList<>();
		Optional<List<User>> optionalEmergencyContactList = userRepository
				.findByEmergencyContactParentAndAuditIsActive(user, true);
		if (optionalEmergencyContactList.isPresent()) {
			savedEmergencyContactList = optionalEmergencyContactList.get();
			for (User savedEmergencyContact : savedEmergencyContactList) {
				UserResponse emergencyContactResponse = new UserResponse();
				emergencyContactResponse = UserMapper.userEntityToResponseMapper(savedEmergencyContact);
				emergencyContactResponseList.add(emergencyContactResponse);
			}
		}
		return emergencyContactResponseList;
	}

	@Override
	public List<AddressResponse> createAddress(List<AddressRequest> addressRequestList, User user) {

		EmsLogger.log(
				"CREATE ADDRESS : " + EmsUtility.toJsonString(addressRequestList) + " FOR USER : " + user.getUserId(),
				logger);

		List<AddressResponse> addressResponseList = new ArrayList<>();
		if (!addressRequestList.isEmpty()) {
			for (AddressRequest addressRequest : addressRequestList) {
				Address address = new Address();
				AddressResponse addressResponse = new AddressResponse();
				address = AddressMapper.addressRequestToEntityMapper(addressRequest, user);
				address = addressRepository.save(address);
				addressResponse = AddressMapper.addressEntityToResponseMapper(address);
				addressResponseList.add(addressResponse);
			}
		}
		return addressResponseList;
	}

	@Override
	public List<AddressResponse> updateAddress(List<AddressUpdateRequest> addressUpdateRequestList, User user) {

		EmsLogger.log("UPDATE ADDRESS : " + EmsUtility.toJsonString(addressUpdateRequestList) + " FOR USER : "
				+ user.getUserId(), logger);

		List<Address> savedAddressList = new ArrayList<>();
		HashMap<String, Address> addressMap = new HashMap<>();

		Optional<List<Address>> optionalAddressList = addressRepository.findByUserAndAuditIsActive(user, true);
		if (optionalAddressList.isPresent()) {
			savedAddressList = optionalAddressList.get();
		}
		if (!savedAddressList.isEmpty()) {
			for (Address address : savedAddressList) {
				addressMap.put(address.getAddressId(), address);
			}
		}
		List<String> updatedAddressIdList = new ArrayList<>();
		List<AddressResponse> updatedAddressResponseList = new ArrayList<>();
		if (!addressUpdateRequestList.isEmpty()) {
			for (AddressUpdateRequest addressUpdateRequest : addressUpdateRequestList) {
				Address updatedAddress = new Address();
				AddressResponse updatedAddressResponse = new AddressResponse();
				updatedAddress = AddressMapper.addressUpdateRequestToEntityMapper(addressUpdateRequest, user);
				if (!EmsUtility.isNullOrEmpty(updatedAddress.getAddressId())) {
					updatedAddress.setAudit(
							AuditUtility.updateApiAuditBuild(addressMap.get(updatedAddress.getAddressId()).getAudit()));
					updatedAddress = addressRepository.save(updatedAddress);
					updatedAddressResponse = AddressMapper.addressEntityToResponseMapper(updatedAddress);
					updatedAddressResponseList.add(updatedAddressResponse);
					updatedAddressIdList.add(updatedAddress.getAddressId());
				} else {
					updatedAddress.setAudit(AuditUtility.createApiAuditBuild());
					updatedAddress = addressRepository.save(updatedAddress);
					updatedAddressResponse = AddressMapper.addressEntityToResponseMapper(updatedAddress);
					updatedAddressResponseList.add(updatedAddressResponse);
					updatedAddressIdList.add(updatedAddress.getAddressId());
				}
			}
		}
		if (!savedAddressList.isEmpty()) {
			for (Address address : savedAddressList) {
				if (!updatedAddressIdList.contains(address.getAddressId())) {
					this.deleteAddress(address);
				}
			}
		}
		return updatedAddressResponseList;
	}

	@Override
	public void deleteAddress(Address address) {

		EmsLogger.log("DELETE ADDRESS : " + address.getAddressId(), logger);

		address.setAudit(AuditUtility.deleteApiAuditBuild(address.getAudit()));
		address = addressRepository.save(address);
	}

	@Override
	public void deleteAddressByUser(User user) {

		EmsLogger.log("DELETE ADDRESS FOR USER : " + user.getUserId(), logger);

		List<Address> savedAddressList = new ArrayList<>();

		Optional<List<Address>> optionalAddressList = addressRepository.findByUserAndAuditIsActive(user, true);
		if (optionalAddressList.isPresent()) {
			savedAddressList = optionalAddressList.get();
			for (Address address : savedAddressList) {
				this.deleteAddress(address);
			}
		}
	}

	@Override
	public List<AddressResponse> getAddressByUser(User user) {

		EmsLogger.log("GET ADDRESS FOR USER : " + user.getUserId(), logger);

		List<Address> savedAddressList = new ArrayList<>();
		List<AddressResponse> addressResponseList = new ArrayList<>();
		Optional<List<Address>> optionalAddressList = addressRepository.findByUserAndAuditIsActive(user, true);
		if (optionalAddressList.isPresent()) {
			savedAddressList = optionalAddressList.get();
			for (Address savedAddress : savedAddressList) {
				AddressResponse savedAddressResponse = new AddressResponse();
				savedAddressResponse = AddressMapper.addressEntityToResponseMapper(savedAddress);
				addressResponseList.add(savedAddressResponse);
			}
		}
		return addressResponseList;
	}

	@Override
	public List<EducationResponse> createEducation(List<EducationRequest> educationRequestList, User user) {

		EmsLogger.log("CREATE EDUCATION : " + EmsUtility.toJsonString(educationRequestList) + " FOR USER : "
				+ user.getUserId(), logger);

		List<EducationResponse> educationResponseList = new ArrayList<>();
		if (!educationRequestList.isEmpty()) {
			for (EducationRequest educationRequest : educationRequestList) {
				Education education = new Education();
				EducationResponse educationResponse = new EducationResponse();
				education = EducationMapper.educationRequestToEntityMapper(educationRequest, user);
				education = educationRepository.save(education);
				educationResponse = EducationMapper.educationEntityToResponseMapper(education);
				educationResponseList.add(educationResponse);
			}
		}
		return educationResponseList;
	}

	@Override
	public List<EducationResponse> updateEducation(List<EducationUpdateRequest> educationUpdateRequestList, User user) {

		EmsLogger.log("UPDATE EDUCATION : " + EmsUtility.toJsonString(educationUpdateRequestList) + " FOR USER : "
				+ user.getUserId(), logger);

		List<Education> savedEducationList = new ArrayList<>();
		HashMap<String, Education> educationMap = new HashMap<>();

		Optional<List<Education>> optionalEducationList = educationRepository.findByUserAndAuditIsActive(user, true);
		if (optionalEducationList.isPresent()) {
			savedEducationList = optionalEducationList.get();
		}
		if (!savedEducationList.isEmpty()) {
			for (Education education : savedEducationList) {
				educationMap.put(education.getEducationId(), education);
			}
		}
		List<String> updatedEducationIdList = new ArrayList<>();
		List<EducationResponse> updatedEducationResponseList = new ArrayList<>();
		if (!educationUpdateRequestList.isEmpty()) {
			for (EducationUpdateRequest educationUpdateRequest : educationUpdateRequestList) {
				Education updatedEducation = new Education();
				EducationResponse updatedEducationResponse = new EducationResponse();
				updatedEducation = EducationMapper.educationUpdateRequestToEntityMapper(educationUpdateRequest, user);
				if (!EmsUtility.isNullOrEmpty(updatedEducation.getEducationId())) {
					updatedEducation.setAudit(AuditUtility
							.updateApiAuditBuild(educationMap.get(updatedEducation.getEducationId()).getAudit()));
					updatedEducation = educationRepository.save(updatedEducation);
					updatedEducationResponse = EducationMapper.educationEntityToResponseMapper(updatedEducation);
					updatedEducationResponseList.add(updatedEducationResponse);
					updatedEducationIdList.add(updatedEducation.getEducationId());
				} else {
					updatedEducation.setAudit(AuditUtility.createApiAuditBuild());
					updatedEducation = educationRepository.save(updatedEducation);
					updatedEducationResponse = EducationMapper.educationEntityToResponseMapper(updatedEducation);
					updatedEducationResponseList.add(updatedEducationResponse);
					updatedEducationIdList.add(updatedEducation.getEducationId());
				}
			}
		}
		if (!savedEducationList.isEmpty()) {
			for (Education education : savedEducationList) {
				if (!updatedEducationIdList.contains(education.getEducationId())) {
					this.deleteEducation(education);
				}
			}
		}
		return updatedEducationResponseList;
	}

	@Override
	public void deleteEducation(Education education) {

		EmsLogger.log("DELETE EDUCATION : " + education.getEducationId(), logger);

		education.setAudit(AuditUtility.deleteApiAuditBuild(education.getAudit()));
		education = educationRepository.save(education);
	}

	@Override
	public List<EducationResponse> getEducationByUser(User user) {

		EmsLogger.log("GET EDUCATION FOR USER : " + user.getUserId(), logger);

		List<Education> savedEducationList = new ArrayList<>();
		List<EducationResponse> educationResponseList = new ArrayList<>();
		Optional<List<Education>> optionalEducationList = educationRepository.findByUserAndAuditIsActive(user, true);
		if (optionalEducationList.isPresent()) {
			savedEducationList = optionalEducationList.get();
			for (Education savedEducation : savedEducationList) {
				EducationResponse savedEducationResponse = new EducationResponse();
				savedEducationResponse = EducationMapper.educationEntityToResponseMapper(savedEducation);
				educationResponseList.add(savedEducationResponse);
			}
		}
		return educationResponseList;
	}

	@Override
	public void deleteEducationByUser(User user) {

		EmsLogger.log("DELETE EDUCATION FOR USER : " + user.getUserId(), logger);

		List<Education> savedEducationList = new ArrayList<>();

		Optional<List<Education>> optionalEducationList = educationRepository.findByUserAndAuditIsActive(user, true);
		if (optionalEducationList.isPresent()) {
			savedEducationList = optionalEducationList.get();
			for (Education education : savedEducationList) {
				this.deleteEducation(education);
			}
		}

	}
}
