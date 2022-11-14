package bai.io.savepic.model.entity;

import bai.io.savepic.model.entity.baseEntity.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class PictureEntity extends BaseEntity {

	private String title;
	private String label;
	private String imgUrl;

	@ManyToOne
	private EventEntity event;
}
