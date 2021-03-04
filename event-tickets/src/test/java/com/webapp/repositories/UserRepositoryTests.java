package com.webapp.repositories;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import com.webapp.entities.User;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class UserRepositoryTests {

	@Autowired
	private UserRepository repository;
	
	@Autowired
	private TestEntityManager entityManager;
	
	@Test
	public void createUser() {
		User user = new User();
		user.setFirstName("Ivan");
		user.setLastName("Ivanov");
		user.setUsername("ivan20");
		user.setPassword("123");
		User savedUser = repository.save(user);
	
		User existUser = entityManager.find(User.class,savedUser.getId());
		assertThat(existUser.getUsername()).isEqualTo(user.getUsername());
	}
	
	@Test
	public void findUserByUsername() {
		String username = "ivan20";
		Optional <User> user = repository.findByUsername(username);
		assertThat(user.get()).isNotNull();
	}
}
