package com.example.demo.dao;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Employee;

@Repository
public interface EmployeeDAO extends CrudRepository<Employee, Integer>{

	public Employee findByemployeeName(String employeeName);
	
	@Modifying
	@Query(value = "UPDATE Employee_details SET employee_salary = ?1 WHERE employee_Name = ?2",nativeQuery = true)
	public int employeeSalary(double employeeSalary,String employeeName);
}
