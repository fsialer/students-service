package com.tecylab.ms.students.app.students_service.application.services;

import com.tecylab.ms.students.app.students_service.application.ports.output.ExternalCoursesOutputPort;
import com.tecylab.ms.students.app.students_service.application.ports.output.StudentPersistencePort;
import com.tecylab.ms.students.app.students_service.domain.exceptions.StudentEmailAlreadyExistsException;
import com.tecylab.ms.students.app.students_service.domain.exceptions.StudentNotFoundException;
import com.tecylab.ms.students.app.students_service.domain.models.Student;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import com.tecylab.ms.students.app.students_service.utils.TestUtils;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class StudentServiceTest {
    @Mock
    private StudentPersistencePort persistencePort;

    @Mock
    private ExternalCoursesOutputPort coursesOutputPort;

    @InjectMocks
    private StudentService studentService;
    @Test
    void testFindById_Success() {
        // Inicialización
        when(persistencePort.findById(anyLong()))
                .thenReturn(Optional.of(TestUtils.buildStudentMock()));

        // Evaluación del comportamiento
        Student student = studentService.findById(1L);

        // Comprobaciones o aserciones
        assertNotNull(student);
        assertEquals(1L, student.getId());
        assertEquals("Pepito", student.getFirstName());

        Mockito.verify(persistencePort, times(1)).findById(1L);
    }

    @Test
    void testFindById_NotFound() {
        when(persistencePort.findById(anyLong()))
                .thenReturn(Optional.empty());

        assertThrows(StudentNotFoundException.class, () -> studentService.findById(1L));

        Mockito.verify(persistencePort, times(1)).findById(1L);
    }

    @Test
    void testFindByIds_Success() {
        List<Long> ids = Collections.singletonList(1L);

        when(persistencePort.findByIds(anyCollection()))
                .thenReturn(Collections.singletonList(TestUtils.buildStudentMock()));

        List<Student> list = studentService.findByIds(ids);

        assertFalse(list.isEmpty());
        assertEquals(1, list.size());
        assertEquals(1L, list.getFirst().getId());
        assertEquals("Pepito", list.getFirst().getFirstName());

        Mockito.verify(persistencePort, times(1)).findByIds(ids);
    }

    @Test
    void testFindByAll() {
        when(persistencePort.findAll())
                .thenReturn(Collections.singletonList(TestUtils.buildStudentMock()));

        List<Student> list = studentService.findAll();

        assertFalse(list.isEmpty());
        assertEquals(1, list.size());
        assertEquals(1L, list.getFirst().getId());
        assertEquals("Pepito", list.getFirst().getFirstName());

        Mockito.verify(persistencePort, times(1)).findAll();
    }

    @Test
    void testSave_Success() {
        Student studentToSave = Student.builder()
                .id(1L)
                .firstName("Pepito")
                .lastName("Lopez")
                .age(18)
                .email("pepito@email.com")
                .address("Calle 1")
                .build();

        when(persistencePort.existsByEmail(anyString()))
                .thenReturn(Boolean.FALSE);

        when(persistencePort.save(any(Student.class)))
                .thenReturn(TestUtils.buildStudentMock());

        Student student = studentService.save(studentToSave);

        assertNotNull(student);
        assertEquals(1L, student.getId());
        assertEquals("Pepito", student.getFirstName());
        assertEquals("Lopez", student.getLastName());
        assertEquals(18, student.getAge());
        assertEquals("pepito@email.com", student.getEmail());
        assertEquals("Calle 1", student.getAddress());

        Mockito.verify(persistencePort, times(1)).existsByEmail("pepito@email.com");
        Mockito.verify(persistencePort, times(1)).save(studentToSave);
    }

    @Test
    void testSave_Failed() {
        Student studentToSave = Student.builder()
                .id(1L)
                .firstName("Pepito")
                .lastName("Lopez")
                .age(18)
                .email("pepito@email.com")
                .address("Calle 1")
                .build();

        when(persistencePort.existsByEmail(anyString()))
                .thenReturn(Boolean.TRUE);

        assertThrows(StudentEmailAlreadyExistsException.class, () -> studentService.save(studentToSave));

        Mockito.verify(persistencePort, times(1)).existsByEmail("pepito@email.com");
        Mockito.verify(persistencePort, times(0)).save(studentToSave);
    }

    @Test
    void testUpdated_Success() {
        Student studentToUpdate = Student.builder()
                .firstName("Pepito")
                .lastName("Lopez")
                .age(18)
                .email("pepito@email.com")
                .address("Calle 1")
                .build();

        when(persistencePort.existsByEmail(anyString()))
                .thenReturn(Boolean.FALSE);

        when(persistencePort.findById(anyLong()))
                .thenReturn(Optional.of(studentToUpdate));

        when(persistencePort.save(any(Student.class)))
                .thenReturn(studentToUpdate);

        Student student = studentService.update(1L, studentToUpdate);

        assertNotNull(student);
        assertEquals("Pepito", student.getFirstName());
        assertEquals("Lopez", student.getLastName());
        assertEquals(18, student.getAge());
        assertEquals("pepito@email.com", student.getEmail());
        assertEquals("Calle 1", student.getAddress());

        Mockito.verify(persistencePort, times(1)).existsByEmail("pepito@email.com");
        Mockito.verify(persistencePort, times(1)).findById(1L);
        Mockito.verify(persistencePort, times(1)).save(studentToUpdate);
    }

    @Test
    void testUpdate_FailedByAlreadyExistsEmail() {
        Student studentToUpdate = Student.builder()
                .firstName("Pepito")
                .lastName("Lopez")
                .age(18)
                .email("pepito@email.com")
                .address("Calle 1")
                .build();

        when(persistencePort.existsByEmail(anyString()))
                .thenReturn(Boolean.TRUE);

        assertThrows(StudentEmailAlreadyExistsException.class, () -> studentService.update(1L, studentToUpdate));

        Mockito.verify(persistencePort, times(1)).existsByEmail("pepito@email.com");
        Mockito.verify(persistencePort, times(0)).findById(1L);
        Mockito.verify(persistencePort, times(0)).save(studentToUpdate);
    }

    @Test
    void testUpdate_FailedByStudentNotFound() {
        Student studentToUpdate = Student.builder()
                .firstName("Pepito")
                .lastName("Lopez")
                .age(18)
                .email("pepito@email.com")
                .address("Calle 1")
                .build();

        when(persistencePort.existsByEmail(anyString()))
                .thenReturn(Boolean.FALSE);

        when(persistencePort.findById(anyLong()))
                .thenReturn(Optional.empty());

        assertThrows(StudentNotFoundException.class, () -> studentService.update(1L, studentToUpdate));

        Mockito.verify(persistencePort, times(1)).existsByEmail("pepito@email.com");
        Mockito.verify(persistencePort, times(1)).findById(1L);
        Mockito.verify(persistencePort, times(0)).save(studentToUpdate);
    }

    @Test
    void testDelete_Success() {
        when(persistencePort.findById(anyLong()))
                .thenReturn(Optional.of(TestUtils.buildStudentMock()));

        studentService.deleteById(1L);

        Mockito.verify(persistencePort, times(1)).deleteById(1L);
        Mockito.verify(coursesOutputPort, times(1)).remoStudentFromCollection(1L);
    }

    @Test
    void testDelete_FailedByStudentNotFound() {
        when(persistencePort.findById(anyLong()))
                .thenReturn(Optional.empty());

        assertThrows(StudentNotFoundException.class, () -> studentService.deleteById(1L));

        Mockito.verify(persistencePort, times(0)).deleteById(1L);
        Mockito.verify(coursesOutputPort, times(0)).remoStudentFromCollection(1L);
    }


}
