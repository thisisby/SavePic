package bai.io.savepic.controller;

import bai.io.savepic.model.dto.EventDto;
import bai.io.savepic.model.entity.Event;
import bai.io.savepic.model.entity.Location;
import bai.io.savepic.model.entity.enums.LocationEnum;
import bai.io.savepic.service.EventService;
import bai.io.savepic.service.LocationService;
import bai.io.savepic.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/event")
public class EventController {

	private final EventService eventService;
	private final ModelMapper modelMapper;
	private final UserService userService;
	private final LocationService locationService;

	public EventController(EventService eventService, ModelMapper modelMapper, UserService userService, LocationService locationService) {
		this.eventService = eventService;
		this.modelMapper = modelMapper;
		this.userService = userService;
		this.locationService = locationService;
	}


	@GetMapping
	public List<Event> getAllEvents() {
		return eventService.findAll();
	}

	@PostMapping("/save")
	public boolean save(@RequestBody EventDto eventDto) {
		Event event = modelMapper.map(eventDto, Event.class);
		Location location = locationService.findLocationByName(LocationEnum.OSH);
		event.setLocation(location);
		eventService.saveEvent(event);

		return true;
	}

	@PostMapping("/savefor")
	public boolean saveForAdmin(@RequestParam Long id, @RequestParam String username) {
		Event event = eventService.findById(id).get();
		eventService.saveEventForAdmin(event, username);
		return true;
	}
}
