package com.tecylab.ms.students.app.students_service.infraestructure.adapters.output.restclient;

import com.tecylab.ms.students.app.students_service.infraestructure.adapters.output.restclient.client.CourseFeignClient;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CourseRestClientAdapterTest {

    @Mock
    private CourseFeignClient client;

    @InjectMocks
    private CourseRestClientAdapter restClientAdapter;

    @Test
    void testRemoStudentFromCollection() {
        doNothing().when(client).removeStudentFromCollection(anyLong());

        restClientAdapter.remoStudentFromCollection(1L);

        verify(client, times(1)).removeStudentFromCollection(1L);
    }
}
