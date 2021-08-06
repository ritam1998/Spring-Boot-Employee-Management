package com.example.demo.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Set;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Employee;
import com.example.demo.entity.Project;
import com.example.demo.exception.DatabaseAccessException;
import com.example.demo.service.EmployeeService;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

/*
 * Here I have created Rest Controller for perform some rest controller operation
 * */

@RestController
public class WebController {

	@Autowired(required = true)
	private EmployeeService employeeService;
	
	@RequestMapping(path = "/addproject",method = RequestMethod.POST)
	public ResponseEntity<?> addProject(@RequestBody Set<Project> project) throws DatabaseAccessException{
		
		List<Project> saveProject = employeeService.addProjects(project);
		
		Set<String> fields = new HashSet<>();
		fields.add("projectId");
		fields.add("projectName");
		fields.add("projectDuration");
						
		FilterProvider filterProvider = new SimpleFilterProvider()
					.setFailOnUnknownId(false)
					.addFilter("projectFilter", SimpleBeanPropertyFilter.filterOutAllExcept(fields));
			
		MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(saveProject);
		mappingJacksonValue.setFilters(filterProvider);
			
		return new ResponseEntity<MappingJacksonValue>(mappingJacksonValue,HttpStatus.OK);
	}
	
	@RequestMapping(path = "/saveemployee", method = RequestMethod.POST)
	public ResponseEntity<?> saveEmployeetData(@RequestBody List<Employee> employee) throws DatabaseAccessException{				

			Set<String> projectFields = new HashSet<>();
			projectFields.add("projectId");
			
			FilterProvider filterProvider = new SimpleFilterProvider()
					.setFailOnUnknownId(false)
					.addFilter("projectFilter", SimpleBeanPropertyFilter.filterOutAllExcept(projectFields));
			
			List<Employee> employeeList = employeeService.addEmployee(employee);		
			
			MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(employeeList);
			mappingJacksonValue.setFilters(filterProvider);
			
			return new ResponseEntity<MappingJacksonValue>(mappingJacksonValue,HttpStatus.OK);
	}
	
	@RequestMapping(path = "/getallemployee",method = RequestMethod.GET)
	public ResponseEntity<?> getAllEmployee(){
		
		List<Employee> allEmployee = employeeService.getAllEmployee();
		
		Set<String> fields = new HashSet<>();
		fields.add("projectId");
		fields.add("projectName");
		fields.add("projectDuration");
						
		FilterProvider filterProvider = new SimpleFilterProvider()
					.setFailOnUnknownId(false)
					.addFilter("projectFilter", SimpleBeanPropertyFilter.filterOutAllExcept(fields));
			
		MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(allEmployee);
		mappingJacksonValue.setFilters(filterProvider);
			
		return new ResponseEntity<MappingJacksonValue>(mappingJacksonValue,HttpStatus.OK);
	}
	
	@RequestMapping(path = "/getallproject",method = RequestMethod.GET)
	public ResponseEntity<?> getAllProject(){
		
		List<Project> allEmployee = employeeService.getAllProjects();
		
		Set<String> fields = new HashSet<>();
		fields.add("employeeId");
		fields.add("employeeName");
		fields.add("employeeSalary");
						
		FilterProvider filterProvider = new SimpleFilterProvider()
					.setFailOnUnknownId(false)
					.addFilter("employeeFilter", SimpleBeanPropertyFilter.filterOutAllExcept(fields));
			
		MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(allEmployee);
		mappingJacksonValue.setFilters(filterProvider);
			
		return new ResponseEntity<MappingJacksonValue>(mappingJacksonValue,HttpStatus.OK);
	}
	
	@RequestMapping(path = "/getemployee",method = RequestMethod.GET)
	public ResponseEntity<?> getEmployee(@RequestParam("employeeName") String employeeName){
		
		Employee employee = employeeService.getEmployee(employeeName);
		
		Set<String> fields = new HashSet<>();
		fields.add("projectId");
		fields.add("projectName");
		fields.add("projectDuration");
						
		System.out.println("working");
		
		FilterProvider filterProvider = new SimpleFilterProvider()
					.setFailOnUnknownId(false)
					.addFilter("projectFilter", SimpleBeanPropertyFilter.filterOutAllExcept(fields));
			
		MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(employee);
		mappingJacksonValue.setFilters(filterProvider);
			
		return new ResponseEntity<MappingJacksonValue>(mappingJacksonValue,HttpStatus.OK);
	}
	
	@RequestMapping(path = "/updateemployee",method = RequestMethod.PUT)
	public ResponseEntity<?> updateEmployee(@RequestParam("employeeSalary") double employeeSalary,@RequestParam("employeeName") String employeeName) throws DatabaseAccessException{
		
		String updateValue = employeeService.updateEmployeeSalary(employeeSalary, employeeName);
		
		Set<String> fields = new HashSet<>();
						
		FilterProvider filterProvider = new SimpleFilterProvider()
					.setFailOnUnknownId(false)
					.addFilter("projectFilter", SimpleBeanPropertyFilter.filterOutAllExcept(fields));
			
		MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(updateValue);
		mappingJacksonValue.setFilters(filterProvider);
			
		return new ResponseEntity<MappingJacksonValue>(mappingJacksonValue,HttpStatus.OK);
	}
	
	@RequestMapping(path = "/deleteemployee",method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteEmployee(@RequestParam("employeeName") String employeeName) throws DatabaseAccessException{
		
		String deleteValue = employeeService.deleteEmployee(employeeName);
		return new ResponseEntity<>(deleteValue,HttpStatus.OK);
	}
}
