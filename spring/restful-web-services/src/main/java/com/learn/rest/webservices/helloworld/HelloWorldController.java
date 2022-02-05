package com.learn.rest.webservices.helloworld;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

//Controller
@RestController
public class HelloWorldController {
	
	@Autowired
	private MessageSource messageSource; 

	// @RequestMapping(method = RequestMethod.GET, path = "/hello-world")
	@GetMapping(path = "/hello-world")
	public String helloWorld() {
		return "Hello World !";
	}
	
	@GetMapping(path = "/hello-world-bean")
	public HelloWorldBean helloWorldBean() {
		return new HelloWorldBean("Hello World Bean !");
	}
	
	@GetMapping(path = "/hello-world/path-variable/{name}")
	public HelloWorldBean helloWorldPathVariable(@PathVariable String name) {
		
		return new HelloWorldBean("Hello World PathVariable : "+name);
	}

	
	
	//e.g. Implementations of I18N in REST Web Service
	@GetMapping(path = "/hello-world-i18n")
	public String helloWorldInternationalization(
	/* @RequestHeader(name = "Accept-Language", required = false) Locale locale */) {

		//return messageSource.getMessage("hello.world", null, locale);
		//OR for generic with all methods supports
		return messageSource.getMessage("hello.world", null, LocaleContextHolder.getLocale());
	}
	
}
