package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.Employee;

public interface EmpService 
{
	public List<Employee> getEmployee();

	public Employee getEmployee(int id);

	public Employee addEmployee(Employee emp);

	public Employee updateEmployee(int id,Employee emp);

	public void delteEmployee(int id);
}
