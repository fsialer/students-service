package com.tecylab.ms.students.app.students_service.infraestructure.adapters.input.rest.models.response;

import java.util.List;

import com.tecylab.ms.students.app.students_service.infraestructure.adapters.input.rest.models.enums.ErrorType;

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
public class ErrorResponse {
    private String code;
    private ErrorType type; //Functional, System
    private String message;
    private List<String> details;
    private String timestamp;
}
