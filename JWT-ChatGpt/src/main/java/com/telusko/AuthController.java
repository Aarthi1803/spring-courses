package com.telusko;

import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

@RestController
@RequestMapping("/auth")
public class AuthController { // This class will handle login and generate JWT tokens.
	
    @Autowired
    private JwtUtil jwtUtil; //Injects (@Autowired) the JwtUtil class to use JWT functions.

    @PostMapping("/login")
//    public String login(@RequestParam String username, @RequestParam String password) { // @RequestParam is used for query parameters (e.g., http://localhost:8080/auth/login??username=admin&password=password).
    public String login(@RequestBody LoginRequest request) {
    	 String username = request.getUsername();
    	 String password = request.getPassword();
        // Step 1: Hardcoded username & password check (Replace with DB validation)
        if ("admin".equals(username) && "password".equals(password)) { // Checks if the username & password are correct (hardcoded for testing).  If correct, generates a JWT token and returns it.
            // Step 2: Generate JWT
            String token = jwtUtil.generateToken(username); // If login is successful, the system issues a JWT. The user stores the JWT and uses it for future requests.
            return "JWT Token: " + token;
        }
        return "Invalid credentials!";
    }
    
}

/*
 
 Real-World Example:
    This is like logging into a banking app.
    If login is successful, the system issues a JWT.
    The user stores the JWT and uses it for future requests.
  
 */
