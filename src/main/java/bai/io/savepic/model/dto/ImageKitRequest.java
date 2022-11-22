package bai.io.savepic.model.dto;

import lombok.Data;

@Data
public class ImageKitRequest {
	private String fileId;
	private String name;
	private int size;
	private String filePath;
	private String url;
	private String fileType;
	private int height;
	private int width;
	private String thumbnailUrl;
}
