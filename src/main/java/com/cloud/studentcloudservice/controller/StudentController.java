package com.cloud.studentcloudservice.controller;

import com.cloud.studentcloudservice.request.CreateStudentRequest;
import com.cloud.studentcloudservice.response.StudentResponse;
import com.cloud.studentcloudservice.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/student")
public class StudentController {
	
	@Autowired
	StudentService studentService;
	
	@PostMapping("/create")
	public StudentResponse createStudent (@RequestBody CreateStudentRequest createStudentRequest) {

		return studentService.createStudent(createStudentRequest);
	}
	
	@GetMapping("getById/{id}")
	public StudentResponse getById (@PathVariable long id) {
		return studentService.getAddressById(id);
	}
	
}
