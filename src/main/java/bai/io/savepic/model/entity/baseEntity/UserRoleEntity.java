package bai.io.savepic.model.entity.baseEntity;

import bai.io.savepic.model.entity.enums.UserRoleEnum;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Data
@Entity
public class UserRoleEntity extends BaseEntity{

	@Enumerated(EnumType.STRING)
	private UserRoleEnum role;
}
