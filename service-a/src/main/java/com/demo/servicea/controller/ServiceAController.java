package com.demo.servicea.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ServiceAController {

	@GetMapping(path = "/service-a")
	public String hello() {
		return "Service - A";
	}
	
	@PostMapping(path="/post") 
	public String post() {
		return "Service - A POST";
	}

}
