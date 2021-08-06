package com.example.demo.service;


import java.util.List;
import java.util.Set;

import com.example.demo.entity.Employee;
import com.example.demo.entity.Project;
import com.example.demo.exception.DatabaseAccessException;

public interface EmployeeService {

	public List<Project> addProjects(Set<Project> project) throws DatabaseAccessException;
	
	public List<Employee> addEmployee(List<Employee> employee) throws DatabaseAccessException;
	
	public List<Employee> getAllEmployee();
	
	public List<Project> getAllProjects();
	
	public Employee getEmployee(String employeeName);
	
	public String updateEmployeeSalary(double employeeSalary,String employeeName) throws DatabaseAccessException;
		
	public String deleteEmployee(String employeeName) throws DatabaseAccessException;
}
