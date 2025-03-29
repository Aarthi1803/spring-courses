package com.telusko.SpringDemo;

import org.springframework.stereotype.Component;


/*public class Laptop {
	public Laptop() {
		System.out.println("Laptop Object Created");
	}
}*/

/*public class Laptop {
	public Laptop() {
		System.out.println("Laptop Object Created");
	}
	
	public void compile() {
		System.out.println("Laptop Compiling");
	}
}*/

/*public class Laptop implements Computer {
	public Laptop() {
		System.out.println("Laptop Object Created");
	}
	
	@Override
	public void compile() {
		System.out.println("Compiling using Laptop");
	}
}*/

/******************************************************/
//Stereotype Annotation - talk to Spring framework with class metadata itself

@Component
public class Laptop implements Computer {
	public Laptop() {
		System.out.println("Laptop Object Created");
	}
	
	@Override
	public void compile() {
		System.out.println("Compiling using Laptop");
	}
} 
