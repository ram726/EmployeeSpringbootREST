package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.EmpDao;
import com.example.demo.entity.Employee;
@Service
public class EmpServiceImpl implements EmpService
{
	
	@Autowired
	private EmpDao dao;
	
	//fetching all emp record
	@Override
	public List<Employee> getEmployee() 
	{
		return dao.findAll();//
	}
	
	//fetching one emp record by using emp id
	@Override
	public Employee getEmployee(int empId)
	{
		return dao.getById(empId);
	}
	
	//adding one emp record into the database
	@Override
	public Employee addEmployee(Employee emp) 
	{
		dao.save(emp);
		return emp;
	}
	
	//updating employee record by id
	@Override
	public Employee updateEmployee(int id, Employee e)
	{
		 Employee emp=dao.getById(id);
		 emp.setFirstName(e.getFirstName());
		 emp.setLastName(e.getLastName());
		 emp.setEmailId(e.getEmailId());
		return dao.save(emp);
	}
	
	//deleting employee record by id
	@Override
	public void delteEmployee(int empId) 
	{
		dao.deleteById(empId);
	}
}