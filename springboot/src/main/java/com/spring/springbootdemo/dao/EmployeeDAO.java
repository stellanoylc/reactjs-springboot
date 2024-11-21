package com.spring.springbootdemo.dao;

import java.util.List;

import com.spring.springbootdemo.entity.Employee;

public interface EmployeeDAO {

	List <Employee> findAll();
	
	 Employee findById(int theId);

	    Employee save(Employee theEmployee);

	    void deleteById(int theId);
}
