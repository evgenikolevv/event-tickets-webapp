package com.webapp.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.webapp.entities.Location;
import com.webapp.repositories.LocationRepository;

@Service
public class LocationService {
	
	private final LocationRepository locationRepository;
	
	@Autowired
	public LocationService(LocationRepository locationRepository) {
		this.locationRepository = locationRepository;
	}
	
	public List<Location> getLocations(){
		List<Location> locations = locationRepository.findAll();
		if(locations.isEmpty()) {
			throw new IllegalStateException("There are no locations!");
		}
		
		return locations;
	}
	
	public Location getLocation(String name) {
		Optional<Location> location = locationRepository.findByName(name);
		if (location.isEmpty()) {
			throw new IllegalStateException("Location with name " + name + " does not exist!");
		}
		
		return location.get();
	}
}
