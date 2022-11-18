package bai.io.savepic.model.dto;

import lombok.Data;

import java.util.Date;

@Data
public class EventDto {
	private String title;
	private String creator;
	private Date createdAt;
}
