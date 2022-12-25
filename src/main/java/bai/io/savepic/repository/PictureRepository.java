package bai.io.savepic.repository;

import bai.io.savepic.model.entity.Event;
import bai.io.savepic.model.entity.Picture;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PictureRepository extends CrudRepository<Picture, Long> {

	Optional<Picture> findByLabel(String label);

	List<Picture> findAllByLabel(String label);

	List<Picture> findPicturesByEventId(Long id);

}
