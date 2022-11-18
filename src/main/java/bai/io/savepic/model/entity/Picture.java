package bai.io.savepic.model.entity;

import bai.io.savepic.model.entity.baseEntity.BaseEntity;
import lombok.Builder;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "pictures")
@Data
@Builder
public class Picture extends BaseEntity {

	private String label;
	private String imgUrl;
	private String path;
}
