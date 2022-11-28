package bai.io.savepic.model.dto;

import bai.io.savepic.model.entity.enums.LocationEnum;
import lombok.Data;

import java.util.Date;

@Data
public class EventDto {
	private String username;
	private String title;
	private String creator;
	private LocationEnum locationEnum;
}
