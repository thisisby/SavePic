package bai.io.savepic.service;

import bai.io.savepic.model.dto.ImageKitRequest;
import bai.io.savepic.model.entity.Picture;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface PictureService {

	Picture findById(Long id);

	List<Picture> findAll();

	@Async
	ResponseEntity<ImageKitRequest> savePicture(MultipartFile multipartFile, String label, String username, Long eventId);

	List<Picture> findAllByLabel(String label);

	List<Picture> findAllMatchForClient(String username);

	Picture savePictureForClient(Picture picture, String username, Long eventId);
}
