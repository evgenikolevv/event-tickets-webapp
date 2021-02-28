package com.webapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.webapp.entities.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

}
