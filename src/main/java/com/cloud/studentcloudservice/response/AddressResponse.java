package com.cloud.studentcloudservice.response;

import lombok.Data;

@Data
public class AddressResponse {

	private long addressId;

	private String street;

	private String city;
}
