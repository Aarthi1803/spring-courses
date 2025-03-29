package com.telusko.SpringDataJPAEx;

import java.util.Optional;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.telusko.SpringDataJPAEx.model.Student;
import com.telusko.SpringDataJPAEx.repository.StudentRepo;

/*@SpringBootApplication
public class SpringDataJpaExApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(SpringDataJpaExApplication.class, args);
		  Student s1 = context.getBean(Student.class);
		  Student s2 = context.getBean(Student.class);
		  Student s3 = context.getBean(Student.class);
		 
		  StudentRepo repo=context.getBean(StudentRepo.class);
		  
		  s1.setRollno(101);
		  s1.setName("Navin");
		  s1.setMarks(75);
		  
		  s2.setRollno(102);
		  s2.setName("Kiran");
		  s2.setMarks(80);
		  
		  s3.setRollno(103);
		  s3.setName("Harsh");
		  s3.setMarks(70);
		  
		  repo.save(s1);

	}
	
}*/

/**********************************************************************/
/*@SpringBootApplication
public class SpringDataJpaExApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(SpringDataJpaExApplication.class, args);
		  Student s1 = context.getBean(Student.class);
		  Student s2 = context.getBean(Student.class);
		  Student s3 = context.getBean(Student.class);
		 
		  StudentRepo repo=context.getBean(StudentRepo.class);
		  
		  s1.setRollno(101);
		  s1.setName("Navin");
		  s1.setMarks(75);
		  
		  s2.setRollno(102);
		  s2.setName("Kiran");
		  s2.setMarks(80);
		  
		  s3.setRollno(103);
		  s3.setName("Harsh");
		  s3.setMarks(70);
		  
		  repo.save(s2);
		  repo.save(s3);
		  
		  System.out.println(repo.findAll());
	}
	
}*/

/**********************************************************************/
/*@SpringBootApplication
public class SpringDataJpaExApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(SpringDataJpaExApplication.class, args);
		  Student s1 = context.getBean(Student.class);
		  Student s2 = context.getBean(Student.class);
		  Student s3 = context.getBean(Student.class);
		 
		  StudentRepo repo=context.getBean(StudentRepo.class);
		  
//		  System.out.println(repo.findById(103));
		  
		  Optional<Student> s= repo.findById(103);
//		  Optional<Student> s= repo.findById(1); // A data that doesn't exist in table - it returns all null and zero values - Student [rollno=0, name=null, marks=0]
		  System.out.println(s.orElse(new Student()));
		  
//		  findById() - returns an Optional class to secure yourself from Null Pointer Exception.
//		  So basically you have to convert from student class to optional student class.
//		  Optional means it will have a value or it will handle null values.
	}
	
}*/


/**********************************************************************/

/*@SpringBootApplication
public class SpringDataJpaExApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(SpringDataJpaExApplication.class, args);
		  Student s1 = context.getBean(Student.class);
		  Student s2 = context.getBean(Student.class);
		  Student s3 = context.getBean(Student.class);
		 
		  StudentRepo repo=context.getBean(StudentRepo.class);
		  
		  System.out.println(repo.findByName("Navin"));
		  System.out.println(repo.findByMarks(80));
		  System.out.println(repo.findByMarksGreaterThan(72));
		  
	}
	
}*/		  

/**********************************************************************/

@SpringBootApplication
public class SpringDataJpaExApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(SpringDataJpaExApplication.class, args);
		  Student s1 = context.getBean(Student.class);
		  Student s2 = context.getBean(Student.class);
		  Student s3 = context.getBean(Student.class);
		 
		  s2.setRollno(102);
		  s2.setName("Kiran");
		  s2.setMarks(85);
		  
		  StudentRepo repo=context.getBean(StudentRepo.class);
		  
//		  repo.save(s2); // updating values in table
		  repo.delete(s2);
	}
	
}

/* repo.save(s2):
  	Before updating, it is firing a Select query just to check if data is present. 
  	If you have that data, it will fire the update query.
	If you don't have that data, it will it will fire a create query.

   repo.delete(s2):
    Before deleting, it is firing a Select query just to check if data is present. 
    If you have that data, it will fire the delete query with help of rollno.
*/

/**********************************************************************/
