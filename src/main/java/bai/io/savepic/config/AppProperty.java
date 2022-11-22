package bai.io.savepic.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import java.util.Base64;


@ConfigurationProperties(prefix = "app")
public class AppProperty {

	private String imageKitPublicKey;
	private String imageKitPrivateKey;
	private String imageKitUrlEndpoint;
	private String imageKitUrlEndpointUpload;
	private String imageKitUrlEndpointDelete;

	public String getImageKitPublicKey() {
		return imageKitPublicKey;
	}
	public void setImageKitPublicKey(String imageKitPublicKey) {
		this.imageKitPublicKey = imageKitPublicKey;
	}
	public String getImageKitPrivateKey() {
		String encoded = "Basic " + Base64.getEncoder().encodeToString((imageKitPrivateKey+":").getBytes());

		return encoded;
	}

	public void setImageKitPrivateKey(String imageKitPrivateKey) {
		this.imageKitPrivateKey = imageKitPrivateKey;
	}
	public String getImageKitUrlEndpoint() {
		return imageKitUrlEndpoint;
	}
	public void setImageKitUrlEndpoint(String imageKitUrlEndpoint) {
		this.imageKitUrlEndpoint = imageKitUrlEndpoint;
	}

	public String getImageKitUrlEndpointUpload() {
		return imageKitUrlEndpointUpload;
	}
	public void setImageKitUrlEndpointUpload(String imageKitUrlEndpointUpload) {
		this.imageKitUrlEndpointUpload = imageKitUrlEndpointUpload;
	}
	public String getImageKitUrlEndpointDelete() {
		return imageKitUrlEndpointDelete;
	}
	public void setImageKitUrlEndpointDelete(String imageKitUrlEndpointDelete) {
		this.imageKitUrlEndpointDelete = imageKitUrlEndpointDelete;
	}
}
