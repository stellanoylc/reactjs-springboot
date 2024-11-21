package com.spring.springbootdemo.rest;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.spring.springbootdemo.entity.GoogleLocation;
import com.spring.springbootdemo.service.GoogleLocationService;


@RestController
@CrossOrigin(origins = "http://localhost:3000") 
@RequestMapping("/api2")
public class GoogleLocationRestController {
	
	private GoogleLocationService googleLocationService;
	
    public GoogleLocationRestController(GoogleLocationService theGoogleLocationService) {
    	googleLocationService = theGoogleLocationService;
    }

	    // expose "/employees" and return a list of employees
	    @GetMapping("/googleLocations")
	    public List<GoogleLocation> findAll() {
	        return googleLocationService.findAll();
	    }

	    @GetMapping("/googleLocations/{employeeId}")
	    public GoogleLocation getGoogleLocation(@PathVariable int employeeId) {

	    	GoogleLocation theGoogleLocation = googleLocationService.findById(employeeId);

	        if (theGoogleLocation == null) {
	            throw new RuntimeException("theGoogleLocation id not found - " + employeeId);
	        }

	        return theGoogleLocation;
	    }

	    // add mapping for POST /employees - add new employee

	    
	    @PostMapping("/googleLocations")
	    public GoogleLocation addEmployee(@RequestBody GoogleLocation theGoogleLocation) {

	        // also just in case they pass an id in JSON ... set id to 0
	        // this is to force a save of new item ... instead of update

	    	theGoogleLocation.setId(0);

	    	GoogleLocation dbGoogleLocation = googleLocationService.save(theGoogleLocation);

	        return dbGoogleLocation;
	    }

	    // add mapping for PUT /employees - update existing employee

	    @PutMapping("/googleLocations")
	    public GoogleLocation updateGoogleLocation(@RequestBody GoogleLocation theGoogleLocation) {

	    	GoogleLocation dbGoogleLocation = googleLocationService.save(theGoogleLocation);

	        return dbGoogleLocation;
	    }

	    // add mapping for DELETE /employees/{employeeId} - delete employee

	    @DeleteMapping("/googleLocations/{employeeId}")
	    public String deleteGoogleLocation(@PathVariable int employeeId) {

	    	GoogleLocation tempGoogleLocation = googleLocationService.findById(employeeId);

	        // throw exception if null

	        if (tempGoogleLocation == null) {
	            throw new RuntimeException("GoogleLocation id not found - " + employeeId);
	        }

	        googleLocationService.deleteById(employeeId);

	        return "Deleted employee id - " + employeeId;
	    }
	    
	    
	    @GetMapping("/allGoogleLocation")
	    public Page<GoogleLocation> getAllGoogleLocation(@RequestParam(defaultValue = "0") int page,
	                                  @RequestParam(defaultValue = "10") int size) {
	        return googleLocationService.getGoogleLocationPage(page, size);
	    }
}
