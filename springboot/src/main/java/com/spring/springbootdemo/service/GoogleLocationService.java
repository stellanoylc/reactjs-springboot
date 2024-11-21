package com.spring.springbootdemo.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.spring.springbootdemo.entity.GoogleLocation;

public interface GoogleLocationService {
	List<GoogleLocation> findAll();
	GoogleLocation findById(int theId);

	GoogleLocation save(GoogleLocation theEmployee);

	 void deleteById(int theId);
	Page<GoogleLocation> getGoogleLocationPage(int page, int size);
}
