package com.demo.controller;

import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
	
	@GetMapping("/")
	public String hello() {
		return "OAuth Testing";	
	}
	
	@GetMapping("/token")
	public String token(@RegisteredOAuth2AuthorizedClient("app-gateway-local-client") OAuth2AuthorizedClient client) {
		return client.getAccessToken().getTokenValue();
	}

}
