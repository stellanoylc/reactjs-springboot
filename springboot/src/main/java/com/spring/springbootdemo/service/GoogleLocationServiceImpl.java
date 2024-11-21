package com.spring.springbootdemo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.spring.springbootdemo.dao.GoogleLocationRepository;
import com.spring.springbootdemo.entity.GoogleLocation;

@Service
public class GoogleLocationServiceImpl implements GoogleLocationService{
	private GoogleLocationRepository googleLocationRepository;

    @Autowired
    public GoogleLocationServiceImpl(GoogleLocationRepository theGoogleLocationRepository) {
    	googleLocationRepository = theGoogleLocationRepository;
    }

    @Override
    public List<GoogleLocation> findAll() {
        return googleLocationRepository.findAll();
    }
    
    @Override
    public GoogleLocation findById(int theId) {
    	 Optional<GoogleLocation> result = googleLocationRepository.findById(theId);

    	 GoogleLocation  theGoogleLocation = null;

         if (result.isPresent()) {
        	 theGoogleLocation = result.get();
         }
         else {
             // we didn't find the employee
             throw new RuntimeException("Did not find theGoogleLocation id - " + theId);
         }

         return theGoogleLocation;
    }
    
    @Override
    public GoogleLocation save(GoogleLocation theGoogleLocation) {
        return googleLocationRepository.save(theGoogleLocation);
    }

    @Override
    public void deleteById(int theId) {
    	googleLocationRepository.deleteById(theId);
    }
    
 

	public Page<GoogleLocation> getGoogleLocationPage(int page, int size) {
		Pageable pageable = PageRequest.of(page, size);
        return googleLocationRepository.findAll(pageable);
	}
}
