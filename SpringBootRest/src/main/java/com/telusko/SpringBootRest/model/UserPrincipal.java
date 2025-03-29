package com.telusko.SpringBootRest.model;

import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class UserPrincipal implements UserDetails{ // Principal refers to current user. Implements UserDetails (required by Spring Security). Converts a User object into a format Spring Security understands.

	private User user; // Stores the user fetched from the database.
	
	public UserPrincipal(User user) {
		this.user = user; // Stores the authenticated user.
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() { // define the user's roles and permissions.
		return Collections.singleton(new SimpleGrantedAuthority("USER")); // Returns the user's role (USER).
	}

	@Override
	public String getPassword() {
		return user.getPassword(); // Returns password from the database.
	}

	@Override
	public String getUsername() {
		return user.getUsername(); // Returns username from the database.
	} 
	
}

/*
public Collection<? extends GrantedAuthority> getAuthorities() {

    Return Type: Collection<? extends GrantedAuthority>
        This means we are returning a collection of objects that implement the GrantedAuthority interface.
        GrantedAuthority represents user roles or permissions in Spring Security.

    Purpose of this Method:
        This method tells Spring Security what roles/permissions the user has.
        Example roles: USER, ADMIN, MANAGER, etc.
        
return Collections.singleton(new SimpleGrantedAuthority("USER"));

    Collections.singleton(...)
        This creates a Set (collection) with only one element.
        In this case, it contains one role: "USER".

    new SimpleGrantedAuthority("USER")
        SimpleGrantedAuthority is a Spring Security class that represents a user role or permission.
        "USER" → This means the user has the "USER" role
        
Expanding the Code (Multiple Roles):
If a user can have multiple roles, modify the method like this:

@Override
public Collection<? extends GrantedAuthority> getAuthorities() {
    return Arrays.asList(
        new SimpleGrantedAuthority("USER"),
        new SimpleGrantedAuthority("ADMIN")
    );
}

    Now the user has both "USER" and "ADMIN" roles.
    They can access both user and admin functionalities.  
    getAuthorities() → Defines which roles a user has.
	Spring Security checks roles before allowing access to certain actions.
	SimpleGrantedAuthority("USER") → Gives the user the "USER" role.     
*/