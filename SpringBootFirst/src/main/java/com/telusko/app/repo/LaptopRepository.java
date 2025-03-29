package com.telusko.app.repo;

import org.springframework.stereotype.Repository;

import com.telusko.app.model.Laptop;

// @Component
@Repository // we can use either @Component or @Repository to create bean(object) for class
public class LaptopRepository {
	
	public void save(Laptop lap) {
		System.out.println("Saved in Database..");
	}
	
}
