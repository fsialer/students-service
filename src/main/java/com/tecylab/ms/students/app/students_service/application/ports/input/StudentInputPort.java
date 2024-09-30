package com.tecylab.ms.students.app.students_service.application.ports.input;

import com.tecylab.ms.students.app.students_service.domain.models.Student;
import java.util.List;

public interface StudentInputPort {

    Student findById(Long id);
    List<Student> findAll();
    Student save(Student student);
    Student update(Long id, Student student);
    void delelteById(Long id);
}
