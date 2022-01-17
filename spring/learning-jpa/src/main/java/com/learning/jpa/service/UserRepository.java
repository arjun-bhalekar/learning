package com.learning.jpa.service;

import org.springframework.data.jpa.repository.JpaRepository;

import com.learning.jpa.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
