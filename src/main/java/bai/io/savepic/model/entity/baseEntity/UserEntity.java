package bai.io.savepic.model.entity.baseEntity;

import bai.io.savepic.model.entity.UserRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "users")
@Inheritance(strategy = InheritanceType.JOINED)
@NoArgsConstructor
public class UserEntity extends BaseEntity implements Serializable {

	private String username;
	private String email;
	private String password;

	public UserEntity(String username, String email, String password) {
		this.username = username;
		this.email = email;
		this.password = password;
	}

	@ManyToMany(fetch = FetchType.EAGER)
	private List<UserRole> roles = new ArrayList<>();
}
