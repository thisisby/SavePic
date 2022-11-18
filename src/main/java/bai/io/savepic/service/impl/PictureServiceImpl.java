package bai.io.savepic.service.impl;

import bai.io.savepic.model.entity.Picture;
import bai.io.savepic.model.entity.UserClient;
import bai.io.savepic.repository.PictureRepository;
import bai.io.savepic.service.PictureService;
import bai.io.savepic.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class PictureServiceImpl implements PictureService {

	private final PictureRepository pictureRepository;
	private final UserService userService;

	@Autowired
	public PictureServiceImpl(PictureRepository pictureRepository, UserService userService) {
		this.pictureRepository = pictureRepository;
		this.userService = userService;
	}

	@Override
	public Picture findById(Long id) {
		return pictureRepository.findById(id).get();
	}

	@Override
	public List<Picture> findAll() {
		return (List<Picture>) pictureRepository.findAll();
	}

	@Override
	public void savePicture(Picture picture) {
		pictureRepository.save(picture);
	}

	@Override
	public Picture findByLabel(String label) {
		return pictureRepository.findByLabel(label).get();
	}

	@Override
	public List<Picture> findAllMatchForClient(String username) {
		return null;
	}

	@Override
	public Picture savePictureForClient(Picture picture, String username) {
		UserClient userClient = (UserClient) userService.findUserEntityByUsername(username);
		Picture pictureById = pictureRepository.findById(picture.getId()).get();
		List<Picture> pictures = userClient.getPictures();
		pictures.add(pictureById);
		return pictureById;
	}
}
