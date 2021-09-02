package com.example.demo;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

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
	
	@Mock
	private Optional<Employee> optionalEmp;
	
	//test case for get all employee
   @Test
    public void getEmployeeTest()
    {
    	when(dao.findAll())
    	.thenReturn( Stream
    			.of(new Employee(1, "Sachin", "Tendulkar", "st@email.com"))
    			.collect(Collectors.toList()));
    	
    	assertEquals(1, service.getEmployee().size());
    	
    }
   
   //test case for get employee by id
   @Test
   public void getEmployeeByIdTest(){
	   Employee emp= new Employee(1, "Sachin", "Tendulkar", "st@email.com");
	   Integer i=1;
	  when(dao.findById(i).get()).thenReturn(emp);
	  
	  Employee e1=service.getEmployee(i);
	  
	  assertEquals(emp,e1);
   }
   
   //test case adding one emp record into the database
   @Test
   public void addEmployeeTest()
   {
	   Employee emp= new Employee(1, "Sachin", "Tendulkar", "st@email.com");
	   when(dao.save(emp)).thenReturn(emp);
	   
	   assertEquals(emp, service.addEmployee(emp));
   }
   
   //test case for updating employee record by id
   @Test
   public void updateEmployeeTest(){
       Employee emp = dao.getById(1);
       emp.setEmailId("ram@gmail.com");
       Employee empUpdated =  dao.save(emp);
       assertThat(empUpdated.getEmailId()).isEqualTo("ram@gmail.com");
   }
   
   //test case for deleting employee record by id
   @Test
   public void delteEmployeeByIdTest()
   {
	   Employee emp= new Employee(1, "Sachin", "Tendulkar", "st@email.com");
	   service.delteEmployee(emp.getId());
	   verify(dao,Mockito.times(1)).deleteById(emp.getId());
   }
}