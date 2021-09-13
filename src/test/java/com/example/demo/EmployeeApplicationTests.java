package com.example.demo;

import static javafx.beans.binding.Bindings.when;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.example.demo.dao.EmpDao;
import com.example.demo.entity.Employee;
import com.example.demo.service.EmpService;

@SpringBootTest
class EmployeeApplicationTests 
{
	@Autowired
	private EmpService service;
	@MockBean
	private EmpDao dao;
   @Test
    public void getEmployeeTest()
    {
    	when(dao.findAll())
    	.thenReturn( Stream
    			.of(new Employee(1, "Sachin", "Tendulkar", "st@email.com"))
    			.collect(Collectors.toList()));
    	assertEquals(1, service.getEmployee().size());
    }
   @Test
   public void getEmployeeByIdTest(){
	   Employee emp= new Employee(1, "Sachin", "Tendulkar", "st@email.com");
	   when(dao.getById(emp.getId())).thenReturn(emp);
	   assertEquals(emp, service.getEmployee(emp.getId()));
   }
   @Test
   public void addEmployeeTest()
   {
	   Employee emp= new Employee(1, "Sachin", "Tendulkar", "st@email.com");
	   when(dao.save(emp)).thenReturn(emp);
	   assertEquals(emp, service.addEmployee(emp));
   }
   @Test
   public void deleteEmployeeByIdTest()
   {
	   Employee emp= new Employee(1, "Sachin", "Tendulkar", "st@email.com");
	   service.delteEmployee(emp.getId());
	   verify(dao,Mockito.times(1)).deleteById(emp.getId());
   }
}
