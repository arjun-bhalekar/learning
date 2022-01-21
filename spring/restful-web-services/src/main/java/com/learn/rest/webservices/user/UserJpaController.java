package com.learn.rest.webservices.user;

import java.net.URI;
import java.util.List;
import java.util.Optional;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.learn.rest.webservices.entity.Post;
import com.learn.rest.webservices.entity.User;
import com.learn.rest.webservices.exception.PostNotFoundException;
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
@RestController()
@RequestMapping("/jpa")
public class UserJpaController {

	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PostRepository postRepository;

	@GetMapping("/users")
	public List<User> retrieAllUsers() {
		return userRepository.findAll();
	}

	@GetMapping("/users/{id}")
	public EntityModel<User> retrieveUser(@PathVariable int id) {
		Optional<User> optional = userRepository.findById(id);
		if (!optional.isPresent()) {
			throw new UserNotFoundException("User not found with id-" + id);
		}
		EntityModel<User> entityModel = EntityModel.of(optional.get());
		
		//now build /users link and add it to entityModel
		
		WebMvcLinkBuilder usersLinkBuilder = linkTo(methodOn(this.getClass()).retrieAllUsers()); 
		entityModel.add(usersLinkBuilder.withRel("all-users"));
		return entityModel;
	}

	// create user - input : user details , output - URI of created resource
	@PostMapping("/users")
	public ResponseEntity<Object> createUser(@Valid @RequestBody User user) {
		User savedUser = userRepository.save(user);

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
		userRepository.deleteById(id);
		return ResponseEntity.noContent().build();
	}
	
	
	@GetMapping("/users/{id}/posts")
	public List<Post> getAllPostForUser(@PathVariable int id) {

		Optional<User> optional = userRepository.findById(id);
		if (!optional.isPresent()) {
			throw new UserNotFoundException("User not found with id-" + id);
		}
		return optional.get().getPosts();

	}
	
	@PostMapping("/users/{id}/posts")
	public ResponseEntity<Object> createPost(@PathVariable int id,@Valid @RequestBody Post post) {
		Optional<User> optional = userRepository.findById(id);
		if (!optional.isPresent()) {
			throw new UserNotFoundException("User not found with id-" + id);
		}
		
		User user = optional.get();
		post.setUser(user);
		postRepository.save(post);
		
		// return created User URI - /user/id
		//e.g. HATEOAS - link generation in response
		URI location = ServletUriComponentsBuilder
									.fromCurrentRequest()
									.path("/{id}")
									.buildAndExpand(post.getId())	.toUri();

		return ResponseEntity.created(location).build();
	}
	
	@GetMapping("/users/{id}/posts/{post_id}")
	public EntityModel<Post> retrieveSinglePost(@PathVariable int id, @PathVariable int post_id) {
		Optional<User> optional = userRepository.findById(id);
		if (!optional.isPresent()) {
			throw new UserNotFoundException("User not found with id-" + id);
		}
		
		Optional<Post> optionalPost = postRepository.findById(post_id);
		if (!optionalPost.isPresent()) {
			throw new PostNotFoundException("Post not found with post id-" + post_id);
		}
		
		EntityModel<Post> entityModel = EntityModel.of(optionalPost.get());
		
		WebMvcLinkBuilder postLinkBuilder = linkTo(methodOn(this.getClass()).getAllPostForUser(id)); 
		entityModel.add(postLinkBuilder.withRel("all-posts"));
		return entityModel;
	}
}
