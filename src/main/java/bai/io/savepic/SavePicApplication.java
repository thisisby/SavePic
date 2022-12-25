package bai.io.savepic;

import bai.io.savepic.config.AppProperty;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
@EnableConfigurationProperties(AppProperty.class)
public class SavePicApplication {

	public static void main(String[] args) {
		SpringApplication.run(SavePicApplication.class, args);
	}


}