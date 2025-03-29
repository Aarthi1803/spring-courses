package com.telusko;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StudentController {
	
	@Autowired
	StudentRepo repo;

	@RequestMapping("/getStudents")
	public List<Student> getStudents(){
//		return List.of(
//				new Student(1,"Aarthi",20),
//				new Student(2,"Isha",25),
//				new Student(3,"Hema",35)
//		);
		return repo.findAll();
	}
	
	@RequestMapping("/addStudent")
	public void addStudent() {
		Student s  = new Student();
		s.setName("Pandian");
		s.setAge(30);
		
		repo.save(s);
	}
	
}
