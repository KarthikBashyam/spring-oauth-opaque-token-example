package com.demo.servicea.security;

import java.util.LinkedHashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationManagerResolver;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationProvider;
import org.springframework.security.oauth2.server.resource.authentication.OpaqueTokenAuthenticationProvider;
import org.springframework.security.oauth2.server.resource.introspection.OpaqueTokenIntrospector;
import org.springframework.security.web.util.matcher.RequestMatcher;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private JwtDecoder jwtDecoder;
	
	@Autowired
	private OpaqueTokenIntrospector opaqueTokenIntrospector;

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		// @formatter:off

		http.authorizeRequests().anyRequest().authenticated().and().oauth2ResourceServer()
				.authenticationManagerResolver(customAuthenticationManager());
		// .jwt(); //JWT validation will happen in memory.
		// .opaqueToken(); //Opaque token validation will happen by invoking the
		// introspect URI.

		// @formatter:on
	}

	AuthenticationManagerResolver<HttpServletRequest> customAuthenticationManager() {
		
		LinkedHashMap<RequestMatcher, AuthenticationManager> authenticationManagers = new LinkedHashMap<>();
		
		List<String> readMethods = List.of("HEAD","GET","OPTIONS");
		
		//Validate read only operations using JWT.
		RequestMatcher readRequestMatcher = request -> readMethods.contains(request.getMethod());
		authenticationManagers.put(readRequestMatcher, jwt());

		//Validate POST operations using opaque Introspect API.
		RequestMatchingAuthenticationManagerResolver resolver = new RequestMatchingAuthenticationManagerResolver(
				authenticationManagers);
		resolver.setDefaultAuthenticationManager(opaque());
		
		return resolver;

	}
	
	AuthenticationManager jwt() {
		JwtAuthenticationProvider jwt = new JwtAuthenticationProvider(jwtDecoder);
		jwt.setJwtAuthenticationConverter(new JwtAuthenticationConverter());
		return jwt::authenticate;
	}
	
	AuthenticationManager opaque() {
		OpaqueTokenAuthenticationProvider opaque = new OpaqueTokenAuthenticationProvider(opaqueTokenIntrospector);
		return opaque::authenticate;	
	}
	

}
