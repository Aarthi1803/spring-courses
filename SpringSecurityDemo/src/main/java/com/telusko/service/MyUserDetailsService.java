package com.telusko.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.telusko.dao.UserRepo;
import com.telusko.model.User;
import com.telusko.model.UserPrincipal;

@Service
public class MyUserDetailsService implements UserDetailsService{ // Fetching User from Database

	@Autowired
	private UserRepo repo; // Injects UserRepo (a repository to fetch users from the database).
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException { // This method fetches user details from the database. Spring Security calls this method automatically when a user logs in.
		User user = repo.findByUsername(username);
		if( user == null ) {
			System.out.println("User 404");
			throw new UsernameNotFoundException("User 404");
		}
			
		return new UserPrincipal(user); // as we need to return object of UserDetails we are wrapping User object into UserPrincipal. UserPrincipal implements UserDetails. 
	}

//	A user logs in.
//	The system retrieves user data from the database.
//	UserPrincipal converts the user data into a format that Spring Security understands.
//	If correct, login is successful.
}
