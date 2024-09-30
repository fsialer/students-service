package com.tecylab.ms.students.app.students_service.application.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.tecylab.ms.students.app.students_service.application.ports.input.StudentInputPort;
import com.tecylab.ms.students.app.students_service.application.ports.output.StudentPersistencePort;
import com.tecylab.ms.students.app.students_service.domain.exceptions.StudentNotFoundException;
import com.tecylab.ms.students.app.students_service.domain.models.Student;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StudentService implements StudentInputPort {

    private final StudentPersistencePort persistencePort;

    @Override
    public Student findById(Long id) {
        return persistencePort.findById(id).orElseThrow(StudentNotFoundException::new);
    }

    @Override
    public List<Student> findAll() {
        return persistencePort.findAll();
    }

    @Override
    public Student save(Student student) {
        return persistencePort.save(student);
    }

    @Override
    public Student update(Long id, Student student) {
        return persistencePort.findById(id)
                .map(oldStudent -> {
                    oldStudent.setFirstName(student.getFirstName());
                    oldStudent.setLastName(student.getLastName());
                    oldStudent.setAge(student.getAge());
                    oldStudent.setEmail(student.getEmail());
                    oldStudent.setAddress(student.getAddress());
                    return persistencePort.save(oldStudent);
                }).orElseThrow(StudentNotFoundException::new);
    }

    @Override
    public void delelteById(Long id) {
        // if(persistencePort.findById(id).isPresent()){
        // persistencePort.delelteById(id);
        // }
        // throw new StudentNotFoundException();
        if (persistencePort.findById(id).isEmpty()) {
            throw new StudentNotFoundException();
        }
        persistencePort.delelteById(id);

    }

}
