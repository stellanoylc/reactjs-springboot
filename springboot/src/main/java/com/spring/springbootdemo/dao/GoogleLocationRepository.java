package com.spring.springbootdemo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.spring.springbootdemo.entity.GoogleLocation;

public interface GoogleLocationRepository extends JpaRepository<GoogleLocation, Integer>, PagingAndSortingRepository <GoogleLocation, Integer>{

}
