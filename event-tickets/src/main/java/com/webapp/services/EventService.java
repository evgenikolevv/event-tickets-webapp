package com.webapp.services;


import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.webapp.entities.Event;
import com.webapp.entities.Location;
import com.webapp.repositories.EventRepository;
import com.webapp.repositories.LocationRepository;

@Service
public class EventService {

	private final EventRepository eventRepository;
	private final LocationRepository locationRepository;
	
	@Autowired
	public EventService(EventRepository eventRepository, LocationRepository locationRepository) {
		this.eventRepository = eventRepository;
		this.locationRepository = locationRepository;
	}
	
	
	public List<Event> getEvents(){
		List<Event> events = eventRepository.findAll();
		if (events.isEmpty()) {
			throw new IllegalStateException("There are no events!");
		}
		
		return events;
	}
	
	public Event getEvent(Long id) {
		Optional<Event> event = eventRepository.findById(id);
		
		if (event.isEmpty()) {
			throw new IllegalStateException("Event with id: " + id + " does not exist!");
		}
		
		return event.get();
	}
	
	public void createEvent(Event event) {
		
		eventRepository.save(event);
	}
	
	@Transactional
	public void updateEvent(Event updatedEvent) {
		Optional<Event> event = eventRepository.findById(updatedEvent.getId());
		Optional<Location> location = locationRepository.findById(updatedEvent.getLocation().getId());
		
		if (event.isEmpty()) {
			throw new IllegalStateException("This event does not exist!");
		}
		
		if (updatedEvent.getName() != null) {
			event.get().setName(updatedEvent.getName());
		}
		
		if (updatedEvent.getDate() != null) {
			event.get().setDate((updatedEvent.getDate()));
		
		}
		
		if (location.isPresent()) {
			event.get().setLocation(location.get());
		}
		
		if (updatedEvent.getTickets() != 0) {
			event.get().setTickets(updatedEvent.getTickets());
		}
		
		if (updatedEvent.getPrice() != 0) {
			event.get().setPrice(updatedEvent.getPrice());
		}
	}
	
}
