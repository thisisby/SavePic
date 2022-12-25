package bai.io.savepic.service.impl;

import bai.io.savepic.config.AppProperty;
import bai.io.savepic.model.dto.ImageKitRequest;
import bai.io.savepic.model.entity.Event;
import bai.io.savepic.model.entity.Picture;
import bai.io.savepic.model.entity.UserClient;
import bai.io.savepic.repository.PictureRepository;
import bai.io.savepic.service.EventService;
import bai.io.savepic.service.PictureService;
import bai.io.savepic.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@Transactional
public class PictureServiceImpl implements PictureService {

	private final PictureRepository pictureRepository;
	private final UserService userService;
	private final AppProperty appProperty;
	private final EventService eventService;

	@Autowired
	public PictureServiceImpl(PictureRepository pictureRepository, UserService userService, AppProperty appProperty, EventService eventService) {
		this.pictureRepository = pictureRepository;
		this.userService = userService;
		this.appProperty = appProperty;
		this.eventService = eventService;
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
	@Async
	public boolean savePicture(MultipartFile[] multipartFile, String label, String username, Long eventId) {

		Event event = eventService.findById(eventId).get();

		String extension = multipartFile[0].getOriginalFilename().split("\\.")[1];
		String fileName = label + "." + extension;

		List<MultipartFile> files = new ArrayList<>(Arrays.asList(multipartFile));
		List<File> fileList = new ArrayList<>();

		files.stream().forEach(file -> {
			fileList.add(convertMultiPartFileToFile(file));
		});

//		final File file = convertMultiPartFileToFile(multipartFile);
		List<ResponseEntity<ImageKitRequest>> res = new ArrayList<>();

		fileList.stream().forEach(file -> {
			res.add(uploadFileToImagekit(fileName, file));
		});

//		ResponseEntity<ImageKitRequest> response = uploadFileToImagekit(fileName, file);

		for (int i = 0; i < res.size(); i++) {
			ImageKitRequest imageKitRequest = res.get(i).getBody();
			Picture picture = Picture
					.builder()
					.label(label)
					.imgUrl(imageKitRequest.getUrl())
					.event(event)
					.build();
			Picture newPic = pictureRepository.save(picture);
			savePictureForClient(newPic, username, eventId);
		}
		files.clear();
		fileList.clear();
//		ImageKitRequest imageKitRequest = response.getBody();
//		Picture picture = Picture
//				.builder()
//				.label(label)
//				.imgUrl(imageKitRequest.getUrl())
//				.build();
//		Picture newPic = pictureRepository.save(picture);
//		savePictureForClient(newPic, username, eventId);
//		file.delete();

		return true;
	}
	private File convertMultiPartFileToFile(final MultipartFile multipartFile) {
		File file = new File(multipartFile.getOriginalFilename());

		try {
			file.createNewFile();
			FileOutputStream outputStream = new FileOutputStream(file);

			byte[] bytes = multipartFile.getBytes();
			outputStream.write(bytes);
			outputStream.close();

		} catch (final IOException ex) {
			System.out.println("Error converting the multi-part file to file= "+ ex.getMessage());
		}
		return file;
	}

	private ResponseEntity<ImageKitRequest> uploadFileToImagekit(String fileName,final File file) {

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.MULTIPART_FORM_DATA);
		headers.set("Authorization", appProperty.getImageKitPrivateKey());
		headers.set("publicKey", appProperty.getImageKitPublicKey());
		headers.set("urlEndpoint", appProperty.getImageKitUrlEndpoint());

		MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
		body.add("file", new FileSystemResource(file));
		body.add("fileName", fileName);

		HttpEntity<MultiValueMap<String, Object>> requestEntity= new HttpEntity<>(body, headers);

		RestTemplate restTemplate = new RestTemplate();

		ResponseEntity<ImageKitRequest> response =  restTemplate.postForEntity(
				appProperty.getImageKitUrlEndpointUpload(),
				requestEntity,
				ImageKitRequest.class);

		return response;

	}

	@Override
	public List<Picture> findAllByLabel(String label) {
		return pictureRepository.findAllByLabel(label);
	}

	@Override
	public List<Picture> findAllMatchForClient(String username) {
		return null;
	}

	@Override
	public Picture savePictureForClient(Picture picture, String username, Long eventId) {
		UserClient userClient = (UserClient) userService.findUserEntityByUsername(username);
		Picture pictureById = pictureRepository.findById(picture.getId()).get();
		List<Picture> pictures = userClient.getPictures();
		pictures.add(pictureById);

		Event event = eventService.findById(eventId).get();
		List<Picture> events = event.getPictures();
		events.add(pictureById);

		return pictureById;
	}

	@Override
	public List<Picture> findAllByEventId(Long id) {
		return pictureRepository.findPicturesByEventId(id);
	}

}
