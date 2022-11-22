package bai.io.savepic;

import bai.io.savepic.config.AppProperty;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(AppProperty.class)
public class SavePicApplication {

	public static void main(String[] args) {
		SpringApplication.run(SavePicApplication.class, args);
	}

}