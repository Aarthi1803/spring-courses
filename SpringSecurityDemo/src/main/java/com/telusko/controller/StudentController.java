package com.telusko.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.telusko.model.Student;

import jakarta.servlet.http.HttpServletRequest;

/*@RestController
public class StudentController {

	List<Student> students = new ArrayList<>(List.of(
			new Student(1, "Navin", "Java"),
			new Student(2, "Aarthi", "Java FSD"),
			new Student(3, "Isha", "AI")
	));
	
	@GetMapping("/students")
	public List<Student> getStudents(){
		return students;
	}
	
	@PostMapping("/students")
	public void addStudent(@RequestBody Student student) {
		students.add(student);
	}
	
}*/


@RestController
public class StudentController {

	List<Student> students = new ArrayList<>(List.of(
			new Student(1, "Navin", "Java"),
			new Student(2, "Aarthi", "Java FSD"),
			new Student(3, "Isha", "AI")
	));
	
	@GetMapping("/csrf-token")
	public CsrfToken getCsrfToken(HttpServletRequest request) {
		return (CsrfToken) request.getAttribute("_csrf");
	}
	
	@GetMapping("/students")
	public List<Student> getStudents(){
		return students;
	}
	
//	@PostMapping("/students")
//	public void addStudent(@RequestBody Student student) {
//		students.add(student);
//	}
	
	@PostMapping("/students")
	public List<Student> addStudent(@RequestBody Student student) {
		students.add(student);
		return students;
	}
}

/*
 CSRF "Cross-Site Request Forgery" and Token : 
I have a session right now in Banking application as it is already logged in, and now I go to some other website which is malicious. 
And this malicious website will search for the cookie in my browser. It will pick up the session ID for secure site of Banking application, 
and then that particular malicious website is trying to access Banking application and then provide the same ID, the session ID, 
which is taken from my browser. So this is called CSRF "Cross-Site Request Forgery". So by default spring security will take care of it. 
So you don't have to manually configure it. There are multiple ways of handling this. One of the way is what if with every request 
what you get in return is a token? So that next time when you send the request you will you have to submit that token as well. 
Now when you talk about HTTP different methods right now out of all these methods like get, post, put, delete and many more, 
the get is the safest method because you don't change the data on the server. 
But what about the post, put and delete? Now of course, every time you send this you are changing something from the server. 
So by default spring Security will implement the CSRF for your put, delete and update and others but not on get. Thus to prevent CSRF we use tokens. 
*/