package com.demo.servicea;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.demo.*")
public class ServiceAApplication implements CommandLineRunner {

	private static final Log LOGGER = LogFactory.getLog(ServiceAApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(ServiceAApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		LOGGER.info("========= SERVICE-A RESOURCE SERVER STARTED ====");
	}

}
