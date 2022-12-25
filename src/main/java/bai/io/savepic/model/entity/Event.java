package bai.io.savepic.model.entity;

import bai.io.savepic.model.entity.baseEntity.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "events")
@Data
@NoArgsConstructor
public class Event extends BaseEntity {

	private String title;
	private String creator;
	private LocalDate createdAt;

	@ManyToOne
	private Location location;

	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY)
	private List<Picture> pictures;
}
