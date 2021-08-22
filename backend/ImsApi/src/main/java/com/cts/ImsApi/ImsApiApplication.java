package com.cts.ImsApi;

import java.util.Date;
import java.util.Optional;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.cts.models.entityModels.User;
import com.cts.repository.UserRepository;

@SpringBootApplication
@ComponentScan("com.*")
@EntityScan(basePackages = "com.*")
@EnableJpaRepositories(basePackages = "com.*")
@CrossOrigin(origins = "*")
public class ImsApiApplication {

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private UserRepository userRepository;

	/**
	 * Creates a default admin account if no default admin exists on the system with
	 * below mentioned credentials. The user name for the default admin is
	 * systemadmin0165
	 */
	@PostConstruct
	public void initAdmin() {
		User user = new User();
		user.setFirstName("System");
		user.setLastName("Admin");
		user.setEmailId("systemadmin01@gmail.com");
		user.setPassword(passwordEncoder.encode("Admin@1234"));
		user.setDateOfAssociation(new Date());
		user.setLastLogin(new Date());
		user.setActiveUser(true);
		user.setAdmin(true);
		user.setLastLogin(new Date());
		String generatedUsername = user.getEmailId().strip().split("@")[0] + user.getFirstName().length()
				+ user.getLastName().length();
		user.setUserName(generatedUsername);
		Optional<User> exists = userRepository.findByUserName(user.getUserName());
		if (exists.isEmpty()) {
			userRepository.save(user);
		}
	}

	/**
	 * This java bean is used to configure cross origin resource sharing
	 * configuration for any origin.
	 * 
	 * @return
	 */
	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			/**
			 * Assists with the registration of global, URL pattern based CorsConfiguration
			 * mappings.
			 */
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**").allowedOriginPatterns("*").allowedHeaders("*").allowedMethods("*")
						.allowCredentials(false).maxAge(3600L);
			}
		};
	}

	public static void main(String[] args) {
		SpringApplication.run(ImsApiApplication.class, args);
	}

}
