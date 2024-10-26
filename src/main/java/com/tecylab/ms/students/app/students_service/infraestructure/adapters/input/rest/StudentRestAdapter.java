package com.tecylab.ms.students.app.students_service.infraestructure.adapters.input.rest;

import java.net.URI;
import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.tecylab.ms.students.app.students_service.application.ports.input.StudentInputPort;
import com.tecylab.ms.students.app.students_service.infraestructure.adapters.input.rest.mapper.StudentRestMapper;
import com.tecylab.ms.students.app.students_service.infraestructure.adapters.input.rest.models.request.StudentCreateRequest;
import com.tecylab.ms.students.app.students_service.infraestructure.adapters.input.rest.models.response.StudentResponse;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

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

    @GetMapping("/find-by-ids")
    public List<StudentResponse> findByIds(@RequestParam List<Long> ids) {
        return restMapper.toStudentResponses(inputPort.findByIds(ids));
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
