package com.telusko.SpringJDBCEx;

import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.telusko.SpringJDBCEx.model.Student;
import com.telusko.SpringJDBCEx.service.StudentService;

@SpringBootApplication
public class SpringJdbcExApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(SpringJdbcExApplication.class, args);
		Student s = context.getBean(Student.class);
		s.setRollno(106);
		s.setName("Krish");
		s.setMarks(85);
		
		StudentService service = context.getBean(StudentService.class);
		service.addStudent(s);
		
		List<Student> students = service.getStudents();
		System.out.println(students);
	}

}
