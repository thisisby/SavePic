package bai.io.savepic.service;

import bai.io.savepic.model.entity.Picture;

import java.util.List;

public interface PictureService {

	Picture findById(Long id);

	List<Picture> findAll();

	void savePicture(Picture picture);

	Picture findByLabel(String label);

	List<Picture> findAllMatchForClient(String username);

	Picture savePictureForClient(Picture picture, String username);
}
