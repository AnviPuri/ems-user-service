package com.ems.user.mapper;

import com.ems.user.dto.request.AddressRequest;
import com.ems.user.dto.request.AddressUpdateRequest;
import com.ems.user.dto.response.AddressResponse;
import com.ems.user.entity.Address;
import com.ems.user.entity.User;
import com.ems.user.utility.AuditUtility;
import com.ems.user.utility.EmsUtility;

public class AddressMapper {

	public static Address addressRequestToEntityMapper(AddressRequest addressRequest, User user) {

		Address address = new Address();
		address.setAddressType(addressRequest.getAddressType());
		address.setStreetAddress(addressRequest.getStreetAddress());
		address.setCity(addressRequest.getCity());
		address.setState(addressRequest.getState());
		address.setCountry(addressRequest.getCountry());
		address.setPinCode(addressRequest.getPinCode());
		address.setAudit(AuditUtility.createApiAuditBuild());
		address.setUser(user);
		return address;
	}

	public static Address addressUpdateRequestToEntityMapper(AddressUpdateRequest addressUpdateRequest, User user) {

		Address address = new Address();
		if (!EmsUtility.isNullOrEmpty(addressUpdateRequest.getAddressId())) {
			address.setAddressId(addressUpdateRequest.getAddressId());
		} else {
			address.setAddressId("");
		}
		address.setAddressType(addressUpdateRequest.getAddressType());
		address.setStreetAddress(addressUpdateRequest.getStreetAddress());
		address.setCity(addressUpdateRequest.getCity());
		address.setState(addressUpdateRequest.getState());
		address.setCountry(addressUpdateRequest.getCountry());
		address.setPinCode(addressUpdateRequest.getPinCode());
		address.setUser(user);
		return address;
	}

	public static AddressResponse addressEntityToResponseMapper(Address address) {

		AddressResponse addressResponse = new AddressResponse();
		addressResponse.setAddressId(address.getAddressId());
		addressResponse.setAddressType(address.getAddressType());
		addressResponse.setStreetAddress(address.getStreetAddress());
		addressResponse.setCity(address.getCity());
		addressResponse.setState(address.getState());
		addressResponse.setCountry(address.getCountry());
		addressResponse.setPinCode(address.getPinCode());
		return addressResponse;
	}

}
