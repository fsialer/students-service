package com.tecylab.ms.students.app.students_service.application.ports.output;

import java.util.Optional;
import java.util.List;

import com.tecylab.ms.students.app.students_service.domain.models.Student;

public interface StudentPersistencePort {

    Optional<Student> findById(Long id);
    List<Student> findByIds(Iterable<Long> ids);
    List<Student> findAll();
    boolean existsByEmail(String email);
    Student save(Student student);
    void deleleById(Long id);
}
