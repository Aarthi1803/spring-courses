package com.telusko.SpringBootRest.model;

import java.util.List;

import org.springframework.stereotype.Component;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//  Lombok helps you to reduce number of lines of code
@Data // It will create getters and setters, toString() and hashcode methods
@NoArgsConstructor // It will create default constructor
@AllArgsConstructor // It will create parameterized constructor with all parameters
@Component // creates (bean) object for this class
@Entity
public class JobPost {
	
	@Id
	private int postId;
	private String postProfile;
	private String postDesc;
	private Integer reqExperience;
	private List<String> postTechStack;
	
}