package bai.io.savepic.model.entity.baseEntity;

import lombok.Data;

import javax.persistence.*;

@MappedSuperclass
@Entity
@Data
public abstract class BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(columnDefinition = "serial", nullable = false, updatable = false)
	protected Long id;


}
