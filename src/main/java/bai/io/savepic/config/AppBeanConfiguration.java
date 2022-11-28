package bai.io.savepic.config;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.autoconfigure.data.rest.RepositoryRestMvcAutoConfiguration;
import org.springframework.boot.autoconfigure.data.rest.RepositoryRestProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.stereotype.Repository;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
@EnableWebMvc
public class AppBeanConfiguration implements WebMvcConfigurer {

	//	@Bean
//	public PasswordEncoder createPasswordEncoder() {
//		return new Pbkdf2PasswordEncoder();
//	}

	@Bean
	public ModelMapper createModelMapper() {
		return new ModelMapper();
	}

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**")
				.allowedMethods("GET", "POST", "PATCH", "PUT");
	}





}
