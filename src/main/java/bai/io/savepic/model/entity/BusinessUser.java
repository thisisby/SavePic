package bai.io.savepic.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class BusinessUser extends UserEntity{

	@OneToMany(cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
	private Set<PictureEntity> pictureEntities;
}
