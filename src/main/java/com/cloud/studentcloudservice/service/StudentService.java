package com.cloud.studentcloudservice.service;

import com.cloud.studentcloudservice.entity.Student;
import com.cloud.studentcloudservice.feignclient.AddressFeignClient;
import com.cloud.studentcloudservice.repository.StudentRepository;
import com.cloud.studentcloudservice.request.CreateStudentRequest;
import com.cloud.studentcloudservice.response.AddressResponse;
import com.cloud.studentcloudservice.response.StudentResponse;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class StudentService {

	Logger logger = LoggerFactory.getLogger(StudentService.class);

	@Autowired
	StudentRepository studentRepository;
	
	@Autowired
	WebClient webClient;

	@Autowired
	AddressFeignClient addressFeignClient;

	@Autowired
	AddressService addressService;

	@Value("${address.service.url}")
	private String addressServiceUrl;

	public StudentResponse createStudent(CreateStudentRequest createStudentRequest) {

		Student student = new Student();
		student.setFirstName(createStudentRequest.getFirstName());
		student.setLastName(createStudentRequest.getLastName());
		student.setEmail(createStudentRequest.getEmail());
		
		student.setAddressId(createStudentRequest.getAddressId());
		student = studentRepository.save(student);
		
		StudentResponse studentResponse = new StudentResponse(student);

		// using web client
//		studentResponse.setAddressResponse(getAddressById(student.getAddressId()));

		// using openfeign
		studentResponse.setAddressResponse(addressFeignClient.getById(student.getAddressId()));

		return studentResponse;
	}
	
	public StudentResponse getAddressById(long id) {

		logger.info("get student by id");

		Student student = studentRepository.findById(id).get();
		StudentResponse studentResponse = new StudentResponse(student);

		// using web client
//		studentResponse.setAddressResponse(getAddressById(student.getAddressId()));

		// using openfeign and circuit breaker
		studentResponse.setAddressResponse(addressService.getAddressById(student));
		
		return studentResponse;
	}

	private AddressResponse getAddressById (Long addressId) {
		Mono<AddressResponse> addressResponse = 
				webClient.get().uri(addressServiceUrl + "/api/address/getById/" + addressId)
		.retrieve().bodyToMono(AddressResponse.class);
		
		return addressResponse.block();
	}
}
