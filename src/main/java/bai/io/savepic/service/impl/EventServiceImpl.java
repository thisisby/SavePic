package bai.io.savepic.service.impl;

import bai.io.savepic.model.entity.Event;
import bai.io.savepic.model.entity.UserAdmin;
import bai.io.savepic.model.entity.enums.LocationEnum;
import bai.io.savepic.repository.EventRepository;
import bai.io.savepic.service.EventService;
import bai.io.savepic.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class EventServiceImpl implements EventService {

	private final UserService userService;
	private final EventRepository eventRepository;

	@Autowired
	public EventServiceImpl(UserService userService, EventRepository eventRepository) {
		this.userService = userService;
		this.eventRepository = eventRepository;
	}

	@Override
	public Event findByName(LocationEnum locationEnum) {
		return null;
	}

	@Override
	public Optional<Event> findById(Long id) {
		return eventRepository.findById(id);
	}

	@Override
	public List<Event> findAll() {
		return (List<Event>) eventRepository.findAll();
	}

	@Override
	public void saveEvent(Event event) {
		eventRepository.save(event);
	}

	@Override
	public boolean saveEventForAdmin(Event event, String username) {
		UserAdmin userAdmin = (UserAdmin) userService.findUserEntityByUsername(username);
		Optional<Event> eventById = eventRepository.findById(event.getId());
		List<Event> events = userAdmin.getEvent();
		events.add(eventById.get());
		return true;
	}
}
