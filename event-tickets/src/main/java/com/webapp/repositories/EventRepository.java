package com.webapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.webapp.entities.Event;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {

}
