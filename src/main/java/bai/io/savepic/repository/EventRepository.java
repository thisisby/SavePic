package bai.io.savepic.repository;

import bai.io.savepic.model.entity.Event;
import bai.io.savepic.model.entity.Picture;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface EventRepository extends CrudRepository<Event, Long> {

	Optional<Event> findByTitle(String title);

	Optional<Event> findByCreatedAt(Date date);
		}
