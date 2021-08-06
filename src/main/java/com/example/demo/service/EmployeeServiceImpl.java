package com.example.demo.service;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dao.EmployeeDAO;
import com.example.demo.dao.ProjectDAO;
import com.example.demo.entity.Employee;
import com.example.demo.entity.Project;
import com.example.demo.exception.DatabaseAccessException;
import com.example.demo.exception.EmployeeNotFoundException;
import com.example.demo.exception.ProjectNotFoundException;

@Service
public class EmployeeServiceImpl implements EmployeeService{

	@Autowired(required = true)
	private EmployeeDAO employeeDao;
	
	@Autowired(required = true)
	private ProjectDAO projectDao;
	
	@Transactional(propagation = Propagation.REQUIRED)
	@Override
	public List<Project> addProjects(Set<Project> project) throws DatabaseAccessException {
		try {
			return (List<Project>) projectDao.saveAll(project);
		}catch(Exception ex) {
			System.out.println("-->"+ex);
			throw new DatabaseAccessException("Something Went Wrong !");
		}
	}

	@Transactional(propagation = Propagation.REQUIRED)
	@Override
	public List<Employee> addEmployee(List<Employee> employee) throws DatabaseAccessException {
		try {
			return (List<Employee>) employeeDao.saveAll(employee);
		}catch(Exception ex) {
			throw new DatabaseAccessException("Something Went Wrong !");
		}
	}

	@Override
	public List<Employee> getAllEmployee() {
		List<Employee> employeeList = (List<Employee>) employeeDao.findAll();
		if(employeeList.size() > 0) {
			return employeeList;
		}
		throw new EmployeeNotFoundException("No Records Found");
	}

	@Override
	public List<Project> getAllProjects() {
		List<Project> projectList = (List<Project>) projectDao.findAll();
		if(projectList.size() > 0) {
			return projectList;
		}
		throw new ProjectNotFoundException("No Records Found");
	}

	@Override
	public Employee getEmployee(String employeeName) {
		Employee getEmployee = employeeDao.findByemployeeName(employeeName);
		if(getEmployee != null) {
			return getEmployee;
		}
		throw new EmployeeNotFoundException("No Records Found");
	}

	@Transactional(propagation = Propagation.REQUIRED)
	@Override
	public String updateEmployeeSalary(double employeeSalary,String employeeName) throws DatabaseAccessException {
		try {
			int result = employeeDao.employeeSalary(employeeSalary, employeeName);
			return "Success : "+result;
		}catch(Exception ex) {
			throw new DatabaseAccessException("Something Went Wrong !");
		}
	}

	@Transactional(propagation = Propagation.REQUIRED)
	@Override
	public String deleteEmployee(String employeeName) throws DatabaseAccessException {
		Employee employee = employeeDao.findByemployeeName(employeeName);
		try {
			if(employee != null) {
				employeeDao.delete(employee);
				return "Succesfully deleted !";
			}else {
				throw new EmployeeNotFoundException("No Records Found");
			}
		}catch (Exception e) {
			throw new DatabaseAccessException("Something Went Wrong !");
		}
	}

}
