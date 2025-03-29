package com.telusko;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

	@GetMapping("/hello") // Authentication is required before accessing this endpoint.
	public String greet() {
		return "Welcome to Telusko";
	}
	
}
