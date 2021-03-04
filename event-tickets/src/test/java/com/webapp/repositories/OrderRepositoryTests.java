package com.webapp.repositories;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import com.webapp.entities.Event;
import com.webapp.entities.Order;
import com.webapp.entities.User;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class OrderRepositoryTests {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private EventRepository eventRepository;
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private TestEntityManager entityManager;
	
	@Test
	public void createOrder() {
		String username = "ivan20";
		Optional <User> user = userRepository.findByUsername(username);
		Optional<Event> event = eventRepository.findById(1L);
		Order order = new Order();
		order.setUser(user.get());
		order.setEvent(event.get());
		Order savedOrder = orderRepository.save(order);
		
		Order existOrder = entityManager.find(Order.class, savedOrder.getId());
		assertThat(existOrder.getId()).isEqualTo(order.getId());
	}
}
