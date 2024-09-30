package com.tecylab.ms.students.app.students_service.infraestructure.adapters.input.rest.models.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
public class StudentCreateRequest {

    @NotBlank(message = "Field firstName cannot be null or blank")
    private String firstName;

    @NotBlank(message = "Field lastName cannot be null or blank")
    private String lastName;

    @NotNull(message = "Field age cannot be null")
    @Min(value=0, message = "Field age must be greater than zero")
    private Integer age;

    @NotBlank(message = "Field address cannot be blank")
    private String address;

    @NotBlank(message = "Field email cannot be blank")
    @Email(message = "Field email invalid format")
    private String email;

    private String timestamp;

}
