package com.spring.springbootdemo.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.spring.springbootdemo.entity.Employee;

public interface EmployeeService {
	List<Employee> findAll();
	 Employee findById(int theId);

	 Employee save(Employee theEmployee);

	 void deleteById(int theId);
	Page<Employee> getEmployeesPage(int page, int size);
}
