package com.demo.client;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.demo.*")
public class OauthClientAppApplication implements CommandLineRunner {
	
	private static final Log LOGGER = LogFactory.getLog(OauthClientAppApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(OauthClientAppApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		LOGGER.info("========= OAUTH CLIENT APP STARTED ====");
	}

}
