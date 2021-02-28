package com.webapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.webapp.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

	public User findByUsername(String username);

}
