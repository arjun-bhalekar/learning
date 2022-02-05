package com.learn.rest.webservices.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.learn.rest.webservices.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
	
	

}
