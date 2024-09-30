package com.tecylab.ms.students.app.students_service.infraestructure.adapters.input.rest;

import java.net.URI;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.tecylab.ms.students.app.students_service.application.ports.input.StudentInputPort;
import com.tecylab.ms.students.app.students_service.infraestructure.adapters.input.rest.mapper.StudentRestMapper;
import com.tecylab.ms.students.app.students_service.infraestructure.adapters.input.rest.models.request.StudentCreateRequest;
import com.tecylab.ms.students.app.students_service.infraestructure.adapters.input.rest.models.response.StudentResponse;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/students")
@RequiredArgsConstructor
public class StudentRestAdapter {

    private final StudentInputPort inputPort;
    private final StudentRestMapper restMapper;

    @GetMapping
    public List<StudentResponse> findAll() {
        return restMapper.toStudentResponses(inputPort.findAll());
    }

    @GetMapping("/{id}")
    public StudentResponse findById(@PathVariable Long id) {
        return restMapper.toStudentResponse(inputPort.findById(id));
    }

    @PostMapping
    public ResponseEntity<StudentResponse> save(@Valid @RequestBody StudentCreateRequest request) {
        StudentResponse response = restMapper.toStudentResponse(
                inputPort.save(restMapper.toStudent(request)));

        return ResponseEntity.created(URI.create("/students/".concat(response.getId().toString()))).body(response);
    }

    @PutMapping("/{id}")
    public StudentResponse update(@PathVariable Long id, @Valid @RequestBody StudentCreateRequest request) {
        return restMapper.toStudentResponse(
                inputPort.update(id, restMapper.toStudent(request)));

    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id){
        inputPort.delelteById(id);
    }

}
