package bai.io.savepic.controller;

import bai.io.savepic.model.dto.PictureDto;
import bai.io.savepic.model.dto.PictureForClientDto;
import bai.io.savepic.model.entity.Picture;
import bai.io.savepic.service.PictureService;
import bai.io.savepic.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/picture")
public class PictureController {

	private final PictureService pictureService;
	private final UserService userService;
	private final ModelMapper modelMapper;

	public PictureController(PictureService pictureService, UserService userService, ModelMapper modelMapper) {
		this.pictureService = pictureService;
		this.userService = userService;
		this.modelMapper = modelMapper;
	}

	@GetMapping
	public List<Picture> getAllPicture(){
		return pictureService.findAll();
	}

	@PostMapping("/save")
	public Picture savePicture(@RequestParam MultipartFile file, @RequestParam String label) {
//		Picture picture = modelMapper.map(pictureDto, Picture.class);
//		pictureService.savePicture(picture);
		System.out.println(label);
		System.out.println(file.getOriginalFilename());

//		return picture;
		return null;
	}

	@PostMapping("/saveForClient")
	public boolean saveForClient(@RequestBody PictureForClientDto pictureForClientDto){
		Picture picture = pictureService.findById(pictureForClientDto.getId());
		pictureService.savePictureForClient(picture, pictureForClientDto.getUsername());
		return true;
	}

	@GetMapping("/findByLabel")
	public Picture findByLabel(@RequestParam String label){
		return pictureService.findByLabel(label);
	}



}
