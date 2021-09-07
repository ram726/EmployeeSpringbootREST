package com.example.demo;

import com.example.demo.controller.EmpController;
import com.example.demo.dao.EmpDao;
import com.example.demo.entity.Employee;
import com.example.demo.service.EmpService;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;

@WebMvcTest(EmpController.class)
public class EmpControllerTest
{
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private EmpService service;
    @MockBean
    private EmpDao dao;
    @InjectMocks
    private EmpController controller;
    private static ObjectMapper mapper = new ObjectMapper();

    @Test
    public void testPostExample() throws Exception {
        Employee emp= new Employee(1,"Steve","Rogers","captain@america.mcu");
        when(service.addEmployee(ArgumentMatchers.any())).thenReturn(emp);
        String json=mapper.writeValueAsString(emp);
        MvcResult requestResult=mockMvc.perform(post("/emp").contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("utf-8").content(json).accept(MediaType.APPLICATION_JSON)).andReturn();
        String result=requestResult.getResponse().getContentAsString();
        Employee emp1=new ObjectMapper().readValue(result,Employee.class);
        assertEquals(1,emp1.getId());
}
    @Test
    public void testGetExample(){
        List empList1=new ArrayList();
        Employee emp1= new Employee(1,"Steve","Rogers","captain@america.mcu");
        Employee emp2= new Employee(2,"Tonny","Stark","tony@stark.mcu");
        empList1.add(emp1);
        empList1.add(emp2);
        when(service.getEmployee()).thenReturn(empList1);
        List empList2=service.getEmployee();
        assertEquals(2,empList2.size());
        Mockito.verify(service,Mockito.times(1)).getEmployee();
    }
    @Test
    public void testPutExample() throws Exception {
        Employee employee = new Employee();
        employee.setFirstName("Black");
        employee.setLastName("Widow");
        employee.setEmailId("black@widow.mcu");
        Optional<Employee> newEmployee = Optional.of(employee);
        String inputInJson = this.mapToJson(employee);
      when(service.updateEmployee(Mockito.anyInt(),ArgumentMatchers.any())).thenReturn(employee);
        RequestBuilder requestBuilder = put("/emp/1"+1)
                .accept(MediaType.APPLICATION_JSON).content(inputInJson)
                .contentType(MediaType.APPLICATION_JSON);
        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
        String outPutInJson = mvcResult.getRequest().getContentAsString();
        assertEquals(inputInJson, outPutInJson);
    }
    @Test
    public void testDeleteExample() throws Exception {
        Mockito.doNothing().when(service).delteEmployee(Mockito.anyInt());
        service.delteEmployee(Mockito.anyInt());
        Mockito.verify(service,Mockito.times(1)).delteEmployee(Mockito.anyInt());
    }
    private String mapToJson(Object object) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(object);
    }
}

