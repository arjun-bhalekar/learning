package com.learn.rest.webservices.user;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.learn.rest.webservices.entity.Post;
import com.learn.rest.webservices.entity.User;
import com.learn.rest.webservices.exception.UserNotFoundException;

/**
 * This can be also called as 'UserResource'.<br>
 * which is responsible for exposing the REST web services	 related to User Resource	<br><br>
 * 
 *  - Retrieve all Users 	-	GET		/users			<br>
 *  - Retrieve one User		-	GET		/users/{id}		<br>
 *  - Create a User			-	POST	/users			<br>
 *  - Delete a User			- DELETE	/users/{id}		<br><br>
 *  
 *  - Retrieve all posts for a User	-	GET		/users/{id}/posts 	<br>
 *  - Create a post for a User		-	POST	/users/{id}/posts	<br>
 *  - Retrieve details of a post	-	GET		/users/{id}/posts/{post_id}	<br><br>
 *  
 * @author ArjunB
 *
 */
@RestController
public class UserController {

	@Autowired
	private UserDaoService service;

	@GetMapping("/users")
	public List<User> retrieAllUsers() {
		return service.findAll();
	}

	@GetMapping("/users/{id}")
	public EntityModel<User> retrieveUser(@PathVariable int id) {
		User user = service.findUserBy(id);
		if (user == null)
			throw new UserNotFoundException("User not found with id-" + id);
		
		EntityModel<User> entityModel = EntityModel.of(user);
		
		//now build /users link and add it to entityModel
		
		WebMvcLinkBuilder usersLinkBuilder = linkTo(methodOn(this.getClass()).retrieAllUsers()); 
		entityModel.add(usersLinkBuilder.withRel("all-users"));
		return entityModel;
	}

	// create user - input : user details , output - URI of created resource
	@PostMapping("/users")
	public ResponseEntity<Object> createUser(@Valid @RequestBody User user) {
		User savedUser = service.saveUser(user);

		// return created User URI - /user/id
		//e.g. HATEOAS - link generation in response
		URI location = ServletUriComponentsBuilder
									.fromCurrentRequest()
									.path("/{id}")
									.buildAndExpand(savedUser.getId())	.toUri();

		return ResponseEntity.created(location).build();
	}
	
	@DeleteMapping("/users/{id}")
	public ResponseEntity<Object> deleteUser(@PathVariable int id) {
		User user = service.deleteUser(id);
		if(user==null)
			throw new UserNotFoundException("User not found with id-" + id);
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping("/users/{id}/posts")
	public List<Post> getAllPostForUser(@PathVariable int id){
		return null;
		//return service.findPosts(id);
	}
	
	@GetMapping("/users/{id}/posts/{post_id}")
	public Post getPostForUser(@PathVariable int id, @PathVariable("post_id") int postId){
		return null;
		//return service.findPosts(id).stream().filter(p -> p.getId()==postId).collect(Collectors.toList()).get(0);
	}

}
