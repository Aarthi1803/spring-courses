package com.telusko.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.telusko.model.User;
import com.telusko.service.JwtService;
import com.telusko.service.UserService;


/*@RestController
public class UserController { // For user to register themselves
	
	@Autowired
	private UserService userService;
	
	@Autowired
	AuthenticationManager authenticationManager;
	
	@PostMapping("/register") // The password sent in the request body will be encrypted by UserService before storing in db.
	public User register(@RequestBody User user) {
		return userService.saveUser(user);
	}
	
	@PostMapping("/encrypt-passwords") // Option 2: Call encryptExistingPasswords() Manually via an API - If you don't want it to run automatically using @PostConstruct in UserService.java, you can expose an API endpoint to call it manually. Calls encryptExistingPasswords() when you hit POST /encrypt-passwords from Postman or the browser.
    public String encryptPasswords() {
        userService.encryptExistingPasswords();
        return "Existing passwords encrypted successfully!";
    }
}*/

/*******************************************************************/
@RestController
public class UserController { // For user to register themselves
	
	@Autowired
	private UserService userService;
	
	@Autowired
	AuthenticationManager authenticationManager;
	
	@Autowired
	private JwtService jwtService;
	
	@PostMapping("/register") // The password sent in the request body will be encrypted by UserService before storing in db.
	public User register(@RequestBody User user) {
		return userService.saveUser(user);
	}
	
	@PostMapping("/encrypt-passwords") // Option 2: Call encryptExistingPasswords() Manually via an API - If you don't want it to run automatically using @PostConstruct in UserService.java, you can expose an API endpoint to call it manually. Calls encryptExistingPasswords() when you hit POST /encrypt-passwords from Postman or the browser.
    public String encryptPasswords() {
        userService.encryptExistingPasswords();
        return "Existing passwords encrypted successfully!";
    }
	
	@PostMapping("/login")
	public String login(@RequestBody User user) { // When I say /login it gives me JWT, after getting JWT I need to pass JWT to other API requests. Reads username & password from the request body.
		
		Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword())); // Verifies username & password in DB. Creates an Authentication object using UsernamePasswordAuthenticationToken. Calls authenticationManager.authenticate(...) to verify the username & password.  Checks credentials against the database (via UserDetailsService).
		
		if(authentication.isAuthenticated()) // Checks if the authentication was successful. If valid, generate a JWT token. If invalid, return “Login failed”.
//			return "Success";
			return jwtService.generateToken(user.getUsername()); // each users get each token.  If successful, generates JWT. Calls jwtService.generateToken(username) to generate a JWT. Returns the JWT to the client.
		else
			return "Login failed";
	}
	
}

/* 
 
 How to Test in Postman
    Open Postman.
    Select POST.
    Enter URL: http://localhost:8080/encrypt-passwords
    Click Send.
    You should see "Existing passwords encrypted successfully!" in the response.

✅ Now, all old passwords are encrypted only when you manually call the API!

Which Option Should You Choose?
Approach											When to Use?
Option 1: Run on Startup (@PostConstruct)			✅ If you want passwords to be encrypted automatically when the application starts.
Option 2: Call via API (/encrypt-passwords)			✅ If you want manual control over when passwords get encrypted.

*/


/*

What Does This /login Endpoint Do?
✔ Accepts a username and password in the request body.
✔ Authenticates the user against the database.
✔ If credentials are valid, generates a JWT token.
✔ Returns the JWT token to the client.
✔ The client must include this token in the Authorization header for all further API requests.

 Real-World Analogy: Airport Security Check:
    A passenger arrives at the airport and shows their passport & ticket (username & password).
    The security officer checks if the passport is valid (authentication).
    If valid, the officer stamps the boarding pass with a QR code (JWT token).
    The passenger must show this QR code (JWT) at every security checkpoint in the airport.

✔ In our application, JWT is like the QR code that grants access to restricted areas (protected APIs).


Authentication authentication = authenticationManager.authenticate(
    new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword())
);

✔ Creates an Authentication object using UsernamePasswordAuthenticationToken.
✔ Calls authenticationManager.authenticate(...) to verify the username & password.
✔ Checks credentials against the database (via UserDetailsService).

  Real-World Example:
    The security officer verifies the passenger’s passport in the database.
    If valid, the passenger is cleared for travel.

How This Works in the Application:
1️. User Calls the /login API with username & password (POST /login).

{
  "username": "john",
  "password": "password123"
}

2️. Spring Security verifies the credentials.
3️. If valid, JWT token is generated and returned.

{
  "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9..."
}

4️. User includes JWT in all future requests (Authorization: Bearer <token>).
5️. JWT is validated on every request to grant access to protected APIs.

Summary of /login Endpoint
Step									Code													Real-World Example
1. User submits credentials				@RequestBody User user									Passenger shows passport & ticket.
2. Validate credentials					authenticationManager.authenticate(...)					Security officer checks the passport.
3. If valid, generate JWT				jwtService.generateToken(user.getUsername())			Boarding pass (JWT) is issued.
4. If invalid, reject login	return 		"Login failed";											Passenger is denied boarding.

*/