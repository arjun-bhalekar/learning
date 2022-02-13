package com.learn.testing;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;

import com.learn.testing.entity.Country;
import com.learn.testing.repository.CountryRepository;
import com.learn.testing.service.CountryService;

@SpringBootTest(classes = {ServiceMockTests.class})
@TestMethodOrder(OrderAnnotation.class)
public class ServiceMockTests {
	
	
	private static final Logger logger = LoggerFactory.getLogger(ServiceMockTests.class);

	//this will create mock for jpa repository, so not required actual DB connection i.e. external dependency
	@Mock
	private CountryRepository repository;
	
	//create/inject instance of country service as we need to test all methods present inside the CountryService class
	@InjectMocks
	private CountryService countryService;
	
	
	
	@BeforeAll
	public static void methodBeforeAllTests() {
		logger.info("methodBeforeAllTests() ");
	}
	
	
	//unit test case 1
	@Test
	@Order(value = 1)
	public void test_getAllCountries() {
		
		logger.info("running test_getAllCountries()");
		
		//create test data
		List<Country> myCountries = new ArrayList<Country>();
		myCountries.add(new Country(1, "India", new Date(), new Date()));
		myCountries.add(new Country(2, "US", new Date(), new Date()));
		myCountries.add(new Country(3, "UK", new Date(), new Date()));
		
		//mocking statement : here we are mocking repository.findAll() method and giving our test data to it.
		when(repository.findAll()).thenReturn(myCountries);
		
		List<Country> resultList =  countryService.findAllCountries();
		
		//test condition
		assertEquals(3, resultList.size());
		
	}
	
	@Test
	@Order(2)
	public void test_getCountryById() {
		
		//create test data
		long countryId = 1;
		Optional<Country> countryOptional1 = Optional.of(new Country(countryId, "India", new Date(), new Date()));
		
		//mocking 
		when(repository.findById(1l)).thenReturn(countryOptional1);
		
		assertEquals(countryId,countryService.findBy(1).getId());
		assertEquals(null,countryService.findBy(2));
	}
	
	@Test
	@Order(3)
	public void test_getCountryByName() {
		
		//create test data
		long countryId = 1;
		String countryName= "India";
		Optional<Country> countryOptional1 = Optional.of(new Country(countryId, countryName, new Date(), new Date()));
		
		//mocking 
		when(repository.findByName(countryName)).thenReturn(countryOptional1);
		
		
		assertEquals(countryName,countryService.findBy(countryName).getName());
		assertEquals(null,countryService.findBy("TEST"));
	}
	
	@Test
	@Order(4)
	public void test_createCountry() {
		
		//test data
		Country country = new Country(1, "India", new Date(), new Date());
		
		//mocking
		when(repository.save(country)).thenReturn(country);
		
		assertEquals(country,countryService.create(country));
	}
	
	@Test @Order(5)
	public void test_deleteCountry() {
		
		//test data
	   long countryId = 1;
	   //Country country = new Country(1, "India", new Date(), new Date());
	   countryService.delete(1);
	   verify(repository, times(1)).deleteById(countryId);
	   
	}
	
}

