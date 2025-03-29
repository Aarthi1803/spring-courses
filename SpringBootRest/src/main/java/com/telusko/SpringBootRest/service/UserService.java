package com.telusko.SpringBootRest.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.telusko.SpringBootRest.model.User;
import com.telusko.SpringBootRest.repo.UserRepo;

import jakarta.annotation.PostConstruct;

@Service
public class UserService {
	
	@Autowired
	private UserRepo repo;
	
	private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12); // adding 12 because it creates password as 2^12
	
	public User saveUser(User user) {
		user.setPassword(encoder.encode(user.getPassword())); // Takes the plain text password. Converts it into an encrypted hash. Stores the encrypted password in the database.
		System.out.println(user.getPassword());
		return repo.save(user); // UserService encrypts the password before saving. The password sent in the request body will be encrypted by UserService before storing
	}
	
	
// Since you already have some plain text passwords in the database (Hema: h@345, Pandi: p@123), you need to update them to encrypted passwords.
//    @PostConstruct // 🔹 Encrypt passwords for existing users on application startup. @PostConstruct → Runs encryptExistingPasswords() automatically when the application starts.
//    public void encryptExistingPasswords() {
//        System.out.println("Checking and encrypting existing passwords...");
//        List<User> users = repo.findAll();
//
//        for (User user : users) {
//            // Check if password is already encrypted (BCrypt starts with "$2a$")
//            if (!user.getPassword().startsWith("$2a$")) { // Loops through all users → Checks if the password is already encrypted ($2a$).
//                user.setPassword(encoder.encode(user.getPassword())); // If the password is plain text, it encrypts and updates it in the database.
//                repo.save(user);
//                System.out.println("Encrypted password for user: " + user.getUsername()); // Once all passwords are encrypted, it prints which users were updated.
//            }
//        }
//    }  
	
	 // Update old passwords when the application starts
//    @PostConstruct
//    public void updateOldPasswords() {
//        System.out.println("Checking and updating old passwords...");
//
//        List<User> users = repo.findAll();
//
//        for (User user : users) {
//            String password = user.getPassword();
//
//            // Check if the password is using {noop}
//            if (password.startsWith("{noop}")) {
//                String plainPassword = password.replace("{noop}", ""); // Remove {noop}
//                String encodedPassword = encoder.encode(plainPassword); // Encrypt with BCrypt
//
//                user.setPassword(encodedPassword);
//                repo.save(user); // Save the updated password
//
//                System.out.println("Updated {noop} password for user: " + user.getUsername());
//            }
//        }
//    }
	
}

/*
	How Does BCrypt Work?
    Before saving to the database, BCryptPasswordEncoder encrypts the password.
    It generates a unique hash each time, even if the same password is used.
    During login, BCrypt automatically matches encrypted passwords.
*/