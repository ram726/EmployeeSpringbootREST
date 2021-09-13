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
	@Override
	public List<Employee> getEmployee() 
	{
		return dao.findAll();//
	}
	@Override
	public Employee getEmployee(int empId)
	{
		return dao.getById(empId);
	}
	@Override
	public Employee addEmployee(Employee emp) 
	{
		dao.save(emp);
		return emp;
	}
	@Override
	public Employee updateEmployee(int id, Employee e)
	{
		 Employee emp=dao.getById(id);
		 emp.setFirstName(e.getFirstName());
		 emp.setLastName(e.getLastName());
		 emp.setEmailId(e.getEmailId());
		return dao.save(emp);
	}
	@Override
	public void delteEmployee(int empId) 
	{
		dao.deleteById(empId);
	}
}