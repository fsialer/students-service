package com.tecylab.ms.students.app.students_service.infraestructure.adapters.input.rest.models.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StudentResponse {

    private Long id;
    private String firstName;
    private String lastName;
    private Integer age;
    private String address;
    private String email;
    private String timestamp; 
}
