package com.card.collector.backend;

import com.card.collector.backend.service.DevInitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.TimeZone;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.card.collector.backend.repository" )
public class BackendApplication implements CommandLineRunner {

	@Value("${spring.profiles.active}")
	private String activeProfile;

	@Autowired
	DevInitService devInitService;

	public static void main(String[] args) {

		SpringApplication.run(BackendApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
		if(activeProfile.equals("dev")) {
			devInitService.init();
		}
	}
}
