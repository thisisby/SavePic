package bai.io.savepic.model.entity;

import bai.io.savepic.model.entity.baseEntity.UserEntity;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "user_clients")
@Data
@NoArgsConstructor
public class UserClient extends UserEntity {

	@OneToMany(fetch = FetchType.LAZY)
	private List<Picture> pictures;

	@Builder
	public UserClient(String username, String email, String password) {
		super(username, email, password);
	}

}
