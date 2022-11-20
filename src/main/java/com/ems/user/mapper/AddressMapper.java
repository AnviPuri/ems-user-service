package com.ems.user.mapper;

import com.ems.user.dto.request.AddressRequest;
import com.ems.user.dto.response.AddressResponse;
import com.ems.user.entity.Address;

public class AddressMapper {

	public static Address addressRequestToEntityMapper(AddressRequest addressRequest) {

		Address address = new Address();

		address.setAddressType(addressRequest.getAddressType());
		address.setStreetAddress(addressRequest.getStreetAddress());
		address.setCity(addressRequest.getCity());
		address.setState(addressRequest.getState());
		address.setCountry(addressRequest.getCountry());
		address.setPinCode(addressRequest.getPinCode());

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
