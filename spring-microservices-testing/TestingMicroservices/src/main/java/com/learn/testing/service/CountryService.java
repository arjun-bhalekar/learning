package com.learn.testing.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learn.testing.entity.Country;
import com.learn.testing.repository.CountryRepository;

@Service
public class CountryService {
	
	@Autowired
	private CountryRepository repository;
	
	public List<Country> findAllCountries(){
		return repository.findAll();
	}
	
	public Country findBy(long id) {
	  	
		Optional<Country> optional = repository.findById(id);
		
		if(optional.isPresent())
			return optional.get();
		else
			return null;
		
	}
	
	public Country findBy(String name) {
	  	
		Optional<Country> optional = repository.findByName(name);
		
		if(optional.isPresent())
			return optional.get();
		else
			return null;
		
	}
	
	public Country create(Country country) {
		
		return repository.save(country);
	}
	
	public void delete(long id) {
		repository.deleteById(id);
	}

}
