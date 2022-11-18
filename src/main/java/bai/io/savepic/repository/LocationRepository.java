package bai.io.savepic.repository;

import bai.io.savepic.model.entity.Location;
import bai.io.savepic.model.entity.enums.LocationEnum;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LocationRepository extends CrudRepository<Location, Long> {

	Optional<Location> findByLocation(LocationEnum location);
}
