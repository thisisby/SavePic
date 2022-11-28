package bai.io.savepic.controller;

import bai.io.savepic.model.dto.ImageKitRequest;
import bai.io.savepic.model.dto.PictureForClientDto;
import bai.io.savepic.model.entity.Picture;
import bai.io.savepic.repository.PictureRepository;
import bai.io.savepic.service.PictureService;
import bai.io.savepic.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/picture")
public class PictureController {

	private final PictureService pictureService;
	private final PictureRepository pictureRepository;
	private final UserService userService;
	private final ModelMapper modelMapper;

	public PictureController(PictureService pictureService, PictureRepository pictureRepository, UserService userService, ModelMapper modelMapper) {
		this.pictureService = pictureService;
		this.pictureRepository = pictureRepository;
		this.userService = userService;
		this.modelMapper = modelMapper;
	}

	@GetMapping
	public List<Picture> getAllPicture(){
		return pictureService.findAll();
	}

	@PostMapping("/save")
	public boolean savePicture(@RequestParam MultipartFile file, @RequestParam String label, @RequestParam String username, @RequestParam Long eventId) {
//		Picture picture = modelMapper.map(pictureDto, Picture.class);
//		pictureService.savePicture(picture);

		ResponseEntity<ImageKitRequest> response = pictureService.savePicture(file, label, username, eventId);
		ImageKitRequest imagekit = response.getBody();
		System.out.println(label);
		System.out.println(file.getOriginalFilename());

		return true;
	}


	@GetMapping("/findByLabel")
	public List<Picture> findAllByLabel(@RequestParam String label){
		return pictureService.findAllByLabel(label);
	}



}
