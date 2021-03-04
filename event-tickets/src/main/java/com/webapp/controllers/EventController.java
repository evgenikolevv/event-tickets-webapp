package com.webapp.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.ModelAndView;
import com.webapp.entities.Event;
import com.webapp.services.EventService;
import com.webapp.services.LocationService;

@Controller
@RequestMapping("/events")
public class EventController {

	private final EventService eventService;
	private final LocationService locationService;
	
	@Autowired
	public EventController(EventService eventService, LocationService locationService) {
		this.eventService = eventService;
		this.locationService = locationService;
	}
	
	@GetMapping
	public String viewEventsPage(Model model) {
		try {
			List<Event> events = eventService.getEvents();
			model.addAttribute("listEvents",events);
		}catch(IllegalStateException e) {
			throw new ResponseStatusException(
					HttpStatus.NOT_FOUND, e.getMessage(), e
					);
		}
		
		return "events";
	}
	
	@GetMapping(path = "{id}")
	public String viewEvent(@PathVariable("id") Long eventId, Model model) {
		try {
			Event event = eventService.getEvent(eventId);
			model.addAttribute("event",event);
		}catch (IllegalStateException e) {
			throw new ResponseStatusException(
					HttpStatus.NOT_FOUND, e.getMessage(), e
					);
		}
		
		return "event";
	}
	
	@GetMapping("/edit/{id}")
	public ModelAndView viewEventEditPage(@PathVariable("id") Long eventId) {
		ModelAndView modelAndView = new ModelAndView("event-edit");
		modelAndView.addObject("event", eventService.getEvent(eventId));
		modelAndView.addObject("locations", locationService.getLocations());
		return modelAndView;
	}
	
	@PostMapping("/update")
	public String editEvent(@ModelAttribute Event event){
		try {
			eventService.updateEvent(event);
		}catch(IllegalStateException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e );
		}
		return "redirect:/events";
	}
	
}
