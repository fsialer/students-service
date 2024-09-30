package com.tecylab.ms.students.app.students_service.infraestructure.adapters.output.persistence;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.tecylab.ms.students.app.students_service.application.ports.output.StudentPersistencePort;
import com.tecylab.ms.students.app.students_service.domain.models.Student;
import com.tecylab.ms.students.app.students_service.infraestructure.adapters.output.persistence.mapper.StudentPersistenceMapper;
import com.tecylab.ms.students.app.students_service.infraestructure.adapters.output.persistence.repository.StudentJpaRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class StudentPersistenceAdapter implements StudentPersistencePort {

    private final StudentJpaRepository jpaRepository;
    private final StudentPersistenceMapper mapper;

    @Override
    public Optional<Student> findById(Long id) {
        return jpaRepository.findById(id).map(mapper::toStudent);
    }

    @Override
    public List<Student> findAll() {
        return mapper.toStudents(jpaRepository.findAll());
    }

    @Override
    public Student save(Student student) {
        return mapper.toStudent(
                jpaRepository.save(mapper.toStudentEntity(student)));
    }

    @Override
    public void delelteById(Long id) {
        jpaRepository.deleteById(id);
    }

}
