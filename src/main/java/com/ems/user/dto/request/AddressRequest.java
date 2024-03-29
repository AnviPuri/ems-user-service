package com.ems.user.dto.request;

public class AddressRequest {

	private String addressType = "";
	private String streetAddress = "";
	private String city = "";
	private String state = "";
	private String country = "";
	private String pinCode = "";

	public AddressRequest() {
		super();
	}

	public AddressRequest(String addressType, String streetAddress, String city, String state, String country,
			String pinCode) {
		super();
		this.addressType = addressType;
		this.streetAddress = streetAddress;
		this.city = city;
		this.state = state;
		this.country = country;
		this.pinCode = pinCode;
	}

	public String getAddressType() {
		return addressType;
	}

	public void setAddressType(String addressType) {
		this.addressType = addressType;
	}

	public String getStreetAddress() {
		return streetAddress;
	}

	public void setStreetAddress(String streetAddress) {
		this.streetAddress = streetAddress;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getPinCode() {
		return pinCode;
	}

	public void setPinCode(String pinCode) {
		this.pinCode = pinCode;
	}

}
