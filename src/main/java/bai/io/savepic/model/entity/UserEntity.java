package bai.io.savepic.model.entity;

import bai.io.savepic.model.entity.baseEntity.BaseEntity;
import bai.io.savepic.model.entity.baseEntity.UserRoleEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserEntity extends BaseEntity implements Serializable {

	private String username;
	private String email;

	@ManyToMany(fetch = FetchType.EAGER)
	private List<UserRoleEntity> roles = new ArrayList<>();
	private String password;
}
