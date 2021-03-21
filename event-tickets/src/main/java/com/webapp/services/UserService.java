package com.webapp.services;




import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.webapp.entities.User;
import com.webapp.repositories.UserRepository;

@Service
public class UserService {

	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
	
	@Autowired
	public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
	}
	
	
	public void createUser(User user) {
		userRepository.findByUsername(user.getUsername())
				.ifPresentOrElse((value) -> {
					throw new IllegalStateException("Username " + user.getUsername() + " is taken!");
				}, () ->{
					user.setPassword(passwordEncoder.encode(user.getPassword()));
					userRepository.save(user);
				});
	}
	
	public User getUser(String username) {
		Optional<User> user = userRepository.findByUsername(username);
		if (user.isEmpty()) {
			throw new IllegalStateException("user with username " + username + " does not exist!");
		}
		return user.get();
	}
	
	public List<User> getUsers(){
		List<User> users = userRepository.findAll();
		if (users.isEmpty()) {
			throw new IllegalStateException("There are no users!");
		}
		
		return users;
	}
	
	
	
}
