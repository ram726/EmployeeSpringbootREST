package com.example.demo;

import com.example.demo.controller.EmpController;
import com.example.demo.entity.Employee;
import com.example.demo.service.EmpService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;

@WebMvcTest(EmpController.class)
public class EmpControllerTest
{
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private EmpService service;
    @InjectMocks
    private EmpController controller;

    @Test
    public void testGetMethod() throws Exception {
        Employee e1 = new Employee();
        e1.setId(1);
        e1.setFirstName("FirstName-1");
        e1.setLastName("LastName-1");
        e1.setEmailId("email1@email.com");

        Employee e2 = new Employee();
        e2.setId(2);
        e2.setFirstName("FirstName-2");
        e2.setLastName("LastName-2");
        e2.setEmailId("email2@email.com");

        List<Employee> empList = new ArrayList(Arrays.asList(e1,e2));
        when(service.getEmployee()).thenReturn(empList);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/emp");
        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
        Assertions.assertEquals(this.mapToJson(empList),mvcResult.getResponse().getContentAsString());
    }
    @Test
    public void testPostMethod() throws Exception {
        Employee e1 = new Employee();
        e1.setId(1);
        e1.setFirstName("FirstName-1");
        e1.setLastName("LastName-1");
        e1.setEmailId("email1@email.com");

        String inputInJson = this.mapToJson(e1);
        when(service.addEmployee(ArgumentMatchers.any(Employee.class))).thenReturn(e1);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/emp")
                .accept(MediaType.APPLICATION_JSON).content(inputInJson)
                .contentType(MediaType.APPLICATION_JSON);
        MvcResult   mvcResult = mockMvc.perform(requestBuilder).andReturn();
        String outPutInJson = mvcResult.getRequest().getContentAsString();
        Assertions.assertEquals(inputInJson, outPutInJson);
    }
    @Test
    public void testPutMethod() throws Exception {
        Employee e1 = new Employee();
        e1.setFirstName("FirstName-1");
        e1.setLastName("LastName-1");
        e1.setEmailId("email1@email.com");
        Optional<Employee> newEmployee = Optional.of(e1);
        String inputInJson = this.mapToJson(e1);
        when(service.updateEmployee(1,e1)).thenReturn(e1);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.put("/emp/"+1)
                .accept(MediaType.APPLICATION_JSON).content(inputInJson)
                .contentType(MediaType.APPLICATION_JSON);
        MvcResult   mvcResult = mockMvc.perform(requestBuilder).andReturn();
        String outPutInJson = mvcResult.getRequest().getContentAsString();
        Assertions.assertEquals(inputInJson, outPutInJson);
    }

    @Test
    public void testDeleteMethod() throws Exception {
        int id = 1;
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .delete("/emp/"+id);
        mockMvc.perform(requestBuilder).andReturn();
    }

    @Test
    public void testGetMethodById() throws Exception {
        Employee e1 = new Employee();
        e1.setFirstName("FirstName-1");
        e1.setLastName("LastName-1");
        e1.setEmailId("email1@email.com");
        //when(service.findById(Matchers.anyInt())).thenReturn(employee);
        when(service.getEmployee(Matchers.anyInt())).thenReturn(e1);
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/emp/"+1);
        MvcResult   mvcResult = mockMvc.perform(requestBuilder).andReturn();
        Assertions.assertEquals(this.mapToJson(e1), mvcResult.getResponse().getContentAsString());
    }
    private String mapToJson(Object object) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(object);
    }
}