package com.tecylab.ms.students.app.students_service.infraestructure.adapters.input.rest.mapper;

import java.time.LocalDate;
import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import com.tecylab.ms.students.app.students_service.domain.models.Student;
import com.tecylab.ms.students.app.students_service.infraestructure.adapters.input.rest.models.request.StudentCreateRequest;
import com.tecylab.ms.students.app.students_service.infraestructure.adapters.input.rest.models.response.StudentResponse;

//@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE) // hace que el atributo entrada sea ignorado a nivel de clase
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface StudentRestMapper {

    Student toStudent(StudentCreateRequest studentCreateRequest);

    // @Mapping(target = "timestamp", expression = "java(mapTimestamp())")
    @Mapping(target = "timestamp", expression = "java(mapTimestamp())")
    StudentResponse toStudentResponse(Student student);

    List<StudentResponse> toStudentResponses(List<Student> students);

    // default String mapTimeStamp() {
    //     return LocalDate.now().toString();
    // }

    default String mapTimestamp() {
        return LocalDate.now().toString();
    }
}
