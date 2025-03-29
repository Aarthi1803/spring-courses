package com.telusko.app.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/*@Component
public class Alien {

	public void code() {
		System.out.println("Alien Coding");
	}
	
}*/

/*@Component
public class Alien {
	
	@Autowired
	Laptop laptop;
	
	public void code() {
		laptop.compile();
	}
	
}*/


@Component
public class Alien {
	
	@Value("25")
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
	
	@Autowired
	@Qualifier("laptop")
	public void setCom(Computer com) {
		this.com = com;
	}

	public void code() {
		System.out.println("Alien Coding");
		com.compile();
	}
		
}
