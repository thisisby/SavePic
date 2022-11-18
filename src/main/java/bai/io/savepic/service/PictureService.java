package bai.io.savepic.service;

import bai.io.savepic.model.entity.Picture;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.util.List;
import java.util.stream.Stream;

public interface PictureService {

	Picture findById(Long id);

	List<Picture> findAll();

	Picture savePicture(Picture picture);

	Path load(String filename);

	Stream<Path> loadAll();

	Picture findByLabel(String label);

	List<Picture> findAllMatchForClient(String username);

	Picture savePictureForClient(Picture picture, String username);
}
