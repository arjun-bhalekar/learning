package com.learn.rest.webservices.user;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.learn.rest.webservices.entity.Post;
import com.learn.rest.webservices.entity.User;

@Component
public class UserDaoService {

	private static List<User> users = new ArrayList<>();
	private static int usersCount = 3;
	private static List<Post> posts = new ArrayList<>();
	
	static {
		users.add(new User(1, "Adam", new Date()));
		users.add(new User(2, "Eve", new Date()));
		users.add(new User(3, "Jack", new Date()));
		
//		posts.add(new Post(10, "Going to Home Town", "After 3 years finally going...", 1));
//		posts.add(new Post(11, "Happy Birthday Jack", "Wish you happy birthday. Enjoy ur day.", 1));
//		posts.add(new Post(12, "Bad days", "As sufering from cold & cough", 2));
//		
		
	}
	
	

	public List<User> findAll() {
		return users;
	}

	public User saveUser(User user) {
		if (user.getId() == null) {
			user.setId(++usersCount);
		}
		users.add(user);
		return user;
	}

	public User findUserBy(int id) {
		for (User user : users) {
			if (user.getId() == id) {
				return user;
			}
		}
		return null;
	}
	
	public User deleteUser(int id) {
		
		Iterator<User> iterator =  users.iterator();
		while(iterator.hasNext()) {
			User user = iterator.next();
			if(user.getId()==id) {
				iterator.remove();
				return user;
			}
		}
		return null;
	}
	
//	public List<Post> findPosts(int userId) {
//
//		return posts.stream()
//				.filter(p -> p.getUserId() == userId)
//				.collect(Collectors.toList());
//	}
//	
//	public Post findPost(int postId) {
//		List<Post> postList =  posts.stream().filter(p -> p.getId()==postId).collect(Collectors.toList());
//		return postList.get(0);
//	}

}
