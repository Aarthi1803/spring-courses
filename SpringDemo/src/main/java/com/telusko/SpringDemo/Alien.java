package com.telusko.SpringDemo;

import java.beans.ConstructorProperties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/*public class Alien{
	
	public void code() {
		System.out.println("Alien Coding");
	}
	
}*/

/*public class Alien{
	
	public Alien() {
		System.out.println("ALien Object created");
	}
	
	public void code() {
		System.out.println("Alien Coding");
	}
	
}*/

/*public class Alien{
	
	int age;
	
	public Alien() {
		System.out.println("ALien Object created");
	}
	
	public void code() {
		System.out.println("Alien Coding");
	}
	
}*/

/*public class Alien{
	
	private int age;
	
	public Alien() {
		System.out.println("Alien Object created");
	}
	
	
	public int getAge() {
		return age;
	}


	public void setAge(int age) {
		System.out.println("Setter called");
		this.age = age;
	}


	public void code() {
		System.out.println("Alien Coding");
	}
	
}*/

/*public class Alien{
	
	private int age;
	private Laptop lap;
	
	public Alien() {
		System.out.println("Alien Object created");
	}
	
	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		System.out.println("Setter called");
		this.age = age;
	}

	public Laptop getLap() {
		return lap;
	}

	public void setLap(Laptop lap) {
		this.lap = lap;
	}

	public void code() {
		System.out.println("Alien Coding");
		lap.compile();
	}
	
}*/

/*public class Alien{
	
	private int age;
	private Laptop lap;
	
	public Alien() {
		System.out.println("Default Constructor Alien Object created");
	}
	
//	public Alien(int age) {
//		System.out.println("Parameterized Constructor Alien Object created");
//		this.age = age;
//	}
	
	@ConstructorProperties({"age", "lap"}) // @ConstructorProperties used because it doesn't need to maintain sequence while passing values to constructor
	public Alien(int age, Laptop lap) {
		this.age = age;
		this.lap = lap;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		System.out.println("Setter called");
		this.age = age;
	}

	public Laptop getLap() {
		return lap;
	}

	public void setLap(Laptop lap) {
		this.lap = lap;
	}

	public void code() {
		System.out.println("Alien Coding");
		lap.compile();
	}
	
}*/

/*public class Alien{

	private int age;
	private Computer com;
	
//	public Alien() {
//		System.out.println("Default Constructor Alien Object created");
//	}
	
//	@ConstructorProperties({"age", "lap"}) // @ConstructorProperties used because it doesn't need to maintain sequence while passing values to constructor
//	public Alien(int age, Laptop lap) {
//		this.age = age;
//		this.lap = lap;
//	}
	
	public int getAge() {
		return age;
	}
	
	public void setAge(int age) {
//		System.out.println("Setter called");
		this.age = age;
	}
	
	public Computer getCom() {
		return com;
	}

	public void setCom(Computer com) {
		this.com = com;
	}

	public void code() {
		System.out.println("Alien Coding");
		com.compile();
	}

}*/

/************************************************/
// Stereotype Annotation - talk to Spring framework with class metadata itself

/*@Component
public class Alien{

	private int age;
	private Computer com;
	
	public int getAge() {
		return age;
	}
	
	public void setAge(int age) {
		this.age = age;
	}
	
	public Computer getCom() {
		return com;
	}

	public void setCom(Computer com) {
		this.com = com;
	}

	public void code() {
		System.out.println("Alien Coding");
		com.compile();
	}

}*/

/*@Component
public class Alien{

	private int age;
//	@Autowired // Autowiring using Field Injection - @Autowired on top of field - Computer com
//	@Qualifier("laptop") // bean name is class name with first letter as small case
//	@Qualifier("com2") // since we mentioned @Component("com2") at Desktop class
	private Computer com;
	
//	@Autowired // Autowiring using Constructor Injection - @Autowired on top of constructor
//	public Alien(Computer com) {
//		this.com = com;
//	}
	
	public int getAge() {
		return age;
	}
	
	public void setAge(int age) {
		this.age = age;
	}
	
	public Computer getCom() {
		return com;
	}

	@Autowired // Autowiring using Setter Injection - @Autowired on top of setter method of Computer
	public void setCom(Computer com) {
		this.com = com;
	}

	public void code() {
		System.out.println("Alien Coding");
		com.compile();
	}

}*/

/*@Component
public class Alien{

	private int age;
	private Computer com;
	
	public int getAge() {
		return age;
	}
	
	public void setAge(int age) {
		this.age = age;
	}
	
	public Computer getCom() {
		return com;
	}

	@Autowired // Autowiring using Setter Injection - @Autowired on top of setter method of Computer
	@Qualifier("laptop") // Eventhough @Primary is mentioned on top of Desktop class we are overriding using @Qualifier("desktop") so Desktop class gets executed. @Qualifier is given more preference than @Primary 
	public void setCom(Computer com) {
		this.com = com;
	}

	public void code() {
		System.out.println("Alien Coding");
		com.compile();
	}

}*/

@Component
public class Alien{

	@Value("21") //  When you use the annotation like "@Value", basically you can inject this value from outside the code. Maybe you have a property file in which you have all the values, you can specify those property names here.
	private int age;
	
	private Computer com;
	
	public int getAge() {
		return age;
	}
	
	public void setAge(int age) {
		this.age = age;
	}
	
	public Computer getCom() {
		return com;
	}

	@Autowired // Autowiring using Setter Injection - @Autowired on top of setter method of Computer
	@Qualifier("laptop") // Eventhough @Primary is mentioned on top of Desktop class we are overriding using @Qualifier("desktop") so Desktop class gets executed. @Qualifier is given more preference than @Primary 
	public void setCom(Computer com) {
		this.com = com;
	}

	public void code() {
		System.out.println("Alien Coding");
		com.compile();
	}

}


/* @Component is a stereotype annotation in Spring, which helps your Spring framework to understand that this Alien is a class where Spring has to manage the objects for it.
So, we have to make this as a managed bean by Spring. Add @Component on all of your classes wherever you want the object to be created by Spring.
The advantage is you don't have to do any configuration in your Java-based configuration.
 */


// For stereotype annotation in Spring - This com object is created inside the container, just search for it. And the way you can do that is by using "@Autowired" annotation.
// Now when you say @Autowired, you're saying Spring, hey, Spring, go to your container and you will find the com object there.	
// If we run code we get error as it tries to find one bean but found 2 bean object so the way to resolve this is by using @Qualifier annotation
// For stereotype annotation in Spring - @Qualifier("laptop") we are mentioning to Computer to fetch only Laptop class
// when we are using Java based configuration, we knew that the method name is your bean name or you can specify the bean name explicitly.
// Here for stereotype annotation we need to use class name with the first letter in small case like 'laptop or desktop'
// (as we have Laptop and Desktop class)
