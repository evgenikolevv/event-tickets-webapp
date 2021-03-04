package com.webapp.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.webapp.entities.Event;
import com.webapp.entities.Order;
import com.webapp.entities.User;
import com.webapp.repositories.EventRepository;
import com.webapp.repositories.OrderRepository;
import com.webapp.repositories.UserRepository;

@Service
public class OrderService {

	private final OrderRepository orderRepository;
	private final EventRepository eventRepository;
	private final UserRepository userRepository;
	
	@Autowired
	public OrderService (OrderRepository orderRepository, EventRepository eventRepository, UserRepository userRepository) {
		this.orderRepository = orderRepository;
		this.eventRepository = eventRepository;
		this.userRepository = userRepository;
	}
	
	public void createOrder(Long eventId, String username, int tickets) {
		Optional<User> user = userRepository.findByUsername(username);
		Optional<Event> event = eventRepository.findById(eventId);
		
		if(user.isEmpty()) {
			throw new IllegalStateException("user with username " + username + " does not exist!");
		}
		
		if(event.isEmpty()) {
			throw new IllegalStateException("Event with id: " + eventId + " does not exist!");
		}
		
		decreaseTickets(tickets,event.get());
		
		Order order = new Order();
		order.setUser(user.get());
		order.setEvent(event.get());
		orderRepository.save(order);
	}
	
	
	public void decreaseTickets (int tickets, Event event) {
		
		int totalTickets = event.getTickets();
		totalTickets = totalTickets - tickets;
		if (totalTickets< 0) {
			throw new IllegalStateException("Not enough tickets");
		}
		
		event.setTickets(totalTickets);
	}
}
