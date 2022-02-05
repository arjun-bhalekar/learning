package com.learn.rest.webservices.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.learn.rest.webservices.entity.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {
	
	

}
