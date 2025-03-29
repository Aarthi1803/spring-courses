package com.telusko.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.telusko.app.model.Alien;
import com.telusko.app.model.Laptop;
import com.telusko.app.service.LaptopService;

/*@SpringBootApplication
public class SpringBootFirstApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(SpringBootFirstApplication.class, args);
		
		Alien obj = context.getBean(Alien.class);
		obj.code();
		
		Alien obj1 = context.getBean(Alien.class);
		obj.code();
	}
}*/

/*@SpringBootApplication
public class SpringBootFirstApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(SpringBootFirstApplication.class, args);
		
		Alien obj = context.getBean(Alien.class);
		obj.code();
		
//		Laptop laptop = context.getBean(Laptop.class);
//		laptop.compile();
	}
	
}*/

/*@SpringBootApplication
public class SpringBootFirstApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(SpringBootFirstApplication.class, args);
		
		Alien obj = context.getBean(Alien.class);
		System.out.println("Age is : " + obj.getAge());
		obj.code();
		
	}
	
}*/

@SpringBootApplication
public class SpringBootFirstApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(SpringBootFirstApplication.class, args);
		
		LaptopService service = context.getBean(LaptopService.class);
		
		Laptop lap = context.getBean(Laptop.class);
		service.addLaptop(lap);
		
//		Alien obj = context.getBean(Alien.class);
//		System.out.println(obj.getAge());
//		obj.code();
		
	}
	
}	
