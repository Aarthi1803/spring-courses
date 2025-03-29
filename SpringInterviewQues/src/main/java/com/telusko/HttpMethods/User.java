package com.telusko.HttpMethods;


import org.springframework.stereotype.Component;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data // It will create getters and setters, toString() and hashcode methods
@NoArgsConstructor // It will create default constructor
@AllArgsConstructor // It will create parameterized constructor with all parameters
@Component
public class User {
    private int id;
    private String name;
    private String email;
    private int age;
}
