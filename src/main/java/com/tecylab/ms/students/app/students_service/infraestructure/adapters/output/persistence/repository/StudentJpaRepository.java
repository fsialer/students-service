package com.tecylab.ms.students.app.students_service.infraestructure.adapters.output.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tecylab.ms.students.app.students_service.infraestructure.adapters.output.persistence.model.StudentEntity;

public interface StudentJpaRepository extends JpaRepository<StudentEntity,Long> {

    boolean existsByEmailIgnoreCase(String email);
}
