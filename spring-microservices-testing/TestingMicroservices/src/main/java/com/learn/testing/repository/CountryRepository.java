package com.learn.testing.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.learn.testing.entity.Country;

public interface CountryRepository extends JpaRepository<Country,Long> {

	public Optional<Country> findByName(String name);
}
