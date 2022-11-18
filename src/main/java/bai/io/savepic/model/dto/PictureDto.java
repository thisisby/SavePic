package bai.io.savepic.model.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class PictureDto {
	private String label;
	private MultipartFile imgUrl;
}
