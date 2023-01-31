package com.cloud.studentcloudservice.response;

import com.cloud.studentcloudservice.entity.Student;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StudentResponse {

    private long id;

    private String firstName;

    private String lastName;

    private String email;

    private AddressResponse addressResponse;

    public StudentResponse(Student student) {

        this.id = student.getId();
        this.firstName = student.getFirstName();
        this.lastName = student.getLastName();
        this.email = student.getEmail();
    }
}
