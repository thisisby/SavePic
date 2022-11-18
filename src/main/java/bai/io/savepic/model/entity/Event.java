package bai.io.savepic.model.entity;

import bai.io.savepic.model.entity.baseEntity.BaseEntity;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "events")
@Data
public class Event extends BaseEntity {

	private String title;
	private String creator;
	private Date createdAt;

	@ManyToOne
	private Location location;
}
