package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Employee;
import com.example.demo.service.EmpService;

@RestController
public class EmpController 
{
	@Autowired
	private EmpService service;
	
	@GetMapping("/home")
	public String empHome()
	{
		return "This is Employee Home Page";
	}
	
	//get the all employee
	@GetMapping("/emp")
	public List<Employee> getCourse()
	{ 
		return this.service.getEmployee();
	}
	
	//get emp by id
	@GetMapping("/emp/{empId}")
	public Employee getOneCourse(@PathVariable String empId)
	{
		return this.service.getEmployee(Integer.parseInt(empId));
	}
	
	//add an emp and return the emp
	@PostMapping("/emp")
	public Employee addCourses(@RequestBody Employee emp)
	{
		return this.service.addEmployee(emp);
	}
	
	//update emp by id using PUT request
	@PutMapping("/emp/{empId}")
	public Employee updateCourse(@PathVariable String empId,@RequestBody Employee emp)
	{
		return this.service.updateEmployee(Integer.parseInt(empId),emp);
	}
	
	//delete emp by id
	@DeleteMapping("/emp/{empId}")
	public void deleteCourse(@PathVariable String empId)
	{
		this.service.delteEmployee(Integer.parseInt(empId));
	}
}
