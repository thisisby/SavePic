package bai.io.savepic.model.entity;

import bai.io.savepic.model.entity.baseEntity.UserEntity;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.beans.factory.annotation.Required;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "user_admins")
@Data
@NoArgsConstructor
public class UserAdmin extends UserEntity {

	private String fullName;

	@OneToMany(cascade = CascadeType.REMOVE)
	private List<Event> event;

	@Builder
	public UserAdmin(String username, String email, String password, String fullName) {
		super(username, email, password);
		this.fullName = fullName;
	}
}
