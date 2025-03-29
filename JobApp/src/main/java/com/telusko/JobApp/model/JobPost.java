package com.telusko.JobApp.model;

import java.util.List;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//  Lombok helps you to reduce number of lines of code
@Data // It will create getters and setters, toString() and hashcode methods
@NoArgsConstructor // Generates a default constructor (no-argument constructor). Purpose: Allows creating an object without passing parameters.
@AllArgsConstructor // Generates a constructor with all fields as arguments. Purpose: Allows creating an object with values assigned to all fields.
@Component // creates (bean) object for this class. Purpose: Marks this class as a Spring-managed bean, meaning Spring will automatically create and manage an instance of JobPost. This class becomes eligible for dependency injection in other Spring components. It gets automatically scanned and registered in the Spring application context.

public class JobPost {
	private int postId;
	private String postProfile;
	private String postDesc;
	private Integer reqExperience;
	private List<String> postTechStack;
}

/* 
1. @Data :
    This annotation bundles multiple annotations together, including:
        @Getter – Generates getter methods for all fields.
        @Setter – Generates setter methods for all fields.
        @ToString – Generates a toString() method including all fields.
        @EqualsAndHashCode – Generates equals() and hashCode() methods.
    Purpose: Reduces the need to manually create getter, setter, toString(), and hashCode methods.
 
 
*/