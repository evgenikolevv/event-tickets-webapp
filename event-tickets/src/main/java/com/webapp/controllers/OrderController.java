package com.webapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;
import com.webapp.services.EventService;
import com.webapp.services.OrderService;
import com.webapp.services.UserService;

@Controller
@RequestMapping("/orders")
public class OrderController {
	private final OrderService orderService;
	private final EventService eventService;
	private final UserService userService;
	
	@Autowired
	public OrderController(OrderService orderService, EventService eventService, UserService userService) {
		super();
		this.orderService = orderService;
		this.eventService = eventService;
		this.userService = userService;
	}
	
	
	@PostMapping(path = "/order")
	public String createOrder(@RequestParam(value ="eventId") Long eventId,
							  @RequestParam String username,
							  @RequestParam int tickets) {
		try {
			orderService.createOrder(eventId, username,tickets);
		}catch(IllegalStateException e) {
			throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, e.getMessage(), e );
		}
		return "redirect:/events";
	}
	
	
}
