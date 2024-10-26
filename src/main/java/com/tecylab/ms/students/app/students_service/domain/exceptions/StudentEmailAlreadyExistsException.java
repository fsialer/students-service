package com.tecylab.ms.students.app.students_service.domain.exceptions;

public class StudentEmailAlreadyExistsException extends RuntimeException {
    public StudentEmailAlreadyExistsException(String email) {
        super("Student email: "+email+" already exists!");
    }
}
