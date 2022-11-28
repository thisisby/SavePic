package bai.io.savepic.service;

import bai.io.savepic.model.entity.Event;
import bai.io.savepic.model.entity.enums.LocationEnum;

import java.util.List;
import java.util.Optional;

public interface EventService {

	Event findByName(LocationEnum locationEnum);

	Optional<Event> findById(Long id);

	List<Event> findAll();

	void saveEvent(Event event, String username);

	boolean saveEventForAdmin(Event event, String username);
}
