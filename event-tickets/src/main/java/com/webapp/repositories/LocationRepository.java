package com.webapp.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.webapp.entities.Location;

@Repository
public interface LocationRepository extends JpaRepository<Location, Long> {

	public Optional<Location> findByName(String name);
}
