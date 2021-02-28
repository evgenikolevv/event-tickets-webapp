package com.webapp.repositories;

import static org.assertj.core.api.Assertions.assertThat;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;
import com.webapp.entities.Event;
import com.webapp.entities.Location;

@DataJpaTest
@AutoConfigureTestDatabase( replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class EventRepositoryTests {

	@Autowired
	private EventRepository eventRepository;
	
	@Autowired
	private LocationRepository locationRepository;
	@Autowired
	private TestEntityManager entityManager;
	
	@Test
	public void createEvent() throws ParseException {
		Event event = new Event();
		Location location = locationRepository.findByName("Burgas");
		event.setName("Software Development");
		event.setLocation(location);
		event.setTickets(20);
		event.setPrice(25.00);
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		event.setDate(format.parse("2021-05-24"));
		Event savedEvent = eventRepository.save(event);
		
		Event existEvent = entityManager.find(Event.class,savedEvent.getId());
		assertThat(existEvent).isEqualTo(event);

	}
}
