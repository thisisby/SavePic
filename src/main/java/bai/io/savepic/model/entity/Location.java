package bai.io.savepic.model.entity;

import bai.io.savepic.model.entity.baseEntity.BaseEntity;
import bai.io.savepic.model.entity.enums.LocationEnum;
import jdk.jfr.DataAmount;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "locations")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Location extends BaseEntity {

	@Column(unique = true)
	@Enumerated(EnumType.STRING)
	private LocationEnum location;

}
