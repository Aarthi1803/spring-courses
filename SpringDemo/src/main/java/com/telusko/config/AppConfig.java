package com.telusko.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Scope;

import com.telusko.SpringDemo.Alien;
import com.telusko.SpringDemo.Computer;
import com.telusko.SpringDemo.Desktop;
import com.telusko.SpringDemo.Laptop;


/*@Configuration
public class AppConfig {
	
	@Bean
	public Desktop desktop() { 
		return new Desktop();
	}
	
}*/

/*@Configuration
public class AppConfig {
	
	@Bean
//	@Bean(name = "com2") //creating our own bean name - com2
//	@Bean(name = {"com2", "desktop1", "Beast"}) // creating an array of bean names
	public Desktop desktop() { // Default bean name of Desktop class is "desktop" which is method name
		return new Desktop();
	}
	
}*/

/*@Configuration
public class AppConfig {
	
	@Bean
	@Scope("prototype") // Default scope is "singleton"-creates only one object everytime we call using context.getBean(). Scope - "prototype"-creates more objects everytime we call using context.getBean() 
	public Desktop desktop() { // Default bean name of Desktop class is "desktop" which is method name
		return new Desktop();
	}
	
}*/

/*@Configuration
public class AppConfig {
	
//	@Bean
//	public Alien alien() {
//		Alien obj = new Alien();
//		obj.setAge(25);
//		obj.setCom(desktop()); // using desktop() as Alien object is expecting Computer interface implementation class which is Desktop here
//		return obj;
//	} 
	
	@Bean
	public Alien alien(@Autowired Computer com) { // @Autowired annotation is optional and is not mandatory
//		return new Alien();
		Alien obj = new Alien();
		obj.setAge(25);
		obj.setCom(com); // To remove tightly coupled communication as Alien depends only on Desktop class we have passed Computer com as parameter so that it can use either Desktop or Laptop class
		return obj;
	}
	
	@Bean
//	@Scope("prototype")
	public Desktop desktop() { // Default bean name of Desktop class is "desktop" which is method name
		return new Desktop();
	}
	
}*/

@Configuration
public class AppConfig {
	
	@Bean
//	public Alien alien(@Qualifier("desktop") Computer com) { // @Qualifier tells which class to call for Computer com. @Qualifier("desktop") - desktop is bean name which is method name desktop() of Desktop class so Desktop class is called
//  In @Qualifier you can mention the name of the bean you want to refer to. So when you say desktop, it'll always refer to desktop only. This is same as what you do in ref - xml based config.
//	In xml based config you mention the name of the bean ID. In the same way here, you mention the name of a bean, which is the desktop. 
	public Alien alien(Computer com) {	
		Alien obj = new Alien();
		obj.setAge(25);
		obj.setCom(com); // To remove tightly coupled communication as Alien depends only on Desktop/Laptop class we have passed Computer com as parameter so that it can use either Desktop or Laptop class
		return obj;
	}
	
	@Bean
	public Desktop desktop() { // Default bean name of Desktop class is "desktop" which is method name
		return new Desktop();
	}
	
	@Bean
	@Primary // // Laptop is given 1st preference same as how we used primary="true" in xml based config. Either we can use @Qualifier in public Alien alien(@Qualifier("desktop") Computer com) {...} or @Primary on top of class 
	public Laptop laptop() { // Default bean name of Laptop class is "laptop" which is method name
		return new Laptop();
	}
	
}
