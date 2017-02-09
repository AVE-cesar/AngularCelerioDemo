package com.jaxio.demo.rest.security;

import java.security.Principal;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SecurityRest {

	@RequestMapping("/user")
	public Principal user(Principal user) {
		return user;
	}
}
