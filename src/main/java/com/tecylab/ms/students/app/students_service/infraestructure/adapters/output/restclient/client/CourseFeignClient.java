package com.tecylab.ms.students.app.students_service.infraestructure.adapters.output.restclient.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "courses-service",url="${courses-service.url}")
public interface CourseFeignClient {

    @DeleteMapping("/courses/remove-student-from-collection/{studentId}")
    void removeStudentFromCollection(@PathVariable Long studentId);
}
