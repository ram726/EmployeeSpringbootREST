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
	
	@GetMapping("/emp")
	public List<Employee> getEmp()
	{ 
		return this.service.getEmployee();
	}
	
	@GetMapping("/emp/{empId}")
	public Employee getOneEmp(@PathVariable String empId)
	{
		return this.service.getEmployee(Integer.parseInt(empId));
	}
	
	@PostMapping("/emp")
	public Employee addEmp(@RequestBody Employee emp)
	{
		return this.service.addEmployee(emp);
	}
	
	@PutMapping("/emp/{empId}")
	public Employee updateEmp(@PathVariable String empId,@RequestBody Employee emp)
	{
		return this.service.updateEmployee(Integer.parseInt(empId),emp);
	}

	@DeleteMapping("/emp/{empId}")
	public void deleteEmp(@PathVariable String empId)
	{
		this.service.delteEmployee(Integer.parseInt(empId));
	}
}
