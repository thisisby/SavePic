package bai.io.savepic.model.entity;

import bai.io.savepic.model.entity.baseEntity.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "pictures")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Picture extends BaseEntity {

	private String label;
	private String imgUrl;

	@JsonIgnore
	@ManyToOne
	private Event event;

}
