package com.learning.jpa;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.learning.jpa.entity.User;
import com.learning.jpa.service.UserRepository;

@Component
public class UserRepositoryCommandLineRunner implements CommandLineRunner {

	private static final Logger log = LoggerFactory.getLogger(UserRepositoryCommandLineRunner.class);
	
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public void run(String... args) throws Exception {
		
		User user = new User("Arjun", "Employee");
		userRepository.save(user);
		log.info("New user is created : "+user);
		
		userRepository.findAll().forEach( u -> log.info(u.toString()));
		Optional<User> userWithIdTwo =   userRepository.findById(2l);
		log.info("userWithIdTwo :"+userWithIdTwo.get());
		
	}
}
