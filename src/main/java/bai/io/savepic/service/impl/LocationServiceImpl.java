package bai.io.savepic.service.impl;

import bai.io.savepic.exception.NotFoundException;
import bai.io.savepic.model.entity.Location;
import bai.io.savepic.model.entity.enums.LocationEnum;
import bai.io.savepic.repository.LocationRepository;
import bai.io.savepic.service.LocationService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class LocationServiceImpl implements LocationService {

	private final LocationRepository locationRepository;

	public LocationServiceImpl(LocationRepository locationRepository) {
		this.locationRepository = locationRepository;
	}

	@Override
	public List<Location> initLocations() {
		List<Location> init = new ArrayList<>();
		if (locationRepository.count() == 0) {
			Arrays.stream(LocationEnum.values()).forEach(locationEnum -> {
				Location location = new Location(locationEnum);
				this.locationRepository.save(location);
				init.add(location);
			});
		}
		return init;
	}

	@Override
	public Location findLocationByName(LocationEnum locationEnum) {
		Optional<Location> location = this.locationRepository.findByLocation(locationEnum);
		if (location.isPresent()) {
			return location.get();
		} else {
			throw new NotFoundException("Location not found");
		}
	}
}
