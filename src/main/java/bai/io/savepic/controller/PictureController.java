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
@CrossOrigin(origins = "http://localhost:3000")
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

	@PostMapping(value = "/save", consumes = {"multipart/form-data"})
	public boolean savePicture(@RequestParam("file") MultipartFile[] file, @RequestParam("label") String label, @RequestParam("username") String username, @RequestParam("eventId") Long eventId) {
//		Picture picture = modelMapper.map(pictureDto, Picture.class);
//		pictureService.savePicture(picture);
		System.out.println("Entered");
		boolean response = pictureService.savePicture(file, label, username, eventId);
//		ImageKitRequest imagekit = response.getBody();
		System.out.println(label);

		return true;
	}



	@GetMapping("/findByLabel")
	public List<Picture> findAllByLabel(@RequestParam String label){
		return pictureService.findAllByLabel(label);
	}

	@GetMapping("/findByEventId")
	public List<Picture> findAllByEventId(@RequestParam Long id) {
		System.out.println(id);
		return pictureService.findAllByEventId(id);
	}



}
