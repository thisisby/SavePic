package bai.io.savepic.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import java.io.Serializable;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientUser extends UserEntity implements Serializable {

	@ManyToMany
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<PictureEntity> savedPictures;
}
