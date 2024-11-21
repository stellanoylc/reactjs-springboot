package com.spring.springbootdemo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.spring.springbootdemo.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer>, PagingAndSortingRepository <Employee, Integer> {

}
