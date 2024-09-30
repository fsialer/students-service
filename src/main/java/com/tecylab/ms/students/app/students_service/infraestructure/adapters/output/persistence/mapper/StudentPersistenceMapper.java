package com.tecylab.ms.students.app.students_service.infraestructure.adapters.output.persistence.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import com.tecylab.ms.students.app.students_service.domain.models.Student;
import com.tecylab.ms.students.app.students_service.infraestructure.adapters.output.persistence.model.StudentEntity;

// @Mapper(componentModel="spring", uses = Mapper2.class) // mapper anidados
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface StudentPersistenceMapper {

    // @Mapping(source ="nombre",target = "firstname")//mapeo de los atributs
    // @Mapping(target = "firstname", constant = "sialer")//Asignandole un valor
    // pore defecto
    // @Mapping(target = "age", expression = "java(calcularEdad(student))")//Asociar
    // atributo a una funcion
    // @Mapping(target = "id", ignore = true)//Ocultar atributo
    StudentEntity toStudentEntity(Student student);

    Student toStudent(StudentEntity studentEntity);

    List<Student> toStudents(List<StudentEntity> studentEntity);
}
