package com.learn.testing.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.learn.testing.entity.Country;
import com.learn.testing.service.CountryService;

@RestController
@RequestMapping("/countries")
public class CountryController {

	private static final Logger logger = LoggerFactory.getLogger(CountryController.class);
	
	@Autowired
	private CountryService service;

	
	//Get all countries 		:  	GET - /countries
	//Get countries by id 		: 	GET - /countries/{id}
	//Get countries by name		:	GET - /counties/{name}
	//Create Country			:	POST - /countries
	//Update Country			:	PUT	 - /countries/{id}
	//Delete country by id		:	DELETE - /countries/{id}
	
	@GetMapping
	public List<Country> getCountries(){
		
		logger.info("inside getCountries()");
		return service.findAllCountries();
	}
	
	@GetMapping("/{id}")
	public Country getCountryBy(@PathVariable long id) {
		return service.findBy(id);
	}
	
	@GetMapping("/byname")
	public Country getCountryBy(@RequestParam String name) {
		return service.findBy(name);
	}
	
	@PostMapping
	public Country createCountry(@RequestBody Country country) {
		country.setId(0);
		return service.create(country);
	}
	
	@PutMapping("/{id}")
	public Country updateCountry(@PathVariable long id,@RequestBody Country country) {
		if(service.findBy(id)==null) {
			throw new RuntimeException("not found");
		}
		
		country.setId(id);
		return service.create(country);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<HttpStatus> deleteCountry(@PathVariable long id) {
		service.delete(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
}
