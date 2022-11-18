package bai.io.savepic.service.impl;

import bai.io.savepic.model.entity.Picture;
import bai.io.savepic.model.entity.UserClient;
import bai.io.savepic.repository.PictureRepository;
import bai.io.savepic.service.PictureService;
import bai.io.savepic.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Stream;

@Service
@Transactional
public class PictureServiceImpl implements PictureService {


	private final String FOLDER_PATH = "C:\\Users\\baist\\baistan\\coding\\java\\Spring Framework\\SavePic\\src\\main\\java\\bai\\io\\savepic\\storage";
	private final PictureRepository pictureRepository;
	private final UserService userService;

	@Value("app.nfs.path")
	private String nfsPath;

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
	public Picture savePicture(Picture picture) {
		return pictureRepository.save(picture);
	}


	@Override
	public Path load(String filename) {
		return null;
	}

	@Override
	public Stream<Path> loadAll() {
		return null;
	}

	@Override
	public Picture findByLabel(String label) {
		Picture picture = pictureRepository.findByLabel(label).get();
		return picture;
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
