package bai.io.savepic.model.entity;

import bai.io.savepic.model.entity.baseEntity.BaseEntity;
import bai.io.savepic.model.entity.enums.UserRoleEnum;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

@Entity
@Table(name = "roles")
@Data
public class UserRole extends BaseEntity {

	@Enumerated(EnumType.STRING)
	private UserRoleEnum role;

}
