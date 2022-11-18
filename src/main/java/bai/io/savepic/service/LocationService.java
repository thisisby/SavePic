package bai.io.savepic.service;

import bai.io.savepic.model.entity.Location;
import bai.io.savepic.model.entity.enums.LocationEnum;

import java.util.List;

public interface LocationService{
	List<Location> initLocations();

	Location findLocationByName(LocationEnum locationEnum);
}
