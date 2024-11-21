package com.spring.springbootdemo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.spring.springbootdemo.dao.EmployeeDAO;
import com.spring.springbootdemo.dao.EmployeeRepository;
import com.spring.springbootdemo.entity.Employee;

import jakarta.transaction.Transactional;

@Service
public class EmployeeServiceImpl implements EmployeeService{
	private EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository theEmployeeRepository) {
        employeeRepository = theEmployeeRepository;
    }

    @Override
    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }
    
    @Override
    public Employee findById(int theId) {
    	 Optional<Employee> result = employeeRepository.findById(theId);

         Employee theEmployee = null;

         if (result.isPresent()) {
             theEmployee = result.get();
         }
         else {
             // we didn't find the employee
             throw new RuntimeException("Did not find employee id - " + theId);
         }

         return theEmployee;
    }
    
    @Override
    public Employee save(Employee theEmployee) {
        return employeeRepository.save(theEmployee);
    }

    @Override
    public void deleteById(int theId) {
        employeeRepository.deleteById(theId);
    }
    
    
    public Page<Employee> getEmployeesPage(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return employeeRepository.findAll(pageable);
    }
}
