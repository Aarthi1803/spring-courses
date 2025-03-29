package com.telusko.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;

@RestController
public class HelloController {

	@GetMapping("/hello")
//	public String greet(HttpServletRequest request) {
	public String greet() {
//		return " Hello World " + request.getSession().getId(); //gives session ID 
		return " Hello World ";
	}
	
	@GetMapping("/about")
	public String about(HttpServletRequest request) {
		return " Telusko "+ request.getSession().getId(); //gives session ID 
	}
	
}
