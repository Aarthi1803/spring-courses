package com.telusko.SpringBootRest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.telusko.SpringBootRest.model.User;
import com.telusko.SpringBootRest.service.UserService;


@RestController
public class UserController { // For user to register themselves
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/register") // The password sent in the request body will be encrypted by UserService before storing in db.
	public User register(@RequestBody User user) {
		return userService.saveUser(user);
	}
	
//	@PostMapping("/encrypt-passwords") // Option 2: Call encryptExistingPasswords() Manually via an API - If you don't want it to run automatically using @PostConstruct in UserService.java, you can expose an API endpoint to call it manually. Calls encryptExistingPasswords() when you hit POST /encrypt-passwords from Postman or the browser.
//    public String encryptPasswords() {
//        userService.encryptExistingPasswords();
//        return "Existing passwords encrypted successfully!";
//    }
	
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