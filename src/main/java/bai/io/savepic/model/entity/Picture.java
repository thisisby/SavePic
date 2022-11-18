package bai.io.savepic.model.entity;

import bai.io.savepic.model.entity.baseEntity.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Table;

@Entity
@Table(name = "pictures")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Picture extends BaseEntity {

	private String label;
	private String imgUrl;
	@Column(length = 50000000)
	private byte[] picByte;

}
