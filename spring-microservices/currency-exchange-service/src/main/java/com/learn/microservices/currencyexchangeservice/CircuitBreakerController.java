package com.learn.microservices.currencyexchangeservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;

@RestController
public class CircuitBreakerController {
	
	
	private static final Logger logger = LoggerFactory.getLogger(CircuitBreakerController.class);

	
	@GetMapping("/sample-api")
	//@Retry(name = "sample-api", fallbackMethod = "hardCodedResponse")
	//@CircuitBreaker(name = "default", fallbackMethod = "hardCodedResponse")
	@RateLimiter(name = "sample-api")
	public String sampleApi() {
		
		logger.info("sample-api call recevied");
		
		//ResponseEntity<String> dummyResponseEntity =  new RestTemplate().getForEntity("http://localhost:8080/dummy-api", String.class);
		//return dummyResponseEntity.getBody();
	
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "sample response";
	}
	
	public String hardCodedResponse(Exception ex) {
		return "fallback-response- internal server error";
	}
}
