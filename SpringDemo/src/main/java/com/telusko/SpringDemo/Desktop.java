package com.telusko.SpringDemo;

import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/*public class Desktop implements Computer {
	
	public Desktop() {
		System.out.println("Desktop object created");
	}
	
	@Override
	public void compile() {
		System.out.println("Compiling using Desktop");
	}

}*/

/******************************************************/
//Stereotype Annotation - talk to Spring framework with class metadata itself

/*@Component
public class Desktop implements Computer {
	
	public Desktop() {
		System.out.println("Desktop object created");
	}
	
	@Override
	public void compile() {
		System.out.println("Compiling using Desktop");
	}

}*/

/*@Component("com2") if com2 is used here as bean name we need to mention in Alien class as @Qualifier("com2") 
public class Desktop implements Computer {
	
	public Desktop() {
		System.out.println("Desktop object created");
	}
	
	@Override
	public void compile() {
		System.out.println("Compiling using Desktop");
	}

}*/

/*@Component
@Primary
public class Desktop implements Computer {
	
	public Desktop() {
		System.out.println("Desktop object created");
	}
	
	@Override
	public void compile() {
		System.out.println("Compiling using Desktop");
	}

}*/


@Component
@Primary
@Scope("prototype")
public class Desktop implements Computer {
	
	public Desktop() {
		System.out.println("Desktop object created");
	}
	
	@Override
	public void compile() {
		System.out.println("Compiling using Desktop");
	}

}
