package bai.io.savepic.controller;

import bai.io.savepic.model.dto.EventDto;
import bai.io.savepic.model.entity.Event;
import bai.io.savepic.model.entity.Location;
import bai.io.savepic.model.entity.Picture;
import bai.io.savepic.repository.EventRepository;
import bai.io.savepic.service.EventService;
import bai.io.savepic.service.LocationService;
import bai.io.savepic.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/event")
@CrossOrigin(origins = "http://localhost:3000")
public class EventController {

	private final EventService eventService;
	private final ModelMapper modelMapper;
	private final UserService userService;
	private final LocationService locationService;
	private final EventRepository eventRepository;

	public EventController(EventService eventService, ModelMapper modelMapper, UserService userService, LocationService locationService, EventRepository eventRepository) {
		this.eventService = eventService;
		this.modelMapper = modelMapper;
		this.userService = userService;
		this.locationService = locationService;
		this.eventRepository = eventRepository;
	}


	@GetMapping
	public List<Event> getAllEvents() {
		return eventService.findAll();
	}

	@PostMapping("/save")
	public boolean save(@RequestBody EventDto eventDto) {
		String username = eventDto.getUsername();
		Event event = modelMapper.map(eventDto, Event.class);
		Location location = locationService.findLocationByName(eventDto.getLocationEnum());
		event.setLocation(location);
		event.setCreatedAt(LocalDate.now());
		eventService.saveEvent(event, username);

		return true;
	}

	@PostMapping("/savefor")
	public boolean saveForAdmin(@RequestParam Long id, @RequestParam String username) {
		Event event = eventService.findById(id).get();
		eventService.saveEventForAdmin(event, username);
		return true;
	}
}
