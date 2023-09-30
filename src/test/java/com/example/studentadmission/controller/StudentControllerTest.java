package com.example.studentadmission.controller;

import com.example.studentadmission.model.Course;
import com.example.studentadmission.model.Student;
import com.example.studentadmission.service.StudentService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@WebMvcTest(value = StudentController.class)
@WithMockUser
public class StudentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StudentService studentService;

    Course mockCourse = new Course(1L,"Computer Networks");
    Student mockStudent = new Student(1L, "John Doe",new ArrayList<>());

    List<Student> studentList= Arrays.asList(mockStudent);

    @Test
    public void retrieveDetailsForCourse() throws Exception {

        Mockito.when(studentService.retrieveAllStudents()).thenReturn(studentList);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/students")
                .accept(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();


        String expected = "[{'id':1,'studentName':'John Doe','courses':[]}]";

        JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
    }

    @Test
    public void createStudentAdmission() throws Exception {

        ResponseEntity responseEntity=new ResponseEntity("Admission successful", HttpStatus.OK);

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("studentId", String.valueOf(1L));
        params.add("courseId", String.valueOf(1L));

        // studentService.addCourse to respond back with mockCourse
        Mockito.when(studentService.admission(Mockito.anyLong(), Mockito.anyLong())).thenReturn(responseEntity);

        // Send course as body to /students/Student1/courses
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/students/admission")
                .params(params)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        MockHttpServletResponse response = result.getResponse();

        assertEquals(HttpStatus.OK.value(), response.getStatus());

        assertEquals("Admission successful",result.getResponse().getContentAsString());
    }
}
